package com.tianque.mobile.baseInfo;

public interface HouseholdStaffMobileAdapter {
	/**
	 * 获取所有常住人口的信息
	 */
	public String findHouseholdStaffList() throws Exception;

	/**
	 * 
	 * @Title: addHouseholdStaffForMobile
	 * @Description: TODO将代理中的人房信息拆分，放在社管中处理 增加户籍人口
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-9-17 下午02:51:53
	 */
	public String addHouseholdStaffForMobile() throws Exception;

	/**
	 * 
	 * @Title: updateHouseholdStaffForMobile
	 * @Description: TODO将代理中的人房信息拆分，放在社管中处理 修改户籍人口
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-9-17 下午02:52:58
	 */
	public String updateHouseholdStaffForMobile() throws Exception;
}
