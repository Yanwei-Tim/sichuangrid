package com.tianque.tenHouseholdsJoint.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.PageResult;
import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.domain.Organization;
import com.tianque.tenHouseholdsJoint.domain.FamilyTeam;

@DynamicIbatisDAO(value = "FamilyTeamDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("FamilyTeamDAO")
public interface FamilyTeamDAO {
	public PageResult<FamilyTeam> queryFamilyTeamForPageResult(
			Organization organization, int pageNum, int pageSize);

	public Long addFamilyTeam(FamilyTeam familyTeam);

	public FamilyTeam getFamilyTeamById(Long id);

	public int updateFamilyTeam(FamilyTeam familyTeam);

	public void batchDeleteFamilyTeamByIds(List<Long> idList);

	public PageResult<FamilyTeam> querySearchFamilyTeamForPageResult(
			FamilyTeam familyTeam, int pageNum, int pageSize);

	public List<FamilyTeam> queryFamilyTeamByOrgIdForList(String orgCode);

	public Integer getFamilyTeamByTeamCodeAndOrgid(FamilyTeam familyTeam);
	
	public FamilyTeam getFamilyTeamByName(Map map);

	public List<FamilyTeam> queryFamilyTeamsForList(FamilyTeam familyTeam); 

}