package com.tianque.plugin.dataManage.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.core.util.DateUtil;
import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.dataManage.location.rentalHouseTemp.domain.RentalHouseTemp;

public class DataManageBaseInfoUtil {
	private static Map<String, Object> dataManageMap = new HashMap<String, Object>();
	private static DataManageBaseInfoUtil dataManageBaseInfoUtil = null;
	private final static Logger logger = Logger.getLogger(DataManageBaseInfoUtil.class);

	private DataManageBaseInfoUtil() {
	}

	/**
	 * 根据类型获取判断唯一性的字段
	 * 
	 * @param type
	 * @return
	 */
	public static String getUniqueColumn(String type) {
		DataManageBaseInfo dataBaseinfo = getDataManageInfoByType(type);
		String mode = dataBaseinfo.getMode();
		if (DataManageBaseInfoTypes.POPULATION.equals(mode)) {
			return "idCardNo";
		}
		if (DataManageBaseInfoTypes.LOCATION.equals(mode)) {
			return "name";
		}
		if (DataManageBaseInfoTypes.COMPANYPLACE.equals(mode)) {
			return "name";
		}
		if (DataManageBaseInfoTypes.HOUSE.equals(mode)) {
			return "address";
		}
		if (DataManageBaseInfoTypes.DUSTBIN.equals(mode)) {
			return "dustbinCode";
		}
		if (DataManageBaseInfoTypes.BUILDDATAS.equals(mode)) {
			return "buildingname";
		}
		logger.error("通过type" + type + "获取唯一约束的列失败");
		return "";
	}

	/**
	 * 单例
	 * 
	 * @return
	 */
	public static DataManageBaseInfoUtil getInstance() {
		if (null == dataManageBaseInfoUtil) {
			dataManageBaseInfoUtil = new DataManageBaseInfoUtil();
		}
		return dataManageBaseInfoUtil;
	}

	/**
	 * 通过type得到xml对象
	 * 
	 * @param type
	 * @return DataManageBaseInfo
	 * @throws Exception
	 */
	public static DataManageBaseInfo getDataManageInfoByType(String type) {
		type = type.toUpperCase();
		if (null == dataManageMap.get(type)) {
			for (DataManageBaseInfo dataManageBaseInfoInList : getXmlList()) {
				dataManageMap.put(dataManageBaseInfoInList.getType().toUpperCase(),
						dataManageBaseInfoInList);
			}
		}
		DataManageBaseInfo result = (DataManageBaseInfo) dataManageMap.get(type);
		if (null == result) {

			throw new BusinessValidationException("不存在" + type + "类型");
		}
		return result;
	}

	// 数据管理中的认领中的获取基本类的所在路径
	public static String getDomainName(String type) {
		return getDataManageInfoByType(type).getDomainName();
	}

	public static String getBaseinfoViewPage(String type) {
		return getDataManageInfoByType(type).getViewPage();
	}

	// 得到修改页面
	public static String getUpdatePage(String type) {
		return getDataManageInfoByType(type).getUpdatePage();
	}

	public static String getTableByType(String type) {
		return getDataManageInfoByType(type).getTable();
	}

