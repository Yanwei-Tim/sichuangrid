package com.tianque.plugin.weChat.domain.redEnvelopeManagement;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 *微信红包发放记录
 */
public class RedEnvelopesPaymentRecords extends BaseDomain {
	/** 签名 */
	private String sign;
	/** 商户订单号 */
	private String mch_billno;
	/** 商户号 */
	private String mch_id;
	/** 公众账号appid */
	private String wxappid;
	/** 接受收红包的用户 用户在wxappid下的openid */
	private String re_openid;
	/** 付款金额 红包总金额 */
	private Integer total_amount;
	/** 发放成功时间 */
	private String send_time;
	/** 红包订单的微信单号 */
	private String send_listid;
	/** 使用API发放现金红包时返回的红包单号 */
	private String detail_id;
	/** 红包状态 */
	private String status;
	/** 发放类型 */
	private String send_type;
	/** 红包类型 */
	private String hb_type;
	/** 红包个数 */
	private Integer total_num;
	/** 发送失败原因 */
	private String reason;
	/** 红包退款时间 */
	private String refund_time;
	/** 红包退款金额 */
	private Integer refund_amount;
	/** 祝福语 */
	private String wishing;
	/** 备注 */
	private String remark;
	/** 活动名称 */
	private String act_name;
	/** 裂变红包领取列表 */
	private String hblist;
	/** 领取红包的Openid */
	private String openid;
	/** 领取金额 */
	private Integer amount;
	/** 领取红包的时间 */
	private String rcv_time;
	/**红包发放记录所属组织机构*/
	private Organization org;
	/** 微信支付商户号API密钥 */
	private String apiKey;
	/** 发送结束时间 */
	private Date send_time_end;
	/** 发送起始时间 */
	private Date send_time_start;
	/** 粉丝昵称 */
	private String fanName;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getMch_billno() {
		return mch_billno;
	}

	public void setMch_billno(String mch_billno) {
		this.mch_billno = mch_billno;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getWxappid() {
		return wxappid;
	}

	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}

	public String getRe_openid() {
		return re_openid;
	}

	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
	}

	public Integer getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Integer total_amount) {
		this.total_amount = total_amount;
	}

	public String getSend_listid() {
		return send_listid;
	}

	public void setSend_listid(String send_listid) {
		this.send_listid = send_listid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSend_type() {
		return send_type;
	}

	public void setSend_type(String send_type) {
		this.send_type = send_type;
	}

	public String getHb_type() {
		return hb_type;
	}

	public void setHb_type(String hb_type) {
		this.hb_type = hb_type;
	}

	public Integer getTotal_num() {
		return total_num;
	}

	public void setTotal_num(Integer total_num) {
		this.total_num = total_num;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRefund_time() {
		return refund_time;
	}

	public void setRefund_time(String refund_time) {
		this.refund_time = refund_time;
	}

	public Integer getRefund_amount() {
		return refund_amount;
	}

	public void setRefund_amount(Integer refund_amount) {
		this.refund_amount = refund_amount;
	}

	public String getWishing() {
		return wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}

	public String getHblist() {
		return hblist;
	}

	public void setHblist(String hblist) {
		this.hblist = hblist;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getRcv_time() {
		return rcv_time;
	}

	public void setRcv_time(String rcv_time) {
		this.rcv_time = rcv_time;
	}

	public String getSend_time() {
		return send_time;
	}

	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}

	public String getDetail_id() {
		return detail_id;
	}

	public void setDetail_id(String detail_id) {
		this.detail_id = detail_id;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public Date getSend_time_end() {
		return send_time_end;
	}

	public void setSend_time_end(Date send_time_end) {
		this.send_time_end = send_time_end;
	}

	public Date getSend_time_start() {
		return send_time_start;
	}

	public void setSend_time_start(Date send_time_start) {
		this.send_time_start = send_time_start;
	}

	public String getFanName() {
		return fanName;
	}

	public void setFanName(String fanName) {
		this.fanName = fanName;
	}

}
