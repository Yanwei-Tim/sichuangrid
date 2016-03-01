package com.tianque.openLayersMap.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.domian.HourseInfo;
import com.tianque.openLayersMap.domian.vo.DrawAreaStatisticVo;
import com.tianque.openLayersMap.domian.vo.JurisdictionStatisticVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.openLayersMap.service.TownshipsUpStatisticService;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;

public class DrawAreaStatisticHelper {

	public static DrawAreaStatisticVo countBoundMapNum(DrawAreaStatisticVo drawAreaStatisticVo,
			String points, Organization organization,
			Map<String, TownshipsUpStatisticService> townshipsUpStatisticService) {
		ScreenCoordinateVo screenCoordinateVo = convScreenCoordinateVo(drawAreaStatisticVo, points);
		Integer keyPersonBoundMapNum = townshipsUpStatisticService.get(
				ServiceImplModeType.KEY_PERSON_STATISTIC).statisticInfoByOrgIdAndPoints(
				screenCoordinateVo, organization.getId());
		Integer keyPlaceBoundMapNum = townshipsUpStatisticService.get(
				ServiceImplModeType.KEY_PLACE_STATISTIC).statisticInfoByOrgIdAndPoints(
				screenCoordinateVo, organization.getId());
		Integer buildDataBoundMapNum = townshipsUpStatisticService.get(
				ServiceImplModeType.BUILDDATA_STATISTIC).statisticInfoByOrgIdAndPoints(
				screenCoordinateVo, organization.getId());
		Integer housePropertyBoundMapNum = townshipsUpStatisticService.get(
				ServiceImplModeType.HOUSEPROPERTY_STATISTIC).statisticInfoByOrgIdAndPoints(
				screenCoordinateVo, organization.getId());
		Integer issueBoundMapNum = townshipsUpStatisticService.get(
				ServiceImplModeType.ISSUE_STATISTIC).statisticInfoByOrgIdAndPoints(
				screenCoordinateVo, organization.getId());
		Integer hourseNum = townshipsUpStatisticService.get(
				ServiceImplModeType.HOURSEINFO_STATISTIC).statisticInfoByOrgIdAndPoints(
				screenCoordinateVo, organization.getId());

		drawAreaStatisticVo.setKeyPersonBoundMapNum(keyPersonBoundMapNum);
		drawAreaStatisticVo.setKeyPlaceBoundMapNum(keyPlaceBoundMapNum);
		drawAreaStatisticVo.setBuildDataBoundMapNum(buildDataBoundMapNum);
		drawAreaStatisticVo.setHousePropertyBoundMapNum(housePropertyBoundMapNum);
		drawAreaStatisticVo.setIssueBoundMapNum(issueBoundMapNum);
		drawAreaStatisticVo.setHourseBoundMapNum(hourseNum);

		return drawAreaStatisticVo;
	}

	private static ScreenCoordinateVo convScreenCoordinateVo(DrawAreaStatisticVo searchVo,
			String points) {
		if (searchVo == null || points == null)
			return null;
		Double[] maxAndMins = OpenLayersGetPoints.getMaxAndMinLonLat(points);
		ScreenCoordinateVo screenCoordinateVo = new ScreenCoordinateVo(maxAndMins[2],
				maxAndMins[0], maxAndMins[3], maxAndMins[1]);
		SearchInfoVo searchInfoVo = new SearchInfoVo();
		searchInfoVo.setGisType(searchVo.getGisType());
		screenCoordinateVo.setSearchInfoVo(searchInfoVo);
		screenCoordinateVo.setPoints(points);
		return screenCoordinateVo;
	}

