package com.tianque.dao;

import java.util.Map;

import org.oproject.framework.orm.PageResult;
import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.domain.AccountLoginDetails;

@DynamicIbatisDAO(value = "accountLoginDetailsDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("accountLoginDetailsDAO")
public interface AccountLoginDetailsDAO {

	/***
	 * 生成报表
	 * 
	 * @param map
	 */
	public void addAccountDetails(Map<String, Object> map);

	/***
	 * 修改拥有地方特色日历的工作天数
	 */
	public void updateHasFeatureDay(Map<String, Object> map);

	/***
	 * 列表查询
	 * 
	 * @param map
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public PageResult<AccountLoginDetails> queryAccountDetailsForPageResult(
			Map<String, Object> map, int page, int pageSize);

	/***
	 * 数据清空
	 * 
	 * @param map
	 */
	public void deleteAccountDetails(Map<String, Object> map);

}
