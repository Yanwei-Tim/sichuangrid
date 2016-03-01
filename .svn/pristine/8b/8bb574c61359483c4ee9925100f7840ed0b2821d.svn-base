package com.tianque.openLayersMap.domian;

public class Point {
	protected final static double R = 6371229; // 地球的半径

	/** 坐标：经度 */
	private double lon;
	public double x;
	/** 坐标：纬度 */
	private double lat;
	public double y;
	/** 是否是线段相交的点 */
	private boolean isOverlap = false;

	public Point() {
	}

	public Point(Object lon, Object lat) {
		setLon((lon != null && !("" + lon).trim().equals("")) ? Double
				.valueOf(lon + "") : 0);
		setLat((lat != null && !("" + lat).trim().equals("")) ? Double
				.valueOf(lat + "") : 0);
	}

	public void setX(double x) {
		setLon(x);
	}

	public void setY(double y) {
		setLat(y);
	}

	public void setLon(double lon) {
		this.lon = lon;
		this.x = lon;
	}

	public void setLat(double lat) {
		this.lat = lat;
		this.y = lat;
	}

	public double getLon() {
		return this.lon;
	}

	public double getLat() {
		return this.lat;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean isOverlap() {
		return isOverlap;
	}

	public void setOverlap(boolean isOverlap) {
		this.isOverlap = isOverlap;
	}

	public double distanceTo(Point p) {
		double x = (p.lon - this.lon) * Math.PI * R
				* Math.cos(((this.lat + p.lat) / 2) * Math.PI / 180) / 180;
		double y = (p.lat - this.lat) * Math.PI * R / 180;
		return Math.hypot(x, y);
	}

	public boolean equals(Object o) {
		if (o instanceof Point) {
			Point p = (Point) o;
			if (p != null && this.lon == p.lon && this.lat == p.lat) {
				return true;
			}
			if (Math.abs(this.x - p.x) < 0.000000000001d
					&& Math.abs(this.y - p.y) < 0.0000000000001d) {
				return true;
			}
		}
		return false;
	}
}
