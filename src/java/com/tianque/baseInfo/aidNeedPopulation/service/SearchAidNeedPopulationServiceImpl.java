package com.tianque.baseInfo.aidNeedPopulation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.aidNeedPopulation.dao.SearchAidNeedPopulationDao;
import com.tianque.baseInfo.aidNeedPopulation.domain.AidNeedPopulation;
import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchAidNeedPopulationVo;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.IdCardUtil;

@Service("searchAidNeedPopulationService")
public class SearchAidNeedPopulationServiceImpl implements
		SearchAidNeedPopulationService {
	@Autowired
	private SearchAidNeedPopulationDao searchAidNeedPopulationDao;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private ActualPopulationProcessorService actualPopulationProcessorService;
	@Autowired
	private PermissionDubboService permissionDubboService;

	@Override
	public PageInfo<AidNeedPopulation> searchAidNeedPopulation(
			SearchAidNeedPopulationVo AidNeedSearchCondition, int pageNum,
			int pageSize, String sortField, String order) {
		PageInfo<AidNeedPopulation> pageInfos = searchAidNeedPopulationDao.searchAidNeedPopulation(
				AidNeedSearchCondition, pageNum, pageSize, sortField, order);
		return pageInfos=hiddenIdCard(pageInfos);
	}
	//隐藏身份证中间4位
			private PageInfo<AidNeedPopulation> hiddenIdCard(PageInfo<AidNeedPopulation> pageInfo){
								//判断权限，有权限不隐藏
								if(permissionDubboService.
										isUserHasPermission(ThreadVariable.getUser().getId(), "isAidNeedPopulationManagementNotHidCard")){
									return pageInfo;
								}
								List<AidNeedPopulation> list = pageInfo.getResult();
								int index=0;
								for (AidNeedPopulation verification:list) {
									verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
									list.set(index, verification);
									index++;
								}
								pageInfo.setResult(list);
								return pageInfo;
			}
	@Override
	public List<AidNeedPopulation> searchAidNeedPopulationForExport(
			SearchAidNeedPopulationVo AidNeedSearchCondition, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		List<AidNeedPopulation> list = searchAidNeedPopulationDao
				.searchAidNeedPopulationForExport(AidNeedSearchCondition,
						pageNum, pageSize, sortField, order);
		if (GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			return list;
		} else {
			if (null != list && list.size() > 0) {
				ActualPopulation actualPopulation = null;
				for (AidNeedPopulation aidNeedPopulation : list) {
					actualPopulation = actualPopulationProcessorService
							.getActualPopulationbyOrgIdAndIdCardNo(
									aidNeedPopulation.getOrganization().getId(),
									aidNeedPopulation.getIdCardNo());
					if (null != actualPopulation) {
						aidNeedPopulation.setHouseCode(actualPopulation
								.getHouseCode());
						aidNeedPopulation.setNoHouseReason(actualPopulation
								.getNoHouseReason());
					}
				}
			}
			return list;
		}
	}

	@Override
	public String[][] getExportPopertyArray() {
		if (GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			return SpecialGroupsContext.getAidNeedPopulationPropertyArraySlf();
		} else {
			return SpecialGroupsContext.getAidNeedPopulationPropertyArrayRla();
		}
	}

	@Override
	public Integer getCount(SearchAidNeedPopulationVo searchAidNeedPopulationVo) {
		// TODO Auto-generated method stub
		return searchAidNeedPopulationDao.getCount(searchAidNeedPopulationVo);
	}

}
