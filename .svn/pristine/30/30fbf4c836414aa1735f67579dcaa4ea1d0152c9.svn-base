package com.tianque.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.datatransfer.DataType;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModelType;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchEnterpriseDao;
import com.tianque.datatransfer.DataExportHelper;
import com.tianque.datatransfer.ExcelWriter;
import com.tianque.domain.Enterprise;
import com.tianque.domain.Organization;
import com.tianque.domain.OtherLocale;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.SearchEnterpriseVo;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@SuppressWarnings("serial")
@Controller("searchEnterpriseController")
@Scope("prototype")
@Transactional
public class SearchEnterpriseController extends SearchBaseAction {
	@Autowired
	private SearchEnterpriseDao searchEnterpriseDao;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private Long orgId;
	private List<AutoCompleteData> autoCompleteDatas = new ArrayList<AutoCompleteData>();
	private String tag;
	private Organization ownerOrg;
	private String issueSerch = "";
	@Autowired
	private PropertyDictService propertyDictService;
	private SearchEnterpriseVo enterpriseSearchCondition;
	private boolean pageOnly;
	private String keyType;
	private Enterprise enterprise;
	private SearchEnterpriseVo location; // 页面上导出的时候 location
	private SearchEnterpriseVo searchSafetyProductionKeyVo;
	private SearchEnterpriseVo searchEnterpriseVo;

	public SearchEnterpriseVo getSearchEnterpriseVo() {
		return searchEnterpriseVo;
	}

	public void setSearchEnterpriseVo(SearchEnterpriseVo searchEnterpriseVo) {
		this.searchEnterpriseVo = searchEnterpriseVo;
	}

	public String searchEnterprise() throws Exception {
		if (null == orgId) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		if (issueSerch.equalsIgnoreCase("issueSerch")) {
			orgId = organizationDubboService.getTownOrganizationId(orgId);
		}
		if (orgId == null) {
			orgId = ownerOrg.getId();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		if (null == enterpriseSearchCondition) {
			enterpriseSearchCondition = new SearchEnterpriseVo();
		}
		enterpriseSearchCondition.setOrgInternalCode(organization
				.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchEnterpriseDao.searchEnterprise(enterpriseSearchCondition,
						page, rows, sidx, sord), organizationDubboService,
				new String[] { "organization" }, orgId));
		return SUCCESS;
	}

	private void populateOrgCondition() {
		if (enterpriseSearchCondition == null) {
			if (searchEnterpriseVo != null) {
				enterpriseSearchCondition = searchEnterpriseVo;
			} else if (searchSafetyProductionKeyVo != null) {
				enterpriseSearchCondition = searchSafetyProductionKeyVo;
			} else {
				enterpriseSearchCondition = new SearchEnterpriseVo();
			}
		}
		if (ownerOrg != null && ownerOrg.getId() != null) {
			Organization org = organizationDubboService
					.getSimpleOrgById(ownerOrg.getId());
			if (org != null) {
				enterpriseSearchCondition.setOrgInternalCode(org
						.getOrgInternalCode());
			} else {
				enterpriseSearchCondition.setOrgInternalCode(null);
			}
		} else {
			enterpriseSearchCondition.setOrgInternalCode(null);
		}
	}

