package com.tianque.tqSearch.domain;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;

public class IssueSearchSolrParams extends BaseSearchSolrParams {
	private SolrQuery solrQuery;

	public IssueSearchSolrParams() {

	}

	public IssueSearchSolrParams(Integer start, Integer rows, String sidx,
			String sord) {
		solrQuery = super.getSolrQuery();
		solrQuery.setStart(start);
		solrQuery.setRows(rows);
		if (ASC.equalsIgnoreCase(sord)) {
			solrQuery.addSortField(sidx, ORDER.asc);
		} else {
			solrQuery.addSortField(sidx, ORDER.desc);
		}
	}

	public IssueSearchSolrParams setSolrQuery(String query) {
		solrQuery.setQuery(query);
		return this;
	}

	public SolrQuery getSolrQuery() {
		return solrQuery;
	}

}
