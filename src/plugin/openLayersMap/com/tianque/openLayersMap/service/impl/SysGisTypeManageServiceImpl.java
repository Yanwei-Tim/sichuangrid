package com.tianque.openLayersMap.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.gis.util.GisGlobalValue;
import com.tianque.openLayersMap.dao.SysGisTypeManageDao;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.KeyPersonService;
import com.tianque.openLayersMap.service.KeyPlaceService;
import com.tianque.openLayersMap.service.SysGisTypeManageService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;

/**
 * 子类类别管理实现类
 * 
 * @author yubin
 * 
 */
@Transactional
@Service("sysGisTypeManageService")
public class SysGisTypeManageServiceImpl extends BaseService implements
		SysGisTypeManageService {
	@Autowired
	private SysGisTypeManageDao sysGisTypeManageDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private KeyPersonService keyPersonService;
	@Autowired
	private KeyPlaceService keyPlaceService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private PermissionService permissionService;

	@Override
	public GisTypeManage addGisTypeManage(GisTypeManage domain) {
		validateGisTypeManage(domain);
		return sysGisTypeManageDao.addGisTypeManage(domain);
	}

	@Override
	public PageInfo<GisTypeManage> findGisTypesForPageByInnerType(int pageNum,
			int pageSize, GisTypeManage domain) {
		if (domain == null || domain.getInnerKey() == null) {
			throw new BusinessValidationException("key为空");
		}
		List<GisTypeManage> list = sysGisTypeManageDao.findGisTypesByInnerType(
				domain, pageNum, pageSize);
		Integer countNum = sysGisTypeManageDao.countGisTypesByInnerType(domain);
		return new PageInfo<GisTypeManage>(pageNum, pageSize, countNum, list);
	}

	@Override
	public GisTypeManage getGisTypeManageById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id为空");
		}
		return sysGisTypeManageDao.findGisTypeManageById(id);
	}

	@Override
	public GisTypeManage updateGisTypeManage(GisTypeManage domain) {
		validateGisTypeManage(domain);
		return sysGisTypeManageDao.updateGisTypeManage(domain);
	}

	@Override
	public void deleteGisTypeManageById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id为空");
		}
		sysGisTypeManageDao.deleteGisTypeManageById(id);
	}

	@Override
	public List<GisTypeManage> getNeedGisTypeManagesByInnerType(
			GisTypeManage domain) {
		if (domain == null || domain.getInnerKey() == null) {
			throw new BusinessValidationException("innerKey为空");
		}
		List<GisTypeManage> gisTypeManageList = sysGisTypeManageDao
				.getNeedGisTypeManagesByInnerType(domain);
		// PermissionFilter.filterPermission(gisTypeManageList,
		// ThreadVariable.getUser().getId(), permissionService);
		return gisTypeManageList;
	}

	@Override
	public List<StatisticInfoVo> getNeedGisTypeManagesByInnerTypeAndOrgId(
			GisTypeManage domain, Long orgId) {
		if (domain == null || domain.getInnerKey() == null || orgId == null) {
			throw new BusinessValidationException("innerKey为空或orgId为空");
		}
		List<GisTypeManage> gisTypeManageList = sysGisTypeManageDao
				.getNeedGisTypeManagesByInnerType(domain);

		List<StatisticInfoVo> satisticInfoVoList = new ArrayList<StatisticInfoVo>();

		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		// 得到子类个数
		for (int i = 0; i < gisTypeManageList.size(); i++) {
			String key = gisTypeManageList.get(i).getInnerKey();
			if (key.equals(GisGlobalValue.PERSON_MODE)) {// 重点人员
				String type = gisTypeManageList.get(i).getTableName();
				Integer countKeyPerson = keyPersonService
						.countTwoDimensionMapPageInfoByOrgInternalCodeAndTableName(
								organization.getOrgInternalCode(), type);
				StatisticInfoVo satisticInfoVo = new StatisticInfoVo();
				satisticInfoVo.setSumNum(countKeyPerson);
				satisticInfoVo.setTypeName(type);
				satisticInfoVo.setGisTypeManage(gisTypeManageList.get(i));
				satisticInfoVoList.add(satisticInfoVo);
			} else {// 重点场所
				String type = gisTypeManageList.get(i).getKey();
				Integer countNum = keyPlaceService
						.countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
								organization.getOrgInternalCode(), type);
				StatisticInfoVo satisticInfoVo = new StatisticInfoVo();
				satisticInfoVo.setSumNum(countNum);
				satisticInfoVo.setTypeName(type);
				satisticInfoVo.setGisTypeManage(gisTypeManageList.get(i));
				satisticInfoVoList.add(satisticInfoVo);
			}

		}
		return satisticInfoVoList;
	}

	@Override
	public Boolean isHasDuplicateGisTypeManage(GisTypeManage domain) {
		if (domain == null) {
			throw new BusinessValidationException("对象为空");
		}
		return sysGisTypeManageDao.isHasDuplicateGisTypeManage(domain);
	}

	@Override
	public GisTypeManage getGisTypeManagesByKey(GisTypeManage domain) {
		if (domain == null || domain.getKey() == null) {
			throw new BusinessValidationException("key为空");
		}
		return sysGisTypeManageDao.getGisTypeManagesByKey(domain);
	}

	@Override
	public GisTypeManage getGisTypeManagesByTableName(GisTypeManage domain) {
		if (domain == null || domain.getTableName() == null) {
			throw new BusinessValidationException("key为空");
		}
		return sysGisTypeManageDao.getGisTypeManagesByTableName(domain);
	}

	@Override
	public PageInfo<GisTypeManage> findGisTypesByInnerTypeOfNull(
			Integer pageSize, Integer pageNum) {
		if (pageSize == null || pageNum == null) {
			throw new BusinessValidationException("参数不能空!");
		}
		List<GisTypeManage> list = sysGisTypeManageDao
				.findGisTypesByInnerTypeOfNull(pageSize, pageNum);
		Integer countNum = sysGisTypeManageDao.countGisTypesByInnerTypeOfNull();
		return new PageInfo<GisTypeManage>(pageNum, pageSize, countNum, list);
	}

	@Override
	public void deleteGisTypeManageByInnerKeyOfNull() {
		sysGisTypeManageDao.deleteGisTypeManageByInnerKeyOfNull();
	}

	@Override
	public GisTypeManage getGisTypeManageByTableNameAndKeyType(
			String tableName, String keyType) {
		if (tableName == null) {
			throw new BusinessValidationException("参数不能空!");
		}
		return sysGisTypeManageDao.getGisTypeManageByTableNameAndKeyType(
				tableName, keyType);
	}

	/**
	 * 子类对象校验
	 * 
	 * @param domain
	 */
	private void validateGisTypeManage(GisTypeManage domain) {
		if (null == domain) {
			throw new BusinessValidationException("子类对象不能为空!");
		} else if (validateHelper.illegalStringLength(1, 20, domain.getName())) {
			throw new BusinessValidationException("类型名称只能输入1-20个字符!");
		} else if (validateHelper.illegalStringLength(1, 30,
				domain.getTableName())) {
			throw new BusinessValidationException("表名只能输入1-30个字符!");
		} else if (validateHelper.illegalStringLength(1, 30, domain.getKey())) {
			throw new BusinessValidationException("类型只能输入1-20个字符!");
		} else if (StringUtil.isStringAvaliable(domain.getInnerKey())
				&& validateHelper.illegalStringLength(1, 60,
						domain.getInnerKey())) {
			throw new BusinessValidationException("类型只能输入1-60个字符!");
		} else if (StringUtil.isStringAvaliable(domain.getOrgFiled())
				&& validateHelper.illegalStringLength(1, 60,
						domain.getOrgFiled())) {
			throw new BusinessValidationException("所属网格字段只能输入1-60个字符!");
		}
	}

	@Override
	public GisTypeManage updateGisTypeManageStatus(GisTypeManage domain) {
		if (domain == null || domain.getId() == null) {
			throw new BusinessValidationException("id为空");
		}
		return sysGisTypeManageDao.updateGisTypeManage(domain);
	}
}
