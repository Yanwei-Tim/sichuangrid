package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.dao.SocialRelationDao;
import com.tianque.domain.SocialRelation;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.SocialRelationService;
import com.tianque.util.IdCardUtil;
import com.tianque.validate.impl.SocialRelationValidatorImpl;

@Service("socialRelationService")
@Transactional
public class SocialRelationServiceImpl extends AbstractBaseService implements
		SocialRelationService {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private SocialRelationDao socialRelationDao;

	private SocialRelationValidatorImpl socialRelationValidatorImpl;

	@Override
	public SocialRelation addSocialRelation(SocialRelation socialRelation) {
		socialRelationValidatorImpl = new SocialRelationValidatorImpl();
		socialRelationValidatorImpl.setValidateHelper(validateHelper);

		ValidateResult socialRelationValidator = socialRelationValidatorImpl
				.validate(socialRelation);
		if (socialRelationValidator.hasError()) {
			throw new BusinessValidationException(
					socialRelationValidator.getErrorMessages());
		} else if (hasDuplicateSocialRelation(
				socialRelation.getTrampResidentId(),
				socialRelation.getIdCardNo(), socialRelation.getId())) {
			throw new BusinessValidationException("已存在相同身份证号码");
		}
		return socialRelationDao.addSocialRelation(socialRelation);
	}

	@Override
	public boolean hasDuplicateSocialRelation(Long trampResidentId,
			String idCardNo, Long exceptedId) {
		idCardNo = idCardNo.toUpperCase();
		List<String> list = new ArrayList<String>();
		list.add(idCardNo);
		if (idCardNo.length() == 18) {
			list.add(IdCardUtil.idCardNo18to15(idCardNo));
		} else if (idCardNo.length() == 15) {
			list.add(IdCardUtil.idCardNo15to18(idCardNo, "19"));
			list.add(IdCardUtil.idCardNo15to18(idCardNo, "20"));
		}
		SocialRelation exsited = socialRelationDao.getSocialRelationByIdCard(
				list, trampResidentId);
		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	@Override
	public SocialRelation getSocialRelationById(Long id) {
		return socialRelationDao.getSocialRelationById(id);
	}

	@Override
	public int deleteSocialRelationById(Long id) {
		return socialRelationDao.deleteSocialRelation(id);
	}

	@Override
	public SocialRelation updateSocialRelation(SocialRelation socialRelation) {
		socialRelationValidatorImpl = new SocialRelationValidatorImpl();
		socialRelationValidatorImpl.setValidateHelper(validateHelper);

		ValidateResult socialRelationValidator = socialRelationValidatorImpl
				.validate(socialRelation);
		if (socialRelationValidator.hasError()) {
			throw new BusinessValidationException(
					socialRelationValidator.getErrorMessages());
		} else if (hasDuplicateSocialRelation(
				socialRelation.getTrampResidentId(),
				socialRelation.getIdCardNo(), socialRelation.getId())) {
			throw new BusinessValidationException("已存在相同身份证号码");
		}
		return socialRelationDao.updateSocialRelation(socialRelation);
	}

	@Override
	public List<SocialRelation> findSocialRelationForPageByTrampResidentId(
			Long trampResidentId, String sidx, String sord) {
		return socialRelationDao.findSocialRelationForPageByTrampResidentId(
				trampResidentId, sidx, sord);
	}

}
