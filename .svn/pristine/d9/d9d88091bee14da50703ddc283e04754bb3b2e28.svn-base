package com.tianque.openLayersMap.util;

import com.tianque.baseInfo.buildDatas.domain.OpenLayersMapInfo;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.Point;
import com.tianque.openLayersMap.domian.vo.GisBoundVo;
import com.tianque.openLayersMap.service.Gis2DLayerService;
import com.tianque.openLayersMap.service.convert.GisConvert;

@SuppressWarnings("unused")
public class GisTransformatUtil {

	private static GisConvert getGisConvert() {
		Object bean = null;
		try {
			bean = SpringBeanUtil
					.getBeanFromSpringByBeanName(GisProperties.MAPTYPE
							+ "GisConvert");
		} catch (Exception e) {
			bean = SpringBeanUtil
					.getBeanFromSpringByBeanName("commonGisConvert");
		}
		return (GisConvert) bean;
	}

	/** 获取25维面积 */
	public static Double to25DArea(String polygon) {
		return getGisConvert().to25DArea(polygon);
	}

	/** 获取25维长度 */
	public static Double to25DLength(String line) {
		return getGisConvert().to25DLength(line);
	}

	/** 获取25维点坐标 */
	public static Point to25DPoint(Point point) {
		return getGisConvert().to25DPoint(point);
	}

	/** 获取2维点坐标 */
	public static Point to2DPoint(Point point) {
		return getGisConvert().to2DPoint(point);
	}

	/** 获取25维点（热区）集合 */
	public static String to25DPolygon(String polygon) {
		return getGisConvert().to25DPolygon(polygon);
	}

	/** 获取2维点（热区）集合 */
	public static String to2DPolygon(String polygon) {
		return getGisConvert().to2DPolygon(polygon);
	}

	/** 获取2维点坐标 */
	public static String[] to2DPoint(String lon, String lat) {
		Point point = to2DPoint(new Point(lon, lat));
		if (point != null && (point.getX() > 0 || point.getY() > 0)) {
			return new String[] { String.valueOf(point.x),
					String.valueOf(point.y) };
		} else {
			return new String[] { "", "" };
		}
	}

	/** 获取25维点坐标 */
	public static String[] to25DPoint(String lon, String lat) {
		Point point = to25DPoint(new Point(lon, lat));
		if (point != null && (point.getX() > 0 || point.getY() > 0)) {
			return new String[] { String.valueOf(point.x),
					String.valueOf(point.y) };
		} else {
			return new String[] { "", "" };
		}
	}

