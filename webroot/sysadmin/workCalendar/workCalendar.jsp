<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<style>
	.calendar{padding:0;}
	#gview_workCalendar .ui-jqgrid-btable .ui-widget-content{background:#fff;opacity:1;filter: Alpha(Opacity=100);}
	.calendar span.red{color:#ff6600;}
	.ui-state-highlight, .ui-widget-content .ui-state-highlight, .ui-widget-header .ui-state-highlight  {border: 1px solid #a6c9e2; background: #CEE6FB; color: #363636; }
	.ui-state-highlight a, .ui-widget-content .ui-state-highlight a,.ui-widget-header .ui-state-highlight a { color: #363636; }
	.calendar td.ui-state-highlight,.calendar td.ui-state-hover{border:0;}
	.calendar-bar .button{width:72px;height:23px;line-height:23px;*line-height:20px;vertical-align:middle;}
	#searchDiv{float: left;}
	#searchDiv label{float: left;}
	#searchDiv .x-form-field-wrap {float: left;}
	.calendar-bar p.left {width: 320px;}
</style>

<div class="content">
<div class="ui-widget-border ui-widget-border-on calendar-bar" style="margin-bottom:5px;">
<p class="left" align="left"><label class="form-label">年份：</label> <select name="calendar" class="calendar-select" id="calendar">
	<s:iterator value="set">
		<option value='<s:property />' ><s:property /></option>
	</s:iterator>
</select> 
<label class="form-label">日历类型：</label> <select name="calendarType"  id="calendarType" class="calendar-select" style="width:100px">
		<option value="0">默认工作日历</option>
	    <option value="1">地区特色日历</option>
</select> 
<div id="searchDiv"> 
	<label class="form-label">已有特色日历的地区：</label> 
	<select name="organization" class="calendar-select" id="organization">
	</select> 
</div>
</p>
<p class="middle" align="right">
<s:if test="userName=='admin'">
	<INPUT name="flg" value="${flg}" id="flg" type="hidden"/><span><input type="button" class="button" value="新增" id="add"/></span>
	<span>
		<input type="button" class="button" value="节假日" id="week" />
		<input type="button" class="button" value="工作日" id="work" /> 
		<input type="hidden" class="button" value="工作时间" id="time" />
		<input type="button" class="button" value="删除" id="delete"/>
	</span>
</s:if>
			<span style="color:red;">*红色表示节假日,黑色表示工作日</span>
</p>
<input name="monthAndDay" id="monthAndDay" value="${monthAndDay}" type="hidden"></div>
<table class="calendar" width="100%" id="workCalendar"></table>
<input type="hidden" id="calendarDate" value="" />
<div id="plist47"></div>
<div class="clear"></div>
<div class="calendarDialog"></div>
</div>
<script type="text/javascript">
	var date;
	var currentOrgId = $("#organization").val();
	var iscityBol = false;
$(function() {
// 	initOccurOrgSelector();
	$("#searchDiv").hide();
	findFeatureOrgs();
	function isAdminFormatter(el,options,rowData){
		var colText=rowData[options.colModel.name];
		var workTime = appendWorkTime(colText)
		var colTextJsonData=eval("("+colText+")");
		var year=$("#calendar").attr("value");
		if(colTextJsonData.day=="0"||colTextJsonData.day==null){return "";}
		if(colTextJsonData.holiday=="1"||colTextJsonData.holiday=="7"){
			return "<span day='"+colTextJsonData.day+"' class='red' "+workTime+">"+colTextJsonData.day+ "<em>" + wek(options,colTextJsonData)+"</em>" +"</span>";
		}
		return "<span day='"+colTextJsonData.day+"' "+workTime+">"+colTextJsonData.day + "<em>" + wek(options,colTextJsonData)+"</em>" +"</span>";
	}
	function appendWorkTime(colText){
		var result="";
		var array = colText.split(",");
		var reg=new RegExp("-","g");
		for(var i=0;i<array.length;i++){
			if(array[i].indexOf("startAM")>=0) result+= " startAM="+array[i].split(":")[1].replace(reg,":");
			if(array[i].indexOf("endAM")>=0) result+= " endAM="+array[i].split(":")[1].replace(reg,":");
			if(array[i].indexOf("startPM")>=0) result+= " startPM="+array[i].split(":")[1].replace(reg,":");
			if(array[i].indexOf("endPM")>=0) result+= " endPM="+array[i].split(":")[1].replace(reg,":");
		}
		return result;
	}
	function wek(options,colTextJsonData){
		var _month;
		var thisText;
		switch (options.colModel.name) {
			case "january":
				_month=1;
				break;
			case "february":
				_month=2;
				break;
			case "march":
				_month=3;
				break;
			case "april":
				_month=4;
				break;
			case "may":
				_month=5;
				break;
			case "june":
				_month=6;
				break;
			case "july":
				_month=7;
				break;
			case "august":
				_month=8;
				break;
			case "september":
				_month=9;
				break;
			case "october":
				_month=10;
				break;
			case "november":
				_month=11;
				break;
			case "december":
				_month=12;
				break;
		}
		var year=$("#calendar").attr("value");
		var weekAll = new Array("星期天","星期一","星期二","星期三","星期四","星期五","星期六");
		var _thisDay=new Date(year,_month-1,colTextJsonData.day);
		var week=weekAll[_thisDay.getDay()];
		return week;
	}
	$.fn.extend({
		jqCalendarGrid:function(option){
		var defaultOption={
			datatype: "local",
			width:1000,
			height:400,
			colNames:['一月','二月', '三月', '四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
			colModel:[
				{name:'january',index:'january', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'february',index:'february', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'march',index:'march', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'april',index:'april', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'may',index:'may', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'june',index:'june', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'july',index:'july', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'august',index:'august', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'september',index:'september', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'october',index:'october', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'november',index:'november', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'december',index:'december', width:50,align:"center", sorttype:"int",formatter:isAdminFormatter,sortable:false}
			],
			gridview:true,
			altRows:true,
			viewrecords:true,
			loadComplete:function(){
				$.loadingComp("close");
			}
		}
		defaultOption.width=screen.width-$(".ui-layout-west").width()-$(".center-left").width()-35;
		$(window).resize(function(){
			defaultOption.width=$(".ui-layout-center").width();
		});

		$.extend(defaultOption,option);
		$(this).jqGrid(defaultOption).navGrid('#plist47',{
		edit:false,add:false,del:false
	    });
	    var day="["+$("#monthAndDay").val()+"]";
		var mydata =eval("("+day+")");
		for(var i=0;i<=mydata.length;i++){
			jQuery(this).jqGrid('addRowData',i+1,mydata[i]);
		}
	}
	});
	var option={
		onCellSelect:function(rowid,iCol,cellcontent,event){
			date="";
			var _e = event || window.event;
			var _dateValue=$("#calendarDate").attr("value");
			var _month=$("#"+rowid+" td:eq("+iCol+")").index()+1;
			var _day=$("#"+rowid+" td:eq("+iCol+")"+" span").attr("day");
			if($("#"+rowid+" td:eq("+iCol+")").hasClass("ui-state-highlight")&&$("#"+rowid+" td:eq("+iCol+")").text()!=""){
					_thisdateValue=_month+"-"+_day+",";
					_dateValue=_dateValue.replace(_thisdateValue,"");
					$("#calendarDate").attr("value",_dateValue);
					$("#"+rowid+" td:eq("+iCol+")").removeClass("ui-state-highlight");
					date+=$("#calendarDate").attr("value");
			}

			else if($(".ui-state-highlight").size()<=1&&_e.ctrlKey==false){
				if(_dateValue==""){
					$("#"+rowid+" td:eq("+iCol+")").addClass("ui-state-highlight");
					_dateValue=_dateValue+_month+"-"+_day+",";
					$("#calendarDate").attr("value",_dateValue);
					date+=$("#calendarDate").attr("value");
				}
				else{
					if($(".ui-state-highlight").size()==1){
						$("td.ui-state-highlight").removeClass("ui-state-highlight");
						$("#"+rowid+" td:eq("+iCol+")").addClass("ui-state-highlight");
						$("#calendarDate").attr("value","");
						_thisdateValue=_month+"-"+_day+",";
						$("#calendarDate").attr("value",_thisdateValue);
						date+=$("#calendarDate").attr("value");
					}
				}
			}
			else if(_e.ctrlKey&&$("#"+rowid+" td:eq("+iCol+")").text()!=""){
					$("#"+rowid+" td:eq("+iCol+")").addClass("ui-state-highlight");
					_dateValue=_dateValue+_month+"-"+_day+",";
					$("#calendarDate").attr("value",_dateValue);
					date+=$("#calendarDate").attr("value");
			}
		},
		onSelectRow:function(rowid){
			$("#"+rowid).removeClass("ui-state-hover").removeClass("ui-state-highlight");
	}
}

	$("#workCalendar").jqCalendarGrid(option);
	//日历行 指向样式移除
	$(".calendar tr").hover(function(){
		$(this).css("background","#fff");
	},function(){
		$(this).css("background","#fff");
	});
	//日历单元格样式加载
	$(".calendar tr td").hover(function(){
		$(this).addClass("ui-state-hover");
	},function(){
		$(this).removeClass("ui-state-hover");
	});
	$("#week").click(function(){
		var year=$("#calendar").val();
		var calendarType=$("#calendarType").val();
		if(date==null||date==""
			|| date.substring(date.lastIndexOf("-")+1,date.length)=="undefined,"){
			 $.messageBox({
					message:"请先选择日期",
					level: "warn"
				 });
			 return;
		}
		var urlPath;
		if(calendarType==0){
			urlPath = '${path}/sysadmin/workCalendarManger/updateWorkCalendarIsWeek.action?workCalendar.year='+year+'&monthAndDay='+date;
		}else{
			urlPath = '${path}/sysadmin/workCalendarManger/updateWorkCalendarIsWeek.action?workCalendar.year='+year+'&monthAndDay='+date+"&workCalendar.calendarType="+calendarType+"&workCalendar.orgId="+currentOrgId;
		}
		$.ajax({
            url: urlPath,
            type: 'post',
            dataType:'json',
            success: function(data){
                if(data==true){
                	$.messageBox({message:"修改日期成功!"});
                	$(".ui-state-highlight").each(function(){
            			$(this).removeClass("ui-state-highlight");
            			$(this).find("span").addClass("red");
            			$("#calendarDate").attr("value","");
            			date="";
            		});

                }else{
                	$("#calendarDate").attr("value","");
                	date="";
                	$(".ui-state-highlight").each(function(){
            			$(this).removeClass("ui-state-highlight");
            		});
                	$.messageBox({
						message:data,
						level: "error"
					});
                }
          	 }
        });
	});

	$("#work").click(function(){
		var year=$("#calendar").val();
		var calendarType=parseInt($("#calendarType").val());
		if(date==null||date=="" || date.substring(date.lastIndexOf("-")+1,date.length)=="undefined,"){
			 $.messageBox({
					message:"请先选择日期",
					level: "warn"
				 });
			 return;
		}
		var urlPath;
		if(calendarType==0){
			urlPath = '${path}/sysadmin/workCalendarManger/updateWorkCalendarIsWork.action?workCalendar.year='+year+'&monthAndDay='+date;
		}else{
			urlPath = '${path}/sysadmin/workCalendarManger/updateWorkCalendarIsWork.action?workCalendar.year='+year+'&monthAndDay='+date+"&workCalendar.calendarType="+calendarType+"&workCalendar.orgId="+currentOrgId;
		}
		$.ajax({
            url: urlPath,
            type: 'post',
            dataType:'json',
            success: function(data){
                if(data==true){
                	$.messageBox({message:"修改日期成功!"});
                	$(".ui-state-highlight").each(function(){
            			$(this).removeClass("ui-state-highlight");
            			$(this).find("span").removeClass("red");
            			$("#calendarDate").attr("value","");
            			date="";
            		});
                }else{
                	$(".ui-state-highlight").each(function(){
            			$(this).removeClass("ui-state-highlight");
            		});
                	$("#calendarDate").attr("value","");
                	date="";
                	$.messageBox({
							message:data,
							level: "error"
					});
                }
          	 }
        });
	});
	function calendarChange(){
		var year=$("#calendar").val();
		var calendarType=$("#calendarType").val();
		currentOrgId = $("#organization").val();
		var yearUrlPath;
		var findWorkCalendarUrlPath;
		if(calendarType==0){
			yearUrlPath = '${path}/sysadmin/workCalendarManger/isYear.action?workCalendar.year='+year;
			findWorkCalendarUrlPath = '${path}/sysadmin/workCalendarManger/findWorkCalendar.action?mode=change&workCalendar.year='+year+"&workCalendar.calendarType="+calendarType;
		}else{
			yearUrlPath = '${path}/sysadmin/workCalendarManger/isYear.action?workCalendar.year='+year+"&workCalendar.calendarType="+calendarType+"&workCalendar.orgId="+currentOrgId;
			findWorkCalendarUrlPath = '${path}/sysadmin/workCalendarManger/findWorkCalendar.action?mode=change&workCalendar.year='+year+"&workCalendar.calendarType="+calendarType+"&workCalendar.orgId="+currentOrgId;
		}
		$("#calendarDate").attr("value","");
		date="";
		$.ajax({
	        url: yearUrlPath,
	        type: 'post',
	        dataType:'json',
	        success: function(data){
	            if(data){
	
	            }
	      	 }
	   	 });
		$.ajax({
	        url: findWorkCalendarUrlPath,
	        type: 'post',
	        dataType:'json',
	        success: function(data){
	        	jQuery(".calendar").jqGrid('clearGridData',false);
	        	var day="["+data+"]";
				var mydata =eval("("+day+")");
				for(var i=0;i<=mydata.length;i++){
					jQuery(".calendar").jqGrid('addRowData',i+1,mydata[i]);
				}
	        }
	   	 });
	}
	function findFeatureOrgs(){
		$.ajax({
	        url: '${path}/sysadmin/workCalendarManger/findFeatureOrgs.action?workCalendar.year='+$("#calendar").val(),
	        type: 'post',
	        dataType:'json',
	        success: function(data){
	        	$("#organization").empty();
	        	for(var i=0;i<data.length;i++){
					 $("#organization").append("<option value='"+data[i].id+"'>"+data[i].orgName+"</option>");
				}
	        	calendarChange();
	        }
	   	 });
	}
	$("#organization").focus(findFeatureOrgs);
	$("#organization").change(calendarChange);
	$("#calendar").change(function(){
		findFeatureOrgs();
// 		calendarChange();
	});
	$("#calendarType").change(function(){
		calendarChange();
		var calendarType=$("#calendarType").val();
		if(calendarType==0){
			$("#searchDiv").hide();
		}
		else{
			$("#searchDiv").show();
		}
	});
	$("#add").click(function(){
		var year=$("#calendar").val();
		var calendarType=$("#calendarType").val();
// 		iscityBol = isOrgCity();
// 		if(calendarType==1 &&!iscityBol){
// 			 $.messageBox({level: "warn",message:"特色日历只能添加在地市级"});
// 			 return;
// 		}
		var parameter;
		if(calendarType == 0){
			parameter = "mode=add&year="+$("#calendar").val()+"&calendarType="+calendarType;
		}else{
			parameter = "mode=add&year="+$("#calendar").val()+"&calendarType="+calendarType+"&orgId="+currentOrgId;
		}
		addWorkTimeDlg(parameter);
	});

	$("#time").click(function(){
		var year=$("#calendar").val();
		var calendarType=$("#calendarType").val();
		if(date==null||date=="" || date.substring(date.lastIndexOf("-")+1,date.length)=="undefined,"){
			 $.messageBox({level: "warn",message:"请先选择日期"});
			 return;
		}
		var startAM = $(".ui-state-highlight>span").attr("startam");
		var endAM = $(".ui-state-highlight>span").attr("endam");
		var startPM = $(".ui-state-highlight>span").attr("startpm");
		var endPM = $(".ui-state-highlight>span").attr("endpm");
		var parameter;
		if(calendarType == 0){
			parameter = "mode=edit&year="+$("#calendar").val()+"&monthAndDay="+date+"&startAM="+startAM+"&endAM="+endAM+"&startPM="+startPM+"&endPM="+endPM;
		}else{
			parameter = "mode=edit&year="+$("#calendar").val()+"&monthAndDay="+date+"&startAM="+startAM+"&endAM="+endAM+"&startPM="+startPM+"&endPM="+endPM+"&calendarType="+calendarType+"&orgId="+currentOrgId;
		}
		addWorkTimeDlg(parameter)
	});

	function addWorkTimeDlg(parameter){
		if(parameter!=null && parameter!="" && !parameter.startWith("?")){
			parameter = "?"+parameter;
		}
		$("#calendarDialog").createDialog({
			width: 400,
	        height: 240,
	        title:"设置工作时间",
	        url:"${path}/sysadmin/workCalendar/maintainWorkTimeDlg.jsp"+parameter,
	        buttons: {
	            "保存" : function(event){
	        		$("#maintainForm").submit();
	        		setTimeout(function(){
	        			findFeatureOrgs()
	        		},3000);
	            },
	            "关闭" : function(){
	            	jQuery(this).dialog("close");
	            }
	        }
		})
	}
	function isExistCalendar() {
		var flag = false;
		var urlPath;
		var year=$("#calendar").val();
		var calendarType=$("#calendarType").val();
		if(calendarType==0){
			urlPath = '${path}/sysadmin/workCalendarManger/isYear.action?workCalendar.year='+year;
		}else{
			urlPath = '${path}/sysadmin/workCalendarManger/isYear.action?workCalendar.year='+year+'&workCalendar.calendarType='+calendarType+'&workCalendar.orgId='+currentOrgId;
		}
			
		$.ajax({
			async:false,
	        url:urlPath,
	        type: 'post',
	        success: function(data){
	        	flag = data;
	      	 }
	    });
		return flag;
	}
	$("#delete").click(function(){
		var year=$("#calendar").val();
		var calendarType=$("#calendarType").val();
		if(calendarType==0){
			$.messageBox({level:"warn",message:"不能删除默认的日历！"});
	 		return;
		}
		if(!isExistCalendar()) {
			$.messageBox({message:"没有此日历不需要删除",level:"error"});
			return;
		}
		var urlPath = '${path}/sysadmin/workCalendarManger/deleteWorkCalendar.action?workCalendar.year='+year+'&workCalendar.calendarType='+calendarType+'&workCalendar.orgId='+currentOrgId;
		$.confirm({
			title:"确认删除",
			message:"该工作日历删除后就不能还原，确认删除？",
			width: 300,
			okFunc: function(){
				$.ajax({
					url:urlPath,
					type:'post',
					success:function(data){
						if(data!=true && data!="true" ){
							$.messageBox({message:data,level:"error"});
							return ;
						}
						jQuery(".calendar").jqGrid('clearGridData',false);
						$.messageBox({message:"工作日历删除成功！"});
						findFeatureOrgs();
					}
				});
			}
		});
	});
});
</script>