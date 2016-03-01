package com.tianque.mobileErrorLogs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.mobileErrorLogs.dao.MobileErrorLogsDao;
import com.tianque.mobileErrorLogs.domain.MobileErrorLogs;

/**
 * @author weiminglong
 *2016年1月7日下午2:42:43
 */
@Transactional
@Service("mobileErrorLogsService")
public class MobileErrorLogsServiceImpl extends AbstractBaseService implements
MobileErrorLogsService {
    @Autowired
    private MobileErrorLogsDao mobileErrorLogsDao;
	@Override
	public MobileErrorLogs addMobileErrorLogs(MobileErrorLogs mobileErrorLogs) {
		 mobileErrorLogs = mobileErrorLogsDao
				.addMobileErrorLogs(mobileErrorLogs);
		return mobileErrorLogs;
	}
	
	@Override
	public PageInfo<MobileErrorLogs> findMobileErrorLogs(
			MobileErrorLogs mobileErrorLogs, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		return mobileErrorLogsDao.findMobileErrorLogs(mobileErrorLogs, pageNum,
				pageSize, sidx, sord);
	}

	@Override
	public PageInfo<MobileErrorLogs> advancedSearchMobileErrorLogs(
			MobileErrorLogs mobileErrorLogs, Integer page, Integer rows,
			String sidx, String sord) {
		return mobileErrorLogsDao
				.advancedSearchMobileErrorLogs(mobileErrorLogs, page, rows,sidx,sord);
	}

}
