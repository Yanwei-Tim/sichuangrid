package com.tianque.plugin.weChat.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.user.Fan;
import com.tianque.plugin.weChat.domain.user.TencentUser;

public interface FanService {
	/**
	 * 微信粉丝列表
	 * @param parameterMap
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Fan> findFan(Fan fan, Integer pageNum, Integer pageSize, String sidx,
			String sord);
	/**新增粉丝信息*/
	public Long saveFan(Map<String, String> messageMap, TencentUser tencentUser);

	/**根据openId和服务号获取对象(防止一个微信号同时关注两个服务号)*/
	public Fan getFanByOpenIdAndWeChatUserId(String openId, String weChatUserId);

	/**根据openId和服务号删除粉丝信息*/
	public int deleteFanByOpenIdAndWeChatUserId(String openId, String weChatUserId);

	/**根据服务号获取粉丝列表*/
	public List<Fan> getFanListByWeChatUserId(String weChatUserId);
	/**根据(Fan中的服务号)获取粉丝分页列表*/
	public PageInfo<Fan> findFanListByWeChatUserIdForPage(Fan fan,Integer pageNum, Integer pageSize, String sidx,
			String sord);

	/**根据服务号和群组Id获取粉丝列表(用于模拟群发 条件：in)*/
	public List<Fan> getFanListByGroupIdsAndWeChatUserId(String groupIds, String weChatUserId);
	/**根据服务号和群组Id获取粉丝列表(用于模拟群发)*/
	public List<Fan> getFanListByGroupIdAndWeChatUserId(Long groupId, String weChatUserId);
	
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
	 * 移动粉丝
	 * @param fan
	 * @return
	 */
	public Integer removeFan(Fan fan,String fanIds);
	/**
	 * 根据ids加载对象	
	 * @param fanIds
	 * @return
	 */
	public List<Fan> getFanByIds(String fanIds);

}
