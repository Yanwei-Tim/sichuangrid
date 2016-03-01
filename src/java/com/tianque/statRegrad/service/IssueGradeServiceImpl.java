package com.tianque.statRegrad.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationType;
import com.tianque.statRegrad.constant.DecodeScaleConstant;
import com.tianque.statRegrad.dao.IntegratedIndicatorForRegradedPointDao;
import com.tianque.statRegrad.domain.IntegratedIndicator;
import com.tianque.statRegrad.domain.IssueGradeNode;
import com.tianque.statRegrad.util.RegradedPointUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("IssueGradeService")
public class IssueGradeServiceImpl implements IssueGradeService {
	private final static Logger LOG = LoggerFactory
			.getLogger(IssueGradeServiceImpl.class);
	@Autowired
	private IntegratedIndicatorForRegradedPointDao regradedPointDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;

	private final static int ACCURACY = 3;// 精确度
	private final static double SCALE = 10;// 扣分系数
	private final static int MAXSIZE = 1000;// oracle in最大支持1000个

	private String year;
	private String month;

	@Override
	public int statRegradedPoints(String date) {
		long start = System.currentTimeMillis();

		Map<Long, IssueGradeNode> map = initIssueGradeNode(date);
		int num = clearIssueGradeNode(null);
		List<IntegratedIndicator> result = new ArrayList<IntegratedIndicator>();
		IntegratedIndicator temp = null;
		for (Map.Entry<Long, IssueGradeNode> entry : map.entrySet()) {
			temp = computeGrade(entry.getValue());
			if (temp != null)
				result.add(temp);
		}
		regradedPointDao.batchAddDate(result);

		if (LOG.isDebugEnabled())
			LOG.debug(Thread.currentThread().getId() + ":cost "
					+ (System.currentTimeMillis() - start) + "ms, " + num
					+ " rows is delete.");
		return result.size();
	}

	@Override
	public List<IssueGradeNode> findIssueGradeNode(String date, Long hash,
			List<Long> valueList) {
		long start = System.currentTimeMillis();
		Map<Long, IssueGradeNode> map = initIssueGradeNode(date);

		List<Long> orgList = new ArrayList<Long>();
		List<IssueGradeNode> result = new ArrayList<IssueGradeNode>();
		int num = 0;
		for (Map.Entry<Long, IssueGradeNode> entry : map.entrySet()) {
			Long value = entry.getKey() % hash;
			if (valueList.contains(value)) {
				result.add(entry.getValue());
				orgList.add(entry.getKey());
				if (orgList.size() == MAXSIZE) {
					num += clearIssueGradeNode(orgList);
					orgList.clear();
				}
			}
		}
		if (orgList.size() > 0) {
			num += clearIssueGradeNode(orgList);
		}
		if (LOG.isDebugEnabled())
			LOG.debug(Thread.currentThread().getId() + ":cost "
					+ (System.currentTimeMillis() - start) + "ms, " + num
					+ " rows is delete.");
		return result;
	}

	@Override
	public IntegratedIndicator computeGrade(IssueGradeNode issueGradeNode) {
		return autoFillGradeAndCoefficientScord(issueGradeNode);
	}

	@Override
	public int clearIssueGradeNode(List<Long> orgList) {
		return regradedPointDao.deleteIssueGrade(year, month, orgList);
	}

	private Map<Long, IssueGradeNode> initIssueGradeNode(String date) {
		long start = System.currentTimeMillis();
		if (StringUtil.isStringAvaliable(date) && date.matches("\\d{6}")) {
			int i = Integer.parseInt(date);
			year = i / 100 + "";
			month = i % 100 + "";
		} else {
			Calendar calendar = Calendar.getInstance();
			year = String.valueOf(calendar.get(Calendar.YEAR));
			int m = calendar.get(Calendar.MONTH);
			month = String.valueOf(m + 1);
			if (m < 9) {
				date = year + "0" + month;
			} else {
				date = year + month;
			}
		}
		List<IssueGradeNode> nodes = regradedPointDao
				.queryIssueGradeForList(date);
		if (LOG.isDebugEnabled()) {
			LOG.debug(Thread.currentThread().getId() + ":nodes size="
					+ nodes.size() + " cost:"
					+ (System.currentTimeMillis() - start));
			start = System.currentTimeMillis();
		}
		// 行政部门类型
		Long adminOrgType = RegradedPointUtil
				.getOrganizationTypeIdByInternalId(propertyDictService,
						OrganizationType.ADMINISTRATIVE_REGION);
		// 职能部门类型
		Long funOrgType = RegradedPointUtil.getOrganizationTypeIdByInternalId(
				propertyDictService, OrganizationType.FUNCTIONAL_ORG);

		Map<Long, IssueGradeNode> map = new HashMap<Long, IssueGradeNode>();
		for (IssueGradeNode issueGradeNode : nodes) {
			map.put(issueGradeNode.getOrgId(), issueGradeNode);
		}

		for (IssueGradeNode node : nodes) {
			if (node.getParentId() == null) {
				continue;
			}
			IssueGradeNode parent = map.get(node.getParentId());
			Long orgType = node.getOrgtype();
			if (parent == null) {
				LOG.warn(node.getParentId() + " is null");
				continue;
			}
			if (adminOrgType.equals(orgType)) {
				parent.addAdminChild(node);
			} else if (funOrgType.equals(orgType)) {
				parent.addFunChild(node);
			} else {
				LOG.warn(orgType + " is error!");
			}
		}

		Organization rootOrg = organizationDubboService.getRootOrganization();
		IssueGradeNode root = map.get(rootOrg.getId());
		if (root != null) {
			root.initIssueSumKinds();
		} else {
			LOG.warn("root is null!");
		}
		if (LOG.isDebugEnabled())
			LOG.debug("initIssueGradeNode cost "
					+ (System.currentTimeMillis() - start));
		return map;
	}

	private IntegratedIndicator autoFillGradeAndCoefficientScord(
			IssueGradeNode IssueGradeNode) {
		// allocationDegree为核心算法，具体算法请看其注释
		List<Long>[] degree = IssueGradeNode.getSumKind();// 三个档次的分数

		IntegratedIndicator integratedIndicator = new IntegratedIndicator(
				IssueGradeNode, year, month);
		if (degree == null) {
			// LOG.warn(IssueGradeNode.getOrgId() + " is null");
			return null;
		}
		for (int i = 0; i < degree.length; i++) {
			List<Long> temp = degree[i];
			if (temp != null
					&& temp.contains(integratedIndicator.getIssueSum())) {
				integratedIndicator.setGrade(i + 1);
				integratedIndicator.setCoefficient(i + 1);
			}
		}

		if (!isDivisorLowerDividend(integratedIndicator.getDealIssueSum()
				.longValue(), integratedIndicator.getIssueSum().longValue())) {
			integratedIndicator.setCoefficient(DecodeScaleConstant.SPECIAL);
		}
		calculateIntegratedIndicator(integratedIndicator);

		return integratedIndicator;
	}

	private void calculateIntegratedIndicator(
			IntegratedIndicator integratedIndicator) {
		double issueSum = integratedIndicator.getIssueSum().doubleValue();
		double dealIssueSum = integratedIndicator.getDealIssueSum()
				.doubleValue();
		double scale = integratedIndicator.getCoefficient().doubleValue();
		if (issueSum > 0) {
			integratedIndicator.setScord(div((issueSum - dealIssueSum) * scale
					* SCALE, issueSum, ACCURACY));
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
			LOG.error("", e);
		}
		return scales == null ? false : (scales.get(1) * divisor) >= (scales
				.get(0) * dividend);
	}
}
