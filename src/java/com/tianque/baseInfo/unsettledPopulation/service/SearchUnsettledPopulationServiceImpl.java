package com.tianque.baseInfo.unsettledPopulation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.unsettledPopulation.dao.SearchUnsettledPopulationDao;
import com.tianque.baseInfo.unsettledPopulation.domain.UnsettledPopulation;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.BaseUnsettledPopulationSearchCondition;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.IdCardUtil;

@Service("searchUnsettledPopulationService")
public class SearchUnsettledPopulationServiceImpl implements SearchUnsettledPopulationService {

	@Autowired
	private SearchUnsettledPopulationDao searchUnsettledPopulationDao;
	@Autowired
	private PermissionDubboService permissionDubboService;

	@Override
	public PageInfo<UnsettledPopulation> searchUnsettledPopulation(
			BaseUnsettledPopulationSearchCondition condition, int pageNum, int pageSize,
			String sortField, String order) {
		PageInfo<UnsettledPopulation>  unsettledPopulation = searchUnsettledPopulationDao.searchUnsettledPopulation(condition, pageNum, pageSize,
				sortField, order);
		 unsettledPopulation=hiddenIdCard(unsettledPopulation);
		 return unsettledPopulation;
	}
	
	//隐藏身份证中间4位
		private PageInfo<UnsettledPopulation> hiddenIdCard(PageInfo<UnsettledPopulation> pageInfo){
				//判断权限，有权限不隐藏
				if(permissionDubboService.
						isUserHasPermission(ThreadVariable.getUser().getId(), "isUnsettledPopulationNotHidCard")){
					return pageInfo;
				}
				List<UnsettledPopulation> list = pageInfo.getResult();
				int index=0;
				for (UnsettledPopulation verification:list) {
					verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
					list.set(index, verification);
					index++;
				}
				pageInfo.setResult(list);
				return pageInfo;
		}

	@Override
	public PageInfo<UnsettledPopulation> fastSearchemptyUnsettledPopulationPage(
			BaseUnsettledPopulationSearchCondition condition, int pageNum, int pageSize,
			String sortField, String order) {
		PageInfo<UnsettledPopulation>  unsettledPopulation = searchUnsettledPopulationDao.fastSearchemptyUnsettledPopulationPage(condition,
				pageNum, pageSize, sortField, order);
		return unsettledPopulation=hiddenIdCard(unsettledPopulation);
	}

}
