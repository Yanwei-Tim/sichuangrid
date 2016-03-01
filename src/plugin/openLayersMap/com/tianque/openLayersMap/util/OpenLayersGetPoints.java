package com.tianque.openLayersMap.util;

import java.util.ArrayList;


public class OpenLayersGetPoints {

	/**
	 * 判断当前经纬度是否在 多个经纬度构造的区域范围内
	 * 
	 * @param lon
	 * @param lat
	 * @param points
	 * @return
	 */
	public static boolean isPointInPolygon(Double lon, Double lat, String points) {
		String s1 = points.substring(9);
		s1 = s1.substring(0, s1.length() - 2);
		String[] s2 = s1.split(",");
		ArrayList<Double> lonList = new ArrayList<Double>();
		ArrayList<Double> latList = new ArrayList<Double>();
		for (int i = 0; i < s2.length; i++) {
			String[] s3 = s2[i].split(" ");
			for (int j = 0; j < s3.length; j++) {
				if (j % 2 == 0) {// 判断是基数还是偶数
					lonList.add(Double.valueOf(s3[j]));
				} else {
					latList.add(Double.valueOf(s3[j]));
				}
			}
		}
		boolean isInside = false;
		double ESP = 1e-9;
		int count = 0;
		double linePoint1x;
		double linePoint1y;
		double linePoint2x = 180;
		double linePoint2y;
		linePoint1x = lon;
		linePoint1y = lat;
		linePoint2y = lat;

		for (int i = 0; i < lonList.size() - 1; i++) {
			double cx1 = lonList.get(i);
			double cy1 = latList.get(i);
			double cx2 = lonList.get(i + 1);
			double cy2 = latList.get(i + 1);
			if (isPointOnLine(lon, lat, cx1, cy1, cx2, cy2)) {
				return true;
			}
			if (Math.abs(cy2 - cy1) < ESP) {
				continue;
			}

			if (isPointOnLine(cx1, cy1, linePoint1x, linePoint1y, linePoint2x,
					linePoint2y)) {
				if (cy1 > cy2)
					count++;
			} else if (isPointOnLine(cx2, cy2, linePoint1x, linePoint1y,
					linePoint2x, linePoint2y)) {
				if (cy2 > cy1)
					count++;
			} else if (isIntersect(cx1, cy1, cx2, cy2, linePoint1x,
					linePoint1y, linePoint2x, linePoint2y)) {
				count++;
			}
		}
		if (count % 2 == 1) {
			isInside = true;
		}

		return isInside;
	}

	private static boolean isPointOnLine(double px0, double py0, double px1,
			double py1, double px2, double py2) {
		boolean flag = false;
		double ESP = 1e-9;
		if ((Math.abs(Multiply(px0, py0, px1, py1, px2, py2)) < ESP)
				&& ((px0 - px1) * (px0 - px2) <= 0)
				&& ((py0 - py1) * (py0 - py2) <= 0)) {
			flag = true;
		}
		return flag;
	}

	private static double Multiply(double px0, double py0, double px1,
			double py1, double px2, double py2) {
		return ((px1 - px0) * (py2 - py0) - (px2 - px0) * (py1 - py0));
	}

	private static boolean isIntersect(double px1, double py1, double px2,
			double py2, double px3, double py3, double px4, double py4) {
		boolean flag = false;
		double d = (px2 - px1) * (py4 - py3) - (py2 - py1) * (px4 - px3);
		if (d != 0) {
			double r = ((py1 - py3) * (px4 - px3) - (px1 - px3) * (py4 - py3))
					/ d;
			double s = ((py1 - py3) * (px2 - px1) - (px1 - px3) * (py2 - py1))
					/ d;
			if ((r >= 0) && (r <= 1) && (s >= 0) && (s <= 1)) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 得到经纬度最大和最小点的坐标
	 * 
	 * @param regularPolygonPoints
	 * @return
	 */
	public static Double[] getMaxAndMinLonLat(String regularPolygonPoints) {
		if(regularPolygonPoints==null || regularPolygonPoints.trim().equals("")) return new Double[4];
		String s1 = regularPolygonPoints.substring(9);
		s1 = s1.substring(0, s1.length() - 2);
		String[] s2 = s1.split(",");
		ArrayList<Double> lon = new ArrayList<Double>();
		ArrayList<Double> lat = new ArrayList<Double>();
		for (int i = 0; i < s2.length; i++) {
			String[] s3 = s2[i].split(" ");
			for (int j = 0; j < s3.length; j++) {
				if (j % 2 == 0) {// 判断是基数还是偶数
					lon.add(Double.valueOf(s3[j]));
				} else {
					lat.add(Double.valueOf(s3[j]));
				}

			}
		}
		double maxLon = 0;
		double minLon = lon.get(0);
		double maxLat = 0;
		double minLat = lat.get(0);
		for (int i = 0; i < lon.size(); i++) {
			if (lon.get(i) > maxLon) {
				maxLon = lon.get(i);
			}
			if (lon.get(i) < minLon) {
				minLon = lon.get(i);
			}
		}
		for (int i = 0; i < lat.size(); i++) {
			if (lat.get(i) > maxLat) {
				maxLat = lat.get(i);
			}
			if (lat.get(i) < minLat) {
				minLat = lat.get(i);
			}
		}
		Double[] maxAndMinLonLat = { maxLon, maxLat, minLon, minLat };
		return maxAndMinLonLat;
	}
	
	/**
	 * 得到中心点坐标
	 * 
	 * @param regularPolygonPoints
	 * @return
	 */
	public static Double[] getCenterLonLat(String points) {
		if(points==null) return null;
		String[] s2 = points.split(" ");
		Double lon = 0D;
		Double lat = 0D;
		for (int i = 0; i < s2.length; i++) {
			String[] s3 = s2[i].split(",");
			for (int j = 0; j < s3.length; j++) {
				if (j % 2 == 0) {// 判断是基数还是偶数
					lon += Double.valueOf(s3[j]);
				} else {
					lat += Double.valueOf(s3[j]);
				}

			}
		}
		if(lon==0D || lat==0D) return null;
		Double[] centerLonLat = { lon/s2.length,lat/s2.length };
		return centerLonLat;
	}

	public static void main(String[] args) {
		double px = 27.416408762207;
		double py = 37.406701640624;
		long start = System.nanoTime();
		for (int i = 0; i < 100000; i++) {
			String points = "POLYGON((97.329719766846 37.405500010986,"
					+ "97.416408762207 37.406701640624,"
					+ "97.416580423584 37.327737407226,"
					+ "97.416408762207 37.317781047363,"
					+ "97.332809671631 37.317781047363,"
					+ "97.32268165039 37.317094401855,"
					+ "97.321823343506 37.371682719726,"
					+ "97.324913248291 37.396745280761,"
					+ "97.329719766846 37.405500010986))";
			isPointInPolygon(px, py, points);
			px++;
			py++;
		}
		long end = System.nanoTime();
		long aa = end - start;
		System.out.println("执行时间：" + aa);

	}
}