	public static DrawAreaStatisticVo countSum(DrawAreaStatisticVo drawAreaStatisticVo,
			String points, Organization organization,
			Map<String, TownshipsUpStatisticService> townshipsUpStatisticService) {
		Integer keyPersonSum = townshipsUpStatisticService.get(
				ServiceImplModeType.KEY_PERSON_STATISTIC).statisticInfoSumByOrgId(
				organization.getId());
		Integer keyPlaceSum = townshipsUpStatisticService.get(
				ServiceImplModeType.KEY_PLACE_STATISTIC).statisticInfoSumByOrgId(
				organization.getId());
		Integer buildDataSum = townshipsUpStatisticService.get(
				ServiceImplModeType.BUILDDATA_STATISTIC).statisticInfoSumByOrgId(
				organization.getId());
		Integer housePropertySum = townshipsUpStatisticService.get(
				ServiceImplModeType.HOUSEPROPERTY_STATISTIC).statisticInfoSumByOrgId(
				organization.getId());
		Integer issueSum = townshipsUpStatisticService.get(ServiceImplModeType.ISSUE_STATISTIC)
				.statisticInfoSumByOrgId(organization.getId());
		Integer hourseNum = statisticInfoSumFromWFS(organization.getId(), points,
				townshipsUpStatisticService);

		drawAreaStatisticVo.setKeyPersonSum(keyPersonSum);
		drawAreaStatisticVo.setKeyPlaceSum(keyPlaceSum);
		drawAreaStatisticVo.setBuildDataSum(buildDataSum);
		drawAreaStatisticVo.setHousePropertySum(housePropertySum);
		drawAreaStatisticVo.setIssueSum(issueSum);
		drawAreaStatisticVo.setHourseNum(hourseNum);
		return drawAreaStatisticVo;
	}

	public static DrawAreaStatisticVo countMapSum(DrawAreaStatisticVo drawAreaStatisticVo,
			String points, Organization organization,
			Map<String, TownshipsUpStatisticService> townshipsUpStatisticService) {
		Integer keyPersonSum = townshipsUpStatisticService.get(
				ServiceImplModeType.KEY_PERSON_STATISTIC).statisticInfoSumByOrgId(
				organization.getId());
		Integer keyPlaceSum = townshipsUpStatisticService.get(
				ServiceImplModeType.KEY_PLACE_STATISTIC).statisticInfoSumByOrgId(
				organization.getId());
		Integer buildDataSum = townshipsUpStatisticService.get(
				ServiceImplModeType.BUILDDATA_STATISTIC).statisticInfoSumByOrgId(
				organization.getId());
		Integer housePropertySum = townshipsUpStatisticService.get(
				ServiceImplModeType.HOUSEPROPERTY_STATISTIC).statisticInfoSumByOrgId(
				organization.getId());
		Integer issueSum = townshipsUpStatisticService.get(ServiceImplModeType.ISSUE_STATISTIC)
				.statisticInfoSumByOrgId(organization.getId());

		drawAreaStatisticVo.setKeyPersonSum(keyPersonSum);
		drawAreaStatisticVo.setKeyPlaceSum(keyPlaceSum);
		drawAreaStatisticVo.setBuildDataSum(buildDataSum);
		drawAreaStatisticVo.setHousePropertySum(housePropertySum);
		drawAreaStatisticVo.setIssueSum(issueSum);

		return drawAreaStatisticVo;
	}

	public static List<Object[]> convertColumn2Pie(HighchartColumnVo personnelColumn) {
		List<Object[]> pieDomain = new ArrayList<Object[]>();
		String[] categories = personnelColumn.getCategories();
		int[] datas = personnelColumn.getSeries().get(0).getData();
		double count = 0.0;
		for (int i = 0; i < datas.length; i++) {
			count = datas[i] + count;
		}
		Object[] obj;
		for (int i = 0; i < categories.length; i++) {
			obj = new Object[2];
			obj[0] = categories[i];
			if (count == 0)
				obj[1] = 0;
			else
				obj[1] = Double.parseDouble(new java.text.DecimalFormat("#.00").format(datas[i]
						/ count * 100));

			pieDomain.add(obj);
		}
		return pieDomain;
	}

