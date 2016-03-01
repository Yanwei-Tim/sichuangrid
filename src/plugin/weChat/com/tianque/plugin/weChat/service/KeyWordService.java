package com.tianque.plugin.weChat.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.user.KeyWord;

public interface KeyWordService {
	/**
	 * 关键字列表
	 * @param keyWord
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<KeyWord> findKeyWord(KeyWord keyWord, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	/**
	 * 添加关键词
	 * @param keyWord
	 * @return
	 */
	public Long addKeyWord(KeyWord keyWord);

	/**
	 * 验证关键词存在
	 * @param keyWord
	 * @return
	 */
	public KeyWord validateKeyWordByWeChatUserIdAndKeyName(KeyWord keyWord);

	/**
	 * 修改关键词
	 * @param keyWord
	 * @return
	 */
	public Integer updateKeyWord(KeyWord keyWord);
	/**
	 * 根据sourceId修改关键字表中的素材描述
	 * @param keyWord
	 * @return
	 */
	public Integer updateKeyWordBySourceId(KeyWord keyWord) ;

	/**
	 * 删除关键词
	 * @param id
	 * @return
	 */
	public Integer deleteKeyWord(String ids);

	/**
	 * 根据服务号删除关键词
	 * @param weChatUserId
	 * @return
	 */
	public Integer deleteKeyWordByWeChatUserId(String  weChatUserId);
	/**
	 * 加载关键词对象
	 * @param id
	 * @return
	 */
	public KeyWord getKeyWordById(Long id);

	/**
	 * 根据微信号获取关键词总数
	 * @param weChatUserId
	 * @return
	 */
	public Long getCountByWeChatUserId(String weChatUserId);

	/**
	 * 根据素材id加载关键字（条件 like）
	 * @param sourceId
	 * @return
	 */
	public List<KeyWord> getKeyWordBySourceId(String sourceId);

	/**
	 * 关键字自动回复
	 * @param keyName
	 * @param weChatUserId
	 * @param fromUser
	 * @return
	 */
	public String receiveMessageByKeyWord(String keyName, String weChatUserId, String fromUser);
	
	/**
	 * 根据服务号获取关键字集合
	 * @param weChatUserId
	 * @return
	 */
	public List<KeyWord> getKeyWordByWeChatUserId(String weChatUserId);
}
