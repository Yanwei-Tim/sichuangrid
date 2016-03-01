package com.tianque.plugin.weChat.dao;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.user.WeChatSource;

public interface WeChatSourceDao {
	/**
	 * 素材列表
	 * @param parameterMap
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<WeChatSource> findWeChatSource(Map<String, Object> parameterMap,
			Integer pageNum, Integer pageSize);

	/**
	 * 添加素材
	 * @param keyWord
	 * @return
	 */
	public Long addWeChatSource(WeChatSource weChatSource);

	/**
	 * 修改素材
	 * @param weChatSource
	 * @return
	 */
	public Integer updateWeChatSource(WeChatSource weChatSource);

	/**
	 * 删除素材
	 * @param id
	 * @return
	 */
	public Integer deleteWeChatSource(Long id);
	
	/**
	 * 根据服务号删除素材
	 * @param id
	 * @return
	 */
	public Integer deleteWeChatSourceByWeChatUserId(String weChatUserId);

	/**
	 * 加载素材对象
	 * @param id
	 * @return
	 */
	public WeChatSource getWeChatSource(Long id);
	/**
	 * 加载素材对象集合
	 * @param ids
	 * @return
	 */
	public List<WeChatSource> getWeChatSourceByIds(String  ids);

	/**
	 * 根据微信号和类型获取素材总数(用于每个服务号所添加的类型里个数的校验)
	 * @param weChatUserId
	 * @return
	 */
	public Long getCountByWeChatUserIdAndSourceType(String weChatUserId, long sourceType);
	/**
	 * 根据微信号素材总数(用于每个服务号所添加的素材个数的校验)
	 * @param weChatUserId
	 * @return
	 */
	public Long getCountByWeChatUserId(String weChatUserId);
	/**
	 * 根据orgId和类型加载素材集合
	 * @param orgId
	 * @param sourceType
	 * @return
	 */

	public List<WeChatSource> getWeChatSourceByOrgIdAndSourceType(long orgId, long sourceType);

}
