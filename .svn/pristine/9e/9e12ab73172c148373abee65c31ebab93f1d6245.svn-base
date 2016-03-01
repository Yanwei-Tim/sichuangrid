package com.tianque.sms.service;

import java.util.List;

import com.tianque.core.base.BaseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.sms.domain.Smstrafficmanage;
import com.tianque.sms.domain.vo.SearchSmstrafficmanageVo;

/**
 * 流量管理:业务逻辑层接口
 * 
 * @author
 * @date 2013-07-02 15:29:10
 */
public interface SmstrafficmanageService extends
		BaseService<Smstrafficmanage, SearchSmstrafficmanageVo> {

	public PageInfo<Smstrafficmanage> findSmstrafficmanageByOrgId(Long orgId,
			Integer pageNum, Integer pageSize, String sortField, String order);

	public Smstrafficmanage getSmstrafficmanagesByOrgId(Long orgId);

	public List<Smstrafficmanage> findSmstrafficmanageByOrgId(Long orgId);

	public Smstrafficmanage getNowSmstrafficmanagesByOrgId(Long orgId);

	public PageInfo<Smstrafficmanage> findOrgTraffic(Long orgId,
			Long sendStatus, Integer pageNum, Integer pageSize,
			String sortField, String order);

	public PageInfo<Smstrafficmanage> findParentOrgTraffic(Long orgId,
			Long sendStatus, Integer pageNum, Integer pageSize,
			String sortField, String order);

	public Smstrafficmanage updateSmstrafficmanageByOrgId(
			Smstrafficmanage smstrafficmanage, Long orgId);

}
