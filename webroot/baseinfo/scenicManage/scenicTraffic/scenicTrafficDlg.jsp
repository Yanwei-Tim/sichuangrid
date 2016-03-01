<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<style type='text/css'>
.ui-widget-content #searchSpot{padding:4px 6px;}
.ui-timepicker-div .ui-widget-header { margin-bottom: 8px; }
.ui-timepicker-div dl { text-align: left; }
.ui-timepicker-div dl dt { float: left; clear:left; padding: 0 0 0 5px; width:24px;}
.ui-timepicker-div dl dd { margin: 0 10px 10px 30%;display:block; }
.ui-timepicker-div td { font-size: 90%; }
.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }

.ui-timepicker-rtl{ direction: rtl; }
.ui-timepicker-rtl dl { text-align: right; padding: 0 5px 0 0; }
.ui-timepicker-rtl dl dt{ float: right; clear: right; }
.ui-timepicker-rtl dl dd { margin: 0 45% 10px 10px;}

</style>

<div class="container container_24" >
	<div id="publicPlace" class="container container_24">
		<form id="maintainForm" method="post"action="${path}/baseinfo/scenicTrafficManage/saveScenicTraffic.action"  >
			<s:if test='"add".equals(mode)'>
				<pop:token/>
			</s:if>
			<input type="hidden" name="mode" id="mode" value="${mode}" />
			<input type="hidden" name="scenicTraffic.id" id="scenicTrafficId" value="${scenicTraffic.id}" />
			<input id="organizationId" type="hidden" name="scenicTraffic.organization.id" value="${scenicTraffic.organization.id }" />
			<input id="_imgUrl" name="scenicTraffic.imgUrl" type="hidden" value="${scenicTraffic.imgUrl}"/>
   		    <input name="isSubmit" id="isSubmit" type="hidden" value="" />
			<div class="grid_4 lable-right">
			   	<em class="form-req">*</em>
	   			<label class="form-lb1">所属网格：</label>
   			</div>
   			<div class="grid_20">
   				<input type="text" id="orgName" name="scenicTraffic.organization.orgName" readonly value="${scenicTraffic.organization.orgName}" style="width: 98%"	class="form-txt" />
   			</div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
	   			<label class="form-lb1">起点：</label>
   			</div>
   			<div class="grid_8">
   				<input type="text" name="scenicTraffic.startAddress" value="${scenicTraffic.startAddress}" maxlength="30" class="form-txt {minlength:2,maxlength:30,messages:{minlength:$.format('起点至少需要输入{0}个字符'),maxlength:$.format('起点最多需要输入{0}个字符')}}"/>
   			</div>
   			<div class="grid_4 lable-right">
				<label class="form-lbl">终点：</label>
			</div>
			<div class="grid_8">
	   			<input type="text" name="scenicTraffic.endAddress" value="${scenicTraffic.endAddress}" maxlength="30" class="form-txt {minlength:2,maxlength:30,messages:{minlength:$.format('终点至少需要输入{0}个字符'),maxlength:$.format('终点最多需要输入{0}个字符')}}"/>
			</div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
			   	<em class="form-req">*</em>
   				<label class="form-lb1">类型：</label>
	   		</div>
	   		<div class="grid_8">
				<select name="scenicTraffic.trafficType.id" id="scenicTraffic-trafficType" class="form-txt {required:true,messages:{required:'请选择类型'}}">
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCENICTRAFFIC_TYPES" defaultValue="${scenicTraffic.trafficType.id}" />
				</select>
			</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">联系电话：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="scenicTraffic.phone"  maxlength="15" value="${scenicTraffic.phone}" class='form-txt {telephone:true,messages:{telephone:$.format("联系电话只能输数字或数字和横杠(-)")}}'/>
	   		</div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">线路名称：</label>
	   		</div>
	   		<div class="grid_20">
	   			<input type="text" name="scenicTraffic.trafficName" value="${scenicTraffic.trafficName}" maxlength="100" class="form-txt {minlength:2,maxlength:100,messages:{minlength:$.format('线路名称至少需要输入{0}个字符'),maxlength:$.format('线路名称最多需要输入{0}个字符')}}" style="width: 98%"/>
	   		</div>
	   		<div class='clearLine'>&nbsp;</div>
	   		<div class="grid_4 lable-right">
	   			<label class="form-lb1">负责单位：</label>
   			</div>
   			<div class="grid_20">
   				<input type="text" name="scenicTraffic.managerUnit" value="${scenicTraffic.managerUnit}" maxlength="100" class="form-txt {minlength:2,maxlength:100,messages:{minlength:$.format('负责单位至少需要输入{0}个字符'),maxlength:$.format('负责单位最多需要输入{0}个字符')}}" style="width: 98%" />
   			</div>
   			<div class='clearLine'>&nbsp;</div>
	   		<div class="grid_4 lable-right">
   				<label class="form-lb1">负责人：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="scenicTraffic.managerPeople" maxlength="15" value="${scenicTraffic.managerPeople}" class="form-txt {minlength:2,maxlength:15,messages:{minlength:$.format('负责人至少需要输入{0}个字符'),maxlength:$.format('负责人最多需要输入{0}个字符')}}" />
	   		</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">负责人电话：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="scenicTraffic.managerPeoplePhone" maxlength="15" value="${scenicTraffic.managerPeoplePhone}" class="form-txt {telephone:true,messages:{telephone:$.format('负责人电话只能输数字或数字和横杠(-)')}}" />
	   		</div>
	   		<div class='clearLine'>&nbsp;</div>
	   		<div class="grid_4 lable-right">
   				<label class="form-lb1">最早班车时间：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="scenicTraffic.firstBusTime" id="scenicTraffic_firstBusTime" maxlength="10" readonly="readonly" value="${scenicTraffic.firstBusTime }" class="form-txt" />
	   		</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">最晚班车时间：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="scenicTraffic.lastBusTime" id="scenicTraffic_lastBusTime" maxlength="10" readonly="readonly" value="${scenicTraffic.lastBusTime }" class="form-txt"/>
	   		</div>
	   		<div class='clearLine'>&nbsp;</div>
	   		<div class="grid_4 lable-right">
   				<label class="form-lb1">周边景区：</label>
	   		</div>
   			<div class="grid_20" style="height:145px">
   				<textarea rows="6" id="aroundScenic" name="scenicTraffic.aroundScenic" class="form-txt {maxlength:300,messages:{maxlength:$.format('周边景区最多需要输入{0}个字符')}}" style="width: 90%">${scenicTraffic.aroundScenic}</textarea>
   				<div style="float: right;padding-top:4px;">
   					<a id="searchSpot" class="button" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>查询</span></a>
   				</div>
   			</div>
	   		<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
   				<label class="form-lb1">备注：</label>
	   		</div>
	   		<div class="grid_20">
	   			<textarea rows="5" name="scenicTraffic.remark" id="remark" class="form-txt {minlength:2,maxlength:300,messages:{minlength:$.format('备注至少需要输入{0}个字符'),maxlength:$.format('备注最多需要输入{0}个字符')}}" style="width: 99%" >${scenicTraffic.remark}</textarea>
   			</div>
		</form>
  	</div>
