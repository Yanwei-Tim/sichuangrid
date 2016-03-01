package com.tianque.dao.mobileUpdate;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.MobileUpdate;
import com.tianque.domain.vo.MobileUpdateSearchVo;

public interface MobileUpdateDao {
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
	public MobileUpdate updateMobileUpdate(MobileUpdate mobileUpdate);

	/**
	 * 删除移动终端更新信息
	 * 
	 * @param ids
	 * @return
	 */
	public void deleteMobileUpdate(Long id);

	/**
	 * 分页查询
	 * 
	 * @param searchVo
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @return
	 */
	public PageInfo<MobileUpdate> searchMobileUpdate(MobileUpdateSearchVo searchVo,
			Integer pageNum, Integer pageSize, String sortField, String order);

	/**
	 * 通过ID查询MobileUpdate对象
	 * 
	 * @param id
	 * @return
	 */
	public MobileUpdate getMobileUpdateById(Long id);

	/**
	 * 根据categoryId查询记录的条数
	 * 
	 * @param categoryId
	 * @return
	 */
	public Integer getMobileUpdateCountByCategoryId(Long categoryId);
	
	/**
	 * 
	* @Title: getMobileUpdateCountByDepartmentNo 
	* @Description: TODO根据部门编号来统计数据
	* @param @param categoryId
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @author wanggz
	* @date 2014-7-21 下午02:29:34
	 */
	public Integer getMobileUpdateCountByDepartmentNo(String departmentNo);

	/**
	 * 根据categoryId查询MobileUpdate对象
	 * 
	 * @param categoryId
	 * @return
	 */
	public MobileUpdate getMobileUpdateByCategoryId(Long categoryId);

}
