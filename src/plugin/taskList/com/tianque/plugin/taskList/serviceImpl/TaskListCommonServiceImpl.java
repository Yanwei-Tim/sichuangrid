package com.tianque.plugin.taskList.serviceImpl;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.judgmentAnalysis.util.StringUtil;
import com.tianque.plugin.taskList.constant.ReplayOrgType;
import com.tianque.plugin.taskList.constants.Constants;
import com.tianque.plugin.taskList.dao.ReceiptDao;
import com.tianque.plugin.taskList.domain.BaseTaskVisit;
import com.tianque.plugin.taskList.domain.DruggyTask;
import com.tianque.plugin.taskList.domain.ExceptionalSituationRecord;
import com.tianque.plugin.taskList.domain.FloatingPopulationTask;
import com.tianque.plugin.taskList.domain.HiddenDanger;
import com.tianque.plugin.taskList.domain.HiddenDangerTask;
import com.tianque.plugin.taskList.domain.MentalPatientTask;
import com.tianque.plugin.taskList.domain.PositiveInfoRecord;
import com.tianque.plugin.taskList.domain.Receipt;
import com.tianque.plugin.taskList.domain.TaskListAttachFile;
import com.tianque.plugin.taskList.domain.TaskListReply;
import com.tianque.plugin.taskList.domain.TaskListStatistics;
import com.tianque.plugin.taskList.domain.TermerRecord;
import com.tianque.plugin.taskList.service.DruggyTaskService;
import com.tianque.plugin.taskList.service.ExceptionalSituationRecordService;
import com.tianque.plugin.taskList.service.HiddenDangerService;
import com.tianque.plugin.taskList.service.MentalPatientTaskService;
import com.tianque.plugin.taskList.service.PositiveInfoRecordService;
import com.tianque.plugin.taskList.service.TaskListAttachFileService;
import com.tianque.plugin.taskList.service.TaskListCommonService;
import com.tianque.plugin.taskList.service.TaskListReplyService;
import com.tianque.plugin.taskList.service.TermerRecordService;
import com.tianque.plugin.taskList.validate.ReceiptValidatorImpl;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.userAuth.api.PropertyDictDubboService;

@Transactional
@Service("taskListCommonService")
public class TaskListCommonServiceImpl implements TaskListCommonService {
	@Autowired
	private ReceiptDao receiptDao;
	@Autowired
	private ReceiptValidatorImpl receiptValidator;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private TaskListReplyService taskListReplyService;
	@Autowired
	private TaskListAttachFileService taskListAttachFileService;
	@Autowired
	private ExceptionalSituationRecordService exceptionalSituationRecordService;
	@Autowired
	private HiddenDangerService hiddenDangerService;
	@Autowired
	private DruggyTaskService druggyTaskService;
	@Autowired
	private TermerRecordService termerRecordService;
	@Autowired
	private MentalPatientTaskService mentalPatientTaskService;
	@Autowired
	private PositiveInfoRecordService positiveInfoRecordService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;

	@Override
	public void updateReceiptStatus(Receipt receipt, String signType) {
		if (receipt == null || receipt.getId() == null
				|| StringUtil.isEmpty(receipt.getObjectType())) {
			throw new BusinessValidationException("签收基本参数为空，签收失败");
		}
		receiptValidator(receipt);
		try {
			fillReceipt(receipt);
			receiptDao.updateReceiptStatus(receipt, signType);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类TaskListCommonServiceImpl的updateReceiptStatus方法出现异常，原因：", "任务清单签收出现错误", e);
		}
	}

	private void receiptValidator(Receipt domain) {
		ValidateResult dataValidateResult = receiptValidator.validate(domain);
		if (dataValidateResult.hasError()) {
			throw new BusinessValidationException(dataValidateResult.getErrorMessages());
		}
	}

