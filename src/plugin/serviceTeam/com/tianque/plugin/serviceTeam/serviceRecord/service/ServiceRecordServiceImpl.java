package com.tianque.plugin.serviceTeam.serviceRecord.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.UsedInfo;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.plugin.serviceTeam.serviceRecord.dao.ServiceRecordAttachmentDao;
import com.tianque.plugin.serviceTeam.serviceRecord.dao.ServiceRecordDao;
import com.tianque.plugin.serviceTeam.serviceRecord.dao.ServiceRecordRelyMemberDao;
import com.tianque.plugin.serviceTeam.serviceRecord.dao.ServiceRecordRelyObjectDao;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecord;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecordAttachment;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecordRelyMember;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecordRelyObject;
import com.tianque.plugin.serviceTeam.serviceRecord.validator.ServiceRecordValidatorImpl;
import com.tianque.plugin.serviceTeam.serviceRecord.vo.ServiceRecordVo;
import com.tianque.plugin.serviceTeam.serviceTeamManage.dao.ServiceTeamDao;
import com.tianque.plugin.serviceTeam.serviceTeamManage.vo.ServiceTeamVo;
import com.tianque.plugin.tqSearch.sqlMap.DeleteSqlMap;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.domain.WorkDiary;
import com.tianque.working.service.WorkDiaryService;

