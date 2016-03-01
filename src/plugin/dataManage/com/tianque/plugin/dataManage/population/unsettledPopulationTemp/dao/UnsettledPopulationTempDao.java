package com.tianque.plugin.dataManage.population.unsettledPopulationTemp.dao;

import com.tianque.plugin.dataManage.population.unsettledPopulationTemp.domain.UnsettledPopulationTemp;

public interface UnsettledPopulationTempDao {

	public UnsettledPopulationTemp getTempById(Long id);

	public void updateTemp(UnsettledPopulationTemp temp);
	// /**
	// * 根据网格内置编码分页查询户籍人口
	// *
	// * @param orgInternalCode
	// * @param pageNum
	// * @param pageSize
	// * @param sortField
	// * @param order
	// * @param searchType
	// * @param currentAddress
	// * @return
	// */
	// PageInfo<UnsettledPopulationTemp> findUnsettledPopulationTempForPage(
	// String orgInternalCode, int pageNum, int pageSize,
	// String sortField, String order, Long searchType,
	// String currentAddress);
	//
	// /**
	// * 导入户籍人口
	// *
	// * @param domain
	// * @return
	// */
	// UnsettledPopulationTemp addUnsettledPopulationTemp(
	// UnsettledPopulationTemp domain);
	//
	// /**
	// * 根据id获取户籍人口
	// *
	// * @param id
	// * @return
	// */
	// UnsettledPopulationTemp getUnsettledPopulationTempById(Long id);
	//
	// /**
	// * 判断是否重复数据
	// *
	// * @param id
	// * @param repeat
	// * @return
	// */
	// UnsettledPopulationTemp updateUnsettledPopulationTempSateById(Long id,
	// Long repeat);
	//
	// /**
	// * 根据身份证号和导入id查询户籍人口
	// *
	// * @param idCardNo
	// * @param oldOrganizationId
	// * 导入时的所属网格
	// * @return
	// */
	// List<UnsettledPopulationTemp>
	// findUnsettledPopulationTempsByIdCardNoAndImportOrgId(
	// List idCardNo, Long oldOrganizationId);
	//
	// /**
	// * 根据id删除户籍人口
	// *
	// * @param id
	// */
	// void deleteUnsettledPopulationTempByIds(Long id);
	//
	// /**
	// * 根据身份证号和网格内置码查询是否已经存在
	// *
	// * @param paseIdcardList
	// * @param orgInternalCode
	// * @return
	// */
	// List<UnsettledPopulationTemp>
	// findUnsettledPopulationTempsByIdCardNoAndOrgInternalCode(
	// List<String> paseIdcardList, String orgInternalCode);
	//
	// /**
	// * 修改被认领数据的inId(包括重复的数据)
	// *
	// * @param idCardNo
	// * @param id
	// * @param idList
	// * @param orgInternalCode
	// */
	// void
	// updateUnsettledPopulationTempInIdByIdCardNoAndIdListAndOrgInternalCode(
	// String idCardNo, Long id, List<Long> idList, String orgInternalCode);
	//
	// /**
	// * 认领数据
	// *
	// * @param id
	// * @param orgId
	// * @param orgInternalCode
	// * @param claim
	// * @param now
	// * @param id2
	// * @param name
	// * @param id3
	// * @return
	// */
	// UnsettledPopulationTemp updateUnsettledPopulationTempForClaimById(Long
	// id,
	// Long orgId, String orgInternalCode, Long claim, Date now, Long id2,
	// String name, Long id3);
	//
	// /**
	// * 根据inId获取户籍人口
	// *
	// * @param inId
	// * @return
	// */
	// List<UnsettledPopulationTemp> getUnsettledPopulationTempByInId(Long
	// inId);
	//
	// /**
	// * 撤销认领数据
	// *
	// * @param id
	// * @param orgId
	// * @param orgInternalCode
	// * @param unClaim
	// */
	// void updateUnsettledPopulationTempForUnClaimById(Long id, Long orgId,
	// String orgInternalCode, Long unClaim);
	//
	// /**
	// * 修改户籍人口
	// *
	// * @param population
	// * @return
	// */
	// UnsettledPopulationTemp updateUnsettledPopulationTemp(
	// UnsettledPopulationTemp population);

}