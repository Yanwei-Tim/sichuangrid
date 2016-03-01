<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<script>
$(document).ready(function(){
	 $('#startCreateTime').dateTimePicker({
		yearRange:'1930:2060',
		timeFormat: 'HH:mm:ss',
		maxDate:'+0y',
		onClose: function (selectedDate) {
        	$("#endCreateTime").datepicker("option", "minDate", selectedDate);
        }
	})
	$('#endCreateTime').dateTimePicker({
		yearRange:'1930:2060',
		timeFormat: 'HH:mm:ss',
		maxDate:'+0y',
		onClose:function(selectedDate) {
       		$("#startCreateTime").datepicker("option", "maxDate",selectedDate);
    	}
	})
});
</script>
<div id="dialog-form" class="container container_24">
        <div class='clearLine'>&nbsp;</div> 
        <div class="grid_5 lable-right">
	     	<label class="form-lbl">粉丝昵称/备注名：</label>
	  	</div>
	  	<div class="grid_7">
	    	<input type="text" id="inputCreateUser" name="inbox.createUser"  maxlength="20"  class="form-txt" />
	  	</div>
	  		
	   	  <div class="grid_5 lable-right">
	  		<label class="form-lbl">所属群组：</label> 
	  	</div>
	    <div class="grid_7">
		    <select id="groupId" name='inbox.groupId' class="form-txt">
		    	<option value=''>全部</option>
		    	<@s.iterator value="weChatGroupList">
					 <option value='${groupId}'>${name}</option>
				</@s.iterator>
			</select>
	   </div>
	   <#--<div class="grid_5 lable-right">
	     	<label class="form-lbl">受理状态：</label>
	  	</div>
	  	<div class="grid_7">
	  		<select id="dealStateBySearch" name="inbox.dealState" class="form-txt">
	  		<option value="">全部</option>
	  			<option value='<@s.property value="@com.tianque.plugin.weChat.util.Constants@NOT_ACCEPT" />'>未受理</option>
				<option value='<@s.property value="@com.tianque.plugin.weChat.util.Constants@ACCEPT" />'>已受理</option>
			</select>
  	   </div>-->
	   <div class='clearLine'>&nbsp;</div>
	   	<div class="grid_5 lable-right">
		  	<label class="form-lbl">接收时间从：</label> 
		</div>
		<div class="grid_7">
			<input type="text" value="<@s.date name="inbox.startCreateTime" format="yyyy-MM-dd HH:mm:ss"/>" id="startCreateTime" name="inbox.startCreateTime" readonly="readonly" class="form-txt"/>
		</div>
	  	 <div class="grid_5 lable-right">
	     	<label class="form-lbl">到：</label>
	  	</div>
	  	<div class="grid_7">
  	    	<input type="text" value="<@s.date name="inbox.endCreateTime" format="yyyy-MM-dd HH:mm:ss"/>" id="endCreateTime" name="inbox.endCreateTime" readonly="readonly" class="form-txt"/>
  	   </div>
  	   	<div class='clearLine'>&nbsp;</div> 
  	   <!-- <div class="grid_5 lable-right">
	  		<label class="form-lbl">有/无效：</label> 
	  	</div>
	    <div class="grid_7">
			<select id="availabilitys" name="inbox.availability" class="form-txt">
	  		     <option value="0">全部</option>
	  			<option  value='1'>有效</option>
				<option  value='2'>无效</option>
			</select>
	   </div>
	     <div class="grid_5 lable-right">
	  		<label class="form-lbl">隐藏/显示：</label> 
	  	</div>
	    <div class="grid_7">
		    <select id="keyWordMsgBySearch" name='inbox.isKeyWordMsg' class="form-txt">
		  		     <option value=1>隐藏关键字消息</option>
		  			<option value=0 >显示关键字消息</option>
			</select>
	   </div>-->
	   <#--<div class="grid_5 lable-right">
	     	<label class="form-lbl">转发状态：</label>
	  	</div>
	  	<div class="grid_7">
	  		<select id="forwardingStateBySearch" name="inbox.forwardingState" class="form-txt">
	  		<option value="">全部</option>
	  			<option value='<@s.property value="@com.tianque.plugin.weChat.util.Constants@NOT_FORWARD" />'>未转发</option>
				<option value='<@s.property value="@com.tianque.plugin.weChat.util.Constants@FORWARD" />'>已转发</option>
			</select>
  	   </div>-->
  	   
	   	<div class='clearLine'>&nbsp;</div> 
  	    <div class="grid_5 lable-right">
	  		<label class="form-lbl">服务单号：</label> 
	  	</div>
	    <div class="grid_19">
	      <input type="text" id="serviceId" name="inbox.serviceId"  maxlength="64"  class='form-txt {maxlength:64,messages:{maxlength:$.format("服务单号最多可以输入{0}个字符")}}' />
	   </div>
	  	<div class='clearLine'>&nbsp;</div> 
  	    <div class="grid_5 lable-right">
	  		<label class="form-lbl">内容：</label> 
	  	</div>
	    <div class="grid_7">
			<textarea  maxlength="64"  name="inbox.content" id="content" style="height:70px;width: 400px" class='form-txt {maxlength:64,messages:{maxlength:$.format("回复内容最多可以输入{0}个字符")}}'></textarea>
	   </div>
</div>
