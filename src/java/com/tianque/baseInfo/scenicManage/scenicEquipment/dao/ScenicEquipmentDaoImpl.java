package com.tianque.baseInfo.scenicManage.scenicEquipment.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.scenicManage.scenicEquipment.domain.ScenicEquipment;
import com.tianque.baseInfo.scenicManage.scenicEquipment.vo.SearchScenicEquipmentVo;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;

@Repository("scenicEquipmentDao")
public class ScenicEquipmentDaoImpl extends AbstractBaseDao implements
		ScenicEquipmentDao {

	@Override
	public ScenicEquipment addScenicEquipment(ScenicEquipment scenicEquipment) {
		Long id = null;
		id = (Long) getSqlMapClientTemplate().insert(
				"scenicEquipment.addScenicEquipment", scenicEquipment);
		return getScenicEquipmentById(id);
	}

	@Override
	public ScenicEquipment getScenicEquipmentById(Long id) {
		return (ScenicEquipment) getSqlMapClientTemplate().queryForObject(
				"scenicEquipment.getScenicEquipmentById", id);
	}

	@Override
	public void deleteScenicEquipmentById(Long id) {
		getSqlMapClientTemplate().delete(
				"scenicEquipment.deleteScenicEquipmentById", id);
	}

	@Override
	public ScenicEquipment updateScenicEquipment(ScenicEquipment scenicEquipment) {
		getSqlMapClientTemplate().update(
				"scenicEquipment.updateScenicEquipment", scenicEquipment);
		return getScenicEquipmentById(scenicEquipment.getId());
	}

	@Override
	public void updateEmphasiseById(Long id, Boolean isEmphasis,
			String logoutReason, Date logoutDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isEmphasis", isEmphasis);
		map.put("logOutReason", logoutReason);
		map.put("logOutTime", logoutDate);
		map.put("id", id);
		getSqlMapClientTemplate().update("scenicEquipment.updateEmphasiseById",
				map);
	}

	@Override
	public PageInfo<ScenicEquipment> searchScenicEquipmentForPage(
			Integer pageNum, Integer pageSize, String sortField, String order,
			SearchScenicEquipmentVo searchScenicEquipmentVo) {

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchScenicEquipment.countScenicEquipment",
				searchScenicEquipmentVo);
		if (countNum == 0) {
			return createPageInfo(0, pageSize, 0,
					new ArrayList<ScenicEquipment>());
		}
		if (searchScenicEquipmentVo != null) {
			searchScenicEquipmentVo.setSortField(sortField);
			searchScenicEquipmentVo.setOrder(order);
		}

		int pageCount = countNum % pageSize == 0 ? countNum / pageSize
				: countNum / pageSize + 1;
		List<ScenicEquipment> list;
		if (pageNum != null) {
			pageNum = pageNum > pageCount ? pageCount : pageNum;
			list = getSqlMapClientTemplate()
					.queryForList(
							"searchScenicEquipment.searchScenicEquipment",
							searchScenicEquipmentVo, (pageNum - 1) * pageSize,
							pageSize);
		} else {
			pageNum = 1;
			list = getSqlMapClientTemplate().queryForList(
					"searchScenicEquipment.searchScenicEquipment",
					searchScenicEquipmentVo);
		}
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<ScenicEquipment> createPageInfo(Integer pageNum,
			Integer pageSize, Integer countNum, List<ScenicEquipment> list) {
		PageInfo<ScenicEquipment> pageInfo = new PageInfo<ScenicEquipment>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public Integer getCount(SearchScenicEquipmentVo searchScenicEquipmentVo) {
		// TODO Auto-generated method stub
		if (null == searchScenicEquipmentVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchScenicEquipment.countScenicEquipment",
				searchScenicEquipmentVo);
	}

}
