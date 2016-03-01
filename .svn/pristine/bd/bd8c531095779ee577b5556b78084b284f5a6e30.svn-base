package com.tianque.plugin.dataManage.location.builddatasTemp.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.buildDatas.domain.Builddatas;
import com.tianque.baseInfo.buildDatas.service.BuilddatasService;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.location.builddatasTemp.domain.BuilddatasTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;

/**
 * 数据管理 - 楼宇信息
 */
@Namespace("/plugin/dataManage/builddatasTempManage")
@Transactional
@Controller("builddatasTempController")
@Scope("request")
public class BuilddatasTempController extends
		AbstractDataManageController<BuilddatasTemp, Builddatas> {
	@Autowired
	private BuilddatasService builddatasService;

	@Autowired
	@Qualifier("builddatasTempService")
	private AbstractDataManageService builddatasTempService;

	@Resource(name = "builddatasTempService")
	public void setDataManageService(
			AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(builddatasTempService);
	}

	@Override
	public String getBigType() {
		return DataManageBaseInfoTypes.BUILDDATAS;
	}

	@Override
	public Builddatas getTargetById(Long id) {
		// return dustbinService.getDustbinById(id);
		return null;
	}

	public BuilddatasTemp getTemp() {
		return temp;
	}

	public void setTemp(BuilddatasTemp temp) {
		this.temp = temp;
	}

	public BuilddatasTemp getPopulation() {
		return population;
	}

	public void setPopulation(BuilddatasTemp population) {
		this.population = population;
	}

	@Override
	public List getCompareList(BuilddatasTemp temp, Builddatas target) {
		// TODO Auto-generated method stub
		return null;
	}

}
