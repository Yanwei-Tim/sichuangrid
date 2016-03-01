package com.tianque.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.ConstructionUnitDao;
import com.tianque.domain.ConstructionUnit;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.bridge.BaseInfoDeleter;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.validate.impl.ConstructionUnitValidateImpl;

@Service("constructionUnitService")
@Transactional
public class ConstructionUnitServiceImpl extends LogableService implements
		ConstructionUnitService {
	@Autowired
	private ConstructionUnitDao constructionUnitDao;
	@Autowired
	OrganizationDubboService organizationDubboService;
	@Autowired
	private BaseInfoDeleter baseInfoDeleter;

	private ConstructionUnitValidateImpl constructionUnitValidateImpl;
	@Autowired
	private ValidateHelper validateHelper;

	/*
	 * 添加
	 */
	@Override
	public ConstructionUnit addConstructionUnit(
			ConstructionUnit constructionUnit) {
		constructionUnitValidateImpl = new ConstructionUnitValidateImpl();
		constructionUnitValidateImpl.setValidateHelper(validateHelper);
		constructionUnitValidateImpl
				.setOrganizationDubboService(organizationDubboService);
		ValidateResult constructionUnitValidator = ((DomainValidator<ConstructionUnit>) constructionUnitValidateImpl)
				.validate(constructionUnit);
		if (constructionUnitValidator.hasError()) {
			throw new BusinessValidationException(
					constructionUnitValidator.getErrorMessages());

		} else if (hasDuplicateConstructionProjectName(constructionUnit
				.getOrganization().getId(), constructionUnit.getProjectName(),
				constructionUnit.getId())) {
			throw new BusinessValidationException("该网格下已存在相同名称的建筑施工单位");
		}

		setChinesePinyin(constructionUnit);
		autoFillOrganizationInternalCode(constructionUnit);
		constructionUnit = constructionUnitDao
				.addConstructionUnit(constructionUnit);
		// placeService.execute(DocumentType.CONSTRUCTION_UNIT,
		// Mode.EDIT.toString(), constructionUnit);
		return constructionUnit;

	}

	/*
	 * 更新
	 */
	@Override
	public ConstructionUnit updateConstructionUnit(
			ConstructionUnit constructionUnit) {
		constructionUnitValidateImpl = new ConstructionUnitValidateImpl();
		constructionUnitValidateImpl.setValidateHelper(validateHelper);
		constructionUnitValidateImpl
				.setOrganizationDubboService(organizationDubboService);
		ValidateResult constructionUnitValidator = ((DomainValidator<ConstructionUnit>) constructionUnitValidateImpl)
				.validate(constructionUnit);
		if (constructionUnitValidator.hasError()) {
			throw new BusinessValidationException(
					constructionUnitValidator.getErrorMessages());

		} else if (hasDuplicateConstructionProjectName(constructionUnit
				.getOrganization().getId(), constructionUnit.getProjectName(),
				constructionUnit.getId())) {
			throw new BusinessValidationException("该网格下已存在相同名称的建筑施工单位");
		}
		setChinesePinyin(constructionUnit);
		autoFillOrganizationInternalCode(constructionUnit);
		constructionUnit = constructionUnitDao
				.updateConstructionUnit(constructionUnit);
		// placeService.execute(DocumentType.CONSTRUCTION_UNIT,
		// Mode.EDIT.toString(), constructionUnit);
		return constructionUnit;
	}

	private void setChinesePinyin(ConstructionUnit constructionUnit) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(constructionUnit.getProjectName());
		constructionUnit.setFullPinyin((String) pinyin.get("fullPinyin"));
		constructionUnit.setSimplePinyin((String) pinyin.get("simplePinyin"));
	}

	private void autoFillOrganizationInternalCode(
			ConstructionUnit constructionUnit) {

		Organization organization = organizationDubboService
				.getSimpleOrgById(constructionUnit.getOrganization().getId());
		if (organization == null) {
			throw new BusinessValidationException("找不到指定的网格");
		} else {
			constructionUnit.setOrgInternalCode(organization
					.getOrgInternalCode());
		}
	}

	@Override
	public ConstructionUnit getConstructionUnitById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("id没有获得");
		}
		return constructionUnitDao.getConstructionUnitById(id);
	}

	@Override
	public boolean deleteConstructionUnitById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("id没有获得");
		}
		ConstructionUnit domain = getConstructionUnitById(id);

		log(WARN, CONSTRUCTION_UNIT, ThreadVariable.getSession().getUserName()
				+ "删除建筑施工单位" + domain.getProjectName(), OperatorType.DELETE,
				ObjectToJSON.convertJSON(domain));
		constructionUnitDao.deleteConstructionUnitById(id);
		// placeService.execute(DocumentType.CONSTRUCTION_UNIT,
		// Mode.DELETE.toString(), domain);
		return true;
	}

	@Override
	public ConstructionUnit updateConstructionUnitById(
			ConstructionUnit constructionUnit) {

		return constructionUnitDao.updateConstructionUnitById(constructionUnit);
	}

	@Override
	public ConstructionUnit updateConstructionUnitByName(String name, Long id,
			ConstructionUnit domain) {
		ConstructionUnit older = constructionUnitDao
				.getConstructionUnitByProjectName(name, id);
		domain.setId(older.getId());
		domain.setCreateDate(older.getCreateDate());
		domain.setCreateUser(older.getCreateUser());
		return updateConstructionUnit(domain);
	}

	@Override
	public PageInfo<ConstructionUnit> findConstructionUnitForPage(Long orgId,
			Integer pageSize, Integer pageNum, String sidx, String sord,
			Long isEmphasis) {
		if (null == orgId) {
			return constructEmptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (null == org) {
				return constructEmptyPageInfo();
			} else {
				return constructionUnitDao
						.findConstructionUnitForPageByOrgInternalCode(
								org.getOrgInternalCode(), pageSize, pageNum,
								sidx, sord, isEmphasis);
			}
		}

	}

	private PageInfo<ConstructionUnit> constructEmptyPageInfo() {
		PageInfo<ConstructionUnit> result = new PageInfo<ConstructionUnit>();
		result.setResult(new ArrayList<ConstructionUnit>());
		return result;
	}

	@Override
	public boolean hasDuplicateConstructionProjectName(Long ownerOrgId,
			String constructionProjectName, Long exceptedId) {
		ConstructionUnit exsited = constructionUnitDao
				.getConstructionUnitByProjectName(constructionProjectName,
						ownerOrgId);
		boolean rs = exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
		return rs;
	}

	@Override
	public List<Long> deleteConstructionUnitById(List<Long> placeIds) {
		if (placeIds == null) {
			throw new BusinessValidationException("id没有获得");
		}
		List<Long> exitPersonIdsList = baseInfoDeleter.getRelateplaceId(
				placeIds, "CONSTRUCTION_UNIT");
		placeIds.removeAll(exitPersonIdsList);
		for (Long id : exitPersonIdsList) {
			ConstructionUnit domain = constructionUnitDao
					.getConstructionUnitById(id);
			log(WARN, CONSTRUCTION_UNIT, ThreadVariable.getSession()
					.getUserName() + "删除建筑施工单位" + domain.getProjectName(),
					OperatorType.DELETE, ObjectToJSON.convertJSON(domain));
		}
		for (Long id : placeIds) {
			deleteConstructionUnitById(id);
		}
		return placeIds;
	}

	@Override
	public boolean hasRelatePlacce(List<Long> placeIds) {
		if (placeIds == null) {
			throw new BusinessValidationException("id没有获得");
		}
		List<Long> exitPersonIdsList = baseInfoDeleter.getRelateplaceId(
				placeIds, "CONSTRUCTION_UNIT");
		if (exitPersonIdsList != null && exitPersonIdsList.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void shiftConstructionUnit(Long[] ids, Long orgId) {

		for (int i = 0; i < ids.length; i++) {
			if (ids[i] == null) {
				continue;
			}
			ConstructionUnit constructionUnit = getConstructionUnitById(ids[i]);
			constructionUnit.getOrganization().setId(orgId);
			constructionUnit.setOrgInternalCode(organizationDubboService
					.getSimpleOrgById(orgId).getOrgInternalCode());
			boolean bol = hasDuplicateConstructionProjectName(orgId,
					constructionUnit.getProjectName(), constructionUnit.getId());
			if (bol) {
				updateConstructionUnitByName(constructionUnit.getProjectName(),
						constructionUnit.getOrganization().getId(),
						constructionUnit);
				deleteConstructionUnitById(ids[i]);

			} else {
				updateConstructionUnit(constructionUnit);
			}
		}

	}

}
