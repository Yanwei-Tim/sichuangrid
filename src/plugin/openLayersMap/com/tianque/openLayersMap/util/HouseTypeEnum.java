package com.tianque.openLayersMap.util;


public enum HouseTypeEnum {

	SPF("#ffff00"), //商品房
	ZJMF( "#ff9900"),//自建民房
	JJSYF("#ff6600"), //经济适用房
	JTSS( "#cc00ff"), //集体宿舍
	XCQF("#66ff00"), //小产权房
	SHBZF("#ffff00"), //社会保障房
	BGF( "#339900"),//办公房
	GF("#3333ff"), //公房
	LZF( "#66ffff"), //廉租房
	CF("#9933ff"), //厂房
	CKF("#ff00ff"), //储库房
	PMF( "#ccff33"),//铺面房
	JCF("#cc0099"), //军产房
	YCF( "#999933"), //央产房
	XZF("#993333"),//闲置房
	QT("#D9D919");//其他

	private String color;

	HouseTypeEnum(final String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}

}
