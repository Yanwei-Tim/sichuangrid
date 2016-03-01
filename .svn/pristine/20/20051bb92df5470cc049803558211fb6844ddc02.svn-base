package com.tianque.plugin.weChat.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.plugin.weChat.dao.TencentUserDao;
import com.tianque.plugin.weChat.dao.WeChatGroupDao;
import com.tianque.plugin.weChat.domain.sendMessage.Article;
import com.tianque.plugin.weChat.domain.sendMessage.TextMessage;
import com.tianque.plugin.weChat.domain.user.Fan;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.domain.user.WeChatGroup;
import com.tianque.plugin.weChat.service.FanService;
import com.tianque.plugin.weChat.service.KeyWordService;
import com.tianque.plugin.weChat.service.TencentUserService;
import com.tianque.plugin.weChat.service.WeChatGroupService;
import com.tianque.plugin.weChat.service.WeChatMenuService;
import com.tianque.plugin.weChat.service.WeChatService;
import com.tianque.plugin.weChat.service.WeChatSourceAttachmentService;
import com.tianque.plugin.weChat.service.WeChatSourceService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("tencentUserService")
@Transactional
public class TencentUserServiceImpl extends AbstractBaseService implements
		TencentUserService {

	@Autowired
	private TencentUserDao tencentUserDao;
	@Autowired
	private OrganizationDubboService organizationService;
	@Autowired
	private WeChatGroupService weChatGroupService;
	@Autowired
	private WeChatGroupDao weChatGroupDao;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private WeChatService weChatService;
	@Autowired
	private FanService fanService;
	@Autowired
	private WeChatSourceService weChatSourceService;
	@Autowired
	private WeChatSourceAttachmentService weChatSourceAttachmentService;
	@Autowired
	private KeyWordService keyWordService;
	@Autowired
	private WeChatMenuService weChatMenuService;
	@Autowired
	private PropertyDictService propertyDictService;

	/**
	 * 发送文本消息
	 */
	public String sendTextMessage(TextMessage textMessage,
			TencentUser tencentUser, String openIds) {
		if (tencentUser.getWeChatUserId() == null
				|| "".equals(tencentUser.getWeChatUserId()))
			throw new ServiceException("发送文本消息时，服务号不能为空！");
		TencentUser ten = getTencentUserByWeChatUserId(tencentUser
				.getWeChatUserId());// getToUserName() 开发者微信号
		if (ten == null)
			throw new ServiceException("发送文本消息时，服务号获取失败！");
		List<TextMessage> listTextMessage = new ArrayList<TextMessage>();
		if (openIds.startsWith("fan_")) {
			String openId = openIds.replaceAll("fan_", "");
			String[] openIdArray = openId.split(",");
			for (int i = 0; i < openIdArray.length; i++) {
				TextMessage t = new TextMessage();
				t.setContent(textMessage.getContent());
				t.setToUserName(openIdArray[i]);
				listTextMessage.add(t);
			}
		}
		if (openIds.startsWith("group_")) {
			String groupIds = openIds.replaceAll("group_", "");
			List<Fan> fanList = fanService.getFanListByGroupIdsAndWeChatUserId(
					groupIds, tencentUser.getWeChatUserId());
			if (fanList.size() > 0) {
				for (int i = 0; i < fanList.size(); i++) {
					TextMessage t = new TextMessage();
					t.setContent(textMessage.getContent());
					t.setToUserName(fanList.get(i).getOpenId());
					listTextMessage.add(t);
				}
			} else {
				throw new ServiceException("发送文本消息时，收件群组不能为空！");
			}
		}
		if (listTextMessage.size() > 0) {
			String result = weChatService
					.replyTextMessage(listTextMessage, ten);
			if (result == null && !"null".equals(result)) {
				return null;
			} else {
				throw new ServiceException(result);
			}
		} else {
			throw new ServiceException("发送文本消息时，收件人不能为空！");

		}
	}

	/**
	 * 发送图片消息
	 */
	public String sendImageMessage(TextMessage textMessage,
			TencentUser tencentUser, Set<String> attachFiles, String openIds) {
		if (tencentUser.getWeChatUserId() == null
				|| "".equals(tencentUser.getWeChatUserId()))
			throw new ServiceException("发送图片消息时，服务号不能为空！");
		TencentUser ten = getTencentUserByWeChatUserId(tencentUser
				.getWeChatUserId());// getToUserName() 开发者微信号
		if (ten == null)
			throw new ServiceException("发送图片消息时，服务号获取失败！");
		String result = null;
		List<TextMessage> listTextMessage = getListTextMessages(openIds, ten,
				"图片");
		if (listTextMessage.size() > 0) {
			for (String fileNameAndId : attachFiles) {
				String fileName = fileNameAndId.substring(1);
				File file = new File(FileUtil.getWebRoot() + File.separator
						+ GridProperties.TMP + File.separator
						+ ThreadVariable.getUser().getId() + File.separator
						+ fileName);
				if (!file.exists())
					throw new ServiceException("图片文件不存在，操件失败");
				if (fileName.contains(" "))
					throw new ServiceException("请上传文件名不含空格的文件");
				String path = GridProperties.PROXY_SERVER_DOMAIN_NAME
						+ "/uploadFile/tmp/" + ThreadVariable.getUser().getId()
						+ "/" + fileName;
				result = weChatService.replyImageMessage(listTextMessage, ten,
						path);
				if (result == null)
					if (file.exists())
						file.delete();
			}
			if (result == null && !"null".equals(result)) {
				return null;
			} else {
				throw new ServiceException(result);
			}
		} else {
			throw new ServiceException("发送图片消息时，收件人不能为空！");

		}
	}

	/**
	 * 发送语音消息
	 */

	public String sendVoiceMessage(TextMessage textMessage,
			TencentUser tencentUser, Set<String> attachFiles, String openIds) {
		if (tencentUser.getWeChatUserId() == null
				|| "".equals(tencentUser.getWeChatUserId()))
			throw new ServiceException("发送语音消息时，服务号不能为空！");
		TencentUser ten = getTencentUserByWeChatUserId(tencentUser
				.getWeChatUserId());// getToUserName() 开发者微信号
		if (ten == null)
			throw new ServiceException("发送语音消息时，服务号获取失败！");
		String result = null;
		List<TextMessage> listTextMessage = getListTextMessages(openIds, ten,
				"语音");
		if (listTextMessage.size() > 0) {
			for (String fileNameAndId : attachFiles) {
				String fileName = fileNameAndId.substring(1);
				File file = new File(FileUtil.getWebRoot() + File.separator
						+ GridProperties.TMP + File.separator
						+ ThreadVariable.getUser().getId() + File.separator
						+ fileName);
				if (!file.exists())
					throw new ServiceException("语音文件不存在，操件失败");
				if (fileName.contains(" "))
					throw new ServiceException("请上传文件名不含空格的文件");
				String path = GridProperties.PROXY_SERVER_DOMAIN_NAME
						+ "/uploadFile/tmp/" + ThreadVariable.getUser().getId()
						+ "/" + fileName;
				result = weChatService.replyVoiceMessage(listTextMessage,
						tencentUser, path);
				if (result == null)
					if (file.exists())
						file.delete();
			}
			if (result != null) {
				throw new ServiceException(result);
			} else {
				return null;
			}
		} else {
			throw new ServiceException("发送语音消息时，收件人不能为空！");
		}
	}

	/**
	 * 发送图文消息
	 */
	public String sendNewsMessage(TextMessage textMessage,
			TencentUser tencentUser, Set<String> attachFiles, Article article,
			String openIds) {
		if (tencentUser.getWeChatUserId() == null
				|| "".equals(tencentUser.getWeChatUserId()))
			throw new ServiceException("发送图文消息时，服务号不能为空！");
		TencentUser ten = getTencentUserByWeChatUserId(tencentUser
				.getWeChatUserId());// getToUserName() 开发者微信号
		if (ten == null)
			throw new ServiceException("发送图文消息时，服务号获取失败！");
		String result = null;
		List<TextMessage> listTextMessage = getListTextMessages(openIds, ten,
				"图文");
		if (listTextMessage.size() > 0) {
			for (String fileNameAndId : attachFiles) {
				String fileName = fileNameAndId.substring(1);
				File file = new File(FileUtil.getWebRoot() + File.separator
						+ GridProperties.TMP + File.separator
						+ ThreadVariable.getUser().getId() + File.separator
						+ fileName);
				if (!file.exists())
					throw new ServiceException("图片文件不存在，操件失败");
				if (fileName.contains(" "))
					throw new ServiceException("请上传文件名不含空格的文件");
				String picUrl = GridProperties.PROXY_SERVER_DOMAIN_NAME
						+ "/uploadFile/tmp/" + ThreadVariable.getUser().getId()
						+ "/" + fileName;
				List<Article> listArticles = new ArrayList<Article>();
				article.setPicUrl(picUrl);
				listArticles.add(article);
				result = weChatService.replyNewsMessage(listTextMessage,
						tencentUser, listArticles);

			}
			if (result != null) {
				throw new ServiceException(result);
			} else {
				return null;
			}
		} else {
			throw new ServiceException("发送图文消息时，收件人不能为空！");
		}
	}

	private List<TextMessage> getListTextMessages(String openIds,
			TencentUser ten, String type) {
		List<TextMessage> listTextMessage = new ArrayList<TextMessage>();
		if (openIds.startsWith("fan_")) {
			String openId = openIds.replaceAll("fan_", "");
			String[] openIdArray = openId.split(",");
			for (int i = 0; i < openIdArray.length; i++) {
				TextMessage t = new TextMessage();
				t.setToUserName(openIdArray[i]);
				listTextMessage.add(t);
			}
		}
		if (openIds.startsWith("group_")) {
			String groupIds = openIds.replaceAll("group_", "");
			List<Fan> fanList = fanService.getFanListByGroupIdsAndWeChatUserId(
					groupIds, ten.getWeChatUserId());
			if (fanList.size() > 0) {
				for (int i = 0; i < fanList.size(); i++) {
					TextMessage t = new TextMessage();
					t.setToUserName(fanList.get(i).getOpenId());
					listTextMessage.add(t);
				}
			} else {
				throw new ServiceException("发送" + type + "消息时，收件群组不能为空!");
			}
		}
		return listTextMessage;
	}

	@Override
	public PageInfo getTencentUserList(TencentUser tencentUser,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("tencentUser", tencentUser);
		PageInfo tencentUserList = tencentUserDao.getTencentUserList(map,
				pageNum, pageSize);
		return tencentUserList;
	}

	@Override
	public Long addTencentUser(TencentUser tencentUser) {
		List<WeChatGroup> weChatGroupList = weChatService
				.getWeChatGroupList(tencentUser);
		// 等于false表示没有重复
		Boolean repeat = false;
		if (tencentUser.getTencentUserId() != null) {
			// 修改前删除上一个对应微信号的AccessToken缓存
			TencentUser t = getTencentUserByTencentUserId(tencentUser
					.getTencentUserId());
			cacheService.remove("weChatAccessToken" + t.getWeChatUserId());
		}

		List<PropertyDict> propertyTypeslist = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.RED_CUFF_TEAM_SUB_TYPE);

		if (propertyTypeslist != null && !propertyTypeslist.isEmpty()) {

			if (weChatGroupList != null && !weChatGroupList.isEmpty()) {

				// 创建或同步红袖套队伍类别之外的分组 到本地
				Boolean temp;
				for (WeChatGroup weChatGroup : weChatGroupList) {
					temp = false;
					for (PropertyDict propertyDict : propertyTypeslist) {
						if (weChatGroup.getName().equals(
								propertyDict.getDisplayName())) {
							temp = true;
							break;
						}
					}

					if (!temp) {
						WeChatGroup oldweChatGroup = weChatGroupService
								.getGroupListByNameAndWeChatUserId(
										weChatGroup.getWeChatUserId(),
										weChatGroup.getName());
						// 先判断本地是否有该分组
						if (oldweChatGroup == null) {
							weChatGroupDao.addWeChatGroup(weChatGroup);
						} else {
							// 如果本地和微信端有相同分组，同步粉丝数量
							oldweChatGroup.setCountFan(weChatGroup
									.getCountFan());
							weChatGroupDao
									.updateWeChatGroupById(oldweChatGroup);
						}
					}
				}

				// 创建红袖套队伍类别的分组(必须创建)，同时同步到微信端(或者同步微信端的粉丝数到本地)
				for (PropertyDict propertyDict : propertyTypeslist) {
					if (propertyDict.getDisplayName() != null) {
						repeat = false;
						// 判断微信那边是否有这个分组名称避免出现同名字的分组
						for (WeChatGroup weChatGroup : weChatGroupList) {

							if (weChatGroup.getName() != null
									&& weChatGroup.getName().equals(
											propertyDict.getDisplayName())) {

								WeChatGroup oldWeChatGroup = weChatGroupService
										.getGroupListByNameAndWeChatUserId(
												tencentUser.getWeChatUserId(),
												propertyDict.getDisplayName());
								// 判断本地是否有该分组
								if (oldWeChatGroup == null) {
									weChatGroupDao.addWeChatGroup(weChatGroup);
								} else {
									// 如果本地和微信端有相同分组，同步粉丝数量
									oldWeChatGroup.setCountFan(weChatGroup
											.getCountFan());
									weChatGroupDao
											.updateWeChatGroupById(oldWeChatGroup);
								}
								repeat = true;
								break;
							}
						}
						if (!repeat) {
							WeChatGroup newWeChatGroup = weChatGroupService
									.createGroup(tencentUser,
											propertyDict.getDisplayName());
							// 新添加分组粉丝数默认为0
							Long temp1 = 0L;
							newWeChatGroup.setCountFan(temp1);
							newWeChatGroup.setWeChatUserId(tencentUser
									.getWeChatUserId());

							WeChatGroup WeChatGroup1 = weChatGroupService
									.getGroupListByNameAndWeChatUserId(
											tencentUser.getWeChatUserId(),
											propertyDict.getDisplayName());
							// 添加分组名字不能重复
							if (WeChatGroup1 == null) {
								weChatGroupDao.addWeChatGroup(newWeChatGroup);
							} else {
								WeChatGroup1.setCountFan(temp1);
								WeChatGroup1.setGroupId(newWeChatGroup
										.getGroupId());
								weChatGroupDao
										.updateWeChatGroupById(WeChatGroup1);
							}

						}
					}
				}
			} else {
				for (PropertyDict propertyDict : propertyTypeslist) {
					if (propertyDict.getDisplayName() != null) {
						weChatGroupService.createGroup(tencentUser,
								propertyDict.getDisplayName());
					}
				}
			}
		}

		/*
		 * boolean flag = weChatGroupService.saveWeChatGroup(tencentUser); if
		 * (flag == false) { throw new ServiceException("该服务号不存在或是未获取微信认证！！！");
		 * } else {
		 */
		Organization org = organizationService.getSimpleOrgById(tencentUser
				.getOrganization().getId());
		if (org != null) {
			tencentUser.setOrganization(org);
		}
		Long id = tencentUserDao.addTencentUser(tencentUser);
		tencentUser.setTencentUserId(id);
		return id;
		/* } */

	}

	/**
	 * 微信号绑定自动回复消息（素材）
	 * 
	 * @param tencentUser
	 * @return
	 */
	public Integer updateTencentUser(TencentUser tencentUser) {
		return tencentUserDao.updateTencentUser(tencentUser);
	}

	@Override
	public TencentUser getTencentUserByTencentUserId(Long tencentUserId) {
		return tencentUserDao.getTencentUserByTencentUserId(tencentUserId);
	}

	@Override
	public void deleteTencentUser(List<Long> ids) {
		for (Long id : ids) {
			TencentUser tencentUser = tencentUserDao
					.getTencentUserByTencentUserId(id);
			String accessToken = (String) cacheService.get("weChatAccessToken"
					+ tencentUser.getWeChatUserId());
			// 删除缓存
			if (accessToken != null) {
				cacheService.remove("weChatAccessToken"
						+ tencentUser.getWeChatUserId());
			}
			keyWordService.deleteKeyWordByWeChatUserId(tencentUser
					.getWeChatUserId());// 删除关键字
			weChatSourceAttachmentService
					.deleteWeChatSouceAttachmentByWeChatUserId(tencentUser
							.getWeChatUserId());// 删除附件
			weChatSourceService.deleteWeChatSourceByWeChatUserId(tencentUser
					.getWeChatUserId());// 删除素材
			// WeChatMenu weChatMenu=new WeChatMenu();
			// weChatMenu.setSourceID(null);
			// weChatMenu.setSourceDescription("");
			// PropertyDict sourceTypeDict=new PropertyDict();
			// sourceTypeDict.setId(null);
			// weChatMenu.setSourceTypeDict(sourceTypeDict);
			// weChatMenu.setWeChatUserId(tencentUser.getWeChatUserId());
			// weChatMenuService.updateWeChatMenuByWeChatUserId(weChatMenu);//修改菜单
			// （把关联的素材置空）
			weChatMenuService.deleteWeChatMenuByWeChatUserId(tencentUser
					.getWeChatUserId());// 删除菜单
			tencentUserDao.deleteTencentUserById(id);
			weChatGroupService.deleteWeChatGroupByWeChatUserId(tencentUser
					.getWeChatUserId());// 删除组
		}
	}

	@Override
	public List<TencentUser> getTencentUserListByOrgId(Long orgId) {
		return tencentUserDao.getTencentUserListByOrgId(orgId);
	}

	/**
	 * 根据素材id加载Tencent集合（条件Like）
	 * 
	 * @param sourceId
	 * @return
	 */
	public List<TencentUser> getTencentUserBySourceId(String sourceId) {
		return tencentUserDao.getTencentUserBySourceId(sourceId);
	}

	@Override
	public TencentUser getTencentUserByWeChatUserId(String weChatUserId) {
		return tencentUserDao.getTencentUserByWeChatUserId(weChatUserId);
	}

	@Override
	public List<TencentUser> getTencentUserListByOrgCode(String orgInternalCode) {
		if (orgInternalCode == null) {
			throw new ServiceException("组织机构编号为空");
		}
		return tencentUserDao.getTencentUserListByOrgCode(orgInternalCode);
	}

}
