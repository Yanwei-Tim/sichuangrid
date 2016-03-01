package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.dangerousGoodsPractitioner.dao.SearchDangerousGoodsPractitionerDao;
import com.tianque.baseInfo.dangerousGoodsPractitioner.domain.DangerousGoodsPractitioner;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchDangerousGoodsPractitioner;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.service.SearchDangerousGoodsPractitionerService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.IdCardUtil;

@Service("searchDangerousGoodsPractitionerService")
public class SearchDangerousGoodsPractitionerServiceImpl implements
		SearchDangerousGoodsPractitionerService {

	@Autowired
	private SearchDangerousGoodsPractitionerDao dao;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private ActualPopulationProcessorService actualPopulationProcessorService;
	@Autowired
	private PermissionDubboService permissionDubboService;

	@Override
	public PageInfo<DangerousGoodsPractitioner> searchDangerousGoodsPractitioner(
			SearchDangerousGoodsPractitioner queryQopulation, int pageNum,
			int pageSize, String sortField, String order) {
		PageInfo<DangerousGoodsPractitioner> dangerousGoodsPractitioner = dao.searchDangerousGoodsPractitioner(queryQopulation, pageNum,
				pageSize, sortField, order);
		return dangerousGoodsPractitioner=hiddenIdCard(dangerousGoodsPractitioner);
	}
	
	//隐藏身份证中间4位
		private PageInfo<DangerousGoodsPractitioner> hiddenIdCard(PageInfo<DangerousGoodsPractitioner> pageInfo){
				//判断权限，有权限不隐藏
				if(permissionDubboService.
						isUserHasPermission(ThreadVariable.getUser().getId(), "isDangerousGoodsPractitionerManagementNotHidCard")){
					return pageInfo;
				}
				List<DangerousGoodsPractitioner> list = pageInfo.getResult();
				int index=0;
				for (DangerousGoodsPractitioner verification:list) {
					verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
					list.set(index, verification);
					index++;
				}
				pageInfo.setResult(list);
				return pageInfo;
		}

	@Override
	public List<DangerousGoodsPractitioner> searchDangerousGoodsPractitionerForExport(
			SearchDangerousGoodsPractitioner queryQopulation, Integer page,
			Integer rows, String sidx, String sord) {
		List<DangerousGoodsPractitioner> list = dao
				.searchDangerousGoodsPractitionerForExport(queryQopulation,
						page, rows, sidx, sord);
		if (GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			return list;
		} else {
			if (null != list && list.size() > 0) {
				ActualPopulation actualPopulation = null;
				for (DangerousGoodsPractitioner dangerousGoodsPractitioner : list) {
					actualPopulation = actualPopulationProcessorService
							.getActualPopulationbyOrgIdAndIdCardNo(
									dangerousGoodsPractitioner
											.getOrganization().getId(),
									dangerousGoodsPractitioner.getIdCardNo());
					if (null != actualPopulation) {
						dangerousGoodsPractitioner
								.setHouseCode(actualPopulation.getHouseCode());
						dangerousGoodsPractitioner
								.setNoHouseReason(actualPopulation
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
			return SpecialGroupsContext
					.getDangerousGoodsPractitionerPropertyArraySlf();
		} else {
			return SpecialGroupsContext
					.getDangerousGoodsPractitionerPropertyArrayRla();
		}

	}

	@Override
	public Integer getCount(SearchDangerousGoodsPractitioner practitionerVo) {
		// TODO Auto-generated method stub
		return dao.getCount(practitionerVo);
	}
}
