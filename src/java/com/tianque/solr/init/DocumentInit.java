package com.tianque.solr.init;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.baseInfo.dangerousGoodsPractitioner.domain.DangerousGoodsPractitioner;
import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.baseInfo.idleYouth.domain.IdleYouth;
import com.tianque.baseInfo.mentalPatient.domain.MentalPatient;
import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisit;
import com.tianque.domain.Enterprise;
import com.tianque.domain.Hospital;
import com.tianque.domain.OtherLocale;
import com.tianque.domain.School;
import com.tianque.solr.domain.DocumentType;
import com.tianque.solr.util.SolrServerFactory;
import com.tianque.solr.util.SolrServerType;

public class DocumentInit {

	private static Logger logger = LoggerFactory.getLogger(DocumentInit.class);

	private static List<Init> inits = new ArrayList<Init>();

	public static void init(String solrUrl) throws Exception {
		logger.info("搜索引擎开始初始化");
		clearDocuments(solrUrl);
		createInits();
		for (Init init : inits) {
			init.init();
		}
		logger.info("搜索引擎初始化结束");
	}

	private static void clearDocuments(String solrUrl) throws Exception {
		SolrServerFactory.getSolrServer(SolrServerType.KEY_PLACE, solrUrl).deleteByQuery("*:*");
		SolrServerFactory.getSolrServer(SolrServerType.KEY_POPULATION, solrUrl)
				.deleteByQuery("*:*");
		SolrServerFactory.getSolrServer(SolrServerType.COMMON_POPULATION, solrUrl).deleteByQuery(
				"*:*");
		SolrServerFactory.getSolrServer(SolrServerType.KEY_PLACE, solrUrl).commit();
		SolrServerFactory.getSolrServer(SolrServerType.KEY_POPULATION, solrUrl).commit();
		SolrServerFactory.getSolrServer(SolrServerType.COMMON_POPULATION, solrUrl).commit();
	}

	private static void createInits() {
		inits.add(new Init<Druggy>(DocumentType.DRUGGY));
		inits.add(new Init<DangerousGoodsPractitioner>(DocumentType.DANGEROUS_GOODS_PRACTITIONER));
		inits.add(new Init<SuperiorVisit>(DocumentType.SUPERIOR_VISIT));
		inits.add(new Init<IdleYouth>(DocumentType.IDLE_YOUTH));
		inits.add(new Init<MentalPatient>(DocumentType.MENTAL_PATIENT));
		inits.add(new Init<PositiveInfo>(DocumentType.POSITIVE_INFO));
		inits.add(new Init<RectificativePerson>(DocumentType.RECTIFICATIVEPERSON));

		inits.add(new Init<Enterprise>(DocumentType.ENTERPRISEKEY));
		inits.add(new Init<Enterprise>(DocumentType.PROTECTIONKEY));
		inits.add(new Init<Enterprise>(DocumentType.SAFETYPRODUCTIONKEY));
		inits.add(new Init<Enterprise>(DocumentType.FIRESAFETYKEY));
		inits.add(new Init<Enterprise>(DocumentType.SECURITYKEY));
		inits.add(new Init<Enterprise>(DocumentType.ENTERPRISEDOWNKEY));

		inits.add(new Init<School>(DocumentType.SCHOOL));
		inits.add(new Init<Hospital>(DocumentType.HOSPITAL));

		inits.add(new Init<OtherLocale>(DocumentType.OTHER_LOCALE));
	}
}
