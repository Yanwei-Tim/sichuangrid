package com.tianque.resourcePool.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.resourcePool.domain.UserHasMyProfilePermission;

@Repository("userHasMyProfilePermissionDao")
public class UserHasMyProfilePermissionDaoImpl extends AbstractBaseDao
		implements UserHasMyProfilePermissionDao {

	@Override
	public Long addUserMyProfilePermission(UserHasMyProfilePermission domain) {
		return (Long) getSqlMapClientTemplate().insert(
				"userHasMyProfilePermission.addUserHasMyProfilePermission",
				domain);
	}

	@Override
	public void deleteMyProfilePermissionByMyProfileId(Long myProfileId) {
		getSqlMapClientTemplate()
				.delete("userHasMyProfilePermission.deleteMyProfilePermissionByMyProfileId",
						myProfileId);
	}

	@Override
	public void deleteMyProfilePermissionByMyProfileIds(String[] myProfileIds) {
		if (myProfileIds == null || myProfileIds.length < 0) {
			throw new BusinessValidationException("参数错误");
		}
		getSqlMapClientTemplate()
				.delete("userHasMyProfilePermission.deleteMyProfilePermissionByMyProfileIds",
						myProfileIds);
	}

	@Override
	public void batchAddUserMyProfilePermission(
			final List<UserHasMyProfilePermission> domains) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (UserHasMyProfilePermission up : domains) {
					executor.insert(
							"userHasMyProfilePermission.addUserHasMyProfilePermission",
							up);
				}
				executor.executeBatch();
				return null;
			}
		});

	}

}
