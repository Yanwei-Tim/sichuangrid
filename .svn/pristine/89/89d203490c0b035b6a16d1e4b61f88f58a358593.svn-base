package com.tianque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.HelpPrecordDao;
import com.tianque.domain.HelpPrecord;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WorkDiaryTypes;
import com.tianque.service.HelpPrecordService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.service.WorkDiaryService;

@Service("helpPrecordService")
public class HelpPrecordServiceImpl extends AbstractBaseService implements HelpPrecordService {
	@Autowired
	private HelpPrecordDao helpPrecordDao;
	@Autowired
	private WorkDiaryService workDiaryService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public HelpPrecord addHelpPrecord(HelpPrecord helpPrecord) {
		helpPrecord = helpPrecordDao.addHelpPrecord(helpPrecord);
		String content = workDiaryService.assemblingContent(helpPrecord.getPersonNmae(), "",
				helpPrecord.getEvents(), WorkDiaryTypes.HELP_PERSONNEL,
				helpPrecord.getPersonTypeName(), helpPrecord.getPersonnelType());
		PropertyDict propertyDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(PropertyTypes.WORKDIARY_DIARY_TYPE,
						WorkDiaryTypes.HELP_PERSONNEL);
		workDiaryService.addWorkDiary(propertyDict, WorkDiaryTypes.TYPE_HELPPERSONNEL,
				helpPrecord.getId(), content, helpPrecord.getAddress(),
				helpPrecord.getHelpPersonnelName(), helpPrecord.getHelpTime());
		return helpPrecord;
	}

	@Override
	public void deleteHelpPrecord(Long personnelId, String personnelType) {
		helpPrecordDao.deleteHelpPrecord(personnelId, personnelType);

	}

	@Override
	public PageInfo<HelpPrecord> findHelpPrecord(Long personnelId, int pageNum, int pageSize,
			String sortField, String order, String personnelType) {
		return helpPrecordDao.findHelpPrecord(personnelId, pageNum, pageSize, sortField, order,
				personnelType);
	}

	@Override
	public HelpPrecord updateHelpPrecord(HelpPrecord helpPrecord) {

		helpPrecord = helpPrecordDao.updateHelpPrecord(helpPrecord);
		String content = workDiaryService.assemblingContent(helpPrecord.getPersonNmae(), "",
				helpPrecord.getEvents(), WorkDiaryTypes.HELP_PERSONNEL,
				helpPrecord.getPersonTypeName(), helpPrecord.getPersonnelType());
		workDiaryService.updateWorkDiary(WorkDiaryTypes.TYPE_HELPPERSONNEL, helpPrecord.getId(),
				content, helpPrecord.getAddress(), helpPrecord.getHelpPersonnelName(),
				helpPrecord.getHelpTime());

		return helpPrecord;
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
