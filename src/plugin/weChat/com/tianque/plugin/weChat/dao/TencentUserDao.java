package com.tianque.plugin.weChat.dao;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.user.TencentUser;

public interface TencentUserDao {

	public PageInfo getTencentUserList(Map<String, Object> parameterMap, Integer pageNum,
			Integer pageSize);

	Long addTencentUser(TencentUser tencentUser);

	/**
	 * 微信号绑定自动回复消息（素材）
	 * @param tencentUser
	 * @return
	 */
		public Integer updateTencentUser(TencentUser tencentUser);
	TencentUser getTencentUserByTencentUserId(Long tencentUserId);

	void deleteTencentUserById(Long id);

	TencentUser getTencentUserByWeChatUserId(String weChatUserId);

	/**
	 * 根据素材id加载Tencent集合（条件Like）
	 * @param sourceId
	 * @return
	 */
	public List<TencentUser> getTencentUserBySourceId(String sourceId);
	/**根据组织机构获取微信公众账号列表*/
	public List<TencentUser> getTencentUserListByOrgId(Long orgId);

	/**根据组织机构编号获取微信公众账号列表*/
	public List<TencentUser> getTencentUserListByOrgCode(String orgInternalCode);

}
