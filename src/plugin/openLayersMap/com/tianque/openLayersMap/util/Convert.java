package com.tianque.openLayersMap.util;

import java.lang.reflect.InvocationTargetException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianque.openLayersMap.domian.Point;

public class Convert {

	private JSONObject correct_pts = new JSONObject();
	private JSONObject num = new JSONObject();

	public static void main(String[] args) throws Exception {
		Convert c = new Convert();

		// /经纬度转2.5维坐标
		Point p = c.GetOMap(106.28657275319, 30.351642853023);

		System.out.println(p.x);
		System.out.println(p.y);

		// / 2.5维坐标转经纬度坐标
		p = c.GetMapJw(87.53486983262678, 6.626914177345894);

		System.out.println(p.x);
		System.out.println(p.y);
	}

	public Convert() {

		JSONObject numHH = new JSONObject();
		numHH.put("num", Math.sin(Math.PI / 4));
		numHH.put("num2", Math.sin(Math.PI / 4));
		num.put("hh", numHH);

		JSONArray cHH = JSONArray
				.parseArray("[{ j: 106.287494, w: 30.345093, utm_x: 0, utm_y: 0, x: 106.287390, y: -30.344014 },"
						+ "{ j: 106.287336, w: 30.347885, utm_x: 0, utm_y: 0, x: 106.290126, y: -30.346180 },"
						+ "{ j: 106.283367, w: 30.344955, utm_x: 0, utm_y: 0, x: 106.283527, y: -30.346501 },"
						+ "{ j: 106.289577, w: 30.340953, utm_x: 0, utm_y: 0, x: 106.285087, y: -30.339693 },"
						+ "{ j: 106.291069, w: 30.345771, utm_x: 0, utm_y: 0, x: 106.291313, y: -30.342315 },"
						+ "{ j: 106.282960, w: 30.341329, utm_x: 0, utm_y: 0, x: 106.279508, y: -30.344104 },"
						+ "{ j: 106.286098, w: 30.337433, utm_x: 0, utm_y: 0, x: 106.278363, y: -30.339294 },"
						+ "{ j: 106.297964, w: 30.348508, utm_x: 0, utm_y: 0, x: 106.300306, y: -30.340049 },"
						+ "{ j: 106.295850, w: 30.342086, utm_x: 0, utm_y: 0, x: 106.291856, y: -30.336641 },"
						+ "{ j: 106.277658, w: 30.345055, utm_x: 0, utm_y: 0, x: 106.278526, y: -30.350127 },"
						+ "{ j: 106.283192, w: 30.334256, utm_x: 0, utm_y: 0, x: 106.272545, y: -30.338774 },"
						+ "{ j: 106.292784, w: 30.350689, utm_x: 0, utm_y: 0, x: 106.297851, y: -30.344866 },"
						+ "{ j: 106.291348, w: 30.353957, utm_x: 0, utm_y: 0, x: 106.299880, y: -30.348153 },"
						+ "{ j: 106.287014, w: 30.355851, utm_x: 0, utm_y: 0, x: 106.297937, y: -30.352211 },"
						+ "{ j: 106.286323, w: 30.351691, utm_x: 0, utm_y: 0, x: 106.293061, y: -30.349618 },"
						+ "{ j: 106.267141, w: 30.341900, utm_x: 0, utm_y: 0, x: 106.265862, y: -30.354336 },"
						+ "{ j: 106.274153, w: 30.344097, utm_x: 0, utm_y: 0, x: 106.274373, y: -30.351610 },"
						+ "{ j: 106.269808, w: 30.337980, utm_x: 0, utm_y: 0, x: 106.264248, y: -30.349813 },"
						+ "{ j: 106.276458, w: 30.336522, utm_x: 0, utm_y: 0, x: 106.268749, y: -30.344628 },"
						+ "{ j: 106.276873, w: 30.343291, utm_x: 0, utm_y: 0, x: 106.276009, y: -30.349323 },"
						+ "{ j: 106.281986, w: 30.349299, utm_x: 0, utm_y: 0, x: 106.286713, y: -30.350539 },"
						+ "{ j: 106.284344, w: 30.353276, utm_x: 0, utm_y: 0, x: 106.292885, y: -30.352011 },"
						+ "{ j: 106.285371, w: 30.363450, utm_x: 0, utm_y: 0, x: 106.304096, y: -30.358813 },"
						+ "{ j: 106.289469, w: 30.361729, utm_x: 0, utm_y: 0, x: 106.306047, y: -30.355024 },"
						+ "{ j: 106.291001, w: 30.355080, utm_x: 0, utm_y: 0, x: 106.300695, y: -30.349199 }"
						+ "]");

		correct_pts.put("hh", cHH);

	}

