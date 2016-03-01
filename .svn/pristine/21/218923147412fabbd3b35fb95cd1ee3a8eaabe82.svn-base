package com.tianque.baseInfo.dangerousGoodsPractitioner.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.baseInfo.dangerousGoodsPractitioner.domain.DangerousGoodsPractitioner;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("dangerousGoodsPractitionerDao")
public class DangerousGoodsPractitionerDaoImpl
		extends
		BaseInfoPopulationBaseDaoImpl<DangerousGoodsPractitioner, DangerousGoodsPractitioner>
		implements DangerousGoodsPractitionerDao {
	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	@Override
	public DangerousGoodsPractitioner updateDangerousGoodsPractitionerEmphasis(
			DangerousGoodsPractitioner dangerousGoodsPractitioner) {
		if (!validateObjId(dangerousGoodsPractitioner.getId())) {
			throw new BusinessValidationException("更新危险品从业人员关注时，id不合法，不能更新");
		}

		if (getSqlMapClientTemplate()
				.update("dangerousGoodsPractitioner.updateDangerousGoodsPractitionerEmphasis",
						dangerousGoodsPractitioner) > 0) {
			dangerousGoodsPractitioner = get(dangerousGoodsPractitioner.getId());
			return dangerousGoodsPractitioner;
		}
		return null;
	}

	@Override
	public DangerousGoodsPractitioner updateDangerousGoodsPractitionerEmphasisById(
			Long id, Long isEmphasis) {
		DangerousGoodsPractitioner domain = get(id);
		Map map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		int count = getSqlMapClientTemplate().update(
				"dangerousGoodsPractitioner.updateEmphasisById", map);
		if (count > 0) {
			// if (isEmphasis.equals(IsEmphasis.Emphasis)) {
			// activeMQMessageSender.send(new BusinesPopulationMsg(get(id),
			// OperateMode.LOG_ON));
			// }
			// if (isEmphasis.equals(IsEmphasis.IsNotEmphasis)) {
			// activeMQMessageSender.send(new BusinesPopulationMsg(get(id),
			// OperateMode.LOG_OUT));
			// }
			return domain;
		}
		return null;
	}

	@Override
	public void updateDeathAndLogoutStateById(Long id, Boolean death,
			Long logoutState) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", logoutState);
		map.put("death", death);
		getSqlMapClientTemplate()
				.update("dangerousGoodsPractitioner.updateDeathAndLogoutStateById",
						map);
		// activeMQMessageSender.send(new BusinesPopulationMsg(get(id),
		// OperateMode.CANCEL_DEATH));

	}

	@Override
	public PageInfo<DangerousGoodsPractitioner> findDangerousGoodsPractitionersForPageByOrgInternalCode(
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord, Long isEmphasis) {
		DangerousGoodsPractitioner dangerousGoodsPractitioner = new DangerousGoodsPractitioner();
		dangerousGoodsPractitioner.setOrgInternalCode(orgInternalCode);
		dangerousGoodsPractitioner.setSortField(sidx);
		dangerousGoodsPractitioner.setOrder(sord);
		dangerousGoodsPractitioner.setIsEmphasis(isEmphasis);
		dangerousGoodsPractitioner.setPopulationType(BaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"dangerousGoodsPractitioner.countDangerousGoodsPractitioners",
				dangerousGoodsPractitioner);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<DangerousGoodsPractitioner> list = getSqlMapClientTemplate()
				.queryForList(
						"dangerousGoodsPractitioner.findDangerousGoodsPractitioners",
						dangerousGoodsPractitioner, (page - 1) * rows, rows);
		PageInfo<DangerousGoodsPractitioner> pageInfo = new PageInfo<DangerousGoodsPractitioner>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	private boolean validateNotNull(
			DangerousGoodsPractitioner dangerousGoodsPractitioner) {
		if (null == dangerousGoodsPractitioner) {
			return false;
		}

		if (null == dangerousGoodsPractitioner.getName()) {
			return false;
		}

		if (null == dangerousGoodsPractitioner.getGender()
				|| null == dangerousGoodsPractitioner.getGender().getId()) {
			return false;
		}

		if (null == dangerousGoodsPractitioner.getIdCardNo()) {
			return false;
		}

		if (null == dangerousGoodsPractitioner.getOrganization()
				|| null == dangerousGoodsPractitioner.getOrganization().getId()) {
			return false;
		}

		if (null == dangerousGoodsPractitioner.getDangerousWorkingType()
				|| null == dangerousGoodsPractitioner.getDangerousWorkingType()
						.getId()) {
			return false;
		}
		return true;
	}

	private boolean validateObjId(Long id) {
		if (null == id || id < 0L) {
			return false;
		}
		return true;
	}

	@Override
	public void updateTableUpdateDateById(Long id) {
		super.updateTableUpdateDateById("dangerousgoodspractitioners", id);
	}

}
