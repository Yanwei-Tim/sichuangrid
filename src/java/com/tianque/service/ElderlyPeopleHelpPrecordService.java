package com.tianque.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.HelpPrecord;

public interface ElderlyPeopleHelpPrecordService {

	public PageInfo<HelpPrecord> findHelpPrecord(Long personnelId, int pageNum, int pageSize,
			String sortField, String order, String personnelType);

	public HelpPrecord addHelpPrecord(HelpPrecord helpPrecord);

	public HelpPrecord updateHelpPrecord(HelpPrecord helpPrecord);

	public void deleteHelpPrecordById(Long id);

	public HelpPrecord getHelpPrecord(Long id);

}
