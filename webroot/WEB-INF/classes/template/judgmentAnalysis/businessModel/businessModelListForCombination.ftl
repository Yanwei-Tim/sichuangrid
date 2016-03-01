<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>


<div class="content">
 
   <div class="ui-corner-all contentNav" id="nav">
        <a id="reload" href="javascript:;"><span>刷新</span></a>
   </div>

    <div class="">
		<table id="businessModelList"> </table>
		<div id="businessModelListPager"></div>
	</div>


</div>

<script type="text/javascript">
        
   $(document).ready(function(){
        
        var colModel=[
		        {name:"id",index:"id",sortable:false,hidden:true},		         		      
		        {name:'name',index:"name",label:'业务模块名称',sortable:true,width:200},
		        {name:'keyName',index:'keyName',label:'关键字',sortable:true,width:120},    
		        {name:'type',index:'type',label:'类型',sortable:true,width:70,align:"center"} 		        
			];
        
        $("#businessModelList").jqGridFunction({
			datatype: "local",
		   	colModel:colModel,
			multiselect:false,
			showColModelButton:true
		});
		
		reloadList();
		
		function reloadList(){
			$("#businessModelList").setGridParam({
			url:'${path}/judgmentAnalysis/businessModelManage/findBusinessModelForPage.action',
			datatype: "json",
			page:1
		    });		    
		    $("#businessModelList").trigger("reloadGrid");
		}
		
			//刷新
			$("#reload").click(function(){
				reloadList();
			});
		
        
    });
        
</script>


