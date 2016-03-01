package com.tianque.plugin.statistics.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.PropertyDomain;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.analyzing.util.AnalyseUtil;
import com.tianque.plugin.statistics.constants.GeneralStituationConstants;
import com.tianque.plugin.statistics.constants.TypeConstants;
import com.tianque.plugin.statistics.dao.GeneralSituationDao;
import com.tianque.plugin.statistics.domain.GeneralSituation;
import com.tianque.plugin.statistics.service.GeneralSituationService;
import com.tianque.plugin.statistics.service.PacketTaskListStatisticsService;
import com.tianque.plugin.statistics.util.GenneralsituationUtil;
import com.tianque.plugin.statistics.vo.GeneralStituationSearchVo;
import com.tianque.plugin.taskList.constant.TaskConstant;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.tableManage.service.TableManageService;
import com.tianque.userAuth.api.PropertyDictDubboService;
import com.tianque.userAuth.api.PropertyDomainDubboService;

@Service("generalSituationService")
@Transactional
public class GeneralSituationServiceImpl implements GeneralSituationService {

	@Autowired
	private TableManageService tableService;
	@Autowired
	private GeneralSituationDao generalSituationDao;
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;
	@Autowired
	private PacketTaskListStatisticsService packetTaskListStatisticsService;
	@Autowired
	private JobMonitorService jobMonitorService;