	public static void autoFillGis2DLayerBy2D(Gis2DLayer domain) {
		try {
			if (domain == null)
				return;
			String[] lonlat = new String[] { domain.getCenterX(),
					domain.getCenterY() };
			String points2 = domain.getPoints();
			if (domain.getIsTransformat()) {
				lonlat = to2DPoint(domain.getCenterX(), domain.getCenterY());
				points2 = to2DPolygon(domain.getPoints());
			}
			domain.setCenterX2(lonlat[0]);
			domain.setCenterY2(lonlat[1]);
			domain.setPoints2(points2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void autoFillGis2DLayerBy3D(Gis2DLayer domain) {
		try {
			if (domain == null) {
				return;
			}
			String[] lonlat = new String[] { domain.getCenterX2(),
					domain.getCenterY2() };
			String points = domain.getPoints2();
			if (domain.getIsTransformat()) {
				lonlat = to25DPoint(domain.getCenterX2(), domain.getCenterY2());
				points = to25DPolygon(domain.getPoints2());
			}
			domain.setCenterX(lonlat[0]);
			domain.setCenterY(lonlat[1]);
			domain.setPoints(points);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void autoFillOpenLayersMapInfo(OpenLayersMapInfo domain) {
		try {
			if (domain != null) {
				if (domain.getCenterLon() != null
						&& domain.getCenterLon() != "") {
					if (GisType.M3D.equals(domain.getGisType())) {// 三维坐标转二维
						String[] lonlat2 = new String[] {
								domain.getCenterLon(), domain.getCenterLat() };
						if (domain.getIsTransformat()) {
							lonlat2 = GisTransformatUtil.to2DPoint(
									domain.getCenterLon(),
									domain.getCenterLat());
						}
						domain.setCenterLon2(lonlat2[0]);
						domain.setCenterLat2(lonlat2[1]);
					} else {
						String[] lonlat = new String[] { domain.getCenterLon(),
								domain.getCenterLat() };
						if (domain.getIsTransformat()) {
							lonlat = GisTransformatUtil.to25DPoint(
									domain.getCenterLon(),
									domain.getCenterLat());
						}
						domain.setCenterLon2(domain.getCenterLon());
						domain.setCenterLat2(domain.getCenterLat());
						domain.setCenterLon(lonlat[0]);
						domain.setCenterLat(lonlat[1]);
					}
				} else if (domain.getCenterLon2() != null
						&& domain.getCenterLon2() != "") {
					String[] lonlat = new String[] { domain.getCenterLon2(),
							domain.getCenterLat2() };
					if (domain.getIsTransformat()) {
						lonlat = GisTransformatUtil.to25DPoint(
								domain.getCenterLon2(), domain.getCenterLat2());
					}
					domain.setCenterLon(lonlat[0]);
					domain.setCenterLat(lonlat[1]);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void autoFillGisBound(GisBoundVo domain) {
		try {
			if (domain != null) {
				if (GisType.M3D.equals(domain.getGisType())) {// 三维坐标转二维
					String[] lonlat = new String[] { domain.getLon(),
							domain.getLat() };
					if (domain.getIsTransformat()) {
						lonlat = to2DPoint(domain.getLon(), domain.getLat());
					}
					domain.setLon2(lonlat[0]);
					domain.setLat2(lonlat[1]);
				} else { // 二维坐标转三维
					String[] lonlat = new String[] { domain.getLon(),
							domain.getLat() };
					if (domain.getIsTransformat()) {
						lonlat = to25DPoint(domain.getLon(), domain.getLat());
					}
					domain.setLon2(domain.getLon());
					domain.setLat2(domain.getLat());
					domain.setLon(lonlat[0]);
					domain.setLat(lonlat[1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String[] transformatPoint(String lon, String lat,
			String gisType, Gis2DLayerService gis2dLayerService) {
		try {
			String transformatDepartmentNo = GisProperties.TRANSFORMAT_DEPARTMENTNO;
			if (gis2dLayerService != null && transformatDepartmentNo != null) {
				Gis2DLayer gis2dLayer;
				for (String orgDepartNo : transformatDepartmentNo.split(",")) {
					gis2dLayer = gis2dLayerService.getByOrgDepartmentNo(
							orgDepartNo.trim(), gisType);
					if (gis2dLayer != null
							&& GisType.M3D.equals(gisType)
							&& OpenLayersGetPoints.isPointInPolygon(
									Double.valueOf(lon), Double.valueOf(lat),
									gis2dLayer.getPoints())) {
						String[] lonlat = to2DPoint(lon, lat);
						return new String[] { lon, lat, lonlat[0], lonlat[1] };
					} else if (gis2dLayer != null
							&& OpenLayersGetPoints.isPointInPolygon(
									Double.valueOf(lon), Double.valueOf(lat),
									gis2dLayer.getPoints2())) {
						String[] lonlat = to25DPoint(lon, lat);
						return new String[] { lonlat[0], lonlat[1], lon, lat };
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[] { lon, lat, lon, lat };
	}

	// transformat 25D Point to 2D point
	public static String[] transformat2DPoint(String lon, String lat) {

		// 同一球面坐标系使用的转换公式
		// Double scale = 1.3;
		// Double yscale = 0.91;
		// Double flagx = 106.285828;
		// Double flagy = 30.345027;
		// Double x0 = Double.parseDouble(lon) - 0 - flagx;
		// Double y0 = Double.parseDouble(lat) - 0 - flagy;
		// x0 = x0 / scale;
		// y0 = y0 / yscale;
		//
		// Double angle = -1 * Math.PI / 4;
		// Double rx = Math.cos(angle) * x0 + Math.sin(angle) * y0;
		// Double ry = Math.cos(angle) * y0 - Math.sin(angle) * x0;
		//
		// ry = ry / 1.13;
		// rx = rx + flagx;
		// ry = ry + flagy;
		// return new String[] { rx.toString(), ry.toString() };

		// 不同坐标系使用的转换公式
		Convert c = new Convert();
		Point point = null;
		try {
			point = c
					.GetMapJw(Double.parseDouble(lon), Double.parseDouble(lat));

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (point != null)
			return new String[] { String.valueOf(point.x),
					String.valueOf(point.y) };
		else
			return new String[] { "", "" };

	}

	// transformat 2D Point to 25D point
	public static String[] transformat25DPoint(String lon, String lat) {
		// 同一球面坐标系使用的转换公式
		// Double scale = 1.3;
		// Double yscale = 0.91;
		// Double flagx = 106.285828;
		// Double flagy = 30.345027;
		// Double x0 = Double.parseDouble(lon) - flagx;
		// Double y0 = Double.parseDouble(lat) - flagy;
		// y0 = y0 * 1.13;
		// Double angle = -1 * Math.PI / 4;
		// Double rx = Math.cos(angle) * x0 - Math.sin(angle) * y0;
		// Double ry = Math.cos(angle) * y0 + Math.sin(angle) * x0;
		// rx = rx * scale;
		// ry = ry * yscale;
		// rx = rx + flagx;
		// ry = ry + flagy;
		// return new String[] { rx.toString(), ry.toString() };

		// 不同坐标系使用的转换公式
		Convert c = new Convert();
		Point point = null;
		try {
			point = c.GetOMap(Double.parseDouble(lon), Double.parseDouble(lat));

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (point != null)
			return new String[] { String.valueOf(point.x),
					String.valueOf(point.y) };
		else
			return new String[] { "", "" };
	}

	public static void main(String[] args) throws Exception {
		// transformat2DPoint("106.15239869679","38.501186685159");
		String value = "POLYGON((106.12780149902 38.505214021995,106.12949665512 38.498454855277,106.134281716 38.499184416129,106.134281716 38.503475950553,106.12780149902 38.505214021995))";
		// System.out.println(geometryStrToArrayStr(value));
		// String value2 = geometryStrToArrayStr(value);
		// System.out.println(arrayStrToGeometryStr(value2));
		// System.out.println(transformat2DPolygon(value));
	}
}
