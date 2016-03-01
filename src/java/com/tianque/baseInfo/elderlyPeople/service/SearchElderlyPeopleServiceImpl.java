package com.tianque.baseInfo.elderlyPeople.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.elderlyPeople.dao.SearchElderlyPeopleDao;
import com.tianque.baseInfo.elderlyPeople.domain.ElderlyPeople;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchElderlyPeopleVo;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.shard.util.ShardConversion;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.IdCardUtil;

@Service("searchElderlyPeopleService")
public class SearchElderlyPeopleServiceImpl implements
		SearchElderlyPeopleService {

	@Autowired
	private SearchElderlyPeopleDao searchElderlyPeopleDao;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private ActualPopulationProcessorService actualPopulationProcessorService;
	@Autowired
	private ShardConversion shardConversion;
	@Autowired
	private PermissionDubboService permissionDubboService;

	@Override
	public PageInfo<ElderlyPeople> searchElderlyPeople(
			SearchElderlyPeopleVo elderlyPeopleCondition, int pageNum,
			int pageSize, String sortField, String order) {
		String shardCode = shardConversion
				.getShardCodeByOrgCode(elderlyPeopleCondition
						.getOrgInternalCode());
		elderlyPeopleCondition.setShardCode(shardCode);
		PageInfo<ElderlyPeople>  pageInfo = searchElderlyPeopleDao.searchElderlyPeople(
				elderlyPeopleCondition, pageNum, pageSize, sortField, order);
		return pageInfo=hiddenIdCard(pageInfo);

	}
	
	//隐藏身份证中间4位
		private PageInfo<ElderlyPeople> hiddenIdCard(PageInfo<ElderlyPeople> pageInfo){
							//判断权限，有权限不隐藏
							if(permissionDubboService.
									isUserHasPermission(ThreadVariable.getUser().getId(), "isElderlyPeopleManagementNotHidCard")){
								return pageInfo;
							}
							List<ElderlyPeople> list = pageInfo.getResult();
							int index=0;
							for (ElderlyPeople verification:list) {
								verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
								list.set(index, verification);
								index++;
							}
							pageInfo.setResult(list);
							return pageInfo;
		}

	@Override
	public List<ElderlyPeople> searchElderlyPeopleForExport(
			SearchElderlyPeopleVo elderlyPeopleCondition, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		String shardCode = shardConversion
				.getShardCodeByOrgCode(elderlyPeopleCondition
						.getOrgInternalCode());
		elderlyPeopleCondition.setShardCode(shardCode);
		List<ElderlyPeople> list = searchElderlyPeopleDao
				.searchElderlyPeopleForExport(elderlyPeopleCondition, pageNum,
						pageSize, sortField, order);
		if (GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			return list;
		} else {
			if (null != list && list.size() > 0) {
				ActualPopulation actualPopulation = null;
				for (ElderlyPeople elderlyPeople : list) {
					actualPopulation = actualPopulationProcessorService
							.getActualPopulationbyOrgIdAndIdCardNo(
									elderlyPeople.getOrganization().getId(),
									elderlyPeople.getIdCardNo());
					if (null != actualPopulation) {
						elderlyPeople.setHouseCode(actualPopulation
								.getHouseCode());
						elderlyPeople.setNoHouseReason(actualPopulation
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
			return SpecialGroupsContext.getElderlyPeoplePropertyArraySlf();
		} else {
			return SpecialGroupsContext.getElderlyPeoplePropertyArrayRla();
		}
	}

	@Override
	public Integer getCount(SearchElderlyPeopleVo elderlyPeopleCondition) {
		String shardCode = shardConversion
				.getShardCodeByOrgCode(elderlyPeopleCondition
						.getOrgInternalCode());
		elderlyPeopleCondition.setShardCode(shardCode);
		return searchElderlyPeopleDao.getCount(elderlyPeopleCondition);
	}

}
