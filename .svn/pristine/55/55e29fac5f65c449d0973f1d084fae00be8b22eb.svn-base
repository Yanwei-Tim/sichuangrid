package com.tianque.plugin.tqSearch.constants;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import com.tianque.exception.base.SystemUtilException;

public enum TqSearchUrl {
	URL_HOUSEHOLDSTAFF(TqSearchType.HOUSEHOLDSTAFF,"/baseinfo/householdStaff/dispathByEncrypt.action?mode=view&id=",
			800, 630), 
	URL_FLOATINGPOPULATION(TqSearchType.FLOATINGPOPULATION,"/baseinfo/floatingPopulationManage/dispathByEncrypt.action?mode=view&id=",
			800, 630), 
	URL_UNSETTLEDPOPULATION(TqSearchType.UNSETTLEDPOPULATION,"/baseinfo/unsettledPopulationManage/dispatchOperateByEncrypt.action?mode=view&id=",
			800, 630), 
	URL_OVERSEAPERSONNEL(TqSearchType.OVERSEAPERSONNEL,"/baseinfo/overseaPersonnelManage/dispatchByEncrypt.action?mode=view&isHiddenPersonnelTrack=1&id=",
			800, 630), 
	URL_HOUSE(TqSearchType.HOUSE_KEY,"/baseinfo/houseInfo/actualHouse/viewActualHouseDlg.jsp?mode=view&houseId=",
			800, 630), 
	URL_BUILDING(TqSearchType.BUILDING_KEY,"/builddatasManage/dispatchByEncrypt.action?mode=view&builddatas.id=",
			800, 400), 
	URL_ISSUE(TqSearchType.ISSUE_KEY,"/issues/issueManage/dispatch.action?mode=viewNew&keyId=", 
			800, 500), 
	URL_ISSUEEVALUATE(TqSearchType.ISSUEEVALUATE_KEY,"/issues/issueManage/dispatch.action?mode=gradeHistory&keyId=",
			800, 550), 
	URL_SERVICERECORD(TqSearchType.SERVICERECORD_KEY,"/plugin/serviceTeam/serviceRecord/dispatch.action?mode=view&showRecordType=&serviceRecord.id=",
			800, 600), 
	URL_PLACE(TqSearchType.PLACE_KEY,"/baseinfo/companyPlace/companyPlaceViewDlg.jsp?key={{key}}&cid=",
			800, 630), 
	URL_DUSTBIN(TqSearchType.DUSTBIN_KEY,"/baseinfo/dustbinManage/dispatchOperateByEncrypt.action?mode=view&location.id=",
			800, 400),
	URL_HOUSEHOLDFAMILY(TqSearchType.HOUSEHOLDFAMILY_KEY,"/baseinfo/houseFamily/findHouseFamilyByEncryptId.action?orgId={{organizationId}}&houseFamily.id=",
			805,600),
	URL_PARTYMEMBER(TqSearchType.PARTYMEMBERS_KEY,"/partyBuildng/memberManage/dispatch.action?mode=view&member.partyOrgType={{partyOrgType}}&organizationId={{organizationId}}&member.id=",
			805,600);

	public static JSON toJSON() {
		try {
			StringBuffer urlSb = new StringBuffer();
			int length = TqSearchUrl.values().length;
			for (int i = 0; i < length; i++) {
				TqSearchUrl searchUrl = TqSearchUrl.values()[i];
				urlSb.append((urlSb.length() > 0) ? "," : "");
				urlSb.append("'").append(searchUrl.getKey())
						.append("':{'url':'").append(searchUrl.getUrl())
						.append("','width':'").append(searchUrl.getWidth())
						.append("','height':'").append(searchUrl.getHeight())
						.append("'}");
			}
			return JSONObject.fromObject("{" + urlSb.toString() + "}");
		} catch (Exception e) {
			throw new SystemUtilException("转化为JSON对象报错", e);
		}
	}

	private String key;
	private String url;
	private Integer width;
	private Integer height;

	private TqSearchUrl(String key, String url, Integer width, Integer height) {
		this.key = key;
		this.url = url;
		this.width = width;
		this.height = height;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

}
