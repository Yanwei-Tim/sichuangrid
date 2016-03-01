package com.tianque.baseInfo.optimalObject.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.baseInfo.optimalObject.domain.OptimalObject;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchOptimalObjectVo;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("optimalObjectDao")
public class OptimalObjectDaoImpl extends
		BaseInfoPopulationBaseDaoImpl<OptimalObject, SearchOptimalObjectVo>
		implements OptimalObjectDao {
	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.OptimalObjectDao#getOptimalObjectByIdAndIdCard(java.lang
	 * .Long, java.lang.String)
	 */
	@Override
	public OptimalObject getOptimalObjectByIdAndIdCardAndOrgId(Long id,
			List<String> idCardNoList, Long orgId) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("id", id);
		query.put("orgId", orgId);
		query.put("idCardNoList", idCardNoList);
		return (OptimalObject) this.getSqlMapClientTemplate().queryForObject(
				"optimalObject.getOptimalObjectByIdAndIdCard", query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.OptimalObjectDao#findOptimalObjectForPageByOrgInternalCode
	 * (java.lang.String, java.lang.Integer, java.lang.Integer,
	 * java.lang.String, java.lang.String, java.lang.Long)
	 */
	@Override
	public PageInfo<OptimalObject> findOptimalObjectForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long isEmphasis) {
		OptimalObject optimalObject = new OptimalObject();
		optimalObject.setOrgInternalCode(orgInternalCode);
		optimalObject.setIsEmphasis(isEmphasis);
		Integer countNum = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("optimalObject.countOptimalObjects",
						optimalObject);
		if (!StringUtils.isEmpty(sidx)) {
			optimalObject.setSortField(sidx);
		}
		optimalObject.setOrder(sord);
		List<OptimalObject> list = this.getSqlMapClientTemplate().queryForList(
				"optimalObject.findOptimalObjects", optimalObject,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.OptimalObjectDao#searchOptimalObjects(java.lang.Integer,
	 * java.lang.Integer, com.tianque.domain.vo.SearchOptimalObjectVo)
	 */
	@Override
	public PageInfo<OptimalObject> searchOptimalObjects(Integer pageNum,
			Integer pageSize, SearchOptimalObjectVo searchOptimalObjectVo) {
		if (null == searchOptimalObjectVo)
			return emptyOptimalObjectPage(pageSize);
		PageInfo<OptimalObject> pageInfo = new PageInfo<OptimalObject>();
		Integer countNum = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("optimalObject.countSearchOptimalobject",
						searchOptimalObjectVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		// pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<OptimalObject> list = this.getSqlMapClientTemplate()
					.queryForList("optimalObject.searchOptimalobject",
							searchOptimalObjectVo, (pageNum - 1) * pageSize,
							pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<OptimalObject>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.dao.OptimalObjectDao#searchAllOptimalObjects(
	 * com.tianque.domain.vo.SearchOptimalObjectVo)
	 */
	@Override
	public List<OptimalObject> searchAllOptimalObjects(
			SearchOptimalObjectVo searchOptimalObjectVo) {
		return getSqlMapClientTemplate().queryForList(
				"optimalObject.searchOptimalobject", searchOptimalObjectVo);
	}

	private PageInfo<OptimalObject> emptyOptimalObjectPage(int pageSize) {
		PageInfo<OptimalObject> pageInfo = new PageInfo<OptimalObject>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<OptimalObject>());
		return pageInfo;
	}

	private PageInfo<OptimalObject> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<OptimalObject> pageInfo = new PageInfo<OptimalObject>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public void updateEmphasiseById(Long long1, Long isEmphasis) {
		Map map = new HashMap();
		map.put("id", long1);
		map.put("isEmphasis", isEmphasis);
		getSqlMapClientTemplate().update("optimalObject.updateEmphasiseById",
				map);

		// if (isEmphasis.equals(IsEmphasis.Emphasis)) {
		// activeMQMessageSender.send(new BusinesPopulationMsg(get(long1),
		// OperateMode.LOG_ON));
		// }
		// if (isEmphasis.equals(IsEmphasis.IsNotEmphasis)) {
		// activeMQMessageSender.send(new BusinesPopulationMsg(get(long1),
		// OperateMode.LOG_OUT));
		// }

	}

	@Override
	public PageInfo fastSearchOptimalObject(
			SearchOptimalObjectVo searchOptimalObjectVo, Integer page,
			Integer rows, String sidx, String sord) {
		if (searchOptimalObjectVo == null) {
			return emptyOptimalObjectPage(rows);
		}
		searchOptimalObjectVo.setSortField(sidx);
		searchOptimalObjectVo.setOrder(sord);
		Integer countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject("optimalObject.countSearchOptimalobject",
						searchOptimalObjectVo);
		@SuppressWarnings("unchecked")
		List<Druggy> list = getSqlMapClientTemplate().queryForList(
				"optimalObject.searchOptimalobject", searchOptimalObjectVo,
				(page - 1) * rows, rows);
		return createPageInfo(page, rows, countNum, list);
	}

	@Override
	public void updateDeathAndLogoutStateById(Long long1, boolean death,
			Long logoutState) {
		Map map = new HashMap();
		map.put("id", long1);
		map.put("isEmphasis", logoutState);
		map.put("death", death);
		getSqlMapClientTemplate().update(
				"optimalObject.updateDeathAndLogoutStateById", map);

		// activeMQMessageSender.send(new BusinesPopulationMsg(get(long1),
		// OperateMode.CANCEL_DEATH));
	}

	@Override
	public Integer getCount(SearchOptimalObjectVo searchOptimalObjectVo) {
		if (null == searchOptimalObjectVo) {
			return 0;
		}
		return (Integer) this.getSqlMapClientTemplate()
				.queryForObject("optimalObject.countSearchOptimalobject",
						searchOptimalObjectVo);
	}

	@Override
	public void updateTableUpdateDateById(Long id) {
		super.updateTableUpdateDateById("optimalobjects", id);
	}

}
