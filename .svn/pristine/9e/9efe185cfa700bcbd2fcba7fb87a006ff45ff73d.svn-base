package com.tianque.partyBuilding.organizations.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.partyBuilding.organizations.dao.RegionalBuildInfoDao;
import com.tianque.partyBuilding.organizations.domain.ClaimRegionalBuildInfo;
import com.tianque.partyBuilding.organizations.domain.RegionalBuildInfo;
import com.tianque.partyBuilding.organizations.domain.RegionalBuildInfoAttachFile;
import com.tianque.partyBuilding.organizations.domain.vo.RegionalBuildInfoVo;
import com.tianque.util.ParametersConvertUtil;

/**
 * 区域联建情况DAO实现
 * */
@Repository("regionalBuildInfoDao")
public class RegionalBuildInfoDaoImpl extends AbstractBaseDao implements
		RegionalBuildInfoDao {

	@Override
	public List<RegionalBuildInfoAttachFile> findRegionalBuildInfoAttachFilesByRegionalBuildInfoId(
			Long regionalBuildInfoId) {
		List<RegionalBuildInfoAttachFile> list = getSqlMapClientTemplate()
				.queryForList(
						"regionalBuildInfo.getRegionalBuildInfoAttachFileByRegionalBuildInfoId",
						regionalBuildInfoId);
		return list;
	}

	@Override
	public PageInfo<RegionalBuildInfo> findRegionalBuildInfoPagerBySearchVo(
			RegionalBuildInfoVo regionalBuildInfoVo, Integer pageNum,
			Integer pageSize, String sortField, String sord) {

		PageInfo<RegionalBuildInfo> pageInfo = new PageInfo<RegionalBuildInfo>();
		Integer countNum = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("regionalBuildInfo.countRegionalBuildInfo",
						regionalBuildInfoVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<RegionalBuildInfo> list = this.getSqlMapClientTemplate()
					.queryForList(
							"regionalBuildInfo.getRegionalBuildInfoBySearchVo",
							regionalBuildInfoVo, (pageNum - 1) * pageSize,
							pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<RegionalBuildInfo>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public RegionalBuildInfo getRegionalBuildInfoById(Long id) {

		return (RegionalBuildInfo) this.getSqlMapClientTemplate()
				.queryForObject("regionalBuildInfo.getRegionalBuildInfoById",
						id);
	}

	@Override
	public RegionalBuildInfoAttachFile getRegionalBuildInfoAttachFilesById(
			Long id) {
		return (RegionalBuildInfoAttachFile) getSqlMapClientTemplate()
				.queryForObject(
						"regionalBuildInfo.getRegionalBuildInfoAttachFileById",
						id);

	}

	@Override
	public void deleteRegionalBuildInfoAttachFilesById(Long id) {
		getSqlMapClientTemplate().delete(
				"regionalBuildInfo.deleteRegionalBuildInfoAttachFileById", id);

	}

	@Override
	public void addRegionalBuildInfoAttachFile(
			RegionalBuildInfoAttachFile regionalBuildInfoAttachFile) {
		getSqlMapClientTemplate().insert(
				"regionalBuildInfo.addRegionalBuildInfoAttachFile",
				regionalBuildInfoAttachFile);

	}

	@Override
	public RegionalBuildInfo addRegionalBuildInfo(
			RegionalBuildInfo regionalBuildInfo) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"regionalBuildInfo.addRegionalBuildInfo", regionalBuildInfo);
		return this.getRegionalBuildInfoById(id);
	}

	@Override
	public RegionalBuildInfo updateRegionalBuildInfo(
			RegionalBuildInfo regionalBuildInfo) {
		getSqlMapClientTemplate().update(
				"regionalBuildInfo.updateRegionalBuildInfo", regionalBuildInfo);

		return this.getRegionalBuildInfoById(regionalBuildInfo.getId());

	}

	@Override
	public void deleteRegionalBuildInfoById(Long id) {
		getSqlMapClientTemplate().delete(
				"regionalBuildInfo.deleteRegionalBuildInfo", id);

	}

	@Override
	public void deleteRegionalBuildInfoAttachFileByRegionalBuildInfoId(
			Long regionalBuildInfoId) {
		getSqlMapClientTemplate()
				.delete("regionalBuildInfo.deleteRegionalBuildInfoAttachFileByRegionalBuildInfoId",
						regionalBuildInfoId);

	}

	@Override
	public void deleteClaimRegionalBuildInfoByRegionalBuildInfoId(
			Long regionalBuildInfoId) {
		getSqlMapClientTemplate()
				.delete("regionalBuildInfo.deletClaimeRegionalBuildInfoByRegionalBuildInfoId",
						regionalBuildInfoId);

	}

	@Override
	public ClaimRegionalBuildInfo addClaimRegionalBuildInfo(
			ClaimRegionalBuildInfo claimRegionalBuildInfo) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"regionalBuildInfo.addClaimRegionalBuildInfo",
				claimRegionalBuildInfo);
		return this.getClaimRegionalBuildInfo(id);
	}

	@Override
	public ClaimRegionalBuildInfo getClaimRegionalBuildInfo(Long id) {

		return (ClaimRegionalBuildInfo) getSqlMapClientTemplate()
				.queryForObject(
						"regionalBuildInfo.getClaimRegionalBuildInfoById", id);
	}

	@Override
	public ClaimRegionalBuildInfo updateClaimRegionalBuildInfo(
			ClaimRegionalBuildInfo claimRegionalBuildInfo) {
		getSqlMapClientTemplate().update(
				"regionalBuildInfo.updateClaimRegionalBuildInfo",
				claimRegionalBuildInfo);
		return this.getClaimRegionalBuildInfo(claimRegionalBuildInfo.getId());
	}

	@Override
	public PageInfo<ClaimRegionalBuildInfo> findClaimRegionalBuildInfoByRegionalBuildInfoId(
			Long regionalBuildInfoId, Integer pageNum, Integer pageSize,
			String sortField, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("regionalBuildInfoId", regionalBuildInfoId);

		map.put("sortField", sortField);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"regionalBuildInfo.countClaimRegionalBuildInfo", map);
		map.put("countNum", countNum);
		List<ClaimRegionalBuildInfo> list = getSqlMapClientTemplate()
				.queryForList(
						"regionalBuildInfo.getClaimRegionalBuildInfoByRegionalBuildInfoId",
						map, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<ClaimRegionalBuildInfo> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List list) {
		PageInfo<ClaimRegionalBuildInfo> pageInfo = new PageInfo<ClaimRegionalBuildInfo>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public void deleteClaimRegionalBuildInfoById(Long id) {
		this.getSqlMapClientTemplate().delete(
				"regionalBuildInfo.deletClaimeRegionalBuildInfoById", id);

	}

	@Override
	public ClaimRegionalBuildInfo getClaimRegionalBuildInfoById(Long id) {
		return (ClaimRegionalBuildInfo) this.getSqlMapClientTemplate()
				.queryForObject(
						"regionalBuildInfo.getClaimRegionalBuildInfoById", id);
	}

	@Override
	public Boolean regionalBuildInfoIsClaim(Long regionalBuildInfoId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("regionalBuildInfoId", regionalBuildInfoId);
		Integer count = (Integer) this.getSqlMapClientTemplate()
				.queryForObject(
						"regionalBuildInfo.countClaimRegionalBuildInfo", map);
		return count > 0;
	}

	@Override
	public Integer countRegionalBuildInfoByOrgIdOrOrgInternalCode(
			List<Long> orgIdList) {
		if (orgIdList == null || orgIdList.size() == 0) {
			return 0;
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgIdsList", ParametersConvertUtil.convertToListString(orgIdList));
			Integer result = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(
							"regionalBuildInfo.countRegionalBuildInfoByOrgIdOrOrgInternalCode",
							map);
			return result == null ? 0 : result;
		}
	}
}
