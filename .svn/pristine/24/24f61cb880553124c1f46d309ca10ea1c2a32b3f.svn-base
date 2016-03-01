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
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.solr.domain.CommonPopulationDocument;
import com.tianque.solr.domain.DocumentType;
import com.tianque.solr.domain.KeyPopulationDocument;
import com.tianque.solr.service.SolrKeyPopulationService;
import com.tianque.solr.service.SolrService;
import com.tianque.solr.util.SolrServerFactory;
import com.tianque.solr.util.SolrServerType;

@Service("keyPopulationService")
public class SolrKeyPopulationServiceImpl implements SolrKeyPopulationService {

	@Autowired
	private SolrService solrService;

	@Autowired
	private GlobalSettingService globalSettingService;

	@Override
	public KeyPopulationDocument getKeyPopulationDocumentByIdCardNo(
			String idCardNo) {
		List<SolrDocument> solrDocuments = solrService
				.getKeyPopulationDocumentByIdCardNo(idCardNo,
						SolrServerType.KEY_POPULATION);
		if (solrDocuments == null || solrDocuments.size() == 0) {
			return null;
		}
		return parseSolrDocument(solrDocuments.get(0));
	}

	private KeyPopulationDocument parseSolrDocument(SolrDocument solrDocument) {
		KeyPopulationDocument keyPopulationDocument = new KeyPopulationDocument();
		List<Long> listOrgIds = new ArrayList<Long>();
		List<Long> listIds = new ArrayList<Long>();
		List<DocumentType> listTypes = new ArrayList<DocumentType>();
		List<String> listOrgInternalCodes = new ArrayList<String>();
		if (solrDocument.getFieldValue("orgIds").getClass().getName()
				.toString().equals(ArrayList.class.getName().toString())) {
			listOrgIds = (List) solrDocument.getFieldValue("orgIds");
			listIds = (List) solrDocument.getFieldValue("ids");
			listTypes = (List) solrDocument.getFieldValue("types");
			listOrgInternalCodes = (List) solrDocument
					.getFieldValue("orgInternalCodes");
		} else {
			listOrgIds.add(Long.valueOf(solrDocument.getFieldValue("orgIds")
					.toString()));
			listIds.add(Long.valueOf(solrDocument.getFieldValue("ids")
					.toString()));
			listTypes.add(DocumentType.valueOf(DocumentType.class, solrDocument
					.getFieldValue("types").toString()));
			listOrgInternalCodes.add(solrDocument.getFieldValue(
					"orgInternalCodes").toString());
		}
		keyPopulationDocument.setName((String) solrDocument
				.getFieldValue("name"));
		keyPopulationDocument.setIdCardNo((String) solrDocument
				.getFieldValue("idCardNo"));
		keyPopulationDocument.setFullPinyin((String) solrDocument
				.getFieldValue("fullPinyin"));
		keyPopulationDocument.setSimplePinyin((String) solrDocument
				.getFieldValue("simplePinyin"));

		keyPopulationDocument = setKeyPopulationDocument(listIds, listOrgIds,
				listTypes, listOrgInternalCodes, keyPopulationDocument);

		return keyPopulationDocument;
	}

	private CommonPopulationDocument parseCommonSolrDocument(
			SolrDocument solrDocument) {
		if (null == solrDocument) {
			return null;
		}
		CommonPopulationDocument commonPopulationDocument = new CommonPopulationDocument(
				Enum.valueOf(DocumentType.class,
						(String) solrDocument.getFieldValue("type")));
		commonPopulationDocument.setOrgId(Long.parseLong(solrDocument
				.getFieldValue("orgId").toString()));
		commonPopulationDocument.setName((String) solrDocument
				.getFieldValue("name"));
		commonPopulationDocument.setAddress((String) solrDocument
				.getFieldValue("address"));
		commonPopulationDocument.setFullPinyin((String) solrDocument
				.getFieldValue("fullPinyin"));
		commonPopulationDocument.setFullPinyin((String) solrDocument
				.getFieldValue("orgInternalCode"));
		commonPopulationDocument.setSimplePinyin((String) solrDocument
				.getFieldValue("simplePinyin"));
		commonPopulationDocument.setId(Long.parseLong(solrDocument
				.getFieldValue("id").toString()));
		commonPopulationDocument.setKey((String) solrDocument
				.getFieldValue("key"));
		commonPopulationDocument.setOrgInternalCode((String) solrDocument
				.getFieldValue("orgInternalCode"));
		commonPopulationDocument.setIdCardNo((String) solrDocument
				.getFieldValue("idCardNo"));
		return commonPopulationDocument;

	}

