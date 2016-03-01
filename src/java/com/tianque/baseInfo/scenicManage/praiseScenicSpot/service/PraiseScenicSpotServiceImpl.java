package com.tianque.baseInfo.scenicManage.praiseScenicSpot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.scenicManage.praiseScenicSpot.dao.PraiseScenicSpotDao;
import com.tianque.baseInfo.scenicManage.praiseScenicSpot.domain.PraiseScenicSpot;
import com.tianque.baseInfo.scenicManage.praiseScenicSpot.validator.PraiseScenicSpotValidatorImpl;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.service.impl.LogableService;

@Transactional
@Service("praiseScenicSpotService")
public class PraiseScenicSpotServiceImpl extends LogableService implements
		PraiseScenicSpotService {

	@Autowired
	@Qualifier("praiseScenicSpotValidator")
	private PraiseScenicSpotValidatorImpl praiseScenicSpotValidator;
	@Autowired
	private PraiseScenicSpotDao praiseScenicSpotDao;
	@Autowired
	private ValidateHelper validateHelper;

	@Override
	public PageInfo<PraiseScenicSpot> findPraiseScenicSpotList(
			PraiseScenicSpot praiseScenicSpot, Integer page, Integer rows,
			String sidx, String sord) {
		return praiseScenicSpotDao.findPraiseScenicSpotList(praiseScenicSpot,
				page, rows, sidx, sord);
	}

	@Override
	public PraiseScenicSpot addPraiseScenicSpot(
			PraiseScenicSpot praiseScenicSpot) {
		try {
			praiseScenicSpotValidator = new PraiseScenicSpotValidatorImpl();
			praiseScenicSpotValidator.setValidateHelper(validateHelper);
			ValidateResult validator = ((DomainValidator<PraiseScenicSpot>) praiseScenicSpotValidator)
					.validate(praiseScenicSpot);
			if (validator.hasError()) {
				throw new BusinessValidationException(
						validator.getErrorMessages());
			}
			praiseScenicSpot = praiseScenicSpotDao
					.addPraiseScenicSpot(praiseScenicSpot);
			return praiseScenicSpot;
		} catch (Exception e) {
			throw new ServiceValidationException("新增信息出现错误", e);
		}
	}

	@Override
	public PraiseScenicSpot getPraiseScenicSpotById(Long id) {
		return praiseScenicSpotDao.getPraiseScenicSpotById(id);
	}

}
