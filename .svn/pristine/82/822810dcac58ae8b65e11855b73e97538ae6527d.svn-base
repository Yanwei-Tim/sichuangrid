package com.tianque.baseInfo.elderlyPeople.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.baseInfo.elderlyPeople.domain.ElderlyPeople;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchElderlyPeopleVo;
import com.tianque.jms.sender.ActiveMQMessageSender;
import com.tianque.shard.util.IdConversionShardUtil;

@Repository("elderlyPeopleDao")
public class ElderlyPeopleDaoImpl extends
		BaseInfoPopulationBaseDaoImpl<ElderlyPeople, SearchElderlyPeopleVo>
		implements ElderlyPeopleDao {
	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	@Override
	public PageInfo<ElderlyPeople> findElderlyPeopleForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long isEmphasis) {
		ElderlyPeople elderlyPeople = new ElderlyPeople();
		elderlyPeople.setIsEmphasis(isEmphasis);
		elderlyPeople.setOrgInternalCode(orgInternalCode);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"elderlyPeople.countElderlyPeople", elderlyPeople);
		if (isStrotFieldAvaliable(sidx)) {
			elderlyPeople.setSortField(sidx);
		}
		elderlyPeople.setOrder(sord);
		List<ElderlyPeople> list = getSqlMapClientTemplate().queryForList(
				"elderlyPeople.findElderlyPeople", elderlyPeople,
				(pageNum - 1) * pageSize, pageSize);

		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<ElderlyPeople> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<ElderlyPeople> pageInfo = new PageInfo<ElderlyPeople>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	@Override
	public ElderlyPeople getElderlyPeopleByIdCardNoAndOrganizationId(
			String idCardNo, Long organizationId, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idCardNo", idCardNo);
		map.put("organizationId", organizationId);
		map.put("shardCode", shardCode);
		return (ElderlyPeople) getSqlMapClientTemplate().queryForObject(
				"elderlyPeople.getElderlyPeopleByIdCardNoAndOrganizationId",
				map);

	}

	@Override
	public void updateDeathAndLogoutStateById(Long id, Boolean death,
			Long logoutState) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", logoutState);
		map.put("isDeath", death);

		getSqlMapClientTemplate().update(
				"elderlyPeople.updateDeathAndLogoutStateById", map);

		// activeMQMessageSender.send(new BusinesPopulationMsg(get(id),
		// OperateMode.CANCEL_DEATH));

	}

	@Override
	public Long saveElderlyPeopleForJob(ElderlyPeople elderlyPeople) {
		Long id = (Long) getSqlMapClientTemplate().queryForObject(
				super.getSequence(elderlyPeople));
		id = IdConversionShardUtil.IdAdditionalShard(id,
				elderlyPeople.getShardCode());
		elderlyPeople.setId(id);
		getSqlMapClientTemplate().insert("elderlyPeople.addElderlyPeople",
				elderlyPeople);
		return id;
	}

	@Override
	public void updateTableUpdateDateById(Long id) {
		String shardCode = IdConversionShardUtil.getShardCodeById(id);
		super.updateTableUpdateDateById("elderlypeople_"+shardCode, id);
	}

}
