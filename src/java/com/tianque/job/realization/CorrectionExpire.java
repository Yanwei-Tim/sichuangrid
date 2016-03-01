package com.tianque.job.realization;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.baseInfo.positiveInfo.service.PositiveInfoService;
import com.tianque.baseInfo.rectificativePerson.dao.SearchRectificativePersonDao;
import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.baseInfo.rectificativePerson.service.RectificativePersonService;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.job.JobHelper;
import com.tianque.sysadmin.service.PropertyDictService;

public class CorrectionExpire implements Job {
	private static Logger logger = LoggerFactory
			.getLogger(CorrectionExpire.class);

	@Autowired
	private SearchRectificativePersonDao searchRectificativePersonDao;
	@Autowired
	private PositiveInfoService posiviteInfoService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private RectificativePersonService rectificativePersonService;

	private PositiveInfo positiveInfo;

	private void searRectificativePerson() {
		logger.info("社区矫正人员转换成刑释人员job开始执行！");
		JobHelper.createMockAdminSession();
		String sFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Date dateTwo = null;
		try {
			dateTwo = new SimpleDateFormat("yyy-MM-dd").parse(sFormat);
		} catch (ParseException e) {
			logger.error("异常信息", e);
		}
		List<RectificativePerson> list = searchRectificativePersonDao
				.searchRectificativePerson(dateTwo);
		for (int i = 0; i < list.size(); i++) {
			RectificativePerson rectificativePerson = list.get(i);
			createDomain(rectificativePerson);
			posiviteInfoService.addPositiveInfo(positiveInfo);
			rectificativePerson.setIsEmphasis(1L);
			rectificativePersonService.updateEmphasiseById(
					rectificativePerson.getId(),
					rectificativePerson.getIsEmphasis());
		}
	}

	private PositiveInfo createDomain(RectificativePerson rectificativePerson) {
		positiveInfo = new PositiveInfo();
		positiveInfo.setName(rectificativePerson.getName());
		positiveInfo.setOrganization(rectificativePerson.getOrganization());
		positiveInfo.setOrgInternalCode(rectificativePerson
				.getOrgInternalCode());
		positiveInfo.setIdCardNo(rectificativePerson.getIdCardNo());
		positiveInfo.setGender(rectificativePerson.getGender());
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.POSITIVEINFO);
		for (int i = 0; i < propertyDicts.size(); i++) {
			if (propertyDicts.get(i).getDisplayName().equals("刑释人员")) {
				positiveInfo.setPositiveInfoType(propertyDicts.get(i));
			}
		}
		positiveInfo.setReleaseOrBackDate(new Date());
		return positiveInfo;
	}

	public PositiveInfo getPositiveInfo() {
		return positiveInfo;
	}

	public void setPositiveInfos(PositiveInfo positiveInfo) {
		this.positiveInfo = positiveInfo;
	}

	private ApplicationContext applicationContext;

	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		try {
			if (applicationContext == null) {
				SchedulerContext schedulerContext = context.getScheduler()
						.getContext();
				applicationContext = (ApplicationContext) schedulerContext
						.get("applicationContext");
			}
			searchRectificativePersonDao = (SearchRectificativePersonDao) applicationContext
					.getBean("searchRectificativePersonDao");
			posiviteInfoService = (PositiveInfoService) applicationContext
					.getBean("posiviteInfoService");
			propertyDictService = (PropertyDictService) applicationContext
					.getBean("propertyDictService");
			rectificativePersonService = (RectificativePersonService) applicationContext
					.getBean("rectificativePersonService");
			searRectificativePerson();
		} catch (Exception e) {
			logger.error("异常信息", e);
			logger.info(e.getMessage());
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		searRectificativePerson();
	}

}
