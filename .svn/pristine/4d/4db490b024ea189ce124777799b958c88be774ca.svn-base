package com.tianque.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.HelpPrecord;

public interface HelpPrecordDao {
	public PageInfo<HelpPrecord> findHelpPrecord(Long personnelId, int pageNum, int pageSize,
			String sortField, String order, String personnelType);

	public HelpPrecord addHelpPrecord(HelpPrecord helpPrecord);

	public HelpPrecord updateHelpPrecord(HelpPrecord helpPrecord);

	public HelpPrecord getHelpPrecord(Long id);

	public void deleteHelpPrecord(Long personnelId, String personnelType);

	public void deleteHelpPrecordById(Long id);
}