	private TPoints GetPx_Py(String city, double x, double y, CoordType typeP) {
		int[] pointsIndex = this.GetFourNearIndex(city, x, y, typeP);
		return this.Get2PairPointsX_Y(city, pointsIndex, typeP);
	}

	// 得到输入所有点中，比较合理的两对点。
	// 一对在X轴上，一对在Y轴上。
	private TPoints Get2PairPointsX_Y(String city, int[] pointsIndex,
			CoordType ct) {
		TPoint pointsX = new TPoint();
		TPoint pointsY = new TPoint();
		boolean haveX = false;
		boolean haveY = false;
		double oldInstanceX = 0;
		double oldInstanceY = 0;

		for (int i = 0; i < pointsIndex.length; i++) {
			for (int j = i + 1; j < pointsIndex.length; j++) {
				Point tempP = GetInstanceCommon2P(city, pointsIndex[i],
						pointsIndex[j], ct);
				if (tempP.x > oldInstanceX && !haveX) {
					oldInstanceX = tempP.x;
					pointsX.index0 = pointsIndex[i];
					pointsX.index1 = pointsIndex[j];
				}
				if (tempP.x > ct.minPreX && !haveX) {
					haveX = true;
				}
				if (tempP.y > oldInstanceY && !haveY) {
					oldInstanceY = tempP.y;
					pointsY.index0 = pointsIndex[i];
					pointsY.index1 = pointsIndex[j];
				}
				if (tempP.y > ct.minPreY && !haveY) {
					haveY = true;
				}
				if (haveY && haveX) {
					return new TPoints(pointsX, pointsY);
				}
			}
		}

		return new TPoints(pointsX, pointsY);
	}

	// 得到两个点在指定坐标上的距离，兼容了25D的点
	private Point GetInstanceCommon2P(String city, int index0, int index1,
			CoordType ct) {
		if ("x".equals(ct.strX) && "y".equals(ct.strY)) {
			return GetInstance2P_XY(city, index0, index1, ct);
		} else {
			return GetInstance2P(city, index0, index1, ct);
		}
	}

	// 得到25D的两个点在X轴和Y轴上的距离。
	private Point GetInstance2P_XY(String city, int index0, int index1,
			CoordType ct) {
		Point p1 = this.fromMap(city, correct_pts.getJSONArray(city)
				.getJSONObject(index0).getDoubleValue("x"), correct_pts
				.getJSONArray(city).getJSONObject(index0).getDoubleValue("y"));

		Point p2 = this.fromMap(city, correct_pts.getJSONArray(city)
				.getJSONObject(index1).getDoubleValue("x"), correct_pts
				.getJSONArray(city).getJSONObject(index1).getDoubleValue("y"));

		double x = Math.abs(p1.x - p2.x);
		double y = Math.abs(p1.y - p2.y);

		return new Point(x, y);
	}

	// 得到两个点在指定坐标上的距离。
	private Point GetInstance2P(String city, int index0, int index1,
			CoordType ct) {
		double x = Math.abs(correct_pts.getJSONArray(city)
				.getJSONObject(index0).getDoubleValue(ct.strX)
				- correct_pts.getJSONArray(city).getJSONObject(index1)
						.getDoubleValue(ct.strX));

		double y = Math.abs(correct_pts.getJSONArray(city)
				.getJSONObject(index0).getDoubleValue(ct.strY)
				- correct_pts.getJSONArray(city).getJSONObject(index1)
						.getDoubleValue(ct.strY));
		return new Point(x, y);
	}

	/**
	 * 得到需要转换坐标的类型
	 * 
	 * @param type
	 * @return
	 */
	private CoordType GetTypeP(String type) {
		CoordType coordType = new CoordType();
		if ("jw".equals(type)) {
			coordType.strX = "j";
			coordType.strY = "w";
			coordType.initValue = 180;
			coordType.minPreX = 0.00005;
			coordType.minPreY = 0.00005;
		} else if ("utm".equals(type)) {
			coordType.strX = "utm_x";
			coordType.strY = "utm_y";
			coordType.initValue = 1294723000;
			coordType.minPreX = 500;
			coordType.minPreY = 500;
		} else if ("xy".equals(type)) {
			coordType.strX = "x";
			coordType.strY = "y";
			coordType.initValue = 1000000000;
			coordType.minPreX = 500;
			coordType.minPreY = 500;
		}
		return coordType;
	}

