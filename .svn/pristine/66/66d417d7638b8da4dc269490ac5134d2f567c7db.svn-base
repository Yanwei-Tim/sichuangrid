package com.tianque.openLayersMap.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.exception.base.SystemUtilException;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.Point;
import com.tianque.openLayersMap.domian.Polygon;
import com.tianque.openLayersMap.service.Gis2DLayerService;

public class GisUtil {
	public static Polygon prevPolygon = new Polygon();
	private final static String SET_MODE = "set";
	private final static String GET_MODE = "get";
	private final static String DEL_MODE = "remove";

	/** 依据两个面集合ps,ps1，重新构造成一个面集合pg */
	public static Polygon intersectPolygon(Polygon polygon1, Polygon polygon2) {
		try {
			Polygon pg = new Polygon();
			Point[] ps1 = polygonIntersect(polygon1, polygon2);
			Point[] ps = polygonIntersect(polygon2, polygon1, ps1);
			List<Point[]> psOrder = orderByPolygon(ps1, ps);
			ps1 = psOrder.get(0);
			ps = psOrder.get(1);
			// 如果有一个面是在另一个面内，直接返回面内的点集合
			if (ps == null || ps.length == 0)
				return new Polygon(ps1);
			if (ps1 == null || ps1.length == 0)
				return new Polygon(ps);
			int psLen = ps.length;
			int ps1Len = ps1.length;
			if (ps[0].equals(ps[ps.length - 1]))
				psLen--;
			if (ps1[0].equals(ps1[ps1.length - 1]))
				ps1Len--;
			for (int i = 0; i < psLen; i++) {
				Point ps_1 = ps[i];
				Point ps_2 = ps[(i + 1) % ps.length];
				pg.add(ps_1);
				if (isContainPoints(ps1, new Point[] { ps_1, ps_2 })) {
					int n_1 = -1, n_2 = -1, n = -1;
					Polygon arr_1 = new Polygon();
					Polygon arr_2 = new Polygon();
					for (int j = 0; j < ps1Len; j++) {
						n_1 = ps_1.equals(ps1[j]) ? j : n_1;
						n_2 = ps_2.equals(ps1[j]) ? j : n_2;
						if (n_1 > -1 && n_2 > -1)
							break;
					}
					if (n_1 > n_2) {
						n = n_2;
						n_2 = n_1;
						n_1 = n;
					}
					if (ps_1.equals(ps1[n_1])) {
						for (int k = n_1 + 1; k < n_2; k++) {
							if (isContainPoints(ps, new Point[] { ps1[k] })) {
								arr_1 = null;
								break;
							}
							arr_1.add(ps1[k]);
						}
						for (int k = n_1 - 1; k > (n_2 - ps1.length); k--) {
							n = (k + ps1.length) % ps1.length;
							if (isContainPoints(ps, new Point[] { ps1[n] })) {
								arr_2 = null;
								break;
							}
							arr_2.add(ps1[n]);
						}
					} else if (ps_2.equals(ps1[n_1])) {
						for (int k = n_2 + 1; k < n_1 + ps1.length; k++) {
							n = k % ps1.length;
							if (isContainPoints(ps, new Point[] { ps1[n] })) {
								arr_1 = null;
								break;
							}
							arr_1.add(ps1[n]);
						}
						for (int k = n_2 - 1; k > n_1; k--) {
							if (isContainPoints(ps, new Point[] { ps1[k] })) {
								arr_2 = null;
								break;
							}
							arr_2.add(ps1[k]);
						}
					}
					if (arr_1 != null && !pg.containsAll(arr_1)) {
						pg.addAll(arr_1);
					}
					if (arr_2 != null && !pg.containsAll(arr_2)) {
						pg.addAll(arr_2);
					}
				}
				if (i == ps.length - 1) {
					pg.add(ps_2);
				}
			}
			return pg;
		} catch (Exception e) {
			throw new SystemUtilException("依据两个面集合，重新构造成一个面集合出错", e);
		}
	}

	/** 面与面的交集 */
	public static Point[] polygonIntersect(Polygon polygon1, Polygon polygon2,
			Point[] overlaps) {
		if (polygon1 == null || polygon2 == null || polygon1.size() < 3) {
			return null;
		}
		Polygon ps = new Polygon();
		for (int i = 0; i < polygon1.size() - 1; i++) {
			double sort = 0;
			Point p1 = polygon1.get(i);
			Point p2 = polygon1.get((i + 1) % polygon1.size());
			Polygon line1 = new Polygon(p1, p2);
			if (polygon2.wrapPoint(p1)) {
				ps.add(p1);
			}
			List<Point> overlapPoints = new ArrayList<Point>();
			if (overlaps != null && overlaps.length > 0) {
				for (Point point : overlaps) {
					if (point.isOverlap() && line1.wrapPoint(point)) {
						overlapPoints.add(point);
					}
				}
			} else {
				for (int j = 0; j < polygon2.size() - 1; j++) {
					Polygon line2 = new Polygon(polygon2.get(j),
							polygon2.get((j + 1) % polygon2.size()));
					Point[] points = line1.lineOverlap(line2);
					if (points != null && points.length == 1
							&& !p2.equals(points[0]) && !p1.equals(points[0])) {
						points[0].setOverlap(true);
						overlapPoints.add(points[0]);
					}
				}
			}
			if (p1.x != p2.x) {
				sort = p2.x - p1.x;
			} else if (p1.y != p2.y) {
				sort = p2.y - p1.y;
			}
			if (sort != 0) {
				overlapPoints = sortLinePoints(overlapPoints, sort);
			}
			ps.addAll(overlapPoints);
		}
		return ps.toArray();
	}

