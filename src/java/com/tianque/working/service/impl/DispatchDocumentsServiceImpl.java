package com.tianque.working.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.DateUtil;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.PageInfoUtil;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.factory.PlatformMessageFactory;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.resourcePool.domain.MyProfile;
import com.tianque.resourcePool.domain.MyProfileAttachFile;
import com.tianque.resourcePool.help.ParseToMyProfile;
import com.tianque.resourcePool.service.MyProfileService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.ParametersConvertUtil;
import com.tianque.viewObject.service.ViewObjectService;
import com.tianque.working.constants.DocumentsConstants;
import com.tianque.working.dao.DispatchDocumentsDao;
import com.tianque.working.domain.Document;
import com.tianque.working.domain.DocumentSignVo;
import com.tianque.working.domain.DocumentState;
import com.tianque.working.domain.DocumentsHasAttachFiles;
import com.tianque.working.domain.DocumentsHasOrg;
import com.tianque.working.domain.DocumentsHasUser;
import com.tianque.working.domain.OptionalObj;
import com.tianque.working.domain.SearchDocumentVo;
import com.tianque.working.domain.UserHasDocuments;
import com.tianque.working.service.DispatchDocumentsService;
import com.tianque.working.service.MyDepartmentGropService;

@Transactional
@Service("dispatchDocumentsService")
public class DispatchDocumentsServiceImpl implements DispatchDocumentsService {
	public final static Logger logger = LoggerFactory
			.getLogger(AbstractBaseService.class);
	@Autowired
	private ViewObjectService viewObjectService;
	@Autowired
	private DispatchDocumentsDao dispatchDocumentsDao;
	@Autowired
	private PlatformMessageService platformaMessageService;
	@Autowired
	private MyProfileService myProfileService;
	@Autowired
	private PlatformMessageFactory platformMessageFactory;
	@Autowired
	private MyDepartmentGropService myDepartmentGropService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	// 列表
	@Override
	public PageInfo finddispatchDocumentsList(Document document, Integer page,
			Integer rows, String sidx, String sord) {
		PageInfo pageInfo = null;
		try {
			document.setUserId(ThreadVariable.getUser().getId());
			document.setOrgId(ThreadVariable.getSession().getOrganization()
					.getId());
			pageInfo = dispatchDocumentsDao.finddispatchDocumentsList(document,
					page, rows, sidx, sord);
		} catch (Exception e) {
			throw new ServiceValidationException("异常信息", e);
		}
		return pageInfo;
	}

	// // 新增String
	// @Override
	// public Document adddispatchDocuments(Document document,
	// DocumentsHasAttachFiles[] files, String sendObjCacheId,
	// String copySendObjCacheId) {
	// Boolean flag = Boolean.FALSE;
	// List<DocumentsHasAttachFiles> fileList = new
	// ArrayList<DocumentsHasAttachFiles>();
	// try {
	// // TODO 对于转发的文件是有id，先这么处理
	// if (null != document.getId()) {
	// flag = true;
	// fileList = this.getDocfilesByDocumentId(document.getId());
	// }
	// Map<String, String> sendObjCacheMap = new HashMap<String, String>();
	// fillProperties(document, files, sendObjCacheId, copySendObjCacheId,
	// sendObjCacheMap);
	// Document savedoc = dispatchDocumentsDao
	// .adddispatchDocuments(document);
	//
	// saveOptionalObj(sendObjCacheMap.get("sendOrgIds"), savedoc.getId(),
	// DocumentState.SEND);
	// addGroupObj(sendObjCacheMap.get("sendGroupIds"), savedoc.getId(),
	// DocumentState.SEND);
	//
	// saveOptionalObj(sendObjCacheMap.get("copyOrgIds"), savedoc.getId(),
	// DocumentState.COPYSEND);
	// addGroupObj(sendObjCacheMap.get("copyGroupIds"), savedoc.getId(),
	// DocumentState.COPYSEND);
	// if (files != null) {
	// validateAttachFiles(files);
	// if (flag) {
	// dispatchDocumentsDao
	// .addDocAttachFiles(convertToDocAttachFilesFortransmit(
	// savedoc, fileList));
	// } else {
	// dispatchDocumentsDao
	// .addDocAttachFiles(convertToDocAttachFiles(savedoc,
	// files));
	// }
	// }
	// return savedoc;
	// } catch (Exception e) {
	// throw new ServiceValidationException("公文保存到数据库出错了", e);
	// }
	// }

