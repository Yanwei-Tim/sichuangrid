package com.tianque.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModuleConstants;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchConstructionUnitDao;
import com.tianque.datatransfer.DataExportHelper;
import com.tianque.datatransfer.ExcelWriter;
import com.tianque.domain.ConstructionUnit;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchConstructionUnitVo;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.vladium.logging.Logger;

@SuppressWarnings("serial")
@Controller("searchConstructionUnitController")
@Scope("prototype")
public class SearchConstructionUnitController extends BaseAction {
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private SearchConstructionUnitDao searchConstructionUnitDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	private Organization organization;
	private SearchConstructionUnitVo constructionUnitVo;
	private boolean pageOnly;
	private List<AutoCompleteData> autoCompleteDatas = new ArrayList<AutoCompleteData>();
	private String tag;
	private Long orgId;
	private String issueSerch = "";
	private ConstructionUnit constructionUnit;

	@PermissionFilter(ename = "searchConstructionUnit")
	public String searchConstructionUnit() throws Exception {
		if (constructionUnitVo == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		searchCommonality();
		return SUCCESS;
	}

	private void searchCommonality() {
		if (issueSerch.equalsIgnoreCase("issueSerch")) {
			organization.setId(organizationDubboService
					.getTownOrganizationId(organization.getId()));
		}
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		constructionUnitVo
				.setOrgInternalCode(organization.getOrgInternalCode());
		PageInfo<ConstructionUnit> pageInfo = ControllerHelper
				.processAllOrgRelativeName(searchConstructionUnitDao
						.searchConstructionUnit(constructionUnitVo, page, rows,
								sidx, sord), organizationDubboService,
						new String[] { "organization" }, organization.getId());
		gridPage = new GridPage(pageInfo);
	}

	@PermissionFilter(ename = "downloadConstructionUnit")
	public String downloadConstructionUnit() throws Exception {
		if (constructionUnitVo == null && organization.getId() == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			populateOrgCondition();
			List records = getNeedExportDatas(page);
			Organization org = organizationDubboService
					.getSimpleOrgById(organization.getId());
			if (organization.getId() != null) {

				if (org != null) {
					constructionUnitVo.setOrgInternalCode(org
							.getOrgInternalCode());
				} else {
					constructionUnitVo.setOrgInternalCode(null);
				}
			} else {
				constructionUnitVo.setOrgInternalCode(null);
			}
			inputStream = exportConstructionUnit(records);
			downloadFileName = new String("建筑施工单位清单".getBytes("gbk"),
					"ISO8859-1") + ".xls";
			systemLogService
					.log(Logger.INFO,
							ModuleConstants.CONSTRUCTION_UNIT,
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "在"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													org.getId(),
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId())
									+ " 导出建筑施工单位", ObjectToJSON
									.convertJSON(constructionUnitVo));
		}
		return SUCCESS;
	}

	private void populateOrgCondition() {
		if (constructionUnitVo == null) {
			constructionUnitVo = new SearchConstructionUnitVo();
		}
		if (organization.getId() != null) {
			Organization org = organizationDubboService
					.getSimpleOrgById(organization.getId());
			if (org != null) {
				constructionUnitVo.setOrgInternalCode(org.getOrgInternalCode());
			} else {
				constructionUnitVo.setOrgInternalCode(null);
			}
		} else {
			constructionUnitVo.setOrgInternalCode(null);
		}
	}

	private List<ConstructionUnit> getNeedExportDatas(int page) {
		if (constructionUnit != null) {
			constructionUnitVo.setIsEmphasis(constructionUnit.getIsEmphasis());
		}
		if (pageOnly) {
			return searchConstructionUnitDao.searchConstructionUnitForExport(
					constructionUnitVo, page, rows, sidx, sord);
		} else {
			return searchConstructionUnitDao.searchConstructionUnitForExport(
					constructionUnitVo, null, null, sidx, sord);
		}
	}

