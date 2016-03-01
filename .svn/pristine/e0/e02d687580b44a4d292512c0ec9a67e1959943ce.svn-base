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
import com.tianque.publicSecurity.dao.VideoSystemDao;
import com.tianque.publicSecurity.domain.VideoSystem;
import com.tianque.publicSecurity.domain.vo.SearchVideoSystemVo;
import com.tianque.publicSecurity.service.VideoSystemService;
import com.tianque.sysadmin.service.PermissionService;

/**
 * 监控视频表:业务逻辑层
 */
@Transactional
@Service("videoSystemService")
public class VideoSystemServiceImpl extends BaseServiceImpl<VideoSystem, SearchVideoSystemVo>
		implements VideoSystemService {

	@Autowired
	private VideoSystemDao videoSystemDao;
	@Autowired
	private PermissionService permissionService;

	@Autowired
	@Qualifier("videoSystemValidator")
	private DomainValidator<VideoSystem> domainValidator;

	@Resource(name = "videoSystemDao")
	public void setBaseDao(VideoSystemDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public VideoSystem add(VideoSystem videoSystem) {
		videoSystemValidator(videoSystem);
		try {
			GisTransformatUtil.autoFillOpenLayersMapInfo(videoSystem.getOpenLayersMapInfo());
			videoSystem.setType(PublicSecurityType.VIDEOSYSTEM.toUpperCase());
			videoSystem = getBaseDao().add(videoSystem);
			return videoSystem;
		} catch (Exception e) {
			return dealException(this, "add", "新增监控视频表信息出现错误", e);
		}
	}

	@Override
	@SolrServerIndex(mode = OperateMode.EDIT, value = UpdateSqlMap.VIDEOSYSTEM_KEY)
	public VideoSystem update(VideoSystem videoSystem) {
		videoSystemValidator(videoSystem);
		try {
			GisTransformatUtil.autoFillOpenLayersMapInfo(videoSystem.getOpenLayersMapInfo());
			videoSystem = getBaseDao().update(videoSystem);
			return videoSystem;
		} catch (Exception e) {
			return dealException(this, "update", "更新监控视频表信息出现错误", e);
		}
	}

	private void videoSystemValidator(VideoSystem videoSystem) {
		ValidateResult baseDataValidator = domainValidator.validate(videoSystem);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public String hasDuplicateVideoNo(Long id, Long orgId, String videoNo) {
		if (orgId == null || videoNo == null) {
			throw new BusinessValidationException("参数错误");
		}
		VideoSystem getVideoSystem = videoSystemDao.getVideoSystemByVideoNo(videoNo, orgId);
		if (getVideoSystem != null && getVideoSystem.getId() != null
				&& !getVideoSystem.getId().equals(id)) {

			String userName = ThreadVariable.getUser().getUserName()
					.equals(getVideoSystem.getCreateUser()) ? "您" : permissionService
					.getFullUserByUerName(getVideoSystem.getCreateUser()) == null ? ""
					: permissionService.getFullUserByUerName(getVideoSystem.getCreateUser())
							.getName();
			return "当前用户(" + userName + ")下已存在相同的编号,请重新输入";
		} else {
			return null;
		}
	}

	@Override
	public void updateEmphasiseByIds(Long[] ids, Long isEmphasis, String logoutReason,
			Date logoutTime) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("Id不能为空");
		}
		for (Long id : ids) {
			updateEmphasiseById(id, isEmphasis, logoutReason, logoutTime);
		}

	}

	private void updateEmphasiseById(Long id, Long isEmphasis, String logoutReason, Date logoutTime) {
		if (id == null) {
			throw new BusinessValidationException("Id不能为空");
		}
		videoSystemDao.updateEmphasiseById(id, isEmphasis, logoutReason, logoutTime);
	}

	@Override
	public void updateByParam(Long id, Long toOrgId, String orgInternalCode) {
		try {
			if (id == null || toOrgId == null) {
				throw new BusinessValidationException("参数错误");
			}
			videoSystemDao.updateByParam(id, toOrgId, orgInternalCode);

		} catch (Exception e) {
			dealException(this, "updateByParam", "更新监控视频信息出现错误", e);
		}

	}

	@Override
	public VideoSystem getVideoSystemByVideoNo(String videoNo, Long orgId) {
		try {
			if (videoNo == null || orgId == null) {
				throw new BusinessValidationException("参数错误");
			}
			return videoSystemDao.getVideoSystemByVideoNo(videoNo, orgId);
		} catch (Exception e) {
			return dealException(this, "getVideoSystemByVideoNo", "得到监控视频信息出现错误", e);
		}
	}

	@Override
	public Integer countByCode(String code, Long toOrgId) {
		if (code == null || toOrgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return videoSystemDao.countByCode(code, toOrgId);
	}

	@Override
	public void transferValidate(Long id, Long toOrgId, String code, Long orgId,
			List<ErrorMessageVo> errorList, List<Long> errorIdList, String orgName,
			String orgInternalCode) {
		if (code == null) {
			VideoSystem getVideoSystem = videoSystemDao.getVideoSystemByVideoNo(
					videoSystemDao.get(id).getVideoNo(), toOrgId);
			if (getVideoSystem != null && !id.equals(getVideoSystem.getId())) {
				String message = "该编号在目标网格的监控视频中已存在";
				ErrorMessageVo errorMessageVo = new ErrorMessageVo(getVideoSystem.getVideoNo(),
						PublicSecurityType.VIDEOSYSTEM, orgName, null, message);
				errorList.add(errorMessageVo);
				errorIdList.add(id);
			}
		} else {
			if (videoSystemDao.countByCode(code, toOrgId) > 1) {
				String message = "目标网格的监控视频中存在相同的编号";
				ErrorMessageVo errorMessageVo = new ErrorMessageVo(code,
						PublicSecurityType.VIDEOSYSTEM, orgName, null, message);
				errorList.add(errorMessageVo);
				errorIdList.add(id);
				videoSystemDao.updateByParam(id, orgId, orgInternalCode);
			}
		}
		return;

	}

	@Override
	@SolrServerIndex(mode = OperateMode.DELETE, value = DeleteSqlMap.VIDEOSYSTEM_KEY)
	public void delete(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("Id不能为空");
		}
		for (Long id : ids) {
			delete(id);
		}

	}

}
