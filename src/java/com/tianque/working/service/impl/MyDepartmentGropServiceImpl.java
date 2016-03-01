package com.tianque.working.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.working.dao.MyDepartmentGropDao;
import com.tianque.working.domain.Document;
import com.tianque.working.domain.GroupObj;
import com.tianque.working.domain.MyDepartmentGrop;
import com.tianque.working.domain.OptionalObj;
import com.tianque.working.service.DispatchDocumentsService;
import com.tianque.working.service.MyDepartmentGropService;

@Service("myDepartmentGropService")
@Transactional
public class MyDepartmentGropServiceImpl extends LogableService implements
		MyDepartmentGropService {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private DispatchDocumentsService dispatchDocumentsService;

	@Autowired
	private MyDepartmentGropDao myDepartmentGropDao;
	MyDepartmentGrop myDepartmentGrop = new MyDepartmentGrop();

	@Override
	public MyDepartmentGrop addMyDepartmentGrop(String name) {
		if (name == null || "".equals(myDepartmentGrop.getName())) {
			throw new BusinessValidationException("群部门名称没有获得");
		}
		if (nameExists(name)) {
			throw new BusinessValidationException("部门群名称重复");
		}
		myDepartmentGrop.setName(name);
		validate(myDepartmentGrop);
		myDepartmentGrop = myDepartmentGropDao
				.addMyDepartmentGrop(myDepartmentGrop);
		return myDepartmentGrop;
	}

	private boolean nameExists(String name) {
		return myDepartmentGropDao.nameExists(ThreadVariable.getUser()
				.getOrganization().getId(), name);
	}

	private void validate(MyDepartmentGrop myDepartmentGrop) {

		myDepartmentGrop.setOrganization(ThreadVariable.getUser()
				.getOrganization());
		autoFillChinesePinyin(myDepartmentGrop);
	}

	private void autoFillChinesePinyin(MyDepartmentGrop myDepartmentGrop) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(myDepartmentGrop.getName());
		myDepartmentGrop.setSimplePinyin(pinyin.get("simplePinyin"));
		myDepartmentGrop.setFullPinyin(pinyin.get("fullPinyin"));
	}

	@Override
	public MyDepartmentGrop findMyDepartmentGropById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("部门群id没有获得");
		}
		return myDepartmentGropDao.findMyDepartmentGropById(id);
	}

	@Override
	public List<MyDepartmentGrop> findMyDepartmentGropForList(Long orgId) {

		return myDepartmentGropDao.findMyDepartmentGropForList(ThreadVariable
				.getUser().getOrganization().getId());
	}

	@Override
	public MyDepartmentGrop updateMyDepartmentGropMembers(Long id,
			String groupMembers) {
		if (id == null) {
			throw new BusinessValidationException("部门群id没有获得");
		}
		return myDepartmentGropDao.updateMyDepartmentGropMembers(id,
				groupMembers);
	}

	@Override
	public MyDepartmentGrop updateMyDepartmentGropName(Long id, String name) {
		if (id == null) {
			throw new BusinessValidationException("部门群id没有获得");
		}
		if (name == null || "".equals(name)) {
			throw new BusinessValidationException("群部门名称没有获得");
		}
		myDepartmentGrop.setId(id);
		myDepartmentGrop.setName(name);
		autoFillChinesePinyin(myDepartmentGrop);

		return myDepartmentGropDao.updateMyDepartmentGropName(myDepartmentGrop);
	}

	@Override
	public List<Organization> findOrgsByInternalIdAndParentId(
			String internalId, Long parentId) {
		String[] internalIds = internalId.split(",");
		Integer orglevel = Integer.valueOf(internalIds[0]) + 10;
		Integer orgtype = Integer.valueOf(internalIds[1]);
		return organizationDubboService.findOrgsByOrgTypeAndOrgLevelAndParentId(
				orgtype, orglevel, parentId);
	}

	@Override
	public List<Organization> findOrgsByorgNameAndParentId(String name,
			int number) {
		List<Organization> organizations = organizationDubboService
				.findOrgsFetchParentOrgByKeyword(name, number);
		if (organizations != null) {
			for (Organization organization : organizations) {
				if (organization != null && organization.getParentOrg() != null
						&& organization.getParentOrg().getId() != null) {
					organization.getParentOrg().setOrgName(
							organizationDubboService.getSimpleOrgById(
									organization.getParentOrg().getId())
									.getOrgName());
				}
			}
		}

		return organizations;
	}

	@Override
	public List<GroupObj> findGroupObjsByGroupId(Long departmentGroupId) {
		return myDepartmentGropDao.findGroupObjsByGroupId(departmentGroupId);
	}

	@Override
	public void deleteMyDepartmentGropById(Long id, boolean exist) {
		if (id == null) {
			throw new BusinessValidationException("部门群id没有获得");
		}

		myDepartmentGropDao.deleteMyDepartmentGropById(id);
		if (exist) {
			updateGroupObjByGroupIdAndDeleteState(id, GroupObj.DELETESTATE_YES);
		}
	}

	@Override
	public void updateGroupObjByGroupIdAndDeleteState(Long id, Long deleteState) {
		if (id == null) {
			throw new BusinessValidationException("部门群id没有获得");
		}
		myDepartmentGropDao.updateGroupObjByGroupIdAndDeleteState(id,
				deleteState);
	}

	@Override
	public List<Organization> findOrganizationByIds(String ids) {
		List<Organization> organizations = new ArrayList<Organization>();
		if (ids != null && !"".equals(ids)) {
			String[] st = ids.split(",");
			for (int i = 0; i < st.length; i++) {
				if (!"".equals(st[i])
						&& organizationDubboService.getSimpleOrgById(Long
								.valueOf(st[i])) != null) {
					organizations.add(organizationDubboService.getSimpleOrgById(Long
							.valueOf(st[i])));
				}
			}
		}

		return organizations;
	}

	@Override
	public List<MyDepartmentGrop> findMyDepartmentsGropByIds(String ids) {
		List<MyDepartmentGrop> myDepartmentGrops = new ArrayList<MyDepartmentGrop>();
		if (ids != null && !"".equals(ids)) {
			String[] st = ids.split(",");
			for (int i = 0; i < st.length; i++) {
				if (findMyDepartmentGropById(Long.valueOf(st[i])) != null) {
					myDepartmentGrops.add(findMyDepartmentGropById(Long
							.valueOf(st[i])));
				}

			}
		}
		return myDepartmentGrops;
	}

	@Override
	public void addOptionalObj(OptionalObj optionalObj) {
		myDepartmentGropDao.addOptionalObj(optionalObj);
	}

	@Override
	public void addGroupObj(GroupObj groupObj) {
		myDepartmentGropDao.addGroupObj(groupObj);
	}

	@Override
	public String getOrgIds(String orgIds, String groupIds) {
		String allOrgIds = "";
		if (orgIds != null && !"".equals(orgIds)) {
			allOrgIds = orgIds + ",";
		}
		if (groupIds != null && !"".equals(groupIds)) {
			String[] st = groupIds.split(",");
			for (int i = 0; i < st.length; i++) {
				if (!"".equals(st[i])) {
					String str = findMyDepartmentGropById(Long.valueOf(st[i]))
							.getGroupMembers();
					if (str != null)
						allOrgIds += str;
				}
			}
		}
		return allOrgIds;
	}

	@Override
	public void updateOptionalObjForOrgIds(Long documentId, String postObj,
			String optionalOrgIds) {
		myDepartmentGropDao.updateOptionalObjForOrgIds(documentId, postObj,
				optionalOrgIds);

	}

	@Override
	public void deleteGroupObjByDocumentId(Long documentId) {
		if (documentId == null) {
			throw new BusinessValidationException("文件id没有获得");
		}
		myDepartmentGropDao.deleteGroupObjByDocumentId(documentId);
	}

	@Override
	public OptionalObj findOptionalObjByDocumentIdandPostObj(Long documentId,
			String postObj) {
		return myDepartmentGropDao.findOptionalObjByDocumentIdandPostObj(
				documentId, postObj);
	}

	@Override
	public String findGroupObjsByDocumentIdandPostObj(Long documentId,
			String postObj) {
		List<GroupObj> groupObjs = new ArrayList<GroupObj>();
		groupObjs = myDepartmentGropDao.findGroupObjsByDocumentIdandPostObj(
				documentId, postObj);
		String st = "";
		for (GroupObj groupObj : groupObjs) {
			st += groupObj.getDepartmentGroupId() + ",";
		}
		return st;
	}

	@Override
	public String judgeDepartmentGropDelete(String ids) {
		if (ids == null || "".equals(ids)) {
			throw new BusinessValidationException("文件id没有获得");
		}
		String existIds = "";
		String documentNames = "";
		String[] st = ids.split(",");
		for (int i = 0; i < st.length; i++) {
			if (st[i] != null
					&& !"".equals(st[i])
					&& findGroupObjsByDocumentIdandDeleteState(
							Long.valueOf(st[i]), GroupObj.DELETESTATE_YES) != null) {
				existIds += st[i];
			}
		}
		if (!"".equals(existIds)) {
			List<Document> documents = dispatchDocumentsService
					.getDocumentsByIds(existIds);
			for (Document document : documents) {
				documentNames += document.getTitle() + ",";
			}
		}
		return documentNames;
	}

	@Override
	public List<GroupObj> findGroupObjsByDocumentIdandDeleteState(
			Long documentId, Long deleteState) {
		if (documentId == null) {
			throw new BusinessValidationException("文件id没有获得");
		}
		if (myDepartmentGropDao.findGroupObjsByDocumentIdandDeleteState(
				documentId, deleteState).isEmpty()) {
			return null;
		}
		return myDepartmentGropDao.findGroupObjsByDocumentIdandDeleteState(
				documentId, deleteState);
	}

	@Override
	public List<Organization> getOrgsForParentIdAndOrgType(String parentId,
			String internalId) {
		String[] internalIds = internalId.split(",");
		Integer orglevel = Integer.valueOf(internalIds[0]);
		Integer orgtype = Integer.valueOf(internalIds[1]);
		if (OrganizationType.FUNCTIONAL_ORG == orgtype) {
			return organizationDubboService.findFunOrgsByFunParentId(Long
					.valueOf(parentId));
		}
		if (OrganizationType.ADMINISTRATIVE_REGION == orgtype) {
			return organizationDubboService.findOrgsByOrgTypeAndOrgLevelAndParentId(
					orgtype, orglevel, Long.valueOf(parentId));
		}
		return null;
	}
}
