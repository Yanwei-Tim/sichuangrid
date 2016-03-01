package com.tianque.working.dao;

import java.util.List;

import com.tianque.working.domain.GroupObj;
import com.tianque.working.domain.MyDepartmentGrop;
import com.tianque.working.domain.OptionalObj;

public interface MyDepartmentGropDao {
	public MyDepartmentGrop addMyDepartmentGrop(MyDepartmentGrop myDepartmentGrop);

	public MyDepartmentGrop findMyDepartmentGropById(Long id);

	public List<MyDepartmentGrop> findMyDepartmentGropForList(Long orgId);

	public MyDepartmentGrop updateMyDepartmentGropMembers(Long id, String groupMembers);

	public MyDepartmentGrop updateMyDepartmentGropName(MyDepartmentGrop myDepartmentGrop);

	public void deleteMyDepartmentGropById(Long id);

	public List<GroupObj> findGroupObjsByGroupId(Long departmentGroupId);

	public List<GroupObj> findGroupObjsByDocumentIdandPostObj(Long documentId, String postObj);

	public void updateGroupObjByGroupIdAndDeleteState(Long id, Long deleteState);

	public void addOptionalObj(OptionalObj optionalObj);

	public void updateOptionalObjForOrgIds(Long documentId, String postObj, String optionalOrgIds);

	public OptionalObj findOptionalObjByDocumentIdandPostObj(Long documentId, String postObj);

	public void addGroupObj(GroupObj groupObj);

	public void deleteGroupObjByDocumentId(Long documentId);

	public List<GroupObj> findGroupObjsByDocumentIdandDeleteState(Long documentId, Long deleteState);

	public boolean nameExists(Long id, String name);

}
