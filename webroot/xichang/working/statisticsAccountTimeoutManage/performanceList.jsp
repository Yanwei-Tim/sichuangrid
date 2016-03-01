<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="content" >
    <div class="ui-corner-all" id="nav">
    	<input type="hidden" name="internalId" id="internalId" value="${internalId }" />
        <div  style="height:25px;line-height:25px;">
            <div class="grid_4 form-right" style=" display:none">
                <label style="line-height:25px;padding-right:10px;">年度：</label>
            </div>
            <div class="grid_8 form-left">
	            <select name="statYear" id="statYear">
	                <s:iterator begin="minYear" end="maxYear" var="newStatYear">
	                    <option value="${newStatYear }">${newStatYear }</option>
	                </s:iterator>
	            </select>
            </div>
            <div class="grid_4 form-right" style=" display:none">
                <label style="line-height:25px;padding:0 10px;">月份：</label>
            </div>
            <div class="grid_8 form-left">
	            <select name="statMonth" id="statMonth">
	            </select>
            </div>
            <div class="grid_8 form-left">&nbsp;</div>
            <a id="search" href="javascript:void(0)"><span>查询</span></a>
            <a id="reload" href="javascript:void(0)"><span>刷新</span></a>
        </div>
       
    </div>
    <div style="width: 100%;">
		<table id="performanceListList"></table>
		 <!-- <div id="performanceListListPager"></div> -->
	</div>
</div>

<script type="text/javascript">
function initMonthVal() {
	$.ajax({
		async: false,
		url: "${path }/account/statisticsAccountTimeoutManage/getMonthTypeList.action",
		success:function(responseData){
			if(!responseData.length) {
				$("#statMonth").append("<option value='1'>1</option>");
				$("#statMonth").hide();
			} else {
				$("#statMonth").show();
			}
			var nowDate = new Date();
			var nowMonth=nowDate.getMonth()+1;
			for(var i = 0;i<responseData.length;i++){
				if(nowMonth>=responseData[i]){
					$("#statMonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
				}
			}
		}
	});
	$("#statYear").val(${nowYear});
	$("#statMonth").val(${nowMonth});
}
    $(function(){
    	   $("#performanceListList").jqGridFunction({
    		   	mtype:'post',
    			datatype: "local",
               	colModel:[
                   	{name:"organization.orgName",label:"单位",index:"organization.orgName",align:"center",width:270},
                	{name:"doing",label:"办理扣分",align:"center",sortable:false,width:270},
                   	{name:"done",label:"办结扣分",align:"center",sortable:false,width:270},
                   	{name:"transfer",label:"流转扣分",align:"center",sortable:false,width:270}
               ],
              	multiselect:false,
              	sortname:"orginternalcode",
              	sortorder:"asc",
       	  	  	rowNum:-1,
       	  		height:$(".ui-layout-center").height()-$("#thisCrumbs").outerHeight()-$(".ui-corner-all").outerHeight()-55
           });
    	   //onOrgChanged(USER_ORG_ID);
    	   initMonthVal();
    	   
    	   
    	   $("#search").click(function (){
    		   onOrgChanged(USER_ORG_ID);
    	   }).click();
    	   $("#reload").click(function (){
    		   onOrgChanged(USER_ORG_ID);
    	   });
    });
    
    function onOrgChanged(orgId){
    	var initParam = {
    		"parentOrgId": orgId,
    		"internalId" :function(){return $("#internalId").val();},
    		"year":function(){return $("#statYear").val();},
    		"month":function(){return $("#statMonth").val();}
    	}
    	$("#performanceListList").setGridParam({
    		url: "${path }/account/statisticsAccountTimeoutManage/statisticsAccountTimeoutList.action",
    		datatype: "json",
    		page:1
    	});
    	$("#performanceListList").setPostData(initParam);
    	$("#performanceListList").trigger("reloadGrid");
    }
  function viewRegradedDetail(){
	  
  }
 function loadAddButton(){
	  
  }
 function showButtons(){
	  
 }
</script> 