	/**
	 * 计算坐标之间的距离。
	 * 
	 * @param x
	 * @param y
	 * @param x1
	 * @param y1
	 * @return
	 */
	private double GetDis(double x, double y, double x1, double y1) {
		return Math.abs(x - x1) + Math.abs(y - y1);
	}

	/**
	 * 从平面坐标得到地图坐标
	 * 
	 * @param city
	 * @param x
	 * @param y
	 * @return
	 */
	private Point toMap(String city, double x, double y) {
		Point p = new Point();
		double x2 = (x - y)
				* Double.parseDouble(this.num.getJSONObject(city).get("num")
						+ "");
		double y2 = (x + y)
				* Double.parseDouble(this.num.getJSONObject(city).get("num")
						+ "")
				* Double.parseDouble(this.num.getJSONObject(city).get("num2")
						+ "");
		p.x = x2;
		p.y = y2;
		return p;
	}

	// 从标准平面坐标得到地图坐标
	private Point fromMap(String city, double x, double y) {
		Point p = new Point();

		y = y
				/ Double.parseDouble(this.num.getJSONObject(city).get("num2")
						+ "");

		double x2 = (x + y)
				/ (Double.parseDouble(this.num.getJSONObject(city).get("num")
						+ "") * 2);
		double y2 = (y - x)
				/ (Double.parseDouble(this.num.getJSONObject(city).get("num")
						+ "") * 2);
		p.x = x2;
		p.y = y2;
		return p;
	}

	// 得到指定点最近的四个点
	private int[] GetFourNearIndex(String city, double x, double y,
			CoordType typeP) {

		int p1 = 0, p2 = 0, p3 = 0, p4 = 0;

		double minDis = typeP.initValue, secMinDis = typeP.initValue, thrMinDis = typeP.initValue, fouMinDis = typeP.initValue;

		for (int i = 0; i < correct_pts.getJSONArray(city).size(); i++) {
			double dis = GetDis(correct_pts.getJSONArray(city).getJSONObject(i)
					.getDoubleValue(typeP.strX), correct_pts.getJSONArray(city)
					.getJSONObject(i).getDoubleValue(typeP.strY), x, y);

			if (dis < minDis) {
				fouMinDis = thrMinDis;
				thrMinDis = secMinDis;
				secMinDis = minDis;
				minDis = dis;
				p4 = p3;
				p3 = p2;
				p2 = p1;
				p1 = i;
			} else if (dis > minDis && dis < secMinDis) {
				fouMinDis = thrMinDis;
				thrMinDis = secMinDis;
				secMinDis = dis;
				p4 = p3;
				p3 = p2;
				p2 = i;
			} else if (dis > secMinDis && dis < thrMinDis) {
				fouMinDis = thrMinDis;
				thrMinDis = dis;
				p4 = p3;
				p3 = i;
			} else if (dis > thrMinDis && dis < fouMinDis) {
				fouMinDis = dis;
				p4 = i;
			}
		}
		int[] list = { p1, p2, p3, p4 };
		return list;
	}

	// 得到小范围地图精度
	private RefPoint GetDgPix_mm_Omap(String city, int indexX0, int indexX1,
			int indexY0, int indexY1, CoordType typeP) {
		RefPoint rp = new RefPoint();

		Point px1 = this.fromMap(city, correct_pts.getJSONArray(city)
				.getJSONObject(indexX0).getDoubleValue("x"), correct_pts
				.getJSONArray(city).getJSONObject(indexX0).getDoubleValue("y"));
		Point px2 = this.fromMap(city, correct_pts.getJSONArray(city)
				.getJSONObject(indexX1).getDoubleValue("x"), correct_pts
				.getJSONArray(city).getJSONObject(indexX1).getDoubleValue("y"));
		Point py1 = this.fromMap(city, correct_pts.getJSONArray(city)
				.getJSONObject(indexY0).getDoubleValue("x"), correct_pts
				.getJSONArray(city).getJSONObject(indexY0).getDoubleValue("y"));
		Point py2 = this.fromMap(city, correct_pts.getJSONArray(city)
				.getJSONObject(indexY1).getDoubleValue("x"), correct_pts
				.getJSONArray(city).getJSONObject(indexY1).getDoubleValue("y"));

		double x1 = px1.x, x2 = px2.x;
		double y1 = py1.y, y2 = py2.y;

		double dj1 = correct_pts.getJSONArray(city).getJSONObject(indexX0)
				.getDoubleValue(typeP.strX);
		double dj2 = correct_pts.getJSONArray(city).getJSONObject(indexX1)
				.getDoubleValue(typeP.strX);
		double dw1 = correct_pts.getJSONArray(city).getJSONObject(indexY0)
				.getDoubleValue(typeP.strY);
		double dw2 = correct_pts.getJSONArray(city).getJSONObject(indexY1)
				.getDoubleValue(typeP.strY);

		double a = Math.abs((dj2 - dj1) * 100000 / (x2 - x1));
		double b = Math.abs((dw2 - dw1) * 100000 / (y2 - y1));
		rp.j = a;
		rp.w = b;
		rp.x = 100000 / a;
		rp.y = 100000 / b;
		return rp;
	}

