package com.tianque.baseInfo.goodSamaritan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.goodSamaritan.dao.SearchGoodSamaritanDao;
import com.tianque.baseInfo.goodSamaritan.domain.GoodSamaritan;
import com.tianque.baseInfo.goodSamaritan.domain.SearchGoodSamaritanVo;
import com.tianque.baseInfo.nurturesWomen.domain.NurturesWomen;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.IdCardUtil;

@Service("searchGoodSamaritanService")
@Transactional
public class SearchGoodSamaritanServiceImpl implements
		SearchGoodSamaritanService {
	@Autowired
	private SearchGoodSamaritanDao dao;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private ActualPopulationProcessorService actualPopulationProcessorService;
	@Autowired
	private PermissionDubboService permissionDubboService;

	@Override
	public PageInfo<GoodSamaritan> searchGoodSamaritan(
			SearchGoodSamaritanVo queryQopulation, int pageNum, int pageSize,
			String sortField, String order) {
		PageInfo<GoodSamaritan> pageInfo = dao.searchGoodSamaritan(queryQopulation, pageNum, pageSize,
				sortField, order);
		return pageInfo=hiddenIdCard(pageInfo);
	}
	
	//隐藏身份证中间4位
	  private PageInfo<GoodSamaritan> hiddenIdCard(PageInfo<GoodSamaritan> pageInfo){
						//判断权限，有权限不隐藏
						if(permissionDubboService.
								isUserHasPermission(ThreadVariable.getUser().getId(), "isGoodSamaritanManagementNotHidCard")){
							return pageInfo;
						}
						List<GoodSamaritan> list = pageInfo.getResult();
						int index=0;
						for (GoodSamaritan verification:list) {
							verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
							list.set(index, verification);
							index++;
						}
						pageInfo.setResult(list);
						return pageInfo;
	}

	@Override
	public List<GoodSamaritan> searchGoodSamaritanForExport(
			SearchGoodSamaritanVo queryQopulation, Integer page, Integer rows,
			String sidx, String sord) {
		List<GoodSamaritan> list = dao.searchGoodSamaritanForExport(
				queryQopulation, page, rows, sidx, sord);
		if (GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			return list;
		} else {
			if (null != list && list.size() > 0) {
				ActualPopulation actualPopulation = null;
				for (GoodSamaritan dangerousGoodsPractitioner : list) {
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
			return SpecialGroupsContext.getGoodSamaritanSlf();
		} else {
			return SpecialGroupsContext.getGoodSamaritanArrayRla();
		}

	}

	@Override
	public Integer getCount(SearchGoodSamaritanVo personnelVo) {
		return dao.getCount(personnelVo);
	}
}
