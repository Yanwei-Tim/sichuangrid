package com.tianque.plugin.tqSearch.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSON;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.tqSearch.constants.RelationShipConfig;
import com.tianque.plugin.tqSearch.constants.TqSearchType;
import com.tianque.plugin.tqSearch.constants.TqSearchUrl;
import com.tianque.plugin.tqSearch.domain.RelationShipResults;
import com.tianque.plugin.tqSearch.domain.TqSearchHisHot;
import com.tianque.plugin.tqSearch.domain.TqSearchResults;
import com.tianque.plugin.tqSearch.domain.TqSearchVo;
import com.tianque.plugin.tqSearch.dubboService.TqSearchService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/baseinfo/tqSearch")
@Controller("tqSearchContorller")
@Scope("prototype")
public class TqSearchContorller extends SearchBaseAction {
	@Autowired
	private TqSearchService tqSearchService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private TqSearchResults tqSearchResults;
	private RelationShipResults relationShipResults;
	private String hishotInfo;
	private TqSearchVo tqSearchVo;
	private Map<String, String> searchs;
	private JSON detailUrls;
	private String ids;
	private String sty;
	private String sq;

	@Action(value = "dispatch", results = {
			@Result(name = "populationSearch", location = "/template/tqSearch/searchInclude/populationSearchDlg.ftl"),
			@Result(name = "issueSearch", location = "/template/tqSearch/searchInclude/issueSearchDlg.ftl"),
			@Result(name = "placeSearch", location = "/template/tqSearch/searchInclude/placeSearchDlg.ftl"),
			@Result(name = "houseSearch", location = "/template/tqSearch/searchInclude/houseSearchDlg.ftl"),
			@Result(name = "fileSearch", location = "/template/tqSearch/searchInclude/fileSearchDlg.ftl"),
			@Result(name = "index", location = "/template/tqSearch/tqSearchIndex.ftl"),
			@Result(name = "list", location = "/template/tqSearch/tqSearchIndex.ftl"),
			@Result(name = "listRight", location = "/template/tqSearch/tqSearchListRight.ftl"),
			@Result(name = "error", type = "json", params = { "root",
					"tqSearchResults", "ignoreHierarchy", "false" }) })
	public String dispatch() throws Exception {
		return mode;
	}

	@Action(value = "detailList", results = {
			@Result(name = "populationSearch", location = "/template/tqSearch/detailList/populationDetailList.ftl"),
			@Result(name = "issueSearch", location = "/template/tqSearch/detailList/issueDetailList.ftl"),
			@Result(name = "placeSearch", location = "/template/tqSearch/detailList/placeDetailList.ftl"),
			@Result(name = "serviceRecordSearch", location = "/template/tqSearch/detailList/serviceRecordDetailList.ftl"),
			@Result(name = "dustbinSearch", location = "/template/tqSearch/detailList/dustbinDetailList.ftl"),
			@Result(name = "primaryMembersSearch", location = "/template/tqSearch/detailList/primaryMembersDetailList.ftl"),
			@Result(name = "accountSearch", location = "/template/tqSearch/detailList/accountDetailList.ftl"),
			@Result(name = "error", type = "json", params = { "root",
					"tqSearchResults", "ignoreHierarchy", "false" }) })
	public String detailList() throws Exception {
		return mode;
	}

	@Action(value = "tqSearch", results = { @Result(name = "success", type = "json", params = {
			"root", "tqSearchResults", "ignoreHierarchy", "false" }) })
	public String tqSearch() throws Exception {
		if (tqSearchVo == null || tqSearchVo.getOrgId() == null) {
			throw new BusinessValidationException("请输入查询条件及组织机构值！");
		}
		Organization org = organizationDubboService.getSimpleOrgById(tqSearchVo
				.getOrgId());
		if (tqSearchVo.getOrgId() != null) {
			if (TqSearchType.ISSUE_KEY.equals(type)) {
				tqSearchVo.getSearchs().put("targetOrgInternalCode",
						org.getOrgInternalCode() + "*");
			} else {
				tqSearchVo.getSearchs().put("orgInternalCode",
						org.getOrgInternalCode() + "*");
			}
		}
		tqSearchVo.setUserName(ThreadVariable.getSession().getUserName());
		tqSearchVo.setType(type);
		tqSearchResults = tqSearchService.queryForResult(tqSearchVo);
		if (tqSearchResults == null) {
			tqSearchResults = new TqSearchResults();
		} else {
			processAllOrgRelativeName(tqSearchVo.getOrgId());
		}
		return SUCCESS;
	}

