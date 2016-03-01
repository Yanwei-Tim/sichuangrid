package com.tianque.solr;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.tianque.core.vo.PageInfo;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.tqSearch.common.SolrResultRelationalObject;
import com.tianque.tqSearch.constant.TqSolrSearchType;
import com.tianque.tqSearch.domain.TqSolrDocumentList;

public class testMain {

	public static void main(String[] args) throws Exception {

		addindex();
		queryAll();
	}

	public static void addindex() {
		try {
			SolrServer server = new HttpSolrServer(
					"http://localhost:8090/issues");
			server.deleteByQuery("*:*");
			for (int i = 0; i < 1; i++) {
				SolrInputDocument document = new SolrInputDocument();
				document.addField("id", "1" + i + 10, new Float(1.0));
				document.addField("subject", "要夺奇巧" + i);
				document.addField("occurDate", new Date());
				server.add(document);
			}
			server.commit();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void queryAll() throws Exception {
		try {
			SolrServer server = new HttpSolrServer(
					"http://localhost:8090/issues");
			SolrQuery params = new SolrQuery("*:*");
			PageInfo pageInfo = new PageInfo(1, 20, 100, null);
			params.setStart(0);
			params.setRows(5);
			// params.setQuery("subject:3");
			SolrDocumentList docs = server.query(params).getResults();
			SolrResultRelationalObject.conversionPageInfoResult(
					new TqSolrDocumentList(docs), pageInfo,
					IssueViewObject.class, TqSolrSearchType.ISSUE_TYPE);
			for (SolrDocument sd : docs) {
				System.out.println(sd.getFieldValue("subject"));
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}

	}

}
