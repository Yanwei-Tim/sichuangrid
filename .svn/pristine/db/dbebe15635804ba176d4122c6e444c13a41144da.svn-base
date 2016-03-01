package com.tianque.baseInfo.permanentAddress.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.permanentAddress.dao.PermanentAddressLogDao;
import com.tianque.baseInfo.permanentAddress.domain.PermanentAddressLog;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;

@Transactional
@Service("permanentAddressLogService")
public class PermanentAddressLogServiceImpl implements
		PermanentAddressLogService {

	@Autowired
	private PermanentAddressLogDao permanentAddressLogDao;

	@Override
	public PermanentAddressLog addPermanentAddressLog(
			PermanentAddressLog permanentAddressLog) {
		return permanentAddressLogDao
				.addPermanentAddressLog(permanentAddressLog);
	}

	@Override
	public boolean deletePermanentAddressLog(Long id) {
		return permanentAddressLogDao.deletePermanentAddressLog(id);
	}

	@Override
	public PermanentAddressLog updatePermanentAddressLog(
			PermanentAddressLog permanentAddressLog) {
		return permanentAddressLogDao
				.updatePermanentAddressLog(permanentAddressLog);
	}

	@Override
	public PermanentAddressLog getPermanentAddressLogById(Long id) {
		return permanentAddressLogDao.getPermanentAddressLogById(id);
	}

	@Override
	public List<PermanentAddressLog> findPermanentAddressLogByJobstatus(
			Integer jobState) {
		if (null == jobState) {
			throw new BusinessValidationException("参数错误");
		}
		return permanentAddressLogDao
				.findPermanentAddressLogByJobstatus(jobState);
	}

	@Override
	public void updatePermanentAddressLogJobstatus(Long id, Integer jobState) {
		if (null == id || null == jobState) {
			throw new BusinessValidationException("参数错误");
		}
		permanentAddressLogDao.updatePermanentAddressLogJobstatus(id, jobState);

	}

	@Override
	public PageInfo<PermanentAddressLog> findAllPermanentAddressLog(
			Integer page, Integer rows, String sidx, String sord) {

		return permanentAddressLogDao.findAllPermanentAddressLog(page, rows,
				sidx, sord);
	}

	@Override
	public void deleteLogByIds(String[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("数据不存在");
		}

		permanentAddressLogDao.deleteLogByIds(ids);
	}

	@Override
	public List<PermanentAddressLog> findPermanentAddressLogByJobstatusAndIds(
			String[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("数据不存在");
		}
		return permanentAddressLogDao
				.findPermanentAddressLogByJobstatusAndIds(ids);
	}

}
