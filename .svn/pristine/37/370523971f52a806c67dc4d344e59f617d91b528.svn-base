package com.tianque.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.EstateInformation;

/**
 * 房产信息业务操作接口。
 */
public interface EstateInformationService {
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
	PageInfo<EstateInformation> findEstateInformationsForPageByOrgInternalCode(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/**
	 * 更新房产信息
	 * 
	 * @param estateInformation
	 * @return
	 */
	EstateInformation updateEstateInInformation(EstateInformation estateInformation);

	/**
	 * 根据id删除房产信息
	 * 
	 * @param id
	 */
	void deleteEstateInformationById(Long id);

	/**
	 * 判断房产证号是否唯一，取出此房产证号的数量
	 * 
	 * @param estateInformation
	 * @return
	 */
	int getExistedProprietaryNoCount(EstateInformation estateInformation);

	/**
	 * 判断土地证号是否唯一，取出此土地证号的数量
	 * 
	 * @param estateInformation
	 * @return
	 */
	int getExistedLandPermitCount(EstateInformation estateInformation);

}
