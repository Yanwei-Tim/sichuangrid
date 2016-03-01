package com.tianque.plugin.dataManage.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unitils.database.annotations.Transactional;

import com.tianque.core.util.GridProperties;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.dataManage.util.Constants;
import com.tianque.plugin.dataManage.util.DataManageBaseInfo;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;

@Service("dataManageDBJobService")
@Transactional
public class DataManageDBJobServiceImpl implements DataManageDBJobService {
	@Autowired
	private DataManageDBJobDao dataManageDBJobDao;
	private Logger logger = Logger.getLogger(DataManageDBJobServiceImpl.class);
	private final static int pageSize = GridProperties.DATAMANAGEDBJOB_PAGESIZE;
	private final static int maxPage = GridProperties.DATAMANAGEDBJOB_MAXPAGE;

	@Override
	public void toDisposeDB() {
		List<DataManageBaseInfo> dataManageBaseInfos = null;
		try {
			dataManageBaseInfos = DataManageBaseInfoUtil.getXmlList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 1.取得所有的表
		if (null != dataManageBaseInfos) {
			List<Map> dataManageTables = getDataManageTables(dataManageBaseInfos);
			for (Map map : dataManageTables) {
				disposeDB(map);
			}
		}
	}

	/**
	 * 取得所有表
	 */
	private List<Map> getDataManageTables(List<DataManageBaseInfo> dataManageBaseInfos) {
		List<Map> dataManageTables = new ArrayList<Map>();
		Map<String, Object> map = null;
		for (DataManageBaseInfo dataManageBaseInfo : dataManageBaseInfos) {
			map = new HashMap<String, Object>();
			map.put("tableName", dataManageBaseInfo.getTable());
			map.put("mode", dataManageBaseInfo.getMode());
			dataManageTables.add(map);
		}
		return dataManageTables;
	}

	/**
	 * 对每一张表进行处理操作
	 */
	private void disposeDB(Map<String, Object> map) {
		// 2.查找是否有未处理的数据
		map.put("dispose", Constants.DisposeState.UNDISPOSE);
		int countUnDisposeData = dataManageDBJobDao.countUnDisposeData(map);
		if (countUnDisposeData > 0) {
			// 2.1查找未处理数据集合
			int pages = countUnDisposeData / pageSize;
			List<Map> unDisposeDataList;
			Long l = System.currentTimeMillis();
			for (int page = 0; page <= pages && page < maxPage; page++) {
				map.put("startRow", 0);
				map.put("endRow", pageSize);
				unDisposeDataList = dataManageDBJobDao.getUnDisposeData(map);
				if (unDisposeDataList != null) {
					for (Map<String, Object> unDisposeData : unDisposeDataList) {
						// 3.取出最早的一条未处理数据
						try {
							getRepeatData(unDisposeData, map);
						} catch (Exception e) {
							logger.error("数据管理" + map.get("tableName") + "表处理重复数据时出错："
									+ unDisposeData + e);
							continue;
						}
					}
				}
			}
			Long ll = System.currentTimeMillis();
			logger.info("操作未处理数据集合：" + (ll - l));

		}
	}

	/**
	 * 查找重复数据
	 */
	private void getRepeatData(Map<String, Object> firstUnDispose, Map<String, Object> map) {
		Map<String, Object> queryMap = getQueryMap(map, firstUnDispose);
		if ("DM_overseaPersonnel_Temp".equals(map.get("tableName"))) {
			queryMap.put("certificateType", firstUnDispose.get("CERTIFICATETYPE"));
			queryMap.put("certificateNo", firstUnDispose.get("CERTIFICATENO"));
		}
		// 4.获得重复数据
		Map<String, Object> repeatData = null;
		if (!DataManageBaseInfoTypes.DUSTBIN.equals(map.get("mode"))
				&& !DataManageBaseInfoTypes.BUILDDATAS.equals(map.get("mode"))) {
			repeatData = dataManageDBJobDao.getRepeatData(queryMap);
		}
		if (repeatData == null) {
			// 5.修改当前数据的处理状态和可认领状态
			Long l = System.currentTimeMillis();
			dataManageDBJobDao.updateCanClaimData(queryMap);
			Long ll = System.currentTimeMillis();
			logger.info("修改当前数据的处理状态和可认领状态：" + (ll - l));
		} else {
			// 4.1.处理重复数据
			disposeRepeatData(firstUnDispose, repeatData, map);
		}
	}

	/**
	 * 根据所属大类不同获取查询条件
	 */
	private Map<String, Object> getQueryMap(Map<String, Object> map,
			Map<String, Object> firstUnDispose) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", firstUnDispose.get("ID"));
		queryMap.put("tableName", map.get("tableName"));
		queryMap.put("orgValue", firstUnDispose.get("DISTRICTORGID"));
		queryMap.put("dispose", Constants.DisposeState.DISPOSED);
		queryMap.put("claimAvailable", Constants.ClaimAvailable.CLAIMAVAILABLE_CAN);
		if (DataManageBaseInfoTypes.POPULATION.equals(map.get("mode"))) {
			queryMap.put("uniqueColumn", "idCardNo");
			queryMap.put("uniqueValue", firstUnDispose.get("IDCARDNO"));
		} else if (DataManageBaseInfoTypes.HOUSE.equals(map.get("mode"))) {
			queryMap.put("uniqueColumn", "address");
			queryMap.put("uniqueValue", firstUnDispose.get("ADDRESS"));
		} else if (DataManageBaseInfoTypes.LOCATION.equals(map.get("mode"))) {
			queryMap.put("uniqueColumn", "name");
			queryMap.put("uniqueValue", firstUnDispose.get("NAME"));
		} else if (DataManageBaseInfoTypes.DUSTBIN.equals(map.get("mode"))) {
			// queryMap.put("uniqueColumn", "dustbinCode");
			// queryMap.put("uniqueValue", firstUnDispose.get("DUSTBINCODE"));
		} else if (DataManageBaseInfoTypes.BUILDDATAS.equals(map.get("mode"))) {
		} else if (DataManageBaseInfoTypes.COMPANYPLACE.equals(map.get("mode"))) {
			queryMap.put("uniqueColumn", "name");
			queryMap.put("uniqueValue", firstUnDispose.get("NAME"));
		} else {
			throw new BusinessValidationException("所属大类有误！");
		}
		return queryMap;
	}

