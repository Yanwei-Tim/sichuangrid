package com.tianque.resourcePool.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.DateUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.resourcePool.domain.MyProfile;

@Repository("myProfileDao")
public class MyProfileDaoImpl extends AbstractBaseDao implements MyProfileDao {

	@Override
	public MyProfile addMyProfile(MyProfile myProfile) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"myProfile.addMyProfile", myProfile);
		return getMyProfileById(id);
	}

	@Override
	public MyProfile getMyProfileById(Long id) {
		return (MyProfile) getSqlMapClientTemplate().queryForObject(
				"myProfile.getMyProfileById", id);
	}

	@Override
	public PageInfo<MyProfile> findMyProfileForList(Long resourcePoolTypeId,
			int searchType, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (resourcePoolTypeId != null) {
			map.put("resourcePoolTypeId", resourcePoolTypeId);
			if (resourcePoolTypeId.intValue() == 0) {
				map.put("orgId", ThreadVariable.getUser().getOrganization()
						.getId());
				map.put("userId", ThreadVariable.getUser().getId());
			} else if (resourcePoolTypeId.intValue() < 999) {
				map.put("orgId", ThreadVariable.getUser().getOrganization()
						.getId());
			} else {
				map.put("userId", ThreadVariable.getUser().getId());
			}
		}

		map.put("searchType", searchType);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"myProfile.countMyProfileForList", map);

		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		if (StringUtil.isStringAvaliable(sord)) {
			map.put("order", sord);
		}

		List<MyProfile> list = getSqlMapClientTemplate().queryForList(
				"myProfile.findMyProfileForList", map,
				(pageNum - 1) * pageSize, pageSize);

		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	private PageInfo<MyProfile> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List<MyProfile> list) {
		PageInfo<MyProfile> pageInfo = new PageInfo<MyProfile>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public void updateMyprofileBySendMesageAndShareDirectoryId(
			MyProfile myProfile) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", myProfile.getId());
		map.put("sendMessage", myProfile.getSendMessage());
		map.put("shareState", myProfile.getShareState());
		map.put("shareDirectoryId", myProfile.getShareDirectory().getId());
		map.put("shareDate", new Date());
		if (myProfile.isUnderJurisdiction()) {
			map.put("underJurisdiction", true);
		} else {
			map.put("underJurisdiction", false);
		}
		getSqlMapClientTemplate()
				.update("myProfile.updateMyprofileBySendMesageAndShareDirectoryId",
						map);

	}

	@Override
	public MyProfile updateMyprofile(MyProfile myProfile) {
		getSqlMapClientTemplate()
				.update("myProfile.updateMyProfile", myProfile);
		return getMyProfileById(myProfile.getId());
	}

	@Override
	public void deleteMyProfileById(Long id) {
		getSqlMapClientTemplate().delete("myProfile.deleteMyProfileById", id);
	}

	@Override
	public void deleteMyProfileByIds(String[] ids) {
		if (ids == null || ids.length < 0) {
			throw new BusinessValidationException("参数错误");
		}
		getSqlMapClientTemplate().delete("myProfile.deleteMyProfileByIds", ids);
	}

	@Override
	public PageInfo<MyProfile> searchMyProfile(MyProfile myProfile,
			int searchType, Date startReleaseTime, Long resourcePoolTypeId,
			Date startCreateTime, Long attached, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		/*
		 * if (resourcePoolTypeId != null &&
		 * !"0".equals(resourcePoolTypeId.toString())) {
		 * map.put("resourcePoolTypeId", resourcePoolTypeId); }
		 */
		if (resourcePoolTypeId != null) {
			map.put("resourcePoolTypeId", resourcePoolTypeId);
			if (resourcePoolTypeId.intValue() == 0) {
				map.put("orgId", ThreadVariable.getUser().getOrganization()
						.getId());
				map.put("userId", ThreadVariable.getUser().getId());
			} else if (resourcePoolTypeId.intValue() < 999) {
				map.put("orgId", ThreadVariable.getUser().getOrganization()
						.getId());
			} else {
				map.put("userId", ThreadVariable.getUser().getId());
			}
		}

		if (attached != null && (attached == 1l || attached == 0L)) { // 1表示有附件，0表示没有附件
			map.put("attached", attached);
		}
		// map.put("userId", ThreadVariable.getUser().getId());
		map.put("searchType", searchType);
		map.put("name", myProfile.getName());
		if (myProfile.getReleaseTime() != null) {
			map.put("releaseTime",
					DateUtil.getEndTime(myProfile.getReleaseTime()));
		}
		map.put("startReleaseTime", startReleaseTime);
		map.put("releaseUnit", myProfile.getReleaseUnit());
		map.put("content", myProfile.getContent());
		map.put("fileNo", myProfile.getFileNo());
		map.put("documentSubject", myProfile.getDocumentSubject());
		map.put("startCreateTime", startCreateTime);
		if (myProfile.getCreateDate() != null) {
			map.put("createDate",
					DateUtil.getEndTime(myProfile.getCreateDate()));
		}
		map.put("source", myProfile.getSource());
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"myProfile.countSearchMyProfiles", map);
		if (!isStrotFieldAvaliable(sidx)) {
			sidx = "id";
		}
		map.put("sortField", sidx);
		if (!isStrotFieldAvaliable(sord)) {
			sord = "desc";
		}
		map.put("order", sord);
		List<MyProfile> list = getSqlMapClientTemplate().queryForList(
				"myProfile.searchMyProfiles", map, (pageNum - 1) * pageSize,
				pageSize);

		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public PageInfo<MyProfile> fastSearchMyProfile(Long userId,
			Long resourcePoolTypeId, int searchType, String searchText,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		/*
		 * if (resourcePoolTypeId != null &&
		 * !"0".equals(resourcePoolTypeId.toString())) {
		 * map.put("resourcePoolTypeId", resourcePoolTypeId); }
		 */
		if (resourcePoolTypeId != null) {
			map.put("resourcePoolTypeId", resourcePoolTypeId);
			if (resourcePoolTypeId.intValue() == 0) {
				map.put("orgId", ThreadVariable.getUser().getOrganization()
						.getId());
				map.put("userId", ThreadVariable.getUser().getId());
			} else if (resourcePoolTypeId.intValue() < 999) {
				map.put("orgId", ThreadVariable.getUser().getOrganization()
						.getId());
			} else {
				map.put("userId", ThreadVariable.getUser().getId());
			}
		}
		map.put("searchType", searchType);
		map.put("searchText", searchText);
		// map.put("userId", userId);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"myProfile.countFastSearchMyProfile", map);
		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		List<MyProfile> list = getSqlMapClientTemplate().queryForList(
				"myProfile.fastSearchMyProfile", map, (pageNum - 1) * pageSize,
				pageSize);

		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	// 取消共享
	@Override
	public void updateMyProfileOfshareState(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("shareState", MyProfile.shareState_NO);
		map.put("underJurisdiction", false);// 改变所有人可看的标识
		getSqlMapClientTemplate().update(
				"myProfile.updateMyProfileOfshareState", map);

	}

	@Override
	public List<MyProfile> selectMyProfilesByIds(List<String> array) {
		return getSqlMapClientTemplate().queryForList(
				"myProfile.selectMyProfilesByIds", array);
	}

	@Override
	public void batchUpateMyProfileBySendMessageAndShareDirectoryId(
			final List<MyProfile> files) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (MyProfile mp : files) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", mp.getId());
					map.put("sendMessage", mp.getSendMessage());
					map.put("shareState", mp.getShareState());
					map.put("shareDirectoryId", mp.getShareDirectory().getId());
					map.put("shareDate", new Date());
					if (mp.isUnderJurisdiction()) {
						map.put("underJurisdiction", true);
					} else {
						map.put("underJurisdiction", false);
					}
					executor.update(
							"myProfile.updateMyprofileBySendMesageAndShareDirectoryId",
							map);
				}
				executor.executeBatch();
				return null;
			}
		});

	}

}