	private KeyPopulationDocument setKeyPopulationDocument(List<Long> listIds,
			List<Long> listOrgIds, List<DocumentType> listTypes,
			List<String> listOrgInternalCodes, KeyPopulationDocument document) {
		Long[] ids = new Long[listIds.size()];
		Long[] orgIds = new Long[listIds.size()];
		DocumentType[] types = new DocumentType[listIds.size()];
		String[] orgInternalCodes = new String[listIds.size()];
		for (int i = 0; i < orgInternalCodes.length; i++) {
			String id = String.valueOf(listIds.get(i));
			String orgId = String.valueOf(listOrgIds.get(i));
			String documentType = String.valueOf(listTypes.get(i));
			ids[i] = Long.valueOf(id);
			orgIds[i] = Long.valueOf(orgId);
			types[i] = DocumentType.valueOf(documentType);
			orgInternalCodes[i] = listOrgInternalCodes.get(i);
		}
		document.setIds(ids);
		document.setOrgIds(orgIds);
		document.setTypes(types);
		document.setOrgInternalCodes(orgInternalCodes);
		return document;
	}

	@Override
	public List<KeyPopulationDocument> findKeyPopulationDocumentListByTag(
			String tag, String orgInternalCode) {
		List<SolrDocument> solrDocuments = solrService
				.findSolrDocumentsByTagAndRows(tag + "* AND orgInternalCodes:"
						+ orgInternalCode + "*", 10,
						SolrServerType.KEY_POPULATION);
		List<KeyPopulationDocument> keyPopulationDocuments = new ArrayList<KeyPopulationDocument>();
		for (SolrDocument solrDocument : solrDocuments) {
			keyPopulationDocuments.add(parseSolrDocument(solrDocument));
		}
		return keyPopulationDocuments;
	}

	@Override
	public PageInfo<CommonPopulationDocument> findKeyPopulationDocumentPageByTag(
			String tag, String orgInternalCode, Integer pageNum,
			Integer pageSize) {
		SolrServer solrServer = SolrServerFactory.getSolrServer(
				SolrServerType.COMMON_POPULATION,
				globalSettingService.getGlobalValue(GlobalSetting.SOLR_URL));
		SolrQuery query = new SolrQuery();
		query.setQuery(tag + "* AND orgInternalCode:" + orgInternalCode + "*");
		query.setRows(pageSize);
		query.setStart((pageNum - 1) * pageSize);
		QueryResponse queryResponse;
		PageInfo<CommonPopulationDocument> page = new PageInfo<CommonPopulationDocument>();
		try {
			queryResponse = solrServer.query(query);
			SolrDocumentList solrDocumentList = queryResponse.getResults();

			List<CommonPopulationDocument> commonPopulationDocuments = new ArrayList<CommonPopulationDocument>();
			for (SolrDocument solrDocument : solrDocumentList) {
				commonPopulationDocuments
						.add(parseCommonSolrDocument(solrDocument));
			}
			page.setResult(commonPopulationDocuments);
			page.setPerPageSize(pageSize);
			page.setTotalRowSize(solrDocumentList.getNumFound());
			page.setCurrentPage(pageNum);
		} catch (SolrServerException e) {
			throw new ServiceValidationException("solr查询错误！", e);
		}
		return page;
	}

}
