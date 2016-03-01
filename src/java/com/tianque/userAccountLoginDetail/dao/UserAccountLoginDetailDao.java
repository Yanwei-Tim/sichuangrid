package com.tianque.userAccountLoginDetail.dao;

import java.util.Date;
import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.UserSign;
import com.tianque.userAccountLoginDetail.domain.UserAccountLoginDetail;
import com.tianque.userAccountLoginDetail.vo.UserAccountLoginStatisticsVo;
import com.tianque.userAccountLoginDetail.vo.UserAccountSearchVo;

public interface UserAccountLoginDetailDao {

	/***
	 * 生成周数据job
	 */
	public void createUserAccountLoginDetailForWeek(Date startDate,Date endDate);
	/***
	 * 生成月数据job
	 */
	public void createUserAccountLoginDetailForMonth(Date startDate,Date endDate);
	/***
	 * 列表数据查询
	 */
	public PageInfo<UserAccountLoginDetail> findUserAccountLoginDetailForPageResult(Organization organization,Integer searchType,
			boolean isFunOrgType,List<Long> funOrgIdList,Integer page,Integer rows, String sidx, String sord);
	
	/***
	 * 列表数据高级搜索
	 */
	public PageInfo<UserAccountLoginDetail> searchUserAccountForPageResult(UserAccountSearchVo userAccountSearchVo,
			Integer page,Integer rows, String sidx, String sord);
	
	/***
	 * 根据账号和时间段查询获得该账号的登录情况
	 */
	
	public List<UserSign> getUserSignByUserIdAndDate(Long userId,Date startDate,Date endDate);
	
	/***
	 * 历史数据清空 周
	 */
	public void deleteUserAccountLoginDeatilWeek();
	
	/***
	 * 历史数据清空 月
	 */
	public void deleteUserAccountLoginDeatilMonth();
	
	/***
	 * 查看登录详情报表
	 */
	public List<UserAccountLoginStatisticsVo> getAccountLoginDetailStatistics(Long orgId,Long orgType,Long funcType);
}