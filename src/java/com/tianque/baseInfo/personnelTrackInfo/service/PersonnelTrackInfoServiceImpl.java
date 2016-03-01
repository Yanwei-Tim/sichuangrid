package com.tianque.baseInfo.personnelTrackInfo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.People;
import com.tianque.baseInfo.personnelTrackInfo.dao.PersonnelTrackInfoDao;
import com.tianque.baseInfo.personnelTrackInfo.domain.PersonnelTrackInfo;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;

@Service("personnelTrackInfoService")
@Transactional
public class PersonnelTrackInfoServiceImpl implements PersonnelTrackInfoService {

	@Autowired
	private PersonnelTrackInfoDao personnelTrackInfoDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PermissionService permissionService;

	public PageInfo<PersonnelTrackInfo> findPersonnelTrackInfoByIdCardNo(
			String idCardNo, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		if (!StringUtil.isStringAvaliable(idCardNo)) {
			throw new BusinessValidationException("参数错误");
		}
		return personnelTrackInfoDao.findPersonnelTrackInfoByIdCardNo(idCardNo,
				pageNum, pageSize, sidx, sord);
	}

	public void addPersonnelTrackWhenAttentionedOrCancel(People basePersonnel,
			Organization oldPersonnelOrg, String personnelType,
			Integer operationType, Integer personinitType,
			String operationContent) {
		Organization org1 = organizationDubboService
				.getSimpleOrgById(basePersonnel.getOrganization().getId());
		User user = permissionService.getSimpleUserById(ThreadVariable
				.getUser().getId());
		/*
		 * String idCardNo = null; if
		 * (personnelType.equalsIgnoreCase("overseaPersonnel")) { idCardNo =
		 * basePersonnel.getCertificateNo(); } else { idCardNo =
		 * basePersonnel.getIdCardNo(); }
		 */
		addPersonnelTrack(createPersonnelTrack(basePersonnel.getIdCardNo(),
				org1, oldPersonnelOrg, basePersonnel.getId(),
				basePersonnel.getName(), personnelType, operationType,
				personinitType, user.getOrganization(), operationContent, user,
				basePersonnel.getCreateDate()));

	}

	public PersonnelTrackInfo addPersonnelTrack(
			PersonnelTrackInfo personnelTrackInfo) {
		return personnelTrackInfoDao.addPersonnelTrackInfo(personnelTrackInfo);
	}

	private PersonnelTrackInfo createPersonnelTrack(String idcardNo,
			Organization personnelOrganization,
			Organization oldPersonnelOrganization, Long personnelId,
			String personnelName, String personnelType, Integer operationType,
			Integer personinitType, Organization operationOrganization,
			String operationContent, User operationUser, Date operationDate) {

		if (!validate(idcardNo, operationOrganization, personnelOrganization,
				personnelId, personnelName, personnelType, operationType,
				personinitType, operationContent, operationUser)) {
			throw new BusinessValidationException("参数错误");
		}

		PersonnelTrackInfo personnelTrack = new PersonnelTrackInfo();
		personnelTrack.setOperationContent(operationContent);
		personnelTrack.setIdCardNo(idcardNo);
		personnelTrack.setOperationType(operationType);
		personnelTrack.setOperationUser(operationUser);

		personnelTrack.setPersonnelId(personnelId);
		personnelTrack.setPersonnelName(personnelName);
		personnelTrack.setPersonnelType(personnelType);
		personnelTrack.setPersoninitType(personinitType);
		personnelTrack.setPersonnelOrganization(personnelOrganization);
		personnelTrack.setOldPersonnelOrganization(oldPersonnelOrganization);
		personnelTrack.setOperationOrganization(operationOrganization);
		personnelTrack.setPersonnelOrgInternalCode(organizationDubboService
				.getSimpleOrgById(personnelOrganization.getId())
				.getOrgInternalCode());
		if (operationDate == null) {
			personnelTrack.setOperationDate(CalendarUtil
					.now("yyyy-MM-dd HH:mm:ss"));
		} else {
			personnelTrack.setOperationDate(operationDate);
		}
		return personnelTrack;
	}

	private boolean validate(String idcardNo,
			Organization operationOrganization,
			Organization personnelOrganization, Long personnelId,
			String personnelName, String personnelType, Integer operationType,
			Integer personinitType, String operationContent, User operationUser) {
		if (!StringUtil.isStringAvaliable(idcardNo)
				|| operationOrganization == null
				|| personnelOrganization == null || personnelId == null
				|| !StringUtil.isStringAvaliable(personnelName)
				|| !StringUtil.isStringAvaliable(personnelType)
				|| operationType == null || personinitType == null
				|| operationUser == null
				|| operationOrganization.getId() == null
				|| operationOrganization.getId() == null
				|| operationUser == null) {
			return false;
		}
		return true;
	}

}
