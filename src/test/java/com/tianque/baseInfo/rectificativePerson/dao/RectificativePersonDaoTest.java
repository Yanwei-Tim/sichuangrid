package com.tianque.baseInfo.rectificativePerson.dao;

import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringBeanByName;

import com.tianque.base.excel.BaseDaoTest;
import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.util.XlsDataSetBeanFactory;

public class RectificativePersonDaoTest extends
		BaseDaoTest<RectificativePerson, RectificativePersonDao> {

	@SpringBeanByName
	private RectificativePersonDao rectificativePersonDao;

	@Override
	public RectificativePersonDao getDao() {

		return rectificativePersonDao;
	}

	@Test
	@DataSet
	@ExpectedDataSet
	@Override
	public void updateTest() throws Exception {
		RectificativePerson rectificativePerson = rectificativePersonDao.get(1L);
		rectificativePerson = (RectificativePerson) XlsDataSetBeanFactory.createBean(
				"RectificativePersonDaoTest.beans.xls", "updataRectificativePersons",
				rectificativePerson, this);
		rectificativePersonDao.update(rectificativePerson);
	}
}
