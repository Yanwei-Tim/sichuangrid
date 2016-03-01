package com.tianque.solr.service;

import java.util.List;

import org.apache.solr.common.SolrDocument;

import com.tianque.solr.domain.DocumentType;
import com.tianque.solr.util.SolrServerType;

public interface SolrService {

	public SolrDocument getDocumentById(String id, DocumentType type,
			SolrServerType solrServerType);

	public List<SolrDocument> findSolrDocumentsByTagAndRows(String tag,
			int rows, SolrServerType solrServerType);

	public List<SolrDocument> getKeyPopulationDocumentByIdCardNo(
			String idCardNo, SolrServerType solrServerType);

}
