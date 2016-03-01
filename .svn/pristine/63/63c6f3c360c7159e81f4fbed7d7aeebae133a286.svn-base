package com.tianque.baseInfo.base.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tianque.baseInfo.base.constant.OperateType;
import com.tianque.baseInfo.base.domain.DuplicatePeople;
import com.tianque.baseInfo.base.domain.PoepleDuplicateRemovalLog;

/***
 * @Description:去除重复数据的工具类
 * @author zhangyouwei@hztian.com
 * @date: 2014-8-6 下午10:14:07
 */
public class PeopleDuplicateRemovalHelper {

	private static Gson gson = new GsonBuilder().serializeNulls()
			.setPrettyPrinting().setVersion(2.2).create();

	/**
	 * 判定人员的重复数据是以哪一条记录为标准的（必须要有baseId,logOut,updateDate,id，tableName等属性有值）
	 * 
	 * @param businessPopulations
	 * @return
	 */
	public static DuplicatePeople decideBusinessPopulationDuplicate(
			List<DuplicatePeople> businessPopulations) {
		DuplicatePeople temp = null;
		/** 未注销的 */
		List<DuplicatePeople> unLogOuts = new ArrayList<DuplicatePeople>();

		/** 已注销的 */
		List<DuplicatePeople> logOuts = new ArrayList<DuplicatePeople>();
		if (businessPopulations != null && businessPopulations.size() > 0) {
			// temp = businessPopulations.get(0);
			for (DuplicatePeople duplicatePeople : businessPopulations) {
				if (duplicatePeople.getLogOut() != null
						&& duplicatePeople.getLogOut().intValue() == 0) {
					unLogOuts.add(duplicatePeople);
				} else {
					logOuts.add(duplicatePeople);
				}
			}

			if (unLogOuts.size() == 1) {// 只有一条为注销的数据
				temp = unLogOuts.get(0);
			} else if (unLogOuts.size() > 1) {// 有多条未注销的数据
				temp = unLogOuts.get(0);
				for (DuplicatePeople duplicatePeople : unLogOuts) {
					if ((temp.getUpdateDate() == null ? temp.getCreateDate()
							: temp.getUpdateDate())
							.before((duplicatePeople.getUpdateDate() == null ? duplicatePeople
									.getCreateDate() : duplicatePeople
									.getUpdateDate()))) {
						temp = duplicatePeople;
					}
				}
			} else if (unLogOuts.size() == 0) {// 全部是已注销
				temp = logOuts.get(0);
				for (DuplicatePeople duplicatePeople : logOuts) {
					if ((temp.getUpdateDate() == null ? temp.getCreateDate()
							: temp.getUpdateDate())
							.before((duplicatePeople.getUpdateDate() == null ? duplicatePeople
									.getCreateDate() : duplicatePeople
									.getUpdateDate()))) {
						temp = duplicatePeople;
					}
				}
			}

		}

		return temp;
	}

	public static PoepleDuplicateRemovalLog fillPoepleDuplicateRemovalLogByDuplicatePeopleAndOperateType(
			DuplicatePeople duplicatePeople, String operateType) {
		PoepleDuplicateRemovalLog poepleDuplicateRemovalLog = null;
		if (duplicatePeople != null && duplicatePeople.getId() != null
				&& StringUtils.isNotBlank(duplicatePeople.getTableName())
				&& StringUtils.isNotBlank(duplicatePeople.getIdCardNo())
				&& duplicatePeople.getBaseInfoId() != null
				&& StringUtils.isNotBlank(operateType)) {
			poepleDuplicateRemovalLog = new PoepleDuplicateRemovalLog();

			/** IdCardNo */
			poepleDuplicateRemovalLog
					.setIdCardNo(duplicatePeople.getIdCardNo());
			/** baseinfoId */
			poepleDuplicateRemovalLog.setBaseInfoId(duplicatePeople
					.getBaseInfoId());

			/** dataId */
			poepleDuplicateRemovalLog.setDataId(duplicatePeople.getId());

			/** dataType */
			poepleDuplicateRemovalLog.setDataType(duplicatePeople
					.getTableName());

			/** operateDate */
			poepleDuplicateRemovalLog.setOperateDate(new Date());
			/** operateType */
			poepleDuplicateRemovalLog.setOperateType(operateType);
			/** baseInfo */
			if (OperateType.delete.name().equals(operateType)
					&& duplicatePeople.getCountrymen() != null) {
				poepleDuplicateRemovalLog.setBaseInfo(gson
						.toJson(duplicatePeople.getCountrymen()));
			}
			/** dataOrg */
			if (duplicatePeople.getOrganization() != null
					&& duplicatePeople.getOrganization().getId() != null) {
				poepleDuplicateRemovalLog.setDataOrg(duplicatePeople
						.getOrganization());
			}

		}
		return poepleDuplicateRemovalLog;
	}

