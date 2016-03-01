<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<script type="text/javascript" src="${path}/extend/amchart/swfobject.js"></script>

<div class="ui-corner-all" id="nav">
	<label style="float:left;padding:0 10px;line-height:25px;">开始年份：</label>
	<select name="minYear" id="minYear" onchange="onChangeMinYear()" style="float:left;">
   		<option value="0"></option>
   		<@s.iterator begin="minYear" end="maxYear" var="newMinYear">
   			<option value="${newMinYear }">${newMinYear }</option>
   		</@s.iterator>
   	</select>
   	<label style="float:left;padding:0 10px;line-height:25px;">开始月份：</label>
   	<select style="float:left;" name="minMonth" id="minMonth" onchange="onChangeMinMonth()">
   		<option value="0"></option>	
   		<@s.iterator begin="1" end="12" var="newMinMonth">
   			<option value="${newMinMonth }">${newMinMonth }</option>
   		</@s.iterator>
   	</select>
   	<label style="float:left;padding:0 10px;line-height:25px;">结束年份：</label>
   	<select style="float:left;" name="maxYear" id="maxYear"  onchange="onChangeMaxYear()">
		<option value="0">&nbsp;&nbsp;&nbsp;&nbsp;</option>	
   	</select>
   	<label style="float:left;padding:0 10px;line-height:25px;">结束月份：</label>
   	<select style="float:left;" name="maxMonth" id="maxMonth" >
   		<option value="0">&nbsp;&nbsp;&nbsp;&nbsp;</option>	
   	</select>
	<a id="search" href="javascript:void(0)"><span>查询</span></a>
    <a id="reload" href="javascript:void(0)"><span>刷新</span></a>
    <@pop.JugePermissionTag ename="generatedDataStatus">
		<a id="refresh" href="javascript:void(0)"><span>生成数据</span></a>
	</@pop.JugePermissionTag>
</div>
<div class="content" style="overflow: hidden; overflow-y: auto; position: relative;">
	<div class="container_24">
		<div class="grid_5 heightAuto" style="width: 40%; float: left">
			<div>
				<table id="statStatueList"></table>
			</div>
		</div>
		<div class="grid_17 lable-right heightAuto" style="width: 60%; float: left" id="showPic"></div>
	</div>
	<div id="statStatueDialog"></div>
	<div id="noticeDialog"></div>
	<div id="promptDialog"></div>
</div>
<script type="text/javascript">
function onChangeMinYear(){
	if($("#minYear").val() > 0){
		$("#minMonth").removeAttr("disabled");
	}else{
		$("#minMonth").attr("disabled","disabled");
		$("#minMonth").val(0);
	}
	if($("#minMonth").val()>0){
		$("#minMonth").val(0);
	}
	$("#maxYear").attr("disabled","disabled");
	$("#maxYear").val(0);
	$("#maxMonth").attr("disabled","disabled");
	$("#maxMonth").val(0);
}

function onChangeMinMonth(){
	if($("#minMonth").val() > 0){
		var minYear = $("#minYear").val();
		var maxYear = ${maxYear};
		$("#maxYear").removeAttr("disabled");
		
		$('#maxYear option').remove();
		$("<option value='0'></option>").appendTo("#maxYear");
		for(var i = minYear ; i<=maxYear;i++){
			$("<option value='"+i+"'>"+i+"</option>").appendTo("#maxYear");
		}
	}else{
		$("#maxYear").attr("disabled","disabled");
		$("#maxYear").val(0);
	}
	$("#maxMonth").attr("disabled","disabled");
	$("#maxMonth").val(0);
}

