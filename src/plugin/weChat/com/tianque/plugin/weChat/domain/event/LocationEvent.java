package com.tianque.plugin.weChat.domain.event;

/**
 * 上报地理位置事件
 * @author 
 *  @date  2014年4月17日
 */
public class LocationEvent extends BaseEvent {
	//地理位置纬度
	private String Latitude;
	//地理位置经度
	private String Longitude;
	//地理位置精度
	private String precision;

	public String getLatitude() {
		return Latitude;
	}

	public String getLongitude() {
		return Longitude;
	}

	public String getPrecision() {
		return precision;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

}
