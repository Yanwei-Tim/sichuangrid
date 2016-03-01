package com.tianque.plugin.weChat.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.user.Fan;

public interface FanDao {
	/**
	 * 微信粉丝列表
	 * @param parameterMap
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Fan> findFan(Map<String, Object> parameterMap, Integer pageNum,
			Integer pageSize);
	/**新增粉丝信息*/
	public Long addFan(Fan fan);

	/**根据openId和服务号获取对象(防止一个微信号同时关注两个服务号)*/
	public Fan getFanByOpenIdAndWeChatUserId(String openId, String weChatUserId);

	/**根据服务号获取粉丝列表*/
	public List<Fan> getFanListByWeChatUserId(String weChatUserId);
	/**根据服务号获取粉丝分页列表*/
	public PageInfo<Fan> findFanListByWeChatUserId(Map<String, Object> parameterMap, Integer pageNum,
			Integer pageSize);

	/**根据openId和服务号删除粉丝信息*/
	public int deleteFanByOpenIdAndWeChatUserId(String openId, String weChatUserId);

	/**根据服务号和群组Id获取粉丝列表*/
	public List<Fan> getFanListByGroupIdAndWeChatUserId(Long groupId, String weChatUserId);
	/**根据服务号和群组Id获取粉丝列表(条件 in)*/
	public List<Fan> getFanListByGroupIdsAndWeChatUserId(String groupIds, String weChatUserId);
	
	/**根据服务号和群组Id ，服务号48小时内有互动      获取粉丝列表（用于转发）*/
	public List<Fan> getFanListByGroupIdAndWeChatUserIdAndBeforDate(Long groupId, String weChatUserId,Date beforeDate);
	
	/**
	 * 修改昵称
	 * @param fan
	 * @return
	 */
	public Integer updateFanById(Fan fan);
	/**
	 * 根据id加载对象	
	 * @param fan
	 * @return
	 */
	public Fan getFanById(Fan fan) ;
	/**
	 * 根据ids加载对象	
	 * @param fanIds
	 * @return
	 */
	public List<Fan> getFanByIds(String fanIds);
}