	@Action(value = "tqSearchHot", results = { @Result(name = "success", type = "json", params = {
			"root", "hishotInfo" }) })
	public String tqSearchHot() throws Exception {
		List<TqSearchHisHot> hishots = tqSearchService.queryForHot(type);
		hishotInfo = TqSearchHisHot.toFlashInfo(hishots);
		return SUCCESS;
	}

	@Action(value = "tqSearchHis", results = { @Result(name = "success", type = "json", params = {
			"root", "hishotInfo" }) })
	public String tqSearchHis() throws Exception {
		List<TqSearchHisHot> hishots = tqSearchService
				.queryForHis(ThreadVariable.getUser().getUserName());
		hishotInfo = TqSearchHisHot.toFlashInfo(hishots);
		return SUCCESS;
	}

	@Action(value = "tqSearchRelationship", results = { @Result(name = "success", type = "json", params = {
			"root", "relationShipResults" }) })
	public String tqSearchRelationship() throws Exception {
		List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
		relationShipResults = new RelationShipResults();
		Map<String, Object> domain = getByTypeAndId(type, ids);
		for (Iterator<Entry<String, String>> it = searchs.entrySet().iterator(); it
				.hasNext();) {
			Entry<String, String> entry = it.next();
			String indexType = entry.getKey();
			String shipType = entry.getValue();
			String sql = RelationShipConfig.getSqlByKey(shipType, domain);
			if (!StringUtil.isStringAvaliable(sql)) {
				continue;
			}
			TqSearchVo tqSearchVo = new TqSearchVo(indexType, sql);
			if (RelationShipConfig.getByKey(shipType).isList()) {
				tqSearchVo.setRows(1);
			}
			if (TqSearchType.PRIMARYMEMBERS_KEY.equals(type)
					|| TqSearchType.PARTYMEMBERS_KEY.equals(type)
					|| TqSearchType.ACCOUNT_KEY.equals(type)) {
				tqSearchVo.getSearchs().put("orgInternalCode",
						ThreadVariable.getOrganization().getOrgInternalCode());
			}
			tqSearchResults = tqSearchService.queryForResult(tqSearchVo);
			children.addAll(formatDetailType(entry, tqSearchResults));
		}
		relationShipResults.setChildren(children);
		relationShipResults.setName(RelationShipConfig.getTitleByKey(type,
				domain));
		return SUCCESS;
	}

