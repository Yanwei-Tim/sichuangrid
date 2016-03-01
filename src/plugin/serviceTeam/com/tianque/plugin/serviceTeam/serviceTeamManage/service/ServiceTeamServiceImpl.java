package com.tianque.plugin.serviceTeam.serviceTeamManage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.serviceTeam.serviceRecord.service.ServiceRecordService;
import com.tianque.plugin.serviceTeam.serviceTeamManage.dao.ServiceTeamDao;
import com.tianque.plugin.serviceTeam.serviceTeamManage.domain.ServiceTeam;
import com.tianque.plugin.serviceTeam.serviceTeamManage.validator.ServiceTeamValidatorImpl;
import com.tianque.plugin.serviceTeam.serviceTeamManage.vo.ServiceTeamVo;
import com.tianque.plugin.serviceTeam.serviceTeamMember.service.ServiceTeamMemberService;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceTeamMemberVo;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 服务团队业务处理实现类
 * 
 * @author yangpengdian
 */
@Service("serviceTeamService")
@Transactional
public class ServiceTeamServiceImpl extends AbstractBaseService implements
		ServiceTeamService {

	@Qualifier("serviceTeamValidator")
	@Autowired
	private ServiceTeamValidatorImpl serviceTeamValidator;
	@Autowired
	private ServiceTeamDao serviceTeamDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ServiceTeamMemberService serviceTeamMemberService;
	@Autowired
	private ServiceRecordService serviceRecordService;

	@Override
	public ServiceTeamVo addServiceTeam(ServiceTeam serviceTeam) {
		try {
			autoFilled(serviceTeam);
			serviceTeamValidator(serviceTeam);
			return serviceTeamDao.addServiceTeam(serviceTeam);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceTeamServiceImpl的addServiceTeam方法出现异常，原因：",
					"新增服务团队出现错误", e);
		}
	}

	@Override
	public ServiceTeamVo updateServiceTeam(ServiceTeam serviceTeam) {
		autoFilled(serviceTeam);
		serviceTeamValidator(serviceTeam);
		try {
			return serviceTeamDao.updateServiceTeam(serviceTeam);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceTeamServiceImpl的updateServiceTeam方法出现异常，原因：",
					"修改服务团队出现错误", e);
		}
	}

	@Override
	public int deleteServiceTeam(String selectedIds) {
		if (selectedIds == null || selectedIds.equals("")) {
			throw new BusinessValidationException("无法定位需要操作的数据！！");
		}
		List<Long> deleteIds = getDeleteIds(selectedIds);
		int count = -1;
		try {
			if (isDeleteAble(deleteIds)) {
				count = serviceTeamDao.deleteServiceTeamByIds(selectedIds
						.split(","));
				// count = 0;
				// for (Long deleteId : deleteIds) {
				// if (null != getServiceTeamById(deleteId)) {
				// int deleteCount = serviceTeamDao.deleteServiceTeam(deleteId);
				// count += deleteCount;
				// }
				// }
			}
		} catch (Exception e) {
			throw new ServiceValidationException("删除服务队伍失败", e);
		}
		return count;
	}

	@Override
	public PageInfo<ServiceTeam> findServiceTeams(ServiceTeamVo serviceTeamVo,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		try {
			return serviceTeamDao.findServiceTeams(
					fillArgs(serviceTeamVo, sidx, sord), pageNum, pageSize);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceTeamServiceImpl的findServiceTeam方法出现异常，原因：",
					"服务团队列表信息出现错误", e);
		}
	}

	@Override
	public ServiceTeamVo getServiceTeamById(Long id) {
		try {
			return serviceTeamDao.getServiceTeamById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceTeamServiceImpl的getServiceTeamById方法出现异常，原因：",
					"根据ID获取服务团队出现错误", e);
		}
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
			if (!serviceTeamMemberService.isDeleteAbleForTeam(id)
					|| (serviceRecordService.countServiceRecordsForTeam(id) > 0)) {
				isDeleteAble = false;
			}
		}
		return isDeleteAble;
	}

	@Override
	public void logOutServiceTeam(ServiceTeam serviceTeam) {
		if (null == serviceTeam.getId()) {
			throw new BusinessValidationException("无法定位要解散的团队，操作失败！！");
		}
		serviceTeamDao.logOutServiceTeam(serviceTeam);
	}

	/*
	 * @Override public Integer countServiceTeamByOrgInternalCode(String
	 * orgInternalCode) { return serviceTeamDao
	 * .countServiceTeamByOrgInternalCode(orgInternalCode); }
	 */
	/**
	 * 对象验证
	 * 
	 * @param serviceTeam
	 */
	private void serviceTeamValidator(ServiceTeam serviceTeam) {
		ValidateResult baseDataValidator = serviceTeamValidator
				.validate(serviceTeam);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	/**
	 * 填充属性
	 * 
	 * @param serviceTeam
	 */
	private void autoFilled(ServiceTeam serviceTeam) {
		autoFillOrganizationInternalCode(serviceTeam);
		autoFillChinesePinyin(serviceTeam);
	}

	/**
	 * 填充组织机构编码
	 * 
	 * @param serviceTeam
	 */
	private void autoFillOrganizationInternalCode(ServiceTeam serviceTeam) {
		Organization org = organizationDubboService
				.getSimpleOrgById(serviceTeam.getOrg().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		serviceTeam.setOrgCode(org.getOrgInternalCode());
	}

	/**
	 * 填充拼音
	 * 
	 * @param serviceTeam
	 */
	private void autoFillChinesePinyin(ServiceTeam serviceTeam) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(serviceTeam.getTeamName());
		serviceTeam.setSimplePinyin(pinyin.get("simplePinyin"));
		serviceTeam.setFullPinyin(pinyin.get("fullPinyin"));
	}

	/**
	 * 给VO设置参数
	 * 
	 * @param serviceTeamVo
	 * @param sidx
	 * @param sord
	 * @return serviceTeamVo
	 */
	private ServiceTeamVo fillArgs(ServiceTeamVo serviceTeamVo, String sidx,
			String sord) {
		Organization org = organizationDubboService
				.getSimpleOrgById(serviceTeamVo.getOrg().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		serviceTeamVo.setOrg(org);
		serviceTeamVo.setSortField(sidx);
		serviceTeamVo.setOrder(sord);
		return serviceTeamVo;
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
	public int[] countServiceObjectsForTeam(ServiceTeamVo serviceTeamVo) {
		if (null == serviceTeamVo || null == serviceTeamVo.getId()) {
			throw new BusinessValidationException("无法定位要查询的团队！！");
		}
		int[] objects = new int[5];
		List<Map> result = serviceTeamDao
				.countServiceObjectsForTeam(serviceTeamVo.getId());
		objects[0] = getPopulationObjectNum(result);
		objects[4] = getAllObjects(result) - objects[0];
		// objects[1] = getNum(result);
		return objects;
	}

	/**
	 * 获得所有对象数
	 */
	private Integer getAllObjects(List<Map> result) {
		int count = 0;
		for (Map map : result) {
			count += (Integer) map.get("count");
		}
		return count;
	}

	/**
	 * 解析服务对象中人员数量
	 */
	private Integer getPopulationObjectNum(List<Map> result) {
		int objectNum = 0;
		Set<String> objectTypes = BaseInfoTables.getBussinesspopulationtables()
				.keySet();
		for (Map map : result) {
			for (String objectType : objectTypes) {
				if (objectType.equals(map.get("objectType"))) {
					objectNum += (Integer) map.get("count");
				}
			}
		}
		return objectNum;
	}

	@Override
	public Integer findServiceTeamMemberHasObjects(
			ServiceTeamMemberVo serviceTeamMemberVo) {
		if (serviceTeamMemberVo.getMemberId() == null
				|| serviceTeamMemberVo.getTeamId() == null) {
			throw new BusinessValidationException("无法定位要操作的成员");
		}
		return serviceTeamDao
				.findServiceTeamMemberHasObjects(serviceTeamMemberVo);
	}

	@Override
	public List<ServiceTeam> searchServiceTeamsForExport(
			ServiceTeamVo serviceTeamVo, Integer page, Integer rows,
			String sidx, String sord) {
		fillArgs(serviceTeamVo, sidx, sord);
		List<ServiceTeam> list = serviceTeamDao.searchServiceTeamsForExport(
				serviceTeamVo, page, rows, sidx, sord);
		ServiceTeamVo vo;
		if (null != list && list.size() > 0) {
			for (ServiceTeam team : list) {
				vo = new ServiceTeamVo();
				vo.setId(team.getId());
				team.setServiceObject(serviceObject(this
						.countServiceObjectsForTeam(vo)));

				team.setMemberNum(((ServiceTeamVo) team).getTeamMembers());

				team.setRecordNum(serviceRecordService
						.countServiceRecordsForTeam(team.getId()));
			}
		}
		return list;

	}

	private String serviceObject(int[] objectNums) {
		if (null != objectNums && objectNums.length > 0) {
			return "人员:" + objectNums[0] + ",其他:" + objectNums[4];
		} else {
			return "";
		}

	}

	@Override
	public String[][] getExportPopertyArray() {

		return SpecialGroupsContext.getServiceTeamPropertyArray();
	}

}
