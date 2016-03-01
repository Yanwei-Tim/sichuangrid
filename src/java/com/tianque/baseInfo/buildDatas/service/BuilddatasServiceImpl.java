package com.tianque.baseInfo.buildDatas.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualCompany.dao.ActualCompanyDao;
import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.baseInfo.actualHouse.dao.ActualHouseDao;
import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.buildDatas.dao.BuildLayoutDao;
import com.tianque.baseInfo.buildDatas.dao.BuilddatasDao;
import com.tianque.baseInfo.buildDatas.dao.HouseBuilddatasBindingDao;
import com.tianque.baseInfo.buildDatas.domain.Builddatas;
import com.tianque.baseInfo.buildDatas.domain.vo.BuilddatasSearchVo;
import com.tianque.baseInfo.buildDatas.service.BuilddatasService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModelType;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.KeyPlaceDao;
import com.tianque.domain.KeyPlace;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.openLayersMap.service.HourseInfoService;
import com.tianque.openLayersMap.util.GisTransformatUtil;
import com.tianque.plugin.tqSearch.sqlMap.DeleteSqlMap;
import com.tianque.plugin.tqSearch.sqlMap.UpdateSqlMap;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Service("builddatasService")
public class BuilddatasServiceImpl implements BuilddatasService {

	private static Logger logger = LoggerFactory
			.getLogger(BuilddatasServiceImpl.class);
	@Autowired
	private BuilddatasDao builddatasDao;
	@Autowired
	private BuildLayoutDao buildLayoutDao;
	@Autowired
	private ActualHouseDao houseInfoDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private HouseBuilddatasBindingDao houseBuilddatasBindingDao;
	@Autowired
	private KeyPlaceDao keyPlaceDao;
	@Autowired
	private ActualCompanyDao actualCompanyDao;
	@Autowired
	private HourseInfoService hourseInfoService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private ShardConversion shardConversion;

	@Override
	public PageInfo<Builddatas> findBuilddatasByOrgInternalCode(
			String orgInternalCode, Integer isBind, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		return this.builddatasDao.findBuilddatasByOrgInternalCode(
				orgInternalCode, isBind, pageNum, pageSize, sortField, order);
	}

	@Override
	public Builddatas addBuilddatas(Builddatas builddatas) {
		try {
			validate(builddatas);

			GisTransformatUtil.autoFillOpenLayersMapInfo(builddatas
					.getOpenLayersMapInfo());
			Organization org = organizationDubboService
					.getSimpleOrgById(builddatas.getOrganization().getId());
			if (org == null) {
				throw new BusinessValidationException("找不到指定的网格");
			}
			if (builddatas.getOpenLayersMapInfo() != null
					&& StringUtil.isStringAvaliable(builddatas
							.getOpenLayersMapInfo().getFeatureId())) {
				Long hourseInfoId = hourseInfoService.getHourseInfoByFeatureId(
						builddatas.getOpenLayersMapInfo().getFeatureId())
						.getId();
				builddatas.setBuildingid(hourseInfoId.toString());
			}
			builddatas = this.builddatasDao.addBuilddatas(builddatas);
			systemLogService.log(
					com.tianque.core.logger.Logger.INFO,
					ModelType.BUILDDATA,
					OperatorType.ADD,
					ThreadVariable.getSession().getUserName()
							+ OperatorType.toString(OperatorType.ADD)
							+ ModelType.BUILDDATA
							+ "位于"
							+ ControllerHelper.getOrganizationRelativeName(
									builddatas.getOrganization().getId(),
									organizationDubboService) + "下的"
							+ builddatas.getBuildingname(), null);
			return builddatas;
		} catch (Exception e) {
			logger.error("BuilddatasServiceImpl addBuilddatas", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增楼宇信息出现错误");
			} else {
				return null;
			}
		}
	}

