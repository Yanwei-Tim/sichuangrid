package com.tianque.plugin.dataManage.location.companyPlaceTemp.controller;

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

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlace;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceBaseSevice;
import com.tianque.core.util.DateUtil;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.location.companyPlaceTemp.domain.CompanyPlaceTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;

/**
 * 
 * 数据管理--单位场所控制层
 */
@Namespace("/plugin/dataManage/companyPlaceTempManage")
@Transactional
@Controller("companyPlaceTempController")
@Scope("request")
public class CompanyPlaceTempController extends
		AbstractDataManageController<CompanyPlaceTemp, CompanyPlace> {
	@Autowired
	private CompanyPlaceBaseSevice companyPlaceBaseSevice;

	@Autowired
	@Qualifier("companyPlaceTempService")
	private AbstractDataManageService locationDataManageService;

	@Resource(name = "companyPlaceTempService")
	public void setDataManageService(
			AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(locationDataManageService);
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.LOCATION;
	}

	public CompanyPlaceTemp getTemp() {
		return temp;
	}

	public void setTemp(CompanyPlaceTemp temp) {
		this.temp = temp;
	}

	public CompanyPlaceTemp getPopulation() {
		return population;
	}

	public CompanyPlaceTemp getCompanyPlace() {
		return population;
	}

	public void setCompanyPlace(CompanyPlaceTemp companyPlace) {
		this.population = companyPlace;
	}

	public CompanyPlaceTemp getLocation() {
		return population;
	}

	@Override
	public List getCompareList(CompanyPlaceTemp temp, CompanyPlace target) {
		List compareList = new ArrayList();
		if (temp.getClassifiCationEn() != null) {
			Map map = new HashMap();
			map.put("notNull", "true");
			map.put("label", "场所名称");
			map.put("tempValue", "" + temp.getName());
			map.put("realValue", "" + target.getName());
			map.put("submitName", "name");
			map.put("argType", "str");
			compareList.add(map);
			map = new HashMap();
			map.put("notNull", "true");
			map.put("label", "关注度");
			map.put("tempValue", temp.getAttentionextent() == null ? "-1" : ""
					+ temp.getAttentionextent().getId());
			map.put("realValue", target.getAttentionextent() == null ? "-1"
					: "" + target.getAttentionextent().getId());
			map.put("PropertyDict",
					"@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT");
			map.put("argType", "PropertyDict");
			map.put("submitName", "attentionextent.id");
			compareList.add(map);
			map = new HashMap();
			map.put("notNull", "true");
			map.put("label", "地址");
			map.put("tempValue", "" + temp.getAddress());
			map.put("realValue", "" + target.getAddress());
			map.put("argType", "str");
			map.put("submitName", "address");
			compareList.add(map);
			map = new HashMap();
			map.put("notNull", "false");
			map.put("label", "一级类型");
			map.put("tempValue", temp.getType() == null ? "-1" : ""
					+ temp.getType().getId());
			map.put("realValue", target.getType() == null ? "-1" : ""
					+ target.getType().getId());
			map.put("PropertyDict",
					"@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_TYPE");
			map.put("argType", "PropertyDict");
			map.put("submitName", "type.id");
			compareList.add(map);
			map = new HashMap();
			map.put("notNull", "false");
			map.put("label", "二级类型");
			map.put("tempValue", temp.getClassifiCation() == null ? "-1" : ""
					+ temp.getClassifiCation().getId());
			map.put("realValue", target.getClassifiCation() == null ? "-1" : ""
					+ target.getClassifiCation().getId());
			map.put("PropertyDict",
					"@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_CLASSIFICATION");
			map.put("argType", "PropertyDict");
			map.put("submitName", "classifiCation.id");
			compareList.add(map);
			map = new HashMap();
			map.put("notNull", "false");
			map.put("label", "单位场所分类");
			map.put("tempValue", temp.getCategory() == null ? "-1" : ""
					+ temp.getCategory().getId());
			map.put("realValue", target.getCategory() == null ? "-1" : ""
					+ target.getCategory().getId());
			map.put("PropertyDict",
					"@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_CATEGORY");
			map.put("argType", "PropertyDict");
			map.put("submitName", "category.id");
			compareList.add(map);
			if (!ModulTypes.EDUCATIONCOMPANY_KEY.equals(temp
					.getClassifiCationEn())
					&& !ModulTypes.PARTYGOVERNMENTORGANCOMPANY_KEY.equals(temp
							.getClassifiCationEn())) {
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "负责人");
				map.put("tempValue", "" + temp.getUserName());
				map.put("realValue", "" + target.getUserName());
				map.put("argType", "str");
				map.put("submitName", "userName");
				compareList.add(map);
			}
			if (!ModulTypes.PARTYGOVERNMENTORGANCOMPANY_KEY.equals(temp
					.getClassifiCationEn())) {
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "联系手机");
				map.put("tempValue", "" + temp.getMobileNumber());
				map.put("realValue", "" + target.getMobileNumber());
				map.put("argType", "str");
				map.put("submitName", "mobileNumber");
				compareList.add(map);
			}
			map = new HashMap();
			map.put("notNull", "false");
			map.put("label", "联系固话");
			map.put("tempValue", "" + temp.getTelePhone());
			map.put("realValue", "" + target.getTelePhone());
			map.put("argType", "str");
			map.put("submitName", "telePhone");
			compareList.add(map);
			map = new HashMap();
			map.put("notNull", "false");
			map.put("label", "传真号码");
			map.put("tempValue", "" + temp.getFaxNumber());
			map.put("realValue", "" + target.getFaxNumber());
			map.put("argType", "str");
			map.put("submitName", "faxNumber");
			compareList.add(map);

			// <!-- 教育私有 -->
			if (ModulTypes.EDUCATIONCOMPANY_KEY.equals(temp
					.getClassifiCationEn())) {
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "校长");
				map.put("tempValue", "" + temp.getUserName());
				map.put("realValue", "" + target.getUserName());
				map.put("argType", "str");
				map.put("submitName", "userName");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "法制副校长");
				map.put("tempValue", "" + temp.getLegalVicePrincipal());
				map.put("realValue", "" + target.getLegalVicePrincipal());
				map.put("argType", "str");
				map.put("submitName", "legalVicePrincipal");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "学校性质");
				map.put("tempValue", temp.getSchoolNature() == null ? "-1" : ""
						+ temp.getSchoolNature().getId());
				map.put("realValue", target.getSchoolNature() == null ? "-1"
						: "" + target.getSchoolNature().getId());
				map.put("PropertyDict",
						"@com.tianque.domain.property.PropertyTypes@HOSPITALS_KIND");
				map.put("argType", "PropertyDict");
				map.put("submitName", "schoolNature.id");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "学校英文名称");
				map.put("tempValue", "" + temp.getSchoolNameEn());
				map.put("realValue", "" + target.getSchoolNameEn());
				map.put("argType", "str");
				map.put("submitName", "schoolNameEn");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "学校网址 ");
				map.put("tempValue", "" + temp.getSchoolWebSite());
				map.put("realValue", "" + target.getSchoolWebSite());
				map.put("argType", "str");
				map.put("submitName", "schoolWebSite");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "周边情况");
				map.put("tempValue", "" + temp.getArea());
				map.put("realValue", "" + target.getArea());
				map.put("argType", "str");
				map.put("submitName", "area");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "电子邮箱");
				map.put("tempValue", "" + temp.getEmail());
				map.put("realValue", "" + target.getEmail());
				map.put("argType", "str");
				map.put("submitName", "email");
				compareList.add(map);
			}

			if (ModulTypes.PUBLICPLACE_KEY.equals(temp.getClassifiCationEn())
					|| ModulTypes.TRAFFICPLACE_KEY.equals(temp
							.getClassifiCationEn())
					|| ModulTypes.ENTERTAINMENTPLACE_KEY.equals(temp
							.getClassifiCationEn())) {
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "主管单位");
				map.put("tempValue", "" + temp.getArea());
				map.put("realValue", "" + target.getArea());
				map.put("argType", "str");
				map.put("submitName", "area");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "面积");
				map.put("tempValue", "" + temp.getCoveredArea());
				map.put("realValue", "" + target.getCoveredArea());
				map.put("argType", "str");
				map.put("submitName", "coveredArea");
				compareList.add(map);
			}

			// <!-- 执照（娱乐场所，商贸场所，上网服务场所，住宿场所，餐饮场所，其他场所，教育） -->
			if (ModulTypes.ENTERTAINMENTPLACE_KEY.equals(temp
					.getClassifiCationEn())
					|| ModulTypes.TRADEPLACE_KEY.equals(temp
							.getClassifiCationEn())
					|| ModulTypes.INTERNETSERVICESPLACE_KEY.equals(temp
							.getClassifiCationEn())
					|| ModulTypes.ACCOMMODATIONSERVICESPLACE_KEY.equals(temp
							.getClassifiCationEn())
					|| ModulTypes.FOODSERVICESPLACE_KEY.equals(temp
							.getClassifiCationEn())
					|| ModulTypes.OTHERPLACE_KEY.equals(temp
							.getClassifiCationEn())
					|| ModulTypes.EDUCATIONCOMPANY_KEY.equals(temp
							.getClassifiCationEn())
					|| ModulTypes.MEDICALHYGIENECOMPANY_KEY.equals(temp
							.getClassifiCationEn())
					|| ModulTypes.DANGEROUSSTORECOMPANY_KEY.equals(temp
							.getClassifiCationEn())
					|| ModulTypes.OTHERCOMPANY_KEY.equals(temp
							.getClassifiCationEn())) {
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "是否有证照");
				map.put("tempValue", "" + temp.getHasLicense());
				map.put("realValue", "" + target.getHasLicense());
				map.put("argType", "int");
				map.put("submitName", "hasLicense");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "营业执照号");
				map.put("tempValue", "" + temp.getBusinessLicenseNo());
				map.put("realValue", "" + target.getBusinessLicenseNo());
				map.put("argType", "str");
				map.put("submitName", "businessLicenseNo");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "true");
				map.put("label", "组织机构代码");
				map.put("tempValue", "" + temp.getOrgCode());
				map.put("realValue", "" + target.getOrgCode());
				map.put("argType", "str");
				map.put("submitName", "orgCode");
				compareList.add(map);
			}
			// <!-- 规模企业（商贸场所，上网服务场所，餐饮服务场所，其他场所） -->
			if (ModulTypes.TRADEPLACE_KEY.equals(temp.getClassifiCationEn())
					|| ModulTypes.INTERNETSERVICESPLACE_KEY.equals(temp
							.getClassifiCationEn())
					|| ModulTypes.FOODSERVICESPLACE_KEY.equals(temp
							.getClassifiCationEn())
					|| ModulTypes.OTHERPLACE_KEY.equals(temp
							.getClassifiCationEn())
					|| ModulTypes.MEDICALHYGIENECOMPANY_KEY.equals(temp
							.getClassifiCationEn())
					|| ModulTypes.DANGEROUSSTORECOMPANY_KEY.equals(temp
							.getClassifiCationEn())
					|| ModulTypes.OTHERCOMPANY_KEY.equals(temp
							.getClassifiCationEn())) {
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "规模");
				map.put("tempValue", temp.getScaleType() == null ? "-1" : ""
						+ temp.getScaleType().getId());
				map.put("realValue", target.getScaleType() == null ? "-1" : ""
						+ target.getScaleType().getId());
				map.put("PropertyDict",
						"@com.tianque.domain.property.PropertyTypes@ENTERPRISE_TYPE");
				map.put("argType", "PropertyDict");
				map.put("submitName", "scaleType.id");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "党员数");
				map.put("tempValue", "" + temp.getPartyCountNumber());
				map.put("realValue", "" + target.getPartyCountNumber());
				map.put("argType", "str");
				map.put("submitName", "partyCountNumber");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "注册资金 ");
				map.put("tempValue", "" + temp.getRegisteredCapital());
				map.put("realValue", "" + target.getRegisteredCapital());
				map.put("argType", "str");
				map.put("submitName", "registeredCapital");
				compareList.add(map);
			}
			// <!-- 商贸场所私有 -->
			if (ModulTypes.TRADEPLACE_KEY.equals(temp.getClassifiCationEn())) {
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "通道口");
				map.put("tempValue", "" + temp.getArea());
				map.put("realValue", "" + target.getArea());
				map.put("argType", "str");
				map.put("submitName", "area");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "小件寄存处");
				map.put("tempValue", "" + temp.getCloakRoom());
				map.put("realValue", "" + target.getCloakRoom());
				map.put("argType", "str");
				map.put("submitName", "cloakRoom");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "总面积");
				map.put("tempValue", "" + temp.getCoveredArea());
				map.put("realValue", "" + target.getCoveredArea());
				map.put("argType", "str");
				map.put("submitName", "coveredArea");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "营业面积");
				map.put("tempValue", "" + temp.getBusinessArea());
				map.put("realValue", "" + target.getBusinessArea());
				map.put("argType", "str");
				map.put("submitName", "businessArea");
				compareList.add(map);
			}
			// <!-- 上网服务场所私有 -->
			if (ModulTypes.INTERNETSERVICESPLACE_KEY.equals(temp
					.getClassifiCationEn())) {
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "所在派出所");
				map.put("tempValue", "" + temp.getArea());
				map.put("realValue", "" + target.getArea());
				map.put("argType", "str");
				map.put("submitName", "area");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "面积");
				map.put("tempValue", "" + temp.getCoveredArea());
				map.put("realValue", "" + target.getCoveredArea());
				map.put("argType", "str");
				map.put("submitName", "coveredArea");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "宽带接入服务商 ");
				map.put("tempValue", "" + temp.getGeneralType());
				map.put("realValue", "" + target.getGeneralType());
				map.put("argType", "str");
				map.put("submitName", "generalType");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "现在使用IP");
				map.put("tempValue", "" + temp.getNowip());
				map.put("realValue", "" + target.getNowip());
				map.put("argType", "str");
				map.put("submitName", "nowip");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "电脑台数");
				map.put("tempValue", "" + temp.getCountNo());
				map.put("realValue", "" + target.getCountNo());
				map.put("argType", "str");
				map.put("submitName", "countNo");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "接入方式 ");
				map.put("tempValue", "" + temp.getGeneralStorage());
				map.put("realValue", "" + target.getGeneralStorage());
				map.put("argType", "str");
				map.put("submitName", "generalStorage");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "临时上网卡数 ");
				map.put("tempValue", "" + temp.getNetworkCardNo());
				map.put("realValue", "" + target.getNetworkCardNo());
				map.put("argType", "str");
				map.put("submitName", "networkCardNo");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "网络文化经营许可证号");
				map.put("tempValue", "" + temp.getGeneralManage());
				map.put("realValue", "" + target.getGeneralManage());
				map.put("argType", "str");
				map.put("submitName", "generalManage");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "网络安全审核意见书号");
				map.put("tempValue", "" + temp.getGeneralMente());
				map.put("realValue", "" + target.getGeneralMente());
				map.put("argType", "str");
				map.put("submitName", "generalMente");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "消防审核意见书号");
				map.put("tempValue", "" + temp.getFireAuditOpinionNo());
				map.put("realValue", "" + target.getFireAuditOpinionNo());
				map.put("argType", "str");
				map.put("submitName", "fireAuditOpinionNo");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "网吧编号");
				map.put("tempValue", "" + temp.getInternetBarNo());
				map.put("realValue", "" + target.getInternetBarNo());
				map.put("argType", "str");
				map.put("submitName", "internetBarNo");
				compareList.add(map);
			}
			// <!-- 施工单位私有 -->
			if (ModulTypes.CONSTRUCTIONPLACE_KEY.equals(temp
					.getClassifiCationEn())) {
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "施工单位");
				map.put("tempValue", "" + temp.getArea());
				map.put("realValue", "" + target.getArea());
				map.put("argType", "str");
				map.put("submitName", "area");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "施工开始时间");
				map.put("tempValue",
						"" + DateUtil.formateYMD(temp.getBegintime()));
				map.put("realValue",
						"" + DateUtil.formateYMD(target.getBegintime()));
				map.put("argType", "strDate");
				map.put("submitName", "begintime");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "施工结束时间");
				map.put("tempValue",
						"" + DateUtil.formateYMD(temp.getEndtime()));
				map.put("realValue",
						"" + DateUtil.formateYMD(target.getEndtime()));
				map.put("argType", "strDate");
				map.put("submitName", "endtime");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "施工面积");
				map.put("tempValue", "" + temp.getCoveredArea());
				map.put("realValue", "" + target.getCoveredArea());
				map.put("argType", "str");
				map.put("submitName", "coveredArea");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "施工挖方量（立方米）");
				map.put("tempValue", "" + temp.getDigVolume());
				map.put("realValue", "" + target.getDigVolume());
				map.put("argType", "str");
				map.put("submitName", "digVolume");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "施工填方量（立方米）");
				map.put("tempValue", "" + temp.getFillVolume());
				map.put("realValue", "" + target.getFillVolume());
				map.put("argType", "str");
				map.put("submitName", "fillVolume");
				compareList.add(map);
			}

			// <!-- 医疗卫生私有 -->
			if (ModulTypes.MEDICALHYGIENECOMPANY_KEY.equals(temp
					.getClassifiCationEn())) {
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
				map.put("label", "医院性质");
				map.put("tempValue", temp.getHospitalNature() == null ? "-1"
						: "" + temp.getHospitalNature().getId());
				map.put("realValue", target.getHospitalNature() == null ? "-1"
						: "" + target.getHospitalNature().getId());
				map.put("PropertyDict",
						"@com.tianque.domain.property.PropertyTypes@HOSPITALS_KIND");
				map.put("argType", "PropertyDict");
				map.put("submitName", "hospitalNature.id");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "所属单位");
				map.put("tempValue", "" + temp.getArea());
				map.put("realValue", "" + target.getArea());
				map.put("argType", "str");
				map.put("submitName", "area");
				compareList.add(map);
			}
			// <!-- 危险品存放单位 私有 -->
			if (ModulTypes.DANGEROUSSTORECOMPANY_KEY.equals(temp
					.getClassifiCationEn())) {
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "存储设备 ");
				map.put("tempValue", "" + temp.getGeneralStorage());
				map.put("realValue", "" + target.getGeneralStorage());
				map.put("argType", "str");
				map.put("submitName", "generalStorage");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "副本许可范围");
				map.put("tempValue", "" + temp.getArea());
				map.put("realValue", "" + target.getArea());
				map.put("argType", "str");
				map.put("submitName", "area");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "货物类别");
				map.put("tempValue", "" + temp.getGeneralType());
				map.put("realValue", "" + target.getGeneralType());
				map.put("argType", "str");
				map.put("submitName", "generalType");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "传真号码2");
				map.put("tempValue", "" + temp.getFaxNo());
				map.put("realValue", "" + target.getFaxNo());
				map.put("argType", "str");
				map.put("submitName", "faxNo");
				compareList.add(map);
			}
			// <!-- 单位-其他私有 -->
			if (ModulTypes.OTHERCOMPANY_KEY.equals(temp.getClassifiCationEn())) {
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "注册资本(万元) ");
				map.put("tempValue", "" + temp.getRegisteredCapitalNo());
				map.put("realValue", "" + target.getRegisteredCapitalNo());
				map.put("argType", "str");
				map.put("submitName", "registeredCapitalNo");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", " 经济性质");
				map.put("tempValue", temp.getEconomicNature() == null ? "-1"
						: "" + temp.getEconomicNature().getId());
				map.put("realValue", target.getEconomicNature() == null ? "-1"
						: "" + target.getEconomicNature().getId());
				map.put("PropertyDict",
						"@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_ECONOMICNATURE");
				map.put("argType", "PropertyDict");
				map.put("submitName", "economicNature.id");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "注册日期");
				map.put("tempValue",
						"" + DateUtil.formateYMD(temp.getBegintime()));
				map.put("realValue",
						"" + DateUtil.formateYMD(target.getBegintime()));
				map.put("argType", "strDate");
				map.put("submitName", "begintime");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "有效期至");
				map.put("tempValue",
						"" + DateUtil.formateYMD(temp.getEndtime()));
				map.put("realValue",
						"" + DateUtil.formateYMD(target.getEndtime()));
				map.put("argType", "strDate");
				map.put("submitName", "endtime");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "经营范围");
				map.put("tempValue", "" + temp.getArea());
				map.put("realValue", "" + target.getArea());
				map.put("argType", "str");
				map.put("submitName", "area");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "注册地址 ");
				map.put("tempValue", "" + temp.getGeneralStorage());
				map.put("realValue", "" + target.getGeneralStorage());
				map.put("argType", "str");
				map.put("submitName", "generalStorage");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "从业人数");
				map.put("tempValue", "" + temp.getCountNo());
				map.put("realValue", "" + target.getCountNo());
				map.put("argType", "str");
				map.put("submitName", "countNo");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "主管部门");
				map.put("tempValue", "" + temp.getGeneralManage());
				map.put("realValue", "" + target.getGeneralManage());
				map.put("argType", "str");
				map.put("submitName", "generalManage");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "管理等级");
				map.put("tempValue", temp.getManagementLevel() == null ? "-1"
						: "" + temp.getManagementLevel().getId());
				map.put("realValue", target.getManagementLevel() == null ? "-1"
						: "" + target.getManagementLevel().getId());
				map.put("PropertyDict",
						"@com.tianque.domain.property.PropertyTypes@EFFECT_EVALUATION_TYPE");
				map.put("argType", "PropertyDict");
				map.put("submitName", "managementLevel.id");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "管理部门");
				map.put("tempValue", "" + temp.getGeneralMente());
				map.put("realValue", "" + target.getGeneralMente());
				map.put("argType", "str");
				map.put("submitName", "generalMente");
				compareList.add(map);
				map = new HashMap();
				map.put("notNull", "false");
				map.put("label", "消防等级");
				map.put("tempValue", temp.getFireLevel() == null ? "-1" : ""
						+ temp.getFireLevel().getId());
				map.put("realValue", target.getFireLevel() == null ? "-1" : ""
						+ target.getFireLevel().getId());
				map.put("PropertyDict",
						"@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_FIREFIGHTINGLEVEL");
				map.put("argType", "PropertyDict");
				map.put("submitName", "fireLevel.id");
				compareList.add(map);
			}
			map = new HashMap();
			map.put("notNull", "false");
			map.put("label", "备注");
			map.put("tempValue", "" + temp.getRemarks());
			map.put("realValue", "" + target.getRemarks());
			map.put("argType", "str");
			map.put("submitName", "remarks");
			compareList.add(map);
		}
		return compareList;
	}

	@Override
	public CompanyPlace getTargetById(Long id) {
		return companyPlaceBaseSevice.getCompanyPlaceInfoByCid(id);
	}
}
