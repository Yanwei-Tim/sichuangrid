package com.tianque.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.ServicePersonDao;
import com.tianque.domain.ServicePerson;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.ServicePersonService;

@Service("servicePersonService")
public class ServicePersonServiceImpl implements ServicePersonService {
	@Autowired
	private ServicePersonDao servicePersonDao;

	@Transactional
	@Override
	public ServicePerson addServicePerson(ServicePerson servicePerson) {
		if (null == servicePerson.getName()
				|| "".equals(servicePerson.getName())) {
			throw new BusinessValidationException("服务人员姓名为空");
		}
		autoFillChinesePinyin(servicePerson);
		return servicePersonDao.addServicePerson(servicePerson);
	}

	@Transactional
	@Override
	public boolean deleteServicePersonById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("服务人员Id为空");
		}
		return servicePersonDao.deleteServicePersonById(id);
	}

	@Override
	public PageInfo<ServicePerson> findServicePersonForPage(int pageNum,
			int pageSize, String sortField, String order, String orgInternalCode) {
		List<ServicePerson> list = servicePersonDao.findServicePersons(pageNum,
				pageSize, sortField, order, orgInternalCode);
		int total = servicePersonDao.getServicePersonTotalSize(orgInternalCode);

		PageInfo<ServicePerson> pageInfo = new PageInfo<ServicePerson>();
		pageInfo.setResult(list);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setTotalRowSize(total);
		return pageInfo;
	}

	@Override
	public ServicePerson getSimpleServicePersonById(Long id) {
		return servicePersonDao.getSimpleServicePersonById(id);
	}

	@Transactional
	@Override
	public ServicePerson updateServicePerson(ServicePerson servicePerson) {
		if (null == servicePerson.getName()
				|| "".equals(servicePerson.getName())) {
			throw new BusinessValidationException("服务人员姓名为空");
		}
		autoFillChinesePinyin(servicePerson);
		boolean flag = servicePersonDao.updateServicePerson(servicePerson);
		if (flag) {
			return servicePersonDao.getSimpleServicePersonById(servicePerson
					.getId());
		}
		return null;
	}

	private void autoFillChinesePinyin(ServicePerson servicePerson) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(servicePerson.getName());
		servicePerson.setSimplePinyin(pinyin.get("simplePinyin"));
		servicePerson.setFullPinyin(pinyin.get("fullPinyin"));
	}
}
