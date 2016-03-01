<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<#macro tempData result className idName> 
	<!--判断属性类型--字典-->
	<#if result.argType=="PropertyDict">
		<#if result.tempValue!="-1"><@pop.PropertyDictViewTag name="${(result.PropertyDict)!}" defaultValue="${(result.tempValue)!}" /><#else><空></#if>
	<!--判断属性类型--布尔型-->
	<#elseif result.argType=="boolean">
		<#if result.tempValue=='true'>是<#elseif result.tempValue=='false'>否<#else><空></#if>
	<!--判断属性类型--long型-->
	<#elseif result.argType=="long">
		<#list result.options as option>
			<#if result.tempValue==option>${result[option]!}</#if>
		</#list>
	<!--判断属性类型--checkBox-->
	<#elseif result.argType=="checkbox">
		<#list result.tempArray as option>
			<p>${result[option]!"<空>"}</p>
		</#list>
	<#elseif result.argType=="int">
		<#if result.realValue=="0" || result.realValue=="">是<#elseif result.realValue=="1">否<#else><空></#if>
	<#else>
		 <#if result.tempValue=='null'><空><#elseif result.tempValue==''><空><#else>${(result.tempValue)!}</#if>
	</#if>
	<#if result.argType=="strs">
		<#list result.submitName as submitName>
			<input type="hidden"  name="temp.${submitName!}" value="${(result[submitName+'Temp'])!}" class="${className!}" id="${submitName!?replace(".","")}${idName!}"/>
		</#list>
	<#elseif result.argType=="checkbox">
			<input type="hidden" value="<#list result.tempArray as option>${option!},</#list>" 
			name="${(result.submitName)!}" class="${className!}" checked id="${(result.submitName)!?replace(".","")}${idName!}"/>
	<#else>
		<input type="hidden"  name="temp.${(result.submitName)!}" value="${(result.tempValue)!}" class="${className!}" id="${(result.submitName)!?replace(".","")}${idName!}"/>
	</#if>
</#macro> 

<#macro realData result  idName> 
	<!--判断属性类型--字典-->
	<#if result.argType=="PropertyDict">
		<#if result.realValue!="-1"><@pop.PropertyDictViewTag name="${(result.PropertyDict)!}" defaultValue="${(result.realValue)!}" /><#else><空></#if>
	<!--判断属性类型--布尔型-->
	<#elseif result.argType=="boolean">
		<#if result.realValue=='true'>是<#elseif result.realValue=='false'>否<#else><空></#if>
	<!--判断属性类型--long型-->
	<#elseif result.argType=="long">
		<#list result.options as option>
			<#if result.realValue==option>${result[option]!}</#if>
		</#list>
	<!--判断属性类型--checkBox-->
	<#elseif result.argType=="checkbox">
		<#list result.realArray as option>
			<p>${result[option]!"<空>"}</p>
		</#list>
	<#elseif result.argType=="int">
		<#if result.realValue=="0" || result.realValue=="">是<#elseif result.realValue=="1">否<#else><空></#if>
	<#else>
		 <#if result.realValue=='null'><空><#elseif result.realValue==''><空><#else>${(result.realValue)!}</#if>
	</#if>
	<#if result.argType=="strs">
		<#list result.submitName as submitName>
			<input type="hidden"  name="temp.${submitName!}" value="${(result[submitName])!}" id="${submitName!?replace(".","")}${idName!}"/>
		</#list>
	<#elseif result.argType=="checkbox">
		<#if idName=="Real">
			<input type="hidden" value="<#list result.realArray as option>${option!},</#list>" 
		name="${(result.submitName)!}"  checked id="${(result.submitName)!?replace(".","")}${idName!}"/>
		<#else>
			<input type="hidden" value="<#list result.tempArray as option>${option!},</#list>" 
		name="${(result.submitName)!}"  checked id="${(result.submitName)!?replace(".","")}${idName!}"/>
		</#if>
	<#else>
		<input type="hidden"  name="temp.${(result.submitName)!}" value="${(result.realValue)!}" id="${(result.submitName)!?replace(".","")}${idName!}"/>
	</#if>
