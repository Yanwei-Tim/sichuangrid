package com.tianque.gis.util;

import java.util.ArrayList;

public class BaiduMapUtil {
	public static final double PI = 3.14159265358979323; // 圆周率
	public static final double R = 6371229; // 地球的半径

	public static final double LATVARIABLE1 = 0.00000179571687757462;
	public static final double LATVARIABLE2 = -0.0000028182741964664;
	public static final double LATINTERCEPT = 30.3040405514275;

	public static final double LNGINTERCEPT = 120.040572844531;
	public static final double LNGVARIABLE1 = 0.00000199062166399106;
	public static final double LNGVARIABLE2 = 0.00000309386146077566;

	// /<summary>地图坐标转化成经纬度坐标</summary>
	public static Double[] ePoint2ELatLng(double px, double py) {

		double x = LATVARIABLE1 * px + LATVARIABLE2 * py + LATINTERCEPT;
		double y = LNGVARIABLE1 * px + LNGVARIABLE2 * py + LNGINTERCEPT;

		return new Double[] { x, y };
	}

	public static double getDistance(double longt1, double lat1, double longt2, double lat2) {
		double x, y, distance;
		x = (longt2 - longt1) * PI * R * Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
		y = (lat2 - lat1) * PI * R / 180;
		distance = Math.hypot(x, y);
		return distance;
	}

	public static Double[] getAround(double lon, double lat, int raidus) {

		Double latitude = lat;
		Double longitude = lon;

		Double degree = (24901 * 1609) / 360.0;
		double raidusMile = raidus;

		Double dpmLat = 1 / degree;
		Double radiusLat = dpmLat * raidusMile;
		Double minLat = latitude - radiusLat;
		Double maxLat = latitude + radiusLat;

		Double mpdLng = degree * Math.cos(latitude * (PI / 180));
		Double dpmLng = 1 / mpdLng;
		Double radiusLng = dpmLng * raidusMile;
		Double minLng = longitude - radiusLng;
		Double maxLng = longitude + radiusLng;
		return new Double[] { minLng, minLat, maxLng, maxLat };

	}

	/**
	 * 根据当前的经纬度判断 是否在一个list的经纬度数组导致的区域内
	 * 
	 * @param px
	 * @param py
	 * @param polygonXApolygonYA
	 * @return
	 */
	public static boolean isPointInPolygonByLonLatList(double px, double py,
			ArrayList<Double[]> polygonXApolygonYA) {
		boolean isInside = false;
		double ESP = 1e-9;
		int count = 0;
		double linePoint1x;
		double linePoint1y;
		double linePoint2x = 180;
		double linePoint2y;

		linePoint1x = px;
		linePoint1y = py;
		linePoint2y = py;

		ArrayList<Double> polygonXA = new ArrayList<Double>();
		ArrayList<Double> polygonYA = new ArrayList<Double>();

		for (int i = 0; i < polygonXApolygonYA.size(); i++) {
			polygonXA.add(polygonXApolygonYA.get(i)[0]);
			polygonYA.add(polygonXApolygonYA.get(i)[1]);
		}

		for (int i = 0; i < polygonXA.size() - 1; i++) {
			double cx1 = polygonXA.get(i);
			double cy1 = polygonYA.get(i);
			double cx2 = polygonXA.get(i + 1);
			double cy2 = polygonYA.get(i + 1);
			if (isPointOnLine(px, py, cx1, cy1, cx2, cy2)) {
				return true;
			}
			if (Math.abs(cy2 - cy1) < ESP) {
				continue;
			}

			if (isPointOnLine(cx1, cy1, linePoint1x, linePoint1y, linePoint2x, linePoint2y)) {
				if (cy1 > cy2)
					count++;
			} else if (isPointOnLine(cx2, cy2, linePoint1x, linePoint1y, linePoint2x, linePoint2y)) {
				if (cy2 > cy1)
					count++;
			} else if (isIntersect(cx1, cy1, cx2, cy2, linePoint1x, linePoint1y, linePoint2x,
					linePoint2y)) {
				count++;
			}
		}
		if (count % 2 == 1) {
			isInside = true;
		}

		return isInside;
	}

