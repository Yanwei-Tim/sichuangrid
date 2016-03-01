package com.tianque.plugin.weChat.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.dao.WeChatSourceDao;
import com.tianque.plugin.weChat.domain.ModuleTable;
import com.tianque.plugin.weChat.domain.user.WeChatSource;

@Repository("weChatSourceDao")
public class WeChatSourceDaoImpl extends AbstractBaseDao implements WeChatSourceDao {
	/**
	 * 素材列表
	 * @param parameterMap
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<WeChatSource> findWeChatSource(Map<String, Object> parameterMap,
			Integer pageNum, Integer pageSize) {

		return getPageInfoByParamMap(new PageInfo<ModuleTable>(), "source.countFindWeChatSource",
				"source.findWeChatSource", pageNum, pageSize, parameterMap);

	}

	/**
	 * 添加素材
	 * @param weChatSource
	 * @return
	 */
	public Long addWeChatSource(WeChatSource weChatSource) {
		return (Long) getSqlMapClientTemplate().insert("source.saveWechatSource", weChatSource);
	}

	/**
	 * 修改素材
	 * @param weChatSource
	 * @return
	 */
	public Integer updateWeChatSource(WeChatSource weChatSource) {
		return (Integer) getSqlMapClientTemplate()
				.update("source.updateWeChatSource", weChatSource);
	}

	/**
	 * 删除素材
	 * @param id
	 * @return
	 */
	public Integer deleteWeChatSource(Long id) {
		return (Integer) getSqlMapClientTemplate().delete("source.deleteWeChatSourceById", id);
	}

	/**
	 * 根据服务号删除素材
	 * @param id
	 * @return
	 */
	public Integer deleteWeChatSourceByWeChatUserId(String weChatUserId) {
		return (Integer) getSqlMapClientTemplate().delete(
				"source.deleteWeChatSourceByWeChatUserId", weChatUserId);
	}

	/**
	 * 加载素材对象
	 * @param id
	 * @return
	 */
	public WeChatSource getWeChatSource(Long id) {
		return (WeChatSource) getSqlMapClientTemplate().queryForObject(
				"source.getWeChatSourceById", id);
	}

	/**
	 * 加载素材对象集合
	 * @param ids
	 * @return
	 */
	public List<WeChatSource> getWeChatSourceByIds(String ids) {
		return (List<WeChatSource>) getSqlMapClientTemplate().queryForList(
				"source.getWeChatSourceByIds", ids);
	}

	/**
	 * 根据微信号和类型获取素材总数(用于每个服务号所添加的类型里个数的校验)
	 * @param weChatUserId
	 *  @param sourceType
	 * @return
	 */
	public Long getCountByWeChatUserIdAndSourceType(String weChatUserId, long sourceType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weChatUserId", weChatUserId);
		map.put("sourceType", sourceType);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"source.getCountByWeChatUserIdAndSourceType", map);
	}

	/**
	 * 根据微信号素材总数(用于每个服务号所添加的素材个数的校验)
	 * @param weChatUserId
	 * @return
	 */
	public Long getCountByWeChatUserId(String weChatUserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weChatUserId", weChatUserId);
		return (Long) getSqlMapClientTemplate()
				.queryForObject("source.getCountByWeChatUserId", map);
	}

	/**
	 * 根据orgId和类型加载素材集合
	 * @param orgId
	 * @param sourceType
	 * @return
	 */

	public List<WeChatSource> getWeChatSourceByOrgIdAndSourceType(long orgId, long sourceType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("sourceType", sourceType);
		return (List<WeChatSource>) getSqlMapClientTemplate().queryForList(
				"source.getWeChatSourceByOrgIdAndSourceType", map);
	}
}
