package com.tianque.plugin.weChat.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WeChatType;
import com.tianque.plugin.weChat.domain.user.KeyWord;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.domain.user.WeChatSource;
import com.tianque.plugin.weChat.service.KeyWordService;
import com.tianque.plugin.weChat.service.TencentUserService;
import com.tianque.plugin.weChat.service.WeChatSourceService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.PropertyDictDubboService;

/**
 * 关键词
 *  @date  2014年5月13日
 */
@Namespace("/keyWordManage")
@Scope("prototype")
@Controller("keyWordController")
public class KeyWordController extends BaseAction {
	private static Logger logger = LoggerFactory.getLogger(KeyWordController.class);

	@Autowired
	private KeyWordService keyWordService;
	@Autowired
	private OrganizationDubboService organizationService;
	@Autowired
	private TencentUserService tencentUserService;
	@Autowired
	private WeChatSourceService weChatSourceService;
	// 获取字典条目
	@Autowired
	private PropertyDictDubboService propertyDictService;
	private KeyWord keyWord;
	private List<TencentUser> listTencentUser;
	private List<WeChatSource> listWeChatSources;

	private String keyWordIdS;
	//素材类型
	private long sourceType;

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/wechat/keyWord/keyWordDlg.jsp"),
			@Result(name = "keyWordNews", location = "/wechat/keyWord/keyWordNewsDlg.jsp"),

