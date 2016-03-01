package com.tianque.partyBuilding.organizations.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.partyBuilding.organizations.domain.ClaimRegionalBuildInfo;
import com.tianque.partyBuilding.organizations.domain.RegionalBuildInfo;
import com.tianque.partyBuilding.organizations.domain.RegionalBuildInfoAttachFile;
import com.tianque.partyBuilding.organizations.domain.vo.RegionalBuildInfoVo;

/**
 * 区域联建情况业务处理接口
 * */
public interface RegionalBuildInfoService {

	/**
	 * @param regionalBuildInfoVo
	 * @param
	 * @param 根据参数不同得到不同的区域联建情况数据
	 *            （用于列表，和查询时的列表显示）
	 * */
	public PageInfo<RegionalBuildInfo> findRegionalBuildInfoBySearchVo(
			RegionalBuildInfoVo regionalBuildInfoVo, Integer pageNum,
			Integer pageSize, String sortField, String sord);

	/**
	 * @param regionalBuildInfoId
	 *            根据区域联建id查询该区域联建的附件
	 */
	public List<RegionalBuildInfoAttachFile> findRegionalBuildInfoAttachFilesByRegionalBuildInfoId(
			Long regionalBuildInfoId);

	/**
	 * @param id
	 *            根据区域联建id查询该区域联建
	 */
	public RegionalBuildInfo getRegionalBuildInfoById(Long id);

	/**
	 * @param id
	 *            根据附件id查询附件
	 */
	public RegionalBuildInfoAttachFile getRegionalBuildInfoAttachFileById(
			Long id);

	/**
	 * @param id
	 *            根据附件id删除该附件
	 * */
	public void deleteRegionalBuildInfoAttachFileById(Long id);

	/** 新增区域联建 */
	public RegionalBuildInfo addRegionalBuildInfo(
			RegionalBuildInfo regionalBuildInfo);

	/**
	 * @param regionalBuildInfoid
	 * @param attachFiles
	 *            新增区域联建的附件
	 */
	public List<RegionalBuildInfoAttachFile> addAttachFileByRegionalBuildInfoId(
			Long regionalBuildInfoId, String[] attachFiles);

	/**
	 * @param regionalBuildInfo
	 * @param attachFiles
	 *            修改区域联建
	 */
	public RegionalBuildInfo updateRegionalBuildInfo(
			RegionalBuildInfo regionalBuildInfo, String[] attachFiles);

	/**
	 * @param analyzeDeleteIds
	 *            删除区域联建并且删除附件和认领记录
	 */
	public void deleteRegionalBuildInfoByIds(List<Long> analyzeDeleteIds);

	/**
	 * @param claimRegionalBuildInfo
	 *            新增认领记录
	 */
	public ClaimRegionalBuildInfo addClaimRegionalBuildInfo(
			ClaimRegionalBuildInfo claimRegionalBuildInfo);

	/**
	 * @param claimRegionalBuildInfo
	 *            修改认领记录
	 */
	public ClaimRegionalBuildInfo updateClaimRegionalBuildInfo(
			ClaimRegionalBuildInfo claimRegionalBuildInfo);

	/**
	 * @param regionalBuildInfoId
	 *            根据区域联建id查找所有的认领情况
	 */
	public PageInfo<ClaimRegionalBuildInfo> findClaimRegionalBuildInfoByRegionalBuildInfoId(
			Long regionalBuildInfoId, Integer pageNum, Integer pageSize,
			String sortField, String sord);

	/**
	 * @param regionalBuildInfoId
	 *            根据认领情况id删除认领情况
	 */
	public void deleteClaimRegionalBuildInfoById(Long id);

	/**
	 * @param regionalBuildInfoId
	 *            根据认领情况id查询认领情况
	 */
	public ClaimRegionalBuildInfo getClaimRegionalBuildInfoById(Long id);

	/**
	 * @param internalId
	 * @param orgInternalCode
	 *            为领导视图提供统计的 根据orgInternalCode的值和 internalId的值的不同得到乡镇的统计和社区的统计
	 */
	public Integer countRegionalBuildInfoByOrgIdOrOrgInternalCode(
			Long internalId, String orgInternalCode);
}
