package com.tianque.baseInfo.base.service;

public interface SourcesStateService {

	/**
	 * 根据Type和ID，修改数据来源
	 * 
	 * @param type
	 * @param id
	 * @param sourcesState
	 */
	public abstract void updateSourcesStateById(String type, Long id, Long sourcesState);

}