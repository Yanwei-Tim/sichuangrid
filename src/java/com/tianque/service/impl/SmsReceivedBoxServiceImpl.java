package com.tianque.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SmsReceivedBoxDao;
import com.tianque.domain.SmsReceivedBox;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.SmsReceivedBoxService;

@Transactional
@Service("SmsReceivedBoxService")
public class SmsReceivedBoxServiceImpl extends AbstractBaseService implements
		SmsReceivedBoxService {

	@Autowired
	private SmsReceivedBoxDao smsReceivedBoxDao;

	@Override
	public SmsReceivedBox getSmsReceivedBoxById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id没有获得");
		}
		return smsReceivedBoxDao.getSmsReceivedBoxById(id);
	}

	@Override
	public SmsReceivedBox addSmsReceivedBox(SmsReceivedBox smsReceivedBox) {
		if (smsReceivedBox == null) {
			throw new BusinessValidationException("id没有获得");
		}
		return smsReceivedBoxDao.addSmsReceivedBox(smsReceivedBox);
	}

	@Override
	public SmsReceivedBox updateSmsReceivedBox(SmsReceivedBox smsReceivedBox) {
		if (smsReceivedBox.getId() == null) {
			throw new BusinessValidationException("id没有获得");
		}
		if (!processValidate(smsReceivedBox)) {
			throw new BusinessValidationException("数据输入错误");
		}
		return smsReceivedBoxDao.updateSmsReceivedBox(smsReceivedBox);
	}

	@Override
	public boolean deleteSmsReceivedBoxById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id没有获得");
		}
		if (smsReceivedBoxDao.deleteSmsReceivedBoxById(id) >= 0) {
			return true;
		}
		return false;
	}

	@Override
	public PageInfo<SmsReceivedBox> findSmsReceivedBoxByOwnerId(Long orgId,
			Integer year, Integer month, int pageNum, int pageSize,
			String sortField, String order) {
		if (orgId == null || orgId == 0L) {
			return constructEmptyPageInfo();
		} else {
			// 判断是否是查询当前年月份的数据
			if (CalendarUtil.compareYearAndMonth(year, month)) {
				return smsReceivedBoxDao.findSmsReceivedBoxByOwnerId(orgId,
						pageNum, pageSize, sortField, order);
			} else {
				String date = year.toString()
						+ (month.toString().length() == 2 ? month.toString()
								: ("0" + month.toString()));
				return smsReceivedBoxDao.findSmsReceivedBoxByOwnerId(orgId,
						date, pageNum, pageSize, sortField, order);
			}
		}
	}

	private boolean validate(SmsReceivedBox smsReceivedBox) {
		if (!StringUtil.isStringAvaliable(smsReceivedBox.getSourceMobile())
				|| !StringUtil.isStringAvaliable(smsReceivedBox
						.getTargetMobile())
				|| !StringUtil
						.isStringAvaliable(smsReceivedBox.getSmsContent())
				|| smsReceivedBox.getSmsContent().trim().length() < 2) {
			return false;
		}
		return true;
	}

	private boolean processValidate(SmsReceivedBox smsReceivedBox) {
		if (!StringUtil.isStringAvaliable(smsReceivedBox.getDisposition())
				|| smsReceivedBox.getDisposition().trim().length() < 2) {
			return false;
		}
		return true;
	}

	private PageInfo<SmsReceivedBox> constructEmptyPageInfo() {
		PageInfo<SmsReceivedBox> result = new PageInfo<SmsReceivedBox>();
		result.setResult(new ArrayList<SmsReceivedBox>());
		return result;
	}

	@Override
	public int countUnprocessSmsReceivedBoxByOwnerId(Long userId) {
		return smsReceivedBoxDao.countUnprocessSmsReceivedBoxByOwnerId(userId);
	}
}
