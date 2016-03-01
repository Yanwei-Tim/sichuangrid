package com.tianque.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchSmsReceivedBoxDao;
import com.tianque.domain.SmsReceivedBox;
import com.tianque.domain.vo.SearchSmsReceivedBoxVo;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.service.SearchSmsReceivedBoxService;

@Transactional
@Service("SearchSmsReceivedBoxService")
public class SearchSmsReceivedBoxServiceImpl extends AbstractBaseService
		implements SearchSmsReceivedBoxService {
	@Autowired
	private SearchSmsReceivedBoxDao searchSmsReceivedBoxDao;

	@Override
	public PageInfo<SmsReceivedBox> searchSmsReceivedBox(
			SearchSmsReceivedBoxVo searchSmsReceivedBoxVo, Integer year,
			Integer month, int pageNum, int pageSize, String sortField,
			String order) {
		if (searchSmsReceivedBoxVo == null) {
			return constructEmptyPageInfo();
		} else {
			// 判断是否是查询当前年月份的数据
			if (CalendarUtil.compareYearAndMonth(year, month)) {
				return searchSmsReceivedBoxDao.searchSmsReceivedBox(
						searchSmsReceivedBoxVo, pageNum, pageSize, sortField,
						order);
			} else {
				String date = year.toString()
						+ (month.toString().length() == 2 ? month.toString()
								: ("0" + month.toString()));
				try {
					return searchSmsReceivedBoxDao.searchSmsReceivedBox(
							searchSmsReceivedBoxVo, date, pageNum, pageSize,
							sortField, order);
				} catch (Exception e) {
					throw new ServiceValidationException(
							"查询的表：smsReceivedBoxs_" + date + "不存在，或者查询出错。", e);
				}
			}
		}
	}

	private PageInfo<SmsReceivedBox> constructEmptyPageInfo() {
		PageInfo<SmsReceivedBox> result = new PageInfo<SmsReceivedBox>();
		result.setResult(new ArrayList<SmsReceivedBox>());
		return result;
	}
}
