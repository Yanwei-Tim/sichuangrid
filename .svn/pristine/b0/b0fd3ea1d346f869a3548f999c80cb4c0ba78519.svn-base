package com.tianque.baseInfo.superiorVisit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.superiorVisit.dao.SuperiorVisitHistoryDao;
import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisitHistory;
import com.tianque.baseInfo.superiorVisit.validator.SuperiorVisitHistoryValidatorImpl;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.vo.VisitHisTypeVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("superiorVisitHistoryService")
public class SuperiorVisitHistoryServiceImpl extends AbstractBaseService
		implements SuperiorVisitHistoryService {

	@Qualifier("SuperiorVisitHistoryValidator")
	@Autowired
	private SuperiorVisitHistoryValidatorImpl superiorVisitHistoryValidator;
	@Autowired
	private SuperiorVisitHistoryDao superiorVisitHistoryDao;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public SuperiorVisitHistory addSuperiorVisitHistory(
			SuperiorVisitHistory superiorVisitHistory, List<Long> visitTypes) {
		try {
			superiorVisitHistoryValidator(superiorVisitHistory);

			Long serviceVisitHistoryId = superiorVisitHistoryDao
					.addSuperiorVisitHistory(superiorVisitHistory);
			if (visitTypes != null && visitTypes.size() > 0) {
				addVisitHisTypes(visitTypes, serviceVisitHistoryId);
			}
			return superiorVisitHistoryDao
					.getSuperiorVisitHistoryById(serviceVisitHistoryId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类SuperiorVisitHistoryServiceImpl的addSuperiorVisitHistory方法出现异常，原因：",
					"新增上访历史记录出现错误", e);
		}
	}

	private void addVisitHisTypes(List<Long> visitTypes,
			Long superiorVisitHistoryId) {
		if (visitTypes != null) {
			for (int i = 0; i < visitTypes.size(); i++) {
				superiorVisitHistoryDao.addSuperiorVisitHisType(
						superiorVisitHistoryId, visitTypes.get(i));
			}
		}
	}

	private String getVisitType(Integer id) {
		if (id == null) {
			return "";
		}
		if (id == 1) {
			return "正常访";
		} else if (id == 0) {
			return "异常访";
		}
		return "";
	}

	@Override
	public PageInfo<SuperiorVisitHistory> findSuperiorVisitHistorys(
			SuperiorVisitHistory superiorVisitHistory, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		try {
			PageInfo<SuperiorVisitHistory> pageInfo = superiorVisitHistoryDao
					.findSuperiorVisitHistorys(superiorVisitHistory, pageNum,
							pageSize, sidx, sord);

			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类SuperiorVisitHistoryServiceImpl的findSuperiorVisitHistorys方法出现异常，原因：",
					"上访历史记录信息出现错误", e);
		}
	}

	@Override
	public SuperiorVisitHistory getSuperiorVisitHistoryById(Long id) {
		try {
			if (null == id) {
				throw new BusinessValidationException("传入参数为空");
			}
			SuperiorVisitHistory history = superiorVisitHistoryDao
					.getSuperiorVisitHistoryById(id);
			List<VisitHisTypeVo> list = superiorVisitHistoryDao
					.findVisitTypeById(id);
			if (list != null && list.size() > 0) {
				List<PropertyDict> pros = new ArrayList<PropertyDict>();
				String typeName = getVisitType(history.getVisitType()) + "：";
				for (int i = 0; i < list.size(); i++) {
					PropertyDict pro = propertyDictService
							.getPropertyDictById(list.get(i)
									.getSuperiorVisitType());
					pros.add(pro);
					typeName += pro.getDisplayName() + ",";
				}
				history.setVisitTypes(pros);
				history.setTypeName(typeName.substring(0, typeName.length() - 1));
			}
			return history;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类SuperiorVisitHistoryServiceImpl的getSuperiorVisitHistoryById方法出现异常，原因：",
					"根据ID获取上访历史记录出现错误", e);
		}
	}

	@Override
	public SuperiorVisitHistory updateSuperiorVisitHistory(
			SuperiorVisitHistory superiorVisitHistory, List<Long> visitTypes) {
		try {
			superiorVisitHistoryValidator(superiorVisitHistory);

			superiorVisitHistoryDao
					.updateSuperiorVisitHistory(superiorVisitHistory);

			superiorVisitHistoryDao
					.deleteSuperiorVisitHistoryTypes(superiorVisitHistory
							.getId());
			if (visitTypes != null && visitTypes.size() > 0) {
				addVisitHisTypes(visitTypes, superiorVisitHistory.getId());
			}

			return superiorVisitHistoryDao
					.getSuperiorVisitHistoryById(superiorVisitHistory.getId());
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类SuperiorVisitHistoryServiceImpl的updateSuperiorVisitHistory方法出现异常，原因：",
					"修改上访历史记录出现错误", e);
		}
	}

	private void deleteSuperiorVisitHistoryById(Long id) {
		if (id == null || id < 0L) {
			throw new BusinessValidationException("传入参数为空");
		}
		superiorVisitHistoryDao.deleteSuperiorVisitHistory(id);
	}

	@Override
	public void deleteSuperiorVisitHistoryByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			for (int i = 0; i < ids.length; i++) {
				deleteSuperiorVisitHistoryById(ids[i]);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类SuperiorVisitServiceImpl的deleteSuperiorVisitHistoryByIds方法出现异常，原因：",
					"删除上访历史记录信息出现错误", e);
		}
	}

	private void superiorVisitHistoryValidator(
			SuperiorVisitHistory SuperiorVisitHistory) {
		ValidateResult baseDataValidator = superiorVisitHistoryValidator
				.validate(SuperiorVisitHistory);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}
}
