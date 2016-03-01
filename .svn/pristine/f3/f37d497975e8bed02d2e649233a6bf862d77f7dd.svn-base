package com.tianque.issue.uitl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.issue.domain.IssueNew;
import com.tianque.util.HttpClientUtils;

/**
 * @ClassName: IssueToCMSUtil
 * @Description: 事件同步到呼叫中心工具类
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2014年10月27日 上午10:02:28
 */
public class IssueToCMSUtil {
	static Logger logger = LoggerFactory.getLogger(IssueToCMSUtil.class);

	/**
	 * @Description: CMS 呼叫中心发送请求
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param url
	 *            请求链接
	 * @param params
	 *            请求参数
	 * @return
	 * @throws
	 */
	public static String post(String url, Map<String, String> params) {
		if (GridProperties.CMS_CALLCENTER_IS_OPEN) {
			return HttpClientUtils.post(url, params);
		}
		return null;
	}

	/**
	 * @Description: CMS 呼叫中心发送请求
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param url
	 *            请求链接
	 * @return
	 * @throws
	 */
	public static String post(String url) {
		return post(url, new HashMap<String, String>());
	}

	public static String post(String url, MultipartEntity data) {
		if (GridProperties.CMS_CALLCENTER_IS_OPEN) {
			HttpPost request = new HttpPost(url);
			request.setEntity(data);
			String result = null;
			try {
				// 获得响应对象
				HttpResponse response = new DefaultHttpClient()
						.execute(request);
				// 判断是否请求成功
				if (response.getStatusLine().getStatusCode() == 200) {
					// 获得响应
					result = EntityUtils.toString(response.getEntity());
					//result=new  String(result.getBytes("8859_1"),"GB2312");  这句可要可不要，以你不出现乱码为准
					return result;
				}
			} catch (ClientProtocolException e) {
				logger.error(e.getMessage(), e);
				result = "网络异常！";
				return result;
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				result = "网络异常！";
				return result;
			}
		}
		return null;
	}

	public static Map<String, String> issueToMap(IssueNew issue,
			Long locationId, Long newStepId) {
		Map<String, String> issueMap = new HashMap<String, String>();
		issueMap.put("newStepId", convertNull(newStepId));// 上报12345的用户id
		issueMap.put("reportUserByIssueId", ThreadVariable.getUser().getId()
				.toString());// 上报12345的用户id
		issueMap.put("location.id", convertNull(locationId));// locationId
		issueMap.put("name",
				"".equals(convertNull(issue.getSourcePerson())) ? "未知"
						: convertNull(issue.getSourcePerson()));// 诉求人姓名
		issueMap.put("telephone", convertNull(issue.getSourceMobile()));// 诉求人电话
		issueMap.put("title", "".equals(convertNull(issue.getSubject())) ? "未知"
				: convertNull(issue.getSubject()));// 诉求标题
		issueMap.put("occurLocation",
				"".equals(convertNull(issue.getOccurLocation())) ? "未知"
						: convertNull(issue.getOccurLocation()));// 诉求地点
		issueMap.put("content",
				"".equals(convertNull(issue.getIssueContent())) ? "暂无"
						: convertNull(issue.getIssueContent()));// 诉求内容
		issueMap.put("isEmergency", issue.getIsEmergency().toString());// 是否紧急
		issueMap.put("centerLon", convertNull(issue.getCenterLon()));// 2.5维经度
		issueMap.put("centerLat", convertNull(issue.getCenterLat()));// 2.5维纬度
		issueMap.put("centerLon2", convertNull(issue.getCenterLon2()));// 2维经度
		issueMap.put("centerLat2", convertNull(issue.getCenterLat2()));// 2维经度
		issueMap.put("serialNumberByIssue",
				convertNull(issue.getSerialNumber()));// 上报12345的社管事件服务单号
		issueMap.put("fromSerialNumber",
				convertNull(issue.getFromSerialNumber()));// 12345首次交办社管的事件
		//		issueMap.put("deadLine", issue.getDeadLine() == null ? null : issue
		//				.getDeadLine().toString()); // 办理期限
		return issueMap;
	}

	private static String convertNull(Object obj) {
		return obj == null || "null".equals(obj.toString().trim()) ? "" : obj
				.toString().trim();
	}

	private static List<Organization> getCMSLocationOrgName(Long orgId,
			String name, String departmentNo) {
		String where = StringUtil.isStringAvaliable(name) ? name : "";
		if (orgId != null) {
			orgId = orgId < 0 ? orgId * -1 : orgId;
			where = "&id=" + orgId;
		} else if (StringUtil.isStringAvaliable(departmentNo)) {
			where = "&departmentNo=" + departmentNo;
		}
		JSONArray jsonarray = JSONArray.fromObject(IssueToCMSUtil
				.post(GridProperties.CMS_CALLCENTER_ISSUE_FIND_CMS_LOCATION
						+ "?name=" + where));
		List<Organization> cmsLocationList = new ArrayList<Organization>();
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject jsonObj = (JSONObject) jsonarray.get(i);
			Organization organization = new Organization();
			organization.setId(Long.parseLong(jsonObj.getString("id")) * -1);
			organization.setOrgName(jsonObj.getString("name") + "12345指挥中心");
			organization.setDepartmentNo(jsonObj.getString("departmentNo"));
			organization.setOrgInternalCode("report12345");
			PropertyDict orgType = new PropertyDict();
			orgType.setInternalId(OrganizationType.OTHER);
			organization.setOrgType(orgType);
			PropertyDict functionalOrgType = new PropertyDict();
			functionalOrgType.setId(0l);
			organization.setFunctionalOrgType(functionalOrgType);
			PropertyDict orgLevel = new PropertyDict();
			orgLevel.setId(0l);
			organization.setOrgLevel(orgLevel);
			cmsLocationList.add(organization);
		}
		return cmsLocationList;
	}

	public static List<Organization> getLocationOrgNameByDepartmentNo(
			String name, String departmentNo) {
		return getCMSLocationOrgName(null, name, departmentNo);
	}

	public static Organization getLocationOrgNameByLocationId(Long orgId) {
		List<Organization> cmsLocationList = getCMSLocationOrgName(orgId, null,
				null);
		if (cmsLocationList.size() == 0) {
			throw new BusinessValidationException("获取呼叫中心坐席失败！坐席不存在。");
		}
		return cmsLocationList.get(0);
	}
}
