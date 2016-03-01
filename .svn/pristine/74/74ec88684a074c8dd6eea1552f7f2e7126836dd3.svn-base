package com.tianque.baseInfo.familyInfo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.familyInfo.domain.GroupFamily;
import com.tianque.baseInfo.familyInfo.domain.GroupFamilyHasPopulation;
import com.tianque.baseInfo.familyInfo.domain.SavePeople;
import com.tianque.baseInfo.familyInfo.domain.SearchGroupFamilyVo;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.service.util.PopulationCatalog;

@Repository("groupFamilyDao")
public class GroupFamilyDaoImpl extends AbstractBaseDao implements
		GroupFamilyDao {

	@Override
	public long addGroupFamily(GroupFamily groupFamily) {
		return (Long) getSqlMapClientTemplate().insert(
				"groupFamily.addGroupFamily", groupFamily);
	}

	@Override
	public void addGroupFamilyHasPopulation(
			GroupFamilyHasPopulation groupFamilyHasPopulation) {
		getSqlMapClientTemplate().insert(
				"groupFamily.addGroupFamilyHasPopulation",
				groupFamilyHasPopulation);
	}

	@Override
	public PageInfo<GroupFamily> pageGroupFamiliesByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long dictId, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("catalogs",
				formCatalogs(PopulationCatalog.getAllActualPopulationCatalog()));
		if (StringUtil.isStringAvaliable(sidx)) {
			map.put("sortField", sidx);
			map.put("order", sord);
		}
		map.put("parentsId", dictId);
		map.put("shardCode", shardCode);
		int countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"groupFamily.countGroupFamilies", map);
		return createPageInfo(
				pageNum,
				pageSize,
				countNum,
				getSqlMapClientTemplate().queryForList(
						"groupFamily.listGroupFamilies", map,
						(pageNum - 1) * pageSize, pageSize));
	}

	@Override
	public PageInfo<GroupFamily> pageGroupFamiliesBySearchGroupFamilyVo(
			String shardCode, SearchGroupFamilyVo searchGroupFamilyVo,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", searchGroupFamilyVo.getOrgInternalCode());
		map.put("familyAccount", searchGroupFamilyVo.getFamilyAccount());
		map.put("familyMaster", searchGroupFamilyVo.getFamilyMaster());
		map.put("masterCardNo", searchGroupFamilyVo.getMasterCardNo());
		map.put("catalogs",
				formCatalogs(PopulationCatalog.getAllActualPopulationCatalog()));
		if (StringUtil.isStringAvaliable(sidx)) {
			map.put("sortField", sidx);
			map.put("order", sord);
		}
		map.put("fastSearchKeyWords",
				searchGroupFamilyVo.getFastSearchKeyWords());
		map.put("shardCode", shardCode);
		int countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"groupFamily.countGroupFamilies", map);
		return createPageInfo(
				pageNum,
				pageSize,
				countNum,
				getSqlMapClientTemplate().queryForList(
						"groupFamily.listGroupFamilies", map,
						(pageNum - 1) * pageSize, pageSize));
	}

	@Override
	public PageInfo<GroupFamily> pageGroupFamiliesByFullSearchGroupFamilyVo(
			SearchGroupFamilyVo searchGroupFamilyVo, Integer pageNum,
			Integer pageSize, String sidx, String sord, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", searchGroupFamilyVo.getOrgInternalCode());
		map.put("familyAccount", searchGroupFamilyVo.getFamilyAccount());
		map.put("familyMaster", searchGroupFamilyVo.getFamilyMaster());
		map.put("masterCardNo", searchGroupFamilyVo.getMasterCardNo());
		map.put("familyAddress", searchGroupFamilyVo.getFamilyAddress());
		map.put("memberName", searchGroupFamilyVo.getMemberName());
		map.put("membersCount", searchGroupFamilyVo.getMembersCount());
		map.put("parentsId", searchGroupFamilyVo.getDictId());
		map.put("catalogs",
				formCatalogs(PopulationCatalog.getAllActualPopulationCatalog()));
		if (StringUtil.isStringAvaliable(sidx)) {
			map.put("sortField", sidx);
			map.put("order", sord);
		}
		map.put("shardCode", shardCode);
		int countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"groupFamily.countGroupFamiliesByFullSearchGroupFamilyVo", map);
		return createPageInfo(
				pageNum,
				pageSize,
				countNum,
				getSqlMapClientTemplate()
						.queryForList(
								"groupFamily.listGroupFamiliesByFullSearchGroupFamilyVo",
								map, (pageNum - 1) * pageSize, pageSize));
	}

	private PageInfo createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public GroupFamily updateGroupFamily(GroupFamily groupFamily) {
		getSqlMapClientTemplate().update("groupFamily.updateGroupFamily",
				groupFamily);
		return getGroupFamilyById(groupFamily.getId());
	}

	@Override
	public GroupFamily getGroupFamilyById(Long id) {
		return (GroupFamily) getSqlMapClientTemplate().queryForObject(
				"groupFamily.getGroupFamilyById", id);
	}

	@Override
	public GroupFamily findGroupFamilyByFamilyAccountAndOrgInternalCode(
			String familyAccount, String orgInternalCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("familyAccount", familyAccount);
		map.put("orgInternalCode", orgInternalCode);
		return (GroupFamily) getSqlMapClientTemplate().queryForObject(
				"groupFamily.findGroupFamilyByFamilyAccountAndOrgInternalCode",
				map);
	}

	@Override
	public GroupFamilyHasPopulation getGroupFamilyHasPopulationByFamilyIdAndPopulationIdAndPopulationType(
			Long familyId, Long populationId, String populationType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("familyId", familyId);
		map.put("populationId", populationId);
		map.put("populationType", populationType);
		return (GroupFamilyHasPopulation) getSqlMapClientTemplate()
				.queryForObject(
						"groupFamily.getGroupFamilyHasPopulationByFamilyIdAndPopulationIdAndPopulationType",
						map);
	}

	@Override
	public int deleteGroupFamilyById(Long id) {
		return getSqlMapClientTemplate().delete(
				"groupFamily.deleteGroupFamilyById", id);
	}

	@Override
	public int deleteGroupFamilyHasPopulationByFamilyIdAndPopulationIdAndPopulationType(
			Long familyId, Long populationId, String populationType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("familyId", familyId);
		map.put("populationId", populationId);
		map.put("populationType", populationType);
		return getSqlMapClientTemplate()
				.delete("groupFamily.deleteGroupFamilyHasPopulationByFamilyIdAndPopulationIdAndPopulationType",
						map);
	}

	private List<Map<String, Object>> formCatalogs(
			List<PopulationCatalog> catalogs) {
		List<Map<String, Object>> catalogsParams = new ArrayList<Map<String, Object>>();
		for (PopulationCatalog populationCatalog : catalogs) {
			catalogsParams
					.add(constructPopulationTableParams(populationCatalog));
		}
		return catalogsParams;
	}

	private Map<String, Object> constructPopulationTableParams(
			PopulationCatalog catalogs) {
		Map<String, Object> catalogParam = new LinkedHashMap<String, Object>();
		catalogParam.put("tableName", catalogs.getTableName());
		catalogParam.put("type", catalogs.getCatalog());
		return catalogParam;
	}

	@Override
	public PageInfo<GroupFamilyHasPopulation> pageFamilyMembersByFamilyIdAndOrgInternalCode(
			Long familyId, String orgInternalCode, Integer pageNum,
			Integer pageSize, String sidx, String sord, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("familyId", familyId);
		map.put("catalogs",
				formCatalogs(PopulationCatalog.getAllActualPopulationCatalog()));
		if (StringUtil.isStringAvaliable(sidx)) {
			map.put("sortField", "g.populationid, g.populationtype");
			map.put("order", sord);
		}
		map.put("shardCode", shardCode);
		int countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"groupFamily.countFamilyMembersByFamilyId", map);
		return createPageInfo(
				pageNum,
				pageSize,
				countNum,
				getSqlMapClientTemplate().queryForList(
						"groupFamily.listFamilyMembersByFamilyId", map,
						(pageNum - 1) * pageSize, pageSize));
	}

	@Override
	public List<GroupFamilyHasPopulation> getFamilyMembersByFamilyId(
			Long familyId) {
		return getSqlMapClientTemplate().queryForList(
				"groupFamily.getFamilyMembersByFamilyId", familyId);
	}

	@Override
	public List<GroupFamilyHasPopulation> findFamilyMembersByFamilyIdAndOrgCode(
			Long familyId, String orgCode, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgCode);
		map.put("familyId", familyId);
		map.put("catalogs",
				formCatalogs(PopulationCatalog.getAllActualPopulationCatalog()));
		map.put("shardCode", shardCode);
		return getSqlMapClientTemplate().queryForList(
				"groupFamily.findFamilyMembersByFamilyIdAndOrgCode", map);
	}

	@Override
	public int deleteGroupFamilyHasPopulationByFamilyId(Long familyId) {
		return getSqlMapClientTemplate().delete(
				"groupFamily.deleteGroupFamilyHasPopulationByFamilyId",
				familyId);
	}

	@Override
	public void updateGroupFamilyAccount(Long id, String newFamilyAccount) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("newFamilyAccount", newFamilyAccount);
		map.put("updateDate", new Date());
		getSqlMapClientTemplate().update(
				"groupFamily.updateGroupFamilyAccount", map);

	}

	@Override
	public GroupFamilyHasPopulation getGroupFamilyHasPopulationByPopulationIdAndPopulationType(
			Long populationId, String populationType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("populationId", populationId);
		map.put("populationType", populationType);
		return (GroupFamilyHasPopulation) getSqlMapClientTemplate()
				.queryForObject(
						"groupFamily.getGroupFamilyHasPopulationByPopulationIdAndPopulationType",
						map);

	}

	@Override
	public void updateGroupFamilyMasterToNull(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("updateDate", new Date());
		getSqlMapClientTemplate().update(
				"groupFamily.updateGroupFamilyMasterToNull", map);
	}

	@Override
	public void updateGroupFamilyHasPopulationRelation(Long populationId,
			String populationType, Long familyRelationId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("populationId", populationId);
		map.put("populationType", populationType);
		map.put("familyrelation", familyRelationId);
		getSqlMapClientTemplate().update(
				"groupFamily.updateGroupFamilyHasPopulationRelation", map);
	}

	@Override
	public void updateGroupFamilyHasPopulationByFamilyIdAndFamilyrelation(
			Long familyId, Long familyRelationId, Long previousFamilyrelation) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("familyId", familyId);
		map.put("familyRelationId", familyRelationId);
		map.put("previousFamilyrelation", previousFamilyrelation);
		getSqlMapClientTemplate()
				.update("groupFamily.updateGroupFamilyHasPopulationByFamilyIdAndFamilyrelation",
						map);
	}

	@Override
	public GroupFamilyHasPopulation getFamilyMembersByFamilyIdAndOrgCodeAndFamilyrelation(
			Long familyId, Long familyrelation, String orgCode, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgCode);
		map.put("familyId", familyId);
		map.put("familyrelation", familyrelation);
		map.put("catalogs",
				formCatalogs(PopulationCatalog.getAllActualPopulationCatalog()));
		map.put("shardCode", shardCode);
		return (GroupFamilyHasPopulation) getSqlMapClientTemplate()
				.queryForObject(
						"groupFamily.getFamilyMembersByFamilyIdAndOrgCodeAndFamilyrelation",
						map);
	}

	@Override
	public SavePeople getPeopleByIdcardNoAndOrgCode(String idCardNo,
			String orgCode, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgCode);
		map.put("idCardNo", idCardNo);
		map.put("catalogs",
				formCatalogs(PopulationCatalog.getAllActualPopulationCatalog()));
		map.put("shardCode", shardCode);
		return (SavePeople) getSqlMapClientTemplate().queryForObject(
				"groupFamily.getPeopleByIdcardNoAndOrgCode", map);
	}

	@Override
	public PageInfo<GroupFamilyHasPopulation> pageFamilyMembersByFamilyIdNoMaster(
			Long familyId, Long familyRelationId, String orgInternalCode,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("familyrelation", familyRelationId);
		map.put("familyId", familyId);
		int countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"groupFamily.countFamilyMembersByFamilyIdNomaster", map);
		map.put("orgInternalCode", orgInternalCode);
		map.put("catalogs",
				formCatalogs(PopulationCatalog.getAllActualPopulationCatalog()));
		if (StringUtil.isStringAvaliable(sidx)) {
			map.put("sortField", "g.populationid, g.populationtype");
			map.put("order", sord);
		}
		map.put("shardCode", shardCode);
		return createPageInfo(
				pageNum,
				pageSize,
				countNum,
				getSqlMapClientTemplate().queryForList(
						"groupFamily.pageFamilyMembersByFamilyIdNoMaster", map,
						(pageNum - 1) * pageSize, pageSize));
	}

	@Override
	public void updateGroupFamilyMemberCountByGroupFamilyId(Long groupFamilyId,
			int membersCount) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", groupFamilyId);
		map.put("membersCount", membersCount);
		getSqlMapClientTemplate().update(
				"groupFamily.updateGroupFamilyMemberCountByGroupFamilyId", map);
	}

	@Override
	public GroupFamily getGroupFamilyByOrgCodeAndFamilyAccount(
			String orgInternalCode, String familyAccount) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("familyAccount", familyAccount);
		return (GroupFamily) getSqlMapClientTemplate().queryForObject(
				"groupFamily.getGroupFamilyByOrgCodeAndFamilyAccount", map);
	}

	@Override
	public int countFamilyMembersByFamilyId(Long familyId) {
		int countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"groupFamily.countNormalFamilyMembersByFamilyId", familyId);
		return countNum;
	}

	@Override
	public void updateGroupFamilyIdcardById(GroupFamily groupFamily) {
		getSqlMapClientTemplate().update(
				"groupFamily.updateGroupFamilyIdcardById", groupFamily);

	}
}