	/**
	 * 获取xml定义的对象集合
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<DataManageBaseInfo> getXmlList() {
		DataManageBaseInfoReader reader = new DataManageBaseInfoReader();
		DataManageBaseInfoList dataManageBaseInfo = reader.getDataManageBaseInfo("config"
				+ File.separator + "dataManageBaseInfo.xml");
		if (null == dataManageBaseInfo) {
			throw new BusinessValidationException("DataManageBaseInfo为空");
		}
		return dataManageBaseInfo.getDataManageBaseInfo();
	}

	public static String getPropertyDictListIds(List<PropertyDict> list) {
		StringBuffer str = new StringBuffer("");
		if (null == list) {
			return "";
		}
		for (PropertyDict propertyDict : list) {
			str.append(propertyDict.getId() + ",");
		}
		if (str.toString().length() == 0) {
			return "";
		} else {
			return str.toString().substring(0, str.toString().length() - 1);
		}
	}

	public static void getPropertyDictMapValue(List<PropertyDict> list, Map<String, Object> map) {
		if (null != list) {
			for (PropertyDict propertyDict : list) {
				map.put("" + propertyDict.getId(), "" + propertyDict.getDisplayName());
			}
		}
	}

	public static Map getDataManageMap() {
		return dataManageMap;
	}

	public static void setDataManageMap(Map dataManageMap) {
		DataManageBaseInfoUtil.dataManageMap = dataManageMap;
	}

	// ///////////////////////////////////////
	public static List getPopulationCommonList(Countrymen populationTemp, Countrymen population) {
		List list = new ArrayList();
		Map map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "身份证号码");
		map.put("tempValue", populationTemp.getIdCardNo());
		map.put("realValue", population.getIdCardNo());
		map.put("argType", "str");
		map.put("submitName", "idCardNo");
		list.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "姓名");
		map.put("tempValue", populationTemp.getName());
		map.put("realValue", population.getName());
		map.put("argType", "str");
		map.put("submitName", "name");
		list.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "性别");
		map.put("tempValue", "" + populationTemp.getGender().getId());
		map.put("realValue", "" + population.getGender().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@GENDER");
		map.put("argType", "PropertyDict");
		map.put("submitName", "gender.id");
		list.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "曾用名/别名");
		map.put("tempValue", "" + populationTemp.getUsedName());
		map.put("realValue", "" + population.getUsedName());
		map.put("argType", "str");
		map.put("submitName", "usedName");
		list.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "联系手机");
		map.put("tempValue", "" + populationTemp.getMobileNumber());
		map.put("realValue", "" + population.getMobileNumber());
		map.put("argType", "str");
		map.put("submitName", "mobileNumber");
		list.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "固定电话");
		map.put("tempValue", "" + populationTemp.getTelephone());
		map.put("realValue", "" + population.getTelephone());
		map.put("argType", "str");
		map.put("submitName", "telephone");
		list.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "出生日期");
		map.put("tempValue", "" + DateUtil.formateYMD(populationTemp.getBirthday()));
		map.put("realValue", "" + DateUtil.formateYMD(population.getBirthday()));
		map.put("argType", "strDate");
		map.put("submitName", "birthday");
		list.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "民族");
		map.put("tempValue", populationTemp.getNation() == null ? "-1" : ""
				+ populationTemp.getNation().getId());
		map.put("realValue", population.getNation() == null ? "-1" : ""
				+ population.getNation().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@NATION");
		map.put("argType", "PropertyDict");
		map.put("submitName", "nation.id");
		list.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "政治面貌");
		map.put("tempValue", populationTemp.getPoliticalBackground() == null ? "-1" : ""
				+ populationTemp.getPoliticalBackground().getId());
		map.put("realValue", population.getPoliticalBackground() == null ? "-1" : ""
				+ population.getPoliticalBackground().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND");
		map.put("argType", "PropertyDict");
		map.put("submitName", "politicalBackground.id");
		list.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "文化程度");
		map.put("tempValue", populationTemp.getSchooling() == null ? "-1" : ""
				+ populationTemp.getSchooling().getId());
		map.put("realValue", population.getSchooling() == null ? "-1" : ""
				+ population.getSchooling().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@SCHOOLING");
		map.put("argType", "PropertyDict");
		map.put("submitName", "schooling.id");
		list.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "职业");
		map.put("tempValue", populationTemp.getCareer() == null ? "-1" : ""
				+ populationTemp.getCareer().getId());
		map.put("realValue", population.getCareer() == null ? "-1" : ""
				+ population.getCareer().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@CAREER");
		map.put("argType", "PropertyDict");
		map.put("submitName", "career.id");
		list.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "工作单位或就读学校");
		map.put("tempValue", "" + populationTemp.getWorkUnit());
		map.put("realValue", "" + population.getWorkUnit());
		map.put("argType", "str");
		map.put("submitName", "workUnit");
		list.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "婚姻状况");
		map.put("tempValue", populationTemp.getMaritalState() == null ? "-1" : ""
				+ populationTemp.getMaritalState().getId());
		map.put("realValue", population.getMaritalState() == null ? "-1" : ""
				+ population.getMaritalState().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS");
		map.put("argType", "PropertyDict");
		map.put("submitName", "maritalState.id");
		list.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "户籍地");
		map.put("tempValue", "" + populationTemp.getProvince() + populationTemp.getCity()
				+ populationTemp.getDistrict());
		map.put("realValue",
				"" + population.getProvince() + population.getCity() + population.getDistrict());
		map.put("argType", "strs");
		map.put("submitName", new String[] { "province", "city", "district" });
		map.put("province", "" + population.getProvince());
		map.put("provinceTemp", "" + populationTemp.getProvince());
		map.put("city", "" + population.getCity());
		map.put("cityTemp", "" + populationTemp.getCity());
		map.put("district", "" + population.getDistrict());
		map.put("districtTemp", "" + populationTemp.getDistrict());
		list.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "户籍地详址");
		map.put("tempValue", "" + populationTemp.getNativePlaceAddress());
		map.put("realValue", "" + population.getNativePlaceAddress());
		map.put("argType", "str");
		map.put("submitName", "nativePlaceAddress");
		list.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "有无住房");
		map.put("tempValue", "" + population.getIsHaveHouse());
		map.put("realValue", "" + population.getIsHaveHouse());
		map.put("argType", "boolean");
		map.put("submitName", "isHaveHouse");
		list.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "无房原因");
		map.put("tempValue", "" + population.getNoHouseReason());
		map.put("realValue", "" + population.getNoHouseReason());
		map.put("argType", "str");
		map.put("submitName", "noHouseReason");
		list.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "其它地址");
		map.put("tempValue", "" + populationTemp.getOtherAddress());
		map.put("realValue", "" + population.getOtherAddress());
		map.put("argType", "str");
		map.put("submitName", "otherAddress");
		list.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "备注");
		map.put("tempValue", "" + populationTemp.getRemark());
		map.put("realValue", "" + population.getRemark());
		map.put("argType", "str");
		map.put("submitName", "remark");
		list.add(map);
		return list;
	}

	public static List getCommonList(RentalHouseTemp temp, RentalHouse real) {
		List list = new ArrayList();
		// Map map = new HashMap();
		// map.put("notNull", "true");
		// map.put("label", "地址");
		// map.put("tempValue", temp.getAddress());
		// map.put("realValue", real.getAddress());
		// map.put("inputType", "text");
		// map.put("submitName", "address");
		// list.add(map);
		// map = new HashMap();
		// map.put("notNull", "false");
		// map.put("label", "建筑名称");
		// map.put("tempValue", temp.getBuildingName());
		// map.put("realValue", real.getBuildingName());
		// map.put("inputType", "text");
		// map.put("submitName", "buildingName");
		// list.add(map);
		// map = new HashMap();
		// map.put("notNull", "false");
		// map.put("label", "名称");
		// map.put("tempValue", temp.getName());
		// map.put("realValue", real.getName());
		// map.put("inputType", "text");
		// map.put("submitName", "createUser");
		// list.add(map);
		// map = new HashMap();
		// map.put("notNull", "false");
		// map.put("label", "是否出租房");
		// map.put("tempValue", temp.getIsRentalHouse().toString());
		// map.put("realValue", real.getIsRentalHouse().toString());
		// map.put("inputType", "boolean");
		// map.put("submitName", "isRentalHouse");
		// list.add(map);
		// map = new HashMap();
		// map.put("notNull", "false");
		// map.put("label", "long_0_1");
		// map.put("tempValue", temp.getIsFireChannels().toString());
		// map.put("realValue", real.getIsFireChannels().toString());
		// map.put("inputType", "long");
		// map.put("submitName", "isFireChannels");
		// map.put("options", new String[] { "1", "0" });
		// map.put("1", "是是");
		// map.put("0", "否否");
		// list.add(map);
		// map = new HashMap();
		// map.put("notNull", "true");
		// map.put("label", "long_2_3");
		// map.put("tempValue", "2");
		// map.put("realValue", "3");
		// map.put("inputType", "long");
		// map.put("submitName", "isFireChannels");
		// map.put("options", new String[] { "-1", "2", "3" });
		// map.put("-1", "非111非");
		// map.put("2", "是222是");
		// map.put("3", "否333否");
		// list.add(map);
		// map = new HashMap();
		// map.put("notNull", "true");
		// map.put("label", "checkbox");
		// map.put("tempValue", "1,2");
		// map.put("realValue", "2,3");
		// map.put("inputType", "checkbox");
		// map.put("submitName", "checkboxTest");
		// map.put("checkboxes", new String[] { "1", "2", "3" });
		// map.put("1", "aaaaaaaaa");
		// map.put("2", "bbbbbbbb");
		// map.put("3", "cccccccccc");
		// list.add(map);
		return list;
	}

	// ////////////////////////////////////////
	public static void main(String[] args) {
		try {
			String str = "";
			String[] strs = new String[] {};
			System.out.println(strs.length);
			strs = str.split(",");
			System.out.println(strs.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
