package com.tianque.openLayersMap.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.SysModuleManageDao;
import com.tianque.openLayersMap.domian.CommonEntityInfoVo;
import com.tianque.openLayersMap.domian.GisModuleVo;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.SysModuleManageService;
import com.tianque.openLayersMap.util.LayerChoose;
import com.tianque.sysadmin.service.PermissionService;

/**
 * 大类类别管理实现类
 * 
 * @author yubin
 * 
 */
@Transactional
@Service("sysModuleManageService")
public class SysModuleManageServiceImpl extends BaseService implements
		SysModuleManageService {

	@Autowired
	private SysModuleManageDao sysModuleManageDao;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private PermissionService permissionService;

	@Override
	public GisModuleVo addModule(GisModuleVo gisModuleVo) {
		validateModule(gisModuleVo);
		if (null == gisModuleVo.getIsHasSonClass()) {
			gisModuleVo.setIsHasSonClass(false);
		}
		gisModuleVo.setIsBusinessLayer(LayerChoose.BUSINESS_LAYER);// 设置为业务图层
		return sysModuleManageDao.addModule(gisModuleVo);
	}

	@Override
	public void deleteModuleById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("id不能为空!");
		}
		sysModuleManageDao.deleteModuleById(id);
	}

	@Override
	public GisModuleVo updateModule(GisModuleVo gisModuleVo) {
		validateModule(gisModuleVo);
		if (null == gisModuleVo.getId()) {
			throw new BusinessValidationException("id不能为空!");
		}
		return sysModuleManageDao.updateModule(gisModuleVo);
	}

	@Override
	public GisModuleVo getModuleById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("id不能为空!");
		}
		return sysModuleManageDao.getModuleById(id);
	}

	@Override
	public List<GisModuleVo> findAllModule() {
		List<GisModuleVo> gisModuleVoList = sysModuleManageDao.findAllModule();
		// PermissionFilter.filterPermission(gisModuleVoList,
		// ThreadVariable.getUser().getId(), permissionService);
		return gisModuleVoList;
	}

	@Override
	public Boolean isExistTableName(String tableName) {
		if (null == tableName) {
			throw new BusinessValidationException("tableName不能为空!");
		}
		GisModuleVo gisModuleVo = sysModuleManageDao
				.getModuleByTableName(tableName);
		if (null == gisModuleVo) {
			return true;
		}
		return false;
	}

	@Override
	public GisModuleVo getModuleByTableName(String tableName) {
		if (tableName == null) {
			throw new BusinessValidationException("参数错误");
		}
		return sysModuleManageDao.getModuleByTableName(tableName);
	}

	/**
	 * 校验gisModuleVo
	 * 
	 * @param gisModuleVo
	 */
	private void validateModule(GisModuleVo gisModuleVo) {
		if (null == gisModuleVo) {
			throw new BusinessValidationException("大类对象不能为空!");
		} else if (null == gisModuleVo.getModuleName()
				|| validateHelper.illegalStringLength(1, 20,
						gisModuleVo.getModuleName())) {
			throw new BusinessValidationException("模块名称只能输入1-20个字符!");
		} else if (null == gisModuleVo.getTableName()
				|| validateHelper.illegalStringLength(1, 20,
						gisModuleVo.getTableName())) {
			throw new BusinessValidationException("表名只能输入1-20个字符!");
		} else if (StringUtil.isStringAvaliable(gisModuleVo.getModeType())
				&& validateHelper.illegalStringLength(1, 100,
						gisModuleVo.getModeType())) {
			throw new BusinessValidationException("实现类型只能输入1-100个字符!");
		} else if (StringUtil.isStringAvaliable(gisModuleVo.getOrgFiled())
				&& validateHelper.illegalStringLength(1, 60,
						gisModuleVo.getOrgFiled())) {
			throw new BusinessValidationException("所属网格字段只能输入1-60个字符!");
		}
	}

	@Override
	public List<CommonEntityInfoVo> getGisModuleByIssearch() {
		List<CommonEntityInfoVo> headerSearchList = sysModuleManageDao
				.getGisModuleByIssearch();
		// PermissionFilter.filterPermission(headerSearchList,
		// ThreadVariable.getUser().getId(), permissionService);
		return headerSearchList;
	}

	@Override
	public List<GisModuleVo> getGisModuleByModeType(String modeType) {
		if (null == modeType) {
			throw new BusinessValidationException("modeType不能为空!");
		}
		String[] modeTypes = modeType.split(",");
		List<GisModuleVo> list = new ArrayList<GisModuleVo>();
		GisModuleVo gisModuleVo = null;
		for (int i = 0; i < modeTypes.length; i++) {
			gisModuleVo = sysModuleManageDao
					.getGisModuleByModeType(modeTypes[i]);
			list.add(gisModuleVo);
		}
		return list;
	}

}
