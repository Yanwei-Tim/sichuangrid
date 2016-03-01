package com.tianque.baseInfo.idleYouth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.idleYouth.dao.SearchIdleYouthDao;
import com.tianque.baseInfo.idleYouth.domain.IdleYouth;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.domain.vo.SearchIdleYouthVo;
import com.tianque.service.ActualPopulationProcessorService;

@Transactional
@Service("searchIdleYouthService")
public class SearchIdleYouthServiceImpl extends AbstractBaseService implements
		SearchIdleYouthService {
	@Autowired
	private SearchIdleYouthDao searchIdleYouthDao;

	@Autowired
	private GlobalSettingService globalSettingService;

	@Autowired
	private ActualPopulationProcessorService actualPopulationProcessorService;

	@Override
	public List<IdleYouth> findIdleYouthsForExport(
			SearchIdleYouthVo idleYouthSearchCondition, Integer pageNum,
			Integer pageSize, String sortField, String order) {

		List<IdleYouth> idleYouthList = searchIdleYouthDao
				.searchIdleYouthsForExport(idleYouthSearchCondition, pageNum,
						pageSize, sortField, order);
		if (GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			return idleYouthList;
		} else {
			if (null != idleYouthList && idleYouthList.size() > 0) {
				ActualPopulation actualPopulation = null;
				for (IdleYouth idleYouth : idleYouthList) {
					actualPopulation = actualPopulationProcessorService
							.getActualPopulationbyOrgIdAndIdCardNo(idleYouth
									.getOrganization().getId(), idleYouth
									.getIdCardNo());
					if (null != actualPopulation) {
						idleYouth.setHouseCode(actualPopulation.getHouseCode());
						idleYouth.setNoHouseReason(actualPopulation
								.getNoHouseReason());
					}
				}
			}
			return idleYouthList;
		}
	}

	@Override
	public Integer getCount(SearchIdleYouthVo idleYouthVo) {
		// TODO Auto-generated method stub
		return searchIdleYouthDao.getCount(idleYouthVo);
	}
}
