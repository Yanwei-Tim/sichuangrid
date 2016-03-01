package com.tianque.working.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.working.dao.MyDepartmentGropDao;
import com.tianque.working.domain.GroupObj;
import com.tianque.working.domain.MyDepartmentGrop;
import com.tianque.working.domain.OptionalObj;

@Repository("myDepartmentGropDao")
public class MyDepartmentGropDaoImpl extends AbstractBaseDao implements MyDepartmentGropDao {

	@Override
	public MyDepartmentGrop addMyDepartmentGrop(MyDepartmentGrop myDepartmentGrop) {
		Long id = (Long) getSqlMapClientTemplate().insert("myDepartmentGrop.addMyDepartmentGrop",
				myDepartmentGrop);
		return findMyDepartmentGropById(id);
	}

	@Override
	public MyDepartmentGrop findMyDepartmentGropById(Long id) {
		return (MyDepartmentGrop) getSqlMapClientTemplate().queryForObject(
				"myDepartmentGrop.findMyDepartmentGropById", id);
	}

	@Override
	public List<MyDepartmentGrop> findMyDepartmentGropForList(Long orgId) {
		List<MyDepartmentGrop> myDepartmentGrops = getSqlMapClientTemplate().queryForList(
				"myDepartmentGrop.findMyDepartmentGropForList", orgId);
		return myDepartmentGrops;
	}

	@Override
	public MyDepartmentGrop updateMyDepartmentGropMembers(Long id, String groupMembers) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("groupMembers", groupMembers);
		getSqlMapClientTemplate().update("myDepartmentGrop.updateMyDepartmentGropMembers", map);
		return findMyDepartmentGropById(id);
	}

	@Override
	public MyDepartmentGrop updateMyDepartmentGropName(MyDepartmentGrop myDepartmentGrop) {
		if (myDepartmentGrop != null && myDepartmentGrop.getId() != null) {
			getSqlMapClientTemplate().update("myDepartmentGrop.updateMyDepartmentGropName",
					myDepartmentGrop);
			return findMyDepartmentGropById(myDepartmentGrop.getId());
		}
		return null;
	}

	@Override
	public void deleteMyDepartmentGropById(Long id) {
		getSqlMapClientTemplate().delete("myDepartmentGrop.deleteMyDepartmentGropById", id);
	}

	@Override
	public List<GroupObj> findGroupObjsByGroupId(Long departmentGroupId) {
		return getSqlMapClientTemplate().queryForList("groupObj.findGroupObjsByGroupId",
				departmentGroupId);
	}

	@Override
	public void updateGroupObjByGroupIdAndDeleteState(Long id, Long deleteState) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("departmentGroupId", id);
		map.put("deleteState", deleteState);
		getSqlMapClientTemplate().update("groupObj.updateGroupObjByGroupIdAndDeleteState", map);
	}

	@Override
	public void addOptionalObj(OptionalObj optionalObj) {
		getSqlMapClientTemplate().insert("optionalObj.addOptionalObj", optionalObj);
	}

	@Override
	public void addGroupObj(GroupObj groupObj) {
		getSqlMapClientTemplate().insert("groupObj.addGroupObj", groupObj);
	}

	@Override
	public void updateOptionalObjForOrgIds(Long documentId, String postObj, String optionalOrgIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("documentId", documentId);
		map.put("postObj", postObj);
		map.put("optionalOrgIds", optionalOrgIds);
		getSqlMapClientTemplate().update("optionalObj.updateOptionalObjForOrgIds", map);

	}

	@Override
	public void deleteGroupObjByDocumentId(Long documentId) {
		getSqlMapClientTemplate().delete("groupObj.deleteGroupObjByDocumentId", documentId);
	}

	@Override
	public OptionalObj findOptionalObjByDocumentIdandPostObj(Long documentId, String postObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("documentId", documentId);
		map.put("postObj", postObj);
		return (OptionalObj) getSqlMapClientTemplate().queryForObject(
				"optionalObj.findOptionalObjByDocumentIdandPostObj", map);
	}

	@Override
	public List<GroupObj> findGroupObjsByDocumentIdandPostObj(Long documentId, String postObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("documentId", documentId);
		map.put("postObj", postObj);
		return (List<GroupObj>) getSqlMapClientTemplate().queryForList(
				"groupObj.findGroupObjsByDocumentIdandPostObj", map);
	}

	@Override
	public List<GroupObj> findGroupObjsByDocumentIdandDeleteState(Long documentId, Long deleteState) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("documentId", documentId);
		map.put("deleteState", deleteState);
		return (List<GroupObj>) getSqlMapClientTemplate().queryForList(
				"groupObj.findGroupObjsByDocumentIdandDeleteState", map);
	}

	@Override
	public boolean nameExists(Long id, String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", id);
		map.put("name", name);
		return (Integer) getSqlMapClientTemplate().queryForObject("myDepartmentGrop.nameExists",
				map) > 0 ? true : false;
	}

}
