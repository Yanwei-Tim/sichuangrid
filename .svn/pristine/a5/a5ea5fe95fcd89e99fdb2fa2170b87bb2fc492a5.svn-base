package com.tianque.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.EstateInformation;

public interface EstateInformationDao {
	/**
	 * 根据ID获取房产信息
	 * 
	 * @param id
	 *        房产信息ID
	 * @return
	 *         房产信息对象
	 */
	EstateInformation getEstateInformationById(Long id);

	/**
	 * 添加房产信息
	 * 
	 * @param bewerbung
	 *        房产信息对象
	 * @return
	 *         房产信息对象
	 */
	EstateInformation addEstateInformation(EstateInformation estateInformation);

	/**
	 * 根据查询条件分页查询房产信息
	 */
	PageInfo<EstateInformation> findEstateInformationForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize, String sidx, String sord);

	/**
	 * 更新房产信息
	 * 
	 * @param estateInformation
	 * @return
	 */
	EstateInformation updateEstateInformation(EstateInformation estateInformation);

	/**
	 * 根据id删除房产信息
	 * 
	 * @param id
	 */
	void deleteEstateInformationById(Long id);

	/**
	 * 取出房产证号的数量
	 * 
	 * @param estateInformation
	 * @return
	 */
	int getExistedProprietaryNoCount(EstateInformation estateInformation);

	/**
	 * 取出土地证号的数量
	 * 
	 * @param estateInformation
	 * @return
	 */
	int getExistedLandPermitCount(EstateInformation estateInformation);

}
