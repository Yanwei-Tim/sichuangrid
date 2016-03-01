<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/common/orgSelectedComponent.jsp"/> 

<style type="text/css">
#load {position:relative;}
#load .ui-button{width:80px;height:25px;}
#load .ui-dialog-buttonset{position:absolute;bottom:20px;right:15px;}
</style>
<div id="nav" class="ui-corner-all">
        <label style="float:left;padding:0 10px;line-height:25px;">开始年份：</label>
        <select name="minYear" id="minYear" onchange="onChangeMinYear()" style="float:left;">
            <option value="0"></option>
            <s:iterator begin="minYear" end="maxYear" var="newMinYear">
                <option value="${newMinYear }">${newMinYear }</option>
            </s:iterator>
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">开始月份：</label>
        <select style="float:left;" name="minMonth" id="minMonth" onchange="onChangeMinMonth()">
            <option value="0"></option> 
            <s:iterator begin="1" end="12" var="newMinMonth">
                <option value="${newMinMonth }">${newMinMonth }</option>
            </s:iterator>
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">结束年份：</label>
        <select style="float:left;" name="maxYear" id="maxYear"  onchange="onChangeMaxYear()">
            <option value="0">&nbsp;&nbsp;&nbsp;&nbsp;</option> 
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">结束月份：</label>
        <select style="float:left;" name="maxMonth" id="maxMonth" >
            <option value="0">&nbsp;&nbsp;&nbsp;&nbsp;</option> 
        </select>
        <pop:JugePermissionTag ename="searchIssueTypeStat">
		    <a id="search" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="generatedDataType">
			<a id="produceDate" href="javascript:void(0)"><span>生成数据</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="exportIssueTypeStat">
			<a id="export" href="javascript:void(0)"><span>导出</span></a>
		</pop:JugePermissionTag>
		<select style="float:left;" name="scopeId" id="scopeId" onchange="onOrgChanged(getCurrentOrgId(),isGrid())">
	   		<option value ="1">下辖部门</option>
	   		<option value ="2">所选部门</option>
		</select>
</div>

<div class="content" style="overflow:hidden;overflow-y:auto;position:relative;">
	<div class="container_24">
		<div class="grid_7 heightAuto">
			<table id="issueTypeStanalList"></table>
		</div>
		<div class="grid_17 heightAuto">
			<div id="tabs" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
				<ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
				<li class="ui-state-default ui-corner-top ui-tabs-selected ui-state-active"><a id="showNewColumnOnHref" href="javascript:void(0)" onclick="showNewColumnOnHref()">事件处理类别统计</a></li>
				<li class="ui-state-default ui-corner-top"><a href="javascript:void(0)" id="showNewLineOnHref"  onclick="showNewLineOnHref()">事件处理类别月度趋势图</a></li>
				</ul>
				<div id="tabs-1">
					<div id="showPieGraph" style="height:300px;"></div>
				</div>
				<div id="tabs-2">
				<div id="showGraph" style="height:300px;"></div>
				</div>
			</div>
		</div>
	</div>
      
	<div id="issueTypeStanalMaintanceDialog"></div>
	<div id="noticeDialog"></div>
	<div id="promptDialog"></div>
</div>

<script type="text/javascript">
 var isTab=true;
