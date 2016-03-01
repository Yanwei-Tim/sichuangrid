/**
 * 
 */
package com.tianque.serviceList.domain;

/**
 * @作者:彭乐
 * @功能: 
 * @时间:2015-11-27 上午10:55:54
 * @邮箱:pengle@hztianque.com
 */
public enum ServiceListEnum {
	//政策法规宣传
	PolicyPropaganda("policyPropaganda",1),
	//食品安全协管
	FoodSafty("foodSafty",2),
	//药品安全协管
	DrugsSafty("drugsSafty",3),
	//工商行政管理协管
	Bussiness("bussiness",4),
	//打击非法传销协管
	PyramidSales("pyramidSales",5),
	//查处取缔无证无照经营协管
	Unlicensed("unlicensed",6),
	//其它情况
	OtherSituation("otherSituation",7);
	
	ServiceListEnum(String key,Integer value){
		this.key=key;
		this.value=value;
	}
	
	public static Integer getValue(String key) {
		if (key != null && !"".equals(key)) {
			for (ServiceListEnum actionTypeEnum : ServiceListEnum.values()) {
				if (key.equals(actionTypeEnum.getKey())) {
					return actionTypeEnum.getValue();
				}
			}
		}
		return null;
	}
	
	private String key;
	private Integer value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	
}
