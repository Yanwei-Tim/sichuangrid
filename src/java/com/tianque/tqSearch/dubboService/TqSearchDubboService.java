package com.tianque.tqSearch.dubboService;

import java.util.List;

import com.tianque.tqSearch.domain.BaseSearchSolrParams;
import com.tianque.tqSearch.domain.BaseSolrDocument;
import com.tianque.tqSearch.domain.TqSolrDocumentList;

public interface TqSearchDubboService {
	
	void addSolrIndex(BaseSolrDocument baseSolrDocument);

	void deleteSolrIndexById(List<String> ids, String type);

	void deleteAllSolrIndex(String type);

	void commitSolrIndex(String type);

	TqSolrDocumentList searchForSolrIndex(
			BaseSearchSolrParams baseSearchSolrParams, String type);
}
