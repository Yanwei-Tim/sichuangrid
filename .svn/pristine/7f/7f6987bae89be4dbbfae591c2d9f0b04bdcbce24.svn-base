package com.tianque.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.OtherLocaleDao;
import com.tianque.domain.OtherLocale;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("otherLocaleDao")
public class OtherLocaleDaoImpl extends AbstractBaseDao implements
		OtherLocaleDao {

	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	@Override
	public OtherLocale addOtherLocale(OtherLocale otherLocale) {
		if (!validateNotNull(otherLocale)) {
			throw new BusinessValidationException("参数错误");
		}
		Long id = (Long) getSqlMapClientTemplate().insert(
				"otherLocale.addOtherLocale", otherLocale);
		return getOtherLocaleById(id);
	}

	@Override
	public OtherLocale getOtherLocaleById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("ID为空");
		}
		return (OtherLocale) getSqlMapClientTemplate().queryForObject(
				"otherLocale.getOtherLocaleById", id);
	}

	@Override
	public OtherLocale updateOtherLocale(OtherLocale otherLocale) {
		getSqlMapClientTemplate().update("otherLocale.updateOtherLocale",
				otherLocale);
		return this.getOtherLocaleById(otherLocale.getId());
	}

	public OtherLocale updateOtherLocaleById(OtherLocale otherLocale) {
		getSqlMapClientTemplate().update("otherLocale.updateOtherLocaleById",
				otherLocale);
		return this.getOtherLocaleById(otherLocale.getId());
	}

	@Override
	public int deleteOtherLocaleById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("ID为空");
		}
		// SiteMsg msg = new SiteMsg(getOtherLocaleById(id),
		// OperateMode.DELETE);
		int count = getSqlMapClientTemplate().delete(
				"otherLocale.deleteOtherLocaleById", id);
		// activeMQMessageSender.send(msg);
		return count;
	}

	public boolean validateNotNull(OtherLocale otherLocale) {
		if (null == otherLocale || null == otherLocale.getName()
				|| "".equals(otherLocale.getName().trim())) {
			return false;
		}
		if (null == otherLocale.getOrganization()) {
			return false;
		}
		return true;
	}

	@Override
	public PageInfo<OtherLocale> findOtherLocalesForPageByOrgInternalCode(
			String orgInternalCode, Integer pageSize, Integer pageNum,
			String sidx, String sord, Long isEmphasis) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("orgInternalCode", orgInternalCode);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("isEmphasis", isEmphasis);
		map.put("importantLocationType", "OTHERLOCALE");
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"otherLocale.countOtherLocales", map);

		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		List<OtherLocale> list = getSqlMapClientTemplate().queryForList(
				"otherLocale.findOtherLocales", map, (pageNum - 1) * pageSize,
				pageSize);

		PageInfo<OtherLocale> pageInfo = new PageInfo<OtherLocale>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);

		return pageInfo;
	}

	@Override
	public Integer countExsistedOtherLocale(String name, Long orgId, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("orgId", orgId);
		map.put("id", id);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"otherLocale.countExsistedOtherLocale", map);
		return countNum;
	}

	@Override
	public OtherLocale getOtherLocaleByName(String name, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("id", id);
		OtherLocale domain = (OtherLocale) getSqlMapClientTemplate()
				.queryForObject("otherLocale.getOtherLocaleByName", map);
		return domain;

	}

	@Override
	public void updateEmphasiseById(Long id, Long isEmphasis,
			String logoutReason, Date logoutDate) {
		Map<String, Object> map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		map.put("logOutReason", logoutReason);
		map.put("logOutTime", logoutDate);
		getSqlMapClientTemplate()
				.update("otherLocale.updateEmphasiseById", map);
	}

}
