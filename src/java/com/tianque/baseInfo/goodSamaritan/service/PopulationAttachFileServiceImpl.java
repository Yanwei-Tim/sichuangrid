package com.tianque.baseInfo.goodSamaritan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.goodSamaritan.dao.PopulationAttachFileDAO;
import com.tianque.baseInfo.goodSamaritan.domain.PopulationAttachFile;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.exception.base.ServiceValidationException;

@Service("populationAttachFileService")
@Transactional
public class PopulationAttachFileServiceImpl extends AbstractBaseService
		implements PopulationAttachFileService {
	@Autowired
	private PopulationAttachFileDAO populationAttachFileDao;
	private List<PopulationAttachFile> populationAttachFiles;

	@Override
	public PopulationAttachFile addPopulationAttachFile(
			PopulationAttachFile populationAttachFile) {
		try {
			if (populationAttachFile != null) {
				populationAttachFileDao
						.addPopulationAttachFile(populationAttachFile);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PopulationAttachFileServiceImpl的addPopulationAttachFile方法出现异常，原因：",
					"新增人口附件信息出现错误", e);
		}
		return null;
	}

	@Override
	public void updatePopulationAttachFile(
			PopulationAttachFile populationAttachFile) {
		try {
			if (populationAttachFile != null) {
				populationAttachFileDao
						.updatePopulationAttachFile(populationAttachFile);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PopulationAttachFileServiceImpl的updatePopulationAttachFile方法出现异常，原因：",
					"修改人口附件信息出现错误", e);
		}

	}

	@Override
	public PopulationAttachFile getPopulationAttachFile(Long id) {
		PopulationAttachFile populationAttachFile = null;
		try {
			populationAttachFile = populationAttachFileDao
					.getPopulationAttachFile(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PopulationAttachFileServiceImpl的getPopulationAttachFile方法出现异常，原因：",
					"单条查询人口附件信息出现错误", e);
		}
		return populationAttachFile;
	}

	@Override
	public List<PopulationAttachFile> queryPopulationAttachFileByBusinessForList(
			Long businessId) {
		try {
			if (businessId != null) {
				populationAttachFiles = populationAttachFileDao
						.queryPopulationAttachFileByBusinessForList(businessId);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PopulationAttachFileServiceImpl的queryPopulationAttachFileByBusinessForList方法出现异常，原因：",
					"根据业务查询附件信息出现错误", e);
		}
		return populationAttachFiles;
	}

	@Override
	public void deletePopulationAttachFile(Long id) {
		try {
			if (id != null) {
				populationAttachFileDao.deletePopulationAttachFile(id);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PopulationAttachFileServiceImpl的deletePopulationAttachFile方法出现异常，原因：",
					"删除附件信息出现错误", e);
		}
	}

	@Override
	public void deletePopulationAttachFileForBusinessIdAndType(Long businessId,
			String type) {
		try {
			if (businessId != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("businessId", businessId);
				map.put("businessType", type);
				populationAttachFileDao
						.deletePopulationAttachFileForBusinessIdAndType(map);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PopulationAttachFileServiceImpl的deletePopulationAttachFileForBusinessId方法出现异常，原因：",
					"删除附件信息出现错误", e);
		}

	}

	public List<PopulationAttachFile> getPopulationAttachFiles() {
		return populationAttachFiles;
	}

	public void setPopulationAttachFiles(
			List<PopulationAttachFile> populationAttachFiles) {
		this.populationAttachFiles = populationAttachFiles;
	}
}
