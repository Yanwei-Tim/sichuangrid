package com.tianque.tqSearch.domain;

import java.util.ArrayList;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class TqSolrDocumentList implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private int numFound;
	private long start;
	private Float maxScore;
	private ArrayList<SolrDocument> results;

	public TqSolrDocumentList() {

	}

	public TqSolrDocumentList(SolrDocumentList solrDocumentList) {
		results = solrDocumentList;
		if (solrDocumentList == null) {
			numFound = 0;
			start = 0;
			maxScore = null;
		} else {
			numFound = Integer.valueOf(solrDocumentList.getNumFound() + "");
			start = solrDocumentList.getStart();
			maxScore = solrDocumentList.getMaxScore();
		}
	}

	public int getNumFound() {
		return numFound;
	}

	public void setNumFound(int numFound) {
		this.numFound = numFound;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public Float getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Float maxScore) {
		this.maxScore = maxScore;
	}

	public ArrayList<SolrDocument> getResults() {
		return results;
	}

	public void setResults(ArrayList<SolrDocument> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "{numFound=" + numFound + ",start=" + start
				+ (maxScore != null ? ",maxScore=" + maxScore : "") + ",docs="
				+ results != null ? results.toString() : null + "}";
	}

}
