package com.tianque.baseInfo.personnelTrackInfo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.baseInfo.personnelTrackInfo.domain.PersonnelTrackInfo;
import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchRectificativePersonVo;

@Repository("personnelTrackInfoDao")
public class PersonnelTrackInfoDaoImpl extends
		BaseInfoPopulationBaseDaoImpl<PositiveInfo, SearchRectificativePersonVo> implements
		PersonnelTrackInfoDao {

	public PageInfo<PersonnelTrackInfo> findPersonnelTrackInfoByIdCardNo(String idCardNo,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idcardNo", idCardNo);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"personnelTrackInfo.countPersonnelTrackInfoByIdCardNo", map);
		if (sidx != null && !"".equals(sidx.trim())) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		List<PersonnelTrackInfo> list = getSqlMapClientTemplate().queryForList(
				"personnelTrackInfo.findPersonnelTrackInfoByIdCardNo", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<PersonnelTrackInfo> createPageInfo(Integer pageNum, Integer pageSize,
			Integer countNum, List<PersonnelTrackInfo> list) {
		PageInfo<PersonnelTrackInfo> pageInfo = new PageInfo<PersonnelTrackInfo>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	public PersonnelTrackInfo addPersonnelTrackInfo(PersonnelTrackInfo personnelTrackInfo) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"personnelTrackInfo.addPersonnelTrackInfo", personnelTrackInfo);
		return getPersonnelInfoTrackById(id);
	}

	private PersonnelTrackInfo getPersonnelInfoTrackById(Long id) {
		return (PersonnelTrackInfo) this.getSqlMapClientTemplate().queryForObject(
				"personnelTrackInfo.getPersonnelInfoTrackById", id);
	}

}
