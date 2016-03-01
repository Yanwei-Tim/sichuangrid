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

        <!--      
        <div class='clearLine'>&nbsp;</div> 
        <div class="grid_5 lable-right">
	     	<label class="form-lbl">粉丝昵称/备注名：</label>
	  	</div>
	  	<div class="grid_7">
	    	<input type="text" id="createUser" name="weChatHiddenDanger.createUser"  maxlength="20"  class="form-txt" />
	  	</div>
	   <div class="grid_5 lable-right">
	     	<label class="form-lbl">受理状态：</label>
	  	</div>
	  	<div class="grid_7">
	  		<select id="dealStateBySearch" name="weChatHiddenDanger.dealState" class="form-txt">
	  		<option value="">全部</option>
	  			<option value='<@s.property value="@com.tianque.plugin.weChat.util.Constants@NOT_ACCEPT" />'>未受理</option>
				<option value='<@s.property value="@com.tianque.plugin.weChat.util.Constants@ACCEPT" />'>已受理</option>
			</select>
  	   </div>
  	    --!>
  	    
	   <div class='clearLine'>&nbsp;</div>
	   	<div class="grid_5 lable-right">
		  	<label class="form-lbl">接收时间从：</label> 
		</div>
		<div class="grid_7">
			<input type="text" value="<@s.date name="weChatHiddenDanger.startCreateTime" format="yyyy-MM-dd HH:mm:ss"/>" id="startCreateTime" name="weChatHiddenDanger.startCreateTime" readonly="readonly" class="form-txt"/>
		</div>
	  	 <div class="grid_5 lable-right">
	     	<label class="form-lbl">到：</label>
	  	</div>
	  	<div class="grid_7">
  	    	<input type="text" value="<@s.date name="weChatHiddenDanger.endCreateTime" format="yyyy-MM-dd HH:mm:ss"/>" id="endCreateTime" name="weChatHiddenDanger.endCreateTime" readonly="readonly" class="form-txt"/>
  	   </div>
  	   	<div class='clearLine'>&nbsp;</div> 
	     <div class="grid_5 lable-right">
	  		<label class="form-lbl">异常类型：</label> 
	  	</div>
	    <div class="grid_7">
			<select name="weChatHiddenDanger.propertyDictId" class="form-txt" id="propertyDictId" onchange="">
                <@pop.PropertyDictLevelSelectTag name="@com.tianque.domain.property.PropertyTypes@DANGER_EXCEPTION_TYPE" defaultValue="请选择" />
            </select>
	   </div>
	   	<div class='clearLine'>&nbsp;</div> 
  	    <div class="grid_5 lable-right">
	  		<label class="form-lbl">地点：</label> 
	  	</div>
	    <div class="grid_19">
	      <input type="text" id="address" name="weChatHiddenDanger.address"  maxlength="64"  class='form-txt {maxlength:64,messages:{maxlength:$.format("服务单号最多可以输入{0}个字符")}}' />
	   </div>
	   
	  	<div class='clearLine'>&nbsp;</div> 
  	    <div class="grid_5 lable-right">
	  		<label class="form-lbl">异常情况：</label> 
	  	</div>
	    <div class="grid_7">
			<textarea  maxlength="64"  name="weChatHiddenDanger.exceptionSituation" id="exceptionSituation" style="height:70px;width: 400px" class='form-txt {maxlength:64,messages:{maxlength:$.format("回复内容最多可以输入{0}个字符")}}'></textarea>
	   </div>
</div>
