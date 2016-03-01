package com.tianque.openLayersMap.service.convert;

import com.tianque.exception.base.SystemUtilException;
import com.tianque.openLayersMap.domian.Point;
import com.tianque.openLayersMap.domian.Polygon;

/**
 * 二三维坐标转换
 */
public abstract class GisConvert {

	/** 转换2维坐标，获取25维点坐标 */
	public abstract Point to25DPoint(Point point);

	/** 转换25维坐标，获取2维点坐标 */
	public abstract Point to2DPoint(Point point);

	/** 获取25维面积 单位（平方米）——在25维地图上，测面积功能可能会有偏差，需要转换 */
	public abstract Double to25DArea(String polygon);

	/** 获取25维长度 单位（米）——在25维地图上，测长度功能可能会有偏差，需要转换 */
	public abstract Double to25DLength(String line);

	/** 转换2维（热区）集合，获取25维点（热区）集合 */
	public String to25DPolygon(String polygonStr) {
		try {
			Polygon polygon = new Polygon(polygonStr);
			Polygon result = new Polygon();
			for (Point p : polygon) {
				result.add(to25DPoint(p));
			}
			return result.toString();
		} catch (Exception e) {
			throw new SystemUtilException("坐标转换 获取25维点（热区）集合出错", e);
		}
	}

	/** 转换25维（热区）集合，获取2维点（热区）集合 */
	public String to2DPolygon(String polygonStr) {
		try {
			Polygon polygon = new Polygon(polygonStr);
			Polygon result = new Polygon();
			for (Point p : polygon) {
				result.add(to2DPoint(p));
			}
			return result.toString();
		} catch (Exception e) {
			throw new SystemUtilException("坐标转换 获取2维点（热区）集合出错", e);
		}
	}
}
