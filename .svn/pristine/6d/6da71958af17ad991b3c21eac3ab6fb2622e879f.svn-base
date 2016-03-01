/**
 * 
 */
package com.tianque.mongodb.dao;

import java.util.List;

import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.core.vo.PageInfo;

/**
 * @since
 * @author 曾利民
 * @version 1.0.0[2014年12月18日]
 */
public interface HouseholdStaffMongoDao extends
		MongodbCommonDao<HouseholdStaff> {

	PageInfo<HouseholdStaff> findHouseholdStaffByOrgIdDefaultList(
			List<Long> orgList, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	PageInfo<HouseholdStaff> findHouseholdStaffByOrgCodeDefaultList(
			String orgCode, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	PageInfo<HouseholdStaff> fastSearchHouseholdStaff(String orgCode,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String condition);

	void move(Integer beginId);
}
