package com.tianque.tqSearch.domain;

import org.apache.solr.common.SolrInputDocument;

import com.tianque.core.base.BaseDomain;

public class BaseSolrDocument extends BaseDomain {
	public static String GET = "get";

	private String documentId;

	private String type;

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SolrInputDocument getSolrInputDocument() throws Exception {
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", getDocumentId());
		return document;
	}
}
