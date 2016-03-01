package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.domain.User;
import com.tianque.service.AutocompleteService;
import com.tianque.userAuth.api.UserAutocompleteDubboService;

@Service("userAutocompleteAllService")
public class UserAutocompleteAllServiceImpl extends AbstractBaseService
		implements AutocompleteService {
	@Autowired
	private UserAutocompleteDubboService userAutocompleteDubboService;

	@Override
	public List findUserForAutocomplete(String name, User user) {
		return userAutocompleteDubboService
				.findSuperiorVisitNameAndPinyinAndAll(name);
	}

}
