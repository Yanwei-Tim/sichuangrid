package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.supervisorManage.supervisorInfo.SupervisorInfoVo;

public interface ServiceMemberHasObjectDao {
	public PageInfo<SupervisorInfoVo> findSupervisorforList(SupervisorInfoVo supervisorInfoVo,
			int pageNum, int pageSize, String sortField, String order);

	public PageInfo<SupervisorInfoVo> searchSupervisor(SupervisorInfoVo supervisorInfoVo,
			int pageNum, int pageSize, String sortField, String order);

	public void addSupervisor(SupervisorInfoVo supervisorInfoVo);

	public SupervisorInfoVo viewSupervisor(Long memberId);

	public int deleteSupervisors(SupervisorInfoVo population);

	public List<SupervisorInfoVo> searchSupervisorsForExport(SupervisorInfoVo condition,
			Integer page, Integer rows, String sortField, String order);

}
