package com.tianque.controller;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;

@SuppressWarnings("serial")
@Controller("mobileVersionController")
@Scope("prototype")
@Transactional
public class MobileVersionController extends BaseAction {
	@Autowired
	private GlobalSettingService globalSettingService;
	private Map<String, String> map;
	private int lastVersion;

	public String getLastVersionInfo() throws Exception {
		String lastVersionStr = globalSettingService
				.getGlobalValue(GlobalSetting.MOBILE_VERSION);
		if (lastVersionStr != null && !"".equals(lastVersionStr.trim()))
			lastVersion = Integer.valueOf(lastVersionStr);
		return SUCCESS;
	}

	public String downLoadLastVersion() throws Exception {
		String filePath = globalSettingService
				.getGlobalValue(GlobalSetting.MOBILE_APK_PATH);
		if (filePath == null || "".equals(filePath)) {
			this.errorMessage = "安装文件不存在";
			return ERROR;
		}
		File apk = new File(filePath);
		if (apk.exists()) {
			inputStream = new java.io.FileInputStream(apk);
			downloadFileName = "lastVersion.apk";
		} else {
			this.errorMessage = "安装文件不存在";
			return ERROR;
		}
		return SUCCESS;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public int getLastVersion() {
		return lastVersion;
	}

	public void setLastVersion(int lastVersion) {
		this.lastVersion = lastVersion;
	}

}
