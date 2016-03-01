package com.tianque.sysadmin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModelType;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.Organization;
import com.tianque.domain.Role;
import com.tianque.domain.User;
import com.tianque.domain.vo.DownLoadUserAbout;
import com.tianque.domain.vo.UserCountAboutVo;
import com.tianque.domain.vo.UserCountVo;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;

@Scope("request")
@Controller("userCountController")
public class UserCountController extends SearchBaseAction {
	public final static Logger logger = LoggerFactory
			.getLogger(UserCountController.class);
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private boolean pageOnly;// 是否导出当前页
	private Long organizationId;
	/**
	 * 层级类型: 如果是本成绩accountType=0 ,如果是直属下辖accountType=1 ,如果是所有下辖accountType=2
	 * 如果是全部accountType=3
	 */
	private Long accountType;
	
	private Long pcOrMobile;
	private Date beginLoginTime;
	private Date endLoginTime;
	private Date beginCreateDate;
	private Date endCreateDate;
	private Long isSelectLoginTime;
	private Date beginActivationTime;
	private Date endActivationTime;
	private Long isSelectActivationTime;
	/***
	 * 账号统计
	 * 
	 * @return
	 */
	public String userCountList() throws Exception {
		if (organizationId == null || accountType == null) {
			gridPage = new GridPage(new PageInfo<User>());
			return SUCCESS;
		}
		PageInfo<UserCountVo> pageInfo = permissionService.findUserCount(
				organizationId, accountType, page, rows, sidx, sord);
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				pageInfo, organizationDubboService,
				new String[] { "organization" }, null));
		return SUCCESS;
	}
	
	public String findUsersAboutUserCount() throws Exception{
		PageInfo<UserCountAboutVo> pageInfo = permissionService.findUsersAboutUserCount(
				organizationId,beginLoginTime,endLoginTime,beginCreateDate,endCreateDate, page, rows, sidx, sord, pcOrMobile, isSelectLoginTime, beginActivationTime, endActivationTime, isSelectActivationTime);
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				pageInfo, organizationDubboService,
				new String[] { "organization" }, null));
		return SUCCESS;
	}

	/**
	 * 账号统计导出
	 */

//	public List<UserCountVo> getNeedExportDatas(int page) {
//		if (organizationId == null || accountType == null) {
//			return null;
//		}
//		if (pageOnly) {
//			return permissionService.findUserCount(organizationId, accountType,
//					page, rows, sidx, sord).getResult();
//		} else {
//			return permissionService.findUserCount(organizationId, accountType,
//					1, Integer.MAX_VALUE, sidx, sord).getResult();
//		}
//	}
	
	/**
	 * 账号统计导出
	 */

	public List<DownLoadUserAbout> getNeedExportDatas(int page) {
		if (organizationId == null) {
			return null;
		}
		PageInfo<UserCountAboutVo> pageInfo = null;
		if (pageOnly) {
			pageInfo = permissionService.findUsersAboutUserCount(
					organizationId,beginLoginTime,endLoginTime,beginCreateDate,endCreateDate, page, rows, sidx, sord, pcOrMobile,isSelectLoginTime, beginActivationTime, endActivationTime, isSelectActivationTime);
		} else {
			pageInfo = permissionService.findUsersAboutUserCount(
					organizationId,beginLoginTime,endLoginTime,beginCreateDate,endCreateDate, 1, Integer.MAX_VALUE, sidx, sord, pcOrMobile,isSelectLoginTime, beginActivationTime, endActivationTime, isSelectActivationTime);
		}
		
		return changeExportMode(ControllerHelper.processAllOrgRelativeName(pageInfo, organizationDubboService, new String[] { "organization" }, null).getResult());
	}
	
	private List<DownLoadUserAbout> changeExportMode(List<UserCountAboutVo> list){
		List<DownLoadUserAbout> abouts = new ArrayList<DownLoadUserAbout>();
		for(UserCountAboutVo vo : list){
			DownLoadUserAbout about = new DownLoadUserAbout(vo);
			abouts.add(about);
		}
		return abouts;
	}
	
	@PermissionFilter(ename = "downUserCount")
	public String downloadUserCountAbout() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			List<DownLoadUserAbout> records = getNeedExportDatas(page);
			inputStream = ExcelExportHelper
					.exportDateToExcel(
							permissionService.getUserCountExportPopertyArray(),
							records);
			downloadFileName = new String("账号统计清单".getBytes("gbk"), "ISO8859-1")
					+ ".xls";
			systemLogService.log(com.vladium.logging.Logger.INFO,
					ModelType.USERCOUNTMANAGE, OperatorType.EXPORT,
					getCurrentUersName() + "在" + getCurrenOrg()
							+ " 导出当前页账号统计清单", null);
		}
		return STREAM_SUCCESS;
	}
	
	@PermissionFilter(ename = "downUserCount")
	public void downloadUserCountAboutAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
		} else {
			if (!pageOnly) {
				pageOnly = true;
				Integer count = permissionService.findUsersAboutUserCount(
						organizationId,beginLoginTime,endLoginTime,beginCreateDate,endCreateDate, 1, Integer.MAX_VALUE, sidx, sord, pcOrMobile, isSelectLoginTime, beginActivationTime, endActivationTime, isSelectActivationTime).getResult().size();
				String[][] excelDefines = permissionService
						.getUserCountExportPopertyArray();
				exportDataAll(count, excelDefines, "账号统计清单");
			}
			systemLogService
			.log(com.vladium.logging.Logger.INFO,
					ModelType.USERCOUNTMANAGE, OperatorType.EXPORT,
					getCurrentUersName() + "在" + getCurrenOrg()
					+ " 导出全部账号统计清单", null);
		}
	}
	
	@PermissionFilter(ename = "downUserCount")
	public String downloadUserCount() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