	private InputStream exportConstructionUnit(List<ConstructionUnit> records)
			throws Exception {
		ExcelWriter writer = constructExcelWriter();
		DataExportHelper helper = constructDataExportHelper();
		writer.addWorkSheet("建筑施工单位");
		appendTitle(writer, "建筑施工单位表");
		appendTableHead(writer);
		for (int index = 0; index < records.size(); index++) {
			ConstructionUnit record = records.get(index);
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
		helper.setPropertyDictService(propertyDictService);
		return helper;
	}

	private void appendTitle(ExcelWriter write, String title) {
		write.addCell(0, 0, title).mergeTo(0, 7)
				.setFont("宋体", 20, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
	}

	private void appendTableHead(ExcelWriter writer) {
		writer.addCell(0, 0, "建筑施工单位表").mergeTo(0, 7)
				.setFont("宋体", 20, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(1, 0, "工程名称")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 0);
		writer.addCell(1, 1, "片组片格")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 1);
		writer.addCell(1, 2, "工程地址")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 2);
		writer.addCell(1, 3, "开发单位")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 3);
		writer.addCell(1, 4, "开发单位联系人")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 4);
		writer.addCell(1, 5, "开发单位联系人手机")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 5);
		writer.addCell(1, 6, "总承包方")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 6);
		writer.addCell(1, 7, "总承包方联系人")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 7);
		writer.addCell(1, 8, "总承包方联系人电话")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 8);
		writer.addCell(1, 9, "劳务方")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 9);
		writer.addCell(1, 10, "劳务方联系人")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 10);
		writer.addCell(1, 11, "劳务方联系人电话")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 11);
		writer.addCell(1, 12, "工资支付日期 ")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 12);
		writer.addCell(1, 13, "实际发放工资总额")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 13);
		writer.addCell(1, 14, "应签订劳动合同人数")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 14);
		writer.addCell(1, 15, "未签订劳动合同人数")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 15);
		writer.addCell(1, 16, "从业人数")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 16);
		writer.addCell(1, 17, "开工时间")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 17);

		writer.addCell(1, 18, "备注")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 18);
	}

	private void appendRow(ExcelWriter writer, DataExportHelper helper,
			int index, ConstructionUnit record) {
		if (!validateStrNull(record.getProjectName()))
			writer.addCell(index, 0, record.getProjectName());
		if (!validateObjectNull(record.getOrganization()))
			writer.addCell(index, 1, helper.getOrganizationRelativeName(record
					.getOrganization().getId()));
		if (!validateStrNull(record.getProjectAddress()))
			writer.addCell(index, 2, record.getProjectAddress());

		if (!validateStrNull(record.getDevelopmentUnit()))
			writer.addCell(index, 3, record.getDevelopmentUnit());
		if (!validateStrNull(record.getDevelopmentContactPerson()))
			writer.addCell(index, 4, record.getDevelopmentContactPerson());
		if (!validateStrNull(record.getDevelopmentContactPersonPhone()))
			writer.addCell(index, 5, record.getDevelopmentContactPersonPhone());

		if (!validateStrNull(record.getContractor()))
			writer.addCell(index, 6, record.getContractor());

		if (!validateStrNull(record.getContractorContactPerson()))
			writer.addCell(index, 7, record.getContractorContactPerson());
		if (!validateStrNull(record.getContractorContactPersonPhone()))
			writer.addCell(index, 8, record.getContractorContactPersonPhone());

		if (!validateStrNull(record.getLaborer()))
			writer.addCell(index, 9, record.getLaborer());
		if (!validateStrNull(record.getLaborerContactPerson()))
			writer.addCell(index, 10, record.getLaborerContactPerson());
		if (!validateStrNull(record.getLaborerContactPersonPhone()))
			writer.addCell(index, 11, record.getLaborerContactPersonPhone());

		if (!validateStrNull(record.getSalaryPayDate().toString()))
			writer.addCell(index, 12, record.getSalaryPayDate());
		if (!validateStrNull(record.getActualTotalSalary().toString()))
			writer.addCell(index, 13, record.getActualTotalSalary());

		if (!validateStrNull(record.getShouldSignContractNumber().toString()))
			writer.addCell(index, 14, record.getShouldSignContractNumber());
		if (!validateStrNull(record.getNotSignContractNumber().toString()))
			writer.addCell(index, 15, record.getNotSignContractNumber());
		if (!validateStrNull(record.getWorkersNumber().toString()))
			writer.addCell(index, 16, record.getWorkersNumber());
		if (!validateStrNull(record.getStartTime().toString()))
			writer.addCell(index, 17, record.getStartTime());

		if (!validateStrNull(record.getRemark()))
			writer.addCell(index, 18, record.getRemark());
	}

	public String searchConstructionUnitForAutoComplete() throws Exception {
		if (orgId == null) {
			orgId = ThreadVariable.getUser().getOrganization().getId();
		}
		orgId = organizationDubboService.getTownOrganizationId(orgId);
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		List<ConstructionUnit> constructionUnits = searchConstructionUnitDao
				.findConstructionUnitNameAndPinyinAndOrgInternalCode(tag,
						organization.getOrgInternalCode());
		for (ConstructionUnit constructionUnit : constructionUnits) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setLabel(constructionUnit.getProjectName());
			autoCompleteData.setDesc(constructionUnit.getProjectAddress());
			autoCompleteData.setValue(constructionUnit.getId().toString());
			autoCompleteDatas.add(autoCompleteData);
		}
		return SUCCESS;
	}

	private boolean validateStrNull(String str) {
		return str == null || str.trim().equals("");
	}

	private boolean validateObjectNull(Object obj) {
		return obj == null;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public String getIssueSerch() {
		return issueSerch;
	}

	public void setIssueSerch(String issueSerch) {
		this.issueSerch = issueSerch;
	}

	public List<AutoCompleteData> getAutoCompleteDatas() {
		return autoCompleteDatas;
	}

	public void setAutoCompleteDatas(List<AutoCompleteData> autoCompleteDatas) {
		this.autoCompleteDatas = autoCompleteDatas;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public SearchConstructionUnitVo getConstructionUnitVo() {
		return constructionUnitVo;
	}

	public void setConstructionUnitVo(
			SearchConstructionUnitVo constructionUnitVo) {
		this.constructionUnitVo = constructionUnitVo;
	}

	public ConstructionUnit getConstructionUnit() {
		return constructionUnit;
	}

	public void setConstructionUnit(ConstructionUnit constructionUnit) {
		this.constructionUnit = constructionUnit;
	}

}
