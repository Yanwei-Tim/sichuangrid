<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24" >
    <form action="${path }/regradedPoints/regradedPointsManage/regradedPoints.action" id="maintainForm" method="post">
	    <div class="grid_5 lable-right">
	        <label class="form-lbl">应用部门：</label>
	    </div>
	    <div class="grid_6">
	        <input type="hidden" value="${organization.id}" id="targeOrgId" name="regradedPoint.targeOrg.id"/>
	        <input type="text" name="orgName" id="orgName" value="<s:property value='organization.orgName'/> " readonly class="form-txt" />
	    </div>
	    <div class="grid_1">&nbsp;</div>
	    <div class="grid_5 lable-right">
	        <label  class="form-lbl">应用时间：</label>
	    </div>
	    <div class="grid_6">
	        <input type="text" id="applicationDate" name="regradedPoint.applicationDate"  class="form-txt" readonly/>
	    </div>
        <div class="grid_1"><em class="form-req">*</em></div>
	    <div class="grid_5 lable-right">
	        <label  class="form-lbl">打分类型：</label>
	    </div>
	    <div class="grid_7 form-left">
	        <input type="radio" name="regradedPoint.pointType" checked value="1" class="category"/><label class="form-check-text" for="sites">加分</label>
	        <input type="radio" name="regradedPoint.pointType" value="-1" class="category"/><label class="form-check-text" for="sites">扣分</label>
	    </div>
	    <div class="grid_5 lable-right">
	        <label  class="form-lbl">打分分值：</label>
	    </div>
	    <div class="grid_6 form-left">
	        <input type="text" name="regradedPoint.points" id="points" class="form-txt" style="text-align:right;" />
	    </div>
        <div class="grid_1"><em class="form-req">*</em></div>
	    <div class="grid_5 lable-right">
	        <label  class="form-lbl">打分原因：</label>
	    </div>
	    <div class="grid_18 form-left">
	        <textarea rows="3" cols="50" class="form-txt" id="content" name="regradedPoint.content"  style="width:98%" ></textarea>
	    </div>
        <div class="grid_1"><em class="form-req">*</em></div>
    </form>
</div>
<script>
//精确到3位的小数
jQuery.validator.addMethod("positiveDouble", function(value, element) {
	var positiveInteger = /^\d+(\.[0-9]{1,3})?$/;
	return this.optional(element) || (positiveInteger.test(value));
});

    $(function(){
        $("#applicationDate").datePicker({
            yearRange: '2010:2060',
            dateFormat:'yy-mm-dd',
            maxDate:'+0y'
        });
        $("#maintainForm").formValidate({
        	promptPosition: "bottomLeft", 
            submitHandler: function(form){
                $(form).ajaxSubmit({
                    success:function(data){
                        if(!data){
                            $.messageBox({
                               message:"给部门打分失败",
                                evel: "error"                           
                            });
                            return;
                        }
                        $.messageBox({message:"成功为部门打分成功!"});
                        $("#statRegradedPointsDialog").dialog("close");
                        $("#statRegradedPointsList").trigger("reloadGrid");
                    },
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                    	$.messageBox({message:"提交数据时发生错误"});
                    }
                });
            },
            rules:{
                "regradedPoint.targeOrg.id":{
            	    required:true
                },
                "regradedPoint.applicationDate":{
            	    required:true
                },
            	"regradedPoint.points" : {
                	required:true,
	            	//digits:true,
	            	positiveDouble:true,
	                min:0.001,
	                max:100
            	},
            	"regradedPoint.content":{
                	required:true,
                	maxlength:200
            	}
            },
            messages:{
            	"regradedPoint.targeOrg.id":{
	                required:"请选择打分部门"
	            },
                "regradedPoint.applicationDate":{
                    required:"请选择打分时间"
                },
                "regradedPoint.points":{
                	required:"请输入打分分值",
	            	//digits:"打分分值只能输入正整数",
	            	positiveDouble:"打分分值只能输入小数位数不大于3位的数字",
	                min:"打分分值最小输入0.001",
	                max:"打分分值最大输入100"
                },
                "regradedPoint.content":{
                	required:"请输入打分原因",
                    maxlength:"打分原因最多输入{0}"
                }
            }
        });
    });
</script>