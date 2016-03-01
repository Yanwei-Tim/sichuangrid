package com.tianque.plugin.weChat.dao;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.user.WeChatMenu;

public interface WeChatMenuDao {
	/**
	 * 微信菜单 列表
	 * @param parameterMap
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<WeChatMenu> findWeChatMenu(Map<String, Object> parameterMap, Integer pageNum,
			Integer pageSize);

	/**
	 * 菜单与素材绑定
	 * @param weChatMenu
	 * @return
	 */
	public Integer updateWeChatMenu(WeChatMenu weChatMenu);
	/**
	 * 根据sourceid修改素材描述
	 * @param weChatMenu
	 * @return
	 */
	public Integer updateWeChatMenuBySourceId(WeChatMenu weChatMenu);

	/**
	 * 加载微信菜单对象
	 * @param id
	 * @return
	 */
	public WeChatMenu getWeChatMenuById(Long id);

	/**
	 * 根据素材id加载微信菜单（条件like）
	 * @param sourceId
	 * @return
	 */
	public List<WeChatMenu> getWeChatMenuBySourceId(String sourceId);
	/**
	 * 根据服务号修改菜单
	 * @param weChatMenu
	 * @return
	 */
	public Integer updateWeChatMenuByWeChatUserId(WeChatMenu weChatMenu);
	/**
	 * 根据服务号和key加载菜单
	 * @param id
	 * @return
	 */
	public WeChatMenu getWeChatMenuByMenuKeyAndWeChatUserId(String menuKey, String weChatUserId);
	/**
	 * 根据服务号和层级加载菜单 
	 * @param rank 1:一级 2：二级
	 * @param weChatUserId
	 * @return
	 */
	public List<WeChatMenu> getWeChatMenuByWeChatUserIdAndRank(Long rank, String weChatUserId);
	/**
	 * 根据服务号和叶子结点加载菜单
	 * @param isLeaf
	 * @param weChatUserId
	 * @return
	 */
	public List<WeChatMenu> getWeChatMenuByWeChatUserIdAndIsleaf(Long isLeaf, String weChatUserId);
	/**
	 * 根据微服务号删除菜单
	 * @param weChatUserId
	 * @return
	 */
	public Integer deleteWeChatMenuByWeChatUserId(String  weChatUserId);
	/**
	 * 保存菜单
	 * @param weChatMenu
	 * @return
	 */
	
	public Long addWeChatMenu(WeChatMenu weChatMenu);
	/**
	 * 查询所有菜单
	 * @param weChatUserId
	 * @return
	 */
	public List<WeChatMenu> getWeChatMenuByWeChatUserId(String weChatUserId);
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public void deleteWeChatMenuById(Long id);
}
