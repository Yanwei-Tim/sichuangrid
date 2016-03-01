package com.tianque.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.datatransfer.DataType;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModuleConstants;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SchoolDao;
import com.tianque.datatransfer.DataExportHelper;
import com.tianque.datatransfer.ExcelWriter;
import com.tianque.domain.Organization;
import com.tianque.domain.OtherLocale;
import com.tianque.domain.School;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.service.SchoolService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.vladium.logging.Logger;

@Controller("searchSchoolController")
@Scope("prototype")
@Transactional
public class SearchSchoolController extends SearchBaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private SystemLogService systemLogService;
	private School school;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private Organization organization;
	Long orgId;
	private List<AutoCompleteData> autoCompleteDatas = new ArrayList<AutoCompleteData>();
	private String tag;
	private String issueSerch = "";
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private SchoolDao schoolDao;
	private boolean pageOnly;

	@PermissionFilter(ename = "searchSchool")
	public String searchSchoolList() throws Exception {
		if (orgId == null) {
			this.errorMessage = "参数错误";
		}
		searchCommonality();
		return SUCCESS;
	}

	private void searchCommonality() {
		if (issueSerch.equalsIgnoreCase("issueSerch")) {
			orgId = organizationDubboService.getTownOrganizationId(orgId);
		}
		if (null == school) {
			school = new School();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		school.setOrgInternalCode(organization.getOrgInternalCode());
		// 高级收缩的时候进入该方法 ，是有一个 关注状态： getIsEmphasis or islogout
		Long emphasis = 0L;
		if (school.getIsEmphasis() == null) {
			emphasis = 2L;
		} else if (school.getIsEmphasis() == true) {
			emphasis = 1L;
		} else if (school.getIsEmphasis() == false) {
			emphasis = 0L;
		}
		PageInfo<School> pageInfo = ControllerHelper.processAllOrgRelativeName(
				schoolService.finallSchoolList(school, page, rows, sidx, sord,
						emphasis), organizationDubboService,
				new String[] { "organization" }, orgId);
		gridPage = new GridPage(pageInfo);
	}

	@PermissionFilter(ename = "downSchool")
	public String downloadSchool() {
		if (school == null && orgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			populateOrgCondition();
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (orgId != null) {

				if (org != null) {
					school.setOrgInternalCode(org.getOrgInternalCode());
				} else {
					school.setOrgInternalCode(null);
				}
			} else {
				school.setOrgInternalCode(null);
			}
			List records = getNeedExportDatas(page);
			try {
				inputStream = exportdownloadSchool(records);
				downloadFileName = new String("学校清单".getBytes("gbk"),
						"ISO8859-1") + ".xls";
				systemLogService
						.log(Logger.INFO,
								ModuleConstants.SCHOOL,
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
										+ "导出学校", ObjectToJSON
										.convertJSON(school));
			} catch (Exception e) {
				errorMessage = e.getMessage();
				return ERROR;
			}
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "downSchool")
	public void downloadSchoolAll() {
		if (school == null && orgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		} else {
			populateOrgCondition();
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (orgId != null) {

				if (org != null) {
					school.setOrgInternalCode(org.getOrgInternalCode());
				} else {
					school.setOrgInternalCode(null);
				}
			} else {
				school.setOrgInternalCode(null);
			}
			try {
				if (!pageOnly) {
					pageOnly = true;
					Integer count = schoolDao.getCount(school);
					exportDataAll(count, getActualCompanyPropertyArray(),
							"学校清单");
				}
				systemLogService
						.log(Logger.INFO,
								ModuleConstants.SCHOOL,
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
										+ "导出学校", ObjectToJSON
										.convertJSON(school));
			} catch (Exception e) {
				errorMessage = e.getMessage();
				return;
			}
		}
		return;
	}

	private String[][] getActualCompanyPropertyArray() {
		String[][] excelColumArray = {
				{ "0", "", "学校周边表", "", "", "true", "0", "20" },
				{ "0", "chineseName", "中文名称", "", "", "true" },
				{ "1", "organization", "所属网格", DataType.DATA_TYPE_ORG, "",
						"true" },
				{ "2", "president", "校长", "", "", "true" },
				{ "3", "kind", "学校性质", DataType.DATA_TYPE_DICT, "", "true" },
				{ "4", "address", "学校地址", "", "", "true" },
				{ "5", "type", "学校类型", DataType.DATA_TYPE_DICT, "", "true" },
				{ "6", "personLiable", "综治负责人", "", "", "true" },
				{ "7", "personLiableTelephone", "联系电话", "", "", "true" },
				{ "8", "personLiableMobileNumber", "联系手机", "", "", "true" },
				{ "9", "webSite", "学校网址", "", "", "true" },
				{ "10", "atSchoolHeadcount", "在校人数", "", "", "true" },
				{ "11", "englishName", "英文名称", "", "", "true" },
				{ "12", "fax", "传真", "", "", "true" },
				{ "13", "email", "电子邮箱", "", "", "true" },
				{ "14", "attentionExtent", "关注程度", DataType.DATA_TYPE_DICT, "",
						"true" },
				{ "15", "hasServiceTeamMember", "有无治安负责人", "", "", "true" },
				{ "16", "lastRecordTime", "最新巡场记录", "", "", "true" },
				{ "17", "remark", "备注", "", "", "true" },
				{ "18", "isEmphasis", "是否注销", DataType.DATA_TYPE_BOOLEAN, "",
						"true" },
				{ "19", "logOutTime", "注销时间", DataType.DATA_TYPE_DATE, "",
						"true" },
				{ "20", "logOutReason", "注销原因", "", "", "true" } };
		return excelColumArray;
	}

	private InputStream exportdownloadSchool(List<School> records)
			throws Exception {
		ExcelWriter writer = constructExcelWriter();
		DataExportHelper helper = constructDataExportHelper();
		writer.addWorkSheet("学校");
		appendTitle(writer, "学校表");
		appendTableHead(writer);
		for (int index = 0; index < records.size(); index++) {
			School school = records.get(index);
			appendRow(writer, helper, index + 3, school);
		}
		return new java.io.FileInputStream(writer.getExcelFile(UUID
				.randomUUID() + ".xls"));
	}

	private DataExportHelper constructDataExportHelper() {
		DataExportHelper helper = new DataExportHelper();
		helper.setOrganizationService(organizationDubboService);
		helper.setPropertyDictService(propertyDictService);
		return helper;
	}

	private ExcelWriter constructExcelWriter() {
		ExcelWriter writer = new ExcelWriter();
		writer.setDefaultFont("宋体", (short) 12, false, false, false, false);
		writer.setDefaultCellBorder(true);
		writer.setDefaultHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);
		writer.setDefaultVerticalAlignment(ExcelWriter.VERTICAL_ALIGN_CENTER);
		return writer;
	}

	private void appendRow(ExcelWriter writer, DataExportHelper helper,
			int index, School school) {
		writer.addCell(index, 0, school.getChineseName());
		writer.addCell(index, 1, helper.getOrganizationRelativeName(school
				.getOrganization().getId()));
		writer.addCell(index, 2, school.getPresident());
		writer.addCell(index, 3, helper.getPropertyDictDisplayName(
				PropertyTypes.SCHOOL_PROPERTY, school.getKind().getId()));
		writer.addCell(index, 4, school.getAddress());
		writer.addCell(index, 5, helper.getPropertyDictDisplayName(
				PropertyTypes.SCHOOL_TYPE, school.getType().getId()));

		writer.addCell(index, 6, school.getPersonLiable());
		writer.addCell(index, 7, school.getPersonLiableTelephone());
		writer.addCell(index, 8, school.getPersonLiableMobileNumber());
		writer.addCell(index, 9, school.getWebSite());
		writer.addCell(index, 10, school.getAtSchoolHeadcount() == null ? ""
				: school.getAtSchoolHeadcount().toString());
		writer.addCell(index, 11, school.getEnglishName());
		writer.addCell(index, 12, school.getFax());
		writer.addCell(index, 13, school.getEmail());
		writer.addCell(
				index,
				14,
				school.getAttentionExtent() == null ? "" : helper
						.getPropertyDictDisplayName(
								PropertyTypes.ATTENTION_EXTENT, school
										.getAttentionExtent().getId()));
		writer.addCell(index, 15, school.getHasServiceTeamMember() > 0 ? "有"
				: "无");
		writer.addCell(index, 16, school.getLastRecordTime());
		writer.addCell(index, 17, school.getRemark());
		writer.addCell(index, 18, school.getIsEmphasis() ? "是" : "否");
		writer.addCell(index, 19, school.getLogOutTime());
		writer.addCell(index, 20, school.getLogOutReason());
	}

	private void appendTitle(ExcelWriter write, String title) {
		write.addCell(0, 0, title).mergeTo(0, 20)
				.setFont("宋体", 20, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
	}

	private void appendTableHead(ExcelWriter writer) {
		writer.addCell(0, 0, "学校周边表").mergeTo(0, 17)
				.setFont("宋体", 20, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(1, 0, "中文名称")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 0);
		writer.addCell(1, 1, "所属网格")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 1);
		writer.addCell(1, 2, "校长").setFont("宋体", 12, true, false, false, false)
				.mergeTo(2, 2);

		writer.addCell(1, 3, "学校性质")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 3);
		writer.addCell(1, 4, "学校地址")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 4);
		writer.addCell(1, 5, "学校类型")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 5);
		writer.addCell(1, 6, "综治负责人")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 6);
		writer.addCell(1, 7, "联系电话")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 7);
		writer.addCell(1, 8, "联系手机")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 8);

		writer.addCell(1, 9, "学校网址")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 9);
		writer.addCell(1, 10, "在校人数")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 10);
		writer.addCell(1, 11, "英文名称")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 11);
		writer.addCell(1, 12, "传真")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 12);
		writer.addCell(1, 13, "电子邮箱")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 13);
		writer.addCell(1, 14, "关注程度")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 14);

		writer.addCell(1, 15, "有无治安负责人")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 15);
		writer.addCell(1, 16, "最新巡场记录")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 16);
		writer.addCell(1, 17, "备注")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 17);

		writer.addCell(1, 18, "是否注销")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 18);
		writer.addCell(1, 19, "注销时间")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 19);
		writer.addCell(1, 20, "注销原因")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 20);

	}

	public String searchSchoolForAutoComplete() {
		if (orgId == null) {
			orgId = ThreadVariable.getUser().getOrganization().getId();
		}
		orgId = organizationDubboService.getTownOrganizationId(orgId);
		organization = organizationDubboService.getSimpleOrgById(orgId);
		List<School> schoolList = schoolService
				.findSchoolByNameAndPinyinAndOrgInternalCode(tag,
						organization.getOrgInternalCode());
		for (School school : schoolList) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setLabel(school.getChineseName());
			autoCompleteData.setDesc(school.getAddress());
			autoCompleteData.setValue(school.getId().toString());
			autoCompleteDatas.add(autoCompleteData);
		}
		return SUCCESS;
	}

	/**
	 * 检索
	 * 
	 * @return SUCCESS
	 */
	public String fastSearch() {
		if (school == null) {
			gridPage = new GridPage(emptyOtherLocalePage(rows));
		} else {
			fastSearchCommonality();
		}
		return SUCCESS;
	}

	private void fastSearchCommonality() {
		Organization ownerOrg = organizationDubboService
				.getSimpleOrgById(orgId);
		school.setOrgInternalCode(ownerOrg.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				schoolService.finallSchoolList(school, page, rows, sidx, sord,
						school.getIsEmphasis() ? 1L : 0L),
				organizationDubboService, new String[] { "organization" },
				orgId));
	}

	private PageInfo<OtherLocale> emptyOtherLocalePage(int pageSize) {
		PageInfo<OtherLocale> pageInfo = new PageInfo<OtherLocale>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<OtherLocale>());
		return pageInfo;
	}

	public List<AutoCompleteData> getAutoCompleteDatas() {
		return autoCompleteDatas;
	}

	public void setAutoCompleteDatas(List<AutoCompleteData> autoCompleteDatas) {
		this.autoCompleteDatas = autoCompleteDatas;
	}

	public List<School> getNeedExportDatas(int page) {
		if (pageOnly) {
			return schoolDao.searchSchoolForExport(school, page, rows, sidx,
					sord);
		} else {
			return schoolDao.searchSchoolForExport(school, null, null, sidx,
					sord);
		}
	}

	private void populateOrgCondition() {
		if (school == null) {
			school = new School();
		}
		if (orgId != null) {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org != null) {
				school.setOrgInternalCode(org.getOrgInternalCode());
			} else {
				school.setOrgInternalCode(null);
			}
		} else {
			school.setOrgInternalCode(null);
		}
	}

	public SchoolDao getSchoolDao() {
		return schoolDao;
	}

	public void setSchoolDao(SchoolDao schoolDao) {
		this.schoolDao = schoolDao;
	}

	public String getIssueSerch() {
		return issueSerch;
	}

	public void setIssueSerch(String issueSerch) {
		this.issueSerch = issueSerch;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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

	public SchoolService getSchoolService() {
		return schoolService;
	}

	public void setSchoolService(SchoolService schoolService) {
		this.schoolService = schoolService;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public OrganizationDubboService getOrganizationDubboService() {
		return organizationDubboService;
	}

	public void setOrganizationDubboService(
			OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
