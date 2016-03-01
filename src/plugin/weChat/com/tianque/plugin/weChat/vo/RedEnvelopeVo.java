package com.tianque.plugin.weChat.vo;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 *微信红包vo
 */
public class RedEnvelopeVo extends BaseDomain {

	/** 红包类型 */
	private Integer redEnvelopeType;
	/** 最小红包金额 */
	private Integer min_value;
	/** 最大红包金额 */
	private Integer max_value;
	/** 单个红包金额 */
	private Integer singleEnvelope_value;
	/** 活动名称 */
	private String act_name;
	/** 商户名称 */
	private String send_name;
	/** 备注 */
	private String remark;
	/** 祝福语 */
	private String wishing;
	/** 分享文案 */
	private String share_content;
	/** 分享链接 */
	private String share_url;
	/**红包所属组织机构*/
	private Organization org;
	/** 发放次数 */
	private int grantNum;
	/** 发放人次 */
	private int grantPeopleNum;
	/** 商户号 */
	private String mch_id;
	/** 公众账号appid */
	private String wxappid;
	/** 公众账号(微信连接号) */
	private String weChatUserId;
	/** 提供方名称 */
	private String nick_name;
	/** 微信支付商户号API密钥 */
	private String apiKey;

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}

	public String getSend_name() {
		return send_name;
	}

	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWishing() {
		return wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}

	public String getShare_content() {
		return share_content;
	}

	public void setShare_content(String share_content) {
		this.share_content = share_content;
	}

	public String getShare_url() {
		return share_url;
	}

	public void setShare_url(String share_url) {
		this.share_url = share_url;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public Integer getRedEnvelopeType() {
		return redEnvelopeType;
	}

	public void setRedEnvelopeType(Integer redEnvelopeType) {
		this.redEnvelopeType = redEnvelopeType;
	}

	public Integer getMin_value() {
		return min_value;
	}

	public void setMin_value(Integer min_value) {
		this.min_value = min_value;
	}

	public Integer getMax_value() {
		return max_value;
	}

	public void setMax_value(Integer max_value) {
		this.max_value = max_value;
	}

	public Integer getSingleEnvelope_value() {
		return singleEnvelope_value;
	}

	public void setSingleEnvelope_value(Integer singleEnvelope_value) {
		this.singleEnvelope_value = singleEnvelope_value;
	}

	public int getGrantNum() {
		return grantNum;
	}

	public void setGrantNum(int grantNum) {
		this.grantNum = grantNum;
	}

	public int getGrantPeopleNum() {
		return grantPeopleNum;
	}

	public void setGrantPeopleNum(int grantPeopleNum) {
		this.grantPeopleNum = grantPeopleNum;
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

	public String getWeChatUserId() {
		return weChatUserId;
	}

	public void setWeChatUserId(String weChatUserId) {
		this.weChatUserId = weChatUserId;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

}