//			List<UserCountVo> records = getNeedExportDatas(page);
			List<UserCountVo> records = null;
			inputStream = ExcelExportHelper
					.exportDateToExcel(
							permissionService.getUserCountExportPopertyArray(),
							records);
			downloadFileName = new String("账号统计清单".getBytes("gbk"), "ISO8859-1")
					+ ".xls";
			systemLogService.log(com.vladium.logging.Logger.INFO,
					ModelType.USERCOUNTMANAGE, OperatorType.EXPORT,
					getCurrentUersName() + "在" + getCurrenOrg()
							+ " 导出当前页账号统计清单", null);
		}
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downUserCount")
	public void downloadUserCountAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
		} else {
			if (!pageOnly) {
				pageOnly = true;
				Integer count = permissionService
						.findUserCount(organizationId, accountType, 1,
								Integer.MAX_VALUE, sidx, sord).getResult()
						.size();
				String[][] excelDefines = permissionService
						.getUserCountExportPopertyArray();
				exportDataAll(count, excelDefines, "账号统计清单");
			}
			systemLogService
					.log(com.vladium.logging.Logger.INFO,
							ModelType.USERCOUNTMANAGE, OperatorType.EXPORT,
							getCurrentUersName() + "在" + getCurrenOrg()
									+ " 导出全部账号统计清单", null);
		}
	}

	// 日志获取当前登陆用户名
	private String getCurrentUersName() {
		return ThreadVariable.getSession().getUserName();
	}

	// 日志获取当前用户操作层级
	private String getCurrenOrg() {
		return organizationDubboService
				.getOrganizationRelativeNameByRootOrgIdAndOrgId(
						organizationId,
						OrganizationServiceHelper.getRootOrg(
								organizationDubboService).getId());
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getAccountType() {
		return accountType;
	}

	public void setAccountType(Long accountType) {
		this.accountType = accountType;
	}

	public Long getPcOrMobile() {
		return pcOrMobile;
	}

	public void setPcOrMobile(Long pcOrMobile) {
		this.pcOrMobile = pcOrMobile;
	}

	public Date getBeginLoginTime() {
		return beginLoginTime;
	}

	public void setBeginLoginTime(Date beginLoginTime) {
		this.beginLoginTime = beginLoginTime;
	}

	public Date getEndLoginTime() {
		return endLoginTime;
	}

	public void setEndLoginTime(Date endLoginTime) {
		this.endLoginTime = endLoginTime;
	}

	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public Long getIsSelectLoginTime() {
		return isSelectLoginTime;
	}

	public void setIsSelectLoginTime(Long isSelectLoginTime) {
		this.isSelectLoginTime = isSelectLoginTime;
	}

	public Date getBeginActivationTime() {
		return beginActivationTime;
	}

	public void setBeginActivationTime(Date beginActivationTime) {
		this.beginActivationTime = beginActivationTime;
	}

	public Date getEndActivationTime() {
		return endActivationTime;
	}

	public void setEndActivationTime(Date endActivationTime) {
		this.endActivationTime = endActivationTime;
	}

	public Long getIsSelectActivationTime() {
		return isSelectActivationTime;
	}

	public void setIsSelectActivationTime(Long isSelectActivationTime) {
		this.isSelectActivationTime = isSelectActivationTime;
	}
	
}