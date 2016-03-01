package com.tianque.eventSource.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.eventSource.domain.EventSource;
import com.tianque.eventSource.domain.EventSourceVo;

public interface EventSourceDao {

	public EventSource addEventSource(EventSource eventSource);

	public EventSource getEventSouceById(Long id);

	public PageInfo<EventSource> findInformation(EventSourceVo eventSourceVo,
			Integer pageNum, Integer pageSize);

	public List<EventSource> findInformation(EventSourceVo eventSourceVo);

	public PageInfo<EventSource> searchInformation(EventSourceVo eventSourceVo,
			Integer pageNum, Integer pageSize);

	public List<EventSource> findInformation(long state, String userName);

	public EventSource updateInformationStateById(Long id, Long propertyDictId,
			String serialNumber);

	public EventSource updateInformationStateBySerialNumber(
			String serialNumber, Long propertyDictId);

	public EventSource getEventSourceBySerialNumber(String serialNumber);

	/***
	 * 批量修改
	 * 
	 * @param ids
	 * @param propertyDictId
	 */
	public void updateInformationStateByIds(List<Long> ids, long propertyDictId);
}
