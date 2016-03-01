package com.tianque.plugin.serviceTeam.serviceMemberWithObject.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.dao.ServiceMemberWithObjectDao;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.domain.ServiceMemberWithObject;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.vo.ServiceMemberWithObjectVo;
import com.tianque.plugin.serviceTeam.serviceTeamHasObjects.dao.ServiceTeamHasObjectsDao;
import com.tianque.plugin.serviceTeam.serviceTeamHasObjects.domain.ServiceTeamHasObjects;
import com.tianque.plugin.serviceTeam.serviceTeamMember.service.ServiceTeamMemberService;
import com.tianque.plugin.serviceTeam.serviceTeamMember.validator.ServiceTeamMemberValidatorImpl;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceTeamMemberVo;
import com.tianque.plugin.serviceTeam.util.Constants;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 服务成员业务处理实现类
 * 
 * @author tengjia
 */
@Service("serviceMemberWithObjectService")
@Transactional
public class ServiceMemberWithObjectServiceImpl extends AbstractBaseService
		implements ServiceMemberWithObjectService {

	@Qualifier("serviceTeamMemberValidator")
	private ServiceTeamMemberValidatorImpl serviceTeamMemberValidator;
	@Autowired
	private ServiceMemberWithObjectDao serviceMemberWithObjectDao;
	@Autowired
	private ServiceTeamHasObjectsDao serviceTeamHasObjectsDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ServiceTeamMemberService serviceTeamMemberService;
	/*
	 * @Autowired private ServiceTeamHasObjectsDao serviceTeamhasObjectDao;
	 */

	// 成员已离职
	private final static int NOTONDUTY = -1;

	@Override
	public ServiceMemberWithObjectVo addObjectAndMemberRelation(
			ServiceMemberWithObject serviceObjectMember) {
		try {

			ServiceMemberWithObjectVo vo = serviceMemberWithObjectDao
					.addObjectAndMemberRelation(serviceObjectMember);
			vo.setTeamId(serviceObjectMember.getTeamId());

			ServiceTeamHasObjects serviceTeamHasObjects = autoAttribute(vo);
			serviceTeamHasObjectsDao
					.addObjectAndTeamRelation(serviceTeamHasObjects);

			return vo;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceMemberWithObjectServiceImpl的addObjectAndMemberRelation方法出现异常，原因：",
					"新增服务成员出现错误", e);
		}
	}

	@Override
	public ServiceMemberWithObjectVo getServiceMemberWithObjectById(Long id) {
		try {
			return serviceMemberWithObjectDao
					.getServiceMemberWithObjectById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceMemberWithObjectServiceImpl的getServiceMemberWithObjectById方法出现异常，原因：",
					"根据ID获取服务成员和服务对象的信息出现错误", e);
		}
	}

	@Override
	public int deleteServiceMemberWithObjectById(String selectedIds) {
		if (selectedIds == null || selectedIds.equals("")) {
			throw new BusinessValidationException("无法定位需要操作的数据！！");
		}
		List<Long> deleteIds = getDeleteIds(selectedIds);
		int count = 0;
		for (Long deleteId : deleteIds) {
			ServiceMemberWithObjectVo vo = serviceMemberWithObjectDao
					.getServiceMemberWithObjectById(deleteId);

			ServiceTeamHasObjects serviceTeamHasObjects = autoAttribute(vo);

			serviceTeamHasObjectsDao
					.deleteServiceTeamHasObjects(serviceTeamHasObjects);

			if (null != getServiceMemberWithObjectById(deleteId)) {
				int deleteCount = serviceMemberWithObjectDao
						.deleteServiceMemberWithObject(deleteId);
				count += deleteCount;
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

	@Override
	public int updateServiceMemberWithObject(
			ServiceMemberWithObject serviceObjectMember) {
		try {

			ServiceMemberWithObjectVo vo = serviceMemberWithObjectDao
					.getServiceMemberWithObjectById(serviceObjectMember.getId());

			ServiceTeamMemberVo serviceTeamMemberVo = serviceTeamMemberService
					.getServiceTeamMemberDetailsById(serviceObjectMember
							.getMemberId());

			Long onDuty = serviceTeamMemberVo.getOnDuty();
			if (Constants.OnDudy.ONDUDY.equals(onDuty)) {
				ServiceTeamHasObjects serviceTeamHasObjects = autoAttribute(vo);
				serviceTeamHasObjects
						.setOnDuty(serviceObjectMember.getOnDuty());
				serviceTeamHasObjectsDao
						.updateServiceTeamHasObjects(serviceTeamHasObjects);
				return serviceMemberWithObjectDao
						.updateServiceMemberWithObject(serviceObjectMember);
			} else {
				return NOTONDUTY;
			}

		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceMemberWithObjectServiceImpl的updateServiceMemberWithObject方法出现异常，原因：",
					e.getMessage(), e);
		}
	}

	@Override
	public void updateServiceTeamHasObjects(Long teamId, Long memberId,
			Long onDuty) {
		try {
			ServiceMemberWithObject serviceObjectMember = new ServiceMemberWithObject();
			serviceObjectMember.setTeamId(teamId);
			serviceObjectMember.setMemberId(memberId);
			serviceObjectMember.setOnDuty(onDuty);

			serviceMemberWithObjectDao
					.updateServiceMemberHasObject(serviceObjectMember);
			ServiceTeamHasObjects serviceTeamHasObjects = new ServiceTeamHasObjects();
			serviceTeamHasObjects.setTeamId(teamId);
			serviceTeamHasObjects.setMemberId(memberId);
			serviceTeamHasObjects.setOnDuty(onDuty);
			serviceTeamHasObjectsDao
					.updateServiceTeamHasObjects(serviceTeamHasObjects);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceMemberWithObjectServiceImpl的updateServiceMemberWithObject方法出现异常，原因：",
					"修改服务成员和对象关联出现错误", e);
		}
	}

	/**
	 * 设置属性值 return ServiceTeamHasObjects
	 */
	private ServiceTeamHasObjects autoAttribute(
			ServiceMemberWithObjectVo serviceMemberWithObjectVo) {
		ServiceTeamHasObjects serviceTeamHasObjects = new ServiceTeamHasObjects();
		serviceTeamHasObjects.setObjectId(serviceMemberWithObjectVo
				.getObjectId());
		serviceTeamHasObjects.setObjectType(serviceMemberWithObjectVo
				.getObjectType());
		serviceTeamHasObjects.setTeamId(serviceMemberWithObjectVo.getTeamId());
		serviceTeamHasObjects.setMemberId(serviceMemberWithObjectVo
				.getMemberId());
		serviceTeamHasObjects.setObjectLogout(serviceMemberWithObjectVo
				.getObjectLogout());
		return serviceTeamHasObjects;

	}

	@Override
	public ServiceMemberWithObjectVo getServiceMemberWithObjectVoByMemberIdAndObjectTypeAndId(
			Long memberId, Long objectId, String objectType) {
		ServiceMemberWithObjectVo serviceMemberWithObjectVo = new ServiceMemberWithObjectVo();
		serviceMemberWithObjectVo.setMemberId(memberId);
		serviceMemberWithObjectVo.setObjectId(objectId);
		serviceMemberWithObjectVo.setObjectType(objectType);
		return serviceMemberWithObjectDao
				.getServiceMemberWithObjectVoByMemberIdAndObjectTypeAndId(serviceMemberWithObjectVo);
	}

	@Override
	public int deleteServiceObjectFromMember(List<Long> memberIds,
			ServiceMemberWithObject serviceMemberWithObject) {
		int deleteCount = 0;
		for (Long memberId : memberIds) {
			serviceMemberWithObject.setMemberId(memberId);

			ServiceTeamHasObjects serviceTeamHasObjects = new ServiceTeamHasObjects();
			serviceTeamHasObjects.setObjectId(serviceMemberWithObject
					.getObjectId());
			serviceTeamHasObjects.setMemberId(memberId);
			serviceTeamHasObjects.setObjectType(serviceMemberWithObject
					.getObjectType());
			Long teamId = serviceTeamMemberService
					.getServiceTeamMemberDetailsById(memberId).getTeamId();
			serviceTeamHasObjects.setTeamId(teamId);

			serviceMemberWithObjectDao
					.deleteServiceMemberHasObject(serviceMemberWithObject);
			serviceTeamHasObjectsDao
					.deleteServiceTeamHasObjects(serviceTeamHasObjects);
			deleteCount++;
		}
		return deleteCount;
	}

	@Override
	public List<ServiceMemberWithObjectVo> findServiceMemberWithObjectsByMemberId(
			Long memberId) {
		return serviceMemberWithObjectDao
				.findServiceMemberWithObjectsByMemberId(memberId);
	}

	@Override
	public void updateServiceMemberAndTeamHasObjectForMove(String objectType,
			Long oldObjectId, Long newObjectId) {
		if (objectType == null || "".equals(objectType) || oldObjectId == null
				|| newObjectId == null) {
			throw new BusinessValidationException("转移人口时修改服务团队人员与对象关联关系参数错误");
		}
		try {
			serviceTeamHasObjectsDao.updateServiceMemberHasObjectForMove(
					objectType, oldObjectId, newObjectId);
			serviceTeamHasObjectsDao.updateServiceTeamHasObjectForMove(
					objectType, oldObjectId, newObjectId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceMemberWithObjectServiceImpl的updateServiceMemberAndTeamHasObjectForMove方法出现异常，原因：",
					"转移人口时修改服务团队人员与对象关联关系出现错误", e);
		}

	}

	@Override
	public void updateServiceMemberAndTeamHasObjectForMove(String objectType,
			Long oldObjectId, Long newObjectId, String newObjectType) {
		if (objectType == null || "".equals(objectType) || oldObjectId == null
				|| newObjectId == null || newObjectType == null
				|| "".equals(newObjectType)) {
			throw new BusinessValidationException("转移人口时修改服务团队人员与对象关联关系参数错误");
		}
		try {
			serviceTeamHasObjectsDao.updateServiceMemberHasObjectForMove(
					objectType, oldObjectId, newObjectId, newObjectType);
			serviceTeamHasObjectsDao.updateServiceTeamHasObjectForMove(
					objectType, oldObjectId, newObjectId, newObjectType);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceMemberWithObjectServiceImpl的updateServiceMemberAndTeamHasObjectForMove方法出现异常，原因：",
					"转移人口时修改服务团队人员与对象关联关系出现错误", e);
		}

	}

	public List<ServiceMemberWithObject> queryServiceMemberWithObjectForList(
			Map map) {
		try {
			return serviceMemberWithObjectDao
					.queryServiceMemberWithObjectForList(map);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceMemberWithObjectServiceImpl的queryServiceMemberWithObjectForList方法出现异常，原因：",
					"查询重复服务人员出现错误", e);
		}
	}

	@Override
	public void addObjectAndMembersRelation(
			List<ServiceMemberWithObject> serviceObjectMembers, boolean isFilter) {

		if (serviceObjectMembers == null || serviceObjectMembers.size() == 0) {
			throw new BusinessValidationException("为一个或多个服务对象添加一个或多个服务人员参数错误");
		}
		if (isFilter) {
			serviceObjectMembers = filter(serviceObjectMembers);
		}
		if (serviceObjectMembers == null || serviceObjectMembers.size() == 0) {
			throw new BusinessValidationException("添加的服务人员已存在");
		}
		try {
			for (ServiceMemberWithObject serviceObjectMember : serviceObjectMembers) {
				addObjectAndMemberRelation(serviceObjectMember);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceMemberWithObjectServiceImpl的addObjectAndMembersRelation方法出现异常，原因：",
					"为一个或多个服务对象添加一个或多个服务人员", e);
		}
	}

	/*
	 * 根据服务对象的信息 组装对象编号集
	 */
	private List<Long> getObjectId(
			List<ServiceMemberWithObject> serviceObjectMembers) {
		if (serviceObjectMembers == null || serviceObjectMembers.size() == 0) {
			return null;
		}
		List<Long> objectIds = new ArrayList<Long>();
		for (ServiceMemberWithObject serviceMemberWithObject : serviceObjectMembers) {
			objectIds.add(serviceMemberWithObject.getObjectId());
		}
		return objectIds;
	}

	/*
	 * 过滤原有已存在的服务人员
	 */
	private List<ServiceMemberWithObject> filter(
			List<ServiceMemberWithObject> serviceObjectMembers) {

		if (serviceObjectMembers == null || serviceObjectMembers.size() == 0) {
			return null;
		}
		List<Long> objectIds = getObjectId(serviceObjectMembers);
		Map map = new HashMap();
		map.put("objectType", serviceObjectMembers.get(0).getObjectType());
		map.put("objectIds", objectIds);
		List<ServiceMemberWithObjectVo> list = serviceMemberWithObjectDao
				.findServiceMembersWithObjectsByTypeAndId(map);
		if(list ==null||list.size()==0){
		   return serviceObjectMembers;
		}
		List<ServiceMemberWithObject> serviceObjectMembersList = new ArrayList();
		boolean isAdd;
		for (ServiceMemberWithObject serviceMemberWithObject : serviceObjectMembers) {
			isAdd = true;
			for (ServiceMemberWithObjectVo serviceMemberWithObjectVo : list) {
				if (serviceMemberWithObjectVo.getMemberId().intValue() == serviceMemberWithObject
						.getMemberId().intValue()
						&& serviceMemberWithObjectVo.getTeamId().intValue() == serviceMemberWithObject
								.getTeamId().intValue()
						&& serviceMemberWithObjectVo.getObjectId().intValue() == serviceMemberWithObject
								.getObjectId().intValue()) {
					isAdd = false;
				}
			}
			if (isAdd) {
				serviceObjectMembersList.add(serviceMemberWithObject);
			}
		}
		return serviceObjectMembersList;
	}
}
