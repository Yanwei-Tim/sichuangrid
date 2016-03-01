package com.tianque.plugin.serviceTeam.serviceTeamManage.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.base.BaseDaoTestCase;
import com.tianque.base.OrgDaoHelper;
import com.tianque.domain.Organization;
import com.tianque.plugin.serviceTeam.serviceTeamManage.domain.ServiceTeam;
import com.tianque.plugin.serviceTeam.serviceTeamManage.vo.ServiceTeamVo;
import com.tianque.sysadmin.dao.OrganizationDao;

public class ServiceTeamDaoTest extends BaseDaoTestCase {
	private Organization org;
	@Autowired
	private ServiceTeamDao serviceTeamDao;
	@Autowired
	private OrganizationDao organizationDao;

	@Before
	public void Before() {
		org = OrgDaoHelper.addRootOrg(organizationDao);
	}

	@Test
	public void test添加团队() {
		ServiceTeam a = getServiceTeam();
		ServiceTeamVo vo = serviceTeamDao.addServiceTeam(a);
		// comperToReturnVo(a, vo);

	}

	private void comperToReturnVo(ServiceTeamVo vo) {
		assertNotNull(vo.getUpdateDate());
		assertNotNull(vo.getId());
		assertEquals(vo.getTeamName(), "haha");
		assertEquals(vo.getRemark(), "nicaibucai");

	}

	private ServiceTeam getServiceTeam() {

		ServiceTeam a = new ServiceTeam();
		//
		a.setTeamName("dddddd");
		a.setRemark("nicaibucai");
		a.setOrg(org);
		a.setOrgCode(org.getOrgInternalCode());
		// return BaseDomainTestHelper.creatDomain(a);
		return a;
	}
}
