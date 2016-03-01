<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript" src="${path}/extend/amchart/swfobject.js"></script>
	
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
		<div class="ui-corner-all" id="nav">
			<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
			<label style="float:left;padding:0 10px;line-height:25px;">年份：</label>
			<select name="year" id="year" style="float:left;margin-top:3px;">
			   <s:iterator begin="minYear" end="maxYear" var="newMinYear">
                  <option value="${newMinYear }">${newMinYear }</option>
               </s:iterator>
			</select>
			<a id="search" href="javascript:void(0)"><span>查询</span></a>
		</div>
<div class="content" style="overflow:hidden;overflow-y:auto;position:relative;height: 360px;">
<div class="container_24">
	<div class="grid_5 heightAuto" style="width:280px;float:left">
		<table id="populationStatisticList"></table>
	</div>
	<div class="grid_17 lable-right heightAuto" style="width:800px;float:left" id="showGraph">
	</div>
</div>
		<div id="populationStatisticDialog"></div>
		<div id="noticeDialog"></div>
		<div id="promptDialog"></div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#year").val(${year});
	jQuery("#populationStatisticList").jqGridFunction({
		datatype:'local',
	    rowNum:-1,
	    width:250,
	    height:300,
		colModel:[
			{name:'gradeName',index:'gradeName',label:'统计类型',sortable:false,width:100},
			{name:'count',index:'count',label:'总数',width:60,sortable:false,align:"right"},
			{name:'scale',index:'scale',label:'总比',width:60,sortable:false,align:"right"}
		],
		ondblClickRow:showPic
	});

	$("#refresh").click(function(event){
		onOrgChanged(getCurrentOrgId(),isGrid());
	});	

	$("#search").click(function(event){
		searchPopulationStatistic();
	});

	if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
	    onOrgChanged(getCurrentOrgId(),isGrid());
	}
});

function onOrgChanged(orgId,isgrid){
	fillData(orgId);
}

function fillData(orgId){
	$("#populationStatisticList").setGridParam({
        url:'${path}/examineScroseStanal/examineScroseStanal.action',
        datatype: "json"
    });
    $("#populationStatisticList").setPostData({
        "page":1,
        "orgId":orgId,
        "year":$("#year").val()
    });
    $("#populationStatisticList").trigger("reloadGrid");

    $.ajax({
        async:false,
        url:"${path}/examineScroseStanal/examineScrosePie.action",
        data:{
            "orgId":orgId,
            "year":$("#year").val()
        },
        success:function(responseData){
            $("#showGraph").html("<div id='flashcontents'>"
            +"<strong>You need to upgrade your Flash Player</strong>"
            +"</div>"
        
            +"<script type='text/javascript'>"  
            +"var so = new SWFObject('../extend/amchart/pie/ampie.swf', 'ampie', '800', '300', '8', '#FFFFFF');"
            +"so.addVariable('path', '../extend/amchart/pie/');"
            +"so.addVariable('settings_file', encodeURIComponent('../extend/amchart/pie/day_settings.jsp?<%=Math.random()%>'));"
            +"so.addVariable('data_file', encodeURIComponent('../extend/amchart/pie/day_data.jsp?<%=Math.random()%>'));"
            +"so.addParam('wmode','transparent');"
            +"so.write('flashcontents');"
            +"<\/script>"
            );
        }
    });
}

function searchPopulationStatistic(){
	var orgId = getCurrentOrgId();
	fillData(orgId);
}
</script>