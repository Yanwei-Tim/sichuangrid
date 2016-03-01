package com.tianque.plugin.dataManage.location.base.dao;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.base.BaseDomain;

@Repository("dataManageLocationDao")
public class LocationTempDaoImpl<T extends BaseDomain> extends AbstractBaseDao implements
		LocationTempDao<T> {

}
