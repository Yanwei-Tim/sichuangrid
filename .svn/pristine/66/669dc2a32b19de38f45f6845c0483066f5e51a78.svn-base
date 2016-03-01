package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.PersonnelTrackDao;
import com.tianque.domain.PersonnelTrack;

@Repository
public class PersonnelTrackDaoImpl extends AbstractBaseDao implements PersonnelTrackDao {

	@Override
	public PersonnelTrack addPersonnelTrack(PersonnelTrack personnelTrack) {
		Long id = (Long) getSqlMapClientTemplate().insert("personnelTrack.addPersonnelTrack",
				personnelTrack);
		return getPersonnelTrackById(id);
	}

	@Override
	public PersonnelTrack getPersonnelTrackById(Long id) {
		return (PersonnelTrack) this.getSqlMapClientTemplate().queryForObject(
				"personnelTrack.getPersonnelTrackById", id);
	}

	@Override
	public PageInfo<PersonnelTrack> findpersonnelTracksByIdcardNo(String idcardNo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idcardNo", idcardNo);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"personnelTrack.countpersonnelTracksByIdcardNo", map);
		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		List<PersonnelTrack> list = getSqlMapClientTemplate().queryForList(
				"personnelTrack.findpersonnelTracksByIdcardNo", map, (pageNum - 1) * pageSize,
				pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<PersonnelTrack> createPageInfo(int pageNum, int pageSize, Integer countNum,
			List list) {
		PageInfo<PersonnelTrack> pageInfo = new PageInfo<PersonnelTrack>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

}