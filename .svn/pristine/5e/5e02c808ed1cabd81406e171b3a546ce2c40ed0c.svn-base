<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<style>
#contactsTableDiv table{border-left:1px solid #CCC; border-top:1px solid #CCC; border-collapse:collapse;}
#contactsTableDiv td{height:25px;overflow:hidden;text-align: center;vertical-align:middle;border-right:1px solid #ccc; border-bottom:1px solid #ccc; border-collapse:collapse; word-break:break-all; word-warp:break-word;}
#contactsTableDiv .ttd1{width: 120px;}
#contactsTableDiv .ttd2{width: 80px;}
#contactsTableDiv .td1{width: 120px;}
#contactsTableDiv .td2{width: 80px;}

#smsArea{}
#smsArea .navLi{padding:0 0 0 20px;position:relative;}
#smsArea .navLi strong{width:7px;height:8px;display:block;background:url(/resource/system/images/arrows-icon.png) no-repeat;position:absolute;top:12px;left:8px;}
#smsArea .navLi strong.down{background-position:0 -46px;}
#smsArea .navLi strong.up{background-position:0 0;}
#smsArea .liEffect.cur{color: #ae2000;}
#smsArea .oa_node_l_0 li{background:none;}
.oa_mod_nav li{padding-left:12px;}
#smsArea .navLi span.oa_cur,
#smsArea .oa_node_l_0 li.oa_cur a{color:#FF0000;}

#selectContactsInfo .smsSelectDelete{color:red}
#selectContactsInfo .smsSelectDelete:hover{color:blue}
.smsSelectDiv{width:120px;height:25px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;}
</style>

<div id="selectContactsInfo" class="container container_24" style="width:99.7%;height:340px;overflow: auto;" >
	<div class="grid_5" style="height:340px;overflow: auto;border-right: 1px #cccccc solid;">
		<div>
			<div id="smsArea" class=" oa_mod_nav  oa_mod_nav_node oa_mod_nav_node_open oa_node_l_0">
                  <div class="navLi liEffect" class="cur">
                  		<strong class="down"></strong>
                  		<a class="nav-node" href="javascript:void(0);" dataName="smsSelectContacts" dataUrl="${path}/interaction/newSMS/smsUplink/workContactList.jsp" id="contactList"><span>平台站内联系人</span></a>
                  </div>
                  <c:if test="${not empty myGroups}">
                  
                  	<div class="navLi">
                  		<strong class="down"></strong>
	                 	<span class="oa_n_t_t oa_mod_nav_node_open" id="789">我的群组</span> 
			             <ul class="oa_node_l_0 hidden"> 
			             	<c:forEach items="${myGroups}" var="obj">
			               		<li><i>●</i><a class="nav-node" href="javascript:void(0);" dataName="smsSelectContacts" dataUrl="${path}/interaction/newSMS/smsUplink/groupHasContactList.jsp?id=${obj.id}" id="my123">${obj.name}</a></li> 
                  			</c:forEach>
			             </ul> 
	              	</div> 
                  	
                  </c:if>
                  <div class="navLi liEffect">
                  		<strong class="down"></strong>
                  		<a class="nav-node" href="javascript:void(0);" dataName="smsSelectContacts" dataUrl="${path}/interaction/newSMS/smsUplink/myContactList.jsp" id="myContactList"><span>其他联系人</span></a>
                  </div>
            </div>
		</div>
	</div>
	
	<div id="smsSelectContacts" class="grid_12" style="height:340px;overflow: auto;"></div>
	
	<div class="grid_1" style="width:3%" ></div>
	
	<div class="grid_6">
			<div id="contactsTableDiv" style="margin-top:20px;height:310px;overflow: auto;"></div>
	</div>
	
</div>
<script type="text/javascript">