	/**
	 * 判断当前经纬度是否在 多个经纬度构造的区域范围内
	 * 
	 * @param px
	 * @param py
	 * @param polygonXA
	 * @param polygonYA
	 * @return
	 */
	public static boolean isPointInPolygon(double px, double py, ArrayList<Double> polygonXA,
			ArrayList<Double> polygonYA) {
		boolean isInside = false;
		double ESP = 1e-9;
		int count = 0;
		double linePoint1x;
		double linePoint1y;
		double linePoint2x = 180;
		double linePoint2y;

		linePoint1x = px;
		linePoint1y = py;
		linePoint2y = py;

		for (int i = 0; i < polygonXA.size() - 1; i++) {
			double cx1 = polygonXA.get(i);
			double cy1 = polygonYA.get(i);
			double cx2 = polygonXA.get(i + 1);
			double cy2 = polygonYA.get(i + 1);
			if (isPointOnLine(px, py, cx1, cy1, cx2, cy2)) {
				return true;
			}
			if (Math.abs(cy2 - cy1) < ESP) {
				continue;
			}

			if (isPointOnLine(cx1, cy1, linePoint1x, linePoint1y, linePoint2x, linePoint2y)) {
				if (cy1 > cy2)
					count++;
			} else if (isPointOnLine(cx2, cy2, linePoint1x, linePoint1y, linePoint2x, linePoint2y)) {
				if (cy2 > cy1)
					count++;
			} else if (isIntersect(cx1, cy1, cx2, cy2, linePoint1x, linePoint1y, linePoint2x,
					linePoint2y)) {
				count++;
			}
		}
		if (count % 2 == 1) {
			isInside = true;
		}

		return isInside;
	}

	private static double Multiply(double px0, double py0, double px1, double py1, double px2,
			double py2) {
		return ((px1 - px0) * (py2 - py0) - (px2 - px0) * (py1 - py0));
	}

	private static boolean isPointOnLine(double px0, double py0, double px1, double py1,
			double px2, double py2) {
		boolean flag = false;
		double ESP = 1e-9;
		if ((Math.abs(Multiply(px0, py0, px1, py1, px2, py2)) < ESP)
				&& ((px0 - px1) * (px0 - px2) <= 0) && ((py0 - py1) * (py0 - py2) <= 0)) {
			flag = true;
		}
		return flag;
	}

	private static boolean isIntersect(double px1, double py1, double px2, double py2, double px3,
			double py3, double px4, double py4) {
		boolean flag = false;
		double d = (px2 - px1) * (py4 - py3) - (py2 - py1) * (px4 - px3);
		if (d != 0) {
			double r = ((py1 - py3) * (px4 - px3) - (px1 - px3) * (py4 - py3)) / d;
			double s = ((py1 - py3) * (px2 - px1) - (px1 - px3) * (py2 - py1)) / d;
			if ((r >= 0) && (r <= 1) && (s >= 0) && (s <= 1)) {
				flag = true;
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		Double[] aaa = ePoint2ELatLng(19614, 15203);
		System.out.print(aaa[0] + "         ");
		System.out.println(aaa[1]);
		// System.out.println();

		Double[] ccc = ePoint2ELatLng(19554, 15202.96);
		System.out.print(ccc[0] + "         ");
		System.out.println(ccc[1]);
		// System.out.println();

		System.out.println(getDistance(aaa[0], aaa[1], ccc[0], ccc[1]));
		// System.out.println();

		// System.out.println(getDistance(19554,15220,19614,15203));
		// System.out.println();

		Double[] bbb = getAround(aaa[0], aaa[1], 1000);
		System.out.println("获得某距离范围的最大经纬度范围(最小经度)：" + bbb[0]);
		System.out.println("获得某距离范围的最大经纬度范围(最小纬度)：" + bbb[1]);
		System.out.println("获得某距离范围的最大经纬度范围(最大经度)：" + bbb[2]);
		System.out.println("获得某距离范围的最大经纬度范围(最大纬度)：" + bbb[3]);

		System.out.println();

		System.out.println(ccc[0] >= bbb[0]);
		System.out.println(ccc[0] <= bbb[2]);
		System.out.println(ccc[1] >= bbb[1]);
		System.out.println(ccc[1] <= bbb[3]);

		System.out.println(getDistance(aaa[0], aaa[1], bbb[0], bbb[1]));
		System.out.println();

		// bbb = getAround(19614,15203, 1000);
		// System.out.println("获得某距离范围的最大经纬度范围(最小经度)：" + bbb[0]);
		// System.out.println("获得某距离范围的最大经纬度范围(最小纬度)：" + bbb[1]);
		// System.out.println("获得某距离范围的最大经纬度范围(最大经度)：" + bbb[2]);
		// System.out.println("获得某距离范围的最大经纬度范围(最大纬度)：" + bbb[3]);

	}

}
