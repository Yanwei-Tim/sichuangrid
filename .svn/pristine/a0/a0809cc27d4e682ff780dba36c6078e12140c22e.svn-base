package com.tianque.sms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.MyGroup;
import com.tianque.sms.domain.SmsSendObject;
import com.tianque.sms.domain.SmsUplink;
import com.tianque.sms.domain.Smsquerycondition;
import com.tianque.sms.domain.Smstrafficmanage;
import com.tianque.sms.domain.vo.SearchSmsUplinkVo;
import com.tianque.sms.service.SmsSendObjectService;
import com.tianque.sms.service.SmsUplinkService;
import com.tianque.sms.service.SmsqueryconditionService;
import com.tianque.sms.service.SmstrafficmanageService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.ContacterDubboService;

/**
 * 短信发件箱:服务前端控制类
 * 
 * @author
 * @date 2013-07-02 09:53:32
 */
@Namespace("/smsUplinkManage")
@Scope("request")
@Controller("smsUplinkController")
public class SmsUplinkController extends BaseAction {

	private static Logger logger = LoggerFactory
			.getLogger(SmsUplinkController.class);

	@Autowired
	private SmsUplinkService smsUplinkService;
	@Autowired
	private SmsSendObjectService smsSendObjectService;
	@Autowired
	private SmsqueryconditionService smsqueryconditionService;
	@Autowired
	private SmstrafficmanageService smstrafficmanageService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ContacterDubboService contacterDubboService;

	private SmsUplink smsUplink;
	private SearchSmsUplinkVo searchSmsUplinkVo;
	private String ids;
	private Long id;
	private List<SmsSendObject> smsSendObjectList;
	private SmsSendObject smsSendObject;
	private Map<String, String> map;
	private int count;
	private boolean bol;
	private String html;
	private Long sendType;
	private String dialogName;
	private List<MyGroup> myGroups;

