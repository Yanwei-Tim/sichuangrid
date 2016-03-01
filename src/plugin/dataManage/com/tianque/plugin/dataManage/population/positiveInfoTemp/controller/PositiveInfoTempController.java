package com.tianque.plugin.dataManage.population.positiveInfoTemp.controller;

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

import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.baseInfo.positiveInfo.service.PositiveInfoService;
import com.tianque.core.util.DateUtil;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.positiveInfoTemp.domain.PositiveInfoTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;

/**
 * 数据管理 - 刑释人员控制类。
 */
@Namespace("/plugin/dataManage/positiveInfoTempManage")
@Transactional
@Scope("request")
@Controller("positiveInfoTempController")
public class PositiveInfoTempController extends
		AbstractDataManageController<PositiveInfoTemp, PositiveInfo> {

	@Autowired
	private PositiveInfoService positiveInfoService;
	@Autowired
	@Qualifier("businessPopulationDataManageService")
	private AbstractDataManageService businessPopulationDataManageService;

	@Resource(name = "businessPopulationDataManageService")
	public void setDataManageService(
			AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(businessPopulationDataManageService);
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.POPULATION;
	}

	public PositiveInfoTemp getPopulation() {
		return population;
	}

	public void setPopulation(PositiveInfoTemp population) {
		this.population = population;
	}

	public PositiveInfoTemp getTemp() {
		return temp;
	}

	public void setTemp(PositiveInfoTemp temp) {
		this.temp = temp;
	}

	@Override
	public PositiveInfo getTargetById(Long id) {
		return positiveInfoService.getPositiveInfoById(id);
	}

	@Override
	public List getCompareList(PositiveInfoTemp positiveInfoTemp,
			PositiveInfo positiveInfo) {
		List compareList = new ArrayList();
		compareList = DataManageBaseInfoUtil.getPopulationCommonList(
				positiveInfoTemp, positiveInfo);
		Map map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "原判刑期");
		map.put("tempValue", "" + positiveInfoTemp.getImprisonmentDate());
		map.put("realValue", "" + positiveInfo.getImprisonmentDate());
		map.put("argType", "str");
		map.put("submitName", "imprisonmentDate");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "关注程度");
		map.put("tempValue",
				positiveInfoTemp.getAttentionExtent() == null ? "-1" : ""
						+ positiveInfoTemp.getAttentionExtent().getId());
		map.put("realValue", positiveInfo.getAttentionExtent() == null ? "-1"
				: "" + positiveInfo.getAttentionExtent().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT");
		map.put("argType", "PropertyDict");
		map.put("submitName", "attentionExtent.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "是否累犯");
		map.put("tempValue", positiveInfoTemp.getIsRepeat() == null ? "-1"
				: positiveInfoTemp.getIsRepeat().toString());
		map.put("realValue", positiveInfo.getIsRepeat() == null ? "-1"
				: positiveInfo.getIsRepeat().toString());
		map.put("argType", "long");
		map.put("submitName", "isRepeat");
		map.put("options", new String[] { "-1", "1", "0" });
		map.put("-1", "<空>");
		map.put("1", "是");
		map.put("0", "否");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "解教（刑释）日期");
		map.put("tempValue",
				""
						+ DateUtil.formateYMD(positiveInfoTemp
								.getReleaseOrBackDate()));
		map.put("realValue",
				"" + DateUtil.formateYMD(positiveInfo.getReleaseOrBackDate()));
		map.put("argType", "strDate");
		map.put("submitName", "releaseOrBackDate");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "人员类型");
		map.put("tempValue",
				positiveInfoTemp.getPositiveInfoType() == null ? "-1" : ""
						+ positiveInfoTemp.getPositiveInfoType().getId());
		map.put("realValue", positiveInfo.getPositiveInfoType() == null ? "-1"
				: "" + positiveInfo.getPositiveInfoType().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@POSITIVEINFO");
		map.put("argType", "PropertyDict");
		map.put("submitName", "positiveInfoType.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "安置日期");
		map.put("tempValue",
				""
						+ DateUtil.formateYMD(positiveInfoTemp
								.getResettlementDate()));
		map.put("realValue",
				"" + DateUtil.formateYMD(positiveInfo.getResettlementDate()));
		map.put("argType", "strDate");
		map.put("submitName", "resettlementDate");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "原罪名/原罪错");
		map.put("tempValue", "" + positiveInfoTemp.getCaseReason());
		map.put("realValue", "" + positiveInfo.getCaseReason());
		map.put("argType", "str");
		map.put("submitName", "caseReason");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "劳教(服刑)场所");
		map.put("tempValue", "" + positiveInfoTemp.getLaborEduAddress());
		map.put("realValue", "" + positiveInfo.getLaborEduAddress());
		map.put("argType", "str");
		map.put("submitName", "laborEduAddress");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "原职业");
		map.put("tempValue", positiveInfoTemp.getAgoProfession() == null ? "-1"
				: "" + positiveInfoTemp.getAgoProfession().getId());
		map.put("realValue", positiveInfo.getAgoProfession() == null ? "-1"
				: "" + positiveInfo.getAgoProfession().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@CAREER");
		map.put("argType", "PropertyDict");
		map.put("submitName", "agoProfession.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "未安置原因");
		map.put("tempValue", "" + positiveInfoTemp.getNoResettlementReason());
		map.put("realValue", "" + positiveInfo.getNoResettlementReason());
		map.put("argType", "str");
		map.put("submitName", "noResettlementReason");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "本年度是否重犯");
		map.put("tempValue", "" + positiveInfoTemp.getIsCrime());
		map.put("realValue", "" + positiveInfo.getIsCrime());
		map.put("argType", "boolean");
		map.put("submitName", "isCrime");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "犯罪行为");
		map.put("tempValue", "" + positiveInfoTemp.getCriminalBehavior());
		map.put("realValue", "" + positiveInfo.getCriminalBehavior());
		map.put("argType", "str");
		map.put("submitName", "criminalBehavior");
		compareList.add(map);

		return compareList;
	}
}
