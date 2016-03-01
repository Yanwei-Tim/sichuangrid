package com.tianque.version.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.version.domain.Versions;

/**
 * @author 杨立忠
 * @version V1.0
 * @创建时间：2013-5-13 下午02:30:52
 */
@Repository("versionsDao")
public class VersionnsDaoImpl extends AbstractBaseDao implements VersionsDao {

	@Override
	public void addVersion(Versions versions) {
		getSqlMapClientTemplate().insert("versions.addVersion", versions);
	}

	@Override
	public void deleteVersion(String versionId) {
		getSqlMapClientTemplate().delete("versions.deleteVersion", versionId);

	}

	@Override
	public PageInfo<Versions> findGridPage(int pageNum, int pageSize, String sortField, String order) {
		List list = getSqlMapClientTemplate().queryForList("versions.findGridPage",
				(pageNum - 1) * pageSize, pageSize);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"versions.countfindGridPage");
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public List<Versions> getAllVersions() {
		return getSqlMapClientTemplate().queryForList("versions.findGridPage");
	}

	@Override
	public Versions getVersionsById(String versionId) {
		return (Versions) getSqlMapClientTemplate().queryForObject("versions.getVersionsById",
				versionId);
	}

	@Override
	public void updateVersion(Map map) {
		getSqlMapClientTemplate().update("versions.updateVersion", map);
	}

	private PageInfo<Versions> createPageInfo(int pageNum, int pageSize, Integer countNum, List list) {
		PageInfo<Versions> pageInfo = new PageInfo<Versions>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

}
