package com.tianque.sms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sms.dao.SmstrashDao;
import com.tianque.sms.domain.SmsUplink;
import com.tianque.sms.domain.Smsdownlink;
import com.tianque.sms.domain.Smstrash;
import com.tianque.sms.domain.vo.SearchSmstrashVo;
import com.tianque.sms.service.SmsUplinkService;
import com.tianque.sms.service.SmsdownlinkService;
import com.tianque.sms.service.SmstrashService;

/**
 * 垃圾短信箱:业务逻辑层
 * 
 * @author
 * @date 2013-09-22 16:42:50
 */
@Repository("smstrashService")
@Transactional
public class SmstrashServiceImpl implements SmstrashService {

	@Autowired
	private SmsUplinkService smsUplinkService;
	@Autowired
	private SmsdownlinkService smsdownlinkService;
	@Autowired
	private SmstrashDao smstrashDao;

	@Override
	public PageInfo<Smstrash> findSmstrashPagerBySearchVo(
			SearchSmstrashVo searchVo, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		return smstrashDao.findSmstrashsBySearchVo(searchVo, pageNum, pageSize,
				sortField, order);
	}

	@Override
	public boolean restoreSMSById(String id) {
		try {
			if (!StringUtil.isStringAvaliable(id)) {
				throw new BusinessValidationException("参数无效！");
			}
			if (Long.parseLong(id.split("_")[0]) == 1L) {
				return smsUplinkService.updateRestoreDeleteStatusById(Long
						.valueOf(id.split("_")[1]));
			} else if (Long.parseLong(id.split("_")[0]) == 2L) {
				return smsdownlinkService.updateRestoreDeleteStatusById(Long
						.valueOf(id.split("_")[1]));
			}
		} catch (Exception e) {
			throw new ServiceValidationException("还原短信失败!", e);
		}
		return false;
	}

	@Override
	public Smstrash get(String id) {
		if (!StringUtil.isStringAvaliable(id)) {
			throw new BusinessValidationException("参数无效！");
		}
		if (Long.parseLong(id.split("_")[0]) == 1L) {
			SmsUplink up = smsUplinkService.get(Long.valueOf(id.split("_")[1]));
			return createSmstrash(up);
		} else if (Long.parseLong(id.split("_")[1]) == 2L) {
			Smsdownlink down = smsdownlinkService.get(Long.valueOf(id
					.split("_")[1]));
			return createSmstrash(down);
		}
		return null;
	}

	private Smstrash createSmstrash(SmsUplink up) {
		Smstrash smstrash = new Smstrash();
		smstrash.setContent(up.getContent());
		smstrash.setFromType(1L);
		smstrash.setMobile(up.getReceiverMobile());
		smstrash.setTime(up.getSendTime());
		return smstrash;
	}

	private Smstrash createSmstrash(Smsdownlink down) {
		Smstrash smstrash = new Smstrash();
		smstrash.setContent(down.getContent());
		smstrash.setFromType(2L);
		smstrash.setMobile(down.getSender());
		smstrash.setTime(down.getSendTime());
		return smstrash;
	}

	@Override
	public void delete(String id) {
		try {
			if (!StringUtil.isStringAvaliable(id)) {
				throw new BusinessValidationException("参数无效！");
			}
			if (Long.parseLong(id.split("_")[0]) == 1L) {
				smsUplinkService.delete(Long.valueOf(id.split("_")[1]));
			} else if (Long.parseLong(id.split("_")[0]) == 2L) {
				smsdownlinkService.delete(Long.valueOf(id.split("_")[1]));
			}
		} catch (Exception e) {
			throw new ServiceValidationException("垃圾短信删除错误!", e);
		}

	}

	@Override
	public void delete(String[] ids) {
		if (ids == null || ids.length <= 0) {
			throw new BusinessValidationException("参数无效！");
		}
		for (String id : ids) {
			delete(id);
		}
	}

}
