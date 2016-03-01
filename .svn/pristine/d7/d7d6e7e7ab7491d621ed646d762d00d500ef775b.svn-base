package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.dao.SocialRelationDao;
import com.tianque.domain.SocialRelation;
import com.tianque.exception.base.BusinessValidationException;

@SuppressWarnings("unchecked")
@Repository("socialRelationDao")
public class SocialRelationDaoImpl extends AbstractBaseDao implements
		SocialRelationDao {

	@Override
	public SocialRelation addSocialRelation(SocialRelation socialRelation) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"socialRelation.addSocialRelation", socialRelation);
		return getSocialRelationById(id);
	}

	@Override
	public SocialRelation getSocialRelationById(Long id) {
		SocialRelation socialRelation = (SocialRelation) getSqlMapClientTemplate()
				.queryForObject("socialRelation.getSocialRelationById", id);
		return socialRelation;
	}

	@Override
	public List<SocialRelation> findSocialRelationForPageByTrampResidentId(
			Long trampResidentId, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("trampResidentId", trampResidentId.toString());

		if (StringUtil.isStringAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);

		List<SocialRelation> list = getSqlMapClientTemplate().queryForList(
				"socialRelation.findSocialRelations", map);

		return list;
	}

	@Override
	public SocialRelation updateSocialRelation(SocialRelation socialRelation) {
		if (socialRelation == null || socialRelation.getId() == null)
			throw new BusinessValidationException("参数错误");
		getSqlMapClientTemplate().update("socialRelation.updateSocialRelation",
				socialRelation);
		return getSocialRelationById(socialRelation.getId());
	}

	@Override
	public int deleteSocialRelation(Long id) {
		if (id == null) {
			return 0;
		}
		int count = getSqlMapClientTemplate().delete(
				"socialRelation.deleteSocialRelationById", id);
		return count;

	}

	@Override
	public SocialRelation getSocialRelationByIdCard(List<String> list,
			Long trampResidentId) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("trampResidentId", trampResidentId);
		query.put("idCardNoList", list);
		SocialRelation socialRelation = (SocialRelation) getSqlMapClientTemplate()
				.queryForObject("socialRelation.getSocialRelationByIdCard",
						query);
		return socialRelation;
	}

}
