package com.tianque.controller;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModelType;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.dao.SearchEstateInformationDao;
import com.tianque.datatransfer.DataExportHelper;
import com.tianque.datatransfer.ExcelWriter;
import com.tianque.domain.EstateInformation;
import com.tianque.domain.Organization;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.SearchEstateInformationVo;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.vladium.logging.Logger;

@SuppressWarnings("serial")
@Controller("searchEstateInformationController")
@Scope("prototype")
public class SearchEstateInformationController extends BaseAction {
	@Autowired
	private SearchEstateInformationDao searchEstateInformationDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;

	private SearchEstateInformationVo searchEstateInformationVo;
	@Autowired
	private SystemLogService systemLogService;
	private Long orgId;

	private boolean pageOnly;

	private Organization ownerOrg;

	@PermissionFilter(ename = "searchEstateInformation")
	public String searchEstateInformation() throws Exception {
		if (searchEstateInformationVo == null) {
			searchEstateInformationVo = new SearchEstateInformationVo();
		}
		sureSearchCondition();
		gridPage = new GridPage(
				ControllerHelper.proccessRelativeOrgNameByPageInfo(
						searchEstateInformationDao.searchEstateInformations(
								searchEstateInformationVo, page, rows, sidx,
								sord), organizationDubboService));
		return SUCCESS;
	}

