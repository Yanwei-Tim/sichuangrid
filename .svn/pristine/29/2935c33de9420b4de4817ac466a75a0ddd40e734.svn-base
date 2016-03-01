package com.tianque.peopleLog.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.peopleLog.domain.PeopleLogAttachFiles;

public interface PeopleLogService {
	/**
	 * 新增民情日志
	 * 
	 * @param peopleLog
	 * @return
	 */
	public PeopleLog addPeopleLog(PeopleLog peopleLog);

	/**
	 * 根据idList删除多个民情日志
	 * 
	 * @param idList
	 */
	public void deletePeopleLogByIds(List<Long> idList);

	/**
	 * 根据id获得民情日志
	 * 
	 * @param id
	 * @return
	 */
	public PeopleLog getPeopleLogById(Long id);

	/**
	 * 修改民情日志
	 * 
	 * @param peopleLog
	 * @return
	 */
	public PeopleLog updatePeopleLog(PeopleLog peopleLog, String[] attachFiles);

	/***
	 * 获取当前登录用户的日志列表
	 * 
	 * @return
	 */
	public PageInfo<PeopleLog> findPeopleLogForPageByUserId(Long userId,
			Integer pageNum, Integer pageSize, String sortField, String sord,
			Integer commentNums);

	public PageInfo findPeopleLogForPageByOrgInternalCode4Bench(
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord, String orgName);

	public List<PeopleLogAttachFiles> addAttachFileByPeopleLogId(
			Long peopleLogId, String[] attachFiles);

	public List<PeopleLogAttachFiles> findPeopleLogAttachFilesByPeopleLogId(
			final Long peopleLogId);

	public void deletePeopleLogAttachFileById(final Long id);

	public PeopleLogAttachFiles getPeopleLogAttachFileById(final Long id);

	public PeopleLog addPeopleLogForServiceRecord(PeopleLog peopleLog,
			String[] attachFiles);
}
