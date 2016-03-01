package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.idleYouth.dao.SearchIdleYouthDao;
import com.tianque.baseInfo.idleYouth.domain.IdleYouth;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchIdleYouthVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.IdCardUtil;

@Repository("searchIdleYouthDao")
public class SearchIdleYouthDaoImpl extends AbstractBaseDao implements
		SearchIdleYouthDao {
	@Autowired
	private PermissionDubboService permissionDubboService;

	public PageInfo<IdleYouth> searchIdleYouth(SearchIdleYouthVo condition,
			int pageNum, int pageSize, String sortField, String order) {
		if (condition == null)
			return emptyIdleYouthPage(pageSize);
		condition.setSortField(sortField);
		condition.setOrder(order);
		PageInfo<IdleYouth> pageInfo = new PageInfo<IdleYouth>();

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIdleYouth.countSearchIdleYouth", condition);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		// pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<IdleYouth> list = getSqlMapClientTemplate().queryForList(
					"searchIdleYouth.searchIdleYouth", condition,
					(pageNum - 1) * pageSize, pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<IdleYouth>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		//隐藏身份证中间4位
		pageInfo=hiddenIdCard(pageInfo);
		return pageInfo;
	}
	
	//隐藏身份证中间4位
		private PageInfo<IdleYouth> hiddenIdCard(PageInfo<IdleYouth> pageInfo){
				//判断权限，有权限不隐藏
				if(permissionDubboService.
						isUserHasPermission(ThreadVariable.getUser().getId(), "isIdleYouthManagementNotHidCard")){
					return pageInfo;
				}
				List<IdleYouth> list = pageInfo.getResult();
				int index=0;
				for (IdleYouth verification:list) {
					verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
					list.set(index, verification);
					index++;
				}
				pageInfo.setResult(list);
				return pageInfo;
		}

	public List<IdleYouth> findIdleYouth() {
		return getSqlMapClientTemplate().queryForList(
				"searchIdleYouth.findSearchIdleYouth");
	}

	private PageInfo<IdleYouth> emptyIdleYouthPage(int pageSize) {
		PageInfo<IdleYouth> pageInfo = new PageInfo<IdleYouth>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<IdleYouth>());
		return pageInfo;
	}

	@Override
	public List<IdleYouth> searchIdleYouthsForExport(
			SearchIdleYouthVo idleYouthSearchCondition, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		idleYouthSearchCondition.setSortField(sortField);
		idleYouthSearchCondition.setOrder(order);
		List<IdleYouth> idleYouthList = null;
		if (pageNum == null) {
			idleYouthList = getSqlMapClientTemplate()
					.queryForList("searchIdleYouth.searchIdleYouth",
							idleYouthSearchCondition);
		} else {
			idleYouthList = getSqlMapClientTemplate().queryForList(
					"searchIdleYouth.searchIdleYouth",
					idleYouthSearchCondition, (pageNum - 1) * pageSize,
					pageSize);
		}
		return idleYouthList;
	}

	public List findIdleYouthNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode) {
		Map map = new HashMap();
		map.put("name", name);
		map.put("orgInternalCode", orgInternalCode);
		return getSqlMapClientTemplate().queryForList(
				"searchIdleYouth.findIdleYouthNameAndPinyinAndOrgInternalCode",
				map);
	}

	@Override
	public int getCountIdleyouthByOrgInternalCodeAndMap(String orgInternalCode,
			Map<String, Object> map) {
		if (!StringUtil.isStringAvaliable(orgInternalCode)) {
			throw new BusinessValidationException("orgInternalCode不能为空");
		}
		map.put("orgInternalCode", orgInternalCode);
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchIdleYouth.getCountIdleyouthByOrgInternalCodeAndMap",
						map);
	}

	@Override
	public Integer getCount(SearchIdleYouthVo idleYouthVo) {
		// TODO Auto-generated method stub
		if (null == idleYouthVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIdleYouth.countSearchIdleYouth", idleYouthVo);

	}
}
