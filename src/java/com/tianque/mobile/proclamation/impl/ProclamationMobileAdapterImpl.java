package com.tianque.mobile.proclamation.impl;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ProclamationController;
import com.tianque.domain.Proclamation;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.proclamation.ProclamationMobileAdapter;

/**
 * 
 * @ClassName: ProclamationMobileAdapterImpl
 * @Description: TODO手机端获取系统公告 实现类
 * @author wanggz
 * @date 2014-7-8 下午01:45:00
 */

@Transactional
@Scope("request")
@Controller("proclamationMobileAdapter")
@Namespace("/mobile/proclamationManage")
public class ProclamationMobileAdapterImpl extends BaseMobileAction implements
		ProclamationMobileAdapter {
	@Autowired
	private ProclamationController proclamationController;

	private Proclamation proclamation;

	@Override
	@Action(value = "findProclamationForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"proclamation", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findProclamationForMobile() throws Exception {
		proclamationController.getDisplayProclamationForMobile();
		proclamation = proclamationController.getProclamation();
		return SUCCESS;
	}

	public Proclamation getProclamation() {
		return proclamation;
	}

	public void setProclamation(Proclamation proclamation) {
		this.proclamation = proclamation;
	}

}
