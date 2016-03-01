package com.tianque.fourTeams.fourTeamsManage.dao;

import java.util.List;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.fourTeams.fourTeamsManage.domain.FourTeamsOrg;

@DynamicIbatisDAO(value = "FourTeamsOrgDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("FourTeamsOrgDAO")
public interface FourTeamsOrgDAO {

	/**
	 * 根据主键查找记录*
	 * 
	 * @return
	 */
	public FourTeamsOrg getFourTeamsOrgByPrimaryKey(Long id);

	/**
	 * 查找全部记录
	 * 
	 * @return
	 */
	public List<FourTeamsOrg> queryFourTeamsOrgForList(FourTeamsOrg fourTeamsOrg);

	/**
	 * 根据主键删除记录
	 * 
	 * @return
	 */
	public void deleteFourTeamsOrgByPrimaryKey(Long id);

	/**
	 * 新增记录
	 * 
	 * @return
	 */
	public Long addFourTeamsOrg(FourTeamsOrg fourTeamsOrg);

	/**
	 * 根据主键全修改记录
	 * 
	 * @return
	 */
	public void updateFourTeamsOrgByPrimaryKey(FourTeamsOrg fourTeamsOrg);

	/**
	 * 根据主键选择性修改记录
	 * 
	 * @return
	 */
	public void updateFourTeamsOrgByPrimaryKeySelective(
			FourTeamsOrg fourTeamsOrg);
}
