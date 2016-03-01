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
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.domain.user.WeChatSource;
import com.tianque.plugin.weChat.domain.user.WeChatSourceAttachment;
import com.tianque.plugin.weChat.service.KeyWordService;
import com.tianque.plugin.weChat.service.TencentUserService;
import com.tianque.plugin.weChat.service.WeChatSourceAttachmentService;
import com.tianque.plugin.weChat.service.WeChatSourceService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.PropertyDictDubboService;

/**
 * 素材
 *  @date  2014年5月13日
 */
@Namespace("/weChatSourceManage")
@Scope("prototype")
@Controller("weChatSourceController")
public class WeChatSourceController extends BaseAction {
	private static Logger logger = LoggerFactory.getLogger(WeChatSourceController.class);

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
	@Autowired
	private WeChatSourceAttachmentService weChatSourceAttachmentService;
	private WeChatSourceAttachment weChatSourceAttachment;

	private WeChatSource weChatSource;
	private List<TencentUser> listTencentUser;
	private List<WeChatSource> listWeChatSources;

	private String weChatSourceIds;
	//素材类型id
	private long sourceTypeId;
	//素材类型
	private long sourceType;

	@Action(value = "dispatch", results = {
			@Result(name = "addTextSource", location = "/wechat/source/textSourceDlg.jsp"),
			@Result(name = "addImageSource", location = "/wechat/source/imageSourceDlg.jsp"),
			@Result(name = "addNewsSource", location = "/wechat/source/newsSourceDlg.jsp"),
			@Result(name = "addVoiceSource", location = "/wechat/source/voiceSourceDlg.jsp"),
			@Result(name = "search", location = "/wechat/source/searchWeChatSourceDlg.jsp"),
			@Result(name = "playMedia", location = "/wechat/source/playMediaDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String dispatch() {
		if ("addSource".equals(mode)) {//添加素材
			this.procOrganization();
			listTencentUser = tencentUserService.getTencentUserListByOrgId(weChatSource.getOrg()
					.getId());
			weChatSource
					.setSourceTypeDict(getWeChatSourceType(WeChatType.MAP.get((int) sourceType)));
			if (sourceType == 1)
				return "addTextSource";
			if (sourceType == 2)
				return "addImageSource";
			if (sourceType == 3)
				return "addNewsSource";
			if (sourceType == 4)
				return "addVoiceSource";
			return ERROR;
		} else if ("updateSource".equals(mode)) {//修改素材
			weChatSource = weChatSourceService.getWeChatSource(weChatSource.getId());
			listTencentUser = tencentUserService.getTencentUserListByOrgId(weChatSource.getOrg()
					.getId());
			procOrganization();
			PropertyDict p = propertyDictService.getPropertyDictById(weChatSource
					.getSourceTypeDict().getId());
			if (!"".equals(p.getDisplayName()) && p.getDisplayName().equals("文本")) {
				return "addTextSource";
			} else if (!"".equals(p.getDisplayName()) && p.getDisplayName().equals("图片")) {
				return "addImageSource";
			} else if (!"".equals(p.getDisplayName()) && p.getDisplayName().equals("图文")) {
				return "addNewsSource";
			} else if (!"".equals(p.getDisplayName()) && p.getDisplayName().equals("语音")) {
				return "addVoiceSource";
			} else
				return null;
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {//高级搜索
			this.procOrganization();
			listTencentUser = tencentUserService.getTencentUserListByOrgId(weChatSource.getOrg()
					.getId());
			return "search";
		} else if ("playMedia".equals(mode)) {
			weChatSourceAttachment = weChatSourceAttachmentService
					.getWeChatSouceAttachmentById(weChatSourceAttachment.getAttachmentId());
			return "playMedia";
		} else {
			return ERROR;
		}
	}

	/**
	 * 素材列表
	 * @return
	 */
	@Action(value = "findWeChatSource", results = {
			@Result(type = "json", name = "success", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String findWeChatSource() {
		try {
			PageInfo<WeChatSource> keyWordList = weChatSourceService.findWeChatSource(weChatSource,
					page, rows, sidx, sord);
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
	 * 新增素材
	 * @return
	 */
	@Action(value = "addWeChatSource", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String addWeChatSource() {
		try {
			Long flag = weChatSourceService.addWeChatSource(weChatSource);
			if (flag > 0)
				return SUCCESS;
			else {
				this.errorMessage = "素材添加失败！";
				return ERROR;
			}
		} catch (ServiceException s) {
			this.errorMessage = s.getMessage();
			logger.error("素材添加失败：" + s.getMessage());
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("素材添加失败：" + e.getMessage());
			return ERROR;
		}

	}

	/**
	 * 修改素材
	 * @return
	 */

	@Action(value = "updateWeChatSource", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String updateWeChatSource() {
		try {
			int flag = weChatSourceService.updateWeChatSource(weChatSource);
			if (flag > 0)
				return SUCCESS;
			else {
				this.errorMessage = "素材修改失败！";
				return ERROR;
			}
		} catch (ServiceException s) {
			this.errorMessage = s.getMessage();
			logger.error("素材修改失败：" + s.getMessage());
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("素材修改失败：" + e.getMessage());
			return ERROR;
		}

	}

	@Action(value = "deleteWeChatSource", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String deleteWeChatSource() {
		try {
			int flag = weChatSourceService.deleteWeChatSource(weChatSourceIds);
			if (flag > 0)
				return SUCCESS;
			else {
				this.errorMessage = "素材删除失败！";
				return ERROR;
			}
		} catch (ServiceException s) {
			this.errorMessage = s.getMessage();
			logger.error("素材删除失败：" + s.getMessage());
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("素材删除失败：" + e.getMessage());
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
		listTencentUser = tencentUserService.getTencentUserListByOrgId(weChatSource.getOrg()
				.getId());
		if (listTencentUser.size() == 0) {
			this.errorMessage = "当前层级下还未添加微信公共账号！";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 获取组织机构层级
	 */
	private void procOrganization() {
		Organization organization = new Organization();
		organization.setId(weChatSource.getOrg().getId());
		organization.setOrgInternalCode(weChatSource.getOrg().getOrgInternalCode());
		organization.setOrgName(ControllerHelper.getOrganizationRelativeName(organization.getId(),
				organizationService));
		weChatSource.setOrg(organization);
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

	public List<TencentUser> getListTencentUser() {
		return listTencentUser;
	}

	public void setListTencentUser(List<TencentUser> listTencentUser) {
		this.listTencentUser = listTencentUser;
	}

	public WeChatSource getWeChatSource() {
		return weChatSource;
	}

	public void setWeChatSource(WeChatSource weChatSource) {
		this.weChatSource = weChatSource;
	}

	public String getWeChatSourceIds() {
		return weChatSourceIds;
	}

	public void setWeChatSourceIds(String weChatSourceIds) {
		this.weChatSourceIds = weChatSourceIds;
	}

	public long getSourceTypeId() {
		return sourceTypeId;
	}

	public void setSourceTypeId(long sourceTypeId) {
		this.sourceTypeId = sourceTypeId;
	}

	public long getSourceType() {
		return sourceType;
	}

	public void setSourceType(long sourceType) {
		this.sourceType = sourceType;
	}

	public WeChatSourceAttachment getWeChatSourceAttachment() {
		return weChatSourceAttachment;
	}

	public void setWeChatSourceAttachment(WeChatSourceAttachment weChatSourceAttachment) {
		this.weChatSourceAttachment = weChatSourceAttachment;
	}

}
