package com.tianque.plugin.weChat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.constant.RedEnvelopeConstant;
import com.tianque.plugin.weChat.dao.RedEnvelopeDao;
import com.tianque.plugin.weChat.domain.redEnvelopeManagement.RedEnvelope;
import com.tianque.plugin.weChat.domain.user.Fan;
import com.tianque.plugin.weChat.service.FanService;
import com.tianque.plugin.weChat.service.RedEnvelopeService;
import com.tianque.plugin.weChat.vo.RedEnvelopeVo;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**红包处理服务类*/
@Service("redEnvelopeService")
@Transactional
public class RedEnvelopeServiceImpl extends AbstractBaseService implements RedEnvelopeService {
	@Autowired
	private OrganizationDubboService organizationService;
	@Autowired
	private FanService fanService;
	@Autowired
	private RedEnvelopeDao redEnvelopeDao;

	@Override
	public RedEnvelope addRedEnvelope(RedEnvelope redEnvelope) {
		verificationRedEnvelope(redEnvelope);
		try {
			//根据部门id重新获取部门,以便得到完整的部门信息,如：orgInternalCode
			redEnvelope.setOrg(organizationService.getFullOrgById(redEnvelope.getOrg().getId()));
			return redEnvelopeDao.addRedEnvelope(redEnvelope);
		} catch (Exception e) {
			logger.error("类RedEnvelopeServiceImpl的addRedEnvelope方法出现异常，原因：", e);
			throw new ServiceException("创建红包出错");
		}
	}

	/**
	 * 验证
	 */
	private void verificationRedEnvelope(RedEnvelope redEnvelope) {
		if (redEnvelope == null) {
			throw new ServiceException("创建红包出错,请刷新后重试!");
		}
		if (redEnvelope.getOrg() == null || redEnvelope.getOrg().getId() == null) {
			throw new ServiceException("当前辖区不能为空！");
		}
		if (!StringUtil.isStringAvaliable(redEnvelope.getAct_name())) {
			throw new ServiceException("活动名称不能为空！");
		}
		if (!StringUtil.isStringAvaliable(redEnvelope.getMch_id())) {
			throw new ServiceException("微信支付商户号不能为空！");
		}
		if (!StringUtil.isStringAvaliable(redEnvelope.getApiKey())) {
			throw new ServiceException("微信支付商户号API密钥不能为空！");
		}
		if (!StringUtil.isStringAvaliable(redEnvelope.getWxappid())) {
			throw new ServiceException("微信公众平台appID不能为空！");
		}
		if (!StringUtil.isStringAvaliable(redEnvelope.getWeChatUserId())) {
			throw new ServiceException("公众账号(微信连接号)不能为空！");
		}
		if (redEnvelope.getRedEnvelopeType() == null) {
			throw new ServiceException("红包类型不能为空！");
		} else {
			if (redEnvelope.getRedEnvelopeType().intValue() == RedEnvelopeConstant.SINGLEENVELOPE) {
				if (redEnvelope.getSingleEnvelope_value() == null) {
					throw new ServiceException("单个红包金额不能为空！");
				}
			} else {
				if (redEnvelope.getMin_value() == null) {
					throw new ServiceException("最小红包金额 不能为空！");
				}
				if (redEnvelope.getMax_value() == null) {
					throw new ServiceException("最大红包金额 不能为空！");
				}
			}
		}
		if (!StringUtil.isStringAvaliable(redEnvelope.getSend_name())) {
			throw new ServiceException("商户名称不能为空");
		}
		if (!StringUtil.isStringAvaliable(redEnvelope.getRemark())) {
			throw new ServiceException("备注不能为空！");
		}
		if (!StringUtil.isStringAvaliable(redEnvelope.getWishing())) {
			throw new ServiceException("祝福语不能为空！");
		}
	}

	@Override
	public RedEnvelope updateRedEnvelope(RedEnvelope redEnvelope) {
		if (redEnvelope == null || redEnvelope.getId() == null) {
			throw new ServiceException("修改红包出错！");
		}
		try {
			return redEnvelopeDao.updateRedEnvelope(redEnvelope);
		} catch (Exception e) {
			logger.error("类RedEnvelopeServiceImpl的updateRedEnvelope方法出现异常，原因：", e);
			throw new ServiceException("修改红包出错");
		}
	}

	@Override
	public RedEnvelope getRedEnvelopeById(Long id) {
		if (id == null) {
			throw new ServiceException("获取红包出错,请刷新后重试!");
		}
		try {
			return redEnvelopeDao.getRedEnvelopeById(id);
		} catch (Exception e) {
			logger.error("类RedEnvelopeServiceImpl的getRedEnvelopeById方法出现异常，原因：", e);
			throw new ServiceException("获取红包出错");
		}
	}

	@Override
	public void deleteRedEnvelopeById(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new ServiceException("请选择一条数据删除！");
		}
		try {
			for (Long id : ids) {
				redEnvelopeDao.deleteRedEnvelopeById(id);
			}
		} catch (Exception e) {
			logger.error("类RedEnvelopeServiceImpl的deleteRedEnvelopeById方法出现异常，原因：", e);
			throw new ServiceException("删除红包配置信息出错");
		}

	}

	@Override
	public PageInfo<RedEnvelopeVo> findRedEnvelopeByPage(RedEnvelopeVo redEnvelopeVo, int pageNum,
			int pageSize, String sortField, String order) {
		if (redEnvelopeVo == null || redEnvelopeVo.getOrg() == null
				|| redEnvelopeVo.getOrg().getId() == null) {
			throw new ServiceException("查询红包列表出错,请刷新后重试!");
		}
		try {
			PageInfo<RedEnvelopeVo> pageInfo = redEnvelopeDao.findRedEnvelopeByPage(redEnvelopeVo,
					pageNum, pageSize, sortField, order);
			return pageInfo;
		} catch (Exception e) {
			logger.error("类RedEnvelopeServiceImpl的findRedEnvelopeByPage方法出现异常，原因：", e);
			throw new ServiceException("查询红包列表出错");
		}
	}

	@Override
	public PageInfo<RedEnvelopeVo> findRedEnvelopeForInboxByPage(Fan fan,
			RedEnvelopeVo redEnvelopeVo, int pageNum, int pageSize, String sortField, String order) {
		if (fan == null || fan.getFanId() == null || redEnvelopeVo == null) {
			throw new ServiceException("查询红包列表出错,请刷新后重试!");
		}
		try {
			//获取粉丝信息
			fan = fanService.getFanById(fan);
			//设置查询条件(微信号和当前部门id)
			redEnvelopeVo.setWeChatUserId(fan.getWeChatUserId());
			redEnvelopeVo.setOrg(organizationService.getFullOrgById(ThreadVariable
					.getOrganization().getId()));
			//查询分页
			PageInfo<RedEnvelopeVo> pageInfo = redEnvelopeDao.findRedEnvelopeByPage(redEnvelopeVo,
					pageNum, pageSize, sortField, order);
			return pageInfo;
		} catch (Exception e) {
			logger.error("类RedEnvelopeServiceImpl的findRedEnvelopeForInboxByPage方法出现异常，原因：", e);
			throw new ServiceException("查询红包列表出错");
		}
	}

}