$(document).ready(function(){
	//列表信息 和 柱图 饼图   外层容器计算高度
	$("#showPieGraph,#showGraph,#tabs").height($("#contentDiv .content").height()-20);
	$("#tabs").height($("#tabs").height()+60);
	$(".ui-tabs-nav li").bind("click",function(){
		$(".ui-tabs-nav li").removeClass("ui-tabs-selected ui-state-active");
		$(this).addClass("ui-tabs-selected ui-state-active");
	})
	
	var minyears;
    var minmonths;
    var maxyears = "<s:property value='maxYear'/>";
    var maxmonths = "<s:property value='maxMonth'/>";

    minyears = maxyears;
    minmonths = maxmonths;
    
    $("#minYear").val(minyears);
    $("#minMonth").val(minmonths);
    $("#maxYear").append("<option value='"+maxyears+"' selected>"+maxyears+"</option>");
    <s:iterator begin="maxMonth" end="12" var="newMinMonth">
        $("#maxMonth").append("<option value='${newMinMonth }'>"+${newMinMonth }+"</option>");
    </s:iterator>
    $("#maxMonth").val(minmonths);
	$(".content",$("#contentDiv")).height($(".ui-layout-center").height()-$(".submenu").height()-$("#nav").height()-20);
	$("#issueTypeStanalList").jqGridFunction({
		datatype:"local",	
		width:308,
		height:600,
		colModel:[
	  		{name:'id',index:'id',hidden:true},
	  		{name:'domainName',index:'domainName',label:"统计类型 ",sortable:false,width:148},
	 		{name:'count',index:'count',label:"总数",sortable:false,width:150}
		],
		ondblClickRow:showPicOnDialog
	})	

	if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
		onOrgChanged();
	}

	$("#reload").click(function(event){
		var minyears;
        var minmonths;
        var maxyears = "<s:property value='maxYear'/>";
        var maxmonths = "<s:property value='maxMonth'/>";
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
        <s:iterator begin="maxMonth-1" end="12" var="newMinMonth">
            $("#maxMonth").append("<option value='${newMinMonth }'>"+${newMinMonth }+"</option>");
        </s:iterator>
        $("#maxMonth").val(minmonths);
		onOrgChanged();
	});

	$("#search").click(function(event){
		$(".ui-tabs-nav li").removeClass("ui-tabs-selected ui-state-active");
		$(".ui-tabs-nav li").first().addClass("ui-tabs-selected ui-state-active");
		
		if(!$("#minYear").attr("disabled") && $("#minMonth").attr("disabled") && $("#maxYear").attr("disabled") && $("#maxMonth").attr("disabled")){
			searchIssueType();
			return ;
		}
		if($("#minYear").attr("disabled") || $("#minMonth").attr("disabled") || $("#maxYear").attr("disabled") || $("#maxMonth").attr("disabled")){
	    	$.notice({
				level:'warn',
				message:'请选择完整的查询时间'
			});
			return ;
		}
		searchIssueType();
	});	
	$("#produceDate").click(function(event){
		$("#issueTypeStanalMaintanceDialog").createDialog({
			width: 400,
			height: 200,
			title: "事件处理类别统计数据重新生成",
			url: "${path}/issueTypeStanal/issueTypeStanalManage/dispatch.action?mode=produceDate"+"&orgId="+getCurrentOrgId(),
			buttons: {
		   		"生成" : function(event){
					produceStaticDate();
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#export").click(function(event){
		var downloadUrl = '${path}/issueTypeStanal/issueTypeStanalManage/downloadIssueTypeStanal.action' ;
		var gridName = 'issueTypeStanalList';
		excelDownload(downloadUrl,gridName,'issueTypeStanalMaintanceDialog','导出事件处理类别统计信息');
	});	
});

function onOrgChanged(){
	//$(".ui-tabs-nav li").removeClass("ui-tabs-selected ui-state-active");
	//$(".ui-tabs-nav li").first().addClass("ui-tabs-selected ui-state-active");
	
	var minYear = $("#minYear").val()==0?0:$("#minYear").val();
	var minMonth = $("#minMonth").val()==0?0:$("#minMonth").val();
	var maxYear = $("#maxYear").val()==0?0:$("#maxYear").val();
	var maxMonth = $("#maxMonth").val()==0?0:$("#maxMonth").val();
	var scopeId = $("#scopeId").val();
	$("#issueTypeStanalList").setGridParam({
		url:"${path}/issueTypeStanal/issueTypeStanalManage/issueTypeStanal.action",
		datatype:"json",
		page:1
	});
	$("#issueTypeStanalList").setPostData({
    	"orgId":getCurrentOrgId(),
    	"minYear":minYear,
		"minMonth":minMonth,
		"maxYear":maxYear,
		"maxMonth":maxMonth,
		"scopeId":scopeId
   	});
	$("#issueTypeStanalList").trigger("reloadGrid");
	if(isTab){
	$("#showNewColumnOnHref").click();
	}else{
		$("#showNewLineOnHref").click();
	}
}

function showNewLineOnHref(){
	var minYear = $("#minYear").val();
	var minMonth = $("#minMonth").val();
	var maxYear = $("#maxYear").val();
	var maxMonth = $("#maxMonth").val();
	var orgId =getCurrentOrgId();
	var scopeId = $("#scopeId").val();
	showNewLine(orgId,minYear,minMonth,maxYear,maxMonth,scopeId);
	isTab=false;
}

function showNewColumnOnHref(){
	var minYear = $("#minYear").val();
	var minMonth = $("#minMonth").val();
	var maxYear = $("#maxYear").val();
	var maxMonth = $("#maxMonth").val();
	var orgId =getCurrentOrgId();
	var scopeId = $("#scopeId").val();
	$("#showPieGraph").columnChart({
		url: "${path}/issueTypeStanal/issueTypeStanalManage/showIssueColumn.action?scopeId="+scopeId+"&orgId="+orgId+"&minYear="+minYear+"&minMonth="+minMonth+"&maxYear="+maxYear+"&maxMonth="+maxMonth,
		moduleName:"事件处理类别统计"+"("+minYear+"-"+minMonth+"至"+maxYear+"-"+maxMonth+")",
		legend:{y:30},
		width:800
	});
	isTab=true;
}
function showNewLine(orgId,minYear,minMonth,maxYear,maxMonth,scopeId){
	$("#showPieGraph").lineChart({
		url: "${path}/issueTypeStanal/issueTypeStanalManage/showIssueLine.action?scopeId="+scopeId+"&orgId="+orgId+"&minYear="+minYear+"&minMonth="+minMonth+"&maxYear="+maxYear+"&maxMonth="+maxMonth,
		moduleName:""
	})
}


function showPicOnDialog(){
	if(isGrid() || $("#scopeId").val() == "2"){
		return ;
	}
	var id = $("#issueTypeStanalList").getGridParam("selrow");        
	if(id == null){
 		return;
	}
	var obj = $("#issueTypeStanalList").getRowData(id);
	if (obj.count==0) {
		return;
	}
	$("#issueTypeStanalMaintanceDialog").createDialog({
		width: 780,
		height: 550,
		title:'事件处理',
		url:"${path}/statAnalyse/issueTypeStanal/issueTypeStanalList.jsp?orgId="+getCurrentOrgId()
			+"&issueTypeStanal.issueTypeDomain.id="+obj.id+"&minYear="+$('#minYear').val()
			+"&minMonth="+$('#minMonth').val()+"&maxYear="+$('#maxYear').val()
			+"&maxMonth="+$('#maxMonth').val()
			+"&scopeId="+$("#scopeId").val(),
		buttons: {
		   	"关闭" : function(){
		        $(this).dialog("close");
			}
		}
	});
}

function searchIssueType(){
	var minYear = $("#minYear").val()==0?0:$("#minYear").val();
	var minMonth = $("#minMonth").val()==0?0:$("#minMonth").val();

	var maxYear = $("#maxYear").val()==0?0:$("#maxYear").val();
	var maxMonth = $("#maxMonth").val()==0?0:$("#maxMonth").val();
	var scopeId = $("#scopeId").val();
	
	$("#issueTypeStanalList").setGridParam({
		url:"${path}/issueTypeStanal/issueTypeStanalManage/searchIssueTypeStanal.action",
		datatype: "json",
		page:1
	});
	$("#issueTypeStanalList").setPostData({
    	"orgId":getCurrentOrgId(),
    	"minYear":minYear,
		"minMonth":minMonth,
		"maxYear":maxYear,
		"maxMonth":maxMonth,
		"scopeId":scopeId
   	});
	$("#issueTypeStanalList").trigger("reloadGrid");
	
	showNewColumnOnHref();
}

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
	$("#maxYear").val(0);
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
		$("#maxYear").val(0);
	}
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
		$("#maxMonth").val(0);
	}
}
var ticketId='';
var times = 1;
function produceStaticDate(){
	if($("#replaceYear").val() == 0 || $("#replaceMonth").val() == 0){
		$.messageBox({message:"请输入重新生成的年份和月份"});
		return ;
	}
	var orgId = getCurrentOrgId();
	$.ajax({
		async:false,
		url:"${path}/issueTypeStanal/issueTypeStanalManage/reProduceDate.action",
		data:{
			"year":$("#replaceYear").val(),
			"month":$("#replaceMonth").val(),
			"orgId":orgId
		},
		success:function(data){
			 var dataJson=eval("("+data+")");
			 $("body").mask("<div id='load' class='ui-widget-border' ><div style='margin-top:80px;'>&nbsp;</div><p id='progressbar-msg' style='margin-left:60px;text-align:left;'>正在生成数据，数据生成需要较长时间，请耐心等待.</p></div>");
			 ticketId = dataJson.ticketId;
             intarcalId = setInterval(getTicketByIds,500);
		}
	});
}
function getTicketByIds(){
    $.ajax({
        url: '${path}/ticket/getTicketById.action',
        type: 'post',
        dataType:'json',
        data:{
            "ticketId":ticketId
        },
        success: function(data){
        	var dataJson=eval("("+data.description+")");
            if(times==3){
                $("#progressbar-msg").text("正在生成数据，数据生成需要较长时间，请耐心等待.");
                times =1;
            }else{
            	$("#progressbar-msg").text($("#progressbar-msg").text() + dataJson.msg);
            	times ++;
            }
            if(dataJson&&dataJson.errorMsg){
                clearInterval(intarcalId);
                $("body").unmask();
                var errorHtml = "<div id='load' class='ui-widget-border'>"+
                                    "<div class='ui-widget' style='margin-top:30px;' id='errorUploadMsgs'>"+
                                    "</div>"+
                                    "<div class='clear'></div>"+
                                    "<div class='ui-dialog-buttonset'><button id='reloadExcel' type='button' class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only'>重新生成</button><button id='cancel' type='button' class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only'>关闭</button></div>"+
                                "</div>";
                $("body").mask(errorHtml);
                var str="<div class='ui-state-error ui-corner-all' style='width:400px;color:#CC0A0C;margin-top:5px;text-align:left;margin-left:5px'><em class='ui-icon ui-icon-info' style='float: left; margin-right: .3em;'></em>"+dataJson.errorMsg+"</div>";
                $("#errorUploadMsgs").html(str);
                $('#cancel').bind('click', function () {$("#issueTypeStanalMaintanceDialog").dialog("close");$('body').unmask(); });
                $('#reloadExcel').bind('click',function(){
                	$("#issueTypeStanalMaintanceDialog").dialog("close");
                    $('body').unmask();
                    $("#issueTypeStanalMaintanceDialog").createDialog({
                        width: 400,
                        height: 200,
                        title:'数据生成',
                        url:"${path}/issueTypeStanal/issueTypeStanalManage/dispatch.action?mode=produceDate"+"&orgId="+getCurrentOrgId(),
                        buttons:{
                            "重新生成数据" : function(event){
                                  $("#statAnalyseForm").submit();
                           },
                           "关闭" : function(){
                        	    $("#issueTypeStanalMaintanceDialog").dialog("close");
                                $(this).dialog("close");
                           }
                        },
                        shouldEmptyHtml:false
                    });
                });
            }
            if(dataJson.successMsg){
                clearInterval(intarcalId);
                $("body").unmask();
                var succHtml = "<div id='load' class='ui-widget-border'>"+
                                "<div class='ui-widget' style='margin-top:30px;'>"+
                                    "<div class='ui-corner-all ui-widget-border ui-state-highlight' style='width:400px;margin-top:5px;text-align:left;margin-left:5px;'><em class='ui-icon ui-icon-circle-check' style='float: left; margin-right: .3em;'></em>数据生成成功</div>"+
                                "</div>"+
                                "<div class='clear'></div>"+
                                "<div class='ui-dialog-buttonset'><button id='closeImportData' type='button' class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only'>关闭</button></div>"+
                                "</div>";
                $("body").mask(succHtml);
                $('#closeImportData').bind('click', function () {$(this).dialog("close");onOrgChanged(getCurrentOrgId(),isGrid());$('body').unmask();    });
            }
        }
    }); 
}
</script>