	/**
	 * 研判分析报表统计
	 * job根据年月、年季度、年年度条件
	 */
	@Override
	public void createGeneralSituationByDate(Integer year, Integer month,Integer quarter,
			Integer createDataType,Integer yearType) {
		if (year == null || createDataType==null) {
			throw new BusinessValidationException("报表数据生成失败，未获得时间信息");
		}
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName()
				+ "  类型：任务清单历史数据生成开始");
		long startTime = System.currentTimeMillis();
		try {
			Date startDate = null;
			Date endDate = null;
			String tableName = "";
			if (GeneralStituationConstants.SEARCH_BY_MONTH
					.equals(createDataType)) {
				Boolean isCreate = tableService
						.createAnalyseTable(
								AnalyseUtil.TASK_LIST_GENERALSITUATION_MONTH_TABLENAME,
								AnalyseUtil.TASK_LIST_GENERALSITUATION_SQL,
								year, month);
				tableName = AnalyseUtil.TASK_LIST_GENERALSITUATION_MONTH_TABLENAME
						+ "_" + year + "_" + month;
				if (!isCreate) {
					// 表已经存在则删除历史数据
					generalSituationDao.deleteGeneralSituationByDate(tableName);
				}
				startDate = CalendarUtil.getMonthStart(year, month);// 本月开始
				endDate = CalendarUtil.getNextMonthStart(year, month);// 本月结束
			} else if (GeneralStituationConstants.SEARCH_BY_QUARTER
					.equals(createDataType)) {
				// 根据传入的月份获得当前所在季度
				switch (quarter) {
				case 1:
					startDate = CalendarUtil.getMonthStart(year, 1);
					endDate = CalendarUtil.getNextMonthStart(year, 3);
					break;
				case 2:
					startDate = CalendarUtil.getMonthStart(year, 4);
					endDate = CalendarUtil.getNextMonthStart(year, 6);
					break;
				case 3:
					startDate = CalendarUtil.getMonthStart(year, 7);
					endDate = CalendarUtil.getNextMonthStart(year, 9);
					break;
				case 4:
					startDate = CalendarUtil.getMonthStart(year, 10);
					endDate = CalendarUtil.getNextMonthStart(year, 12);
					break;
				default:
					throw new BusinessValidationException("所选择月份不正确");
				}
				Boolean isCreate = tableService
						.createAnalyseTable(
								AnalyseUtil.TASK_LIST_GENERALSITUATION_QUARTER_TABLENAME,
								AnalyseUtil.TASK_LIST_GENERALSITUATION_SQL,
								year, quarter);
				tableName = AnalyseUtil.TASK_LIST_GENERALSITUATION_QUARTER_TABLENAME
						+ "_" + year + "_" + quarter;
				if (!isCreate) {
					// 表已经存在则删除历史数据
					generalSituationDao.deleteGeneralSituationByDate(tableName);
				}
			}else if(GeneralStituationConstants.SEARCH_BY_YEAR
					.equals(createDataType)){
				if(yearType==0){
					Boolean isCreate = tableService
					.createAnalyseTableForYear(
							AnalyseUtil.TASK_LIST_GENERALSITUATION_FIR_YEAR_TABLENAME,
							AnalyseUtil.TASK_LIST_GENERALSITUATION_SQL,
							year);
					tableName = AnalyseUtil.TASK_LIST_GENERALSITUATION_FIR_YEAR_TABLENAME
							+ "_" + year;
					if (!isCreate) {
						// 表已经存在则删除历史数据
						generalSituationDao.deleteGeneralSituationByDate(tableName);
					}
					startDate = CalendarUtil.getMonthStart(year, 1);
					endDate = CalendarUtil.getNextMonthStart(year, 6);
				}else if(yearType==1){
					Boolean isCreate = tableService
					.createAnalyseTableForYear(
							AnalyseUtil.TASK_LIST_GENERALSITUATION_SCO_YEAR_TABLENAME,
							AnalyseUtil.TASK_LIST_GENERALSITUATION_SQL,
							year);
					tableName = AnalyseUtil.TASK_LIST_GENERALSITUATION_SCO_YEAR_TABLENAME
							+ "_" + year;
					if (!isCreate) {
						// 表已经存在则删除历史数据
						generalSituationDao.deleteGeneralSituationByDate(tableName);
					}
					startDate = CalendarUtil.getMonthStart(year, 6);
					endDate = CalendarUtil.getNextMonthStart(year, 12);
				}else{
					Boolean isCreate = tableService
					.createAnalyseTableForYear(
							AnalyseUtil.TASK_LIST_GENERALSITUATION_YEAR_TABLENAME,
							AnalyseUtil.TASK_LIST_GENERALSITUATION_SQL,
							year);
					tableName = AnalyseUtil.TASK_LIST_GENERALSITUATION_YEAR_TABLENAME
							+ "_" + year;
					if (!isCreate) {
						// 表已经存在则删除历史数据
						generalSituationDao.deleteGeneralSituationByDate(tableName);
					}
					startDate = CalendarUtil.getMonthStart(year, 1);
					endDate = CalendarUtil.getNextMonthStart(year, 12);
				}
			}
			// 因为手机用户可以录入社区层级数据，所以统计使用社区层级level
			Long orgLevel = propertyDictDubboService
					.findPropertyDictByDomainNameAndDictDisplayName(
							PropertyTypes.ORGANIZATION_LEVEL,
							OrganizationLevel.VILLAGE_KEY).getId();
			Long orgType = propertyDictDubboService
					.findPropertyDictByDomainNameAndDictDisplayName(
							OrganizationType.ORG_TYPE_KEY,
							OrganizationType.ADMINISTRATIVE_KEY).getId();
			Map<String, String> columnMap = GeneralStituationConstants.columnMap;
			Map<String, String> subTypeColumnMap = GeneralStituationConstants.subTypeColumnMap;
			/*
			 * 注释: 1.流动人口下划分为 异常情况、民警带领下开展工作、宣传核查，其中宣传核查数据新增的时候没有固定的数据字典子类别。
			 * 异常情况、民警带领下开展工作新增时都有子类别划分。
			 * 发现治安隐患新增时也存在子类别，但是他只有两层子类别，所以当下与异常情况和民警工作一起查询， 将治安隐患历史数据查询时*
			 * detailType与basicType写成相同，到时候查询统计时注意参数控制即可
			 * 2.吸毒、社区服刑、刑释只有一层类别，而且新增中无子类别
			 * ，所以直接统计即可，宣传核查相比只是多了一个流口大类别，所以只需要做特殊判断处理就好
			 * 3.重症精神病人员单独提取是因为重症精神病人签收分为卫生所签收和派出所签收，在字段以不同的字段保存
			 * 4.当前历史数据生成是只生成网格层级数据，最后通过sql代码叠加，将网格数据一直递推到中国层级，主要保证查询的时候速度快
			 */
			// 治安隐患、异常情况、民警带领下开展工作
			for (Entry<String, String> entry : GeneralStituationConstants.hasSubTypeMap
					.entrySet()) {
				String basicType = "";
				if (GeneralStituationConstants.WORKINGSITUATION_KEY
						.equals(entry.getKey())
						|| GeneralStituationConstants.EXCEPTIONALSITUATIONRECORD_KEY
								.equals(entry.getKey())) {
					basicType = GeneralStituationConstants.FLOATING_POPULATION_KEY;
				} else {
					basicType = GeneralStituationConstants.HIDDENDANGGER_KEY;
				}
				generalSituationDao.createGeneralSituationByType(tableName,
						year, month, orgType, orgLevel, entry.getValue(),
						basicType, entry.getKey(),
						subTypeColumnMap.get(entry.getKey()),
						columnMap.get(entry.getKey()), startDate, endDate);
			}
			// 吸毒、社区服刑、刑释、宣传核查
			for (Entry<String, String> entry : GeneralStituationConstants.tableMap
					.entrySet()) {
				if (GeneralStituationConstants.PROPAGANDAANDVERIFICATION_KEY
						.equals(entry.getKey())) {
					generalSituationDao.createGeneralSituationByDate(tableName,
							year, month, orgType, orgLevel, entry.getValue(),
							GeneralStituationConstants.FLOATING_POPULATION_KEY,
							entry.getKey(), columnMap.get(entry.getKey()),
							startDate, endDate);
				} else {
					generalSituationDao.createGeneralSituationByDate(tableName,
							year, month, orgType, orgLevel, entry.getValue(),
							entry.getKey(), null,
							columnMap.get(entry.getKey()), startDate, endDate);
				}
			}
			// 精神病(有两个签收状态，所以需要分开统计)
			generalSituationDao.createGeneralSituationOfMentalpatient(
					tableName, year, month, orgType, orgLevel,
					GeneralStituationConstants.SEVEREPSYCHOSIS_KEY, startDate,
					endDate);

			// 数据累加操作
			packetTaskListStatisticsService
					.generalsituationStatistics(tableName);
			// 将orgId为null的数据清除
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime) + "生成任务清单研判历史数据", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"生成任务清单研判历史数据失败，原因："+e.getMessage(), false);
			throw new ServiceValidationException("数据生成失败", e);
		}
	}

	/***
	 * 饼状图只能选择一个组织机构信息
	 */
	@Override
	public List<Object[]> getTaskListOfPie(
			GeneralStituationSearchVo generalStituationSearchVo) {
		List<Object[]> objectList = new ArrayList<Object[]>();
		List<GeneralSituation> list = getTaskListOfColumn(generalStituationSearchVo, "pie");
		List<Object> hasType = new ArrayList<Object>();
		if(list!=null && list.size()!=0){
			int total = calculationTotal(list, generalStituationSearchVo.getIsSign());
			for(GeneralSituation generalSituation:list){
				Object[] obj= null;
				if (generalStituationSearchVo.getBasicType().length > 1
						|| (generalStituationSearchVo.getBasicType().length == 1 && TypeConstants.PANDECT_KEY
								.equals(generalStituationSearchVo.getBasicType()[0]))
						|| (generalStituationSearchVo.getBasicType().length == 1 && generalStituationSearchVo
								.getDetailType() == null)) {
					// 选择了多个主类别或者选择总况的情况
					hasType.add(generalSituation.getSubType());
					obj = dealTaskListPie(generalSituation, generalStituationSearchVo, total, 0);
				} else if ((generalStituationSearchVo.getBasicType().length == 1 && generalStituationSearchVo
						.getDetailType().length > 1)
						|| (generalStituationSearchVo.getDetailType().length == 1 && generalStituationSearchVo
								.getSubType() == null)) {
					// 选择了一个主类别，一个或多个子类别，没有详细类别
					// 选择了多个主类别或者选择总况的情况
					hasType.add(generalSituation.getDetailType());
//					if(TypeConstants.HIDDENDANGGER_KEY.equals(generalStituationSearchVo.getBasicType()[0])){
//						obj = dealTaskListPie(generalSituation, generalStituationSearchVo, total, 2);
//					}else{
						obj = dealTaskListPie(generalSituation, generalStituationSearchVo, total, 1);
//					}
				} else if (generalStituationSearchVo.getBasicType().length == 1
						&& generalStituationSearchVo.getDetailType().length == 1
						&& generalStituationSearchVo.getSubType().length > 0) {
					// 选择了一个主、一个子、一个或多个详细类别
					hasType.add(generalSituation.getSubType());
					obj = dealTaskListPie(generalSituation, generalStituationSearchVo, total, 2);
				}
				objectList.add(obj);
			}
			//将已选择的但是数据库不存在的封装成0数据放入list
		}else{
			//根据条件创建空的对象
			if (generalStituationSearchVo.getBasicType().length > 1
					|| (generalStituationSearchVo.getBasicType().length == 1 && TypeConstants.PANDECT_KEY
							.equals(generalStituationSearchVo.getBasicType()[0]))
					|| (generalStituationSearchVo.getBasicType().length == 1 && generalStituationSearchVo
							.getDetailType() == null)) {
				// 选择了多个主类别或者选择总况的情况
				for (String key:generalStituationSearchVo.getBasicType()) {
					if(TypeConstants.typeMapName.get(key)!=null){
						 Object[] obj = new Object[2];
						 obj[0] = TypeConstants.typeMapName.get(key)+"(0)";
						 obj[1] =0 ;
						 objectList.add(obj);
					}
				}
			} else if ((generalStituationSearchVo.getBasicType().length == 1 && generalStituationSearchVo
					.getDetailType().length > 1)
					|| (generalStituationSearchVo.getDetailType().length == 1 && generalStituationSearchVo
							.getSubType() == null)) {
				for (String key:generalStituationSearchVo.getDetailType()) {
					if(TypeConstants.typeMapName.get(key)!=null){
						 Object[] obj = new Object[2];
						 obj[0] = TypeConstants.typeMapName.get(key)+"(0)";
						 obj[1] =0 ;
						 objectList.add(obj);
					}
				}
			} else if (generalStituationSearchVo.getBasicType().length == 1
					&& generalStituationSearchVo.getDetailType().length == 1
					&& generalStituationSearchVo.getSubType().length > 0) {
				// 选择了一个主、一个子、一个或多个详细类别
				for(Long id:generalStituationSearchVo.getSubType()){
					PropertyDict dict = propertyDictDubboService.getPropertyDictById(id);
					if(dict!=null){
						Object[] obj = new Object[2];
						 obj[0] = dict.getDisplayName()+"(0)";
						 obj[1] =0 ;
						 objectList.add(obj);
					}
				}
			}
		}
		for(Object[] obj:objectList){
			System.out.println(obj[0]+"==="+obj[1]);
		}
		return objectList;
	}
	
	private int calculationTotal(List<GeneralSituation> list,Integer isSign){
		int total = 0 ;
		for(GeneralSituation generalSituation:list){
			if(isSign==0){//上报
				total += generalSituation.getMonthCreateCount();
			}else{//签收
				total += generalSituation.getMonthCreateSign();
			}
		}
		return total;
	}
	
	/***
	 * 数据封装
	 */
	private Object[] dealTaskListPie(GeneralSituation generalSituation,GeneralStituationSearchVo generalStituationSearchVo,int total,Integer searchType){
		if(searchType==null){
			throw new BusinessValidationException("数据封装错误");
		}
		Object [] object = new Object[2];
		if(searchType==0){//根据主类别
			String columnName = TypeConstants.typeMapName.get(generalSituation.getBasesicType());
			object[0] = columnName+"("+generalSituation.getMonthCreateCount()+")"; 
		}else if(searchType==1){//根据子类别
			String columnName = TypeConstants.typeMapByName.get(generalSituation.getDetailType());
			object[0] = columnName+"("+generalSituation.getMonthCreateCount()+")"; 
		}else{//根据详情类别
			PropertyDict propertyDict = propertyDictDubboService.getPropertyDictById(generalSituation.getSubType());
			if(propertyDict==null){
				throw new BusinessValidationException("数据封装出错");
			}
			object[0] = propertyDict.getDisplayName()+"("+generalSituation.getMonthCreateCount()+")"; 
		}
		if(generalStituationSearchVo.getIsSign()==0){//上报
			object[1] = total== 0 ? 0 : Double.parseDouble(new java.text.DecimalFormat("#.00").format((double)generalSituation.getMonthCreateCount() / total * 100));
		}else{//签收
			object[1] = total== 0 ? 0 : Double.parseDouble(new java.text.DecimalFormat("#.00").format((double)generalSituation.getMonthCreateSign() / total * 100));
		}
		
		return object;
	}
	
	@Override
	public List<GeneralSituation> getTaskListOfColumn(
			GeneralStituationSearchVo generalStituationSearchVo,String searchType) {
		if (generalStituationSearchVo == null
				|| generalStituationSearchVo.getOrgIds() == null
				|| generalStituationSearchVo.getYear() == null
				|| generalStituationSearchVo.getMonth() == null) {
			throw new BusinessValidationException("查询失败，参数未获得");
		}
		if("pie".equals(searchType) && generalStituationSearchVo.getOrgIds().size()>1){
			throw new BusinessValidationException("查询失败，参数未获得");
		}
		List<GeneralSituation> list = null;
		Map<String, Object> map = new HashMap<String, Object>();
		String tableName = getTableName(generalStituationSearchVo);
		if (!StringUtil.isStringAvaliable(tableName)) {
			throw new BusinessValidationException("未获得正确表名");
		}
		map.put("tableName", tableName);
		map.put("orgIds", generalStituationSearchVo.getOrgIds());
		if (generalStituationSearchVo.getBasicType().length > 1
				|| (generalStituationSearchVo.getBasicType().length == 1 && TypeConstants.PANDECT_KEY
						.equals(generalStituationSearchVo.getBasicType()[0]))
				|| (generalStituationSearchVo.getBasicType().length == 1 && generalStituationSearchVo
						.getDetailType() == null)) {
			// 选择了多个主类别或者选择总况的情况
			if (!TypeConstants.PANDECT_KEY.equals(generalStituationSearchVo
					.getBasicType()[0])) {
				map.put("basesicType", generalStituationSearchVo.getBasicType());
			}
			list = generalSituationDao
					.findGeneralSituationListByBasesicType(map);
		} else if ((generalStituationSearchVo.getBasicType().length == 1 && generalStituationSearchVo
				.getDetailType().length > 1)
				|| (generalStituationSearchVo.getDetailType().length == 1 && generalStituationSearchVo
						.getSubType() == null)) {
			// 选择了一个主类别，一个或多个子类别，没有详细类别
			map.put("basesicType", generalStituationSearchVo.getBasicType());
			map.put("detailType", generalStituationSearchVo.getDetailType());
			list = generalSituationDao
					.findGeneralSituationListByDetailType(map);
		} else if (generalStituationSearchVo.getBasicType().length == 1
				&& generalStituationSearchVo.getDetailType().length == 1
				&& generalStituationSearchVo.getSubType().length > 0) {
			// 选择了一个主、一个子、一个或多个详细类别
			map.put("basesicType", generalStituationSearchVo.getBasicType());
			map.put("detailType", generalStituationSearchVo.getDetailType());
			map.put("subType", generalStituationSearchVo.getSubType());
			list = generalSituationDao.findGeneralSituationListBySubType(map);
		}

		if (list != null && list.size() != 0) {
			for (GeneralSituation g : list) {
				System.out.println(g.getOrganization().getOrgName() + "=主类别="
						+ g.getBasesicType() + "=子类别=" + g.getDetailType()
						+ "=详细类别=" + g.getSubType() + "==月签收:"
						+ g.getMonthCreateSign() + "===月上报"
						+ g.getMonthCreateCount());
			}
		}
		return list;
	}

	/***
	 * 判断所查询的表是否存在，并且返回表名
	 */
	public String getTableName(
			GeneralStituationSearchVo generalStituationSearchVo) {
		String tableName = null;
		if (generalStituationSearchVo.getDateSearchType() == null) {
			return tableName;
		}
		if (GeneralStituationConstants.SEARCH_BY_MONTH
				.equals(generalStituationSearchVo.getDateSearchType())) {
			tableService.createAnalyseTable(
					AnalyseUtil.TASK_LIST_GENERALSITUATION_MONTH_TABLENAME,
					AnalyseUtil.TASK_LIST_GENERALSITUATION_SQL,
					generalStituationSearchVo.getYear(),
					generalStituationSearchVo.getMonth());
			tableName = AnalyseUtil.TASK_LIST_GENERALSITUATION_MONTH_TABLENAME
					+ "_" + generalStituationSearchVo.getYear() + "_"
					+ generalStituationSearchVo.getMonth();
		} else if (GeneralStituationConstants.SEARCH_BY_QUARTER
				.equals(generalStituationSearchVo.getDateSearchType())) {
			tableService.createAnalyseTable(
					AnalyseUtil.TASK_LIST_GENERALSITUATION_QUARTER_TABLENAME,
					AnalyseUtil.TASK_LIST_GENERALSITUATION_SQL,
					generalStituationSearchVo.getYear(),
					generalStituationSearchVo.getQuarter());
			tableName = AnalyseUtil.TASK_LIST_GENERALSITUATION_QUARTER_TABLENAME
					+ "_"
					+ generalStituationSearchVo.getYear()
					+ "_"
					+ generalStituationSearchVo.getQuarter();
		} else if (GeneralStituationConstants.SEARCH_BY_YEAR
				.equals(generalStituationSearchVo.getDateSearchType())) {
			// 按年查詢暫時不做
		}
		return tableName;
	}

}
