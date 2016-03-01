package com.tianque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SmsMessageMarkDao;
import com.tianque.domain.SmsMessageMark;
import com.tianque.domain.property.SmsMessageMarkType;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.service.SmsMessageMarkService;

@Transactional
@Service("smsMessageMarkService")
public class SmsMessageMarkServiceImpl implements SmsMessageMarkService {
	@Autowired
	private SmsMessageMarkDao smsMessageMarkDao;

	@Override
	public SmsMessageMark getSimpleSmsMessageMarkById(Long id) {
		try {
			return smsMessageMarkDao.getSimpleSmsMessageMarkById(id);
		} catch (Exception e) {
			throw new ServiceValidationException("根据id获取短信提示语模版出错", e);
		}

	}

	@Override
	public SmsMessageMark addSmsMessageMark(SmsMessageMark smsMessageMark) {
		try {
			SmsMessageMark sms = new SmsMessageMark();
			if (smsMessageMark.getOperationtType() == SmsMessageMarkType.DEAL_CODE) {
				sms = smsMessageMarkDao
						.getSimpleSmsMessageMarkByDealType(smsMessageMark
								.getDealType());

			} else {
				sms = smsMessageMarkDao
						.getSimpleSmsMessageMarkByOperationtType(smsMessageMark
								.getOperationtType());
			}
			if (sms == null || sms.getId() == null) {
				sms = smsMessageMarkDao.addSmsMessageMark(smsMessageMark);
			}
			return sms;
		} catch (Exception e) {
			throw new ServiceValidationException("新增短信提示语模版出错", e);
		}

	}

	@Override
	public SmsMessageMark updateSmsMessageMark(SmsMessageMark smsMessageMark) {
		try {
			return smsMessageMarkDao.updateSmsMessageMark(smsMessageMark);
		} catch (Exception e) {
			throw new ServiceValidationException("更新短信提示语模版出错", e);
		}

	}

	@Override
	public boolean deleteSmsMessageMarkById(Long id) {
		try {
			smsMessageMarkDao.deleteSmsMessageMarkById(id);
		} catch (Exception e) {
			throw new ServiceValidationException("删除短信提示语模版出错", e);
		}
		return true;

	}

	@Override
	public PageInfo<SmsMessageMark> findSmsMessageMarks(Integer page,
			Integer rows, String sidx, String sord) {
		try {
			return smsMessageMarkDao
					.findSmsMessageMarks(page, rows, sidx, sord);
		} catch (Exception e) {
			throw new ServiceValidationException("更新短信提示语模版出错", e);
		}
	}

	@Override
	public SmsMessageMark getSimpleSmsMessageMarkByDealType(int dealType) {
		try {
			return smsMessageMarkDao
					.getSimpleSmsMessageMarkByDealType(dealType);
		} catch (Exception e) {
			throw new ServiceValidationException("根据处理类型获取短信提示语模版出错", e);
		}

	}

	@Override
	public SmsMessageMark getSimpleSmsMessageMarkByOperationtType(
			int operationtType) {
		try {
			return smsMessageMarkDao
					.getSimpleSmsMessageMarkByOperationtType(operationtType);
		} catch (Exception e) {
			throw new ServiceValidationException("根据操作类型获取短信提示语模版出错", e);
		}
	}
}
