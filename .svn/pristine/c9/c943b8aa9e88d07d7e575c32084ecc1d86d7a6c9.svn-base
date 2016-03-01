package com.tianque.sms.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.sms.domain.Smstrafficmanage;
import com.tianque.sms.domain.vo.SearchSmstrafficmanageVo;
import com.tianque.sms.service.SmstrafficmanageService;
import com.tianque.sms.util.SmstrafficUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 流量管理:服务前端控制类
 * 
 * @author
 * @date 2013-07-02 15:29:10
 */
@Namespace("/smstrafficmanageManage")
@Scope("request")
@Controller("smstrafficmanageController")
public class SmstrafficmanageController extends BaseAction {

	@Autowired
	private SmstrafficmanageService smstrafficmanageService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private Smstrafficmanage smstrafficmanage;
	private SearchSmstrafficmanageVo searchsmstrafficmanageVo;
	private String ids;
	private Long id;
	private Long organizationId;
	private Long num;

	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	@Action(value = "getSmstrafficmanageById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"smstrafficmanage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getSmstrafficmanageById() throws Exception {
		if (id == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		smstrafficmanage = smstrafficmanageService.get(id);
		return SUCCESS;
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	@Action(value = "addSmstrafficmanage", results = {
			@Result(name = "success", type = "json", params = { "root",
					"smstrafficmanage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "addSmstrafficmanage")
	public String addSmstrafficmanage() throws Exception {
		if (smstrafficmanage == null || smstrafficmanage.getOrgId() == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}

		Organization organization = organizationDubboService
				.getSimpleOrgById(smstrafficmanage.getOrgId());

		if (organization.getParentOrg() != null) {
			Long orgId = organization.getParentOrg().getId();
			Smstrafficmanage parentSmstrafficmanage = smstrafficmanageService
					.getSmstrafficmanagesByOrgId(orgId);
			Long[] traffic = getTraffic(smstrafficmanage);// 设置流量
			Long[] sum = getTraffic(parentSmstrafficmanage);// 总流量
			if (traffic[0] > sum[0]) {
				this.errorMessage = "电信流量不能超出" + (sum[0]);
				return ERROR;
			}
			if (traffic[1] > sum[1]) {
				this.errorMessage = "移动流量不能超出" + (sum[1]);
				return ERROR;
			}
			if (traffic[2] > sum[2]) {
				this.errorMessage = "联通流量不能超出" + (sum[2]);
				return ERROR;
			}
		}

		Smstrafficmanage trSmstrafficmanage = smstrafficmanageService
				.getSmstrafficmanagesByOrgId(smstrafficmanage.getOrgId());
		if (trSmstrafficmanage == null) {// 新增
			smstrafficmanage.setOrgInternalCode(organization
					.getOrgInternalCode());
			smstrafficmanage = smstrafficmanageService.add(smstrafficmanage);
		} else {// 修改
			smstrafficmanage.setId(trSmstrafficmanage.getId());
			smstrafficmanage.setOrgInternalCode(trSmstrafficmanage
					.getOrgInternalCode());
			smstrafficmanageService.updateSmstrafficmanageByOrgId(
					smstrafficmanage, smstrafficmanage.getOrgId());
		}

		return SUCCESS;
	}

	private Long[] getTraffic(Smstrafficmanage smstrafficmanage) {
		Long[] trafficNum = new Long[] { 0L, 0L, 0L };

		if (smstrafficmanage != null) {
			if (smstrafficmanage.getTelecomTraffic() != null)
				trafficNum[0] += smstrafficmanage.getTelecomTraffic();
			if (smstrafficmanage.getMobileTraffic() != null)
				trafficNum[1] += smstrafficmanage.getMobileTraffic();
			if (smstrafficmanage.getChinaUnicomTraffic() != null)
				trafficNum[2] += smstrafficmanage.getChinaUnicomTraffic();
		}

		return trafficNum;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	@Action(value = "updateSmstrafficmanage", results = {
			@Result(name = "success", type = "json", params = { "root",
					"smstrafficmanage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "updateSmstrafficmanage")
	public String updateSmstrafficmanage() throws Exception {
		if (smstrafficmanage == null || smstrafficmanage.getId() == null
				|| !checkOrganization(smstrafficmanage.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		smstrafficmanage = smstrafficmanageService.update(smstrafficmanage);
		return SUCCESS;
	}

	/**
	 * 根据ID删除实体对象
	 * 
	 * @param id
	 *            记录ID
	 */
	@Action(value = "deleteSmstrafficmanageById", results = {
			@Result(name = "success", type = "json", params = { "root", "id" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteSmstrafficmanage")
	public String deleteSmstrafficmanageById() throws Exception {
		if (id == null) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		smstrafficmanageService.delete(id);
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deleteSmstrafficmanageByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteSmstrafficmanage")
	public String deleteSmstrafficmanageByIds() throws Exception {
		if (ids == null || "".equals(ids.trim())) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		String[] idsStr = ids.split(",");
		Long[] idsLong = new Long[idsStr.length];
		String id = null;
		for (int i = 0; i < idsStr.length; i++) {
			id = idsStr[i];
			if (id != null && !"".equals(id.trim())) {
				idsLong[i] = Long.parseLong(idsStr[i]);
			}
		}
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		smstrafficmanageService.delete(idsLong);
		return SUCCESS;
	}

	@Action(value = "loadNum", results = {
			@Result(name = "success", type = "json", params = { "root",
					"smstrafficmanage" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String loadNum() throws Exception {
		smstrafficmanage = smstrafficmanageService
				.getSmstrafficmanagesByOrgId(organizationId);

		return SUCCESS;
	}

	@Actions(value = { @Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/interaction/newSMS/smstrafficmanageManage/smstrafficmanageViewDlg.jsp"),
			@Result(name = "set", location = "/interaction/newSMS/smstrafficmanageManage/setTrafficViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			// organizationDubboService.findOrganizationsByParentId(parentId);
		} else if ("set".equals(mode)) {
			if (smstrafficmanage == null) {
				this.errorMessage = "数据出错，请联系刷新页面";
				return ERROR;
			}
			Smstrafficmanage trSmstrafficmanage = smstrafficmanageService
					.getSmstrafficmanagesByOrgId(smstrafficmanage.getOrgId());
			if (trSmstrafficmanage != null) {
				smstrafficmanage = trSmstrafficmanage;
			}
			return "set";
		}
		return SUCCESS;
	}

	@Action(value = "findSmstrafficmanageByOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findSmstrafficmanageByOrgId() throws Exception {

		PageInfo<Smstrafficmanage> pageInfo = smstrafficmanageService
				.findSmstrafficmanageByOrgId(organizationId, page, rows, sidx,
						sord);
		if (organizationId == 1) {// 如果组织机构是1 显示中国层级数据
			pageInfo.getResult().add(
					0,
					smstrafficmanageService
							.getNowSmstrafficmanagesByOrgId(organizationId));
			pageInfo = new PageInfo(page, rows + 1,
					(int) (pageInfo.getTotalRowSize() + 1),
					pageInfo.getResult());
		}

		gridPage = new GridPage(pageInfo);

		return SUCCESS;
	}

	@Action(value = "findDeptTraffic", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findDeptTraffic() throws Exception {
		PageInfo pageInfo1 = smstrafficmanageService.findParentOrgTraffic(
				organizationId, SmstrafficUtil.SENDEND, page, rows, sidx, sord);

		PageInfo pageInfo2 = smstrafficmanageService.findOrgTraffic(
				organizationId, SmstrafficUtil.SENDEND, 1, rows, sidx, sord);
		List list = pageInfo1.getResult();
		List list2 = pageInfo2.getResult();
		list2.addAll(list);

		gridPage = new GridPage(new PageInfo(page, rows,
				(int) (pageInfo1.getTotalRowSize() + pageInfo2
						.getTotalRowSize()), list2));

		return SUCCESS;
	}

	private boolean checkOrganization(Organization org) {
		return (org != null && org.getId() != null);
	}

	public Smstrafficmanage getSmstrafficmanage() {
		return smstrafficmanage;
	}

	public void setSmstrafficmanage(Smstrafficmanage smstrafficmanage) {
		this.smstrafficmanage = smstrafficmanage;
	}

	public SearchSmstrafficmanageVo getSearchSmstrafficmanageVo() {
		return searchsmstrafficmanageVo;
	}

	public void setSearchSmstrafficmanageVo(
			SearchSmstrafficmanageVo searchsmstrafficmanageVo) {
		this.searchsmstrafficmanageVo = searchsmstrafficmanageVo;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public SearchSmstrafficmanageVo getSearchsmstrafficmanageVo() {
		return searchsmstrafficmanageVo;
	}

	public void setSearchsmstrafficmanageVo(
			SearchSmstrafficmanageVo searchsmstrafficmanageVo) {
		this.searchsmstrafficmanageVo = searchsmstrafficmanageVo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

}
