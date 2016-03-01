package com.tianque.baseInfo.primaryOrg.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.primaryOrg.dao.FourLevelPlatformDao;
import com.tianque.baseInfo.primaryOrg.domain.FourLevelPlatform;
import com.tianque.baseInfo.primaryOrg.domain.vo.SearchFourLevelPlatformVo;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMembersOrgType;
import com.tianque.baseInfo.primaryOrg.primaryMembers.service.PrimaryMembersOrgTypeService;
import com.tianque.baseInfo.primaryOrg.service.FourLevelPlatformService;
import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;

/**
 * 四级平台表:业务逻辑层
 * 
 * @author
 * @date 2014-03-10 09:38:17
 */
@Transactional
@Service("fourLevelPlatformService")
public class FourLevelPlatformServiceImpl extends
		BaseServiceImpl<FourLevelPlatform, SearchFourLevelPlatformVo> implements
		FourLevelPlatformService {

	@Autowired
	private FourLevelPlatformDao fourLevelPlatformDao;
	@Autowired
	private PrimaryMembersOrgTypeService primaryMembersOrgTypeService;

	@Autowired
	@Qualifier("fourLevelPlatformValidator")
	private DomainValidator<FourLevelPlatform> domainValidator;

	@Resource(name = "fourLevelPlatformDao")
	public void setBaseDao(FourLevelPlatformDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public FourLevelPlatform add(FourLevelPlatform fourlevelplatform) {
		fourlevelplatformValidator(fourlevelplatform);
		try {
			fourlevelplatform = getBaseDao().add(fourlevelplatform);
			return fourlevelplatform;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类FourlevelplatformServiceImpl的add方法出现异常，原因：",
					"新增四级平台表信息出现错误", e);
		}
	}

	@Override
	public FourLevelPlatform update(FourLevelPlatform fourlevelplatform) {
		fourlevelplatformValidator(fourlevelplatform);
		try {
			fourlevelplatform = getBaseDao().update(fourlevelplatform);
			return fourlevelplatform;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类FourlevelplatformServiceImpl的update方法出现异常，原因：",
					"更新四级平台表信息出现错误", e);
		}
	}

	private void fourlevelplatformValidator(FourLevelPlatform fourlevelplatform) {
		ValidateResult baseDataValidator = domainValidator
				.validate(fourlevelplatform);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public Integer getCount(SearchFourLevelPlatformVo searchFourLevelPlatformVo) {
		return fourLevelPlatformDao.getCount(searchFourLevelPlatformVo);
	}

	@Override
	public Integer countFourLevelPlatformByOrgId(Long orgId) {
		try {
			return fourLevelPlatformDao.countFourLevelPlatformByOrgId(orgId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类FourlevelplatformServiceImpl的统计方法出现异常，原因：",
					"统计四级平台表信息出现错误", e);
		}
	}

	@Override
	public int deleteFourLevelPlatformByIds(String ids, Long isFourLevelPlatform) {
		if (ids == null || ids.equals("")) {
			throw new BusinessValidationException("无法定位需要操作的数据！！");
		}
		List<Long> deleteIds = getDeleteIds(ids);
		int count = -1;
		if (isDeleteAble(deleteIds, isFourLevelPlatform)) {
			count = 0;
			for (Long deleteId : deleteIds) {
				if (null != get(deleteId)) {
					int deleteCount = fourLevelPlatformDao
							.deleteFourLevelPlatform(deleteId);
					count += deleteCount;
				}
			}
		}
		return count;
	}

	/**
	 * 获得要进行删除操作的id组
	 * 
	 * @return
	 */
	private List<Long> getDeleteIds(String selectedIds) {
		String[] deleteIdStrs = selectedIds.split(",");
		List<Long> deleteIds = new ArrayList<Long>();
		for (String deleteIdStr : deleteIdStrs) {
			Long deleteId = Long.parseLong(deleteIdStr);
			deleteIds.add(deleteId);
		}
		return deleteIds;
	}

	/**
	 * 是否可以删除
	 * 
	 * @param deleteIds
	 * @return true 可以 false 不可以
	 */
	private boolean isDeleteAble(List<Long> deleteIds, Long isFourLevelPlatform) {
		PrimaryMembersOrgType primaryMembersOrgType = new PrimaryMembersOrgType();
		for (Long id : deleteIds) {
			primaryMembersOrgType.setPrimaryOrgId(id);
			primaryMembersOrgType.setIsFourLevelPlatform(isFourLevelPlatform);
			if (primaryMembersOrgTypeService
					.countFindPrimaryOrgMembers(primaryMembersOrgType) > 0) {
				return false;
			}
		}
		return true;
	}
}
