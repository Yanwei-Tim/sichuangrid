package com.tianque.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.domain.WorkContacter;
import com.tianque.domain.vo.ContacterVo;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.userAuth.api.ContacterDubboService;

@SuppressWarnings("serial")
@Controller("workContactController")
@Scope("prototype")
@Transactional
public class WorkContactController extends BaseAction {
	public static String key = "76682f743ae018364a082b2e87f2d2f5";
	public static String code = "U20HbAN0VCdQaAI2VTYEeA9sADlXN1IrWzwAMAFpU3wEY1RkBzcDIgRkBnwBIgdoB2U";
	@Autowired
	private ContacterDubboService contacterDubboService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PermissionService permissionService;
	private String leavel;
	private ContacterVo contacterVo;
	private Boolean isHasOrganition = false;
	private WorkContacter workContacter;

	@PermissionFilter(ename = "viewWorkContact")
	public String findWorkContacts() throws Exception {
		User user = ThreadVariable.getUser();
		if (user == null || user.getOrgInternalCode() == null
				|| user.getOrganization().getId() == null) {
			errorMessage = "用户信息不存在";
			return ERROR;
		}

		PageInfo<WorkContacter> workContacters = contacterDubboService
				.findWorkContacterForUpate(page, rows, sidx, sord,
						user.getOrganization(), leavel);
		PageInfo<ContacterVo> pageInfo = new PageInfo<ContacterVo>();
		pageInfo.setCurrentPage(workContacters.getCurrentPage());
		pageInfo.setPerPageSize(workContacters.getPerPageSize());
		pageInfo.setTotalRowSize(workContacters.getTotalRowSize());
		pageInfo.setResult(transToWorkContactVo(workContacters.getResult()));
		pageInfo = ControllerHelper
				.processAllOrgRelativeName(pageInfo, organizationDubboService,
						new String[] { "organization" }, null);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public String getContacterById() throws Exception {
		if (id != null) {
			workContacter = contacterDubboService.getWorkContacterById(id);
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "viewMyContact")
	public String findOtherWorkContacts() throws Exception {
		User users = ThreadVariable.getUser();
		if (users == null || users.getOrganization() == null
				|| users.getOrganization().getId() == null) {
			errorMessage = "用户信息不存在";
			return ERROR;
		}
		PageInfo<WorkContacter> workContacters = contacterDubboService
				.findWorkContacterForUpate(page, rows, sidx, sord,
						users.getOrganization(), leavel);
		PageInfo<ContacterVo> pageInfo = new PageInfo<ContacterVo>();
		pageInfo.setCurrentPage(workContacters.getCurrentPage());
		pageInfo.setPerPageSize(workContacters.getPerPageSize());
		pageInfo.setTotalRowSize(workContacters.getTotalRowSize());

		pageInfo.setResult(transToWorkContactVo(workContacters.getResult()));
		pageInfo = ControllerHelper
				.processAllOrgRelativeName(pageInfo, organizationDubboService,
						new String[] { "organization" }, null);
		for (ContacterVo contacterVo : pageInfo.getResult()) {
			User user = permissionService.getFullUserById(contacterVo
					.getFromUser().getId());
			contacterVo.setUserName(user.getUserName());
		}
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public String searchWorkContacters() throws Exception {
		User user = ThreadVariable.getUser();
		if (contacterVo == null) {
			contacterVo = new ContacterVo();
		}
		if (contacterVo.getOrganization() == null
				|| contacterVo.getOrganization().getId() == null) {
			List<Organization> list = organizationDubboService
					.findProvinceOrganizationsByOrgId(user.getOrganization()
							.getId());
			if (list != null && list.size() != 0) {
				contacterVo.setOrganization(list.get(0));
				isHasOrganition = true;
			}
		}
		if (user.getOrganization() != null
				&& user.getOrganization().getOrgLevel() != null
				&& user.getOrganization().getOrgLevel().getId() != null) {
			contacterVo.setCurrentUserOrgLeavel(user.getOrganization()
					.getOrgLevel().getId());
		}
		PageInfo<WorkContacter> workContacters = contacterDubboService
				.searchUsersByWorkContacter(contacterVo, isHasOrganition, page,
						rows, sidx, sord);

		workContacters = contacterVoSetOrg(workContacters);
		PageInfo<ContacterVo> pageInfo = new PageInfo<ContacterVo>();
		pageInfo.setCurrentPage(workContacters.getCurrentPage());
		pageInfo.setPerPageSize(workContacters.getPerPageSize());
		pageInfo.setTotalRowSize(workContacters.getTotalRowSize());
		pageInfo.setResult(transToWorkContactVo(workContacters.getResult()));
		pageInfo = ControllerHelper
				.processAllOrgRelativeName(pageInfo, organizationDubboService,
						new String[] { "organization" }, null);
		gridPage = new GridPage(pageInfo);

		return SUCCESS;
	}

	public String searchWorkContactersForMobile() throws Exception {
		User user = ThreadVariable.getUser();
		if (contacterVo == null) {
			contacterVo = new ContacterVo();
		}
		if (contacterVo.getOrganization() == null
				|| contacterVo.getOrganization().getId() == null) {
			Organization org = organizationDubboService.getSimpleOrgById(user
					.getOrganization().getId());
			if (org != null) {
				contacterVo.setOrganization(org);
				isHasOrganition = true;
			}
		}
		if (user.getOrganization() != null
				&& user.getOrganization().getOrgLevel() != null
				&& user.getOrganization().getOrgLevel().getId() != null) {
			contacterVo.setCurrentUserOrgLeavel(user.getOrganization()
					.getOrgLevel().getId());
		}
		PageInfo<WorkContacter> workContacters = contacterDubboService
				.searchUsersByWorkContacter(contacterVo, isHasOrganition, page,
						rows, sidx, sord);

		workContacters = contacterVoSetOrg(workContacters);
		PageInfo<ContacterVo> pageInfo = new PageInfo<ContacterVo>();
		pageInfo.setCurrentPage(workContacters.getCurrentPage());
		pageInfo.setPerPageSize(workContacters.getPerPageSize());
		pageInfo.setTotalRowSize(workContacters.getTotalRowSize());
		pageInfo.setResult(transToWorkContactVo(workContacters.getResult()));
		pageInfo = ControllerHelper
				.processAllOrgRelativeName(pageInfo, organizationDubboService,
						new String[] { "organization" }, null);
		gridPage = new GridPage(pageInfo);

		return SUCCESS;
	}

	private PageInfo<WorkContacter> contacterVoSetOrg(
			PageInfo<WorkContacter> pageInfo) {
		List<WorkContacter> list;
		if (pageInfo != null && pageInfo.getPerPageSize() != 0) {
			list = pageInfo.getResult();
			for (WorkContacter workContacter : list) {
				if (workContacter.getFromUser() != null
						&& workContacter.getFromUser().getOrganization() != null
						&& workContacter.getFromUser().getOrganization()
								.getId() != null) {
					Organization org = organizationDubboService
							.getSimpleOrgById(workContacter.getFromUser()
									.getOrganization().getId());
					workContacter.getFromUser().setOrganization(org);
				}
			}
			pageInfo.setResult(list);
		}
		return pageInfo;
	}

	private List<ContacterVo> transToWorkContactVo(
			List<WorkContacter> workContacters) {
		List<ContacterVo> wcVo = new ArrayList<ContacterVo>();
		for (WorkContacter workContacter : workContacters) {
			wcVo.add(new ContacterVo(workContacter));
		}
		return wcVo;
	}

	public ContacterVo getContacterVo() {
		return contacterVo;
	}

	public void setContacterVo(ContacterVo contacterVo) {
		this.contacterVo = contacterVo;
	}

	public String getLeavel() {
		return leavel;
	}

	public void setLeavel(String leavel) {
		this.leavel = leavel;
	}

	public Boolean getIsHasOrganition() {
		return isHasOrganition;
	}

	public void setIsHasOrganition(Boolean isHasOrganition) {
		this.isHasOrganition = isHasOrganition;
	}

	public WorkContacter getWorkContacter() {
		return workContacter;
	}

	public void setWorkContacter(WorkContacter workContacter) {
		this.workContacter = workContacter;
	}

}
