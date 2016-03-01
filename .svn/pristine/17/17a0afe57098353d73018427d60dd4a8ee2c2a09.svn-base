package com.tianque.plugin.orgchange.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tianque.core.cache.service.CacheService;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;

/**
 * 进度信息
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年10月16日
 */
public class ProgressInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private long changeId;
	private int progress;// 进度
	private int complet;// 已完成
	private int total;// 总数
	private List<String> completeModules;// 已经完成模块
	private String currentModule;// 当前模块
	private String completeModuleString;// 执行完成模块所串联成的字符串数据，用于前端显示
	private transient CacheService cacheService;
	private int isException;// 是否执行出现异常
	private long delayTime;
	private long currentModuleDelayTime;

	public ProgressInfo() {
	}

	public ProgressInfo(long changeId, int total, CacheService cacheService) {
		super();
		this.changeId = changeId;
		this.total = total;
		this.cacheService = cacheService;
		saveCache();
	}

	public int getIsException() {
		return isException;
	}

	public void setIsException(int isException) {
		this.isException = isException;
		saveCache();
	}

	public String getCompleteModuleString() {
		return completeModuleString;
	}

	public void setCompleteModuleString(String completeModuleString) {
		this.completeModuleString = completeModuleString;
	}

	private void saveCache() {
		cacheService.set(OrgChangeUtils.ORGCHANGE_PROGRESS + changeId, 3600,
				this);
	}

	public long getChangeId() {
		return changeId;
	}

	public void setChangeId(long changeId) {
		this.changeId = changeId;
		saveCache();
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
		saveCache();
	}

	public List<String> getCompleteModules() {
		return completeModules;
	}

	public void setCompleteModules(List<String> completeModules) {
		this.completeModules = completeModules;
		saveCache();
	}

	public void addCompleteModules(String completeModule) {
		if (completeModules == null) {
			completeModules = new ArrayList<String>();
		}
		completeModules.add(completeModule);
		saveCache();
	}

	public String getCurrentModule() {
		return currentModule;
	}

	public void setCurrentModule(String currentModule) {
		this.currentModule = currentModule;
		saveCache();
	}

	public void addCurrentModule(String currentModule) {
		addCompleteModules(this.currentModule + "耗时" + currentModuleDelayTime
				+ "毫秒");
		this.currentModule = currentModule;
		if (total > 0) {
			progress = ++complet * 100 / total;
		}
		saveCache();
	}

	public int getComplet() {
		return complet;
	}

	public void setComplet(int complet) {
		if (total > 0) {
			progress = complet * 100 / total;
		}
		this.complet = complet;
		saveCache();
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		saveCache();
	}

	public long getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(long delayTime) {
		this.delayTime = delayTime;
		saveCache();
	}

	public long getCurrentModuleDelayTime() {
		return currentModuleDelayTime;
	}

	public void setCurrentModuleDelayTime(long currentModuleDelayTime) {
		this.delayTime += currentModuleDelayTime;
		this.currentModuleDelayTime = currentModuleDelayTime;
		saveCache();
	}
}
