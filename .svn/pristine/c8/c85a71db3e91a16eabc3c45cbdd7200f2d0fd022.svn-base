package com.tianque.aidsPopulations.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.aidsPopulations.dao.SearchAidspopulationsDao;
import com.tianque.aidsPopulations.dataConverter.SpecialGroupsContext;
import com.tianque.aidsPopulations.domain.Aidspopulations;
import com.tianque.aidsPopulations.domain.vo.SearchAidspopulationsVo;
import com.tianque.aidsPopulations.service.SearchAidspopulationsService;
import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.IdCardUtil;

@Transactional
@Service("searchAidspopulationsService")
public class SearchAidspopulationsServiceImpl implements
		SearchAidspopulationsService {
	@Autowired
	private SearchAidspopulationsDao searchAidspopulationsDao;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private ActualPopulationProcessorService actualPopulationProcessorService;
	@Autowired
	private PermissionDubboService permissionDubboService;

	@Override
	public PageInfo<Aidspopulations> searchAidspopulations(
			SearchAidspopulationsVo searchAidspopulationsVo, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		PageInfo<Aidspopulations> aidspopulations = searchAidspopulationsDao.searchAidspopulations(
				searchAidspopulationsVo, pageNum, pageSize, sortField, order);
		return aidspopulations=hiddenIdCard(aidspopulations);
	}
	
	//隐藏身份证中间4位
		private PageInfo<Aidspopulations> hiddenIdCard(PageInfo<Aidspopulations> pageInfo){
				//判断权限，有权限不隐藏
				if(permissionDubboService.
						isUserHasPermission(ThreadVariable.getUser().getId(), "isAidspopulationsManagementNotHidCard")){
					return pageInfo;
				}
				List<Aidspopulations> list = pageInfo.getResult();
				int index=0;
				for (Aidspopulations verification:list) {
					verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
					list.set(index, verification);
					index++;
				}
				pageInfo.setResult(list);
				return pageInfo;
		}

	@Override
	public String[][] getExportPopertyArray() {

		if (GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			return SpecialGroupsContext.getAidspopulationsImportArraySlf();
		} else {
			return SpecialGroupsContext.getAidspopulationsImportArrayRla();
		}

	}

	@Override
	public List<Aidspopulations> searchAidspopulationsForExport(
			SearchAidspopulationsVo searchAidspopulationsVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {

		List<Aidspopulations> list = searchAidspopulationsDao
				.searchAidspopulationsForExport(searchAidspopulationsVo,
						pageNum, pageSize, sidx, sord);
		if (GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			return list;
		} else {
			if (null != list && list.size() > 0) {
				ActualPopulation actualPopulation = null;
				for (Aidspopulations aidspopulations : list) {
					actualPopulation = actualPopulationProcessorService
							.getActualPopulationbyOrgIdAndIdCardNo(
									aidspopulations.getOrganization().getId(),
									aidspopulations.getIdCardNo());
					if (null != actualPopulation) {
						aidspopulations.setHouseCode(actualPopulation
								.getHouseCode());
						aidspopulations.setNoHouseReason(actualPopulation
								.getNoHouseReason());
					}
				}
			}
			return list;
		}
	}

	@Override
	public Integer getCount(SearchAidspopulationsVo searchAidspopulationsVo) {
		// TODO Auto-generated method stub
		return searchAidspopulationsDao.getCount(searchAidspopulationsVo);
	}

}
