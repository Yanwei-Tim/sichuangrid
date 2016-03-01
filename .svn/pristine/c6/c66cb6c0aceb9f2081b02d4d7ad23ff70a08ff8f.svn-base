package com.tianque.tqSearch.domain;

import org.apache.solr.client.solrj.SolrQuery;

import com.tianque.core.base.BaseDomain;

public class BaseSearchSolrParams extends BaseDomain {
	public static String ASC = "asc";

	public SolrQuery getSolrQuery() {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.set("shards.tolerant", true);
		solrQuery.set("q", "*:*");
		return solrQuery;
	}
}