	public String downloadEnterprise() throws Exception {
		if (enterpriseSearchCondition == null && orgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			populateOrgCondition();
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org != null) {
				enterpriseSearchCondition.setOrgInternalCode(org
						.getOrgInternalCode());
			} else {
				org = organizationDubboService.getSimpleOrgById(ownerOrg
						.getId());
				enterpriseSearchCondition.setOrgInternalCode(org
						.getOrgInternalCode());
			}
			List<Enterprise> records = getNeedExportDatas(page);
			inputStream = exportdownloadEnterprise(records);
			downloadFileName = new String(fillDisplayName().getBytes("gbk"),
					"ISO8859-1") + ".xls";
			systemLogService
					.log(com.tianque.core.logger.Logger.INFO,
							ModelType.ENTERPRISE,
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
									+ "导出规上企业", ObjectToJSON
									.convertJSON(enterpriseSearchCondition));
		}
		return SUCCESS;
	}

	public void downloadEnterpriseAll() throws Exception {
		if (enterpriseSearchCondition == null && orgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		} else {
			populateOrgCondition();
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org != null) {
				enterpriseSearchCondition.setOrgInternalCode(org
						.getOrgInternalCode());
			} else {
				org = organizationDubboService.getSimpleOrgById(ownerOrg
						.getId());
				enterpriseSearchCondition.setOrgInternalCode(org
						.getOrgInternalCode());
			}
			if (enterprise != null) {
				enterpriseSearchCondition.setIsEmphasis(enterprise
						.getIsEmphasis() ? 0L : 1L);
			}

			if (location != null) {
				enterpriseSearchCondition.setIsEmphasis(location
						.getIsEmphasis());
			}

			if (keyType != null) {
				enterpriseSearchCondition.setKeyType(keyType);
			}
			if (!pageOnly) {
				pageOnly = true;
				Integer count = searchEnterpriseDao
						.getCount(enterpriseSearchCondition);
				exportDataAll(count, getActualCompanyPropertyArray(),
						fillDisplayName());
			}
			systemLogService
					.log(com.tianque.core.logger.Logger.INFO,
							ModelType.ENTERPRISE,
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
									+ "导出规上企业", ObjectToJSON
									.convertJSON(enterpriseSearchCondition));
		}
		return;
	}

	private String[][] getActualCompanyPropertyArray() {
		if (keyType == null) {
			keyType = enterpriseSearchCondition.getKeyType();
		}
		if (keyType.equals("enterpriseKey")
				|| keyType.equals("enterpriseDownKey")
				|| keyType.equals("safetyProductionKey")) {
			String[][] excelColumArray = {
					{ "0", "", fillDisplayName(), "", "", "true", "0", "24" },
					{ "0", "name", "企业名称", "", "", "true" },
					{ "1", "type", "企业类型", DataType.DATA_TYPE_DICT, "", "true" },
					{ "2", "businessLicense", "工商执照号码", "", "", "true" },
					{ "3", "organization", "片组片格", DataType.DATA_TYPE_ORG, "",
							"true" },
					{ "4", "legalPerson", "法人代表", "", "", "true" },
					{ "5", "industry", "所属行业", DataType.DATA_TYPE_DICT, "",
							"true" },
					{ "6", "address", "企业地址", "", "", "true" },
					{ "7", "area", "面积(m2)", "", "", "true" },
					{ "8", "registeredCapital", "注册资金(万元)", "", "", "true" },
					{ "9", "employeeAmount", "员工数", "", "", "true" },
					{ "10", "partyMemberAmount", "党员数", "", "", "true" },
					{ "11", "enterpriseTelephone", "企业联系电话", "", "", "true" },
					{ "12", "fax", "企业传真号码", "", "", "true" },
					{ "13", "telephone", "法人联系电话", "", "", "true" },
					{ "14", "mobileNumber", "法人手机号码", "", "", "true" },
					{ "15", "attentionExtent", "关注程度", DataType.DATA_TYPE_DICT,
							"", "true" },
					{ "16", "riskEnterprise", "是否为危化企业",
							DataType.DATA_TYPE_BOOLEAN, "", "true" },
					{ "17", "hiddenCases", "隐患情况", "", "", "true" },
					{ "18", "hiddenRectification", "隐患整改情况", "", "", "true" },
					{ "19", "hasServiceTeamMember", "有无治安负责人", "", "", "true" },
					{ "20", "lastRecordTime", "最新巡场记录", "", "", "true" },
					{ "21", "remark", "备注", "", "", "true" },
					{ "22", "isEmphasis", "是否取消关注", DataType.DATA_TYPE_BOOLEAN,
							"", "true" },
					{ "23", "logOutTime", "取消关注时间", DataType.DATA_TYPE_DATE,
							"", "true" },
					{ "24", "logOutReason", "取消关注原因", "", "", "true" } };
			return excelColumArray;
		} else if (keyType.equals("fireSafetyKey")
				|| keyType.equals("securityKey")) {
			String[][] excelColumArrayOther = {
					{ "0", "", fillDisplayName(), "", "", "true", "0", "16" },
					{ "0", "name", "场所名称", "", "", "true" },
					{ "1", "type", "场所类型", DataType.DATA_TYPE_DICT, "", "true" },
					{ "2", "organization", "片组片格", DataType.DATA_TYPE_ORG, "",
							"true" },
					{ "3", "legalPerson", "负责人", "", "", "true" },
					{ "4", "address", "场所地址", "", "", "true" },
					{ "5", "telephone", "联系电话", "", "", "true" },
					{ "6", "mobileNumber", "手机号码", "", "", "true" },
					{ "7", "attentionExtent", "关注程度", DataType.DATA_TYPE_DICT,
							"", "true" },
					{ "8", "riskEnterprise", "是否存在隐患",
							DataType.DATA_TYPE_BOOLEAN, "", "true" },
					{ "9", "hiddenCases", "隐患情况", "", "", "true" },
					{ "10", "hiddenRectification", "隐患整改情况", "", "", "true" },
					{ "11", "hasServiceTeamMember", "有无治安负责人", "", "", "true" },
					{ "12", "lastRecordTime", "最新巡场记录", "", "", "true" },
					{ "13", "remark", "备注", "", "", "true" },
					{ "14", "isEmphasis", "是否取消关注", DataType.DATA_TYPE_BOOLEAN,
							"", "true" },
					{ "15", "logOutTime", "取消关注时间", DataType.DATA_TYPE_DATE,
							"", "true" },
					{ "16", "logOutReason", "取消关注原因", "", "", "true" } };
			return excelColumArrayOther;
		} else {

		}
		return new String[0][0];
	}

	private InputStream exportdownloadEnterprise(List<Enterprise> records)
			throws Exception {
		ExcelWriter writer = constructExcelWriter();
		DataExportHelper helper = constructDataExportHelper();
		writer.addWorkSheet(fillDisplayName());
		// appendTitle(writer, fillDisplayName());
		appendTableHead(writer);
		for (int index = 0; index < records.size(); index++) {
			Enterprise enterprise = records.get(index);
			appendRow(writer, helper, index + 3, enterprise);
		}
		return new java.io.FileInputStream(writer.getExcelFile(UUID
				.randomUUID() + ".xls"));
	}

	private String fillDisplayName() {
		if (keyType == null) {
			keyType = enterpriseSearchCondition.getKeyType();
		}
		if (keyType.equals("enterpriseKey")) {
			return "规上企业";
		} else if (keyType.equals("enterpriseDownKey")) {
			return "规下企业";
		} else if (keyType.equals("safetyProductionKey")) {
			return "安全生产重点";
		} else if (keyType.equals("fireSafetyKey")) {
			return "消防安全重点";
		} else if (keyType.equals("securityKey")) {
			return "治安重点";
		} else {
			return "";
		}
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
			int index, Enterprise enterprise) {
		if (keyType == null) {
			keyType = enterpriseSearchCondition.getKeyType();
		}
		if (keyType.equals("enterpriseKey")
				|| keyType.equals("enterpriseDownKey")
				|| keyType.equals("safetyProductionKey")) {
			writer.addCell(index, 0, enterprise.getName());
			writer.addCell(index, 1, helper
					.getPropertyDictDisplayName(PropertyTypes.ENTERPRISE_TYPE,
							enterprise.getType().getId()));
			writer.addCell(index, 2, enterprise.getBusinessLicense());
			writer.addCell(index, 3, helper
					.getOrganizationRelativeName(enterprise.getOrganization()
							.getId()));
			writer.addCell(index, 4, enterprise.getLegalPerson());
			writer.addCell(
					index,
					5,
					enterprise.getIndustry() == null ? "" : helper
							.getPropertyDictDisplayName(PropertyTypes.CAREER,
									enterprise.getIndustry().getId()));
			writer.addCell(index, 6, enterprise.getAddress());
			writer.addCell(index, 7, enterprise.getArea() == null ? ""
					: enterprise.getArea().toString());
			writer.addCell(index, 8,
					enterprise.getRegisteredCapital() == null ? "" : enterprise
							.getRegisteredCapital().toString());
			writer.addCell(index, 9,
					enterprise.getEmployeeAmount() == null ? "" : enterprise
							.getEmployeeAmount().toString());
			writer.addCell(index, 10,
					enterprise.getPartyMemberAmount() == null ? "" : enterprise
							.getPartyMemberAmount().toString());
			writer.addCell(index, 11, enterprise.getEnterpriseTelephone());
			writer.addCell(index, 12, enterprise.getFax());
			writer.addCell(index, 13, enterprise.getTelephone());
			writer.addCell(index, 14, enterprise.getMobileNumber());
			writer.addCell(
					index,
					15,
					enterprise.getAttentionExtent() == null ? "" : helper
							.getPropertyDictDisplayName(
									PropertyTypes.ATTENTION_EXTENT, enterprise
											.getAttentionExtent().getId()));
			writer.addCell(index, 16, enterprise.isRiskEnterprise() ? "是" : "否");
			writer.addCell(index, 17, enterprise.getHiddenCases());
			writer.addCell(index, 18, enterprise.getHiddenRectification());
			writer.addCell(index, 19,
					enterprise.getHasServiceTeamMember() > 0 ? "有" : "无");
			writer.addCell(index, 20, enterprise.getLastRecordTime());
			writer.addCell(index, 21, enterprise.getRemark());
			writer.addCell(index, 22, enterprise.getIsEmphasis() ? "是" : "否");
			writer.addCell(index, 23, enterprise.getLogOutTime());
			writer.addCell(index, 24, enterprise.getLogOutReason());
		} else if (keyType.equals("fireSafetyKey")
				|| keyType.equals("securityKey")) {
			writer.addCell(index, 0, enterprise.getName());
			writer.addCell(index, 1, helper
					.getPropertyDictDisplayName(PropertyTypes.ENTERPRISE_TYPE,
							enterprise.getType().getId()));
			writer.addCell(index, 2, helper
					.getOrganizationRelativeName(enterprise.getOrganization()
							.getId()));
			writer.addCell(index, 3, enterprise.getLegalPerson());
			writer.addCell(index, 4, enterprise.getAddress());
			writer.addCell(index, 5, enterprise.getTelephone());
			writer.addCell(index, 6, enterprise.getMobileNumber());
			writer.addCell(
					index,
					7,
					enterprise.getAttentionExtent() == null ? "" : helper
							.getPropertyDictDisplayName(
									PropertyTypes.ATTENTION_EXTENT, enterprise
											.getAttentionExtent().getId()));
			writer.addCell(index, 8, enterprise.isRiskEnterprise() ? "是" : "否");
			writer.addCell(index, 9, enterprise.getHiddenCases());
			writer.addCell(index, 10, enterprise.getHiddenRectification());
			writer.addCell(index, 11,
					enterprise.getHasServiceTeamMember() > 0 ? "有" : "无");
			writer.addCell(index, 12, enterprise.getLastRecordTime());
			writer.addCell(index, 13, enterprise.getRemark());
			writer.addCell(index, 14, enterprise.getIsEmphasis() ? "是" : "否");
			writer.addCell(index, 15, enterprise.getLogOutTime());
			writer.addCell(index, 16, enterprise.getLogOutReason());
		}
	}

	private void appendTitle(ExcelWriter write, String title) {
		write.addCell(0, 0, title).mergeTo(0, 19)
				.setFont("宋体", 20, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
	}

	private void appendTableHead(ExcelWriter writer) {
		if (keyType == null) {
			keyType = enterpriseSearchCondition.getKeyType();
		}
		if (keyType.equals("enterpriseKey")
				|| keyType.equals("enterpriseDownKey")
				|| keyType.equals("safetyProductionKey")) {
			writer.addCell(0, 0, fillDisplayName())
					.mergeTo(0, 24)
					.setFont("宋体", 20, true, false, false, false)
					.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
			writer.addCell(1, 0, "企业名称")
					.setFont("宋体", 12, true, false, false, false).mergeTo(2, 0);
			writer.addCell(1, 1, "企业类型")
					.setFont("宋体", 12, true, false, false, false).mergeTo(2, 1);
			writer.addCell(1, 2, "工商执照号码")
					.setFont("宋体", 12, true, false, false, false).mergeTo(2, 2);
			writer.addCell(1, 3, "片组片格")
					.setFont("宋体", 12, true, false, false, false).mergeTo(2, 3);
			writer.addCell(1, 4, "法人代表")
					.setFont("宋体", 12, true, false, false, false).mergeTo(2, 4);
			writer.addCell(1, 5, "所属行业")
					.setFont("宋体", 12, true, false, false, false).mergeTo(2, 5);
			writer.addCell(1, 6, "企业地址")
					.setFont("宋体", 12, true, false, false, false).mergeTo(2, 6);
			writer.addCell(1, 7, "面积(m2)")
					.setFont("宋体", 12, true, false, false, false).mergeTo(2, 7);
			writer.addCell(1, 8, "注册资金(万元)")
					.setFont("宋体", 12, true, false, false, false).mergeTo(2, 8);
			writer.addCell(1, 9, "员工数")
					.setFont("宋体", 12, true, false, false, false).mergeTo(2, 9);
			writer.addCell(1, 10, "党员数")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 10);
			writer.addCell(1, 11, "企业联系电话")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 11);
			writer.addCell(1, 12, "企业传真号码")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 12);
			writer.addCell(1, 13, "法人联系电话")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 13);
			writer.addCell(1, 14, "法人手机号码")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 14);
			writer.addCell(1, 15, "关注程度")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 15);
			writer.addCell(1, 16, "是否为危化企业")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 16);
			writer.addCell(1, 17, "隐患情况")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 17);
			writer.addCell(1, 18, "隐患整改情况")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 18);
			writer.addCell(1, 19, "有无治安负责人")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 19);
			writer.addCell(1, 20, "最新巡场记录")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 20);
			writer.addCell(1, 21, "备注")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 21);
			writer.addCell(1, 22, "是否取消关注")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 22);
			writer.addCell(1, 23, "取消关注时间")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 23);
			writer.addCell(1, 24, "取消关注原因")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 24);
		} else if (keyType.equals("fireSafetyKey")
				|| keyType.equals("securityKey")) {
			writer.addCell(0, 0, fillDisplayName())
					.mergeTo(0, 16)
					.setFont("宋体", 20, true, false, false, false)
					.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
			writer.addCell(1, 0, "场所名称")
					.setFont("宋体", 12, true, false, false, false).mergeTo(2, 0);
			writer.addCell(1, 1, "场所类型")
					.setFont("宋体", 12, true, false, false, false).mergeTo(2, 1);
			writer.addCell(1, 2, "片组片格")
					.setFont("宋体", 12, true, false, false, false).mergeTo(2, 2);
			writer.addCell(1, 3, "负责人")
					.setFont("宋体", 12, true, false, false, false).mergeTo(2, 3);
			writer.addCell(1, 4, "场所地址")
					.setFont("宋体", 12, true, false, false, false).mergeTo(2, 4);
			writer.addCell(1, 5, "联系电话")
					.setFont("宋体", 12, true, false, false, false).mergeTo(2, 5);
			writer.addCell(1, 6, "手机号码")
					.setFont("宋体", 12, true, false, false, false).mergeTo(2, 6);
			writer.addCell(1, 7, "关注程度")
					.setFont("宋体", 12, true, false, false, false).mergeTo(2, 7);
			writer.addCell(1, 8, "是否存在隐患")
					.setFont("宋体", 12, true, false, false, false).mergeTo(2, 8);
			writer.addCell(1, 9, "隐患情况")
					.setFont("宋体", 12, true, false, false, false).mergeTo(2, 9);
			writer.addCell(1, 10, "隐患整改情况")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 10);
			writer.addCell(1, 11, "有无治安负责人")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 11);
			writer.addCell(1, 12, "最新巡场记录")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 12);
			writer.addCell(1, 13, "备注")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 13);
			writer.addCell(1, 14, "是否取消关注")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 14);
			writer.addCell(1, 15, "取消关注时间")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 15);
			writer.addCell(1, 16, "取消关注原因")
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(2, 16);
		} else {
		}

	}

	public List<Enterprise> getNeedExportDatas(int page) {
		if (enterprise != null) {
			enterpriseSearchCondition
					.setIsEmphasis(enterprise.getIsEmphasis() ? 0L : 1L);
		}

		if (location != null) {
			enterpriseSearchCondition.setIsEmphasis(location.getIsEmphasis());
		}

		if (keyType != null) {
			enterpriseSearchCondition.setKeyType(keyType);
		}
		if (pageOnly) {
			return searchEnterpriseDao.searchEnterpriseForExport(
					enterpriseSearchCondition, page, rows, sidx, sord);
		} else {
			return searchEnterpriseDao.searchEnterpriseForExport(
					enterpriseSearchCondition, null, null, sidx, sord);
		}
	}

	public String searchEnterpriseForAutoComplete() throws Exception {
		if (orgId == null) {
			orgId = ThreadVariable.getUser().getOrganization().getId();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		List<Enterprise> emEnterprises = searchEnterpriseDao
				.findEnterpriseByNameAndPinyinAndOrgInternalCode(tag,
						organization.getOrgInternalCode());
		for (Enterprise enterprise : emEnterprises) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setLabel(enterprise.getName());
			autoCompleteData.setDesc(enterprise.getAddress());
			autoCompleteData.setValue(enterprise.getId().toString());
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
		if (searchSafetyProductionKeyVo == null && searchEnterpriseVo == null) {
			gridPage = new GridPage(emptyOtherLocalePage(rows));
		} else {
			fastSearchCommonality();
		}
		return SUCCESS;
	}

	private void fastSearchCommonality() {
		Organization ownerOrg = organizationDubboService
				.getSimpleOrgById(orgId);
		if (searchEnterpriseVo != null) {
			searchSafetyProductionKeyVo = searchEnterpriseVo;
		}
		searchSafetyProductionKeyVo.setOrgInternalCode(ownerOrg
				.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchEnterpriseDao.searchEnterprise(
						searchSafetyProductionKeyVo, page, rows, sidx, sord),
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

	public OrganizationDubboService getOrganizationDubboService() {
		return organizationDubboService;
	}

	public void setOrganizationDubboService(
			OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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

	public Organization getOwnerOrg() {
		return ownerOrg;
	}

	public void setOwnerOrg(Organization ownerOrg) {
		this.ownerOrg = ownerOrg;
	}

	public String getIssueSerch() {
		return issueSerch;
	}

	public void setIssueSerch(String issueSerch) {
		this.issueSerch = issueSerch;
	}

	public PropertyDictService getPropertyDictService() {
		return propertyDictService;
	}

	public void setPropertyDictService(PropertyDictService propertyDictService) {
		this.propertyDictService = propertyDictService;
	}

	public SearchEnterpriseVo getEnterpriseSearchCondition() {
		return enterpriseSearchCondition;
	}

	public void setEnterpriseSearchCondition(
			SearchEnterpriseVo enterpriseSearchCondition) {
		this.enterpriseSearchCondition = enterpriseSearchCondition;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public SearchEnterpriseVo getSearchSafetyProductionKeyVo() {
		return searchSafetyProductionKeyVo;
	}

	public void setSearchSafetyProductionKeyVo(
			SearchEnterpriseVo searchSafetyProductionKeyVo) {
		this.searchSafetyProductionKeyVo = searchSafetyProductionKeyVo;
	}

	public SearchEnterpriseVo getLocation() {
		return location;
	}

	public void setLocation(SearchEnterpriseVo location) {
		this.location = location;
	}

}
