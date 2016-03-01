<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<div id="gisFunctionFields">
	<input type="hidden" name="function.id" value="${function.id }" />
	<input type="hidden" name="function.functionType" value="refineSearch" />
			
   	<div class="grid_5 lable-right">
		<label class="form-lbl">绑定类型：</label>
	</div>
	<div class="grid_6 ">
		<input type="hidden" id="function-event-TypeHidden"
			<s:if test="function.boundEventIsValid==null" > name="function.event.id" value="${function.event.id }" </s:if> />
		<select name="function.event.id" id="function-event-Type" class="form-select control"
   			<s:if test='"add".equals(mode)'>disabled='true'</s:if>
   			<s:if test="function.boundEventIsValid==null" >disabled='true'</s:if>
   			<s:if test="function.boundEventIsValid==false" >disabled='true'</s:if>
   			id="function-event">  
  				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BOUNDTYPE" defaultValue="${function.event.id }" />
		</select>
	</div>
	<div class="grid_4 lable-right">
	</div>
	<div class="grid_4 ">
		<input id="function-boundEventIsValid" type="checkbox"  name="function.boundEventIsValid" value="true" class="dialogtip control"  
			<s:if test="function.boundEventIsValid" >checked="checked"</s:if> />
		<label class="form-check-text">支持绑定</label>
	</div>
	<div class="grid_4 ">
		<input id="function-unBoundEventIsValid" type="checkbox"  name="function.unBoundEventIsValid" value="true" class="dialogtip control"  
			<s:if test="function.unBoundEventIsValid" >checked="checked"</s:if> />
		<label class="form-check-text">支持解绑</label>
	</div>
	<div class='clearLine'></div>
			
	<fieldset class="fieldset"> 
			<legend> 列表显示字段配置</legend>
		<div id="title">
			<div class="grid_5 lable-right">
				<label class="form-lbl">标题名称：</label>
			</div>
			<div class="grid_6">
				<input type="text" id="function-titleName" name="function.titleName"  class="form-txt dialogtip" value="${function.titleName }" maxlength="20"  title="用于列表显示标题字段的中文名称。"/>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">标题字段：</label>
			</div>
			<div class="grid_6">
			<input type="text" id="function-titleValue" name="function.titleValue" class="form-txt dialogtip columnSelect" value="${function.titleValue }" maxlength="60"   title="用于列表显示标题字段的属性名称。"/>
			</div>
		</div>
		<div class='clearLine'></div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl ">图标Url：</label>
		</div>
		<div class="grid_16">
			<input type="text" id="function-iconUrl" name="function.iconUrl"  class="form-txt control" value="${function.iconUrl }" maxlength="600" readonly/>
		</div>
		<div class="grid_3 lable-right">
			<input id="function-isViewIcon" type="checkbox"  name="function.isViewIcon" value="true"  class="dialogtip control" 
				<s:if test="function.isViewIcon" >checked="checked"</s:if> />
			<label class="form-check-text">是否显示 </label>
		</div>
		<div class='clearLine'></div>
		<s:iterator value="{'A','B','C','D','E'}"  var="char" status="st">
			<s:iterator value="{'fieldNameA','fieldNameB','fieldNameC','fieldNameD','fieldNameE'}"  var="fieldName" status="stk">
				<s:if test="#st.index==#stk.index"> <s:set id="fieldCName" value="fieldName"></s:set> </s:if>
			</s:iterator>
			<s:iterator value="{'fieldA','fieldB','fieldC','fieldD','fieldE'}"  var="field" status="stv">
				<s:if test="#st.index==#stv.index"> <s:set id="fieldEName" value="field"></s:set> </s:if>
			</s:iterator>
			<div id="field${char}">
				<div class="grid_5 lable-right">
					<label class="form-lbl">中文名${char}：</label>
				</div>
				<div class="grid_6">
					<input type="text" id="function-fieldName${char}" name="function.fieldName${char}"  class="form-txt dialogtip" value="${function[fieldCName] }" maxlength="20" title="用于列表显示的第${st.index+1 }个字段的中文名称。" />
				</div>
				<div class="grid_4 lable-right">
					<label class="form-lbl">属性${char}：</label>
				</div>
				<div class="grid_6">
					<input type="text" id="function-field${char}" name="function.field${char}" class="form-txt dialogtip columnSelect" value="${function[fieldEName] }"" maxlength="60"  title="用于列表显示的第${st.index+1 }个字段的属性名称。" />
				</div>
				<div class="grid_3 lable-right">
					<s:if test="#st.index==0">
						<input type="button"  class="button" value="添加内容" id="function-contentButton"  />
					</s:if>
					<s:else>
						<input type="button"  class="button" value="删除内容" id="function-ClearContentButton"  />
					</s:else>
				</div>
			</div>
			<div class='clearLine'></div>
		</s:iterator>
	</fieldset>	
	<fieldset class="fieldset">  <legend> 搜索字段配置</legend>
		<s:iterator value="{'A','B','C'}"  var="char" status="st">
			<s:iterator value="{'searchFieldAName','searchFieldBName','searchFieldCName'}"  var="fieldName" status="stk">
				<s:if test="#st.index==#stk.index"> <s:set id="fieldCName" value="fieldName"></s:set> </s:if>
			</s:iterator>
			<s:iterator value="{'searchFieldA','searchFieldB','searchFieldC'}"  var="field" status="stv">
				<s:if test="#st.index==#stv.index"> <s:set id="fieldEName" value="field"></s:set> </s:if>
			</s:iterator>
			<div id="fieldSearch${char}">
				<div class="grid_5 lable-right">
					<label class="form-lbl">中文名${char}：</label>
				</div>
				<div class="grid_6">
					<input type="text" id="function-searchField${char}Name" name="function.searchField${char}Name" class="form-txt dialogtip" value="${function[fieldCName] }" maxlength="20" title="用于精确搜索的第${st.index+1 }个条件的中文名称。"  />
				</div>
				<div class="grid_4 lable-right">
					<label class="form-lbl">属性${char}：</label>
				</div>
				<div class="grid_6">
					<input type="text" id="function-searchField${char}" name="function.searchField${char}" class="form-txt dialogtip columnSelect" value="${function[fieldEName] }"" maxlength="60"  title="用于精确搜索的第${st.index+1 }个条件的属性值。"  />
				</div>
				<div class="grid_3 lable-right">
					<s:if test="#st.index==0">
						<input type="button"  class="button" value="添加条件" id="searchButton"  />
					</s:if>
					<s:else>
						<input type="button"  class="button" value="删除条件" id="deleteSearchButton" />
					</s:else>
				</div>
			</div>
			<div class='clearLine'></div>
		</s:iterator>
	</fieldset>	
	<fieldset class="fieldset"><legend> 详情查看显示字段配置</legend>
		<div class="grid_5 lable-right">
			<label class="form-lbl">详情url：</label>
		</div>
		<div class="grid_16">
			<input type="text" id="function-detailsUrl" name="function.detailsUrl"  class="form-txt control" value="${function.detailsUrl }" maxlength="600" />
		</div>
		<div class='clearLine'></div>
		
		<s:iterator value="{'A','B','C','D','E'}"  var="char" status="st">
			<s:iterator value="{'detailFieldNameA','detailFieldNameB','detailFieldNameC','detailFieldNameD','detailFieldNameE'}"  var="fieldName" status="stk">
				<s:if test="#st.index==#stk.index"> <s:set id="fieldCName" value="fieldName"></s:set> </s:if>
			</s:iterator>
			<s:iterator value="{'detailFieldA','detailFieldB','detailFieldC','detailFieldD','detailFieldE'}"  var="field" status="stv">
				<s:if test="#st.index==#stv.index"> <s:set id="fieldEName" value="field"></s:set> </s:if>
			</s:iterator>
			<div id="fieldDetails${char}">
				<div class="grid_5 lable-right">
					<label class="form-lbl">中文名${char}：</label>
				</div>
				<div class="grid_6">
					<input type="text" id="function-detailFieldName${char}" name="function.detailFieldName${char}"  class="form-txt dialogtip" value="${function[fieldCName] }" maxlength="20" title="用于详情查看的第${st.index+1 }个字段的中文名称。" />
				</div>
				<div class="grid_4 lable-right">
					<label class="form-lbl">属性${char}：</label>
				</div>
				<div class="grid_6">
					<input type="text" id="function-detailField${char}" name="function.detailField${char}" class="form-txt dialogtip columnSelect" value="${function[fieldEName] }" maxlength="60"  title="用于详情查看的第${st.index+1 }个字段的属性值。" />
				</div>
				<div class="grid_3 lable-right">
					<s:if test="#st.index==0">
						<input type="button"  class="button" value="添加内容" id="function-detailContentButton"   />
					</s:if>
					<s:else>
						<input type="button"  class="button" value="删除内容" id="function-ClearDetailContentButton" />
					</s:else>
				</div>
			</div>
			<div class='clearLine'></div>
		</s:iterator>
	</fieldset>	
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("fieldset").each(function(){
		$(this).find("div[id *= field]").each(function(index){
			if(index>0){
				var fieldName = $(this).find("input:text:eq(0)").val();
				var field = $(this).find("input:text:eq(1)").val();
				if(!fieldName ||  !field) $(this).hide();
			}
		})
	})
	
	$("#gisFunctionFields").delegate("#function-contentButton,#searchButton,#function-detailContentButton","click",function(){//删除列表内容
		var isCanAdd = true;
		$fieldset = $(this).parents("fieldset");
		$fieldset.find("div[id *= field]:visible").each(function(i){
			var fieldName = $(this).find("input:text:eq(0)").val();
			var field = $(this).find("input:text:eq(1)").val();
			if(!fieldName ||  !field){
				$.messageBox({message:"中文名或属性名称不能为空！!"});
				isCanAdd = false;
				return;
			}
		})
		if(isCanAdd){
			var $hideFields = $fieldset.find("div[id *= field]:hidden");
			if($hideFields.length==1) $(this).hide();
			$hideFields.eq(0).show();
		}
	});
	
	$("#gisFunctionFields").delegate("#function-ClearContentButton,#deleteSearchButton,#function-ClearDetailContentButton","click",function(){//动态隐藏列表内容
		$(this).parents("fieldset div[id *= field]").hide();
		$(this).parents("fieldset div[id *= field]").find("input:text").attr("value","");
		if(this.id=="function-ClearContentButton"){
			$("#function-contentButton").show();
		}else if(this.id=="deleteSearchButton"){
			$("#searchButton").show();
		}else if(this.id=="function-ClearDetailContentButton"){
			$("#function-detailContentButton").show();
		}
	});
	
	$("#function-iconUrl").click(function(){
		$("#iconDialog").createDialog({
			width:140,
			height:160,
			title:"图标选择",
			url:"/openLayersMap/system/sysManage/gisIconManageDig.jsp",
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	
	$("#function-boundEventIsValid").click(function(){//设置精确搜索绑定按钮事件是否有效效果
		if($(this).attr("checked")==undefined){
			$("#function-event-TypeHidden").attr("name",$("#function-event-Type").attr("name"));
			$("#function-event-TypeHidden").attr("value",$("#function-event-Type").attr("value"));
			$("#function-event-Type").attr("disabled",true);
		}else{
			$("#function-event-TypeHidden").attr("name","");
			$("#function-event-TypeHidden").attr("value","");
			$("#function-event-Type").attr("disabled",false);
		}
	});
});

function constructFields(){
	var charArray = new Array('A','B','C','D','E');
	var fields = "";
	var field = "";
	if(!isNullOrEmpty(field)) fields += field+",";
	fields += appendCommonFieldsByType("function",charArray);

	fields += appendDetailFieldsByType("function",charArray);
	
	for(var i=0;i<charArray.length;i++){
		field = $("#function-searchField"+charArray[i]).val();
		if(!isNullOrEmpty(field)) fields += field+",";
	}
	
	return fields;
}

function appendCommonFieldsByType(type,charArray){
	var fields="";
	var field = $("#"+type+"-titleValue").val();
	if(!isNullOrEmpty(field)) fields += field+",";
	for(var i=0;i<charArray.length;i++){
		field = $("#"+type+"-field"+charArray[i]).val();
		if(!isNullOrEmpty(field)) fields += field+",";
	}
	return fields;
}

function appendDetailFieldsByType(type,charArray){
	var fields="";
	var field="";
	for(var i=0;i<charArray.length;i++){
		field = $("#"+type+"-detailField"+charArray[i]).val();
		if(!isNullOrEmpty(field)) fields += field+",";
	}
	return fields;
}

function isNullOrEmpty(value){
	if(value==null||value==undefined||value==""){
		return true;
	}
	return false;
}
</script>		