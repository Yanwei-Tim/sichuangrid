package com.tianque.baseInfo.base.service;

import java.util.List;

import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.core.vo.PageInfo;

public interface BaseInfoPopulationTemplateService {
	List<Long> updateLogOutDetailByPopulationTypeAndIds(
			LogoutDetail logoutDetail, String populationType, Long[] ids);

	/**
	 * 人口业务模块，关注和取消关注的操作，并修改该模块的count计数器
	 * 
	 * @param logoutDetail
	 * @param populationType
	 * @param ids
	 * @return
	 */
	List<Long> updateLogOutDetailAndCountByPopulationTypeAndIds(Long orgId,
			LogoutDetail logoutDetail, String populationType, Long[] ids);

	/**
	 * 业务模块，查询服务成员的条数，用于列表显示：“有无服务成员”
	 * 
	 * @param populationType
	 * @return
	 */
	public <T extends Countrymen> PageInfo<T> fitServiceMemberHasObject(
			String populationType, PageInfo<T> peopleInfoPageInfos);

	/**
	 * 新增添加缓存验证
	 * 
	 * @param <T>
	 * @param countrymen
	 * @param cacheKey
	 * @param cacheValue
	 * @return
	 */
	public <T extends AttentionPopulation> boolean checkDataExitInCache(
			T attentionPopulation, String cacheKey, String cacheValue);

	/**
	 * 清除缓存
	 * 
	 * @param <T>
	 * @param countrymen
	 * @param cacheKey
	 */
	public <T extends AttentionPopulation> void cleanDataInCache(
			T attentionPopulation, String cacheKey);

}
