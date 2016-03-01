package com.tianque.baseInfo.scenicManage.scenicTraffic.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.scenicManage.scenicTraffic.domain.ScenicTraffic;
import com.tianque.baseInfo.scenicManage.scenicTraffic.vo.SearchScenicTrafficVo;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;

/**
 * @ClassName: ScenicTrafficDAOImpl
 * @Description: 景区交通
 * @author wangxiaohu wsmalltiger@163.com
 * @date Oct 31, 2013 11:34:12 AM
 */
@Repository("scenicTrafficDao")
public class ScenicTrafficDAOImpl extends AbstractBaseDao implements
		ScenicTrafficDAO {

	@Override
	public ScenicTraffic addScenicTraffic(ScenicTraffic scenicTraffic) {
		if (scenicTraffic != null) {
			scenicTraffic.setCreateUser(ThreadVariable.getUser().getName());
			scenicTraffic.setSourcesState(0L);
		}
		Long id = (Long) getSqlMapClientTemplate().insert(
				"scenicTraffic.addScenicTraffic", scenicTraffic);
		return getScenicTrafficById(id);
	}

	@Override
	public void deleteScenicTrafficById(Long id) {
		getSqlMapClientTemplate().delete(
				"scenicTraffic.deleteScenicTrafficById", id);
	}

	@Override
	public ScenicTraffic getScenicTrafficById(Long id) {
		return (ScenicTraffic) getSqlMapClientTemplate().queryForObject(
				"scenicTraffic.getScenicTrafficById", id);
	}

	@Override
	public PageInfo searchScenicTrafficForPage(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchScenicTrafficVo scenicTraffic) {
		Integer countNum = getCount(scenicTraffic);
		if (countNum == 0) {
			return createPageInfo(0, pageSize, 0,
					new ArrayList<ScenicTraffic>());
		}
		if (scenicTraffic != null) {
			scenicTraffic.setSortField(sortField);
			scenicTraffic.setOrder(order);
		}
		int pageCount = countNum % pageSize == 0 ? countNum / pageSize
				: countNum / pageSize + 1;
		List<ScenicTraffic> list;
		if (pageNum != null) {
			pageNum = pageNum > pageCount ? pageCount : pageNum;
			list = getSqlMapClientTemplate().queryForList(
					"searchScenicTraffic.searchScenicTraffic", scenicTraffic,
					(pageNum - 1) * pageSize, pageSize);
		} else {
			pageNum = 1;
			list = getSqlMapClientTemplate().queryForList(
					"searchScenicTraffic.searchScenicTraffic", scenicTraffic);
		}
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public void updateEmphasiseById(Long id, Boolean emphasis,
			String logoutReason, Date logoutTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isEmphasis", emphasis);
		map.put("logOutReason", logoutReason);
		map.put("logOutTime", logoutTime);
		map.put("id", id);
		map.putAll(getUpdateDateAndUser());
		getSqlMapClientTemplate().update("scenicTraffic.updateEmphasiseById",
				map);
	}

	@Override
	public ScenicTraffic updateScenicTraffic(ScenicTraffic scenicTraffic) {
		if (scenicTraffic != null) {
			scenicTraffic.setUpdateUser(ThreadVariable.getUser().getName());
		}
		getSqlMapClientTemplate().update("scenicTraffic.updateScenicTraffic",
				scenicTraffic);
		return getScenicTrafficById(scenicTraffic.getId());
	}

	private PageInfo<ScenicTraffic> createPageInfo(Integer pageNum,
			Integer pageSize, Integer countNum, List<ScenicTraffic> list) {
		PageInfo<ScenicTraffic> pageInfo = new PageInfo<ScenicTraffic>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public Integer getCount(SearchScenicTrafficVo searchScenicTrafficVo) {
		if (null == searchScenicTrafficVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate()
				.queryForObject("searchScenicTraffic.countScenicTraffic",
						searchScenicTrafficVo);
	}

}
