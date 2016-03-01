<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">ServiceTeamMember</@s.param>
</@s.include>

<div class="container_24">
	<div class='clearLine'></div>
	<div class='clearLine'></div>
	<div class='grid_24'>
		<b>数据合并为：</b>
	</div>
	<input type="hidden" id="birthday" value="${(serviceTeamMemberVo.birthday)!}">
	<form id="combineForm" action="${path}/plugin/serviceTeam/serviceTeamMember/combineServiceTeamMembers.action" method="post">
		<@pop.token />
		<input type="hidden" name="selectedIds" id="removeIds" value="0">
		<input type="hidden"  name="serviceTeamMemberVo.mobile" id="mobile" value="${(serviceTeamMemberVo.mobile)!}">
		<input type="hidden"  name="serviceTeamMemberVo.name" id="name" value="${(serviceTeamMemberVo.name)!}">
		<input type="hidden"  name="serviceTeamMemberVo.baseId" id="baseId" value="${(serviceTeamMemberVo.baseId)!}">
		<input type="hidden" name="serviceTeamMemberVo.org.id" id="orgId">
		<div class="grid_3 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">姓名：</label>
		</div>
		<div class="grid_9">
			<input type="text" name="serviceTeamMemberBase.name" readonly="readonly" id="name" maxlength="20" value='${(serviceTeamMemberVo.name)!}'
			class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入姓名",exculdeParticalChar:"不能输入非法字符",minlength:$.format("姓名至少需要输入{0}个字符"),maxlength:$.format("姓名最多需要输入{0}个字符")}}'/>
		</div>
		<div class="grid_3 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">性别：</label>
		</div>
		<div class="grid_9">
			<select name="serviceTeamMemberBase.gender.id"  style="width: 98%" id="gender" class='form-txt {required:true,messages:{required:"请选择性别"}}'>
				<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${(serviceTeamMemberVo.gender.id)!}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_3 lable-right">
			<label class="form-lb1">职位： </label>
		</div>
		<div class="grid_9">
			<input type="text" name="serviceTeamMemberBase.job" id="job" value='${(serviceTeamMemberVo.job)!}' class='form-txt {exculdeParticalChar:true,minlength:2,maxlength:20,messages:{exculdeParticalChar:"不能输入非法字符",minlength:$.format("职位至少需要输入{0}个字符"),maxlength:$.format("职位最多需要输入{0}个字符")}}' />
		</div>
		<div class="grid_3 lable-right">
			<label class="form-lb1">出生年份 ：</label>
		</div>
		<div class="grid_9">
			<select id="year" class="basic-input" name="serviceTeamMemberBase.birthday">
				<option value="" selected>-请选择-</option>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_3 lable-right">
			<label class="form-lb1">联系手机：</label>
		</div>
		<div class="grid_9">
			<input type="text" name="serviceTeamMemberBase.mobile" maxlength="11" value="${(serviceTeamMemberVo.mobile)!}"
			title="请输入11位以1开头的联系手机  例如：13988888888" class='form-txt {mobile:true,messages:{mobile:"手机号码输入只能是以1开头的11位数字"}}' />
		</div>
	
		<div class="grid_3 lable-right">
			<label class="form-lb1">联系电话：</label>
		</div>
		<div class="grid_9">
			<input type="text" name="serviceTeamMemberBase.homePhone" id="homePhone" maxlength="15" value="${(serviceTeamMemberVo.homePhone)!}" title="请输入由数字和-组成的联系电话,例如：0577-88888888"
			class='form-txt {telephone:true,messages:{telephone:$.format("固定电话不合法，只能输数字和横杠(-)")}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
	</form>
	<div class='clearLine'></div>
	<div class='grid_24'>
		<b>已选择合并的数据</b>
	</div>
	<div class="content">
		<div style="clear: both;"></div>
		<div style="width: 100%;">
			<table id="sameMembersList"></table>
			<div id="sameMembersListPager"></div>
		</div>
		<div id="sameMembersDialog"></div>
	</div>
	
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#orgId").val(USER_ORG_ID);
	$("#sameMembersList").jqGridFunction({
		datatype: "local",
		colModel:[
			{name:"baseId",index:"baseId",sortable:true,hidden:true},
			{name:"organization.id",index:"organization.id",sortable:false,hidden:true},
			{name:"operation",index:"operation",label:"操作",formatter:remove_ServiceTeamMember,sortable:false,width:80,align:"center"},
			{name:'name',index:"name",label:"姓名",width:100,frozen:true,sortable:true},
			{name:'gender',index:'gender',label:"性别",width:80,formatter:genderFormatter,sortable:true,align:"center"},
			{name:'mobile',index:'mobile',label:"联系手机",sortable:true,width:110,align:"center"},
			{name:'job',index:'job',label:"职位",sortable:true,width:150},
			{name:'homePhone',index:'homePhone',label:"固定电话",sortable:true,width:110},
			{name:"birthday",index:"birthday",label:"出生年份",sortable:true,width:80,align:"center"},
			{name:"org.orgName",index:"org.orgName",label:"所属区域(网格)",sortable:false,width:350}
		],
		height:290,
	    showColModelButton:false
	});
	
	getSameMembersList();
		
	
	//获取年份的选择下拉框
	$.ajax({
		async: false,
		url: "${path }/plugin/serviceTeam/serviceTeamMember/getCurrentTimeForIntegrativeQueryYear.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
		}
	});
	//选中出生年份
	if($("#birthday").val()){
		$("#year").val($("#birthday").val());
	}
});
//列表显示
function getSameMembersList(){
	$("#sameMembersList").setGridParam({
		url:'${path}/plugin/serviceTeam/serviceTeamMember/findSameMembersByNameAndMobile.action?mode=getSameMember&serviceTeamMemberVo.name='+encodeURIComponent($("#name").val())+'&serviceTeamMemberVo.mobile='+$("#mobile").val()+'&serviceTeamMemberVo.baseId='+$("#baseId").val()+'&serviceTeamMemberVo.org.id='+$("#orgId").val()+'&selectedIds='+$("#removeIds").val(),
		datatype: "json",
		page:1,
		mytype:"post"
	});
	$("#sameMembersList").trigger("reloadGrid"); 
}
//移除待合并的内容
function removeServiceMember(selectedId){
	$.messageBox({message:"成功移除待合并成员！"});
	$("#sameMembersList").delRowData(selectedId);
	$("#removeIds").val($("#removeIds").val()+","+selectedId);
}
//表单验证
$("#combineForm").formValidate({
	submitHandler: function(form) {
		$(form).ajaxSubmit({
	         success: function(data){
	         	if(!data.baseId){
	           		$.messageBox({
						message:data,
						level: "error"
				 	});
	            	return;
				}
				$.messageBox({message:"成功合并!"});
				$("#serviceTeamMemberList").trigger("reloadGrid");
				$("#_serviceTeamMemberDialog").dialog("close");
			},
		    error: function(XMLHttpRequest, textStatus, errorThrown){
	         	$.messageBox({message:"提交错误",level: "error"});
		    }
		});
	}
});

</script>