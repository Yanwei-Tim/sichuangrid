<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>	
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/openLayersMap/includes/jsInclude.jsp" />
<div id="tableColumnSelect"  class="container container_24">
	
	<div>	
			<input id="selectA" type="hidden" name="selectA" value="<s:property value='#parameters.selectA'/>" />
			<input id="selectB" type="hidden" name="selectB" value="<s:property value='#parameters.selectB'/>" />
			<s:iterator value="tableColumNameNoteVos" id="TableNameNoteVo" status="st">
					<s:if test="comments==null">	
						<div class="grid_8 lable-right" style="text-align:center;">
							<div id="comment<s:property value='#st.index'/>"><s:property value="comments"/></div>
						</div>
					</s:if>
					<s:else>
　　						<div class="grid_8 lable-right heightAuto" style="text-align:center;">
							<div id="comment<s:property value='#st.index'/>"><s:property value="comments"/></div>
						</div>
					</s:else>
					<div class="grid_9 lable-right"style="text-align:center;" >
						<div id="columnName<s:property value='#st.index'/>"><s:property value="columnName"/></div> 
					</div>
					<div class="grid_6 lable-right">
						<input id="<s:property value='#st.index'/>" onclick="enterSelect(this);" type="button"  class="button" value="确定选择"/>
					</div>
 					<div class='clearLine'></div>
 					<hr width="" style="margin:0 3%;border-bottom:1px solid #ccc;"/>
			</s:iterator> 
	</div>
</div>

<script type="text/javascript">
	function enterSelect(e){
		var comment=$("#comment"+e.id).text();
		var columnName=$("#columnName"+e.id).text();
		var selectA=$("#selectA").val();
		var selectB=$("#selectB").val();
		if(comment==null ||comment=="" ||comment=="undefined"){
		}else{
			$("#"+selectA).attr("value",comment);
		}
   		$("#"+selectB).attr("value",columnName);
		$("#columnSelectDialog").dialog("close");
	}
</script>