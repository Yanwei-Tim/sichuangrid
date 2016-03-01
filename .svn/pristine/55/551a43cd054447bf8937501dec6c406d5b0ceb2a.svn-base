package com.tianque.dao;

import java.util.Date;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Dustbin;
import com.tianque.domain.vo.SearchDustbinVo;

public interface DustbinDao {
	public PageInfo<Dustbin> findDustbinForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long isEmphasis, String partType,
			Long partTypeId);

	public Dustbin getDustbinById(Long id);

	public Dustbin addDustbin(Dustbin dustbin);

	public Dustbin updateDustbin(Dustbin dustbin);

	public void updateEmphasiseById(Long id, Long isEmphasis,
			String logoutReason, Date logoutDate);

	public void deleteDustbinById(Long id);

	public Dustbin getDustbinByUnitName(String unitName, Long orgId);

	PageInfo<Dustbin> searchDustbinPagerBySearchVo(SearchDustbinVo searchVo,
			Integer pageNum, Integer pageSize, String sortField, String order,
			String partType);

	public Dustbin getDustbinByDustbinCode(String dustbinCode, Long orgId);
}
