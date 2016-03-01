package com.tianque.plugin.analyzing.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.plugin.analyzing.domain.LoginManage;

@DynamicIbatisDAO(value = "LoginManageDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("LoginManageDAO")
public interface LoginManageDAO {
	/**
	 * 新增登录情况的数据
	 * 
	 * @param companyPlace
	 * @return
	 */
	public Long addLoginManage(Map<String, Object> map);

	/**
	 * 统计用户的登录情况-list 查询直属下辖（区域）
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public List<LoginManage> queryLoginManageForList(Map<String, Object> map);

	/**
	 * 统计用户的登录情况-list 查询各层级
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public List<LoginManage> queryLoginManageByOrgIdForList(
			Map<String, Object> map);

	/**
	 * 统计用户的登录情况-count
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public Integer getCount(Map<String, Object> map);

	/**
	 * 根据年月删除
	 * 
	 * @param map
	 */
	public void deleteLoginMange(Map<String, Object> map);

}
