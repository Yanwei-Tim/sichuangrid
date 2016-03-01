package com.tianque.baseInfo.base.dao;

import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.DuplicatePeople;

public interface BaseInfoDao {
	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public Countrymen get(Long id);

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
	 * 判断人员基本信息是否已经存在
	 * 
	 * @param idCardNo
	 * @param orgId
	 * @return
	 */
	public Countrymen existBaseInfo(String idCardNo);

	public void updateDeathStateById(Map<String, Object> map);

	public List<Countrymen> getBaseInfosByIdcardno(String idcardno);

	public List<Countrymen> getAddressInfoByIdForList(List<Long> baseInfoIds);

	public Countrymen getBaseInfoByIdCardNo(String idCardNo);

	/**
	 * 根据身份证号修改基础表的身份证、地址等信息
	 * 
	 * @param countrymen
	 * @param idCardNo
	 * @return
	 */
	public Countrymen updateBaseInfoIdCardNo(Countrymen countrymen,
			String idCardNo);

	/**
	 * 查询出一条身份证重复的数据
	 * 
	 * @return
	 */
	public DuplicatePeople getUniquenessDuplicatePeople();

	/**
	 * 根据身份证号码获取重复的数据
	 * 
	 * @param idCardNo
	 * @return
	 */
	public List<DuplicatePeople> getDuplicatePeoplesByIdcardno(String idCardNo);

	/***
	 * 获取baseinfo所有的idcardno重复的数据
	 * 
	 * @return
	 */
	public List<DuplicatePeople> getAllBaseInfoDuplicatePeople();
}
