package com.tianque.working.service;

import java.util.List;

import com.tianque.domain.Organization;
import com.tianque.working.domain.GroupObj;
import com.tianque.working.domain.MyDepartmentGrop;
import com.tianque.working.domain.OptionalObj;

public interface MyDepartmentGropService {

	public MyDepartmentGrop addMyDepartmentGrop(String name);

	public MyDepartmentGrop findMyDepartmentGropById(Long id);

	public List<MyDepartmentGrop> findMyDepartmentsGropByIds(String ids);

	public List<MyDepartmentGrop> findMyDepartmentGropForList(Long orgId);

	public MyDepartmentGrop updateMyDepartmentGropMembers(Long id, String groupMembers);

	public MyDepartmentGrop updateMyDepartmentGropName(Long id, String name);

	public List<Organization> findOrgsByInternalIdAndParentId(String internalId, Long parentId);

	public List<Organization> findOrgsByorgNameAndParentId(String name, int number);

	public List<GroupObj> findGroupObjsByGroupId(Long departmentGroupId);

	public void deleteMyDepartmentGropById(Long id, boolean exist);

	public void updateGroupObjByGroupIdAndDeleteState(Long id, Long deleteState);

	public List<Organization> findOrganizationByIds(String ids);

	public List<Organization> getOrgsForParentIdAndOrgType(String parentId, String internalId);

	public void addOptionalObj(OptionalObj optionalObj);

	public void updateOptionalObjForOrgIds(Long documentId, String postObj, String optionalOrgIds);

	public OptionalObj findOptionalObjByDocumentIdandPostObj(Long documentId, String postObj);

	public void addGroupObj(GroupObj groupObj);

	public String getOrgIds(String orgIds, String groupIds);

	public void deleteGroupObjByDocumentId(Long documentId);

	public String findGroupObjsByDocumentIdandPostObj(Long documentId, String postObj);

	public List<GroupObj> findGroupObjsByDocumentIdandDeleteState(Long documentId, Long deleteState);

	public String judgeDepartmentGropDelete(String ids);

}
