package com.tianque.plugin.account.controller;


import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.account.api.ThreeRecordsComprehensiveDubboService;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.Organization;
import com.tianque.excel.definition.SpecialGroupsContextYinchuan;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.vo.SearchComprehensiveVo;
import com.tianque.plugin.account.vo.ThreeRecordsCatalogVo;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.PropertyDictDubboService;

@Scope("request")
@Namespace("/threeRecords/peopleaspirationCataLogComprehensive")
@Controller("peopleaspirationCataLogComprehensiveController")
public class PeopleaspirationCataLogComprehensiveController extends SearchBaseAction {
	
	private Long orgId;
	private String orgIds;
	private SearchComprehensiveVo searchVo;
	@Autowired
	private ThreeRecordsComprehensiveDubboService comprehensiveDubboService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;
	private List<Organization> childOrg;
	private boolean pageOnly;// 是否导出当前页
	
	public List<ThreeRecordsCatalogVo> getNeedExportDatas(int page){
		List<Long> orgIds = orgIds();
		PageInfo<ThreeRecordsCatalogVo> pageInfo = null;
		if(searchVo.getLedgerType() == LedgerConstants.POORPEOPLE){
			if(pageOnly){
				pageInfo = comprehensiveDubboService.exportPoorPeopleCataLog(searchVo, orgIds, page, rows);
			}else{
				pageInfo = comprehensiveDubboService.exportPoorPeopleCataLog(searchVo, orgIds, 1, Integer.MAX_VALUE);
			}
		}else if(searchVo.getLedgerType() == LedgerConstants.STEADYWORK){
			if(pageOnly){
				pageInfo = comprehensiveDubboService.exportSteadyWorkCataLog(searchVo, orgIds, page, rows);
			}else{
				pageInfo = comprehensiveDubboService.exportSteadyWorkCataLog(searchVo, orgIds, 1, Integer.MAX_VALUE);
			}
		}else{
			if(pageOnly){
				pageInfo = comprehensiveDubboService.exportCataLog(searchVo, orgIds, page, rows);
			}else{
				pageInfo = comprehensiveDubboService.exportCataLog(searchVo, orgIds, 1, Integer.MAX_VALUE);
			}
		}
		return ControllerHelper.processAllOrgRelativeName(pageInfo, organizationDubboService, new String[] { "organization" }, null).getResult();
	}
	
	public String downloadCataLog() throws Exception {
			List<ThreeRecordsCatalogVo> records = getNeedExportDatas(page);
			String title = "中江县三本台账工作民生信息登记目录"; 
			if(searchVo.getLedgerType() == LedgerConstants.POORPEOPLE){
				title = "中江县三本台账工作困难群众信息登记目录";
			}else if(searchVo.getLedgerType() == LedgerConstants.STEADYWORK){
				title = "中江县三本台账工作稳定信息登记目录";
			}
			inputStream = ExcelExportHelper.exportDateToExcel(SpecialGroupsContextYinchuan.getCataLogViewObject(title), records);
			downloadFileName = new String(title.getBytes("gbk"), "ISO8859-1") + ".xls";
		return STREAM_SUCCESS;
	}
	
	public void downloadCataLogAll() throws Exception {
		if (!pageOnly) {
			pageOnly = true;
			List<Long> orgIds = orgIds();
			String title = "中江县三本台账工作民生信息登记目录";
			Integer count = null; 
			if(searchVo.getLedgerType() == LedgerConstants.POORPEOPLE){
				count = comprehensiveDubboService.countPoorPeopleCreateAndDone(searchVo, orgIds, 1, Integer.MAX_VALUE);
			}else if(searchVo.getLedgerType() == LedgerConstants.STEADYWORK){
				count = comprehensiveDubboService.countSteadyWorkCreateAndDone(searchVo, orgIds, 1, Integer.MAX_VALUE);
			}else{
				count = comprehensiveDubboService.countfindJurisdictionsCreateAndDone(searchVo, orgIds, 1, Integer.MAX_VALUE);
			}
			
			if(searchVo.getLedgerType() == LedgerConstants.POORPEOPLE){
				title = "中江县三本台账工作困难群众信息登记目录";
			}else if(searchVo.getLedgerType() == LedgerConstants.STEADYWORK){
				title = "中江县三本台账工作稳定信息登记目录";
			}
			exportDataAll(count, SpecialGroupsContextYinchuan.getCataLogViewObject(title), title);
		}
	}
	
	private List<Long> orgIds(){
		List<Long> ids = new ArrayList<Long>();
		if(StringUtil.isStringAvaliable(orgIds)){
			String[] orgs = orgIds.split(",");
			for(int i = 0; i < orgs.length; i++){
				ids.add(Long.valueOf(orgs[i].trim()));
			}
		}else{
			ids.add(orgId);
		}
		return ids;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public SearchComprehensiveVo getSearchVo() {
		return searchVo;
	}

	public void setSearchVo(SearchComprehensiveVo searchVo) {
		this.searchVo = searchVo;
	}

	public List<Organization> getChildOrg() {
		return childOrg;
	}
	public void setChildOrg(List<Organization> childOrg) {
		this.childOrg = childOrg;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}
	
}
