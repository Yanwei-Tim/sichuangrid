/**
 * 
 */
package com.tianque.mongodb.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.core.vo.PageInfo;
import com.tianque.job.SessionHelper;
import com.tianque.mongodb.dao.AbstractMongodbDao;
import com.tianque.mongodb.dao.HouseholdStaffMongoDao;
import com.tianque.mongodb.job.HouseholdStaffMoveMongoDb;

/**
 * @since
 * @author 曾利民
 * @version 1.0.0[2014年12月18日]
 */
@Repository("householdStaffMogonDao")
public class HouseholdStaffMogonDaoImpl extends
		AbstractMongodbDao<HouseholdStaff> implements HouseholdStaffMongoDao {

	@Autowired
	private HouseholdStaffMoveMongoDb householdStaffMoveMongoDb;

	public HouseholdStaffMogonDaoImpl() {
		entityClass = HouseholdStaff.class;
	}

	@Override
	public PageInfo<HouseholdStaff> findHouseholdStaffByOrgIdDefaultList(
			List<Long> orgList, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		return queryByOrgParentidForPage(orgList, pageNum, pageSize, sidx, sord);
	}

	@Override
	public PageInfo<HouseholdStaff> findHouseholdStaffByOrgCodeDefaultList(
			String orgCode, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		return queryByOrgCodeForPage(orgCode, pageNum, pageSize, sidx, sord,
				"orgCode_1_logOut_1");
	}

	@Override
	public PageInfo<HouseholdStaff> fastSearchHouseholdStaff(String orgCode,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String condition) {
		return fastSearchForPage(orgCode, pageNum, pageSize, sidx, sord,
				condition);
	}

	@Override
	public void move(final Integer beginId) {
		Thread t = new Thread() {
			@Override
			public void run() {
				SessionHelper.createMockAdminSession();
				householdStaffMoveMongoDb.execute(beginId);
			}
		};
		t.start();
	}

}
