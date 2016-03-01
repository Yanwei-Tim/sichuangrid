package com.tianque.baseInfo.idleYouth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.idleYouth.dao.IdleYouthsHasPropertyDictsDAO;
import com.tianque.baseInfo.idleYouth.domain.IdleYouthsHasPropertyDicts;

@Service("idleYouthsHasPropertyDictsService")
@Transactional
public class IdleYouthsHasPropertyDictsServiceImpl implements
		IdleYouthsHasPropertyDictsService {

	@Autowired
	private IdleYouthsHasPropertyDictsDAO idleYouthsHasPropertyDictsDao;

	@Override
	public void deleteIdleYouthsHasPropertyDictsByIdleYouthId(Long id) {
		if(id != null){
			idleYouthsHasPropertyDictsDao
			.deleteIdleYouthsHasPropertyDictsByIdleYouthId(id);
		}
	}

	@Override
	public void updateIdleYouthsHasPropertyDictsByIdleYouthId(Long idleYouthId,
			Long newIdleYouthId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idleYouthId", idleYouthId);
		map.put("newIdleYouthId", newIdleYouthId);
		idleYouthsHasPropertyDictsDao
				.updateIdleYouthsHasPropertyDictsByIdleYouthId(map);
	}

	@Override
	public void addIdleYouthsHasPropertyDicts(
			IdleYouthsHasPropertyDicts idleYouthsHasPropertyDicts) {
		if (idleYouthsHasPropertyDicts != null) {
			idleYouthsHasPropertyDictsDao
					.addIdleYouthsHasPropertyDicts(idleYouthsHasPropertyDicts);
		}
	}

	@Override
	public List<IdleYouthsHasPropertyDicts> queryIdleYouthsHasPropertyDictsByIdleYouthIdForList(
			Long idleYouthId) {
		return idleYouthsHasPropertyDictsDao
				.queryIdleYouthsHasPropertyDictsByIdleYouthIdForList(idleYouthId);
	}

}
