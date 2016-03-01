<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="出租房查询"  class="container container_24">
	<input type="hidden" name="searchLettingHouseVo.orgInternalCode" id="query_orgInternalCode" 
	value="${searchLettingHouseVo.orgInternalCode}" >
	<div  class="grid_4 lable-right">
        <label class="form-lbl">房东姓名：</label>
    </div>
    <div class="grid_5">     
        <input type="text" name="searchLettingHouseVo.name" id="query_name" maxlength="20" class="form-txt" />
    </div>
	<div  class="grid_4 lable-right">
                <label class="form-lbl">编号：</label>
            </div>
            <div class="grid_4">     
                <input type="text" name="searchLettingHouseVo.lettingHouseNo" id="query_lettingHouseNo" maxlength="20" class="form-txt" />
            </div>
            <div  class="grid_3 lable-right">
                <label class="form-lbl">联系电话：</label>
            </div>
            <div class="grid_4">
                <input type="text" name="searchLettingHouseVo.telephone" id="query_telephone" maxlength="20" class="form-txt" />
            </div>
            <div class='clearLine'>&nbsp;</div>
            
            <div  class="grid_4 lable-right">
                <label class="form-lbl">身份证号码：</label>
            </div>
            <div class="grid_5">
                <input type="text" name="searchLettingHouseVo.idCardNo" id="query_idCardNo" maxlength="18" class="form-txt" />
            </div>
            <div  class="grid_4 lable-right">
                <label class="form-lbl">联系手机：</label>
            </div>
            <div class="grid_4" >
                <input type="text" name="searchLettingHouseVo.mobileNumber" id="query_mobileNumber" maxlength="11" class="form-txt" />
            </div>
            <div class="grid_3 lable-right">
                <label class="form-lbl">出租房类别：</label>
            </div>
            <div class="grid_4">     
                <select name="searchLettingHouseVo.type.id" id="query_type" class="form-select" >
                    <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_TYPE"/>
                </select>
            </div>
            <div class='clearLine'>&nbsp;</div>
            <div class="grid_4 lable-right">
                <label class="form-lbl">出租房结构：</label>
            </div>
            <div class="grid_5">     
                <select name="searchLettingHouseVo.lettingHouseStruts.id" id="query_lettingHouseStruts" class="form-select" >
                    <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS" />
                </select>
            </div>
            <div  class="grid_4 lable-right">
                <label class="form-lbl">登记日期 从：</label>
            </div>
            <div class="grid_4">     
                    <input type="text" name="searchLettingHouseVo.registDateFrom" id="query_registDateFrom" maxlength="20" readonly class="form-txt" />
            </div>
            <div  class="grid_3 lable-right">
                <label class="form-lbl">到：</label>
            </div>
            <div class="grid_4">     
                    <input type="text" name="searchLettingHouseVo.registDateEnd" id="query_registDateEnd" maxlength="20" readonly class="form-txt" />
            </div>
            <div class='clearLine'>&nbsp;</div>
            <div  class="grid_4 lable-right">
                <label class="form-lbl">房东地址：</label>
            </div>
            <div class="grid_13">
               <input type="text" name="searchLettingHouseVo.landlordAddr" id="query_landlordAddr" maxlength="50" class="form-txt" style="width:99.5%"/>
            </div>
            <div class='clearLine'>&nbsp;</div>
            <hr>
            
            <div class='clearLine'>&nbsp;</div>
            <div  class="grid_4 lable-right">
                <label class="form-lbl">出租房地址：</label>
            </div>
            <div class="grid_13">
                <input type="text" name="searchLettingHouseVo.lettingHouseAddr" id="query_lettingHouseAddr"  maxlength="50" class="form-txt" style="width:99.5%"/>
            </div>
            <div class="grid_3 lable-right">
                <label class="form-lbl">隐患程度：</label>
            </div>
            <div class="grid_4">
                <select name="searchLettingHouseVo.hiddenTroubleLevel.id" id="query_hiddenTroubleLevel" class="form-select" >
                    <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@HIDDEN_TROUBLE_LEVEL"/>
                </select>
            </div>
            <div class='clearLine'>&nbsp;</div>
            <div class="grid_4 lable-right">
                <label class="form-lbl">出租用途：</label>
            </div>
            <div class="grid_5">     
                <select name="searchLettingHouseVo.usage.id" id="query_usage" class="form-select" >
                    <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_USAGE"/>
                </select>
            </div>
            <div  class="grid_4 lable-right">
                <label class="form-lbl">房间数 从：</label>
            </div>
            <div class="grid_4">
                <input type="text" name="searchLettingHouseVo.roomNumbersFrom" id="query_roomNumbersFrom" maxlength="6" class="form-txt"/>
            </div>
            <div  class="grid_3 lable-right">
                <label class="form-lbl">到：</label>
            </div>
            <div class="grid_4">
                <input type="text" name="searchLettingHouseVo.roomNumbersEnd" id="query_roomNumbersEnd" maxlength="6" class="form-txt"/>
            </div>
            <div class="grid_4 lable-right">
                <label class="form-lbl">出租房性质：</label>
            </div>
            <div class="grid_5">     
                <select name="searchLettingHouseVo.lettingHouseProperty.id" id="query_lettingHouseProperty" class="form-select" >
                    <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_PROPERTY"/>
                </select>
            </div>
            <div  class="grid_4 lable-right">
                <label class="form-lbl">限住人数 从：</label>
            </div>
            <div class="grid_4">
                <input type="text" name="searchLettingHouseVo.limitPersonsFrom" id="query_limitPersonsFrom" maxlength="6" class="form-txt" />
            </div>
            <div  class="grid_3 lable-right">
                <label class="form-lbl">到：</label>
            </div>
            <div class="grid_4">
                <input type="text" name="searchLettingHouseVo.limitPersonsEnd" id="query_limitPersonsEnd" maxlength="6" class="form-txt" />
            </div>
            <div  class="grid_4 lable-right">
                <label class="form-lbl">出租房面积 从：</label>
            </div>
            <div class="grid_5">
                <input type="text" name="searchLettingHouseVo.lettingHouseAreasFrom" id="query_lettingHouseAreasFrom" maxlength="8" class="form-txt" />
            </div>
            <div class="grid_1">M<sup>2</sup></div>
            <div  class="grid_3 lable-right">
                <label class="form-lbl">到：</label>
            </div>
            <div class="grid_4">
                <input type="text" name="searchLettingHouseVo.lettingHouseAreasEnd" id="query_lettingHouseAreasEnd" maxlength="8" class="form-txt" />
            </div>
            <div class="grid_1">M<sup>2</sup></div>
            <div class='clearLine'>&nbsp;</div>
            <div  class="grid_4 lable-right">
                <label class="form-lbl">实住人数 从：</label>
            </div>
            <div class="grid_5">
                <input type="text" name="searchLettingHouseVo.realityPersonsFrom" id="query_realityPersonsFrom" maxlength="6" class="form-txt" />
            </div>
            <div  class="grid_4 lable-right">
                <label class="form-lbl">到：</label>
            </div>
            <div class="grid_4">
                <input type="text" name="searchLettingHouseVo.realityPersonsEnd" id="query_realityPersonsEnd" maxlength="6" class="form-txt" />
            </div>
            <div class='clearLine'>&nbsp;</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	var landlordName = $("#searchByLandlordName").val();
	if(landlordName=="请输入房东姓名"){
		$("#query_name").val("");
	}else{
		$("#query_name").val(landlordName);
	}
	$("#query_registDateFrom").datePicker({
        yearRange:"1900:2030",
        dateFormat:"yy-mm-dd",
        maxDate:"+0d",
        onSelect: function(dateText, inst) {
            if(dateText!=null&&dateText!=""){
                var dateUnit=dateText.split("-");
                var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
                $("#query_registDateEnd").datepicker("option","minDate",date);
            }
        }
    }); 

    $("#query_registDateEnd").datePicker({
        yearRange:"1900:2030",
        dateFormat:"yy-mm-dd",
        maxDate:"+0d",
        onSelect: function(dateText, inst) {
            if(dateText!=null&&dateText!=""){
                var dateUnit=dateText.split("-");
                var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
                $("#query_registDateFrom").datepicker("option", "maxDate",date);
            }
        }
    });
});

</script>