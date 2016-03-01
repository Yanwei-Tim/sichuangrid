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
import com.tianque.publicSecurity.dao.SnapshotSystemDao;
import com.tianque.publicSecurity.domain.SnapshotSystem;
import com.tianque.publicSecurity.domain.vo.SearchSnapshotSystemVo;
import com.tianque.publicSecurity.service.SnapshotSystemService;
import com.tianque.sysadmin.service.PermissionService;

/**
 * 抓拍系统表:业务逻辑层
 * 
 * @author
 * @date 2014-02-11 15:12:12
 */
@Transactional
@Service("snapshotSystemService")
public class SnapshotSystemServiceImpl extends
		BaseServiceImpl<SnapshotSystem, SearchSnapshotSystemVo> implements
		SnapshotSystemService {

	@Autowired
	private SnapshotSystemDao snapshotSystemDao;
	@Autowired
	private PermissionService permissionService;

	@Autowired
	@Qualifier("snapshotSystemValidator")
	private DomainValidator<SnapshotSystem> domainValidator;

	@Resource(name = "snapshotSystemDao")
	public void setBaseDao(SnapshotSystemDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public SnapshotSystem add(SnapshotSystem snapshotsystem) {
		snapshotsystemValidator(snapshotsystem);
		try {
			GisTransformatUtil.autoFillOpenLayersMapInfo(snapshotsystem
					.getOpenLayersMapInfo());
			snapshotsystem.setType(PublicSecurityType.SNAPSHOTSYSTEM
					.toUpperCase());
			snapshotsystem = getBaseDao().add(snapshotsystem);
			return snapshotsystem;
		} catch (Exception e) {
			return dealException(this, "add", "新增抓拍系统表信息出现错误", e);
		}
	}

	@Override
	@SolrServerIndex(mode = OperateMode.EDIT, value = UpdateSqlMap.SNAPSHOTSYSTEM_KEY)
	public SnapshotSystem update(SnapshotSystem snapshotsystem) {
		snapshotsystemValidator(snapshotsystem);
		try {
			GisTransformatUtil.autoFillOpenLayersMapInfo(snapshotsystem
					.getOpenLayersMapInfo());
			snapshotsystem = getBaseDao().update(snapshotsystem);
			return snapshotsystem;
		} catch (Exception e) {
			return dealException(this, "update", "更新抓拍系统表信息出现错误", e);
		}
	}

	private void snapshotsystemValidator(SnapshotSystem snapshotsystem) {
		ValidateResult baseDataValidator = domainValidator
				.validate(snapshotsystem);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public String hasDuplicateSnapshotNo(Long id, Long orgId, String snapshotNo) {
		if (orgId == null || snapshotNo == null) {
			throw new BusinessValidationException("参数错误");
		}
		SnapshotSystem getSnapshotSystem = snapshotSystemDao
				.getSnapshotSystemBySnapshotNo(snapshotNo, orgId);
		if (getSnapshotSystem != null && getSnapshotSystem.getId() != null
				&& !getSnapshotSystem.getId().equals(id)) {

			String userName = ThreadVariable.getUser().getUserName()
					.equals(getSnapshotSystem.getCreateUser()) ? "您"
					: permissionService.getFullUserByUerName(getSnapshotSystem
							.getCreateUser()) == null ? "" : permissionService
							.getFullUserByUerName(
									getSnapshotSystem.getCreateUser())
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
		snapshotSystemDao.updateEmphasiseById(id, isEmphasis, logoutReason,
				logoutTime);
	}

	@Override
	public void updateByParam(Long id, Long toOrgId, String orgInternalCode) {
		try {
			if (id == null || toOrgId == null) {
				throw new BusinessValidationException("参数错误");
			}
			snapshotSystemDao.updateByParam(id, toOrgId, orgInternalCode);

		} catch (Exception e) {
			dealException(this, "updateByParam", "更新抓拍系统信息出现错误", e);
		}

	}

	@Override
	public SnapshotSystem getSnapshotSystemBySnapshotNo(String snapshotNo,
			Long orgId) {
		try {
			if (snapshotNo == null || orgId == null) {
				throw new BusinessValidationException("参数错误");
			}
			return snapshotSystemDao.getSnapshotSystemBySnapshotNo(snapshotNo,
					orgId);
		} catch (Exception e) {
			return dealException(this, "getSnapshotSystemBySnapshotNo",
					"得到抓拍系统信息出现错误", e);
		}
	}

	@Override
	public Integer countByCode(String code, Long toOrgId) {
		if (code == null || toOrgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return snapshotSystemDao.countByCode(code, toOrgId);
	}

	@Override
	public void transferValidate(Long id, Long toOrgId, String code,
			Long orgId, List<ErrorMessageVo> errorList, List<Long> errorIdList,
			String orgName, String orgInternalCode) {
		if (code == null) {
			SnapshotSystem getSnapshotSystem = snapshotSystemDao
					.getSnapshotSystemBySnapshotNo(snapshotSystemDao.get(id)
							.getSnapshotNo(), toOrgId);
			if (getSnapshotSystem != null
					&& !id.equals(getSnapshotSystem.getId())) {
				String message = "该编号在目标网格的抓拍系统中已存在";
				ErrorMessageVo errorMessageVo = new ErrorMessageVo(
						getSnapshotSystem.getSnapshotNo(),
						PublicSecurityType.SNAPSHOTSYSTEM, orgName, null,
						message);
				errorList.add(errorMessageVo);
				errorIdList.add(id);
			}
		} else {
			if (snapshotSystemDao.countByCode(code, toOrgId) > 1) {
				String message = "目标网格的抓拍系统中存在相同的编号";
				ErrorMessageVo errorMessageVo = new ErrorMessageVo(code,
						PublicSecurityType.SNAPSHOTSYSTEM, orgName, null,
						message);
				errorList.add(errorMessageVo);
				errorIdList.add(id);
				snapshotSystemDao.updateByParam(id, orgId, orgInternalCode);
			}
		}
		return;

	}

	@Override
	@SolrServerIndex(mode = OperateMode.DELETE, value = DeleteSqlMap.SNAPSHOTSYSTEM_KEY)
	public void delete(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("Id不能为空");
		}
		for (Long id : ids) {
			delete(id);
		}

	}

}
