package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.service.AutocompleteService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.UserAutocompleteDubboService;

@Service("userAutocompleteSuperiorService")
public class UserAutocompleteSuperiorServiceImpl extends AbstractBaseService
		implements AutocompleteService {

	@Autowired
	private UserAutocompleteDubboService userAutocompleteDubboService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public List<User> findUserForAutocomplete(String name, User user) {
		Organization organization = organizationDubboService
				.getSimpleOrgById(user.getOrganization().getId());
		return userAutocompleteDubboService
				.findSuperiorVisitNameAndPinyinAndsubordinate(name,
						organization.getOrgLevel().getInternalId());
	}

}
