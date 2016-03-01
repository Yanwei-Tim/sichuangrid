package com.tianque.baseInfo.publicPlace.service;

import com.tianque.baseInfo.publicPlace.domain.PublicPlace;
import com.tianque.core.vo.PageInfo;

public interface PublicPlaceService {
	public PublicPlace addPublicPlace(PublicPlace place);

	public PublicPlace updatePublicPlace(PublicPlace place);

	public PublicPlace getPlaceById(Long id);

	public void deletePublicPlaceByIds(Long[] ids);

	public PageInfo<PublicPlace> findPublicPlaceForPageByOrgInternalCode(Long orgId, Integer page,
			Integer rows, String sidx, String sord, Boolean isEmphasis);

	public PublicPlace updateEmphasiseByIds(Long[] ids, PublicPlace location);

	public Boolean hasDuplicatePlace(Long orgId, String placeName, Long exceptedId);

	public PublicPlace updatePlaceByPlaceNameAndOrgId(String placeName, Long orgId,
			PublicPlace domain);

	public PublicPlace hasDuplicatePublicPlace(Long orgId, String placeName);
}
