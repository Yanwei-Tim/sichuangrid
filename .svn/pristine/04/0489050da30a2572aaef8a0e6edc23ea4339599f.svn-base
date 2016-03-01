package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.dao.ExamineScroseStanalDao;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.service.ExamineScroseStanalService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Service("examineScroseStanalService")
public class ExamineScroseStanalServiceImpl implements ExamineScroseStanalService {

	@Autowired
	private ExamineScroseStanalDao examineScroseStanalDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public int countAll(Long orgId, String year) {
		OrganizationVo organizationVo = new OrganizationVo();
		organizationVo.setParentOrgId(orgId);
		List<Long> orgIdList = organizationDubboService.findOrgIdsBySearchVo(organizationVo);
		return examineScroseStanalDao.countAll(orgIdList, year);
	}

	@Override
	public int examineScroseStanal(Long orgId, String year, Integer beginScrose, Integer endScrose) {
		OrganizationVo organizationVo = new OrganizationVo();
		organizationVo.setParentOrgId(orgId);
		List<Long> orgIdList = organizationDubboService.findOrgIdsBySearchVo(organizationVo);
		return examineScroseStanalDao.examineScroseStanal(orgIdList, year, beginScrose, endScrose);
	}

}
