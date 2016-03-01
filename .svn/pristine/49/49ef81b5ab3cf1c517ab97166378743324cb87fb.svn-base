package com.tianque.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
import com.tianque.dao.SearchOtherLocaleDao;
import com.tianque.datatransfer.DataExportHelper;
import com.tianque.datatransfer.ExcelWriter;
import com.tianque.domain.Organization;
import com.tianque.domain.OtherLocale;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.SearchOtherLocaleVo;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.vladium.logging.Logger;

@SuppressWarnings("serial")
@Controller("searchOtherLocaleController")
@Scope("prototype")
public class SearchOtherLocaleController extends SearchBaseAction {
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private SearchOtherLocaleDao searchOtherLocaleDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	private Organization organization;
	private SearchOtherLocaleVo searchOtherLocaleVo;
	private boolean pageOnly;
	private List<AutoCompleteData> autoCompleteDatas = new ArrayList<AutoCompleteData>();
	private String tag;
	private Long orgId;
	private String issueSerch = "";
	private OtherLocale otherLocale;

	@PermissionFilter(ename = "searchOtherLocale")
	public String searchOtherLocale() throws Exception {
		if (searchOtherLocaleVo == null) {
			this.errorMessage = "参数错误";
		}
		searchCommonality();
		return SUCCESS;
	}

	private void searchCommonality() {
		if (issueSerch.equalsIgnoreCase("issueSerch")) {
			organization.setId(organizationDubboService
					.getTownOrganizationId(organization.getId()));
		}
		if (null == searchOtherLocaleVo) {
			searchOtherLocaleVo = new SearchOtherLocaleVo();
		}
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		searchOtherLocaleVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		PageInfo<OtherLocale> pageInfo = ControllerHelper
				.processAllOrgRelativeName(searchOtherLocaleDao
						.searchOtherLocale(searchOtherLocaleVo, page, rows,
								sidx, sord), organizationDubboService,
						new String[] { "organization" }, orgId);
		gridPage = new GridPage(pageInfo);
	}

	@PermissionFilter(ename = "downOtherLocale")
	public String downloadOtherLocale() throws Exception {
		if (searchOtherLocaleVo == null && orgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {

			populateOrgCondition();
			List records = getNeedExportDatas(page);
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (orgId != null) {

				if (org != null) {
					searchOtherLocaleVo.setOrgInternalCode(org
							.getOrgInternalCode());
				} else {
					searchOtherLocaleVo.setOrgInternalCode(null);
				}
			} else {
				org = organizationDubboService.getSimpleOrgById(organization
						.getId());
				searchOtherLocaleVo
						.setOrgInternalCode(org.getOrgInternalCode());
			}
			inputStream = exportOtherLocale(records);
			downloadFileName = new String("其他场所清单".getBytes("gbk"), "ISO8859-1")
					+ ".xls";
			systemLogService
					.log(Logger.INFO,
							ModuleConstants.OTHER_LOCATION,
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
									+ " 导出其他场所", ObjectToJSON
									.convertJSON(searchOtherLocaleVo));
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "downOtherLocale")
	public void downloadExcelExportAll() throws Exception {
		if (orgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchOtherLocaleVo == null) {
			searchOtherLocaleVo = new SearchOtherLocaleVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		searchOtherLocaleVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = searchOtherLocaleDao.getCount(searchOtherLocaleVo);
			String[][] excelDefines = getExportPopertyArray();
			exportDataAll(count, excelDefines, "其他场所清单");
		}
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.OTHER_LOCATION,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organization.getId(),
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + " 导出其他场所",
						ObjectToJSON.convertJSON(searchOtherLocaleVo));
	}

	private void populateOrgCondition() {
		if (searchOtherLocaleVo == null) {
			searchOtherLocaleVo = new SearchOtherLocaleVo();
		}
		if (orgId != null) {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org != null) {
				searchOtherLocaleVo
						.setOrgInternalCode(org.getOrgInternalCode());
			} else {
				searchOtherLocaleVo.setOrgInternalCode(null);
			}
		} else {
			searchOtherLocaleVo.setOrgInternalCode(null);
		}
	}

