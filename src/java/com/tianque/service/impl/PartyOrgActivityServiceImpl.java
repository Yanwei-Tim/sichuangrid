package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.PartyOrgActivityDao;
import com.tianque.dao.PartyOrgActivityFileDao;
import com.tianque.domain.Organization;
import com.tianque.domain.PartyOrgActivity;
import com.tianque.domain.property.IssuePersonAndSiteType;
import com.tianque.domain.vo.SearchPartyOrgActivityVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.HelpPersonnelService;
import com.tianque.service.HelpPrecordService;
import com.tianque.service.PartyOrgActivityService;
import com.tianque.service.bridge.BaseInfoDeleter;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.validate.AbstractCountrymenValidator;

@Service("partyOrgActivityService")
@Transactional
public class PartyOrgActivityServiceImpl extends LogableService implements
		PartyOrgActivityService {

	@Qualifier("partyOrgActivityValidator")
	@Autowired
	private AbstractCountrymenValidator<PartyOrgActivity> updateValidator;

	@Autowired
	private HelpPersonnelService helpPersonnelService;

	@Autowired
	private HelpPrecordService helpPrecordService;

	@Autowired
	private PartyOrgActivityDao partyOrgActivityDao;

	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	private BaseInfoDeleter baseInfoDeleter;

	@Autowired
	private PartyOrgActivityFileDao partyOrgActivityFileDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyOrgActivityService#addPartyOrgActivity(com.tianque
	 * .domain. PartyOrgActivity)
	 */
	@Override
	public PartyOrgActivity addPartyOrgActivity(
			PartyOrgActivity partyOrgActivity) {
		this.partyOrgActivityValidator(partyOrgActivity);
		try {
			partyOrgActivity.setCreateDate(new Date());
			return this.partyOrgActivityDao
					.addPartyOrgActivity(partyOrgActivity);
		} catch (Exception e) {
			return dealException("PartyOrgActivityServiceImpl",
					"addPartyOrgActivity", "保存党组织活动记录出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyOrgActivityService#getPartyOrgActivityById(java
	 * .lang.Long)
	 */
	@Override
	public PartyOrgActivity getPartyOrgActivityById(Long id) {
		if (null == id || id < 0L) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			return this.partyOrgActivityDao.getPartyOrgActivityById(id);
		} catch (Exception e) {
			return dealException("PartyOrgActivityServiceImpl",
					"getPartyOrgActivityById", "获取党组织活动记录出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyOrgActivityService#getPartyOrgActivityByPartyOrgId
	 * (java.lang.Long)
	 */
	@Override
	public List<PartyOrgActivity> getPartyOrgActivityByPartyOrgId(
			Long partyOrgId) {
		if (null == partyOrgId) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			return this.partyOrgActivityDao
					.getPartyOrgActivityByPartyOrgId(partyOrgId);
		} catch (Exception e) {

			return dealException("PartyOrgActivityServiceImpl",
					"getPartyOrgActivityByPartyOrgId", "获取党组织下所有党组织活动记录信息出现错误",
					e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyOrgActivityService#updatePartyOrgActivity(com
	 * .tianque.domain. PartyOrgActivity)
	 */
	@Override
	public PartyOrgActivity updatePartyOrgActivity(
			PartyOrgActivity partyOrgActivity) {
		this.partyOrgActivityValidator(partyOrgActivity);
		try {
			partyOrgActivity = this.partyOrgActivityDao
					.updatePartyOrgActivity(partyOrgActivity);
			return partyOrgActivity;
		} catch (Exception e) {
			return dealException("PartyOrgActivityServiceImpl",
					"updatePartyOrgActivity", "修改党组织活动记录出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyOrgActivityService#deletePartyOrgActivityById
	 * (java.lang.Long)
	 */
	@Override
	public void deletePartyOrgActivityById(Long id) {
		if (null == id || id < 0L) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			this.partyOrgActivityFileDao
					.deletePartyOrgActivityFileByOrgActivityId(id);
			this.partyOrgActivityDao.deletePartyOrgActivityById(id);
		} catch (Exception e) {
			dealException("PartyOrgActivityServiceImpl",
					"deletePartyOrgActivityById", "删除党组织活动记录出现错误", e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.service.PartyOrgActivityService#
	 * findPartyOrgActivityForPageByOrgId(java.lang. Long, java.lang.Integer,
	 * java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public PageInfo<PartyOrgActivity> findPartyOrgActivityForPageByOrgId(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, String yearActivity) {
		try {
			if (null == orgId) {
				return constructEmptyPageInfo();
			} else {
				Organization organization = organizationDubboService
						.getSimpleOrgById(orgId);
				if (null == organization) {
					return constructEmptyPageInfo();
				} else {
					return this.partyOrgActivityDao
							.findPartyOrgActivityForPageByOrgId(orgId, pageNum,
									pageSize, sidx, sord, yearActivity);
				}
			}
		} catch (Exception e) {
			return dealException("PartyOrgActivityServiceImpl",
					"findPartyOrgActivityForPageByOrgId", "分页查询党员信息出现错误", e);
		}
	}

	private PageInfo<PartyOrgActivity> constructEmptyPageInfo() {
		PageInfo<PartyOrgActivity> partyOrgActivityPageInfo = new PageInfo<PartyOrgActivity>();
		partyOrgActivityPageInfo.setResult(new ArrayList<PartyOrgActivity>());
		return partyOrgActivityPageInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyOrgActivityService#deletePartyPartyOrgActivityById
	 * (java.util.List)
	 */
	@Override
	public List<Long> deletePartyPartyOrgActivityById(List<Long> ids) {
		if (null == ids) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			List<Long> exitPersonIdsList = baseInfoDeleter.getRelatepersonId(
					ids, IssuePersonAndSiteType.PARTYMEMBERINFOS);
			List<Long> delPersonIdList = new ArrayList<Long>();
			delPersonIdList.clear();
			delPersonIdList.addAll(ids);
			delPersonIdList.removeAll(exitPersonIdsList);
			for (Long id : delPersonIdList) {
				delectPartyPartyOrgActivity(id);
			}
			return delPersonIdList;
		} catch (Exception e) {
			return dealException("PartyOrgActivityServiceImpl",
					"deletePartyPartyOrgActivityById", "删除党组织活动记录出现错误", e);
		}
	}

	private void delectPartyPartyOrgActivity(Long id) throws JSONException {
		PartyOrgActivity partyOrgActivity = this.partyOrgActivityDao
				.getPartyOrgActivityById(id);
		if (null != partyOrgActivity) {
			log(WARN, PARTYORGACTIVITY,
					ThreadVariable.getSession().getUserName() + "本级党建删除党组织活动记录"
							+ partyOrgActivity.getActivitySubject(),
					OperatorType.DELETE, null);
			helpPrecordService.deleteHelpPrecord(id, "partyOrgActivity");
			helpPersonnelService.deleteHelpPersonnel(id, "partyOrgActivity");
			this.deletePartyOrgActivityById(id);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyOrgActivityService#searchPartyOrgActivitys(java
	 * .lang.Integer, java.lang.Integer,
	 * com.tianque.domain.vo.SearchPartyOrgActivityVo)
	 */
	@Override
	public PageInfo<PartyOrgActivity> searchPartyOrgActivitys(Integer pageNum,
			Integer pageSize, SearchPartyOrgActivityVo searchPartyOrgActivityVo) {
		try {
			return this.partyOrgActivityDao.searchPartyOrgActivitys(pageNum,
					pageSize, searchPartyOrgActivityVo);
		} catch (Exception e) {
			return dealException("PartyOrgActivityServiceImpl",
					"searchPartyOrgActivitys", "分页查询本级党建党组织活动记录出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyOrgActivityService#searchAllPartyOrgActivitys
	 * (com.tianque.domain.vo. SearchPartyOrgActivityVo )
	 */
	@Override
	public List<PartyOrgActivity> searchAllPartyOrgActivitys(
			SearchPartyOrgActivityVo searchPartyOrgActivityVo) {
		try {
			return this.partyOrgActivityDao
					.searchAllPartyOrgActivitys(searchPartyOrgActivityVo);
		} catch (Exception e) {
			return dealException("PartyOrgActivityServiceImpl",
					"searchAllPartyOrgActivitys", "查询本级党建党组织活动记录出现错误", e);
		}
	}

	/**
	 * 数据校验
	 * 
	 * @param partyOrgInfovalidator
	 */
	private void partyOrgActivityValidator(PartyOrgActivity partyOrgActivity) {
		ValidateResult partyOrgInfoValidator = updateValidator
				.validateSpecializedInfo(partyOrgActivity);
		if (partyOrgInfoValidator.hasError()) {
			throw new BusinessValidationException(
					partyOrgInfoValidator.getErrorMessages());
		}
	}

	@Override
	public Integer getCount(SearchPartyOrgActivityVo searchPartyOrgActivityVo) {
		return partyOrgActivityDao.getCount(searchPartyOrgActivityVo);
	}

}
