package com.tianque.dao;

import java.util.List;

import com.tianque.domain.ServicePerson;

public interface ServicePersonDao {
	public boolean deleteServicePersonById(Long id);

	public boolean updateServicePerson(ServicePerson servicePerson);

	public ServicePerson addServicePerson(ServicePerson servicePerson);

	public ServicePerson getSimpleServicePersonById(Long id);

	public List<ServicePerson> findServicePersons(int pageNum, int pageSize, String sortField,
			String order, String orgInternalCode);

	public int getServicePersonTotalSize(String orgInternalCode);
}
