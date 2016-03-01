package com.tianque.baseInfo.goodSamaritan.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.goodSamaritan.domain.PopulationAttachFile;

@DynamicIbatisDAO(value = "PopulationAttachFileDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("PopulationAttachFileDAO")
public interface PopulationAttachFileDAO {
	public Long addPopulationAttachFile(
			PopulationAttachFile populationAttachFile);

	public PopulationAttachFile getPopulationAttachFile(Long id);

	public void updatePopulationAttachFile(
			PopulationAttachFile populationAttachFile);

	public void deletePopulationAttachFile(Long id);

	public void deletePopulationAttachFileForBusinessIdAndType(Map map);

	public void batchDeletePopulationAttachFile(List<Long> ids);

	public List<PopulationAttachFile> queryPopulationAttachFileByBusinessForList(
			Long businessId);
}
