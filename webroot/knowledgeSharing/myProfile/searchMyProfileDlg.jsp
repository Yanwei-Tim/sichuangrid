<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="资料维护" class="container container_24">
	 <form id="myProfile-form" method="post" action="" >
	    <input id="resourcePoolTypeId" type="hidden" name="resourcePoolTypeId" value="${resourcePoolTypeId }">
	    <input id="searchType" type="hidden" name="searchType" value="${searchType }"/>
	    <div class="grid_4 lable-right">
			<label >名称：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="myProfile.name" class="form-txt" maxlength="50" style="width: 99.5%"
			id="myProfile_name" value=""/>
		</div>
		<div class='clearLine'>&nbsp;</div>


		<div class="grid_4 lable-right">
			<label><s:if test='resourcePoolTypeId==0'>发文单位(作者)：</s:if><s:else>
			    <s:if test='resourcePoolTypeId>3'>作者：</s:if><s:else>发文单位：</s:else>
			</s:else>
			</label>
		</div>
		<div class="grid_19">
			<input type="text" name="myProfile.releaseUnit" class="form-txt " maxlength="50" style="width: 99.5%"
			
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
	          <input type="text" id="releaseTime" name="myProfile.releaseTime" readonly="readonly" class="form-txt" />
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
	           <input type="text" id="createDate" name="myProfile.createDate" readonly="readonly" class="form-txt" />
	      </div>
	   
	    <div class='clearLine'>&nbsp;</div>
		<s:if test='resourcePoolTypeId>3'></s:if><s:else>
	        <div class="grid_4 lable-right">
			  <label >文件号：</label>
		    </div>
			<div class="grid_7">
				<input type="text" name="myProfile.fileNo" class="form-txt" maxlength="50" style="width: 98%"
				id="myProfile_fileNo"/>
			</div>
		</s:else>
	      <s:if test='resourcePoolTypeId>3'></s:if><s:else>
		     <div class="grid_5 lable-right">
				<label >文件主题：</label>
		     </div>
			  <div class="grid_7">
					<input type="text" name="myProfile.documentSubject" class="form-txt" maxlength="50"  style="width: 98%"
					id="myProfile_documentSubject" value=""/>
				</div>
			</s:else>
	      <div class='clearLine'>&nbsp;</div>
	      <div class="grid_4 lable-right">
	            <label class="form-lbl">资料来源：</label>
	     </div>
	     <div class="grid_7">
	          <div style="float: left;width:100px;height:15px">
						<select class="form-txt" id="source" name="myProfile.source" class="S_object" style="width:180%;height:25px;">
								<option value="" selected="selected" >请选择</option>
			 					<option value="自行录入" >自行录入</option>
			 					<option value="从台账中同步">从台账中同步</option>
			 					<option value="从公文中同步">从公文中同步</option>
						</select>
				</div>
		 </div>
		 <div class="grid_5 lable-right">
			<label >是否有附件：</label>
	     </div>
		 <div class="grid_2 lable-right" >
				<input type="radio" name="attached" value="1">是
		 </div>
		 <div class="grid_2 lable-right" >
			<input type="radio" name="attached" value="0" >否
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
	
});
</script>