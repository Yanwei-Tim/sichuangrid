package com.tianque.baseInfo.buildDatas.dao;

import java.util.List;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.buildDatas.domain.HouseBuilddatasBinding;
import com.tianque.core.vo.PageInfo;

/**
 * @描述:
 * @版权:
 * @公司:
 * @作者:王熙斌
 * @创建时间：2013-1-29 上午11:25:12
 **/
public interface HouseBuilddatasBindingDao {
	public PageInfo<HouseInfo> findHouseInfosByBuilddatasId(Long builddatasId, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	public PageInfo<HouseInfo> findUnBoundedByOrgInternalCode(String orgInternalCode, int page,
			int rows, String sidx, String sord, String queryStr);

	public void addBinding(HouseBuilddatasBinding houseBuilddatasBinding);

	public void removeBindingHouseInfo(List<Long> houseIds);
}
