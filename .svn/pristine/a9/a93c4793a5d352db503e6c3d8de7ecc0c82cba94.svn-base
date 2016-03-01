package com.tianque.baseInfo.base.service;

import java.util.List;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.DuplicatePeople;

public interface BaseInfoService {
	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public Countrymen getBaseInfoById(Long id);

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	public Countrymen add(Countrymen countrymen);

	public Long save(Countrymen countrymen);

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	public Countrymen update(Countrymen countrymen);

	/**
	 * 根据ID删除实体对象
	 * 
	 * @param id
	 *            记录ID
	 */
	public void delete(Long id);

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	public void delete(Long[] ids);

	/**
	 * 同步基本信息的房屋信息
	 * 
	 * @param countrymen
	 */
	public void updateBaseInfoHouseState(Countrymen countrymen);

	/**
	 * 取消死亡
	 * 
	 * @param baseId
	 * @param death
	 */
	public void cancelDeath(Long baseId, Boolean death);

	/**
	 * 该方法中江三本台账有调用
	 * 
	 * 判断人员基本信息是否已经存在
	 * 
	 * @param idCardNo
	 * @return
	 */
	public Countrymen existBaseInfo(String idCardNo);

	public void updateDeathStateById(Long baseInfoId, Boolean death,
			String idCardNo, Long orgId, String orgCode, String operateScource);

	public List<Countrymen> getBaseInfosByIdcardno(String idcardno);

	public Countrymen getBaseInfoByIdCardNo(String idCardNo);

	public List<Countrymen> getBaseInfoByIdForList(List<Long> baseInfoIds);

	/**
	 * 修改根据身份证号修基本信息（身份证号、地址信息）
	 * 
	 * @param countrymen
	 * @param idCardNo
	 */
	public Countrymen updateBaseInfoIdCardNo(Countrymen countrymen,
			String idCardNo);

	/**
	 * 查询出基础表（baseInfo）重复数据类【返回的是重复的身份证号码所对应的重复数据】
	 * 
	 * @return
	 */
	public List<DuplicatePeople> getDuplicatePeopleInBaseInfo();

	/**
	 * 查询出baseInfo表所有idcardno重复的数据
	 * 
	 * @return
	 */
	public List<DuplicatePeople> getAllBaseInfoDuplicatePeople();

}
