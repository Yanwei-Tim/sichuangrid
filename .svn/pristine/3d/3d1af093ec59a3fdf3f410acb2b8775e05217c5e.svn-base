package com.tianque.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.positiveInfo.dao.SearchPositiveInfosDao;
import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchPositiveInfoVo;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.service.SearchPositiveInfosService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.IdCardUtil;

@Service("searchPositiveInfosService")
public class SearchPositiveInfosServiceImpl implements
		SearchPositiveInfosService {
	@Autowired
	private SearchPositiveInfosDao searchPositiveInfosDao;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private ActualPopulationProcessorService actualPopulationProcessorService;
	@Autowired
	private PermissionDubboService permissionDubboService;

	@Override
	public PageInfo<PositiveInfo> searchAttentionPersonnel(
			SearchPositiveInfoVo searchPositiveInfosVo, int pageNum,
			int pageSize, String sortField, String order) {
		PageInfo<PositiveInfo> positiveInfoPageInfos = searchPositiveInfosDao.searchAttentionPersonnel(
				searchPositiveInfosVo, pageNum, pageSize, sortField, order);
		//隐藏身份证中间4位
		positiveInfoPageInfos=hiddenIdCard(positiveInfoPageInfos);
		return positiveInfoPageInfos;
	}

	//隐藏身份证中间4位
		private PageInfo<PositiveInfo> hiddenIdCard(PageInfo<PositiveInfo> pageInfo){
					//判断权限，有权限不隐藏
					if(permissionDubboService.
							isUserHasPermission(ThreadVariable.getUser().getId(), "isPositiveInfoManagementNotHidCard")){
						return pageInfo;
					}
					List<PositiveInfo> list = pageInfo.getResult();
					int index=0;
					for (PositiveInfo verification:list) {
						verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
						list.set(index, verification);
						index++;
					}
					pageInfo.setResult(list);
					return pageInfo;
			}
	@Override
	public List<PositiveInfo> searchPositiveInfoForExport(
			SearchPositiveInfoVo searchPositiveInfosVo, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		List<PositiveInfo> list = searchPositiveInfosDao
				.searchAttentionPersonnelForExport(searchPositiveInfosVo,
						pageNum, pageSize, sortField, order);
		if (GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			return list;
		} else {
			if (null != list && list.size() > 0) {
				ActualPopulation actualPopulation = null;
				for (PositiveInfo positiveInfo : list) {
					actualPopulation = actualPopulationProcessorService
							.getActualPopulationbyOrgIdAndIdCardNo(positiveInfo
									.getOrganization().getId(), positiveInfo
									.getIdCardNo());
					if (null != actualPopulation) {
						positiveInfo.setHouseCode(actualPopulation
								.getHouseCode());
						positiveInfo.setNoHouseReason(actualPopulation
								.getNoHouseReason());
					}
				}
			}
			return list;
		}
	}

	@Override
	public int getCountPositiveInfoByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map) {
		return searchPositiveInfosDao
				.getCountPositiveInfoByOrgInternalCodeAndMap(orgInternalCode,
						map);
	}

	@Override
	public List<PositiveInfo> getPositiveInfoByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map) {
		return searchPositiveInfosDao.getPositiveInfoByOrgInternalCodeAndMap(
				orgInternalCode, map);
	}

	@Override
	public List<PositiveInfo> findPositiveInfos() {
		return searchPositiveInfosDao.findPositiveInfos();
	}

	@Override
	public List<PositiveInfo> getPositiveInfoByOrgInternalCodeAndMap(
			Map<String, Object> map) {
		return searchPositiveInfosDao
				.getPositiveInfoByOrgInternalCodeAndMap(map);
	}

	@Override
	public String[][] getExportPopertyArray() {
		if (GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			return SpecialGroupsContext.getPositiveInfoPropertyArraySlf();
		} else {
			return SpecialGroupsContext.getPositiveInfoPropertyArrayRla();
		}
	}

	@Override
	public Integer getCount(SearchPositiveInfoVo searchPositiveInfoVo) {
		// TODO Auto-generated method stub
		return searchPositiveInfosDao.getCount(searchPositiveInfoVo);
	}

}
