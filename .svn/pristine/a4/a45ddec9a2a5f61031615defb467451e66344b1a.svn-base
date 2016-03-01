package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.ServicePersonDao;
import com.tianque.domain.ServicePerson;
import com.tianque.exception.base.BusinessValidationException;

@Repository("servicePersonDao")
public class ServicePersonDaoImpl extends AbstractBaseDao implements
		ServicePersonDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<ServicePerson> findServicePersons(int pageNum, int pageSize,
			String sortField, String order, String orgInternalCode) {
		Map map = new HashMap();
		if (isStrotFieldAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		map.put("orgInternalCode", orgInternalCode);
		List<ServicePerson> servicePersonList = getSqlMapClientTemplate()
				.queryForList("servicePerson.findServicePersonForPage", map,
						(pageNum - 1) * pageSize, pageSize);
		return servicePersonList;
	}

	private void checkIsNull(ServicePerson servicePerson) {
		if (null == servicePerson.getName()) {
			throw new BusinessValidationException("服务人员名称为空");
		}
		if (null == servicePerson.getPhone()) {
			throw new BusinessValidationException("服务人员电话为空");
		}
		if (null == servicePerson.getOrganization()) {
			throw new BusinessValidationException("服务人员所属网格为空");
		}
		if (null == servicePerson.getOrgInternalCode()) {
			throw new BusinessValidationException("服务人员所属网格编码为空");
		}
	}

	@Override
	public ServicePerson addServicePerson(ServicePerson servicePerson) {
		checkIsNull(servicePerson);
		Long id = (Long) getSqlMapClientTemplate().insert(
				"servicePerson.addServicePerson", servicePerson);
		return getSimpleServicePersonById(id);
	}

	@Override
	public boolean deleteServicePersonById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("所要删除的服务人员编号为空");
		}
		return getSqlMapClientTemplate().delete(
				"servicePerson.deleteServicePersonById", id) == 1;
	}

	@Override
	public boolean updateServicePerson(ServicePerson servicePerson) {
		checkIsNull(servicePerson);
		int flag = getSqlMapClientTemplate().update(
				"servicePerson.updateServicePerson", servicePerson);
		return flag == 1;
	}

	@Override
	public int getServicePersonTotalSize(String orgInternalCode) {
		if (null == orgInternalCode) {
			throw new BusinessValidationException("orgInternalCode为空");
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"servicePerson.getTotalRowSize", orgInternalCode);
	}

	@Override
	public ServicePerson getSimpleServicePersonById(Long id) {
		if (null == id) {
			throw new BusinessValidationException();
		}
		return (ServicePerson) getSqlMapClientTemplate().queryForObject(
				"servicePerson.getServicePersonById", id);
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}
}
