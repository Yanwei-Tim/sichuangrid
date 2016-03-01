<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<div id="dialog-form" title="研判分析改造历史统计" class="container container_24">
	 <form id="maintainForm" method="post"	action="${path}/sysadmin/jobMonitor/manualBuilddataCompletion.action" >
	 	 <pop:token/>
	 	<div class="grid_6 lable-right">
		<em class="form-req">*</em>
		<label class="form-lbl">统计类型： </label>
		</div>
	 	 <div class="grid_18" >
	 	 <select name="packetStatisticsVo.typeName" class='form-txt'>
	 	 	<option value='builddata'>楼宇</option>
	 	 	<option value='all_youth_population'>青少年</option>
	 	 	<option value='orgLoginStanals'>登陆统计</option>
	 	 	 
	 	 </select>
  		</div>
  		
		<div class="grid_6 lable-right">
		<em class="form-req">*</em>
		<label class="form-lbl">统计起始年份： </label>
		</div>
		<div class="grid_6" >
			<select name="packetStatisticsVo.startYear" class='form-txt'>
				<option value="2015">2015</option>
				<option value="2014">2014</option>
				<option value="2013">2013</option>
				<option value="2012">2012</option>
			</select>
  		</div>
  		
		<div class="grid_6 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">月份： </label>
		</div>
		<div class="grid_6">
		   <select name="packetStatisticsVo.startMonth" class='form-txt'>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
			</select>
  		</div>
  		<div class='clearLine'>&nbsp;</div>
  		<div class="grid_6 lable-right">
		<em class="form-req">*</em>
		<label class="form-lbl">统计终止年份： </label>
		</div>
		<div class="grid_6" >
			<select name="packetStatisticsVo.endYear" class='form-txt'>
				<option value="2015">2015</option>
				<option value="2014">2014</option>
				<option value="2013">2013</option>
				<option value="2012">2012</option>
			</select>
  		</div>
  		
		<div class="grid_6 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">月份： </label>
		</div>
		<div class="grid_6">
		   <select name="packetStatisticsVo.endMonth" class='form-txt'>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
			</select>
  		</div>
	</form>
</div>	
<script type="text/javascript">
$("#maintainForm").formValidate({
	promptPosition: "bottomLeft",
	submitHandler: function(form) {
     $(form).ajaxSubmit({
         success: function(data){
        	 console.log(data);
	        	 if(data!=true){
                	 $.messageBox({
						message:data,
						level: "error"
					 });
                 	return;
                 }
				 $.messageBox({message:"数据清洗成功!"});
  	   },
  	   error: function(XMLHttpRequest, textStatus, errorThrown){
  	      alert("提交错误");
  	   }
  	  });
},
	rules:{
	},
	messages:{
	}
});
</script>