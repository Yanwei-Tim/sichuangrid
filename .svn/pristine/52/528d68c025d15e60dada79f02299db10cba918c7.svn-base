package com.tianque.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.HelpPersonnelDao;
import com.tianque.domain.HelpPersonnel;
import com.tianque.service.HelpPersonnelService;

@Service("helpPersonnelService")
public class HelpPersonnelServiceImpl extends AbstractBaseService implements HelpPersonnelService {
	@Autowired
	private HelpPersonnelDao helpPersonnelDao;

	@Override
	public HelpPersonnel addHelpPersonnel(HelpPersonnel helpPersonnel) {
		if (helpPersonnel.getName() != null) {
			setChinesePinyin(helpPersonnel);
		}
		return helpPersonnelDao.addHelpPersonnel(helpPersonnel);
	}

	@Override
	public void deleteHelpPersonnel(Long personnelId) {
		helpPersonnelDao.deleteHelpPersonnel(personnelId);
	}

	@Override
	public void deleteHelpPersonnel(Long personnelId, String personnelType) {
		helpPersonnelDao.deleteHelpPersonnel(personnelId, personnelType);
	}

	@Override
	public PageInfo<HelpPersonnel> findHelpPersonnel(Long personnelId, int pageNum, int pageSize,
			String sortField, String order, String personnelType) {
		return helpPersonnelDao.findHelpPersonnel(personnelId, pageNum, pageSize, sortField, order,
				personnelType);
	}

	@Override
	public List<HelpPersonnel> findHelpPersonnelForList(Long personnelId, String personnelType) {
		return helpPersonnelDao.findperHelpPersonnelForList(personnelId, personnelType);
	}

	@Override
	public HelpPersonnel updateHelpPersonnel(HelpPersonnel helpPersonnel) {
		return helpPersonnelDao.updateHelpPersonnel(helpPersonnel);
	}

	@Override
	public List<HelpPersonnel> searchPersonInCharegeForAutoComplete(Long personnelId,
			String personnelType, String tag) {
		return helpPersonnelDao.searchPersonInCharegeForAutoComplete(personnelId, personnelType,
				tag);
	}

	@Override
	public HelpPersonnel getHelpPersonnel(Long id) {
		return helpPersonnelDao.getHelpPersonnel(id);
	}

	private void setChinesePinyin(HelpPersonnel helpPersonnel) {
		Map<String, String> pinyin = Chinese2pinyin.changeChinese2Pinyin(helpPersonnel.getName());
		helpPersonnel.setFullPinyin((String) pinyin.get("fullPinyin"));
		helpPersonnel.setSimplePinyin((String) pinyin.get("simplePinyin"));
	}

}
