package com.tianque.plugin.dataManage.dustbin.dustbinTemp.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.domain.Dustbin;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.dustbin.dustbinTemp.domain.DustbinTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.service.DustbinService;

/**
 * 数据管理 - 部件信息控制类。
 */
@Namespace("/plugin/dataManage/dustbinTempManage")
@Transactional
@Controller("dustbinTempController")
@Scope("request")
public class DustbinTempController extends
		AbstractDataManageController<DustbinTemp, Dustbin> {
	@Autowired
	private DustbinService dustbinService;

	@Autowired
	@Qualifier("dustbinTempService")
	private AbstractDataManageService dustbinTempService;

	@Resource(name = "dustbinTempService")
	public void setDataManageService(
			AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(dustbinTempService);
	}

	@Override
	public String getBigType() {
		return DataManageBaseInfoTypes.DUSTBIN;
	}

	@Override
	public Dustbin getTargetById(Long id) {
		// return dustbinService.getDustbinById(id);
		return null;
	}

	public DustbinTemp getTemp() {
		return temp;
	}

	public void setTemp(DustbinTemp temp) {
		this.temp = temp;
	}

	public DustbinTemp getPopulation() {
		return population;
	}

	public void setPopulation(DustbinTemp population) {
		this.population = population;
	}

	@Override
	public List getCompareList(DustbinTemp temp, Dustbin target) {
		// TODO Auto-generated method stub
		return null;
	}

}
