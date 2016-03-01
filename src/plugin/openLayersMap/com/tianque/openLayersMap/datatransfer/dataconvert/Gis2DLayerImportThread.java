package com.tianque.openLayersMap.datatransfer.dataconvert;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.baseInfo.excelimportlog.service.ExcelImportLogService;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.service.Gis2DLayerService;
import com.tianque.openLayersMap.util.GisOrgZoom;
import com.tianque.openLayersMap.util.OpenLayersGetPoints;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

public class Gis2DLayerImportThread extends Thread {
	public final static Logger logger = LoggerFactory
			.getLogger(Gis2DLayerImportThread.class);

	private String gisType;
	private File upload;
	private List features;
	private Session session;
	private String uuid;
	private String fileName;
	private String fileType;
	private Long ticketId;

	private Integer total;
	private Integer successNum;
	private Integer failNum;

	private OrganizationDubboService organizationDubboService;
	private Gis2DLayerService gis2dLayerService;
	private PropertyDictService propertyDictService;
	private ExcelImportLogService excelImportLogService;

	public Gis2DLayerImportThread(Session session, String gisType, File upload,
			String fileName) {
		super();
		this.gisType = gisType;
		this.upload = upload;
		this.session = session;
		this.uuid = UUID.randomUUID().toString();
		this.fileName = fileName;
		this.fileType = fileName.substring(fileName.lastIndexOf("."));
		total = 0;
		successNum = 0;
		failNum = 0;
		init();
	}

	private void init() {
		try {
			this.organizationDubboService = (OrganizationDubboService) SpringBeanUtil
					.getBeanFromSpringByBeanName("organizationDubboService");
			this.gis2dLayerService = (Gis2DLayerService) SpringBeanUtil
					.getBeanFromSpringByBeanName("gis2DLayerService");
			this.propertyDictService = (PropertyDictService) SpringBeanUtil
					.getBeanFromSpringByBeanName("propertyDictService");
			this.excelImportLogService = (ExcelImportLogService) SpringBeanUtil
					.getBeanFromSpringByBeanName("excelImportLogService");

			FileInputStream fis = new FileInputStream(upload);
			SAXReader reader = new SAXReader();
			Document document = reader
					.read(new InputStreamReader(fis, "utf-8"));
			Element root = document.getRootElement();
			features = root.elements("featureMember");
			total = features.size();
			ticketId = excelImportLogService.addImportLog(uuid, fileName,
					fileType, total, Gis2DLayer.class.getName()).getId();
		} catch (Exception e) {
			throw new ServiceValidationException("数据读取失败", e);
		}
	}

	@Override
	public void run() {
		createThreadUser();
		addOrUpdateLayerInfo();
	}

	private void createThreadUser() {
		ThreadVariable.setSession(session);
		User user = new User();
		user.setId(session.getUserId());
		user.setOrganization(session.getOrganization());
		ThreadVariable.setUser(user);
		ThreadVariable.setOrganization(session.getOrganization());
		ThreadVariable.setSourcesState(ConstantsProduct.SourcesState.IMPORT);
	}

	private void addOrUpdateLayerInfo() {
		Organization org = null;
		try {
			Long addNum = 0L;
			Long updateNum = 0L;
			StringBuffer errorDepartmentNos = new StringBuffer();
			for (int i = 0; i < features.size(); i++) {
				Element feature = (Element) features.get(i);
				Element geometrys = (Element) feature.elements().get(0);
				String node = geometrys.getName();
				String points = geometrys.element("geometryProperty")
						.element("Polygon").element("outerBoundaryIs")
						.element("LinearRing").elementTextTrim("coordinates");
				String departmentNo = geometrys.elementTextTrim("PAC");
				departmentNo = substrDepartmentNo(departmentNo, node);
				org = organizationDubboService
						.getOrgByDepartmentNo(departmentNo);
				if (org != null) {
					Gis2DLayer layer = gis2dLayerService.getByOrgId(
							org.getId(), gisType);
					if (layer == null) {
						layer = new Gis2DLayer();
						layer.setRemark(org.getOrgName());
					}
					layer.setGisType(gisType);
					layer.setIsTransformat(false);
					layer.setOrganization(org);
					layer.setOrgInternalCode(org.getOrgInternalCode());
					setGis2DLayerByPoint(layer, points);
					if (layer.getId() == null) {
						gis2dLayerService.addGis2DLayer(layer);
						addNum++;
					} else {
						gis2dLayerService.updateGis2DLayer(layer);
						updateNum++;
					}
					successNum++;
				} else {
					failNum++;
					errorDepartmentNos.append(departmentNo);
				}
				if (i % 5 == 0 || i == features.size() - 1) {
					excelImportLogService.updateImportLogCurrentNum(uuid,
							successNum + failNum, failNum);
				}
			}
			excelImportLogService.updateImportStatus(uuid, 2);
			logger.error("导入地图区域数据：新增【" + addNum + "】条，修改【" + updateNum
					+ "】，报错DepartmentNo【" + errorDepartmentNos.toString() + "】");
		} catch (Exception e) {
			throw new ServiceValidationException("导入地图区域数据出错：", e);
		}
	}

