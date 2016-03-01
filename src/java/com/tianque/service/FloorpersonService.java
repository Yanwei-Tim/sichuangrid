package com.tianque.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Floorperson;

public interface FloorpersonService {
	public PageInfo<Floorperson> findFloorperson(Long palceId, int pageNum, int pageSize,
			String sortField, String order, String placeType);

	public Floorperson addFloorperson(Floorperson floorperson);

	public Floorperson updateFloorperson(Floorperson floorperson);

	public void deleteFloorperson(Long placeId, String placeType);

	public void deleteFloorpersonById(Long id);

	public Floorperson getFloorperson(Long id);

}
