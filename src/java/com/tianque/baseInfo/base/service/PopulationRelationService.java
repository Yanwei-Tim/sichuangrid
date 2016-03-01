package com.tianque.baseInfo.base.service;

import java.util.List;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.domain.PopulationTypeBean;

public interface PopulationRelationService {

	/**
	 * 操作业务人员新增修改时实口信息的处理
	 * 
	 * @param countrymen
	 * @param actualPopulationType
	 */
	public Countrymen businessOption(Countrymen countrymen,
			String actualPopulationType);

	/**
	 * 绑定实口和业务人员关系
	 * 
	 * @param actualId
	 * @param actualType
	 * @param populationId
	 * @param populationType
	 */
	public void addPopulationRelation(Long actualId, String actualType,
			Long populationId, String populationType);

	/**
	 * 从业务人员删除实口和业务人员关系
	 * 
	 * @param populationId
	 * @param populationType
	 */
	public void businessDeletePopulationRelation(Long populationId,
			String populationType);

	/**
	 * 从实口删除实口和业务人员关系
	 * 
	 * @param actualId
	 * @param actualType
	 */
	public void actualDeletePopulationRelation(Long actualId, String actualType);

	/**
	 * 实口注销时所有业务人员全部注销
	 * 
	 * @param actualId
	 * @param actualType
	 * @param logoutDetail
	 */
	public void actualPopulationLogOut(Long actualId, String actualType,
			LogoutDetail logoutDetail);

	/**
	 * 实口删除时删除所有业务人员
	 * 
	 * @param actualId
	 * @param actualType
	 */
	public void actualPopulationDel(Long actualId, String actualType);

	public List<PopulationTypeBean> getActualPopulationTypeBeans(Long actualId,
			String actualType);

	public PopulationTypeBean getBusinessPopulationTypeBean(Long populationId,
			String populationType);
}
