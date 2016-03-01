package com.tianque.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SpeechDao;
import com.tianque.domain.Speech;
import com.tianque.exception.base.BusinessValidationException;

@Repository("speechDao")
public class SpeechDaoImpl extends AbstractBaseDao implements SpeechDao {

	@Override
	public PageInfo<Speech> findSpeechs(Map<String, Object> map, Integer page,
			Integer rows) {

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"speech.countSpeechByMap", map);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;

		List<Speech> list = getSqlMapClientTemplate().queryForList(
				"speech.findSpeechs", map, (page - 1) * rows, rows);

		return createPageInfo(page, rows, countNum, list);

	}

	private PageInfo<Speech> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<Speech> pageInfo = new PageInfo<Speech>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public Speech addSpeech(Speech speech) {
		validateSpeech(speech);
		Long id = (Long) getSqlMapClientTemplate().insert("speech.addSpeech",
				speech);
		return getSpeechById(id);
	}

	private void validateSpeech(Speech speech) {
		if (speech.getTitle() == null || speech.getTitle().trim().equals("")) {
			throw new BusinessValidationException("标题不能为空");
		}
		if (speech.getContent() == null
				|| speech.getContent().trim().equals("")) {
			throw new BusinessValidationException("内容不能为空");
		}

	}

	@Override
	public Speech updateSpeech(Speech speech) {
		validateSpeech(speech);
		getSqlMapClientTemplate().update("speech.updateSpeech", speech);
		return getSpeechById(speech.getId());
	}

	@Override
	public Speech getSpeechById(Long speechId) {
		return (Speech) getSqlMapClientTemplate().queryForObject(
				"speech.getSpeechById", speechId);
	}

	@Override
	public void deleteSpeechById(Long speechId) {
		getSqlMapClientTemplate().delete("speech.deleteSpeechById", speechId);
	}

	@Override
	public List<Speech> findSpeechsByMap(Map<String, Object> map) {
		return getSqlMapClientTemplate()
				.queryForList("speech.findSpeechs", map);
	}

}
