package com.tianque.plugin.analysisNew.service;

import java.util.List;

import com.tianque.plugin.analysisNew.domain.HighchartColumnVo;
import com.tianque.plugin.analysisNew.domain.PersonnelAreaDataVo;
import com.tianque.plugin.analysisNew.domain.StatAnalysePlaceVo;

public interface StatisticsPersonnelNewService {
	public List<PersonnelAreaDataVo> getPositiveinfoAreaDataByOrgId(Long orgId);

	public List<PersonnelAreaDataVo> getIdleyouthAreaDataByOrgId(Long orgId);

	public HighchartColumnVo getSuperiorVisitColumnByOrgId(Long orgId);

	public HighchartColumnVo getPositiveinfoColumnByOrgId(Long orgId);

	public HighchartColumnVo getIdleyouthColumnByOrgId(Long orgId);

	public List<Object[]> getPositiveInfoPieByOrgId(Long orgId);

	public List<Object[]> getIdleyouthPieByOrgId(Long orgId);

	public List<PersonnelAreaDataVo> getImportantPersonelAreaDataByOrgId(
			Long orgId);

	public HighchartColumnVo getImportantPersonlColumnByOrgId(Long orgId);

	public List<Object[]> getImportantPersonlPieByOrgId(Long orgId);

	public List<PersonnelAreaDataVo> findStatAnalyseSuperiorVisit(Long orgId);

	public HighchartColumnVo getDangerousGoodsColumnByOrgId(Long orgId);

	public List<Object[]> getDangerousGoodsPractitionerPieByOrgId(Long orgId);

	public List<PersonnelAreaDataVo> getDangerousGoodsPractitionerAreaDataByOrgId(
			Long orgId);

	public List<Object[]> getImportantPersonlPieByOrgIdAndMonth(Long orgId,
			String typeName, int year, int month);

	List<PersonnelAreaDataVo> getImportantPersonelAreaDataByOrgIdAndMonth(
			Long orgId, String typeName, int year, int month);

	List<StatAnalysePlaceVo> getImportantPlAreaDataByOrgIdAndMonth(Long orgId,
			String typeName, int year, int month);

}
