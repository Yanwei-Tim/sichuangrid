package com.tianque.web.api.cms.callcenter;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.domain.User;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.web.api.base.BaseWebApi;

/**
 * @ClassName: UserWebService
 * @Description: 用户接口
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2014年10月22日 下午4:10:41
 */
@Namespace("/webApi/user")
public class UserWebService extends BaseWebApi
{
	Logger logger = LoggerFactory.getLogger(UserWebService.class);
	@Autowired
	private PermissionService permissionService;
	private Long userId;
	private String mobile;
	
	/**
	 * @Description: cms 根据社管用户id获取用户手机号码
	 * @author wangxiaohu wsmalltiger@163.com
	 * @return
	 * @throws
	 */
	@Action(value = "getSheguanUserMobileById", results = { @Result(name = "success", type = "json", params = { "root", "mobile" }) })
	public String getSheguanUserMobileById()
	{
		try
		{
			User user = permissionService.getFullUserById(userId);
			mobile = user.getMobile();
		}
		catch (Exception e)
		{
			logger.error("cms 获取社管用户手机号码出错：", e.getMessage());
		}
		return SUCCESS;
	}
	
	public Long getUserId()
	{
		return userId;
	}
	
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
	
	public String getMobile()
	{
		return mobile;
	}
	
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
}