	@Override
	@SolrServerIndex(mode = OperateMode.EDIT, value = UpdateSqlMap.BUILDING_KEY)
	public Builddatas updateBuilddatas(Builddatas builddatas) {
		try {
			if (builddatas == null || builddatas.getId() == null) {
				throw new BusinessValidationException("请选中一条记录");
			}
			if (builddatas.getIsPropertyManagement() == null) {
				builddatas.setIsPropertyManagement(false);
			}

			validate(builddatas);
			GisTransformatUtil.autoFillOpenLayersMapInfo(builddatas
					.getOpenLayersMapInfo());
			Organization org = organizationDubboService
					.getSimpleOrgById(builddatas.getOrganization().getId());
			if (org == null) {
				throw new BusinessValidationException("找不到指定的网格");
			}
			if (builddatas.getOpenLayersMapInfo() != null
					&& StringUtil.isStringAvaliable(builddatas
							.getOpenLayersMapInfo().getFeatureId())) {
				Long hourseInfoId = hourseInfoService.getHourseInfoByFeatureId(
						builddatas.getOpenLayersMapInfo().getFeatureId())
						.getId();
				builddatas.setBuildingid(hourseInfoId.toString());
			}
			Builddatas oldBuilddatas = this.builddatasDao
					.getBuilddatasById(builddatas.getId());
			builddatas = this.builddatasDao.updateBuilddatas(builddatas);

			String shardCode = shardConversion.getShardCode(builddatas
					.getOrganization());
			// 更新建筑物名称与住房的建筑物名称同步
			List<HouseInfo> houseInfos = houseInfoDao
					.findHouseInfosListByBuildingId(builddatas.getId(),
							shardCode);
			for (HouseInfo houseInfo : houseInfos) {
				houseInfoDao.synchronizationBuildName(houseInfo.getId(),
						builddatas.getBuildingname(), builddatas
								.getOpenLayersMapInfo(), shardConversion
								.getShardCode(houseInfo.getOrganization()));
			}
			systemLogService.log(
					com.tianque.core.logger.Logger.INFO,
					ModelType.BUILDDATA,
					OperatorType.UPDATE,
					ThreadVariable.getSession().getUserName()
							+ OperatorType.toString(OperatorType.UPDATE)
							+ ModelType.BUILDDATA
							+ "位于"
							+ ControllerHelper.getOrganizationRelativeName(
									builddatas.getOrganization().getId(),
									organizationDubboService)
							+ "下的"
							+ (oldBuilddatas.getBuildingname().equals(
									builddatas.getBuildingname()) ? builddatas
									.getBuildingname() : oldBuilddatas
									.getBuildingname()
									+ "->"
									+ builddatas.getBuildingname()),
					ObjectToJSON.convertJSON(oldBuilddatas),
					oldBuilddatas.getId() + "", builddatas.getId() + "",
					oldBuilddatas.getBuildingname(), builddatas
							.getBuildingname());
			// TODO 更新楼宇时，需要对应的更新楼宇绑定的房屋的坐标信息
			return builddatas;
		} catch (Exception e) {
			logger.error("BuilddatasServiceImpl updateBuilddatas", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("更新楼宇信息出现错误");
			} else {
				return null;
			}
		}
	}

