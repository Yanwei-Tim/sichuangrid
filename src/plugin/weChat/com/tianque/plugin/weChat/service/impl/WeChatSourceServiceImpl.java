package com.tianque.plugin.weChat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.StoredFile;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.weChat.dao.WeChatSourceAttachmentDao;
import com.tianque.plugin.weChat.dao.WeChatSourceDao;
import com.tianque.plugin.weChat.domain.user.KeyWord;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.domain.user.WeChatMenu;
import com.tianque.plugin.weChat.domain.user.WeChatSource;
import com.tianque.plugin.weChat.domain.user.WeChatSourceAttachment;
import com.tianque.plugin.weChat.service.KeyWordService;
import com.tianque.plugin.weChat.service.TencentUserService;
import com.tianque.plugin.weChat.service.WeChatMenuService;
import com.tianque.plugin.weChat.service.WeChatSourceService;
import com.tianque.plugin.weChat.util.Constants;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.PropertyDictDubboService;

/**素材处理服务类*/
@Service("weChatSourceService")
@Transactional
public class WeChatSourceServiceImpl extends AbstractBaseService implements WeChatSourceService {
	private static Logger logger = Logger.getLogger(WeChatSourceServiceImpl.class);
	@Autowired
	private WeChatSourceDao weChatSourceDao;
	@Autowired
	private OrganizationDubboService organizationService;
	@Autowired
	private KeyWordService keyWordService;
	@Autowired
	private PropertyDictDubboService propertyDictService;
	@Autowired
	private WeChatMenuService weChatMenuService;
	@Autowired
	private WeChatSourceAttachmentDao weChatSourceAttachmentDao;
	@Autowired
	private TencentUserService tencentUserService;

	/**
	 * 素材列表
	 * @param parameterMap
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<WeChatSource> findWeChatSource(WeChatSource weChatSource, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		if ("sourceDescription".equals(sidx))
			sidx = "source_description";
		if ("sourceTypeDict.id".equals(sidx))
			sidx = "source_type";
		if ("id".equals(sidx))
			sidx = "source_id";
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("weChatSource", weChatSource);
		PageInfo<WeChatSource> pageInfo = weChatSourceDao.findWeChatSource(map, pageNum, pageSize);
		for (WeChatSource vo : pageInfo.getResult()) {
			vo.setSourceAttachmentList(weChatSourceAttachmentDao
					.getWeChatSouceAttachmentByWeChatuserIdAndSouceId(vo.getWeChatUserId(),
							vo.getId()));
		}
		return pageInfo;
	}

	/**
	 * 添加素材
	 * @param keyWord
	 * @return
	 */
	public Long addWeChatSource(WeChatSource weChatSource) {
		Organization org = organizationService.getSimpleOrgById(weChatSource.getOrg().getId());
		weChatSource.setOrg(org);
		//		if (getCountByWeChatUserIdAndSourceType(weChatSource.getWeChatUserId(), weChatSource
		//				.getSourceTypeDict().getId()) >=  Constants.WECHATSOURCE_TYPE_COUNT) {
		//				PropertyDict propertyDict = propertyDictService.getPropertyDictById(weChatSource
		//						.getSourceTypeDict().getId());
		//				throw new ServiceException("微信号[" + weChatSource.getWeChatUserId() + "]已经添加"
		//						+ Constants.WECHATSOURCE_TYPE_COUNT + "条" + propertyDict.getDisplayName()
		//						+ "素材，不能再添加了！");
		//		}
		if (getCountByWeChatUserId(weChatSource.getWeChatUserId()) >= Constants.WECHATSOURCE_ALL_COUNT) {
			throw new ServiceException("微信号[" + weChatSource.getWeChatUserId() + "]已经添加"
					+ Constants.WECHATSOURCE_ALL_COUNT + "条" + "素材，不能再添加了！");
		} else {
			Long id = 0L;
			if (weChatSource.getPath() != null && !"".equals(weChatSource.getPath())) {
				try {
					String fileName = weChatSource.getPath().substring(1);
					StoredFile s = FileUtil.copyTmpFileToStoredFile(fileName,
							Constants.WECHAT_ATTACHFILE);
					weChatSource.setPath("/" + s.getFullName().replaceAll("\\\\", "/"));
					id = weChatSourceDao.addWeChatSource(weChatSource);
					WeChatSourceAttachment weChatSourceAttachment = new WeChatSourceAttachment();
					weChatSourceAttachment.setFileName(s.getStoredFileName());
					weChatSourceAttachment
							.setExtFileName(fileName.substring(fileName.indexOf(".") + 1));
					weChatSourceAttachment.setFileActualUrl("/"
							+ s.getFullName().replaceAll("\\\\", "/"));
					weChatSourceAttachment.setSourceId(id);
					weChatSourceAttachment.setWeChatUserId(weChatSource.getWeChatUserId());
					weChatSourceAttachmentDao.addWeChatSourceAttachment(weChatSourceAttachment);
				} catch (Exception e) {
					logger.error("素材附件添加失败：" + e.getMessage());
					return 0L;
				}
			} else {
				id = weChatSourceDao.addWeChatSource(weChatSource);
			}
			return id;
		}

	}

