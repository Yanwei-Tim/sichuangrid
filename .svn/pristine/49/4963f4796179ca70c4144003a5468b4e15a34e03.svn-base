package com.tianque.tenHouseholdsJoint.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.PageResult;
import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.tenHouseholdsJoint.domain.FamilyInfo;

@DynamicIbatisDAO(value = "FamilyInfoDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("FamilyInfoDAO")
public interface FamilyInfoDAO {
	public PageResult<FamilyInfo> queryFamilyInfoForPageResult(
			Map<String, Object> map, int pageNum, int pageSize);

	public Long addFamilyInfo(FamilyInfo familyInfo);

	public int updateFamilyInfo(FamilyInfo familyInfo);

	public void deleteFamilyInfo(String[] ids);

	public FamilyInfo getFamilyInfoById(Long id);

	public PageResult<FamilyInfo> queryFamilyByConditionForPageResult(Map map,
			int pageNum, int pageSize);

	public void updateLogoutFamilyById(Map map);

	public List<FamilyInfo> queryFamilyInfoByHelpLineForList(
			FamilyInfo familyInfo);

	public FamilyInfo getFamilyInfoByHelpLine(String helpLine);

	public List<FamilyInfo> queryFamilyInfoByTeamIdForList(Long teamId);

	public List<FamilyInfo> queryFamilyInfosForList(FamilyInfo familyInfo);

}