</div>
<div id="searchScenicSpotListDialog"></div>
<script type="text/javascript">
<s:if test='"add".equals(mode)'>
$("#organizationId").val($("#currentOrgId").val());
$.ajax({
	async: false,
	url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
	data:{
		"organization.id" : $("#currentOrgId").val()
	},
	success:function(responseData){
		$("#orgName").val(responseData);
	}
});
</s:if>
if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
	$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	$(".shadow").show();
}
if(""!=$("#_imgUrl").val() && typeof($("#_imgUrl").val())!="undefined"){
	$("#img").attr("src","${path }/"+$("#_imgUrl").val());
};
$('#scenicTraffic_firstBusTime').timepicker({
	showSecond: false, //显示秒
	timeFormat: 'HH:mm',//格式化时间
	stepHour: 1,//设置步长
	stepMinute: 1,
	stepSecond: 1
	});
$('#scenicTraffic_lastBusTime').timepicker({
	showSecond: false, //显示秒
	timeFormat: 'HH:mm',//格式化时间
	stepHour: 1,//设置步长
	stepMinute: 1,
	stepSecond: 1
	});
$(document).ready(function(){
	
	$('#scenicTraffic_lastBusTime').timepicker();
	$("#maintainForm").formValidate({
		submitHandler: function(form) {
			$("#_imgUrl").val($("#imgUrl").val());
         	$(form).ajaxSubmit({
         		async : false,
             	success: function(data){
                     if(!data.id){
                    	 $.messageBox({
							message:data,
							level: "error"
						 });
                     	return;
                     }
                     $("#<s:property value='#parameters.dailogName[0]'/>").proccessSuccess(data.id,data);
      	   },
      	   error: function(XMLHttpRequest, textStatus, errorThrown){
      	      alert("提交错误");
      	   }
      	  });
	},
		rules:{
		},
		messages:{
		}
	});
	$("#searchSpot").click(function(event){
		var organizationId=$("#organizationId").val();
		if(organizationId==""||organizationId==null){
			return;
		}
		$("#searchScenicSpotListDialog").createDialog({
			width:500,
			height:500,
			title:'旅游景点列表',
			url:'/baseinfo/scenicSpotManage/dispatchOperate.action?dailogName=searchScenicSpotListDialog&organizationId='+organizationId+'&mode=allSearch',
			buttons: {
				"确定":function(){
					saveScenicSpotNames();
					$(this).dialog("close");
				},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			},
			close: function(event, ui) {$("#${lowerCaseModuleName}List").trigger("reloadGrid");}
		});
	});
	function saveScenicSpotNames(){
		var selectedSpotName="";
		var spotIds = $("#searchScenicSpotList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<spotIds.length;i++){
			var rowData = $("#searchScenicSpotList").getRowData(spotIds[i]);
			if(rowData.name!=""&&rowData.name!=null){
				selectedSpotName+=";"+rowData.name;
			}
		}
		if(selectedSpotName==""){
			return
		}else{
			selectedSpotName=selectedSpotName.substr(1);
		}
		var aroundScenic = $("#aroundScenic").val();
		$("#aroundScenic").val(aroundScenic+" " + selectedSpotName);
	}
});
</script>