	@PermissionFilter(ename = "downloadEstateInformation")
	public String downloadEstateInformation() throws Exception {
		if (searchEstateInformationVo == null && orgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			populateOrgCondition();
			List<EstateInformation> records = getNeedExportDatas(page);
			Organization organization = organizationDubboService
					.getSimpleOrgById(orgId);
			if (orgId != null) {

				if (organization != null) {
					searchEstateInformationVo.setOrgInternalCode(organization
							.getOrgInternalCode());
				} else {
					searchEstateInformationVo.setOrgInternalCode(null);
				}
			} else {
				searchEstateInformationVo.setOrgInternalCode(null);
			}
			inputStream = exportSpecialCarePerson(records);
			downloadFileName = new String("房产信息表".getBytes("gbk"), "ISO8859-1")
					+ ".xls";
			systemLogService
					.log(Logger.INFO,
							ModelType.BASE,
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "在"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													organization.getId(),
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId())
									+ " 导出房产信息", ObjectToJSON
									.convertJSON(searchEstateInformationVo));

		}
		return SUCCESS;
	}

	private void sureSearchCondition() {
		if (orgId != null) {
			Organization organization = organizationDubboService
					.getSimpleOrgById(orgId);
			if (organization != null) {
				searchEstateInformationVo.setOrgInternalCode(organization
						.getOrgInternalCode());
			}
		}
	}

	private void populateOrgCondition() {
		if (searchEstateInformationVo == null) {
			searchEstateInformationVo = new SearchEstateInformationVo();
		}
		if (orgId != null) {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org != null) {
				searchEstateInformationVo.setOrgInternalCode(org
						.getOrgInternalCode());
			} else {
				searchEstateInformationVo.setOrgInternalCode(null);
			}
		} else {
			searchEstateInformationVo.setOrgInternalCode(null);
		}

	}

	private List<EstateInformation> getNeedExportDatas(int page) {
		if (pageOnly) {
			return searchEstateInformationDao.searchEstateInformationForExport(
					searchEstateInformationVo, page, rows, sidx, sord);
		} else {
			return searchEstateInformationDao.searchEstateInformationForExport(
					searchEstateInformationVo, null, null, sidx, sord);
		}
	}

	private InputStream exportSpecialCarePerson(List<EstateInformation> records)
			throws Exception {
		ExcelWriter writer = constructExcelWriter();
		DataExportHelper helper = constructDataExportHelper();
		writer.addWorkSheet("房产信息");
		appendTitle(writer, "房产信息表");
		appendTableHead(writer);
		for (int index = 0; index < records.size(); index++) {
			EstateInformation record = records.get(index);
			appendRow(writer, helper, index + 2, record);
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

	private void appendTitle(ExcelWriter write, String title) {
		write.addCell(0, 0, title).mergeTo(0, 17)
				.setFont("宋体", 20, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
	}

	private void appendTableHead(ExcelWriter writer) {
		writer.addCell(1, 0, "房产证号")
				.setFont("宋体", 12, true, false, false, false).mergeTo(1, 0);
		writer.addCell(1, 1, "土地证号")
				.setFont("宋体", 12, true, false, false, false).mergeTo(1, 1);
		writer.addCell(1, 2, "所属网格")
				.setFont("宋体", 12, true, false, false, false).mergeTo(1, 2);
		writer.addCell(1, 3, "住宅产权")
				.setFont("宋体", 12, true, false, false, false).mergeTo(1, 3);
		writer.addCell(1, 4, "建筑结构")
				.setFont("宋体", 12, true, false, false, false).mergeTo(1, 4);
		writer.addCell(1, 5, "建筑面积")
				.setFont("宋体", 12, true, false, false, false).mergeTo(1, 5);
		writer.addCell(1, 6, "占地面积")
				.setFont("宋体", 12, true, false, false, false).mergeTo(1, 6);
		writer.addCell(1, 7, "建构年代")
				.setFont("宋体", 12, true, false, false, false).mergeTo(1, 7);
		writer.addCell(1, 8, "房产地址")
				.setFont("宋体", 12, true, false, false, false).mergeTo(1, 8);
		writer.addCell(1, 9, "有出租房")
				.setFont("宋体", 12, true, false, false, false).mergeTo(1, 9);
		writer.addCell(1, 10, "危房")
				.setFont("宋体", 12, true, false, false, false).mergeTo(1, 10);
		writer.addCell(1, 11, "自来水已入户")
				.setFont("宋体", 12, true, false, false, false).mergeTo(1, 11);
		writer.addCell(1, 12, "互联网已入户")
				.setFont("宋体", 12, true, false, false, false).mergeTo(1, 12);
		writer.addCell(1, 13, "有卫生间")
				.setFont("宋体", 12, true, false, false, false).mergeTo(1, 13);
		writer.addCell(1, 14, "出租房地址")
				.setFont("宋体", 12, true, false, false, false).mergeTo(1, 14);
		writer.addCell(1, 15, "出租房房产证号")
				.setFont("宋体", 12, true, false, false, false).mergeTo(1, 15);
		writer.addCell(1, 16, "房主身份证号码(多个房主用逗号分隔)")
				.setFont("宋体", 12, true, false, false, false).mergeTo(1, 16);

	}

	private void appendRow(ExcelWriter writer, DataExportHelper helper,
			int index, EstateInformation record) {
		writer.addCell(index, 0, record.getProprietaryNo());
		writer.addCell(index, 1, record.getLandPermit());
		writer.addCell(index, 2, helper.getOrganizationRelativeName(record
				.getOrganization().getId()));
		writer.addCell(
				index,
				3,
				record.getHouseEquity() == null ? "" : helper
						.getPropertyDictDisplayName(PropertyTypes.HOUSE_EQUITY,
								record.getHouseEquity().getId()));
		writer.addCell(
				index,
				4,
				record.getBuildingStructure() == null ? "" : helper
						.getPropertyDictDisplayName(
								PropertyTypes.BUILDING_STRUCTURE, record
										.getBuildingStructure().getId()));
		writer.addCell(index, 5, record.getCoveredArea() == null ? "" : record
				.getCoveredArea().toString());
		writer.addCell(index, 6, record.getFloorArea() == null ? "" : record
				.getFloorArea().toString());
		writer.addCell(
				index,
				7,
				record.getStructureYear() == null ? "" : helper
						.getPropertyDictDisplayName(
								PropertyTypes.STRUCTURE_YEAR, record
										.getStructureYear().getId()));
		writer.addCell(index, 8, record.getHousePropertyPlace());
		writer.addCell(index, 9, record.isHire());
		writer.addCell(index, 10, record.isDangerousBuilding());
		writer.addCell(index, 11, record.isHasTapWater());
		writer.addCell(index, 12, record.isHasNet());
		writer.addCell(index, 13, record.isHasToilet());
		writer.addCell(index, 14, record.getOccupiedAddress());
		writer.addCell(index, 15, record.getOccupiedNo());

		String idCarNos = "";
		writer.addCell(index, 16, idCarNos);
	}

	public SearchEstateInformationVo getSearchEstateInformationVo() {
		return searchEstateInformationVo;
	}

	public void setSearchEstateInformationVo(
			SearchEstateInformationVo searchEstateInformationVo) {
		this.searchEstateInformationVo = searchEstateInformationVo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public Organization getOwnerOrg() {
		return ownerOrg;
	}

	public void setOwnerOrg(Organization ownerOrg) {
		this.ownerOrg = ownerOrg;
	}

}
