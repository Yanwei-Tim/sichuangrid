package com.tianque.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchSmsSendBoxDao;
import com.tianque.domain.SmsSendBox;
import com.tianque.domain.vo.SearchSmsSendBoxVo;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.service.SearchSmsSendBoxService;

@Transactional
@Service("SearchSmsSendBoxService")
public class SearchSmsSendBoxServiceImpl extends AbstractBaseService implements
		SearchSmsSendBoxService {
	@Autowired
	private SearchSmsSendBoxDao searchSmsSendBoxDao;

	@Override
	public PageInfo<SmsSendBox> searchSmsSendBox(
			SearchSmsSendBoxVo searchSmsSendBoxVo, Integer year, Integer month,
			int pageNum, int pageSize, String sortField, String order) {
		if (searchSmsSendBoxVo == null) {
			return constructEmptyPageInfo();
		} else {
			// 判断是否是查询当前年月份的数据
			if (CalendarUtil.compareYearAndMonth(year, month)) {
				return searchSmsSendBoxDao.searchSmsSendBox(searchSmsSendBoxVo,
						pageNum, pageSize, sortField, order);
			} else {
				String date = year.toString()
						+ (month.toString().length() == 2 ? month.toString()
								: ("0" + month.toString()));
				try {
					return searchSmsSendBoxDao.searchSmsSendBox(
							searchSmsSendBoxVo, date, pageNum, pageSize,
							sortField, order);
				} catch (Exception e) {
					throw new ServiceValidationException("查询的表：smsSendBoxs_"
							+ date + "不存在，或者查询出错。", e);
				}
			}
		}
	}

	private PageInfo<SmsSendBox> constructEmptyPageInfo() {
		PageInfo<SmsSendBox> result = new PageInfo<SmsSendBox>();
		result.setResult(new ArrayList<SmsSendBox>());
		return result;
	}

}
