package com.tianque.threeRecordsIssue.dataTrans.dataConvert;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.floatingPopulation.service.FloatingPopulationService;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.PopulationTypeBean;
import com.tianque.service.PopulationTypeService;
import com.tianque.service.util.PopulationType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("threeCountyDataConverterPoxy")
public class ThreeCountyDataConverterPoxy extends ThreeAbstractDataConverter<Countrymen> {

	@Autowired
	public FloatingPopulationService floatingPopulationService;

	@Autowired
	public HouseholdStaffService householdStaffService;
	@Autowired
	private PopulationTypeService populationTypeService;

	ThreeDataConvert<Countrymen> convert;

	public void setConvert(ThreeDataConvert<Countrymen> convert) {
		this.convert = convert;
	}

	@Override
	public Countrymen updateDomain(Countrymen domain) {
		convert.updateDomain(domain);
		Long id = domain.getId();
		FloatingOrHouseHoldConvert(domain, id);
		return domain;
	}

	public Countrymen updateDomain(ThreeDataConvert convertt, Countrymen domain) {
		convertt.updateDomain(domain);
		Long id = domain.getId();
		FloatingOrHouseHoldConvert(domain, id);
		return domain;
	}

	private void FloatingOrHouseHoldConvert(Countrymen domain, Long id) {
		String populationType = domain.getAttentionPopulationType();
		PopulationTypeBean populationTypeBean = populationTypeService
				.getPopulationTypeByPopulationIdAndType(id, populationType);
		if (populationTypeBean != null) {
			Long actualId = populationTypeBean.getActualId();
			String ActualType = populationTypeBean.getActualType();
			String ActualPopulationType = domain.getActualPopulationType();
			commonConver(ActualType, ActualPopulationType, actualId);
		}
	}

	/**
	 * @param actualType
	 *            数据库中类型
	 * @param actualPopulationType
	 *            excel 中类型
	 * @param actualId
	 *            实有类型id
	 */
	private void commonConver(String actualType, String actualPopulationType,
			Long actualId) {
		// 判断系统中的 业务人口实口类型和 excel中的类型是否相同，不同就进行转换
		if (!actualType.equals(actualPopulationType)) {
			if (!StringUtils.isEmpty(actualPopulationType)) {
				// 1 excel 中的人口类型
				if (PopulationType.FLOATING_POPULATION
						.equals(actualPopulationType)) {
					householdStaffService.toFloatingPopulation(actualId);

				} else if (PopulationType.HOUSEHOLD_STAFF
						.equals(actualPopulationType)) {
					floatingPopulationService.toHouseholdStaff(actualId);
				}
			}
		}
	}

	@Override
	public ValidateResult validate(Countrymen domain, int realRow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Countrymen convertToDomain(String[] cellValues, ValidateResult result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Countrymen persistenceDomain(Countrymen domain) {

		// 1 ：查询人员的实口类型
		ActualPopulation actualPopulation = actualPopulationProcessorService
				.getActualPopulationbyOrgIdAndIdCardNo(domain.getOrganization()
						.getId(), domain.getIdCardNo());
		// 2 如果存在 ，判断是 流动还是户籍 与excel对比 然后 转换
		if (actualPopulation != null) {
			Long actualId = actualPopulation.getId();
			// 数据库中类型
			String ActualType = actualPopulation.getActualPopulationType();
			// excel 中类型
			String ActualPopulationType = domain.getActualPopulationType();
			commonConver(ActualType, ActualPopulationType, actualId);
		}
		domain = convert.persistenceDomain(domain);
		return domain;
	}

	public Countrymen persistenceDomain(ThreeDataConvert<Countrymen> convertt,
			Countrymen domain) {

		// 1 ：查询人员的实口类型
		ActualPopulation actualPopulation = actualPopulationProcessorService
				.getActualPopulationbyOrgIdAndIdCardNo(domain.getOrganization()
						.getId(), domain.getIdCardNo());
		// 2 如果存在 ，判断是 流动还是户籍 与excel对比 然后 转换
		if (actualPopulation != null) {
			Long actualId = actualPopulation.getId();
			// 数据库中类型
			String ActualType = actualPopulation.getActualPopulationType();
			// excel 中类型
			String ActualPopulationType = domain.getActualPopulationType();
			commonConver(ActualType, ActualPopulationType, actualId);
		}
		domain = convertt.persistenceDomain(domain);
		return domain;
	}

}
