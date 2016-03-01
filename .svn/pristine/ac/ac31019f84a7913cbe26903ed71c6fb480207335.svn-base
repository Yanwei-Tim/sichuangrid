package com.tianque.plugin.serviceTeam.serviceMemberWithObject.vo;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.StringUtil;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.domain.ServiceMemberWithObject;

public class ServiceMembersWithObjectVo extends BaseDomain {

	/* ServiceMemberWithObject Json对象 */
	private String serviceMembers;

	private List<ServiceMemberWithObject> serviceObjectMembers;

	public String getServiceMembers() {
		return serviceMembers;
	}

	public void setServiceMembers(String serviceMembers) {
		this.serviceMembers = serviceMembers;
	}

	public List<ServiceMemberWithObject> getServiceObjectMembers() {
		if (!StringUtil.isStringAvaliable(serviceMembers)) {
			return null;
		}
		List<ServiceMemberWithObject> objs = null;
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(serviceMembers);
		if (jsonArray != null) {
			objs = new ArrayList<ServiceMemberWithObject>();
			List list = (List) JSONSerializer.toJava(jsonArray);
			for (Object o : list) {
				JSONObject jsonObject = JSONObject.fromObject(o);
				ServiceMemberWithObject obj = (ServiceMemberWithObject) JSONObject
						.toBean(jsonObject, ServiceMemberWithObject.class);
				objs.add(obj);
			}
		}
		return objs;
	}

	public void setServiceObjectMembers(
			List<ServiceMemberWithObject> serviceObjectMembers) {
		this.serviceObjectMembers = serviceObjectMembers;
	}

}
