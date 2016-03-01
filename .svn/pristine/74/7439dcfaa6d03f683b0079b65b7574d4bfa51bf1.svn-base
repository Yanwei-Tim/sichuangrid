package com.tianque.upgradeContent.dao;

import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.upgradeContent.domain.UserCheckUpgradeContent;

@DynamicIbatisDAO(value = "UserCheckUpgradeContentDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("UserCheckUpgradeContentDAO")
public interface UserCheckUpgradeContentDAO {
	public Long addUserCheckUpgradeContent(
			UserCheckUpgradeContent userCheckUpgradeContent);

	public void updateUserCheckUpgradeContent(
			UserCheckUpgradeContent userCheckUpgradeContent);

	public void deleteUserCheckUpgradeContent(Long id);

	public void deleteUserCheckUpgradeContentByUpgradeContentId(
			Long upgradeContentId);

	public UserCheckUpgradeContent getUserCheckUpgradeContentById(Long id);

	public UserCheckUpgradeContent getUserCheckUpgradeContentByUpgradeContentId(
			Map<String, Object> map);

	public UserCheckUpgradeContent getUserCheckUpgradeContentByUserIdAndUpgradeContentId(
			Map<String, Object> map);
}
