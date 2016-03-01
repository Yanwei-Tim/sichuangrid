<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">ServiceRecord</@s.param>
</@s.include>

<div class="content">
	<div class="ui-corner-all contentNav" id="nav">
	<div class="btnbanner btnbannerData">
		<@s.include value="/common/orgSelectedComponent.jsp"/>
		<div class="userState">
			<select id="displayLevel" name="displayLevel" class="basic-input" >
				<option value="<@s.property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE"/>" selected="selected">仅显示本级</option>
				<option value="<@s.property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_DIRECTJURISDICTION"/>">直属下辖</option>
				<option value="<@s.property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_ALLURISDICTION"/>">全部</option>
			</select>
			<select id="displayYear" name="displayYear" class="basic-input" >
			</select>
		</div>
		<@pop.JugePermissionTag ename="searchServiceRecord">
		<a id="searchServiceRecord" href="javascript:void(0)"><span>高级搜索</span></a>
		</@pop.JugePermissionTag>
	</div>
	<span class="lineBetween "></span>
	<@pop.JugePermissionTag ename="addServiceRecord">
	<a id="addServiceRecord" href="javascript:void(0)"><span>新增</span></a>
	</@pop.JugePermissionTag>
	<@pop.JugePermissionTag ename="developPeopleLogForServiceRecord">
	<a id="developPeopleLogForServiceRecord" href="javascript:void(0)"><span>生成民情日志</span></a>
	</@pop.JugePermissionTag>
	<a id="reloadServiceRecord"  href="javascript:void(0)"><span>刷新</span></a>
	<@pop.JugePermissionTag ename="exportServiceRecord">
	<a id="exportServiceRecord"  href="javascript:void(0)"><span>导出</span></a>
	</@pop.JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="serviceRecordList"> </table>
		<div id="serviceRecordListPager"></div>
	</div>
	<div id="serviceRecordDialog"></div>
</div>
<script type="text/javascript">

//修改服务记录
function updateRecordOperator(selectedId){
	var serviceRecord =  $("#serviceRecordList").getRowData(selectedId);
	$("#serviceRecordDialog").createDialog({
		title:'修改服务记录',
		width: 600,
		height: 500,
		url:PATH+'/plugin/serviceTeam/serviceRecord/dispatchByEncrypt.action?mode=edit&dailogName=serviceRecordDialog&serviceRecord.id='+serviceRecord.encryptId+'&showRecordType=2',
		buttons: {
			"保存并关闭" : function(event){
				$("#serviceRecordForm").submit();
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

//删除服务记录
function deleteRecordOperator(selectedIds){
	var allValue=deleteOperatorByEncrypt('serviceRecord',selectedIds,'encryptId');
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?",
		okFunc: function(){
			$.ajax({
				url:PATH+'/plugin/serviceTeam/serviceRecord/deleteServiceRecordsByEncrypt.action?mode=delete&recordIds='+allValue,
				success:function(data){
					if(data>0){
					    $.messageBox({message:"成功删除服务记录!"});
						$("#serviceRecordList").trigger("reloadGrid");
					}else{
						$.messageBox({
							message:"删除服务记录出错!",
							level:"warn"
						});
					}
				}
			});
		}
	});
}


function viewServiceRecord(selectedId) {
	<@pop.JugePermissionTag ename="viewServiceRecord">
	var rowData=  $("#serviceRecordList").getRowData(selectedId);
	$("#serviceRecordDialog").createDialog({
		width: 600,
		height: 400,
		title: '查看服务记录信息',
		url:PATH+'/plugin/serviceTeam/serviceRecord/dispatchByEncrypt.action?showRecordType=2&mode=view&serviceRecord.id='+rowData.encryptId,
		buttons: {
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
	</@pop.JugePermissionTag>
}


	
function callback(){
    TQ.serviceRecordList()
}

$.cacheScript({
	url:'/resource/getScript/serviceTeam/template/serviceTeam/serviceRecord/serviceRecordList.js',
    callback: callback
})

</script>


