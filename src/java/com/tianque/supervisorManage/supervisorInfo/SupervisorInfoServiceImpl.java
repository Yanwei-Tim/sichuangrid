package com.tianque.supervisorManage.supervisorInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.ServiceMemberHasObjectService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("supervisorInfoService")
public class SupervisorInfoServiceImpl implements SupervisorInfoService {
	@Autowired
	private ServiceMemberHasObjectService serviceMemberHasObjectService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public PageInfo<SupervisorInfoVo> findSupervisorforList(SupervisorInfoVo supervisorInfoVo,
			int pageNum, int pageSize, String sortField, String order) {
		PageInfo<SupervisorInfoVo> pageInfo = serviceMemberHasObjectService.findSupervisorforList(
				supervisorInfoVo, pageNum, pageSize, sortField, order);
		completeOrgNameForPageInfo(pageInfo);
		return pageInfo;

	}

	@Override
	public void addSupervisor(SupervisorInfoVo supervisorInfoVo) {
		serviceMemberHasObjectService.addSupervisor(supervisorInfoVo);

	}

	@Override
	public void deleteSupervisors(SupervisorInfoVo population) {
		serviceMemberHasObjectService.deleteSupervisors(population);

	}

	@Override
	public String[][] getExportPopertyArray(String supervisorName) {
		return SpecialGroupsContext.getSupervisorRla(supervisorName);
	}

	@Override
	public PageInfo<SupervisorInfoVo> searchSupervisor(SupervisorInfoVo supervisorInfoVo,
			int pageNum, int pageSize, String sortField, String order) {
		PageInfo<SupervisorInfoVo> pageInfo = serviceMemberHasObjectService.searchSupervisor(
				supervisorInfoVo, pageNum, pageSize, sortField, order);
		completeOrgNameForPageInfo(pageInfo);
		return pageInfo;
	}

	private void completeOrgNameForPageInfo(PageInfo<SupervisorInfoVo> pageInfo) {

		if (pageInfo == null || pageInfo.getResult() == null || pageInfo.getResult().size() == 0) {
		} else {
			for (SupervisorInfoVo population : pageInfo.getResult()) {
				Organization organization = organizationDubboService
						.getOrganizationByOrgInternalCode(population.getOrgInternalCode());
				organization.setOrgName(ControllerHelper.getOrganizationRelativeName(
						organization.getId(), organizationDubboService));
				population.setOrganization(organization);
			}
		}
	}

	@Override
	public List<SupervisorInfoVo> searchSupervisorsForExport(SupervisorInfoVo supervisorInfoVo,
			Integer page, Integer rows, String sidx, String sord) {
		return serviceMemberHasObjectService.searchSupervisorsForExport(supervisorInfoVo, page,
				rows, sidx, sord);
	}

	@Override
	public SupervisorInfoVo viewSupervisor(SupervisorInfoVo supervisorInfoVo) {
		return serviceMemberHasObjectService.viewSupervisor(supervisorInfoVo);
	}

}
