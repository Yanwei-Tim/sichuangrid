package com.tianque.dao;

import java.util.List;

import com.tianque.domain.SocietySafeCheck;

/**
 * 社会治安重点地区排查整治工作情况
 */

public interface SocietySafeCheckDao {

	// 添加一个对象
	void addSocietySafeCheck(SocietySafeCheck societySafeCheck);

	// 删除一个对象通过id
	void deleteSocietySafeCheckById(Long id);

	// 更新一个对象
	void updateSocietySafeCheck(SocietySafeCheck societySafeCheck);

	// 查找一个对象通过id
	SocietySafeCheck getSocietySafeCheckById(Long id);

	// 报表回退
	void backReport(Long id);

	// 查找一个对象通过signCode
	SocietySafeCheck getSocietySafeCheckBySignCode(String signCode);

	// 查找一个对象通过与其对应的台账id
	SocietySafeCheck getSocietySafeCheckByDailyLogId(Long dailyLogId);

	// 查找所有对象
	List<SocietySafeCheck> findAllSocietySafeCheck();

	// 查找组织机构下属的数据
	List<SocietySafeCheck> findCollectionData(Integer reportState,
			String reportYear, Integer reportMonth, String orgCode,
			Integer reportType);

	// 查找季报的数据
	List<SocietySafeCheck> findQuarterData(Integer reportState,
			String reportYear, Integer quarter, String orgCode);

	// 方法没有显式调用
	// 查询下辖单位上报情况
	// public PageInfo<IssueSubmitInfoVo> findIssueSubmitInfo(Integer page,
	// Integer rows, Long orgId,
	// String reportYear, String directoryNameSign);
}
