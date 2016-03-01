package com.tianque.init;

import java.util.ArrayList;
import java.util.List;

import com.tianque.sysadmin.service.PermissionService;

public class PermissionPersistenceInitialization implements Initialization {

	private List<PermissionInit> inits = new ArrayList<PermissionInit>();

	private PersistentStrategy persistentStrategy;

	public PermissionPersistenceInitialization(
			PermissionService permissionService) {
		persistentStrategy = new PersistentStrategy(permissionService);
	}

	public void addPermissionInitialization(PermissionInit initialization) {
		if (initialization == null)
			return;
		initialization.setStrategy(persistentStrategy);
		if (!inits.contains(initialization)) {
			inits.add(initialization);
		}
		// addInitialization(initialization);
	}

	public void init() throws Exception {
		for (PermissionInit init : inits) {
			init.init();
		}
	}

}
