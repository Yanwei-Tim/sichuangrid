package com.tianque.baseInfo.overseaPersonnel.dao;

import java.util.List;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchOverseaPersonnelVo;
import com.tianque.service.util.PopulationCatalog;

public interface OverseaPersonnelDao extends
		BaseInfoPopulationBaseDao<OverseaPersonnel, SearchOverseaPersonnelVo> {

	public OverseaPersonnel addOverseaPersonnel(OverseaPersonnel overseaPersonnel);

	public OverseaPersonnel getOverseaPersonnelById(Long id);

	public OverseaPersonnel updateOverseaPersonnel(OverseaPersonnel overseaPersonnel);

	public int deleteOverseaPersonnelById(Long id);

	public PageInfo<OverseaPersonnel> findOverseaPersonnelForListPageByOrgInternalCode(
			String orgInternalCode, int pageNum, int pageSize, String sortField, String order,
			Long isEmphasis);

	public PageInfo<OverseaPersonnel> findOVerseaPersonnelByOrgInternalCodeForGis(
			String orgInternalCode, int pageNum, int pageSize, String sortField, String order);

	public OverseaPersonnel getOverseaPersonnelByCertificateNoAndOrganizationId(
			String CertificateNo, Long organizationId);

	// public OverseaPersonnel getOverseaPersonnelByCertificate(List<String> idCardNoList,Long
	// orgId);
	public OverseaPersonnel getOverseaPersonnelByCertificate(Long certificateType,
			String certificateNo, Long orgId);

	public OverseaPersonnel findGisOverseaStaffById(Long personId);

	public void updateActualPopulationToHasHouseState(Long populationId, String address);

	public List<OverseaPersonnel> findAllBindingOverseaStaffByorgCodeForGis(
			PopulationCatalog catalog, String orgInternalCode);
}
