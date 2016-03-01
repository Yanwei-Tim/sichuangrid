package com.tianque.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.vo.GisVo;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.GlobalValue;
import com.tianque.sysadmin.service.OrganizationDubboService;

@SuppressWarnings("serial")
@Transactional
@Scope("request")
@Controller("gisController")
public class GisController extends BaseAction {
	private String departmentNo;
	private String layerName;
	List<Object> result;
	private String[] RGBs = { "#ffff00", "#ffff11", "#ffff22", "#ffff33",
			"#ffff44", "#ffff55", "#ffff66", "#ffff77", "#ffff88", "#ffff99" };
	private String[] departmentNos = { "330100", "330324", "330522", "330621",
			"330282", "330922" };// shi
	private String[] departmentNos2 = { "330112", "330013", "330015", "330017",
			"330018", "330009" };// 县

	@Autowired
	private OrganizationDubboService organizationDubboService;

	public String count() throws Exception {
		result = new ArrayList<Object>();
		System.out.println(new Date() + "geoServer 请求过来...departmentNo = "
				+ departmentNo);
		departmentNos = organizationDubboService
				.findDepartmentNosByParentDeparmentNo(departmentNo);
		if (departmentNo != null && departmentNo.length() == 2) {// 市一级
			layerName = GlobalValue.SHI_LAYER;
		} else if (departmentNo != null && departmentNo.length() == 4) {// 县区一级
			layerName = GlobalValue.XIAN_LAYER;
		}
		for (int i = 0; i < departmentNos.length; i++) {
			GisVo vo = new GisVo();
			vo.setDepartmentNo(departmentNos[i] + "00");
			vo.setRGB("#ffff00");
			vo.setCount(i);
			result.add(vo);
		}
		return SUCCESS;
	}

	public String event() {
		return SUCCESS;
	}

	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	public String[] getRGBs() {
		return RGBs;
	}

	public void setRGBs(String[] rGBs) {
		RGBs = rGBs;
	}

	public String[] getDepartmentNos() {
		return departmentNos;
	}

	public void setDepartmentNos(String[] departmentNos) {
		this.departmentNos = departmentNos;
	}

	public List<Object> getResult() {
		return result;
	}

	public void setResult(List<Object> result) {
		this.result = result;
	}

	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	public String[] getDepartmentNos2() {
		return departmentNos2;
	}

	public void setDepartmentNos2(String[] departmentNos2) {
		this.departmentNos2 = departmentNos2;
	}

}