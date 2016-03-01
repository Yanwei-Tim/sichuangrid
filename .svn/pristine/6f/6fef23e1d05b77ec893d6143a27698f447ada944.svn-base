package com.tianque.service;

import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Speech;

public interface SpeechService {
	/**
	 * 根据map条件查询speech
	 * 
	 * @param map
	 *        map中的key orgId 组织机构id
	 *        speechType 类型
	 *        sortField 排序字段
	 *        order 排序方式
	 * @param size
	 *        查询的数据的大小 null表示查询全部
	 * @return
	 */
	public PageInfo<Speech> findSpeechs(Map<String, Object> map, Integer page, Integer rows);

	/**
	 * 添加一条Speech,要求本级标题不能相同
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
