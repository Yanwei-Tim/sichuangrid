package com.tianque.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.DialogMode;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.SocialRelation;
import com.tianque.service.SocialRelationService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.validate.impl.SocialRelationValidatorImpl;

/**
 * 未满16周岁的随行人员控制类。
 */
@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("socialRelationController")
public class SocialRelationController extends BaseAction {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private SocialRelationService socialRelationService;
	@Autowired
	private PropertyDictService propertyDictService;

	private SocialRelation socialRelation;
	private boolean bol;
	private long existedCount;
	private String modeType;

	private SocialRelationValidatorImpl socialRelationValidatorImpl;
	private Long id;

	public String dispatch() {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {

			socialRelation = new SocialRelation();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			socialRelation = socialRelationService.getSocialRelationById(socialRelation.getId());
		}
		return SUCCESS;
	}

	/**
	 * 根据流动人口id查询未满16周岁的随行人员
	 * 
	 * @return SUCCESS
	 */
	public String findSocialRelationForPageByTrampResidentId() {
		if (socialRelation == null || socialRelation.getTrampResidentId() == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			gridPage = new GridPage(
					socialRelationService.findSocialRelationForPageByTrampResidentId(
							socialRelation.getTrampResidentId(), sidx, sord));

		}
		return SUCCESS;
	}

	/**
	 * 新增未满16周岁的随行人员
	 * 
	 * @return SUCCESS
	 */
	// @PermissionFilter(ename = "addSocialRelation")
	public String addSocialRelation() {
		socialRelationValidatorImpl = new SocialRelationValidatorImpl();
		socialRelationValidatorImpl.setValidateHelper(validateHelper);
		ValidateResult socialRelationValidator = socialRelationValidatorImpl
				.validate(socialRelation);
		if (socialRelationValidator.hasError()) {
			return ERROR;
		}

		socialRelation = socialRelationService.addSocialRelation(socialRelation);
		return SUCCESS;
	}

	/**
	 * 修改未满16周岁的随行人员
	 * 
	 * @return SUCCESS
	 */
	// @PermissionFilter(ename = "updateSocialRelation")
	public String updateSocialRelation() {
		socialRelationValidatorImpl = new SocialRelationValidatorImpl();
		socialRelationValidatorImpl.setValidateHelper(validateHelper);
		ValidateResult socialRelationValidator = socialRelationValidatorImpl
				.validate(socialRelation);
		if (socialRelationValidator.hasError()) {
			return ERROR;
		}
		socialRelation = socialRelationService.updateSocialRelation(socialRelation);
		return SUCCESS;
	}

	/**
	 * 删除未满16周岁的随行人员
	 * 
	 * @return SUCCESS
	 */
	// @PermissionFilter(ename = "deleteSocialRelation")
	public String deleteSocialRelation() {
		if (socialRelation == null || socialRelation.getId() == null) {
			return ERROR;
		} else {
			socialRelationService.deleteSocialRelationById(socialRelation.getId());
			bol = true;
		}
		return SUCCESS;
	}

	/**
	 * 根据ID获取未满16周岁的随行人员
	 * 
	 * @return SUCCESS
	 */
	public String getSocialRelationById() {
		if (id == null) {
			return ERROR;
		} else {
			socialRelation = socialRelationService.getSocialRelationById(id);
			if (socialRelation.getGender() != null) {
				socialRelation.setGender(propertyDictService.getPropertyDictById(socialRelation
						.getGender().getId()));
			}
			if (socialRelation.getOccupation() != null) {
				socialRelation.setOccupation(propertyDictService.getPropertyDictById(socialRelation
						.getOccupation().getId()));
			}

		}
		return SUCCESS;
	}

	public String hasDuplicateSocialRelation() {
		if (socialRelation == null || socialRelation.getTrampResidentId() == null) {
			return ERROR;
		} else {
			String str = socialRelationService.hasDuplicateSocialRelation(
					socialRelation.getTrampResidentId(), socialRelation.getIdCardNo(),
					socialRelation.getId()) ? ERROR : SUCCESS;
			return str;
		}
	}

	private PageInfo<SocialRelation> emptyPage(int pageSize) {
		PageInfo<SocialRelation> pageInfo = new PageInfo<SocialRelation>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<SocialRelation>());
		return pageInfo;
	}

	public SocialRelation getSocialRelation() {
		return socialRelation;
	}

	public void setSocialRelation(SocialRelation socialRelation) {
		this.socialRelation = socialRelation;
	}

	public long getExistedCount() {
		return existedCount;
	}

	public void setExistedCount(long existedCount) {
		this.existedCount = existedCount;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	public String getModeType() {
		return modeType;
	}

	public void setModeType(String modeType) {
		this.modeType = modeType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}