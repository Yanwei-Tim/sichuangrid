package com.tianque.baseInfo.emergencyShelter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.emergencyShelter.dao.EmergencyShelterDao;
import com.tianque.baseInfo.emergencyShelter.domain.EmergencyShelter;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("emergencyShelterService")
@Transactional
public class EmergencyShelterServiceImpl extends AbstractBaseService implements
		EmergencyShelterService {

	@Autowired
	@Qualifier("emergencyShelterValidator")
	private DomainValidator<EmergencyShelter> domainValidator;
	@Autowired
	private OrganizationDubboService orgnizationService;
	@Autowired
	private EmergencyShelterDao emergencyShelterDao;

	@Override
	public EmergencyShelter addEmergencyShelter(
			EmergencyShelter emergencyShelter) {
		emergencyShelter.setIsEmphasis(true);
		ValidateResult idleValidator = domainValidator
				.validate(emergencyShelter);
		if (idleValidator.hasError()) {
			throw new BusinessValidationException(
					idleValidator.getErrorMessages());
		} else if (hasDuplicateEmergencyShelter(emergencyShelter
				.getOrganization().getId(), emergencyShelter.getName(),
				emergencyShelter.getId())) {
			throw new BusinessValidationException("该网格下已存在相同单位");
		}
		emergencyShelter.setOrgInternalCode(orgnizationService
				.getSimpleOrgById(emergencyShelter.getOrganization().getId())
				.getOrgInternalCode());
		autoFillChinesePinyin(emergencyShelter);
		return emergencyShelterDao.addEmergencyShelter(emergencyShelter);
	}

	private void autoFillChinesePinyin(EmergencyShelter domain) {
		Map<String, String> pinyin = Chinese2pinyin.changeChinese2Pinyin(domain
				.getName());
		domain.setFullPinyin((String) pinyin.get("fullPinyin"));
		domain.setSimplePinyin((String) pinyin.get("simplePinyin"));
	}

	@Override
	public void deleteEmergencyShelter(List<Long> idList) {
		if (null == idList) {
			throw new BusinessValidationException("删除实有单位时idList不能为空");
		}
		String[] ids = StringUtil.parseString(idList);
		if(ids==null || ids.length==0){
			throw new BusinessValidationException("删除实有单位时Id不能为空");
		}
		try{
			emergencyShelterDao.deleteEmergencyShelterByIds(ids);
		}catch(Exception e){
			throw new ServiceValidationException("删除实有单位报错",e);
		}
		// for (Long id : idList) {
		// if (null == id || id < 0L) {
		// throw new ServiceException("删除实有单位时id不合法");
		// }
		// emergencyShelterDao.deleteEmergencyShelter(id);
		// }
	}

	@Override
	public PageInfo<EmergencyShelter> findEmergencyShelterForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sortField,
			String sord, Boolean logOut) {
		return emergencyShelterDao
				.findEmergencyShelterForPageByOrgInternalCode(
						orgnizationService.getSimpleOrgById(orgId)
								.getOrgInternalCode(), pageNum, pageSize,
						sortField, sord, logOut);
	}

	@Override
	public EmergencyShelter getEmergencyShelterById(Long id) {
		if (null == id || id < 0L) {
			throw new BusinessValidationException("删除实有单位时id不合法");
		}
		return emergencyShelterDao.getEmergencyShelterById(id);
	}

	@Override
	public boolean hasDuplicateEmergencyShelter(Long orgId, String companyName,
			Long exceptedId) {
		EmergencyShelter exsited = emergencyShelterDao
				.getEmergencyShelterByNameAndOrganizationId(companyName, orgId);
		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	@Override
	public EmergencyShelter updateEmergencyShelter(
			EmergencyShelter emergencyShelter) {
		if (emergencyShelter.getIsEmphasis() == null) {
			emergencyShelter.setIsEmphasis(emergencyShelterDao
					.getEmergencyShelterById(emergencyShelter.getId())
					.getIsEmphasis());
		}
		ValidateResult idleValidator = domainValidator
				.validate(emergencyShelter);
		if (idleValidator.hasError()) {
			throw new BusinessValidationException(
					idleValidator.getErrorMessages());
		} else if (hasDuplicateEmergencyShelter(emergencyShelter
				.getOrganization().getId(), emergencyShelter.getName(),
				emergencyShelter.getId())) {
			throw new BusinessValidationException("该网格下已存在相同单位");
		}
		emergencyShelter.setOrgInternalCode(orgnizationService
				.getSimpleOrgById(emergencyShelter.getOrganization().getId())
				.getOrgInternalCode());
		autoFillChinesePinyin(emergencyShelter);
		return emergencyShelterDao.updateEmergencyShelter(emergencyShelter);
	}

	@Override
	public List<EmergencyShelter> updateEmphasisByIdList(List<Long> idList,
			EmergencyShelter domain) {
		validatorIdList(idList);
		List<EmergencyShelter> emergencyShelterList = new ArrayList<EmergencyShelter>();
		for (Long id : idList) {
			EmergencyShelter emergencyShelter = emergencyShelterDao
					.getEmergencyShelterById(id);
			emergencyShelter.setIsEmphasis(domain.getIsEmphasis());
			emergencyShelter.setLogOutReason(domain.getLogOutReason());
			emergencyShelter.setLogOutTime(domain.getLogOutTime());
			emergencyShelter = updateEmergencyShelter(emergencyShelter);
			emergencyShelterList.add(emergencyShelter);
		}
		return emergencyShelterList;
	}

	public List<Long> validatorIdList(List<Long> idList) {
		if (null == idList) {
			throw new BusinessValidationException("应急避难场所idList不能为空");
		}
		for (Long id : idList) {
			if (null == id || id < 0L) {
				throw new BusinessValidationException("应急避难场所id不合法");
			}
		}
		return idList;
	}
}
