package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.HelpPersonnel;

public interface HelpPersonnelService {
	public HelpPersonnel addHelpPersonnel(HelpPersonnel helpPersonnel);

	public HelpPersonnel updateHelpPersonnel(HelpPersonnel helpPersonnel);

	public PageInfo<HelpPersonnel> findHelpPersonnel(Long personnelId, int pageNum, int pageSize,
			String sortField, String order, String personnelType);

	public void deleteHelpPersonnel(Long personnelId);

	public List<HelpPersonnel> findHelpPersonnelForList(Long personnelId, String personnelType);

	public void deleteHelpPersonnel(Long personnelId, String personnelType);

	public List<HelpPersonnel> searchPersonInCharegeForAutoComplete(Long personnelId,
			String personnelType, String tag);

	public HelpPersonnel getHelpPersonnel(Long id);

}
