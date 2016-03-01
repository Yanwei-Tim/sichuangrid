package com.tianque.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Report;
import com.tianque.domain.vo.PenalArrestVo;

public interface PenalArrestService {

	public PenalArrestVo addPenalArrestVo(PenalArrestVo penalArrestVo);

	public PenalArrestVo savePenalArrestVo(PenalArrestVo penalArrestVo);

	public PenalArrestVo updatePenalArrestVoSateById(PenalArrestVo penalArrestVo);

	public PenalArrestVo findPenalArrestVo(PenalArrestVo penalArrestVo);

	public void deleteReportById(Long id);

	public PageInfo<Report> findUnderLinePenalArrestVosByOrgCodeAndyear(
			PenalArrestVo penalArrestVo, Integer page, Integer rows, String sidx, String sord);

	public PenalArrestVo findPenalArrestVoById(PenalArrestVo penalArrestVo);

	public PageInfo<Report> findreportsByOrgCodeAndyear(PenalArrestVo treatment, Integer page,
			Integer rows, String sidx, String sord);

	public int countPenalArrest(String year, String month, String type, Long orgId);
}
