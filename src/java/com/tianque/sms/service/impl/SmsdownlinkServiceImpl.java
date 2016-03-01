package com.tianque.sms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.sms.dao.SmsdownlinkDao;
import com.tianque.sms.domain.Smsdownlink;
import com.tianque.sms.domain.vo.SearchSmsdownlinkVo;
import com.tianque.sms.service.SmsUplinkService;
import com.tianque.sms.service.SmsdownlinkService;

/**
 * 短信收件箱:业务逻辑层
 * 
 * @author
 * @date 2013-07-08 17:27:15
 */
@Repository("smsdownlinkService")
public class SmsdownlinkServiceImpl extends
		BaseServiceImpl<Smsdownlink, SearchSmsdownlinkVo> implements
		SmsdownlinkService {

	private static Logger logger = LoggerFactory
			.getLogger(SmsdownlinkServiceImpl.class);

	@Autowired
	private SmsUplinkService smsUplinkService;

	@Resource(name = "smsdownlinkDao")
	public void setBaseDao(SmsdownlinkDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public Smsdownlink add(Smsdownlink smsdownlink) {
		try {
			smsdownlink = getBaseDao().add(smsdownlink);
			return smsdownlink;
		} catch (Exception e) {
			return dealException(this, "add", "新增短信收件箱信息出现错误", e);
		}
	}

	@Override
	public Smsdownlink update(Smsdownlink smsdownlink) {
		try {
			smsdownlink = getBaseDao().update(smsdownlink);
			return smsdownlink;
		} catch (Exception e) {
			return dealException(this, "update", "更新短信收件箱信息出现错误", e);
		}
	}

	@Override
	public PageInfo<Smsdownlink> findSmsdownlinkForPager(Long isRead,
			String sender, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		if (sender == null) {
			throw new BusinessValidationException("发送者号码不能为空");
		}
		SmsdownlinkDao dao = (SmsdownlinkDao) getBaseDao();
		Integer countNum = dao.countSmsdownlinkBySender(sender);
		List<Smsdownlink> list = dao.findSmsdownlinkBySender(isRead, sender,
				pageNum, pageSize, sidx, sord);

		return new PageInfo<Smsdownlink>(pageNum, pageSize, countNum, list);
	}

	@Override
	public List<Smsdownlink> findSmsContentBySender(String sender) {
		if (sender == null) {
			throw new BusinessValidationException("发送者号码不能为空");
		}
		SmsdownlinkDao dao = (SmsdownlinkDao) getBaseDao();
		return dao.findSmsContentBySender(sender);
	}

	@Override
	public void updateDeleteStatusBySender(String[] sender) {
		if (sender == null) {
			throw new BusinessValidationException("发送者号码不能为空");
		}

		SmsdownlinkDao dao = (SmsdownlinkDao) getBaseDao();
		for (int i = 0; i < sender.length; i++) {
			smsUplinkService
					.updateDeleteStatusSmsUplinkByReceiverMobile(sender[i]);
			dao.updateDeleteStatusSmsdownlinkBySender(sender[i]);
		}

	}

	@Override
	public void updateSmsDownLinkBySender(Long isRead, String sender) {
		if (sender == null || isRead == null) {
			throw new BusinessValidationException("参数不能为空");
		}
		SmsdownlinkDao dao = (SmsdownlinkDao) getBaseDao();
		dao.updateSmsDownLinkBySender(isRead, sender);
	}

	@Override
	public Boolean updateDeleteStatusByIds(Long[] ids) {
		if (null == ids || ids.length <= 0) {
			throw new BusinessValidationException("参数无效！");
		}
		try {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < ids.length; i++) {
				if (ids[i] == null) {
					continue;
				}
				sb.append(",").append(ids[i]);
				if (i != 0 && i % 500 == 0) {
					((SmsdownlinkDao) getBaseDao()).updateDeleteStatusByIds(sb
							.toString().substring(1));
					sb = new StringBuffer();
				}
			}
			if (sb.length() > 0) {
				((SmsdownlinkDao) getBaseDao()).updateDeleteStatusByIds(sb
						.toString().substring(1));
			}
		} catch (Exception e) {

			return dealException(this, "updateDeleteStatusByIds",
					"更新短信删除状态为删除失败", e);
		}
		return true;
	}

	@Override
	public Boolean updateRestoreDeleteStatusById(Long id) {
		try {
			return ((SmsdownlinkDao) getBaseDao())
					.updateRestoreDeleteStatusById(id);
		} catch (Exception e) {
			return dealException(this, "updateRestoreDeleteStatusById",
					"更新短信删除状态为删除失败", e);
		}
	}

}