	/**
	 * 修改素材
	 * @param weChatSource
	 * @return
	 */
	public Integer updateWeChatSource(WeChatSource weChatSource) {
		try {
			if (weChatSource.getPath() != null && !"".equals(weChatSource.getPath())) {
				String fileName = weChatSource.getPath().substring(1);
				StoredFile s = FileUtil.copyTmpFileToStoredFile(fileName,
						Constants.WECHAT_ATTACHFILE);
				weChatSource.setPath("/" + s.getFullName().replaceAll("\\\\", "/"));
				List<WeChatSourceAttachment> weChatSourceAttachmentList = weChatSourceAttachmentDao
						.getWeChatSouceAttachmentByWeChatuserIdAndSouceId(
								weChatSource.getWeChatUserId(), weChatSource.getId());
				WeChatSourceAttachment weChatSourceAttachment = weChatSourceAttachmentList.size() > 0 ? weChatSourceAttachmentList
						.get(0) : new WeChatSourceAttachment();
				weChatSourceAttachment.setFileName(s.getStoredFileName());
				weChatSourceAttachment
						.setExtFileName(fileName.substring(fileName.indexOf(".") + 1));
				weChatSourceAttachment.setFileActualUrl("/"
						+ s.getFullName().replaceAll("\\\\", "/"));
				weChatSourceAttachment.setSourceId(weChatSource.getId());
				weChatSourceAttachment.setWeChatUserId(weChatSource.getWeChatUserId());
				if (weChatSourceAttachmentList.size() > 0)
					weChatSourceAttachmentDao
							.updateWeChatSouceAttachmentById(weChatSourceAttachment);
				else
					weChatSourceAttachmentDao.addWeChatSourceAttachment(weChatSourceAttachment);

			} else {
				//修改语音或是图片时，当选择别的微信号时， 把当前素材记录和附近记录复制一遍
				PropertyDict p = propertyDictService.getPropertyDictById(weChatSource
						.getSourceTypeDict().getId());
				if (p.getDisplayName().equals("图片") || p.getDisplayName().equals("语音")
						|| p.getDisplayName().equals("图文")) {
					WeChatSource w = getWeChatSource(weChatSource.getId());
					if (!w.getWeChatUserId().equals(weChatSource.getWeChatUserId())) {
						w.setId(null);
						w.setWeChatUserId(weChatSource.getWeChatUserId());
						w.setSourceDescription(weChatSource.getSourceDescription());
						w.setTitle(weChatSource.getTitle());
						w.setDescription(weChatSource.getDescription());
						w.setUrl(weChatSource.getUrl());
						Long id = weChatSourceDao.addWeChatSource(w);
						WeChatSourceAttachment weChatSourceAttachment = new WeChatSourceAttachment();
						weChatSourceAttachment.setFileName(w.getPath().substring(
								w.getPath().lastIndexOf("/") + 1));
						weChatSourceAttachment.setExtFileName(w.getPath().substring(
								w.getPath().indexOf(".") + 1));
						weChatSourceAttachment.setFileActualUrl(w.getPath());
						weChatSourceAttachment.setSourceId(id);
						weChatSourceAttachment.setWeChatUserId(weChatSource.getWeChatUserId());
						weChatSourceAttachmentDao.addWeChatSourceAttachment(weChatSourceAttachment);
						return Integer.valueOf(id + "");
					}
				}

			}
			KeyWord k = new KeyWord();
			k.setSourceId(weChatSource.getId().toString());
			k.setSourceDescription(weChatSource.getSourceDescription());
			keyWordService.updateKeyWordBySourceId(k);
			WeChatMenu menu = new WeChatMenu();
			menu.setSourceID(weChatSource.getId().toString());
			menu.setSourceDescription(weChatSource.getSourceDescription());
			weChatMenuService.updateWeChatMenuBySourceId(menu);
			return weChatSourceDao.updateWeChatSource(weChatSource);
		} catch (Exception e) {
			logger.error("素材修改失败：" + e.getMessage());
			return 0;
		}

	}

