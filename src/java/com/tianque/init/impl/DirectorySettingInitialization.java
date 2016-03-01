package com.tianque.init.impl;

import com.tianque.domain.property.SourceType;
import com.tianque.init.Initialization;
import com.tianque.resourcePool.domain.DirectorySetting;
import com.tianque.resourcePool.service.DirectorySettingService;

public class DirectorySettingInitialization implements Initialization {
	private DirectorySettingService directorySettingService;

	public DirectorySettingInitialization(DirectorySettingService directorySettingService) {
		this.directorySettingService = directorySettingService;
	}

	@Override
	public void init() throws Exception {
		addDirectorySetting(SourceType.LAW_REGULATION, SourceType.LAW, 1);
		addDirectorySetting(SourceType.RULE_REGULATION, SourceType.RULE, 2);
		addDirectorySetting(SourceType.POLICY_DOCUMENT, SourceType.POLICY, 3);
		addDirectorySetting(SourceType.EXPERIENCE_MATERIAL, SourceType.EXPERIENCE, 4);
		addDirectorySetting(SourceType.RESEARCH_REPORT, SourceType.RESEARCH, 5);
		addDirectorySetting(SourceType.BRIEFINGS, SourceType.BRIEFING, 6);
	}

	public DirectorySetting addDirectorySetting(String name, Integer resourcePoolType,
			Integer indexId) {

		DirectorySetting directorySetting = new DirectorySetting();
		directorySetting.setName(name);
		directorySetting.setResourcePoolType(1);
		directorySetting.setDirectoryType(DirectorySetting.publicDirectory);
		directorySetting.setIndexId(indexId);
		directorySetting.setLevel(2);
		directorySetting = directorySettingService.add(directorySetting);
		return directorySetting;

	}

}
