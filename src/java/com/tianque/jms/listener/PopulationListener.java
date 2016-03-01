package com.tianque.jms.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.baseInfo.elderlyPeople.domain.ElderlyPeople;
import com.tianque.baseInfo.elderlyPeople.service.ElderlyPeopleService;
import com.tianque.baseInfo.nurturesWomen.domain.NurturesWomen;
import com.tianque.baseInfo.nurturesWomen.service.NurturesWomenService;
import com.tianque.cache.PageInfoCacheThreadLocal;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.domain.Organization;
import com.tianque.jms.OperateMode;
import com.tianque.jms.msg.BaseMsg;
import com.tianque.jms.msg.PopulationMsg;
import com.tianque.job.SessionHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;

public class PopulationListener {
	private static Logger logger = LoggerFactory
			.getLogger(PopulationListener.class);

	@Autowired
	private NurturesWomenService nurturesWomenService;
	@Autowired
	private ElderlyPeopleService elderlyPeopleService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	public void onMessage(BaseMsg msg) {
		try {
			if (msg instanceof PopulationMsg) {
				if (BaseInfoTables.NURTURESWOMEN_KEY
						.equals(((PopulationMsg) msg).getType())) {
					// 育龄妇女新增
					saveNurturesWomen((PopulationMsg) msg);
				} else if (BaseInfoTables.ELDERLYPEOPLE_KEY
						.equals(((PopulationMsg) msg).getType())) {
					// 老年人新增
					saveElderlyPeople((PopulationMsg) msg);
				}
			}
			PageInfoCacheThreadLocal.commit();
		} catch (Exception e) {
			logger.error("mq添加失败:", e);
			PageInfoCacheThreadLocal.rollback();
		}
	}

	/**
	 * 添加育龄妇女
	 * 
	 * @param msg
	 */
	private void saveNurturesWomen(PopulationMsg msg) {
		Long exsited = nurturesWomenService
				.getNurturesWomenByBaseinfoIdAndOrgId(msg.getBaseinfoId(),
						msg.getOrgId());
		Long sourcesState = ConstantsProduct.SourcesState.ADDED;
		if (exsited == null) {
			NurturesWomen nurturesWomen = new NurturesWomen();
			Organization organization = organizationDubboService
					.getSimpleOrgById(msg.getOrgId());
			nurturesWomen.setOrganization(organization);
			nurturesWomen.setOrgInternalCode(organization.getOrgInternalCode());
			nurturesWomen.setBaseInfoId(msg.getBaseinfoId());
			nurturesWomen.setAddressInfoId(msg.getAddressId());
			nurturesWomen.setId(msg.getObjectId());
			nurturesWomen.setActualPopulationType(msg.getObjectType());
			sourcesState = createThreadVariable(msg, sourcesState);
			nurturesWomenService.addNurturesWomenForJob(nurturesWomen,
					sourcesState);
		}
	}

	/**
	 * 添加老年人
	 * 
	 * @param msg
	 */
	private void saveElderlyPeople(PopulationMsg msg) {
		Long exsited = elderlyPeopleService
				.getElderlyPeopleByBaseinfIdAndOrgId(msg.getBaseinfoId(),
						msg.getOrgId());
		Long sourcesState = ConstantsProduct.SourcesState.ADDED;
		if (exsited == null) {
			ElderlyPeople elderlyPeople = new ElderlyPeople();
			Organization organization = organizationDubboService
					.getSimpleOrgById(msg.getOrgId());
			elderlyPeople.setOrganization(organization);
			elderlyPeople.setOrganization(organization);
			elderlyPeople.setOrgInternalCode(organization.getOrgInternalCode());
			elderlyPeople.setBaseInfoId(msg.getBaseinfoId());
			elderlyPeople.setAddressInfoId(msg.getAddressId());
			elderlyPeople.setId(msg.getObjectId());
			elderlyPeople.setActualPopulationType(msg.getObjectType());
			sourcesState = createThreadVariable(msg, sourcesState);
			elderlyPeopleService.addElderlyPeopleForJob(elderlyPeople,
					sourcesState);
		}
	}

	private Long createThreadVariable(PopulationMsg msg, Long sourcesState) {
		if (OperateMode.ADD.equals(msg.getMode())) {
			ExcelImportHelper.isImport.set(false);
		} else {
			ExcelImportHelper.isImport.set(true);
			sourcesState = ConstantsProduct.SourcesState.IMPORT;
		}
		SessionHelper.createMockSessionByUserName(msg.getCreateUser(),
				msg.getCreateUser());
		return sourcesState;
	}
}
