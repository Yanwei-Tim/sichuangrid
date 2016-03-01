package com.tianque.upgradeContent.service;

import java.util.Map;

import com.tianque.upgradeContent.domain.UserCheckUpgradeContent;

public interface UserCheckUpgradeContentService {
	/**
	 * 根据用户编号查询 用户查看升级内容关联信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserCheckUpgradeContent getUserCheckUpgradeContentByUserIdAndUpgradeContentId(
			Long userId);

	/**
	 * 根据用户编号和升级内容编号查询 用户查看升级内容关联信息
	 * 
	 * @param map
	 * @return
	 */
	public UserCheckUpgradeContent getUserCheckUpgradeContentByUserIdAndUpgradeContentId(
			Map<String, Object> map);

	/**
	 * 添加
	 * 
	 * @param userCheckUpgradeContent
	 * @return
	 */
	public UserCheckUpgradeContent addUserCheckUpgradeContent(
			UserCheckUpgradeContent userCheckUpgradeContent);

	/**
	 * 修改
	 * 
	 * @param userCheckUpgradeContent
	 */
	public void updateUserCheckUpgradeContent(
			UserCheckUpgradeContent userCheckUpgradeContent);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void deleteUserCheckUpgradeContent(Long id);

	/**
	 * 根据升级内容ID 删除用户查看升级内容关联信息
	 * 
	 * @param upgradeContentId
	 */
	public void deleteUserCheckUpgradeContentByUpgradeContentId(
			Long upgradeContentId);

	/**
	 * 根据编号查询
	 * 
	 * @param id
	 * @return
	 */
	public UserCheckUpgradeContent getUserCheckUpgradeContentById(Long id);

}
