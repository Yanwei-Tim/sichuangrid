<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div>
    <form action="${path}/serviceList/pyramidSalesManageManage/signPyramidSalesManage.action?" method="post" id="maintainForm">
    <input type="hidden" name="mode" id="mode" value="${mode}" />
	<input type="hidden" name="pyramidSalesManage.id"  value="${pyramidSalesManage.id}" />
	<input type="hidden" name="pyramidSalesManage.organization.id"  value="${pyramidSalesManage.organization.id}" />
	<div class="container container_24"> 
	    <div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">时间：</label>
		</div>
		<div class="grid_8 lable-left">
			<input id='inputTime' value='<s:date name="pyramidSalesManage.inputTime" format="yyyy-MM-dd HH:mm:ss"/>' readonly="readonly"  class="form-txt"/>
		</div>
	    <div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">地点：</label>
		</div>
		<div class="grid_8 lable-left">
			<input value='${pyramidSalesManage.address}' title="${pyramidSalesManage.address}" readonly="readonly" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">姓名：</label>
		</div>
		<div class="grid_8 lable-left">
			<input id='createUser' value='${pyramidSalesManage.createUser}'  readonly="readonly" class="form-txt"/>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">类别：</label>
		</div>
		<div class="grid_8">
			<select  id="category"  class="form-select "  disabled="disabled" >
	        	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PYRAMID_CATEGORY" defaultValue="${pyramidSalesManage.category.id}" />
	        </select>		    
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">传销类别：</label>
		</div>
		<div class="grid_8">
			<select id="pyramidCategory" disabled="disabled"  class="form-select ">
	        	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PYRAMID_SALES_CATEGORY" defaultValue="${pyramidSalesManage.pyramidCategory.id}" />
	        </select>		    
		</div>
		
	    <div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">情况描述：</label>
		</div>
		<div class="grid_8 ">
			<input value='${pyramidSalesManage.details}' title="${pyramidSalesManage.details}" readonly="readonly" class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">参与人员：</label>
		</div>
		<div class="grid_8 ">
			<input value='${pyramidSalesManage.personnel}' title="${pyramidSalesManage.personnel}" readonly="readonly" class="form-txt"/>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">备注：</label>
		</div>
		<div class="grid_20 heightAuto">
            <textarea rows="4" name="pyramidSalesManage"  cols="" class="form-txt" disabled="disabled" >${pyramidSalesManage.remake}</textarea>
    	</div>
		<div class='clearLine'>&nbsp;</div>
		<br/>
		
		<div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">签收人：</label>
		</div>
		<div class="grid_8 ">
			<input name='pyramidSalesManage.signPeople' value='${pyramidSalesManage.signPeople}' readonly="readonly" class="form-txt"/>
		</div>
		<div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">签收日期：</label>
		</div>
		<div class="grid_8 lable-left">
			<input id='inputTime' name="pyramidSalesManage.signDate" value='<s:date name="pyramidSalesManage.signDate" format="yyyy-MM-dd HH:mm:ss"/>' readonly="readonly" class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">签收意见：</label>
		</div>
		<div class="grid_20 heightAuto">
            <textarea rows="4" id="signContent" name="pyramidSalesManage.signContent" maxlength="300"  cols="" class="form-txt" >${pyramidSalesManage.signContent}</textarea>
    	</div>
		<div class='clearLine'>&nbsp;</div>
    </div>
    </form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#maintainForm").formValidate({
		submitHandler: function(form){
			 $("#maintainForm").ajaxSubmit({
					success : function(data) {
						if(!data.id){
	           	 			$.messageBox({ 
								level: "error",
								message:data
				 			});
	            			return;
						}
						$.messageBox({message:"签收成功！"});
						$("#serviceListDialog").dialog('close');
						$("#pyramidSalesManageList").trigger("reloadGrid");
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("提交错误");
					}
			 });
		},
	});
});
</script>