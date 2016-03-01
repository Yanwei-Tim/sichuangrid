package com.tianque.baseInfo.scenicManage.praiseScenicSpot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.scenicManage.praiseScenicSpot.domain.PraiseScenicSpot;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;

@Repository("praiseScenicSpotDao")
public class PraiseScenicSpotDaoImpl extends AbstractBaseDao implements
		PraiseScenicSpotDao {

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	@SuppressWarnings("unchecked")
	private PageInfo<PraiseScenicSpot> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List list) {
		PageInfo<PraiseScenicSpot> pageInfo = new PageInfo<PraiseScenicSpot>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public PageInfo<PraiseScenicSpot> findPraiseScenicSpotList(
			PraiseScenicSpot praiseScenicSpot, Integer page, Integer rows,
			String sidx, String sord) {
		if (isStrotFieldAvaliable(sidx)) {
			praiseScenicSpot.setSortField(sidx);
			praiseScenicSpot.setOrder(sord);
		}
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"praiseScenicSpot.countPraiseScenicSpotList", praiseScenicSpot);
		List<PraiseScenicSpot> list = getSqlMapClientTemplate().queryForList(
				"praiseScenicSpot.findPraiseScenicSpotList", praiseScenicSpot,
				(page - 1) * rows, rows);
		return createPageInfo(page, rows, countNum, list);
	}

	@Override
	public PraiseScenicSpot addPraiseScenicSpot(
			PraiseScenicSpot praiseScenicSpot) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"praiseScenicSpot.addPraiseScenicSpot", praiseScenicSpot);
		return getPraiseScenicSpotById(id);
	}

	@Override
	public PraiseScenicSpot getPraiseScenicSpotById(Long id) {
		return (PraiseScenicSpot) getSqlMapClientTemplate().queryForObject(
				"praiseScenicSpot.getPraiseScenicSpotById", id);
	}
}