	/**
	 * 按认领状态处理重复数据
	 * 
	 * @param firstUnDispose
	 * @param repeatData
	 */
	private void disposeRepeatData(Map<String, Object> firstUnDispose,
			Map<String, Object> repeatData, Map<String, Object> queryMap) {
		String tableName = (String) queryMap.get("tableName");
		String mode = (String) queryMap.get("mode");
		// 4.2.重复数据是否存在差异(true：有差异)
		Long l = System.currentTimeMillis();
		boolean hasDifferent = dataCompare(firstUnDispose, repeatData);
		Long ll = System.currentTimeMillis();
		logger.info("重复数据对比：" + (ll - l));
		if (hasDifferent) {
			// 4.2.1.处理未认领重复数据
			l = System.currentTimeMillis();
			disposeRepeatData(firstUnDispose, repeatData, tableName);
			ll = System.currentTimeMillis();
			logger.info("处理不一致重复数据：" + (ll - l));
			// 4.2.1.2修改最新数据的可认领状态
			queryMap = getRepeatDataQueryMap(firstUnDispose, repeatData);
		} else {
			logger.info("处理完全重复数据");
			queryMap = getNoDiffrentRepeatDataQueryMap(firstUnDispose, repeatData);
		}
		l = System.currentTimeMillis();
		queryMap.put("dispose", Constants.DisposeState.DISPOSED);
		queryMap.put("tableName", tableName);
		dataManageDBJobDao.updateRepeatData(queryMap);
		if (Constants.ClaimState.BECLAIMED.intValue() == ((BigDecimal) repeatData.get("CLAIMSTATE"))
				.intValue()) {
			Map<String, Object> query = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableName", tableName);
			map.put("mode", mode);
			query = getQueryMap(map, repeatData);
			query.put("claimAvailable", Constants.ClaimAvailable.CLAIMAVAILABLE_CANT);
			query.put("id", firstUnDispose.get("ID"));
			query.put("claimState", Constants.ClaimState.BEUNCLAIM);
			dataManageDBJobDao.updateBeClaimRepeatData(query);
		}
		ll = System.currentTimeMillis();
		logger.info("修改最新数据的可认领状态：" + (ll - l));
	}

