package com.tianque.incident.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.incident.dao.IncidentSystemManagerDao;
import com.tianque.incident.domain.IncidentType;
import com.tianque.incident.domain.component.IncidentDegreeRule;
import com.tianque.incident.service.IncidentSystemManagerService;
import com.tianque.incident.validate.AbstractIncidentValidator;
import com.tianque.service.impl.LogableService;

@Service("incidentSystemManagerService")
@Transactional
public class IncidentSystemManagerServiceImpl extends LogableService implements
		IncidentSystemManagerService {
	@Autowired
	private IncidentSystemManagerDao incidentSystemManagerDao;

	@Qualifier("incidentTypeValidator")
	@Autowired
	private AbstractIncidentValidator<IncidentType> incidentTypeValidator;

	// 根据事件类型获取其所有子节点
	@Override
	public List<IncidentType> getIncidentsBysubjectionTypeId(
			Long subjectionTypeId) {
		if (subjectionTypeId == null || subjectionTypeId < 0L) {
			throw new BusinessValidationException("获取事件类型Id异常！");
		} else {
			return incidentSystemManagerDao
					.getIncidentsBysubjectionTypeId(subjectionTypeId);
		}
	}

	/**
	 * 根据id获取预警信息
	 */
	@Override
	public IncidentType getIncidentTypeById(Long id) {
		if (id == null || id <= 0L) {
			throw new BusinessValidationException("预警信息Id获取异常!");
		} else {
			return incidentSystemManagerDao.getIncidentTypeById(id);
		}
	}

	@Override
	public IncidentType addIncidentType(IncidentType incidentType) {
		IncidentTypeValidator(incidentType);
		try {
			return incidentSystemManagerDao.addIncidentType(incidentType);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类 IncidentSystemManagerServiceImpl 中 addIncidentType方法异常：",
					"类 IncidentSystemManagerServiceImpl 中 addIncidentType方法异常：",
					e);
		}

	}

	@Override
	public IncidentDegreeRule addIncidentDegreeRule(
			IncidentDegreeRule incidentDegreeRule) {
		IncidentDegreeRuleValidator(incidentDegreeRule);
		try {
			return incidentSystemManagerDao
					.addIncidentDegreeRule(incidentDegreeRule);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类 IncidentSystemManagerServiceImpl 中 addIncidentDegreeRule方法异常",
					"类 IncidentSystemManagerServiceImpl 中 addIncidentDegreeRule方法异常：",
					e);
		}

	}

	@Override
	public IncidentType updateIncidentType(IncidentType incidentType) {
		IncidentTypeValidator(incidentType);
		try {
			return incidentSystemManagerDao.updateIncidentType(incidentType);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类 IncidentSystemManagerServiceImpl 中 updateIncidentType方法异常",
					"类 IncidentSystemManagerServiceImpl 中 updateIncidentType方法异常：",
					e);
		}

	}

	@Override
	public IncidentDegreeRule updateIncidentDegreeRule(
			IncidentDegreeRule incidentDegreeRule) {
		IncidentDegreeRuleValidator(incidentDegreeRule);
		try {
			return incidentSystemManagerDao
					.addIncidentDegreeRule(incidentDegreeRule);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类 IncidentSystemManagerServiceImpl 中 updateIncidentDegreeRule方法异常",
					"类 IncidentSystemManagerServiceImpl 中 updateIncidentDegreeRule方法异常：",
					e);
		}
	}

	@Override
	public void deleteIncidentType(Long subjectTypeId) {
		try {
			if (subjectTypeId == null || subjectTypeId < 0L) {
				throw new BusinessValidationException("删除事件类型时，获取id失败！");
			} else {
				incidentSystemManagerDao.deleteIncidentTypeById(subjectTypeId);
				incidentSystemManagerDao
						.deleteIncidentDegreeRuleByIncidentTypeId(subjectTypeId);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类 IncidentSystemManagerServiceImpl 中 deleteIncidentType方法异常",
					"类 IncidentSystemManagerServiceImpl 中 deleteIncidentType方法异常：",
					e);
		}
	}

	private void IncidentTypeValidator(IncidentType incidentType) {
		ValidateResult IncidentTypeValidate = incidentTypeValidator
				.IncidentValidate(incidentType);
		if (IncidentTypeValidate.hasError()) {
			throw new BusinessValidationException(
					IncidentTypeValidate.getErrorMessages());
		} else if (hasDuplicateIncidentType(incidentType.getSubjection()
				.getId(), incidentType.getName(), incidentType.getId())) {
			throw new BusinessValidationException("该分类下已经存相同的名称");
		}
	}

	private void IncidentDegreeRuleValidator(
			IncidentDegreeRule incidentDegreeRule) {
		ValidateResult IncidentDegreeRuleValidate = incidentTypeValidator
				.IncidentTypeDegreeValidate(incidentDegreeRule);
		if (IncidentDegreeRuleValidate.hasError()) {
			throw new BusinessValidationException(
					IncidentDegreeRuleValidate.getErrorMessages());
		}
	}

	@Override
	public boolean hasDuplicateIncidentType(Long subjectTypeId, String name,
			Long exceptedId) {
		IncidentType exsitedIncidentType = incidentSystemManagerDao
				.getIncidentTypeByNameAndSubjectTypeId(subjectTypeId, name);
		return exceptedId == null ? exsitedIncidentType != null
				: (exsitedIncidentType != null && !exceptedId
						.equals(exsitedIncidentType.getId()));
	}

}