</#macro> 

<script type="text/javascript">
	function chooseValue(name,value){
		var anotherValue = "Temp";
		if(value=="Temp"){
			anotherValue="Real";
		}
		$("input[id^="+name+value+"]").each(function(i) {
			$("#"+name+value+i).addClass("submitValue");
		});
		$("input[id^="+name+anotherValue+"]").each(function(i) {
			$("#"+name+anotherValue+i).removeClass("submitValue");
		});
		$("#"+name+value).addClass("submitValue");
		$("#"+name+anotherValue).removeClass("submitValue");
	}
	function chooseValues(value){
		var anotherValue = "Temp";
		if(value=="Temp"){
			anotherValue="Real";
		}
		$("#province"+value).addClass("submitValue");
		$("#province"+anotherValue).removeClass("submitValue");
		$("#city"+value).addClass("submitValue");
		$("#city"+anotherValue).removeClass("submitValue");
		$("#district"+value).addClass("submitValue");
		$("#district"+anotherValue).removeClass("submitValue");
	}
</script>
<#macro radioCode result>
	<td class="hideRadio no-b-l">
		<#if result.argType=="strs">
			<input type="radio" name="strs<#list result.submitName as submitName>${submitName}</#list>" value="Temp" onclick="chooseValues(this.value)"/>
		<#else>
			<input type="radio" name="${(result.submitName)!?replace(".","")}" value="Temp" onclick="chooseValue(this.name,'Temp')"/>
		</#if>
	</td>
	<td class='longNode'>
		<@realData result=result idName="Real"/>
	</td>
	<td class="hideRadio no-b-l">
		<#if result.argType=="strs">
			<input type="radio" name="strs<#list result.submitName as submitName>${submitName}</#list>" value="Real" onclick="chooseValues(this.value)"/>
		<#else>
			<input type="radio" name="${(result.submitName)!?replace(".","")}" value="Real" onclick="chooseValue(this.name,'Real')"/>
		</#if>
	</td>
</#macro>

<div id="tFileCompare">
<form id="compareForm">
<input type="hidden" name="targetOrgId" value="${targetOrgId!}" class="submitValue"/>
<input type="hidden" name="targetId" value="${targetId!}" class="submitValue"/>
<span class="tFileCompareTips">提示：请对差异字段进行核实，认领正确的字段信息。如果没有差异，可直接认领。</span>
<table class="cfsj">
    <thead>
        <tr style="background:#f2f2f2">
            <th style="width:20%;">字段</th>
            <th style="width:35%;">职能部门导入数据</th>
            <th style="width:5%;">&nbsp;</th>
            <th style="width:35%;">平台添加数据</th>
            <th style="width:5%;">&nbsp;</th>
        </tr>
    
    </thead>
    
    <tbody>
    <!--遍历从action传来的列表-->
    <!--第一次遍历无冲突必填-->
    <#list compareList as result>
    	<#if result.tempValue == result.realValue && result.notNull=="true" >
    			<tr class="noConflict" <#if result.label=="有无住房" || result.label=="无房原因">style="display:none;"</#if>>
    				<td>
    					<!--<em class="form-req">*</em>-->
    					<label class="form-lb1">${(result.label)!}：</label>
    				</td>
    				<td colspan="4" class='longNode'>
    					<@tempData result=result className="submitValue"  idName=""/>
    				</td>
    			</tr>
    	</#if>
    </#list>
    <!--第二次遍历无冲突非必填-->
    <#list compareList as result>
    	<#if result.tempValue == result.realValue && result.notNull=="false">
    			<tr class="noConflict" >
    				<td>
    					<label class="form-lb1">${(result.label)!}：</label>
    				</td>
    				<td colspan="4" class='longNode'>
    					<@tempData result=result className="submitValue"  idName=""/>
    				</td>
    			</tr>
    	</#if>
    </#list>
    <!--第三次遍历有冲突必填-->
    <#list compareList as result>
    	<#if result.tempValue!=result.realValue && result.notNull=="true">
				<tr class="hasConflict">
    				<td>
   					<!--<em class="form-req">*</em>-->
    					<label class="form-lb1">${(result.label)!}：</label>
    				</td>
    				<td class='longNode'>
    					<@tempData result=result className=""  idName="Temp"/>
    				</td>
    					<@radioCode result=result/>
    			</tr>
    	</#if>
    </#list>
    <!--第四次遍历有冲突非必填-->
    <#list compareList as result>
    	<#if result.tempValue!=result.realValue && result.notNull=="false">
    			<tr class="hasConflict">
    				<td>
    					<label class="form-lb1">${(result.label)!}：</label>
    				</td>
					<td class='longNode'>
    					<@tempData result=result className=""  idName="Temp"/>
    				</td>
    					<@radioCode result=result/>
			    </tr>
    	</#if>
    </#list>
   
    </tbody>
