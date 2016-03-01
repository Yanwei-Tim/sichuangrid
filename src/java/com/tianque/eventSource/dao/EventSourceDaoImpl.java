package com.tianque.eventSource.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.eventSource.domain.EventSource;
import com.tianque.eventSource.domain.EventSourceVo;

@Repository("eventSourceDao")
public class EventSourceDaoImpl extends AbstractBaseDao implements
		EventSourceDao {

	public EventSource addEventSource(EventSource eventSource) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"eventSource.addEventSource", eventSource);
		return getEventSouceById(id);
	}

	@Override
	public EventSource getEventSouceById(Long id) {
		return (EventSource) getSqlMapClientTemplate().queryForObject(
				"eventSource.getEventSourceById", id);
	}

	public PageInfo<EventSource> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<EventSource> pageInfo = new PageInfo<EventSource>();
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setResult(list);
		return pageInfo;
	}

	public PageInfo<EventSource> emptyEventSourcePage(int pageSize) {// 仅用于search
		PageInfo<EventSource> pageInfo = new PageInfo<EventSource>();
		pageInfo.setCurrentPage(0);
		pageInfo.setTotalRowSize(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<EventSource>());
		return pageInfo;
	}

	@Override
	public PageInfo<EventSource> findInformation(EventSourceVo eventSourceVo,
			Integer pageNum, Integer pageSize) {

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"eventSource.countEventSource", eventSourceVo);
		int pageCount = 0;

		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		List<EventSource> eventSourceList = (List<EventSource>) getSqlMapClientTemplate()
				.queryForList("eventSource.findEventSource", eventSourceVo,
						(pageNum - 1) * pageSize, pageSize);

		return createPageInfo(pageNum, pageSize, countNum, eventSourceList);

	}

	@Override
	public List<EventSource> findInformation(EventSourceVo eventSourceVo) {

		List<EventSource> eventSourceList = (List<EventSource>) getSqlMapClientTemplate()
				.queryForList("eventSource.findEventSource", eventSourceVo);
		return eventSourceList;

	}

	public List<EventSource> findInformation(long state, String userName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", state);
		map.put("userName", userName);
		List<EventSource> eventSourceList = (List<EventSource>) getSqlMapClientTemplate()
				.queryForList("eventSource.findEventSourceByTypeAndCreateUser",
						map);
		return eventSourceList;

	}

	@Override
	public PageInfo<EventSource> searchInformation(EventSourceVo eventSourceVo,
			Integer pageNum, Integer pageSize) {
		if (eventSourceVo == null) {
			return emptyEventSourcePage(pageSize);
		}
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchEventSource.searchCountEventSource", eventSourceVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<EventSource> eventSourceList = (List<EventSource>) getSqlMapClientTemplate()
				.queryForList("searchEventSource.searchFindEventSource",
						eventSourceVo, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, eventSourceList);
	}

	@Override
	public EventSource updateInformationStateById(Long id, Long propertyDictId,
			String serialNumber) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("propertyDictId", propertyDictId);
		map.put("serialNumber", serialNumber);
		getSqlMapClientTemplate().update(
				"eventSource.updateInformationStateById", map);
		return getEventSouceById(id);

	}

	public EventSource updateInformationStateBySerialNumber(
			String serialNumber, Long propertyDictId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serialNumber", serialNumber);
		map.put("propertyDictId", propertyDictId);
		getSqlMapClientTemplate().update(
				"eventSource.updateInformationStateBySerialNumber", map);
		return getEventSourceBySerialNumber(serialNumber);
	}

	@Override
	public EventSource getEventSourceBySerialNumber(String serialNumber) {
		return (EventSource) getSqlMapClientTemplate().queryForObject(
				"eventSource.getEventSourceBySerialNumber", serialNumber);
	}

	@Override
	public void updateInformationStateByIds(List<Long> idList,
			long propertyDictId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idList", idList);
		map.put("propertyDictId", propertyDictId);
		getSqlMapClientTemplate().update(
				"eventSource.updateInformationStateByIds", map);

	}

}
