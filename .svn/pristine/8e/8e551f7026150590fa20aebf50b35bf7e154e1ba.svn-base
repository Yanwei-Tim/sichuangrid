package com.tianque.service;

import java.util.List;

import com.tianque.domain.SocialRelation;

public interface SocialRelationService {

	public SocialRelation addSocialRelation(SocialRelation socialRelation);

	public SocialRelation getSocialRelationById(Long id);

	public int deleteSocialRelationById(Long id);

	public SocialRelation updateSocialRelation(SocialRelation socialRelation);

	public boolean hasDuplicateSocialRelation(Long trampResidentId, String idCardNo, Long exceptedId);

	public List<SocialRelation> findSocialRelationForPageByTrampResidentId(Long trampResidentId,
			String sidx, String sord);

}
