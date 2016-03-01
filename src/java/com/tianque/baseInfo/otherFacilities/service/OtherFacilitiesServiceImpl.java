package com.tianque.baseInfo.otherFacilities.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.otherFacilities.dao.OtherFacilitiesDao;
import com.tianque.baseInfo.otherFacilities.domain.OtherFacilities;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("otherFacilitiesService")
@Transactional
public class OtherFacilitiesServiceImpl extends LogableService implements
		OtherFacilitiesService {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private OtherFacilitiesDao otherFacilitiesDao;

	@Qualifier("otherFacilitiesValidator")
	@Autowired
	private DomainValidator<OtherFacilities> domainValidator;

	/***
	 * 新增
	 */
	@Override
	public OtherFacilities addOtherFacilities(OtherFacilities otherFacilities) {
		otherFacilitiesValidate(otherFacilities);
		if (hasDuplicateOtherFacilitiesObjNum(otherFacilities.getOrganization()
				.getId(), otherFacilities.getObjNum(), otherFacilities.getId())) {
			throw new BusinessValidationException("该网格下已存在相同编号的部件");
		}
		try {

			autoFillOrganizationInternalCode(otherFacilities);
			otherFacilities = otherFacilitiesDao
					.addOtherFacilities(otherFacilities);
			return otherFacilities;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类OtherFacilitiesService的addOtherFacilities方法出现异常，原因：",
					"新增其他设施出现错误", e);
		}

	}

	/***
	 * 修改
	 */
	@Override
	public OtherFacilities updateOtherFacilities(OtherFacilities otherFacilities) {
		validateUpdate(otherFacilities);
		otherFacilitiesValidate(otherFacilities);
		if (hasDuplicateOtherFacilitiesObjNum(otherFacilities.getOrganization()
				.getId(), otherFacilities.getObjNum(), otherFacilities.getId())) {
			throw new BusinessValidationException("该网格下已存在相同编号的部件");
		}
		autoFillOrganizationInternalCode(otherFacilities);
		otherFacilities = otherFacilitiesDao
				.updateOtherFacilities(otherFacilities);
		return otherFacilities;
	}

	/***
	 * 列表
	 */
	@Override
	public PageInfo<OtherFacilities> findOtherFacilitiesForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		if (orgId == null) {
			return otherFacilitiesEmptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org == null) {
				return otherFacilitiesEmptyPageInfo();
			} else {
				return otherFacilitiesDao
						.findOtherFacilitiesForPageByOrgInternalCode(
								org.getOrgInternalCode(), pageNum, pageSize,
								sidx, sord);
			}
		}
	}

	/***
	 * 根据id查找
	 */
	@Override
	public OtherFacilities getOtherFacilitiesById(Long id) {
		if (null == id || id < 0L) {
			throw new BusinessValidationException("其他设施id不合法");
		}
		return otherFacilitiesDao.getOtherFacilitiesById(id);
	}

	/***
	 * 根据id数组删除
	 */
	@Override
	public void deleteOtherFacilitiesByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("id没有获得");
		}
		for (int i = 0; i < ids.length; i++) {
			deleteOtherFacilitiesById(ids[i]);
		}
	}

	/***
	 * 根据id删除
	 */
	private void deleteOtherFacilitiesById(Long id) {
		if (id == null || id < 0L) {
			throw new BusinessValidationException("id没有获得");
		}
		OtherFacilities otherFacilities = otherFacilitiesDao
				.getOtherFacilitiesById(id);
		log(WARN, DRUGGY, ThreadVariable.getSession().getUserName() + "删除其他设施"
				+ otherFacilities.getObjName(), OperatorType.DELETE,
				ObjectToJSON.convertJSON(otherFacilities));
		otherFacilitiesDao.deleteOtherFacilitiesById(id);
	}

	/**
	 * 数据校验
	 */
	private void otherFacilitiesValidate(OtherFacilities otherFacilities) {
		ValidateResult otherFacilitiesValidator = domainValidator
				.validate(otherFacilities);
		if (otherFacilitiesValidator.hasError()) {
			throw new BusinessValidationException(
					otherFacilitiesValidator.getErrorMessages());
		}
	}

	/**
	 * 唯一性校验
	 */
	@Override
	public boolean hasDuplicateOtherFacilitiesObjNum(Long orgId, String objNum,
			Long exceptedId) {
		OtherFacilities exsited = otherFacilitiesDao
				.getOtherFacilitiesByObjNum(objNum, orgId);

		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	/**
	 * 修改验证
	 */
	private void validateUpdate(OtherFacilities update) {
		OtherFacilities otherFacilities = this.otherFacilitiesDao
				.getOtherFacilitiesById(update.getId());
		if (null != update.getOrganization()
				&& null != update.getOrganization().getId()
				&& !update.getOrganization().getId()
						.equals(otherFacilities.getOrganization().getId())) {
			throw new BusinessValidationException("所属网格不能修改");
		}
		if (null != update.getCreateUser()
				&& !update.getCreateUser().equals(
						otherFacilities.getCreateUser())) {
			throw new BusinessValidationException("创建人不能修改");
		}
		if (null != update.getCreateDate()
				&& !update.getCreateDate().equals(
						otherFacilities.getCreateDate())) {
			throw new BusinessValidationException("创建时间不能修改");
		}
	}

	private PageInfo<OtherFacilities> otherFacilitiesEmptyPageInfo() {
		PageInfo<OtherFacilities> result = new PageInfo<OtherFacilities>();
		result.setResult(new ArrayList<OtherFacilities>());
		return result;
	}

	private void autoFillOrganizationInternalCode(
			OtherFacilities otherFacilities) {
		Organization org = organizationDubboService.getSimpleOrgById(otherFacilities
				.getOrganization().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		otherFacilities.setOrgInternalCode(org.getOrgInternalCode());
	}

	/**
	 * @Override public OtherFacilities updateOtherFacilitiesByObjNameAndOrgId(
	 *           String objName, Long orgId, OtherFacilities domain) { try {
	 *           OtherFacilities older = this.getOtherFacilitiesByObjName(
	 *           objName, orgId); domain.setId(older.getId());
	 *           domain.setCreateDate(older.getCreateDate());
	 *           domain.setCreateUser(older.getCreateUser()); return
	 *           updateOtherFacilities(domain); } catch (Exception e) { logger
	 *           .error(
	 *           "类OtherFacilitiesServiceImpl的updateOtherFacilitiesByObjNameAndOrgId方法出现异常，原因："
	 *           , e); throw new BusinessValidationException("修改公共设施出现错误"); } }
	 *           private OtherFacilities getOtherFacilitiesByObjName(String
	 *           objName, Long orgId) { return
	 *           otherFacilitiesDao.getOtherFacilitiesByObjName(objName, orgId);
	 *           }
	 */

	/*
	 * @Override public void updateEmphasiseByIds(Long[] ids, Boolean
	 * isEmphasis,String logOutReason) { for (int i = 0; i < ids.length; i++) {
	 * updateEmphasiseById(ids[i],isEmphasis,logOutReason); } } private void
	 * updateEmphasiseById(Long id, Boolean isEmphasis,String logOutReason) {
	 * otherFacilitiesDao.updateEmphasiseById(id,isEmphasis,logOutReason); }
	 */

	/*
	 * @Override public OtherFacilities existOtherFacilities(String objName,
	 * Long orgId) { if (objName == null || orgId == null) { throw new
	 * BusinessValidationException("参数错误"); } return
	 * getOtherFacilitiesByObjName(objName, orgId); }
	 */

}
