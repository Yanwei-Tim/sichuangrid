<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
	<div id="undergroundSpace" class="container container_24">
		<form action="${path}/baseinfo/undergroundSpaceManage/addUndergroundSpace.action" method="get" id="maintainForm">
		<div id="perUuid"></div>
		<input id="_imgUrl" type="hidden" name="undergroundSpace.imgUrl" value="">
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" name="modeType" id="modeType" value="${modeType}" />
		<input type="hidden" name="undergroundSpace.id" id="undergroundSpace-id" value="${undergroundSpace.id}" />
		<input type="hidden" name="undergroundSpace.organization.id" id="undergroundSpaceOrganizationId" value="${organizationId}" />
		<input id="keyType" type="hidden" name="" value="${keyType}" />
		<input id="personTypeName" type="hidden" name="personTypeName" value="${personTypeName}">
		<div class="grid_4 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">所属网格：</label>
		</div>
		<div class="grid_18">
			<input type="text"  name="undergroundSpace.organization.orgName" id="orgName"  readonly class="form-txt" value="${ undergroundSpace.organization.orgName}"/>
		</div>						
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">单位名称：</label>
		</div>
		<div class="grid_18">
			<input type="text" id="unitName" name="undergroundSpace.unitName" class="form-txt" value="${undergroundSpace.unitName}" maxlength="20"
			/>
		</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">单位地址：</label>
		</div>
		<div class="grid_18">
			<input type="text" name="undergroundSpace.unitAddress" class="form-txt" value="${undergroundSpace.unitAddress}" maxlength="50"
			/>
		</div>
		<div class="clearLine"></div>
		<div class="grid_4 lable-right">
            <label class="form-lbl">负责人：</label>
        </div>
        <div class="grid_7">
            <input type="text" name="undergroundSpace.superintendent" id="superintendent" class="dialogtip form-txt" value="${undergroundSpace.superintendent}"
             maxlength="20"/>
        </div>
        <div class="grid_4 lable-right">
            <label class="form-lbl">联系电话：</label>
        </div>
        <div class="grid_7">
            <input type="text" name="undergroundSpace.telephone" id="telephone" class="dialogtip form-txt" value="${undergroundSpace.telephone}" title="请输入由数字和-组成的联系电话 例如：0577-88888888"
            maxlength="20"/>
        </div>
		<div class="clearLine">&nbsp;</div>
		<div class="grid_4 lable-right">
            <label class="form-lbl">单位类别：</label>
        </div>
        <div class="grid_7">
            <input type="text" name="undergroundSpace.unitType" id="unitType" class="dialogtip form-txt" value="${undergroundSpace.unitType}"
             maxlength="50"/>
        </div>
        <div class="grid_4 lable-right">
            <label class="form-lbl">货物类别：</label>
        </div>
        <div class="grid_7">
            <input type="text" name="undergroundSpace.commodityType" id="commodityType" class="dialogtip form-txt" value="${undergroundSpace.commodityType}" 
            maxlength="50"/>
        </div>
        <div class="clearLine">&nbsp;</div>
        <div class="grid_4 lable-right">
			<label class="form-lbl">副本许可范围：</label>
		</div>
		<div class="grid_18">
			<input type="text" name="undergroundSpace.copyScope" class="form-txt" value="${undergroundSpace.copyScope}" maxlength="50"
			/>
		</div>
         <div class="clearLine">&nbsp;</div>
        <div class="grid_4 lable-right">
			<label class="form-lbl">存储设备：</label>
		</div>
		<div class="grid_18">
			<input type="text" name="undergroundSpace.storageDevice" class="form-txt" value="${undergroundSpace.storageDevice}" maxlength="50"
			/>
		</div>
        <div class="clearLine"></div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
		   <label class="form-lbl">备注：</label>
		</div>
		<div class="grid_20">
			<textarea rows="3" cols="" id="undergroundSpace-remark" name="undergroundSpace.remark" class="form-txt" >${undergroundSpace.remark}</textarea>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<input name="isSubmit" id="isSubmit" type="hidden" value="">
			</form>
	 </div>	