	// 从经纬度得到地图像素值,如需将地图坐标转换成经纬则反过来算即可
	// 小范围内地图满足线性关系
	private Point GetPx_mm(String city, double utm_x, double utm_y, TPoint px,
			TPoint py, CoordType typeP) throws NumberFormatException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		RefPoint px_src = JSONObject.parseObject(correct_pts.getJSONArray(city)
				.get(px.index0).toString(), RefPoint.class);
		RefPoint gp_src = JSONObject.parseObject(correct_pts.getJSONArray(city)
				.get(px.index0).toString(), RefPoint.class);

		JSONObject jo1 = correct_pts.getJSONArray(city)
				.getJSONObject(px.index0);

		px_src.x = jo1.getDoubleValue("x");
		px_src.y = jo1.getDoubleValue("y");
		px_src.w = jo1.getDoubleValue("w");
		px_src.j = jo1.getDoubleValue("j");
		px_src.utm_x = jo1.getDoubleValue("utm_x");
		px_src.utm_y = jo1.getDoubleValue("utm_y");

		JSONObject jo2 = correct_pts.getJSONArray(city)
				.getJSONObject(px.index0);

		gp_src.x = jo2.getDoubleValue("x");
		gp_src.y = jo2.getDoubleValue("y");
		gp_src.w = jo2.getDoubleValue("w");
		gp_src.j = jo2.getDoubleValue("j");
		gp_src.utm_x = jo2.getDoubleValue("utm_x");
		gp_src.utm_y = jo2.getDoubleValue("utm_y");

		RefPoint dgPix = GetDgPix_mm_Omap(city, px.index0, px.index1,
				py.index0, py.index1, typeP);
		Point px_1 = fromMap(city, px_src.x, px_src.y);

		double dj1 = 0;// Double.valueOf(BeanUtils.getProperty(gp_src,
						// typeP.strX));
		double dw1 = 0;// Double.valueOf(BeanUtils.getProperty(gp_src,
						// typeP.strY));

		if ("j".equals(typeP.strX)) {
			dj1 = gp_src.j;
		}
		if ("w".equals(typeP.strY)) {
			dw1 = gp_src.w;
		}

		if ("utm_x".equals(typeP.strX)) {
			dj1 = gp_src.utm_x;
		}
		if ("utm_y".equals(typeP.strY)) {
			dw1 = gp_src.utm_y;
		}

		if ("x".equals(typeP.strX)) {
			dj1 = gp_src.x;
		}
		if ("y".equals(typeP.strY)) {
			dw1 = gp_src.y;
		}

		double dj = utm_x, dw = utm_y;

		double x_1 = px_1.x;
		double y_1 = px_1.y;

		double dj_s = dj - dj1, dw_s = dw - dw1;

		double x = dj_s * dgPix.x + x_1;
		double y = -dw_s * dgPix.y + y_1;

		Point p = toMap(city, x, y);

