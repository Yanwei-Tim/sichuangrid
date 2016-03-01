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

import com.tianque.core.base.BaseAction;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WeChatType;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.domain.user.WeChatMenu;
import com.tianque.plugin.weChat.domain.user.WeChatSource;
import com.tianque.plugin.weChat.service.TencentUserService;
import com.tianque.plugin.weChat.service.WeChatMenuService;
import com.tianque.plugin.weChat.service.WeChatSourceService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.PropertyDictDubboService;

/**
 * 微信菜单
 *  @date  2014年5月13日
 */
@Namespace("/weChatMenuManage")
@Scope("prototype")
@Controller("weChatMenuController")
public class WeChatMenuController extends BaseAction {
	private static Logger logger = LoggerFactory.getLogger(WeChatMenuController.class);
	@Autowired
	private WeChatMenuService weChatMenuService;
	@Autowired
	private OrganizationDubboService organizationService;
	@Autowired
	private TencentUserService tencentUserService;
	@Autowired
	private WeChatSourceService weChatSourceService;
	// 获取字典条目
	@Autowired
	private PropertyDictDubboService propertyDictService;
	private List<TencentUser> listTencentUser;
	private List<WeChatSource> listWeChatSources;

	private WeChatMenu weChatMenu;
	/**菜单json**/
	private String menuJson;
	private List<WeChatMenu> weChatMenuParentList;
	private List<WeChatMenu> weChatMenuChildList;
	//素材类型
	private long sourceType;

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/wechat/menu/weChatMenuDlg.jsp"),
			@Result(name = "addOrUpdateWeChatMenu", location = "/wechat/menu/weChatMenuAddDlg.jsp"),
			@Result(name = "weChatMenuNews", location = "/wechat/menu/weChatMenuNewsDlg.jsp"),
			@Result(name = "search", location = "/wechat/menu/searchWeChatMenuDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String dispatch() {
		try {
			if ("addWeChatMenu".equals(mode)) {
				listTencentUser = tencentUserService.getTencentUserListByOrgId(weChatMenu.getOrg()
						.getId());
				return "addOrUpdateWeChatMenu";
			} else if ("updateWeChatMenu".equals(mode)) {
				listTencentUser = tencentUserService.getTencentUserListByOrgId(weChatMenu.getOrg()
						.getId());
				weChatMenuParentList = weChatMenuService.getWeChatMenuByWeChatUserIdAndRank(1L,
						weChatMenu.getWeChatUserId());
				weChatMenuChildList = weChatMenuService.getWeChatMenuByWeChatUserIdAndRank(2L,
						weChatMenu.getWeChatUserId());
				return "addOrUpdateWeChatMenu";
			} else if (DialogMode.ADD_MODE.equals(mode)) {//微信菜单 绑定  文本，图片，图文，语音
				listWeChatSources = weChatSourceService.getWeChatSourceByOrgIdAndSourceType(
						weChatMenu.getOrg().getId(),
						getWeChatSourceType(WeChatType.MAP.get((int) sourceType)).getId());
				weChatMenu = weChatMenuService.getWeChatMenuById(weChatMenu.getId());
				weChatMenu.setSourceTypeDict(getWeChatSourceType(WeChatType.MAP
						.get((int) sourceType)));
				if ("图文".equals(WeChatType.MAP.get((int) sourceType))) {
					if (weChatMenu.getSourceID() != null && !"".equals(weChatMenu.getSourceID())) {
						if (weChatMenu.getSourceID().indexOf(",") == -1) {
							weChatMenu.setSourceID("");
							weChatMenu.setSourceDescription("");
						}
						for (int j = 0; j < listWeChatSources.size(); j++) {
							if (weChatMenu.getSourceID().indexOf(
									listWeChatSources.get(j).getId().toString()) > -1) {
								listWeChatSources.remove(j);
								j--;
							}
						}
					}
					return "weChatMenuNews";
				} else
					return SUCCESS;
			} else if (DialogMode.SEARCH_MODE.equals(mode)) {
				listTencentUser = tencentUserService.getTencentUserListByOrgId(weChatMenu.getOrg()
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
	 * 菜单列表
	 * @return
	 */
	@Action(value = "findWeChatMenu", results = {
			@Result(type = "json", name = "success", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String findWeChatMenu() {
		try {
			if (weChatMenu.getWeChatUserId() != null && !"".equals(weChatMenu.getWeChatUserId()))
				weChatMenu.setWeChatUserId("'" + weChatMenu.getWeChatUserId() + "'");
			else {
				String weChatUserIdTemp = "";
				List<TencentUser> tencentUserList = tencentUserService
						.getTencentUserListByOrgId(weChatMenu.getOrg().getId());
				if (tencentUserList.size() > 0) {
					for (int i = 0; i < tencentUserList.size(); i++) {
						if (i == tencentUserList.size() - 1)
							weChatUserIdTemp += "'" + tencentUserList.get(i).getWeChatUserId()
									+ "'";
						else
							weChatUserIdTemp += "'" + tencentUserList.get(i).getWeChatUserId()
									+ "'" + ",";
					}
				} else {
					weChatUserIdTemp = "'no'";
				}
				weChatMenu.setWeChatUserId(weChatUserIdTemp);
			}

			PageInfo<WeChatMenu> weChatMenuList = weChatMenuService.findWeChatMenu(weChatMenu,
					page, rows, sidx, sord);
			gridPage = new GridPage(weChatMenuList);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("findWeChatMenuERROR", e);
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/**
	 * 菜单绑定素材
	 * @return
	 */

	@Action(value = "updateWeChatMenu", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String updateWeChatMenu() {
		try {
			if (weChatMenu.getSourceID() == null || "-1".equals(weChatMenu.getSourceID())
					|| "".equals(weChatMenu.getSourceID())) {
				weChatMenu.setSourceID(null);
				weChatMenu.setSourceDescription("");
				weChatMenu.getSourceTypeDict().setId(null);
			}
			int flag = weChatMenuService.updateWeChatMenu(weChatMenu);
			if (flag > 0)
				return SUCCESS;
			else {
				this.errorMessage = "菜单绑定素材失败！";
				return ERROR;
			}
		} catch (ServiceException s) {
			this.errorMessage = s.getMessage();
			logger.error("菜单绑定素材失败：" + s.getMessage());
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("菜单绑定素材失败：" + e.getMessage());
			return ERROR;
		}

	}

	@Action(value = "addWeChatMenu", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(type = "json", name = "error", params = { "root", "errorMessage" }) })
	public String addWeChatMenu() {
		try {
			Long flag = weChatMenuService.addWeChatMenu(menuJson, weChatMenu.getWeChatUserId());
			if (flag > 0)
				return SUCCESS;
			else {
				this.errorMessage = "微信菜单创建失败！";
				return ERROR;
			}
		} catch (ServiceException s) {
			this.errorMessage = s.getMessage();
			logger.error("微信菜单创建失败：" + s.getMessage());
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("微信菜单创建失败：" + e.getMessage());
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
		listTencentUser = tencentUserService.getTencentUserListByOrgId(weChatMenu.getOrg().getId());
		listWeChatSources = weChatSourceService.getWeChatSourceByOrgIdAndSourceType(weChatMenu
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

	public WeChatMenu getWeChatMenu() {
		return weChatMenu;
	}

	public void setWeChatMenu(WeChatMenu weChatMenu) {
		this.weChatMenu = weChatMenu;
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

	public long getSourceType() {
		return sourceType;
	}

	public void setSourceType(long sourceType) {
		this.sourceType = sourceType;
	}

	public String getMenuJson() {
		return menuJson;
	}

	public void setMenuJson(String menuJson) {
		this.menuJson = menuJson;
	}

	public List<WeChatMenu> getWeChatMenuParentList() {
		return weChatMenuParentList;
	}

	public void setWeChatMenuParentList(List<WeChatMenu> weChatMenuParentList) {
		this.weChatMenuParentList = weChatMenuParentList;
	}

	public List<WeChatMenu> getWeChatMenuChildList() {
		return weChatMenuChildList;
	}

	public void setWeChatMenuChildList(List<WeChatMenu> weChatMenuChildList) {
		this.weChatMenuChildList = weChatMenuChildList;
	}
}
