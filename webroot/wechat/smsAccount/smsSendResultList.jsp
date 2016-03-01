<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp" />
<div class="content">
	<div style="clear: both;"></div>
	<div style="width: 100%">
		<table id="smsSendResultList">
		</table>
		<div id="smsSendResultListPager"></div>
	</div>
</div>
<div id="smsSendResultMaintanceDialog"></div>

<script type="text/javascript">
$(function() {
		//列表显示
		$("#smsSendResultList").jqGridFunction({
			url :'${path}/smsResponseManage/showSmsSendResult.action',
			datatype: "json",
			postData : {
				"smsSendResult.groupId" : ${param.groupId}
			},
			sortname:"createDate",
			multiselect:true,
			shrinkToFit:true,
			colModel : [ {name : "id", index : "id",label : 'id',frozen:true,hidden : true,sortable : false},
			             {name : "mobile", index : 'mobile',sortable : false,label : '手机号',align : 'center',width : 95},
			             {name : "state",index : 'state',sortable : false,label : '结果状态',align : 'center',width : 90}, 
			             {name : "createDate",index : 'createDate',sortable : true,label : '结果时间',align : 'center',width : 90},
			             ]
		});

})
	
</script>