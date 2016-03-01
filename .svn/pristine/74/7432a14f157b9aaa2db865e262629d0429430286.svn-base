package com.tianque.solr.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.solr.domain.CommonPopulationDocument;
import com.tianque.solr.domain.KeyPopulationDocument;

public interface SolrKeyPopulationService {

	public KeyPopulationDocument getKeyPopulationDocumentByIdCardNo(
			String idCardNo);

	public List<KeyPopulationDocument> findKeyPopulationDocumentListByTag(
			String tag, String orgInternalCode);

	public PageInfo<CommonPopulationDocument> findKeyPopulationDocumentPageByTag(
			String tag, String orgInternalCode, Integer pageNum,
			Integer pageSize);
}
