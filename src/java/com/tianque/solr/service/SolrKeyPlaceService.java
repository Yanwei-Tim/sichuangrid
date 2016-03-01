package com.tianque.solr.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.solr.domain.DocumentType;
import com.tianque.solr.domain.KeyPlaceDocument;

public interface SolrKeyPlaceService {

	public List<KeyPlaceDocument> findKeyPlaceDocumentListByTag(String tag,
			String orgInternalCode);

	public PageInfo<KeyPlaceDocument> findKeyPlaceDocumentPageByTag(String tag,
			String orgInternalCode, Integer pageNum, Integer pageSize);

	public KeyPlaceDocument getKeyPlaceDocumentByIdName(String name,
			DocumentType documentType);
}
