package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.mPersonnel.dao.SearchMPersonnelDao;
import com.tianque.baseInfo.mPersonnel.domain.MPersonnel;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchMPersonnelVo;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.service.SearchMPersonnelService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.IdCardUtil;

@Service("searchMPersonnelService")
@Transactional
public class SearchMPersonnelServiceImpl implements SearchMPersonnelService {

	@Autowired
	private SearchMPersonnelDao dao;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private ActualPopulationProcessorService actualPopulationProcessorService;
	@Autowired
	private PermissionDubboService permissionDubboService;

	@Override
	public PageInfo<MPersonnel> searchMPersonnel(
			SearchMPersonnelVo queryQopulation, int pageNum, int pageSize,
			String sortField, String order) {
		PageInfo<MPersonnel>  pageInfo = dao.searchMPersonnel(queryQopulation, pageNum, pageSize,
				sortField, order);
		return pageInfo=hiddenIdCard(pageInfo);
	}

	//隐藏身份证中间4位
	  private PageInfo<MPersonnel> hiddenIdCard(PageInfo<MPersonnel> pageInfo){
						//判断权限，有权限不隐藏
						if(permissionDubboService.
								isUserHasPermission(ThreadVariable.getUser().getId(), "isMPersonnelManagementNotHidCard")){
							return pageInfo;
						}
						List<MPersonnel> list = pageInfo.getResult();
						int index=0;
						for (MPersonnel verification:list) {
							verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
							list.set(index, verification);
							index++;
						}
						pageInfo.setResult(list);
						return pageInfo;
	}
	@Override
	public List<MPersonnel> searchMPersonnelForExport(
			SearchMPersonnelVo queryQopulation, Integer page, Integer rows,
			String sidx, String sord) {
		List<MPersonnel> list = dao.searchMPersonnelForExport(queryQopulation,
				page, rows, sidx, sord);
		if (GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			return list;
		} else {
			if (null != list && list.size() > 0) {
				ActualPopulation actualPopulation = null;
				for (MPersonnel dangerousGoodsPractitioner : list) {
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
			return SpecialGroupsContext.getMPersonnelSlf();
		} else {
			return SpecialGroupsContext.getMPersonnelArrayRla();
		}

	}

	@Override
	public Integer getCount(SearchMPersonnelVo personnelVo) {
		return dao.getCount(personnelVo);
	}
}
