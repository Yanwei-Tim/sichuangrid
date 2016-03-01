package com.tianque.plugin.weChat.vo;

/**
 *微信红包(发送红包至微信的封装对象)
 */
public class RedEnvelopeObjectVo {

	/** 签名 (需要满足微信的签名生成算法 )*/
	private String sign;
	/** 商户订单号（每个订单号必须唯一） 组成： mch_id+yyyymmdd+10位一天内不能重复的数字*/
	private String mch_billno;
	/** 商户号 */
	private String mch_id;
	/** 公众账号appid */
	private String wxappid;
	/** 提供方名称 */
	private String nick_name ;
	/** 商户名称 */
	private String send_name;
	/** 最小红包金额 */
	private Integer min_value;
	/** 最大红包金额 */
	private Integer max_value;
	/** 付款金额 */
	private Integer total_amount ;
	/** 红包发放总人数 total_num=1(暂时接口只能为1)  */
	private Integer total_num ;
	/** 活动名称 */
	private String act_name;
	/** 接受收红包的用户 用户在wxappid下的openid */
	private String re_openid;
	/** 备注 */
	private String remark;
	/** 祝福语 */
	private String wishing;
	/** 随机字符串 */
	private String nonce_str;
	/** 调用接口的机器Ip地址  */
	private String client_ip;
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
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getSend_name() {
		return send_name;
	}
	public void setSend_name(String send_name) {
		this.send_name = send_name;
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
	public Integer getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(Integer total_amount) {
		this.total_amount = total_amount;
	}
	public Integer getTotal_num() {
		return total_num;
	}
	public void setTotal_num(Integer total_num) {
		this.total_num = total_num;
	}
	public String getAct_name() {
		return act_name;
	}
	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}
	public String getRe_openid() {
		return re_openid;
	}
	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
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
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}

}
