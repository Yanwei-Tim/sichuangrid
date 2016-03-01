package com.tianque.dao;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Speech;

public interface SpeechDao {
	/**
	 * 根据map条件查询speech
	 * 
	 * @param map
	 *        map中的key orgid 组织机构id
	 *        speechType 类型
	 *        sortField 排序字段
	 *        order 排序方式
	 * @return
	 */
	public PageInfo<Speech> findSpeechs(Map<String, Object> map, Integer page, Integer rows);

	public List<Speech> findSpeechsByMap(Map<String, Object> map);

	/**
	 * 添加一条Speech
	 * 
	 * @param speech
	 * @return
	 */
	public Speech addSpeech(Speech speech);

	/**
	 * 修改Speech
	 * 
	 * @param speech
	 * @return
	 */
	public Speech updateSpeech(Speech speech);

	/**
	 * 根据id查询speech
	 * 
	 * @param speechId
	 *        speech的id
	 * @return
	 */
	public Speech getSpeechById(Long speechId);

	/**
	 * 根据id删除speech
	 * 
	 * @param speechId
	 *        speech的id
	 */
	public void deleteSpeechById(Long speechId);

}
