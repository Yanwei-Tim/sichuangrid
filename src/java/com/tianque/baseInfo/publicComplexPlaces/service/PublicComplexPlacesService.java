package com.tianque.baseInfo.publicComplexPlaces.service;

import com.tianque.baseInfo.publicComplexPlaces.domain.PublicComplexPlaces;
import com.tianque.core.vo.PageInfo;

public interface PublicComplexPlacesService {
	public PublicComplexPlaces addPublicComplexPlaces(PublicComplexPlaces place);

	public PublicComplexPlaces updatePublicComplexPlaces(PublicComplexPlaces place);

	public PublicComplexPlaces getPlaceById(Long id);

	public void deletePublicComplexPlacesByIds(Long[] ids);

	public PageInfo<PublicComplexPlaces> findPublicComplexPlacesForPageByOrgInternalCode(Long orgId, Integer page,
			Integer rows, String sidx, String sord, Boolean isEmphasis);

	public PublicComplexPlaces updateEmphasiseByIds(Long[] ids, PublicComplexPlaces location);

	public Boolean hasDuplicatePlace(Long orgId, String placeName, Long exceptedId);

	public PublicComplexPlaces updatePlaceByPlaceNameAndOrgId(String placeName, Long orgId,
			PublicComplexPlaces domain);

	public PublicComplexPlaces hasDuplicatePublicComplexPlaces(Long orgId, String placeName);
}
