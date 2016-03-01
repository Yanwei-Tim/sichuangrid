package com.tianque.service;

import java.util.Date;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Dustbin;
import com.tianque.domain.vo.SearchDustbinVo;

public interface DustbinService {
	public PageInfo<Dustbin> findDustbinForPageByOrgInternalCode(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			Boolean isEmphasis, String partType);

	public Dustbin getDustbinById(Long id);

	public Dustbin addDustbin(Dustbin dustbin);

	public Dustbin updateDustbin(Dustbin dustbin);

	public void deleteDustbinByIds(Long[] ids);

	public void updateEmphasiseByIds(Long[] ids, Long isEmphasis,
			String logoutReason, Date logoutDate);

	PageInfo<Dustbin> searchDustbinPagerBySearchVo(Long orgid,
			SearchDustbinVo searchVo, Integer pageNum, Integer pageSize,
			String sortField, String order, String partType);
	
	public boolean hasDuplicateDustbin(Dustbin dustbin);

	/**
	 * Excel导出格式定义
	 * 
	 * @return
	 */
	String[][] getExportPopertyArray(String partyName);

	String getVideoParamterById(Long id);

	/*
	 * public PageInfo<Dustbin> findDustbinByQueryCondition( SearchDustbinVo
	 * searchDustbinVo, Integer pageNum, Integer pageSize, String sidx, String
	 * sord); public PageInfo<Dustbin> fastSearchDustbin(SearchDustbinVo
	 * searchDustbinVo, Integer pageNum, Integer pageSize, String sortField,
	 * String order) ; public List<Druggy>
	 * searchDustbinForExport(SearchDustbinVo searchDustbinVo, Integer pageNum,
	 * Integer pageSize, String sidx, String sord);
	 */

	public Dustbin getDustbinByDustbinCode(String dustbinCode, Long orgId);

}