	/**
	 * 通过坐标区域从WFS服务中获得房屋热区总数（主要应用于画区域统计）
	 * 
	 * @param orgId
	 * @return Integer
	 */
	private static Integer statisticInfoSumFromWFS(Long orgId, String points,
			Map<String, TownshipsUpStatisticService> townshipsUpStatisticService) {
		if (points == null) {
			throw new BusinessValidationException("points不能为空!");
		}
		Double[] maxAndMinLonLatArrys = OpenLayersGetPoints.getMaxAndMinLonLat(points);
		List<HourseInfo> hourseInfos = findHourseInfosByMaxAndMinLonLatArrysFromWFS(maxAndMinLonLatArrys);
		int countNum = 0;
		Integer hourseSumNum = townshipsUpStatisticService.get(
				ServiceImplModeType.HOURSEINFO_STATISTIC).statisticInfoSumByOrgId(orgId);// 根据组织机构Id统计房屋的总数
		List<HourseInfo> hourseList = new ArrayList<HourseInfo>();
		for (int i = 0; i < hourseInfos.size(); i++) {
			HourseInfo hourseInfo = hourseInfos.get(i);
			// 精确筛选地图数据，将符合需求的数据放入一个新的集合
			boolean isPointInPolygon = OpenLayersGetPoints.isPointInPolygon(
					Double.valueOf(hourseInfo.getLon()), Double.valueOf(hourseInfo.getLat()),
					points);
			if (isPointInPolygon) {
				hourseList.add(hourseInfo);
			}
		}
		if (hourseList.size() > 0) {
			countNum = hourseList.size() + hourseSumNum;
		} else if (hourseList.size() == 0) {
			countNum = hourseSumNum;
		}
		return countNum;
	}

