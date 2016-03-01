package com.tianque.baseInfo.landscaping.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.landscaping.dao.LandscapingDao;
import com.tianque.baseInfo.landscaping.domain.Landscaping;
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

@Service("landscapingService")
@Transactional
public class LandscapingServiceImpl extends LogableService implements
		LandscapingService {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private LandscapingDao landscapingDao;

	@Qualifier("landscapingValidator")
	@Autowired
	private DomainValidator<Landscaping> domainValidator;

	/***
	 * 新增
	 */
	@Override
	public Landscaping addLandscaping(Landscaping landscaping) {
		landscapingValidate(landscaping);
		if (hasDuplicateLandscapingObjNum(
				landscaping.getOrganization().getId(), landscaping.getObjNum(),
				landscaping.getId())) {
			throw new BusinessValidationException("该网格下已存在相同编号的部件");
		}
		try {

			autoFillOrganizationInternalCode(landscaping);
			landscaping = landscapingDao.addLandscaping(landscaping);
			return landscaping;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类LandscapingService的addLandscaping方法出现异常，原因：", "园林绿化出现错误",
					e);
		}

	}

	/***
	 * 修改
	 */
	@Override
	public Landscaping updateLandscaping(Landscaping landscaping) {
		validateUpdate(landscaping);
		landscapingValidate(landscaping);
		if (hasDuplicateLandscapingObjNum(
				landscaping.getOrganization().getId(), landscaping.getObjNum(),
				landscaping.getId())) {
			throw new BusinessValidationException("该网格下已存在相同编号的部件");
		}
		autoFillOrganizationInternalCode(landscaping);
		landscaping = landscapingDao.updateLandscaping(landscaping);
		return landscaping;
	}

	/***
	 * 列表
	 */
	@Override
	public PageInfo<Landscaping> findLandscapingForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		if (orgId == null) {
			return landscapingEmptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org == null) {
				return landscapingEmptyPageInfo();
			} else {
				return landscapingDao
						.findLandscapingForPageByOrgInternalCode(
								org.getOrgInternalCode(), pageNum, pageSize,
								sidx, sord);
			}
		}
	}

	/***
	 * 根据id查找
	 */
	@Override
	public Landscaping getLandscapingById(Long id) {
		if (null == id || id < 0L) {
			throw new BusinessValidationException("园林绿化id不合法");
		}
		return landscapingDao.getLandscapingById(id);
	}

	/***
	 * 根据id数组删除
	 */
	@Override
	public void deleteLandscapingByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("id没有获得");
		}
		for (int i = 0; i < ids.length; i++) {
			deleteLandscapingById(ids[i]);
		}
	}

	/***
	 * 根据id删除
	 */
	private void deleteLandscapingById(Long id) {
		if (id == null || id < 0L) {
			throw new BusinessValidationException("id没有获得");
		}
		Landscaping landscaping = landscapingDao.getLandscapingById(id);
		log(WARN, DRUGGY, ThreadVariable.getSession().getUserName() + "删除园林绿化"
				+ landscaping.getObjName(), OperatorType.DELETE,
				ObjectToJSON.convertJSON(landscaping));
		landscapingDao.deleteLandscapingById(id);
	}

	/**
	 * 数据校验
	 */
	private void landscapingValidate(Landscaping landscaping) {
		ValidateResult landscapingValidator = domainValidator
				.validate(landscaping);
		if (landscapingValidator.hasError()) {
			throw new BusinessValidationException(
					landscapingValidator.getErrorMessages());
		}
	}

	/**
	 * 唯一性校验
	 */
	@Override
	public boolean hasDuplicateLandscapingObjNum(Long orgId, String objNum,
			Long exceptedId) {
		Landscaping exsited = landscapingDao.getLandscapingByObjNum(objNum,
				orgId);

		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	/**
	 * 修改验证
	 */
	private void validateUpdate(Landscaping update) {
		Landscaping landscaping = this.landscapingDao.getLandscapingById(update
				.getId());
		if (null != update.getOrganization()
				&& null != update.getOrganization().getId()
				&& !update.getOrganization().getId()
						.equals(landscaping.getOrganization().getId())) {
			throw new BusinessValidationException("所属网格不能修改");
		}
		if (null != update.getCreateUser()
				&& !update.getCreateUser().equals(landscaping.getCreateUser())) {
			throw new BusinessValidationException("创建人不能修改");
		}
		if (null != update.getCreateDate()
				&& !update.getCreateDate().equals(landscaping.getCreateDate())) {
			throw new BusinessValidationException("创建时间不能修改");
		}
	}

	private PageInfo<Landscaping> landscapingEmptyPageInfo() {
		PageInfo<Landscaping> result = new PageInfo<Landscaping>();
		result.setResult(new ArrayList<Landscaping>());
		return result;
	}

	private void autoFillOrganizationInternalCode(Landscaping landscaping) {
		Organization org = organizationDubboService.getSimpleOrgById(landscaping
				.getOrganization().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		landscaping.setOrgInternalCode(org.getOrgInternalCode());
	}

	/**
	 * @Override public Landscaping updateLandscapingByObjNameAndOrgId( String
	 *           objName, Long orgId, Landscaping domain) { try { Landscaping
	 *           older = this.getLandscapingByObjName( objName, orgId);
	 *           domain.setId(older.getId());
	 *           domain.setCreateDate(older.getCreateDate());
	 *           domain.setCreateUser(older.getCreateUser()); return
	 *           updateLandscaping(domain); } catch (Exception e) { logger
	 *           .error(
	 *           "类LandscapingServiceImpl的updateLandscapingByObjNameAndOrgId方法出现异常，原因："
	 *           , e); throw new BusinessValidationException("修改公共设施出现错误"); } }
	 *           private Landscaping getLandscapingByObjName(String objName,
	 *           Long orgId) { return
	 *           landscapingDao.getLandscapingByObjName(objName, orgId); }
	 */

	/*
	 * @Override public void updateEmphasiseByIds(Long[] ids, Boolean
	 * isEmphasis,String logOutReason) { for (int i = 0; i < ids.length; i++) {
	 * updateEmphasiseById(ids[i],isEmphasis,logOutReason); } } private void
	 * updateEmphasiseById(Long id, Boolean isEmphasis,String logOutReason) {
	 * landscapingDao.updateEmphasiseById(id,isEmphasis,logOutReason); }
	 */

	/*
	 * @Override public Landscaping existLandscaping(String objName, Long orgId)
	 * { if (objName == null || orgId == null) { throw new
	 * BusinessValidationException("参数错误"); } return
	 * getLandscapingByObjName(objName, orgId); }
	 */

}
