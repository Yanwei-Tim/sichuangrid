package com.tianque.solr.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolrServerFactory {
	private static Logger logger = LoggerFactory
			.getLogger(SolrServerFactory.class);
	private static Map<String, SolrServer> map = new HashMap<String, SolrServer>();

	public static SolrServer getSolrServer(SolrServerType solrServerName,
			String solrUrl) {
		String solrServer = "";
		if (SolrServerType.KEY_PLACE.equals(solrServerName)) {
			solrServer = "keyPlace";
		} else if (SolrServerType.KEY_POPULATION.equals(solrServerName)) {
			solrServer = "keyPopulation";
		} else if (SolrServerType.COMMON_POPULATION.equals(solrServerName)) {
			solrServer = "commonPopulation";
		}
		if (null == map.get(solrServerName.toString())) {
			SolrServer server = null;
			try {
				server = new HttpSolrServer(solrUrl + solrServer + "/");
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			// server.setSoTimeout(100000); // socket read timeout
			// server.setConnectionTimeout(10000);
			// server.setDefaultMaxConnectionsPerHost(100);
			// server.setMaxTotalConnections(100);
			// server.setFollowRedirects(false); // defaults to false
			// server.setAllowCompression(true);
			// server.setMaxRetries(1); // defaults to 0. > 1 not recommended.
			// server.setParser(new XMLResponseParser());
			map.put(solrServerName.toString(), server);
		}
		return map.get(solrServerName.toString());
	}
}
