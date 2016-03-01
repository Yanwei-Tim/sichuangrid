package com.tianque.plugin.serviceTeam.serviceObject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.serviceTeam.serviceObject.dao.ServiceObjectDao;
import com.tianque.plugin.serviceTeam.serviceObject.vo.ServiceObjectHouseVo;
import com.tianque.plugin.serviceTeam.serviceObject.vo.ServiceObjectLocationVo;
import com.tianque.plugin.serviceTeam.serviceObject.vo.ServiceObjectPopulationVo;
import com.tianque.plugin.serviceTeam.serviceObject.vo.ServiceObjectVo;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("serviceObjectService")
@Transactional
public class ServiceObjectServiceImpl extends AbstractBaseService implements
		ServiceObjectService {
	@Autowired
	private ServiceObjectDao serviceObjectDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private Integer countNum;

	@Override
	public PageInfo findObjects(ServiceObjectVo serviceObjectVo,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		serviceObjectVo = fillSearchArgs(serviceObjectVo, sidx, sord);
		PageInfo pageInfo = new PageInfo();
		// 若为重点场所或者实有单位
		if (PopulationCatalog.ALL_IMPORTANT_PLACE.equals(serviceObjectVo
				.getObjectBigType())
				|| PopulationCatalog.ACTUAL_COMPANY.equals(serviceObjectVo
						.getObjectBigType())
				|| PopulationCatalog.DOUBLE_NEW.equals(serviceObjectVo
						.getObjectBigType())
				|| PopulationCatalog.ALL_ENTERPRISE.equals(serviceObjectVo
						.getObjectBigType())) {
			// 进行场所dao操作
			if (serviceObjectVo.getTableName().equals("enterprises")) {
				serviceObjectVo.setKeyType(getKeyType(serviceObjectVo
						.getObjectType()));
			}
			fillAddressColumn(serviceObjectVo, serviceObjectVo.getObjectType());
			pageInfo = serviceObjectDao.findLocations(serviceObjectVo, pageNum,
					pageSize);
			List<ServiceObjectLocationVo> list = pageInfo.getResult();
			for (ServiceObjectVo vo : list) {
				vo.setObjectType(serviceObjectVo.getObjectType());
				vo.setObjectTypeCname(BaseInfoTables.getTypeDisplayNames(vo
						.getObjectType()));
				pageInfo.setResult(list);
			}
			return pageInfo;
			// 若为房屋
		} else if (BaseInfoTables.ACTUALHOUSE_KEY.equals(serviceObjectVo
				.getObjectBigType())) {
			pageInfo = serviceObjectDao.findHouses(serviceObjectVo, pageNum,
					pageSize);
			List<ServiceObjectHouseVo> list = pageInfo.getResult();
			for (ServiceObjectHouseVo vo : list) {
				vo.setObjectType(serviceObjectVo.getObjectType());
				vo.setObjectTypeCname(BaseInfoTables.getTypeDisplayNames(vo
						.getObjectType()));
				pageInfo.setResult(list);
			}
			return pageInfo;
			// 若为业务人员
		} else {
			pageInfo = serviceObjectDao.findPopulations(serviceObjectVo,
					pageNum, pageSize);
			List<ServiceObjectPopulationVo> list = pageInfo.getResult();
			for (ServiceObjectPopulationVo vo : list) {
				vo.setObjectType(serviceObjectVo.getObjectType());
				vo.setObjectTypeCname(BaseInfoTables.getTypeDisplayNames(vo
						.getObjectType()));
				pageInfo.setResult(list);
			}
			return pageInfo;
		}
	}

	/**
	 * 1.objectBigType与bigType为人口、场所、房屋分类2.type这三个下的大分类（如重点人员，需救助人员等）
	 * 3.objectType为重点人员分类下的具体分类（如刑事解教人员）
	 */
	@Override
	public PageInfo findObjectsForServiceTeamMember(
			ServiceObjectVo serviceObjectVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		List<ServiceObjectVo> list = new ArrayList<ServiceObjectVo>();
		String bigType = serviceObjectVo.getObjectBigType();
		countNum = 0;
		// 获得人员下的所有大分类名称（如：重点人员）
		String[] type = getObjectBigType(serviceObjectVo.getObjectBigType());
		// 遍历各个大分类下的所有具体分类数据
		for (int i = 0; i < type.length; i++) {
			List smallList = findObjectsByBigType(bigType, type[i],
					serviceObjectVo, pageNum, pageSize, sidx, sord);
			list.addAll(smallList);
		}
		return getPageInfo(pageNum, pageSize, countNum, list);

	}

	/** 搜索出对应大分类（如重点人员）的所有对象数据 **/
	private List<ServiceObjectVo> findObjectsByBigType(String bigType,
			String ObjectBigType, ServiceObjectVo serviceObjectVo,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		List list = new ArrayList<ServiceObjectVo>();
		for (String keyName : BaseInfoTables.keyTablesMaps.get(ObjectBigType)
				.keySet()) {
			serviceObjectVo.setObjectType(keyName);
			fillSearchArgs(serviceObjectVo, sidx, sord);
			if (bigType.equals("population")) {
				List<ServiceObjectPopulationVo> smallList = serviceObjectDao
						.findPopulationsList(serviceObjectVo);
				countNum += serviceObjectDao
						.countFindPopulationsList(serviceObjectVo);
				for (ServiceObjectPopulationVo vo : smallList) {
					vo.setObjectType(keyName);
					vo.setObjectTypeCname(BaseInfoTables.getTypeDisplayNames(vo
							.getObjectType()));
				}
				list.addAll(smallList);
			} else if (bigType.equals("location")) {
				fillAddressColumn(serviceObjectVo, keyName);
				serviceObjectVo.setKeyType(getKeyType(keyName));
				List<ServiceObjectLocationVo> smallList = serviceObjectDao
						.findLocationsList(serviceObjectVo);
				countNum += serviceObjectDao
						.countFindLocationsList(serviceObjectVo);
				for (ServiceObjectLocationVo vo : smallList) {
					vo.setObjectType(keyName);
					vo.setObjectTypeCname(BaseInfoTables.getTypeDisplayNames(vo
							.getObjectType()));
				}
				list.addAll(smallList);
			} else {
				List<ServiceObjectHouseVo> smallList = serviceObjectDao
						.findHousesList(serviceObjectVo);
				countNum += serviceObjectDao
						.countFindHousesList(serviceObjectVo);
				for (ServiceObjectHouseVo vo : smallList) {
					vo.setObjectType(keyName);
					vo.setObjectTypeCname(BaseInfoTables.getTypeDisplayNames(vo
							.getObjectType()));
				}
				list.addAll(smallList);
			}

		}
		return list;
	}

	// FIXME 场所的地址字段变更要修改！！！
	private void fillAddressColumn(ServiceObjectVo serviceObjectVo,
			String keyName) {
		if (BaseInfoTables.DANGEROUSCHEMICALSUNIT_KEY.equalsIgnoreCase(keyName)) {
			serviceObjectVo.setAddressColumn("UNITADDRESS");
		} else if (BaseInfoTables.PUBLICPLACE_KEY.equalsIgnoreCase(keyName)
				|| BaseInfoTables.PUBLICCOMPLEXPLACES_KEY
						.equalsIgnoreCase(keyName)) {
			serviceObjectVo.setAddressColumn("PLACEADDRESS");
		} else if (BaseInfoTables.INTERNETBAR_KEY.equalsIgnoreCase(keyName)) {
			serviceObjectVo.setAddressColumn("PLACEADDRESS");
		} else if (BaseInfoTables.NEWECONOMICORGANIZATIONS_KEY
				.equalsIgnoreCase(keyName)) {
			serviceObjectVo.setAddressColumn("RESIDENCE");
		} else if (BaseInfoTables.ACTUALCOMPANY_KEY.equalsIgnoreCase(keyName)) {
			serviceObjectVo.setAddressColumn("COMPANYADDRESS");
		} else {
			serviceObjectVo.setAddressColumn("address");
		}

	}

	/** 获取人员、场所、房屋等大分类下的每个大分类数组（如重点人员，重点人员下还有其他小分类） **/
	private String[] getObjectBigType(String objectBigType) {
		if (objectBigType.equals("population")) {
			String[] type = { BaseInfoTables.LOVINGCARE_KEY,
					BaseInfoTables.IMPORTANTPERSONNEL_KEY,
					BaseInfoTables.UNEMPLOYED_KEY };
			return type;
		} else if (objectBigType.equals("location")) {
			String[] type = { BaseInfoTables.IMPORTANTPLACE_KEY,
					BaseInfoTables.DOUBLENEW_KEY,
					BaseInfoTables.ACTUALCOMPANY_KEY,
					BaseInfoTables.ENTERPRISE_KEY };
			return type;
		} else {
			String[] type = { BaseInfoTables.ACTUALHOUSE_KEY };
			return type;
		}
	}

	/** 分页操作 **/
	private PageInfo getPageInfo(Integer pageNum, Integer pageSize,
			Integer countNum, List list) {
		Object[] objectArray = list.toArray();
		list.clear();
		for (int i = pageSize * (pageNum - 1); i < pageSize * pageSize; i++) {
			if (i >= objectArray.length) {
				break;
			}
			list.add(objectArray[i]);
		}
		return new PageInfo(pageNum, pageSize, countNum, list);
	}

	/** 若为三个重点则赋予keyType进行区分 **/
	private String getKeyType(String ObjectType) {
		if (ObjectType.equals(BaseInfoTables.SAFETYPRODUCTIONKEY_KEY)) {
			return "safetyProductionKey";
		} else if (ObjectType.equals(BaseInfoTables.SECURITYKEY_KEY)) {
			return "securityKey";
		} else if (ObjectType.equals(BaseInfoTables.FIRESAFETYKEY_KEY)) {
			return "fireSafetyKey";
		} else if (ObjectType.equals(BaseInfoTables.ENTERPRISEKEY_KEY)) {
			return "enterpriseKey";
		} else if (ObjectType.equals(BaseInfoTables.ENTERPRISEDOWNKEY_KEY)) {
			return "enterpriseDownKey";
		} else {
			return null;
		}
	}

	/** 为查询的对象进行赋值 **/
	private ServiceObjectVo fillSearchArgs(ServiceObjectVo serviceObjectVo,
			String sidx, String sord) {
		PopulationCatalog objectCatalog = PopulationCatalog
				.parse(serviceObjectVo.getObjectType());
		if (objectCatalog != null) {
			serviceObjectVo.setObjectBigType(objectCatalog.getParentCatalog());
			serviceObjectVo.setTableName(objectCatalog
					.getStatisticListSetting().getTableName());
			serviceObjectVo.setSearchName(objectCatalog
					.getStatisticListSetting().getSearchField());
		}
		if (serviceObjectVo.getOrganization() != null) {
			serviceObjectVo.setOrganization(organizationDubboService
					.getFullOrgById(serviceObjectVo.getOrganization().getId()));
		}
		serviceObjectVo.setSortField(sidx);
		serviceObjectVo.setOrder(sord);
		return serviceObjectVo;
	}

	public ServiceObjectDao getServiceObjectDao() {
		return serviceObjectDao;
	}

	public void setServiceObjectDao(ServiceObjectDao serviceObjectDao) {
		this.serviceObjectDao = serviceObjectDao;
	}

	public Integer getCountNum() {
		return countNum;
	}

	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}

}
