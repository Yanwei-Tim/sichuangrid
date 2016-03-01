package com.tianque.openLayersMap.controller;

import java.util.List;

import net.sf.json.JSON;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.Point;
import com.tianque.openLayersMap.domian.Polygon;
import com.tianque.openLayersMap.service.Gis2DLayerService;
import com.tianque.openLayersMap.util.BigModeType;
import com.tianque.openLayersMap.util.GisGlobalValueMap;
import com.tianque.openLayersMap.util.GisProperties;
import com.tianque.openLayersMap.util.GisTransformatUtil;
import com.tianque.openLayersMap.util.GisType;
import com.tianque.openLayersMap.util.GisUtil;
import com.tianque.openLayersMap.util.ServiceImplModeType;

@Namespace("/gisUtilManage")
@Scope("prototype")
@Controller("gisUtilController")
public class GisUtilController extends BaseAction {

	@Autowired
	private Gis2DLayerService gis2dLayerService;
	/** 点集合 */
	private String points;
	private String parentPoints;
	private Point point;

	private JSON json;
	private String className;
	private Boolean isInPolygon;
	private List<String> geometrys;
	private Double measure;
	private String keyId;
	private Long orgId;
	private String gisType;

	@Action(value = "findStaticProperties", results = {
			@Result(type = "json", params = { "root", "json",
					"excludeNullProperties", "true", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findStaticProperties() throws Exception {
		if (GisProperties.class.getSimpleName().equals(className)) {
			json = GisUtil.toJSON(new GisProperties());
		} else if (ServiceImplModeType.class.getSimpleName().equals(className)) {
			json = GisUtil.toJSON(new ServiceImplModeType());
		} else if (BigModeType.class.getSimpleName().equals(className)) {
			json = GisUtil.toJSON(new BigModeType());
		} else if (GisGlobalValueMap.class.getSimpleName().equals(className)) {
			json = GisUtil.toJSON(new GisGlobalValueMap());
		}
		return SUCCESS;
	}

	/** 求两个面的交集 */
	@Action(value = "intersectPolygon", results = {
			@Result(name = "success", type = "json", params = { "root",
					"points", "ignoreHierarchy", "false" }),
			@Result(name = "threadSu", type = "json", params = { "root",
					"keyId", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String intersectPolygon() throws Exception {
		if (!StringUtil.isStringAvaliable(points) || orgId == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Gis2DLayer parentGis2dLayer = gis2dLayerService.getByOrgId(orgId,
				gisType);
		if (parentGis2dLayer != null) {
			parentPoints = GisType.M3D.equals(gisType) ? parentGis2dLayer
					.getPoints() : parentGis2dLayer.getPoints2();
			Polygon polygon = new Polygon(points);
			Polygon parentPolygon = new Polygon(parentPoints);
			if ("threadSu".equals(mode)) {
				keyId = GisUtil.startIntersectThread(parentPolygon, polygon);
				return mode;
			}
			if (!parentPolygon.wrapPolygon(polygon)) {
				polygon = GisUtil.intersectPolygon(parentPolygon, polygon);
			}
			points = polygon.toString();
		}
		return SUCCESS;
	}

	@Action(value = "getPolygonById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"points", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getPolygonById() throws Exception {
		if (!StringUtil.isStringAvaliable(keyId)) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Polygon polygon = GisUtil.getIntersectPolygon(keyId);
		if (polygon != null) {
			points = polygon.toString();
		}
		return SUCCESS;
	}

	/** 点是否在多个区域中的一个区域内 */
	@Action(value = "isPointInPolygon", results = {
			@Result(name = "success", type = "json", params = { "root",
					"isInPolygon", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String isPointInPolygon() throws Exception {
		if (point == null || geometrys == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		for (String geometry : geometrys) {
			Polygon polygon = new Polygon(geometry);
			isInPolygon = polygon.wrapPoint(point);
			if (isInPolygon == true) {
				return SUCCESS;
			}
		}
		return SUCCESS;
	}

	/** 区域集合[A1,A2...]在区域[B]内 */
	@Action(value = "isPolygonsInPolygon", results = {
			@Result(name = "success", type = "json", params = { "root",
					"isInPolygon", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String isPolygonsInPolygon() throws Exception {
		if (geometrys == null || !StringUtil.isStringAvaliable(points)) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Polygon polygonB = new Polygon(points);
		for (String geometry : geometrys) {
			isInPolygon = polygonB.wrapPolygon(new Polygon(geometry));
			if (isInPolygon == false) {
				return SUCCESS;
			}
		}
		return SUCCESS;
	}

	/** 将三维坐标转为二维坐标 */
	@Action(value = "to2DPoint", results = {
			@Result(name = "success", type = "json", params = { "root",
					"point", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String to2DPoint() throws Exception {
		if (point == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		point = GisTransformatUtil.to2DPoint(point);
		return SUCCESS;
	}

	/** 将二维坐标转为三维坐标 */
	@Action(value = "to25DPoint", results = {
			@Result(name = "success", type = "json", params = { "root",
					"point", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String to25DPoint() throws Exception {
		if (point == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		point = GisTransformatUtil.to25DPoint(point);
		return SUCCESS;
	}

	/** 在地图上测到的25维距离，其实是测量2维的距离，因此需要转换 */
	@Action(value = "to25DLength", results = {
			@Result(name = "success", type = "json", params = { "root",
					"measure", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String to25DLength() throws Exception {
		if (point == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		measure = GisTransformatUtil.to25DLength(points);
		return SUCCESS;
	}

	/** 在地图上测到的25维距离，其实是测量2维的距离，因此需要转换 */
	@Action(value = "to25DArea", results = {
			@Result(name = "success", type = "json", params = { "root",
					"measure", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String to25DArea() throws Exception {
		if (point == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		measure = GisTransformatUtil.to25DArea(points);
		return SUCCESS;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getParentPoints() {
		return parentPoints;
	}

	public void setParentPoints(String parentPoints) {
		this.parentPoints = parentPoints;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public JSON getJson() {
		return json;
	}

	public void setJson(JSON json) {
		this.json = json;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Boolean getIsInPolygon() {
		return isInPolygon;
	}

	public void setIsInPolygon(Boolean isInPolygon) {
		this.isInPolygon = isInPolygon;
	}

	public List<String> getGeometrys() {
		return geometrys;
	}

	public void setGeometrys(List<String> geometrys) {
		this.geometrys = geometrys;
	}

	public Double getMeasure() {
		return measure;
	}

	public void setMeasure(Double measure) {
		this.measure = measure;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getGisType() {
		return gisType;
	}

	public void setGisType(String gisType) {
		this.gisType = gisType;
	}

}
