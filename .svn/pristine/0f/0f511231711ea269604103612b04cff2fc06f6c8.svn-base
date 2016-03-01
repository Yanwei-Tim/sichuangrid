package com.tianque.plugin.serviceTeam.router;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.domain.Organization;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.dao.ServiceMemberWithObjectDao;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.domain.ServiceMemberWithObject;
import com.tianque.plugin.serviceTeam.serviceRecord.dao.ServiceRecordDao;
import com.tianque.plugin.serviceTeam.serviceRecord.dao.ServiceRecordRelyObjectDao;
import com.tianque.plugin.serviceTeam.serviceRecord.vo.ServiceRecordVo;
import com.tianque.plugin.serviceTeam.serviceTeamHasObjects.dao.ServiceTeamHasObjectsDao;
import com.tianque.plugin.serviceTeam.serviceTeamHasObjects.domain.ServiceTeamHasObjects;
import com.tianque.plugin.serviceTeam.serviceTeamManage.dao.ServiceTeamDao;
import com.tianque.plugin.serviceTeam.serviceTeamManage.vo.ServiceTeamVo;
import com.tianque.plugin.serviceTeam.serviceTeamMember.dao.ServiceTeamMemberDao;

@Service("routerService")
@Transactional
public class RouterServiceImpl implements RouterService {
	@Autowired
	private ServiceTeamDao serviceTeamDao;
	@Autowired
	private ServiceRecordDao serviceRecordDao;
	@Autowired
	private ServiceTeamMemberDao serviceTeamMemberDao;
	@Autowired
	private ServiceTeamHasObjectsDao serviceTeamHasObjectsDao;
	@Autowired
	private ServiceMemberWithObjectDao serviceMemberWithObjectDao;
	@Autowired
	private ServiceRecordRelyObjectDao serviceRecordRelyObjectDao;

	@Override
	public Map<String, Integer> getServiceTeamDataForWorkBench(Organization org) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("teamAmount", getTeamAmount(org));
		map.put("memberAmount", getMemberAmount(org));
		map.put("recordAmount", getRecordAmount(org));
		map.put("objectHasRecordAmount", getObjectHasRecordAmount(org));
		map.put("objectAmount", getObjectAmount(org));
		return map;
	}

	/**
	 * 获得团队数量
	 */
	private Integer getTeamAmount(Organization org) {
		ServiceTeamVo serviceTeamVo = new ServiceTeamVo();
		serviceTeamVo.setOrg(org);
		serviceTeamVo.setLogOut(0L);
		return serviceTeamDao.countServiceTeams(serviceTeamVo);
	}

	/**
	 * 获得成员数量
	 */
	private Integer getMemberAmount(Organization org) {
		return serviceTeamMemberDao.getAllServiceTeamMembers(org.getOrgInternalCode());
	}

	/**
	 * 获得服务记录数量
	 */
	private Integer getRecordAmount(Organization org) {
		ServiceRecordVo serviceRecordVo = new ServiceRecordVo();
		serviceRecordVo.setOrganization(org);
		return serviceRecordDao.countServiceRecords(serviceRecordVo);
	}

	/**
	 * 累计服务人次
	 */
	private Integer getObjectHasRecordAmount(Organization org) {
		ServiceRecordVo serviceRecordVo = new ServiceRecordVo();
		serviceRecordVo.setDisplayLevel("allJurisdiction");
		List<ServiceRecordVo> list = serviceRecordDao.getServiceRecordObjects(serviceRecordVo);
		Integer amount = new Integer(0);
		for (ServiceRecordVo vo : list) {
			String str = vo.getServiceObjects();
			String[] strs = str.split(",");
			amount += strs.length;
		}
		return amount;
	}

	/**
	 * 对象数量
	 */
	private Integer getObjectAmount(Organization org) {
		ServiceTeamVo serviceTeamVo = new ServiceTeamVo();
		serviceTeamVo.setOrg(org);
		serviceTeamVo.setDisplayLevel("allJurisdiction");
		return serviceTeamDao.countAllServiceTeamObjects(serviceTeamVo);
	}

	@Override
	public void deleteServiceTeamHasObjectsAndServiceMemberHasObject(String ObjectType,
			Long ObjectId) {
		ServiceMemberWithObject serviceMemberWithObject = new ServiceMemberWithObject();
		serviceMemberWithObject.setObjectId(ObjectId);
		serviceMemberWithObject.setObjectType(ObjectType);

		ServiceTeamHasObjects serviceTeamHasObjects = new ServiceTeamHasObjects();
		serviceTeamHasObjects.setObjectId(ObjectId);
		serviceTeamHasObjects.setObjectType(ObjectType);

		serviceTeamHasObjectsDao.deleteServiceTeamWithObjects(serviceTeamHasObjects);
		serviceMemberWithObjectDao.deleteServiceMemberHasObject(serviceMemberWithObject);
	}

	@Override
	public void updateServiceTeamHasObjectsAndServiceMemberHasObject(String ObjectType,
			Long ObjectId, Long logout) {
		if (logout == 1L) {
			logout = 0L;
		} else if (logout == 0L) {
			logout = 1L;
		}
		ServiceMemberWithObject serviceMemberWithObject = new ServiceMemberWithObject();
		serviceMemberWithObject.setObjectId(ObjectId);
		serviceMemberWithObject.setObjectType(ObjectType);
		serviceMemberWithObject.setObjectLogout(logout);

		ServiceTeamHasObjects serviceTeamHasObjects = new ServiceTeamHasObjects();
		serviceTeamHasObjects.setObjectId(ObjectId);
		serviceTeamHasObjects.setObjectType(ObjectType);
		serviceTeamHasObjects.setObjectLogout(logout);

		serviceTeamHasObjectsDao.updateServiceTeamWithObjects(serviceTeamHasObjects);
		serviceMemberWithObjectDao.updateServiceMemberHasObject(serviceMemberWithObject);

	}

	@Override
	public void setOrgIdAndCardNoOrName(Long orgId, String cardNoOrName, String objectType,
			Long objectId) {
		Map map = getInfoMap(orgId, cardNoOrName, objectType, objectId);
		serviceRecordRelyObjectDao.setOrgIdAndCardNoOrName(map);
	}

	/**根据传入的值组装map*/
	private Map getInfoMap(Long orgId, String cardNoOrName, String objectType, Long objectId) {
		Map map = new HashMap();
		map.put("orgId", orgId);
		map.put("cardNoOrName", cardNoOrName);
		map.put("objectType", objectType);
		map.put("objectId", objectId);
		return map;
	}
}
