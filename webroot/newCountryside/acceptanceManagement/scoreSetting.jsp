<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<style>
	.pTop{
		margin-top: 10px;
		margin-left: 10px;
		vertical-align: middle;
	}
	
	.dTop{
		margin-top: 10px;
		margin-left: 10px;
		vertical-align: middle;
	}
	
	.spanLeft{
		margin-left: 30px;
	}
	
	.inputBtn{
		margin-left: 10px;
	}

	.inputOne{
		width: 100px;
	}
	.inputTwo{
		width: 50px;
	}
	.button {
		padding: 3px;
		cursor: pointer;
	}
	
	.addButton{
		padding: 1px 7px 1px 7px;
		cursor: pointer;
	}
</style>

<input style="display: none;" class="optionByFor" id="option1" type="text" value="characteristicPlanting"/>
<input style="display: none;" class="optionByFor" id="option2" type="text" value="proportionOfIncome"/>
<input style="display: none;" class="optionByFor" id="option3" type="text" value="farmerPerIncome"/>
<input style="display: none;" class="optionByFor" id="option4" type="text" value="capitaHousingArea"/>
<input style="display: none;" class="optionByFor" id="option5" type="text" value="housingSolution"/>
<input style="display: none;" class="optionByFor" id="option6" type="text" value="educationCountFullCoverage"/>
<input style="display: none;" class="optionByFor" id="option7" type="text" value="insuredProportion"/>
<input style="display: none;" class="optionByFor" id="option8" type="text" value="hasBuyInsurance"/>
<input style="display: none;" class="optionByFor" id="option9" type="text" value="activitiesIdentification"/>
<input style="display: none;" class="optionByFor" id="option10" type="text" value="socialWorkCenterArea"/>
<input style="display: none;" class="optionByFor" id="option11" type="text" value="surveySatisfaction"/>
<input style="display: none;" class="optionByFor" id="option12" type="text" value="threeSatisfaction"/>
<input style="display: none;" class="optionByFor" id="option13" type="text" value="isAllStanding"/>
<input style="display: none;" class="optionByFor" id="option14" type="text" value="roadHardeningRate"/>
<input style="display: none;" class="optionByFor" id="option15" type="text" value="drinkingCoverage"/>
<input style="display: none;" class="optionByFor" id="option16" type="text" value="isPowerGrid"/>
<input style="display: none;" class="optionByFor" id="option17" type="text" value="treatmentPollution"/>
<input style="display: none;" class="optionByFor" id="option18" type="text" value="garbageDisposal"/>
<input style="display: none;" class="optionByFor" id="option19" type="text" value="sewageTreatmentCoverage"/>
<input style="display: none;" class="optionByFor" id="option20" type="text" value="courtyardRenovationCoverage"/>


<form id="addScoringRules" action="${path }/baseinfo/scoringRulesManage/saveScoringRules.action" method="post">
<div style="width: 100%;height: 90%;overflow:auto;">
	<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('admin')">
		<input type="button" style="margin-left: 10px;" value="修改字段值" class="button" onclick="showUpdate();"/>
	</s:if>
	<div id="content" >
		
	</div>
	<div class='dTop' align="right">
		得分总值：<input value="0" type="text" class="{digits:true,max:100,messages:{digits:'得分总之必须为整数',max:'得分总值不能超过100'}}" id="maxFraction" name="maxFraction" style="width: 30px;" readonly="readonly"/>
		<input  class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" type="submit" value="保存"/>
	</div>