		return p;
	}

	private Point GetJw_mm(String city, double x, double y, TPoint px1,
			TPoint py1, CoordType typeP) throws Exception {
		RefPoint mappx_src = JSONObject.parseObject(correct_pts.getJSONArray(
				city).getJSONObject(px1.index0).toString(), RefPoint.class);
		RefPoint gp_src = JSONObject.parseObject(correct_pts.getJSONArray(city)
				.getJSONObject(px1.index0).toString(), RefPoint.class);

		JSONObject jo1 = correct_pts.getJSONArray(city).getJSONObject(
				px1.index0);

		mappx_src.x = jo1.getDoubleValue("x");
		mappx_src.y = jo1.getDoubleValue("y");
		mappx_src.w = jo1.getDoubleValue("w");
		mappx_src.j = jo1.getDoubleValue("j");
		mappx_src.utm_x = jo1.getDoubleValue("utm_x");
		mappx_src.utm_y = jo1.getDoubleValue("utm_y");

		JSONObject jo2 = correct_pts.getJSONArray(city).getJSONObject(
				px1.index0);

		gp_src.x = jo2.getDoubleValue("x");
		gp_src.y = jo2.getDoubleValue("y");
		gp_src.w = jo2.getDoubleValue("w");
		gp_src.j = jo2.getDoubleValue("j");
		gp_src.utm_x = jo2.getDoubleValue("utm_x");
		gp_src.utm_y = jo2.getDoubleValue("utm_y");

		RefPoint dgPix = GetDgPix_mm_Omap(city, px1.index0, px1.index1,
				py1.index0, py1.index1, typeP);

		Point px = fromMap(city, x, y);
		Point px_src = fromMap(city, mappx_src.x, mappx_src.y);

		double dj1 = 0;
		double dw1 = 0;
		if ("j".equals(typeP.strX)) {
			dj1 = gp_src.j;
		}
		if ("w".equals(typeP.strY)) {
			dw1 = gp_src.w;
		}

		if ("utm_x".equals(typeP.strX)) {
			dj1 = gp_src.utm_x;
		}
		if ("utm_y".equals(typeP.strY)) {
			dw1 = gp_src.utm_y;
		}

		if ("x".equals(typeP.strX)) {
			dj1 = gp_src.x;
		}
		if ("y".equals(typeP.strY)) {
			dw1 = gp_src.y;
		}

		double x_1 = px_src.x;
		double y_1 = px_src.y;

		double px_s = px.x - x_1, py_s = y_1 - px.y;

		double gp_j = px_s / dgPix.x + dj1;
		double gp_w = py_s / dgPix.y + dw1;

		return new Point(gp_j, gp_w);
	}

	public Point GetOMap(String city, double j, double w) throws Exception {
		CoordType typeP = this.GetTypeP("jw");
		TPoints twoIndexs = GetPx_Py(city, j, w, typeP);
		DPoint pt = GetOMap_index(city, j, w, twoIndexs.px, twoIndexs.py, typeP);
		pt.y = -pt.y;

		return new Point(pt.x, pt.y);
	}

	public Point GetOMap(double j, double w) throws Exception {
		String city = "hh";
		CoordType typeP = this.GetTypeP("jw");
		TPoints twoIndexs = GetPx_Py(city, j, w, typeP);
		DPoint pt = GetOMap_index(city, j, w, twoIndexs.px, twoIndexs.py, typeP);
		pt.y = -pt.y;

		return new Point(pt.x, pt.y);
	}

	public Point GetMapJw(String city, double x, double y) throws Exception {
		y = -y;
		CoordType typeP = GetTypeP("xy");
		TPoints twoIndexs = GetPx_Py(city, x, y, typeP);
		CoordType typeP1 = GetTypeP("jw");
		Point p = GetMapJw_index(city, x, y, twoIndexs.px, twoIndexs.py, typeP1);
		return p;
	}

	public Point GetMapJw(double x, double y) throws Exception {
		String city = "hh";
		y = -y;
		CoordType typeP = GetTypeP("xy");
		TPoints twoIndexs = GetPx_Py(city, x, y, typeP);
		CoordType typeP1 = GetTypeP("jw");
		Point p = GetMapJw_index(city, x, y, twoIndexs.px, twoIndexs.py, typeP1);
		return p;
	}

	private DPoint GetOMap_index(String city, double utm_x, double utm_y,
			TPoint px0, TPoint py0, CoordType typeP) throws Exception {
		Point xy = GetPx_mm(city, utm_x, utm_y, px0, py0, typeP);
		DPoint dp = new DPoint();

		dp.x = xy.x;
		dp.y = xy.y;
		dp.px = px0;
		dp.py = py0;

		return dp;
	}

	private Point GetMapJw_index(String city, double x, double y, TPoint px0,
			TPoint py0, CoordType typeP) throws Exception {
		Point p = this.GetJw_mm(city, x, y, px0, py0, typeP);
		return p;
	}

}

// class Point {
// double x = 0;
// double y = 0;
//
// public Point() {
// };
//
// public Point(double x, double y) {
// this.x = x;
// this.y = y;
// }
// }

/**
 * 坐标类型
 * 
 * @author gao
 * 
 */
class CoordType {
	String strX;
	String strY;
	int initValue;
	double minPreX;
	double minPreY;
}

class RefPoint {
	double j;
	double w;
	double x;
	double y;
	double utm_x;
	double utm_y;
}

class TPoints {
	TPoint px;
	TPoint py;

	public TPoints() {
	};

	public TPoints(TPoint px, TPoint py) {
		this.px = px;
		this.py = py;
	};

}

class TPoint {
	int index0;
	int index1;
}

class DPoint {
	double x;
	double y;
	TPoint px;
	TPoint py;
}

class JWPoint {
	double lng;
	double lat;
	TPoint px;
	TPoint py;
}
