package com.tianque.service.mobileUpdate;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.MobileUpdate;
import com.tianque.domain.vo.MobileUpdateSearchVo;

public interface MobileUpdateService {
	/**
	 * 增加移动终端更新信息
	 * 
	 * @param mobileUpdate
	 * @return
	 */
	public MobileUpdate addMobileUpdate(MobileUpdate mobileUpdate);

	/**
	 * 更新移动终端更新信息
	 * 
	 * @param mobileUpdate
	 * @return
	 */
	public MobileUpdate updateMobileUpdate(MobileUpdate mobileUpdate,
			String oldOrgDepartmentNo);

	/**
	 * 删除移动终端更新信息
	 * 
	 * @param ids
	 * @return
	 */
	public void deleteMobileUpdate(Long id);

	/**
	 * 分页查询移动终端信息
	 * 
	 * @param searchVo
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @return
	 */
	public PageInfo<MobileUpdate> findMobileUpdateBySearchVo(
			MobileUpdateSearchVo searchVo, Integer pageNum, Integer pageSize,
			String sortField, String order);

	/**
	 * 通过ID查询MobileUpdate对象
	 * 
	 * @param id
	 * @return
	 */
	public MobileUpdate getMobileUpdateById(Long id);

	/**
	 * 通过categoryId查询MobileUpdate对象
	 * 
	 * @param categoryId
	 * @return
	 */
	public MobileUpdate getMobileUpdateByCategoryId(Long categoryId);

	/**
	 * 通过categoryId查询MobileUpdate对象
	 * 
	 * @param categoryName
	 * @return
	 */
	public MobileUpdate getMobileUpdateByCategoryName(String categoryName);

}
