package com.tianque.plugin.orgchange.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tianque.plugin.orgchange.domain.OrgMapping;

/**
 * 组织机构迁移和合并公共实现类
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月23日
 */
@Component("commonHandler")
public class CommonHandler extends AbstractOrgChangeHandler {
	private final static Logger logger = LoggerFactory
			.getLogger(CommonHandler.class);

	@Override
	public void merge(OrgMapping orgMapping) {
		if (logger.isDebugEnabled())
			logger.debug("begin merge.");
		execute(orgMapping);
		if (logger.isDebugEnabled())
			logger.debug("end merge.");

	}

}