	private void fillReceipt(Receipt receipt) {
		if (StringUtil.isEmpty(Constants.getTableByKey(receipt.getObjectType()))) {
			throw new BusinessValidationException("参数有误，签收失败");
		}
		receipt.setTableName(Constants.getTableByKey(receipt.getObjectType()));
	}

	
	@Override
	public List<BaseTaskVisit> getAllKindTask(Long orgId,Integer searchType,Integer year,Integer month,Integer week,Integer searchYear) {
		if(orgId==null || searchType==null){
			throw new BusinessValidationException("查询失败，查询参数未获得");
		}
		List<BaseTaskVisit> baseTaskList  = null;
		String key = MemCacheConstant.getTaskListReportCachKey(MemCacheConstant.TASK_LIST_ALL_KEY, orgId,searchType,year,month,week,searchYear);
		if(StringUtil.isStringAvaliable(key)){
			baseTaskList = (List<BaseTaskVisit>) cacheService.get(MemCacheConstant.TASK_LIST_ALL_NAMESPACE, key);
			if(baseTaskList!=null && baseTaskList.size()!=0){
				return baseTaskList;
			}
		}
		try {
			Map<String,Object> map = getDateByCondition(searchType, year, month, week,searchYear);
			Long orgType = propertyDictService.findPropertyDictByDomainNameAndDictDisplayName(
					PropertyTypes.ORGANIZATION_TYPE, OrganizationType.ADMINISTRATIVE_KEY).getId();
			map.put("orgType", orgType);
			map.put("parentOrgId", orgId);
			//获取各个层级的走访，未走访，走访率
			baseTaskList = receiptDao.getTaskSumAndVisitByParentOrgId(map);
			for (int i = 0; i < baseTaskList.size(); i++) {

				if (baseTaskList.get(i).getId().equals(orgId)) {
					BaseTaskVisit temp = baseTaskList.get(i);
					temp.setOrgname("合计");
					baseTaskList.remove(i);
					baseTaskList.add(temp);

				}
			}
			if(StringUtil.isStringAvaliable(key)){
				cacheService.set(MemCacheConstant.TASK_LIST_ALL_NAMESPACE, key,ModulTypes.CACHETIME, baseTaskList);
			}
			return baseTaskList;
		} catch (Exception e) {
			throw new ServiceValidationException("获取走访报表出错", e);
		}

	}
	
	private Map<String,Object> getDateByCondition(Integer searchType,Integer year,Integer month,Integer week,Integer searchYear){
		 Map<String,Object> map = new HashMap<String,Object>();
		 Date startDate = null;
		 Date endDate = null;
		 if(searchType==0){//根据年月查询
//				if(CalendarUtil.compareYearAndMonth(year, month)){//当前年月则查询本月数据
					startDate = CalendarUtil.getMonthStart(year, month);
//				}
				endDate = CalendarUtil.getNextMonthStart(year, month);
		 }else if(searchType==1){//根据周查询
				if(week==0){//本周
					startDate = CalendarUtil.getWeekMonday();
					endDate = CalendarUtil.getNextWeekMonday();
				}else{//上周
					startDate = CalendarUtil.getBeforWeekMonday(CalendarUtil.now());//根据当前时间获取上周周一
					endDate = CalendarUtil.getWeekMonday();//根据当前时间获取本周周一
				}
		 }else if(searchType==2){//根据年查询
			 startDate = CalendarUtil.getMonthStart(searchYear, 1);
			 endDate = CalendarUtil.getNextMonthStart(searchYear, 12);
		 }
		 if(startDate!=null){
			 map.put("startDate",startDate);
		 }
		 map.put("endDate",endDate);
		 return map;
	}

