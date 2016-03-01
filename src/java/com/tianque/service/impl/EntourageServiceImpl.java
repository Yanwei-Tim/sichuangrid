package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.dao.EntourageDao;
import com.tianque.domain.Entourage;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.EntourageService;
import com.tianque.util.IdCardUtil;
import com.tianque.validate.impl.EntourageValidatorImpl;

@Transactional
@Service("entourageService")
public class EntourageServiceImpl extends AbstractBaseService implements
		EntourageService {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private EntourageDao entourageDao;

	private EntourageValidatorImpl entourageValidatorImpl;

	@Override
	public Entourage addEntourage(Entourage entourage) {
		entourageValidatorImpl = new EntourageValidatorImpl();
		entourageValidatorImpl.setValidateHelper(validateHelper);

		ValidateResult entourageValidator = entourageValidatorImpl
				.validate(entourage);
		if (entourageValidator.hasError()) {
			throw new BusinessValidationException(
					entourageValidator.getErrorMessages());
		} else if (hasDuplicateEntourage(entourage.getTrampResidentId(),
				entourage.getIdCardNo(), entourage.getId())) {
			throw new BusinessValidationException("已存在相同身份证号码");
		}
		return entourageDao.addEntourage(entourage);
	}

	@Override
	public boolean hasDuplicateEntourage(Long trampResidentId, String idCardNo,
			Long exceptedId) {
		idCardNo = idCardNo.toUpperCase();
		List<String> list = new ArrayList<String>();
		list.add(idCardNo);
		if (idCardNo.length() == 18) {
			list.add(IdCardUtil.idCardNo18to15(idCardNo));
		} else if (idCardNo.length() == 15) {
			list.add(IdCardUtil.idCardNo15to18(idCardNo, "19"));
			list.add(IdCardUtil.idCardNo15to18(idCardNo, "20"));
		}
		Entourage exsited = entourageDao.getEntourageByIdCard(list,
				trampResidentId);
		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	@Override
	public Entourage getEntourageById(Long id) {
		return entourageDao.getEntourageById(id);
	}

	@Override
	public int deleteEntourageById(Long id) {
		return entourageDao.deleteEntourageById(id);
	}

	@Override
	public Entourage updateEntourage(Entourage entourage) {
		entourageValidatorImpl = new EntourageValidatorImpl();
		entourageValidatorImpl.setValidateHelper(validateHelper);

		ValidateResult entourageValidator = entourageValidatorImpl
				.validate(entourage);
		if (entourageValidator.hasError()) {
			throw new BusinessValidationException(
					entourageValidator.getErrorMessages());
		} else if (hasDuplicateEntourage(entourage.getTrampResidentId(),
				entourage.getIdCardNo(), entourage.getId())) {
			throw new BusinessValidationException("已存在相同身份证号码");
		}
		return entourageDao.updateEntourage(entourage);
	}

	@Override
	public List<Entourage> findEntourageForPageByTrampResidentId(
			Long trampResidentId, String sidx, String sord) {
		return entourageDao.findEntourageForPageByTrampResidentId(
				trampResidentId, sidx, sord);
	}

}
