package com.tianque.newVillage.service;

import java.util.Date;

import com.tianque.core.vo.PageInfo;
import com.tianque.newVillage.domain.NewVillageBasic;

public interface NewVillageBasicService {
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

	/***
	 * 数据上报
	 */
	public void reportNewVillageBasicInfo(Long basicId, Integer isPlaning);

	/**
	 * 发现同一年的是否有已上报的且包含基本信息且未被退回的信息
	 * 
	 * @return 有则返回true,没有则返回false
	 */
	public boolean findSameYearNewVillageBasics(Long orgId, Integer year);

}
