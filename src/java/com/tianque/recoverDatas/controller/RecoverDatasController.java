package com.tianque.recoverDatas.controller;

import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.recoverDatas.domain.RecoverDatas;
import com.tianque.recoverDatas.service.RecoverDatasService;
import com.tianque.recoverDatas.vo.RecoverDatasVo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/sysadmin/recoverDatasManage")
@Scope("request")
@Controller("recoverDatasController")
public class RecoverDatasController extends BaseAction {

	private RecoverDatasVo recoverDatasVo;
	private String ids;
	@Autowired
	private RecoverDatasService recoverDatasService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private PageInfo<RecoverDatas> emptyPage(int pageSize) {
		PageInfo<RecoverDatas> pageInfo = new PageInfo<RecoverDatas>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<RecoverDatas>());
		return pageInfo;
	}

	@Action(value = "fastSearch", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String fastSearch() throws Exception {
		if (recoverDatasVo == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		}
		if (recoverDatasVo.getOrganization() == null
				|| recoverDatasVo.getOrganization().getId() == null) {
			errorMessage = "请先选择要查询的范围";
			return "error";
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				recoverDatasService.findRecoverDatasForPage(recoverDatasVo,
						page, rows, sidx, sord), organizationDubboService,
				new String[] { "organization" }, recoverDatasVo
						.getOrganization().getId()));
		return SUCCESS;
	}

	// 查询
	@Action(value = "searchRecoverDatas", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String searchRecoverDatas() throws Exception {
		if (recoverDatasVo == null) {
			gridPage = new GridPage(emptyPage(rows == 0 ? 20 : rows));
			return SUCCESS;
		}
		PageInfo<RecoverDatas> pageInfo = recoverDatasService
				.findRecoverDatasForPage(recoverDatasVo, page, rows, sidx, sord);
		gridPage = new GridPage(pageInfo);

		return SUCCESS;
	}

	@Action(value = "getRecoverDatasById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"recoverDatas", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getRecoverDatasById() throws Exception {

		return SUCCESS;
	}

	@Action(value = "recoverDatasById", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String recoverDatasById() throws Exception {
		if (null == recoverDatasVo || null == recoverDatasVo.getId()) {
			errorMessage = "参数错误！";
			return ERROR;
		}
		recoverDatasService.recoverActualPopulation(recoverDatasVo.getId());
		return SUCCESS;
	}

	@Action(value = "deleteRecoverDataByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteByIds() throws Exception {
		if (ids == null || ids.equals("")) {
			this.errorMessage = "id不能为空!";
			return ERROR;
		}
		String[] str = ids.split(",");
		Long[] id = new Long[str.length];
		for (int i = 0; i < str.length; i++) {
			id[i] = Long.parseLong(str[i]);
		}
		recoverDatasService.deleteRecoverDatasByIds(id);
		return SUCCESS;
	}

	public RecoverDatasVo getRecoverDatasVo() {
		return recoverDatasVo;
	}

	public void setRecoverDatasVo(RecoverDatasVo recoverDatasVo) {
		this.recoverDatasVo = recoverDatasVo;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
