package com.tianque.openLayersMap.domian.vo;

import java.io.Serializable;

/**
 * 二维地图	当前地图屏幕内的实体类
 * @author jiangjinling
 *
 */
@SuppressWarnings("serial")
public class ScreenCoordinateVo implements Serializable {
	
	/** 当前地图屏幕内最小经度 */
	private Double minLon;

	/** 当前地图屏幕内最大经度 */
	private Double maxLon;

	/** 当前地图屏幕内最小纬度 */
	private Double minLat;

	/** 当前地图屏幕内最大纬度 */
	private Double maxLat;
	/** 坐标点集合 */
	private String points;
	
	private SearchInfoVo searchInfoVo;

	public ScreenCoordinateVo() {
		// TODO Auto-generated constructor stub
	}
	
	public ScreenCoordinateVo(Double minLon, Double maxLon, Double minLat, Double maxLat) {
		this.minLon = minLon;
		this.maxLon = maxLon;
		this.minLat = minLat;
		this.maxLat = maxLat;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public Double getMinLon() {
		return minLon;
	}

	public void setMinLon(Double minLon) {
		this.minLon = minLon;
	}

	public Double getMaxLon() {
		return maxLon;
	}

	public void setMaxLon(Double maxLon) {
		this.maxLon = maxLon;
	}

	public Double getMinLat() {
		return minLat;
	}

	public void setMinLat(Double minLat) {
		this.minLat = minLat;
	}

	public Double getMaxLat() {
		return maxLat;
	}

	public void setMaxLat(Double maxLat) {
		this.maxLat = maxLat;
	}

	public SearchInfoVo getSearchInfoVo() {
		return searchInfoVo;
	}

	public void setSearchInfoVo(SearchInfoVo searchInfoVo) {
		this.searchInfoVo = searchInfoVo;
	}

}