	/**
	 * 根据传入的所有重复的数据返回分组后的重复数据的集合（本来是所有的重复数据在一个list里面的， 经过处理后就是同身份证号的数据在同一个里面）
	 * 
	 * @param allBaseInfodDuplicatePeoples
	 *            （就是将baseinfo表的所有重复数据取出来放在一个list里面）
	 * @return
	 */
	public static List<List<DuplicatePeople>> getAllBaseInfoDuplicatePeople(
			List<DuplicatePeople> allBaseInfoDuplicatePeoples) {
		if (allBaseInfoDuplicatePeoples == null
				|| allBaseInfoDuplicatePeoples.size() < 0) {
			return null;
		}
		Map<String, List<DuplicatePeople>> map = new HashMap<String, List<DuplicatePeople>>();
		for (DuplicatePeople duplicatePeople : allBaseInfoDuplicatePeoples) {
			if (StringUtils.isNotBlank(duplicatePeople.getIdCardNo())
					&& map.containsKey(duplicatePeople.getIdCardNo())) {// 存在，并且身份证号码已经放在map里面了（即存在对应的list了）
				map.get(duplicatePeople.getIdCardNo()).add(duplicatePeople);
			} else if (StringUtils.isNotBlank(duplicatePeople.getIdCardNo())
					&& !map.containsKey(duplicatePeople.getIdCardNo())) {// 身份证号码没有存在map中（新建list然后放入map中）
				List<DuplicatePeople> list = new ArrayList<DuplicatePeople>();
				list.add(duplicatePeople);
				map.put(duplicatePeople.getIdCardNo(), list);
			}
		}
		List<List<DuplicatePeople>> result = new ArrayList<List<DuplicatePeople>>();
		for (Map.Entry<String, List<DuplicatePeople>> entry : map.entrySet()) {
			result.add(entry.getValue());
		}

		return result;
	}

	/**
	 * 根据传入的所有重复的数据返回分组后的重复数据的集合（本来是所有的重复数据在一个list里面的，
	 * 经过处理后就是同basinfoId的数据在同一个里面）
	 * 
	 * @param allBaseInfodDuplicatePeoples
	 *            （就是将其他的（户籍、流动、业务）表的所有重复数据取出来放在一个list里面）
	 * @return
	 */
	public static List<List<DuplicatePeople>> getAllActualPopulationDuplicatePeople(
			List<DuplicatePeople> allActualPopulationDuplicatePeoples) {
		if (allActualPopulationDuplicatePeoples == null
				|| allActualPopulationDuplicatePeoples.size() < 0) {
			return null;
		}
		Map<String, List<DuplicatePeople>> map = new HashMap<String, List<DuplicatePeople>>();
		for (DuplicatePeople duplicatePeople : allActualPopulationDuplicatePeoples) {
			if (duplicatePeople.getBaseInfoId() != null
					&& duplicatePeople.getOrganization() != null
					&& duplicatePeople.getOrganization().getId() != null
					&& map.containsKey(duplicatePeople.getBaseInfoId()
							+ "_allActualPopulation_"
							+ duplicatePeople.getOrganization().getId())) {// 存在，并且baseinfoid已经放在map里面了（即存在对应的list了）
				map.get(duplicatePeople.getBaseInfoId()
						+ "_allActualPopulation_"
						+ duplicatePeople.getOrganization().getId()).add(
						duplicatePeople);
			} else if (duplicatePeople.getBaseInfoId() != null
					&& duplicatePeople.getOrganization() != null
					&& duplicatePeople.getOrganization().getId() != null
					&& !map.containsKey(duplicatePeople.getBaseInfoId()
							+ "_allActualPopulation_"
							+ duplicatePeople.getOrganization().getId())) {// baseinfoid没有存在map中（新建list然后放入map中）
				List<DuplicatePeople> list = new ArrayList<DuplicatePeople>();
				list.add(duplicatePeople);
				map.put(duplicatePeople.getBaseInfoId()
						+ "_allActualPopulation_"
						+ duplicatePeople.getOrganization().getId(), list);
			}
		}

		List<List<DuplicatePeople>> result = new ArrayList<List<DuplicatePeople>>();
		for (Map.Entry<String, List<DuplicatePeople>> entry : map.entrySet()) {
			result.add(entry.getValue());
		}

		return result;
	}
}
