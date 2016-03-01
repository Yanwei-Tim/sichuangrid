package com.tianque.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.ServicePerson;

public interface ServicePersonService {
	public boolean deleteServicePersonById(Long id);

	public ServicePerson updateServicePerson(ServicePerson servicePerson);

	public ServicePerson addServicePerson(ServicePerson servicePerson);

	public PageInfo<ServicePerson> findServicePersonForPage(int pageNum, int pageSize,
			String sortField, String order, String orgInternalCode);

	public ServicePerson getSimpleServicePersonById(Long id);
}
