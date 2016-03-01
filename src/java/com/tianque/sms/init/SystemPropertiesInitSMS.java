package com.tianque.sms.init;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.sms.domain.SmsSendObject;
import com.tianque.sms.domain.Smsquerycondition;
import com.tianque.sms.service.impl.SmsSendObjectServiceImpl;
import com.tianque.sms.service.impl.SmsqueryconditionServiceImpl;

public class SystemPropertiesInitSMS {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private SmsSendObjectServiceImpl smsSendObjectServiceImpl = new SmsSendObjectServiceImpl();
	private SmsqueryconditionServiceImpl smsqueryconditionServiceImpl = new SmsqueryconditionServiceImpl();

	public void init() {
		SmsSendObject sendObj = new SmsSendObject();
		String template = "<div id=\"searchTemplate\" class=\"container container_24\" style=\"margin-top:5px;height:130px;\">\t\n\t\t<div class=\"grid_4 lable-right\">\n\t\t\t<label class=\"form-lbl\">身份证号码：<\\/label>\n\t\t<\\/div>\n\t\t<div class=\"grid_8\">\n\t    \t<input type=\"text\" name=\"${身份证}\" id=\"idCardNo\"  class=\"form-txt\" maxlength=\"18\"\\/>\n\t\t<\\/div>\n\t\t<div class=\"grid_4 lable-right\">\n\t\t\t<label class=\"form-lbl\">姓名：<\\/label>\n\t\t<\\/div>\n\t\t<div class=\"grid_8\">\n\t\t\t<input type=\"text\" id=\"name\" name=\"${姓名}\" class=\"form-txt\" \\/>\n\t\t<\\/div>\n\t\t<div class=\"grid_4 lable-right\">\n            <label class=\"form-lbl\">戒毒情况：<\\/label>\n        <\\/div>\n        <div class=\"grid_8\">\n            <select name=\"${戒毒情况}\" class=\"form-txt\">\n                <option value=\"\"><\\/option>\n                <option value=\"482\">强制戒毒<\\/option>\n                <option value=\"483\">劳教戒毒<\\/option>\n                <option value=\"484\">限期戒毒<\\/option>\n                <option value=\"485\">自愿戒毒<\\/option>\n                <option value=\"486\">社区戒毒<\\/option>\n                <option value=\"487\">其他<\\/option>\n            <\\/select>\n        <\\/div>\n        <div class=\"grid_4 lable-right\">\n\t\t\t<label class=\"form-lbl\">性别：<\\/label>\n\t\t<\\/div>\n\t\t<div class=\"grid_8\">\n\t\t\t<select id=\"conditionGender\" name=\"${性别}\" class=\"form-txt\">\n                <option value=\"\"><\\/option>\n                <option value=\"102\">未知的性别<\\/option>\n                <option value=\"103\">男性<\\/option>\n                <option value=\"104\">女性<\\/option>\n                <option value=\"105\">未说明的性别<\\/option>\n<\\/select>\n\t\t<\\/div>\n        \n        <div class='clearLine'><\\/div>\n        \n\t\t<div class=\"grid_4 lable-right\">\n            <label class=\"form-lbl\">吸毒原因： <\\/label>\n        <\\/div>\n        <div class=\"grid_8\">\n            <select id=\"conditionDrugReason\" name=\"${吸毒原因}\" class=\"form-txt\">\n                 <option value=\"\"><\\/option>\n                <option value=\"469\">受亲友吸毒影响<\\/option>\n                <option value=\"470\">被人诱骗<\\/option>\n                <option value=\"471\">被人逼迫<\\/option>\n                <option value=\"472\">好奇<\\/option>\n                <option value=\"473\">为治病<\\/option>\n                <option value=\"474\">人生受挫<\\/option>\n                <option value=\"475\">寻求刺激<\\/option>\n                <option value=\"476\">其他<\\/option>\n            <\\/select>\n        <\\/div>\n        \n\t\t<div class=\"grid_4 lable-right\">\n\t\t\t<label class=\"form-lbl\">吸毒状态：<\\/label>\n\t\t<\\/div>\n\t\t<div class=\"grid_8\">\n            <select id=\"detoxicateCondition\" name=\"${吸毒状态}\" class=\"form-txt\">\n                 <option value=\"\"><\\/option>\n                <option value=\"488\">在吸<\\/option>\n                <option value=\"489\">停吸<\\/option>\n            <\\/select>\n        <\\/div>\n\t\t<div class='clearLine'><\\/div>\n\t\t <div class=\"grid_4 lable-right\">\n\t\t\t<label class=\"form-lbl\">出生日期 ：<\\/label>\n\t\t<\\/div>\n\t\t<div class=\"grid_3\">\n\t\t\t<input type=\"text\" id=\"conditionBirthday\" name=\"${出生日期起始时间}\" readonly=\"readonly\" class=\"form-txt\" \\/>\n\t\t<\\/div>\n\t\t<div class=\"grid_2\" align=\"center\" style=\"\">—<\\/div>\n\t\t<div class=\"grid_3\">\n\t\t\t<input type=\"text\" id=\"endConditionBirthday\" name=\"${出生日期结束时间}\" readonly=\"readonly\" class=\"form-txt\" \\/>\n\t\t<\\/div>\n\t\t\n\t\t <div class=\"grid_4 lable-right\">\n\t\t\t<label class=\"form-lbl\">查获日期 ：<\\/label>\n\t\t<\\/div>\n\t\t<div class=\"grid_3\">\n\t\t\t<input type=\"text\" id=\"conditionFerretOutDate\" name=\"${查获日期起始时间}\" readonly=\"readonly\" class=\"form-txt\" \\/>\n\t\t<\\/div>\n\t\t<div class=\"grid_2\" align=\"center\" style=\"\">—<\\/div>\n\t\t<div class=\"grid_3\">\n\t\t\t<input type=\"text\" id=\"endConditionFerretOutDate\" name=\"${查获日期结束时间}\" readonly=\"readonly\" class=\"form-txt\" \\/>\n\t\t<\\/div>\n\t\n<\\/div>\n<script type=\"text\\/javascript\">\n\n$(document).ready(function(){\n\t$('#conditionFerretOutDate').datePicker({\n\t\tyearRange:'1930:2060',\n\t\tmaxDate:'+0d'\n\t});\n\t$('#endConditionFerretOutDate').datePicker({\n\t\tyearRange:'1930:2060',\n\t\tmaxDate:'+0d'\n\t});\n\t$('#conditionBirthday').datePicker({\n\t\tyearRange:'1930:2060',\n\t\tmaxDate:'+0d'\n\t});\n\t$('#endConditionBirthday').datePicker({\n\t\tyearRange:'1930:2060',\n\t\tmaxDate:'+0d'\n\t});\n});\n\n<\\/script>";
		sendObj.setName("吸毒人员");
		sendObj.setTemplate(template);
		sendObj.setDescription("吸毒人员查询模板");
		sendObj.setEnable(true);
		sendObj.setCreateUser("admin");
		sendObj.setCreateDate(new Date());
		sendObj = smsSendObjectServiceImpl.add(sendObj);
		// 吸毒人员sql基础查询语句
		String sql = "";
		sql = "select name as name, mobilenumber as mobile, orgid , orginternalcode from druggys where 1=1 and mobilenumber is not null";
		addSmsquery(sendObj, "table", "吸毒人员sql基础查询语句", "table", true, sql);
		// 吸毒人员性别查询语句
		sql = " and gender=${gender} ";
		addSmsquery(sendObj, "gender", "性别", "number", false, sql);
		// 吸毒人员出生日期起始时间查询语句
		sql = " and birthday > to_date('${startBirthday}','yyyy-mm-dd') ";
		addSmsquery(sendObj, "startBirthday", "出生日期起始时间", "date", false, sql);
		// 吸毒人员出生日期结束时间查询语句
		sql = " and birthday < to_date('${endBirthday}','yyyy-mm-dd') ";
		addSmsquery(sendObj, "endBirthday", "出生日期结束时间", "date", false, sql);
		// 吸毒人员姓名查询语句
		sql = " and name like '${name}%' ";
		addSmsquery(sendObj, "name", "姓名", "varchar2", false, sql);
		// 吸毒人员身份证查询语句
		sql = " and idcardno like '${idcardno}%' ";
		addSmsquery(sendObj, "idcardno", "身份证", "varchar2", false, sql);
		// 吸毒人员查获日期起始时间查询语句
		sql = " and ferretoutdate > to_date('${startFerretoutdate}','yyyy-mm-dd') ";
		addSmsquery(sendObj, "startFerretoutdate", "查获日期起始时间", "date", false,
				sql);
		// 吸毒人员查获日期结束时间查询语句
		sql = " and ferretoutdate < to_date('${endFerretoutdate}','yyyy-mm-dd') ";
		addSmsquery(sendObj, "endFerretoutdate", "查获日期结束时间", "date", false, sql);
		// 吸毒人员吸毒原因查询语句
		sql = " and drugreason = ${drugreason} ";
		addSmsquery(sendObj, "drugreason", "吸毒原因", "number", false, sql);
		// 吸毒人员吸毒状态查询语句
		sql = " and detoxicatecondition = ${detoxicatecondition} ";
		addSmsquery(sendObj, "detoxicatecondition", "吸毒状态", "number", false,
				sql);
		// 吸毒人员戒毒情况查询语句
		sql = " and detoxicatecase = ${detoxicatecase} ";
		addSmsquery(sendObj, "detoxicatecase", "戒毒情况", "number", false, sql);

		logger.info("吸毒人员查询模板初始化结束!");//
	}

	private void addSmsquery(SmsSendObject sendObj, String key,
			String description, String type, boolean isNull, String sql) {
		Smsquerycondition smsquerycondition = new Smsquerycondition();
		smsquerycondition.setSmsSendObject(sendObj);
		smsquerycondition.setKey(key);
		smsquerycondition.setDescription(description);
		smsquerycondition.setType(type);
		smsquerycondition.setIsNull(isNull);
		smsquerycondition.setCreateUser("admin");
		smsquerycondition.setCreateDate(new Date());
		smsqueryconditionServiceImpl.add(smsquerycondition);
	}

}
