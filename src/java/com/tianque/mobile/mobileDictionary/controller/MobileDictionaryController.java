package com.tianque.mobile.mobileDictionary.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.mobile.mobileDictionary.domain.MobileDictionary;
import com.tianque.mobile.mobileDictionary.service.MobileDictionaryService;

@Controller("mobileDictionaryController")
@Namespace("/mobileDictionary/mobileDictionaryManage")
@Scope("prototype")
public class MobileDictionaryController extends BaseAction {

	@Autowired
	private MobileDictionaryService mobileDictionaryService;

	private MobileDictionary mobileDictionary;

	@Action(value = "findMobileDictionary", results = {
			@Result(name = "success", type = "json", params = { "root",
					"mobileDictionary" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findMobileDictionary() throws Exception {
		if(mobileDictionary==null||mobileDictionary.getType()==null){
			errorMessage="参数错误";
			return ERROR;
		}
		List<MobileDictionary> lists = mobileDictionaryService
				.findAllMobileDictionary(mobileDictionary);
		if (lists.size() > 0) {
			mobileDictionary = lists.get(0);
		} else {
			mobileDictionary = new MobileDictionary();
		}
		return SUCCESS;
	}

	public MobileDictionary getMobileDictionary() {
		return mobileDictionary;
	}

	public void setMobileDictionary(MobileDictionary mobileDictionary) {
		this.mobileDictionary = mobileDictionary;
	}
}