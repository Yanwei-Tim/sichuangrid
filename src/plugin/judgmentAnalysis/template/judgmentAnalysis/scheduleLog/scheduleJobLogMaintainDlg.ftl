<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="container container_24">
 
   
	<div class="grid_4 lable-right">
	 <em class="form-req">*</em>
		<label class="form-lbl">服务器名称：</label>
	</div>
	<div class="grid_7">
		 ${(scheduleJobLog.appName)!}
  	</div>
  		
	<div class="grid_4 lable-right" >
	    <em class="form-req">*</em>
		<label class="form-lb1">调度名称：</label>
	</div>
	<div class="grid_7">
		 ${(scheduleJobLog.jobName)!}
	</div>
	
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right" >
	     <em class="form-req">*</em>
		<label class="form-lbl">开始时间：</label>
	</div>
	<div class="grid_7 ">
		 	${scheduleJobLog.startTime?string("yyyy-MM-dd HH:mm:ss")}
	</div>
 
	
	<div class="grid_4 lable-right" >
	     <em class="form-req">*</em>
		<label class="form-lbl">结束时间：</label>
	</div>
	<div class="grid_7 ">
		  ${scheduleJobLog.endTime?string("yyyy-MM-dd HH:mm:ss")}
	</div>
	
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right" >
		<label class="form-lbl">异常数：</label>
	</div>
	<div class="grid_7 ">
		  	${(scheduleJobLog.exceptionNum)!}
	</div>
 
	
	<div class="grid_4 lable-right" >	    
		<label class="form-lbl">自定义参数：</label>
	</div>
	<div class="grid_7 ">
		  	${(scheduleJobLog.taskParamter)!}
	</div>
	
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right" >	    
		<label class="form-lbl">运行环境：</label>
	</div>
	<div class="grid_7 ">
		  	${(scheduleJobLog.ownSign)!}
	</div>
 
	
	<div class="grid_4 lable-right" >	    
		<label class="form-lbl">任务项：</label>
	</div>
	<div class="grid_7 ">
		  	${(scheduleJobLog.taskItems)!}
	</div>
	
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right" >	    
		<label class="form-lbl">每次获取记录数：</label>
	</div>
	<div class="grid_7 ">
		  	${(scheduleJobLog.eachfethDate)!}
	</div>
 	
	<div class="grid_4 lable-right" >	    
		<label class="form-lbl">返回结果数：</label>
	</div>
	<div class="grid_7 ">
		  	${(scheduleJobLog.listSize)!}
	</div>	
 		
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right" >	    
		<label class="form-lbl">创建时间：</label>
	</div>
	<div class="grid_7 ">
		 ${scheduleJobLog.createDate?string("yyyy-MM-dd HH:mm:ss")}
	</div>	
 	
	<div class="grid_4 lable-right" >	    
		<label class="form-lbl">创建人呢：</label>
	</div>
	<div class="grid_7 ">
		  	${(scheduleJobLog.createUser)!}
	</div>
	
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right" >	    
		<label class="form-lbl">备注：</label>
	</div>
	<div class="grid_7 ">
		  	${(scheduleJobLog.remark)!}
	</div>	
		
	<div class="clear"></div>
 

</div>
 