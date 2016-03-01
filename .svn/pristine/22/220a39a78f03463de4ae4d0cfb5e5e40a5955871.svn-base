package com.tianque.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.PartyMemberInfoDao;
import com.tianque.domain.Organization;
import com.tianque.domain.PartyMemberInfo;
import com.tianque.domain.property.IssuePersonAndSiteType;
import com.tianque.domain.vo.SearchPartyMemberInfoVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.service.HelpPersonnelService;
import com.tianque.service.HelpPrecordService;
import com.tianque.service.PartyMemberInfoService;
import com.tianque.service.bridge.BaseInfoDeleter;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.IdCardUtil;
import com.tianque.validate.AbstractCountrymenValidator;

@Service("partyMemberInfoService")
@Transactional
public class PartyMemberInfoServiceImpl extends LogableService implements
		PartyMemberInfoService {

	private static Logger logger = LoggerFactory
			.getLogger(PartyMemberInfoServiceImpl.class);

	@Qualifier("partyMemberInfoValidator")
	@Autowired
	private AbstractCountrymenValidator<PartyMemberInfo> updateValidator;

	@Autowired
	private HelpPersonnelService helpPersonnelService;

	@Autowired
	private HelpPrecordService helpPrecordService;

	@Autowired
	private PartyMemberInfoDao partyMemberInfoDao;

	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	private BaseInfoDeleter baseInfoDeleter;

	private String messageFormat(Object... args) {
		return MessageFormat.format(
				"类PartyMemberInfoServiceImpl的{0}方法出现异常，原因：", args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyMemberInfoService#addPartyMemberInfo(com.tianque
	 * .domain.PartyMemberInfo)
	 */
	@Override
	public PartyMemberInfo addPartyMemberInfo(PartyMemberInfo partyMemberInfo) {
		// this.partyMemberInfoValidator(partyMemberInfo);
		try {
			autoFillOrganizationInternalCode(partyMemberInfo);
			return this.partyMemberInfoDao.addPartyMemberInfo(partyMemberInfo);
		} catch (Exception e) {

			logger.error(messageFormat("addPartyMemberInfo"), e);

			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("保存党员信息出现错误");
			} else {
				return null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyMemberInfoService#getPartyMemberInfoById(java
	 * .lang.Long)
	 */
	@Override
	public PartyMemberInfo getPartyMemberInfoById(Long id)
			throws ServiceValidationException {
		if (null == id) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			return this.partyMemberInfoDao.getPartyMemberInfoById(id);
		} catch (Exception e) {
			return dealException("PartyMemberInfoServiceImpl",
					"getPartyMemberInfoById", "根据ID获取党员信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyMemberInfoService#getPartyMemberInfoByPartyOrgId
	 * (java.lang.Long)
	 */
	@Override
	public List<PartyMemberInfo> getPartyMemberInfoByPartyOrgId(Long partyOrgId)
			throws ServiceValidationException {
		if (null == partyOrgId) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			return this.partyMemberInfoDao
					.getPartyMemberInfoByPartyOrgId(partyOrgId);
		} catch (Exception e) {
			return dealException("PartyMemberInfoServiceImpl",
					"getPartyMemberInfoByPartyOrgId", "获取党组织下所有党员信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyMemberInfoService#updatePartyMemberInfo(com.
	 * tianque.domain.PartyMemberInfo)
	 */
	@Override
	public PartyMemberInfo updatePartyMemberInfo(PartyMemberInfo partyMemberInfo)
			throws ServiceValidationException {
		// this.partyMemberInfoValidator(partyMemberInfo);
		try {
			if (partyMemberInfo.isDeath()) {
				partyMemberInfo.setIsEmphasis(1L);
			}
			autoFillOrganizationInternalCode(partyMemberInfo);
			partyMemberInfo = this.partyMemberInfoDao
					.updatePartyMemberInfo(partyMemberInfo);
			return partyMemberInfo;
		} catch (Exception e) {
			return dealException("PartyMemberInfoServiceImpl",
					"updatePartyMemberInfo", "修改党员基本信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyMemberInfoService#deletePartyMemberInfoById(
	 * java.lang.Long)
	 */
	@Override
	public void deletePartyMemberInfoById(Long id)
			throws ServiceValidationException {
		if (null == id || id < 0L) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			this.partyMemberInfoDao.deletePartyMemberInfoById(id);
		} catch (Exception e) {
			dealException("PartyMemberInfoServiceImpl",
					"deletePartyMemberInfoById", "删除党员信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyMemberInfoService#findPartyMemberInfoForPageByOrgId
	 * (java.lang.Long, java.lang.Integer, java.lang.Integer, java.lang.String,
	 * java.lang.String, java.lang.Long)
	 */
	@Override
	public PageInfo<PartyMemberInfo> findPartyMemberInfoForPageByOrgId(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, Long isEmphasis) throws ServiceValidationException {
		try {
			if (null == orgId) {
				return constructEmptyPageInfo();
			} else {
				return this.partyMemberInfoDao
						.findPartyMemberInfoForPageByOrgId(orgId, pageNum,
								pageSize, sidx, sord, isEmphasis);
			}
		} catch (Exception e) {
			return dealException("PartyMemberInfoServiceImpl",
					"findPartyMemberInfoForPageByOrgInternalCode",
					"分页查询党员信息出现错误", e);
		}
	}

	private PageInfo<PartyMemberInfo> constructEmptyPageInfo() {
		PageInfo<PartyMemberInfo> partyMemberInfoPageInfo = new PageInfo<PartyMemberInfo>();
		partyMemberInfoPageInfo.setResult(new ArrayList<PartyMemberInfo>());
		return partyMemberInfoPageInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyMemberInfoService#deletePartyMemberInfoById(
	 * java.util.List)
	 */
	@Override
	public List<Long> deletePartyMemberInfoById(List<Long> ids)
			throws ServiceValidationException {
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
			for (Long id : exitPersonIdsList) {
				PartyMemberInfo domain = this.partyMemberInfoDao
						.getPartyMemberInfoById(id);
				log(WARN, PARTYMEMBERINFO, DELETE, OperatorType.DELETE,
						ObjectToJSON.convertJSON(domain));
			}
			for (Long id : delPersonIdList) {
				delectPartyMemberInfo(id);
			}
			return delPersonIdList;
		} catch (Exception e) {
			return dealException("PartyMemberInfoServiceImpl",
					"deletePartyMemberInfoById", "删除党员信息出现错误", e);
		}
	}

	private void delectPartyMemberInfo(Long id) throws JSONException {
		PartyMemberInfo partyMemberInfo = this.partyMemberInfoDao
				.getPartyMemberInfoById(id);
		log(WARN, PARTYMEMBERINFO, ThreadVariable.getSession().getUserName()
				+ "本级党建删除党员信息" + partyMemberInfo.getName(),
				OperatorType.DELETE, ObjectToJSON.convertJSON(partyMemberInfo));
		if (null != partyMemberInfo) {
			helpPrecordService.deleteHelpPrecord(id, "partyMemberInfo");
			helpPersonnelService.deleteHelpPersonnel(id, "partyMemberInfo");
			this.partyMemberInfoDao.deletePartyMemberInfoById(id);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyMemberInfoService#hasRelatePersons(java.util
	 * .List)
	 */
	@Override
	public Boolean hasRelatePersons(List<Long> personIds)
			throws ServiceValidationException {
		if (null == personIds) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			List<Long> exitPersonIdsList = baseInfoDeleter.getRelatepersonId(
					personIds, IssuePersonAndSiteType.PARTYMEMBERINFOS);
			if (exitPersonIdsList != null && exitPersonIdsList.size() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return dealException("PartyMemberInfoServiceImpl",
					"hasRelatePersons", "获取人员基本信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyMemberInfoService#hasDuplicatePartyMemberInfo
	 * (java.lang.Long, java.lang.String, java.lang.Long)
	 */
	@Override
	public Boolean hasDuplicatePartyMemberInfo(Long orgId, String idCardNo,
			Long exceptedId) throws ServiceValidationException {
		try {
			idCardNo = idCardNo.toUpperCase();
			PartyMemberInfo exsited = new PartyMemberInfo();
			if (null == exceptedId) {
				exsited = this.partyMemberInfoDao
						.getPartyMemberInfoByOrgIdAndCardNo(
								this.procIdCardNo(idCardNo), orgId);
			} else {
				exsited = this.partyMemberInfoDao
						.getPartyMemberInfoByIdAndIdCardAndOrgId(exceptedId,
								this.procIdCardNo(idCardNo), orgId);
			}
			return exceptedId == null ? exsited != null
					: (exsited != null && !exceptedId.equals(exsited.getId()));
		} catch (Exception e) {
			return dealException("PartyMemberInfoServiceImpl",
					"hasDuplicatePartyMemberInfo", "操作出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyMemberInfoService#searchPartyMemberInfos(java
	 * .lang.Integer, java.lang.Integer,
	 * com.tianque.domain.vo.SearchPartyMemberInfoVo)
	 */
	@Override
	public PageInfo<PartyMemberInfo> searchPartyMemberInfos(Integer pageNum,
			Integer pageSize, SearchPartyMemberInfoVo searchPartyMemberInfoVo)
			throws ServiceValidationException {
		try {
			return this.partyMemberInfoDao.searchPartyMemberInfos(pageNum,
					pageSize, searchPartyMemberInfoVo);
		} catch (Exception e) {
			return dealException("PartyMemberInfoServiceImpl",
					"searchPartyMemberInfos", "分页查询本级党建党员信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyMemberInfoService#getPartyMemberInfoIdCardNo
	 * (java.lang.String, java.lang.Long)
	 */
	@Override
	public PartyMemberInfo getPartyMemberInfoIdCardNo(String idCardNo,
			Long orgId) throws ServiceValidationException {
		try {
			idCardNo = idCardNo.toUpperCase();
			List<String> list = new ArrayList<String>();
			list.add(idCardNo);
			if (idCardNo.length() == 18) {
				list.add(IdCardUtil.idCardNo18to15(idCardNo));
			} else if (idCardNo.length() == 15) {
				list.add(IdCardUtil.idCardNo15to18(idCardNo, "19"));
				list.add(IdCardUtil.idCardNo15to18(idCardNo, "20"));
			}
			return this.partyMemberInfoDao.getPartyMemberInfoByOrgIdAndCardNo(
					list, orgId);
		} catch (Exception e) {
			return dealException("PartyMemberInfoServiceImpl",
					"getPartyMemberInfoIdCardNo", "获取本级党建党员信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyMemberInfoService#updatePartyMemberInfoByIdCardNo
	 * (java.lang.String, java.lang.Long, com.tianque.domain.PartyMemberInfo)
	 */
	@Override
	public PartyMemberInfo updatePartyMemberInfoByIdCardNo(String idCardNo,
			Long orgId, PartyMemberInfo partyMemberInfo)
			throws ServiceValidationException {
		try {
			PartyMemberInfo older = this.getPartyMemberInfoIdCardNo(idCardNo,
					orgId);
			partyMemberInfo.setId(older.getId());
			partyMemberInfo.setCreateDate(older.getCreateDate());
			partyMemberInfo.setCreateUser(older.getCreateUser());
			return this.updatePartyMemberInfo(partyMemberInfo);
		} catch (Exception e) {
			if (!ExcelImportHelper.isImport.get()) {
				logger.error(messageFormat("updatePartyMemberInfoByIdCardNo"),
						e);

				throw new BusinessValidationException("修改本级党建党员信息出现错误");
			} else {
				return null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyMemberInfoService#searchAllPartyMemberInfos(
	 * com.tianque.domain.vo.SearchPartyMemberInfoVo )
	 */
	@Override
	public List<PartyMemberInfo> searchAllPartyMemberInfos(
			SearchPartyMemberInfoVo searchPartyMemberInfoVo)
			throws ServiceValidationException {
		try {
			return this.partyMemberInfoDao
					.searchAllPartyMemberInfos(searchPartyMemberInfoVo);
		} catch (Exception e) {
			return dealException("PartyMemberInfoServiceImpl",
					"searchAllPartyMemberInfos", "查询本级党建党员信息出现错误", e);
		}
	}

	/**
	 * 数据校验
	 * 
	 * @param partyMemberInfo
	 */
	private void partyMemberInfoValidator(PartyMemberInfo partyMemberInfo) {
		autoFillOrganizationInternalCode(partyMemberInfo);
		autoFillBirthday(partyMemberInfo);
		ValidateResult partyMemberInfoValidator = updateValidator
				.validateBaseInfo(partyMemberInfo);
		if (partyMemberInfoValidator.hasError()) {
			throw new BusinessValidationException(
					partyMemberInfoValidator.getErrorMessages());
		} else if (hasDuplicatePartyMemberInfo(partyMemberInfo
				.getOrganization().getId(), partyMemberInfo.getIdCardNo(),
				partyMemberInfo.getId())) {
			throw new BusinessValidationException("该网格下已存在相同身份证号码");
		}
		partyMemberInfoValidator = updateValidator
				.validateSpecializedInfo(partyMemberInfo);
		if (partyMemberInfoValidator.hasError()) {
			throw new BusinessValidationException(
					partyMemberInfoValidator.getErrorMessages());
		}
		autoFillChinesePinyin(partyMemberInfo);
	}

	/**
	 * 身份证号码处理
	 * 
	 * @param idCardNo
	 * @return
	 */
	private List<String> procIdCardNo(String idCardNo) {
		List<String> list = new ArrayList<String>();
		list.add(idCardNo);
		if (idCardNo.length() == 18) {
			list.add(IdCardUtil.idCardNo18to15(idCardNo));
		} else if (idCardNo.length() == 15) {
			list.add(IdCardUtil.idCardNo15to18(idCardNo, "19"));
			list.add(IdCardUtil.idCardNo15to18(idCardNo, "20"));
		}
		return list;
	}

	private void autoFillOrganizationInternalCode(
			PartyMemberInfo partyMemberInfo) {
		Organization organization = this.organizationDubboService
				.getSimpleOrgById(partyMemberInfo.getOrganization().getId());
		if (null == organization) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		partyMemberInfo.setOrgInternalCode(organization.getOrgInternalCode());
	}

	private void autoFillBirthday(PartyMemberInfo partyMemberInfo) {
		if (StringUtil.isStringAvaliable(partyMemberInfo.getIdCardNo())) {
			partyMemberInfo.setBirthday(IdCardUtil
					.parseBirthday(partyMemberInfo.getIdCardNo()));
		}
	}

	private void autoFillChinesePinyin(PartyMemberInfo partyMemberInfo) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(partyMemberInfo.getName());
		partyMemberInfo.setSimplePinyin(pinyin.get("simplePinyin"));
		partyMemberInfo.setFullPinyin(pinyin.get("fullPinyin"));
	}

	@Override
	public Integer getCount(SearchPartyMemberInfoVo searchPartyMemberInfoVo) {
		return partyMemberInfoDao.getCount(searchPartyMemberInfoVo);
	}
}