$(document).ready(function(){
	initContactsHtml();
	$("#smsArea .nav-node").click(function(){
		var dataUrl=$(this).attr("dataUrl");
		var dataName=$(this).attr("dataName");
		$("#"+dataName).load(dataUrl);
		$("#smsArea .nav-node").prev("strong").addClass("down").removeClass("up");
		$(this).prev("strong").addClass("up").removeClass("down");
		$("#smsArea .nav-node").parent().removeClass("oa_cur");
		$(this).parent().find("span")
			.addClass("oa_cur").parents(".navLi").siblings().find("span").removeClass("oa_cur");
	})
	
	$("#smsArea .oa_n_t_t").click(function(){
		var navNode=$("#smsArea .nav-node");
		var oaNtt=$("#smsArea .oa_n_t_t");
		if($(this).next().hasClass("hidden")){
			$(this).next().removeClass("hidden");
			$(this).find(".oa_node_l_0").removeClass("hidden")
				.closest(".navLi").siblings().find(".oa_node_l_0").addClass("hidden");
			navNode.prev("strong").addClass("down").removeClass("up");
			oaNtt.prev("strong").addClass("down").removeClass("up");
			navNode.parent().find("span").removeClass("oa_cur");
			oaNtt.parent().find("span").removeClass("oa_cur");
			$(this).prev("strong").addClass("up").removeClass("down");	
			$(this).parent().find("span").addClass("oa_cur");
		}else{
			$(this).prev("strong").addClass("down").removeClass("up");
			$(this).next().addClass("hidden");
			$(this).parent().find("span").removeClass("oa_cur");
		}
	})
	
	$("#smsArea .oa_node_l_0 li").click(function(){
		var navNode=$("#smsArea .nav-node");
		var oaNtt=$("#smsArea .oa_n_t_t");
		var oanodel0=$("#smsArea .oa_node_l_0 li");
		navNode.parent(".navLi").find("span").removeClass("oa_cur");
		oanodel0.parents(".navLi").find("span").removeClass("oa_cur");
		oanodel0.removeClass("oa_cur");
		$(this).parents(".navLi").find("span").addClass("oa_cur");
		$(this).addClass("oa_cur");
	})
});

function initContactsHtml(){
	
	var html='<table id="contactTable"><tr><td class="ttd1" >已选人员</td><td class="ttd2" >操作</td></tr>';
	var type = $("#sendObjectType").val(),selectValue="";
	if(type == 2){
		selectValue = $("#selectAddressee").val();
	}
	if(!selectValue){
		var arr = selectValue.split(",");
		for(var i=0;i< 8;i++){
			html += '<tr class="empty"><td class="td1 empty" >&nbsp;</td><td class="td2" >&nbsp;</td></tr>';
		}
		html += '</table>';
	}else{
		var arr = selectValue.split(",");
		for(var i=0;i< arr.length;i++){
			html += '<tr><td class="td1" title="'+arr[i]+'"><div class="smsSelectDiv">'+arr[i]+'</div></td><td class="td2" ><div><a href="javascript:;" class="smsSelectDelete" onclick="deleteMobileInput(this);"><span>删除</span></a></div></td></tr>';
		}
		for(i;i< 8;i++){
			html += '<tr class="empty"><td class="td1" >&nbsp;</td><td class="td2" >&nbsp;</td></tr>';
		}
		html += '</table>';
	}
	$("#contactsTableDiv").html(html);
	$("#smsSelectContacts").load("${path}/interaction/newSMS/smsUplink/workContactList.jsp");
}

function deleteMobileInput(obj){
	$(obj).parent().parent().parent().remove();
	$("#contactTable").find("tr").last().after('<tr class="empty"><td class="td1" >&nbsp;</td><td class="td2" >&nbsp;</td></tr>');
}

function selectRowData(id,status){
	var obj = $("#contactTable");
	var rowData = $("#selectContactsList").getRowData(id);
	var str = rowData.name+"("+rowData.mobileNumber+")";
	var td=obj.find("td[title='"+str+"']");
	if(status){
		if(td.length == 0){
			var tr = obj.find(".empty").first();
			if(!tr){
				obj.find("tr").last().after('<tr><td class="td1" title="'+str+'"><div class="smsSelectDiv">'+str+'</div></td><td class="td2" ><div><a href="javascript:;" class="smsSelectDelete" onclick="deleteMobileInput(this);"><span>删除</span></a></div></td></tr>');
			}else{
				tr.removeClass("empty").html('<td class="td1" title="'+str+'"><div class="smsSelectDiv">'+str+'</div></td><td class="td2" ><div><a href="javascript:;" class="smsSelectDelete" onclick="deleteMobileInput(this);"><span>删除</span></a></div></td>');
			}
		}
	}else{
		if(td.length > 0){
			td.parent().remove();
			obj.find("tr").last().after('<tr class="empty"><td class="td1" >&nbsp;</td><td class="td2" >&nbsp;</td></tr>');
		}
	}
}

</script>


