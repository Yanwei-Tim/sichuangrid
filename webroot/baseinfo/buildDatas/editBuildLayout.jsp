<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>	
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style>
.ui-widget-content {color: #333;}
.focus{background:#ccc;}
.rowtxt{width:42px;}
.hosueMarkConstruc{height:50px;line-height:25px;}
.hosueMarkConstruc strong{padding:0 5px;}
.houseMsg{font:12px/25px "";}
.houseMsg .title{font-weight:bold;}
/*houseManage*/
.houseListManage{width:750px;margin:0 0 0 30px;/*height:400px;border:1px solid #ccc;*/}
.houseListManageEdit{width:0px;width:98%;}
.houseListManage .houseTop{height:42px;}
.houseListManage .houseTop .left,
.houseListManage .houseTop .right{width:28px;height:42px;}
.houseListManage .houseTop .left,
.houseListManage .houseTop .center,
.houseListManage .houseTop .right{float:left;background:url(${resource_path}/resource/system/images/icon_hsoueTopBg.png) no-repeat;}
.houseListManage .houseTop .center{width:665px;height:42px;}
.houseListManageEdit .houseTop .center{width:90%;}
.houseListManage .houseTop .left{background-position:0 -45px;}
.houseListManage .houseTop .center{background-position:0 0;background-repeat:repeat-x;}
.houseListManage .houseTop .right{background-position:0 -93px;}

.houseListManage .houseListCon{width:95.6%;height:320px;border:2px solid #535453;position:relative;overflow:hidden;}
.houseListManage .houseList{width:93.4%;height:320px;overflow:hidden;position:absolute;left:58px;top:0px;}
#layoutTable{width:100%;/*border-left:1px solid #A6C9E2;border-top:1px solid #A6C9E2;*/border-collapse:collapse;}
#layoutTable tr{}
#layoutTable td{width:117px;/**width:0px;*width:auto;height:56px;*/padding:8px 5px 2px;border-right:1px solid #333;border-bottom:1px solid #333;border-collapse:collapse;text-align: center;vertical-align:middle;}
#layoutTable td .houseCon{width:117px;/*height:56px;*/border:2px solid #959797;margin:0 auto;}
#layoutTable td .houseWindow{height:21px;background:#ccc;overflow:hidden;}
#layoutTable td .window{width:117px;height:21px;background:url(${resource_path}/resource/system/images/icon_editWindow.png);}
#layoutTable td .houseWindow2{height:55px;background:#ccc;overflow:hidden;}
#layoutTable td .window2{width:117px;height:55px;background:url(${resource_path}/resource/system/images/icon_displayWindow.png);}
#layoutTable td .houseData{border-top:2px solid #959797;}
#layoutTable td .householdData{height:18px;}
#layoutTable td .householdNameData{height:20px;padding:2px 0 0 0;border-top:2px solid #959797;}
#layoutTable td .householdNameData select{width:90px;}
#layoutTable td .householdData input{width:116px;height:15px;border:1px solid #fff;text-align:center;}
#layoutTable td .householdData input.autoCompete{border:1px solid #ccc;}
#layoutTable td .householdData a{display:block;line-height:15px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;word-break:break-all;word-wrap:break-word;}
.ui-jqgrid-btable {table-layout: fixed;margin: 0em;outline-style: none;}

.houseButtonArea{width:735px;height:35px;position:relative;}
.houseButtonAreaEdit{width:0px;width:97%;}
.houseButtonArea a{display:block;}
.houseButtonArea .scrollBtn a{background:url(${resource_path}/resource/system/images/icon_houseSarrow.png) no-repeat;position:absolute;}
.houseButtonArea .scrollBtn a.up,
.houseButtonArea .scrollBtn a.down{width:16px;height:20px;top:30px;}
.houseButtonArea .functionBtn{display:inline-block;*display:inline;*zoom:1;position:absolute;right:30px;}
.houseButtonArea .functionBtn a.editArea{float:left;width:58px;height:28px;margin:0 5px;padding:0 5px 0 0;font:12px/28px "microsoft yahei";color:#9AA1A6;text-align:right;background:url(${resource_path}/resource/system/images/icon_editBtns.png) no-repeat;}
.houseButtonArea .functionBtn a:hover{color:#434343;}
.houseButtonArea .scrollBtn a.left,
.houseButtonArea .scrollBtn a.right{width:35px;height:25px;top:7px;}
.houseButtonArea .scrollBtn a.up{left:-4px;}
.houseButtonArea .scrollBtn a.down{left:28px;}
.houseButtonArea .scrollBtn a.left{left:60px;}
.houseButtonArea .scrollBtn a.right{left:120px;}

.houseButtonArea .scrollBtn a.upEnable{background-position:0 0;}
.houseButtonArea .scrollBtn a.downEnable{background-position:-54px 0;}
.houseButtonArea .scrollBtn a.leftEnable{background-position:0 -25px;}
.houseButtonArea .scrollBtn a.rightEnable{background-position:-45px -25px;}

.houseButtonArea .scrollBtn a.upHover:hover{background-position:-1px -145px;}
.houseButtonArea .scrollBtn a.downHover:hover{background-position:-54px -145px;}
.houseButtonArea .scrollBtn a.leftHover:hover{background-position:0 -172px;}
.houseButtonArea .scrollBtn a.rightHover:hover{background-position:-45px -171px;}

.houseButtonArea .scrollBtn a.upDisable{background-position:0 -68px;}
.houseButtonArea .scrollBtn a.downDisable{background-position:-54px -68px;}
.houseButtonArea .scrollBtn a.leftDisable{background-position:0 -94px;}
.houseButtonArea .scrollBtn a.rightDisable{background-position:-45px -94px;}

.houseButtonArea .functionBtn a.edit{background-position:0 0;}
.houseButtonArea .functionBtn a.save{background-position:-157px 0;}
.houseButtonArea .functionBtn a.merge{background-position:-79px 0;}
.houseButtonArea .functionBtn a.callback{background-position:-237px 0;}

.houseButtonArea .functionBtn a.edit:hover{background-position:0px -37px;}
.houseButtonArea .functionBtn a.merge:hover{background-position:-79px -37px;}
.houseButtonArea .functionBtn a.save:hover{background-position:-157px -37px;}
.houseButtonArea .functionBtn a.callback:hover{background-position:-237px -37px;}

.houseButtonArea .functionBtn a.edit.hidden,
.houseButtonArea .functionBtn a.callback.hidden,
.houseButtonArea .functionBtn a.merge.hidden,
.houseButtonArea .functionBtn a.save.hidden,
.houseButtonArea .functionBtn a.look.hidden{display:none;}

.bindingCon{float:right;}
/*==*/
.actionHouseMarkFunc{width:172px;margin:-235px 0 0 0;position:absolute;right:7px;top:50%;}
.hosueMarktabChange{padding:0 0 10px 0;border:1px solid #B7CBDB;border-radius:5px;box-shadow:2px 1px 5px #D2D2D3;background:#E4EEF7;}
.hosueMarktabChange .tabChangeList{width:150px;height:25px;margin:10px auto;border:1px solid #B7CBDB;border-radius:5px;}
.tabChangeList li{float:left;width:75px;height:25px;line-height:25px;text-align:center;}
.tabChangeList li.current{background:#83BF00;}
.tabChangeList li a{font-weight:bold;color:#90BBDB;}
.tabChangeList li.current a{color:#385301;}
.tabChangeContent .content{margin:0;background:#E4EEF7;}
.tabChangeContent .content li{height:25px;padding:0 10px;/*border-top:1px solid #F7FAFD;*/border-bottom:1px solid #B7CBDB;font:12px/25px "microsoft yahei";color:#517B9B;position:relative;}
.tabChangeContent .personName,
.tabChangeContent .personCataGory,
.tabChangeContent .personCataNum{float:left;width:50px;height:25px;display:inline-block;overflow:hidden;}
.tabChangeContent .personCataGory{width:25px;height:18px;margin:3px 0 0 5px;background:#ccc;}
.tabChangeContent .personCataNum{width:45px;}
.tabChangeContent .personalMsg{position:absolute;right:10px;top:5px;}
.tabChangeContent .checkAll{height:25px;}
.tabChangeContent .checkAll label{float:right;margin:5px 10px 0 0;}

/*==*/
.selectHouse{margin:10px 0 0;}
.selectHouse #houseName{height:20px;line-height:20px;}
.selectHouse #selectUnit,.selectHouse #houseReset{width:50px;height:26px;}
.displayFloor{width:58px;height:320px;position:absolute;left:0px;top:0px;overflow:hidden;}
.displayFloor li{border-bottom:1px solid #535453;border-right:1px solid #535453;text-align:center;background:#E3E5E4;}
.ui-autocomplete-category {font-weight: bold;padding: .2em .4em;margin: .8em 0 .2em;line-height: 1.5;}
</style>
<div class="container">
	<div class="clearfix">
		<div class="basicData">
			<div class="houseMsg">  
				<div><span class="title">楼宇名称：</span>${builddatas.buildingname }</div>
			</div>
			<div class="houseMsg">
				<span class="title">共有住房：</span> <c:choose><c:when test="${houseNum!=null}">${houseNum}</c:when><c:otherwise>0</c:otherwise></c:choose>  个、
				<span class="title">场所：</span> <c:choose><c:when test="${keyPlaceNum!=null }">${keyPlaceNum}</c:when><c:otherwise>0</c:otherwise></c:choose>  个<!-- 、
				<span class="title">单位：</span>${actualCompanyNum} 个 -->
			</div>
		</div>
	</div>
</div>
<div class="actionHouseMarkFunc">
	<div class="hosueMarktabChange">
		<ul class="tabChangeList">
			<li class="current"><a href="javascript:;">人员类型</a></li>
			<li><a href="javascript:;">房屋用途</a></li>
		</ul>
		<div class="tabChangeContent">
			<c:forEach items="${typeColorList}" var="typeColorMap" varStatus="st">
				<c:choose>
					<c:when test="${st.index==0}">
						<div class="content">
							<ul class="contentList">
								<c:forEach items="${typeColorMap}" var="typeColor">
									<li><label title="${typeColor.key}">
										<span class="personName">${typeColor.key}</span>
										<span class="personCataNum"><strong></strong></span>
										<span class="personCataGory" style="background-color:${typeColor.value}"></span>
										<input type="radio" class="personalMsg" name="person" id="${typeColor.key}"/>
									</label></li>
								</c:forEach>
							</ul>
							<div class="checkAll">
								<label><input type="radio" disabled />&nbsp;全不选</label>
							</div>
						</div>
				</c:when>
				<c:when test="${st.index==1}">
					<div class="content hidden">
						<ul class="contentList">
							<c:forEach items="${typeColorMap}" var="typeColor">
								<li><label title="${typeColor.key}">
									<span class="personName">${typeColor.key}</span>
									<span class="personCataNum"><strong></strong></span>
									<span class="personCataGory" style="background-color:${typeColor.value}"></span>
									<input type="checkbox" class="personalMsg" name="${typeColor.key}"/>
								</label></li>
							</c:forEach>
						</ul>
						<div class="checkAll">
							<label><input type="checkbox"/>&nbsp;全选/全不选</label>
						</div>
					</div>
				</c:when>
			<c:otherwise>
					<input type="hidden" name = "typeNumber" value="${typeColorMap}"></input>
				</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
	</div>
</div>
<s:if test="%{buildLayout.layoutCellList!=null && flag == false}">
<div id="selectHouse" class="selectHouse">
	<input  type="text" id="houseName" title="输入房间号/公司名称"/>
	<input type="button" value="搜索" id="selectUnit"/>
	<input type="button" value="重置" id="houseReset"/>
</div>
</s:if>
<div class="houseButtonArea">
	<div class="scrollBtn">
		<a href="javascript:;" class="up upHover upEnable" id="scrollUp"></a>
		<a href="javascript:;" class="down downHover downDisable" id="scrollDown"></a>
		<a href="javascript:;" class="left leftHover leftEnable" id="scrollLeft"></a>
		<a href="javascript:;" class="right rightHover rightDisable" id="scrollRight"></a>
	</div>
	<div class="functionBtn">
		<a href="javascript:;" id="edit" class="editArea edit" >编辑</a>
		
		<a href="javascript:;" id="addRow" class="editArea merge hidden">插行</a>
        <a href="javascript:;" id="addCol" class="editArea merge hidden">插列</a>
        <a href="javascript:;" id="delRow" class="editArea merge hidden">删行</a>
        <a href="javascript:;" id="delCol" class="editArea merge hidden">删列</a>
		
		<a href="javascript:;" id="merge" class="editArea merge hidden" >合并</a>
		<a href="javascript:;" id="splitCol" class="editArea merge hidden" >右拆</a>
		<a href="javascript:;" id="splitRow" class="editArea merge hidden" >上拆</a>
		<a href="javascript:;" id="save" class="editArea save hidden" >保存</a>
		<a href="javascript:;" id="back" class="editArea callback hidden" >撤销</a>
	</div>
</div>
<div class="houseListManage">
	<div class="houseTop">
		<div class="left"></div>
		<div class="center"></div>
		<div class="right"></div>
	</div>
	<div class="houseListCon">
		<div class="displayFloor"></div>
		<div class="houseList">
			<table border="1" class="ui-jqgrid-btable" id="layoutTable" style="" >
			<c:choose>
				<c:when test="${buildLayout.layoutCellList==null}">
					<c:forEach begin="1" end="${buildLayout.totalRow}" step="1" var="lr">
					  <tr>
					  <c:forEach begin="1" end="${buildLayout.totalCol}" step="1" var="ld">
					  		
					  		<td class="row" data-colspan="1" colspan="1" data-rowspan="1" rowspan="1" style="width:160px;">
					  			<div class="houseCon">
					  				<div class="houseWindow2">
					  					<div class="window2"></div>
					  				</div>
					  				<div class="underline"></div>
					  				<div class="houseData">
						  				<div class="householdData">
							  				<input type="text" class="rowtxt" lrld="${buildLayout.totalRow-lr+1}${ld>9?'':'0'}${ld}"   id="bindHouseRoom${lr}${ld}"/>
							  			</div>
								  			<div class="householdData householdNameData">
							  					<select class="mph" id="bindhouseSt${lr}${ld}"  cr="${lr}${ld}">
							  						<option value="0" id="house_0">请选择房屋</option>
							  						<c:forEach items="${houseInfoList}" var="obj" >
							  							<option title ="${obj.address}" value="${obj.id}" id="house_${obj.id}">${obj.address}</option>
							  						</c:forEach>
												</select>
											</div>
					  				</div>
					  			</div>
				  			</td>
					  		</c:forEach>
					  </tr>
						</c:forEach>
				</c:when>
				<c:otherwise>
						<c:set var="lrow" value="-1" scope="page" />
					<c:forEach items="${buildLayout.layoutCellList}" var="blayout" varStatus="lr">
						<c:if test="${blayout.row!=lrow}">
					  		<tr>
						</c:if>
						  		<td class="row <c:if test="flag ==true ">bigtd</c:if>" data-colspan="${blayout.colSpan}" colspan="${blayout.colSpan}" data-rowspan="${blayout.rowSpan}" rowspan="${blayout.rowSpan}">
						  			<div class="houseCon">
						  				<div class="houseWindow2"><div class="window2"></div></div>
						  				<div class="underline"></div>
						  				<div class="houseData">
						  					<c:choose>
						  						<c:when test="${flag == false}">
								  					<c:set var="td" value="0" scope="page"/>
									  				<c:forEach items="${houseInfoList}" var="houseInfo" varStatus="st">
									  					<c:choose>
									  						<c:when test="${houseInfo.id==blayout.housePropertyId}">
									  							<div class="household">
											  						<div class="householdData">
												  						<a href="javaScript:void(0);" onclick="getHouseInfoForLayout('${houseInfo.encryptId}')" title="${blayout.room}" id="${houseInfo.id}" name="${houseInfo.houseUses.displayName}">
												  							 ${blayout.room}
												  						</a>
											  						</div>
											  					</div>
																<input type="hidden" id="color" value="${houseInfo.backGround}" />
																<c:set var="td" value="1" scope="page"/>
									  						</c:when>
									  					</c:choose>
									  				</c:forEach>
									  				<c:if test="${fn:contains(blayout.room,'暂无数据')&&td==0&&blayout.housePropertyId==0}" >
														<div class="household">
									  						<div class="householdData">
										  						<a href="javaScript:void(0);" onclick="getHouseInfoForLayout(null)">
												  					${blayout.room}
												  				</a>
									  						</div>
									  					</div>
														<input type="hidden" id="color" value="${houseInfo.backGround}" />
										  			</c:if>
									  			</c:when>
									  			<c:otherwise>
													<div class="household">
														<div class="householdData">
															<input type="text" class="rowtxt" id="bindHouseRoom${blayout.row}${blayout.col}"  value="<c:set var="td" value="0" scope="page" /><c:forEach items="${houseInfoList}" var="houseInfo"><c:if test="${houseInfo.id==blayout.housePropertyId}">${blayout.room}<c:set var="td" value="1" scope="page" /></c:if><c:if test="${fn:contains(blayout.room,'暂无数据')&&td==0&&blayout.housePropertyId==0}">${blayout.room}<c:set var="td" value="1" scope="page" /></c:if></c:forEach>" />
														</div>
														<div class="householdData householdNameData">
															<select id="bindhouseSt${blayout.row}${blayout.col}" index="${blayout.row}${blayout.col}">
																<option value="0">请选择房屋</option>
																<c:forEach items="${houseInfoList}" var="houseInfo">
																	<c:if test="${houseInfo.id==blayout.housePropertyId}">
																		<option value="${houseInfo.id}" id="house_${houseInfo.id}" selected>${houseInfo.address}</option>
																	</c:if>
																	<c:if test="${houseInfo.id != blayout.housePropertyId}">
																		<option value="${houseInfo.id}" id="house_${houseInfo.id}">${houseInfo.address}</option>
																	</c:if>
																</c:forEach>
															</select>
														</div>
													</div>
								  				</c:otherwise>					  				
							  				</c:choose>
						  				</div>
						  			</div>
					  			</td>
						<c:set var="lrow" value="${blayout.row}" scope="page"/>
					</c:forEach>	
					</c:otherwise>
					</c:choose>
			</table>
		</div>
	</div>
</div>
<input type="hidden" value="${buildLayout.layoutCellList!=null }" id="isLayouted">
<input type="hidden" value="${buildLayout.id }" id="layoutId">
<input type="hidden" value="${buildLayout.totalRow }" id="totalRow">
<input type="hidden" value="${buildLayout.totalCol }" id="totalCol">
<input type="hidden" value="${buildLayout.org.id }" name="orgId" id="orgId">
<input type="hidden" value="${buildingid }" name="buildingid2" id="buildingid2">
<input type="hidden" value="${builddatas.id }" name="builddatas.id" id="builddatasId">
<input type="hidden" value="${builddatas.buildingid }" name="builddatas.buildingid" id="buildingid">
<input type="hidden" value="${builddatas.centerx }" name="builddatas.centerx" id="centerx">
<input type="hidden" value="${builddatas.centery }" name="builddatas.centery" id="centery">
<input type="hidden" value="${flag }" name="flag" id="flag">
<input type="hidden" value="${mode }" id="mode">
<div id="housePropertyForLayoutDialog"></div>
<div id="personForLayoutDialog"></div>
<script type="text/javascript">
var dialogWidth=750;
var dialogHeight=560;
var tdWidth = $(".houseList").width()/4-6;
initTabChange();
$(document).ready(function(){
	initTableInfo();
	initButtonEnable();
	editStaticFunc();
	initFastSearchPosition();//快速定位
	$("#merge").click(mergeCell);
	$("#save").click(saveOrUpdateLayout);
	$("#splitCol").click(function(){ splitCell("col"); });
	$("#splitRow").click(function(){ splitCell("row"); });
	$("#addRow").click(function(){ addCells("row"); });
	$("#addCol").click(function(){ addCells("col"); });
	$("#delRow").click(function(){
		$.confirm({
			message: "确认删除第一行，数据一经删除将不能恢复！",
		    okFunc:function(){ delCells("row"); },
		})
	});
	$("#delCol").click(function(){
		$.confirm({
			message: "确认删除最后一列，数据一经删除将不能恢复！",
		    okFunc:function(){ delCells("col"); },
		})
	});
	$("#edit").click(function(){ editLayout() });
	$("#back").click(function(){ viewLayout() });

	$(".row :hidden[id=color]").each(function(){
		$(this).parent().css("background",$(this).val());
	});
	
	$("#layoutTable").delegate("td","click",function(e){//只允许同时选中两个单元格s1，s2
		var $row=$(this).parent();
		if($(this).hasClass("focus")){
			$(this).removeClass("focus").removeClass("s1").removeClass("s2");
		}else{
			$(this).addClass("focus");
			if($("#flag").val()=='false'){//查看状态，只允许同时选中一个单元格s1
				$("#layoutTable td.s1").removeClass("focus").removeClass("s1");
				$(this).addClass("s1");
			}else if($("#layoutTable td").hasClass("s2")){
				$("#layoutTable td.s1").removeClass("focus").removeClass("s1");
				$("#layoutTable td.s2").addClass("s1").removeClass("s2");
				$(this).addClass("s2");
			}else if($("#layoutTable td").hasClass("s1")){
				$(this).addClass("s2");
			}else{
				$(this).addClass("s1");
			}
		}
	})
	
	// 遍历所有单元格内的select，给select添加未被选中的option
	$("#layoutTable").delegate("select","change",function(){
			var $select                          = $(this),
				value                              = $select.val(),
				$allSelects                       = $("#layoutTable").find("select"),
				$input                             = $select.parents("td").find(".householdData input"),
				conNum                          = $select.parents("td").index()+1,
				rowNum                          = parseInt($("#totalRow").val(),10)-$select.parents("tr").index();
				newRoom                        = (conNum<10?rowNum+"0"+conNum : rowNum+""+conNum) +"[暂无数据!]";
			if(value != 0){//select选中内容不为空
				$input.val($select.find("option:selected").text());
				$allSelects.find("option[value="+value+"]").hide();
				$select.attr("prevValue",value);
			}else{//select选中内容为空
				$input.val(newRoom);
				var prevValue = $select.attr("prevValue");
				$allSelects.find("option[value="+prevValue+"]").show();
				$select.attr("prevValue",0);
			}
	});
	
	function initTableInfo(){
		formatTDsWidth();
		scrollFloor();//设置楼层信息
		//初始化下拉选择框的选择项
		$("#layoutTable").find("select option:selected").each(function(){
			var $select                          = $(this).parent(),
				$allSelect                        = $("#layoutTable").find("select");
			if($select.val()!=0){
				$select.attr("prevValue",$select.val());
				$allSelect.find("option[value="+$select.val()+"]").hide();
			}
		})
	}
	
	function editStaticFunc(){
		if($("#mode").val()!='layout' || $("#flag").val() !='false'){
			$(".houseListManage").addClass("houseListManageEdit");
			$(".actionHouseMarkFunc").addClass("hidden");
			$(".houseButtonArea").addClass("houseButtonAreaEdit");
		}else if($("#flag").val()=='false'){
			$(".houseListManage").removeClass("houseListManageEdit");
			$(".actionHouseMarkFunc").removeClass("hidden");
			$(".houseButtonArea").removeClass("houseButtonAreaEdit");
		}
	}
	
	function initButtonEnable(){
		var isLayouted = $("#isLayouted").val();
		if($("#flag").val()==null || $("#flag").val()==''){//新增状态
			$("#save,#merge").removeClass("hidden");
			$("#edit").addClass("hidden");
		}else if($("#flag").val()=='false'){//查看状态
			$("#edit").removeClass("hidden").siblings().addClass("hidden");
		}else if($("#flag").val()=='true'){//编辑状态
			$("#edit").addClass("hidden").siblings().removeClass("hidden");
		}
	}
	
	function saveOrUpdateLayout(){
		var buildLayoutStr    = "",
			  layoutArr            = [],
			  totalCol              = $("#totalCol").val(),
			  totalRow            = $("#totalRow").val();        //整个楼宇的布局结果
		for(var i = 0;i <totalRow;i++){
			for(var j = 0;j <totalCol;j++){
				var cell = document.getElementById("layoutTable").rows[i].cells[j];
				if(!cell){
					break;
				}
				var colSpan = $(cell).data("colspan");
				var rowSpan = $(cell).data("rowspan");
				var houseId = $(cell).find(".houseCon .houseData option:selected").val()
				var room = $(cell).find(".houseCon .houseData input").val();
				if(room==''){
					room = ' ';
				}
				layoutArr.push({
					"row":i,
					"col":j,
					"colSpan":colSpan,
					"rowSpan":rowSpan,
					"housePropertyId":houseId,
					"room":room
				});
			}
		} 
		ajaxAddBuildLayout(totalRow,totalCol,JSON.stringify(layoutArr));
	}
	function ajaxAddBuildLayout(totalRow,totalCol,buildLayoutStr){
		var builddatasId = $("#builddatasId").val();
		$.ajax({
			url:"${path}/builddatasManage/addBuildLayout.action",
			type: 'POST',
			data:{
				"buildLayout.id":$("#layoutId").val(),
				"buildLayout.totalCol":totalCol,
				"buildLayout.totalRow":totalRow,
				"buildLayout.org.id":getCurrentOrgId(),
				"buildLayout.layoutInfo":buildLayoutStr,
				"buildLayout.buildId":builddatasId,
				"builddatas.buildingid":$("#buildingid").val(),
				"builddatas.centerx":$("#centerx").val(),
				"builddatas.centery":$("#centery").val(),
				"builddatas.id":builddatasId
			},
			success:function(data){
				if(!data){
		        	$.messageBox({message:"生成布局失败", level: "error"});
		            return;
				}
				$.messageBox({message:"生成楼宇布局成功"});
				//生产布局，刷新列表
				onOrgChanged(getCurrentOrgId());
				viewLayout();
			}
		});
	}
	
	function viewLayout(){
		$("#buildLayoutDialog").load("${path}/builddatasManage/generatedLayout.action?mode=layout&builddatas.id="+$("#builddatasId").val()+"&flag=false");
	}
	function editLayout(){
		$("#buildLayoutDialog").load("${path}/builddatasManage/generatedLayout.action?mode=layout&builddatas.id="+$("#builddatasId").val()+"&flag=true");
	}
});
function formatTDsWidth(){
	//初始化单元格宽度
	$("#layoutTable td").each(function(){
		$(this).css("width",tdWidth*parseInt($(this).attr("colspan")));
	})
}

function getColPosition($cell){//获取单元格，所在的colspan位置
	var $prevTr = $cell.parent("tr"),
		totalCol                   = parseInt($("#totalCol").val(),10),
		totalRow                  = parseInt($("#totalRow").val(),10);
	while($prevTr.children().length != totalCol && $prevTr.prev("tr").length>0){
		$prevTr = $prevTr.prev("tr");
	}
	var cellsPosition = function(){ var array=[]; for(var i=0;i<totalCol;i++){ array[i] = 0; }; return array; }(),
		startRowIndex = $prevTr.index(),
		endRowIndex    = $cell.parent("tr").index();
	for(var i=startRowIndex;i<endRowIndex;i++,$prevTr = $prevTr.next()){
		for(var j=0,k=0;j<totalCol;){
			if(cellsPosition[j]>0){
				cellsPosition[j] -= 1;
				continue;
			}
			var $td = $prevTr.find("td").eq(k);
			if($td.length == 0){
				return false;
			}else{
				k++;
				var colspan = parseInt($td.attr("colspan"),10);
				var rowspan = parseInt($td.attr("rowspan"),10);
				for(var l=0;l<colspan;l++){
					cellsPosition[j] = rowspan-1;
					j++;
				}
			}
		}
	}
	var index = 0,
		$prevAllCell = $cell.prevAll("td");
	for(var i=0,j=0;i<cellsPosition.length;i++){
		if(cellsPosition[i]>0){
			index ++;
		}else{
			if($prevAllCell.eq(j).length==0) break;
			index += parseInt($prevAllCell.eq(j).attr("colspan"),10);
			j++;
		}
	}
	return index;
}
//合并单元格
function mergeCell(){
	var $selectCells  = $("#layoutTable td.focus");
	if($selectCells.length != 2){
		$.messageBox({ message:"请选择相邻的两个单元格进行合并！", level: "error" });
		return;
	}
	var $firstCell                = $selectCells.eq(0),
		$secendCell             = $selectCells.eq(1),
		$secendTr                  = $secendCell.parent(),
		totalCol                   = parseInt($("#totalCol").val(),10),
		totalRow                  = parseInt($("#totalRow").val(),10),
		firstColspan             = parseInt($firstCell.attr("colspan"),10),
		firstRowspan           = parseInt($firstCell.attr("rowspan"),10),
		secendColspan        = parseInt($secendCell.attr("colspan"),10),
		secendRowspan      = parseInt($secendCell.attr("rowspan"),10),
		newColSpan            = firstColspan + secendColspan,
		newRowSpan           = firstRowspan + secendRowspan,
		firstRowPosition      = $firstCell.parent().index(),
		secendRowPosition = $secendCell.parent().index(),
		firstColPosition        = getColPosition($firstCell),
		secendColPosition   = getColPosition($secendCell);
	if(firstRowspan == secendRowspan//高度（rowspan）大小相等
			&& firstRowPosition == secendRowPosition//同行
			&& secendColPosition == (firstColPosition+firstColspan)//相邻的两个单元格
			){//左右合并：在同一行中，合并相邻的两个单元格
		$selectCells.not(":eq(0)").remove();
		$firstCell.attr({"colspan":newColSpan,"data-colspan":newColSpan})
					.css("width",newColSpan*tdWidth).addClass("split");
	}else if(firstColspan == secendColspan//宽度（colspan）大小相等
			&& firstColPosition == secendColPosition//同列
			&& secendRowPosition == (firstRowPosition+firstRowspan)//相邻的两个单元格
			){//上下合并：在同一列中，合并相邻的两个单元格
		$selectCells.not(":eq(0)").remove();
		$firstCell.attr({"rowspan":newRowSpan,"data-rowspan":newRowSpan})
					.addClass("split");
		if($secendTr.find("td").length==0){
			delCells("row");
		}
	}else{
		$.messageBox({ message:"请选择相邻的两个单元格进行合并！", level: "error" });
	}
}
	
//拆分单元格，mode='row' 表示上下拆分，mode='col'表示左右拆分
function splitCell(mode){
	var $selectCells 	 = $("#layoutTable td.focus"),     //所有选中房屋
		$selectCell		 = $selectCells.eq(0),             //待拆分的房屋
		$nextRow          = $selectCell.parent("tr").next("tr"),  //待拆分的房屋下一行
		$newCell           = $selectCell.clone().attr({ "colspan":1, "data-colspan":1 }).css({width:117}),  //拆分出的新房屋
	    colspan 	         = parseInt($selectCell.attr("colspan"),10),   //宽度
	    rowspan            = parseInt($selectCell.attr("rowspan"),10),  //高度
	    colPosition        = getColPosition($selectCell), //处于第几列
	    rowPosition       = $selectCell.parent().index(), //处于第几行
	    splitColspan       = colspan>2?(colspan-1):1, //设新宽度
	    splitRowspan     = rowspan>2?(rowspan-1):1;  //设新高度
	  
	if($selectCells.length != 1){
		$.messageBox({ message:"请选择一个单元格进行拆分！", level: "error" });
		return;
	}
	if(mode == 'row'){
		if(rowspan==1){
			$.messageBox({ message:"当前单元格不能进行拆分！", level: "error" });
			return;
		}
		for(var i=0;i<$nextRow.find("td").length;i++){
			var $curTd             = $nextRow.find("td").eq(i),
				curColspan        = parseInt($curTd.attr("colspan"),10),  //宽度
				curColPosition   = getColPosition($curTd);  //前一个处于第几列
	
			if(colPosition == curColPosition +curColspan ){
				  var cloneCell = changeRoomNum($selectCell);
				  $curTd.after(cloneCell.attr({ "rowspan":splitRowspan, "data-rowspan":splitRowspan })); //与相邻的比较决定前后
				  break;
			}else if(colPosition < curColPosition +curColspan){
				  var cloneCell = changeRoomNum($selectCell);
				  $curTd.before(cloneCell.attr({ "rowspan":splitRowspan, "data-rowspan":splitRowspan }));
				  break;

			}
		}
		if($nextRow.find("td").length == 0){
			$nextRow.append($selectCell.clone().attr({ "rowspan":splitRowspan, "data-rowspan":splitRowspan }));
		}
		$selectCell.attr({ "rowspan":1, "data-rowspan":1 });
	}else if(mode=="col"){
		var selCell = $selectCell.find("input").eq(0).val();
		    befStr=parseInt(selCell.substring(0,selCell.indexOf("[")))+1;
		    afStr=selCell.substring(selCell.indexOf("["));
		    cloneCell = $selectCell.clone();
		    cloneCell.find("input").eq(0).val(befStr+afStr); 
		    $newCell = cloneCell.attr({ "colspan":1, "data-colspan":1 }).css({width:117});
			$selectCell.attr({ "colspan":splitColspan, "data-colspan":splitColspan })
							.width(tdWidth*splitColspan)
							.after($newCell);
		if(colspan==1){
			$selectCell.parent("tr").siblings().filter(function(trIndex){
				var $td                      = null,
					$tds                      = $(this).children("td"),
					curRowPosition     = $(this).index();
				if(curRowPosition>rowPosition && curRowPosition<rowPosition+rowspan){//当待拆分的单元格rowspan较大时，某些行不用执行下列操作
					return false;
				}
				for(var toEditCellPosition=0,i=0;i<$tds.length;i++){
					$td = $tds.eq(i);
					toEditCellPosition += parseInt($td.attr("colspan"),10);
					if(toEditCellPosition>colPosition){
						var _colspan = parseInt($td.attr("colspan"),10);
						$td.attr({ "colspan":_colspan+1, "data-colspan":_colspan+1 })
								.width(tdWidth*(_colspan+1))
						break;
					}
				}
				return false;
			})
			$("#totalCol").val(parseInt($("#totalCol").val(),10) +1);
		}
	}
	$("#layoutTable").css({"marginLeft":"0px"});
}

function changeRoomNum(selectCell){
	  var cloneCell=selectCell.clone();
	  var selCell = selectCell.find("input").eq(0).val();
	  var befStr=selCell.substring(0,selCell.indexOf("[")-2)-1;
	  var afStr=selCell.substring(selCell.indexOf("[")-2);
	  cloneCell.find("input").eq(0).val(befStr+afStr);
	  return cloneCell;
	  
  }

function addCells(mode){
	var $table                = $("#layoutTable"),
		$allTrs                  = $("#layoutTable tr"),
		$firstCell              = $allTrs.eq(0).find("td:eq(0)"),
		$newCell              = $firstCell.clone().attr({ "colspan":1, "data-colspan":1, "rowspan":1, "data-rowspan":1 }).css({width:117}),
		getNewCell          = function(rowIndex,colIndex){
										var $td              = $newCell.clone(),
											selectValue    = $td.find("select").val(),
											room             = (colIndex<10 ?rowIndex+"0"+colIndex : rowIndex+""+colIndex) +"[暂无数据!]" ;
										$td.find("input").val(room);
										$td.find("select").val(0).find("option[value="+selectValue+"]").hide();
										return $td;
									},
		totalCol                = parseInt($("#totalCol").val(),10),
		totalRow               = parseInt($("#totalRow").val(),10);
		if(mode=="row"){//添加行
			var $tr = $("<tr>");
			for(var i=0;i<totalCol;i++){
				$tr.append(getNewCell(totalRow+1,i+1));
			}
			$table.prepend($tr);
			$("#totalRow").val(totalRow +1);
			scrollFloor();
		}else if(mode=="col"){//添加列
			for(var i=0;i<$allTrs.length;i++){
				$allTrs.eq(i).append(getNewCell(totalRow-i,totalCol+1));
			}
			$("#totalCol").val(totalCol +1);
		}
		formatTDsWidth();
}
	
function delCells(mode){
	var $table                = $("#layoutTable"),
		$allTrs                  = $("#layoutTable tr"),
		$firstTr                 = $allTrs.eq(0),
		totalCol                = parseInt($("#totalCol").val(),10);
	if(mode=="row"){//删除行
		for(var i=0;i<$firstTr.find("td").length;i++){
			var $td = $firstTr.find("td").eq(i);
			var rowspan = parseInt($td.attr("rowspan"),10);
			if(rowspan>1){
				$table.find("td.focus").removeClass("focus");
				$td.addClass("focus");
				splitCell("row");
			}
			$td.find("select").val(0).change();
		}
		$firstTr.remove();
		$("#totalRow").val(parseInt($("#totalRow").val(),10) -1);
		scrollFloor();
	}else if(mode=="col"){//删除列
		for(var i=0;i<$allTrs.length;i++){
			var $tr = $allTrs.eq(i);
			var $td = $tr.find("td").last();
			var colspan = parseInt($td.attr("colspan"),10);
			if(colspan>1){
				$table.find("td.focus").removeClass("focus");
				$td.addClass("focus");
				splitCell("col");
			}
			$td.find("select").val(0).change();
			$tr.find("td").last().remove();
		}
		$("#totalCol").val(parseInt($("#totalCol").val(),10) -1);
	}
}
	
function initTabChange(){
	var $tabChange              = $(".hosueMarktabChange"),
		$tabChangeLis             = $tabChange.find(".tabChangeList li"),
		$checkAllbox               = $tabChange.find(".checkAll input:checkbox"),
		$allCheckboxs	           = $tabChange.find("input:checkbox").not($checkAllbox),
		$checkAllRadio            = $tabChange.find(".checkAll input:radio"),
		$allRadio	                   = $tabChange.find("input:radio").not($checkAllRadio),
		$table		                   = $("#layoutTable"),
		$allTd		                   = $("#layoutTable td");

	$tabChange.find(".content li:first").css("border-top-width","0px");
	
	$tabChangeLis.click(function(){
		var index                                = $(this).index(),
			$tabChangeContent           = $tabChange.find(".content");
		$(this).addClass("current").siblings().removeClass("current");
		$tabChangeContent.eq(index).show().siblings().hide();
		$tabChangeContent.find("li:first").css("border-top-width","0px");
		layoutTableClearAll($allCheckboxs);
		layoutTableClearAll($allRadio);
	});
	
	initNoDefineRoom();
	
	function initNoDefineRoom(){
		/* var $layoutTableTd = $("table#layoutTable").find("td");
		for(var i=0;i<$layoutTableTd.length;i++){
			var exist = $layoutTableTd.eq(i).find("div.houseData").length > 0;
			if(!exist){
				$layoutTableTd.eq(i).find(".houseCon").append('<div class="houseData"><div class="householdData"></div></div>');
			}
		} */
	}


	function getTypeNumberByTypeName(typeName){
		var $typeNumber      = $("input[name='typeNumber']"),
			isOk                       = false,
			result                     = null;
		for(var i=0;i<$typeNumber.length;i++){
			var typeNumStr = $typeNumber.eq(i).val();
			if(typeNumStr == null) continue;
			var typeNumArray = typeNumStr.substring(1,typeNumStr.length-1).split(",");
			for(var j=0;j<typeNumArray.length;j++){
				if(typeNumArray[j].split("=")[0].trim()==typeName.trim()){
					result =typeNumArray[j].split("=")[1];
					isOk = true;
					break;
				}
			}
			if(isOk) break;
		}
		return result;
	}
	
	$allRadio.each(function(){
		var personType     = $(this).attr("id"),
			typeNumber      = getTypeNumberByTypeName(personType);
		$(this).parent().children("span").children().html("("+typeNumber+")");
		$(this).click(function(){
			var ids="",
				id="",
				color = $(this).parent().children("span[style]").css("background-color");
			$allTd.find("a[id]").each(function(index){
				ids += (ids.length==0? $(this).attr("id") : "," +$(this).attr("id"));
			})
			$checkAllRadio.removeAttr("disabled").attr("checked",false);
			ajaxFindHouseIdByPersonTypeAndHouseIds(personType,ids,color);
		})
	});

	$allCheckboxs.each(function(index){
		var typeNumber           = getTypeNumberByTypeName($(this).attr("name"));
		$(this).parent().children("span").children().html("("+typeNumber+")");
		$(this).click(function(){ clickCheckboxFunc(this) })
	});
	
	$checkAllbox.click(function(){
		if($(this).is(":checked")){
			$allCheckboxs.not(":checked").each(function(index){
				$(this).attr("checked",true);
				clickCheckboxFunc(this)
			});
		}else{
			layoutTableClearAll($allCheckboxs);
		}
	});

	$checkAllRadio.click(function(){
		$(this).is(":checked") && layoutTableClearAll($allRadio);
	});
	
	function clickCheckboxFunc(checkbox){
		var $checkbox       = $(checkbox),
			color                  = $checkbox.parent().children("span[style]").css("background-color"),
			matchName       = $checkbox.attr("name"),
			$matchTds         = $allTd.find("a[name='"+matchName+"']").parents("td");
		if($checkbox.is(":checked")){
			$matchTds.find(".houseData").css("background",color);
		}else{
			$matchTds.find(".houseData").css("background","#FFF");
		}
		$checkAllbox.attr('checked',$allCheckboxs.length==$allCheckboxs.filter(':checked').length);
	}

	function layoutTableClearAll(element){
		$(element).attr("checked",false);
		$allTd.find(".houseData").css("background","#FFF");
	}
	
	function ajaxFindHouseIdByPersonTypeAndHouseIds(type,ids,color){
		$.ajax({
			url:"${path}/builddatasManage/findHouseIdByPersonTypeAndHouseIds.action",
			data:{
				"personType":type,
				"idList":ids
			},
			success:function(data){
				$allTd.find(".houseData").css("background","#FFF");
				if(data==null) return;
				for(var i=0;i<data.length;i++){
					$allTd.each(function(index){
						var relativeId = $(this).find("a").attr("id");
						if(relativeId==data[i]){
							$(this).find(".houseData").css("background",color);
						}
					})
				}
			}
		});
	}
}
	
//房屋快速定位 
function initFastSearchPosition(){
	$("#selectUnit").on("click",function(){
		var value                   = $("#houseName").attr("value"),
			$targetTds             = $("#layoutTable td a[title='"+value+"']").parents("td"),
			$firstTargetTd        = $targetTds.eq(0),
			$firstTargetLi         = $(".floorList li").eq($firstTargetTd.parent("tr").index());
		if(value==null || $targetTds.length==0){
			return;
		}
		var liTop                     = $firstTargetLi.position().top,
			tdTop                    = $firstTargetTd.position().top,
			tdLeft                    = $firstTargetTd.position().left;
		$("#layoutTable td").removeClass("focus");
		$targetTds.addClass("focus");
		$(".houseList").scrollTop(tdTop).scrollLeft(tdLeft);
		$(".displayFloor").scrollTop(liTop).scrollLeft(0);
	});
	
	$("#houseReset").on("click",function(){
		$(".houseList").scrollTop(0).scrollLeft(0);
		$(".displayFloor").scrollTop(0).scrollLeft(0);
		$("#houseName").attr("value","");
		$("#layoutTable td").removeClass("focus");
	});
	
	$(window).keydown(function(event){
		 if (event.keyCode === 13) {
			$("#selectUnit").click();
		}
	});
}

function scrollFloor(){
	$(".displayFloor").children().css({"margin-top": "0px"});
	$("#layoutTable").css({"margin-top": "0px"});
	$(".displayFloor").empty();
	$(".houseList").scrollWay({ floor:'.displayFloor',upLine:1,leftLine: 4, speed: 500,up: "scrollUp",down: "scrollDown",left:"scrollLeft",right:"scrollRight"});
	scrollWayAfter();
}

function getHouseInfoForLayout(houseId){
	if(houseId==null){
		$.messageBox({level: "error", message:"该单元格还没有添加住房!" });
		return;
	}
	$("#housePropertyForLayoutDialog").createDialog({
		width:dialogWidth,
		height:dialogHeight,
		title:"查看住房信息",
		url:"${path}/baseinfo/houseInfo/actualHouse/viewActualHouseDlg.jsp?mode=view&houseId="+houseId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close"); 
		   }
		}
	});
}


/******************************************待清理代码************************************************/
	getAutoCompelete();
	//数据模糊匹配
	function getAutoCompelete(){
		 $.widget( "custom.catcomplete", $.ui.autocomplete, {
			  _renderMenu: function( ul, items ) {
			    var that = this,
			      currentCategory = "";
			    $.each( items, function( index, item ) {
			    	item.category = item.category == null ? (item.buildType == 3 ? '场所' : (item.buildType == 2 ? '单位' : '房屋')) : item.category;
			      if (item.category != currentCategory ) {
			        ul.append( "<li class='ui-autocomplete-category'>" + item.category + "</li>" );
			        currentCategory = item.category;
			      }
			      that._renderItemData( ul, item );
			    });
			  }
		});
		var orgId = $("#orgId").attr("value");
		var tags="",tagsData="";
		var builddatasId = $("#builddatasId").val();

		//把匹配过的删除掉
		/*function(request,response){
				response($.map(tags, function(item) {
					if($("#layoutTable .autoCompete[name="+item.buildType+"-"+item.id+"-"+item.keyPlaceType+"]")[0]){
						return null;
					}
					if(item.label==null){
						return {
							category: item.category,
							label: item.label,
							value: "",
							id: item.id,
							housePropertyId:item.housePropertyId,
							buildType:item.buildType,
							keyPlaceType:item.keyPlaceType
						}
					}else{
						return {
							category: item.category,
							label: item.label,
							value: item.value,
							id: item.id,
							housePropertyId:item.housePropertyId,
							buildType:item.buildType,
							keyPlaceType:item.keyPlaceType
						}
					}
			}))
		}*/
		$.ajax({
			url:"${path}/builddatasManage/findLayoutAllTags.action",
			data:{
				"orgId":orgId,
				"builddatas.id":builddatasId
			},
			async:false,
			success:function(data){
			  tags = data;
			  $( "#layoutTable .autoCompete").each(function(index){
				  	var _id = $(this).parent().children("input").eq(1).val();
				  	for(var i=0;i<tags.length;i++){
						if(tags[i].id==_id){
							tags.splice(i,1);
							break;
						}
					}
			  });
			}
		});

		$( "#layoutTable .autoCompete" ).each(function(index){
			$(this).catcomplete({
		      	delay: 0,
		      	source:tags,
	      		change:function(event, ui){
					var thisVal=$(this).val()
					if(tags==''){
						$(this).attr("value","");
						$.messageBox({
							message:"当前楼宇无住房，请先绑定"
						})
					}else{
						var isInTags = false;
						for(var i=0;i<tags.length;i++){
							if(tags[i].label==thisVal){
								isInTags = true;
								break;
							}
						}
						var currentObj=$(this).parent().children("input");
						if($(this).attr("temp") != "" && $(this).attr("temp") != null){
							var obj = {category: $(this).attr("category"), label: $(this).attr("temp"), id: currentObj.eq(1).attr("value"), buildType: currentObj.eq(2).attr("value"), keyPlaceType: currentObj.eq(3).attr("value")};
							tags.push(obj);
						}
						if(isInTags){
							$(this).attr("value",thisVal).attr("temp",thisVal).attr("category",ui.item.category);
							currentObj.eq(1).attr("value",ui.item.id);
							currentObj.eq(2).attr("value",ui.item.buildType);
							currentObj.eq(3).attr("value",ui.item.keyPlaceType);
							$.messageBox({
								message:"输入正确"
							});
							tags.splice(i,1);
						} else {
							$(this).attr("value","").attr("temp","").attr("category");
							currentObj.eq(1).attr("value","0");
							currentObj.eq(2).attr("value","0");
							currentObj.eq(3).attr("value","0");
							$.messageBox({
								message:"输入错误"
							})
						}
					}
		      		//$(this).parent().children("input").eq(0).attr("name",ui.item.buildType+"-"+ui.item.id+"-"+ui.item.keyPlaceType);
      	  		}
			 });
		})
		 
		$( "#houseName" ).catcomplete({
		      delay: 0,
		      source: tags,
		      change:function(event, ui){
		      	var allName=$(this).val();
		      	$( "#layoutTable td").each(function(){
			      	var $this=$(this);
		      		var thisName=$this.find(".householdData a").attr("title");
		      		if(thisName==allName){
		      			$this.attr("name",allName);
		      		}
		      	})
	      	  }
		 });
	}
	
function scrollWayAfter(){
	/* $(".houseList").find(".household").each(function(index){
		var $selected = $(this).find("option:selected");
		var select = $(this).find("select");
		var room = $selected.text();
		var index = select.attr("index");
		
		var roomTxt = $("#bindHouseRoom"+index).val();
		
		if(room!=null && room!="" && room.indexOf("请选择")!=0 && roomTxt == null && roomTxt == ''){
			
			$(this).find("input:visible").val(room);
		}
	}) */
}
function getBuildInfoForLayout(id,type,keyPlaceType){
		var url = "";
		var title="";
		if(type == 1){
			title="查看住房信息";
			url = "${path}/baseinfo/actualHouseManage/dispatchOperate.action?mode=view&houseInfo.id="+id;
		}else if(type == 2){
			title="查看单位信息";
			url = "${path}/baseinfo/actualCompanyManage/dispatchOperate.action?mode=view&location.id="+id;
		}else if(type == 3){
			title="查看场所信息";
			//学校
			if(keyPlaceType=="SCHOOL"){
				url = "${path}/baseinfo/schoolManage/dispatchOperate.action?superviceRecordName=巡场情况&supervisorName=治安负责人&mode=view&locationType=SCHOOL&keyType=&location.id="+id;
			}
			//治安重点
			else if(keyPlaceType=="SECURITYKEY"){
				url = "${path}/baseinfo/safetyProductionKeyManage/dispatchOperate.action?superviceRecordName=巡场情况&supervisorName=治安负责人&mode=view&locationType=SECURITYKEY&keyType=securityKey&location.id="+id;
			}
			//安全生产重点
			else if(keyPlaceType=="SAFETYPRODUCTIONKEY"){
				url = "${path}/baseinfo/safetyProductionKeyManage/dispatchOperate.action?supervisorName=治安负责人&superviceRecordName=巡场情况&mode=view&keyType=safetyProductionKey&locationType=SAFETYPRODUCTIONKEY&location.id="+id;
			}
			//消防安全重点
			else if(keyPlaceType=="FIRESAFETYKEY"){
				url = "${path}/baseinfo/fireSafetyEnterpriseManage/dispatchOperate.action?superviceRecordName=巡场情况&supervisorName=消防安全负责人&mode=view&locationType=FIRESAFETYKEY&keyType=fireSafetyKey&location.id="+id;
			}
			//危险化学品单位
			else if(keyPlaceType=="DANGEROUSCHEMICALSUNIT"){
				url = "${path}/baseinfo/dangerousChemicalsUnitManage/dispatchOperate.action?superviceRecordName=巡场情况&supervisorName=治安负责人&mode=view&locationType=DANGEROUSCHEMICALSUNIT&location.id="+id;
			}
			//网吧
			else if(keyPlaceType=="INTERNETBAR"){
				url = "${path}/baseinfo/internetBarManage/dispatchOperate.action?superviceRecordName=巡场情况&supervisorName=治安负责人&mode=view&locationType=INTERNETBAR&location.id="+id;
			}
			//公共场所
			else if(keyPlaceType=="PUBLICPLACE"){
				url = "${path}/baseinfo/publicPlaceManage/dispatchOperate.action?superviceRecordName=巡场情况&supervisorName=治安负责人&mode=view&locationType=PUBLICPLACE&location.id="+id;
			}
			//其他
			else if(keyPlaceType=="OTHER_LOCALE"){
				url = "${path}/baseinfo/otherLocaleManage/dispatchOperate.action?superviceRecordName=巡场情况&supervisorName=治安负责人&mode=view&locationType=OTHERLOCALE&keyType=&location.id="+id;
			}
		}
		$("#housePropertyForLayoutDialog").createDialog({
			width:dialogWidth,
			height:dialogHeight,
			title:title,
			url:url,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close"); 
			   }
			}
		});
	}
	
	function getPersonForLayout(idCard){
		var orgId = $("#orgId").attr("value");
		$.ajax({
			method:'post',
			async:false,
			url: "${path }/baseinfo/permanentResidentManage/getPermanentResidentByIdCardNo.action",
			data:{
				"permanentResident.idCardNo":idCard,
				"ownerOrg.id":orgId
			},
			success:function(result){
				if(result == null || result.id == null){
					$.messageBox({message:"该人员信息不存在!"});
					return;
				}
				
				getPersonInfoForLayout(result.id);
			}
		});
	}
	
	function getPersonInfoForLayout(id){
		$("#personForLayoutDialog").createDialog({
			width:dialogWidth,
			height:dialogHeight,
			title:"查看人员信息",
			url:"${path}/baseinfo/permanentResidentManage/dispatchOperate.action?mode=view&permanentResident.id="+id,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close"); 
			   }
			}
		});
	}
</script>