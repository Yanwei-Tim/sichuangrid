package com.tianque.version.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.vo.PageInfo;
import com.tianque.version.dao.VersionsDao;
import com.tianque.version.domain.Versions;

/**
 * @author 杨立忠
 * @version V1.0
 * @创建时间：2013-5-13 下午03:12:51
 */

@Service("versionsService")
@Transactional
public class VersionsServiceImpl implements VersionsService {

	@Autowired
	private VersionsDao versionsDao;

	@Override
	public Versions addVersion(Versions versions) {
		versionsDao.addVersion(versions);
		return getVersionsById(versions.getVersionId());

	}

	@Override
	public PageInfo<Versions> findGridPage(int pageNum, int pageSize, String sortField, String order) {
		return versionsDao.findGridPage(pageNum, pageSize, sortField, order);
	}

	@Override
	public Versions getVersionsById(String versionId) {
		return versionsDao.getVersionsById(versionId);
	}

	@Override
	public void updateVersion(Versions versions, String versionId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("versionId", versions.getVersionId());
		map.put("versionContent", versions.getVersionContent());
		map.put("oldVersionId", versionId);

		versionsDao.updateVersion(map);

	}

}
