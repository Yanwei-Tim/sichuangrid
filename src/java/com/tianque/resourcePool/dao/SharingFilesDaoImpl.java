package com.tianque.resourcePool.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.DateUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.resourcePool.domain.MyProfile;
import com.tianque.util.ParametersConvertUtil;

@Repository("sharingFilesDao")
public class SharingFilesDaoImpl extends AbstractBaseDao implements
		SharingFilesDao {

	@Override
	public PageInfo<MyProfile> findSharingFilesForList(Long orgId,
			String orgInternalCode, Long resourcePoolTypeId, int searchType,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (orgId == null || !StringUtil.isStringAvaliable(orgInternalCode)) {
			throw new BusinessValidationException(
					"SharingFilesDaoImpl.findSharingFilesForList 参数错误！");
		}
		if (orgId != null) {
			map.put("orgId", orgId);
		}
		if (StringUtil.isStringAvaliable(orgInternalCode)) {
			map.put("orgInternalCode", orgInternalCode);
		}
		if (resourcePoolTypeId != null
				&& !"0".equals(resourcePoolTypeId.toString())) {
			map.put("resourcePoolTypeId", resourcePoolTypeId);
		}
		map.put("searchType", searchType);
		map.put("userId", ThreadVariable.getUser().getId());

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"userHasMyProfilePermission.countSharingFilesForList", map);

		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		if (StringUtil.isStringAvaliable(sord)) {
			map.put("order", sord);
		}
		List<MyProfile> myProfiles = getSqlMapClientTemplate().queryForList(
				"userHasMyProfilePermission.findSharingFilesForList", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, myProfiles);
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
	public PageInfo<MyProfile> fastSearchSharingFiles(Long resourcePoolTypeId,
			int searchType, String searchText, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (resourcePoolTypeId != null
				&& !"0".equals(resourcePoolTypeId.toString())) {
			map.put("resourcePoolTypeId", resourcePoolTypeId);
		}
		map.put("searchType", searchType);
		map.put("searchText", searchText);
		map.put("orgId", ThreadVariable.getUser().getOrganization().getId());
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"userHasMyProfilePermission.countFastSearchSharingFiles", map);
		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		List<MyProfile> list = getSqlMapClientTemplate().queryForList(
				"userHasMyProfilePermission.fastSearchSharingFiles", map,
				(pageNum - 1) * pageSize, pageSize);

		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	public PageInfo<MyProfile> fastSearchSharingFilesforMobile(
			Long resourcePoolTypeId, int searchType, String searchText,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (resourcePoolTypeId != null
				&& !"0".equals(resourcePoolTypeId.toString())) {
			map.put("resourcePoolTypeId", resourcePoolTypeId);
		}
		map.put("searchType", searchType);
		map.put("searchText", searchText);
		map.put("orgId", ThreadVariable.getUser().getOrganization().getId());
		Integer countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"userHasMyProfilePermission.countFastSearchSharingFilesforMobile",
						map);
		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		List<MyProfile> list = getSqlMapClientTemplate().queryForList(
				"userHasMyProfilePermission.fastSearchSharingFilesforMobile",
				map, (pageNum - 1) * pageSize, pageSize);

		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public PageInfo<MyProfile> searchSharingFiles(MyProfile myProfile,
			int searchType, Date startReleaseTime, Long resourcePoolTypeId,
			Date startCreateTime, Date startShareDate, List<Long> orgIdsList,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (resourcePoolTypeId != null
				&& !"0".equals(resourcePoolTypeId.toString())) {
			map.put("resourcePoolTypeId", resourcePoolTypeId);
		}
		map.put("orgId", ThreadVariable.getUser().getOrganization().getId());
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
		map.put("startShareDate", startShareDate);
		if (myProfile.getShareDate() != null) {
			map.put("shareDate", DateUtil.getEndTime(myProfile.getShareDate()));
		}
		if (myProfile.getOrganization().getId() != null) {
			map.put("shareOrganizationId", myProfile.getOrganization().getId());
		}
		if (orgIdsList != null) {
			map.put("orgIdsList",
					ParametersConvertUtil.convertToListString(orgIdsList));
		}
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"userHasMyProfilePermission.countSearchSharingFiles", map);
		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		List<MyProfile> list = getSqlMapClientTemplate().queryForList(
				"userHasMyProfilePermission.searchSharingFiles", map,
				(pageNum - 1) * pageSize, pageSize);

		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public PageInfo<MyProfile> findSharingFilesForListByIds(String[] ids) {
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("ids", ids);
		List<MyProfile> list = getSqlMapClientTemplate().queryForList(
				"userHasMyProfilePermission.findSharingFilesForListByIds", ids);
		PageInfo<MyProfile> pageInfo = new PageInfo<MyProfile>();
		pageInfo.setResult(list);
		return pageInfo;
	}
}
