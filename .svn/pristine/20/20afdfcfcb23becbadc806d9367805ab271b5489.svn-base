package com.tianque.newVillage.dao;

import java.util.Date;

import com.tianque.core.vo.PageInfo;
import com.tianque.newVillage.domain.NewVillageBasic;

public interface NewVillageBasicDao {

	/***
	 * 新增
	 */
	public NewVillageBasic addNewVillageBasic(NewVillageBasic newVillageBasic);

	/***
	 * 修改
	 */
	public NewVillageBasic updateNewVillageBasic(NewVillageBasic newVillageBasic);

	/***
	 * 上报
	 */
	public void reportNewVillageBasic(Long id, Integer reportStatus,
			Date reportDate, String reportUser);

	/***
	 * 审核
	 */
	public void checkNewVillageBasic(Long id, Integer checkStatus,
			Date checkDate, String checkUser);

	/***
	 * 列表查询
	 */
	public PageInfo<NewVillageBasic> findNewVillageBasicForList(
			NewVillageBasic newVillageBasic, Integer page, Integer rows,
			String sidx, String sord);

	/***
	 * 通过ID查询
	 */
	public NewVillageBasic getNewVillageBasicById(Long id);

	/***
	 * 批量删除
	 */
	public void deleteNewVillageBasicByIds(String ids[]);

	/***
	 * 判断组织机构下同年同季度是否存在数据
	 */
	public boolean getNewVillageBasicByYear(Long orgId, Integer year,
			Integer isPlaning, Integer quarter);

	/**
	 * 通过组织机构orgId和年份year,来查是否有 该年且该机构 已提交的且包含基本信息basicYearInfo
	 * 的待审核或审核通过的信息
	 * 
	 * @param newVillageBasic
	 * @return
	 */
	public boolean findSameYearNewVillageBasics(Long orgId, Integer year);
}
