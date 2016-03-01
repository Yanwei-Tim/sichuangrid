package com.tianque.mobile.companyPlace;

/**
 * 
 * @ClassName: CompanyPlaceMobileAdapter
 * @Description: TODO单位场所改造，手机端接口
 * @author wanggz
 * @date 2014-6-16 下午02:30:34
 */
public interface CompanyPlaceMobileAdapter {

	/**
	 * 
	 * @Title: findCompanyPlaceForMobile
	 * @Description: TODO加载列表数据
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-16 下午02:49:11
	 */
	public String findCompanyPlaceListForMobile() throws Exception;

	/**
	 * 
	 * @Title: addCompanyPlaceForMobile
	 * @Description: TODO新增单位场所 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-17 上午10:33:22
	 */
	public String addCompanyPlaceForMobile() throws Exception;

	/**
	 * 
	 * @Title: updateCompanyPlaceByIdForMobile
	 * @Description: TODO修改单位场所
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-17 下午02:41:54
	 */
	public String updateCompanyPlaceByIdForMobile() throws Exception;

	/**
	 * 
	 * @Title: deleteCompanyPlaceByIdForMobile
	 * @Description: TODO通过id删除companyplace 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-17 下午04:03:44
	 */
	public String deleteCompanyPlaceByIdForMobile() throws Exception;

	/**
	 * 
	 * @Title: findCompanyPlaceByIdForMobile
	 * @Description: TODO通过id查看单位场所信息 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-19 上午10:02:24
	 */
	public String findCompanyPlaceByIdForMobile() throws Exception;

	/**
	 * 
	 * @Title: findCompanyPlaceServiceMembersListForMobile
	 * @Description: TODO查询单位场所治安负责人列表 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-19 下午01:55:36
	 */
	public String findCompanyPlaceServiceMembersListForMobile()
			throws Exception;

	/**
	 * 
	 * @Title: findServiceMemberFromTeamsForMobile
	 * @Description: TODO查询治安负责人 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-19 下午04:59:51
	 */
	public String findServiceMemberFromTeamsForMobile() throws Exception;

	/**
	 * 
	 * @Title: addObjectAndMemberRelationForMobile
	 * @Description: TODO添加治安负责人 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-19 下午10:51:49
	 */
	public String addObjectAndMemberRelationForMobile() throws Exception;

	/**
	 * 
	 * @Title: leaveOrBackOnDutyForMobile
	 * @Description: TODO卸任治安负责人 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-20 上午12:53:09
	 */
	public String leaveOrBackOnDutyForMobile() throws Exception;

	/**
	 * 
	 * @Title: findServiceRecordsForMobile
	 * @Description: TODO查询巡查情况 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-20 上午01:46:11
	 */
	public String findServiceRecordsForMobile() throws Exception;

	/**
	 * 
	 * @Title: addServiceRecordForMobile
	 * @Description: TODO新增巡场情况 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-21 下午01:17:07
	 */
	public String addServiceRecordForMobile() throws Exception;

	/**
	 * 
	 * @Title: updateServiceRecordForMobile
	 * @Description: TODO修改巡场情况 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-21 下午02:32:47
	 */
	public String updateServiceRecordForMobile() throws Exception;

	/**
	 * 
	 * @Title: deleteServiceRecordsForMobile
	 * @Description: TODO删除巡场情况 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-21 下午03:05:29
	 */
	public String deleteServiceRecordsForMobile() throws Exception;

	/**
	 * 
	 * @Title: findServiceRecordByIdForMobile
	 * @Description: TODO通过id查找巡场情况数据 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-21 下午03:22:14
	 */
	public String findServiceRecordByIdForMobile() throws Exception;

	/**
	 * 根据ID和TYPE查询对应的keyplace信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getKeyPlaceByIdAndTypeForMobile() throws Exception;

}
