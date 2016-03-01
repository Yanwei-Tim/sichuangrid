package com.tianque.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PersonnelTrack;

public interface PersonnelTrackDao {
	public PersonnelTrack getPersonnelTrackById(Long id);

	public PersonnelTrack addPersonnelTrack(PersonnelTrack personnelTrack);

	public PageInfo<PersonnelTrack> findpersonnelTracksByIdcardNo(String idcardNo, Integer pageNum,
			Integer pageSize, String sidx, String sord);

}
