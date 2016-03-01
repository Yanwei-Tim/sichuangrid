package com.tianque.baseInfo.handicapped.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.baseInfo.handicapped.domain.Handicapped;
import com.tianque.baseInfo.handicapped.domain.HandicappedSdisabilityType;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchHandicappedVo;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("handicappedDao")
public class HandicappedDaoImpl extends
		BaseInfoPopulationBaseDaoImpl<Handicapped, SearchHandicappedVo>
		implements HandicappedDao {
	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	@Override
	public PageInfo<Handicapped> findHandicappedsForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sortField, String order, Long isEmphasis) {
		Handicapped handicapped = new Handicapped();
		handicapped.setOrgInternalCode(orgInternalCode);
		handicapped.setSortField(sortField);
		handicapped.setOrder(order);
		handicapped.setIsEmphasis(isEmphasis);

		return findIdleYouthsForPageByObject(handicapped, pageNum, pageSize);
	}

	@SuppressWarnings("unchecked")
	private PageInfo<Handicapped> findIdleYouthsForPageByObject(
			Handicapped handicapped, Integer pageNum, Integer pageSize) {
		Integer countIdleYouth = (Integer) getSqlMapClientTemplate()
				.queryForObject("handicapped.countHandicappeds", handicapped);

		int pageCount = 0;
		if ((countIdleYouth % pageSize) == 0) {
			pageCount = countIdleYouth / pageSize;
		} else {
			pageCount = countIdleYouth / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		List<Handicapped> list = (List<Handicapped>) getSqlMapClientTemplate()
				.queryForList("handicapped.findHandicappeds", handicapped,
						(pageNum - 1) * pageSize, pageSize);

		PageInfo<Handicapped> pageInfo = new PageInfo<Handicapped>();
		pageInfo.setResult(list);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setTotalRowSize(countIdleYouth);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public Handicapped getHandicappedByIdCardNoAndOrganizationId(
			String idCardNo15, String idCardNo18, Long organizationId) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idCardNo15", idCardNo15);
		map.put("idCardNo18", idCardNo18);
		map.put("organizationId", organizationId);
		return (Handicapped) getSqlMapClientTemplate().queryForObject(
				"handicapped.getHandicappedByIdCardNoAndOrganizationId", map);
	}

	@Override
	public PageInfo<Handicapped> searchHandicappeds(Integer pageNum,
			Integer pageSize, SearchHandicappedVo searchHandicappedVo) {

		int pageCount = 0;// 总页数

		Integer countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject("searchHandicapped.countSearchHandicapped",
						searchHandicappedVo);
		pageCount = countNum % pageSize == 0 ? countNum / pageSize : countNum
				/ pageSize + 1;

		// pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<Handicapped> list = new ArrayList<Handicapped>();
		if (countNum > 0) {
			list = getSqlMapClientTemplate().queryForList(
					"searchHandicapped.searchHandicapped", searchHandicappedVo,
					(pageNum - 1) * pageSize, pageSize);
		}

		return buildHandicappedPage(pageSize, countNum, pageNum, list);

	}

	@Override
	public PageInfo<Handicapped> searchHandicappedsForMobile(Integer pageNum,
			Integer pageSize, SearchHandicappedVo searchHandicappedVo) {

		int pageCount = 0;// 总页数

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchHandicapped.countSearchHandicappedForMobile",
				searchHandicappedVo);
		pageCount = countNum % pageSize == 0 ? countNum / pageSize : countNum
				/ pageSize + 1;

		pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<Handicapped> list = new ArrayList<Handicapped>();
		if (countNum > 0) {
			list = getSqlMapClientTemplate().queryForList(
					"searchHandicapped.searchHandicappedForMobile",
					searchHandicappedVo, (pageNum - 1) * pageSize, pageSize);
		}

		return buildHandicappedPage(pageSize, countNum, pageNum, list);

	}

	/**
	 * 生成pageInfo对象
	 * 
	 * @param pageSize
	 * @param rowSize
	 * @param pageNum
	 * @param list
	 * @return
	 */
	private PageInfo<Handicapped> buildHandicappedPage(int pageSize,
			int rowSize, int pageNum, List<Handicapped> list) {
		PageInfo<Handicapped> pageInfo = new PageInfo<Handicapped>();
		pageInfo.setTotalRowSize(rowSize);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(list);
		return pageInfo;
	}

	@Override
	public void updateEmphasiseById(Long id, Long isEmphasis) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		getSqlMapClientTemplate()
				.update("handicapped.updateEmphasiseById", map);
		Handicapped handicapped = get(id);
		// if (isEmphasis.equals(IsEmphasis.Emphasis)) {
		// activeMQMessageSender.send(new BusinesPopulationMsg(handicapped,
		// OperateMode.LOG_ON));
		// }
		// if (isEmphasis.equals(IsEmphasis.IsNotEmphasis)) {
		// activeMQMessageSender.send(new BusinesPopulationMsg(handicapped,
		// OperateMode.LOG_OUT));
		// }

	}

	@Override
	public List<Handicapped> searchAllHandicappeds(
			SearchHandicappedVo searchHandicappedVo) {
		return (List<Handicapped>) getSqlMapClientTemplate().queryForList(
				"searchHandicapped.searchHandicapped", searchHandicappedVo);
	}

	@Override
	public void updateDeathAndLogoutStateById(Long id, boolean death,
			Long logoutState) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("isEmphasis", logoutState);
		map.put("death", death);
		getSqlMapClientTemplate().update(
				"handicapped.updateDeathAndLogoutStateById", map);

		// activeMQMessageSender.send(new BusinesPopulationMsg(get(id),
		// OperateMode.CANCEL_DEATH));

	}

	@Override
	public Integer getCount(SearchHandicappedVo searchHandicappedVo) {
		if (null == searchHandicappedVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate()
				.queryForObject("searchHandicapped.countSearchHandicapped",
						searchHandicappedVo);
	}

	@Override
	public void deleteDisbilityType(
			HandicappedSdisabilityType handicappedSdisabilityType) {
		getSqlMapClientTemplate().delete("handicapped.deleteDisbilityType",
				handicappedSdisabilityType);
	}

	@Override
	public void saveDisabilityType(
			HandicappedSdisabilityType handicappedSdisabilityType) {
		getSqlMapClientTemplate().insert("handicapped.saveDisabilityType",
				handicappedSdisabilityType);
	}

	@Override
	public List<HandicappedSdisabilityType> queryDisabilityLevelById(
			HandicappedSdisabilityType handicappedSdisabilityType) {
		return getSqlMapClientTemplate().queryForList(
				"handicapped.queryDisabilityLevelById",
				handicappedSdisabilityType);
	}

	@Override
	public void saveHandicappedSdisabilityTypeToReal(
			HandicappedSdisabilityType handicappedSdisabilityType) {
		getSqlMapClientTemplate().insert(
				"handicapped.saveHandicappedSdisabilityTypeToReal",
				handicappedSdisabilityType);
	}

	@Override
	public void updateTableUpdateDateById(Long id) {
		super.updateTableUpdateDateById("handicappeds", id);
	}

}
