package com.tianque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Proclamation;
import com.tianque.domain.User;
import com.tianque.service.ProclamationService;
import com.tianque.sysadmin.service.PermissionService;

@Scope("prototype")
@Transactional
@Controller("proclamationController")
public class ProclamationController extends BaseAction {

	@Autowired
	private ProclamationService proclamationService;

	@Autowired
	private PermissionService permissionService;

	private Proclamation proclamation;

	private Long proclamationId;

	private User user;

	public String proclamationsList() throws Exception {
		user = permissionService.getFullUserById(ThreadVariable.getSession()
				.getUserId());
		return SUCCESS;
	}

	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			proclamation = new Proclamation();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)
				|| DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			proclamation = proclamationService
					.getProclamationById(proclamationId);
		}
		return SUCCESS;
	}

	public String findProclamations() throws Exception {
		gridPage = new GridPage(proclamationService.findProclamations(page,
				rows, sidx, sord));
		return SUCCESS;
	}

	public String addProclamation() throws Exception {
		proclamation = proclamationService.addProclamation(proclamation);
		return SUCCESS;
	}

	public String updateProclamation() throws Exception {
		proclamation = proclamationService.updateProclamation(proclamation);
		return SUCCESS;
	}

	public String validateHasDisplay() throws Exception {
		Proclamation proclamation = proclamationService
				.getDisplayProclamation();
		if (DialogMode.ADD_MODE.equals(mode) && proclamation != null) {
			return ERROR;
		} else if (DialogMode.EDIT_MODE.equals(mode) && proclamation != null
				&& !proclamation.getId().equals(proclamationId)) {
			return ERROR;
		}
		return SUCCESS;
	}

	public String getDisplayProclamation() throws Exception {
		proclamation = proclamationService.getDisplayProclamation();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: getDisplayProclamationForMobile
	 * @Description: TODO为手机端新增查询系统公告方法
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-7-8 下午02:26:08
	 */
	public String getDisplayProclamationForMobile() throws Exception {
		proclamation = proclamationService.getDisplayProclamationForMobile();
		return SUCCESS;
	}

	public Proclamation getProclamation() {
		return proclamation;
	}

	public void setProclamation(Proclamation proclamation) {
		this.proclamation = proclamation;
	}

	public Long getProclamationId() {
		return proclamationId;
	}

	public void setProclamationId(Long proclamationId) {
		this.proclamationId = proclamationId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
