<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<div id="dialog-form" title="楼宇信息表管理页面" class="container container_24">
	<c:if test='${mode!="view"}'>
		<form id="maintainForm" method="post" action="">
		<pop:token />
	</c:if>
	
		<input type="hidden" id="mode" name="mode" value="${mode }"/>
		<input type="hidden" id="id" name="builddatas.id" value="${builddatas.id }"/>
		<input type="hidden" id="organization" name="builddatas.organization.id" value="${builddatas.organization.id }"/>
		<input type="hidden" id="buildingid" name="builddatas.buildingid" value="${builddatas.buildingid }"/>
		<input type="hidden" id="centerx" name="builddatas.centerx" value="${builddatas.centerx }"/>
		<input type="hidden" id="centery" name="builddatas.centery" value="${builddatas.centery }"/>
		<input type="hidden" id="gisType" value="<s:property value='#parameters.gisType'/>"/>
		<input type="hidden" id="featureId" name="builddatas.openLayersMapInfo.featureId" value="${builddatas.openLayersMapInfo.featureId }"/>
		
		<div class="grid_4  lable-right">
			<label class="form-lbl">所属网格：</label>
		</div>
		<div class="grid_20">
			<input type="text" style="padding-right: 9px;" name="orgName" id="orgName" value="${builddatas.organization.orgName}" disabled class="form-txt" />
		</div>

		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
		<c:if test='${mode!="view"}'><em class="form-req">*</em></c:if>
			<label class="form-lbl">楼宇名称：</label>
		</div>
		<div class="grid_20">
			<input type="text" style="padding-right: 9px;"  name="builddatas.buildingname" id="buildingname" value="${builddatas.buildingname}"  <c:if test='${mode=="view"}'>disabled</c:if> class="form-txt {isLawful:true,messages:{isLawful:'您输入了非法脚本请重新输入!'}}" maxlength="50" />
		</div>		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
		<c:if test='${mode!="view"}'><em class="form-req">*</em></c:if>
			<label class="form-lbl">楼宇类型：</label>
		</div>
		<div class="grid_8">
			<select name="builddatas.type.id" id="builddatas-type" class="form-select" <c:if test='${mode=="view"}'>disabled</c:if>>
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BUILDDATAS_TYPE" defaultValue="${builddatas.type.id}" />
			</select>
		</div>
		
		<div class="grid_7 lable-right">
 			<input type="checkbox" name="builddatas.isPropertyManagement" value="true" <c:if test="${true==builddatas.isPropertyManagement }">checked</c:if>  <c:if test='${mode=="view"}'>disabled</c:if> />
 			<label class="form-lb1">是否有物管</label>
 		</div>
				
		<div class='clearLine'>&nbsp;</div>		
		<div class="grid_4 lable-right">
		<c:if test='${mode!="view"}'><em class="form-req">*</em></c:if>
			<label class="form-lbl">楼宇地址：</label>
		</div>
		<div class="grid_20">
			<input type="text" style="padding-right: 9px;" name="builddatas.buildingaddress" id="buildingaddress" value="${builddatas.buildingaddress}"  <c:if test='${mode=="view"}'>disabled</c:if> class="form-txt {isLawful:true,messages:{isLawful:'您输入了非法脚本请重新输入!'}}" maxlength="50" />
		</div>		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">业主：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="builddatas.owner" id="owner" value="${builddatas.owner}"  <c:if test='${mode=="view"}'>disabled</c:if> class="form-txt {isLawful:true,messages:{isLawful:'您输入了非法脚本请重新输入!'}}" maxlength="20" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">负责人：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="builddatas.responsibleperson" id="responsibleperson" value="${builddatas.responsibleperson}"  <c:if test='${mode=="view"}'>disabled</c:if> class="form-txt {isLawful:true,messages:{isLawful:'您输入了非法脚本请重新输入!'}}" maxlength="20" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">联系电话：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="builddatas.phone" id="phone" value="${builddatas.phone}"  <c:if test='${mode=="view"}'>disabled</c:if> class="form-txt" maxlength="20" />
		</div>
		<div class="grid_4 lable-right">
		<c:if test='${mode!="view"}'><em class="form-req">*</em></c:if>
			<label class="form-lbl">建筑结构：</label>
		</div>
		<div class="grid_8">
			<select name="builddatas.buildingstructures.id" id="buildingstructures" class="form-select" <c:if test='${mode=="view"}'>disabled</c:if>>
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS" defaultValue="${builddatas.buildingstructures.id}" />
			</select>
		</div>		
		<div class='clearLine'>&nbsp;</div>	
		
			<div class="grid_4 lable-right">
				<label class="form-lbl">经度：</label>
			</div>
			<div class="grid_7">
				<input type="text"  id="builddatas-centerLon" name="builddatas.openLayersMapInfo.centerLon" value="${builddatas.openLayersMapInfo.centerLon}"  readonly class="form-txt"/>
			</div>
			<div class="grid_2 lable-right">
				<label class="form-lbl">纬度：</label>
			</div>
			<div class="grid_6">
				<input type="text" id="builddatas-centerLat" name="builddatas.openLayersMapInfo.centerLat" value="${builddatas.openLayersMapInfo.centerLat}"  readonly class="form-txt"/>
				<input type="hidden" id="builddatas-isTransformat" name="builddatas.openLayersMapInfo.isTransformat" value="${builddatas.openLayersMapInfo.isTransformat}" />
				<input type="hidden" id="builddatas-gisType" name="builddatas.openLayersMapInfo.gisType" value="${builddatas.openLayersMapInfo.gisType}" />
			</div>
			<div class="grid_5">
				<c:if test='${mode!="view" && mode!="gisEdit"}'>
					<input type="button" value="绑定" class="defaultBtn" id="bindMap"/>
					<c:if test='${mode=="edit" && builddatas.openLayersMapInfo.centerLon != null && builddatas.openLayersMapInfo.centerLat != null }'>
						<input type="button" value="解绑" class="defaultBtn" id="cancelBind"/>
					</c:if>
				</c:if>
				<c:if test='${mode=="view" && builddatas.openLayersMapInfo.centerLon != null && builddatas.openLayersMapInfo.centerLat != null}'>
					<input type="button" value="查看" class="defaultBtn" id="viewMap"/>
				</c:if>
			</div>
		<c:if test='${mode!="view"}'>
			</form>
		</c:if>		
