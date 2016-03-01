package com.tianque.baseInfo.base.service;

import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.dao.PopulationRelationDao;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.domain.PopulationTypeBean;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.plugin.tqSearch.sqlMap.UpdateSqlMap;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.service.PopulationTypeService;
import com.tianque.util.PropertyUtil;

@Transactional
@Service("populationRelationService")
public class PopulationRelationServiceImpl implements PopulationRelationService {
	public final static Logger logger = LoggerFactory
			.getLogger(PopulationRelationService.class);
	@Autowired
	private BaseInfoService baseInfoService;
	@Autowired
	private PopulationRelationDao populationRelationDao;
	@Autowired
	private PopulationTypeService populationTypeService;
	@Autowired
	private ActualPopulationProcessorService actualPopulationProcessorService;

	@Override
	public Countrymen businessOption(Countrymen countrymen,
			String actualPopulationType) {
		Countrymen exist = actualPopulationProcessorService
				.getActualPopulationbyOrgIdAndIdCardNo(countrymen
						.getOrganization().getId(), countrymen.getIdCardNo());
		if (exist == null) {
			return actualPopulationAdd(countrymen, actualPopulationType);
		} else {
			try {
				PropertyUtil.copyPropertiesWithRecursion(Countrymen.class,
						exist, countrymen, new String[] { "baseInfoId",
								"addressInfoId" });
				if (ConstantsProduct.SourcesState.JOB.equals(countrymen
						.getSourcesState())) {
					exist.setSourcesState(ConstantsProduct.SourcesState.JOB);
				}
				return actualPopulationUpdate(exist, actualPopulationType);
			} catch (Exception e) {
				logger.error("人口关系维护出错:" + e.getMessage());
				return null;
			}
		}
	}

	private Countrymen actualPopulationAdd(Countrymen countrymen,
			String actualPopulationType) {
		try {
			Countrymen exist = baseInfoService.existBaseInfo(countrymen
					.getIdCardNo());
			if (exist != null) {
				exist.setBaseInfoId(exist.getId());
				exist.setId(null);
			} else {
				exist = new Countrymen();
			}
			PropertyUtil.copyPropertiesWithRecursion(Countrymen.class, exist,
					countrymen, new String[] { "baseInfoId" });
			Object object = SpringBeanUtil
					.getBeanFromSpringByBeanName(actualPopulationType
							+ "Service");
			String methodName = "add"
					+ actualPopulationType.substring(0, 1).toUpperCase()
					+ actualPopulationType.substring(1) + "BaseInfo";
			exist.setSourcesState(ConstantsProduct.SourcesState.SYNCHRO);
			Object population = getObject(exist, actualPopulationType);
			Method method = object.getClass().getMethod(methodName,
					population.getClass());
			return (Countrymen) method.invoke(object,
					new Object[] { population });
		} catch (Exception e) {
			logger.error("人口关系维护出错:" + e);
			return null;
		}
	}

	private Countrymen actualPopulationUpdate(Countrymen countrymen,
			String actualPopulationType) {
		Object object = SpringBeanUtil
				.getBeanFromSpringByBeanName(actualPopulationType + "Service");
		String methodName = "update"
				+ actualPopulationType.substring(0, 1).toUpperCase()
				+ actualPopulationType.substring(1) + "BaseInfo";
		try {
			Object population = getObject(countrymen, actualPopulationType);
			Method method = object.getClass().getMethod(methodName,
					population.getClass());
			return (Countrymen) method.invoke(object,
					new Object[] { population });
		} catch (Exception e) {
			logger.error("人口关系维护出错:" + e.getMessage());
			return null;
		}
	}

	private void businessPopulationDelete(Long populationId,
			String populationType) {
		Object object = SpringBeanUtil
				.getBeanFromSpringByBeanName(populationType + "Service");
		String methodName = "delete"
				+ populationType.substring(0, 1).toUpperCase()
				+ populationType.substring(1) + "ByIds";
		try {
			Method method = object.getClass().getMethod(methodName,
					Long[].class);
			method.invoke(object, new Object[] { new Long[] { populationId } });
		} catch (Exception e) {
			logger.error("人口关系维护出错:" + e.getMessage());
		}
	}

