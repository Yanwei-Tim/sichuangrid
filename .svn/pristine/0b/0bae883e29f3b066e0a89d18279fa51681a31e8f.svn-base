package com.tianque.plugin.dataManage.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.dataManage.base.ReflectionUtil;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;
import com.tianque.plugin.dataManage.base.vo.SearchVo;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;

@Repository("abstractDataManageDao")
public class AbstractDataManageDaoImp<T extends BaseDomain> extends
		AbstractBaseDao implements AbstractDataManageDao<T> {

	@Override
	public PageInfo findTemps(Class t, SearchVo searchVo, Integer page,
			Integer rows) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				getCountListSqlId(t), searchVo);
		List<T> resultList = getSqlMapClientTemplate().queryForList(
				getListSqlId(t), searchVo, (page - 1) * rows, rows);
		return new PageInfo(page, rows, count, resultList);
	}

	@Override
	public Long addTemp(T temp) {
		return (Long) getSqlMapClientTemplate().insert(
				getInsertIntoSqlId(temp), temp);
	}

	@Override
	public void updateTempBase(T temp) {
		getSqlMapClientTemplate().update(getUpdateSqlId(temp) + "Base", temp);
	}

	@Override
	public void updateTempBusiness(T temp) {
		getSqlMapClientTemplate().update(getUpdateSqlId(temp) + "Business",
				temp);
	}

	@Override
	public void updateLocationTempBase(T temp) {
		getSqlMapClientTemplate().update(getUpdateSqlId(temp) + "Info", temp);
	}

	@Override
	public void updateDustbinTempBase(T temp) {
		getSqlMapClientTemplate().update(getUpdateSqlId(temp) + "Info", temp);
	}

	@Override
	public void updateBuilddatasTempBase(T temp) {
		getSqlMapClientTemplate().update(getUpdateSqlId(temp) + "Info", temp);
	}

	@Override
	public T getTempById(Class t, Long id) {
		return (T) getSqlMapClientTemplate().queryForObject(
				getGetTempByIdSqlId(t), id);
	}

	@Override
	public void updateClaimDetail(T temp, ClaimDetail claimDetail) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", ((BaseDomain) temp).getId());

		map.put("claimState", claimDetail.getClaimState());
		map.put("claimDate", claimDetail.getClaimDate());
		if (claimDetail.getClaimUser() == null) {
			map.put("claimUserId", null);
			map.put("claimUserName", null);
		} else {
			map.put("claimUserId", claimDetail.getClaimUser().getId());
			map.put("claimUserName", claimDetail.getClaimUser().getName());
		}
		if (claimDetail.getClaimOrg() == null) {
			map.put("claimOrgId", null);
		} else {
			map.put("claimOrgId", claimDetail.getClaimOrg().getId());
		}

		map.put("inId", claimDetail.getInId());

		getSqlMapClientTemplate().update(getUpdateClaimDetailSqlId(temp), map);

	}

	/** 获得修改ClaimDetail的sql（如：druggyTemp.updateDruggyTempClaimDetail） */
	private String getUpdateClaimDetailSqlId(T population) {
		return lowerFirst(population.getClass().getSimpleName()) + ".update"
				+ population.getClass().getSimpleName() + "ClaimDetail";
	}

	/** 获得根据id获取对象的sql（如：druggyTemp.getTempById） */
	private String getGetTempByIdSqlId(Class t) {
		String className = lowerFirst(t.getSimpleName()) + "." + "get"
				+ t.getSimpleName() + "ById";
		return className;
	}

	/** 获得所要的列表显示sql（如：druggyTemp.findTemps） */
	private String getListSqlId(Class t) {
		String className = lowerFirst(t.getSimpleName()) + "." + "findTemps";
		return className;
	}

	/** 获得所要的列表count的显示sql（如：druggyTemp.countFindTemps） */
	private String getCountListSqlId(Class t) {
		String className = lowerFirst(t.getSimpleName()) + "."
				+ "countFindTemps";
		return className;
	}

	/** 获得所要的新增sql（如：druggyTemp.addDruggyTemp） */
	private String getInsertIntoSqlId(T temp) {
		String simpleName = temp.getClass().getSimpleName();
		return lowerFirst(simpleName) + ".add" + simpleName;
	}

	/** 获得所要的修改sql（如：druggyTemp.updateDruggyTemp） */
	private String getUpdateSqlId(T temp) {
		String simpleName = temp.getClass().getSimpleName();
		return lowerFirst(simpleName) + ".update" + simpleName;
	}

	/** 获得类的名称首字母变小写后的字符 */
	private String lowerFirst(String className) {
		return className.substring(0, 1).toLowerCase() + className.substring(1);
	}

	@Override
	public Integer stepClaimTempByIds(String table, Long[] tempIds,
			Long targertOrgId, String orgCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tabel", table);
		map.put("orgId", targertOrgId);
		map.put("orgCode", orgCode);
		map.put("ids", tempIds);
		if (table.equals("DM_COMPANYPLACE_TEMP")) {
			return getSqlMapClientTemplate().update(
					"dataManage.stepClaimTempCompanyPlaceByIds", map);
		} else {
			return getSqlMapClientTemplate().update(
					"dataManage.stepClaimTempByIds", map);
		}
	}

	@Override
	public void deleteTempById(Class t, Long id) {
		getSqlMapClientTemplate().delete(getDeleteTempByIdSqlId(t), id);
	}

	private String getDeleteTempByIdSqlId(Class T) {
		String className = lowerFirst(T.getSimpleName()) + "." + "delete"
				+ T.getSimpleName() + "ById";
		return className;
	}

	@Override
	public T getLocationByNameAndOrgId(T temp) {
		return (T) getSqlMapClientTemplate().queryForObject(
				getSelectSqlId(temp) + "ByTemp", temp);
	}

	/**
	 * 获得所要的查询sql（如：internetBar.getInternetBar）
	 */
	private String getSelectSqlId(T temp) {
		String simpleName = temp.getClass().getSimpleName();
		return lowerFirst(simpleName) + ".get" + simpleName;
	}

	@Override
	public Map<String, Object> hasDuplicate(Map<String, Object> queryMap) {
		return (Map<String, Object>) getSqlMapClientTemplate().queryForObject(
				"dataManageDBJob.getRepeatData", queryMap);
	}

	@Override
	public void unDoClaimDetailByTableAndIds(String table, Long[] ids,
			ClaimDetail detail) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tabel", table);
		map.put("ids", ids);
		map.put("claimDetail", detail);
		map.put("oldorgid", detail.getDistrictOrgId());
		if (table.equals("DM_COMPANYPLACE_TEMP")) {
			getSqlMapClientTemplate().update(
					"dataManage.unDoClaimDetailCompanyPlaceByTableAndIds", map);
		} else {
			getSqlMapClientTemplate().update(
					"dataManage.unDoClaimDetailByTableAndIds", map);
		}
	}

	@Override
	public void updateTheSamePopulationPassiveClaim(T temp,
			ClaimDetail passiveClaim) {
		String type = temp.getClass().getSimpleName();
		String talbe = DataManageBaseInfoUtil.getTableByType(type);
		String uniqueColumn = DataManageBaseInfoUtil.getUniqueColumn(type);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("table", talbe);
		if ("DM_overseaPersonnel_Temp".equals(talbe)) {
			Long certificateType = 0L;
			String certificateNo = null;
			try {
				certificateType = ReflectionUtil.getTargetCertificateType(temp)
						.getId();
				certificateNo = ReflectionUtil.getTargetCertificateNo(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("certificateType", certificateType);
			map.put("certificateNo", certificateNo);
		} else {
			map.put("uniqueColumn", uniqueColumn);
			map.put("uniqueValue",
					ReflectionUtil.executeTargetGetMethod(temp, "get"
							+ StringUtil.firstCharUpperCase(uniqueColumn)));
		}
		map.put("claimDetail", passiveClaim);
		map.put("id", temp.getId());

		getSqlMapClientTemplate().update(
				"dataManage.updateTheSamePopulationPassiveClaim", map);
	}

	@Override
	public void updateTempDataOrgId(Map map) {
		if (map.get("tableName").equals("DM_COMPANYPLACE_TEMP")) {
			getSqlMapClientTemplate().update(
					"dataManage.updateCompanyPlaceOrgId", map);
		} else {
			getSqlMapClientTemplate().update("dataManage.updateTempDataOrgId",
					map);
		}
	}
}
