package com.tianque.sms.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.sms.domain.Smstrash;
import com.tianque.sms.domain.vo.SearchSmstrashVo;
import com.tianque.sms.service.SmstrashService;

/**
 * 垃圾短信箱:服务前端控制类
 * 
 * @author
 * @date 2013-09-22 16:42:50
 */
@Namespace("/smstrashManage")
@Scope("request")
@Controller("smstrashController")
public class SmstrashController extends BaseAction {

	@Autowired
	private SmstrashService smstrashService;

	private Smstrash smstrash;
	private SearchSmstrashVo searchSmstrashVo;
	private String ids;
	private String idstr;
	private boolean bol;

	@Action(value = "dispatchOperate", results = { @Result(name = "success", location = "/interaction/newSMS/smstrashManage/smstrashView.jsp") })
	public String dispatc() throws Exception {
		if (!StringUtil.isStringAvaliable(idstr)) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		smstrash = smstrashService.get(idstr);
		return SUCCESS;
	}

	@Action(value = "restoreSMSById", results = {
			@Result(name = "success", type = "json", params = { "root", "bol" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "restoreSmstrash")
	public String restoreSMSById() throws Exception {
		if (!StringUtil.isStringAvaliable(idstr)) {
			this.errorMessage = "请选择要还原的记录!";
			return ERROR;
		}
		bol = smstrashService.restoreSMSById(idstr);
		return SUCCESS;
	}

	/**
	 * 根据ID删除实体对象
	 * 
	 * @param id
	 *            记录ID
	 */
	@Action(value = "deleteSmstrashById", results = {
			@Result(name = "success", type = "json", params = { "root", "id" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteSmstrash")
	public String deleteSmstrashById() throws Exception {
		if (!StringUtil.isStringAvaliable(idstr)) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		smstrashService.delete(idstr);
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deleteSmstrashByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteSmstrash")
	public String deleteSmstrashByIds() throws Exception {
		if (ids == null || "".equals(ids.trim())) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		String[] idsStr = ids.split(",");
		if (idsStr.length == 0) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		smstrashService.delete(idsStr);
		return SUCCESS;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * 
	 * @return Pager对象
	 */
	@Action(value = "findSmstrashPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "smstrashManagement")
	public String findSmstrashPagerBySearchVo() throws Exception {
		gridPage = new GridPage(smstrashService.findSmstrashPagerBySearchVo(
				searchSmstrashVo, page, rows, sidx, sord));
		return SUCCESS;
	}

	public Smstrash getSmstrash() {
		return smstrash;
	}

	public void setSmstrash(Smstrash smstrash) {
		this.smstrash = smstrash;
	}

	public SearchSmstrashVo getSearchSmstrashVo() {
		return searchSmstrashVo;
	}

	public void setSearchSmstrashVo(SearchSmstrashVo searchSmstrashVo) {
		this.searchSmstrashVo = searchSmstrashVo;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIdstr() {
		return idstr;
	}

	public void setIdstr(String idstr) {
		this.idstr = idstr;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

}