	/**
	 * 删除素材
	 * @param ids
	 * @return
	 */
	public Integer deleteWeChatSource(String ids) {
		String[] idArray = ids.split(",");
		int flag = 0;
		for (int i = 0; i < idArray.length; i++) {
			List<KeyWord> keyWord = keyWordService.getKeyWordBySourceId(idArray[i]);

			List<WeChatMenu> weChatMenuList = weChatMenuService.getWeChatMenuBySourceId(idArray[i]);
			List<TencentUser> tencentUserList = tencentUserService
					.getTencentUserBySourceId(idArray[i]);
			if (keyWord != null && keyWord.size() > 0)
				throw new ServiceException("素材编号[" + keyWord.get(0).getSourceId() + "]已被关键字["
						+ keyWord.get(0).getKeyName() + "]绑定，不能被删除！");//关键字
			else if (weChatMenuList != null && weChatMenuList.size() > 0)
				throw new ServiceException("素材编号[" + weChatMenuList.get(0).getSourceID() + "]已被菜单["
						+ weChatMenuList.get(0).getMenuName() + "]绑定，不能被删除！");//菜单
			else if (tencentUserList != null && tencentUserList.size() > 0)
				throw new ServiceException("素材编号[" + tencentUserList.get(0).getSourceId()
						+ "]已被服务号[" + tencentUserList.get(0).getWeChatUserId() + "]绑定，不能被删除！");//服务号
			else {
				//				WeChatSourceAttachment attachment = weChatSourceAttachmentDao
				//						.getWeChatSouceAttachmentBySourceId(Long.parseLong(idArray[i]));
				//				if (attachment != null) {
				//					String path = FileUtil.getWebRoot() + attachment.getFileActualUrl();
				//					File file = new File(path);
				//					if (file.exists())
				//						file.delete();
				//			
				//				}
				weChatSourceAttachmentDao.deleteWeChatSouceAttachmentBySourceId(Long
						.parseLong(idArray[i]));
				flag += weChatSourceDao.deleteWeChatSource(Long.parseLong(idArray[i]));
			}
		}
		return flag;
	}

	/**
	 * 根据服务号删除素材
	 * @param id
	 * @return
	 */
	public Integer deleteWeChatSourceByWeChatUserId(String weChatUserId) {
		return weChatSourceDao.deleteWeChatSourceByWeChatUserId(weChatUserId);
	}

	/**
	 * 加载素材对象
	 * @param id
	 * @return
	 */
	public WeChatSource getWeChatSource(Long id) {
		return weChatSourceDao.getWeChatSource(id);
	}

	/**
	 * 加载素材对象集合
	 * @param ids
	 * @return
	 */
	public List<WeChatSource> getWeChatSourceByIds(String ids) {
		return weChatSourceDao.getWeChatSourceByIds(ids);
	}

	/**
	 * 根据微信号和类型获取素材总数(用于每个服务号所添加的类型里个数的校验)
	 * @param weChatUserId
	 * @param sourceType
	 * @return
	 */
	public Long getCountByWeChatUserIdAndSourceType(String weChatUserId, long sourceType) {
		return weChatSourceDao.getCountByWeChatUserIdAndSourceType(weChatUserId, sourceType);
	}

	/**
	 * 根据微信号素材总数(用于每个服务号所添加的素材个数的校验)
	 * @param weChatUserId
	 * @return
	 */
	public Long getCountByWeChatUserId(String weChatUserId) {
		return weChatSourceDao.getCountByWeChatUserId(weChatUserId);
	}

	/**
	 * 根据orgId和类型加载素材集合
	 * @param orgId
	 * @param sourceType
	 * @return
	 */

	public List<WeChatSource> getWeChatSourceByOrgIdAndSourceType(long orgId, long sourceType) {
		return weChatSourceDao.getWeChatSourceByOrgIdAndSourceType(orgId, sourceType);
	}

}