	/**
	 * 通过坐标区域从WFS服务中获得房屋热区的对象列表
	 * 
	 * @param maxAndMinLonLatArrys
	 * @return
	 */
	private static List<HourseInfo> findHourseInfosByMaxAndMinLonLatArrysFromWFS(
			Double[] maxAndMinLonLatArrys) {
		HttpClient client = new HttpClient();
		Map<String, String> wfsOption= GisProperties.WFS_OPTION;
		String featureType = wfsOption.get("featureType");
		String featurePrefix = wfsOption.get("featurePrefix");
		String featurePath = wfsOption.get("layerPath");
		String url =featurePath + "?VERSION=1.0.0&REQUEST=GetFeature&TYPENAME="
				+ featurePrefix + ":" + featureType + "&BBOX=" + maxAndMinLonLatArrys[2] + ","
				+ maxAndMinLonLatArrys[3] + "," + maxAndMinLonLatArrys[0] + ","
				+ maxAndMinLonLatArrys[1];
		PostMethod postMethod = new PostMethod(url);
		List<HourseInfo> hourseList = new ArrayList<HourseInfo>();

		try {
			client.executeMethod(postMethod);
			SAXReader reader = new SAXReader();
			Document document = reader.read(postMethod.getResponseBodyAsStream());
			Element root = document.getRootElement();
			List rList = root.elements("featureMember");
			for (Iterator rit = rList.iterator(); rit.hasNext();) {
				Element memberElm = (Element) rit.next();
				Element elm = memberElm.element(featureType);
				Double[] lonlat = OpenLayersGetPoints.getCenterLonLat(getFeaturePoints(elm));
				HourseInfo hourseInfo = new HourseInfo();
				hourseInfo.setFeatureId(elm.attribute("fid").getValue());
				hourseInfo.setLon(lonlat[0].toString());
				hourseInfo.setLat(lonlat[1].toString());
				hourseList.add(hourseInfo);
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return hourseList;
	}

	private static String getFeaturePoints(Element element) {
		if (element == null)
			return null;
		return element.element("the_geom").element("MultiPolygon").element("polygonMember")
				.element("Polygon").element("outerBoundaryIs").element("LinearRing")
				.element("coordinates").getText();
	}

	public static JurisdictionStatisticVo countJurisdictionMapSum(
			JurisdictionStatisticVo jurisdictionStatisticVo, Organization organization,
			Map<String, TownshipsUpStatisticService> townshipsUpStatisticService) {
		// 人口总数
		Integer populationSum = 0;
		// 常住人口数量
		Integer householdStaffSum = 0;
		// 流动人口数量
		Integer floatingPopulationSum = 0;
		// 境外人员数量
		Integer overseaStaffSum = 0;
		// 未落户人员数量
		Integer unsettledPopulationSum = 0;
		// 重点人员数量
		Integer keyPersonSum = 0;
		// 关怀对象数量
		Integer carePersonSum = 0;
		// 其他关注对象数量
		Integer otherPersonSum = 0;
		List<StatisticInfoVo> personList = new ArrayList<StatisticInfoVo>();
		List<StatisticInfoVo> keyPersonList = new ArrayList<StatisticInfoVo>();
		personList = townshipsUpStatisticService.get(ServiceImplModeType.PERSON_STATISTIC)
				.statisticInfoByOrgId(organization.getId());
		keyPersonList = townshipsUpStatisticService.get(ServiceImplModeType.KEY_PERSON_STATISTIC)
				.statisticInfoByOrgId(organization.getId());
		for (StatisticInfoVo vo : personList) {
			if (GisGlobalValueMap.HOUSEHOLDSTAFF.equals(vo.getGisTypeManage().getKey())) {
				householdStaffSum = vo.getSumNum();
			} else if (GisGlobalValueMap.FLOATINGPOPULATION.equals(vo.getGisTypeManage().getKey())) {
				floatingPopulationSum = vo.getSumNum();
			} else if (GisGlobalValueMap.OVERSEASTAFF.equals(vo.getGisTypeManage().getKey())) {
				overseaStaffSum = vo.getSumNum();
			} else if (GisGlobalValueMap.UNSETTLEDPOPULATION.equals(vo.getGisTypeManage().getKey())) {
				unsettledPopulationSum = vo.getSumNum();
			}
		}
		populationSum = householdStaffSum + floatingPopulationSum + overseaStaffSum
				+ unsettledPopulationSum;
		for (StatisticInfoVo vo : keyPersonList) {
			if (GisGlobalValueMap.PERSON_MODE.equals(vo.getTypeName())) {
				keyPersonSum = vo.getSumNum();
			} else if (GisGlobalValueMap.OTHER_PERSON.equals(vo.getTypeName())) {
				otherPersonSum = vo.getSumNum();
			} else if (GisGlobalValueMap.CARE_PERSON.equals(vo.getTypeName())) {
				carePersonSum = vo.getSumNum();
			}
		}
		// 组织场所数量
		Integer orgLocationSum = townshipsUpStatisticService.get(
				ServiceImplModeType.KEY_PLACE_STATISTIC).statisticInfoSumByOrgId(
				organization.getId());
		// 房屋数量
		Integer houseSum = townshipsUpStatisticService.get(
				ServiceImplModeType.HOUSEPROPERTY_STATISTIC).statisticInfoSumByOrgId(
				organization.getId());

		jurisdictionStatisticVo.setPopulationSum(populationSum);
		jurisdictionStatisticVo.setHouseholdStaffSum(householdStaffSum);
		jurisdictionStatisticVo.setFloatingPopulationSum(floatingPopulationSum);
		jurisdictionStatisticVo.setOverseaStaffSum(overseaStaffSum);
		jurisdictionStatisticVo.setUnsettledPopulationSum(unsettledPopulationSum);
		jurisdictionStatisticVo.setKeyPersonSum(keyPersonSum);
		jurisdictionStatisticVo.setCarePersonSum(carePersonSum);
		jurisdictionStatisticVo.setOtherPersonSum(otherPersonSum);
		jurisdictionStatisticVo.setOrgLocationSum(orgLocationSum);
		jurisdictionStatisticVo.setHouseSum(houseSum);

		return jurisdictionStatisticVo;
	}

}