	/** 将在一条直线上的点集合进行排序 */
	public static List<Point> sortLinePoints(List<Point> points, double sord) {
		if (points == null || points.size() < 2) {
			return points;
		}
		List<Point> result = new ArrayList<Point>();
		if (points.get(0).x != points.get(1).x) {
			for (; points.size() > 0;) {
				Point sordValue = points.get(0);
				for (int i = 0; i < points.size(); i++) {
					if ((sord > 0 && sordValue.x > points.get(i).x)
							|| (sord < 0 && sordValue.x < points.get(i).x)) {
						sordValue = points.get(i);
					}
				}
				points.remove(sordValue);
				result.add(sordValue);
			}
		} else {
			for (; points.size() > 0;) {
				Point sordValue = points.get(0);
				for (int i = 0; i < points.size(); i++) {
					if ((sord > 0 && sordValue.y > points.get(i).y)
							|| (sord < 0 && sordValue.y < points.get(i).y)) {
						sordValue = points.get(i);
					}
				}
				points.remove(sordValue);
				result.add(sordValue);
			}
		}
		return result;
	}

	/** 判断点集合points1是否包含点集合points2 */
	public static boolean isContainPoints(Point[] points1, Point[] points2) {
		Polygon polygon1 = new Polygon(points1);
		Polygon polygon2 = new Polygon(points2);
		return polygon1.containsAll(polygon2);
	}

