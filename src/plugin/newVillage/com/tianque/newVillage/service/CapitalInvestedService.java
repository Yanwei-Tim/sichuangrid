package com.tianque.newVillage.service;

import com.tianque.newVillage.domain.CapitalInvested;

/**
 * @ClassName: CapitalInvestedService
 * @Description: 资金投入信息
 */
public interface CapitalInvestedService {
	/**
	 * 增加 资金投入信息
	 * 
	 * @param capitalInvested
	 * @return CapitalInvested
	 */
	public CapitalInvested addCapitalInvested(CapitalInvested capitalInvested);

	/**
	 * 根据id获得 资金投入信息
	 * 
	 * @param id
	 * @return CapitalInvested
	 */
	public CapitalInvested getCapitalInvestedById(Long id);

	/**
	 * 根据id删除 资金投入信息
	 * 
	 * @param id
	 */
	public void deleteCapitalInvestedById(String[] id);

	/**
	 * 修改 资金投入信息
	 * 
	 * @param CapitalInvested
	 * @return CapitalInvested
	 */
	public CapitalInvested updateCapitalInvested(CapitalInvested capitalInvested);

	/***
	 * 根据baseId年份、季度查询数据
	 */
	public CapitalInvested getCapitalInvestedByBasicId(Long id);
}
