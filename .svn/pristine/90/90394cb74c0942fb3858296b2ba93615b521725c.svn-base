package com.tianque.solr.init;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.util.GlobalValue;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.init.ContextType;
import com.tianque.jms.OperateMode;
import com.tianque.jms.sender.ActiveMQMessageSender;
import com.tianque.jms.solr.SolrMessage;
import com.tianque.solr.domain.DocumentType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.ApplicationContextFactory;

public class Init<T> {
	private static Logger logger = LoggerFactory.getLogger(Init.class);
	protected Organization rootOrg = getRootOrg();

	private DocumentType documentType;

	public Init(DocumentType documentType) {
		this.documentType = documentType;
	}

	private Organization getRootOrg() {
		try {
			OrganizationDubboService organizationDubboService = getOrganizationDubboService();
			List<Organization> organizations = organizationDubboService
					.findOrganizationsByParentId(null);
			if (organizations.size() > 0) {
				return organizations.get(0);
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return new Organization();
	}

	private OrganizationDubboService getOrganizationDubboService() {
		return (OrganizationDubboService) ApplicationContextFactory.getInstance().getBean(
				Enum.valueOf(ContextType.class, GlobalValue.environment), "organizationDubboService");
	}

	private void createDocuments(int page) throws Exception {
		logger.info("准备向jms发送type为" + documentType.toString() + "类型的第" + page + "页的数据");
		PageInfo pageInfo = PageInfoHelper.getPageInfo(documentType, rootOrg, page);
		List<T> result = pageInfo.getResult();
		for (T domain : result) {
			Long id = (Long) domain.getClass().getMethod("getId").invoke(domain);
			SolrMessage solrMsgCreator = new SolrMessage(id, documentType.toString(),
					OperateMode.ADD.toString());
			getJmsTemplate(solrMsgCreator);
		}
		if (pageInfo.getPageNum() > pageInfo.getCurrentPage()) {
			createDocuments(page + 1);
			Thread.sleep(1000);
		}
	}

	public void init() {
		try {
			createDocuments(1);
		} catch (Exception e) {
			logger.error("异常信息", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	private ActiveMQMessageSender getMessageSender() {
		return (ActiveMQMessageSender) ServiceFactory.getService("activeMQMessageSender");
	}

	private void getJmsTemplate(SolrMessage solrMessage) {
		getMessageSender().send(solrMessage);
	}
}
