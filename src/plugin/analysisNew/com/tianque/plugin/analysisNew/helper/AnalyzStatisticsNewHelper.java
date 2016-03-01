package com.tianque.plugin.analysisNew.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationType;
import com.tianque.plugin.analyzing.domain.StatisticsBaseInfoAddCountVo;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * @Description:研判分析帮助类
 * @author zhangyouwei@hztian.com
 * @date: 2014-8-22 上午10:56:14
 */
@Component("analyzStatisticsNewHelper")
public class AnalyzStatisticsNewHelper {

	@Autowired
	private PropertyDictService propertyDictService;

	/**
	 * 获取当前月份的历史月份（向前推delayMonths个月份）
	 * 
	 * @param time
	 * @param delayMonths
	 * @return
	 */
	public String[] getTime(String[] time, Integer delayMonths) {
		Date nowDate = new Date();
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		nowCalendar.add(Calendar.MONTH, -12);
		for (int i = 0; i < 12; i++) {
			SimpleDateFormat timePattern = new SimpleDateFormat("yyyy-MM");
			time[i] = timePattern.format(nowCalendar.getTime());
			nowCalendar.add(Calendar.MONTH, 1);
		}
		return time;
	}

	/**
	 * 排序操作
	 * 
	 * @param time
	 * @return
	 */
	public String[] sortTime(String[] time) {
		String[] sortTime = new String[12];
		for (int j = time.length - 1; j >= 0; j--) {
			sortTime[time.length - 1 - j] = time[j];
		}
		return sortTime;
	}

	/**
	 * 根据取得的数据做一个合计的操作（统计出总数）
	 * 
	 * @param voList
	 * @return
	 */
	public List<StatisticsBaseInfoAddCountVo> getLastResult(
			List<StatisticsBaseInfoAddCountVo> voList) {
		StatisticsBaseInfoAddCountVo total = new StatisticsBaseInfoAddCountVo();
		Integer totalTodayAddCount = 0;
		Integer totalThisMonthCount = 0;
		Integer totalAttentionCount = 0;
		Integer totalAllCount = 0;
		for (StatisticsBaseInfoAddCountVo vo : voList) {
			totalTodayAddCount += vo.getTodayAddCount();
			totalThisMonthCount += vo.getThisMonthCount();
			totalAttentionCount += vo.getAttentionCount();
			totalAllCount += vo.getAllCount();
		}
		total.setStatisticsType("合计");
		total.setSeq(1000L);
		total.setTodayAddCount(totalTodayAddCount);
		total.setThisMonthCount(totalThisMonthCount);
		total.setAttentionCount(totalAttentionCount);
		total.setAllCount(totalAllCount);
		voList.add(total);
		return voList;
	}

	/**
	 * 取出网格类型是行政部门的对应的字典项的id的集合
	 * 
	 * @return
	 */
	public List<Long> getDictIds() {
		List<PropertyDict> orgTypes = propertyDictService
				.findPropertyDictByDomainNameAndInternalIds(
						OrganizationType.ORG_TYPE_KEY, new int[] {
								OrganizationType.ADMINISTRATIVE_REGION,
								OrganizationType.OTHER });
		List<Long> dictIds = new ArrayList<Long>();
		for (PropertyDict dict : orgTypes) {
			dictIds.add(dict.getId());
		}
		return dictIds;
	}

	public int getOrgLevelInternalIdByOrg(Organization org) {

		int orgLevelInternalId = 0;
		if (org != null && org.getOrgLevel() != null) {
			PropertyDict dict = propertyDictService.getPropertyDictById(org
					.getOrgLevel().getId());
			if (dict != null) {
				orgLevelInternalId = dict.getInternalId();
			}
		}
		return orgLevelInternalId;
	}
}
