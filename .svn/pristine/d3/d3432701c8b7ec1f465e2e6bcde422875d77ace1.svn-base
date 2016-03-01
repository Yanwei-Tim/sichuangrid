package com.tianque.upgradeContent.dao;

import org.oproject.framework.orm.PageResult;
import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.upgradeContent.domain.UpgradeContent;
import com.tianque.upgradeContent.domain.VO.UpgradeContentVO;

@DynamicIbatisDAO(value = "UpgradeContentDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("UpgradeContentDAO")
public interface UpgradeContentDAO {
	public PageResult<UpgradeContent> queryUpgradeContenForPageResult(
			UpgradeContentVO upgradeContentVO, int pageNum, int pageSize);

	public Long addUpgradeContent(UpgradeContent upgradeContent);

	public void updateUpgradeContent(UpgradeContent upgradeContent);

	public void deleteUpgradeContent(Long id);

	public UpgradeContent getUpgradeContentById(Long id);

	public UpgradeContent getTheLatestUpgradeContent(Long id);
}
