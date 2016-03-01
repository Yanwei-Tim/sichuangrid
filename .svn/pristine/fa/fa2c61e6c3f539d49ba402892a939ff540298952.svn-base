package com.tianque.sms.dao;

import java.util.List;

import com.tianque.core.base.BaseDao;
import com.tianque.sms.domain.Smstrafficmanage;
import com.tianque.sms.domain.vo.SearchSmstrafficmanageVo;

/**
 * 流量管理:数据操作层接口
 * 
 * @author
 * @date 2013-07-02 15:29:10
 */
public interface SmstrafficmanageDao extends
		BaseDao<Smstrafficmanage, SearchSmstrafficmanageVo> {

	public Smstrafficmanage getSmstrafficmanagesByOrgId(Long orgId);

	public List<Smstrafficmanage> findSmstrafficmanageByOrgId(String orgId);

	public Smstrafficmanage getNowSmstrafficmanagesByOrgId(Long orgId);

	public List<Smstrafficmanage> findOrgTraffic(Long orgId, Long sendStatus,
			Integer pageNum, Integer pageSize, String sortField, String order);

	public List<Smstrafficmanage> findParentOrgTraffic(String orgIds);
}
