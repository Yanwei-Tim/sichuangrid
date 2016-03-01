package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.nurturesWomen.domain.NurturesWomen;
import com.tianque.baseInfo.qPersonnel.dao.SearchQPersonnelDao;
import com.tianque.baseInfo.qPersonnel.domain.QPersonnel;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchQPersonnelVo;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.service.SearchQPersonnelService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.IdCardUtil;

@Service("searchQPersonnelService")
@Transactional
public class SearchQPersonnelServiceImpl implements SearchQPersonnelService {

	@Autowired
	private SearchQPersonnelDao dao;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private ActualPopulationProcessorService actualPopulationProcessorService;
	@Autowired
	private PermissionDubboService permissionDubboService;

	@Override
	public PageInfo<QPersonnel> searchQPersonnel(
			SearchQPersonnelVo queryQopulation, int pageNum, int pageSize,
			String sortField, String order) {
		PageInfo<QPersonnel>  pageInfo = dao.searchQPersonnel(queryQopulation, pageNum, pageSize,
				sortField, order);
		return pageInfo=hiddenIdCard(pageInfo);
	}
	
	//隐藏身份证中间4位
	  private PageInfo<QPersonnel> hiddenIdCard(PageInfo<QPersonnel> pageInfo){
						//判断权限，有权限不隐藏
						if(permissionDubboService.
								isUserHasPermission(ThreadVariable.getUser().getId(), "isQPersonnelManagementNotHidCard")){
							return pageInfo;
						}
						List<QPersonnel> list = pageInfo.getResult();
						int index=0;
						for (QPersonnel verification:list) {
							verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
							list.set(index, verification);
							index++;
						}
						pageInfo.setResult(list);
						return pageInfo;
	}

	@Override
	public List<QPersonnel> searchQPersonnelForExport(
			SearchQPersonnelVo queryQopulation, Integer page, Integer rows,
			String sidx, String sord) {
		List<QPersonnel> list = dao.searchQPersonnelForExport(queryQopulation,
				page, rows, sidx, sord);
		if (GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			return list;
		} else {
			if (null != list && list.size() > 0) {
				ActualPopulation actualPopulation = null;
				for (QPersonnel dangerousGoodsPractitioner : list) {
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
			return SpecialGroupsContext.getQPersonnelSlf();
		} else {
			return SpecialGroupsContext.getQPersonnelArrayRla();
		}

	}

	@Override
	public Integer getCount(SearchQPersonnelVo personnelVo) {
		return dao.getCount(personnelVo);
	}
}