	/**
	 * 发送对象操作分发
	 */
	@Actions({ @Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/interaction/newSMS/smsUplink/smsUplinkViewDlg.jsp"),
			@Result(name = "sendObjects", location = "/interaction/newSMS/smsUplink/selectSendObjectDlg.jsp"),
			@Result(name = "search", location = "/interaction/newSMS/smsUplink/searchSmsUplinkDlg.jsp"),
			@Result(name = "sub", location = "/interaction/newSMS/smsUplink/smsSubUplinkList.jsp"),
			@Result(name = "viewSub", location = "/interaction/newSMS/smsUplink/smsSubUplinkViewDlg.jsp"),
			@Result(name = "selectAddressee", location = "/interaction/newSMS/smsUplink/selectContactsDlg.jsp") }) })
	public String dispatchOperate() throws Exception {
		if ("add".equals(mode)) {
			smsSendObjectList = smsSendObjectService.findSimpleSmsSendObjects();
		} else if ("search".equals(mode)) {
			return "search";
		} else if ("sub".equals(mode)) {
			return "sub";
		} else if ("sendObjects".equals(mode)) {
			smsSendObjectList = smsSendObjectService.findSimpleSmsSendObjects();
			return "sendObjects";
		} else if ("selectAddressee".equals(mode)) {
			myGroups = contacterDubboService.findMyGroupByOwnerId(ThreadVariable
					.getUser().getId());
			return "selectAddressee";
		} else if ("viewSub".equals(mode)) {
			if (null == id) {
				return "viewSub";
			}
			smsUplink = smsUplinkService.get(id);
			return "viewSub";
		}
		return SUCCESS;
	}

	/**
	 * 根据Id 查询发送对象
	 */
	@Action(value = "getSmsSendObjectTemplateById", results = {
			@Result(name = "success", type = "json", params = { "root", "html",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getSmsSendObjectTemplateById() throws Exception {
		smsSendObject = smsSendObjectService.get(id);
		if (!StringUtil.isStringAvaliable(smsSendObject.getTemplate())) {
			ids = "发送对象查询条件不存在，请联系管理员!";
			return SUCCESS;
		}
		// StringWriter writer = new StringWriter();
		// Configuration cfg = new Configuration();
		// String str = smsSendObject.getTemplate();
		// cfg.setTemplateLoader(new TempletLoader(str));
		// cfg.setDefaultEncoding("UTF-8");
		// Template template = cfg.getTemplate("");
		// Map<String, Object> root = new HashMap<String, Object>();
		// root.put("sms", smsSendObject);
		// template.process(root, writer);
		// html = writer.toString();

		List<Smsquerycondition> sqcList = smsqueryconditionService
				.findSmsqueryconditionsBySmsSendObjectId(smsSendObject.getId());
		Map<String, String> sqcMap = new HashMap<String, String>();
		for (Smsquerycondition sqc : sqcList) {
			sqcMap.put(sqc.getDescription(), sqc.getKey());
		}
		Pattern p = Pattern.compile("\\$\\{(.*?)\\}");
		Matcher m = p.matcher(smsSendObject.getTemplate());
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "map." + sqcMap.get(m.group(1)));
		}
		html = m.appendTail(sb).toString();
		return SUCCESS;
	}

	/**
	 * 查询短信发送对象人数
	 */
	@Action(value = "countSmsSendObjectNumber", results = {
			@Result(name = "success", type = "json", params = { "root", "count" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String countSmsSendObjectNumber() throws Exception {
		count = smsUplinkService.countSmsSendObjectNumber(map);
		return SUCCESS;
	}

	/**
	 * 验证短信发送数是否超额
	 */
	@Action(value = "validateSmsNumber", results = {
			@Result(name = "success", type = "json", params = { "root", "bol" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String validateSmsNumber() throws Exception {
		Smstrafficmanage smstrafficmanage = smstrafficmanageService
				.getSmstrafficmanagesByOrgId(ThreadVariable.getOrganization()
						.getId());
		count = smsUplinkService.countSmsSendObjectNumber(map);
		bol = (getSmsCount(smstrafficmanage) - getHasSmsCount(smstrafficmanage) - count) > 0;
		return SUCCESS;
	}

	@Action(value = "returnSms", results = {
			@Result(name = "success", type = "json", params = { "root", "bol" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String returnSms() throws Exception {
		if (smsUplink == null || smsUplink.getContent() == null
				|| !StringUtil.isStringAvaliable(smsUplink.getContent())) {
			logger.error("参数错误");
			this.errorMessage = "内容不能为空,请联系管理员";
			return ERROR;
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("sendType", "1");// 短信类型
		map.put("smsSendContent", smsUplink.getContent());// 短信内容
		map.put("smsSendId", smsUplink.getReceiverMobile());// 短信号码
		map.put("smsLevel", String.valueOf(smsUplink.getSmsLevel()));// 短信是否紧急

		bol = smsUplinkService.addSendSMSInfo(map);

		return SUCCESS;
	}

	/**
	 * 新增发送短信jobSql语句
	 */
	@Action(value = "addSendSMSJobSql", results = {
			@Result(name = "success", type = "json", params = { "root", "bol" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addSendSMSJobSql() throws Exception {
		if (map == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		if (!smsUplinkService.addSendSMSInfo(map)) {
			this.errorMessage = "操作失败，请联系管理员!";
			return ERROR;
		}
		bol = true;
		return SUCCESS;
	}

	@Action(value = "deleteSMS", results = {
			@Result(name = "success", type = "json", params = { "root", "bol" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteSMS() throws Exception {
		if (!StringUtil.isStringAvaliable(ids)) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		String[] idsStr = ids.split(",");
		Long[] idsLong = new Long[idsStr.length];
		for (int i = 0; i < idsStr.length; i++) {
			if (!StringUtil.isStringAvaliable(idsStr[i])) {
				continue;
			}
			idsLong[i] = Long.valueOf(idsStr[i]);
		}
		bol = smsUplinkService.updateDeleteStatusByIds(idsLong);
		return SUCCESS;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 */
	@Action(value = "findSmsUplinkPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findSmsUplinkPagerBySearchVo() throws Exception {
		if (null == searchSmsUplinkVo) {
			searchSmsUplinkVo = new SearchSmsUplinkVo();
		}
		gridPage = new GridPage(smsUplinkService.findPagerBySearchVo(
				searchSmsUplinkVo, page, rows, sidx, sord));
		return SUCCESS;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 */
	@Action(value = "findSubSmsUplinksBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findSubSmsUplinksBySearchVo() throws Exception {
		if (null == searchSmsUplinkVo) {
			searchSmsUplinkVo = new SearchSmsUplinkVo();
		}
		gridPage = new GridPage(smsUplinkService.findSubSmsUplinksBySearchVo(
				searchSmsUplinkVo, page, rows, sidx, sord));
		return SUCCESS;
	}

	/**
	 * 查询(提供分页、查找、排序功能)
	 */
	@Action(value = "findSmsSendPeopleInfoPager", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findSmsSendPeopleInfoPager() throws Exception {
		if (null == map) {

		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				smsUplinkService.findSmsSendPeopleInfoPager(map, page, rows,
						sidx, sord), organizationDubboService,
				new String[] { "orgId" }, ThreadVariable.getOrganization()
						.getId()));
		return SUCCESS;
	}

	private int getSmsCount(Smstrafficmanage smstrafficmanage) {
		if (null == smstrafficmanage) {
			return getIntNumber(0L) + getIntNumber(0L) + getIntNumber(0L);
		} else {
			Long chinaUnicom = smstrafficmanage.getChinaUnicomTraffic();
			Long telecom = smstrafficmanage.getTelecomTraffic();
			Long mobile = smstrafficmanage.getMobileTraffic();
			return getIntNumber(chinaUnicom) + getIntNumber(telecom)
					+ getIntNumber(mobile);
		}
	}

	private int getHasSmsCount(Smstrafficmanage smstrafficmanage) {
		if (null == smstrafficmanage) {
			return getIntNumber(0L) + getIntNumber(0L) + getIntNumber(0L);
		} else {
			Long hasTelecom = smstrafficmanage.getHasTelecomTraffic();
			Long hasMobile = smstrafficmanage.getHasMobileTraffic();
			Long hasChinaUnicom = smstrafficmanage.getHasChinaUnicomTraffic();
			return getIntNumber(hasTelecom) + getIntNumber(hasMobile)
					+ getIntNumber(hasChinaUnicom);
		}
	}

	private int getIntNumber(Long number) {
		if (null != number) {
			return number.intValue();
		}
		return 0;
	}

	public SmsUplink getSmsUplink(Smstrafficmanage smstrafficmanage) {
		if (null == smstrafficmanage) {

		}
		return smsUplink;
	}

	public void setSmsUplink(SmsUplink smsUplink) {
		this.smsUplink = smsUplink;
	}

	public SearchSmsUplinkVo getSearchSmsUplinkVo() {
		return searchSmsUplinkVo;
	}

	public void setSearchSmsUplinkVo(SearchSmsUplinkVo searchSmsUplinkVo) {
		this.searchSmsUplinkVo = searchSmsUplinkVo;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<SmsSendObject> getSmsSendObjectList() {
		return smsSendObjectList;
	}

	public void setSmsSendObjectList(List<SmsSendObject> smsSendObjectList) {
		this.smsSendObjectList = smsSendObjectList;
	}

	public SmsSendObject getSmsSendObject() {
		return smsSendObject;
	}

	public void setSmsSendObject(SmsSendObject smsSendObject) {
		this.smsSendObject = smsSendObject;
	}

	public Map getMap() {
		return map;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public SmsUplink getSmsUplink() {
		return smsUplink;
	}

	public Long getSendType() {
		return sendType;
	}

	public void setSendType(Long sendType) {
		this.sendType = sendType;
	}

	public String getDialogName() {
		return dialogName;
	}

	public void setDialogName(String dialogName) {
		this.dialogName = dialogName;
	}

	public List<MyGroup> getMyGroups() {
		return myGroups;
	}

	public void setMyGroups(List<MyGroup> myGroups) {
		this.myGroups = myGroups;
	}

}
