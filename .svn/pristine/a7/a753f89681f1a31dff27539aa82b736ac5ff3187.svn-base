package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.supervisorManage.supervisorInfo.SupervisorInfoVo;

public interface ServiceMemberHasObjectService {
	public PageInfo<SupervisorInfoVo> findSupervisorforList(SupervisorInfoVo supervisorInfoVo,
			int pageNum, int pageSize, String sortField, String order);

	public PageInfo<SupervisorInfoVo> searchSupervisor(SupervisorInfoVo supervisorInfoVo,
			int pageNum, int pageSize, String sortField, String order);

	public void addSupervisor(SupervisorInfoVo supervisorInfoVo);

	public SupervisorInfoVo viewSupervisor(SupervisorInfoVo supervisorInfoVo);

	public List<SupervisorInfoVo> searchSupervisorsForExport(SupervisorInfoVo supervisorInfoVo,
			Integer page, Integer rows, String sidx, String sord);

	public void deleteSupervisors(SupervisorInfoVo population);
}
