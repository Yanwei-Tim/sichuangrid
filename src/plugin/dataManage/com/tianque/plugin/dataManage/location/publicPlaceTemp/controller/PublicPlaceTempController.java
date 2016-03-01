package com.tianque.plugin.dataManage.location.publicPlaceTemp.controller;

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

import com.tianque.baseInfo.publicPlace.domain.PublicPlace;
import com.tianque.baseInfo.publicPlace.service.PublicPlaceService;
import com.tianque.core.util.DateUtil;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.location.publicPlaceTemp.domain.PublicPlaceTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;

/**
 * 数据管理 - 公共场所控制类。
 */
@Namespace("/plugin/dataManage/publicPlaceTempManage")
@Transactional
@Controller("publicPlaceTempController")
@Scope("request")
public class PublicPlaceTempController extends
		AbstractDataManageController<PublicPlaceTemp, PublicPlace> {
	@Autowired
	private PublicPlaceService publicPlaceServic;

	@Autowired
	@Qualifier("publicPlaceTempService")
	private AbstractDataManageService locationDataManageService;

	@Resource(name = "publicPlaceTempService")
	public void setDataManageService(AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(locationDataManageService);
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.LOCATION;
	}

	public PublicPlaceTemp getTemp() {
		return temp;
	}

	public void setTemp(PublicPlaceTemp temp) {
		this.temp = temp;
	}

	public PublicPlaceTemp getPopulation() {
		return population;
	}

	public void setPopulation(PublicPlaceTemp population) {
		this.population = population;
	}

	public PublicPlaceTemp getLocation() {
		return population;
	}

	public void setLocation(PublicPlaceTemp location) {
		this.population = location;
	}

	@Override
	public List getCompareList(PublicPlaceTemp temp, PublicPlace target) {
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
		map.put("notNull", "true");
		map.put("label", "场所地址");
		map.put("tempValue", "" + temp.getAddress());
		map.put("realValue", "" + target.getPlaceAddress());
		map.put("argType", "str");
		map.put("submitName", "address");
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
		map.put("label", "备案登记号码");
		map.put("tempValue", "" + temp.getRegistrationNumber());
		map.put("realValue", "" + target.getRegistrationNumber());
		map.put("argType", "str");
		map.put("submitName", "registrationNumber");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "开业时间");
		map.put("tempValue", "" + DateUtil.formateYMD(temp.getOpeningDate()));
		map.put("realValue", "" + DateUtil.formateYMD(target.getOpeningDate()));
		map.put("argType", "strDate");
		map.put("submitName", "openingDate");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "领取执照时间");
		map.put("tempValue", "" + DateUtil.formateYMD(temp.getLicenseDate()));
		map.put("realValue", "" + DateUtil.formateYMD(target.getLicenseDate()));
		map.put("argType", "strDate");
		map.put("submitName", "licenseDate");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "场所等级");
		map.put("tempValue", "" + temp.getPlaceLevel());
		map.put("realValue", "" + target.getPlaceLevel());
		map.put("argType", "str");
		map.put("submitName", "placeLevel");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "公共场所类别");
		map.put("tempValue", "" + temp.getCategory());
		map.put("realValue", "" + target.getCategory());
		map.put("argType", "str");
		map.put("submitName", "category");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "总面积");
		map.put("tempValue", "" + temp.getTotalArea());
		map.put("realValue", "" + target.getTotalArea());
		map.put("argType", "str");
		map.put("submitName", "totalArea");
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
		map.put("label", "包间数");
		map.put("tempValue", "" + temp.getPrivateRoomCount());
		map.put("realValue", "" + target.getPrivateRoomCount());
		map.put("argType", "str");
		map.put("submitName", "privateRoomCount");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "核定人数");
		map.put("tempValue", "" + temp.getVouchedPeopleCount());
		map.put("realValue", "" + target.getVouchedPeopleCount());
		map.put("argType", "str");
		map.put("submitName", "vouchedPeopleCount");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "通道口");
		map.put("tempValue", "" + temp.getPassageway());
		map.put("realValue", "" + target.getPassageway());
		map.put("argType", "str");
		map.put("submitName", "passageway");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "小件寄存处");
		map.put("tempValue", temp.getCloakroom() == null ? "-1" : "" + temp.getCloakroom().getId());
		map.put("realValue", target.getCloakroom() == null ? "-1" : ""
				+ target.getCloakroom().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@PUBLICPLACE_CLOAKROOM");
		map.put("argType", "PropertyDict");
		map.put("submitName", "cloakroom.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "内部结构");
		map.put("tempValue", "" + temp.getInnerStructure());
		map.put("realValue", "" + target.getInnerStructure());
		map.put("argType", "str");
		map.put("submitName", "innerStructure");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "建筑物结构");
		map.put("tempValue", "" + temp.getBuildingStructure());
		map.put("realValue", "" + target.getBuildingStructure());
		map.put("argType", "str");
		map.put("submitName", "buildingStructure");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "周围环境");
		map.put("tempValue", "" + temp.getSurrounding());
		map.put("realValue", "" + target.getSurrounding());
		map.put("argType", "str");
		map.put("submitName", "surrounding");
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

	@Override
	public PublicPlace getTargetById(Long id) {
		return publicPlaceServic.getPlaceById(id);
	}

}
