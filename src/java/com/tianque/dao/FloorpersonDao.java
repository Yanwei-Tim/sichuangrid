package com.tianque.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Floorperson;

public interface FloorpersonDao {
	public PageInfo<Floorperson> findFloorperson(Long palceId, int pageNum, int pageSize,
			String sortField, String order, String placeType);

	public Floorperson addFloorperson(Floorperson floorperson);

	public Floorperson updateFloorperson(Floorperson floorperson);

	public Floorperson getFloorperson(Long id);

	public void deleteFloorperson(Long placeId, String placeType);

	public void deleteFloorpersonById(Long id);

}
