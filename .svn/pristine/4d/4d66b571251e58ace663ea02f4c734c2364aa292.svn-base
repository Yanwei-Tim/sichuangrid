package com.tianque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.HelpPrecordDao;
import com.tianque.domain.HelpPrecord;
import com.tianque.service.ElderlyPeopleHelpPrecordService;

@Service("elderlyPeopleHelpPrecordService")
public class ElderlyPeopleHelpPrecordServiceImpl extends AbstractBaseService implements
		ElderlyPeopleHelpPrecordService {
	@Autowired
	private HelpPrecordDao helpPrecordDao;

	@Override
	public HelpPrecord addHelpPrecord(HelpPrecord helpPrecord) {
		helpPrecord = helpPrecordDao.addHelpPrecord(helpPrecord);
		return helpPrecord;
	}

	@Override
	public PageInfo<HelpPrecord> findHelpPrecord(Long personnelId, int pageNum, int pageSize,
			String sortField, String order, String personnelType) {
		return helpPrecordDao.findHelpPrecord(personnelId, pageNum, pageSize, sortField, order,
				personnelType);
	}

	@Override
	public HelpPrecord updateHelpPrecord(HelpPrecord helpPrecord) {
		return helpPrecordDao.updateHelpPrecord(helpPrecord);
	}

	@Override
	public void deleteHelpPrecordById(Long id) {
		helpPrecordDao.deleteHelpPrecordById(id);
	}

	@Override
	public HelpPrecord getHelpPrecord(Long id) {
		return helpPrecordDao.getHelpPrecord(id);
	}

}
