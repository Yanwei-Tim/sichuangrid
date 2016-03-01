package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.HelpPersonnel;

public interface HelpPersonnelDao {
	public HelpPersonnel addHelpPersonnel(HelpPersonnel helpPersonnel);

	public HelpPersonnel updateHelpPersonnel(HelpPersonnel helpPersonnel);

	public PageInfo<HelpPersonnel> findHelpPersonnel(Long personnelId, int pageNum, int pageSize,
			String sortField, String order, String personnelType);

	public void deleteHelpPersonnel(Long placeId);

	public List<HelpPersonnel> findperHelpPersonnelForList(Long personnelId, String personnelType);

	public HelpPersonnel getHelpPersonnel(Long id);

	public void deleteHelpPersonnel(Long personnelId, String personnelType);

	public List<HelpPersonnel> searchPersonInCharegeForAutoComplete(Long personnelId,
			String personnelType, String tag);

}
