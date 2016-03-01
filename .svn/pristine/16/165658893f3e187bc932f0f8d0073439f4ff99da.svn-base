<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="共享资料" class="container container_24">
	 <form id="sharingFile-form" method="post" action="" >
	    <input id="resourcePoolTypeId" type="hidden" name="resourcePoolTypeId" value="${resourcePoolTypeId }">
	    <input id="searchType" type="hidden" name="searchType" value="${searchType }"/>
	    
	    <div class="grid_4 lable-right">
			<label >名称：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="myProfile.name" class="form-txt" maxlength="150" style="width: 98.5%"
			id="myProfile_name" value=""/>
		</div>
		<div class='clearLine'>&nbsp;</div>


		<div class="grid_4 lable-right">
			<label><s:if test='resourcePoolTypeId==0'>发文单位(作者)：</s:if><s:else>
			    <s:if test='resourcePoolTypeId>3'>作者：</s:if><s:else>发文单位：</s:else>
			</s:else></label>
		</div>
		<div class="grid_19">
			<input type="text" name="myProfile.releaseUnit" class="form-txt " maxlength="50" style="width: 98.5%"
			
			  id="myProfile_releaseUnit" value="" />
		</div>
		<div class='clearLine'>&nbsp;</div>

	   
		<div class="grid_4 lable-right">
            <label class="form-lbl"><s:if test='resourcePoolTypeId==0'>发文(撰文)</s:if><s:else>
			    <s:if test='resourcePoolTypeId>3'>发文</s:if><s:else>撰文</s:else>
			</s:else>时间 ：</label>
        </div>
        <div class="grid_3">
            	<input type="text" id="startReleaseTime"  name="startReleaseTime" readonly="readonly" class="form-txt" />
        </div>
        <div class="grid_1" align="center">
            	<label class="form-lbl">-</label>
        </div>
        <div class="grid_3">
            	<input type="text" id="releaseTime"  name="myProfile.releaseTime" readonly="readonly" class="form-txt" />
        </div>
        <div class="grid_5 lable-right">
            <label class="form-lbl">创建时间 ：</label>
     </div>
     <div class="grid_3">
           <input type="text" id="startCreateTime"  name="startCreateTime" readonly="readonly" class="form-txt" />
     </div>
     <div class="grid_1" align="center">
           <label class="form-lbl">-</label>
      </div>
      <div class="grid_3">
           <input type="text" id="createDate"  name="myProfile.createDate" readonly="readonly" class="form-txt" />
      </div>
        
    <div class='clearLine'>&nbsp;</div>
    <s:if test='resourcePoolTypeId>3'></s:if><s:else>
		<div class="grid_4 lable-right">
			<label >文件号：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="myProfile.fileNo" class="form-txt" maxlength="30" style="width: 98%"
			id="myProfile_fileNo"/>
		</div>
	     <div class="grid_5 lable-right">
			<label >文件主题：</label>
	     </div>
		  <div class="grid_7">
				<input type="text" name="myProfile.documentSubject" class="form-txt" maxlength="50"  style="width: 97%"
				id="myProfile_documentSubject" value=""/>
			</div>
	      <div class='clearLine'>&nbsp;</div>
      </s:else>
      <div class="grid_4 lable-right">
            <label class="form-lbl">共享时间段：</label>
     </div>
     <div class="grid_3">
           <input type="text" id="startShareDate"  name="startShareDate" readonly="readonly" class="form-txt" />
     </div>
     <div class="grid_1" align="center">
           <label class="form-lbl">-</label>
      </div>
      <div class="grid_3">
           <input type="text" id="shareDate" class="form-txt"  name="myProfile.shareDate" readonly="readonly" />
      </div>
     <div class="grid_5 lable-right">
		<label >共享部门：</label>
     </div>
	  <div class="grid_7">
	    	<input id="organizationId" type="hidden" name="myProfile.organization.id" class="form-txt" />
	    	<input id="org_autocomplete" type="text" name="myProfile.organization.orgName" class="form-txt" style="width:97%"  value=""/>
	</div>
      <div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label >文件内容：</label>
		</div>
		<div class="grid_19 heightAuto">
			<textarea name="myProfile.content" style="width: 98.5%"
			id="myProfile_content" class="form-txt"></textarea>
		</div>
		
	
   	</form>
  
   

</div>
<style>
	#myProfile_content{height:70px;*height:60px;}
</style>
<script type="text/javascript">

$(document).ready(function(){
	$("#startShareDate").datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
		maxDate:'+0d',
	   	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#shareDate").datepicker("option", "minDate",date);
			}
		}
	});

	$("#shareDate").datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
		maxDate:'+0d',
	    onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#startShareDate").datepicker("option", "maxDate",date);
			}
		}
	});
	
	$("#startReleaseTime").datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
		maxDate:'+0d',
	   	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#releaseTime").datepicker("option", "minDate",date);
			}
		}
	});

	$("#releaseTime").datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
		maxDate:'+0d',
	    onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#startReleaseTime").datepicker("option", "maxDate",date);
			}
		}
	});

	$("#startCreateTime").datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
		maxDate:'+0d',
	   	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#createDate").datepicker("option", "minDate",date);
			}
		}
	});

	$("#createDate").datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
		maxDate:'+0d',
	    onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#startCreateTime").datepicker("option", "maxDate",date);
			}
		}
	});
   
	
	
	$("#org_autocomplete").autocomplete({
		source: function(request, response) {
			$.ajax({
				url: "${path}/sysadmin/orgManage/ajaxFindOrganizationsByOrgName.action",
				data:{"organization.orgName":request.term},
				success: function(data) {
					response($.map(data, function(item) {
						return {
							label: item.orgName+",",
							value: item.orgName,
							id: item.id
						}
					}))
				},
				error : function(){
					$.messageBox({
						message:"搜索失败，请重新登入！",
						level:"error"
					});
				}
			})
		},
		select: function(event, ui) {
			$("#organizationId").val(ui.item.id);
		}
	});
	
	
});
</script>