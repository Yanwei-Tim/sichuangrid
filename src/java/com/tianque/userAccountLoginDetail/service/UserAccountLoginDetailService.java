package com.tianque.userAccountLoginDetail.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.UserSign;
import com.tianque.userAccountLoginDetail.domain.UserAccountLoginDetail;
import com.tianque.userAccountLoginDetail.vo.UserAccountLoginStatisticsVo;
import com.tianque.userAccountLoginDetail.vo.UserAccountSearchVo;

public interface UserAccountLoginDetailService {
	/***
	 * 生成周数据job
	 */
	public void createUserAccountLoginDetailForWeek(int year,int month);
	/***
	 * 生成月数据job
	 */
	public void createUserAccountLoginDetailForMonth(int year,int month);
	/***
	 * 列表数据查询
	 */
	public PageInfo<UserAccountLoginDetail> findUserAccountLoginDetailForPageResult(Organization organization,Integer searchType,
			Integer page,Integer rows, String sidx, String sord);
	
	/***
	 * 列表数据高级搜索
	 */
	public PageInfo<UserAccountLoginDetail> searchUserAccountForPageResult(UserAccountSearchVo userAccountSearchVo,
			Integer page,Integer rows, String sidx, String sord);
	/***
	 * 根据账号和时间段查询获得该账号的登录情况
	 */
	
	public List<UserSign> getUserSignByUserIdAndDate(Long userId,Integer searchType);
	
	/***
	 * 手动生成报表
	 */
	public void createUserAccountLoginDetail();
	
	/***
	 * 查看登录详情报表
	 */
	public List<UserAccountLoginStatisticsVo> getAccountLoginDetailStatistics(Long orgId);
	
}
