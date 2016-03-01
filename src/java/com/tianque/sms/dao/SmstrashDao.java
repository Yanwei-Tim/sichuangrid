package com.tianque.sms.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.sms.domain.Smstrash;
import com.tianque.sms.domain.vo.SearchSmstrashVo;

/**
 * 垃圾短信箱:数据操作层接口
 * 
 * @author
 * @date 2013-09-22 16:42:50
 */
public interface SmstrashDao {

	public PageInfo<Smstrash> findSmstrashsBySearchVo(
			SearchSmstrashVo searchVo, Integer pageNum, Integer pageSize,
			String sortField, String order);

	void delete(Long id);

	void delete(Long[] ids);

	public Smstrash restoreSMS(Long id);
}
