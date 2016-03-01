<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<%-- <s:action name="ajaxOrganization" var="getLoginOrg" executeResult="false" namespace="/sysadmin/orgManage" > --%>
<%-- 	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param> --%>
<%-- </s:action> --%>
<%-- <s:action name="getTitleProvinceName" var="getTitleProvinceName" executeResult="false" namespace="/sysadmin/orgManage" /> --%>

<div class="column column-inform" id="noticeListColumn">
	<div class="portlet">
		<div class="portlet-header">通知通报</div>
		<div class="portlet-content" style="height:100px;overflow-y:auto;">
			<ul class="inform-list" id="noticeList">
			<%--
				<s:action name="publicNoticeList" var="getNoticeList" executeResult="false" namespace="/sysadmin/publicNoticeManage" >
					<s:param name="publicNoticeLevel" value="1"></s:param>
					<s:param name="organizationId" value='<s:property value="@com.tianque.core.util.ThreadVariable@getUser().organization.id"/>'></s:param>
				</s:action>
				
				<s:iterator value="#getNoticeList.result" id="id"> 
					<s:property value="#id.publicNoticeTitle" /> 
				</s:iterator>
			--%>
			</ul>
		</div>		
	</div>
</div>
<div class="workMemos workMemosHaining">
	<div class="tips addworkmemos">
		<div class="tips-text">
			<form id="addWorkMemo" method="post">
				<div>新增工作备忘录</div>
				<div class="">
					<textarea class="addwork-textarea {required:true,maxlength:60,messages:{required:'请输入备忘录内容',maxlength:'最多只能输入60字符'}}" name="workMemo.memoContents" id="text"></textarea>
				</div>
				<div class="info">
					<span>时间：</span><span id="addWorkDate"></span>
					<input type="hidden" id="workDate" name="workMemo.addMemoDate" value=''/>
				</div>
				<div class="workmemo-btn">
					<input type="button" value="确定" class="btn okbtn" id="submit"></input>
					<input type="button" value="关闭" class="btn closebtn" id="workmemoClose"></input>
				</div>
			</form>
		</div>
		<div class="tips-angle diamond"></div>
	</div>
	<h3 class="btitle">日程安排</h3><!-- <a href="javascript:;" class="add-workmemo" id="addWorkmemo">新增</a> -->
	<div class="calendar">
		<div class="lunarCalendar">
			<h3 id="today"></h3>
			<div id="today_info">
				
			</div>
		</div>
		<div id="workMemo"></div>
	</div>
</div>
<div class="today">
	<s:action name="getTodayMemoContentsByUserIdAndAddMemoDate" var="getMemeContents" executeResult="false" namespace="/workMemo/workMemoManage"/>
		<h3 class="btitle">今天（<span id="todayDate">${getMemeContents.today}</span>） </h3>
		<ul>
			<c:forEach items="${getMemeContents.list}" var="workMemo">
				<li id="${workMemo.id}">${workMemo.memoContents}</li>
			</c:forEach>
		</ul>
</div>
<div id="maintainDlg"></div>
<div id="publicNoticeRecordDlg"></div>
<s:bean name="java.util.Date" var="date"/>
<script>
function workbenchLeftResize(){
	clearTimeout(window._workbenchLeftTimer);
	$(".workbench-ctt-left").height(document.documentElement.clientHeight-100);
	$(".today ul").height($(".workbench-ctt-left").outerHeight(true)-$(".workMemos").outerHeight(true)-$(".today .btitle").outerHeight(true))
}
$(function(){
	$.ajax({
		url:'${path}/sysadmin/publicNoticeManage/publicNoticeReceiveList.action?publicNoticeLevel=2&organizationId=<s:property value="@com.tianque.core.util.ThreadVariable@getUser().organization.id"/>',
		success:function(data){
			if(data.rows.length<=0){
				$("#noticeList").append("<li><span>无通知通报</span></li>");
			}
			for(var i=0;i<data.rows.length;i++){
				//fateson add  需要修改查看模式
				//$("#noticeList").append("<li><a href='javascript:void(0)' title="+data.rows[i].publicNoticeTitle+" onclick=showPageByTopMenu('systemManagement-publicNoticeManagement')>"+data.rows[i].publicNoticeTitle+"</a></li>");
				$("#noticeList").append("<li><a href='javascript:void(0)' title="+data.rows[i].publicNoticeTitle+" onclick=showPublicNoticeRecord("+data.rows[i].id+")>"+data.rows[i].publicNoticeTitle+"</a></li>");
			}
		}
	});
	$.lunarInsert({
		defaultDate:'<s:date name="#date" format="yyyy-MM-dd"/>'
	})

	$("#workMemo").workMemoPicker({
		defaultDate:'<s:date name="#date" format="yyyy-MM-dd"/>'
	});

	$("#submit").click(function(){
		if($("#text").val()==null || $("#text").val()==''){
			$.messageBox({
				level: "error",
				message:"请填写备忘内容！"
 			});
			return;
		}
		if($("#text").val().length>60){
			$.messageBox({
				level: "error",
				message:"备忘内容不能超过60字！"
 			});
			return;
		}
		$("#addWorkMemo").ajaxSubmit({
			async: false,
			url: "${path}/workMemo/workMemoManage/addWorkMemo.action",
			type:'POST',
			success:function(data){
				if(!data.id){
			 		$.messageBox({
						level: "error",
						message:"请填写备忘内容！"
		 			});
					return;
				}
				$(".addworkmemos").hide();
				$.messageBox({message:" 工作备忘录新增成功！"});
				var addDate=$("#workDate").attr("value");
				var today='<s:date name="#date" format="yyyy-MM-dd"/>';
				var thisDateArr=today.split("-");
				if(addDate==thisDateArr[0]+"-"+parseInt(thisDateArr[1])+"-"+parseInt(thisDateArr[2])){
					$(".today ul").append('<li id='+data.id+'>'+data.memoContents+'</>');
				}
				var dom=$("#workMemo").find("a").filter(function(index) {
					return $(this).text() == addDate.split("-")[2];
				});
				dom.addClass("hasData").data("hasData",true);
				$("#workMemo").workMemoPicker("update");
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert("提交数据时发生错误");
			}
		});
	});
	$("#workmemoClose").click(function(){
		$(".addworkmemos").hide();
	})

	var isAdminForJudgeModifyRole = '<s:property value="#loginAction.user.admin"/>';
	var UserNameForJudgeModifyRole = '<s:property value="#loginAction.user.userName"/>';
	if(UserNameForJudgeModifyRole == "admin"||isAdminForJudgeModifyRole=='true'||$.cookie('oldSid')!=null){
		$("#modifyRoleLi").show();
	}
	
	workbenchLeftResize();
	$(window).resize(function(){
		window._workbenchLeftTimer=setTimeout(function(){
			workbenchLeftResize();
		},300);
	});
})

    var dialogWidth = 500;
    var dialogHeight = 300;
function showPublicNoticeRecord(publicNoticeId){
	$("#publicNoticeRecordDlg").createDialog({
        width:dialogWidth,
		width:800,
		height:500,
        title:"查看通知通报信息",
        url:"${path}/sysadmin/publicNoticeManage/dispatch.action?mode=readReceive&publicNotice.id="+publicNoticeId,
        buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
    });
}


	
	function dlgShowHistory(){
		$("#noticeDlg").createDialog({
			width:910,
			height:480,
			title:"查看通知通告历史",
			url:"${path}/workBench/publicNoticeHistoryList.jsp",
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	
	}
</script>