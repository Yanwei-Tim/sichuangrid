package com.tianque.eventSource.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.eventSource.domain.EventSource;
import com.tianque.eventSource.domain.EventSourceVo;

public interface EventSourceService {

	public EventSource addEventSource(EventSource eventSource);

	public PageInfo<EventSource> findInformation(EventSourceVo eventSourceVo, Integer pageNum,
			Integer pageSize);

	public List<EventSource> findInformation(EventSourceVo eventSourceVo);

	public List<EventSource> findInformation(long state, String userName);

	public PageInfo<EventSource> searchInformation(EventSourceVo eventSourceVo, Integer pageNum,
			Integer pageSize);

	public EventSource getEventSouceById(Long id);

	public EventSource updateInformationStateBySerialNumber(String serialNumber, Integer internalId);

	public void updateInformationStateById(List<Long> ids, Integer internalId);

	public EventSource updateInformationStateById(Long id, Integer internalId, String serialNumber);

	public EventSource getEventSourceBySerialNumber(String serialNumber);
}
