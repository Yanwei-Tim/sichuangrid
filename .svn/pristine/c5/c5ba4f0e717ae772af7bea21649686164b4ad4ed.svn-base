package com.tianque.baseInfo.companyPlace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.companyPlace.dao.ContaminationInfoDAO;
import com.tianque.baseInfo.companyPlace.domain.ContaminationInfo;
import com.tianque.baseInfo.companyPlace.service.ContaminationInfoService;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("contaminationInfoService")
@Transactional
public class ContaminationInfoServiceImpl implements ContaminationInfoService {
	@Autowired
	private ContaminationInfoDAO contaminationInfoDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ContaminationInfo addContaminationInfo(
			ContaminationInfo contaminationInfo) {
		if (contaminationInfo == null) {
			throw new BusinessValidationException("参数错误！");
		}
		try {
			Long id = contaminationInfoDao
					.addContaminationInfo(contaminationInfo);
			return contaminationInfoDao.getContaminationInfoById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ContaminationInfoServiceImpl的addContaminationInfo方法出现异常，原因：",
					"新增污染源业务信息出错！", e);
		}
	}

	@Override
	public boolean deleteContaminationInfo(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！");
		}
		try {
			return contaminationInfoDao.deleteContaminationInfo(id) > 0;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ContaminationInfoServiceImpl的deleteContaminationInfo方法出现异常，原因：",
					"删除污染源业务信息出错！", e);
		}
	}

	@Override
	public void updateContaminationInfo(ContaminationInfo contaminationInfo) {
		if (contaminationInfo == null) {
			throw new BusinessValidationException("参数错误！");
		}
		try {
			contaminationInfoDao.updateContaminationInfo(contaminationInfo);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ContaminationInfoServiceImpl的updateContaminationInfo方法出现异常，原因：",
					"修改污染源业务信息出错！", e);
		}
	}

	@Override
	public boolean deleteContaminationInfoByBusinessId(Long businessId) {
		if (businessId == null) {
			throw new BusinessValidationException("参数错误！");
		}
		try {
			return contaminationInfoDao
					.deleteContaminationInfoByBusinessId(businessId) > 0;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ContaminationInfoServiceImpl的deleteContaminationInfoByBusinessId方法出现异常，原因：",
					"根据业务编号删除对应的污染源业务信息出错！", e);
		}
	}

	@Override
	public ContaminationInfo getContaminationInfoByBusinessId(Long businessId) {
		if (businessId == null) {
			throw new BusinessValidationException("参数错误！");
		}
		try {
			ContaminationInfo contaminationInfo = contaminationInfoDao
					.getContaminationInfoByBusinessId(businessId);
			if (contaminationInfo != null
					&& contaminationInfo.getTownOrg() != null) {
				Organization organization = organizationDubboService
						.getSimpleOrgById(contaminationInfo.getTownOrg()
								.getId());
				contaminationInfo.setTownOrg(organization);
			}
			return contaminationInfo;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ContaminationInfoServiceImpl的getContaminationInfoByBusinessId方法出现异常，原因：",
					"根据业务编号查询对应的污染源业务信息出错！", e);
		}
	}
}