	@Override
	public List<FloatingPopulationTask> getFloatingPopulationTaskList(Long orgId,Integer searchType,Integer year,Integer month,Integer week,Integer searchYear) {
		if(orgId==null){
			throw new BusinessValidationException("查询失败，组织机构参数未获得");
		}
		String key =  MemCacheConstant.getTaskListReportCachKey(MemCacheConstant.TASK_LIST_FLOATING_KEY, orgId,searchType,year,month,week,searchYear);
		List<FloatingPopulationTask> floatingPopulationTask=null;
		if(StringUtil.isStringAvaliable(key)){
			floatingPopulationTask = (List<FloatingPopulationTask>) cacheService.get(MemCacheConstant.TASK_LIST_ALL_NAMESPACE, key);
			if(floatingPopulationTask!=null && floatingPopulationTask.size()!=0){
				return floatingPopulationTask;
			}
		}
		try {
			Map<String,Object> map = getDateByCondition(searchType, year, month, week,searchYear);
			Long orgType = propertyDictService.findPropertyDictByDomainNameAndDictDisplayName(
					PropertyTypes.ORGANIZATION_TYPE, OrganizationType.ADMINISTRATIVE_KEY).getId();
			map.put("orgType", orgType);
			map.put("parentOrgId", orgId);
			//获取流口异常类型
			List<PropertyDict> list = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.EXCEPTION_SITUATION_TYPE);
			if(list!=null && list.size()!=0){
				for(PropertyDict dict:list){
					map.put(dict.getSimplePinyin(), dict.getId());
				}
			}
			//获取各个层级的走访，未走访，走访率
			floatingPopulationTask = receiptDao
					.getFloatingPopulationVisitByParentOrgId(map);
			//计算合计
			if(floatingPopulationTask!=null && floatingPopulationTask.size()!=0){
				totalCalculation(floatingPopulationTask, orgId);
			}
//			for (int i = 0; i < floatingPopulationTask.size(); i++) {
//
//				if (floatingPopulationTask.get(i).getId().equals(orgId)) {
//					FloatingPopulationTask temp = floatingPopulationTask.get(i);
//					temp.setOrgname("合计");
//					floatingPopulationTask.remove(i);
//					floatingPopulationTask.add(temp);
//
//				}
//			}
			if(StringUtil.isStringAvaliable(key)){
				cacheService.set(MemCacheConstant.TASK_LIST_ALL_NAMESPACE, key,ModulTypes.CACHETIME, floatingPopulationTask);
			}
			return floatingPopulationTask;
		} catch (Exception e) {
			throw new ServiceValidationException("获取走访报表出错", e);
		}
	}

	private void totalCalculation(List<FloatingPopulationTask> floatingPopulationTask,Long currentId){
		//宣传核查
		Long policeSum = 0L;
		Long policeVisit = 0L;
		//民警带领开展工作
		Long publicSum = 0L;
		Long publicVisit = 0L;
		//大量聚集
		Long gatherSum = 0L;
		Long gatherVisit = 0L;
		Long gatherReply = 0L;//大量聚集已回复
		//异常气味
		Long smellSum = 0L;
		Long smellVisit = 0L;
		Long smellReply = 0L;
		//异常声音
		Long noiseSum = 0L;
		Long noiseVisit = 0L;
		Long noiseReply = 0L;
		//无身份证
		Long noIdcardSum = 0L;
		Long noIdcardVisit = 0L;
		Long noIdCardReply = 0L;
		//群租房人员来往复杂
		Long groupLiveSum = 0L;
		Long groupLiveVisit = 0L;
	    Long groupLiveReply = 0L;
		//已搬走
		Long liveSum = 0L;
		Long liveVisit = 0L;
		Long liveReply = 0L;
		FloatingPopulationTask currentTask = new FloatingPopulationTask();
		for(FloatingPopulationTask task:floatingPopulationTask){
			policeSum +=task.getPoliceSum();
			policeVisit +=task.getPoliceVisit();
			publicSum +=task.getPublicSum();
			publicVisit +=task.getPublicVisit();
			gatherSum +=task.getGatherSum();
			gatherVisit +=task.getGatherVisit();
			gatherReply +=task.getGatherReply();
			smellSum +=task.getSmellSum();
			smellVisit +=task.getSmellVisit();
			smellReply +=task.getSmellReply();
			noiseSum +=task.getNoiseSum();
			noiseVisit +=task.getNoiseVisit();
			noiseReply +=task.getNoiseReply();
			noIdcardSum +=task.getNoIdcardSum();
			noIdcardVisit +=task.getNoIdcardVisit();
			noIdCardReply +=task.getNoIdCardReply();
			groupLiveSum +=task.getGroupLiveSum();
			groupLiveVisit +=task.getGroupLiveVisit();
		    groupLiveReply +=task.getGroupLiveReply();
			liveSum +=task.getLiveSum();
			liveVisit +=task.getLiveVisit();
			liveReply +=task.getLiveReply();
		}
		currentTask.setPoliceSum(policeSum);
		currentTask.setPoliceVisit(policeVisit);
		currentTask.setPublicSum(publicSum) ;
		currentTask.setPublicVisit(publicVisit);
		currentTask.setGatherSum(gatherSum);
		currentTask.setGatherVisit(gatherVisit);
		currentTask.setGatherReply(gatherReply);
		currentTask.setSmellSum(smellSum);
		currentTask.setSmellVisit(smellVisit);
		currentTask.setSmellReply(smellReply);
		currentTask.setNoiseSum(noiseSum);
		currentTask.setNoiseVisit(noiseVisit);
		currentTask.setNoiseReply(noiseReply);
		currentTask.setNoIdcardSum(noIdcardSum);
		currentTask.setNoIdcardVisit(noIdcardVisit);
		currentTask.setNoIdCardReply(noIdCardReply);
		currentTask.setGroupLiveSum(groupLiveSum);
		currentTask.setGroupLiveVisit(groupLiveVisit);
		currentTask.setGroupLiveReply(groupLiveReply);
		currentTask.setLiveSum(liveSum) ;
		currentTask.setLiveVisit(liveVisit);
		currentTask.setLiveReply(liveReply) ;
		currentTask.setOrgname("合计");
		currentTask.setId(currentId);
		floatingPopulationTask.add(currentTask);
	}
	
	@Override
	public List<HiddenDangerTask> getHiddenDangerVisitList(Long orgId,Integer searchType,Integer year,Integer month,Integer week,Integer searchYear) {
		if(orgId==null){
			throw new BusinessValidationException("查询失败，组织机构参数未获得");
		}
		String key =  MemCacheConstant.getTaskListReportCachKey(MemCacheConstant.TASK_LIST_HIDDENDANGER_KEY, orgId,searchType,year,month,week,searchYear);
		List<HiddenDangerTask> hiddenDangerTask=null;
		if(StringUtil.isStringAvaliable(key)){
			hiddenDangerTask = (List<HiddenDangerTask>) cacheService.get(MemCacheConstant.TASK_LIST_ALL_NAMESPACE, key);
			if(hiddenDangerTask!=null && hiddenDangerTask.size()!=0){
				return hiddenDangerTask;
			}
		}
		try {
			Map<String,Object> map = getDateByCondition(searchType, year, month, week,searchYear);
			Long orgType = propertyDictService.findPropertyDictByDomainNameAndDictDisplayName(
					PropertyTypes.ORGANIZATION_TYPE, OrganizationType.ADMINISTRATIVE_KEY).getId();
			map.put("orgType", orgType);
			map.put("parentOrgId", orgId);
			List<PropertyDict> list = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.DANGER_EXCEPTION_TYPE);
			if(list!=null && list.size()!=0){
				for(PropertyDict dict:list){
					map.put(dict.getSimplePinyin(), dict.getId());
				}
			}
			//获取各个层级的走访，未走访，走访率
			hiddenDangerTask = receiptDao.getHiddenDangerVisitByParentOrgId(map);
			for (int i = 0; i < hiddenDangerTask.size(); i++) {

				if (hiddenDangerTask.get(i).getId().equals(orgId)) {
					HiddenDangerTask temp = hiddenDangerTask.get(i);
					temp.setOrgname("合计");
					hiddenDangerTask.remove(i);
					hiddenDangerTask.add(temp);

				}
			}
			if(StringUtil.isStringAvaliable(key)){
				cacheService.set(MemCacheConstant.TASK_LIST_ALL_NAMESPACE, key,ModulTypes.CACHETIME, hiddenDangerTask);
			}
			return hiddenDangerTask;
		} catch (Exception e) {
			throw new ServiceValidationException("获取走访报表出错", e);
		}
	}

	@Override
	public List<BaseTaskVisit> getSpecialGroupSumAndVisitList(Long orgId,Integer searchType,Integer year,Integer month,Integer week,Integer searchYear) {
		if(orgId==null){
			throw new BusinessValidationException("查询失败，组织机构参数未获得");
		}
		String key =  MemCacheConstant.getTaskListReportCachKey(MemCacheConstant.TASK_LIST_SPECIAL_KEY, orgId,searchType,year,month,week,searchYear);
		List<BaseTaskVisit> baseTaskList=null;
		if(StringUtil.isStringAvaliable(key)){
			baseTaskList =(List<BaseTaskVisit>) cacheService.get(MemCacheConstant.TASK_LIST_SPECIAL_KEY, key);
			if(baseTaskList!=null && baseTaskList.size()!=0){
				return baseTaskList;
			}
		}
		try {
			Map<String,Object> map = getDateByCondition(searchType, year, month, week,searchYear);
			Long orgType = propertyDictService.findPropertyDictByDomainNameAndDictDisplayName(
					PropertyTypes.ORGANIZATION_TYPE, OrganizationType.ADMINISTRATIVE_KEY).getId();
			map.put("orgType", orgType);
			map.put("parentOrgId", orgId);
			//获取各个层级的走访，未走访，走访率
			baseTaskList = receiptDao.getSpecialGroupSumAndVisitList(map);
			for (int i = 0; i < baseTaskList.size(); i++) {

				if (baseTaskList.get(i).getId().equals(orgId)) {
					BaseTaskVisit temp = baseTaskList.get(i);
					temp.setOrgname("合计");
					baseTaskList.remove(i);
					baseTaskList.add(temp);
				}
			}
			if(StringUtil.isStringAvaliable(key)){
				cacheService.set(MemCacheConstant.TASK_LIST_ALL_NAMESPACE, key,ModulTypes.CACHETIME, baseTaskList);
			}
			return baseTaskList;
		} catch (Exception e) {
			throw new ServiceValidationException("获取任务清单特殊人群报表出错", e);
		}
	}

	@Override
	public TaskListReply addTaskListReply(TaskListReply taskListReply) {
		if (StringUtils.isBlank(taskListReply.getReplyContent())) {
			throw new BusinessValidationException("回复类容不能为空");
		}
		if (taskListReply.getReplyContent().length() > 1000) {
			throw new BusinessValidationException("回复类容长度不能超过1000");
		}
		if (StringUtils.isBlank(taskListReply.getModuleKey()) || taskListReply.getTaskId() == null) {
			throw new BusinessValidationException("参数缺失");
		}
		if (Constants.REPLY_EXCEPTIONALSITUATIONRECORD_KEY.equals(taskListReply.getModuleKey())) {
			ExceptionalSituationRecord exceptionalSituationRecord = exceptionalSituationRecordService
					.getExceptionalSituationRecordById(taskListReply.getTaskId());
			if (exceptionalSituationRecord.getStatus() != 1) {
				throw new BusinessValidationException("未签收的不能回复");
			}
			exceptionalSituationRecord.setHasReplay(1);
			// 回复时间只记录第一次
			if(exceptionalSituationRecord.getReplayDate()==null){
				exceptionalSituationRecord.setReplayDate(new Date());
			}
			exceptionalSituationRecordService
					.updateExceptionalSituationRecord(exceptionalSituationRecord);
		} else if (Constants.REPLY_HIDDENDANGER_KEY.equals(taskListReply
				.getModuleKey())) {
			HiddenDanger hiddenDanger = hiddenDangerService.getHiddenDangerById(taskListReply
					.getTaskId());
			if (hiddenDanger.getIshandle() != 1) {
				throw new BusinessValidationException("未签收的不能回复");
			}
			hiddenDanger.setHasReplay(1);
			if(hiddenDanger.getReplayDate()==null) {
				hiddenDanger.setReplayDate(new Date());
			}
			hiddenDangerService.updateHiddenDanger(hiddenDanger);
		} else if (Constants.REPLY_DRUGGYTASK_KEY.equals(taskListReply.getModuleKey())) {
			DruggyTask druggyTask = druggyTaskService.getDruggyTaskById(taskListReply.getTaskId());
			if (druggyTask.getStatus() != 1) {
				throw new BusinessValidationException("未签收的不能回复");
			}
			druggyTask.setHasReplay(1);
			if(druggyTask.getReplayDate()==null){
				druggyTask.setReplayDate(new Date());
			}
			druggyTaskService.updateDruggyTask(druggyTask);
		} else if (Constants.REPLY_MENTALPATIENTTASK_KEY.equals(taskListReply.getModuleKey())) {
			MentalPatientTask task = mentalPatientTaskService
					.getMentalPatientTaskById(taskListReply.getTaskId());
			if (task.getStatusJustice() != 1 && task.getStatusPolice() != 1) {
				throw new BusinessValidationException("未签收的不能回复");
			}
			task.setHasReplay(1);
			if(ReplayOrgType.JUSTICE.equals(taskListReply.getReplayOrgType())){
				task.setReplayDateJustic(new Date());
			}else if(ReplayOrgType.POLICE.equals(taskListReply.getReplayOrgType())){
				task.setReplayDatePolice(new Date());
			}else{
				throw new BusinessValidationException("错误的回复组织类别");
			}
//			if(task.getReplayDate()==null){
//				task.setReplayDate(new Date());
//			}
			mentalPatientTaskService.updateMentalPatientTask(task);
		} else if (Constants.REPLY_TERMERRECORD_KEY.equals(taskListReply.getModuleKey())) {
			TermerRecord termerRecord = termerRecordService.getTermerRecordById(taskListReply
					.getTaskId());
			if (termerRecord.getStatus() != 1) {
				throw new BusinessValidationException("未签收的不能回复");
			}
			termerRecord.setHasReplay(1);
			if(termerRecord.getReplayDate()==null){
				termerRecord.setReplayDate(new Date());
			}
			termerRecordService.updateTermerRecord(termerRecord);
		} else if (Constants.REPLY_POSITIVEINFORECORD_KEY.equals(taskListReply.getModuleKey())) {
			PositiveInfoRecord positiveInfoRecord = positiveInfoRecordService
					.getPositiveInfoRecordById(taskListReply.getTaskId());
			if (positiveInfoRecord.getStatus() != 1) {
				throw new BusinessValidationException("未签收的不能回复");
			}
			positiveInfoRecord.setHasReplay(1);
			if(positiveInfoRecord.getReplayDate()==null){
				positiveInfoRecord.setReplayDate(new Date());
			}
			positiveInfoRecordService.updatePositiveInfoRecord(positiveInfoRecord);
		} else {
			throw new BusinessValidationException("参数[moduleKey]错误");
		}
		taskListReply.setReplyDate(new Date());
		taskListReply.setReplyUser(ThreadVariable.getUser().getName());
		taskListReply.setReplyUserId(ThreadVariable.getUser().getId());
		TaskListReply saveTaskListReply = taskListReplyService.addTaskListReply(taskListReply);
		//附件保存
		if (taskListReply.getAttachFileNames() != null) {
			for (String s : taskListReply.getAttachFileNames()) {
				if (StringUtils.isNotBlank(s)) {
					try {
						addAttachFileForTemp(s.trim(), taskListReply.getModuleKey(),
								saveTaskListReply.getId());
					} catch (Exception e) {
						throw new ServiceValidationException("附件保存失败", e);
					}
				}
			}
		}

		return taskListReply;
	}

	@Override
	public List<TaskListReply> queryTaskListReplyByTaskId(String moduleKey, Long taskId) {
		if (StringUtils.isBlank(moduleKey)) {
			throw new BusinessValidationException("参数缺失[moduleKey]");
		}
		if (taskId == null) {
			throw new BusinessValidationException("参数缺失[taskId]");
		}
		return taskListReplyService.queryTaskListReplyByTaskId(moduleKey, taskId);
	}

	private void addAttachFileForTemp(String attachFileName, String moduleKey, Long taskId)
			throws Exception {
		TaskListAttachFile taskListAttachFile = new TaskListAttachFile();
		taskListAttachFile.setBusinessId(taskId);
		StoredFile storeFile = FileUtil.copyTmpFileToStoredFile(attachFileName,
				GridProperties.TASKLIST_PATH);
		taskListAttachFile.setPhysicsFullFileName(storeFile.getStoredFilePath()
				+ File.separator + storeFile.getStoredFileName());
		taskListAttachFile.setFileName(attachFileName);
		taskListAttachFile.setModuleKey(moduleKey);
		taskListAttachFileService.addTaskListAttachFile(taskListAttachFile);
	}

	@Override
	public List<TaskListStatistics> getTaskListStatistics(Long orgId,
			Integer searchType, Integer year, Integer month, Integer week,
			Integer searchYear) {
		if(orgId==null){
			throw new BusinessValidationException("查询失败，组织机构参数未获得");
		}
		String key =  MemCacheConstant.getTaskListReportCachKey(MemCacheConstant.TASK_LIST_NEW_STATISTICS, orgId,searchType,year,month,week,searchYear);
		List<TaskListStatistics> list=null;
		if(StringUtil.isStringAvaliable(key)){
			list = (List<TaskListStatistics>) cacheService.get(MemCacheConstant.TASK_LIST_ALL_NAMESPACE, key);
			if(list!=null){
				return list;
			}
		}
		try {
			Map<String,Object> map = getDateByCondition(searchType, year, month, week,searchYear);
			Long orgType = propertyDictService.findPropertyDictByDomainNameAndDictDisplayName(
					PropertyTypes.ORGANIZATION_TYPE, OrganizationType.ADMINISTRATIVE_KEY).getId();
			map.put("orgType", orgType);
			map.put("parentOrgId", orgId);
			//获取各个层级的走访，未走访，走访率
			list = receiptDao.getTaskListStatistics(map);
			if(list!=null){
				for(TaskListStatistics taskListStatistics:list){
					taskListStatistics.setVisitsProportionOfDruggy(calculationPercentage(taskListStatistics.getVisitsDruggyNumber(),taskListStatistics.getDruggyPopulationNum()));
					taskListStatistics.setVisitsProportionOfMentalPatient(calculationPercentage(taskListStatistics.getVisitsMentalPatientNumber(),taskListStatistics.getMentalPatientPopulationNum()));
					taskListStatistics.setVisitsProportionOfRectificative(calculationPercentage(taskListStatistics.getVisitsRectificativeNumber(),taskListStatistics.getRectificativePopulationNum()));
					taskListStatistics.setVisitsProportionOfPositiveInfo(calculationPercentage(taskListStatistics.getVisitsPositiveInfoNumber(),taskListStatistics.getPositiveInfoPopulationNum()));
				}
			}
			if(StringUtil.isStringAvaliable(key)){
				cacheService.set(MemCacheConstant.TASK_LIST_ALL_NAMESPACE, key,ModulTypes.CACHETIME, list);
			}
			return list;
		} catch (Exception e) {
			throw new ServiceValidationException("获取任务清单新报表出错", e);
		}
	}

	private String calculationPercentage(int molecule,int denominator){
		String result="";
		if(molecule==0 || denominator==0){
			result="0.00%";
		}else{
			DecimalFormat df = new DecimalFormat("######0.00");
			double data = (double)molecule / (double)denominator * 100;
			result = df.format(data) + "%";
		}
		return result;
	}
}
