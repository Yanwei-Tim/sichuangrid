package com.tianque.publicSecurity.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.openLayersMap.util.GisTransformatUtil;
import com.tianque.plugin.tqSearch.sqlMap.DeleteSqlMap;
import com.tianque.plugin.tqSearch.sqlMap.UpdateSqlMap;
import com.tianque.plugin.transfer.vo.ErrorMessageVo;
import com.tianque.publicSecurity.constant.PublicSecurityType;
import com.tianque.publicSecurity.dao.SkynetDao;
import com.tianque.publicSecurity.domain.Skynet;
import com.tianque.publicSecurity.domain.vo.SearchSkynetVo;
import com.tianque.publicSecurity.service.SkynetService;
import com.tianque.sysadmin.service.PermissionService;

/**
 * 天网表:业务逻辑层
 * 
 * @author
 * @date 2014-02-10 14:21:16
 */
@Transactional
@Service("skynetService")
public class SkynetServiceImpl extends BaseServiceImpl<Skynet, SearchSkynetVo>
		implements SkynetService {

	@Autowired
	private SkynetDao skynetDao;
	@Autowired
	private PermissionService permissionService;

	@Autowired
	@Qualifier("skynetValidator")
	private DomainValidator<Skynet> domainValidator;

	@Resource(name = "skynetDao")
	public void setBaseDao(SkynetDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public Skynet add(Skynet skynet) {
		skynetValidator(skynet);
		try {
			GisTransformatUtil.autoFillOpenLayersMapInfo(skynet
					.getOpenLayersMapInfo());
			skynet.setType(PublicSecurityType.SKYNET.toUpperCase());
			skynet = getBaseDao().add(skynet);
			return skynet;
		} catch (Exception e) {
			return dealException(this, "add", "新增天网表信息出现错误", e);
		}
	}

	@Override
	@SolrServerIndex(mode = OperateMode.EDIT, value = UpdateSqlMap.SKYNET_KEY)
	public Skynet update(Skynet skynet) {
		skynetValidator(skynet);
		try {
			GisTransformatUtil.autoFillOpenLayersMapInfo(skynet
					.getOpenLayersMapInfo());
			skynet = getBaseDao().update(skynet);
			return skynet;
		} catch (Exception e) {
			return dealException(this, "update", "更新天网表信息出现错误", e);
		}
	}

	private void skynetValidator(Skynet skynet) {
		ValidateResult baseDataValidator = domainValidator.validate(skynet);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public String hasDuplicateSkynetNo(Long id, Long orgId, String skynetNo) {
		if (orgId == null || skynetNo == null) {
			throw new BusinessValidationException("参数错误");
		}
		Skynet getSkynet = skynetDao.getSkynetBySkynetNo(skynetNo, orgId);
		if (getSkynet != null && getSkynet.getId() != null
				&& !getSkynet.getId().equals(id)) {

			String userName = ThreadVariable.getUser().getUserName()
					.equals(getSkynet.getCreateUser()) ? "您"
					: permissionService.getFullUserByUerName(getSkynet
							.getCreateUser()) == null ? "" : permissionService
							.getFullUserByUerName(getSkynet.getCreateUser())
							.getName();
			return "当前用户(" + userName + ")下已存在相同的编号,请重新输入";
		} else {
			return null;
		}

	}

	@Override
	public void updateEmphasiseByIds(Long[] ids, Long isEmphasis,
			String logoutReason, Date logoutTime) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("Id不能为空");
		}
		for (Long id : ids) {
			updateEmphasiseById(id, isEmphasis, logoutReason, logoutTime);
		}

	}

	private void updateEmphasiseById(Long id, Long isEmphasis,
			String logoutReason, Date logoutTime) {
		if (id == null) {
			throw new BusinessValidationException("Id不能为空");
		}
		skynetDao.updateEmphasiseById(id, isEmphasis, logoutReason, logoutTime);
	}

	@Override
	public void updateByParam(Long id, Long toOrgId, String orgInternalCode) {
		try {
			if (id == null || toOrgId == null) {
				throw new BusinessValidationException("参数错误");
			}
			skynetDao.updateByParam(id, toOrgId, orgInternalCode);

		} catch (Exception e) {
			dealException(this, "updateByParam", "更新天网表信息出现错误", e);
		}

	}

	@Override
	public Skynet getSkynetBySkynetNo(String skynetNo, Long toOrgId) {
		try {
			if (skynetNo == null || toOrgId == null) {
				throw new BusinessValidationException("参数错误");
			}
			return skynetDao.getSkynetBySkynetNo(skynetNo, toOrgId);
		} catch (Exception e) {
			return dealException(this, "getSkynetBySkynetNo", "得到天网信息出现错误", e);
		}

	}

	@Override
	public Integer countByCode(String code, Long toOrgId) {
		if (code == null || toOrgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return skynetDao.countByCode(code, toOrgId);
	}

	@Override
	public void transferValidate(Long id, Long toOrgId, String code,
			Long orgId, List<ErrorMessageVo> errorList, List<Long> errorIdList,
			String orgName, String orgInternalCode) {
		if (code == null) {
			Skynet getSkynet = skynetDao.getSkynetBySkynetNo(skynetDao.get(id)
					.getSkynetNo(), toOrgId);
			if (getSkynet != null && !getSkynet.getId().equals(id)) {
				String message = "该编号在目标网格的天网中已存在";
				ErrorMessageVo errorMessageVo = new ErrorMessageVo(
						getSkynet.getSkynetNo(), PublicSecurityType.SKYNET,
						orgName, null, message);
				errorList.add(errorMessageVo);
				errorIdList.add(id);
			}
		} else {
			if (skynetDao.countByCode(code, toOrgId) > 1) {
				String message = "目标网格的天网中存在相同的编号";
				ErrorMessageVo errorMessageVo = new ErrorMessageVo(code,
						PublicSecurityType.SKYNET, orgName, null, message);
				errorList.add(errorMessageVo);
				errorIdList.add(id);
				skynetDao.updateByParam(id, orgId, orgInternalCode);
			}
		}
		return;
	}

	@Override
	@SolrServerIndex(mode = OperateMode.DELETE, value = DeleteSqlMap.SKYNET_KEY)
	public void delete(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("Id不能为空");
		}
		for (Long id : ids) {
			delete(id);
		}

	}
}
