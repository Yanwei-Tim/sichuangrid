package com.tianque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.MyContacter;
import com.tianque.domain.User;
import com.tianque.userAuth.api.ContacterDubboService;

@SuppressWarnings("serial")
@Controller("myContacterController")
@Scope("prototype")
@Transactional
public class MyContacterController extends BaseAction {

	@Autowired
	private ContacterDubboService contacterDubboService;

	private MyContacter myContacter;

	public String dispatch() {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			return prepareAddMyContacter();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			return prepareUpdateMyContacter();
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			return viewMyContacter();
		}

		return SUCCESS;
	}

	private String prepareAddMyContacter() {
		myContacter = new MyContacter();
		return SUCCESS;
	}

	private String prepareUpdateMyContacter() {
		if (myContacter != null) {
			myContacter = contacterDubboService.getSimpleMyContacterById(myContacter.getId());
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "viewMyContact")
	private String viewMyContacter() {
		if (myContacter != null) {
			myContacter = contacterDubboService.getSimpleMyContacterById(myContacter.getId());
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "addMyContact")
	public String addMyContacter() {
		if (!validate(myContacter)) {
			return ERROR;
		}
		myContacter.setOwner(ThreadVariable.getUser());
		myContacter = contacterDubboService.addMyContacter(myContacter);
		return SUCCESS;
	}

	@PermissionFilter(ename = "updateMyContact")
	public String updateMyContacter() {
		if (!validate(myContacter)) {
			return ERROR;
		}
		myContacter.setOwner(ThreadVariable.getUser());
		myContacter = contacterDubboService.updateMyContacter(myContacter);
		return SUCCESS;
	}

	@PermissionFilter(ename = "deleteMyContact")
	public String deleteMyContacter() {
		if (myContacter != null) {
			if (!contacterDubboService.deleteContacterById(myContacter.getId())) {
				this.errorMessage = "该信息已在其他地方引用，不能删除!";
				return ERROR;
			}
		} else {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		return SUCCESS;
	}

	public String findMyContacters() {
		User user = ThreadVariable.getUser();
		PageInfo<MyContacter> myContacts = contacterDubboService.findMyContacterByOwnerId(user.getId(),
				page, rows, sidx, sord);

		gridPage = new GridPage(myContacts);
		return SUCCESS;
	}

	@PermissionFilter(ename = "searchMyContact")
	public String searchMyContacters() {
		myContacter.setOwner(ThreadVariable.getUser());
		PageInfo<MyContacter> myContacts = contacterDubboService.searchMyContacter(myContacter, page,
				rows, sidx, sord);
		gridPage = new GridPage(myContacts);
		return SUCCESS;
	}

	private boolean validate(MyContacter myContact) {
		if (myContact == null) {
			this.errorMessage = "参数错误!";
			return false;
		}
		if (myContact.getName() == null || "".equals(myContact.getName().trim())) {
			this.errorMessage = "姓名不能为空";
			return false;
		}
		if (myContact.getMobileNumber() == null || "".equals(myContact.getMobileNumber().trim())) {
			this.errorMessage = "联系手机不能为空";
			return false;
		}
		return true;
	}

	public MyContacter getMyContacter() {
		return myContacter;
	}

	public void setMyContacter(MyContacter myContact) {
		this.myContacter = myContact;
	}

}