	// private void saveOptionalObj(String sendOptrionalObjIds, Long id,
	// String postObj) {
	// if (null != sendOptrionalObjIds && !sendOptrionalObjIds.isEmpty()) {
	// OptionalObj optionalObj = new OptionalObj();
	// optionalObj.setOptionalOrgIds(sendOptrionalObjIds);
	// optionalObj.setPostObj(postObj);
	// optionalObj.setDocumentId(id);
	// myDepartmentGropService.addOptionalObj(optionalObj);
	// }
	// }
	//
	// private void addGroupObj(String sendDepartmentGroupIds, Long id,
	// String postObj) {
	// if (null != sendDepartmentGroupIds && !sendDepartmentGroupIds.isEmpty())
	// {
	// String[] st = sendDepartmentGroupIds.split(",");
	// for (int i = 0; i < st.length; i++) {
	// GroupObj groupObj = new GroupObj();
	// groupObj.setDepartmentGroupId(Long.valueOf(st[i]));
	// groupObj.setDeleteState(GroupObj.DELETESTATE_NO);
	// groupObj.setPostObj(postObj);
	// groupObj.setDocumentId(id);
	// myDepartmentGropService.addGroupObj(groupObj);
	// }
	// }
	// }

	private DocumentsHasAttachFiles[] convertToDocAttachFilesFortransmit(
			Document savedoc, List<DocumentsHasAttachFiles> files) {
		DocumentsHasAttachFiles[] result = new DocumentsHasAttachFiles[files
				.size()];
		if (files == null || files.size() == 0)
			return null;
		for (int index = 0; index < files.size(); index++) {
			// 如果file[index] 为空 直接返回
			if (files.get(index) == null)
				continue;

			result[index] = files.get(index);
			result[index].setDocumentId(savedoc.getId());
		}
		return result;
	}

	/***
	 * 绑定公文与用户关系方法封装
	 */
	public void bingdingDocAndUser(Document document, String[] userIds,
			Long sendType) {
		Document historyDocument = dispatchDocumentsDao
				.getDocumentsByIdAndUserId(document.getId(), ThreadVariable
						.getUser().getId());
		UserHasDocuments userHasDocuments = new UserHasDocuments();
		userHasDocuments.setDocumentId(document.getId());
		userHasDocuments.setIsSign(DocumentsConstants.noSign);
		userHasDocuments.setPostObj(sendType);
		userHasDocuments.setReadState(DocumentsConstants.notRead);
		if (historyDocument != null) {
			if (StringUtil.isStringAvaliable(historyDocument
					.getApprovalOpinion())
					&& StringUtil.isStringAvaliable(document
							.getApprovalOpinion())) {
				StringBuffer sb = new StringBuffer();
				sb.append(historyDocument.getApprovalOpinion());
				sb.append(DocumentsConstants.TEXTAREA_WRAP);
				sb.append(ThreadVariable.getUser().getName() + "："
						+ document.getApprovalOpinion());
				userHasDocuments.setApprovalOpinion(sb.toString());
			} else {
				if (StringUtil.isStringAvaliable(document.getApprovalOpinion())) {
					userHasDocuments.setApprovalOpinion(ThreadVariable
							.getUser().getName()
							+ "："
							+ document.getApprovalOpinion());
				}
			}
		}
		for (String userId : userIds) {
			// 先查询该用户是否已经有公文信息
			Integer isHas = dispatchDocumentsDao.countUserHasDocument(
					document.getId(), Long.parseLong(userId));
			if (isHas == null || isHas == 0) {
				userHasDocuments.setUserId(Long.parseLong(userId));
				dispatchDocumentsDao.addUserHasDocument(userHasDocuments);// 绑定公文与收件人关系
				sendPlatformMessage(document, userId);// 发送消息提醒
			}
		}
	}