<script>
	$("#undergroundSpaceOrganizationId").val($("#currentOrgId").val());
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
		$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
		$(".shadow").show();
	}

	var existed = true;
	$(document).ready(function(){
	//ajax异步验证是否有重复记录存在
	jQuery.validator.addMethod("countExsistedUndergroundSpace", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async:false,
			url:"${path}/baseinfo/undergroundSpaceManage/hasDuplicateUndergroundSpaceUnitName.action",
			data:{
				"undergroundSpace.organization.id":$("#undergroundSpaceOrganizationId").val(),
				"undergroundSpace.unitName":$('#unitName').val(),
				"undergroundSpace.id":function(){if($("#mode").val() == "add" ){return -1;}else{ return $("#undergroundSpace-id").val();}}
			},
			success:function(responseData){
				
				existed = responseData;
			}
		});
		return existed;
	});


		if(""!=$("#_imgUrl").val()){
			$("#img").attr("src",$("#_imgUrl").val());
		}
		$('#logOutTime').datePicker({
			yearRange:'1900:2030',
			dateFormat:'yy-mm-dd',
	        maxDate:'+0d'
		});
		
	function changeOrgId(){
		$("#undergroundSpaceOrganizationId").val($("#currentOrgId").val());
	}
	
	var undergroundSpaceOrgTree="";
	function isGridForTreeSelect(){
		if(undergroundSpaceOrgTree != ""){
			return $.getSelectedNode(undergroundSpaceOrgTree).attributes.orgLevelInternalId == <s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>;
		}else{
			return true;
		}
	}

	
	
	var bol = true;
		$("#tabs").tabFunction({ cache: true });
		<s:if test='"add".equals(mode)'>
			$.ajax({
				async: false,
				url:"${path}/sysadmin/orgManage/getOrgRelativePath.action",
				data:{
					"organization.id":$("#currentOrgId").val()
				},
				success:function(responseData){
					$("#orgName").val(responseData);
				}
			});
		</s:if>
		
		$(".dialogtip").inputtip();
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
			$("#_imgUrl").attr("src",$("#_imgUrl").val());
				$(form).ajaxSubmit({
					success:function(data){
						if(!data.id){
	           	 			$.messageBox({
								message:data,
								evel: "error"
				 			});
	            			return;
						}
						if("add"==$("#mode").val()){
							 $.messageBox({message:"地下空间新增成功"});
							 doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
							 $("#undergroundSpaceList").addRowData(data.id,data,"first");
							 $("#undergroundSpaceList").setSelection(data.id);
						 }
							 $("#undergroundSpaceList").setRowData(data.id,data);
							 if("edit"==$("#mode").val()){
								 $("#undergroundSpaceList").setRowData(data.id,data);
								 $.messageBox({message:"地下空间修改成功"});
								 $("#undergroundSpaceList").setSelection(data.id);
							 } 
							 doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
	  	      			alert("提交数据时发生错误");
		   		    }
				});
			},
			rules:{
			"undergroundSpace.unitName":{
				required:true,
				exculdeParticalChar:true,
				countExsistedUndergroundSpace:true,
				minlength:1,
				maxlength:20
			},
			"undergroundSpace.unitAddress":{
				minlength:1,
				maxlength:50
			},
			"undergroundSpace.superintendent":{
				minlength:1,
				maxlength:50
			},
			"undergroundSpace.telephone":{
				telephone:true
			},
			"undergroundSpace.unitType":{
				maxlength:50
			},
			"undergroundSpace.commodityType":{
				maxlength:50
			},
			"undergroundSpace.copyScope":{
				maxlength:50
			},
			"undergroundSpace.storageDevice":{
				maxlength:50
			},
			"undergroundSpace.logOutReason":{
				maxlength:20
			},
			"undergroundSpace.remark":{
				maxlength:300
		   }
		},
			messages:{
			"undergroundSpace.unitName":{
				required:"请输入单位名称",
				exculdeParticalChar:"不能输入非法字符",
				countExsistedUndergroundSpace:"该网格下已存在该名称地下空间",
				minlength:$.format("单位名称至少需要输入{0}个字符"),
				maxlength:$.format("单位名称最多需要输入{0}个字符")
			},
	
			"undergroundSpace.unitAddress":{
				minlength:$.format("单位地址至少需要输入{0}个字符"),
				maxlength:$.format("单位地址最多需要输入{0}个字符")
			},
			"undergroundSpace.superintendent":{
				minlength:$.format("负责人至少需要输入{0}个字符"),
				maxlength:$.format("负责人最多需要输入{0}个字符")
			},
			"undergroundSpace.telephone":{
				telephone:$.format("固定电话不合法，只能输数字和横杠(-)")
			},
			"undergroundSpace.unitType":{
				maxlength:$.format("单位类别最多需要输入{0}个字符")
			},
			"undergroundSpace.commodityType":{
				maxlength:$.format("货物类别最多需要输入{0}个字符")
			},
			"undergroundSpace.copyScope":{
				maxlength:$.format("副本许可范围最多需要输入{0}个字符")
			},
			"undergroundSpace.storageDevice":{
				maxlength:$.format("存储设备最多需要输入{0}个字符")
			},
			"undergroundSpace.logOutReason":{
				maxlength:$.format("注销原因最多需要输入{0}个字符")
			},
			"undergroundSpace.remark":{
				maxlength:$.format("备注最多需要输入{0}个字符")
			}
		}
		});
		
	
	
		$(".dialogtip").inputtip();
		<s:if test='"add_path".equals(modeType)'>
		$("#orgName").click(function(event){
			$("#noticeDialog").createDialog({
				width: 300,
				height: 350,
				title:'请选择一个部门',
				url:'${path}/common/organizationSelector.jsp',
				buttons: {
					"确定" : function(){
						closeDialog();
					},
					"取消" : function(){
						$("#noticeDialog").dialog("close");
					}
				}
			});
		});
		</s:if>
	
		<s:if test='#parameters.dialog != null'>
			undergroundSpaceOrgTree=$("#orgName").treeSelect({
				inputName:"ownerOrg.id",
				url:"/sysadmin/orgManage/loadTownForExtTree.action",
				onSelect:changeOrgId
			});
			var bol = false;
			$.ajax({
				async:false,
				url:"${path}/issue/issueManage/checkOccurOrgId.action",
				data:{
					"issueNew.occurOrg.id":${USER_ORG_ID}
				},
				success:function(responseData){
					bol = responseData;
				}
			});
			var url = "";
			if(bol){
				url = PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIdsWhenRootIsTown.action?organization.id="+$("#organizationId").val()
			}else{
				url = PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+$("#organizationId").val()
			}
			$.ajax({
				url:url,
				success:function(data){
					$.searchChild(undergroundSpaceOrgTree,data);
				}
			});
		</s:if>
	});
	
	<s:if test='"add_path".equals(modeType)'>
	function setZone(selectOrgId,selectOrgName){
			$("#organizationId").val(selectOrgId);
			$("#orgName").val(selectOrgName);
		}
	</s:if>

</script>
