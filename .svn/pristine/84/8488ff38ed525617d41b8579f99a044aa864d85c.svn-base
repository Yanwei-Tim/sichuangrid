package com.tianque.sms.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.sms.domain.Smstrash;
import com.tianque.sms.domain.vo.SearchSmstrashVo;

/**
 * 垃圾短信箱:业务逻辑层接口
 * 
 * @author
 * @date 2013-09-22 16:42:50
 */
public interface SmstrashService {
	public PageInfo<Smstrash> findSmstrashPagerBySearchVo(
			SearchSmstrashVo searchVo, Integer pageNum, Integer pageSize,
			String sortField, String order);

	public boolean restoreSMSById(String id);

	public Smstrash get(String id);

	public void delete(String id);

	public void delete(String[] ids);

}
