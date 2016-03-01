package com.tianque.baseInfo.otherAttentionPersonnel.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.baseInfo.otherAttentionPersonnel.domain.OtherAttentionPersonnel;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchOtherAttentionPersonnelVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("otherAttentionPersonnelDao")
public class OtherAttentionPersonnelDaoImpl
		extends
		BaseInfoPopulationBaseDaoImpl<OtherAttentionPersonnel, SearchOtherAttentionPersonnelVo>
		implements OtherAttentionPersonnelDao {

	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	@Override
	public PageInfo<OtherAttentionPersonnel> findOtherAttentionPersonnelsForPageByOrgInternalCode(
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord, Long isEmphasis) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("isEmphasis", isEmphasis);
		map.put("populationType", BaseInfoTables.OTHERATTENTIONPERSONNEL_KEY);// 用于关联查询服务成员和管理记录
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"otherAttentionPersonnel.countOtherAttentionPersonnels", map);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<OtherAttentionPersonnel> list = getSqlMapClientTemplate()
				.queryForList(
						"otherAttentionPersonnel.findOtherAttentionPersonnels",
						map, (page - 1) * rows, rows);
		PageInfo<OtherAttentionPersonnel> pageInfo = new PageInfo<OtherAttentionPersonnel>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public void updateDeathAndLogoutStateById(Long id, boolean death,
			Long logoutState) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", logoutState);
		map.put("death", death);
		getSqlMapClientTemplate().update(
				"otherAttentionPersonnel.updateDeathAndLogoutStateById", map);
		// activeMQMessageSender.send(new BusinesPopulationMsg(get(id),
		// OperateMode.CANCEL_DEATH));

	}

	private boolean validateObjId(Long id) {
		if (null == id || id < 0L) {
			return false;
		}
		return true;
	}

	@Override
	public OtherAttentionPersonnel updateOtherAttentionPersonnelEmphasis(
			OtherAttentionPersonnel otherAttentionPersonnel) {
		if (!validateObjId(otherAttentionPersonnel.getId())) {
			throw new BusinessValidationException("更新其他人员关注时，id不合法，不能更新");
		}

		if (getSqlMapClientTemplate()
				.update("otherAttentionPersonnel.updateOtherAttentionPersonnelEmphasis",
						otherAttentionPersonnel) > 0) {
			otherAttentionPersonnel = get(otherAttentionPersonnel.getId());
			return otherAttentionPersonnel;
		}
		return null;
	}

	@Override
	public void updateTableUpdateDateById(Long id) {
		super.updateTableUpdateDateById("otherattentionpersonnels", id);
	}

}