	private void addOrUpdateLayerInfoFromXCQX_CHOUXI() {
		Gis2DLayer layer = null;
		for (Iterator rit = features.iterator(); rit.hasNext();) {
			Element memberElm = (Element) rit.next();
			Element elm = memberElm.element("XCQX_CHOUXI");
			if (elm == null)
				continue;
			String points = elm.element("GEOMETRY").element("Polygon")
					.element("outerBoundaryIs").element("LinearRing")
					.elementText("coordinates");
			String orgName = elm.elementText("NAME");
			boolean exit = true;
			if (StringUtil.isStringAvaliable(orgName)) {
				List<Organization> orgList = organizationDubboService
						.findOrganizationsByOrgName(orgName);
				if (orgList != null && orgList.size() > 0) {
					layer = gis2dLayerService.getByOrgId(
							orgList.get(0).getId(), gisType);
					if (layer == null) {
						exit = false;
						layer = new Gis2DLayer();
					}
					layer.setGisType(gisType);
					layer.setRemark(orgName);
					layer.setOrganization(orgList.get(0));
					layer.setOrgInternalCode(orgList.get(0)
							.getOrgInternalCode());
					setGis2DLayerByPoint(layer, points);
					if (exit) {
						gis2dLayerService.updateGis2DLayer(layer);
					} else {
						gis2dLayerService.addGis2DLayer(layer);
					}
				}
			}
		}
	}

	private void setGis2DLayerByPoint(Gis2DLayer layer, String points) {
		if (StringUtil.isStringAvaliable(points) && layer != null) {
			points = points.trim();
			Double[] lonlat = OpenLayersGetPoints.getCenterLonLat(points);
			if (points.indexOf(" ") > points.indexOf(",")) {
				points = points.replaceAll(",", ",,").replaceAll(" ", ",")
						.replaceAll(",,", " ");
			}
			if (points.indexOf("POLYGON") < 0) {
				points = "POLYGON((" + points + "))";
			}
			// if (GisType.M3D.equals(layer.getGisType().trim())) {
			layer.setPoints(points);
			layer.setCenterX(lonlat[0].toString());
			layer.setCenterY(lonlat[1].toString());
			// } else {
			// layer.setPoints2(points);
			// layer.setCenterX2(lonlat[0].toString());
			// layer.setCenterY2(lonlat[1].toString());
			// }
			Integer zoom = GisOrgZoom.getZoomByOrgLevelId(layer
					.getOrganization().getOrgLevel().getId(),
					propertyDictService);
			layer.setZoom(zoom);
		}
	}

	private String substrDepartmentNo(String departmentNo, String node) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("sheng", OrganizationLevel.PROVINCE);
		map.put("shi", OrganizationLevel.CITY);
		map.put("xian", OrganizationLevel.DISTRICT);
		map.put("xiangzhen", OrganizationLevel.TOWN);
		Integer orgLevel = map.get(node);
		orgLevel = (orgLevel == null) ? OrganizationLevel.GRID : orgLevel;
		return GisOrgZoom.substrDepartmentNo(departmentNo, orgLevel);
	}

	public Integer getTotal() {
		return total;
	}

	public Long getTicketId() {
		return ticketId;
	}
}
