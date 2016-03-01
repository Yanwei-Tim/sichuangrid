package com.tianque.baseInfo.scenicManage.scenicSpot.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.scenicManage.scenicSpot.domain.ScenicSpot;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.jms.OperateMode;
import com.tianque.jms.msg.SiteMsg;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("scenicSpotDao")
public class ScenicSpotDaoImpl extends AbstractBaseDao implements ScenicSpotDao {

	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	@SuppressWarnings("unchecked")
	private PageInfo<ScenicSpot> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<ScenicSpot> pageInfo = new PageInfo<ScenicSpot>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<ScenicSpot> findScenicSpotList(ScenicSpot scenicSpot,
			Integer pageNum, Integer pageSize, String sortField, String order,
			Boolean isEmphasis) {

		if (isStrotFieldAvaliable(sortField)) {
			scenicSpot.setSortField(sortField);
			scenicSpot.setOrder(order);
		}
		scenicSpot.setIsEmphasis(isEmphasis);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"scenicSpot.countScenicSpot", scenicSpot);
		List<ScenicSpot> list = getSqlMapClientTemplate().queryForList(
				"scenicSpot.findScenicSpotList", scenicSpot,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public ScenicSpot addScenicSpot(ScenicSpot scenicSpot) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"scenicSpot.addScenicSpot", scenicSpot);
		return getSimpleScenicSpotById(id);
	}

	@Override
	public void deleteScenicSpotById(Long id) {
//		SiteMsg msg = new SiteMsg(getSimpleScenicSpotById(id),
//				OperateMode.DELETE);
		getSqlMapClientTemplate().delete("scenicSpot.deleteScenicSpotById", id);
//		activeMQMessageSender.send(msg);
	}

	@Override
	public ScenicSpot updateScenicSpot(ScenicSpot scenicSpot) {
		getSqlMapClientTemplate().update("scenicSpot.updateScenicSpot",
				scenicSpot);
		return getSimpleScenicSpotById(scenicSpot.getId());
	}

	@Override
	public ScenicSpot getSimpleScenicSpotById(Long id) {
		return (ScenicSpot) getSqlMapClientTemplate().queryForObject(
				"scenicSpot.getSimpleScenicSpotById", id);
	}

	@Override
	public ScenicSpot findScenicSpotByNameAndOrgId(String name, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("orgId", orgId);
		return (ScenicSpot) getSqlMapClientTemplate().queryForObject(
				"scenicSpot.findScenicSpotByNameAndOrgId", map);
	}

	@Override
	public List<ScenicSpot> searchScenicSpotForExport(ScenicSpot scenicSpot,
			Integer pageNum, Integer pageSize, String sortField, String order) {
		if (isStrotFieldAvaliable(sortField)) {
			scenicSpot.setSortField(sortField);
			scenicSpot.setOrder(order);
		}

		if (pageNum == null) {
			return getSqlMapClientTemplate().queryForList(
					"scenicSpot.findScenicSpotList", scenicSpot);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"scenicSpot.findScenicSpotList", scenicSpot,
					(pageNum - 1) * pageSize, pageSize);
		}
	}

	public List findScenicSpotByNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode) {
		Map map = new HashMap();
		map.put("name", name);
		map.put("orgInternalCode", orgInternalCode);
		return getSqlMapClientTemplate().queryForList(
				"scenicSpot.findScenicSpotByNameAndPinyinAndOrgInternalCode",
				map);
	}

	@Override
	public ScenicSpot getScenicSpotByName(String name, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("orgId", orgId);
		ScenicSpot domain = (ScenicSpot) getSqlMapClientTemplate()
				.queryForObject("scenicSpot.getScenicSpotByName", map);
		return domain;
	}

	@Override
	public void updateEmphasiseById(Long id, Boolean isEmphasis,
			String logoutReason, Date logoutDate) {
		Map<String, Object> map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		map.put("logOutReason", logoutReason);
		map.put("logOutTime", logoutDate);
		map.putAll(getUpdateDateAndUser());
		getSqlMapClientTemplate().update("scenicSpot.updateEmphasiseById", map);

	}

	@Override
	public Integer getCount(ScenicSpot scenicSpot) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"scenicSpot.countScenicSpot", scenicSpot);
	}

}
