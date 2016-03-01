package com.tianque.plugin.dataManage.location.schoolTemp.controller;

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

import com.tianque.domain.School;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.location.schoolTemp.domain.SchoolTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.service.SchoolService;

/**
 * 数据管理 - 学校控制类。
 */
@Namespace("/plugin/dataManage/schoolTempManage")
@Transactional
@Controller("schoolTempController")
@Scope("request")
public class SchoolTempController extends AbstractDataManageController<SchoolTemp, School> {
	@Autowired
	private SchoolService schoolService;
	@Autowired
	@Qualifier("schoolTempService")
	private AbstractDataManageService locationDataManageService;

	@Resource(name = "schoolTempService")
	public void setDataManageService(AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(locationDataManageService);
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.LOCATION;
	}

	public SchoolTemp getTemp() {
		return temp;
	}

	public void setTemp(SchoolTemp temp) {
		this.temp = temp;
	}

	public SchoolTemp getLocation() {
		return population;
	}

	public SchoolTemp getPopulation() {
		return population;
	}

	public void setLocation(SchoolTemp Location) {
		this.population = Location;
	}

	@Override
	public List getCompareList(SchoolTemp temp, School target) {
		List compareList = new ArrayList();
		Map map = new HashMap();
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "学校名称");
		map.put("tempValue", "" + temp.getName());
		map.put("realValue", "" + target.getChineseName());
		map.put("argType", "str");
		map.put("submitName", "name");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", " 学校地址");
		map.put("tempValue", "" + temp.getAddress());
		map.put("realValue", "" + target.getAddress());
		map.put("argType", "str");
		map.put("submitName", "address");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "学校性质");
		map.put("tempValue", temp.getKind() == null ? "-1" : "" + temp.getKind().getId());
		map.put("realValue", target.getKind() == null ? "-1" : "" + target.getKind().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@SCHOOL_PROPERTY");
		map.put("argType", "PropertyDict");
		map.put("submitName", "kind.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "校长");
		map.put("tempValue", "" + temp.getManager());
		map.put("realValue", "" + target.getPresident());
		map.put("argType", "str");
		map.put("submitName", "manager");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "学校类型");
		map.put("tempValue", temp.getType() == null ? "-1" : "" + temp.getType().getId());
		map.put("realValue", target.getType() == null ? "-1" : "" + target.getType().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@SCHOOL_TYPE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "type.id");
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
		map.put("label", "英文名称");
		map.put("tempValue", "" + temp.getEnglishName());
		map.put("realValue", "" + target.getEnglishName());
		map.put("argType", "str");
		map.put("submitName", "englishName");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "学校网址");
		map.put("tempValue", "" + temp.getWebSite());
		map.put("realValue", "" + target.getWebSite());
		map.put("argType", "str");
		map.put("submitName", "webSite");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "法制副校长");
		map.put("tempValue", "" + temp.getVicePresident());
		map.put("realValue", "" + target.getPersonLiable());
		map.put("argType", "str");
		map.put("submitName", "vicePresident");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "在校人数");
		map.put("tempValue", "" + temp.getAtSchoolHeadcount());
		map.put("realValue", "" + target.getAtSchoolHeadcount());
		map.put("argType", "str");
		map.put("submitName", "atSchoolHeadcount");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "联系电话");
		map.put("tempValue", "" + temp.getTelephone());
		map.put("realValue", "" + target.getPersonLiableTelephone());
		map.put("argType", "str");
		map.put("submitName", "telephone");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "联系手机");
		map.put("tempValue", "" + temp.getMobileNumber());
		map.put("realValue", "" + target.getPersonLiableMobileNumber());
		map.put("argType", "str");
		map.put("submitName", "mobileNumber");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "传真");
		map.put("tempValue", "" + temp.getFax());
		map.put("realValue", "" + target.getFax());
		map.put("argType", "str");
		map.put("submitName", "fax");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "电子邮箱");
		map.put("tempValue", "" + temp.getEmail());
		map.put("realValue", "" + target.getEmail());
		map.put("argType", "str");
		map.put("submitName", "email");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "周边情况");
		map.put("tempValue", "" + temp.getRemark());
		map.put("realValue", "" + target.getRemark());
		map.put("argType", "str");
		map.put("submitName", "remark");
		compareList.add(map);
		return compareList;
	}

	@Override
	public School getTargetById(Long id) {
		return schoolService.getSchoolById(id);
	}

}
