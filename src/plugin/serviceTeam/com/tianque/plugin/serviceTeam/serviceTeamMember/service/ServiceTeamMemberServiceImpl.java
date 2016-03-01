package com.tianque.plugin.serviceTeam.serviceTeamMember.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.serviceTeam.router.vo.ServiceMemberVo;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.dao.ServiceMemberWithObjectDao;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.service.ServiceMemberWithObjectService;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.vo.ServiceMemberWithObjectVo;
import com.tianque.plugin.serviceTeam.serviceRecord.dao.ServiceRecordDao;
import com.tianque.plugin.serviceTeam.serviceRecord.dao.ServiceRecordRelyMemberDao;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecord;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecordRelyMember;
import com.tianque.plugin.serviceTeam.serviceRecord.service.ServiceRecordService;
import com.tianque.plugin.serviceTeam.serviceTeamHasObjects.dao.ServiceTeamHasObjectsDao;
import com.tianque.plugin.serviceTeam.serviceTeamHasObjects.domain.ServiceTeamHasObjects;
import com.tianque.plugin.serviceTeam.serviceTeamManage.service.ServiceTeamService;
import com.tianque.plugin.serviceTeam.serviceTeamMember.dao.ServiceTeamMemberDao;
import com.tianque.plugin.serviceTeam.serviceTeamMember.domain.ServiceTeamMember;
import com.tianque.plugin.serviceTeam.serviceTeamMember.domain.ServiceTeamMemberBase;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceMemberInTeamVo;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceTeamMemberVo;
import com.tianque.plugin.serviceTeam.util.Constants;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 服务成员业务处理实现类
 * 
 * @author tengjia
 */