	/** 将对象 转化为 JSON对象 */
	public static JSON toJSON(Object obj) {
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			StringBuffer sBuffer = new StringBuffer();
			for (Field field : fields) {
				try {
					Object value = field.get(obj);
					if (value instanceof Map) {
						value = JSONObject.fromObject(value).toString();
					}
					sBuffer.append("'" + field.getName() + "':'"
							+ ((value == null) ? "" : value) + "',");
				} catch (Exception e) {
				}
			}
			String result = sBuffer.toString();
			if (result.endsWith(",")) {
				result = result.substring(0, result.length() - 1);
			}
			return JSONObject.fromObject("{" + result + "}");
		} catch (Exception e) {
			throw new SystemUtilException("GisUtil类的toJSON方法错误原因是：", e);
		}
	}

	/** 将两个点集合改成同一方向(顺时针、逆时针)，将pg2按pg1的顺序排序 */
	private static List<Point[]> orderByPolygon(Point[] pg1, Point[] pg2) {
		List<Point[]> result = new ArrayList<Point[]>();
		if (pg1 == null || pg1.length == 0 || pg2 == null || pg2.length == 0) {
			result.add(0, pg1);
			result.add(1, pg2);
			return result;
		}
		Polygon pgL1 = new Polygon();
		Polygon pgL2 = new Polygon();
		boolean exist = false;
		int index = 0;
		for (int i = 0; pg1 != null && i < pg1.length; i++) {
			Point point = pg1[i];
			if (isContainPoints(pg2, new Point[] { point })) {
				exist = true;
			}
			if (exist) {
				pgL1.add(index, point);
				index++;
			} else {
				pgL1.add(pgL1.size(), point);
			}
		}
		exist = false;
		index = 0;
		for (int j = 0; pg2 != null && j < pg2.length; j++) {
			Point point = pg2[j];
			if (point.equals(pgL1.get(0))) {
				exist = true;
			}
			if (exist) {
				pgL2.add(index, point);
				index++;
			} else {
				pgL2.add(pgL2.size(), point);
			}
		}
		result.add(0, pgL1.toArray());
		result.add(1, pgL2.toArray());
		return result;
	}

	public static Point[] polygonIntersect(Polygon polygon1, Polygon polygon2) {
		List<IntersectThread> list = new ArrayList<IntersectThread>();
		int threadRows = Math.max(100, polygon1.size() / 10 + 1);
		for (int i = 0; i < 10; i++) {
			int start = Math.min(threadRows * i - 1, polygon1.size());
			start = Math.max(start, 0);
			int end = Math.min(threadRows * (i + 1), polygon1.size());
			Polygon polygon = new Polygon();
			polygon.addAll(polygon1.subList(start, end));
			if (polygon.size() == 0) {
				break;
			}
			IntersectThread thread = new GisUtil().new IntersectThread(polygon,
					polygon2, false);
			thread.start();
			list.add(thread);
		}
		Polygon result = new Polygon();
		while (list.size() > 0) {
			if (Thread.State.TERMINATED.equals(list.get(0).getState())) {
				result.addAll(list.get(0).getResult());
				list.remove(0);
			}
		}
		return result.toArray();
	}

	public static String startIntersectThread(Polygon polygon1, Polygon polygon2) {
		IntersectThread thread = new GisUtil().new IntersectThread(polygon1,
				polygon2, true);
		thread.start();
		String keyId = thread.getName() + thread.getId();
		cache(SET_MODE, keyId, false);
		return keyId;
	}

	public static Polygon getIntersectPolygon(String keyId) {
		try {
			Polygon polygon = new Polygon();
			Boolean isIntersectEnd = cache(GET_MODE, keyId, null);
			if (isIntersectEnd != null) {
				if (isIntersectEnd) {
					List<Gis2DLayer> gis2dLayers = getGis2DLayerService()
							.findByOrgCode(keyId, null);
					polygon = new Polygon(gis2dLayers.get(0).getPoints());
					cache(DEL_MODE, keyId, null);
					getGis2DLayerService().deleteById(
							gis2dLayers.get(0).getId());
				} else {
					return null;
				}
			}
			return polygon;
		} catch (Exception e) {
			throw new SystemUtilException("getPolygonByThreadId报错：", e);
		}
	}

	public static Gis2DLayerService getGis2DLayerService() {
		return (Gis2DLayerService) SpringBeanUtil
				.getBeanFromSpringByBeanName("gis2DLayerService");
	}

	private static Boolean cache(String mode, String code, Object obj) {
		CacheService cacheService = (CacheService) SpringBeanUtil
				.getBeanFromSpringByBeanName("cacheService");
		String cacheKey = MemCacheConstant.generateCacheKeyFromMethodName(
				GisUtil.class, MemCacheConstant.GISUTIL_INTERSECTPOLYGON, code);
		if (SET_MODE.equals(mode)) {
			cacheService.set(MemCacheConstant.MAP_NAMESPACE, cacheKey, obj);
		} else if (GET_MODE.equals(mode)) {
			return (Boolean) cacheService.get(MemCacheConstant.MAP_NAMESPACE,
					cacheKey);
		} else if (DEL_MODE.equals(mode)) {
			cacheService.remove(MemCacheConstant.MAP_NAMESPACE, cacheKey);
		}
		return null;
	}

	public class IntersectThread extends Thread {
		private Polygon polygon1;
		private Polygon polygon2;
		private boolean flag;
		private Polygon result;

		public IntersectThread(Polygon polygon1, Polygon polygon2, boolean flag) {
			this.polygon1 = polygon1;
			this.polygon2 = polygon2;
			this.flag = flag;
		}

		@Override
		public void run() {
			GisUtil.mockAdminSession();
			if (flag) {
				if (polygon1.wrapPolygon(polygon2)) {
					result = polygon2;
				} else {
					result = GisUtil.intersectPolygon(polygon1, polygon2);
				}
				String keyId = this.getName() + this.getId();
				GisUtil.getGis2DLayerService().saveGis2DLayer(
						mockGis2DLayer(keyId, result.toString()));
				cache(SET_MODE, keyId, true);
			} else {
				result = new Polygon(GisUtil.polygonIntersect(polygon1,
						polygon2, null));
			}
		}

		public Polygon getResult() {
			return result;
		}
	}

	private static Gis2DLayer mockGis2DLayer(String keyId, String points) {
		Organization org = new Organization();
		org.setId(0L);
		Gis2DLayer gis2dLayer = new Gis2DLayer();
		gis2dLayer.setOrganization(org);
		gis2dLayer.setOrgInternalCode(keyId);
		gis2dLayer.setCenterX("0");
		gis2dLayer.setCenterY("0");
		gis2dLayer.setZoom(0);
		gis2dLayer.setPoints(points);
		gis2dLayer.setMinLon(0D);
		gis2dLayer.setMaxLon(0D);
		gis2dLayer.setMinLat(0d);
		gis2dLayer.setMaxLat(0d);
		return gis2dLayer;
	}

	private static void mockAdminSession() {
		Session session = new Session();
		session.setUserName("admin");
		session.setUserRealName("超级管理员");
		session.setAccessIp("127.0.0.1");
		session.setAccessTime(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		session.setLoginDate(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		session.setLogin(true);
		session.setUserId(1L);
		session.setSessionId(UUID.randomUUID().toString());
		Organization org = new Organization();
		org.setId(1L);
		org.setOrgInternalCode(".");
		session.setOrganization(org);
		session.setOrgInternalCode(".");
		User user = new User();
		ThreadVariable.setUser(user);
		user.setOrganization(org);
		ThreadVariable.setSession(session);
		ThreadVariable.setOrganization(org);
	}
}
