package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.PartyOrgInfoDao;
import com.tianque.domain.Organization;
import com.tianque.domain.PartyOrgInfo;
import com.tianque.domain.vo.SearchPartyOrgInfoVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.partyBuilding.members.constant.MemberType;
import com.tianque.partyBuilding.members.service.MemberAssociatePartyOrgService;
import com.tianque.service.PartyOrgInfoService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.validate.AbstractCountrymenValidator;

@Service("partyOrgInfoService")
@Transactional
public class PartyOrgInfoServiceImpl implements PartyOrgInfoService {

	@Autowired
	private PartyOrgInfoDao partyOrgInfoDao;

	@Autowired
	private MemberAssociatePartyOrgService memberAssociatePartyOrgService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Qualifier("partyOrgInfoValidator")
	@Autowired
	private AbstractCountrymenValidator<PartyOrgInfo> updateValidator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyOrgInfoService#addPartyOrgInfo(com.tianque.domain
	 * .PartyOrgInfo)
	 */
	@Override
	public void addPartyOrgInfo(PartyOrgInfo partyOrgInfo) {
		this.partyOrgInfovalidator(partyOrgInfo);
		// 校验党组织是否已存在
		if (isExistPartyOrgInfoByIdAndOrgId(partyOrgInfo.getOrganization()
				.getId(), null)) {
			throw new BusinessValidationException("本级党建党支部名称已存在");
		}
		try {
			partyOrgInfo.setCreateDate(new Date());
			this.partyOrgInfoDao.addPartyOrgInfo(partyOrgInfo);
		} catch (Exception e) {
			throw new ServiceValidationException("保存本级党组织信息出错", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyOrgInfoService#getPartyOrgInfoById(java.lang
	 * .Long)
	 */
	@Override
	public PartyOrgInfo getPartyOrgInfoById(Long id) {
		try {
			return this.partyOrgInfoDao.getPartyOrgInfoById(id);
		} catch (Exception e) {
			throw new ServiceValidationException("获取本级党组织信息出错", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyOrgInfoService#getPartyOrgInfoByName(java.lang
	 * .String)
	 */
	@Override
	public PartyOrgInfo getPartyOrgInfoByNameAndOrgId(String partyBranchName,
			Long orgId) {
		try {
			return this.partyOrgInfoDao.getPartyOrgInfoByNameAndOrgId(
					partyBranchName, orgId);
		} catch (Exception e) {
			throw new ServiceValidationException("获取本级党组织信息出错", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyOrgInfoService#getPartyOrgInfoByOrgId(java.lang
	 * .Long)
	 */
	@Override
	public PartyOrgInfo getPartyOrgInfoByOrgId(Long orgId) {
		try {
			return this.partyOrgInfoDao
					.getPartyOrgInfoByIdAndOrgId(orgId, null);
		} catch (Exception e) {
			throw new ServiceValidationException("获取本级党组织信息出错", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyOrgInfoService#isExistPartyOrgInfoByIdAndOrgId
	 * (java.lang.Long, java.lang.Long)
	 */
	@Override
	public boolean isExistPartyOrgInfoByIdAndOrgId(Long orgId, Long id) {
		try {
			PartyOrgInfo partyOrgInfo = this.partyOrgInfoDao
					.getPartyOrgInfoByIdAndOrgId(orgId, id);
			if (null != partyOrgInfo) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}

		} catch (Exception e) {
			throw new ServiceValidationException("获取本级党建组织信息出错", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyOrgInfoService#updatePartyOrgInfo(com.tianque
	 * .domain.PartyOrgInfo)
	 */
	@Override
	public void updatePartyOrgInfo(PartyOrgInfo partyOrgInfo, String oldPartyOrg) {
		this.partyOrgInfovalidator(partyOrgInfo);
		// 校验党组织是否已存在
		if (isExistPartyOrgInfoByIdAndOrgId(partyOrgInfo.getOrganization()
				.getId(), partyOrgInfo.getId())) {
			throw new BusinessValidationException("党支部名称已存在");
		}
		try {
			partyOrgInfo.setUpdateDate(new Date());
			this.partyOrgInfoDao.updatePartyOrgInfo(partyOrgInfo);
			memberAssociatePartyOrgService
					.updatePartyorgByPartyOrgTypeAndPartyorg(
							MemberType.REGION_PARTY_ORG, oldPartyOrg,
							partyOrgInfo.getPartyBranchName());
		} catch (Exception e) {
			throw new ServiceValidationException("修改本级党组织信息出错", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyOrgInfoService#searchPartyOrgInfos(java.lang
	 * .Integer, java.lang.Integer, com.tianque.domain.vo.SearchPartyOrgInfoVo)
	 */
	@Override
	public PageInfo<PartyOrgInfo> searchPartyOrgInfos(Integer pageNum,
			Integer pageSize, SearchPartyOrgInfoVo searchPartyOrgInfoVo) {
		try {
			return this.partyOrgInfoDao.searchPartyOrgInfos(pageNum, pageSize,
					searchPartyOrgInfoVo);
		} catch (Exception e) {
			throw new ServiceValidationException("分页查询本级党建党组织信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyOrgInfoService#searchAllPartyOrgInfos(com.tianque
	 * .domain.vo. SearchPartyOrgInfoVo)
	 */
	@Override
	public List<PartyOrgInfo> searchAllPartyOrgInfos(
			SearchPartyOrgInfoVo searchPartyOrgInfoVo) {
		try {
			return this.partyOrgInfoDao
					.searchAllPartyOrgInfos(searchPartyOrgInfoVo);
		} catch (Exception e) {
			throw new ServiceValidationException("分页查询本级党建党组织信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.service.PartyOrgInfoService#
	 * findPartyOrgInfoForPageByOrgInternalCode(java.lang .Long,
	 * java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public PageInfo<PartyOrgInfo> findPartyOrgInfoForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		try {
			if (null == orgId) {
				return constructEmptyPageInfo();
			} else {
				Organization organization = organizationDubboService
						.getSimpleOrgById(orgId);
				if (null == organization) {
					return constructEmptyPageInfo();
				} else {
					return this.partyOrgInfoDao
							.findPartyOrgInfoForPageByOrgInternalCode(
									organization.getOrgInternalCode(), pageNum,
									pageSize, sidx, sord);
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException("分页查询下级党建党组织信息出现错误", e);

		}
	}

	/**
	 * 数据校验
	 * 
	 * @param partyOrgInfovalidator
	 */
	private void partyOrgInfovalidator(PartyOrgInfo partyOrgInfo) {
		ValidateResult partyOrgInfoValidator = updateValidator
				.validateSpecializedInfo(partyOrgInfo);
		if (partyOrgInfoValidator.hasError()) {
			throw new BusinessValidationException(
					partyOrgInfoValidator.getErrorMessages());
		}
	}

	private PageInfo<PartyOrgInfo> constructEmptyPageInfo() {
		PageInfo<PartyOrgInfo> partyOrgInfoPageInfo = new PageInfo<PartyOrgInfo>();
		partyOrgInfoPageInfo.setResult(new ArrayList<PartyOrgInfo>());
		return partyOrgInfoPageInfo;
	}

	@Override
	public Integer getCount(SearchPartyOrgInfoVo searchPartyOrgInfoVo) {
		return partyOrgInfoDao.getCount(searchPartyOrgInfoVo);
	}
}
