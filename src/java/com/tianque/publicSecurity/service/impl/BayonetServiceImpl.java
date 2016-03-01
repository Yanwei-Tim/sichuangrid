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
import com.tianque.publicSecurity.dao.BayonetDao;
import com.tianque.publicSecurity.domain.Bayonet;
import com.tianque.publicSecurity.domain.vo.SearchBayonetVo;
import com.tianque.publicSecurity.service.BayonetService;
import com.tianque.sysadmin.service.PermissionService;

/**
 * 卡口表:业务逻辑层
 * 
 * @author
 * @date 2014-02-11 10:44:36
 */
@Transactional
@Service("bayonetService")
public class BayonetServiceImpl extends
		BaseServiceImpl<Bayonet, SearchBayonetVo> implements BayonetService {

	@Autowired
	private BayonetDao bayonetDao;
	@Autowired
	private PermissionService permissionService;

	@Autowired
	@Qualifier("bayonetValidator")
	private DomainValidator<Bayonet> domainValidator;

	@Resource(name = "bayonetDao")
	public void setBaseDao(BayonetDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public Bayonet add(Bayonet bayonet) {
		bayonetValidator(bayonet);
		try {
			GisTransformatUtil.autoFillOpenLayersMapInfo(bayonet
					.getOpenLayersMapInfo());
			bayonet.setType(PublicSecurityType.BAYONET.toUpperCase());
			bayonet = getBaseDao().add(bayonet);
			return bayonet;
		} catch (Exception e) {
			return dealException(this, "add", "新增卡口表信息出现错误", e);
		}
	}

	@Override
	@SolrServerIndex(mode = OperateMode.EDIT, value = UpdateSqlMap.BAYONET_KEY)
	public Bayonet update(Bayonet bayonet) {
		bayonetValidator(bayonet);
		try {
			GisTransformatUtil.autoFillOpenLayersMapInfo(bayonet
					.getOpenLayersMapInfo());
			bayonet = getBaseDao().update(bayonet);
			return bayonet;
		} catch (Exception e) {

			return dealException(this, "update", "更新卡口表信息出现错误", e);
		}
	}

	private void bayonetValidator(Bayonet bayonet) {
		ValidateResult baseDataValidator = domainValidator.validate(bayonet);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public String hasDuplicateBayonetNo(Long id, Long orgId, String bayonetNo) {
		if (orgId == null || bayonetNo == null) {
			throw new BusinessValidationException("参数错误");
		}
		Bayonet getBayonet = bayonetDao.getBayonetByBayonetNo(bayonetNo, orgId);
		if (getBayonet != null && getBayonet.getId() != null
				&& !getBayonet.getId().equals(id)) {

			String userName = ThreadVariable.getUser().getUserName()
					.equals(getBayonet.getCreateUser()) ? "您"
					: permissionService.getFullUserByUerName(getBayonet
							.getCreateUser()) == null ? "" : permissionService
							.getFullUserByUerName(getBayonet.getCreateUser())
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
		bayonetDao
				.updateEmphasiseById(id, isEmphasis, logoutReason, logoutTime);
	}

	@Override
	public void updateByParam(Long id, Long toOrgId, String orgInternalCode) {
		try {
			if (id == null || toOrgId == null) {
				throw new BusinessValidationException("参数错误");
			}
			bayonetDao.updateByParam(id, toOrgId, orgInternalCode);

		} catch (Exception e) {

			dealException(this, "updateByParam", "更新卡口表信息出现错误", e);
		}
	}

	@Override
	public Bayonet getBayonetByBayonetNo(String bayonetNo, Long toOrgId) {
		try {
			if (bayonetNo == null || toOrgId == null) {
				throw new BusinessValidationException("参数错误");
			}
			return bayonetDao.getBayonetByBayonetNo(bayonetNo, toOrgId);
		} catch (Exception e) {

			return dealException(this, "getBayonetByBayonetNo", "得到卡口信息出现错误", e);
		}
	}

	@Override
	public Integer countByCode(String bayonetNo, Long toOrgId) {
		if (bayonetNo == null || toOrgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return bayonetDao.countByCode(bayonetNo, toOrgId);
	}

	@Override
	public void transferValidate(Long id, Long toOrgId, String code,
			Long orgId, List<ErrorMessageVo> errorList, List<Long> errorIdList,
			String orgName, String orgInternalCode) {
		if (code == null) {
			Bayonet getBayonet = bayonetDao.getBayonetByBayonetNo(bayonetDao
					.get(id).getBayonetNo(), toOrgId);
			if (getBayonet != null && !id.equals(getBayonet.getId())) {
				String message = "该编号在目标网格的卡口中已存在";
				ErrorMessageVo errorMessageVo = new ErrorMessageVo(
						getBayonet.getBayonetNo(), PublicSecurityType.BAYONET,
						orgName, null, message);
				errorList.add(errorMessageVo);
				errorIdList.add(id);
			}
		} else {
			if (bayonetDao.countByCode(code, toOrgId) > 1) {
				String message = "目标网格的卡口中存在相同的编号";
				ErrorMessageVo errorMessageVo = new ErrorMessageVo(code,
						PublicSecurityType.BAYONET, orgName, null, message);
				errorList.add(errorMessageVo);
				errorIdList.add(id);
				bayonetDao.updateByParam(id, orgId, orgInternalCode);
			}
		}
		return;

	}

	@Override
	@SolrServerIndex(mode = OperateMode.DELETE, value = DeleteSqlMap.BAYONET_KEY)
	public void delete(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("Id不能为空");
		}
		for (Long id : ids) {
			delete(id);
		}

	}
}
