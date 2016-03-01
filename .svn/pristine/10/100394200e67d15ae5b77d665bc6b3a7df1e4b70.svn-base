package com.tianque.plugin.serviceTeam.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.plugin.AbstractSystemPropertiesPlugin;
import com.tianque.plugin.serviceTeam.util.Constants;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.sysadmin.service.PropertyDomainService;

/**
 * 插件初始化加载类
 * 
 * @author Administrator
 */
public class ServiceTeamPropertyInit extends AbstractSystemPropertiesPlugin {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public ServiceTeamPropertyInit(PropertyDomainService propertyDomainService,
			PropertyDictService propertyDictService) {
		super(propertyDomainService, propertyDictService);
	}

	@Override
	public void init() throws Exception {
		System.out.println("服务团队插件被加载");
		initRentalBuildings();
	}

	/**
	 * 初始化工作:添加数据字典
	 */
	private void initRentalBuildings() {
		initServiceTeamProperties();
		initTeamPositionProperties();
		logger.info("服务团队类型字典初始化结束(插件)!");
	}

	/**
	 * 服务团队字典
	 */
	private void initServiceTeamProperties() {
		propertyDomain = addPropertyDomain(Constants.ServiceTeamPropertyTypes.TEAM, false, null);
		addPropertyDict(Constants.ServiceTeamPropertyTypes.ENTERTAINMENT,
				Constants.ServiceTeamPropertyTypes.ENTERTAINMENT_ID, 1);
		addPropertyDict(Constants.ServiceTeamPropertyTypes.LEGAL_SERVICES,
				Constants.ServiceTeamPropertyTypes.LEGAL_SERVICES_ID, 2);
		addPropertyDict(Constants.ServiceTeamPropertyTypes.SAFE_CONSTRUCTION,
				Constants.ServiceTeamPropertyTypes.SAFE_CONSTRUCTION_ID, 3);
		addPropertyDict(Constants.ServiceTeamPropertyTypes.PEOPLES_LIVELIHOOD,
				Constants.ServiceTeamPropertyTypes.PEOPLES_LIVELIHOOD_ID, 4);
		addPropertyDict(Constants.ServiceTeamPropertyTypes.CONFLICT_MEDIATION,
				Constants.ServiceTeamPropertyTypes.CONFLICT_MEDIATION_ID, 5);
		addPropertyDict(Constants.ServiceTeamPropertyTypes.SECURITY_GUARD,
				Constants.ServiceTeamPropertyTypes.SECURITY_GUARD_ID, 6);
		addPropertyDict(Constants.ServiceTeamPropertyTypes.MEDICAL_SERVICES,
				Constants.ServiceTeamPropertyTypes.MEDICAL_SERVICES_ID, 7);
		addPropertyDict(Constants.ServiceTeamPropertyTypes.MASS_TREAT_TEAM,
				Constants.ServiceTeamPropertyTypes.MASS_TREAT_TEAM_ID, 8);
		addPropertyDict(Constants.ServiceTeamPropertyTypes.OTHER,
				Constants.ServiceTeamPropertyTypes.OTHERN, 9);
		logger.info("服务团队类型字典初始化结束(插件)!");
	}

	/**
	 * 团队职务字典
	 */
	private void initTeamPositionProperties() {
		propertyDomain = addPropertyDomain(Constants.ServiceTeamPositionPropertyTypes.TEAMPOSITION,
				false, null);
		addPropertyDict(Constants.ServiceTeamPositionPropertyTypes.TEAMLEADER,
				Constants.ServiceTeamPositionPropertyTypes.TEAMLEADERID, 1);
		addPropertyDict(Constants.ServiceTeamPositionPropertyTypes.DEPUTYCAPTAIN,
				Constants.ServiceTeamPositionPropertyTypes.DEPUTYCAPTAINID, 1);
		addPropertyDict(Constants.ServiceTeamPositionPropertyTypes.TEAMMEMBER,
				Constants.ServiceTeamPositionPropertyTypes.TEAMMEMBERID, 1);
	}
}
