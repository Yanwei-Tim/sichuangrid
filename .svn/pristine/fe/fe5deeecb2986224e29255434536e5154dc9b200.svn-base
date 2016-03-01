package com.tianque.solr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.solr.domain.DocumentType;
import com.tianque.solr.service.SolrService;
import com.tianque.solr.util.SolrServerFactory;
import com.tianque.solr.util.SolrServerType;

@Service("solrService")
public class SolrServiceImpl implements SolrService {

	@Autowired
	private GlobalSettingService globalSettingService;

	@Override
	public SolrDocument getDocumentById(String id, DocumentType type,
			SolrServerType solrServerType) {
		SolrServer solrServer = SolrServerFactory.getSolrServer(solrServerType,
				globalSettingService.getGlobalValue(GlobalSetting.SOLR_URL));
		SolrQuery query = new SolrQuery();
		query.setQuery("key:" + type + "-" + id);
		try {
			QueryResponse queryResponse = solrServer.query(query);
			SolrDocumentList solrDocumentList = queryResponse.getResults();
			if (solrDocumentList.size() != 0) {
				return solrDocumentList.get(0);
			}
		} catch (SolrServerException e) {
			throw new ServiceValidationException("solr查询错误！", e);
		}
		return null;
	}

	@Override
	public List<SolrDocument> findSolrDocumentsByTagAndRows(String tag,
			int rows, SolrServerType solrServerType) {
		SolrServer solrServer = SolrServerFactory.getSolrServer(solrServerType,
				globalSettingService.getGlobalValue(GlobalSetting.SOLR_URL));
		SolrQuery query = new SolrQuery();
		query.setQuery(tag);
		query.setStart(0);
		query.setRows(rows);
		List<SolrDocument> solrDocuments = new ArrayList<SolrDocument>();
		try {
			// sort
			QueryResponse queryResponse = solrServer.query(query);
			SolrDocumentList solrDocumentList = queryResponse.getResults();

			for (SolrDocument solrDocument : solrDocumentList) {
				solrDocuments.add(solrDocument);
			}
		} catch (SolrServerException e) {
			throw new ServiceValidationException("solr查询错误！", e);
		}
		return solrDocuments;
	}

	@Override
	public List<SolrDocument> getKeyPopulationDocumentByIdCardNo(
			String idCardNo, SolrServerType solrServerType) {
		SolrServer solrServer = SolrServerFactory.getSolrServer(solrServerType,
				globalSettingService.getGlobalValue(GlobalSetting.SOLR_URL));
		SolrQuery query = new SolrQuery();
		query.setQuery("idCardNo:" + idCardNo);
		QueryResponse queryResponse;
		try {
			queryResponse = solrServer.query(query);
			SolrDocumentList solrDocumentList = queryResponse.getResults();
			if (solrDocumentList.size() != 0) {
				return solrDocumentList;
			}
		} catch (SolrServerException e) {
			throw new ServiceValidationException("solr查询错误！", e);
		}
		return null;
	}

}
