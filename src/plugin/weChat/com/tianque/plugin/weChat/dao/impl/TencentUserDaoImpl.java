package com.tianque.plugin.weChat.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Session;
import com.tianque.plugin.weChat.dao.TencentUserDao;
import com.tianque.plugin.weChat.domain.user.TencentUser;

@Repository("tencentUserDao")
public class TencentUserDaoImpl extends AbstractBaseDao implements TencentUserDao {

	@Override
	public PageInfo getTencentUserList(Map<String, Object> parameterMap, Integer pageNum,
			Integer pageSize) {
		return getPageInfoByParamMap(new PageInfo<TencentUser>(),
				"tencentUser.countTencentUserList", "tencentUser.getTencentUserList", pageNum,
				pageSize, parameterMap);
	}

	@Override
	public Long addTencentUser(TencentUser tencentUser) {
		Session session = ThreadVariable.getSession();
		Long tencentUserId = tencentUser.getTencentUserId();
		if (tencentUserId == null) {
			tencentUser.setCreateDept(session.getOrganization().getId());
			tencentUser.setCreateDate(session.getAccessTime());
			tencentUser.setCreateUser(session.getUserName());
			tencentUserId = (Long) getSqlMapClientTemplate().insert("tencentUser.addTencentUser",
					tencentUser);
		} else {
			tencentUser.setUpdateDept(session.getOrganization().getId());
			tencentUser.setUpdateDate(session.getAccessTime());
			tencentUser.setUpdateUser(session.getUserName());
			getSqlMapClientTemplate().update("tencentUser.updateTencentUserByBase", tencentUser);
		}
		return tencentUserId;
	}
/**
 * 微信号绑定自动回复消息（素材）
 * @param tencentUser
 * @return
 */
	@Override
	public Integer updateTencentUser(TencentUser tencentUser) {
		Session session = ThreadVariable.getSession();
		tencentUser.setUpdateDept(session.getOrganization().getId());
		Integer flag = getSqlMapClientTemplate().update("tencentUser.updateTencentUser",
				tencentUser);
		return flag;
	}

	@Override
	public TencentUser getTencentUserByTencentUserId(Long tencentUserId) {
		TencentUser tencentUser = (TencentUser) getSqlMapClientTemplate().queryForObject(
				"tencentUser.getTencentUserByTencentUserId", tencentUserId);
		return tencentUser;
	}

	@Override
	public void deleteTencentUserById(Long id) {
		getSqlMapClientTemplate().delete("tencentUser.deleteTencentUserById", id);
	}

	@Override
	public TencentUser getTencentUserByWeChatUserId(String weChatUserId) {
		return (TencentUser) getSqlMapClientTemplate().queryForObject(
				"tencentUser.getTencentUserByWeChatUserId", weChatUserId);
	}
	/**
	 * 根据素材id加载Tencent集合（条件Like）
	 * @param sourceId
	 * @return
	 */
	@Override
	public List<TencentUser> getTencentUserBySourceId(String sourceId) {
		return (List<TencentUser>) getSqlMapClientTemplate().queryForList(
				"tencentUser.getTencentUserBySourceId", sourceId);
	}
	
	

	@Override
	public List<TencentUser> getTencentUserListByOrgId(Long orgId) {
		return getSqlMapClientTemplate().queryForList("tencentUser.getTencentUserListByOrgId",
				orgId);
	}

	@Override
	public List<TencentUser> getTencentUserListByOrgCode(String orgInternalCode) {
		return  getSqlMapClientTemplate().queryForList("tencentUser.getTencentUserListByOrgCode",
				orgInternalCode);
	}

}
