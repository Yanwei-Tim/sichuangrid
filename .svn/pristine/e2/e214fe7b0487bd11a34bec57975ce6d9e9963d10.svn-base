package com.tianque.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.FileUtil;
import com.tianque.dao.HainingVillageMapDao;
import com.tianque.domain.HainingVillageMap;
import com.tianque.service.HainingVillageMapService;

@Service("hainingVillageMapService")
@Transactional
public class HainingVillageMapServiceImpl implements HainingVillageMapService {
	@Autowired
	private HainingVillageMapDao hainingVillageMapDao;

	@Override
	public HainingVillageMap getHainingVillageMapByDepartmentNo(String departmentNo) {

		return hainingVillageMapDao.getHainingVillageMapByDepartmentNo(departmentNo);
	}

	@Override
	public HainingVillageMap addHainingVillageMap(HainingVillageMap hainingVillageMap) {

		return hainingVillageMapDao.addHainingVillageMap(hainingVillageMap);
	}

	@Override
	public HainingVillageMap updateHainingVillageMap(HainingVillageMap hainingVillageMap) {
		return hainingVillageMapDao.updateHainingVillageMap(hainingVillageMap);
	}

	@Override
	public boolean deleteImg(HainingVillageMap hainingVillageMap) {
		hainingVillageMapDao.deleteImg(hainingVillageMap.getDepartmentNo());
		String ss = FileUtil.getWebRoot() + File.separator + hainingVillageMap.getImgUrl();
		File img = new File(ss);
		if (img.isFile()) {
			img.delete();
		}
		return true;
	}

	public HainingVillageMapDao getHainingVillageMapDao() {
		return hainingVillageMapDao;
	}

	public void setHainingVillageMapDao(HainingVillageMapDao hainingVillageMapDao) {
		this.hainingVillageMapDao = hainingVillageMapDao;
	}

}
