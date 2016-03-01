package com.tianque.plugin.dataManage.location.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unitils.database.annotations.Transactional;

import com.tianque.plugin.dataManage.location.base.dao.LocationTempDao;
import com.tianque.plugin.dataManage.location.base.domain.LocationTempBaseDomain;
import com.tianque.service.impl.LogableService;

@Service("dataManageLocationService")
@Transactional
public class LocationTempServiceImpl<T extends LocationTempBaseDomain> extends LogableService
		implements LocationTempService<T> {
	@Autowired
	private LocationTempDao<T> locationTempDao;
}