</div>
</form>
<div id="setValueWindow"></div>
<script>
	var divNum = 1;//记录生成id
	var fristBo = true;//用于判断老字段值
	var isChange = false;//判断是否改变了字段值
	var selectNotValue = "<option value='0'>大于</option>"+
		"<option value='1'>小于</option>"+
		"<option value='2'>等于</option>"+
		"<option value='3'>不等于</option>"+
		"<option value='4'>介于</option>"+
		"<option value='5'>大于等于</option>"+
		"<option value='6'>小于等于</option>";	

	//初始化菜单
	function initMenu(dataVal){
		//如果有值的话只能初始化一次字段值
		if(fristBo == true){
			if(dataVal != ""){
				var listVal = "";
				var forNum = 1;
				if(isChange == false){
					for(var i = 0;i < dataVal.length;i++){
						var dataValue = dataVal[i];
						var pId = dataValue.correspondingValue;
						if(listVal.indexOf(pId) == -1){
							$("#option"+forNum).val(pId);
							forNum++;
						}
						listVal += pId+",";
					}
				}
				fristBo = false;
			}
		}
		var optionHtml = '<p class="pTop" company="%" id="'+$("#option1").val()+'">1.培育有优势特色产业 <input onclick="addHtml(\''+$("#option1").val()+'\',\'%\');" type="button" value="添加" class="inputBtn addButton"/></p>'+
		'<hr/>'+
		'<p class="pTop" company="%" id="'+$("#option2").val()+'">2.农业主导产业收入占农村家庭经营收入的比重<input onclick="addHtml(\''+$("#option2").val()+'\',\'%\');" type="button" value="添加" class="inputBtn addButton"/></p>'+
		'<hr/>'+
		'<p class="pTop" company="万元" id="'+$("#option3").val()+'">3.农民人均可支配收入<input onclick="addHtml(\''+$("#option3").val()+'\',\'万元\');" type="button" value="添加" class="inputBtn addButton"/></p>'+
		'<hr/>'+
		'<p class="pTop" company="平方米" id="'+$("#option4").val()+'">4.农村人均安全住房面积<input onclick="addHtml(\''+$("#option4").val()+'\',\'平方米\');" type="button" value="添加" class="inputBtn addButton"/></p>'+
		'<hr/>'+
		'<p class="pTop" id="'+$("#option5").val()+'">5.无房户、危房户、住房困难户住房问题全部解决<input onclick="addHtml(\''+$("#option5").val()+'\');" type="button" value="添加" class="inputBtn addButton"/></p>'+
		'<hr/>'+
		'<p class="pTop" id="'+$("#option6").val()+'">6.九年义务教育目标人群全覆盖<input onclick="addHtml(\''+$("#option6").val()+'\');" type="button" value="添加" class="inputBtn addButton"/></p>'+
		'<hr/>'+
		'<p class="pTop" id="'+$("#option7").val()+'">7.新农合参保率<input onclick="addHtml(\''+$("#option7").val()+'\');" type="button" value="添加" class="inputBtn addButton"/></p>'+
		'<hr/>'+
		'<p class="pTop" id="'+$("#option8").val()+'">8.新农保应保尽保<input onclick="addHtml(\''+$("#option8").val()+'\');" type="button" value="添加" class="inputBtn addButton"/></p>'+
		'<hr/>'+
		'<p class="pTop" company="次" id="'+$("#option9").val()+'">9.每年组织群众性文体活动<input onclick="addHtml(\''+$("#option9").val()+'\',\'次\');" type="button" value="添加" class="inputBtn addButton"/></p>'+
		'<hr/>'+
		'<p class="pTop" company="平方米" id="'+$("#option10").val()+'">10.村级公共服务活动中心服务设施面积<input onclick="addHtml(\''+$("#option10").val()+'\',\'平方米\');" type="button" value="添加" class="inputBtn addButton"/></p>'+
		'<hr/>'+
		'<p class="pTop" id="'+$("#option11").val()+'">11.基层党组织和农村党员调查满意度<input onclick="addHtml(\''+$("#option11").val()+'\');" type="button" value="添加" class="inputBtn addButton"/></p>'+
		'<hr/>'+
		'<p class="pTop" id="'+$("#option12").val()+'">12.三务公开群众满意度<input onclick="addHtml(\''+$("#option12").val()+'\');" type="button" value="添加" class="inputBtn addButton"/></p>'+
		'<hr/>'+
		'<p class="pTop" id="'+$("#option13").val()+'">13.通过新建、改造、保护等形式推进新村建设全覆盖<input onclick="addHtml(\''+$("#option13").val()+'\');" type="button" value="添加" class="inputBtn addButton"/></p>'+
		'<hr/>'+
		'<p class="pTop" id="'+$("#option14").val()+'">14.通村通组道路硬化率<input onclick="addHtml(\''+$("#option14").val()+'\');" type="button" value="添加" class="inputBtn addButton"/></p>'+
		'<hr/>'+
		'<p class="pTop" id="'+$("#option15").val()+'">15.安全饮水全覆盖<input onclick="addHtml(\''+$("#option15").val()+'\');" type="button" value="添加" class="inputBtn addButton"/></p>'+
		'<hr/>'+
		'<p class="pTop" id="'+$("#option16").val()+'">16.农村电网改造全覆盖<input onclick="addHtml(\''+$("#option16").val()+'\');" type="button" value="添加" class="inputBtn addButton"/></p>'+
		'<hr/>'+
		'<p class="pTop" id="'+$("#option17").val()+'">17.面源污染有效治理<input onclick="addHtml(\''+$("#option17").val()+'\');" type="button" value="添加" class="inputBtn addButton"/></p>'+
		'<hr/>'+
		'<p class="pTop" id="'+$("#option18").val()+'">18.生活垃圾处理全覆盖<input onclick="addHtml(\''+$("#option18").val()+'\');" type="button" value="添加" class="inputBtn addButton"/></p>'+
		'<hr/>'+
		'<p class="pTop" id="'+$("#option19").val()+'">19.生活污水处理覆盖率<input onclick="addHtml(\''+$("#option19").val()+'\');" type="button" value="添加" class="inputBtn addButton"/></p>'+
		'<hr/>'+
		'<p class="pTop" company="%" id="'+$("#option20").val()+'">20.农村院落整治覆盖面<input onclick="addHtml(\''+$("#option20").val()+'\',\'%\');" type="button" value="添加" class="inputBtn addButton"/></p>'
		$("#content").append(optionHtml);
	}
	
		
		
		
	//初始化评分设置
	function initContent(){
		$.ajax({
            type: "GET",
            url: "${path }/baseinfo/scoringRulesManage/getListInfo.action",
            dataType: "json",
            success: function(data){
                 if(data == ""){
                	initMenu(""); 
                	$(".pTop").each(function(){
                		var htmlValue = "<div id='dTop"+divNum+"' class='dTop'><select name='calculationSymbol' num='"+divNum+"'>"+selectNotValue+"</select> <input id='start"+divNum+"' name='scoringRangeStart' type='text' class='inputOne'/><span style='display:none;' id='end"+divNum+"'><input class='inputTwo' type='text' name=''/>&nbsp;&nbsp;至&nbsp;&nbsp;<input class='inputTwo' type='text' name='scoringRangeEnd'/></span>";
                		var pId = $(this).attr("id");
                		var commpany = $(this).attr("company");
                		//通过单位标识修改单位名称
                		if(commpany != undefined || commpany != "undefined"){
                			if(commpany == "%"){
                    			htmlValue += " %";
                    		}
                    		if(commpany == "万元"){
                    			htmlValue += " 万元";
                    		}
                    		if(commpany == "平方米"){
                    			htmlValue += " 平方米";
                    		}
                    		if(commpany == "次"){
                    			htmlValue += " 次";
                    		}
                		}
                		$(this).after(htmlValue+"<input name='correspondingValue' hidden=true type='text' value='"+pId+"'/><span class='spanLeft'>得分：<input class='inputOne' name='fraction' type='text' /></span> <input onClick='htmlDel(\"dTop"+divNum+"\")' type='button' value='删除' class='inputBtn addButton'/></div>");
                		divNum++;
                	}); 
                 }else{
                	initMenu(data);
                	var countNum = 0;
                	var listVal = "";//用于记录是否存在值
                	var forNum = 1;
                	for(var i = 0; i < data.length; i++){
                		var dataValue = data[i];
                		var pId = dataValue.correspondingValue;
                		if(listVal.indexOf(pId) == -1){
                			pId = pId != $("#option"+forNum).val() ? $("#option"+forNum).val() : pId;
                			forNum++;
                		}
                		listVal += pId+",";
                		var scoringRangeEnd =  dataValue.scoringRangeEnd == undefined ? '' : dataValue.scoringRangeEnd;
                		var calculationSymbolVal =  dataValue.calculationSymbol;
                		var fractionVal =  dataValue.fraction;
                		var commpany = $("#"+pId).attr("company");
                		var selectValue = "<option value='0' "+(calculationSymbolVal==0 ? 'selected="selected"' : '')+">大于</option>"+
	                		"<option value='1' "+(calculationSymbolVal==1 ? 'selected="selected"' : '')+">小于</option>"+
	                		"<option value='2' "+(calculationSymbolVal==2 ? 'selected="selected"' : '')+">等于</option>"+
	                		"<option value='3' "+(calculationSymbolVal==3 ? 'selected="selected"' : '')+">不等于</option>"+
	                		"<option value='4' "+(calculationSymbolVal==4 ? 'selected="selected"' : '')+">介于</option>"+
	                		"<option value='5' "+(calculationSymbolVal==5 ? 'selected="selected"' : '')+">大于等于</option>"+
	                		"<option value='6' "+(calculationSymbolVal==6 ? 'selected="selected"' : '')+">小于等于</option>";
	                	var htmlValue = "<div id='dTop"+divNum+"' class='dTop'><input type='text' pId='"+pId+"' value='"+calculationSymbolVal+"' style='display:none' id='dTopD"+divNum+"'/><select  name='calculationSymbol' num='"+divNum+"'>"+selectValue+"</select> <input value='"+dataValue.scoringRangeStart+"' id='start"+divNum+"'  type='text' class='inputOne' "+(calculationSymbolVal==4 ? 'style="display:none;"' : 'name="scoringRangeStart"')+"/><span "+(calculationSymbolVal!=4 ? 'style="display:none;"' : '')+" id='end"+divNum+"'><input class='inputTwo' type='text' id='endInput"+divNum+"' "+(calculationSymbolVal==4 ? 'name="scoringRangeStart"' : '')+" value='"+dataValue.scoringRangeStart+"'/>&nbsp;&nbsp;至&nbsp;&nbsp;<input class='inputTwo' type='text' name='scoringRangeEnd' value='"+scoringRangeEnd+"'/></span>";
                		//通过单位标识修改单位名称
                		if(commpany != undefined || commpany != "undefined"){
                			if(commpany == "%"){
                    			htmlValue += " %";
                    		}
                    		if(commpany == "万元"){
                    			htmlValue += " 万元";
                    		}
                    		if(commpany == "平方米"){
                    			htmlValue += " 平方米";
                    		}
                    		if(commpany == "次"){
                    			htmlValue += " 次";
                    		}
                		}
                		$("#"+pId).after(htmlValue+"<input value='"+pId+"' name='correspondingValue' hidden=true type='text' /><span class='spanLeft'>得分：<input class='inputOne' name='fraction' value='"+fractionVal+"' type='text' /></span> <input onClick='htmlDel(\"dTop"+divNum+"\")' type='button' value='删除' class='inputBtn addButton'/></div>");
                		divNum++;
                		countNum += parseInt(fractionVal);
                	}
                	fristBo = false;
                 }
                 if(isNaN(countNum)){
                	 countNum = 0;
                 }
                 $("#maxFraction").val(countNum);
                 initSelect();
                 initFraction();
            }
        });
	}
	
	//删除事件
	function htmlDel(val){
		$("#"+val).remove();
	}
	
	//添加事件
	function addHtml(val,commpany){
		var htmlValue = "<div id='dTop"+divNum+"' class='dTop'><select name='calculationSymbol' num='"+divNum+"'>"+selectNotValue+"</select> <input id='start"+divNum+"' name='scoringRangeStart' type='text' class='inputOne'/><span style='display:none;' id='end"+divNum+"'><input class='inputTwo' id='endInput"+divNum+"' type='text' name=''/>&nbsp;&nbsp;至&nbsp;&nbsp;<input class='inputTwo' type='text' name='scoringRangeEnd'/></span>";
		if(commpany != undefined || commpany != "undefined"){
			if(commpany == "%"){
    			htmlValue += " %";
    		}
    		if(commpany == "万元"){
    			htmlValue += " 万元";
    		}
    		if(commpany == "平方米"){
    			htmlValue += " 平方米";
    		}
    		if(commpany == "次"){
    			htmlValue += " 次";
    		}
		}
		$("#"+val).after(htmlValue+"<input name='correspondingValue' hidden=true type='text' value='"+val+"'/><span class='spanLeft'>得分：<input class='inputOne' name='fraction' type='text' /></span> <input onClick='htmlDel(\"dTop"+divNum+"\")' type='button' value=' 删除  ' class='inputBtn'/></div>");
		initSelect();
		initFraction();
		divNum++;
	}
	
	//下拉款改变选中事件(写在生成select后，不然该事件无效)
	function initSelect(){
     	$("select").change(function(){
     		var num = $(this).attr("num");
     		var name = $("#start"+num).attr("name");
     		var name1 = $("#endInput"+num).attr("name");
     		if($(this).val() == 4){
     			if(name != ""){
     				$("#start"+num).attr("name","");
     				$("#start"+num).hide();
     				$("#endInput"+num).attr("name",name);
     				$("#end"+num).show();
     			}
     		}else{
     			if(name1 != ""){
     				$("#start"+num).attr("name",name1);
     				$("#start"+num).show();
     				$("#endInput"+num).attr("name","");
         			$("#end"+num).hide();
     			}
     		}
     	});
	}
	
	//初始化得分光标离开事件
	function initFraction(){
		$("input[name='fraction']").blur(function(){
			var countNum = 0;
			$("input[name='fraction']").each(function(){
				if($(this).val() != ""){
					countNum += parseInt($(this).val());
				}
			});
			if(isNaN(countNum)){
				countNum = 0;
			}
			$("#maxFraction").val(countNum);
		});
	}
	
	//弹出修改字段值页面
	function showUpdate(){
		$("#setValueWindow").createDialog({
			width: 500,
			height: 400,
			title:'修改字段值',
			url:'${path}/newCountryside/acceptanceManagement/setValue.jsp',
			buttons: {
		   		"确定" : function(event){
		   			var confirmNum = 1;
		   			$(".setOption").each(function(){
		   				//判断字段值是否改变
		   				if($("#option"+confirmNum).val() != $(this).val()){
		   					isChange = true;
		   					$("#option"+confirmNum).val($(this).val());	
		   				}
		   				confirmNum++;
		   			});
		   			$("#content").html("");
		   			$(this).dialog("close");
		   			initContent();
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	}
	
	$(document).ready(function(){
		initContent();
		$("#addScoringRules").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				$(form).ajaxSubmit({
					async:false,
					success:function(data){
						if(data == true){
							 $.messageBox({message:"评分设置保存成功!"});
						}else{
							 $.messageBox({message:data,level:'error'});
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
	  	      			alert("提交数据时发生错误");
		   		    }
				});
			},
			rules:{
			},
			messages:{
			}
		});
	});
</script>