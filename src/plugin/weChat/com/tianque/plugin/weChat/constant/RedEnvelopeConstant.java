package com.tianque.plugin.weChat.constant;

public class RedEnvelopeConstant {
	/**
	 * 随机金额红包
	 */
	public final static int RANDOMENVELOPE = 1;
	public final static String RANDOMENVELOPENAME = "随机金额红包";
	
	/**
	 * 固定金额红包
	 */
	public final static int SINGLEENVELOPE = 2;
	public final static String SINGLEENVELOPENAME = "固定金额红包";
	
	/**
	 * 自定义字符串
	 */
	public final static String LETTERANDDIGITAL_STRING ="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public final static String DIGITAL_STRING ="0123456789";
	
	/**
	 * 自定义length
	 */
	public final static int TEN_LENGTH = 10;
	public final static int THIRTYTWO_LENGTH = 32;
	/**
	 * 微信接口（暂时红包发放总人数只能为1）
	 */
	public final static int TOTAL_NUM = 1;
	
	/**
	 * 微信密钥（暂时这么写，需要配置）
	 */
	public final static String REDENVELOPE_KEY ="tianquekejitianqueshuaigeweixing";
	
	/**
	 * 返回信息字符串标志
	 */
	public final static String RETURN_CODE_FAIL ="FAIL";//失败
	public final static String RETURN_CODE_SUCCESS ="SUCCESS";//成功
	
	/**
	 * 红包证书存放路径 
	 */
	public final static String REDENVELOPE_APICLIENT_CERT ="D:/apiclient_cert.p12";
	
	/**
	 * 订单类型 
	 */
	public final static String BILL_TYPE ="MCHT";//MCHT:通过商户订单号获取红包信息
	
	/**
	 * 发放类型
	 */
	public final static String API ="API";
	public final static String API_NAME ="通过API接口发放 ";
	public final static String UPLOAD ="UPLOAD";
	public final static String UPLOAD_NAME ="通过上传文件方式发放";
	public final static String ACTIVITY ="ACTIVITY";
	public final static String ACTIVITY_NAME ="通过活动方式发放";
	
	/**
	 * 红包类型(GROUP:裂变红包 ;NORMAL:普通红包 )
	 */
	public final static String GROUP ="GROUP";
	public final static String GROUP_NAME ="裂变红包";
	public final static String NORMAL ="NORMAL";
	public final static String NORMAL_NAME ="普通红包";
	
	/**
	 * SENDING:发放中;SENT:已发放待领取;FAILED：发放失败;RECEIVED:已领取;REFUND:已退款 
	 */
	public final static String SENDING ="SENDING";
	public final static String SENDING_NAME ="发放中";
	public final static String SENT ="SENT";
	public final static String SENT_NAME ="已发放待领取";
	public final static String FAILED ="FAILED";
	public final static String FAILED_NAME ="发放失败";
	public final static String RECEIVED ="RECEIVED";
	public final static String RECEIVED_NAME ="已领取";
	public final static String REFUND ="REFUND";
	public final static String REFUND_NAME ="已退款 ";
	
	

	
	
}
