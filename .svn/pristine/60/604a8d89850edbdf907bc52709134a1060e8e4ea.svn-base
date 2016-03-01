package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.dao.ServiceMemberHasObjectDao;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.ServiceMemberHasObjectService;
import com.tianque.supervisorManage.supervisorInfo.SupervisorInfoVo;

@Service("serviceMemberHasObjectService")
public class ServiceMemberHasObjectServiceImpl implements
		ServiceMemberHasObjectService {
	@Autowired
	private ServiceMemberHasObjectDao serviceMemberHasObjectDao;

	@Override
	public PageInfo<SupervisorInfoVo> findSupervisorforList(
			SupervisorInfoVo supervisorInfoVo, int pageNum, int pageSize,
			String sortField, String order) {
		return serviceMemberHasObjectDao.findSupervisorforList(
				supervisorInfoVo, pageNum, pageSize, sortField, order);
	}

	@Override
	public PageInfo<SupervisorInfoVo> searchSupervisor(
			SupervisorInfoVo supervisorInfoVo, int pageNum, int pageSize,
			String sortField, String order) {
		return serviceMemberHasObjectDao.searchSupervisor(supervisorInfoVo,
				pageNum, pageSize, sortField, order);
	}

	@Override
	public void addSupervisor(SupervisorInfoVo supervisorInfoVo) {
		try {
			serviceMemberHasObjectDao.addSupervisor(supervisorInfoVo);
		} catch (Exception e) {
			throw new BusinessValidationException(e.getMessage());
		}
	}

	@Override
	public void deleteSupervisors(SupervisorInfoVo population) {
		// TODO判断该成员和该对象是否有对应的服务记录
		serviceMemberHasObjectDao.deleteSupervisors(population);
	}

	@Override
	public List<SupervisorInfoVo> searchSupervisorsForExport(
			SupervisorInfoVo supervisorInfoVo, Integer page, Integer rows,
			String sidx, String sord) {
		List<SupervisorInfoVo> list = serviceMemberHasObjectDao
				.searchSupervisorsForExport(supervisorInfoVo, page, rows, sidx,
						sord);
		return list;
	}

	@Override
	public SupervisorInfoVo viewSupervisor(SupervisorInfoVo supervisorInfoVo) {
		return serviceMemberHasObjectDao.viewSupervisor(supervisorInfoVo
				.getMemberId());
	}
}