	public List<OtherLocale> getNeedExportDatas(int page) {
		List<OtherLocale> list = new ArrayList<OtherLocale>();
		if (otherLocale != null) {
			searchOtherLocaleVo.setIsEmphasis(otherLocale.getIsEmphasis() ? 0L
					: 1L);
		}
		if (pageOnly) {
			list = searchOtherLocaleDao.searchOtherLocaleForExport(
					searchOtherLocaleVo, page, rows, sidx, sord);
		} else {
			list = searchOtherLocaleDao.searchOtherLocaleForExport(
					searchOtherLocaleVo, null, null, sidx, sord);
		}
		return list;
	}

	private InputStream exportOtherLocale(List<OtherLocale> records)
			throws Exception {
		ExcelWriter writer = constructExcelWriter();
		DataExportHelper helper = constructDataExportHelper();
		writer.addWorkSheet("其他场所");
		appendTitle(writer, "其他场所表");
		appendTableHead(writer);
		for (int index = 0; index < records.size(); index++) {
			OtherLocale record = records.get(index);
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
		write.addCell(0, 0, title).mergeTo(0, 13)
				.setFont("宋体", 20, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
	}

	private String[][] getExportPopertyArray() {
		String[][] excelColumArray = {
				{ "0", "", "其他场所表", "", "", "true", "0", "13" },
				{ "0", "name", "名称", "", "", "true" },
				{ "1", "organization", "片组片格", DataType.DATA_TYPE_ORG, "",
						"true" },
				{ "2", "address", "地址", "", "", "true" },
				{ "3", "type", "场所类型", DataType.DATA_TYPE_DICT,
						PropertyTypes.OTHER_LOCALE_TYPE, "true" },
				{ "4", "contactPerson", "联系人", "", "", "true" },
				{ "5", "mobileNumber", "联系手机", "", "", "true" },
				{ "6", "telephone", "联系电话", "", "", "true" },
				{ "7", "attentionExtent", "关注程度", DataType.DATA_TYPE_DICT,
						PropertyTypes.ATTENTION_EXTENT, "true" },
				{ "8", "hasServiceTeamMember", "有无治安负责人",
						DataType.DATA_TYPE_COUNTS, "", "true" },
				{ "9", "lastRecordTime", "最新巡场时间", DataType.DATA_TYPE_DATE, "",
						"true" },
				{ "10", "remark", "备注", "", "", "true" },
				{ "11", "isEmphasis", "是否注销", DataType.DATA_TYPE_BOOLEAN, "",
						"true" },
				{ "12", "logOutTime", "注销时间", DataType.DATA_TYPE_DATE, "",
						"true" },
				{ "13", "logOutReason", "注销原因", "", "", "true" } };
		return excelColumArray;
	}

	private void appendTableHead(ExcelWriter writer) {
		writer.addCell(0, 0, "其他场所表").mergeTo(0, 13)
				.setFont("宋体", 20, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(1, 0, "名称").setFont("宋体", 12, true, false, false, false)
				.mergeTo(2, 0);
		writer.addCell(1, 1, "片组片格")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 1);
		writer.addCell(1, 2, "地址").setFont("宋体", 12, true, false, false, false)
				.mergeTo(2, 2);
		writer.addCell(1, 3, "场所类型")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 3);
		writer.addCell(1, 4, "联系人")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 4);
		writer.addCell(1, 5, "联系手机")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 5);
		writer.addCell(1, 6, "联系电话")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 6);
		writer.addCell(1, 7, "关注程度")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 7);
		writer.addCell(1, 8, "有无治安负责人")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 8);
		writer.addCell(1, 9, "最新巡场时间")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 9);
		writer.addCell(1, 10, "备注")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 10);
		writer.addCell(1, 11, "是否注销")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 11);
		writer.addCell(1, 12, "注销时间")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 12);
		writer.addCell(1, 13, "注销原因")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 13);
	}

	private void appendRow(ExcelWriter writer, DataExportHelper helper,
			int index, OtherLocale record) {
		if (!validateStrNull(record.getName()))
			writer.addCell(index, 0, record.getName());
		if (!validateObjectNull(record.getOrganization()))
			writer.addCell(index, 1, helper.getOrganizationRelativeName(record
					.getOrganization().getId()));
		if (!validateStrNull(record.getAddress()))
			writer.addCell(index, 2, record.getAddress());
		if (!validateObjectNull(record.getType()))
			writer.addCell(index, 3, helper.getPropertyDictDisplayName(
					PropertyTypes.OTHER_LOCALE_TYPE, record.getType().getId()));
		if (!validateStrNull(record.getContactPerson()))
			writer.addCell(index, 4, record.getContactPerson());
		if (!validateStrNull(record.getMobileNumber()))
			writer.addCell(index, 5, record.getMobileNumber());
		if (!validateStrNull(record.getTelephone()))
			writer.addCell(index, 6, record.getTelephone());
		writer.addCell(
				index,
				7,
				record.getAttentionExtent() == null ? "" : helper
						.getPropertyDictDisplayName(
								PropertyTypes.ATTENTION_EXTENT, record
										.getAttentionExtent().getId()));
		writer.addCell(index, 8, record.getHasServiceTeamMember() > 0 ? "有"
				: "无");
		writer.addCell(index, 9, record.getLastRecordTime());
		if (!validateStrNull(record.getRemark()))
			writer.addCell(index, 10, record.getRemark());
		writer.addCell(index, 11,
				Boolean.TRUE.equals(record.getIsEmphasis()) ? "是" : "否");
		writer.addCell(index, 12, record.getLogOutTime());
		writer.addCell(index, 13, record.getLogOutReason());
	}

	public String searchOtherLocaleForAutoComplete() throws Exception {
		if (orgId == null) {
			orgId = ThreadVariable.getUser().getOrganization().getId();
		}
		orgId = organizationDubboService.getTownOrganizationId(orgId);
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		List<OtherLocale> otherLocales = searchOtherLocaleDao
				.findOtherLocaleNameAndPinyinAndOrgInternalCode(tag,
						organization.getOrgInternalCode());
		for (OtherLocale otherLocale : otherLocales) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setLabel(otherLocale.getName());
			autoCompleteData.setDesc(otherLocale.getAddress());
			autoCompleteData.setValue(otherLocale.getId().toString());
			autoCompleteDatas.add(autoCompleteData);
		}
		return SUCCESS;
	}

	/**
	 * 检索
	 * 
	 * @return SUCCESS
	 */
	public String fastSearch() throws Exception {
		if (searchOtherLocaleVo == null) {
			gridPage = new GridPage(emptyOtherLocalePage(rows));
		} else {
			fastSearchCommonality();
		}
		return SUCCESS;
	}

	private void fastSearchCommonality() {
		Organization ownerOrg = organizationDubboService
				.getSimpleOrgById(orgId);
		searchOtherLocaleVo.setOrgInternalCode(ownerOrg.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchOtherLocaleDao.searchOtherLocale(searchOtherLocaleVo,
						page, rows, sidx, sord), organizationDubboService,
				new String[] { "organization" }, orgId));
	}

	private PageInfo<OtherLocale> emptyOtherLocalePage(int pageSize) {
		PageInfo<OtherLocale> pageInfo = new PageInfo<OtherLocale>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<OtherLocale>());
		return pageInfo;
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

	public SearchOtherLocaleVo getSearchOtherLocaleVo() {
		return searchOtherLocaleVo;
	}

	public void setSearchOtherLocaleVo(SearchOtherLocaleVo searchOtherLocaleVo) {
		this.searchOtherLocaleVo = searchOtherLocaleVo;
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

	public OtherLocale getOtherLocale() {
		return otherLocale;
	}

	public void setOtherLocale(OtherLocale otherLocale) {
		this.otherLocale = otherLocale;
	}

}
