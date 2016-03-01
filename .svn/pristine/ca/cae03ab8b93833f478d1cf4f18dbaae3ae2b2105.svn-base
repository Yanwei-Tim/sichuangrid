package com.tianque.plugin.weChat.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.dao.KeyWordDao;
import com.tianque.plugin.weChat.domain.ModuleTable;
import com.tianque.plugin.weChat.domain.user.KeyWord;

@Repository("keyWordDao")
public class KeyWordDaoImpl extends AbstractBaseDao implements KeyWordDao {
	/**
	 * 关键字列表
	 * @param parameterMap
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<KeyWord> findKeyWord(Map<String, Object> parameterMap, Integer pageNum,
			Integer pageSize) {

		return getPageInfoByParamMap(new PageInfo<ModuleTable>(), "keyWords.countFindKeyWords",
				"keyWords.findKeyWord", pageNum, pageSize, parameterMap);

	}

	/**
	 * 添加关键词
	 * @param keyWord
	 * @return
	 */
	public Long addKeyWord(KeyWord keyWord) {
		return (Long) getSqlMapClientTemplate().insert("keyWords.saveKeyWord", keyWord);
	}

	/**
	 * 删除关键词
	 * @param id
	 * @return
	 */
	public Integer deleteKeyWord(Long id) {
		return (Integer) getSqlMapClientTemplate().delete("keyWords.deleteKeyWordById", id);
	}

	/**
	 * 根据服务号删除关键词
	 * @param weChatUserId
	 * @return
	 */
	public Integer deleteKeyWordByWeChatUserId(String weChatUserId) {
		return (Integer) getSqlMapClientTemplate().delete("keyWords.deleteKeyWordByWeChatUserId",
				weChatUserId);
	}

	/**
	 * 修改关键词
	 * @param keyWord
	 * @return
	 */
	public Integer updateKeyWord(KeyWord keyWord) {
		return (Integer) getSqlMapClientTemplate().update("keyWords.updateKeyWord", keyWord);
	}

	/**
	 * 根据sourceId修改关键字表中的素材描述
	 * @param keyWord
	 * @return
	 */
	public Integer updateKeyWordBySourceId(KeyWord keyWord) {
		return (Integer) getSqlMapClientTemplate().update("keyWords.updateKeyWordBySourceId",
				keyWord);
	}

	/**
	 * 验证关键词存在
	 * @param keyWord
	 * @return
	 */
	public KeyWord validateKeyWordByWeChatUserIdAndKeyName(KeyWord keyWord) {
		return (KeyWord) getSqlMapClientTemplate().queryForObject(
				"keyWords.getKeyWordByWeChatIdAndKeyName", keyWord);
	}

	/**
	 * 加载关键词对象
	 * @param id
	 * @return
	 */
	public KeyWord getKeyWordById(Long id) {
		return (KeyWord) getSqlMapClientTemplate().queryForObject("keyWords.getKeyWordById", id);
	}

	/**
	 * 根据微信号获取关键词总数
	 * @param weChatUserId
	 * @return
	 */
	public Long getCountByWeChatUserId(String weChatUserId) {
		return (Long) getSqlMapClientTemplate().queryForObject("keyWords.getCountByWeChatUserId",
				weChatUserId);
	}

	/**
	 * 根据素材id加载关键字（条件 like）
	 * @param sourceId
	 * @return
	 */
	public List<KeyWord> getKeyWordBySourceId(String sourceId) {
		return (List<KeyWord>) getSqlMapClientTemplate().queryForList(
				"keyWords.getKeyWordBySourceId", sourceId);

	}

	/**
	 * 根据服务号获取关键字集合
	 * @param weChatUserId
	 * @return
	 */
	public List<KeyWord> getKeyWordByWeChatUserId(String weChatUserId) {
		return (List<KeyWord>) getSqlMapClientTemplate().queryForList(
				"keyWords.getKeyWordByWeChatUserId", weChatUserId);
	}
}
