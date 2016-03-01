package com.tianque.plugin.weChat.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.dao.FanDao;
import com.tianque.plugin.weChat.domain.ModuleTable;
import com.tianque.plugin.weChat.domain.user.Fan;

@Repository("fanDao")
public class FanDaoImpl extends AbstractBaseDao implements FanDao {

	/**
	 * 微信粉丝列表
	 * @param parameterMap
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Fan> findFan(Map<String, Object> parameterMap, Integer pageNum, Integer pageSize) {
		return getPageInfoByParamMap(new PageInfo<ModuleTable>(), "fan.countFindFan",
				"fan.findFan", pageNum, pageSize, parameterMap);
	}

	@Override
	public PageInfo<Fan> findFanListByWeChatUserId(Map<String, Object> parameterMap,
			Integer pageNum, Integer pageSize) {
		return getPageInfoByParamMap(new PageInfo<ModuleTable>(), "fan.countFanListByWeChatUserId",
				"fan.findFanListByWeChatUserId", pageNum, pageSize, parameterMap);
	}

	@Override
	public Long addFan(Fan fan) {
		return (Long) getSqlMapClientTemplate().insert("fan.saveFan", fan);

	}

	@Override
	public Fan getFanByOpenIdAndWeChatUserId(String openId, String weChatUserId) {
		Fan fan = new Fan();
		fan.setOpenId(openId);
		fan.setWeChatUserId(weChatUserId);
		return (Fan) getSqlMapClientTemplate().queryForObject("fan.getFanByOpenIdAndWeChatUserId",
				fan);
	}

	@Override
	public int deleteFanByOpenIdAndWeChatUserId(String openId, String weChatUserId) {
		Fan fan = new Fan();
		fan.setOpenId(openId);
		fan.setWeChatUserId(weChatUserId);
		return getSqlMapClientTemplate().delete("fan.deleteFanByOpenIdAndWeChatUserId", fan);
	}

	/**根据服务号获取粉丝列表*/
	public List<Fan> getFanListByWeChatUserId(String weChatUserId) {
		return (List<Fan>) getSqlMapClientTemplate().queryForList("fan.getFanListByWeChatUserId",
				weChatUserId);
	}

	/**根据服务号和群组Id获取粉丝列表 (条件：in)*/
	public List<Fan> getFanListByGroupIdsAndWeChatUserId(String groupIds, String weChatUserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupIds", groupIds);
		map.put("weChatUserId", weChatUserId);
		return (List<Fan>) getSqlMapClientTemplate().queryForList(
				"fan.getFanListByGroupIdsAndWeChatUserId", map);
	}

	/**根据服务号和群组Id获取粉丝列表*/
	public List<Fan> getFanListByGroupIdAndWeChatUserId(Long groupId, String weChatUserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupId", groupId);
		map.put("weChatUserId", weChatUserId);
		return (List<Fan>) getSqlMapClientTemplate().queryForList(
				"fan.getFanListByGroupIdAndWeChatUserId", map);
	}

	/**根据服务号和群组Id ，服务号48小时内有互动      获取粉丝列表（用于转发）*/
	public List<Fan> getFanListByGroupIdAndWeChatUserIdAndBeforDate(Long groupId,
			String weChatUserId, Date beforeDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupId", groupId);
		map.put("weChatUserId", weChatUserId);
		map.put("beforeDate", beforeDate);
		return (List<Fan>) getSqlMapClientTemplate().queryForList(
				"fan.getFanListByGroupIdAndWeChatUserIdAndBeforDate", map);
	}

	/**
	 * 修改昵称
	 * @param fan
	 * @return
	 */
	public Integer updateFanById(Fan fan) {
		return (Integer) getSqlMapClientTemplate().update("fan.updateFanById", fan);
	}

	/**
	 * 根据id加载对象	
	 * @param fan
	 * @return
	 */
	public Fan getFanById(Fan fan) {
		return (Fan) getSqlMapClientTemplate().queryForObject("fan.getFanById", fan);
	}

	/**
	 * 根据ids加载对象	
	 * @param fanIds
	 * @return
	 */
	public List<Fan> getFanByIds(String fanIds) {
		return (List<Fan>) getSqlMapClientTemplate().queryForList("fan.getFanByIds", fanIds);
	}
}
