package com.tianque.newVillage.service;

import java.util.List;

import com.tianque.newVillage.domain.BasicInfoMation;
import com.tianque.newVillage.domain.NewVillageLeaderTeam;

public interface BasicInfomationService {

	/***
	 * 新增辖区基础信息
	 * 
	 * @param basicInfoMation
	 * @return
	 */
	public BasicInfoMation addBasicInfoMation(BasicInfoMation basicInfoMation);

	/***
	 * 修改辖区基础信息
	 * 
	 * @param basicInfoMation
	 * @return
	 */
	public BasicInfoMation updateBasicInfoMation(BasicInfoMation basicInfoMation);

	/**
	 * 通过组织机构ID查询辖区基础信息
	 * 
	 * @param orgId
	 * @return
	 */
	public BasicInfoMation getBasicInfoMationByOrgId(Long orgId);

	/***
	 * 通过ID查询辖区基础信息数据
	 * 
	 * @param id
	 * @return
	 */
	public BasicInfoMation getBasicInfoMationById(Long id);

	/***
	 * 修改基础信息图片
	 */
	public BasicInfoMation updateBasicInfomationImgUrl(
			BasicInfoMation basicInfoMation);

	/***
	 * 修改基础信息简介
	 */
	public BasicInfoMation updateBasicInfomationbasicIntroduction(
			BasicInfoMation basicInfoMation);

	/***
	 * 新增领导班子信息
	 */
	public NewVillageLeaderTeam addNewVillageLeaderTeam(
			NewVillageLeaderTeam newVillageLeaderTeam);

	/***
	 * 修改领导班子信息
	 */
	public NewVillageLeaderTeam updateNewVillageLeaderTeam(
			NewVillageLeaderTeam newVillageLeaderTeam);

	/***
	 * 删除领导班子信息
	 */
	public void deleteNewVillageLeaderTeamById(Long id);

	/***
	 * 修改领导班子图片信息
	 */
	public NewVillageLeaderTeam updateNewVillageLeaderTeamImgUrl(
			NewVillageLeaderTeam newVillageLeaderTeam);

	/***
	 * 根据ID查询领导班子信息
	 */
	public NewVillageLeaderTeam getNewVillageLeaderTeamById(Long id);

	/***
	 * 根据组织机构查询领导信息
	 */
	public List<NewVillageLeaderTeam> getNewVillageLeaderTeamByOrgId(Long orgId);

}
