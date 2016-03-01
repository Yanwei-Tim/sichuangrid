package com.tianque.baseInfo.rectificativePerson.dao;

import java.util.List;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchRectificativePersonVo;

public interface RectificativePersonDao extends
		BaseInfoPopulationBaseDao<RectificativePerson, SearchRectificativePersonVo> {

	public PageInfo<RectificativePerson> findRectificativePersonsForPageByOrgInternalCode(
			String orgInternalCode, int pageNum, int pageSize, String sortField, String order,
			Long isEmphasis);

	public RectificativePerson updateEmphasiseById(Long id, Long isEmphasis);

	public void updateDeathAndLogoutStateById(Long id, Boolean death, Long logoutState);

	public List<RectificativePerson> findRectificativePersonByRectifyDate();

	public RectificativePerson updateIsRemindByid(Long id);

	public List<RectificativePerson> findRectificativePersonByIsRemind(
			Long remindTime);
	
	public void updateTableUpdateDateById(Long id);
}