	@Override
	public Builddatas getBuilddatasById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("请选中一条记录");
		}
		return this.builddatasDao.getBuilddatasById(id);
	}

	@Override
	@SolrServerIndex(mode = OperateMode.DELETE, value = DeleteSqlMap.BUILDDATAS_KEY)
	public List<Long> deleteMultiBuilddatas(List<Long> idList) {
		if (idList == null || idList.size() == 0) {
			throw new BusinessValidationException("请选中一条或者多条要删除的记录");
		}
		try {
			for (Long id : idList) {
				Builddatas oldBuilddatas = this.builddatasDao
						.getBuilddatasById(id);
				String shardCode = shardConversion.getShardCode(oldBuilddatas
						.getOrganization());
				deleteBuilddatasById(id, shardCode);
				systemLogService.log(
						com.tianque.core.logger.Logger.INFO,
						ModelType.BUILDDATA,
						OperatorType.DELETE,
						ThreadVariable.getSession().getUserName()
								+ OperatorType.toString(OperatorType.DELETE)
								+ ModelType.BUILDDATA
								+ "位于"
								+ ControllerHelper
										.getOrganizationRelativeName(
												oldBuilddatas.getOrganization()
														.getId(),
												organizationDubboService)
								+ "下的" + oldBuilddatas.getBuildingname(),
						ObjectToJSON.convertJSON(oldBuilddatas), id + "", "",
						"", "");
			}
		} catch (Exception e) {
			throw new ServiceValidationException("删除楼宇出错", e);
		}

		return idList;
	}

	public void deleteBuilddatasByIds(Long[] ids, String shardCode) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("请选中一条或者多条要删除的记录");
		}
		for (Long id : ids) {
			deleteBuilddatasById(id, shardCode);
		}
	}

	@Override
	@SolrServerIndex(mode = OperateMode.DELETE, value = DeleteSqlMap.BUILDDATAS_KEY)
	public void deleteBuilddatasById(Long id, String shardCode) {
		if (id == null) {
			throw new BusinessValidationException("请选中一条记录");
		}
		// 删除楼宇
		builddatasDao.deleteBuilddatasById(id);
		// 删除楼宇布局
		buildLayoutDao.deleteBuildLayoutByBuildId(id);
		// 更新房屋楼宇绑定信息
		List<HouseInfo> houseInfoList = houseInfoDao
				.findHouseInfosListByBuildingId(id, shardCode);
		for (HouseInfo houseInfo : houseInfoList) {
			houseInfo.setBuilddatasId(null);
			houseInfo.setOpenLayersMapInfo(null);
			houseInfoDao.updateBuildDatasId(houseInfo, shardCode);
			houseInfoDao.synchronizationBuildName(houseInfo.getId(), "",
					houseInfo.getOpenLayersMapInfo(), shardCode);
		}

		List<KeyPlace> keyPlaceList = keyPlaceDao
				.findKeyPlaceListByBuilddatasId(id);

		List<Long> keyPlaceIds = new ArrayList<Long>();
		for (KeyPlace keyPlace : keyPlaceList) {
			keyPlace.setBuildDatasId(null);
			keyPlaceDao.updateKeyPlace(keyPlace);
			keyPlaceIds.add(keyPlace.getId());
		}
		keyPlaceDao.unboundKeyPlace(keyPlaceIds);

		List<ActualCompany> actualCompanyList = actualCompanyDao
				.findActualCompanyListByBuildingId(id);

		for (ActualCompany actualCompany : actualCompanyList) {
			if (actualCompany.getGisInfo() != null) {
				actualCompany.getGisInfo().setBuildingId(null);
			}
			actualCompanyDao.updateactualCompanyInfoForGis(actualCompany);
		}

	}

	@Override
	public Boolean deleteLayoutAndUpdateBuilddatasByBuildingId(Long id) {
		buildLayoutDao.deleteBuildLayoutByBuildId(id);
		Builddatas builddatas = builddatasDao.getBuilddatasById(id);
		builddatas.setIsLayout(0L);
		updateBuilddatas(builddatas);
		return true;
	}

	@Override
	public PageInfo<Builddatas> searchBuilddatas(
			BuilddatasSearchVo builddatasSearchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return builddatasDao.searchBuilddatas(builddatasSearchVo, page, rows,
				sidx, sord);
	}

	@Override
	public Builddatas getBuilddatasByBuildId(String buildId) {
		if (buildId == null) {
			throw new BusinessValidationException("请选中楼宇");
		}
		return this.builddatasDao.getBuilddatasByBuildId(buildId);
	}

	private void validate(Builddatas builddatas) {

		if (builddatas == null) {
			throw new BusinessValidationException("参数不能为空");
		}

		boolean orgIsNotNull = validateOrgIsNotNull(builddatas
				.getOrganization());
		if (!orgIsNotNull) {
			throw new BusinessValidationException("所属网格不能为空");
		}

		if (validateHelper.emptyString(builddatas.getBuildingname())) {
			throw new BusinessValidationException("楼宇名称不能为空");
		}

		if (validateHelper.illegalStringLength(0, 50,
				builddatas.getBuildingname())) {
			throw new BusinessValidationException("楼宇名称不能输入大于50字符");
		}

		if (validateHelper.emptyString(builddatas.getBuildingaddress())) {
			throw new BusinessValidationException("楼宇地址不能为空");
		}

		if (validateHelper.illegalStringLength(0, 50,
				builddatas.getBuildingaddress())) {
			throw new BusinessValidationException("楼宇地址不能输入大于50字符");
		}

		if (builddatas.getBuildingstructures() == null
				|| builddatas.getBuildingstructures().getId() == null) {
			throw new BusinessValidationException("建筑结构不能为空");
		}
		if (builddatas.getType() == null
				|| builddatas.getType().getId() == null) {
			throw new BusinessValidationException("楼宇类型不能为空");
		}

	}

	private boolean validateOrgIsNotNull(Organization org) {
		if (org == null) {
			return false;
		}
		if (org != null && org.getId() == null) {
			return false;
		}
		return true;
	}

	@Override
	public PageInfo<Builddatas> findUnBoundBuilddatasByStr(String str,
			Integer pageNum, Integer pageSize, String sortField, String order,
			Long orgId) {
		if (str == null) {
			throw new BusinessValidationException("查询字符串不能为空！");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		return builddatasDao.findUnBoundBuilddatasByStr(str, pageNum, pageSize,
				sortField, order, org.getOrgInternalCode());
	}

	// 根据坐标集统计楼宇数量
	@Override
	public Integer countBoundBuildDatasByLonAndLatArrays(
			Double[] lonAndLatArrays) {
		if (lonAndLatArrays == null) {
			throw new BusinessValidationException("参数传入错误");
		}
		checkForPoint(lonAndLatArrays);
		return builddatasDao
				.countBoundBuildDatasByLonAndLatArrays(lonAndLatArrays);
	}

	/**
	 * 检查经纬度是否存为空
	 * 
	 * @param maxX
	 * @param minX
	 * @param maxY
	 * @param minY
	 */
	private void checkForPoint(Double[] lonAndLatArrays) {
		if (lonAndLatArrays[0] == null) {
			throw new BusinessValidationException("最大经度不能为空");
		}
		if (lonAndLatArrays[2] == null) {
			throw new BusinessValidationException("最小经度不能为空");
		}
		if (lonAndLatArrays[1] == null) {
			throw new BusinessValidationException("最大纬度不能为空");
		}
		if (lonAndLatArrays[3] == null) {
			throw new BusinessValidationException("最大纬度不能为空");
		}
	}

	@Override
	public Integer countBuildDatasByLonAndLatArrays(Double[] lonAndLatArrays) {
		if (lonAndLatArrays == null) {
			throw new BusinessValidationException("参数传入错误");
		}
		checkForPoint(lonAndLatArrays);
		return builddatasDao.countBuildDatasByLonAndLatArrays(lonAndLatArrays);
	}

	@Override
	public Integer countBoundBuildDatasByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("参数传入错误");
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		return builddatasDao.countBoundBuildDatasByOrgCode(organization
				.getOrgInternalCode());
	}

	public Integer countBoundBuildDatas(String orgCode) {
		if (orgCode == null) {
			throw new BusinessValidationException("orgCode不能为空");
		}
		return builddatasDao.countBoundBuildDatas(orgCode);
	}

	public Integer countUnBoundBuilddatasByOrgCode(String orgCode) {
		if (orgCode == null) {
			throw new BusinessValidationException("orgCode不能为空");
		}
		return builddatasDao.countUnBoundBuilddatasByOrgCode(orgCode);
	}

	@Override
	public PageInfo<HouseInfo> findHouseInfosByBuilddatasIdForPage(
			Long builddatasId, int page, int rows, String sidx, String sord) {
		return houseBuilddatasBindingDao.findHouseInfosByBuilddatasId(
				builddatasId, page, rows, sidx, sord);
	}

	@Override
	public PageInfo<HouseInfo> findUnBoundedByOrgId(Long orgId, int page,
			int rows, String sidx, String sord, String queryStr) {
		return houseBuilddatasBindingDao
				.findUnBoundedByOrgInternalCode(organizationDubboService
						.getSimpleOrgById(orgId).getOrgInternalCode(), page,
						rows, sidx, sord, queryStr);
	}

	@Override
	public void boundBuilddatas(Builddatas builddatas) {
		builddatasDao.boundBuilddatas(builddatas);
		String shardCode = shardConversion.getShardCode(builddatas
				.getOrganization());
		List<HouseInfo> houseInfoList = houseInfoDao
				.findHouseInfosListByBuildingId(builddatas.getId(), shardCode);
		for (HouseInfo houseInfo : houseInfoList) {
			houseInfoDao.bound(houseInfo.getId(), builddatas
					.getOpenLayersMapInfo().getCenterLat(), builddatas
					.getOpenLayersMapInfo().getCenterLon(), shardConversion
					.getShardCode(houseInfo.getOrganization()));
		}

	}

	@Override
	public void unboundBuilddatas(Builddatas builddatas) {
		builddatasDao.unboundBuilddatas(builddatas);
		String shardCode = shardConversion.getShardCode(builddatas
				.getOrganization());
		List<HouseInfo> houseInfoList = houseInfoDao
				.findHouseInfosListByBuildingId(builddatas.getId(), shardCode);
		for (HouseInfo houseInfo : houseInfoList) {
			houseInfoDao.unbound(houseInfo.getId(),
					shardConversion.getShardCode(houseInfo.getOrganization()));
		}
	}

	@Override
	public PageInfo<Builddatas> searchBuilddatasByNameAndAddress(
			BuilddatasSearchVo builddatasSearchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return builddatasDao.searchBuilddatasByNameAndAddress(
				builddatasSearchVo, page, rows, sidx, sord);
	}

	@Override
	public List<Builddatas> findBuildDatasByBuildId(String builId) {

		return builddatasDao.findBuildDatasByBuildId(builId);
	}

}
