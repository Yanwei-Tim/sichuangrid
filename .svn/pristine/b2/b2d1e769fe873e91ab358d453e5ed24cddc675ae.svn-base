package com.tianque.upgradeContent.service;

import java.util.List;

import org.oproject.framework.orm.PageResult;

import com.tianque.domain.DefaultSortAndPage;
import com.tianque.upgradeContent.domain.UpgradeContent;
import com.tianque.upgradeContent.domain.VO.UpgradeContentVO;

public interface UpgradeContentService {
	/**
	 * 列表查询
	 * 
	 * @param upgradeContentVO
	 * @param defaultSortAndPage
	 * @return
	 */
	public PageResult<UpgradeContent> queryUpgradeContenForPageResult(
			UpgradeContentVO upgradeContentVO,
			DefaultSortAndPage defaultSortAndPage);

	/**
	 * 添加
	 * 
	 * @param upgradeContent
	 * @return
	 */
	public UpgradeContent addUpgradeContent(UpgradeContent upgradeContent);

	/**
	 * 修改
	 * 
	 * @param upgradeContent
	 */
	public void updateUpgradeContent(UpgradeContent upgradeContent);

	/**
	 * 根据升级内容编号和用户进行修改
	 * 
	 * @param upgradeContent
	 * @param userId
	 */
	public void updateIsUpgradUpgradeContent(UpgradeContent upgradeContent,
			Long userId);

	/**
	 * 删除/批量删除
	 * 
	 * @param ids
	 */
	public void deleteUpgradeContent(List<Long> ids);

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 */
	public UpgradeContent getUpgradeContentById(Long id);

	/**
	 * 得到最新的升级内容
	 * 
	 * @return
	 */
	public UpgradeContent getTheLatestUpgradeContent();

}
