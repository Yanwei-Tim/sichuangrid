package com.tianque.baseInfo.primaryOrg.primaryMembers.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.primaryOrg.primaryMembers.dao.PrimaryMembersOrgTypeDao;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMembers;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMembersOrgType;
import com.tianque.baseInfo.primaryOrg.primaryMembers.service.PrimaryMembersOrgTypeService;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;

@Service("primaryMembersOrgTypeService")
@Transactional
public class PrimaryMembersOrgTypeServiceImpl extends AbstractBaseService
		implements PrimaryMembersOrgTypeService {
	@Autowired
	private PrimaryMembersOrgTypeDao primaryMembersOrgTypeDao;

	/**
	 * 根据成员和组织查询对应的关联信息
	 */
	public PrimaryMembersOrgType findPrimaryMembersOrgTypeByMemberAndPrimaryOrg(
			Long memberId, Long primaryOrgId) {
		try {
			if (memberId == null) {
				throw new BusinessValidationException("没有选中成员信息!");
			}
			return primaryMembersOrgTypeDao
					.findPrimaryMembersOrgTypeByMemberAndPrimaryOrg(memberId,
							primaryOrgId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryMembersOrgTypeServiceImpl的findPrimaryMembersOrgTypeByMemberID方法出现异常，原因：",
					"根据成员和组织查询对应的组织信息出现错误", e);
		}
	}

	/**
	 * 根据成员查询对应的组织信息
	 */
	public List<PrimaryMembersOrgType> findPrimaryMembersOrgTypeByMember(
			Long memberId) {
		try {
			if (memberId == null) {
				throw new BusinessValidationException("没有选中成员信息!");
			}
			return primaryMembersOrgTypeDao
					.findPrimaryMembersOrgTypeByMember(memberId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryMembersOrgTypeServiceImpl的findPrimaryMembersOrgTypeByMemberID方法出现异常，原因：",
					"根据成员查询对应的组织信息出现错误", e);
		}
	}

	/**
	 * 成员选择组织
	 */
	@Override
	public Long addPrimaryMembersOrgType(
			PrimaryMembersOrgType primaryMembersOrgType) {
		try {
			if (primaryMembersOrgType == null) {
				throw new BusinessValidationException("成员组织新增失败!");
			}
			return primaryMembersOrgTypeDao
					.addPrimaryMembersOrgType(primaryMembersOrgType);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryMembersOrgTypeServiceImpl的addPrimaryMembersOrgType方法出现异常，原因：",
					"成员组织新增出现错误", e);
		}
	}

	@Override
	public void deltePrimaryMembersOrgType(Long primaryMembersId,
			Long primaryOrgId) {
		primaryMembersOrgTypeDao.deltePrimaryMembersOrgType(primaryMembersId,
				primaryOrgId);
	}

	@Override
	public void updatePrimaryMembersOrgType(
			PrimaryMembersOrgType primaryMembersOrgType) {
		primaryMembersOrgTypeDao
				.updatePrimaryMembersOrgType(primaryMembersOrgType);
	}

	@Override
	public void updatePrimaryMembers(PrimaryMembersOrgType primaryMembersOrgType) {
		primaryMembersOrgTypeDao.updatePrimaryMembers(primaryMembersOrgType);
	}

	@Override
	public void deltePrimaryMembersOrgTypeByPrimaryMembersId(
			long primaryMembersId) {
		primaryMembersOrgTypeDao
				.deltePrimaryMembersOrgTypeByPrimaryMembersId(primaryMembersId);

	}

	@Override
	public void deltePrimaryMembersOrgTypeByPrimaryMembersIds(List<Long> ids) {
		primaryMembersOrgTypeDao
				.deltePrimaryMembersOrgTypeByPrimaryMembersIds(ids);
	}

	@Override
	public PrimaryMembers getprimaryOrgName(PrimaryMembers primaryMembers) {
		List<PrimaryMembersOrgType> list = primaryMembersOrgTypeDao
				.getprimaryOrgName(primaryMembers.getId());
		String primaryOrgIds = null;
		String primaryOrgNames = null;
		for (PrimaryMembersOrgType p : list) {
			if (primaryOrgIds == null) {
				primaryOrgIds = p.getIsFourLevelPlatform() + "-"
						+ p.getPrimaryOrgId().toString();
			} else {
				primaryOrgIds = primaryOrgIds + ","
						+ p.getIsFourLevelPlatform() + "-"
						+ p.getPrimaryOrgId().toString();
			}
			if (primaryOrgNames == null) {
				primaryOrgNames = p.getDetailName();
			} else {
				primaryOrgNames = primaryOrgNames + "," + p.getDetailName();
			}
		}
		primaryMembers.setPrimaryOrgIds(primaryOrgIds);
		primaryMembers.setPrimaryOrgNames(primaryOrgNames);
		return primaryMembers;
	}

	@Override
	public int countFindPrimaryOrgMembers(
			PrimaryMembersOrgType primaryMembersOrgType) {
		return primaryMembersOrgTypeDao
				.countFindPrimaryOrgMembers(primaryMembersOrgType);
	}

	@Override
	public boolean isFourLevelPlat(Long memberId) {
		List<PrimaryMembersOrgType> primaryMembersOrgTypeList = primaryMembersOrgTypeDao
				.findPrimaryMembersOrgTypeByMember(memberId);
		Long parameter = new Long(1L);
		for (PrimaryMembersOrgType p : primaryMembersOrgTypeList) {
			if (parameter.equals(p.getIsFourLevelPlatform())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void deletePrimaryMembersOrgType(
			PrimaryMembersOrgType primaryMembersOrgType) {
		primaryMembersOrgTypeDao
				.deletePrimaryMembersOrgType(primaryMembersOrgType);
	}

	/**
	 * 
	 * @Title: updatePrimarymembersorgtypeOrgIdForOrgChange
	 * @Description: TODO党委组织修改成员关联关系表
	 * @param @param newId
	 * @param @param oldId 设定文件
	 * @return void 返回类型
	 * @author wanggz
	 * @date 2014-10-17 上午10:09:34
	 */
	@Override
	public void updatePrimarymembersorgtypeOrgIdForOrgChange(Long newId,
			Long oldId) {
		try {
			primaryMembersOrgTypeDao
					.updatePrimarymembersorgtypeOrgIdForOrgChange(newId, oldId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryMembersOrgTypeServiceImpl的updatePrimarymembersorgtypeOrgIdForOrgChange方法出现异常，原因：",
					"修改关联关系出现错误", e);
		}
	}
}
