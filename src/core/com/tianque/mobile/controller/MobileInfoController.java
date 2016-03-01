package com.tianque.mobile.controller;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.DataExportHelper;
import com.tianque.datatransfer.ExcelWriter;
import com.tianque.domain.Organization;
import com.tianque.mobile.domain.MobileInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.MobileInfoDubboService;

@Controller("mobileInfoController")
@Scope("request")
public class MobileInfoController extends BaseAction {
	@Autowired
	private MobileInfoDubboService mobileInfoDubboService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private MobileInfo mobileInfo;
	private boolean pageOnly;
	private Long orgId;

	public String findMobileInfos() {

		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				mobileInfoDubboService.findMobileInfoForPage(page, rows, sidx,
						sord), organizationDubboService,
				new String[] { "organization" }, ThreadVariable
						.getOrganization().getId()));

		return SUCCESS;
	}

	public String dispatch() {
		if (!StringUtil.isStringAvaliable(mode)) {
			this.errorMessage = "参数错误";
		}
		if (DialogMode.ADD_MODE.equals(mode)) {
			mobileInfo = new MobileInfo();
			mobileInfo.setOrganization(new Organization());
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			mobileInfo = mobileInfoDubboService.getMobileInfoById(mobileInfo
					.getId());
			mobileInfo.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrg(mobileInfo.getOrganization(),
							organizationDubboService));
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			mobileInfo = new MobileInfo();
			return "search";
		}

		return SUCCESS;
	}

	public String addMobileInfo() {
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		mobileInfo.setOrgInternalCode(organization.getOrgInternalCode());
		mobileInfo.setOrganization(organization);
		mobileInfoDubboService.addMobileInfo(mobileInfo);
		return SUCCESS;
	}

	public String downloadMobileInfo() {
		if (mobileInfo == null && orgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			List records = getNeedExportDatas(page);
			try {
				inputStream = exportMobileInfo(records);
				downloadFileName = new String("手机IMSI相关信息清单".getBytes("gbk"),
						"ISO8859-1") + ".xls";
			} catch (Exception e) {
				errorMessage = e.getMessage();
				return ERROR;
			}
		}
		return SUCCESS;
	}

	public String findMobileInfosByQueryCondition() {
		if (mobileInfo == null) {
			this.errorMessage = "参数错误";
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		mobileInfo.setOrgInternalCode(organization.getOrgInternalCode());
		PageInfo<MobileInfo> pageInfo = ControllerHelper
				.processAllOrgRelativeName(mobileInfoDubboService
						.findMobileInfosByQueryCondition(mobileInfo, page,
								rows, sidx, sord), organizationDubboService,
						new String[] { "organization" }, ThreadVariable
								.getOrganization().getId());
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	private List<MobileInfo> getNeedExportDatas(int page) {
		if (pageOnly) {
			return mobileInfoDubboService.searchMobileInfosForExport(
					mobileInfo, page, rows, sidx, sord);
		} else {
			return mobileInfoDubboService.searchMobileInfosForExport(
					mobileInfo, null, null, sidx, sord);
		}
	}

	private InputStream exportMobileInfo(List<MobileInfo> records)
			throws Exception {
		ExcelWriter writer = constructExcelWriter();
		DataExportHelper helper = constructDataExportHelper();
		writer.addWorkSheet("手机IMSI相关信息");
		appendTitle(writer, "手机IMSI相关信息表");
		appendTableHead(writer);
		for (int index = 0; index < records.size(); index++) {
			MobileInfo record = records.get(index);
			appendRow(writer, helper, index + 3, record);
		}
		return new java.io.FileInputStream(writer.getExcelFile(UUID
				.randomUUID() + ".xls"));
	}

	private ExcelWriter constructExcelWriter() {
		ExcelWriter writer = new ExcelWriter();
		writer.setDefaultFont("宋体", (short) 12, false, false, false, false);
		writer.setDefaultCellBorder(true);
		writer.setDefaultHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);
		writer.setDefaultVerticalAlignment(ExcelWriter.VERTICAL_ALIGN_CENTER);
		return writer;
	}

	private DataExportHelper constructDataExportHelper() {
		DataExportHelper helper = new DataExportHelper();
		helper.setOrganizationService(organizationDubboService);
		return helper;
	}

	private void appendTitle(ExcelWriter write, String title) {
		write.addCell(0, 0, title).mergeTo(0, 21)
				.setFont("宋体", 20, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
	}

	private void appendTableHead(ExcelWriter writer) {
		writer.addCell(0, 0, "手机IMSI相关信息表").mergeTo(0, 21)
				.setFont("宋体", 20, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(1, 0, "IMSI号")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 0);
		writer.addCell(1, 1, "户主名称")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 1);
		writer.addCell(1, 2, "手机号码")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 2);
		writer.addCell(1, 3, "所属网格")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 3);
		writer.addCell(1, 4, "受理时间")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 4);
		writer.addCell(1, 5, "有效时间")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 5);
	}

	private void appendRow(ExcelWriter writer, DataExportHelper helper,
			int index, MobileInfo record) {
		if (!validateStrNull(record.getImsiNo()))
			writer.addCell(index, 0, record.getImsiNo());
		if (!validateStrNull(record.getName()))
			writer.addCell(index, 1, record.getName());
		if (!validateStrNull(record.getMobileNumber()))
			writer.addCell(index, 2, record.getMobileNumber());
		if (!validateObjectNull(record.getOrganization()))
			writer.addCell(index, 3, helper.getOrganizationRelativeName(record
					.getOrganization().getId()));
		if (!validateObjectNull(record.getAcceptTime()))
			writer.addCell(index, 4, dateFormat(record.getAcceptTime()));
		if (!validateObjectNull(record.getEffectivelyTime()))
			writer.addCell(index, 5, dateFormat(record.getEffectivelyTime()));
	}

	private boolean validateStrNull(String str) {
		return str == null || str.trim().equals("");
	}

	private boolean validateObjectNull(Object obj) {
		return obj == null;
	}

	private String dateFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public MobileInfo getMobileInfo() {
		return mobileInfo;
	}

	public void setMobileInfo(MobileInfo mobileInfo) {
		this.mobileInfo = mobileInfo;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
}
