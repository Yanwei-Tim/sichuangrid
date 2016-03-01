package com.tianque.sysadmin.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByName;

import com.tianque.base.excel.BaseDaoTest;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

public class OrganizationDaoTest extends
		BaseDaoTest<Organization, OrganizationDubboService> {

	@SpringBeanByName
	private OrganizationDubboService organizationDao;

	@Override
	public OrganizationDubboService getDao() {
		return organizationDao;
	}

	@Test
	@Ignore
	@Override
	public void updateTest() {

	}

	@Test
	@Ignore
	@Override
	public void deleteById() throws Exception {
		// TODO Auto-generated method stub
		super.deleteById();
	}
}
