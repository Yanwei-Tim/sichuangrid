package com.tianque.eventSource.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.eventSource.dao.EventSourceDao;
import com.tianque.eventSource.domain.EventSource;
import com.tianque.eventSource.domain.EventSourceVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("eventSourceService")
@Transactional
public class EventSourceServiceImpl implements EventSourceService {

	@Autowired
	private EventSourceDao eventSourceDao;
	@Qualifier("eventSourceValidator")
	@Autowired
	private DomainValidator<EventSource> validator;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public EventSource addEventSource(EventSource eventSource) {
		eventSourceValidator(eventSource);
		return eventSourceDao.addEventSource(eventSource);

	}

	private void eventSourceValidator(EventSource eventSource) {
		ValidateResult dataValidator = validator.validate(eventSource);
		if (dataValidator.hasError()) {
			throw new BusinessValidationException(dataValidator.getErrorMessages());
		}
	}

	public PageInfo<EventSource> constructEmptyPageInfo() {
		PageInfo<EventSource> result = new PageInfo<EventSource>();
		result.setResult(new ArrayList<EventSource>());
		return result;
	}

	@Override
	public PageInfo<EventSource> findInformation(EventSourceVo eventSourceVo,
			Integer pageNum, Integer pageSize) {

		return eventSourceDao.findInformation(eventSourceVo, pageNum, pageSize);
	}

	@Override
	public List<EventSource> findInformation(EventSourceVo eventSourceVo) {
		return eventSourceDao.findInformation(eventSourceVo);
	}

	public List<EventSource> findInformation(long state, String userName) {
		return eventSourceDao.findInformation(state, userName);
	}

	@Override
	public PageInfo<EventSource> searchInformation(EventSourceVo eventSourceVo,
			Integer pageNum, Integer pageSize) {
		return eventSourceDao.searchInformation(eventSourceVo, pageNum,
				pageSize);
	}

	@Override
	public EventSource getEventSouceById(Long id) {
		if (id == null || id < 0L) {
			throw new BusinessValidationException("传入参数为空");
		}
		return eventSourceDao.getEventSouceById(id);
	}

	public void updateInformationStateById(List<Long> ids, Integer internalId) {
		long dictId = getPropertyDictIdByInternalId(internalId);
		eventSourceDao.updateInformationStateByIds(ids, dictId);
	}

	private Long getPropertyDictIdByInternalId(Integer internalId) {
		return propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.EVENTSOURCE_STATE, internalId).get(0)
				.getId();
	}

	public EventSource updateInformationStateBySerialNumber(
			String serialNumber, Integer internalId) {
		return eventSourceDao.updateInformationStateBySerialNumber(
				serialNumber, getPropertyDictIdByInternalId(internalId));
	}

	public EventSource updateInformationStateById(Long id, Integer internalId,
			String serialNumber) {
		return eventSourceDao.updateInformationStateById(id,
				getPropertyDictIdByInternalId(internalId), serialNumber);
	}

	@Override
	public EventSource getEventSourceBySerialNumber(String serialNumber) {
		return eventSourceDao.getEventSourceBySerialNumber(serialNumber);
	}

}
