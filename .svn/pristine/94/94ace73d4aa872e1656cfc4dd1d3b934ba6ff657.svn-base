package com.tianque.plugin.dataManage.location.internetBarTemp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.internetBar.domain.InternetBar;
import com.tianque.baseInfo.internetBar.service.InternetBarService;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.location.internetBarTemp.domain.InternetBarTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;

/**
 * 数据管理 - 网吧控制类。
 */
@Namespace("/plugin/dataManage/internetBarTempManage")
@Transactional
@Controller("internetBarTempController")
@Scope("request")
public class InternetBarTempController extends
		AbstractDataManageController<InternetBarTemp, InternetBar> {
	@Autowired
	private InternetBarService internetBarService;
	@Autowired
	@Qualifier("internetBarTempService")
	private AbstractDataManageService locationDataManageService;

	@Resource(name = "internetBarTempService")
	public void setDataManageService(AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(locationDataManageService);
	}

	// public String getAjaxUrl() {
	// return "hasDuplicateInternetBarTempLocation";
	// }

	public String getBigType() {
		return DataManageBaseInfoTypes.LOCATION;
	}

	public InternetBarTemp getTemp() {
		return temp;
	}

	public void setTemp(InternetBarTemp temp) {
		this.temp = temp;
	}

	public InternetBarTemp getLocation() {
		return population;
	}

	// public void setPopulation(InternetBarTemp population) {
	// this.population = population;
	// }
	public InternetBarTemp getPopulation() {
		return population;
	}

	public void setLocation(InternetBarTemp Location) {
		this.population = Location;
	}

	@Override
	public InternetBar getTargetById(Long id) {
		return internetBarService.getInternetBarById(id);
	}

	@Override
	public List getCompareList(InternetBarTemp temp, InternetBar target) {
		List compareList = new ArrayList();
		Map map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "场所名称");
		map.put("tempValue", "" + temp.getName());
		map.put("realValue", "" + target.getPlaceName());
		map.put("submitName", "name");
		map.put("argType", "str");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "关注程度");
		map.put("tempValue", temp.getAttentionExtent() == null ? "-1" : ""
				+ temp.getAttentionExtent().getId());
		map.put("realValue", target.getAttentionExtent() == null ? "-1" : ""
				+ target.getAttentionExtent().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT");
		map.put("argType", "PropertyDict");
		map.put("submitName", "attentionExtent.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "负责人");
		map.put("tempValue", "" + temp.getManager());
		map.put("realValue", "" + target.getManager());
		map.put("argType", "str");
		map.put("submitName", "manager");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "联系电话");
		map.put("tempValue", "" + temp.getMobile());
		map.put("realValue", "" + target.getMobile());
		map.put("argType", "str");
		map.put("submitName", "mobile");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "网吧编号");
		map.put("tempValue", "" + temp.getBarCode());
		map.put("realValue", "" + target.getBarCode());
		map.put("argType", "str");
		map.put("submitName", "barCode");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "场所地址");
		map.put("tempValue", "" + temp.getAddress());
		map.put("realValue", "" + target.getPlaceAddress());
		map.put("argType", "str");
		map.put("submitName", "address");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "宽带接入服务商");
		map.put("tempValue", "" + temp.getNetAccessProviders());
		map.put("realValue", "" + target.getNetAccessProviders());
		map.put("argType", "str");
		map.put("submitName", "netAccessProviders");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "现使用IP");
		map.put("tempValue", "" + temp.getUseIP());
		map.put("realValue", "" + target.getUseIP());
		map.put("argType", "str");
		map.put("submitName", "useIP");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "接入方法");
		map.put("tempValue", "" + temp.getInternetAccessType());
		map.put("realValue", "" + target.getInternetAccessType());
		map.put("argType", "str");
		map.put("submitName", "internetAccessType");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "网络文化经营许可证号");
		map.put("tempValue", "" + temp.getNetCultureLicenceNo());
		map.put("realValue", "" + target.getNetCultureLicenceNo());
		map.put("argType", "str");
		map.put("submitName", "netCultureLicenceNo");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "电脑台数");
		map.put("tempValue", "" + temp.getTotalComputerNum());
		map.put("realValue", "" + target.getTotalComputerNum());
		map.put("argType", "str");
		map.put("submitName", "totalComputerNum");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "所在地派出所名称");
		map.put("tempValue", "" + temp.getStationPolice());
		map.put("realValue", "" + target.getStationPolice());
		map.put("argType", "str");
		map.put("submitName", "stationPolice");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "营业面积");
		map.put("tempValue", "" + temp.getOperationArea());
		map.put("realValue", "" + target.getOperationArea());
		map.put("argType", "str");
		map.put("submitName", "operationArea");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "网络安全审核意见书号");
		map.put("tempValue", "" + temp.getNetSecurityAuditNo());
		map.put("realValue", "" + target.getNetSecurityAuditNo());
		map.put("argType", "str");
		map.put("submitName", "netSecurityAuditNo");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "临时上网卡数");
		map.put("tempValue", "" + temp.getTempNetCardNum());
		map.put("realValue", "" + target.getTempNetCardNum());
		map.put("argType", "str");
		map.put("submitName", "tempNetCardNum");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "消防审核意见书号");
		map.put("tempValue", "" + temp.getFireAuditDocumentNo());
		map.put("realValue", "" + target.getFireAuditDocumentNo());
		map.put("argType", "str");
		map.put("submitName", "fireAuditDocumentNo");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "备注");
		map.put("tempValue", "" + temp.getRemark());
		map.put("realValue", "" + target.getRemark());
		map.put("argType", "str");
		map.put("submitName", "remark");
		compareList.add(map);
		return compareList;
	}
}
