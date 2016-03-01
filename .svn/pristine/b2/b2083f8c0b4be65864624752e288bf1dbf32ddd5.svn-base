package com.tianque.working.dao;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByName;

import com.tianque.base.excel.BaseDaoTest;
import com.tianque.core.exception.DAOException;
import com.tianque.util.XlsDataSetBeanFactory;
import com.tianque.working.domain.WorkingRecord;

public class WorkingRecordDaoTest extends BaseDaoTest<WorkingRecord, WorkingRecordDao> {

	@SpringBeanByName
	private WorkingRecordDao workingRecordDao;

	@Override
	public WorkingRecordDao getDao() {
		return workingRecordDao;
	}

	@Test(expected = DAOException.class)
	public void 台帐目录为null时add() throws Exception {
		WorkingRecord workingRecord = getAddWorkingRecord();
		workingRecord.setDailyDirectory(null);
		workingRecordDao.add(workingRecord);
	}

	@Test(expected = DAOException.class)
	public void 组织机构代码为null时add() throws Exception {
		WorkingRecord workingRecord = getAddWorkingRecord();
		workingRecord.setOrgInternalCode(null);
		workingRecordDao.add(workingRecord);
	}

	@Test(expected = DAOException.class)
	public void 组织机构id为null时add() throws Exception {
		WorkingRecord workingRecord = getAddWorkingRecord();
		workingRecord.getOrganization().setId(null);
		workingRecordDao.add(workingRecord);
	}

	@Test(expected = DAOException.class)
	public void 台帐id为null时update() throws Exception {
		WorkingRecord workingRecord = getUpdateWorkingRecord();
		workingRecord.setId(null);
		workingRecordDao.update(workingRecord);
	}

	@Test(expected = DAOException.class)
	public void 台帐目录为null时update() throws Exception {
		WorkingRecord workingRecord = getUpdateWorkingRecord();
		workingRecord.setDailyDirectory(null);
		workingRecordDao.add(workingRecord);
	}

	@Test(expected = DAOException.class)
	public void 组织机构代码为null时update() throws Exception {
		WorkingRecord workingRecord = getUpdateWorkingRecord();
		workingRecord.setOrgInternalCode(null);
		workingRecordDao.add(workingRecord);
	}

	@Test(expected = DAOException.class)
	public void 组织机构id为null时update() throws Exception {
		WorkingRecord workingRecord = getUpdateWorkingRecord();
		workingRecord.getOrganization().setId(null);
		workingRecordDao.add(workingRecord);
	}

	private WorkingRecord getAddWorkingRecord() throws Exception {
		return XlsDataSetBeanFactory.createBeans("WorkingRecordDaoTest.beans.xls",
				"addWorkingRecords", WorkingRecord.class, this).get(0);
	}

	private WorkingRecord getUpdateWorkingRecord() throws Exception {
		return XlsDataSetBeanFactory.createBeans("WorkingRecordDaoTest.beans.xls",
				"updateWorkingRecords", WorkingRecord.class, this).get(0);
	}

}
