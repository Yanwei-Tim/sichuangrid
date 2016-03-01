package com.tianque.mobile.sysadmin.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.User;
import com.tianque.exception.base.OperationFailedException;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.sysadmin.UserMobileAdapter;
import com.tianque.mobile.sysadmin.vo.UserInfo;
import com.tianque.sysadmin.service.PermissionService;

/**
 * 手机端 ： 获得当前登录的用户信息
 */
@Transactional
@Scope("request")
@Controller("userMobileAdapter")
public class UserMobileAdapterImpl extends BaseMobileAction implements
		UserMobileAdapter {
	@Autowired
	private PermissionService permissionService;

	private UserInfo userInfo;

	private User user;

	@Autowired
	protected ValidateHelper validateHelper;

	public String getCurrentLoginUser() throws Exception {
		Long userId = ThreadVariable.getSession().getUserId();
		user = permissionService.getSimpleUserById(userId);
		userInfo = new UserInfo();
		copyPropertiesWithRecursion(User.class, userInfo, user);
		userInfo.setProject_area("SiChuan");
		userInfo.setUpdate_mode("AreaUpdate");
		return SUCCESS;
	}

	/**
	 * 手机端 修改用户信息
	 */
	@Override
	public String updateUserById() throws Exception {
		// 验证必填参数是否传入 同时验证参数合法性
		if (user == null
				|| user.getId() == null
				|| validateHelper.emptyString(user.getName())
				|| validateHelper.emptyString(user.getMobile())
				|| validateHelper.emptyString(user.getWorkPhone())
				|| !validateHelper.isConSpeCharacters(user.getName())
				|| validateHelper.illegalStringLength(2, 15, user.getName())
				|| validateHelper.illegalExculdeParticalChar(user.getName())
				|| validateHelper.illegalMobilePhone(user.getMobile())
				|| validateHelper.illegalStringLength(0, 20, user
						.getWorkPhone())
				|| validateHelper.illegalTelephone(user.getWorkPhone())) {
			errorMessage = "请确认必填参数是否正确完善！";
			return ERROR;
		}
		User oldUser = permissionService.getSimpleUserById(user.getId());
		if (oldUser != null) {
			oldUser.setName(user.getName());
			oldUser.setMobile(user.getMobile());
			oldUser.setWorkPhone(user.getWorkPhone());
			if (user.getHomePhone() != null) {
				oldUser.setHomePhone(user.getHomePhone());
			}
			if (user.getWorkCompany() != null) {
				oldUser.setWorkCompany(user.getWorkCompany());
			}
			user = permissionService.updateUserForMobile(oldUser);
		} else {
			errorMessage = "未找到要修改的用户！";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 属性
	 * 
	 * @param cls
	 * @param target
	 * @param source
	 */
	private void copyPropertiesWithRecursion(Class cls, Object target,
			Object source) {
		copyPropertiesWithOutRecursion(cls, target, source);
		Class superCls = cls.getSuperclass();

		if (superCls != null && !superCls.equals(Object.class)) {
			copyPropertiesWithRecursion(superCls, target, source);
		}

	}

	private void copyPropertiesWithOutRecursion(Class cls, Object target,
			Object source) {
		try {
			Field[] fields = cls.getDeclaredFields();
			for (Field field : fields) {
				int mod = field.getModifiers();
				if ((mod & Modifier.FINAL) != 0) {
					continue;
				}
				field.setAccessible(true);
				field.set(target, field.get(source));
			}
		} catch (Exception e) {
			throw new OperationFailedException("属性复制异常", e);
		}
	}

	/* get set 方法 */
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