@Service("serviceRecordService")
public class ServiceRecordServiceImpl extends AbstractBaseService implements
		ServiceRecordService {

	@Qualifier("serviceRecordValidator")
	@Autowired
	private ServiceRecordValidatorImpl serviceRecordValidator;
	@Autowired
	private ServiceRecordDao serviceRecordDao;
	@Autowired
	private ServiceTeamDao serviceTeamDao;
	@Autowired
	private ServiceRecordRelyObjectDao serviceRecordRelyObjectDao;
	@Autowired
	private ServiceRecordRelyMemberDao serviceRecordRelyMemberDao;
	@Autowired
	private ServiceRecordAttachmentDao serviceRecordAttachmentDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private WorkDiaryService workDiaryService;
	/** 附件对象列表 */
	private List<ServiceRecordAttachment> serviceRecordAttachments;

	@Override
	public ServiceRecordVo addServiceRecord(ServiceRecord serviceRecord) {
		try {
			autoFilled(serviceRecord);

			serviceRecordValidator(serviceRecord);

			Long serviceRecordId = serviceRecordDao
					.addServiceRecord(serviceRecord);

			addRelationship(serviceRecord, serviceRecordId);

			addWorkDiary(serviceRecord);
			return serviceRecordDao.getServiceRecordById(serviceRecordId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceRecordServiceImpl的addServiceRecord方法出现异常，原因：",
					"新增服务记录出现错误", e);
		}
	}

	// FIXME 这个objectId为什么？？
	@Override
	public PageInfo<ServiceRecordVo> findServiceRecords(
			ServiceRecordVo serviceRecordVo, Long[] objectId,
			String objectType, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		try {
			// 判断是否是单位场所（总类别），重点场所（所有类别），如果是，查询所有的类型类别
			if (objectType != null && !"".equals(objectType)
					&& ModulTypes.BUSSINESS_AND_SIZED.containsKey(objectType)) {
				serviceRecordVo
						.setObjectTypeList(ModulTypes.allCompanyPlaceMapKey);
			} else {
				List<String> objectTypeList = new ArrayList<String>();
				objectTypeList.add(objectType);
				serviceRecordVo.setObjectTypeList(objectTypeList);
			}
			if (serviceRecordVo != null
					&& serviceRecordVo.getRecordAddDateEnd() != null) {
				serviceRecordVo.setRecordAddDateEnd(CalendarUtil
						.getLastDaySecoend(serviceRecordVo
								.getRecordAddDateEnd()));
			}
			PageInfo<ServiceRecordVo> pageInfo = serviceRecordDao
					.findServiceRecords(
							fillSearchArgs(serviceRecordVo, objectId,
									objectType, sidx, sord), pageNum, pageSize);
			for (ServiceRecordVo vo : pageInfo.getResult()) {
				vo.setServiceRecordAttachments(findServiceRecordAttachments(vo
						.getId()));
				if (vo.getTeamId() != null) {
					ServiceTeamVo serviceTeamVo = serviceTeamDao
							.getServiceTeamById(vo.getTeamId());
					if (serviceTeamVo != null
							&& serviceTeamVo.getTeamName() != null) {
						vo.setTeamName(serviceTeamVo.getTeamName());
					}
				}
				vo.setInternalId(organizationDubboService
						.getFullOrgById(vo.getOrganization().getId())
						.getOrgLevel().getInternalId());
			}
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceRecordServiceImpl的findServiceRecords方法出现异常，原因：",
					"服务记录列表信息出现错误", e);
		}
	}

	@Override
	public ServiceRecordVo getServiceRecordById(Long id) {
		try {
			ServiceRecordVo vo = serviceRecordDao.getServiceRecordById(id);

			vo.setObjects(this.findServiceObjects(id));

			vo.setMembers(this.findServiceMembers(id));

			vo.setServiceRecordAttachments(this
					.findServiceRecordAttachments(id));

			return vo;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceRecordServiceImpl的getServiceRecordById方法出现异常，原因：",
					"根据ID获取服务记录出现错误", e);
		}
	}

	@Override
	public ServiceRecordVo updateServiceRecord(ServiceRecord serviceRecord,Long serviceObjectId) {
		try {
			autoFilled(serviceRecord);
			serviceRecordValidator(serviceRecord);

			serviceRecordRelyObjectDao
					.deleteServiceRecordRelyObjects(serviceRecord.getId(),serviceObjectId);
			serviceRecordRelyMemberDao
					.deleteServiceRecordRelyMembers(serviceRecord.getId());

			addRelationship(serviceRecord, serviceRecord.getId());

			return serviceRecordDao.updateServiceRecord(serviceRecord);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceRecordServiceImpl的updateServiceRecord方法出现异常，原因：",
					"修改服务记录出现错误", e);
		}
	}

	@Override
	public List<ServiceRecordAttachment> findServiceRecordAttachments(Long id) {
		return serviceRecordAttachmentDao.findServiceRecordAttachments(id);
	}

	@Override
	public ServiceRecordAttachment getServiceRecordAttachmentById(Long id) {
		return serviceRecordAttachmentDao.getServiceRecordAttachmentById(id);
	}

	@Override
	@SolrServerIndex(mode = OperateMode.DELETE, value = DeleteSqlMap.SERVICERECORD_KEY)
	public int deleteServiceRecords(Long[] deleteIds) {
		int count = 0;
		for (int i = 0; i < deleteIds.length; i++) {
			if (null != getServiceRecordById(deleteIds[i])) {
				int deleteCount = serviceRecordDao
						.deleteServiceRecord(deleteIds[i]);
				count += deleteCount;

				serviceRecordRelyObjectDao
						.deleteServiceRecordRelyObjects(deleteIds[i],null);

				serviceRecordRelyMemberDao
						.deleteServiceRecordRelyMembers(deleteIds[i]);

				serviceRecordAttachmentDao
						.deleteServiceRecordHasAttachments(deleteIds[i]);
			}
		}
		return count;
	}

	@Override
	public List<ServiceRecordRelyObject> findServiceObjects(Long id) {
		return serviceRecordRelyObjectDao.findServiceObjects(id);
	}

	@Override
	public int deleteAttachmentByFileId(Long fileId) {
		return serviceRecordAttachmentDao
				.deleteServiceRecordHasAttachment(fileId);
	}

	@Override
	public List getDisplayYear() {
		Date minTime = serviceRecordDao.getMinTime();
		SimpleDateFormat f = new SimpleDateFormat("yyyy");
		Integer nowYear = Integer.parseInt(f.format(new Date()));
		List<Map<String, String>> year = new ArrayList();
		if (minTime != null) {
			Integer minYear = Integer.parseInt(f.format(minTime));
			do {
				Map<String, String> map = new HashMap<String, String>();
				map.put(String.valueOf(nowYear), String.valueOf(nowYear));
				year.add(map);
				nowYear--;
			} while (minYear <= nowYear);
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put(String.valueOf(nowYear), String.valueOf(nowYear));
			year.add(map);
		}
		return year;
	}

	@Override
	public List<ServiceRecordRelyMember> findServiceMembers(Long recordId) {
		return serviceRecordRelyMemberDao.findServiceMembers(recordId);
	}

	/**
	 * 添加对应serviceRecordId的三种关联关系
	 */
	private void addRelationship(ServiceRecord serviceRecord, Long id) {

		addServiceRecordRelyObjects(serviceRecord.getObjectIds(),
				serviceRecord.getObjectType(), serviceRecord
						.getServiceObjects().split(","), serviceRecord.getId());

		addServiceRecordRelyMembers(serviceRecord.getMemberIds(), serviceRecord
				.getServiceMembers().split(","), serviceRecord.getId());

		addServiceRecordAttachments(serviceRecord.getAttachFileNames(),
				serviceRecord.getId());
	}

	/** 添加工作日志 */
	private void addWorkDiary(ServiceRecord serviceRecord) {
		WorkDiary workDiary = getWorkDiary(serviceRecord);
		if (workDiary != null) {
			workDiaryService.addWorkDiary(workDiary);
		}

	}

	/** 获得工作日志对象 */
	private WorkDiary getWorkDiary(ServiceRecord serviceRecord) {
		WorkDiary workDiary = new WorkDiary();
		Organization org = organizationDubboService
				.getSimpleOrgById(serviceRecord.getUserOrgId());
		if (org == null) {
			throw new BusinessValidationException("层级不存在");
		}
		PropertyDict diaryType = getDiaryType(serviceRecord.getObjectType());
		if (diaryType != null) {
			workDiary.setDiaryType(diaryType);
			workDiary.setOrganization(org);
			workDiary.setOrgInternalCode(org.getOrgInternalCode());
			workDiary.setWorkTime(serviceRecord.getOccurDate());
			workDiary.setWorkPlace(serviceRecord.getOccurPlace());
			workDiary.setWorkContent(serviceRecord.getServiceContent());
			workDiary.setWorkUserName(serviceRecord.getServiceMembers());
			return workDiary;
		}
		return null;
	}

	/** 获得工作日志类型 */
	private PropertyDict getDiaryType(String objectType) {

		if (PopulationCatalog.parse(objectType) != null) {
			String objectParentType = PopulationCatalog.parse(objectType)
					.getParentCatalog();
			if (objectParentType
					.equals(PopulationCatalog.ALL_ATTENTION_POPULATION)) {
				return propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								PropertyTypes.WORKDIARY_DIARY_TYPE, "特殊人群走访帮教类");
			} else if (objectParentType
					.equals(PopulationCatalog.ALL_UNEMPLOYED_POPULATION)) {
				return propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								PropertyTypes.WORKDIARY_DIARY_TYPE, "失业人员走访服务类");
			} else if (objectParentType
					.equals(PopulationCatalog.ALL_BIRTH_POPULATION)) {
				return propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								PropertyTypes.WORKDIARY_DIARY_TYPE, "育龄妇女走访服务类");
			} else if (objectParentType
					.equals(PopulationCatalog.ALL_LOVINGCARE_POPULATION)) {
				return propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								PropertyTypes.WORKDIARY_DIARY_TYPE, "关怀对象走访服务类");
			} else if (objectParentType
					.equals(PopulationCatalog.ALL_IMPORTANT_PLACE)) {
				return propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								PropertyTypes.WORKDIARY_DIARY_TYPE, "重点场所走访巡防类");
			} else if (objectParentType.equals(PopulationCatalog.DOUBLE_NEW)) {
				return propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								PropertyTypes.WORKDIARY_DIARY_TYPE, "社会组织走访巡防类");
			} else if (objectParentType
					.equals(PopulationCatalog.ALL_ENTERPRISE)) {
				return propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								PropertyTypes.WORKDIARY_DIARY_TYPE, "企业走访巡防类");
			}
		} else if (objectType.equals(BaseInfoTables.RENTALHOUSE_KEY)) {
			return propertyDictService
					.findPropertyDictByDomainNameAndDictDisplayName(
							PropertyTypes.WORKDIARY_DIARY_TYPE, "出租房走访巡防类");
		}
		return null;

	}

	/**
	 * 新增附件
	 * 
	 * @param attachFileNames
	 *            ，serviceRecordId
	 */
	public void addServiceRecordAttachments(String[] attachFileNames,
			Long serviceRecordId) {
		if (null != attachFileNames && attachFileNames.length > 0) {
			ServiceRecordAttachment serviceRecordAttachment = null;
			StoredFile storedFile = null;
			for (String attachFileName : attachFileNames) {

				if (attachFileName.charAt(0) == ',') {
					attachFileName = attachFileName.substring(1);
					try {
						storedFile = FileUtil.copyTmpFileToStoredFile(
								attachFileName,
								GridProperties.SERVICERECORD_PATH);
					} catch (Exception e) {
						e.printStackTrace();
					}
					serviceRecordAttachment = new ServiceRecordAttachment();
					serviceRecordAttachment.setRecordId(serviceRecordId);
					serviceRecordAttachment.setFileSize(storedFile
							.getFileSize());
					serviceRecordAttachment.setFileActualUrl(storedFile
							.getStoredFilePath()
							+ File.separator
							+ storedFile.getStoredFileName());
					serviceRecordAttachment.setFileName(attachFileName);
					serviceRecordAttachmentDao
							.addServiceRecordAttachment(serviceRecordAttachment);
				}
			}
		}
	}

	/**
	 * 新增对象依赖关系
	 */
	public void addServiceRecordRelyObjects(Long[] objectIds,
			String populationType, String[] objectNames, Long serviceRecordId) {

		for (int i = 0; i < objectIds.length; i++) {
			ServiceRecordRelyObject relyObject = new ServiceRecordRelyObject();
			relyObject.setObjectId(objectIds[i]);
			relyObject.setObjectName(objectNames[i]);
			relyObject.setObjectType(populationType);
			relyObject.setRecordId(serviceRecordId);
			serviceRecordRelyObjectDao.addServiceRecordRelyObject(relyObject);
		}

	}

	/**
	 * 新增成员依赖关系
	 */
	public void addServiceRecordRelyMembers(Long[] memberIds,
			String[] memberNames, Long serviceRecordId) {

		for (int i = 0; i < memberIds.length; i++) {
			ServiceRecordRelyMember relyMember = new ServiceRecordRelyMember();
			relyMember.setMemberId(memberIds[i]);
			relyMember.setMemberName(memberNames[i]);
			relyMember.setRecordId(serviceRecordId);
			serviceRecordRelyMemberDao.addServiceRecordRelyMember(relyMember);
		}

	}

	/**
	 * 给VO设置参数 (搜索用)
	 * 
	 * @param serviceRecordVo
	 * @param sidx
	 * @param sord
	 * @return serviceRecordVo
	 */
	private ServiceRecordVo fillSearchArgs(ServiceRecordVo serviceRecordVo,
			Long[] objectId, String objectType, String sidx, String sord) {
		Organization organization = organizationDubboService
				.getSimpleOrgById(serviceRecordVo.getOrganization().getId());
		if (organization == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		serviceRecordVo.setOrganization(organization);
		serviceRecordVo.setSortField(sidx);
		serviceRecordVo.setOrder(sord);
		if (objectId != null) {
			serviceRecordVo.setObjectId(objectId[0]);
			serviceRecordVo.setObjectType(objectType);
		}
		if (serviceRecordVo.getDisplayYear() != null) {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			try {
				serviceRecordVo.setOccurDateStart(f.parse(serviceRecordVo
						.getDisplayYear() + "-01-01"));
				serviceRecordVo.setOccurDateEnd(f.parse(serviceRecordVo
						.getDisplayYear() + "-12-31"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return serviceRecordVo;
	}

	/**
	 * 对象验证
	 * 
	 * @param serviceRecord
	 */
	private void serviceRecordValidator(ServiceRecord serviceRecord) {
		ValidateResult baseDataValidator = serviceRecordValidator
				.validate(serviceRecord);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	/**
	 * 填充属性
	 * 
	 * @param serviceRecord
	 */
	private void autoFilled(ServiceRecord serviceRecord) {
		// 根据组织id获取完整organization
		Organization organization = organizationDubboService
				.getSimpleOrgById(serviceRecord.getOrganization().getId());
		if (organization == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		serviceRecord.setOrganization(organization);
	}

	public List<ServiceRecordAttachment> getServiceRecordAttachments() {
		return serviceRecordAttachments;
	}

	public void setServiceRecordAttachments(
			List<ServiceRecordAttachment> serviceRecordAttachments) {
		this.serviceRecordAttachments = serviceRecordAttachments;
	}

	public ServiceRecordValidatorImpl getServiceRecordValidator() {
		return serviceRecordValidator;
	}

	public void setServiceRecordValidator(
			ServiceRecordValidatorImpl serviceRecordValidator) {
		this.serviceRecordValidator = serviceRecordValidator;
	}

	public ServiceRecordDao getServiceRecordDao() {
		return serviceRecordDao;
	}

	public void setServiceRecordDao(ServiceRecordDao serviceRecordDao) {
		this.serviceRecordDao = serviceRecordDao;
	}

	public ServiceTeamDao getServiceTeamDao() {
		return serviceTeamDao;
	}

	public void setServiceTeamDao(ServiceTeamDao serviceTeamDao) {
		this.serviceTeamDao = serviceTeamDao;
	}

	public ServiceRecordRelyObjectDao getServiceRecordRelyObjectDao() {
		return serviceRecordRelyObjectDao;
	}

	public void setServiceRecordRelyObjectDao(
			ServiceRecordRelyObjectDao serviceRecordRelyObjectDao) {
		this.serviceRecordRelyObjectDao = serviceRecordRelyObjectDao;
	}

	public ServiceRecordRelyMemberDao getServiceRecordRelyMemberDao() {
		return serviceRecordRelyMemberDao;
	}

	public void setServiceRecordRelyMemberDao(
			ServiceRecordRelyMemberDao serviceRecordRelyMemberDao) {
		this.serviceRecordRelyMemberDao = serviceRecordRelyMemberDao;
	}

	public ServiceRecordAttachmentDao getServiceRecordAttachmentDao() {
		return serviceRecordAttachmentDao;
	}

	public void setServiceRecordAttachmentDao(
			ServiceRecordAttachmentDao serviceRecordAttachmentDao) {
		this.serviceRecordAttachmentDao = serviceRecordAttachmentDao;
	}

	@Override
	public Integer countServiceRecordsForTeam(Long id) {
		return serviceRecordDao.countServiceRecordsForTeam(id);
	}

	@Override
	public Integer countServiceRecordsForTeamMember(Long id) {
		return serviceRecordDao.countServiceRecordsForTeamMember(id);
	}

	@Override
	public List<ServiceRecordVo> getNeedExportDatas(boolean pageOnly,
			ServiceRecordVo serviceRecordVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		PageInfo<ServiceRecordVo> pageInfo = new PageInfo<ServiceRecordVo>();
		if (pageOnly) {
			pageInfo = serviceRecordDao.findServiceRecords(
					fillSearchArgs(serviceRecordVo, null, null, sidx, sord),
					pageNum, pageSize);
		} else {
			pageInfo = serviceRecordDao.findServiceRecords(
					fillSearchArgs(serviceRecordVo, null, null, sidx, sord),
					null, null);
		}
		for (ServiceRecordVo vo : pageInfo.getResult()) {
			if (vo.getTeamId() != null) {
				vo.setTeamName(serviceTeamDao
						.getServiceTeamById(vo.getTeamId()).getTeamName());
			}
		}
		return pageInfo.getResult();

	}

	@Override
	public Integer getCount(ServiceRecordVo serviceRecordVo) {
		// TODO Auto-generated method stub
		return serviceRecordDao.getCount(fillSearchArgs(serviceRecordVo, null,
				null, null, null));
	}

	@Override
	public void updateServiceRecordByTransfer(Long orgId, String orgCode,
			String objectType, Long oldObjectId, Long newObjectId) {
		if (orgId == null || orgCode == null || "".equals(orgCode)
				|| objectType == null || "".equals(objectType)
				|| oldObjectId == null || newObjectId == null) {
			throw new BusinessValidationException("转移人口时修改服务记录参数错误");
		}
		try {
			serviceRecordDao.updateServiceRecordsOrgAndObejctId(orgId, orgCode,
					objectType, oldObjectId);
			serviceRecordDao.updateServiceRecordRelyObject(objectType,
					oldObjectId, newObjectId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceRecordServiceImpl的updateServiceRecordByTransfer方法出现异常，原因：",
					"转移人口时修改服务记录出现错误", e);
		}

	}

	@Override
	public void updateServiceRecordByTransfer(Long orgId, String orgCode,
			String objectType, Long oldObjectId, Long newObjectId,
			String newObjectType) {
		if (orgId == null || orgCode == null || "".equals(orgCode)
				|| objectType == null || "".equals(objectType)
				|| oldObjectId == null || newObjectId == null
				|| newObjectType == null || "".equals(newObjectType)) {
			throw new BusinessValidationException("转移人口时修改服务记录参数错误");
		}
		try {
			serviceRecordDao.updateServiceRecordsOrgAndObejctId(orgId, orgCode,
					newObjectType, newObjectId);
			serviceRecordDao.updateServiceRecordRelyObject(objectType,
					oldObjectId, newObjectId, newObjectType);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceRecordServiceImpl的updateServiceRecordByTransfer方法出现异常，原因：",
					"转移人口时修改服务记录出现错误", e);
		}

	}

	@Override
	public void updateServiceRecordsOrg(Long orgId, String orgCode,
			String objectType, Long objectId) {
		if (orgId == null || orgCode == null || "".equals(orgCode)
				|| objectType == null || "".equals(objectType)
				|| objectId == null) {
			throw new BusinessValidationException("转移人口时修改服务记录参数错误");
		}
		try {
			serviceRecordDao.updateServiceRecordsOrg(orgId, orgCode,
					objectType, objectId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceRecordServiceImpl的updateServiceRecordsOrg方法出现异常，原因：",
					"转移人口时修改服务记录出现错误", e);
		}

	}

	@Override
	public void deleteServiceRecordHasObject(Long objectId, String objectType) {
		try {
			serviceRecordDao.deleteServiceRecordHasObject(objectId, objectType);
		} catch (Exception e) {
			throw new ServiceValidationException("删除服务时记录出错", e);
		}

	}

	@Override
	public List<UsedInfo> getSpecialCrowdCountForUsedInfo(
			Date beforDayStartDate, Date beforDayEndDate, Date beforWeekMonday,
			Date beforWeekSunday, Date monthStartDate, Date monthEndDate,
			Long orgId, Long orgTypeId) {
		if (beforDayStartDate == null || beforDayEndDate == null
				|| beforWeekMonday == null || beforWeekSunday == null
				|| monthStartDate == null || monthEndDate == null
				|| orgId == null || orgTypeId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return serviceRecordDao
				.getSpecialCrowdCountForUsedInfo(beforDayStartDate,
						beforDayEndDate, beforWeekMonday, beforWeekSunday,
						monthStartDate, monthEndDate, orgId, orgTypeId);
	}
}
