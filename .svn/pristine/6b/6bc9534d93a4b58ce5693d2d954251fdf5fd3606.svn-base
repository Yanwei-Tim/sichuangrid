package com.tianque.solr.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.solr.domain.DocumentType;
import com.tianque.solr.domain.KeyPlaceDocument;
import com.tianque.solr.service.SolrKeyPlaceService;
import com.tianque.solr.service.SolrService;
import com.tianque.solr.util.SolrServerFactory;
import com.tianque.solr.util.SolrServerType;

@Service("keyPlaceService")
public class SolrKeyPlaceServiceImpl implements SolrKeyPlaceService {
	@Autowired
	private SolrService solrService;
	@Autowired
	private GlobalSettingService globalSettingService;

	@Override
	public List<KeyPlaceDocument> findKeyPlaceDocumentListByTag(String tag,
			String orgInternalCode) {
		List<SolrDocument> solrDocuments = solrService
				.findSolrDocumentsByTagAndRows(tag + "* AND orgInternalCode:"
						+ orgInternalCode + "*", 10, SolrServerType.KEY_PLACE);
		List<KeyPlaceDocument> keyPlaceDocuments = new ArrayList<KeyPlaceDocument>();
		for (SolrDocument solrDocument : solrDocuments) {
			keyPlaceDocuments.add(parseSolrDocument(solrDocument));
		}
		return keyPlaceDocuments;
	}

	private KeyPlaceDocument parseSolrDocument(SolrDocument solrDocument) {
		if (null == solrDocument) {
			return null;
		}
		KeyPlaceDocument keyPlaceDocument = new KeyPlaceDocument();
		keyPlaceDocument.setOrgId(Long.parseLong(solrDocument.getFieldValue(
				"orgId").toString()));
		keyPlaceDocument.setName((String) solrDocument.getFieldValue("name"));
		keyPlaceDocument.setAddress((String) solrDocument
				.getFieldValue("address"));
		keyPlaceDocument.setFullPinyin((String) solrDocument
				.getFieldValue("fullPinyin"));
		keyPlaceDocument.setSimplePinyin((String) solrDocument
				.getFieldValue("simplePinyin"));
		keyPlaceDocument.setId(Long.parseLong(solrDocument.getFieldValue("id")
				.toString()));
		keyPlaceDocument.setKey((String) solrDocument.getFieldValue("key"));
		keyPlaceDocument.setCreateDate((Date) solrDocument
				.getFieldValue("createDate"));
		keyPlaceDocument.setType((String) solrDocument.getFieldValue("type"));
		return keyPlaceDocument;
	}

	@Override
	public KeyPlaceDocument getKeyPlaceDocumentByIdName(String id,
			DocumentType documentType) {
		SolrDocument solrDocument = solrService.getDocumentById(id,
				documentType, SolrServerType.KEY_PLACE);
		return parseSolrDocument(solrDocument);
	}

	@Override
	public PageInfo<KeyPlaceDocument> findKeyPlaceDocumentPageByTag(String tag,
			String orgInternalCode, Integer pageNum, Integer pageSize) {
		SolrServer solrServer = SolrServerFactory.getSolrServer(
				SolrServerType.KEY_PLACE,
				globalSettingService.getGlobalValue(GlobalSetting.SOLR_URL));
		SolrQuery query = new SolrQuery();
		query.setQuery(tag + "* AND orgInternalCode:" + orgInternalCode + "*");
		query.setRows(pageSize);
		query.setStart((pageNum - 1) * pageSize);
		PageInfo<KeyPlaceDocument> page = new PageInfo<KeyPlaceDocument>();
		try {
			QueryResponse queryResponse = solrServer.query(query);
			SolrDocumentList solrDocumentList = queryResponse.getResults();

			List<KeyPlaceDocument> keyPlaceDocuments = new ArrayList<KeyPlaceDocument>();
			for (SolrDocument solrDocument : solrDocumentList) {
				keyPlaceDocuments.add(parseSolrDocument(solrDocument));
			}
			page.setResult(keyPlaceDocuments);
			page.setPerPageSize(pageSize);
			page.setTotalRowSize(solrDocumentList.getNumFound());
			page.setCurrentPage(pageNum);
		} catch (SolrServerException e) {
			throw new ServiceValidationException("solr查询错误！", e);
		}
		return page;
	}

}
