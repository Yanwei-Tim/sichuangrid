package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.druggy.dao.SearchDruggyDao;
import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchDruggyVo;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.service.SearchDruggyService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.IdCardUtil;

@Transactional
@Service("searchDruggyService")
public class SearchDruggyServiceImpl implements SearchDruggyService {

	@Autowired
	private SearchDruggyDao searchDruggyDao;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private ActualPopulationProcessorService actualPopulationProcessorService;
	@Autowired
	private PermissionDubboService permissionDubboService;

	@Override
	public List<Druggy> searchDruggysForExport(SearchDruggyVo searchDruggyVo,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		List<Druggy> list = null;
		try {
			list = searchDruggyDao.searchDruggysForExport(searchDruggyVo,
					pageNum, pageSize, sidx, sord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			return list;
		} else {
			try {
				if (null != list && list.size() > 0) {
					ActualPopulation actualPopulation = null;
					for (Druggy druggy : list) {
						actualPopulation = actualPopulationProcessorService
								.getActualPopulationbyOrgIdAndIdCardNo(druggy
										.getOrganization().getId(), druggy
										.getIdCardNo());
						if (null != actualPopulation) {
							druggy.setHouseCode(actualPopulation.getHouseCode());
							druggy.setNoHouseReason(actualPopulation
									.getNoHouseReason());
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
	}

	@Override
	public PageInfo<Druggy> fastSearchDruggy(SearchDruggyVo searchDruggyVo,
			Integer pageNum, Integer pageSize, String sortField, String order) {
		PageInfo<Druggy> druggy = searchDruggyDao.fastSearchDruggy(searchDruggyVo, pageNum,
				pageSize, sortField, order);
		 druggy=hiddenIdCard(druggy);
		 return druggy;
	}

	@Override
	public PageInfo<Druggy> findDruggysByQueryCondition(
			SearchDruggyVo searchDruggyVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		PageInfo<Druggy> pageInfos = searchDruggyDao.findDruggysByQueryCondition(searchDruggyVo,
				pageNum, pageSize, sidx, sord);
		//隐藏身份证中间4位
		pageInfos=hiddenIdCard(pageInfos);
		return pageInfos;
	}

	//隐藏身份证中间4位
		private PageInfo<Druggy> hiddenIdCard(PageInfo<Druggy> pageInfo){
							//判断权限，有权限不隐藏
							if(permissionDubboService.
									isUserHasPermission(ThreadVariable.getUser().getId(), "isDruggyManagementNotHidCard")){
								return pageInfo;
							}
							List<Druggy> list = pageInfo.getResult();
							int index=0;
							for (Druggy verification:list) {
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
			return SpecialGroupsContext.getDruggyPropertyArraySlf();
		} else {
			return SpecialGroupsContext.getDruggyPropertyArrayRla();
		}

	}

	@Override
	public Integer getCount(SearchDruggyVo searchDruggyVo) {
		return searchDruggyDao.getCount(searchDruggyVo);
	}
}
