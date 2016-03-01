<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>	
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<style>
#layoutTable{width:100%;border-left:1px solid #A6C9E2;border-top:1px solid #A6C9E2;border-collapse:collapse;}
#layoutTable td{height:26px;border-right:1px solid #A6C9E2;border-bottom:1px solid #A6C9E2;border-collapse:collapse;text-align: center;}
.ui-widget-content {
	color: #333;
}
.ui-jqgrid-btable {
	table-layout: fixed;
	margin: 0em;
	outline-style: none;
}
#layoutTable td{}
.focus{background:#ffff90;}
.rowtxt{width:42px;}
</style>

<div class="container">
	<div class="ui-cornRr-all" id="nav">
		<a id="merge" href="javascript:void(0)" ><span><strong class="ui-ico-xz"></strong>合并</span></a>
	</div>


	<div align="center">
		<input type="hidden" value="${layoutRow }" name="layoutRow" id="layoutRow">
		<input type="hidden" value="${layoutColumn }" name="layoutColumn" id="layoutColumn">
		<input type="hidden" value="${organization.id }" name="orgId" id="orgId">
		
		<input type="hidden" value="${builddatas.buildingid }" name="builddatas.buildingid" id="buildingid">
		<input type="hidden" value="${builddatas.centerx }" name="builddatas.centerx" id="centerx">
		<input type="hidden" value="${builddatas.centery }" name="builddatas.centery" id="centery">
	
		<table border="1" class="ui-jqgrid-btable" id="layoutTable">
			<s:iterator begin="1" end="layoutRow" step="1" status='lr'>
			  <tr id="layoutTr<s:property value='#lr.index'/>">
			  	<s:iterator begin="1" end="layoutColumn" step="1" status="ld">
			  		 <td style="width: 125px;" id="layoutTd<s:property value='#ld.index'/>" align="center">
			  			<label>
			  				<input type="checkbox" />
			  				<input type="text" class="rowtxt" id="bindHouseRoom<s:property value='#lr.index'/><s:property value='#ld.index'/>" />
			  				室
			  			</label>
						<select id="bindhouseSt<s:property value='#lr.index'/><s:property value='#ld.index'/>" onchange="getHouseInfo(<s:property value='#lr.index'/>,<s:property value='#ld.index'/>)">
							<option value="0"></option>
							<s:iterator value="housePropertyList" id="houseProperty">
								<option value="<s:property value='#houseProperty.id'/>">
									<s:property value='#houseProperty.houseName'/>
								</option>
							</s:iterator>
						</select>
					</td>
			  	</s:iterator>
			  </tr>
			</s:iterator>
		 
		</table>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#merge").click(function(){
			mergeCell();
		});
		
	});
	
	$("#layoutTable td").click(function(e){
		var $target=$(e.target);
		var that=$(this);
		var row=that.parent();
		var thatCheckbox=that.find("input:checkbox");
		if($target.is("select") || $target.is("input")){
			return false;
		}
		if(row.siblings().find(".focus")[0]){//不同行，不可同时被选中
			return false;
		}
		
		if(thatCheckbox.attr("checked")==undefined){
			that.addClass("focus");
			thatCheckbox.attr("checked","checked");
		}else{
			that.removeClass("focus");
			thatCheckbox.removeAttr("checked");
		}
	})
	$("#layoutTable td input:checkbox").click(function(e){
		e.stopPropagation();
		var that=$(this).closest("td");
		if(that.parent().siblings().find(".focus")[0]){//不同行，不可同时被选中
			return false;
		}
		if($(this).attr("checked")){
			$(this).attr("checked","checked").closest("td").addClass("focus");
		}else{
			$(this).removeAttr("checked").closest("td").removeClass("focus");
		}
	})
	
	function getHouseInfo(lr,ld){
		var houseId = $("#bindhouseSt"+lr+ld).val();
		
		if(houseId == 0){
			$("#bindHouseRoom"+lr+ld).val('');
			return;
		}
		
		$.ajax({
			method:'post',
			async:false,
			url: "${path }/baseinfo/housePropertyManage/getHousePropertyById.action",
			data:{
				"houseProperty.id":houseId
			},
			success:function(result){
				if(result!=null&&result!=""){
					$("#bindHouseRoom"+lr+ld).val(result.room);
				}else{
					$("#bindHouseRoom"+lr+ld).val('');
				}
			}
		});
		
	}
	
	function mergeCell(){
		var selectCell=$("#layoutTable td.focus");
		var callspan=0;
		var firstCell=selectCell.eq(0);
		selectCell.each(function(){
			var thisCol=$(this).attr("colspan")==undefined?1:parseInt($(this).attr("colspan"));
			callspan=callspan+thisCol;
		})
		if(callspan==undefined && callspan=='NaN'){callspan=0}
		if(selectCell.size()<2){
			alert('请先选择合并的单元格！');
			return;
		}
		selectCell.not(":eq(0)").remove();
		firstCell.attr("colspan",callspan);
	}
</script>