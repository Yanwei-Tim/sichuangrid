package com.tianque.peopleLog.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.peopleLog.domain.PeopleLogAttachFiles;

public interface PeopleLogDao {
	/**
	 * 新增民情日志
	 * 
	 * @param peopleLog
	 * @return PeopleLog
	 */
	public PeopleLog addPeopleLog(PeopleLog peopleLog);

	/**
	 * 根据id删除日志
	 * 
	 * @param id
	 */
	public void deletePeopleLogById(long id);

	/**
	 * 根据日志id删除日志评论
	 * 
	 * @param logId
	 */
	public void deleteCommentLogByLogId(long logId);

	/**
	 * 根据id获得民情日志对象
	 * 
	 * @param id
	 * @return PeopleLog
	 */
	public PeopleLog getPeopleLogById(long id);

	/**
	 * 修改日志
	 * 
	 * @param peopleLog
	 * @return peopleLog
	 */
	public PeopleLog updatePeopleLog(PeopleLog peopleLog);

	/**
	 * 根据用户id查找民情日志
	 * 
	 * @return peopleLogList
	 */
	public List<PeopleLog> getPeopleLogByUserId(long userId);

	/**
	 * 根据网格编码分页查找民情日志列表
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param sord
	 * @return
	 */
	public PageInfo<PeopleLog> findPeopleLogForPageByOrgInternalCode(String orgInternalCode,
			Integer pageNum, Integer pageSize, String sortField, String sord, Integer commentNums);

	/** 根据登录用户的id查询日志 */
	public PageInfo<PeopleLog> findPeopleLogForPageByUserId(Long userId, Integer pageNum,
			Integer pageSize, String sortField, String sord, Integer commentNums);

	public void commentPeopleLog(Integer commentNum, Long id);

	/**
	 * 工作台 根据网格编码分页查找民情日志列表
	 * 
	 * @param orgInternalCode
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param sord
	 * @param orgName
	 * @return
	 */
	public PageInfo<PeopleLog> findPeopleLogForPageByOrgInternalCode4Bench(String orgInternalCode,
			Integer pageNum, Integer pageSize, String sortField, String sord, String orgName);

	/**
	 * 添加附件
	 * 
	 * @param peopleLogAttachFiles
	 */
	public void addPeopleLogAttachFiles(PeopleLogAttachFiles peopleLogAttachFiles);

	/**
	 * 根据日志Id查找附件
	 * 
	 * @param peopleLogId
	 * @return
	 */
	public List<PeopleLogAttachFiles> findPeopleLogAttachFilesByPeopleLogId(Long peopleLogId);

	public PeopleLogAttachFiles getPeopleLogAttachFileById(final Long id);

	public void deletePeopleLogAttachFileById(final Long id);

	public void deletePeopleLogAttachFilesByPeopleLogId(final Long peopleLogId);
}
