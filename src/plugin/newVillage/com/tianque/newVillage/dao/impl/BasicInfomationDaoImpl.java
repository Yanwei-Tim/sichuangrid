package com.tianque.newVillage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.dao.AbstractBaseDao;
import com.tianque.newVillage.dao.BasicInfomationDao;
import com.tianque.newVillage.domain.BasicInfoMation;
import com.tianque.newVillage.domain.NewVillageLeaderTeam;

@Repository("basicInfomationDao")
public class BasicInfomationDaoImpl extends AbstractBaseDao implements
		BasicInfomationDao {

	@Override
	public BasicInfoMation addBasicInfoMation(BasicInfoMation basicInfoMation) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"basicInfomation.addBasicInfomation", basicInfoMation);
		return getBasicInfoMationById(id);
	}

	@Override
	public BasicInfoMation updateBasicInfoMation(BasicInfoMation basicInfoMation) {
		getSqlMapClientTemplate().update(
				"basicInfomation.updateBasicInfomation", basicInfoMation);
		return getBasicInfoMationById(basicInfoMation.getId());
	}

	@Override
	public BasicInfoMation getBasicInfoMationByOrgId(Long orgId) {
		return (BasicInfoMation) getSqlMapClientTemplate().queryForObject(
				"basicInfomation.getBasicInfomationByOrgId", orgId);
	}

	@Override
	public BasicInfoMation getBasicInfoMationById(Long id) {
		return (BasicInfoMation) getSqlMapClientTemplate().queryForObject(
				"basicInfomation.getBasicInfomationById", id);
	}

	@Override
	public BasicInfoMation updateBasicInfomationImgUrl(
			BasicInfoMation basicInfoMation) {
		getSqlMapClientTemplate().update(
				"basicInfomation.updateBasicInfomationImgUrl", basicInfoMation);
		return getBasicInfoMationById(basicInfoMation.getId());
	}

	@Override
	public BasicInfoMation updateBasicInfomationbasicIntroduction(
			BasicInfoMation basicInfoMation) {
		getSqlMapClientTemplate().update(
				"basicInfomation.updateBasicInfomationbasicIntroduction",
				basicInfoMation);
		return getBasicInfoMationById(basicInfoMation.getId());
	}

	@Override
	public NewVillageLeaderTeam addNewVillageLeaderTeam(
			NewVillageLeaderTeam newVillageLeaderTeam) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"newVillageLeaderTeam.addNewvillageleaderteams",
				newVillageLeaderTeam);
		return getNewVillageLeaderTeamById(id);
	}

	@Override
	public NewVillageLeaderTeam updateNewVillageLeaderTeam(
			NewVillageLeaderTeam newVillageLeaderTeam) {
		getSqlMapClientTemplate().update(
				"newVillageLeaderTeam.updateNewvillageleaderteams",
				newVillageLeaderTeam);
		return getNewVillageLeaderTeamById(newVillageLeaderTeam.getId());
	}

	@Override
	public void deleteNewVillageLeaderTeamById(Long id) {
		getSqlMapClientTemplate().delete(
				"newVillageLeaderTeam.deleteNewVillageLeaderTeamById", id);

	}

	@Override
	public NewVillageLeaderTeam updateNewVillageLeaderTeamImgUrl(
			NewVillageLeaderTeam newVillageLeaderTeam) {
		getSqlMapClientTemplate().update(
				"newVillageLeaderTeam.updateNewvillageleaderteamsImgUrl",
				newVillageLeaderTeam);
		return getNewVillageLeaderTeamById(newVillageLeaderTeam.getId());
	}

	@Override
	public NewVillageLeaderTeam getNewVillageLeaderTeamById(Long id) {
		return (NewVillageLeaderTeam) getSqlMapClientTemplate().queryForObject(
				"newVillageLeaderTeam.getNewvillageleaderteamsById", id);
	}

	@Override
	public List<NewVillageLeaderTeam> getNewVillageLeaderTeamByOrgId(Long orgId) {
		return getSqlMapClientTemplate().queryForList(
				"newVillageLeaderTeam.getNewvillageleaderteamsOrgId", orgId);
	}

}