@Service("serviceTeamMemberService")
@Transactional
public class ServiceTeamMemberServiceImpl extends AbstractBaseService implements
		ServiceTeamMemberService {

	@Autowired
	private ServiceTeamMemberDao serviceTeamMemberDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ServiceTeamHasObjectsDao serviceTeamHasObjectsDao;
	@Autowired
	private ServiceRecordRelyMemberDao serviceRecordRelyMemberDao;
	@Autowired
	private ServiceMemberWithObjectDao serviceMemberWithObjectDao;
	@Autowired
	private ServiceRecordDao serviceRecordDao;
	@Autowired
	private ServiceMemberWithObjectService serviceMemberWithObjectService;
	@Autowired
	private ServiceRecordService serviceRecordService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private ServiceTeamService serviceTeamService;
	private static final int HASOBJECTS = -1;
	private static final int HASRECORDS = -2;

	private void validateSearchParameter(ServiceTeamMemberVo serviceTeamMemberVo) {
		if (null == serviceTeamMemberVo || null == serviceTeamMemberVo.getOrg()) {
			throw new BusinessValidationException("无法定位要查询的数据");
		}
	}

	@Override
	public ServiceTeamMemberVo addServiceTeamMemberBase(
			ServiceTeamMemberBase serviceTeamMemberBase) {
		try {
			// serviceTeamValidator(serviceTeamMemberBase);
			autoFilled(serviceTeamMemberBase);
			return serviceTeamMemberDao
					.addServiceTeamMemberBase(serviceTeamMemberBase);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceTeamMemberServiceImpl的addServiceTeamMemberBase方法出现异常，原因：",
					"新增服务成员出现错误", e);
		}
	}

	@Override
	public ServiceTeamMemberVo addTeamAndMemberRelation(
			ServiceTeamMember serviceTeamMemberDetails) {
		try {
			return serviceTeamMemberDao
					.addTeamAndMemberRelation(serviceTeamMemberDetails);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceTeamMemberServiceImpl的addServiceTeamMemberDetails方法出现异常，原因：",
					"新增服务成员出现错误", e);
		}
	}

	@Override
	public ServiceTeamMemberVo updateServiceTeamMemberBase(
			ServiceTeamMemberBase serviceTeamMemberBase) {
		autoFilled(serviceTeamMemberBase);
		try {
			// serviceTeamValidator(serviceTeamMemberBase);
			return serviceTeamMemberDao
					.updateServiceTeamMemberBase(serviceTeamMemberBase);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceTeamMemberServiceImpl的updateServiceTeamMemberBase方法出现异常，原因：",
					"修改服务成员出现错误", e);
		}
	}

	@Override
	public int deleteServiceTeamMemberBase(String selectedIds) {
		if (!StringUtil.isStringAvaliable(selectedIds)) {
			throw new BusinessValidationException("无法定位需要操作的数据！！");
		}
		List<Long> deleteIds = getDeleteIds(selectedIds);
		int count = 0;
		if (isDeleteAble(deleteIds)) {
			try {
				count = serviceTeamMemberDao
						.deleteServiceTeamMemberBaseByIds(selectedIds
								.split(","));
			} catch (Exception e) {
				throw new ServiceValidationException("删除服务队伍成员信息失败", e);
			}
			// for (Long deleteId : deleteIds) {
			// if (null != getTeamMemberBaseById(deleteId)) {
			// int deleteCount =
			// serviceTeamMemberDao.deleteServiceTeamMemberBase(deleteId);
			// count += deleteCount;
			// }
			// }
		} else {
			throw new BusinessValidationException(
					"请清空服务记录以及该服务成员所在团队记录，再删除服务成员！");
		}
		return count;

	}

	/**
	 * 是否可以删除
	 * 
	 * @param deleteIds
	 * @return true 可以 false 不可以
	 */
	private boolean isDeleteAble(List<Long> deleteIds) {
		boolean isDeleteAble = true;
		for (Long id : deleteIds) {
			if (!serviceTeamMemberDao.isDeleteAbleForTeamMember(id)
					|| (serviceRecordService
							.countServiceRecordsForTeamMember(id) > 0)) {
				isDeleteAble = false;
			}
		}
		return isDeleteAble;
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
			deleteIds.add(Long.parseLong(deleteIdStr));
		}
		return deleteIds;
	}

	@Override
	public ServiceTeamMemberVo getTeamMemberBaseById(Long id) {
		try {
			return serviceTeamMemberDao.getServiceTeamMemberBaseById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceTeamMemberServiceImpl的getTeamMemberBaseById方法出现异常，原因：",
					"根据ID获取服务成员出现错误", e);
		}
	}

	@Override
	public ServiceTeamMemberVo getServiceTeamMemberDetailsById(Long id) {
		try {
			return serviceTeamMemberDao.getServiceTeamMemberDetailsById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceTeamMemberServiceImpl的getServiceTeamMemberDetailsById方法出现异常，原因：",
					"根据ID获取服务成员出现错误", e);
		}
	}

	@Override
	public PageInfo<ServiceTeamMemberVo> findServiceTeamMemberBases(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		try {
			validateSearchParameter(serviceTeamMemberVo);

			serviceTeamMemberVo.setOrg((organizationDubboService
					.getSimpleOrgById(serviceTeamMemberVo.getOrg().getId())));
			return serviceTeamMemberDao.findServiceTeamMemberVoPage(
					serviceTeamMemberVo, pageNum, pageSize, sidx, sord);

		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceTeamMemberServiceImpl的findServiceTeamMemberBases方法出现异常，原因：",
					"服务成员列表信息出现错误", e);
		}
	}

	@Override
	public PageInfo<ServiceTeamMemberVo> searchServiceTeamMemberBases(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		try {
			validateSearchParameter(serviceTeamMemberVo);

			serviceTeamMemberVo.setOrg((organizationDubboService
					.getSimpleOrgById(serviceTeamMemberVo.getOrg().getId())));
			return serviceTeamMemberDao.findServiceTeamMemberVoPage(
					serviceTeamMemberVo, pageNum, pageSize, sidx, sord);

		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceTeamMemberServiceImpl的searchServiceTeamMemberBases方法出现异常，原因：",
					"服务成员高级查询信息出现错误", e);
		}
	}

	/**
	 * 填充属性
	 * 
	 * @param serviceTeam
	 */
	private void autoFilled(ServiceTeamMemberBase serviceTeamMemberBase) {
		autoFillOrganizationInternalCode(serviceTeamMemberBase);
		autoFillChinesePinyin(serviceTeamMemberBase);
	}

	/**
	 * 填充组织机构编码
	 * 
	 * @param serviceTeam
	 */
	private void autoFillOrganizationInternalCode(
			ServiceTeamMemberBase serviceTeamMemberBase) {
		Organization org = organizationDubboService
				.getSimpleOrgById(serviceTeamMemberBase.getOrg().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		serviceTeamMemberBase.setOrgInternalCode(org.getOrgInternalCode());
	}

	@Override
	public PageInfo<ServiceTeamMemberVo> findServiceTeamMemberVoPageInTeam(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		PageInfo<ServiceTeamMemberVo> pageInfo = new PageInfo<ServiceTeamMemberVo>();
		try {

			validateSearchParameter(serviceTeamMemberVo);
			if (serviceTeamMemberVo.getTeamId() != null) {
				serviceTeamMemberVo
						.setOrg((organizationDubboService
								.getSimpleOrgById(serviceTeamMemberVo.getOrg()
										.getId())));
				pageInfo = serviceTeamMemberDao.findServiceTeamMemberVoPage(
						serviceTeamMemberVo, pageNum, pageSize, sidx, sord);
				if (pageInfo.getResult() != null) {
					for (ServiceTeamMemberVo vo : pageInfo.getResult()) {

						vo.setInternalId(organizationDubboService
								.getFullOrgById(vo.getOrg().getId())
								.getOrgLevel().getInternalId());
					}
				}
			}
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceTeamMemberServiceImpl的findMaintenanceTeam方法出现异常，原因：",
					"维护队员查询列表出现错误", e);
		}
	}

	@Override
	public PageInfo<ServiceTeamMemberVo> findServiceTeamMemberVoPageNotInTeam(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		try {
			validateSearchParameter(serviceTeamMemberVo);

			if (null == serviceTeamMemberVo.getTeamId()) {
				return new PageInfo<ServiceTeamMemberVo>();
			}

			serviceTeamMemberVo.setOrg((organizationDubboService
					.getSimpleOrgById(serviceTeamMemberVo.getOrg().getId())));

			return serviceTeamMemberDao.findServiceTeamMemberVoPageNotInTeam(
					serviceTeamMemberVo, pageNum, pageSize, sidx, sord);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceTeamMemberServiceImpl的findNoMaintenanceTeam方法出现异常，原因：",
					"维护队员查询列表出现错误", e);
		}
	}

	/**
	 * 填充拼音
	 * 
	 * @param serviceTeam
	 */
	private void autoFillChinesePinyin(
			ServiceTeamMemberBase serviceTeamMemberBase) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(serviceTeamMemberBase.getName());
		serviceTeamMemberBase.setSimplePinyin(pinyin.get("simplePinyin"));
		serviceTeamMemberBase.setFullPinyin(pinyin.get("fullPinyin"));
	}

	@Override
	public int reMoveTeamAndMemberRelation(
			ServiceTeamMemberVo serviceTeamMemberVo) {
		if (serviceTeamMemberVo == null
				|| serviceTeamMemberVo.getMemberId() == null) {
			throw new BusinessValidationException("无法定位要移除的队员");
		} else {
			if (findServiceObjectInServiceTeam(serviceTeamMemberVo)) {
				return HASOBJECTS;
			} else if (findServiceRecordsInServiceTeam(serviceTeamMemberVo)) {
				return HASRECORDS;
			} else {
				return serviceTeamMemberDao
						.reMoveTeamAndMemberRelation(serviceTeamMemberVo
								.getMemberId());
			}
		}
	}

	@Override
	public int updateServiceTeamMemberOnDuty(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer count) {
		if (serviceTeamMemberVo == null
				|| serviceTeamMemberVo.getMemberId() == null
				|| serviceTeamMemberVo.getOnDuty() == null) {
			throw new BusinessValidationException("无法定位要移除的队员");
		} else {
			if (count > 0) {
				serviceMemberWithObjectService.updateServiceTeamHasObjects(
						serviceTeamMemberVo.getTeamId(), serviceTeamMemberVo
								.getMemberId(), Constants.OnDudy.UNDUDY);
			}
			return serviceTeamMemberDao
					.updateServiceTeamMemberOnDuty(serviceTeamMemberVo);
		}
	}

	@Override
	public PageInfo<ServiceTeamMemberVo> findServiceMemberFromTeams(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		try {
			validateSearchParameter(serviceTeamMemberVo);

			serviceTeamMemberVo.setOrg((organizationDubboService
					.getSimpleOrgById(serviceTeamMemberVo.getOrg().getId())));
			return serviceTeamMemberDao.findServiceMemberFromTeams(
					serviceTeamMemberVo, pageNum, pageSize, sidx, sord);

		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceTeamMemberServiceImpl的findServiceMemberFromTeams方法出现异常，原因：",
					"服务成员列表信息出现错误", e);
		}
	}

	@Override
	public List<AutoCompleteData> findServiceMembersForServiceRecord(
			ServiceTeamMemberVo serviceTeamMemberVo) {
		List<AutoCompleteData> result = new ArrayList<AutoCompleteData>();
		Organization org = organizationDubboService
				.getSimpleOrgById(serviceTeamMemberVo.getOrg().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		serviceTeamMemberVo.setOrg(org);
		List<ServiceTeamMemberVo> serviceTeamMembers = serviceTeamMemberDao
				.findServiceMembersForServiceRecord(serviceTeamMemberVo);
		if (serviceTeamMembers == null || serviceTeamMembers.size() == 0) {
			return new ArrayList<AutoCompleteData>();
		} else {
			for (ServiceTeamMemberVo member : serviceTeamMembers) {
				AutoCompleteData data = new AutoCompleteData();
				data.setLabel(member.getName());
				data.setValue(member.getMemberId() + "-" + member.getName());
				// data.setDesc(member.getTeamName());
				result.add(data);
			}
		}
		return result;
	}

	@Override
	public boolean isDeleteAbleForTeam(Long id) {
		if (null == id || 0 == id) {
			throw new BusinessValidationException("无法定位该团队！！");
		}
		return serviceTeamMemberDao.isDeleteAbleForTeam(id);
	}

	@Override
	public boolean isDeleteAbleForTeamMember(Long id) {
		if (null == id || 0 == id) {
			throw new BusinessValidationException("无法定位该成员！！");
		}
		return serviceTeamMemberDao.isDeleteAbleForTeamMember(id);
	}

	/**
	 * 查找成员关联的记录是否和团队关联
	 */
	public boolean findServiceRecordsInServiceTeam(
			ServiceTeamMemberVo serviceTeamMemberVo) {
		List<Integer> recordIds = serviceRecordRelyMemberDao
				.findServiceRecordsInServiceTeam(serviceTeamMemberVo
						.getMemberId());
		boolean hasRely = false;
		if (recordIds != null) {
			for (Integer recordId : recordIds) {
				if (serviceRecordDao.findServiceTeamInServiceRecords(new Long(
						recordId)) >= 1) {
					hasRely = true;
					break;
				}
			}
		}
		return hasRely;
	}

	/**
	 * 查找成员关联的对象是否和团队关联
	 * 
	 * @return true 有关联，false 无关联
	 */
	public boolean findServiceObjectInServiceTeam(
			ServiceTeamMemberVo serviceTeamMemberVo) {
		return serviceTeamHasObjectsDao
				.findServiceObjectInServiceTeam(serviceTeamMemberVo) >= 1;
	}

	@Override
	public PageInfo<ServiceMemberVo> findServiceMembersByServiceMemberVo(
			ServiceMemberVo serviceMemberVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		try {
			// validateSearchParameter(serviceMemberVo);
			serviceMemberVo.setSortField(sidx);
			serviceMemberVo.setOrder(sord);
			// 判断是否是单位场所（总类别），重点场所（所有类别），如果是，查询所有的类型类别
			if (serviceMemberVo.getObjectType() != null
					&& !"".equals(serviceMemberVo.getObjectType())
					&& ModulTypes.BUSSINESS_AND_SIZED
							.containsKey(serviceMemberVo.getObjectType())) {
				serviceMemberVo
						.setObjectTypeList(ModulTypes.allCompanyPlaceMapKey);
			} else {
				List<String> objectTypeList = new ArrayList<String>();
				objectTypeList.add(serviceMemberVo.getObjectType());
				serviceMemberVo.setObjectTypeList(objectTypeList);
			}
			return serviceTeamMemberDao.findServiceMembersByServiceMemberVo(
					serviceMemberVo, pageNum, pageSize, sidx, sord);

		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceTeamMemberServiceImpl的findServiceMembersByServiceMemberVo方法出现异常，原因：",
					"服务成员列表信息出现错误", e);
		}
	}

	@Override
	public PageInfo<ServiceTeamMemberVo> findServiceTeamMemberBasesPageList(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer page,
			Integer rows, String sidx, String sord) {
		Organization org = organizationDubboService
				.getSimpleOrgById(serviceTeamMemberVo.getOrg().getId());
		serviceTeamMemberVo.setOrg(org);
		serviceTeamMemberVo.setSortField(sidx);
		serviceTeamMemberVo.setOrder(sord);
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		return serviceTeamMemberDao.findServiceTeamMemberBasesPageList(
				serviceTeamMemberVo, page, rows, sidx, sord);
	}

	@Override
	public List<ServiceTeamMemberVo> searchServiceTeamMemberForExport(
			ServiceTeamMemberVo serviceTeamMemberCondition, Integer page,
			Integer rows, String sidx, String sord) {
		serviceTeamMemberCondition.setSortField(sidx);
		serviceTeamMemberCondition.setOrder(sord);
		List<ServiceTeamMemberVo> list = serviceTeamMemberDao
				.searchServiceTeamMemberForExport(serviceTeamMemberCondition,
						page, rows, sidx, sord);
		return list;
	}

	@Override
	public String[][] getExportPopertyArray() {

		return SpecialGroupsContext.getServiceTeamMemberPropertyArraySlf();

	}

	@Override
	public List<ServiceTeamMemberVo> getServiceTeamMemberDetailsByBaseId(
			Long baseId) {

		return serviceTeamMemberDao.getServiceTeamMemberDetailsByBaseId(baseId);
	}

	@Override
	public PageInfo<ServiceTeamMemberVo> findSameMembersByNameAndMobile(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer page,
			Integer rows, String sidx, String sord) {
		serviceTeamMemberVo.setSortField(sidx);
		serviceTeamMemberVo.setOrder(sord);
		Organization org = organizationDubboService
				.getSimpleOrgById(serviceTeamMemberVo.getOrg().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		serviceTeamMemberVo.setOrg(org);
		return serviceTeamMemberDao.findSameMembersByNameAndMobile(
				serviceTeamMemberVo, page, rows);
	}

	@Override
	public List<ServiceMemberInTeamVo> getServiceMemberInTeamVoByMemberId(
			Long memberId) {
		List<ServiceMemberInTeamVo> members = new ArrayList<ServiceMemberInTeamVo>();
		List<ServiceMemberInTeamVo> list = serviceTeamMemberDao
				.getServiceMemberInTeamVoByMemberId(memberId);
		// 去除注销的数据
		for (ServiceMemberInTeamVo vo : list) {
			if (!Constants.TeamLogout.LOGOUT.equals(serviceTeamService
					.getServiceTeamById(vo.getTeamId()).getLogOut())) {
				members.add(vo);
			}
		}
		return members;
	}

	/**
	 * 此处selectedIds为之前移除的成员Id，用于之后判断是否需要合并，在此之列的不合并
	 * serviceTeamMemberVo用于查询（记录了最原始的name，id和手机） serviceTeamMemberBase为修改后的标准
	 */
	@Override
	public void combineServiceTeamMembers(
			ServiceTeamMemberBase serviceTeamMemberBase,
			ServiceTeamMemberVo serviceTeamMemberVo, String selectedIds) {
		List<Long> notCombineIds = getDeleteIds(selectedIds);
		Organization org = organizationDubboService
				.getSimpleOrgById(serviceTeamMemberVo.getOrg().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		serviceTeamMemberVo.setOrg(org);
		List<ServiceTeamMemberVo> combineMembers = serviceTeamMemberDao
				.findSameMembersByNameAndMobile(serviceTeamMemberVo, null, null)
				.getResult();
		// 判断是否需要合并，需要则查出该基础信息对应的成员信息列表，并将所有这些成员的baseId统一为标准的baseId,删除无用的base数据
		for (ServiceTeamMemberVo combineMember : combineMembers) {
			if (needCombined(combineMember, notCombineIds)) {
				List<ServiceTeamMemberVo> teamMembers = serviceTeamMemberDao
						.findServiceMembersByBaseId(combineMember.getBaseId());
				// 团队成员数据的baseId统一操作
				for (ServiceTeamMemberVo teamMember : teamMembers) {
					teamMember.setBaseId(serviceTeamMemberVo.getBaseId());
					serviceTeamMemberDao.combine(teamMember);
				}
				serviceTeamMemberDao.deleteServiceTeamMemberBase(combineMember
						.getBaseId());
			}
		}
		serviceTeamMemberBase.setId(serviceTeamMemberVo.getBaseId());
		serviceTeamMemberDao.updateServiceTeamMemberBase(serviceTeamMemberBase);
		// 完毕后根据标准的baseId查出所有重复成员并开始处理关联关系
		updateMemberRelationship(serviceTeamMemberDao
				.findServiceMembersByBaseId(serviceTeamMemberVo.getBaseId()),
				serviceTeamMemberVo.getBaseId());
	}

	/** 处理成员与团队、记录、对象之间的关联关系 */
	private void updateMemberRelationship(
			List<ServiceTeamMemberVo> teamMembers, Long standardId) {
		Map<Long, List<ServiceTeamMemberVo>> teamMembersMap = new HashMap<Long, List<ServiceTeamMemberVo>>();
		// 以团队id为key团队内的重复成员列表为value构建map对象
		for (ServiceTeamMemberVo teamMember : teamMembers) {
			/*
			 * teamMember.setPosition(propertyDictService
			 * .getPropertyDictById(teamMember.getPosition().getId()));
			 */
			Long teamId = teamMember.getTeamId();
			List<ServiceTeamMemberVo> list = new ArrayList<ServiceTeamMemberVo>();
			if (teamMembersMap.get(teamId) != null) {
				list = teamMembersMap.get(teamMember.getTeamId());
			}
			list.add(teamMember);
			teamMembersMap.put(teamId, list);
		}

		for (Long teamId : teamMembersMap.keySet()) {
			// 获得一个团队内所有的重复成员
			List<ServiceTeamMemberVo> list = teamMembersMap.get(teamId);
			// 处理关联关系
			updateMemberAndRecordRelationship(list);
			updateMemberAndObjectRelationship(list);
			updateMemberAndTeamRelationship(list);
		}
	}

	/** 处理成员与团队之间的关联关系 */
	private void updateMemberAndTeamRelationship(List<ServiceTeamMemberVo> list) {
		ServiceTeamHasObjects serviceTeamHasObjects = new ServiceTeamHasObjects();
		for (int i = 1; i < list.size(); i++) {
			serviceTeamMemberDao.reMoveTeamAndMemberRelation(list.get(i)
					.getMemberId());
			serviceTeamHasObjects.setMemberId(list.get(i).getMemberId());
			serviceTeamHasObjectsDao
					.deleteServiceTeamHasObjects(serviceTeamHasObjects);
		}
		ServiceTeamMemberVo serviceTeamMemberVo = list.get(0);
		serviceTeamMemberVo.setPosition(getPosition(list));
		serviceTeamMemberVo.setOnDuty(getOnDutyInTeam(list));
		serviceTeamMemberDao
				.updateServiceTeamMemberOnDutyAndPosition(serviceTeamMemberVo);
	}

	/** 处理成员与对象之间的关联关系 */
	private void updateMemberAndObjectRelationship(
			List<ServiceTeamMemberVo> list) {
		Map<String, Map<Long, List<ServiceMemberWithObjectVo>>> objectTypeAndIds = getObjectTypeAndIds(list);
		Long standardMemberId = list.get(0).getMemberId();
		// 循环map依次对各个类型各个对象进行去重处理
		for (String objectType : objectTypeAndIds.keySet()) {
			// 循环map依次对该类型下的各个对象进行去重处理
			for (Long objectId : objectTypeAndIds.get(objectType).keySet()) {
				// 循环该对象下的全部含重复成员的关联关系
				for (ServiceMemberWithObjectVo memberWithObject : objectTypeAndIds
						.get(objectType).get(objectId)) {
					if (memberWithObject.getMemberId().intValue() != standardMemberId
							.intValue()) {
						// 根据memberId、objectType和objectId以对象为单位逐个修改每个对象里的全部重复成员的memberId
						memberWithObject.setStandardMemberId(standardMemberId);
						serviceMemberWithObjectDao
								.updateRepeatData(memberWithObject);
					}

				}
				// 经过修改后的该对象中如果存在多个相同的memberId则删除其余重复关联关系
				List<ServiceMemberWithObjectVo> repeatMembers = getRepeatMembersFromObject(
						serviceMemberWithObjectDao
								.findServiceMemberWithObjectsByTypeAndId(
										objectType, objectId), standardMemberId);
				for (int i = 1; i < repeatMembers.size(); i++) {
					serviceMemberWithObjectDao
							.deleteServiceMemberWithObject(repeatMembers.get(i)
									.getId());
				}
				// 修改余下的一个关联关系中在职情况和职位的字段属性
				Long onDuty = getOnDutyInObject(objectTypeAndIds
						.get(objectType).get(objectId));
				ServiceMemberWithObjectVo vo = repeatMembers.get(0);
				vo.setOnDuty(onDuty);
				serviceMemberWithObjectDao.updateOnDuty(vo);
			}
		}
	}

	/** 处理成员与记录之间的关联关系 */
	private void updateMemberAndRecordRelationship(
			List<ServiceTeamMemberVo> list) {
		List<Integer> recordIds = new ArrayList<Integer>();
		ServiceRecord serviceRecord = new ServiceRecord();
		Long standardMemberId = list.get(0).getMemberId();
		recordIds = getRecordIds(list);
		for (Integer recordId : recordIds) {
			// 1.根据memberId和recordId以记录为单位逐个修改每个记录里的全部重复成员的memberId
			for (ServiceTeamMemberVo teamMember : list) {
				if (teamMember.getMemberId().intValue() != standardMemberId
						.intValue()) {
					ServiceRecordRelyMember recordRelyMember = new ServiceRecordRelyMember();
					recordRelyMember.setRecordId(Long.valueOf(recordId));
					recordRelyMember.setMemberId(teamMember.getMemberId());
					recordRelyMember.setStandardMemberId(standardMemberId);
					serviceRecordRelyMemberDao
							.updateRepeatData(recordRelyMember);
				}

			}
			// 2.经过修改后的该记录中如果存在多个相同的memberId则删除其余重复关联关系
			List<ServiceRecordRelyMember> repeatMembers = getRepeatMembersFromRecord(
					serviceRecordRelyMemberDao.findServiceMembers(Long
							.valueOf(recordId)), standardMemberId);
			for (int i = 1; i < repeatMembers.size(); i++) {
				serviceRecordRelyMemberDao
						.deleteServiceRecordRelyMembersById(repeatMembers
								.get(i).getId());
			}
			// 3.更新记录中的服务所属人字段
			String member = getNewMemberInRecord(serviceRecordRelyMemberDao
					.findServiceMembers(Long.valueOf(recordId)));
			serviceRecord.setId(Long.valueOf(recordId));
			serviceRecord.setServiceMembers(member);
			serviceRecordDao.updateMemberInRecord(serviceRecord);
		}

	}

	/** 获得以对象类型为key该类型下的对象列表为value的map对象 */
	private Map<String, Map<Long, List<ServiceMemberWithObjectVo>>> getObjectTypeAndIds(
			List<ServiceTeamMemberVo> list) {
		Map<String, Map<Long, List<ServiceMemberWithObjectVo>>> typeAndIds = new HashMap<String, Map<Long, List<ServiceMemberWithObjectVo>>>();
		for (ServiceTeamMemberVo teamMember : list) {
			// 查出每个对象所涉及的对象（其实是对象和成员的关联关系）
			List<ServiceMemberWithObjectVo> memberHasObjects = serviceMemberWithObjectService
					.findServiceMemberWithObjectsByMemberId(teamMember
							.getMemberId());
			// 循环查出的对象列表中该添加到对应MAP中的值
			for (ServiceMemberWithObjectVo serviceMemberWithObjectVo : memberHasObjects) {
				Map<Long, List<ServiceMemberWithObjectVo>> voMap = new HashMap<Long, List<ServiceMemberWithObjectVo>>();
				List<ServiceMemberWithObjectVo> voList = new ArrayList<ServiceMemberWithObjectVo>();
				if (typeAndIds.get(serviceMemberWithObjectVo.getObjectType()) != null) {
					voMap = typeAndIds.get(serviceMemberWithObjectVo
							.getObjectType());
					if (voMap.get(serviceMemberWithObjectVo.getObjectId()) != null) {
						voList = voMap.get(serviceMemberWithObjectVo
								.getObjectId());
					}

				}
				voList.add(serviceMemberWithObjectVo);
				voMap.put(serviceMemberWithObjectVo.getObjectId(), voList);
				typeAndIds
						.put(serviceMemberWithObjectVo.getObjectType(), voMap);
			}
		}
		return typeAndIds;
	}

	/** 获得服务记录的新记录所属人字段值 */
	private String getNewMemberInRecord(
			List<ServiceRecordRelyMember> membersInRecords) {
		String member = "";
		for (ServiceRecordRelyMember membersInRecord : membersInRecords) {
			member += "," + membersInRecord.getMemberName();
		}
		member = member.substring(1);
		return member;
	}

	/** 获得含有重复成员的记录的成员列表 */
	private List<ServiceRecordRelyMember> getRepeatMembersFromRecord(
			List<ServiceRecordRelyMember> memberListInRecord,
			Long standardMemberId) {
		List<ServiceRecordRelyMember> repeatMembers = new ArrayList<ServiceRecordRelyMember>();
		for (ServiceRecordRelyMember member : memberListInRecord) {
			if (member.getMemberId().intValue() == standardMemberId.intValue()) {
				repeatMembers.add(member);
			}
		}
		return repeatMembers;
	}

	/** 获得含有重复成员的对象的成员列表 */
	private List<ServiceMemberWithObjectVo> getRepeatMembersFromObject(
			List<ServiceMemberWithObjectVo> memberListInObject,
			Long standardMemberId) {
		List<ServiceMemberWithObjectVo> repeatMembers = new ArrayList<ServiceMemberWithObjectVo>();
		for (ServiceMemberWithObjectVo member : memberListInObject) {
			if (member.getMemberId().intValue() == standardMemberId.intValue()) {
				repeatMembers.add(member);
			}
		}
		return repeatMembers;
	}

	/** 获得同一个团队的这些重复服务成员所涉及的全部recordId */
	private List<Integer> getRecordIds(List<ServiceTeamMemberVo> list) {
		List<Integer> minRecordIds = null;
		List<Integer> recordIds = serviceRecordRelyMemberDao
				.findServiceRecordsInServiceTeam(list.get(0).getMemberId());
		for (ServiceTeamMemberVo teamMember : list) {
			minRecordIds = serviceRecordRelyMemberDao
					.findServiceRecordsInServiceTeam(teamMember.getMemberId());
			if (minRecordIds != null && minRecordIds.size() != 0) {
				for (int i = 0; i < minRecordIds.size(); i++) {
					if (recordIds.indexOf(minRecordIds.get(i)) == -1) {
						recordIds.add(minRecordIds.get(i));
					}
				}
			}
		}
		return recordIds;
	}

	/** 获得需要合并的同团队成员中的最大职位id（将成员职务统一为最大的职务） */
	private PropertyDict getPosition(List<ServiceTeamMemberVo> list) {
		PropertyDict position = new PropertyDict();
		position = list.get(0).getPosition();
		for (ServiceTeamMemberVo teamMember : list) {
			if (teamMember.getPosition() == null) {
				position = propertyDictService
						.getPropertyDictByDomainName("队员");
			} else if (teamMember.getPosition().getInternalId() < position
					.getInternalId()) {
				position = teamMember.getPosition();
			}
		}
		return position;
	}

	/** 获得需要合并的同团队成员的在职情况（有一人在职则统一为在职） */
	private Long getOnDutyInTeam(List<ServiceTeamMemberVo> list) {
		Long onDuty = Constants.OnDudy.UNDUDY;
		for (ServiceTeamMemberVo teamMember : list) {
			if (teamMember.getOnDuty() != null) {
				if (teamMember.getOnDuty().intValue() == Constants.OnDudy.ONDUDY
						.intValue()) {
					onDuty = Constants.OnDudy.ONDUDY;
				}
			}
		}
		return onDuty;
	}

	/** 获得需要合并的同团队成员的在职情况（有一人在职则统一为在职） */
	private Long getOnDutyInObject(List<ServiceMemberWithObjectVo> list) {
		Long onDuty = Constants.OnDudy.UNDUDY;
		for (ServiceMemberWithObjectVo serviceMemberWithObjectVo : list) {
			if (serviceMemberWithObjectVo.getOnDuty() != null) {
				if (serviceMemberWithObjectVo.getOnDuty().intValue() == Constants.OnDudy.ONDUDY
						.intValue()) {
					onDuty = Constants.OnDudy.ONDUDY;
				}
			}
		}
		return onDuty;
	}

	/** 根据传入的移除ID判断循环中该服务成员是否需要被合并true:需要 */
	private boolean needCombined(ServiceTeamMemberVo vo,
			List<Long> notCombineIds) {
		for (Long id : notCombineIds) {
			if (id.intValue() == vo.getBaseId().intValue()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void changeOrg(Long orgId, String selectedIds) {
		List<Long> baseIds = getDeleteIds(selectedIds);
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		for (Long baseId : baseIds) {
			ServiceTeamMemberBase serviceTeamMemberBase = new ServiceTeamMemberBase();
			serviceTeamMemberBase.setId(baseId);
			serviceTeamMemberBase.setOrg(org);
			serviceTeamMemberDao.changeOrg(serviceTeamMemberBase);
		}

	}

	/************************ 迁移合并方法 **************************/
	/**
	 * 
	 * @Title: queryServiceTeamHasObjectsForList
	 * @Description: TODO查询重复服务团队关系
	 * @param @param map
	 * @param @return 设定文件
	 * @return List<ServiceTeamHasObjects> 返回类型
	 * @author wanggz
	 * @date 2014-10-28 上午10:31:28
	 */
	public List<ServiceTeamHasObjects> queryServiceTeamHasObjectsForList(Map map) {
		try {
			return serviceTeamHasObjectsDao
					.queryServiceTeamHasObjectsForList(map);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceTeamMemberServiceImpl的queryServiceTeamHasObjectsForList方法出现异常，原因：",
					"查询重复服务团队关系出错", e);
		}
	}

	@Override
	public PageInfo<ServiceTeamMemberVo> findServiceMembersFromTeams(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		try {
			validateSearchParameter(serviceTeamMemberVo);

			serviceTeamMemberVo.setOrg((organizationDubboService
					.getSimpleOrgById(serviceTeamMemberVo.getOrg().getId())));
			return serviceTeamMemberDao.findServiceMembersFromTeams(
					serviceTeamMemberVo, pageNum, pageSize, sidx, sord);

		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceTeamMemberServiceImpl的findServiceMembersFromTeams方法出现异常，原因：",
					"服务成员列表信息出现错误", e);
		}
	}

}
