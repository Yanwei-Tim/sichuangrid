package com.tianque.plugin.taskList.daoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.taskList.dao.PropagandaAndVerificationDao;
import com.tianque.plugin.taskList.domain.PropagandaAndVerification;
import com.tianque.plugin.taskList.domain.PropagandaAndVerificationVo;

/**
 * 宣传核查dao实现
 * 
 * @author GAOHU
 *
 */
@Repository("propagandaAndVerificationDao")
public class PropagandaAndVerificationDaoImpl extends AbstractBaseDao implements
		PropagandaAndVerificationDao {

	@Override
	public PropagandaAndVerification getPropagandaAndVerificationById(Long id) {
		return (PropagandaAndVerification) getSqlMapClientTemplate()
				.queryForObject(
						"propagandaAndVerification.getPropagandaAndVerificationById",
						id);
	}

	@Override
	public PropagandaAndVerification addPropagandaAndVerification(
			PropagandaAndVerification propagandaAndVerification) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"propagandaAndVerification.addPropagandaAndVerification",
				propagandaAndVerification);
		return getPropagandaAndVerificationById(id);
	}

	@Override
	public void deletePropagandaAndVerification(List<Long> ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		getSqlMapClientTemplate()
				.delete("propagandaAndVerification.deletePropagandaAndVerificationByIds",
						map);

	}

	@Override
	public PropagandaAndVerification updatePropagandaAndVerification(
			PropagandaAndVerification propagandaAndVerification) {
		getSqlMapClientTemplate().update(
				"propagandaAndVerification.updatePropagandaAndVerification",
				propagandaAndVerification);
		return getPropagandaAndVerificationById(propagandaAndVerification
				.getId());

	}

	@Override
	public PageInfo<PropagandaAndVerification> searchPropagandaAndVerification(
			PropagandaAndVerificationVo propagandaAndVerificationVo,
			Integer pageNum, Integer pageSize, String sortField, String order) {

		PageInfo<PropagandaAndVerification> pageInfo = new PageInfo<PropagandaAndVerification>();

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"propagandaAndVerification.countPropagandaAndVerificationByVo",
				getConditionMapOut(propagandaAndVerificationVo, sortField,
						order));
		int pageCount = 0;
		if (pageSize != 0 && countNum > 0) {
			pageCount = (countNum - 1) / pageSize + 1;
		} else {
			pageNum = pageNum > pageCount ? pageCount : pageNum;
		}

		if (countNum > 0) {
			List<PropagandaAndVerification> list = getSqlMapClientTemplate()
					.queryForList(
							"propagandaAndVerification.searchPropagandaAndVerification",
							getConditionMapOut(propagandaAndVerificationVo,
									sortField, order),
							(pageNum - 1) * pageSize, pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<PropagandaAndVerification>());
		}
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setTotalRowSize(countNum);
		return pageInfo;

	}
	
	private Map getConditionMapOut(PropagandaAndVerificationVo condition,
			String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (condition == null) {
			return map;
		}
		map.put("fastSearchKeyWords", condition.getFastSearchKeyWords());
		map.put("orgInternalCode", condition.getOrgInternalCode());
		map.put("occurrenceDateStart", condition.getOccurrenceDateStart());
		map.put("occurrenceDateEnd", condition.getOccurrenceDateEnd());

		map.put("address", condition.getAddress());
		map.put("verificationReport", condition.getVerificationReport());
		map.put("propaganda", condition.getPropaganda());
		map.put("exceptionSituation", condition.getExceptionSituationId());
		map.put("sortField", sortField);
		map.put("order", order);
		map.put("mode", condition.getMode());
		map.put("funOrgId", condition.getFunOrgId());
		map.put("ishandle", condition.getIshandle());
		return map;
	}

	@Override
	public PropagandaAndVerification updatePropagandaAndVerificationSignDetail(
			PropagandaAndVerification propagandaAndVerification) {
		getSqlMapClientTemplate()
				.update("propagandaAndVerification.updatePropagandaAndVerificationSignDetail",
						propagandaAndVerification);
		return getPropagandaAndVerificationById(propagandaAndVerification
				.getId());
	}

	@Override
	public List<PropagandaAndVerification> findPropagandaAndVerificationByName(
			PropagandaAndVerificationVo propagandaAndVerificationVo) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("orgCode", propagandaAndVerificationVo.getOrganization().getOrgInternalCode());
		map.put("name", propagandaAndVerificationVo.getName());
		return getSqlMapClientTemplate().queryForList(
				"propagandaAndVerification.findPropagandaAndVerificationByName", map);
	}
	
	

}
