package com.tianque.dao;

import java.util.List;

import com.tianque.domain.SocialRelation;

public interface SocialRelationDao {

	public SocialRelation addSocialRelation(SocialRelation socialRelation);

	public SocialRelation updateSocialRelation(SocialRelation updateSocialRelation);

	public int deleteSocialRelation(Long id);

	public SocialRelation getSocialRelationById(Long id);

	public SocialRelation getSocialRelationByIdCard(List<String> list, Long trampResidentId);

	public List<SocialRelation> findSocialRelationForPageByTrampResidentId(Long trampResidentId,
			String sidx, String sord);

}
