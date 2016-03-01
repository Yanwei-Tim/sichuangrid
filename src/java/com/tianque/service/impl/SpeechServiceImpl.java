package com.tianque.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SpeechDao;
import com.tianque.domain.Speech;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.SpeechService;

@Service("speechService")
public class SpeechServiceImpl extends LogableService implements SpeechService {
	@Autowired
	private SpeechDao speechDao;

	@Override
	public PageInfo<Speech> findSpeechs(Map<String, Object> map, Integer page,
			Integer rows) {
		return speechDao.findSpeechs(map, page, rows);
	}

	@Override
	public Speech addSpeech(Speech speech) {
		Speech localSpeech = findSpeechByTitleAndOrgIdAndSpeechType(
				speech.getTitle(), speech.getOrgId(), speech.getSpeechType());
		if (localSpeech != null) {
			throw new BusinessValidationException("主题重复，请修改");
		}
		return speechDao.addSpeech(speech);
	}

	/**
	 * 根据title和orgId以及speechType查找speech，用来判断是否有重复主题
	 * 
	 * @param title
	 * @param orgId
	 * @return
	 */
	private Speech findSpeechByTitleAndOrgIdAndSpeechType(String title,
			Long orgId, String speechType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("title", title);
		map.put("speechType", speechType);
		List<Speech> list = speechDao.findSpeechsByMap(map);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Speech updateSpeech(Speech speech) {
		Speech localSpeech = findSpeechByTitleAndOrgIdAndSpeechType(
				speech.getTitle(), speech.getOrgId(), speech.getSpeechType());
		// 因为主题不能重复，所以做个判断，如果根据主题和orgId查询到数据的id和修改的数据的id一致，说明用户没有修改主题
		if (localSpeech != null && !localSpeech.getId().equals(speech.getId())) {
			throw new BusinessValidationException("主题重复，请修改");
		}
		return speechDao.updateSpeech(speech);
	}

	@Override
	public Speech getSpeechById(Long speechId) {
		return speechDao.getSpeechById(speechId);
	}

	@Override
	public void deleteSpeechById(Long speechId) {
		speechDao.deleteSpeechById(speechId);
	}

}
