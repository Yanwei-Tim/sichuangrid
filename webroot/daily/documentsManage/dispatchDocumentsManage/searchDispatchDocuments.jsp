<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="" class="container container_24">
	<input type="hidden" id="mode" value="${mode}" />
	<form id="searchDispatchDocForm" method="post" action="">
	<input type="hidden"  name="searchDocumentVo.dispatchState" value="${searchDocumentVo.dispatchState}" />
		<div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">文件标题：</label>
			</div>
			<div class="grid_20 form-left">
		    	<input type="text" id="title" name="searchDocumentVo.title" maxlength="160" value="${searchDocumentVo.title}" class="form-txt" style="width:95%" />
			</div>
			<div class='clearLine'></div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">文件号：</label>
			</div>
			<div class="grid_8 form-left">
		    	<input type="text" id="documentNo" name="searchDocumentVo.documentNo" maxlength="50" value="${searchDocumentVo.documentNo}" class="form-txt" />
			</div>
    		<div class="grid_4 lable-right">
				<label class="form-lbl">机密程度：</label>
			</div>
			<div class="grid_8">
				<select class="form-txt" name="searchDocumentVo.secretDegree.id" id="secretDegree" style="width: 182px;height: 25px">
   						<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SECRETDEGREE" defaultValue="${searchDocumentVo.secretDegree.id}"/>
 				</select>
 			</div>
			<div class='clearLine'></div>		
			
			<div class="grid_4 lable-right">
	  			<label class="form-lbl">主题词：</label>
	  		</div>
    		<div class="grid_8 form-left">
				<input type="text" id="theme" name="searchDocumentVo.theme" maxlength="30" value="${searchDocumentVo.theme}" class="form-txt" />
    		</div>
    		
    		<div class="grid_4 lable-right">
				<label class="form-lbl">紧急程度：</label>
			</div>
			<div class="grid_8">
				<select class="form-txt" name="searchDocumentVo.urgentDegree.id" id="urgentDegree" style="width: 182px;height: 25px">
   						<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@URGENTDEGREE" defaultValue="${searchDocumentVo.urgentDegree.id}"/>
 				</select>
 			</div>
 			<div class='clearLine'>&nbsp;</div>	
 			<div>
	 			<div class="grid_4 lable-right">
					<label class="form-lbl">是否有附件：</label>
				</div>
				<div class="grid_8">
					<select class="form-txt" id="attachFiles" name="searchDocumentVo.attachFiles">
						<option></option>
						<option value="true">是</option>
						<option value="false">否</option>
					</select>
	 			</div>
 			</div>
 			<%-- <div>
 				<select id="select1" style="width: 205px;height: 25px" name="searchDocumen">
 					<option></option>
 					<option value="true">是</option>
 					<option value="false">否</option>
 				</select>
 			</div> --%>
 			<div id="creatDate">
 			<div class="grid_4 lable-right">
			<label class="form-lbl">创建时间：</label>
			</div>
			<div class="grid_3">
				<input type="text" id="createDateStart" name="searchDocumentVo.createDateStart" readonly="readonly" class="form-txt" />
			</div>
			 <div class="grid_1 lable-right">
				<label class="form-lbl">至：</label>
			</div>
			<div class="grid_3">
				<input type="text" id="createDateEnd" name="searchDocumentVo.createDateEnd" readonly="readonly" class="form-txt" />
			</div>	
			</div>	
			<div class='clearLine'>&nbsp;</div>			
			<div class="grid_4 lable-right" >
				<label class="form-lbl">是否同步资料库：</label>
			</div>
			<div class="grid_8">
			<select class="form-txt" id="synchroDocs" name="searchDocumentVo.synchroDocs">
				<option value=""></option>
				<option value="true">是</option>
				<option value="false">否</option>
			</select>
 			</div>
			<div class='clearLine'>&nbsp;</div>		
			<div class="grid_4 lable-right">
				<label class="form-lbl">内容：</label>
			</div>
			<div class="heightAuto" style="display:inline;float:left;width:490px;">
    			<textarea rows="3" cols="" id="contents" name="searchDocumentVo.contents"
    				class="form-txt" style="height:90px;">${searchDocumentVo.contents}</textarea>
			</div>
		</div>
</form>
</div>

<script type="text/javascript">
$(function(){
	if("searchDocuments"==$('#mode').val()){
		$('#creatDate').hide();
	}else if("search"==$('#mode').val()){
		$('#creatDate').show();
    }
	$('#createDateStart').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
   		onSelect:function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#createDateEnd").datepicker("option", "minDate",date);
			}
		}
	});

	$('#createDateEnd').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#createDateStart").datepicker("option", "maxDate",date);
			}
		}
	});
	$('#startSendTime').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
   		onSelect:function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#endSendTime").datepicker("option", "minDate",date);
			}
		}
	});

	$('#endSendTime').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#startSendTime").datepicker("option", "maxDate",date);
			}
		}
	});
});
</script>