</table>
</form>
<#--<a href="javascript:;" style="color:blue;margin-left:10px;" id = "confirm">确定修改</a>-->
<#--<a href="javascript:;" style="color:blue;" id = "cancel">取消</a>-->
</div>
<script type="text/javascript">
var noConflict = true;
	<#list compareList as result>
    	<#if result.tempValue != result.realValue>
   			 noConflict = false;
   		</#if>	 
   	</#list>
$(function(){
    
	/* $('tr:has(.hideRadio)').eq(0).find(".hideRadio").removeClass("hideRadio");
	
	$('.cfsj tr').not('.noConflict').find(':radio').click(function(){
		$('.cfsj tr').not('.noConflict').find(':radio').click(function(){		
			var thisparents = $(this).parents("tr");
			thisparents.removeClass('yellowbg'); 
			var nextfirst = thisparents.nextAll(":has(:radio)").eq(0);
			if(nextfirst.has(".hideRadio").length === 1)
			{
				nextfirst.children("td").removeClass("hideRadio").parents("tr").addClass('yellowbg');
			}
			else
			{
				return true;
			}
		});
	}); */
	
	/* $('#confirm').click(function(){
		var radiosize = $('.cfsj .necessary input:radio:checked').size();
		if(radiosize < 2){
			$('.necessary').not('.noConflict').addClass('redborder').has('input:radio:checked').removeClass('redborder');
		}
	});
	
	$('.cfsj .necessary input:radio').click(function(){
		$(this).parents('tr').removeClass('redborder');
	}); */
    
    
    
    var wrap = $('#tFileCompare');
    // 第一个项加黄色背景
    wrap.find('.hasConflict:first').addClass('yellowbg')
        .find('td.hideRadio').removeClass('hideRadio');

    // radio 元素绑定事件
    wrap.find('input[type=radio]').bind('click',function(){
    
        var tr = $(this).closest('tr').removeClass('yellowbg'),
            nextUntil = tr.nextAll(":has(:radio)").eq(0);
           $(this).closest('tr').addClass('checked');
           var checked=$(nextUntil[0]).hasClass("checked");
        if( nextUntil[0] && checked ==false ){
            nextUntil.addClass('yellowbg').find('td').removeClass('hideRadio');
        }else{
             noConflict = true;
        }
    })
    
    
    wrap.find('#confirm').bind('click',function(){
    
        var hideNode = wrap.find('td.hideRadio');
        var nNode = wrap.find('.necessary');
        
        if ( !!hideNode ){
            nNode
                .not('.noConflict').addClass('redborder')
                .has('input:radio:checked').removeClass('redborder')
        }
        
    })
    
});
</script>



















