package com.tianque.plugin.orgchange.dao;

import java.util.List;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月26日
 */
@DynamicIbatisDAO(value = "CommonHandlerDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("CommonHandlerDAO")
public interface CommonHandlerDAO {

	public Integer getCount(String sql);

	public int updateData(String sql);

	public int deleteData(String sql);

	public List<String> queryStringForList(String sql);
}