function onChangeMaxYear(){
	if($("#maxYear").val() > 0){
		$("#maxMonth").removeAttr("disabled");
		if($("#maxYear").val() == $("#minYear").val()){
			var minMonth = $("#minMonth").val();
			$('#maxMonth option').remove();
			$("<option value='0'></option>").appendTo("#maxMonth");
			for(var i = minMonth ; i<=12;i++){
				$("<option value='"+i+"'>"+i+"</option>").appendTo("#maxMonth");
			}
		}else{
			$('#maxMonth option').remove();
			$("<option value='0'></option>").appendTo("#maxMonth");
			for(var i = 1 ; i<=12;i++){
				$("<option value='"+i+"'>"+i+"</option>").appendTo("#maxMonth");
			}
		}
	}else{
		$("#maxMonth").attr("disabled","disabled");
		$("#maxMonth").val(0);
	}
}
$(document).ready(function(){
    var minyears;
    var minmonths;
    var maxyears = "<@s.property value='maxYear'/>";
    var maxmonths = "<@s.property value='maxMonth'/>";
    if(maxmonths == 1){
        minyears = maxyears-1;
        minmonths = 12;
    }else{
        minyears = maxyears;
        minmonths = maxmonths-1;
    }
    $("#minYear").val(minyears);
    $("#minMonth").val(minmonths);
    $("#maxYear").append("<option value='"+maxyears+"' selected>"+maxyears+"</option>");
    <@s.iterator begin="maxMonth-1" end="12" var="newMinMonth">
        $("#maxMonth").append("<option value='${newMinMonth }'>"+${newMinMonth }+"</option>");
	</@s.iterator>
	$("#maxMonth").val(minmonths);

    
	$(".content",$("#contentDiv")).height($(".ui-layout-center").height()-$(".submenu").height()-$("#nav").height()-20);
	jQuery("#statStatueList").jqGridFunction({
		datatype:'local',
	    sortname:'state',
	    rowNum:-1,
	    width:308,
	    height:600,
		colModel:[
			{name:'state',key:true,hidden:true},
			{name:'stateName',index:'stateName',label:'统计类型',sortable:false},
			{name:'count',index:'count',label:'总数',width:80,sortable:false},
			{name:'ratio',index:'ratio',label:'总比',width:78,sortable:false},
            {name:'startDate',label:'开始时间',sortable:false,hidden:true},
            {name:'endDate',label:'结束时间',sortable:false,hidden:true}
		],
		ondblClickRow:showPie
	});
/**
	$("#refresh").click(function(event){
		onOrgChanged();
	});
	*/
	$("#refresh").click(function(event){
        $("#statStatueDialog").createDialog({
            width:500,
            height: 150,
            title: "事件处理状态统计--按月份",
            url: "${path}/stat/statAnalyseIssueStatueManage/dispatch.action?mode=search",
            buttons: {
                "生成数据" : function(event){
            		reGenerateData();
                    $(this).dialog("close");
                },
                "关闭" : function(){
                    $(this).dialog("close");
                }
            }
        });
    });
	
	$("#search").click(function(event){
		searchIssueStat();
        }
    );

    $("#reload").click(function(event){
    	var minyears;
        var minmonths;
        var maxyears = "<@s.property value='maxYear'/>";
        var maxmonths = "<@s.property value='maxMonth'/>";
        if(maxmonths == 1){
            minyears = maxyears-1;
            minmonths = 12;
        }else{
            minyears = maxyears;
            minmonths = maxmonths-1;
        }
        $("#minYear").val(minyears);
        $("#minMonth").val(minmonths);
        $("#maxYear").html("<option value='0' selected></option>");
        $("#maxYear").append("<option value='"+maxyears+"' selected>"+maxyears+"</option>");
        $("#maxMonth").html("<option value='0' selected></option>");
        <@s.iterator begin="maxMonth-1" end="12" var="newMinMonth">
            $("#maxMonth").append("<option value='${newMinMonth }'>"+${newMinMonth }+"</option>");
        </@s.iterator>
        $("#maxMonth").val(minmonths);
        onOrgChanged();
    });
	
	if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
		onOrgChanged(getCurrentOrgId(),isGrid());
	}	
	
});

function reGenerateData(){	
	var orgId = getCurrentOrgId();
	var year = $("#year").val();
	var month = $("#month").val();
	$.ajax({
		async:false,
		url:"${path}/stat/statAnalyseIssueStatueManage/reGenerateData.action",
		data:{
			"orgId":orgId,
			"issueStatueStanal.year":year,
			"issueStatueStanal.month":month
		},
		success:function(responseData){
			$.messageBox({message:"已重新生成本月数据"});
			onOrgChanged(getCurrentOrgId(),isGrid());
		}
	});
}

