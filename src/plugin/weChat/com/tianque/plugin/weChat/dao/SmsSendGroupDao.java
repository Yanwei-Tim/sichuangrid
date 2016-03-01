package com.tianque.plugin.weChat.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.sms.SmsSendGroup;

/**
 * 短信发送组
 * @ClassName: SmsSendGroupDao 
 * @author: he.simin
 * @date: 2015年11月5日 下午3:24:11
 */
public interface SmsSendGroupDao {
	/**
	 * 添加短信发送组
	 * @Title: saveSmsSendGroup 
	 * @param smsSendGroup
	 * @return: 
	 */
	public Long saveSmsSendGroup(SmsSendGroup smsSendGroup);

	/**
	 * id获取短信发送组
	 * @Title: getById 
	 * @param id
	 * @return: SmsSendGroup
	 */
	public SmsSendGroup getById(Long id);

	/**
	 * 根据短信发送id获取
	 * @Title: getBySmsSendId 
	 * @param smsSendId
	 * @return
	 * @return: SmsSendGroup
	 */
	public SmsSendGroup getBySmsSendId(String smsSendId);


	/**
	 * 更新短信发送组
	 * @Title: updateSmsSendGroup 
	 * @param smsSendGroup
	 * @return: void
	 */
	public void updateSmsSendGroup(SmsSendGroup smsSendGroup);

	/**
	 * 分页查询短信发送组
	 * @Title: findSmsSendGroup 
	 * @param smsSendGroup
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return: PageInfo<SmsSendGroup>
	 */
	public PageInfo<SmsSendGroup> findSmsSendGroup(SmsSendGroup smsSendGroup,
			Integer pageNum,
			Integer pageSize, String sidx, String sord);

}
