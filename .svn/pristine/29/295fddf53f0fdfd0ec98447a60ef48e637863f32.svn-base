package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.dao.EvaluateStatisticDao;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.EvaluateStatisticVo;
import com.tianque.domain.vo.EvaluateVo;
import com.tianque.service.EvaluateStatisticService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("evaluateStatisticService")
public class EvaluateStatisticServiceImpl extends AbstractBaseService implements
		EvaluateStatisticService {

	@Autowired
	private EvaluateStatisticDao evaluateStatisticDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private List<EvaluateStatisticVo> findEvaluateStatistic(Long evaluatetypeid,
			String evaluateResult, String orgInternalCode, int rownum) {
		return evaluateStatisticDao.findEvaluateStatistic(evaluatetypeid, evaluateResult,
				orgInternalCode, rownum);
	}

	@Override
	public List<EvaluateVo> findEvaluateVo(Long evaluatetypeid, String orgInternalCode, Long orgId) {
		List<Organization> organizationList = organizationDubboService.findAdminOrgsByParentId(orgId);
		List<EvaluateVo> list = evaluateStatisticDao.findEvaluateVo(evaluatetypeid,
				orgInternalCode, organizationList.size());
		for (int i = 0; i < list.size(); i++) {
			List<EvaluateStatisticVo> evaluateStatisticList = findEvaluateStatistic(evaluatetypeid,
					list.get(i).getScore(), orgInternalCode, organizationList.size());
			for (int j = 0; j < evaluateStatisticList.size(); j++) {
				Organization org = organizationDubboService.getSimpleOrgById(evaluateStatisticList
						.get(j).getOrganization().getId());
				evaluateStatisticList.get(j).setOrganization(org);
			}
			list.get(i).setParticulars(evaluateStatisticList);
			list.get(i).setSum(organizationList.size());
		}
		return list;
	}

	@Override
	public List<Object[]> getEvaluateColumnByOrgId(Long evaluatetypeid, String orgInternalCode,
			Long orgId) {
		List<Object[]> positiveInfoPieDatas = new ArrayList<Object[]>();
		List<Organization> organizationList = organizationDubboService.findAdminOrgsByParentId(orgId);
		List<EvaluateVo> list = evaluateStatisticDao.findEvaluateVo(evaluatetypeid,
				orgInternalCode, organizationList.size());
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setSum(organizationList.size());
			Object[] positiveInfoPieData = new Object[2];
			if (list.size() == 0) {
				positiveInfoPieData[1] = 0;
			} else {
				positiveInfoPieData[1] = Double.parseDouble(new java.text.DecimalFormat("#.00")
						.format(list.get(i).getAmount() / list.get(i).getSum() * 100));
			}
			positiveInfoPieData[0] = list.get(i).getScore() + "( "
					+ new java.text.DecimalFormat("#").format(list.get(i).getAmount()) + " )";
			positiveInfoPieDatas.add(positiveInfoPieData);
		}
		return positiveInfoPieDatas;
	}

}