	/**
	 * 数据对比
	 */
	private static boolean dataCompare(Map<String, Object> firstUnDispose,
			Map<String, Object> repeatData) {
		for (String key : firstUnDispose.keySet()) {
			if (key.equals("ID") || key.startsWith("CLAIM") || key.equals("DISPOSE")
					|| key.equals("IMPORTDEPARTMENTID") || key.equals("OLDORGID")
					|| key.equals("INID") || key.equals("CREATEDATE") || key.equals("UPDATEDATE")
					|| key.equals("CREATEUSER") || key.equals("UPDATEUSER")
					|| key.equals("IMPORTTIME") || key.equals("BIRTHDAY")
					|| key.equals("ORGINTERNALCODE") || key.equals("ORGID")) {
				continue;
			}
			if (firstUnDispose.get(key) == null || repeatData.get(key) == null) {
				if (firstUnDispose.get(key) == null && repeatData.get(key) == null) {
					continue;
				}
				return true;
			}
			if (!firstUnDispose.get(key).toString().equals(repeatData.get(key).toString())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取无差异重复数据的map
	 * 
	 * @param firstUnDispose
	 * @param repeatData
	 * @return
	 */
	private Map<String, Object> getNoDiffrentRepeatDataQueryMap(Map<String, Object> firstUnDispose,
			Map<String, Object> repeatData) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", firstUnDispose.get("ID"));
		queryMap.put("claimAvailable", "" + Constants.ClaimAvailable.CLAIMAVAILABLE_CANT);
		queryMap.put(
				"claimState",
				((BigDecimal) (repeatData.get("CLAIMSTATE"))).intValue() == (Constants.ClaimState.UNCLAIM)
						.intValue() ? "" + Constants.ClaimState.BEUNCLAIM : ""
						+ Constants.ClaimState.BECLAIMED);
		return queryMap;
	}

	/**
	 * 处理重复数据
	 * 
	 * @param firstUnDispose
	 * @param repeatData
	 */
	private void disposeRepeatData(Map<String, Object> firstUnDispose,
			Map<String, Object> repeatData, String tableName) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		// 4.2.1.1修改重复数据的可认领状态
		queryMap.put("tableName", tableName);
		queryMap.put("id", repeatData.get("ID"));
		queryMap.put("claimAvailable", "" + Constants.ClaimAvailable.CLAIMAVAILABLE_CANT);
		queryMap.put("claimState", "" + Constants.ClaimState.BEUNCLAIM);
		queryMap.put("dispose", Constants.DisposeState.DISPOSED);
		dataManageDBJobDao.updateRepeatData(queryMap);
	}

	/**
	 * 获得重复数据的map
	 * 
	 * @param firstUnDispose
	 * @param repeatData
	 * @return
	 */
	private Map<String, Object> getRepeatDataQueryMap(Map<String, Object> firstUnDispose,
			Map<String, Object> repeatData) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", firstUnDispose.get("ID"));
		queryMap.put("claimAvailable", "" + Constants.ClaimAvailable.CLAIMAVAILABLE_CAN);
		queryMap.put("claimState", "" + Constants.ClaimState.UNCLAIM);
		return queryMap;
	}
}