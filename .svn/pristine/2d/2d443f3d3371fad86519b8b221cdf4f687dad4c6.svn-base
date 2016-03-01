package com.tianque.plugin.analyzing.service;

import java.util.List;

import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.PersonnelAreaDataVo;
import com.tianque.plugin.analyzing.domain.StatAnalysePlaceVo;

public interface StatisticsPersonnelService {
	public List<PersonnelAreaDataVo> getPositiveinfoAreaDataByOrgId(Long orgId);

	public List<PersonnelAreaDataVo> getIdleyouthAreaDataByOrgId(Long orgId);

	public HighchartColumnVo getSuperiorVisitColumnByOrgId(Long orgId);

	public HighchartColumnVo getPositiveinfoColumnByOrgId(Long orgId);

	public HighchartColumnVo getIdleyouthColumnByOrgId(Long orgId);

	public List<Object[]> getPositiveInfoPieByOrgId(Long orgId);

	public List<Object[]> getIdleyouthPieByOrgId(Long orgId);

	public List<PersonnelAreaDataVo> getImportantPersonelAreaDataByOrgId(Long orgId);

	public HighchartColumnVo getImportantPersonlColumnByOrgId(Long orgId);

	public List<Object[]> getImportantPersonlPieByOrgId(Long orgId);

	public List<PersonnelAreaDataVo> findStatAnalyseSuperiorVisit(Long orgId);

	public HighchartColumnVo getDangerousGoodsColumnByOrgId(Long orgId);

	public List<Object[]> getDangerousGoodsPractitionerPieByOrgId(Long orgId);

	public List<PersonnelAreaDataVo> getDangerousGoodsPractitionerAreaDataByOrgId(Long orgId);

	public List<Object[]> getImportantPersonlPieByOrgIdAndMonth(Long orgId, String typeName,
			int year, int month);

	List<PersonnelAreaDataVo> getImportantPersonelAreaDataByOrgIdAndMonth(Long orgId,
			String typeName, int year, int month);

	List<StatAnalysePlaceVo> getImportantPlAreaDataByOrgIdAndMonth(Long orgId, String typeName,
			int year, int month);

}
