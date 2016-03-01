package com.tianque.plugin.analysisNew.service;

import java.util.List;

import com.tianque.domain.Organization;
import com.tianque.plugin.analysisNew.domain.BaseinfoStatisticVo;
import com.tianque.plugin.analysisNew.domain.HighchartColumnVo;

public interface BaseinfoStatisticNewService {
	/**
	 * 根据年和月份统计数据， 先判断当前的查询日期数据是否生产，生产过的查询统计表，没有生成的查询目标表
	 * 
	 * @param orgId
	 *            组织机构id
	 * @param year
	 *            统计的年
	 * @param month
	 *            统计的月
	 * @param type
	 *            查询的关键字,通过type解析表名，分类的依据等初始化数据
	 * @return
	 */
	public List<BaseinfoStatisticVo> getStatisticInfoForList(long orgId,
			int year, int month, String type, String domainName,
			Integer orgLevelDistance);

	/**
	 * 生成类型分布图数据
	 * 
	 * @param year
	 * @param month
	 * @param type
	 * @param orgInternalCode
	 * @param domainName
	 * @return
	 */
	public List<Object[]> getStatisticInfo(int year, int month, String type,
			Organization org, String domainName);

	/**
	 * 根据组织机构和type标示查询该组织机构往下一级的区域分布图数据
	 * 
	 * @param type
	 * @param orgId
	 * @return
	 */
	public HighchartColumnVo getArealDistributionList(String type, Long orgId);

	/**
	 * 根据组织机构、type和年月在历史数据表查询该组织机构往下一级的区域分布图数据
	 * 
	 * @param orgId
	 * @param type
	 * @param year
	 * @param month
	 * @return
	 */
	public HighchartColumnVo getArealDistributionListFromHistory(Long orgId,
			String type, int year, int month);

	/**
	 * 生成所有的重点人员的统计
	 */
	public void generateHistoryStatistic();

	/**
	 * 根据组织机构标示查询该组织机构往下一级的实有房屋区域分布图数据
	 * 
	 * @param orgId
	 * @return
	 */
	public HighchartColumnVo getArealDistributionListForWorkBench(Long orgId);

	/**
	 * 根据组织机构标示查询该组织机构往下一级的事件在办/已办 区域分布图数据
	 * 
	 * @param orgId
	 * @return
	 */
	public HighchartColumnVo getIssueListForWorkBench(Long orgId);

	public int getImportPersonCount(Long orgId);

	public int getrentalHouseCountByTypeAndId(String type, Long orgId);

	public void addCurrentMonthHistoryStatisticInfoByType(String type);

	/**
	 * 根据年和月份生产统计数据，
	 */
	public void createHistoryStatisticByType(int year, int month, String type,
			String orgInternalCode);

	public void createHistoryStatisticByType(int year, int month);
	
	/**
	 * 关于重点青少年添加查询条件('0~6岁'，'6~18岁'，'12~25岁'，'25~35岁')
	 */
	public String getIdleYouth_TypeName();
}