			@Result(name = "search", location = "/wechat/keyWord/searchKeyWordDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String dispatch() {
		try {
			if (DialogMode.ADD_MODE.equals(mode)) {//添加文本图片图文语音（关键字）
				this.procOrganization();
				listTencentUser = tencentUserService.getTencentUserListByOrgId(keyWord.getOrg()
						.getId());
				listWeChatSources = weChatSourceService.getWeChatSourceByOrgIdAndSourceType(keyWord
						.getOrg().getId(),
						getWeChatSourceType(WeChatType.MAP.get((int) sourceType)).getId());
				keyWord.setSourceTypeDict(getWeChatSourceType(WeChatType.MAP.get((int) sourceType)));
				if (WeChatType.MAP.get((int) sourceType).equals("图文"))
					return "keyWordNews";
				else
					return SUCCESS;
			} else if (DialogMode.EDIT_MODE.equals(mode)) {//修改
				keyWord = keyWordService.getKeyWordById(keyWord.getId());
				this.procOrganization();
				listTencentUser = tencentUserService.getTencentUserListByOrgId(keyWord.getOrg()
						.getId());
				listWeChatSources = weChatSourceService.getWeChatSourceByOrgIdAndSourceType(keyWord
						.getOrg().getId(), keyWord.getSourceTypeDict().getId());

				if (propertyDictService.getPropertyDictById(keyWord.getSourceTypeDict().getId())
						.getDisplayName().equals("图文")) {
					for (int j = 0; j < listWeChatSources.size(); j++) {
						if (keyWord.getSourceId().indexOf(
								listWeChatSources.get(j).getId().toString()) > -1) {
							listWeChatSources.remove(j);
							j--;
						}
					}
					return "keyWordNews";
				} else
					return SUCCESS;
			} else if (DialogMode.SEARCH_MODE.equals(mode)) {
				procOrganization();
				listTencentUser = tencentUserService.getTencentUserListByOrgId(keyWord.getOrg()
						.getId());
				return "search";
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("关键词跳转页面错误", e);
			return ERROR;
		}
	}

	/**
	 * 验证当前层级下有没有微信号或是素材
	 * @return
	 */
	@Action(value = "validataSourceOrWeChatUser", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String validataSourceOrWeChatUser() {
		listTencentUser = tencentUserService.getTencentUserListByOrgId(keyWord.getOrg().getId());
		listWeChatSources = weChatSourceService.getWeChatSourceByOrgIdAndSourceType(keyWord
				.getOrg().getId(), getWeChatSourceType(WeChatType.MAP.get((int) sourceType))
				.getId());
		if (listTencentUser.size() == 0) {
			this.errorMessage = "当前层级下还未添加微信公共账号！";
			return ERROR;
		}
		if (listWeChatSources.size() == 0) {
			this.errorMessage = "当前层级下还未添加" + WeChatType.MAP.get((int) sourceType) + "素材！";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 关键词列表
	 * @return
	 */
	@Action(value = "findkeyWord", results = {
			@Result(type = "json", name = "success", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String findKeyWord() {
		try {
			PageInfo<KeyWord> keyWordList = keyWordService.findKeyWord(keyWord, page, rows, sidx,
					sord);
			gridPage = new GridPage(keyWordList);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("findKeyWordERROR", e);
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/**
	 * 新增关键词
	 * @return
	 */

	@Action(value = "addKeyWord", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String addKeyWord() {
		try {
			Long flag = keyWordService.addKeyWord(keyWord);
			if (flag > 0)
				return SUCCESS;
			else {
				this.errorMessage = "关键词添加失败！";
				return ERROR;
			}
		} catch (ServiceException s) {
			this.errorMessage = s.getMessage();
			logger.error("关键词添加失败：" + s.getMessage());
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("关键词添加失败：" + e.getMessage());
			return ERROR;
		}

	}

	@Action(value = "updateKeyWord", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String updateKeyWord() {
		try {
			int flag = keyWordService.updateKeyWord(keyWord);
			if (flag > 0)
				return SUCCESS;
			else {
				this.errorMessage = "关键词修改失败！";
				return ERROR;
			}
		} catch (ServiceException s) {
			this.errorMessage = s.getMessage();
			logger.error("关键词修改失败：" + s.getMessage());
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("关键词修改失败：" + e.getMessage());
			return ERROR;
		}

	}

	@Action(value = "deleteKeyWord", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String deleteKeyWord() {
		try {
			int flag = keyWordService.deleteKeyWord(keyWordIdS);
			if (flag > 0)
				return SUCCESS;
			else {
				this.errorMessage = "关键词删除失败！";
				return ERROR;
			}
		} catch (ServiceException s) {
			this.errorMessage = s.getMessage();
			logger.error("关键词删除失败：" + s.getMessage());
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("关键词删除失败：" + e.getMessage());
			return ERROR;
		}

	}

	/**
	 * 验证关键词是不是已经存在
	 * @return
	 */
	@Action(value = "validateKeyWord", results = {
			@Result(name = "success", type = "json", params = { "root", "keyWord",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String validateKeyWord() {
		try {
			keyWord = keyWordService.validateKeyWordByWeChatUserIdAndKeyName(keyWord);
			return SUCCESS;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("微信关键词重复性验证错误", e);
			return ERROR;
		}
	}

	/**
	 * 获取组织机构层级
	 */
	private void procOrganization() {
		Organization organization = new Organization();
		organization.setId(keyWord.getOrg().getId());
		organization.setOrgName(ControllerHelper.getOrganizationRelativeName(organization.getId(),
				organizationService));
		keyWord.setOrg(organization);
	}

	/**
	 * 根据名称获取素材类型对应实体
	 */
	private PropertyDict getWeChatSourceType(String name_CN) {
		PropertyDict propertyDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(PropertyTypes.WECHAT_TYPE, name_CN);
		if (propertyDict == null)
			return null;
		else
			return propertyDict;
	}

	public List<WeChatSource> getListWeChatSources() {
		return listWeChatSources;
	}

	public void setListWeChatSources(List<WeChatSource> listWeChatSources) {
		this.listWeChatSources = listWeChatSources;
	}

	public KeyWord getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(KeyWord keyWord) {
		this.keyWord = keyWord;
	}

	public List<TencentUser> getListTencentUser() {
		return listTencentUser;
	}

	public void setListTencentUser(List<TencentUser> listTencentUser) {
		this.listTencentUser = listTencentUser;
	}

	public String getKeyWordIdS() {
		return keyWordIdS;
	}

	public void setKeyWordIdS(String keyWordIdS) {
		this.keyWordIdS = keyWordIdS;
	}

	public long getSourceType() {
		return sourceType;
	}

	public void setSourceType(long sourceType) {
		this.sourceType = sourceType;
	}

}
