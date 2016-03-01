package com.tianque.platformMessage.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.domain.SearchPlatformMessageVo;

@Repository("searchPlatformMessageDao")
public class SearchPlatformMessageDaoImpl extends AbstractBaseDao implements
		SearchPlatformMessageDao {

	@Override
	public PageInfo<PlatformMessage> searchOutboxPlatformMessage(
			SearchPlatformMessageVo searchPlatformMessageVo, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		if (searchPlatformMessageVo == null || searchPlatformMessageVo.getSendUserId() == null
				|| searchPlatformMessageVo.getSendUserId() == 0) {
			return emptyPage(pageSize);
		}
		Integer countPersonnelMessages = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchPlatformMessage.countSearchOutboxPlatformMessage", searchPlatformMessageVo);
		int pageCount = 0;
		if (pageSize != 0 && countPersonnelMessages > 0)
			pageCount = (countPersonnelMessages - 1) / pageSize + 1;
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		searchPlatformMessageVo.setSortField(sortField);
		searchPlatformMessageVo.setOrder(order);

		List<PlatformMessage> list = this.getSqlMapClientTemplate().queryForList(
				"searchPlatformMessage.searchOutboxPlatformMessage", searchPlatformMessageVo,
				(pageNum - 1) * pageSize, pageSize);

		PageInfo<PlatformMessage> pageInfo = new PageInfo<PlatformMessage>();
		pageInfo.setResult(list);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setTotalRowSize(countPersonnelMessages);
		pageInfo.setCurrentPage(pageNum);
		return pageInfo;
	}

	@Override
	public PageInfo<PlatformMessage> searchInboxPlatformMessage(
			SearchPlatformMessageVo searchPlatformMessageVo, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		if (searchPlatformMessageVo == null
				|| searchPlatformMessageVo.getPlatformMessageTypes() == null
				|| searchPlatformMessageVo.getPlatformMessageTypes().size() < 1) {
			return emptyPage(pageSize);
		}
		Integer countPersonnelMessages = (Integer) getSqlMapClientTemplate().queryForObject(
				"platformMessageNew.countSearchInboxPlatformMessage", searchPlatformMessageVo);
		int pageCount = 0;
		if (pageSize != 0 && countPersonnelMessages > 0)
			pageCount = (countPersonnelMessages - 1) / pageSize + 1;
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		searchPlatformMessageVo.setSortField(sortField);
		searchPlatformMessageVo.setOrder(order);

		List<PlatformMessage> list = this.getSqlMapClientTemplate().queryForList(
				"platformMessageNew.searchInboxPlatformMessage", searchPlatformMessageVo,
				(pageNum - 1) * pageSize, pageSize);

		PageInfo<PlatformMessage> pageInfo = new PageInfo<PlatformMessage>();
		pageInfo.setResult(list);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setTotalRowSize(countPersonnelMessages);
		pageInfo.setCurrentPage(pageNum);
		return pageInfo;
	}

	private PageInfo<PlatformMessage> emptyPage(int pageSize) {
		PageInfo<PlatformMessage> pageInfo = new PageInfo<PlatformMessage>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PlatformMessage>());
		return pageInfo;
	}

}
