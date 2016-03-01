package com.tianque.workBench.tableConfig.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.ThreadVariable;
import com.tianque.service.util.TabPatel;
import com.tianque.service.util.WorkBenchTabConfiger;
import com.tianque.workBench.patelConfiger.dao.PatelDao;

@Service("tableService")
@Transactional
public class TableServiceImpl implements TableService {
	@Autowired
	private PatelDao patelDao;

	public List<String> getPatelConfigerKeyName() {
		Long userId = ThreadVariable.getUser().getId();
		List<String> list = patelDao.getKeyNamesByUserId(userId);
		List<String> temp = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			temp.addAll(patelDao.getTabConfiger(userId, list.get(i)));
		}
		list.addAll(temp);
		return list;
	}

	public List<WorkBenchTabConfiger> getPatelConfiger() {
		Long userId = ThreadVariable.getUser().getId();
		return obtainEntity(patelDao.getKeyNamesByUserId(userId));
	}

	public List<TabPatel> getTabConfiger(String keyName) {
		Long userId = ThreadVariable.getUser().getId();
		return obtainTabEntity(keyName, userId);
	}

	public void addConfiguration(String keyName) {
		if (!"".equals(keyName)) {
			Long userId = ThreadVariable.getUser().getId();
			Integer index = patelDao.getCurrentMaxConfiger();
			if (index == null) {
				index = 0;
			} else {
				index++;
			}
			buildConfiguration(userId, keyName, index);
		}
	}

	public void addTabConfiguration(String keyName, String tabKeyName) {
		Long userId = ThreadVariable.getUser().getId();
		Integer index = patelDao.getIndexByUserIdAndKeyname(userId, keyName);
		Integer tabIndex = patelDao.getCurrentMaxTabConfiger(keyName);
		if (tabIndex == null) {
			tabIndex = 0;
		} else {
			tabIndex++;
		}
		patelDao.buildConfiguration(userId, keyName, index, tabKeyName,
				tabIndex);
	}

	private List<WorkBenchTabConfiger> obtainEntity(List<String> list) {
		List<WorkBenchTabConfiger> temps = new ArrayList<WorkBenchTabConfiger>();
		for (int i = 0; i < list.size(); i++) {
			WorkBenchTabConfiger workBenchTabConfiger = new WorkBenchTabConfiger();
			WorkBenchTabConfiger temp = WorkBenchTabConfiger.allWorkBenchTabConfiger
					.get(list.get(i));
			workBenchTabConfiger.setKeyName(temp.getKeyName());
			workBenchTabConfiger.setEname(temp.getEname());
			workBenchTabConfiger.setCname(temp.getCname());
			workBenchTabConfiger.setUrl(temp.getUrl());
			workBenchTabConfiger.setMaxUrl(temp.getMaxUrl());
			temps.add(temp);
		}
		return temps;
	}

	private List<TabPatel> obtainTabEntity(String keyName, Long userId) {
		List<String> tabKeyNames = patelDao.getTabConfiger(userId, keyName);
		if (tabKeyNames == null || tabKeyNames.size() == 0) {
			return new ArrayList<TabPatel>();
		}
		TabPatel[] temp = new TabPatel[tabKeyNames.size()];
		for (TabPatel tabPatel : WorkBenchTabConfiger.allWorkBenchTabConfiger
				.get(keyName).getTabPatels()) {
			String tabKeyName = tabPatel.getKeyName();
			if (tabKeyNames.contains(tabKeyName)) {
				temp[tabKeyNames.indexOf(tabKeyName)] = tabPatel;
			}
		}
		return Arrays.asList(temp);
	}

	public void buildConfiguration(Long userId, String keyName, Integer index) {
		patelDao.buildConfiguration(userId, keyName, index, null, null);
	}
}
