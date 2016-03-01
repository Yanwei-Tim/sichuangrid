<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<style type='text/css'>
.ui-widget-content #searchSpot{padding:4px 6px;}
.ui-timepicker-div .ui-widget-header { margin-bottom: 8px; }
.ui-timepicker-div dl { text-align: left; }
.ui-timepicker-div dl dt { float: left; clear:left; padding: 0 0 0 5px; width:24px;}
.ui-timepicker-div dl dd { margin: 0 10px 10px 30%;display:block; }
.ui-timepicker-div td { font-size: 90%; }
.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }

.ui-timepicker-rtl{ direction: rtl; }
.ui-timepicker-rtl dl { text-align: right; padding: 0 5px 0 0; }
.ui-timepicker-rtl dl dt{ float: right; clear: right; }
.ui-timepicker-rtl dl dd { margin: 0 45% 10px 10px;}

.hidden{display: none;}
</style>
<div id="dialog-form" title="GPS定位设置" class="container container_24" style="overflow:auto;padding-top:25px;height: 470px;">
	<s:if test='!"view".equals(mode)'>
	    <form id="maintainForm" method="post" action="" >
	</s:if>
	<div class="grid_6 lable-right">
	</div>
	<div class="grid_12">
		<input type="hidden" id="__isALLOW" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_ALLOW"/>'
		value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_ALLOW)"/>"/>
		<input type="checkbox" id="_ck1" onclick="$('#__isALLOW').val(this.checked?1:0)" class="form-txt" <s:if test="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_ALLOW) == true || map.get(@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_ALLOW) == 'true'">checked='checked'</s:if> style="width: 20px; float: left;"/>
		<label for="_ck1" style="float: left;">是否允许定位</label>
	</div>
	<div style="clear:both"></div>
	<div class="grid_6 lable-right">
		定位日期：
	</div>
	<div class="grid_12">
		<input type="checkbox" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_WEEKDAY"/>' value="1" style="width: 20px; float: left;" id="_weekCk1"
		 class="form-txt {required:true, messages:{required:'请选择定位日期'}}" <s:if test="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_WEEKDAY).indexOf('1')!=-1">checked='checked'</s:if>/>
	<label for="_weekCk1" style="float: left;">星期一</label><input type="checkbox" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_WEEKDAY"/>' value="2" style="width: 20px; float: left;" id="_weekCk2"
		 class="form-txt {required:true, messages:{required:'请选择定位日期'}}" <s:if test="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_WEEKDAY).indexOf('2')!=-1">checked='checked'</s:if>/>
	<label for="_weekCk2" style="float: left;">星期二</label><input type="checkbox" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_WEEKDAY"/>' value="3" style="width: 20px; float: left;" id="_weekCk3"
		 class="form-txt {required:true, messages:{required:'请选择定位日期'}}" <s:if test="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_WEEKDAY).indexOf('3')!=-1">checked='checked'</s:if>/>
	<label for="_weekCk3" style="float: left;">星期三</label><input type="checkbox" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_WEEKDAY"/>' value="4" style="width: 20px; float: left;" id="_weekCk4"
		 class="form-txt {required:true, messages:{required:'请选择定位日期'}}" <s:if test="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_WEEKDAY).indexOf('4')!=-1">checked='checked'</s:if>/>
	<label for="_weekCk4" style="float: left;">星期四</label><input type="checkbox" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_WEEKDAY"/>' value="5" style="width: 20px; float: left;" id="_weekCk5"
		 class="form-txt {required:true, messages:{required:'请选择定位日期'}}" <s:if test="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_WEEKDAY).indexOf('5')!=-1">checked='checked'</s:if>/>
	<label for="_weekCk5" style="float: left;">星期五</label><input type="checkbox" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_WEEKDAY"/>' value="6" style="width: 20px; float: left;" id="_weekCk6"
		 class="form-txt {required:true, messages:{required:'请选择定位日期'}}" <s:if test="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_WEEKDAY).indexOf('6')!=-1">checked='checked'</s:if>/>
	<label for="_weekCk6" style="float: left;">星期六</label><input type="checkbox" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_WEEKDAY"/>' value="7" style="width: 20px; float: left;" id="_weekCk7"
		 class="form-txt {required:true, messages:{required:'请选择定位日期'}}" <s:if test="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_WEEKDAY).indexOf('7')!=-1">checked='checked'</s:if>/>
	<label for="_weekCk7" style="float: left;">星期日</label></div>
	<div style="clear:both"></div>
	<div class="grid_6 lable-right">
		<label>GPS定位时间间隔：</label>
	</div>
	<div class="grid_4">
		<input type="text" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_POSITION_TIME_INTERVAL"/>'
		 class="form-txt {required:true,onlyNum_GPS_time_interval: true, messages:{required:'请输入GPS定位时间间隔',onlyNum_GPS_time_interval: 'GPS定位时间间隔只能输入小于10位的数字'}}" maxlength="10" id="MOBILE_GPS_POSITION_TIME_INTERVAL" value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_POSITION_TIME_INTERVAL)"/>" style="width: 60px;" />
		&nbsp;单位：分钟，如:5
	</div>
	<div class="grid_3 lable-right">
		<label>定位时间段：</label>
	</div>
	<div class="grid_6">
		<input id="MOBILE_GPS_STARTDATETIME" class="form-txt" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_STARTDATETIME"/>' readonly="readonly" value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_STARTDATETIME)"/>" style="width: 70px;">
		到<input id="MOBILE_GPS_ENDDATETIME" class="form-txt" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_ENDDATETIME"/>' readonly="readonly" value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_GPS_ENDDATETIME)"/>" style="width: 70px;"></div>
	<div style="clear:both"></div>
   	<s:if test='!"view".equals(mode)'>
		<br/>
		<div class="grid_8"></div>
		<s:if test="userName=='admin'">
			<div class="grid_8">
				<button type="submit" style="height:30px;">保存</button>
				<button type="reset" style="height:30px;">重置</button>
			</div>
		</s:if>
	   </form>
	</s:if>
</div>
<script type="text/javascript">
jQuery.validator.addMethod("onlyNum_GPS_time_interval", function(value, element){
	if(value==null||value==undefined||value==""){return true}
	return /^[0-9]{0,10}$/.test(value);
});
$(document).ready(function(){
	$('#MOBILE_GPS_STARTDATETIME').timepicker({
		timeFormat: 'HH:mm:ss',//格式化时间
		stepHour: 1,//设置步长
		stepMinute: 1,
		stepSecond: 1
	});
	$('#MOBILE_GPS_ENDDATETIME').timepicker({
		timeFormat: 'HH:mm:ss',//格式化时间
		stepHour: 1,//设置步长
		stepMinute: 1,
		stepSecond: 1
	});
	<s:if test='!"view".equals(mode)'>
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
				$("#maintainForm").ajaxSubmit({
			        success: function(data){
						$.messageBox({message:"成功保存GPS定位时间间隔!"});
			 	   },
			 	   error: function(XMLHttpRequest, textStatus, errorThrown){
			 	   		$.messageBox({message:"提交错误",level: "error"	});				
			 	   }
			 	});
			}
		});
	</s:if>
	$("#maintainForm").attr("action","${path}/sysadmin/globalSettingManage/updateGlobalSetting.action");
	$.loadingComp("close");
});
</script>