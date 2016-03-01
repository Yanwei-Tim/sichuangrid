package com.tianque.openLayersMap.domian;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Polygon extends ArrayList<Point> {
	private final static String POLYGON_HEAD="POLYGON";
	private final static double INFINITY = -1e10;
	private final static double ESP = 1e-10;
	
	public Polygon(){
		super();
	}
	
	public Polygon(String polygonStr){
		super();
		if (polygonStr != null && !"".equals(polygonStr)) {
			String temp = polygonStr.substring(polygonStr.lastIndexOf("((") + 2,
					polygonStr.indexOf("))"));
			String[] temps = temp.split(",");
			if (temps != null) {
				for (int i = 0; i < temps.length; i++) {
					String[] point = temps[i].trim().split(" ");
					this.add(new Point(point[0], point[1]));
				}
			}
		}
	}
	
	public Polygon(Point ...points){
		super();
		if(points!=null){
			for (int i = 0; i < points.length; i++) {
				this.add(points[i]);
			}
		}
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		Point point = null;
		for (int i = 0; i < this.size(); i++) {
			point = this.get(i);
			if (point != null) {
				sb.append(sb.length()>0?",":"");
				sb.append(point.x + " " + point.y);
			}
		}
		return POLYGON_HEAD+"((" + sb.toString() + "))";
	}
	
	public Point[] toArray() {
		Point[] result = new Point[this.size()];
		for (int i = 0; i < this.size(); i++) {
			result[i] = this.get(i);
		}
		return result;
    }
	
	/**polygon区域在this区域里面
	 * 线：特殊的polygon，size==2
	 * */
	public boolean wrapPolygon(Polygon polygon){
		if(polygon.size()==2){
			return wrapLine(polygon);
		}
		Polygon line = null;
		for (int i = 0; i < polygon.size(); i++) {
			line = new Polygon(polygon.get(i), polygon.get((i + 1) % polygon.size()));
			if (!this.wrapLine(line)) {
				return false;
			}
		}
		return true;
		
	}
	/**point点在this区域里面
	 * 线：特殊的区域，this.size()==2，点在线段上
	 */
	public boolean wrapPoint(Point p){
		if(this.size()==2){
			Point p1 = this.get(0);
			Point p2 = this.get(1);
			return Math.abs(multiplyCross(p1, p2, p)) < ESP
					&& ((p.x - p1.x) * (p.x - p2.x) <= 0) 
					&& ((p.y - p1.y) * (p.y - p2.y) <= 0);
		}else{
			int count = 0;
			Polygon line = new Polygon(p, new Point(INFINITY, p.y) );
			for (int i = 0; i < this.size(); i++) { // 得到多边形的一条边
				Polygon side = new Polygon(this.get(i), this.get((i + 1) % this.size()));
				if (side.wrapPoint(p)) {
					count = 1;
					break;
				} 
				// 如果side平行x轴则不作考虑
				if (Math.abs(side.get(0).y - side.get(1).y) < ESP) {
					continue;
				}
				if ((line.wrapPoint(side.get(0)) && side.get(0).y > side.get(1).y )
						|| (line.wrapPoint(side.get(1)) && side.get(1).y > side.get(0).y)
						|| line.lineCross(side) ) {
						count++;
				}
			}
			// 如果点在多边形内： 返回0，点在多边形边上：返回1，点在多边形外： 返回2
			return (count % 2 != 0 || count==1);
		}
		
	}
	/**
	 * line线在this区域里面
	 */
	public boolean wrapLine(Polygon line){
		if (this.size()<2 || line == null || line.size() != 2
				|| !this.wrapPoint(line.get(0)) ) {
			return false;
		}
		Polygon line2 = null;
		for (int i = 0; i < this.size(); i++) {
			line2 = new Polygon(this.get(i), this.get((i + 1) % this.size()) );
			if (line.lineCross(line2) 
					&& !line2.wrapPoint(line.get(0)) && !line2.wrapPoint(line.get(1))
					&& !line.wrapPoint(line2.get(0)) && !line.wrapPoint(line2.get(1))) {
				return false;
			}
		}
		return true;
		
	}
	
	/** 线段相交于一点
	 *依据： y = kx + b
	 */
	public Point[] lineOverlap(Polygon line) {
		if (line == null || line.size() != 2 || this.size() != 2) {
			return null;
		}
		Point p1 = this.get(0),p2 = this.get(1);
		Point p3 = line.get(0),p4 =line.get(1);
		Point p = null;
		if(p1.x==p2.x && p3.x==p4.x){//两条线重叠
			if(p1.equals(p3) && p2.equals(p4)){
				return line.toArray();
			}
		}else if (p1.x == p2.x) {
			BigDecimal k = new BigDecimal(p3.y - p4.y).divide(new BigDecimal(
					p3.x - p4.x), RoundingMode.HALF_UP);
			BigDecimal y = new BigDecimal(p1.x - p3.x).multiply(k).add(
					new BigDecimal(p3.y));
			p = new Point(p1.x, y.doubleValue());
		} else if (p3.x == p4.x) {
			BigDecimal k = new BigDecimal(p1.y - p2.y).divide(new BigDecimal(
					p1.x - p2.x), RoundingMode.HALF_UP);
			BigDecimal y = new BigDecimal(p3.x - p1.x).multiply(k).add(
					new BigDecimal(p1.y));
			p = new Point(p3.x, y.doubleValue());
		} else {// 假设 两条线相交于一点 (x,y)
			BigDecimal k1 = new BigDecimal(p1.y - p2.y).divide(new BigDecimal(
					p1.x - p2.x), RoundingMode.HALF_UP);
			BigDecimal b1 = new BigDecimal(p1.y).subtract(k1
					.multiply(new BigDecimal(p1.x)));
			BigDecimal k2 = new BigDecimal(p3.y - p4.y).divide(new BigDecimal(
					p3.x - p4.x), RoundingMode.HALF_UP);
			BigDecimal b2 = new BigDecimal(p3.y).subtract(k2
					.multiply(new BigDecimal(p3.x)));
			if (Math.abs(k1.subtract(k2).doubleValue()) >= ESP) {
				BigDecimal x = b2.subtract(b1).divide(k1.subtract(k2),
						RoundingMode.HALF_UP);
				BigDecimal y = k1.multiply(x).add(b1);
				p = new Point(x.doubleValue(), y.doubleValue());
			}
		}
		if (p != null && this.wrapPoint(p) && line.wrapPoint(p)) {
			return new Point[]{p};
		}
		return null;
	}
	
	/** 判断线段相交 */
	public boolean lineCross(Polygon line) {
		Point p1 = this.get(0),p2 = this.get(1);
		Point p3 = line.get(0),p4 = line.get(1);
		return ((Math.max(p1.x, p2.x) >= Math.min(p3.x, p4.x))
				&& (Math.max(p3.x, p4.x) >= Math.min(p1.x, p2.x))
				&& (Math.max(p1.y, p2.y) >= Math.min(p3.y, p4.y))
				&& (Math.max(p3.y, p4.y) >= Math.min(p1.y, p2.y))
				&& (multiplyCross(p3, p2, p1) * multiplyCross(p2, p4, p1) >= 0) && (multiplyCross(
				p1, p4, p3) * multiplyCross(p4, p2, p3) >= 0));
	}
	
	/** 计算叉乘 |P0P1| × |P0P2| 
	 * 若>0，则向量|p0p2|在|p0p1|的左边
	 * 若<0，则向量|p0p2|在|p0p1|的右边
	 * 若=0，则说明p0、p1和p2共线
	 * */
	private double multiplyCross(Point p1, Point p2, Point p0) {
		return ((p1.x - p0.x) * (p2.y - p0.y) - (p2.x - p0.x) * (p1.y - p0.y));
	}
}
