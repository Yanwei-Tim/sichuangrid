package com.tianque.plugin.weChat.dao;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.user.WeChatGroup;

public interface WeChatGroupDao {
	/**
	 * 微信分组列表
	 * @param parameterMap
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<WeChatGroup> findWeChatGroup(Map<String, Object> parameterMap, Integer pageNum,
			Integer pageSize);
	/**
	 * 修改分组名（本系统）
	 * @param weChatGroup
	 * @return
	 */

	public Integer updateWeChatGroupById(WeChatGroup weChatGroup);
	/**保存群组*/
	public Long addWeChatGroup(WeChatGroup weChatGroup);

	/**根据服务号id（微信端）和groupId（微信端）获取群租对象*/
	public WeChatGroup getGroupByGroupIdAndWeChatUserId(WeChatGroup weChatGroup);

	/**根据服务号id（微信端）群列表*/
	public List<WeChatGroup> getGroupListWeChatUserId(String weChatUserId);

	/**根据服务号id（微信端）删除群组信息*/
	public void deleteWeChatGroupByWeChatUserId(String weChatUserId);
	/**
	 * 根据id加载对象	
	 * @param weChatGroup
	 * @return
	 */
	public WeChatGroup getWeChatGroupById(WeChatGroup weChatGroup);
	
	/**
	 * 根据orgId得到群组信息
	 * @param orgId
	 * @return
	 */
	public List<WeChatGroup> getGroupListByOrgId(Long orgId);
	/**
	 * 根据groupName和微信公众号得到groupId
	 * @param map
	 * @return
	 */
	public WeChatGroup getGroupListByNameAndWeChatUserId(Map<String,Object> map);
}
