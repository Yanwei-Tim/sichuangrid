package com.tianque.workBench.tableConfig.service;

import java.util.List;

import com.tianque.service.util.TabPatel;
import com.tianque.service.util.WorkBenchTabConfiger;

public interface TableService {

	/**
	 * 获取常用配置
	 * 
	 * @return
	 */
	public List<WorkBenchTabConfiger> getPatelConfiger();

	/**
	 * 根据常用配置keyName获取标签配置
	 * 
	 * @param keyName
	 * @return
	 */
	public List<TabPatel> getTabConfiger(String keyName);

	/**
	 * 添加常用配置
	 * 
	 * @param keyName
	 * @param index
	 */
	public void addConfiguration(String keyName);

	/**
	 * 添加标签配置
	 * 
	 * @param keyName
	 * @param tabKeyName
	 * @param tabIndex
	 */
	public void addTabConfiguration(String keyName, String tabKeyName);

	/**
	 * 获取所有配置keyName
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> getPatelConfigerKeyName();
}