function searchIssueStat(){
	var orgId = getCurrentOrgId();
	var minYear = $("#minYear").val();
	var minMonth = $("#minMonth").val();

	var maxYear = $("#maxYear").val();
	var maxMonth = $("#maxMonth").val();
	$("#statStatueList").setGridParam({
		url:'${path}/stat/statAnalyseIssueStatueManage/getIssueState.action',
		postData: {
			"orgId":orgId,
			"minYear":minYear,
			"minMonth":minMonth,
			"maxYear":maxYear,
			"maxMonth":maxMonth
		}
	});
	$("#statStatueList").trigger("reloadGrid");

	$.ajax({
        async:false,
        url:"${path}/stat/statAnalyseIssueStatueManage/showAll.action?mode=returnToGraph",
        data:{
        	"orgId":orgId,
			"minYear":minYear,
			"minMonth":minMonth,
			"maxYear":maxYear,
			"maxMonth":maxMonth
        },
        success:function(responseData){
            $("#showPic").html("<div id='flashcontents'>"
            +"<strong>You need to upgrade your Flash Player</strong>"
            +"</div>"
        
            +"<script type='text/javascript'>"  
            +"var so = new SWFObject('../extend/amchart/pie/ampie.swf', 'ampie', '100%', '260', '8', '#FFFFFF');"
            +"so.addVariable('path', '../extend/amchart/pie/');"
            +"so.addVariable('settings_file', encodeURIComponent('../extend/amchart/pie/day_settings.jsp?showTitle="+responseData.title_key1+"&chartDataList="+responseData.data_key1+"'));"
            +"so.addVariable('data_file', encodeURIComponent('../extend/amchart/pie/day_data.jsp?showTitle="+responseData.title_key1+"&chartDataList="+responseData.data_key1+"'));"
            +"so.addParam('wmode','transparent');"
            +"so.write('flashcontents');"
            +"<\/script>"
            +"<div id='flashcontent1'>"
		    +"<strong>You need to upgrade your Flash Player</strong>"
		    +"</div>"

		    +"<script type='text/javascript'>"	
		    +"var so = new SWFObject('../extend/amchart/line/amline.swf', 'amline', '100%', '380', '8', '#FFFFFF');"
		    +"so.addVariable('path', '../extend/amchart/line/');"
		    +"so.addVariable('settings_file', encodeURIComponent('../extend/amchart/line/day_settings.jsp?showTitle="+responseData.title_key2+"&chartDataList="+responseData.data_key2+"'));"
		    +"so.addVariable('data_file', encodeURIComponent('../extend/amchart/line/day_data.jsp?showTitle="+responseData.title_key2+"&chartDataList="+responseData.data_key2+"'));"
		    +"so.addParam('wmode','transparent');"
		    +"so.write('flashcontent1');"
			+"<\/script>"
                 
            );
        }
    });
}
function onOrgChanged(orgId,isgrid){
    
    var minYear = $("#minYear").val()==0?0:$("#minYear").val();
    var minMonth = $("#minMonth").val()==0?0:$("#minMonth").val();
    var maxYear = $("#maxYear").val()==0?0:$("#maxYear").val();
    var maxMonth = $("#maxMonth").val()==0?0:$("#maxMonth").val();
	$("#statStatueList").setGridParam({
		url:"${path}/stat/statAnalyseIssueStatueManage/getIssueState.action",
		datatype: "json",
		page:1
	});
	$("#statStatueList").setPostData({
    	"orgId":getCurrentOrgId(),
        "minYear":minYear,
        "minMonth":minMonth,
        "maxYear":maxYear,
        "maxMonth":maxMonth
   	});
	$("#statStatueList").trigger("reloadGrid");

	$.ajax({
		async:false,
		url:"${path}/stat/statAnalyseIssueStatueManage/showAll.action?mode=returnToGraph",
		data:{
			"orgId":getCurrentOrgId(),
            "minYear":minYear,
            "minMonth":minMonth,
            "maxYear":maxYear,
            "maxMonth":maxMonth
		},
		success:function(responseData){
			$("#showPic").html("<div id='flashcontents'>"
			+"<strong>You need to upgrade your Flash Player</strong>"
			+"</div>"
		
			+"<script type='text/javascript'>"	
			+"var so = new SWFObject('../extend/amchart/pie/ampie.swf', 'ampie', '100%', '260', '8', '#FFFFFF');"
			+"so.addVariable('path', '../extend/amchart/pie/');"
			+"so.addVariable('settings_file', encodeURIComponent('../extend/amchart/pie/day_settings.jsp?showTitle="+responseData.title_key1+"&chartDataList="+responseData.data_key1+"'));"
			+"so.addVariable('data_file', encodeURIComponent('../extend/amchart/pie/day_data.jsp?showTitle="+responseData.title_key1+"&chartDataList="+responseData.data_key1+"'));"
			+"so.addParam('wmode','transparent');"
			+"so.write('flashcontents');"
			+"<\/script>"

			+"<div id='flashcontent1'>"
		    +"<strong>You need to upgrade your Flash Player</strong>"
		    +"</div>"

		    +"<script type='text/javascript'>"	
		    +"var so = new SWFObject('../extend/amchart/line/amline.swf', 'amline', '100%', '380', '8', '#FFFFFF');"
		    +"so.addVariable('path', '../extend/amchart/line/');"
		    +"so.addVariable('settings_file', encodeURIComponent('../extend/amchart/line/day_settings.jsp?showTitle="+responseData.title_key2+"&chartDataList="+responseData.data_key2+"'));"
		    +"so.addVariable('data_file', encodeURIComponent('../extend/amchart/line/day_data.jsp?showTitle="+responseData.title_key2+"&chartDataList="+responseData.data_key2+"'));"
		    +"so.addParam('wmode','transparent');"
		    +"so.write('flashcontent1');"
			+"<\/script>"
			);
		}
	});
}
function showPie(){
	var selId = $("#statStatueList").getGridParam("selrow");
	if(selId == null)return ;
	var statanlIssue = $("#statStatueList").getRowData(selId);
	$("#statStatueDialog").createDialog({
        width: 800,
        height: 360,
        title:"事件处理"+statanlIssue.stateName+"分布图",
        url:'${path}/stat/statAnalyseIssueStatueManage/statIssuePie.action?state='+statanlIssue.state+'&orgId='+getCurrentOrgId()+"&startDate="+statanlIssue.startDate+"&endDate="+statanlIssue.endDate,
        buttons: {
            "关闭" : function(){
                $(this).dialog("close");
            }
        }
    });
}
</script>