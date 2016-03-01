package com.tianque.baseInfo.roadTraffic.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.roadTraffic.dao.RoadTrafficDao;
import com.tianque.baseInfo.roadTraffic.domain.RoadTraffic;
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

@Service("roadTrafficService")
@Transactional
public class RoadTrafficServiceImpl extends LogableService implements
		RoadTrafficService {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private RoadTrafficDao roadTrafficDao;

	@Qualifier("roadTrafficValidator")
	@Autowired
	private DomainValidator<RoadTraffic> domainValidator;

	/***
	 * 新增
	 */
	@Override
	public RoadTraffic addRoadTraffic(RoadTraffic roadTraffic) {
		roadTrafficValidate(roadTraffic);
		if (hasDuplicateRoadTrafficObjNum(
				roadTraffic.getOrganization().getId(), roadTraffic.getObjNum(),
				roadTraffic.getId())) {
			throw new BusinessValidationException("该网格下已存在相同编号的部件");
		}
		try {

			autoFillOrganizationInternalCode(roadTraffic);
			roadTraffic = roadTrafficDao.addRoadTraffic(roadTraffic);
			return roadTraffic;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类RoadTrafficService的addRoadTraffic方法出现异常，原因：",
					"新增公共设施出现错误", e);
		}

	}

	/***
	 * 修改
	 */
	@Override
	public RoadTraffic updateRoadTraffic(RoadTraffic roadTraffic) {
		validateUpdate(roadTraffic);
		roadTrafficValidate(roadTraffic);
		if (hasDuplicateRoadTrafficObjNum(
				roadTraffic.getOrganization().getId(), roadTraffic.getObjNum(),
				roadTraffic.getId())) {
			throw new BusinessValidationException("该网格下已存在相同编号的部件");
		}
		autoFillOrganizationInternalCode(roadTraffic);
		roadTraffic = roadTrafficDao.updateRoadTraffic(roadTraffic);
		return roadTraffic;
	}

	/***
	 * 列表
	 */
	@Override
	public PageInfo<RoadTraffic> findRoadTrafficForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		if (orgId == null) {
			return roadTrafficEmptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org == null) {
				return roadTrafficEmptyPageInfo();
			} else {
				return roadTrafficDao
						.findRoadTrafficForPageByOrgInternalCode(
								org.getOrgInternalCode(), pageNum, pageSize,
								sidx, sord);
			}
		}
	}

	/***
	 * 根据id查找
	 */
	@Override
	public RoadTraffic getRoadTrafficById(Long id) {
		if (null == id || id < 0L) {
			throw new BusinessValidationException("公共设施id不合法");
		}
		return roadTrafficDao.getRoadTrafficById(id);
	}

	/***
	 * 根据id数组删除
	 */
	@Override
	public void deleteRoadTrafficByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("id没有获得");
		}
		for (int i = 0; i < ids.length; i++) {
			deleteRoadTrafficById(ids[i]);
		}
	}

	/***
	 * 根据id删除
	 */
	private void deleteRoadTrafficById(Long id) {
		if (id == null || id < 0L) {
			throw new BusinessValidationException("id没有获得");
		}
		RoadTraffic roadTraffic = roadTrafficDao.getRoadTrafficById(id);
		log(WARN, DRUGGY, ThreadVariable.getSession().getUserName() + "删除公共设施"
				+ roadTraffic.getObjName(), OperatorType.DELETE,
				ObjectToJSON.convertJSON(roadTraffic));
		roadTrafficDao.deleteRoadTrafficById(id);
	}

	/**
	 * 数据校验
	 */
	private void roadTrafficValidate(RoadTraffic roadTraffic) {
		ValidateResult roadTrafficValidator = domainValidator
				.validate(roadTraffic);
		if (roadTrafficValidator.hasError()) {
			throw new BusinessValidationException(
					roadTrafficValidator.getErrorMessages());
		}
	}

	/**
	 * 唯一性校验
	 */
	@Override
	public boolean hasDuplicateRoadTrafficObjNum(Long orgId, String objNum,
			Long exceptedId) {
		RoadTraffic exsited = roadTrafficDao.getRoadTrafficByObjNum(objNum,
				orgId);

		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	/**
	 * 修改验证
	 */
	private void validateUpdate(RoadTraffic update) {
		RoadTraffic roadTraffic = this.roadTrafficDao.getRoadTrafficById(update
				.getId());
		if (null != update.getOrganization()
				&& null != update.getOrganization().getId()
				&& !update.getOrganization().getId()
						.equals(roadTraffic.getOrganization().getId())) {
			throw new BusinessValidationException("所属网格不能修改");
		}
		if (null != update.getCreateUser()
				&& !update.getCreateUser().equals(roadTraffic.getCreateUser())) {
			throw new BusinessValidationException("创建人不能修改");
		}
		if (null != update.getCreateDate()
				&& !update.getCreateDate().equals(roadTraffic.getCreateDate())) {
			throw new BusinessValidationException("创建时间不能修改");
		}
	}

	private PageInfo<RoadTraffic> roadTrafficEmptyPageInfo() {
		PageInfo<RoadTraffic> result = new PageInfo<RoadTraffic>();
		result.setResult(new ArrayList<RoadTraffic>());
		return result;
	}

	private void autoFillOrganizationInternalCode(RoadTraffic roadTraffic) {
		Organization org = organizationDubboService.getSimpleOrgById(roadTraffic
				.getOrganization().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		roadTraffic.setOrgInternalCode(org.getOrgInternalCode());
	}

	/**
	 * @Override public RoadTraffic updateRoadTrafficByObjNameAndOrgId( String
	 *           objName, Long orgId, RoadTraffic domain) { try { RoadTraffic
	 *           older = this.getRoadTrafficByObjName( objName, orgId);
	 *           domain.setId(older.getId());
	 *           domain.setCreateDate(older.getCreateDate());
	 *           domain.setCreateUser(older.getCreateUser()); return
	 *           updateRoadTraffic(domain); } catch (Exception e) { logger
	 *           .error(
	 *           "类RoadTrafficServiceImpl的updateRoadTrafficByObjNameAndOrgId方法出现异常，原因："
	 *           , e); throw new BusinessValidationException("修改公共设施出现错误"); } }
	 *           private RoadTraffic getRoadTrafficByObjName(String objName,
	 *           Long orgId) { return
	 *           roadTrafficDao.getRoadTrafficByObjName(objName, orgId); }
	 */

	/*
	 * @Override public void updateEmphasiseByIds(Long[] ids, Boolean
	 * isEmphasis,String logOutReason) { for (int i = 0; i < ids.length; i++) {
	 * updateEmphasiseById(ids[i],isEmphasis,logOutReason); } } private void
	 * updateEmphasiseById(Long id, Boolean isEmphasis,String logOutReason) {
	 * roadTrafficDao.updateEmphasiseById(id,isEmphasis,logOutReason); }
	 */

	/*
	 * @Override public RoadTraffic existRoadTraffic(String objName, Long orgId)
	 * { if (objName == null || orgId == null) { throw new
	 * BusinessValidationException("参数错误"); } return
	 * getRoadTrafficByObjName(objName, orgId); }
	 */

}
