package com.tianque.statRegrad.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.StatRegradedPoint;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.statRegrad.constant.DecodeScaleConstant;
import com.tianque.statRegrad.constant.RankConstant;
import com.tianque.statRegrad.dao.IntegratedIndicatorForRegradedPointDao;
import com.tianque.statRegrad.domain.IntegratedIndicator;
import com.tianque.statRegrad.util.RegradedPointUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("integratedIndicatorForRegradedPointServiceImpl")
public class IntegratedIndicatorForRegradedPointServiceImpl implements
		StatRegradedPointsService, IntegratedIndicatorForRegradedPointService {
	private final static Logger logger = LoggerFactory
			.getLogger(IntegratedIndicatorForRegradedPointServiceImpl.class);
	private final static int ACCURACY = 3;// 精确度
	private final static double SCALE = 10;// 扣分系数

	@Autowired
	private IntegratedIndicatorForRegradedPointDao integratedIndicatorForRegradedPointDao;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public List<StatRegradedPoint> findStatRegradedPoints(
			List<StatRegradedPoint> points, Integer year, Integer month,
			PropertyDict reoprtDateType, Long targeOrgId, int internalId,
			String sidx, String sord) {
		PropertyDict dict = propertyDictService
				.getPropertyDictById(reoprtDateType.getId());
		Map<String, Date> date = RegradedPointUtil.getStartDateAndEndDate(year,
				month, dict.getDisplayName());
		List<IntegratedIndicator> integratedIndicators = findStatRegradedPoints(
				date.get(RegradedPointUtil.START_DATE),
				date.get(RegradedPointUtil.END_TDATE), targeOrgId,
				RegradedPointUtil.getOrganizationTypeIdByInternalId(
						propertyDictService, internalId));
		parseStatRegradedPoints(points, integratedIndicators);
		return points;
	}

	private List<IntegratedIndicator> findStatRegradedPoints(Date startDate,
			Date endDate, Long targeOrgId, Long orgType) {
		return integratedIndicatorForRegradedPointDao
				.findIntegratedIndicatorByParentOrgIdAndYearAndMonth(startDate,
						endDate, targeOrgId, orgType);
	}

	@Override
	@Transactional
	public void statRegradedPoints() {
		integratedIndicatorForRegradedPointDao.statRegradedPoints();
		updateRegradedPoints();
	}

	@Override
	@Transactional
	public void updateRegradedPoints() {
		List<Organization> organizations = findAllOrganizationExcludeGridAndCountry();
		if (organizations == null || organizations.size() == 0) {
			return;
		}
		for (Organization organization : organizations) {
			updateAdminOrganizationIntegratedIndicator(organization.getId());
			updateFunOrganizationIntegratedIndicator(organization.getId());
		}
	}

	private void updateRegradedPoints(Long orgType, Long parentOrgId) {
		List<IntegratedIndicator> integratedIndicators = findIntegratedIndicators(
				orgType, parentOrgId);
		autoFillGradeAndCoefficientScord(integratedIndicators, orgType,
				parentOrgId);
		integratedIndicatorForRegradedPointDao
				.batchUpdateDate(integratedIndicators);
	}

	private void updateAdminOrganizationIntegratedIndicator(Long parentOrgId) {
		Long orgType = RegradedPointUtil.getOrganizationTypeIdByInternalId(
				propertyDictService, OrganizationType.ADMINISTRATIVE_REGION);
		updateRegradedPoints(orgType, parentOrgId);
	}

	private void updateFunOrganizationIntegratedIndicator(Long parentOrgId) {
		Long orgType = RegradedPointUtil.getOrganizationTypeIdByInternalId(
				propertyDictService, OrganizationType.FUNCTIONAL_ORG);
		updateRegradedPoints(orgType, parentOrgId);
	}

	private List<Organization> findAllOrganizationExcludeGridAndCountry() {
		List<PropertyDict> orgLevels = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
		List<Organization> organizations = new ArrayList<Organization>();
		for (PropertyDict orgLevel : orgLevels) {
			if (orgLevel.getInternalId() != OrganizationLevel.COUNTRY
					|| orgLevel.getInternalId() != OrganizationLevel.GRID) {
				organizations.addAll(organizationDubboService
						.fingOrganizationforLevel(orgLevel.getId()));
			}
		}
		return organizations;
	}

	private List<Long> findIssueSumKinds(Long orgType, Long parentOrgId) {
		return integratedIndicatorForRegradedPointDao.findIssueSumKinds(
				orgType, parentOrgId);
	}

	private List<IntegratedIndicator> findIntegratedIndicators(Long orgType,
			Long parentOrgId) {
		return integratedIndicatorForRegradedPointDao
				.findIntegratedIndicatorsByOrgType(orgType, parentOrgId);
	}

	private void autoFillGradeAndCoefficientScord(
			List<IntegratedIndicator> integratedIndicators, Long orgType,
			Long parentOrgId) {
		Map<String, List<Long>> degree = allocationDegree(orgType, parentOrgId);// 三个档次的分数
		for (IntegratedIndicator integratedIndicator : integratedIndicators) {
			if (degree.get("first") != null
					&& degree.get("first").contains(
							integratedIndicator.getIssueSum().longValue())) {
				integratedIndicator.setGrade(RankConstant.FIRST);
				integratedIndicator.setCoefficient(DecodeScaleConstant.FIRST);
			}

			if (degree.get("second") != null
					&& degree.get("second").contains(
							integratedIndicator.getIssueSum().longValue())) {
				integratedIndicator.setGrade(RankConstant.SECOND);
				integratedIndicator.setCoefficient(DecodeScaleConstant.SECOND);
			}

			if (degree.get("third") != null
					&& degree.get("third").contains(
							integratedIndicator.getIssueSum().longValue())) {
				integratedIndicator.setGrade(RankConstant.THIRD);
				integratedIndicator.setCoefficient(DecodeScaleConstant.THIRD);
			}

			if (!isDivisorLowerDividend(integratedIndicator.getDealIssueSum()
					.longValue(), integratedIndicator.getIssueSum().longValue())) {
				integratedIndicator.setCoefficient(DecodeScaleConstant.SPECIAL);
			}
		}
		calculateIntegratedIndicator(integratedIndicators);
	}

	private void calculateIntegratedIndicator(
			List<IntegratedIndicator> integratedIndicators) {
		for (IntegratedIndicator integratedIndicator : integratedIndicators) {
			double issueSum = integratedIndicator.getIssueSum().doubleValue();
			double dealIssueSum = integratedIndicator.getDealIssueSum()
					.doubleValue();
			double scale = integratedIndicator.getCoefficient().doubleValue();
			if (issueSum > 0) {
				integratedIndicator.setScord(div((issueSum - dealIssueSum)
						* scale * SCALE, issueSum, ACCURACY));
			}
		}
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	private double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	private boolean isDivisorLowerDividend(long divisor, long dividend) {
		List<Integer> scales = null;
		try {
			scales = RegradedPointUtil.getScale();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return scales == null ? false : (scales.get(1) * divisor) >= (scales
				.get(0) * dividend);
	}

	/** 根据所有乡镇办事处和职能部门单位，根据本级和该部门结构下的下辖单位，事件接件总数量的多少排名分为三个档次 */
	private Map<String, List<Long>> allocationDegree(Long orgType,
			Long parentOrgId) {
		Map<String, List<Long>> sumKind = new HashMap<String, List<Long>>();
		try {
			List<Integer> scales = RegradedPointUtil.getRankScale();// {1,2,2}
			Integer scaleCount = scaleCount(scales);// 5
			List<Long> sumKinds = findIssueSumKinds(orgType, parentOrgId);// parentOrgId下辖的所有的职能部门的当前月份的事件接件总数
			int count = sumKinds.size();// 下辖职能部门的数量（即是接件总数的个数【因为需要分为三个档次，所以当总数为1、2、3时可以直接根据总数的分档次，当分数总数超过3个时，需要进行特殊处理把所有的分数分为三个类】）

			switch (count) {
			case 0:
				sumKind = null;
				break;
			case 1:
				sumKind.put("first", sumKinds);
				break;
			case 2:
				sumKind.put("first", sumKinds.subList(0, 1));
				sumKind.put("second", sumKinds.subList(1, count));
				break;
			case 3:
				sumKind.put("first", sumKinds.subList(0, 1));
				sumKind.put("second", sumKinds.subList(1, 2));
				sumKind.put("third", sumKinds.subList(2, count));
				break;
			default:
				int first = rounding(count * scales.get(0), scaleCount);
				int second = rounding(count * scales.get(1), scaleCount);
				sumKind.put("first", sumKinds.subList(0, first));
				sumKind.put("second", sumKinds.subList(first, first + second));
				sumKind.put("third", sumKinds.subList(first + second, count));
				break;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sumKind;
	}

	private int rounding(int divisor, int dividend) {
		return (divisor % dividend == 0) ? (divisor / dividend) : (divisor
				/ dividend + 1);
	}

	private Integer scaleCount(List<Integer> scales) {
		Integer count = 0;
		for (Integer scale : scales) {
			count += scale;
		}
		return count;
	}

	private void parseStatRegradedPoints(
			List<StatRegradedPoint> statRegradedPoints,
			List<IntegratedIndicator> integratedIndicators) {
		if (integratedIndicators != null && integratedIndicators.size() > 0) {
			for (StatRegradedPoint statRegradedPoint : statRegradedPoints) {
				for (IntegratedIndicator integratedIndicator : integratedIndicators) {
					if (statRegradedPoint.getOrg().getId().intValue() == integratedIndicator
							.getOrganization().getId().intValue())
						statRegradedPoint
								.setIntegratedIndicator(integratedIndicator
										.getScord());
				}
			}
		}
	}
}
