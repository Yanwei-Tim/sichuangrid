<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="/includes/baseInclude.jsp" />
	<div id="workingSituation" class="container container_24">
		<form action="${path}/baseInfo/workingSituationManage/addWorkingSituation.action" method="post" id="maintainFormForWorkingSituationAdd">
		
	<input type="hidden" name="workingSituation.organization.orgName" id="orgName" value="${workingSituation.organization.orgName }"/>
	<input type="hidden" name="workingSituation.id" id="workingSituationId" value="${workingSituation.id }"/>
	
	<input id="organizationId"	type="hidden" name="workingSituation.organization.id" value="${workingSituation.organization.id}" />
		<div class="grid_6 lable-right">
			<label class="form-lbl">发生日期：</label>
		</div>
		<div class="grid_14">
			<input type="text" name="workingSituation.occurrenceDate" id="occurrenceDate" class="form-txt"
		value="<s:date name="workingSituation.occurrenceDate" format="yyyy-MM-dd HH:mm:ss"/>" readonly style="background-color: white"/>
		
		</div>
		<div class="grid_6 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">民警姓名：</label>
		</div>
		<div class="grid_14">
          <input type="text" name="workingSituation.policeName" id="policeName" value="${workingSituation.policeName}" class="form-txt" />
        </div>
		
		<div class="grid_6 lable-right">
			<label class="form-lbl">身份证号码：</label>
		</div>
		<div class="grid_14">
			<input name="workingSituation.idCard" value="${workingSituation.idCard}"  class="form-txt {idCard:true,messages:{idCard:$.format('请输入一个合法的身份证号码')}}" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lbl">电话号码：</label>
		</div>
		<div class="grid_14">
			<input name="workingSituation.phone" value="${workingSituation.phone}"  class="form-txt {phoneAndMobile:true,messages:{phoneAndMobile:$.format('请输入以固定电话：028-87653333或者手机：15102888888为格式的号码')}}" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_6 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">工作内容：</label>
		</div>
		<div class="grid_14">
		  <select name="workingSituation.workcontent.id"  class="form-txt" id="workcontent">
		  	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@WORKING_CONTENT_TYPE" defaultValue="${workingSituation.workcontent.id}" />
		  </select>
		</div>
		<div class="grid_6 lable-right">
			<label class="form-lbl">备注：</label>
		</div>
		<div class="grid_14">
            <textarea rows="4" name="workingSituation.remark"  cols="" class="form-txt">${workingSituation.remark}</textarea>
        </div>
		
		
		<div class='clearLine'>&nbsp;</div>
			</form>
	 </div>	
<script type="text/javascript">
jQuery.validator.addMethod("phoneAndMobile", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var mobile = /^(1[3|4|5|7|8][0-9])+\d{8}$/;
	var length = value.length;
	if(length == 11 && mobile.test(value)){return true;}
	var phone = /^([0-9]{3,4}-)+[0-9]{7,8}$/;	
	if (value.match(phone)==null) {return false;}
	return true
});
<s:if test="workingSituation.id!=null">
$("#maintainFormForWorkingSituationAdd").attr("action","${path}/baseInfo/workingSituationManage/updateWorkingSituation.action");
</s:if>


$(document).ready(function(){
	$("#maintainFormForWorkingSituationAdd").formValidate({
		submitHandler: function(form){
		
		$(form).ajaxSubmit({
			async:false,
			success : function(data) {
				if (!data) {
					$.messageBox({
						message : "信息保存失败!",
						level : "error"
					});
					return;
				}
	
				$.messageBox({message:"信息保存成功!"}); 
				$("#workingSituationList").trigger("reloadGrid");
				$("#workingSituationDialog").dialog("close");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("提交错误");
			}
			});
		},
		rules:{
			"workingSituation.policeName":{
				required:true,
				minlength:2,
				maxlength:10
			},
			"workingSituation.remark":{
				maxlength:200
			},
			"workingSituation.workcontent.id":{
				required:true
			}
		},
		messages:{	
		     "workingSituation.policeName":{
			   required:"请输入民警姓名",
			   minlength:$.format("民警姓名至少输入{0}个字"),
				maxlength:$.format("民警姓名最多输入{0}个字")
			},
			"workingSituation.remark":{
				maxlength:$.format("备注最多输入{0}个字")
			},
			"workingSituation.workcontent.id":{
				required:"请输入工作内容"
			}
				
		}
	});
	
});

</script>