package com.tianque.mobile.sysadmin;

public interface UserMobileAdapter {
	/**
	 * 获取登录人员的信息
	 */
	public String getCurrentLoginUser() throws Exception;

	/**
	 * 
	 * @Title: updateUserById
	 * @Description: TODO手机端 修改用户信息
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-8-6 下午03:33:54
	 */
	public String updateUserById() throws Exception;
}
