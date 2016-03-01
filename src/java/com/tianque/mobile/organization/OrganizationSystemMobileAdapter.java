package com.tianque.mobile.organization;

/**
 * 
 * @ClassName: OrganizationMobileAdapter
 * @Description: TODO手机端 组织机构系统模块适配器接口
 * @author wanggz
 * @date 2014-9-29 上午09:53:14
 */
public interface OrganizationSystemMobileAdapter {

	/**
	 * 
	 * @Title: findNewEconomicOrganizationsForMobile
	 * @Description: TODO非公有制经济组织 列表方法
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-9-29 上午09:57:44
	 */
	public String findNewEconomicOrganizationsForMobile() throws Exception;

	/**
	 * 
	 * @Title: findNewEconomicOrganizationsById
	 * @Description: TODO根据id查找非公有制经济组织
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-10-10 上午09:35:56
	 */
	public String findNewEconomicOrganizationsById() throws Exception;

	/**
	 * 
	 * @Title: addNewEconomicOrganizations
	 * @Description: TODO新增非公有制经济组织
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-10-10 上午10:50:10
	 */
	public String addNewEconomicOrganizationsForMobile() throws Exception;

	/**
	 * 
	 * @Title: updateNewEconomicOrganizationsForMobileById
	 * @Description: TODO修改非公有制经济组织
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-10-10 上午11:24:54
	 */
	public String updateNewEconomicOrganizationsForMobileById()
			throws Exception;

	/**
	 * 
	 * @Title: searchNewEconomicOrganizationsForMobile
	 * @Description: TODO查询非公有制经济组织
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-10-10 下午05:17:06
	 */
	public String searchNewEconomicOrganizationsForMobile() throws Exception;

	/**
	 * 
	 * @Title: hasDuplicateNewSocietyOrganizationsName
	 * @Description: TODO查询组织名称是否重复
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-10-15 上午01:42:16
	 */
	public String hasDuplicateNewSocietyOrganizationsName() throws Exception;

	/**
	 * 
	 * @Title: hasDuplicateNewEconomicOrganizations
	 * @Description: TODO查询非公有制经济组织名称是否重复
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-10-15 下午02:29:17
	 */
	public String hasDuplicateNewEconomicOrganizationsName() throws Exception;

	/**
	 * 
	 * @Title: hasDuplicateNewEconomicOrganizationsLicenseNumber
	 * @Description: TODO查询非公有制经济组织营业执照号是否重复
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-10-15 下午02:46:03
	 */
	public String hasDuplicateNewEconomicOrganizationsLicenseNumber()
			throws Exception;

}
