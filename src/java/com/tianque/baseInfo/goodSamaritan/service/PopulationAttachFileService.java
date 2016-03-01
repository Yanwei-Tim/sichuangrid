package com.tianque.baseInfo.goodSamaritan.service;

import java.util.List;

import com.tianque.baseInfo.goodSamaritan.domain.PopulationAttachFile;

public interface PopulationAttachFileService {
	public PopulationAttachFile addPopulationAttachFile(
			PopulationAttachFile populationAttachFile);

	public void updatePopulationAttachFile(
			PopulationAttachFile populationAttachFile);

	public PopulationAttachFile getPopulationAttachFile(Long id);

	public void deletePopulationAttachFile(Long id);

	public void deletePopulationAttachFileForBusinessIdAndType(Long businessId,
			String type);

	public List<PopulationAttachFile> queryPopulationAttachFileByBusinessForList(
			Long businessId);
}