	@Action(value = "tqSearchDetailList", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage" }) })
	public String tqSearchDetailList() throws Exception {
		String searchType = searchs.get("searchType");
		String searchField = searchs.get("searchField");
		String id = searchs.get("id");
		String type = searchs.get("type");
		Map<String, Object> domain = getByTypeAndId(type, id);
		String sql = RelationShipConfig.getSqlByKey(searchField, domain);
		TqSearchVo tqSearchVo = new TqSearchVo(searchType, sql);
		tqSearchResults = tqSearchService.queryForResult(tqSearchVo);
		gridPage = new GridPage(new PageInfo(tqSearchResults.getPageNum(),
				tqSearchResults.getPageSize(), tqSearchResults.getCountNum(),
				tqSearchResults.getResult()));
		return SUCCESS;
	}

	@Action(value = "findDetailUrls", results = { @Result(name = "success", type = "json", params = {
			"root", "detailUrls" }) })
	public String findDetailUrls() throws Exception {
		detailUrls = TqSearchUrl.toJSON();
		return SUCCESS;
	}

	private Map<String, Object> getByTypeAndId(String type, String id) {
		if (!StringUtil.isStringAvaliable(type) || id == null) {
			throw new BusinessValidationException("参数错误");
		}
		TqSearchVo tqSearchVo = new TqSearchVo();
		tqSearchVo.setType(type);
		tqSearchVo.getSearchs().put("id", id);
		Object obj = tqSearchService.queryForObject(tqSearchVo);
		return (obj != null) ? (Map<String, Object>) obj : null;
	}

	private List<Map<String, Object>> formatDetailType(// 点击详情，是出现列表，还是查看内容
			Entry<String, String> entry, TqSearchResults tqSearchResults) {
		if (tqSearchResults == null) {
			return new ArrayList<Map<String, Object>>();
		}
		List<Map<String, Object>> result = tqSearchResults.getResult();
		boolean isList = RelationShipConfig.getByKey(entry.getValue()).isList();
		for (int i = 0; i < result.size(); i++) {
			if (isList) {
				result.get(i).put("detailType", "list");
			}
			result.get(i).put("detailSearchType", entry.getKey());
			result.get(i).put("detailSearchField", entry.getValue());
			result.get(i).put("detailRelationId", ids);
			result.get(i).put("detailRelationType", type);
		}
		return result;
	}

	private void processAllOrgRelativeName(Long organizationId) {
		if (tqSearchResults != null && tqSearchResults.getResult() != null) {
			Map<String, Object> orgIds = null;
			for (int i = 0; i < tqSearchResults.getResult().size(); i++) {
				Map<String, Object> map = tqSearchResults.getResult().get(i);
				orgIds = new HashMap<String, Object>();
				if (type != null && TqSearchType.ISSUE_KEY.equals(type)) {
					orgIds.put("occurOrgName", map.get("occurOrgId"));
					orgIds.put("targetOrgName", map.get("targetOrgId"));
				} else {
					orgIds.put("orgName", map.get("orgId"));
				}
				for (Iterator<Entry<String, Object>> it = orgIds.entrySet()
						.iterator(); it.hasNext();) {
					Entry<String, Object> orgName_Id = it.next();
					String orgNameField = orgName_Id.getKey();
					Object orgId = orgName_Id.getValue();
					if (orgId != null) {
						tqSearchResults
								.getResult()
								.get(i)
								.put(orgNameField,
										ControllerHelper.getRelativeOrgNameListByOrgId(
												Long.valueOf(orgId + ""),
												organizationDubboService, null));
					}
				}
			}
		}
	}

	public TqSearchResults getTqSearchResults() {
		return tqSearchResults;
	}

	public void setTqSearchResults(TqSearchResults tqSearchResults) {
		this.tqSearchResults = tqSearchResults;
	}

	public String getHishotInfo() {
		return hishotInfo;
	}

	public void setHishotInfo(String hishotInfo) {
		this.hishotInfo = hishotInfo;
	}

	public TqSearchVo getTqSearchVo() {
		return tqSearchVo;
	}

	public void setTqSearchVo(TqSearchVo tqSearchVo) {
		this.tqSearchVo = tqSearchVo;
	}

	public Map<String, String> getSearchs() {
		return searchs;
	}

	public void setSearchs(Map<String, String> searchs) {
		this.searchs = searchs;
	}

	public RelationShipResults getRelationShipResults() {
		return relationShipResults;
	}

	public void setRelationShipResults(RelationShipResults relationShipResults) {
		this.relationShipResults = relationShipResults;
	}

	public JSON getDetailUrls() {
		return detailUrls;
	}

	public void setDetailUrls(JSON detailUrls) {
		this.detailUrls = detailUrls;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSty() {
		return sty;
	}

	public void setSty(String sty) {
		this.sty = sty;
	}

	public String getSq() {
		return sq;
	}

	public void setSq(String sq) {
		this.sq = sq;
	}

}
