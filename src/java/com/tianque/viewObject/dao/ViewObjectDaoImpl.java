package com.tianque.viewObject.dao;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.viewObject.vo.ViewObjectVo;

@Repository("viewObjectDao")
public class ViewObjectDaoImpl extends BaseDaoImpl<ViewObjectVo, ViewObjectVo>
		implements ViewObjectDao {

	// 方法没有显式调用
	// @Override
	// public ViewObjectVo getViewObjectDefNum(List<Map<String, Object>> list) {
	// ViewObjectVo resultVo = new ViewObjectVo();
	// List<Integer> listNum = getSqlMapClientTemplate().queryForList(
	// "viewObjectVo.getViewObjectDefNum", list);
	// resultVo.setDefProvinceNum(listNum.get(0));
	// resultVo.setDefCityNum(listNum.get(1));
	// resultVo.setDefDistrictNum(listNum.get(2));
	// resultVo.setDefTownNum(listNum.get(3));
	// resultVo.setDefVillageNum(listNum.get(4));
	// resultVo.setDefGridNum(listNum.get(5));
	// resultVo.setDefProvinceFucDepartmentNum(listNum.get(6));
	// resultVo.setDefCityFucDepartmentNum(listNum.get(7));
	// resultVo.setDefDistrictFucDepartmentNum(listNum.get(8));
	// resultVo.setDefTownFucDepartmentNum(listNum.get(9));
	// return resultVo;
	// }

	// @Override
	// public List<Long> getOrgIdsWhenSelectAll(Long[] longsByStrings) {
	// Map<String, Object> map = new HashMap<String, Object>();
	// String userOrg = ThreadVariable.getOrganization().getOrgInternalCode();
	// if (ThreadVariable.getOrganization().getOrgType() != null
	// && ThreadVariable.getOrganization().getOrgType()
	// .getInternalId() == OrganizationType.FUNCTIONAL_ORG
	// && ThreadVariable.getOrganization().getParentOrg() != null) {
	// userOrg = ThreadVariable.getOrganization().getParentOrg()
	// .getOrgInternalCode();
	// }
	// map.put("orgCode", userOrg);
	// map.put("longs", longsByStrings);
	//
	// return getSqlMapClientTemplate().queryForList(
	// "viewObjectVo.getOrgIdsWhenSelectAll", map);
	//
	// }

	// @Override
	// public List<Long> getOrgIdsWhenSelectByLevel(
	// List<Map<String, Object>> selectedLevelList) {
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("list", selectedLevelList);
	// return getSqlMapClientTemplate().queryForList(
	// "viewObjectVo.getOrgIdsWhenSelectByLevel", map);
	// }
	// @Override
	// public List<String> getSelectedOrgNamesByOrgIdsAndTypeLevel(
	// List<Long> orgIds, Long typeId, Long levelId) {
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("orgIds", orgIds);
	// map.put("levelId", levelId);
	// map.put("typeId", typeId);
	// return getSqlMapClientTemplate().queryForList(
	// "viewObjectVo.getSelectedOrgNamesByOrgIdsAndTypeLevel", map);
	//
	// }

	// @Override
	// public List<Long> getOrgIdsMobile(String orgCode, Integer levelType) {
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("orgCode", orgCode);
	// map.put("levelType", levelType);
	// return getSqlMapClientTemplate().queryForList(
	// "viewObjectVo.getOrgIdsMobile", map);
	// }

}
