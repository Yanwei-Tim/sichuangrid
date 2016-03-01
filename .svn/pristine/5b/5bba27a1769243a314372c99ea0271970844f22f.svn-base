package com.tianque.plugin.weChat.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.constant.RedEnvelopeConstant;
import com.tianque.plugin.weChat.dao.RedEnvelopesPaymentRecordsDao;
import com.tianque.plugin.weChat.domain.redEnvelopeManagement.RedEnvelopesPaymentRecords;
import com.tianque.plugin.weChat.proxy.service.BaseHttpClientService;
import com.tianque.plugin.weChat.service.RedEnvelopesPaymentRecordsService;
import com.tianque.plugin.weChat.util.Constants;
import com.tianque.plugin.weChat.util.MessageUtil;
import com.tianque.plugin.weChat.util.RedEnvelopeUtils;
import com.tianque.plugin.weChat.vo.RedEnvelopesPaymentRecordsVo;

/**
 * 微信红包发放记录
 */
@Service("redEnvelopesPaymentRecordsService")
@Transactional
public class RedEnvelopesPaymentRecordsServiceImpl extends AbstractBaseService implements
		RedEnvelopesPaymentRecordsService {
	@Autowired
	private RedEnvelopesPaymentRecordsDao redEnvelopesPaymentRecordsDao;
	@Autowired
	private BaseHttpClientService baseHttpClientService;

	@Override
	public RedEnvelopesPaymentRecords addRedEnvelopesPaymentRecords(
			RedEnvelopesPaymentRecords redEnvelopesPaymentRecords) {
		verificationRedEnvelopesPaymentRecords(redEnvelopesPaymentRecords);
		try {
			return redEnvelopesPaymentRecordsDao
					.addRedEnvelopesPaymentRecords(redEnvelopesPaymentRecords);
		} catch (Exception e) {
			logger.error(
					"类RedEnvelopesPaymentRecordsServiceImpl的addRedEnvelopesPaymentRecords方法出现异常，原因：",
					e);
			throw new ServiceException("新增红包发放记录出错");
		}
	}

	/**
	 * 验证
	 */
	private void verificationRedEnvelopesPaymentRecords(
			RedEnvelopesPaymentRecords redEnvelopesPaymentRecords) {
		if (redEnvelopesPaymentRecords == null) {
			throw new ServiceException("新增红包发放记录出错！");
		}
		if (redEnvelopesPaymentRecords.getOrg() == null
				|| redEnvelopesPaymentRecords.getOrg().getId() == null) {
			throw new ServiceException("当前辖区不能为空！");
		}
	}

	@Override
	public RedEnvelopesPaymentRecords getRedEnvelopesPaymentRecordsById(Long id) {
		if (id == null) {
			throw new ServiceException("获取红包发放记录出错!");
		}
		try {
			return redEnvelopesPaymentRecordsDao.getRedEnvelopesPaymentRecordsById(id);
		} catch (Exception e) {
			logger.error(
					"类RedEnvelopesPaymentRecordsServiceImpl的getRedEnvelopesPaymentRecordsById方法出现异常，原因：",
					e);
			throw new ServiceException("获取红包发放记录出错");
		}
	}

	@Override
	public void deleteRedEnvelopesPaymentRecordsById(Long id) {

	}

	@Override
	public PageInfo<RedEnvelopesPaymentRecords> findRedEnvelopesPaymentRecordsByPage(
			RedEnvelopesPaymentRecords redEnvelopesPaymentRecords, int pageNum, int pageSize,
			String sortField, String order) {
		if (redEnvelopesPaymentRecords == null || redEnvelopesPaymentRecords.getOrg() == null
				|| redEnvelopesPaymentRecords.getOrg().getId() == null) {
			throw new ServiceException("查询红包发放记录列表出错,请刷新后重试!");
		}
		try {
			PageInfo<RedEnvelopesPaymentRecords> pageInfo = redEnvelopesPaymentRecordsDao
					.findRedEnvelopesPaymentRecordsByPage(redEnvelopesPaymentRecords, pageNum,
							pageSize, sortField, order);
			return pageInfo;
		} catch (Exception e) {
			logger.error(
					"类RedEnvelopesPaymentRecordsServiceImpl的findRedEnvelopesPaymentRecordsByPage方法出现异常，原因：",
					e);
			throw new ServiceException("查询红包发放记录列表出错");
		}
	}

	@Override
	public RedEnvelopesPaymentRecords viewRedEnvelopesPaymentRecords(
			RedEnvelopesPaymentRecords record) {
		RedEnvelopesPaymentRecordsVo redRecordVo = null;
		Map<String, String> redEnvelopeMap = null;
		Map<String, String> messageMap = null;
		String result_code = null;
		String return_msg = null;
		String nonce_str = null;
		String filePath = null;
		String backInfo = null;
		String xmlStr = null;
		String url = null;
		try {
			//32位随机字符串
			nonce_str = RedEnvelopeUtils.getRandomString(
					RedEnvelopeConstant.LETTERANDDIGITAL_STRING,
					RedEnvelopeConstant.THIRTYTWO_LENGTH);
			redEnvelopeMap = setSignatureParmMap(record, nonce_str);
			//生成签名
			String sign = RedEnvelopeUtils.getSignature(redEnvelopeMap, record.getApiKey());
			//配置红包(查看)对象
			redRecordVo = configurationRedEnvelopeRecordObject(record, nonce_str, sign);
			//生成xml格式,满足微信接口xml格式需要
			xmlStr = redEnvelopesPaymentRecordsVoToXml(redRecordVo);
			filePath = GridProperties.CERTIFICATE_STORE_DIRECTORY;
//			filePath = RedEnvelopeConstant.REDENVELOPE_APICLIENT_CERT;
			url = Constants.REDENVELOPE_RECORD_VIEW_URL + "&requestType=viewRedEnvelope&content="
					+ URLEncoder.encode(xmlStr) + "&filePath=" + filePath + "&merchantID="
					+ record.getMch_id();
			//请求微信代理,并返回相应信息
			backInfo = baseHttpClientService.postMethod(url);
			//处理微信代理，返回的信息
			backInfo = URLDecoder.decode(backInfo, "UTF-8");
			InputStream iStream = new ByteArrayInputStream(backInfo.getBytes());
			messageMap = MessageUtil.parseXml(iStream);
			return_msg = messageMap.get("return_msg");
			result_code = messageMap.get("result_code");
		} catch (Exception e) {
			logger.error(
					"类RedEnvelopesPaymentRecordsServiceImpl的viewRedEnvelopesPaymentRecords方法出现异常，原因：",
					e);
			throw new ServiceException("查看红包出错");
		}
		if (StringUtil.isStringAvaliable(result_code)
				&& result_code.equals(RedEnvelopeConstant.RETURN_CODE_FAIL)) {
			//由于微信提供查看的接口，返回的提示有误，所以在后台将提示信息做了替换处理
			if (return_msg.indexOf("发红包") != -1) {
				return_msg = return_msg.replaceAll("发红包", "查看红包");
			}
			throw new ServiceException("查看红包出错,原因：" + return_msg);
		}
		//通过微信返回的内容,设置红包查看对象
		setRedEnvelopeViewObject(record, messageMap);
		//logger.error("代理端返回：" + messageMap);
		return record;
	}

	/**
	 * 通过微信返回的内容,设置红包查看对象
	 */
	private void setRedEnvelopeViewObject(RedEnvelopesPaymentRecords record,
			Map<String, String> messageMap) {
		record.setMch_billno(messageMap.get("mch_billno"));
		record.setMch_id(messageMap.get("mch_id"));
		record.setDetail_id(messageMap.get("detail_id"));
		String status = messageMap.get("status");
		if (status.equalsIgnoreCase(RedEnvelopeConstant.SENT)) {
			status = RedEnvelopeConstant.SENT_NAME;
		} else if (status.equalsIgnoreCase(RedEnvelopeConstant.SENDING)) {
			status = RedEnvelopeConstant.SENDING_NAME;
		} else if (status.equalsIgnoreCase(RedEnvelopeConstant.FAILED)) {
			status = RedEnvelopeConstant.FAILED_NAME;
		} else if (status.equalsIgnoreCase(RedEnvelopeConstant.RECEIVED)) {
			status = RedEnvelopeConstant.RECEIVED_NAME;
		} else if (status.equalsIgnoreCase(RedEnvelopeConstant.REFUND)) {
			status = RedEnvelopeConstant.REFUND_NAME;
		}
		record.setStatus(status);
		String sendType = messageMap.get("send_type");
		if (sendType.equalsIgnoreCase(RedEnvelopeConstant.API)) {
			sendType = RedEnvelopeConstant.API_NAME;
		}
		record.setSend_type(sendType);
		String hb_type = messageMap.get("hb_type");
		if (hb_type.equalsIgnoreCase(RedEnvelopeConstant.NORMAL)) {
			hb_type = RedEnvelopeConstant.NORMAL_NAME;
		}
		record.setHb_type(hb_type);
		String total_num_str = messageMap.get("total_num");
		if (StringUtil.isStringAvaliable(total_num_str) && !total_num_str.equalsIgnoreCase("null")) {
			record.setTotal_num(Integer.valueOf(total_num_str));
		}
		String total_amount_str = messageMap.get("total_amount");
		if (StringUtil.isStringAvaliable(total_amount_str)
				&& !total_amount_str.equalsIgnoreCase("null")) {
			record.setTotal_amount(Integer.valueOf(total_amount_str));
		}
		record.setReason(messageMap.get("reason"));
		record.setSend_time(messageMap.get("send_time"));
		record.setRefund_time(messageMap.get("refund_time"));
		String refund_amount_str = messageMap.get("refund_amount");
		if (StringUtil.isStringAvaliable(refund_amount_str)
				&& !refund_amount_str.equalsIgnoreCase("null")) {
			record.setRefund_amount(Integer.valueOf(refund_amount_str));
		}
		record.setWishing(messageMap.get("wishing"));
		record.setRemark(messageMap.get("remark"));
		record.setAct_name(messageMap.get("act_name"));
		record.setOpenid(messageMap.get("openid"));
		String amount_str = messageMap.get("amount");
		if (StringUtil.isStringAvaliable(amount_str) && !amount_str.equalsIgnoreCase("null")) {
			record.setAmount(Integer.valueOf(amount_str));
		}
		record.setRcv_time(messageMap.get("rcv_time"));
	}

	/**
	 * 设置签名参数，用于获取签名
	 */
	private Map<String, String> setSignatureParmMap(RedEnvelopesPaymentRecords record,
			String nonce_str) {
		Map<String, String> redEnvelopeMap = new HashMap<String, String>();
		redEnvelopeMap.put("mch_id", record.getMch_id());
		redEnvelopeMap.put("mch_billno", record.getMch_billno());
		redEnvelopeMap.put("nonce_str", nonce_str);
		redEnvelopeMap.put("appid", record.getWxappid());
		redEnvelopeMap.put("bill_type", RedEnvelopeConstant.BILL_TYPE);
		return redEnvelopeMap;
	}

	/**
	 * 配置(查看)红包对象
	 */
	private RedEnvelopesPaymentRecordsVo configurationRedEnvelopeRecordObject(
			RedEnvelopesPaymentRecords record, String nonce_str, String sign) {
		RedEnvelopesPaymentRecordsVo redRecordsVo = new RedEnvelopesPaymentRecordsVo();
		redRecordsVo.setNonce_str(nonce_str);
		redRecordsVo.setMch_id(record.getMch_id());
		redRecordsVo.setMch_billno(record.getMch_billno());
		redRecordsVo.setAppid(record.getWxappid());
		redRecordsVo.setSign(sign);
		redRecordsVo.setBill_type(RedEnvelopeConstant.BILL_TYPE);
		return redRecordsVo;
	}

	/**
	 * 转化为微信接口规定的xml格式
	 */
	private String redEnvelopesPaymentRecordsVoToXml(RedEnvelopesPaymentRecordsVo redcordVo) {
		String xmlStr = MessageUtil.redEnvelopesPaymentRecordsVoToXml(redcordVo);
		xmlStr = xmlStr.replaceAll("__", "_");
		return xmlStr;
	}
}
