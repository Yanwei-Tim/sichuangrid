package com.tianque.baseInfo.rectificativePerson.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchRectificativePersonVo;

@Repository("rectificativePersonDao")
public class RectificativepersonDaoImpl
		extends
		BaseInfoPopulationBaseDaoImpl<RectificativePerson, SearchRectificativePersonVo>
		implements RectificativePersonDao {

	@Override
	public void updateDeathAndLogoutStateById(Long id, Boolean death,
			Long logoutState) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", logoutState);
		map.put("death", death);
		getSqlMapClientTemplate().update(
				"rectificativePerson.updateDeathAndLogoutStateById", map);
	}

	@Override
	public PageInfo<RectificativePerson> findRectificativePersonsForPageByOrgInternalCode(
			String orgInternalCode, int pageNum, int pageSize,
			String sortField, String order, Long isEmphasis) {
		if (orgInternalCode == null || "".equals(orgInternalCode.trim())) {
			return emptyPage(pageSize);
		}
		RectificativePerson rectificativePerson = new RectificativePerson();
		rectificativePerson.setOrgInternalCode(orgInternalCode);
		rectificativePerson.setIsEmphasis(isEmphasis);
		if (StringUtil.isStringAvaliable(sortField)) {
			rectificativePerson.setSortField(sortField);
		}
		rectificativePerson.setOrder(order);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"rectificativePerson.countRectificativePersonsForPage",
				rectificativePerson);
		@SuppressWarnings("unchecked")
		List<RectificativePerson> list = getSqlMapClientTemplate()
				.queryForList(
						"rectificativePerson.findRectificativePersonsForPage",
						rectificativePerson, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<RectificativePerson> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List<RectificativePerson> list) {
		PageInfo<RectificativePerson> pageInfo = new PageInfo<RectificativePerson>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private PageInfo<RectificativePerson> emptyPage(int pageSize) {
		PageInfo<RectificativePerson> pageInfo = new PageInfo<RectificativePerson>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<RectificativePerson>());
		return pageInfo;
	}

	@Override
	public RectificativePerson updateEmphasiseById(Long id, Long isEmphasis) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		getSqlMapClientTemplate().update(
				"rectificativePerson.updateEmphasiseById", map);
		return get(id);
	}

	@Override
	public List<RectificativePerson> findRectificativePersonByRectifyDate() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("attentionPopulationType",
				BaseInfoTables.RECTIFICATIVEPERSON_KEY);
		return getSqlMapClientTemplate().queryForList(
				"rectificativePerson.findRectificativePersonByRectifyDate");
	}

	@Override
	public List<RectificativePerson> findRectificativePersonByIsRemind(
			Long remindTime) {
		Map map = new HashMap();
		map.put("attentionPopulationType",
				BaseInfoTables.RECTIFICATIVEPERSON_KEY);
		map.put("remindTime", remindTime);
		return getSqlMapClientTemplate().queryForList(
				"rectificativePerson.findRectificativePersonByIsRemind", map);
	}

	@Override
	public RectificativePerson updateIsRemindByid(Long id) {
		Map map = new HashMap();
		map.put("id", id);
		getSqlMapClientTemplate().update(
				"rectificativePerson.updateIsRemindByid", map);
		return get(id);

	}

	@Override
	public void updateTableUpdateDateById(Long id) {
		super.updateTableUpdateDateById("rectificativepersons", id);
	}

}
