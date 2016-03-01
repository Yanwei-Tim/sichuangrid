package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.fPersonnel.dao.SearchFPersonnelDao;
import com.tianque.baseInfo.fPersonnel.domain.FPersonnel;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchFPersonnelVo;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.service.SearchFPersonnelService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.IdCardUtil;

@Service("searchFPersonnelService")
@Transactional
public class SearchFPersonnelServiceImpl implements SearchFPersonnelService {

	@Autowired
	private SearchFPersonnelDao dao;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private ActualPopulationProcessorService actualPopulationProcessorService;
	@Autowired
	private PermissionDubboService permissionDubboService;

	@Override
	public PageInfo<FPersonnel> searchFPersonnel(
			SearchFPersonnelVo queryQopulation, int pageNum, int pageSize,
			String sortField, String order) {
		PageInfo<FPersonnel>  pageInfo = dao.searchFPersonnel(queryQopulation, pageNum, pageSize,
				sortField, order);
		return pageInfo=hiddenIdCard(pageInfo);
	}
	
	//隐藏身份证中间4位
	  private PageInfo<FPersonnel> hiddenIdCard(PageInfo<FPersonnel> pageInfo){
						//判断权限，有权限不隐藏
						if(permissionDubboService.
								isUserHasPermission(ThreadVariable.getUser().getId(), "isFPersonnelManagementNotHidCard")){
							return pageInfo;
						}
						List<FPersonnel> list = pageInfo.getResult();
						int index=0;
						for (FPersonnel verification:list) {
							verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
							list.set(index, verification);
							index++;
						}
						pageInfo.setResult(list);
						return pageInfo;
	}

	@Override
	public List<FPersonnel> searchFPersonnelForExport(
			SearchFPersonnelVo queryQopulation, Integer page, Integer rows,
			String sidx, String sord) {
		List<FPersonnel> list = dao.searchFPersonnelForExport(queryQopulation,
				page, rows, sidx, sord);
		if (GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			return list;
		} else {
			if (null != list && list.size() > 0) {
				ActualPopulation actualPopulation = null;
				for (FPersonnel dangerousGoodsPractitioner : list) {
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
			return SpecialGroupsContext.getFPersonnelSlf();
		} else {
			return SpecialGroupsContext.getFPersonnelArrayRla();
		}

	}

	@Override
	public Integer getCount(SearchFPersonnelVo personnelVo) {
		return dao.getCount(personnelVo);
	}
}