	private Object getObject(Countrymen countrymen, String actualPopulationType) {
		if (BaseInfoTables.HOUSEHOLDSTAFF_KEY.equals(actualPopulationType)) {
			HouseholdStaff householdStaff = new HouseholdStaff();
			PropertyUtil.copyPropertiesWithRecursion(Countrymen.class,
					householdStaff, countrymen);
			householdStaff.setId(countrymen.getId());
			if (ConstantsProduct.SourcesState.JOB.equals(countrymen
					.getSourcesState())) {
				householdStaff
						.setSourcesState(ConstantsProduct.SourcesState.JOB);
			}
			return householdStaff;
		} else if (BaseInfoTables.FLOATINGPOPULATION_KEY
				.equals(actualPopulationType)) {
			FloatingPopulation floatingPopulation = new FloatingPopulation();
			PropertyUtil.copyAllPropertiesWithRecursion(Countrymen.class,
					floatingPopulation, countrymen);
			floatingPopulation.setId(countrymen.getId());
			if (ConstantsProduct.SourcesState.JOB.equals(countrymen
					.getSourcesState())) {
				floatingPopulation
						.setSourcesState(ConstantsProduct.SourcesState.JOB);
			}
			return floatingPopulation;
		} else {
			logger.error("人口关系维护出错:实口key值为不存在");
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		Countrymen countrymen = new Countrymen();
		countrymen.setName("11");
		Class c = Class
				.forName("com.tianque.plugin.floatingPopulation.service.FloatingPopulationServiceImpl");
		Object object = c.newInstance();
		Method method = object.getClass().getMethod("addFloatingPopulation",
				new FloatingPopulation().getClass());
		method.invoke(object, new Object[] { countrymen });
	}

	@Override
	public void addPopulationRelation(Long actualId, String actualType,
			Long populationId, String populationType) {
		populationRelationDao.addPopulationRelation(actualId, actualType,
				populationId, populationType);
	}

	@Override
	@SolrServerIndex(mode = OperateMode.EDIT, value = UpdateSqlMap.POPULATION_TYPE_KEY)
	public void businessDeletePopulationRelation(Long populationId,
			String populationType) {
		populationRelationDao.businessDeletePopulationRelation(populationId,
				populationType);
	}

	@Override
	public void actualDeletePopulationRelation(Long actualId, String actualType) {
		populationRelationDao.actualDeletePopulationRelation(actualId,
				actualType);

	}

	@Override
	public void actualPopulationLogOut(Long actualId, String actualType,
			LogoutDetail logoutDetail) {
		try {
			List<PopulationTypeBean> list = populationTypeService
					.getPopulationTypeByActualIdAndType(actualId, actualType);
			Object object = SpringBeanUtil
					.getBeanFromSpringByBeanName(actualType + "Service");

			Method method = object
					.getClass()
					.getSuperclass()
					.getMethod("updateLogOutByPopulationTypeAndId",
							LogoutDetail.class, String.class, Long.class);
			for (int i = 0; i < list.size(); i++) {
				PopulationTypeBean temp = list.get(i);
				method.invoke(object,
						new Object[] { logoutDetail, temp.getPopulationType(),
								temp.getPopulationId() });
			}
		} catch (Exception e) {
			logger.error("人口关系维护出错:" + e.getMessage());
		}

	}

	@Override
	public List<PopulationTypeBean> getActualPopulationTypeBeans(Long actualId,
			String actualType) {
		return populationTypeService.getPopulationTypeByActualIdAndType(
				actualId, actualType);

	}

	@Override
	public PopulationTypeBean getBusinessPopulationTypeBean(Long populationId,
			String populationType) {
		return populationTypeService.getPopulationTypeByPopulationIdAndType(
				populationId, populationType);

	}

	@Override
	public void actualPopulationDel(Long actualId, String actualType) {
		try {
			List<PopulationTypeBean> list = populationTypeService
					.getPopulationTypeByActualIdAndType(actualId, actualType);
			for (int i = 0; i < list.size(); i++) {
				PopulationTypeBean temp = list.get(i);
				businessPopulationDelete(temp.getPopulationId(),
						temp.getPopulationType());
			}
		} catch (Exception e) {
			throw new ServiceValidationException("删除关联的业务信息出错", e);
		}
	}
}
