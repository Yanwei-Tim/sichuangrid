package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.otherAttentionPersonnel.dao.SearchOtherAttentionPersonnelDao;
import com.tianque.baseInfo.otherAttentionPersonnel.domain.OtherAttentionPersonnel;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchOtherAttentionPersonnelVo;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.service.SearchOtherAttentionPersonnelService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.IdCardUtil;

@Service("searchOtherAttentionPersonnelService")
public class SearchOtherAttentionPersonnelServiceImpl implements
		SearchOtherAttentionPersonnelService {

	@Autowired
	private SearchOtherAttentionPersonnelDao dao;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private ActualPopulationProcessorService actualPopulationProcessorService;
	@Autowired
	private PermissionDubboService permissionDubboService;

	@Override
	public PageInfo<OtherAttentionPersonnel> searchOtherAttentionPersonnel(
			SearchOtherAttentionPersonnelVo queryQopulation, int pageNum,
			int pageSize, String sortField, String order) {
		PageInfo<OtherAttentionPersonnel>  pageInfo = dao.searchOtherAttentionPersonnel(queryQopulation, pageNum,
				pageSize, sortField, order);
		return pageInfo=hiddenIdCard(pageInfo);
		
	}

	//隐藏身份证中间4位
		private PageInfo<OtherAttentionPersonnel> hiddenIdCard(PageInfo<OtherAttentionPersonnel> pageInfo){
				//判断权限，有权限不隐藏
				if(permissionDubboService.
						isUserHasPermission(ThreadVariable.getUser().getId(), "isOtherAttentionPersonnelManagementNotHidCard")){
					return pageInfo;
				}
				List<OtherAttentionPersonnel> list = pageInfo.getResult();
				int index=0;
				for (OtherAttentionPersonnel verification:list) {
					verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
					list.set(index, verification);
					index++;
				}
				pageInfo.setResult(list);
				return pageInfo;
		}
	@Override
	public List<OtherAttentionPersonnel> searchOtherAttentionPersonnelForExport(
			SearchOtherAttentionPersonnelVo queryQopulation, Integer page,
			Integer rows, String sidx, String sord) {
		List<OtherAttentionPersonnel> list = dao
				.searchOtherAttentionPersonnelForExport(queryQopulation, page,
						rows, sidx, sord);
		if (GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			return list;
		} else {
			if (null != list && list.size() > 0) {
				ActualPopulation actualPopulation = null;
				for (OtherAttentionPersonnel dangerousGoodsPractitioner : list) {
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
			return SpecialGroupsContext.getOtherAttentionPersonnelSlf();
		} else {
			return SpecialGroupsContext.getOtherAttentionPersonnelArrayRla();
		}

	}

	@Override
	public Integer getCount(SearchOtherAttentionPersonnelVo personnelVo) {
		// TODO Auto-generated method stub
		return dao.getCount(personnelVo);
	}
}