	// 发文
	@Override
	public void sendDocuments(Long[] ids, String mode) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("未获得发送的公文数据");
		}
		try {
			for (Long id : ids) {
				Document document = dispatchDocumentsDao.getDocumentsById(id);
				String sendUserIds = document.getSendUserIds();
				String copyUserIds = document.getCopyUserIds();
				if (sendUserIds != null && sendUserIds.length() != 0) {
					String[] userIds = sendUserIds.split(",");
					bingdingDocAndUser(document, userIds,
							DocumentsConstants.mainSend);
				}

				if (copyUserIds != null && copyUserIds.length() != 0) {
					String[] userIds = copyUserIds.split(",");
					bingdingDocAndUser(document, userIds,
							DocumentsConstants.copySend);
				}

				// 把文件的状态改为已发送
				document.setDispatchState(DocumentState.SENDED);
				document.setDispatchDate(new Date());
				dispatchDocumentsDao.updateDocDispatchState(document);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("异常信息", e);
		}
	}

	// 删除发文
	@Override
	public void deleteDispatchDocById(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("未获得删除的公文数据");
		}
		try {
			for (Long id : ids) {
				Document document = dispatchDocumentsDao.getDocumentsById(id);
				if (document != null) {
					if (DocumentState.UNSENDED.equalsIgnoreCase(document
							.getDispatchState())) {
						dispatchDocumentsDao.deleteDispatchDocById(id);
						dispatchDocumentsDao.deleteAttachFiles(id);
						dispatchDocumentsDao.deleteDocumentsHasAttachFiles(id);
					} else {
						throw new BusinessValidationException("已发送或者转发的文件不允许删除");
					}

				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException("异常信息", e);
		}
	}

	// 获取发文信息
	@Override
	public Document getDocumentsById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("未得到公文的查询信息");
		}
		try {
			return dispatchDocumentsDao.getDocumentsById(id);
		} catch (Exception e) {
			throw new ServiceValidationException("异常信息", e);
		}
	}

	// 根据发文id获取附件
	@Override
	public List<DocumentsHasAttachFiles> getDocfilesByDocumentId(Long id) {
		List<DocumentsHasAttachFiles> docHasAttachFiles = null;
		try {
			docHasAttachFiles = dispatchDocumentsDao
					.getDocfilesByDocumentId(id);
		} catch (Exception e) {
			throw new ServiceValidationException("异常信息", e);
		}
		return docHasAttachFiles;
	}

	// 根据文件本省的id查找附件实现下载附件的功能
	@Override
	public DocumentsHasAttachFiles getDocfilesByAttachFileId(Long id) {
		DocumentsHasAttachFiles docHasAttachFiles = null;
		try {
			docHasAttachFiles = dispatchDocumentsDao.getDocfilesById(id);
		} catch (Exception e) {
			throw new ServiceValidationException("异常信息", e);
		}
		return docHasAttachFiles;
	}

	// 查询、快速过滤
	@Override
	public PageInfo<Document> searchDispatchDocuments(
			SearchDocumentVo searchDocumentVo, Integer page, Integer rows,
			String sidx, String sord) {
		PageInfo<Document> pageInfo = null;
		try {
			setProperties(searchDocumentVo);
			pageInfo = dispatchDocumentsDao.searchDispatchDocuments(
					searchDocumentVo, page, rows, sidx, sord);
		} catch (Exception e) {
			throw new ServiceValidationException("异常信息", e);
		}
		return pageInfo;
	}

	// 全部公文查询、快速过滤
	@Override
	public PageInfo<Document> searchAllDocuments(
			SearchDocumentVo searchDocumentVo, Integer page, Integer rows,
			String sidx, String sord) {
		PageInfo<Document> pageInfo = null;
		try {
			pageInfo = dispatchDocumentsDao.searchAllDocuments(
					searchDocumentVo, page, rows, sidx, sord);
		} catch (Exception e) {
			throw new ServiceValidationException("异常信息", e);
		}
		return pageInfo;
	}

	// 签收详细列表 ----该方法未使用
	@Override
	public PageInfo findSignerInfoList(DocumentsHasOrg documentsHasOrg,
			Integer page, Integer rows, String sidx, String sord) {
		PageInfo<DocumentsHasOrg> pageInfo = null;
		Long orgLevel = null;
		Long orgType = null;
		List<Organization> orgList = null;
		try {
			if (!documentsHasOrg.getOrgStatus().isEmpty()) {
				int level = Integer.parseInt(documentsHasOrg.getOrgStatus()
						.split("-")[0]);
				int Type = Integer.parseInt(documentsHasOrg.getOrgStatus()
						.split("-")[1]);
				orgLevel = propertyDictService
						.findPropertyDictByDomainNameAndInternalId(
								PropertyTypes.ORGANIZATION_LEVEL, level).get(0)
						.getId();
				orgType = propertyDictService
						.findPropertyDictByDomainNameAndInternalId(
								PropertyTypes.ORGANIZATION_TYPE, Type).get(0)
						.getId();
				if (orgLevel != null || orgType != null) {
					OrganizationVo organizationVo = new OrganizationVo();
					organizationVo.setOrgLevelId(orgLevel);
					organizationVo.setOrgTypeId(orgType);
					orgList = organizationDubboService
							.findNameAndRemarkBySearchVo(organizationVo);
					if (orgList != null && orgList.size() > 0) {
						documentsHasOrg.setOrgIdsList(ParametersConvertUtil
								.convertToListString(orgList));
					} else {
						documentsHasOrg.setOrgIdsList(ParametersConvertUtil
								.nullStringList());
					}
				}
			}
			pageInfo = dispatchDocumentsDao.findSignerInfoList(documentsHasOrg,
					orgLevel, orgType, page, rows, sidx, sord);
			if (orgList == null || pageInfo.getTotalRowSize() == 0) {
				return pageInfo;
			}
			for (int i = 0; i < pageInfo.getResult().size(); i++) {
				for (Organization org : orgList) {
					if (org.getId().equals(
							pageInfo.getResult().get(i).getOrganizationId())) {
						pageInfo.getResult().get(i)
								.setOrgName(org.getOrgName());
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException("异常信息", e);
		}
		return pageInfo;
	}

	// 签收详细列表 ----该方法未使用
	@Override
	public PageInfo findReaderList(DocumentsHasUser documentsHasUser,
			Integer page, Integer rows, String sidx, String sord) {
		PageInfo pageInfo = null;
		try {
			Organization org = organizationDubboService
					.getSimpleOrgById(documentsHasUser.getOrganizationId());
			List<User> users = permissionService
					.findUsersByOrgId(documentsHasUser.getOrganizationId());
			if (users == null || users.size() < 1) {
				return PageInfoUtil.emptyPage(rows);
			}
			documentsHasUser.setOrgName(org.getOrgName());
			pageInfo = dispatchDocumentsDao.findReaderList(users,
					documentsHasUser, page, rows, sidx, sord);
		} catch (Exception e) {
			throw new ServiceValidationException("异常信息", e);
		}
		return pageInfo;
	}

	@Override
	public List<MyProfile> synchToMyProfile(String ids, Long sendMessage,
			Long resourcePoolTypeId, String setPermissionCacheId) {
		if (ids == null || "".equals(ids)) {
			throw new BusinessValidationException("文件id没有获得");
		}
		if (resourcePoolTypeId == null) {
			throw new BusinessValidationException("没有设置文件存放的目录");
		}
		List<MyProfile> myProfiles = new ArrayList<MyProfile>();
		String[] st = ids.split(",");
		for (String id : st) {
			Document doc = new Document();
			MyProfile myProfile = new MyProfile();
			doc = getDocumentsById(Long.valueOf(id));
			myProfile = ParseToMyProfile.parse(doc, resourcePoolTypeId,
					sendMessage);
			List<DocumentsHasAttachFiles> docfiles = getDocfilesByDocumentId(Long
					.valueOf(id));
			List<MyProfileAttachFile> myProfileAttachFile = new ArrayList<MyProfileAttachFile>();
			if (docfiles != null) {
				for (DocumentsHasAttachFiles docfile : docfiles) {
					MyProfileAttachFile file = new MyProfileAttachFile();
					file.setFileName(docfile.getFileName());
					file.setFileActualUrl(docfile.getFileActualUrl());
					file.setMyPrifile(myProfile);
					myProfileAttachFile.add(file);
				}
				myProfile.setMyProfileAttachFile(myProfileAttachFile);
			}
			myProfiles.add(myProfile);

			// 把公文的同步状态改为true
			if (true != doc.getSynchroDocs()) {
				dispatchDocumentsDao.updateDocSynchroDocs(Long.valueOf(id),
						DocumentState.SYNCHRODOCS);
			}

		}
		myProfileService.synchToMyProfile(ids, myProfiles, sendMessage,
				resourcePoolTypeId, setPermissionCacheId);
		return myProfiles;
	}

	@Override
	public List<Document> getDocumentsByIds(String ids) {
		if (ids == null || "".equals(ids)) {
			throw new BusinessValidationException("文件查询参数没有获得");
		}
		List<Document> myProfiles = new ArrayList<Document>();
		String[] st = ids.split(",");
		for (String id : st) {
			myProfiles.add(getDocumentsById(Long.valueOf(id)));
		}
		return myProfiles;
	}

	// 方法应该未使用
	@Override
	public void urgeDocuments(Long[] organizationIds, Document document) {
		try {
			document = getDocumentsById(document.getId());
			String userIds = document.getSendUserIds();
			String copyUserIds = document.getCopyUserIds();
			if (StringUtil.isStringAvaliable(userIds)) {
				String[] userId = userIds.split(",");
				for (String id : userId) {
					sendPlatformMessage(document, id);
				}
			}
			if (StringUtil.isStringAvaliable(copyUserIds)) {
				String[] copyUserId = copyUserIds.split(",");
				for (String id : copyUserId) {
					sendPlatformMessage(document, id);
				}
			}
		} catch (Exception e) {
			logger.info("文件签收提醒出现异常：" + e);
			throw new ServiceValidationException("文件签收提醒出现异常", e);
		}
	}

	private ValidateResult validateAttachFiles(DocumentsHasAttachFiles[] files) {
		ValidateResult result = new ValidateResult();
		if (files != null && files.length > 10) {
			result.addErrorMessage("发送公文附件最多只能上传10个!");
		}
		return result;
	}

	private DocumentsHasAttachFiles[] convertToDocAttachFiles(
			Document document, DocumentsHasAttachFiles[] files)
			throws Exception {
		DocumentsHasAttachFiles[] result = new DocumentsHasAttachFiles[files.length];
		try {
			if (files == null || files.length == 0) {
				return null;
			}
			for (int index = 0; index < files.length; index++) {
				// 如果file[index] 为空 直接返回
				if (files[index] == null) {
					continue;
				}
				result[index] = convertToDocAttachFile(files[index]);
				result[index].setDocumentId(document.getId());
			}
		} catch (Exception e) {
			logger.info("附件转换异常" + e);
		}
		return result;
	}

	private DocumentsHasAttachFiles convertToDocAttachFile(
			DocumentsHasAttachFiles info) throws Exception {
		DocumentsHasAttachFiles file = new DocumentsHasAttachFiles();
		if (info.getFileId() != null) {
			file.setId(info.getFileId());
			file.setFileName(info.getFileName());
		} else {
			StoredFile sf = FileUtil.copyTmpFileToStoredFile(
					info.getFileName(), GridProperties.DOCUMENTS_ATTACHFILE);
			file.setFileActualUrl(sf.getFullName());
			file.setFileName(info.getFileName());
		}
		return file;
	}

	private void autoFillChinesePinyin(Document document) {
		if (document.getTitle() != null) {
			Map<String, String> map = Chinese2pinyin
					.changeChinese2Pinyin(document.getTitle());
			document.setSimplePinyin(map.get("simplePinyin"));
			document.setFullPinyin(map.get("fullPinyin"));
		}
	}

	/***
	 * 设置属性
	 * 
	 * @param document
	 * @param files
	 */
	public void fillPropertics(Document document,
			DocumentsHasAttachFiles[] files, String userIdList,
			String copyUserIdList) {
		document.setSendUserIds(userIdList);
		document.setCopyUserIds(copyUserIdList);
		if (null == files) {
			document.setAttachFiles(DocumentState.NOTATTACHFILES);
		} else {
			document.setAttachFiles(DocumentState.ATTACHFILES);
		}
		document.setDispatchState(DocumentState.UNSENDED);
		document.setSynchroDocs(DocumentState.synchroDocs);
		document.setOrgId(ThreadVariable.getOrganization().getId());
		document.setUserId(ThreadVariable.getUser().getId());
		autoFillChinesePinyin(document);
	}

	@Override
	public Document updateDocument(String userIdList, String copyUserIdList,
			Document document, DocumentsHasAttachFiles[] files) {
		if (!document.getDispatchState().equalsIgnoreCase(
				DocumentState.UNSENDED)) {
			throw new BusinessValidationException("已发送或者转发的文件不允许修改");
		}
		try {
			fillPropertics(document, files, userIdList, copyUserIdList);
			// 第一步 保存发文
			Document updatedoc = dispatchDocumentsDao.updateDocument(document);
			// 保存公文附件与用户关系
			updateDocumentAttachFiles(updatedoc, files);

		} catch (Exception e) {
			throw new ServiceValidationException("修改发文失败", e);

		}

		return this.getDocumentsById(document.getId());
	}

	public boolean hasRecordOptionalObj(Long documentId, String postObj) {
		OptionalObj optionalObj = myDepartmentGropService
				.findOptionalObjByDocumentIdandPostObj(documentId, postObj);
		if (optionalObj != null) {
			return true;
		}
		return false;
	}

	// private void updateOrgIds(Document updatedoc, Map<String, String> map) {
	// // saveOptionalObj(sendObjCacheMap.get("sendOrgIds"), savedoc.getId(),
	// // DocumentState.SEND);
	// // addGroupObj(sendObjCacheMap.get("sendGroupIds"), savedoc.getId(),
	// // DocumentState.SEND);
	// //
	// // saveOptionalObj(sendObjCacheMap.get("copyOrgIds"), savedoc.getId(),
	// // DocumentState.COPYSEND);
	// // addGroupObj(sendObjCacheMap.get("copyGroupIds"), savedoc.getId(),
	// // DocumentState.COPYSEND);
	//
	// if (hasRecordOptionalObj(updatedoc.getId(), DocumentState.SEND)) {
	// myDepartmentGropService.updateOptionalObjForOrgIds(
	// updatedoc.getId(), DocumentState.SEND,
	// map.get("sendOrgIds"));
	// } else {
	// saveOptionalObj(map.get("sendOrgIds"), updatedoc.getId(),
	// DocumentState.SEND);
	// }
	// if (hasRecordOptionalObj(updatedoc.getId(), DocumentState.COPYSEND)) {
	// myDepartmentGropService.updateOptionalObjForOrgIds(
	// updatedoc.getId(), DocumentState.COPYSEND,
	// map.get("copyOrgIds"));
	// } else {
	// saveOptionalObj(map.get("copyOrgIds"), updatedoc.getId(),
	// DocumentState.COPYSEND);
	// }
	//
	// myDepartmentGropService.deleteGroupObjByDocumentId(updatedoc.getId());
	// addGroupObj(map.get("sendGroupIds"), updatedoc.getId(),
	// DocumentState.SEND);
	// addGroupObj(map.get("copyGroupIds"), updatedoc.getId(),
	// DocumentState.COPYSEND);
	// }

	private void updateDocumentAttachFiles(Document document,
			DocumentsHasAttachFiles[] files) throws Exception {
		if (null == files || files.length == 0) {
			dispatchDocumentsDao.deleteAttachFiles(document.getId());
			dispatchDocumentsDao
					.deleteDocumentsHasAttachFiles(document.getId());
			return;
		}

		List<DocumentsHasAttachFiles> oldList = this
				.getDocfilesByDocumentId(document.getId());

		if (!oldList.isEmpty()) {
			List<DocumentsHasAttachFiles> newList = new ArrayList<DocumentsHasAttachFiles>(
					Arrays.asList(files));
			List<String> newAttachFiles = new ArrayList<String>();
			for (DocumentsHasAttachFiles file : files) {
				newAttachFiles.add(file.getFileName());
			}

			for (DocumentsHasAttachFiles file : oldList) {
				if (newAttachFiles.contains(file.getFileName())) {
					newList.remove(file);
				} else {
					dispatchDocumentsDao
							.deleteDocumentsHasAttachFileByFileId(file
									.getFileId());

				}
			}

			validateAttachFiles(files);
			dispatchDocumentsDao.addDocAttachFiles(convertToDocAttachFiles(
					document, newList.toArray(new DocumentsHasAttachFiles[0])));
		} else {
			validateAttachFiles(files);
			dispatchDocumentsDao.addDocAttachFiles(convertToDocAttachFiles(
					document, files));
		}

	}

	@Override
	public Map<String, Object> calculateDocumentPercent(Long documentId) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			jsonMap = dispatchDocumentsDao.calculateDocumentPercent(documentId);
		} catch (Exception e) {
			throw new ServiceValidationException("异常信息", e);
		}
		return jsonMap;
	}

	private void setProperties(SearchDocumentVo searchDocumentVo) {
		searchDocumentVo.setUserId(ThreadVariable.getUser().getId());
		if (null != searchDocumentVo.getCreateDateEnd()) {
			searchDocumentVo.setCreateDateEnd(DateUtil
					.getEndTime(searchDocumentVo.getCreateDateEnd()));
		}
	}

	@Override
	public Document searchEarliestDocument() {
		Document document = new Document();
		try {
			document = dispatchDocumentsDao.searchEarliestDocument();
		} catch (Exception e) {
			throw new ServiceValidationException("查询创建时间最早的发文出错了", e);
		}
		return document;
	}

	@Override
	public void deleteMyDocumentsAttachFile(Long id) {
		try {
			dispatchDocumentsDao.deleteDocumentsHasAttachFileByFileId(id);
		} catch (Exception e) {
			throw new ServiceValidationException("删除附件的时候报错", e);
		}
	}

	@Override
	public void addDispathDocumentInfo(String userIdList,
			String copyUserIdList, Document document,
			DocumentsHasAttachFiles[] files) {
		Boolean flag = Boolean.FALSE;
		List<DocumentsHasAttachFiles> fileList = new ArrayList<DocumentsHasAttachFiles>();
		if (null != document.getId()) {
			flag = true;
			fileList = this.getDocfilesByDocumentId(document.getId());
		}
		try {
			// 验证公文信息
			fillPropertics(document, files, userIdList, copyUserIdList);

			// 新增公文数据
			Document soveDocument = dispatchDocumentsDao
					.adddispatchDocuments(document);
			// 保存附件
			if (files != null) {
				validateAttachFiles(files);
				if (flag) {
					dispatchDocumentsDao
							.addDocAttachFiles(convertToDocAttachFilesFortransmit(
									soveDocument, fileList));
				} else {
					dispatchDocumentsDao
							.addDocAttachFiles(convertToDocAttachFiles(
									soveDocument, files));
				}
			}

		} catch (Exception e) {
			throw new ServiceValidationException("保存公文信息出错", e);
		}
	}

	@Override
	public void updateDocumentReadState(Long documentId, Long userId,
			Integer readState, Date readDate) {
		if (documentId == null || userId == null) {
			throw new BusinessValidationException("公文阅读失败，未获得公文信息");
		}
		try {
			dispatchDocumentsDao.updateDocumentReadState(documentId, userId,
					readState, readDate);
		} catch (Exception e) {
			throw new ServiceValidationException("修改公文阅读状态失败", e);
		}
	}

	@Override
	public void bindingDocWithUser(Document document, String[] userId,
			String[] copyUserId) {
		if (document == null || document.getId() == null) {
			throw new BusinessValidationException("公文转发失败，未获得公文信息");
		}
		try {
			if (userId != null && userId.length != 0) {
				bingdingDocAndUser(document, userId,
						DocumentsConstants.mainSend);
			}

			if (copyUserId != null && copyUserId.length != 0) {
				bingdingDocAndUser(document, copyUserId,
						DocumentsConstants.copySend);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("公文转发失败", e);
		}
	}

	/***
	 * 发送消息
	 * 
	 * @param document
	 * @param userId
	 */
	/**
	 * @param document
	 * @param userId
	 */
	private void sendPlatformMessage(Document document, String userId) {
		try {
			PlatformMessage pm = platformMessageFactory
					.createUserDocumentPlatformMessage(document,
							Long.parseLong(userId));
			platformaMessageService.sendPlatformMessageToUser(pm);// 发送消息
		} catch (Exception e) {
			throw new ServiceValidationException("公文发送时发送消息失败", e);
		}
	}

	@Override
	public List<DocumentSignVo> findReminderInfoByDocumentId(Long documentId) {
		if (documentId == null) {
			throw new BusinessValidationException("公文签收信息查询失败");
		}
		List<DocumentSignVo> list = dispatchDocumentsDao
				.findReminderInfoByDocumentId(documentId);
		if (list != null && list.size() != 0) {
			for (DocumentSignVo documentSignVo : list) {
				User user = permissionService.getSimpleUserById(documentSignVo
						.getUserId());
				if (user != null) {
					documentSignVo.setUserName(user.getName() + "("
							+ user.getUserName() + ")");
				}
			}
		}
		return list;
	}

	@Override
	public PageInfo finddispatchDocumentsListForHistory(Document document,
			Integer page, Integer rows, String sidx, String sord) {
		if (document == null) {
			document = new Document();
		}
		PageInfo pageInfo = null;
		try {
			document.setOrgId(ThreadVariable.getSession().getOrganization()
					.getId());
			pageInfo = dispatchDocumentsDao
					.finddispatchDocumentsListForHistory(document, page, rows,
							sidx, sord);
		} catch (Exception e) {
			throw new ServiceValidationException("异常信息", e);
		}
		return pageInfo;
	}

	@Override
	public void deleteDispatchDocForHistroy(Long[] ids) {
		try {
			for (Long id : ids) {
				Document document = dispatchDocumentsDao.getDocumentsById(id);
				if (document != null) {
					if (document.getDispatchState().equalsIgnoreCase(
							DocumentState.UNSENDED)) {
						dispatchDocumentsDao.deleteDispatchDocById(id);
						dispatchDocumentsDao.deleteAttachFiles(id);
						dispatchDocumentsDao.deleteDocumentsHasAttachFiles(id);
						if (null != document.getSendObjId()) {
							viewObjectService.deleteViewObjectById(document
									.getSendObjId());
						}
						if (null != document.getCopySendObjId()) {
							viewObjectService.deleteViewObjectById(document
									.getCopySendObjId());
						}
					} else {
						throw new BusinessValidationException("已发送或者转发的文件不允许删除");
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException("异常信息", e);
		}
	}

	@Override
	public Document getDocumentsByIdAndUserId(Long documentId, Long userId) {
		if (documentId == null || userId == null) {
			throw new BusinessValidationException("数据查询失败，未获得数据信息");
		}

		return dispatchDocumentsDao.getDocumentsByIdAndUserId(documentId,
				userId);
	}
}
