package com.tianque.viewObject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.domain.Organization;
import com.tianque.service.TemporaryPopulationService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.viewObject.service.ViewObjectService;
import com.tianque.viewObject.vo.ViewObjectVo;

@Namespace("/viewObject")
@Controller("viewObjectManager")
@Transactional
public class ViewObjectController extends BaseAction {
	private ViewObjectVo viewObjectVo;
	@Autowired
	private ViewObjectService viewObjectService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private TemporaryPopulationService temporaryPopulationService;

	private List<String> orgNames;// 选择的详细的组织机构
	private String showLink;// 哪个层级要显示详细
	private Long id;// 要查看详细的vo对象的id
	private String type;
	private String level;

	@Action(value = "ajaxGetViewObjectDefNum", results = { @Result(name = "success", type = "json", params = {
			"root", "viewObjectVo" }) })
	public String ajaxGetViewObjectDefNum() {
		viewObjectVo = viewObjectService.getViewObjectDefNum();
		return SUCCESS;
	}

	@Action(value = "ajaxGetselectedOrgNames", results = { @Result(name = "success", location = "/daily/documentsManage/dispatchDocumentsManage/selectedOrgDetail.jsp") })
	public String ajaxGetselectedOrgNames() {
		orgNames = viewObjectService.getselectedOrgNames(viewObjectVo, showLink.split("-")[1],
				showLink.split("-")[0]);
		return SUCCESS;
	}

	@Action(value = "ajaxGetselectedOrgNamesByIdAndLevelAndType", results = { @Result(name = "success", location = "/daily/documentsManage/dispatchDocumentsManage/selectedOrgDetail.jsp") })
	public String ajaxGetselectedOrgNamesByIdAndLevelAndType() {
		orgNames = viewObjectService.getselectedOrgNames(viewObjectService.getViewObjectById(id),
				type, level);
		return SUCCESS;
	}

	@Action(value = "ajaxSaveViewObjectToCache", results = { @Result(name = "success", type = "json", params = {
			"root", "cacheId" }) })
	public String ajaxSaveViewObjectToCache() {
		cacheId = temporaryPopulationService.addViewObjecToTemporary(viewObjectVo);
		return SUCCESS;
	}

	@Action(value = "ajaxGetViewObjectFromCache", results = { @Result(name = "success", type = "json", params = {
			"root", "viewObjectVo", "excludeNullProperties", "true" }) })
	public String ajaxGetViewObjectFromCache() {
		viewObjectVo = (ViewObjectVo) temporaryPopulationService.getObjectByIdFromTemporary(cacheId
				.get("id"));
		if (null != viewObjectVo.getExclusiveIdStrs()
				&& !viewObjectVo.getExclusiveIdStrs().isEmpty()) {
			setSelectedOrgLevelTypeMapList(viewObjectVo.getExclusiveIdStrs());
		}
		return SUCCESS;
	}

	private void setSelectedOrgLevelTypeMapList(String orgIds) {
		Organization org;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		for (String str : viewObjectVo.getExclusiveIdStrs().split(",")) {
			if (null != str && !str.isEmpty()) {
				map = new HashMap<String, String>();
				org = organizationDubboService.getFullOrgById(Long.parseLong(str));
				map.put(str, org.getOrgLevel().getInternalId() + "-"
						+ org.getOrgType().getInternalId());
				list.add(map);
			}
		}
		viewObjectVo.setSelectedOrgLevelTypeMapList(list);
	}

	@Action(value = "ajaxGetViewObjectFromDB", results = { @Result(name = "success", type = "json", params = {
			"root", "viewObjectVo", "excludeNullProperties", "true" }) })
	public String ajaxGetViewObjectFromDB() {
		viewObjectVo = viewObjectService.getViewObjectById(viewObjectVo.getId());
		if (null != viewObjectVo.getExclusiveIdStrs()
				&& !viewObjectVo.getExclusiveIdStrs().isEmpty()) {
			setSelectedOrgLevelTypeMapList(viewObjectVo.getExclusiveIdStrs());
		}
		return SUCCESS;
	}

	@Action(value = "ajaxGetSelectedNumWhenSelectByLine", results = { @Result(name = "success", type = "json", params = {
			"root", "viewObjectVo" }) })
	public String ajaxGetSelectedNumWhenSelectByLine() {
		return SUCCESS;
	}

	public String addSelectedOrgToCache() {
		return SUCCESS;
	}

	public ViewObjectVo getViewObjectVo() {
		return viewObjectVo;
	}

	public void setViewObjectVo(ViewObjectVo viewObjectVo) {
		this.viewObjectVo = viewObjectVo;
	}

	public List<String> getOrgNames() {
		return orgNames;
	}

	public void setOrgNames(List<String> orgNames) {
		this.orgNames = orgNames;
	}

	public String getShowLink() {
		return showLink;
	}

	public void setShowLink(String showLink) {
		this.showLink = showLink;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
