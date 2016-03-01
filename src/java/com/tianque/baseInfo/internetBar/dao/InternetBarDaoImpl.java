package com.tianque.baseInfo.internetBar.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.internetBar.domain.InternetBar;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchInternetBarVo;
import com.tianque.jms.OperateMode;
import com.tianque.jms.msg.SiteMsg;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("internetBarDao")
public class InternetBarDaoImpl extends AbstractBaseDao implements
		InternetBarDao {

	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	@Override
	public InternetBar addInternetBar(InternetBar internetBar) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"internetBar.addInternetBar", internetBar);
		return getInternetBarById(id);
	}

	@Override
	public InternetBar getInternetBarById(Long id) {
		return (InternetBar) getSqlMapClientTemplate().queryForObject(
				"internetBar.getInternetBarById", id);
	}

	@Override
	public void deleteInternetBarById(Long id) {
//		SiteMsg msg = new SiteMsg(getInternetBarById(id), OperateMode.DELETE);
		getSqlMapClientTemplate().delete("internetBar.deleteInternetBarById",
				id);
//		activeMQMessageSender.send(msg);
	}

	@Override
	public InternetBar updateInternetBar(InternetBar internetBar) {
		getSqlMapClientTemplate().update("internetBar.updateInternetBar",
				internetBar);
		return getInternetBarById(internetBar.getId());
	}

	@Override
	public void updateEmphasiseById(Long id, Boolean isEmphasis,
			String logoutReason, Date logoutDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isEmphasis", isEmphasis);
		map.put("logOutReason", logoutReason);
		map.put("logOutTime", logoutDate);
		map.put("id", id);
		getSqlMapClientTemplate()
				.update("internetBar.updateEmphasiseById", map);
	}

	@Override
	public InternetBar getInternetBarByPlaceNameAndOrgId(String placeName,
			Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("placeName", placeName);
		map.put("orgId", id);
		return (InternetBar) getSqlMapClientTemplate().queryForObject(
				"internetBar.getInternetBarByMap", map);
	}

	@Override
	public PageInfo<InternetBar> searchInternetBarForPage(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchInternetBarVo searchInternetBarVo) {

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchInternetBar.countInternetBar", searchInternetBarVo);
		if (countNum == 0) {
			return createPageInfo(0, pageSize, 0, new ArrayList<InternetBar>());
		}
		if (searchInternetBarVo != null) {
			searchInternetBarVo.setSortField(sortField);
			searchInternetBarVo.setOrder(order);
		}

		int pageCount = countNum % pageSize == 0 ? countNum / pageSize
				: countNum / pageSize + 1;
		List<InternetBar> list;
		if (pageNum != null) {
			pageNum = pageNum > pageCount ? pageCount : pageNum;
			list = getSqlMapClientTemplate().queryForList(
					"searchInternetBar.searchInternetBar", searchInternetBarVo,
					(pageNum - 1) * pageSize, pageSize);
		} else {
			pageNum = 1;
			list = getSqlMapClientTemplate().queryForList(
					"searchInternetBar.searchInternetBar", searchInternetBarVo);
		}
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<InternetBar> createPageInfo(Integer pageNum,
			Integer pageSize, Integer countNum, List<InternetBar> list) {
		PageInfo<InternetBar> pageInfo = new PageInfo<InternetBar>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public Integer getCount(SearchInternetBarVo searchInternetBarVo) {
		// TODO Auto-generated method stub
		if (null == searchInternetBarVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchInternetBar.countInternetBar", searchInternetBarVo);
	}

}
