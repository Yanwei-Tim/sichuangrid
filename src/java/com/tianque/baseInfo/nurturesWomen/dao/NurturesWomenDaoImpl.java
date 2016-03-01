package com.tianque.baseInfo.nurturesWomen.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.baseInfo.nurturesWomen.domain.NurturesWomen;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchNurturesWomenVo;
import com.tianque.jms.sender.ActiveMQMessageSender;
import com.tianque.service.helper.ManagePopulationByHouseHelper;
import com.tianque.shard.util.IdConversionShardUtil;

@Repository("nurturesWomenDao")
public class NurturesWomenDaoImpl extends
		BaseInfoPopulationBaseDaoImpl<NurturesWomen, SearchNurturesWomenVo>
		implements NurturesWomenDao {
	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;
	@Autowired
	private ManagePopulationByHouseHelper managePopulationByHouseHelper;

	@Override
	public PageInfo<NurturesWomen> findNurturesWomenForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sortField, String order, Long isEmphasis) {
		NurturesWomen nurturesWomen = new NurturesWomen();
		nurturesWomen.setOrgInternalCode(orgInternalCode);
		nurturesWomen.setIsEmphasis(isEmphasis);
		nurturesWomen.setSortField(sortField);
		nurturesWomen.setOrder(order);
		Integer countNurturesWomen = (Integer) getSqlMapClientTemplate()
				.queryForObject("nurturesWomen.countNurturesWomen",
						nurturesWomen);
		int pageCount = 0;
		if ((countNurturesWomen % pageSize) == 0) {
			pageCount = countNurturesWomen / pageSize;
		} else {
			pageCount = countNurturesWomen / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		List<NurturesWomen> listNurturesWomen = getSqlMapClientTemplate()
				.queryForList("nurturesWomen.findNurturesWomen", nurturesWomen,
						(pageNum - 1) * pageSize, pageSize);
		PageInfo<NurturesWomen> pageInfo = new PageInfo<NurturesWomen>();

		pageInfo.setResult(listNurturesWomen);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setTotalRowSize(countNurturesWomen);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public NurturesWomen getNurturesWomenByIdCardNoAndOrganizationId(
			String idCardNo, Long orgId, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idCardNo", idCardNo);
		map.put("orgId", orgId);
		map.put("shardCode", shardCode);
		return (NurturesWomen) getSqlMapClientTemplate().queryForObject(
				"nurturesWomen.getNurturesWomenByIdCardNoAndOrgId", map);
	}

	@Override
	public PageInfo<NurturesWomen> searchNurturesWomen(Integer pageNum,
			Integer pageSize, String sidx, String sord,
			SearchNurturesWomenVo searchNurturesWomenVo) {
		PageInfo<NurturesWomen> pageInfo = new PageInfo<NurturesWomen>();
		if (searchNurturesWomenVo == null) {
			pageInfo.setTotalRowSize(0);
			pageInfo.setCurrentPage(0);
			pageInfo.setPerPageSize(pageSize);
			pageInfo.setResult(new ArrayList<NurturesWomen>());
		} else {
			searchNurturesWomenVo.setSortField(sidx);
			searchNurturesWomenVo.setOrder(sord);
			Integer countNum = (Integer) getSqlMapClientTemplate()
					.queryForObject("nurturesWomen.countSearchNurturesWomen",
							searchNurturesWomenVo);
			int pageCount = 0;
			if ((countNum % pageSize) == 0) {
				pageCount = countNum / pageSize;
			} else {
				pageCount = countNum / pageSize + 1;
			}
			// pageNum = pageNum > pageCount ? pageCount : pageNum;
			if (countNum > 0) {
				List<NurturesWomen> list = getSqlMapClientTemplate()
						.queryForList("nurturesWomen.searchNurturesWomen",
								searchNurturesWomenVo,
								(pageNum - 1) * pageSize, pageSize);
				pageInfo.setResult(list);
			} else {
				pageInfo.setResult(new ArrayList<NurturesWomen>());
			}

			pageInfo.setTotalRowSize(countNum);
			pageInfo.setCurrentPage(pageNum);
			pageInfo.setPerPageSize(pageSize);
		}
		return pageInfo;
	}

	@Override
	public List<NurturesWomen> searchAllNurturesWomen(String sidx, String sord,
			SearchNurturesWomenVo searchNurturesWomenVo) {
		return getSqlMapClientTemplate().queryForList(
				"nurturesWomen.searchNurturesWomen", searchNurturesWomenVo);
	}

	@Override
	public void updateDeathAndLogoutStateById(Long id, Boolean death,
			Long logoutState) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("death", death);
		map.put("isEmphasis", logoutState);

		getSqlMapClientTemplate().update(
				"nurturesWomen.updateDeathAndLogoutStateById", map);

		// activeMQMessageSender.send(new BusinesPopulationMsg(get(id),
		// OperateMode.CANCEL_DEATH));

	}

	@Override
	public Integer getCount(SearchNurturesWomenVo searchNurturesWomenVo) {
		// TODO Auto-generated method stub
		if (null == searchNurturesWomenVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate()
				.queryForObject("nurturesWomen.countSearchNurturesWomen",
						searchNurturesWomenVo);
	}

	public Long saveNurturesWomenForJob(NurturesWomen nurturesWomen) {
		Long id = (Long) getSqlMapClientTemplate().queryForObject(
				super.getSequence(nurturesWomen));
		id = IdConversionShardUtil.IdAdditionalShard(id,
				nurturesWomen.getShardCode());
		nurturesWomen.setId(id);
		getSqlMapClientTemplate().insert("nurturesWomen.addNurturesWomen",
				nurturesWomen);
		return id;
	}

	@Override
	public void updateTableUpdateDateById(Long id) {
		String shardCode = IdConversionShardUtil.getShardCodeById(id);
		super.updateTableUpdateDateById("nurtureswomen_" + shardCode, id);
	}

}