</div>
<script>
	$(function(){
		if($("#gisType").val()=="2D" || gisType=="2D"){
			$("#builddatas-centerLon").val(${builddatas.openLayersMapInfo.centerLon2});
			$("#builddatas-centerLat").val(${builddatas.openLayersMapInfo.centerLat2});
		}
		$("#bindMap").click(function(){
			$("#gisBuilddatasDialog").createDialog({
			  zIndex:1020,
              width: 800,
              height: 600,
              title:'绑定建筑物',
              url:"${path}/openLayersMap/product_3.0/gis2Dbuilddatas.jsp?flag=1&organizationId="+getCurrentOrgId()+"&gisType="+$("#gisType").val(),
              buttons: {
                  "关闭" : function(){
                	  $("#builddatasList").trigger("reloadGrid");
                     $(this).dialog("close");
                  }
              },
              shouldEmptyHtml:false
			});
		});
		
		
		$("#viewMap").click(function(){
			$("#gisBuilddatasDialog").createDialog({
				  zIndex:1020,
	              width: 800,
	              height: 600,
	              title:'查看建筑物',
	              url:"${path}/openLayersMap/product_3.0/gis2Dbuilddatas.jsp?flag=3&organizationId="+getCurrentOrgId()+"&viewLon="+$("#builddatas-centerLon").val()+"&viewLat="+$("#builddatas-centerLat").val()+"&gisType="+gisType,
	              buttons: {
	                  "关闭" : function(){
	                	  $("#builddatasList").trigger("reloadGrid");
	                     $(this).dialog("close");
	                  }
	              },
	              shouldEmptyHtml:false
			});
		});
		
		
		$("#cancelBind").click(function(){
			$("#builddatas-centerLon").val("");
			$("#builddatas-centerLat").val("");
			$("#buildingid").val("");
		});
		
		<c:if test='${mode!="view"}'>
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft", 
			submitHandler: function(form){
				$(form).ajaxSubmit({
					success:function(data){
						if(!data.id){
	           	 			$.messageBox({
							message:data,
							level: "error"
			 				});
	            			return;
						}
						
						//parent.updateBuilding(data.id,data.organization.id);
						<c:if test='${mode=="add"}'>
			   		 		//$("#builddatasList").addRowData(data.id,data,"first");
			   		 		if("<s:property value='#parameters.flag'/>"=='true'){
			   		 			onOrgChanged(getCurrentOrgId());
			   		 		}
							$.messageBox({message:"新增楼宇信息成功!"});
							$("#builddatasMaintanceDialog").dialog("close");
						</c:if>
						<c:if test='${mode=="edit"}'>
				 			//$("#builddatasList").setRowData(data.id,data);
				 			if("<s:property value='#parameters.flag'/>"=='true'){
				 				onOrgChanged(getCurrentOrgId());
				 			}
							$.messageBox({message:"修改楼宇信息成功!"});
							$("#builddatasMaintanceDialog").dialog("close");
						</c:if>
						
						<c:if test='${mode=="gisEdit"}'>
							$.messageBox({message:"修改楼宇信息成功!"});
							$("#builddatasMaintanceDialog").dialog("close");
						</c:if>
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
	  	      			alert("提交数据时发生错误");
		   		    }
				});
			},
			rules:{
				"builddatas.buildingname":{
					required:true,
					exculdeParticalChar:true,
					maxlength:50},
				"builddatas.buildingaddress":{
					required:true,
					exculdeParticalChar:true,
					maxlength:50},
				"builddatas.owner":{
					required:false,
					exculdeParticalChar:true,
					maxlength:20},
				"builddatas.responsibleperson":{
					required:false,
					exculdeParticalChar:true,
					maxlength:20},
				"builddatas.phone":{
					required:false,
					telephone:true,
					minlength:2,
					maxlength:20},
				"builddatas.buildingstructures.id":{
					required:true},
				"builddatas.type.id":{
					required:true}
			},
			messages:{
				"builddatas.buildingname":{
					required:"请输入楼宇名称",
					exculdeParticalChar:"不能输入非法字符",
					maxlength:$.format("楼宇名称最多需要输入{0}个字符")},
				"builddatas.buildingaddress":{
					required:"请输入楼宇地址",
					exculdeParticalChar:"不能输入非法字符",
					maxlength:$.format("楼宇地址最多需要输入{0}个字符")},
				"builddatas.owner":{
					required:"请输入业主",
					exculdeParticalChar:"不能输入非法字符",
					maxlength:$.format("业主最多需要输入{0}个字符")},
				"builddatas.responsibleperson":{
					required:"请输入负责人",
					exculdeParticalChar:"不能输入非法字符",
					maxlength:$.format("负责人最多需要输入{0}个字符")},
				"builddatas.phone":{
					required:"请输入联系电话",
					telephone:$.format("联系电话只能输数字和横杠(-)"),
					minlength:$.format("联系电话最少需要输入{0}个字符"),
					maxlength:$.format("联系电话最多需要输入{0}个字符")},
				"builddatas.buildingstructures.id":{
					required:"请输入建筑结构"},
				"builddatas.type.id":{
					required:"请输入楼宇类型"}
			}
		});
	    </c:if>
	         
	    <c:if test='${mode=="add"}'>
	        $("#maintainForm").attr("action","${path}/builddatasManage/addBuilddatas.action");
	    </c:if>
	    <c:if test='${mode=="edit"}'>
	        $("#maintainForm").attr("action","${path}/builddatasManage/updateBuilddatas.action");
	    </c:if>
	    <c:if test='${mode=="gisEdit"}'>
	        $("#maintainForm").attr("action","${path}/builddatasManage/updateBuilddatas.action");
	    </c:if>
	});
	
	
</script>
