package com.tianque.partyBuilding.organizations.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.partyBuilding.organizations.domain.ClaimRegionalBuildInfo;
import com.tianque.partyBuilding.organizations.domain.RegionalBuildInfo;
import com.tianque.partyBuilding.organizations.domain.RegionalBuildInfoAttachFile;
import com.tianque.partyBuilding.organizations.domain.vo.RegionalBuildInfoVo;

/**
 * 区域联建情况DAO接口
 * */
public interface RegionalBuildInfoDao {

	/**
	 * @param regionalBuildInfoId
	 *            根据区域联建id查询该区域联建的附件
	 */
	public List<RegionalBuildInfoAttachFile> findRegionalBuildInfoAttachFilesByRegionalBuildInfoId(
			Long regionalBuildInfoId);

	/**
	 * @param regionalBuildInfoVo
	 * @param
	 * @param 根据参数不同得到不同的区域联建情况数据
	 *            （用于列表，和查询时的列表显示）
	 * */
	public PageInfo<RegionalBuildInfo> findRegionalBuildInfoPagerBySearchVo(
			RegionalBuildInfoVo regionalBuildInfoVo, Integer pageNum,
			Integer pageSize, String sortField, String sord);

	/** 根据id查询该区域联建 */
	public RegionalBuildInfo getRegionalBuildInfoById(Long id);

	/** 根据id查询该区域联建 的附件 */
	public RegionalBuildInfoAttachFile getRegionalBuildInfoAttachFilesById(
			Long id);

	/** 根据id删除附件 */
	public void deleteRegionalBuildInfoAttachFilesById(Long id);

	/**
	 * @param regionalBuildInfoAttachFile
	 *            新增附件
	 */
	public void addRegionalBuildInfoAttachFile(
			RegionalBuildInfoAttachFile regionalBuildInfoAttachFile);

	/**
	 * @param regionalBuildInfo
	 *            新增区域联建情况
	 */
	public RegionalBuildInfo addRegionalBuildInfo(
			RegionalBuildInfo regionalBuildInfo);

	/**
	 * @param regionalBuildInfo
	 *            修改区域联建情况
	 */
	public RegionalBuildInfo updateRegionalBuildInfo(
			RegionalBuildInfo regionalBuildInfo);

	/**
	 * @param id
	 *            根据id删除区域联建情况
	 * */
	public void deleteRegionalBuildInfoById(Long id);

	/**
	 * @param id
	 *            根据区域联建情况id删除该区域联建情况附件
	 * */
	public void deleteRegionalBuildInfoAttachFileByRegionalBuildInfoId(
			Long regionalBuildInfoId);

	/**
	 * @param id
	 *            根据区域联建情况id删除该区域联建情况认领信息
	 * */
	public void deleteClaimRegionalBuildInfoByRegionalBuildInfoId(
			Long regionalBuildInfoId);

	/**
	 * @param id
	 *            新增区域联建情况认领信息
	 * */
	public ClaimRegionalBuildInfo addClaimRegionalBuildInfo(
			ClaimRegionalBuildInfo claimRegionalBuildInfo);

	/**
	 * @param id
	 *            根据id查询区域联建情况认领信息
	 * */
	public ClaimRegionalBuildInfo getClaimRegionalBuildInfo(Long id);

	/**
	 * @param id
	 *            修改区域联建情况认领信息
	 * */
	public ClaimRegionalBuildInfo updateClaimRegionalBuildInfo(
			ClaimRegionalBuildInfo claimRegionalBuildInfo);

	public PageInfo<ClaimRegionalBuildInfo> findClaimRegionalBuildInfoByRegionalBuildInfoId(
			Long regionalBuildInfoId, Integer pageNum, Integer pageSize,
			String sortField, String sord);

	/**
	 * @param id
	 *            删除认领信息
	 */
	public void deleteClaimRegionalBuildInfoById(Long id);

	/**
	 * @param id
	 *            根据id查询认领信息
	 */
	public ClaimRegionalBuildInfo getClaimRegionalBuildInfoById(Long id);

	/**
	 * @param id
	 *            根据regionalBuildInfoId查询是否有认领信息
	 */
	public Boolean regionalBuildInfoIsClaim(Long regionalBuildInfoId);

	/**
	 * @param internalId
	 * @param orgInternalCode
	 *            为领导视图提供统计的 根据orgInternalCode的值和 internalId的值的不同得到乡镇的统计和社区的统计
	 */
	public Integer countRegionalBuildInfoByOrgIdOrOrgInternalCode(
			List<Long> orgIdsList);

}
