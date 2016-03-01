package com.tianque.fourTeams.fourTeamsIssue.approval.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.approval.dao.ApprovalItemDao;
import com.tianque.approval.domain.ApprovalItem;
import com.tianque.approval.domain.ApprovalItemFile;
import com.tianque.approval.domain.property.ApprovalItemStatus;
import com.tianque.approval.domain.vo.ApprovalItemVo;
import com.tianque.approval.service.ApprovalItemFileService;
import com.tianque.approval.service.ItemService;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.fourTeams.fourTeamsIssue.approval.service.FourTeamsApprovalItemService;
import com.tianque.fourTeams.fourTeamsIssue.constants.FourTeamsIssueConstants;
import com.tianque.fourTeams.fourTeamsIssue.constants.FourTeamsIssueTypes;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueService;
import com.tianque.fourTeams.service.impl.FourTeamsIssueBusinessDelegate;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("fourTeamsApprovalItemService")
@Transactional
public class FourTeamsApprovalItemServiceImpl implements
		FourTeamsApprovalItemService {
	public final static Logger logger = LoggerFactory
			.getLogger(AbstractBaseService.class);

	@Qualifier("approvalItemValidator")
	@Autowired
	private DomainValidator<ApprovalItem> approvalItemValidator;
	@Autowired
	private ApprovalItemDao approvalItemDao;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ApprovalItemFileService approvalItemFileService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private FourTeamsIssueService issueService;
	@Autowired
	protected FourTeamsIssueDao issueDao;
	@Autowired
	private FourTeamsIssueBusinessDelegate issueBusinessDelegate;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public ApprovalItem addApprovalItem(ApprovalItem approvalItem,
			String[] attachFiles) {
		approvalItem.setApprovalNumber(getCurrentApprovalItemPrefix(itemService
				.getItemById(approvalItem.getItem().getId()).getItemNumber()));
		ValidateResult baseDataValidator = approvalItemValidator
				.validate(approvalItem);
		if (baseDataValidator != null && baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		approvalItem.setOrgInternalCode(organizationDubboService
				.getFullOrgById(approvalItem.getOrganization().getId())
				.getOrgInternalCode());
		approvalNumberWhetherToRepeat(approvalItem);
		approvalItem.setCreateOrg(ThreadVariable.getOrganization());
		approvalItem.setCreateOrgCode(organizationDubboService.getFullOrgById(
				ThreadVariable.getOrganization().getId()).getOrgInternalCode());
		approvalItem = approvalItemDao.add(approvalItem);
		addApprovalItemAttachment(attachFiles, approvalItem);
		addIssue(approvalItem);

		return approvalItem;
	}

	private void addIssue(ApprovalItem approvalItem) {
		FourTeamsIssueNew issue = getIssue(approvalItem);
		List<ApprovalItemFile> list = approvalItemFileService
				.findApprovalItemFileByApprovalItemId(approvalItem.getId());

		List<FourTeamsIssueAttachFile> files = new ArrayList<FourTeamsIssueAttachFile>();

		if (list != null && !list.isEmpty()) {

			for (ApprovalItemFile approvalItemFile : list) {
				FourTeamsIssueAttachFile issueAttachFile = new FourTeamsIssueAttachFile();
				issueAttachFile.setFileActualUrl(approvalItemFile
						.getFileActualUrl());
				issueAttachFile.setFileName(approvalItemFile.getFileName());
				files.add(issueAttachFile);
			}

		}
		issueService.addIssue(issue, files, null, null, null, null);
	}

	private FourTeamsIssueNew getIssue(ApprovalItem approvalItem) {
		FourTeamsIssueNew issue = new FourTeamsIssueNew();
		issue.setOccurOrg(approvalItem.getOrganization());
		issue.setOccurOrgInternalCode(organizationDubboService.getFullOrgById(
				approvalItem.getOrganization().getId()).getOrgInternalCode());
		issue.setCreateOrg(ThreadVariable.getOrganization());
		issue.setCreateOrgInternalCode(organizationDubboService.getFullOrgById(
				ThreadVariable.getOrganization().getId()).getOrgInternalCode());
		issue.setSerialNumber(approvalItem.getApprovalNumber());
		issue.setSubject(itemService
				.getItemById(approvalItem.getItem().getId()).getItemName());
		issue.setOccurLocation(approvalItem.getCurrentAddress());
		issue.setOccurDate(new Date());
		issue.setRelatePeopleCount(1);
		issue.setIssueTypes(issueTypeService.findEnabledIssueTypesByParentName(
				approvalItem.getOrganization().getId(),
				FourTeamsIssueTypes.ITEM));
		issue.setMainCharacters(approvalItem.getName());
		issue.setIssueContent(approvalItem.getName()
				+ " 申请了 "
				+ itemService.getItemById(approvalItem.getItem().getId())
						.getItemName());
		// issue.setIssueType(true);
		return issue;
	}

	@Override
	public void deleteApprovalItemById(Long id) {
		if (null == id || id < 0) {
			return;
		}
		approvalItemDao.delete(id);
	}

	@Override
	public PageInfo<ApprovalItem> findApprovalItemPage(String searchtxt,
			ApprovalItemVo approvalItemVo, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		return approvalItemDao.findApprovalItemPage(searchtxt, approvalItemVo,
				pageNum, pageSize, sortField, order);
	}

	@Override
	public ApprovalItem getApprovalItemById(Long id) {
		if (null == id || id < 0) {
			return null;
		}
		return approvalItemDao.get(id);
	}

	@Override
	public ApprovalItem updateApprovalItem(ApprovalItem approvalItem,
			String[] attachFiles) {
		ValidateResult baseDataValidator = approvalItemValidator
				.validate(approvalItem);
		if (baseDataValidator != null && baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		if (null == approvalItem.getId()) {
			throw new BusinessValidationException("申请事项不能为空");
		}
		approvalItem.setOrgInternalCode(organizationDubboService
				.getFullOrgById(approvalItem.getOrganization().getId())
				.getOrgInternalCode());
		approvalNumberWhetherToRepeat(approvalItem);
		addApprovalItemAttachment(attachFiles, approvalItem);
		return approvalItemDao.update(approvalItem);
	}

	private void approvalNumberWhetherToRepeat(ApprovalItem approvalItem) {
		ApprovalItem appItem = approvalItemDao
				.getApprovalItemByApprovalNumber(approvalItem
						.getApprovalNumber());
		if (null != appItem && null == approvalItem.getId()) {
			throw new BusinessValidationException("申请事项编号重复");
		}
		if (null != appItem && null != appItem.getId()
				&& !appItem.getId().equals(approvalItem.getId())) {
			throw new BusinessValidationException("申请事项编号重复");
		}
	}

	private void updateApprovalItemFiles(ApprovalItem approvalItem,
			String[] attachFiles) {
		ApprovalItemFile[] approvalItemFiles = convertToApprovalFileInfos(attachFiles);
		if (approvalItemFiles == null || approvalItemFiles.length == 0) {
			deleteApprovalItemFileByItemId(findApprovalItemFiles(approvalItem));
		}
		List<ApprovalItemFile> oldList = approvalItemFileService
				.findApprovalItemFileByApprovalItemId(approvalItem.getId());
		if (!oldList.isEmpty()) {
			List<ApprovalItemFile> newList = new ArrayList<ApprovalItemFile>(
					Arrays.asList(approvalItemFiles));
			for (ApprovalItemFile file : oldList) {
				if (newList.contains(file)) {
					newList.remove(file);
				} else {
					approvalItemFileService.deleteApprovalItemFileById(file
							.getId());
				}
			}
			approvalItemFiles = convertToApprovalItemAttachFiles(approvalItem,
					newList);
		}
	}

	@Override
	public List<ApprovalItem> findApprovalItemByItemId(Long itemId) {
		return approvalItemDao.findApprovalItemByItemId(itemId);
	}

	private String getCurrentApprovalItemPrefix(String itemNumber) {
		return CalendarUtil.format("yyMMddHHmmss", new Date()) + itemNumber;
	}

	private void addApprovalItemAttachment(String[] fileName,
			ApprovalItem approvalItem) {
		Map<String, Long> map = findApprovalItemFiles(approvalItem);
		if (null != fileName && fileName.length != 0) {
			ApprovalItemFile approvalItemFile = null;
			StoredFile sf = null;
			for (int i = 0; i < fileName.length; i++) {
				// id是否存在，存在则获取
				if (!StringUtil.isStringAvaliable(fileName[i].substring(0,
						fileName[i].indexOf(",")))) {
					approvalItemFile = new ApprovalItemFile();
					sf = FileUtil
							.copyTmpFileToStoredFile(fileName[i]
									.substring(fileName[i].indexOf(",") + 1),
									GridProperties.ITEM_ATTACHFILE);
					approvalItemFile.setFileActualUrl(sf.getFullName());
					approvalItemFile.setFileName(fileName[i]
							.substring(fileName[i].indexOf(",") + 1));
					approvalItemFile.setApprovalItem(approvalItem);
					approvalItemFileService
							.addApprovalItemFile(approvalItemFile);
				} else {
					map.remove(fileName[i].substring(fileName[i].indexOf(",") + 1));
				}
			}
		}
		deleteApprovalItemFileByItemId(map);
	}

	private ApprovalItemFile[] convertToApprovalItemAttachFiles(
			ApprovalItem approvalItem, List<ApprovalItemFile> files) {
		ApprovalItemFile[] result = new ApprovalItemFile[files.size()];
		if (files == null || files.size() == 0)
			return null;
		for (int index = 0; index < files.size(); index++) {
			// 如果file[index] 为空 直接返回
			if (files.get(index) == null)
				continue;
			result[index] = convertToApprovalItemAttachFile(files.get(index));
			result[index].setApprovalItem(approvalItem);
		}
		return result;
	}

	private ApprovalItemFile convertToApprovalItemAttachFile(
			ApprovalItemFile info) {
		ApprovalItemFile file = new ApprovalItemFile();
		if (info.getId() != null) {
			file.setId(info.getId());
			file.setFileName(info.getFileName());
		} else {
			StoredFile sf = FileUtil.copyTmpFileToStoredFile(
					info.getFileName(), GridProperties.ITEM_ATTACHFILE);
			file.setFileActualUrl(sf.getFullName());
			file.setFileName(info.getFileName());
		}
		return file;
	}

	private ApprovalItemFile[] convertToApprovalFileInfos(String[] fileInfos) {
		if (null == fileInfos || fileInfos.length == 0) {
			return null;
		} else {
			ApprovalItemFile[] result = new ApprovalItemFile[fileInfos.length];
			for (int index = 0; index < fileInfos.length; index++) {
				result[index] = convertToApprovalFileInfo(fileInfos[index]);
			}
			return result;
		}
	}

	private ApprovalItemFile convertToApprovalFileInfo(String file) {
		ApprovalItemFile info = new ApprovalItemFile();
		// id是否存在，存在则获取
		String idStr = file.substring(0, file.indexOf(","));
		if (StringUtil.isStringAvaliable(idStr)) {
			info.setId(Long.valueOf(idStr));
		}
		info.setFileName(file.substring(file.indexOf(",") + 1));
		return info;
	}

	private Map<String, Long> findApprovalItemFiles(ApprovalItem approvalItem) {
		List<ApprovalItemFile> list = approvalItemFileService
				.findApprovalItemFileByApprovalItemId(approvalItem.getId());
		Map<String, Long> map = new HashMap<String, Long>();
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map.put(list.get(i).getFileName(), list.get(i).getId());
			}
		}
		return map;
	}

	private void deleteApprovalItemFileByItemId(Map<String, Long> map) {
		Set<String> key = map.keySet();
		ApprovalItemFile approvalItemFile = null;
		for (Iterator it = key.iterator(); it.hasNext();) {
			approvalItemFile = approvalItemFileService
					.getApprovalItemFileById(map.get(it.next()));
			if (approvalItemFile != null) {
				approvalItemFileService
						.deleteApprovalItemFileById(approvalItemFile.getId());
				deleteFile(approvalItemFile.getFileActualUrl());
			}
		}
	}

	private void deleteFile(String sPath) {
		String downFilePath = FileUtil.getWebRoot() + File.separator + sPath;
		File file = new File(downFilePath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			try {
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void complete(String approvalNumber) {
		if (null == approvalNumber) {
			return;
		}
		approvalItemDao.updateApprovalItemByApprovalNumber(
				ApprovalItemStatus.HAVE_GONE_THROUGH, approvalNumber);
	}

	@Override
	public void approval(Long id, FourTeamsIssueLogNew issueLog, Long approval) {
		ApprovalItem approvalItem = approvalItemDao.get(id);

		FourTeamsIssueNew issueNew = issueService
				.getIssueBySerialNumber(approvalItem.getApprovalNumber());
		appendOperationLog(issueNew, issueLog, (approval == 1 ? "审核通过"
				: "审核未通过"));

		if (null != approval && approval == 1L) {
			approvalItemDao.updateApprovalItemByApprovalNumber(
					ApprovalItemStatus.IN_PROCESS,
					approvalItem.getApprovalNumber());
		} else {
			approvalItemDao.updateApprovalItemByApprovalNumber(
					ApprovalItemStatus.AUDIT_DOES_NOT_PASS,
					approvalItem.getApprovalNumber());
		}
	}

	private void appendOperationLog(FourTeamsIssueNew issue,
			FourTeamsIssueLogNew log, String desc) {
		if (log.getTargeOrg() != null) {
			Organization target = organizationDubboService.getSimpleOrgById(log
					.getTargeOrg().getId());
			log.setTargeOrg(target);
			log.setTargeOrgInternalCode(target.getOrgInternalCode());
		}
		log.setDealDescription(desc);
		log.setDealOrg(ThreadVariable.getSession().getOrganization());
		log.setDealTime(CalendarUtil.now());
		log.setDealUserName(ThreadVariable.getUser().getName());
		log.setIssue(issue);
		log.setMobile(ThreadVariable.getUser().getMobile());
		log.setCreateUser(ThreadVariable.getUser().getUserName());
		log.setCreateDate(new Date());
		issueBusinessDelegate.addIssueLog(log);
	}

	@Override
	public ApprovalItem getApprovalItemByApprovalNumber(String approvalNumber) {
		if (approvalNumber == null || "".equals(approvalNumber)) {
			throw new BusinessValidationException("编号不能为空！");
		}
		return approvalItemDao.getApprovalItemByApprovalNumber(approvalNumber);
	}

	@Override
	public ApprovalItem approvalItemToIssue(FourTeamsIssueNew issue,
			ApprovalItem approvalItem, String[] attachFiles) {
		updateIssue(approvalItem, issue);
		FourTeamsIssueNew postIssue = issueDao.updateIssue(issue);
		FourTeamsIssueNew existIssue = issueDao.getFullIssueById(issue.getId());
		List<FourTeamsIssueAttachFile> files = createIssueAttachFile(attachFiles);
		issueService.addIssueAttachFiles(postIssue, existIssue, files);
		updateApprovalItem(getApprovalItemByIssue(existIssue, approvalItem));
		updateApprovalItemFiles(getApprovalItemByIssue(issue, approvalItem),
				attachFiles);
		return approvalItem;
	}

	private ApprovalItem getApprovalItemByIssue(FourTeamsIssueNew issue,
			ApprovalItem approvalItem) {
		approvalItem.setName(issue.getMainCharacters());
		approvalItem.setOrganization(issue.getOccurOrg());
		approvalItem.setOrgInternalCode(issue.getOccurOrgInternalCode());
		return approvalItem;
	}

	private List<FourTeamsIssueAttachFile> createIssueAttachFile(
			String[] fileNameAndIdS) {

		if (fileNameAndIdS == null) {
			return new ArrayList<FourTeamsIssueAttachFile>();
		}

		List<FourTeamsIssueAttachFile> list = new ArrayList<FourTeamsIssueAttachFile>();

		for (String fileNameAndId : fileNameAndIdS) {
			if (StringUtil.isStringAvaliable(fileNameAndId)) {

				String[] id_fileName = fileNameAndId.split(",");

				String id = id_fileName[0];

				FourTeamsIssueAttachFile issueAttachFile = new FourTeamsIssueAttachFile();

				if (StringUtil.isStringAvaliable(id)) {

					issueAttachFile = issueService.getIssueAttachFileById(Long
							.parseLong(id));

				} else {

					StoredFile sf = FileUtil.copyTmpFileToStoredFile(
							id_fileName[1], GridProperties.ISSUE_ATTACHFILE);

					issueAttachFile.setFileActualUrl(sf.getFullName());

					issueAttachFile.setFileName(id_fileName[1]);
				}
				list.add(issueAttachFile);
			}
		}
		return list;
	}

	@Override
	public ApprovalItem auditItemPassToIssue(FourTeamsIssueNew issue,
			ApprovalItem approvalItem, String[] attachFiles) {
		updateIssue(approvalItem, issue);
		FourTeamsIssueNew postIssue = issueDao.updateIssue(issue);
		FourTeamsIssueNew existIssue = issueDao.getFullIssueById(issue.getId());
		List<FourTeamsIssueAttachFile> files = createIssueAttachFile(attachFiles);
		issueService.addIssueAttachFiles(postIssue, existIssue, files);
		updateApprovalItem(getApprovalItemByIssue(existIssue, approvalItem));
		updateApprovalItemFiles(getApprovalItemByIssue(issue, approvalItem),
				attachFiles);
		return approvalItem;
	}

	private void updateApprovalItem(ApprovalItem approvalItem) {
		ValidateResult baseDataValidator = approvalItemValidator
				.validate(approvalItem);
		if (baseDataValidator != null && baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		if (null == approvalItem.getId()) {
			throw new BusinessValidationException("申请事项不能为空");
		}
		approvalItem.setOrgInternalCode(organizationDubboService
				.getFullOrgById(approvalItem.getOrganization().getId())
				.getOrgInternalCode());
		approvalNumberWhetherToRepeat(approvalItem);
		approvalItemDao.update(approvalItem);
	}

	private FourTeamsIssueNew updateIssue(ApprovalItem approvalItem,
			FourTeamsIssueNew issue) {
		if (issue.getOccurOrg() != null) {
			issue.setOccurOrg(organizationDubboService.getFullOrgById(issue
					.getOccurOrg().getId()));
			issue.setOccurOrgInternalCode(organizationDubboService
					.getFullOrgById(issue.getOccurOrg().getId())
					.getOrgInternalCode());
		} else {
			issue.setOccurOrgInternalCode(organizationDubboService
					.getFullOrgById(approvalItem.getOrganization().getId())
					.getOrgInternalCode());
		}
		issue.setCreateOrg(ThreadVariable.getOrganization());
		issue.setCreateOrgInternalCode(organizationDubboService.getFullOrgById(
				ThreadVariable.getOrganization().getId()).getOrgInternalCode());
		issue.setSerialNumber(approvalItem.getApprovalNumber());
		issue.setIssueTypes(issueTypeService.findEnabledIssueTypesByParentName(
				approvalItem.getOrganization().getId(),
				FourTeamsIssueTypes.ITEM));

		// issue.setIssueType(true);
		// 设置事件的来源方式
		issue.setSourceKind(propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.SOURCE_KIND,
						FourTeamsIssueConstants.APPROVAL_INPUT));
		return issue;
	}

	@Override
	public FourTeamsIssueNew getIssueBySerialNumber(String approvalNumber) {
		return issueService.getIssueBySerialNumber(approvalNumber);
	}

	@Override
	public void deleteApprovalItemByApprovalNumber(String approvalNumber) {
		ApprovalItem approvalItem = getApprovalItemByApprovalNumber(approvalNumber);
		if (approvalItem != null) {
			approvalItemFileService
					.deleteApprovalItemFileByApprovalItemId(approvalItem
							.getId());
			approvalItemDao.deleteApprovalItemByApprovalNumber(approvalNumber);
		}

	}
}
