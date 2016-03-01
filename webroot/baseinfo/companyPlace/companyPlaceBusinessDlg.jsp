<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp" %>
<link href="${resource_path }/resource/system/css/selectAll.css" rel="stylesheet" type="text/css">
<style>

/* CSS Document */
@charset "utf-8";
/* CSS Document */
body,h1,h2,h3,h4,h5,h6,p,blockquote,dl,dt,dd,ul,ol,li,pre,fieldset,lengend,button,input,textarea,th,td {margin: 0;padding: 0;}
body,button,input,select,textarea {font: 12px/1 Tahoma,Helvetica,Arial,"\5b8b\4f53",sans-serif;line-height:20px;}
h1 { font-size: 18px;}
h2 { font-size: 16px;}
h3 { font-size: 14px;}
h4,h5,h6 { font-size: 100%;}
ul,ol,li{ list-style: none;}
a {color:#000;text-decoration:none;}
a:hover { text-decoration:none;}
img { border:0 none; vertical-align:middle;}
table {border-collapse: collapse;border-spacing: 0;}
td,th { border: 1px solid #ccc;}
input{vertical-align:middle;outline:none;}
html { overflow-y: scroll;}
em{font-style: normal;}
body,a{font-family: "Microsoft YaHei"}
.cf:before, .cf:after {content:"";display:table;}/* 别在content里添加内容 */
.cf:after {clear:both;}/* 写上就能自动生成了 */
.cf {zoom:1;}

</style>

<script type="text/javascript">
(function($){
	$.fn.extend({
		selectArea:function(opction){
			var thatId = $(this).attr("id"),
				that = $("#"+thatId)
			var o = {
				url:"",
				datas:[],
				height:402,
				width:700,
				method:"post",
				titleData:["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"],
				levelMenu:false,
				dataMed:{},
				menuUrl:"",
				clickCallback:function(e,data){},
				menuCallback:function(e,data){}
			}
			var chidText,
				parText;

			$.extend(o,opction)
			var htmls = $("<div class='selectArea "+ thatId +"Lay' style='width:"+ o.width +"px;height:"+ o.height +"px;margin:-"+ o.height/2 +"px 0 0 -"+ o.width/2 +"px'>"+
							"<ul class='title'></ul>"+
							"<ul class='main'></ul>"+
							"<a href='javascript:;' class='dialogX'></a>"+
						"</div>");
			$.each(o.titleData,function(i,d){
				htmls.find(".title").append("<li id='pinyin"+i+"'><a href='javascript:;'>"+d+"</a></li>");
			})
			that.append(htmls)
			 var ajaxQq = function(id,$that,url){
				var newUrl = "";
				if(!!url){
					newUrl = url
				}else{
					newUrl = o.url
				}
				o.dataMed.pinyin = id;
				if(o.url != ""){
					$.ajax({
						url:newUrl,
						type:o.method,
						data:o.dataMed,
						success:function(data) {    
					        $.each(data,function(i,d){
					        	$that.append("<li><a href='javascript:;' ids="+ d.id +">"+d.displayName+"</a></li>");
							})
					    },    
					    error : function() {    
					          alert("异常！");    
					    }    
					})
				}else{
					 $.each(o.datas,function(i,d){
						$that.append("<li><a href='javascript:;' ids="+ d.id +">"+d.displayName+"</a></li>");
					})
				}
			} 

			if(o.levelMenu == true){
				
				htmls.append("<div class='levelMenuMain'><div class='levelMenuTitle'><span>二级类目</span></div><ul class='levelMenu'></ul></div>");
				htmls.prepend("<div class='xzts'>当前所选类目：<span></span></div>")
				htmls.addClass("levelMenuLay");

				$("body").on("click",".levelMenu a",function(e){
					var that = $(e.target);
					chidText = that.html();
					if(!that.hasClass("on")){
						htmls.find(".levelMenu a").removeClass("on");
						that.addClass("on");
					}
					o.menuCallback(e,{
						id:that.attr("ids"),
						text:that.html()
					});
					htmls.find(".xzts span").html(""+ parText +" > "+ chidText +"");
				})
			}

			
			$("body").on("click",".main a",function(e){
				var that = $(e.target);
				parText = that.html();
				if(!that.hasClass("on")){
					var thatA = that.attr("ids");
					htmls.find(".main a").removeClass("on");
					that.addClass("on");
					if(o.levelMenu == true){
						htmls.find(".levelMenu").html("");
						ajaxQq(thatA,htmls.find(".levelMenu"),o.menuUrl);
					}
				}
				o.clickCallback(e,{
					id:that.attr("ids"),
					text:that.html()
				});
			})


			that.append(htmls)	
			

			htmls.find(".dialogX").click(function(){
				$("body").off("click",".main a")
				$("body").off("click",".levelMenu a")
				htmls.remove()
			})

			that.find(".title li").bind("click",function(e){
				if(!that.hasClass("on")){
					that.find(".main").html("")
					that.find(".levelMenu").html("");
					var thatA = $(this).find("a").html();
					ajaxQq(thatA,htmls.find(".main"))

					that.find("a").removeClass("on")
					$(this).find("a").addClass("on");
				}
			}).eq(0).click()
			
		}
	})
})(jQuery)
</script>

<div class="container container_24">
<form id="maintainForm" method="post" action="" >
<input type="hidden" id="propertyDomainId" name="propertyDomainId"/>
<input type="hidden" id="propertyDomainId_tradeType" name="propertyDomainId"/>
<input type="hidden" name="mode" value="${param.mode}"/>
<input type="hidden" id="modulKey" name="modulKey" value="${param.modulKey}"/>
<input type="hidden" id="type" name="type" value="${param.type}"/>
<input type="hidden" name="companyPlaceId" id="companyPlaceId_id" value="<c:choose><c:when test="${mode=='edit'}">${companyPlaceId}</c:when><c:otherwise>${param.id}</c:otherwise></c:choose>"/>
<input type="hidden" name="targetId" id="targetId" value="<c:choose><c:when test="${mode=='edit'}">${companyPlaceId}</c:when><c:otherwise>${param.id}</c:otherwise></c:choose>"/>
<pop:JugePermissionTag ename="newWhetherProductionKey">
<div id="newWhetherProductionKey" style="display:none;border-bottom:1px solid #999;padding-bottom:10px;margin-bottom:10px;" class="cf">
	<div class="grid_24" style="align:center;">
		<input type="hidden" id="proKeyCompanyPlaceBusinessId" name="companyPlaceBusinessVO.proKeyCompanyPlaceBusiness.id" value="${companyPlaceBusinessVO.proKeyCompanyPlaceBusiness.id}"/>
	    <input type="hidden" id="proKeyIsKeyTypeValue" value="${companyPlaceBusinessVO.proKeyCompanyPlaceBusiness.isKeyType}"/>
	    <input type="hidden" id="delProKeyID" name="companyPlaceBusinessVO.delProKeyID"/>
		<input type="checkbox" id="proKeyIsKeyType" name="companyPlaceBusinessVO.proKeyCompanyPlaceBusiness.isKeyType" value="<s:property value="@com.tianque.baseInfo.companyPlace.constant.IsKeyType@PRODUCTION_KEY_TYPE"/>" class="typeInfoContext" fateson="KEYPOPULATION" contextdiv="keyPopulationContext"/>
		<label class="form-lb1">是否安全生产重点</label>
	</div>
	
 <div id="productionKey"  style="display:none;">
    
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lb1">隐患情况：</label>
	</div>
	
	<div class="grid_20 heightAuto">
		<textarea id="proKeyHiddangerInfo" name="companyPlaceBusinessVO.proKeyCompanyPlaceBusiness.hiddangerInfo"  maxlength="100" class="form-txt {isLawful:true,minlength:2,maxlength:100, messages:{isLawful:'您输入了非法脚本，请重新输入！',minlength:$.format('隐患情况至少需要输入{0}个字符'),maxlength:$.format('隐患情况最多需要输入{0}个字符')}}" style="height:70px">${companyPlaceBusinessVO.proKeyCompanyPlaceBusiness.hiddangerInfo}</textarea>
    </div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lb1">隐患整改情况：</label>
	</div>
	
	<div class="grid_20 heightAuto">
		<textarea id="proKeyCorrectionInfo" name="companyPlaceBusinessVO.proKeyCompanyPlaceBusiness.correctionInfo" maxlength="100" class="form-txt {isLawful:true,minlength:2,maxlength:100, messages:{isLawful:'您输入了非法脚本，请重新输入！',minlength:$.format('隐患整改情况至少需要输入{0}个字符'),maxlength:$.format('隐患整改情况最多需要输入{0}个字符')}}" style="height:70px">${companyPlaceBusinessVO.proKeyCompanyPlaceBusiness.correctionInfo}</textarea>
    </div>
	<div class='clearLine'>&nbsp;</div>
	<div class="filePanel cf">
		<div class="grid_4 lable-right">
			<label class="form-lbl">附件：
				<select id="proKeyAttachFiles" name="companyPlaceBusinessVO.proKeyAttachFiles" multiple="multiple" style="width:200px;display:none"></select>
			</label>
		</div>
		<div class="grid_20 heightAuto" style='margin-top:10px;'>
			<div id="custom-queue_proKey" style="width:468px;*width:503px;height:65px;overflow:auto;overflow-x: hidden;border:1px solid #7F9DB9;"></div>
			<input id="custom_file_upload_proKey"  name="uploadFile" type="file" style="padding-left:20px;width:70px;" readonly="readonly" disabled="disabled"/>
		</div>
	</div>
 </div>
</div>
</pop:JugePermissionTag>
<pop:JugePermissionTag ename="newWhetherFireSafetyKey">
<div id="newWhetherFireSafetyKey" style="display:none;border-bottom:1px solid #999;padding-bottom:10px;margin-bottom:10px;" class="cf">
	<div class="grid_24" style="align:center;">
		<input type="hidden" id="fireSafetyKeyCompanyPlaceBusinessId" name="companyPlaceBusinessVO.fireSafetyKeyCompanyPlaceBusiness.id" value="${companyPlaceBusinessVO.fireSafetyKeyCompanyPlaceBusiness.id}"/>
		<input type="hidden" id="fireSafetyKeyIsKeyTypeValue" value="${companyPlaceBusinessVO.fireSafetyKeyCompanyPlaceBusiness.isKeyType}"/>
		<input type="checkbox" id="fireSafetyKeyIsKeyType" name="companyPlaceBusinessVO.fireSafetyKeyCompanyPlaceBusiness.isKeyType" value="<s:property value="@com.tianque.baseInfo.companyPlace.constant.IsKeyType@FIRESAFETY_KEY_TYPE"/>" class="typeInfoContext" fateson="KEYPOPULATION" contextdiv="keyPopulationContext"/>
		<input type="hidden" id="delfirKeyID" name="companyPlaceBusinessVO.delfirKeyID"/>
		<label class="form-lb1">是否消防安全重点</label>
	</div>
	
  <div id="fireSafetyKey"  style="display:none;">
  
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lb1">隐患情况：</label>
	</div>
	
	<div class="grid_20 heightAuto">
		<textarea id="fireSafetyKeyHiddangerInfo" name="companyPlaceBusinessVO.fireSafetyKeyCompanyPlaceBusiness.hiddangerInfo" maxlength="100" class="form-txt {isLawful:true,minlength:2,maxlength:100, messages:{isLawful:'您输入了非法脚本，请重新输入！',minlength:$.format('隐患情况至少需要输入{0}个字符'),maxlength:$.format('隐患情况最多需要输入{0}个字符')}}" style="height:70px">${companyPlaceBusinessVO.fireSafetyKeyCompanyPlaceBusiness.hiddangerInfo}</textarea>
    </div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lb1">隐患整改情况：</label>
	</div>
	<div class="grid_20 heightAuto">
		<textarea id="fireSafetyKeyCorrectionInfo" name="companyPlaceBusinessVO.fireSafetyKeyCompanyPlaceBusiness.correctionInfo" maxlength="100" class="form-txt {isLawful:true,minlength:2,maxlength:100, messages:{isLawful:'您输入了非法脚本，请重新输入！',minlength:$.format('隐患整改情况至少需要输入{0}个字符'),maxlength:$.format('隐患整改情况最多需要输入{0}个字符')}}" style="height:70px">${companyPlaceBusinessVO.fireSafetyKeyCompanyPlaceBusiness.correctionInfo}</textarea>
    </div>
    
	<div class='clearLine'>&nbsp;</div>
	<select id="fireSafetyKeyAttachFiles" name="companyPlaceBusinessVO.fireSafetyKeyAttachFiles" multiple="multiple" style="width:200px;display:none"></select>
	<div class="filePanel cf">
		<div class="grid_4 lable-right">
			<label class="form-lbl">附件：</label>
		</div>
		<div class="grid_20 heightAuto" style='margin-top:10px;'>
			<div id="custom-queue_fireSafetyKey" style="width:468px;*width:503px;height:65px;overflow:auto;overflow-x: hidden;border:1px solid #7F9DB9;"></div>
			<input id="custom_file_upload_fireSafetyKey"  name="uploadFile" type="file" style="padding-left:20px;width:70px;" readonly="readonly" disabled="disabled"/>
		</div>
	</div>
</div>
</div>
</pop:JugePermissionTag>

<pop:JugePermissionTag ename="newWhetherSecurityKey">
<div id="newWhetherSecurityKey" style="display:none;border-bottom:1px solid #999;padding-bottom:10px;margin-bottom:10px;" class="cf">
	<div class="grid_20">
		<input type="hidden" id="securityKeyCompanyPlaceBusinessId" name="companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.id" value="${companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.id}"/>
		<input type="hidden" id="securityKeyIsKeyTypeValue" value="${companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.isKeyType}"/>
		<input type="checkbox" id="securityKeyIsKeyType" name="companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.isKeyType" value="<s:property value="@com.tianque.baseInfo.companyPlace.constant.IsKeyType@SECURITY_KEY_TYPE"/>" id="isKeyType" class="typeInfoContext" fateson="KEYPOPULATION" contextdiv="keyPopulationContext"/>
		<input type="hidden" id="delsecKeyID" name="companyPlaceBusinessVO.delsecKeyID"/>
		<label class="form-lb1">是否治安重点</label>
	</div>
	
  <div id="securityKey"  style="display:none;">
	
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">挂牌整治：</label>
	</div>
	<div class="grid_10">
			<s:iterator value="@com.tianque.domain.property.RenovateType@getInternalProperties()" var="renovateTypes">
				<input  type="radio" name="companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.listedCorrection" value="${identify}" <c:if test="${companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.listedCorrection == identify }">checked="checked"</c:if>>${displayName}
			</s:iterator>
	</div>
	<div class="grid_3" style="margin-left: 30px;">
		<label>挂牌等级：</label>
	</div>
	<div class="grid_6 heightAuto"> 
		<select name="companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.listedLeve" id="listedLeve">
			<option value="<s:property value="@com.tianque.baseInfo.companyPlace.constant.ListingType@LISTING_RED_VALUE"/>" <s:if test="companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.listedLeve == @com.tianque.baseInfo.companyPlace.constant.ListingType@LISTING_RED_VALUE">selected="selected"</s:if>>
				<s:property value="@com.tianque.baseInfo.companyPlace.constant.ListingType@LISTING_RED_NAME"/></option>
			<option value="<s:property value="@com.tianque.baseInfo.companyPlace.constant.ListingType@LISTING_YELLOW_VALUE"/>" <s:if test="companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.listedLeve == @com.tianque.baseInfo.companyPlace.constant.ListingType@LISTING_YELLOW_VALUE">selected="selected"</s:if>>
				<s:property value="@com.tianque.baseInfo.companyPlace.constant.ListingType@LISTING_YELLOW_NAME"/></option>
		</select>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">隐患情况：</label>
	</div>
	<div class="grid_20"> 
		<textarea id="securityKeyHiddangerInfo" name="companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.hiddangerInfo" maxlength="100" class="form-txt {isLawful:true,minlength:2,maxlength:100, messages:{isLawful:'您输入了非法脚本，请重新输入！',minlength:$.format('隐患情况至少需要输入{0}个字符'),maxlength:$.format('隐患情况最多需要输入{0}个字符')}}" style="height:70px">${companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.hiddangerInfo}</textarea>
	</div>

	<div class='clearLine'>&nbsp;</div>
	<div style="margin-top: 60px;">
		<div class="grid_4 lable-right">
			<label class="form-lbl">整改时限：</label>
		</div>
		<div class="grid_12"> 
			<input type="text" name="companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.correctionBeiginDate" id="correctionBeiginDate" maxlength="50" value="<fmt:formatDate value="${companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.correctionBeiginDate}" type="date" pattern="yyyy-MM-dd" />" class="form-txt" style="width:130px;" readonly/>
			至
			<input type="text" name="companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.correctionEndDate" id="correctionEndDate" maxlength="50" value="<fmt:formatDate value="${companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.correctionEndDate}" type="date" pattern="yyyy-MM-dd" />" style="width:130px;" class="form-txt" readonly/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">效果评估：</label>
		</div>
		<div class="grid_3"> 
			<select name="companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.effectassessment.id" id="effectassessment" class="form-txt" >
			    <option value="" selected="selected" >请选择</option>
	 			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@EFFECT_EVALUATION_TYPE" defaultValue="${companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.effectassessment.id}" notNull="true" />
			</select>
		</div>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">隐患整改情况：</label>
	</div>
	<div class="grid_20 heightAuto"> 
		<textarea id="securityKeyCorrectionInfo" name="companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.correctionInfo" maxlength="100" class="form-txt {isLawful:true,minlength:2,maxlength:100, messages:{isLawful:'您输入了非法脚本，请重新输入！',minlength:$.format('隐患整改情况至少需要输入{0}个字符'),maxlength:$.format('隐患整改情况最多需要输入{0}个字符')}}" style="height:70px">${companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.correctionInfo}</textarea>
	</div>
	
	<div class='clearLine'>&nbsp;</div>
	<select id="securityKeyAttachFiles" name="companyPlaceBusinessVO.securityKeyAttachFiles" multiple="multiple" style="width:200px;display:none"></select>
	<div class="filePanel cf">
		<div class="grid_4 lable-right">
			<label class="form-lbl">附件：</label>
		</div>
		<div class="grid_20 heightAuto" style='margin-top:10px;'>
			<div id="custom-queue_securityKey" style="width:468px;*width:503px;height:65px;overflow:auto;overflow-x: hidden;border:1px solid #7F9DB9;"></div>
			<input id="custom_file_upload_securityKey"  name="uploadFile" type="file" style="padding-left:20px;width:70px;" readonly="readonly" disabled="disabled"/>
		</div>
	</div>
 </div>
</div>
</pop:JugePermissionTag>

<pop:JugePermissionTag ename="newWhetherPollutionSource">
<div id="newWhetherPollutionSource" style="display:none;border-bottom:1px solid #999;padding-bottom:10px;margin-bottom:10px;" class="cf">
	<div class="grid_20">
		<input type="hidden" id="pollSourceCompanyPlaceBusinessId" name="companyPlaceBusinessVO.pollSourceCompanyPlaceBusiness.id" value="${companyPlaceBusinessVO.pollSourceCompanyPlaceBusiness.id}"/>
	    <input type="hidden" id="pollSourceIsKeyTypeValue" value="${companyPlaceBusinessVO.pollSourceCompanyPlaceBusiness.isKeyType}"/>
		<input type="checkbox" id="pollSourceIsKeyType" name="companyPlaceBusinessVO.pollSourceCompanyPlaceBusiness.isKeyType" value="<s:property value="@com.tianque.baseInfo.companyPlace.constant.IsKeyType@POLLUTION_SOURCE_TYPE"/>" id="isKeyType" class="typeInfoContext" fateson="KEYPOPULATION" contextdiv="keyPopulationContext"/>
		<input type="hidden" id="delpollKeyID" name="companyPlaceBusinessVO.delpollKeyID"/>
		<label class="form-lb1">是否污染源</label>
	</div>
 <div id="pollutionSource"  style="display:none;">
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">污染类型：</label>
	</div>
	<div class="grid_8"> 
		<select name="companyPlaceBusinessVO.pollSourceCompanyPlaceBusiness.pollutionType.id" id="pollutionType" class="form-txt" >
		    <option value="" selected="selected" >请选择</option>
 			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POLLUTION_TYPE" defaultValue="${companyPlaceBusinessVO.pollSourceCompanyPlaceBusiness.pollutionType.id}" notNull="true" />
		</select>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">污染情况：</label>
	</div>
	<div class="grid_20 heightAuto"> 
		<textarea id="pollSourcePollutionInfo" name="companyPlaceBusinessVO.pollSourceCompanyPlaceBusiness.pollutionInfo" maxlength="100" class="form-txt {isLawful:true,minlength:2,maxlength:100, messages:{isLawful:'您输入了非法脚本，请重新输入！',minlength:$.format('污染情况至少需要输入{0}个字符'),maxlength:$.format('污染情况最多需要输入{0}个字符')}}" style="height:70px">${companyPlaceBusinessVO.pollSourceCompanyPlaceBusiness.pollutionInfo}</textarea>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<select id="pollSourceAttachFiles" name="companyPlaceBusinessVO.pollSourceAttachFiles" multiple="multiple" style="width:200px;display:none"></select>
	<div class="filePanel cf">
		<div class="grid_4 lable-right">
			<label class="form-lbl">附件：</label>
		</div>
		<div class="grid_20 heightAuto" style='margin-top:10px;'>
			<div id="custom-queue_pollSource" style="width:468px;*width:503px;height:65px;overflow:auto;overflow-x: hidden;border:1px solid #7F9DB9;"></div>
			<input id="custom_file_upload_pollSource"  name="uploadFile" type="file" style="padding-left:20px;width:70px;" readonly="readonly" disabled="disabled"/>
		</div>
	</div>
 </div>
</div>
 
 <div id="pollutionCase"  style="display:none;">
<pop:JugePermissionTag ename="contaminationInfoManagement">

	<div id="contaminationInfoDiv" style="display:none;border-bottom:1px solid #999;padding-bottom:10px;margin-bottom:10px;" class="cf">
		<div class="grid_6 lable-right">污染物情况：</div>
		<input type="hidden" name="companyPlaceBusinessVO.contaminationInfo.id" id="contaminationInfoId" value='${companyPlaceBusinessVO.contaminationInfo.id }'/>
		<input type="hidden" name="companyPlaceBusinessVO.contaminationInfo.businessId" id="contaminationInfoBusinessId" value='${companyPlaceBusinessVO.contaminationInfo.businessId }'/>
		<div class="grid_3">
			<input type="hidden" id="isWasteWaterValue" value="${companyPlaceBusinessVO.contaminationInfo.isWasteWater}"/>
			<input type="checkbox" name="companyPlaceBusinessVO.contaminationInfo.isWasteWater" id="isWasteWater" value="0"/>
			废水
		</div>
		<div class="grid_3">
			<input type="hidden" id="isWasteGasValue" value="${companyPlaceBusinessVO.contaminationInfo.isWasteGas}"/>
			<input type="checkbox" name="companyPlaceBusinessVO.contaminationInfo.isWasteGas" id="isWasteGas" value="0"/>
			废气
		</div>
		<div class="grid_3">
			<input type="hidden" id="isWasteSolidValue" value="${companyPlaceBusinessVO.contaminationInfo.isWasteSolid}"/>
			<input type="checkbox" name="companyPlaceBusinessVO.contaminationInfo.isWasteSolid" id="isWasteSolid" value="0"/>
			固废
		</div>
		<div class="grid_3">
			<input type="hidden" id="isRadioactionValue" value="${companyPlaceBusinessVO.contaminationInfo.isRadioaction}"/>
			<input type="checkbox" name="companyPlaceBusinessVO.contaminationInfo.isRadioaction" id="isRadioaction" value="0"/>
			放射性
		</div>
		<div class="grid_3">
			<input type="hidden" id="isNoiseValue" value="${companyPlaceBusinessVO.contaminationInfo.isNoise}"/>
			<input type="checkbox" name="companyPlaceBusinessVO.contaminationInfo.isNoise" id="isNoise" value="0"/>
			噪音
		</div>
		<div class="grid_3">
			<input type="hidden" id="isHeavyMetalValue" value="${companyPlaceBusinessVO.contaminationInfo.isHeavyMetal}"/>
			<input type="checkbox" name="companyPlaceBusinessVO.contaminationInfo.isHeavyMetal" id="isHeavyMetal" value="0"/>
			重金属
		</div>
		<!--  -->
		<div class='clearLine'>&nbsp;</div>
		<div id="wasteWaterDiv" style="display:none;">
			<div class="grid_6 lable-right">
				<label class="form-lb1">废水污染物名称：</label>
			</div>
			<div class="grid_6">
				<div class="ui-widget">	
					<input type="text" name="companyPlaceBusinessVO.contaminationInfo.wasteWaterName" id="wasteWaterName" maxlength="60" style="width: 99%" value='${companyPlaceBusinessVO.contaminationInfo.wasteWaterName }' class="form-txt" />
				</div>
			</div>
			<div class="grid_6 lable-right">
				<label class="form-lb1">废水类型：</label>
			</div>
			<div class="grid_6">
				<select name="companyPlaceBusinessVO.contaminationInfo.wasteWaterType.id" id="wasteWaterType" class="form-txt" >
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@WASTEWATER_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.wasteWaterType.id}"></pop:OptionTag>
				</select>
			</div>
		</div>
		<!--  -->
		<div class='clearLine'>&nbsp;</div>
		<div id="wasteGasDiv" style="display:none;">
			<div class="grid_6 lable-right">
				<label class="form-lb1">废气污染物名称：</label>
			</div>
			<div class="grid_6">
				<div class="ui-widget">	
					<input type="text" name="companyPlaceBusinessVO.contaminationInfo.wasteGasName" id="wasteGasName" maxlength="60" style="width: 99%" value='${companyPlaceBusinessVO.contaminationInfo.wasteGasName }' class="form-txt " />
				</div>
			</div>
			<div class="grid_6 lable-right">
				<label class="form-lb1">废气类型：</label>
			</div>
			<div class="grid_6">
				<select name="companyPlaceBusinessVO.contaminationInfo.wasteGasType.id" id="wasteGasType" class="form-txt" >
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@WASTEGAS_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.wasteGasType.id}"></pop:OptionTag>
				</select>
			</div>
		</div>
		<!--  -->
		<div class='clearLine'>&nbsp;</div>
		<div id="wasteSolidDiv" style="display:none;">
			<div class="grid_6 lable-right">
				<label class="form-lb1">固废污染物名称：</label>
			</div>
			<div class="grid_6">
				<div class="ui-widget">	
					<input type="text" name="companyPlaceBusinessVO.contaminationInfo.wasteSolidName" id="wasteSolidName" maxlength="60" style="width: 99%" value='${companyPlaceBusinessVO.contaminationInfo.wasteSolidName }' class="form-txt" />
				</div>
			</div>
			<div class="grid_6 lable-right">
				<label class="form-lb1">固废类型：</label>
			</div>
			<div class="grid_6">
				<select name="companyPlaceBusinessVO.contaminationInfo.wasteSolidType.id" id="wasteSolidType" class="form-txt" >
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@WASTESOLID_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.wasteSolidType.id}"></pop:OptionTag>
				</select>
			</div>
		</div>
		<!--  -->
		<div class='clearLine'>&nbsp;</div>
		<div id="radioactionDiv" style="display:none;">
			<div class="grid_6 lable-right">
				<label class="form-lb1">放射性废物名称：</label>
			</div>
			<div class="grid_6">
				<div class="ui-widget">	
					<input type="text" name="companyPlaceBusinessVO.contaminationInfo.radioactionName" id="radioactionName" maxlength="60" style="width: 99%" value='${companyPlaceBusinessVO.contaminationInfo.radioactionName }' class="form-txt" />
				</div>
			</div>
			<div class="grid_6 lable-right">
				<label class="form-lb1">放射物类型：</label>
			</div>
			<div class="grid_6">
				<select name="companyPlaceBusinessVO.contaminationInfo.radioactionType.id" id="radioactionType" class="form-txt" >
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RADIOACTION_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.radioactionType.id}"></pop:OptionTag>
				</select>
			</div>
		</div>
		<!--  -->		
		<div class='clearLine'>&nbsp;</div>
		<div id="noiseDiv" style="display:none;">
			<div class="grid_6 lable-right">
				<label class="form-lb1">噪音测点名称：</label>
			</div>
			<div class="grid_4">
				<div class="ui-widget">	
					<input type="text" name="companyPlaceBusinessVO.contaminationInfo.noiseName" id="noiseName" maxlength="60" style="width: 99%" value='${companyPlaceBusinessVO.contaminationInfo.noiseName }' class="form-txt" />
				</div>
			</div>
			<div class="grid_3 lable-right">
				<label class="form-lb1">噪声源性质:</label>
			</div>
			<div class="grid_4">
				<div class="ui-widget">	
					<input type="text" name="companyPlaceBusinessVO.contaminationInfo.noiseNature" id="noiseNature" maxlength="60" style="width: 99%" value='${companyPlaceBusinessVO.contaminationInfo.noiseNature }' class="form-txt" />
				</div>
			</div>
			<div class="grid_3 lable-right">
				<label class="form-lb1">噪音类型：</label>
			</div>
			<div class="grid_4">
				<select name="companyPlaceBusinessVO.contaminationInfo.noiseType.id" id="noiseType" class="form-txt" >
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NOISE_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.noiseType.id}"></pop:OptionTag>
				</select>
			</div>
		</div>
		<!--  -->
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lb1">所属街办：</label>
		</div>
		<div class="grid_6">
			<input type="text"  id="orgName"  name="companyPlaceBusinessVO.contaminationInfo.townOrg.orgName"  style="width: 99%"  readonly value="${companyPlaceBusinessVO.contaminationInfo.townOrg.orgName}" class="form-txt" />
			<input type="hidden"  id="orgId"  name="companyPlaceBusinessVO.contaminationInfo.townOrg.id" value="${companyPlaceBusinessVO.contaminationInfo.townOrg.id}" class="form-txt" />
			<input type="hidden"  id="orgInternalCode"  name="companyPlaceBusinessVO.contaminationInfo.townOrg.orgInternalCode" value="${companyPlaceBusinessVO.contaminationInfo.townOrg.orgInternalCode}" class="form-txt" />
		</div>
		<div class="grid_6 lable-right">
			<label class="form-lb1">所属流域：</label>
		</div>
		<div class="grid_6">
			<input type="hidden" name="companyPlaceBusinessVO.contaminationInfo.basin.id" id="basin" class="form-txt" 
			readonly="readonly" value="${companyPlaceBusinessVO.contaminationInfo.basin.id}"/>
			<input name="basinName" id="basinName" class="form-txt" readonly="readonly" 
			value="<pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@BASIN" defaultValue="${companyPlaceBusinessVO.contaminationInfo.basin.id}"/>"/>
			<%-- <select name="companyPlaceBusinessVO.contaminationInfo.basin.id" id="basin" class="form-txt" >
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BASIN" defaultValue="${companyPlaceBusinessVO.contaminationInfo.basin.id}"></pop:OptionTag>
			</select> --%>
		</div>
		
		<div id="basin_dlg"></div>
		
		<!--  -->
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lb1">产业类型：</label>
		</div>
		<div class="grid_4">
			<select name="companyPlaceBusinessVO.contaminationInfo.industryType.id" id="industryType" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@INDUSTRY_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.industryType.id}"></pop:OptionTag>
			</select>
		</div>
		<div class="grid_3 lable-right">
			<label class="form-lb1">行业类别：</label>
		</div>
		<div class="grid_4">
			<input type="hidden" name="companyPlaceBusinessVO.contaminationInfo.tradeType.id" id="tradeType" class="form-txt" 
			readonly="readonly" value="${companyPlaceBusinessVO.contaminationInfo.tradeType.id}"/>
			<input name="tradeTypeSmallName" id="tradeTypeSmallName" class="form-txt" readonly="readonly" 
			value="<pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@TRADE_TYPE_SMALL" defaultValue="${companyPlaceBusinessVO.contaminationInfo.tradeTypeSmall.id}"/>"/>
			<input type="hidden" name="companyPlaceBusinessVO.contaminationInfo.tradeTypeSmall.id" id="tradeTypeSmall" class="form-txt" 
			readonly="readonly" value="${companyPlaceBusinessVO.contaminationInfo.tradeTypeSmall.id}"/>
			<%-- <select name="companyPlaceBusinessVO.contaminationInfo.tradeType.id" id="tradeType" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@TRADE_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.tradeType.id}"></pop:OptionTag>
			</select> --%>
		</div>
		<div id="tradeTypeSmall_dlg"></div>
			
		<div class="grid_3 lable-right">
			<label class="form-lb1">营业面积：</label>
		</div>
		<div class="grid_3">
			<input type="text" name="companyPlaceBusinessVO.contaminationInfo.area" id="area" maxlength="20" style="width: 99%" value='${companyPlaceBusinessVO.contaminationInfo.area }' 
			class="form-txt {decimal:true,range:[0,9999999.99],messages:{decimal:'请输入非负数 ，保留两位小数点',range: '请输入0至9999999.99之间的非负数 '}}" />
		</div>
		<div class="grid_1">㎡</div>
		
		<!--  -->
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lb1">是否环评：</label>
		</div>
		<div class="grid_6">
			<select name="companyPlaceBusinessVO.contaminationInfo.assessmentType.id" id="assessmentType" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ASSESSMENT_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.assessmentType.id}"></pop:OptionTag>
			</select>
		</div>
		<div class="grid_6 lable-right">
			<label class="form-lb1">开业日期：</label>
		</div>
		<div class="grid_6">
			<input type="text" name="companyPlaceBusinessVO.contaminationInfo.startBusinessDate" id="startBusinessDate" value="<fmt:formatDate value="${companyPlaceBusinessVO.contaminationInfo.startBusinessDate}" type="date" pattern="yyyy-MM-dd" />" class="form-txt" style="width:130px;" readonly/>
		</div>
		<!--  -->
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lb1">是否在居民楼下：</label>
		</div>
		<div class="grid_4">
			<select name="companyPlaceBusinessVO.contaminationInfo.lowAsicsType.id" id="lowAsicsType" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LOWASICS_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.lowAsicsType.id}"></pop:OptionTag>
			</select>
		</div>
		<div class="grid_3 lable-right">
			<label class="form-lb1">关注程度：</label>
		</div>
		<div class="grid_4">
			<select name="companyPlaceBusinessVO.contaminationInfo.concernType.id" id="concernType" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CONCERN_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.concernType.id}"></pop:OptionTag>
			</select>
		</div>
		<div class="grid_3 lable-right">
			<label class="form-lb1">隶属关系：</label>
		</div>
		<div class="grid_4">
			<select name="companyPlaceBusinessVO.contaminationInfo.relationshipType.id" id="relationshipType" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RELATIONSHIP_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.relationshipType.id}"></pop:OptionTag>
			</select>
		</div>
		<!--  -->
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lb1">单位类别：</label>
		</div>
		<div class="grid_4">
			<select name="companyPlaceBusinessVO.contaminationInfo.unitType.id" id="unitType" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@UNIT_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.unitType.id}"></pop:OptionTag>
			</select>
		</div>
		<div class="grid_3 lable-right">
			<label class="form-lb1">企业规模：</label>
		</div>
		<div class="grid_4">
			<select name="companyPlaceBusinessVO.contaminationInfo.scaleType.id" id="scaleType" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCALE_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.scaleType.id}"></pop:OptionTag>
			</select>
		</div>
		<div class="grid_3 lable-right">
			<label class="form-lb1">重点行业：</label>
		</div>
		<div class="grid_4">
			<select name="companyPlaceBusinessVO.contaminationInfo.keyIndustryType.id" id="keyIndustryType" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@KEYINDUSTRY_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.keyIndustryType.id}"></pop:OptionTag>
			</select>
		</div>
		<!--  -->
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lb1">监管类别：</label>
		</div>
		<div class="grid_4">
			<select name="companyPlaceBusinessVO.contaminationInfo.superviseType.id" id="superviseType" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SUPERVISE_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.superviseType.id}"></pop:OptionTag>
			</select>
		</div>
		<div class="grid_3 lable-right">
			<label class="form-lb1">污染源类：</label>
		</div>
		<div class="grid_4">
			<select name="companyPlaceBusinessVO.contaminationInfo.contaminationType.id" id="contaminationType" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CONTAMINATION_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.contaminationType.id}"></pop:OptionTag>
			</select>
		</div>
		<div class="grid_3 lable-right">
			<label class="form-lb1">行政许可：</label>
		</div>
		<div class="grid_4">
			<select name="companyPlaceBusinessVO.contaminationInfo.licensingType.id" id="licensingType" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LICENSING_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.licensingType.id}"></pop:OptionTag>
			</select>
		</div>
		<!--  -->
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lb1">行政处罚时间：</label>
		</div>
		<div class="grid_6">
			<input type="text" name="companyPlaceBusinessVO.contaminationInfo.penaltyDate" id="penaltyDate" value="<fmt:formatDate value="${companyPlaceBusinessVO.contaminationInfo.penaltyDate}" type="date" pattern="yyyy-MM-dd" />" class="form-txt" style="width:130px;" readonly/>
		</div>
		<div class="grid_6 lable-right">
			<label class="form-lb1">行政处罚金额：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="companyPlaceBusinessVO.contaminationInfo.penaltyAmount" id="penaltyAmount" maxlength="20" style="width: 99%" value='${companyPlaceBusinessVO.contaminationInfo.penaltyAmount }'
			class="form-txt {decimal:true,range:[0,9999999.99],messages:{decimal:'请输入非负数 ，保留两位小数点',range: '请输入0至9999999.99之间的非负数 '}}" />
		</div>
		<div class="grid_1">元</div>
		<!--  -->
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lbl">行政处罚原因：</label>
		</div>
		<div class="grid_18 heightAuto"> 
			<textarea id="penaltyReason" name="companyPlaceBusinessVO.contaminationInfo.penaltyReason" maxlength="512" class="form-txt" style="height:70px">${companyPlaceBusinessVO.contaminationInfo.penaltyReason}</textarea>
		</div>
		<!--  -->
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lbl">行政处罚执行情况：</label>
		</div>
		<div class="grid_18 heightAuto"> 
			<textarea id="penaltyExecute" name="companyPlaceBusinessVO.contaminationInfo.penaltyExecute" maxlength="512" class="form-txt" style="height:70px">${companyPlaceBusinessVO.contaminationInfo.penaltyExecute}</textarea>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lbl">中心位置：</label>
		</div>
		<div class="grid_2 lable-right">
			<label class="form-lbl">经度：</label>
		</div>
		<div class="grid_5">
			<input type="text"  id="builddatas-centerLon" name="companyPlaceBusinessVO.contaminationInfo.openLayersMapInfo.centerLon" value=""  readonly class="form-txt"/>
		</div>
		<div class="grid_2 lable-right">
			<label class="form-lbl">纬度：</label>
		</div>
		<div class="grid_5">
			<input type="text" id="builddatas-centerLat" name="companyPlaceBusinessVO.contaminationInfo.openLayersMapInfo.centerLat" value=""  readonly class="form-txt"/>
	   		<input type="hidden" id="buildingid" name="companyPlaceBusinessVO.contaminationInfo.buildDataId" value=""/>
			<input type="hidden" id="builddatas-isTransformat" name="companyPlaceBusinessVO.contaminationInfo.openLayersMapInfo.isTransformat" value="${companyPlaceBusinessVO.contaminationInfo.openLayersMapInfo.isTransformat}" />
			<input type="hidden" id="builddatas-gisType" name="companyPlaceBusinessVO.contaminationInfo.openLayersMapInfo.gisType" value="${companyPlaceBusinessVO.contaminationInfo.openLayersMapInfo.gisType}" />
	   </div>
	   <div class="grid_4">
			<c:if test='${mode!="view" && mode!="gisEdit"}'>
				<input type="button" value="绑定" class="defaultBtn" id="bindMap"/>
				<span id="cancelBindSpan"style="display:none;">
					<input type="button" value="解绑" class="defaultBtn" id="cancelBind"/>
				</span>
			</c:if>
	   </div>
	</div>
</pop:JugePermissionTag>
</div>
</pop:JugePermissionTag>
</form>
</div>

<script type="text/javascript">
var gisType="2D";

$(function(){
	
	//根据ID和TYPE查询对应的keyplace信息
	fillKeyPlaceByIdAndType();
	function fillKeyPlaceByIdAndType(){
		$.ajax({
			url:PATH+"/baseinfo/companyPlaceBusinessManage/getKeyPlaceByIdAndType.action",
			data:{
				"id": $("#companyPlaceId_id").val(),
				"type": "<s:property value='@com.tianque.baseInfo.companyPlace.constacts.ModulTypes@POLLUTIONSOURCE_KEY'/>"
			}, 
			success:function(responseData){
				if(responseData != null && responseData.id != null){
					if($("#gisType").val()=="3D" || gisType=="3D"){
						$("#builddatas-centerLon").val(responseData.openLayersMapInfo.centerLon);
						$("#builddatas-centerLat").val(responseData.openLayersMapInfo.centerLat);
						$("#buildingid").val(responseData.buildDatasId);
					}else{
						$("#builddatas-centerLon").val(responseData.openLayersMapInfo.centerLon2);
						$("#builddatas-centerLat").val(responseData.openLayersMapInfo.centerLat2);
						$("#buildingid").val(responseData.buildDatasId);
					}
					$("#cancelBindSpan").show();
				}
			}
				
		});
	}
	$("#bindMap").click(function(){
		$("#gisBuilddatasDialog").createDialog({
		  zIndex:1020,
          width: 800,
          height: 600,
          title:'绑定建筑物',
          url:"${path}/openLayersMap/product_3.0/gis2Dbuilddatas.jsp?flag=1&module=companyplace&organizationId="+getCurrentOrgId()+"&gisType="+gisType,
          buttons: {
              "关闭" : function(){
            	 $("#builddatasList").trigger("reloadGrid");
                 $(this).dialog("close");
              }
          },
          shouldEmptyHtml:false
		});
	});
	
	$("#cancelBind").click(function(){
		$("#builddatas-centerLon").val("");
		$("#builddatas-centerLat").val("");
		$("#buildingid").val("");
	});
	<pop:JugePermissionTag ename="contaminationInfoManagement">
	$('#startBusinessDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
		maxDate:'-0d'
	});
	$('#penaltyDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
		maxDate:'-0d'
	});
	
	
	
	
	$("#isWasteWater").change(function(){
		if($("#isWasteWater").attr("checked")){
			$("#wasteWaterDiv").css('display','block');
		}else{
			$("#wasteWaterDiv").css('display','none');
			cleanWasteWater();
		}
	});
	$("#isWasteSolid").change(function(){
		if($("#isWasteSolid").attr("checked")){
			$("#wasteSolidDiv").css('display','block');
		}else{
			$("#wasteSolidDiv").css('display','none');
			cleanWasteSolid();
		}
	});
	$("#isRadioaction").change(function(){
		if($("#isRadioaction").attr("checked")){
			$("#radioactionDiv").css('display','block');
		}else{
			$("#radioactionDiv").css('display','none');
			cleanRadioaction();
		}
	});
	$("#isNoise").change(function(){
		if($("#isNoise").attr("checked")){
			$("#noiseDiv").css('display','block');
		}else{
			$("#noiseDiv").css('display','none');
			cleanNoise();
		}
	});
	$("#isWasteGas").change(function(){
		if($("#isWasteGas").attr("checked")){
			$("#wasteGasDiv").css('display','block');
		}else{
			$("#wasteGasDiv").css('display','none');
			cleanWasteGas();
		}
	});
	
	fillTownOrgInfo();
	fillDictCommon();
	fillPropertyDomainByDomainName("所属流域");
	fillPropertyDomainByDomainName("行业-类别");
	
	</pop:JugePermissionTag>
	$('#correctionBeiginDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#correctionEndDate").datepicker("option", "minDate",date);
			}
		}
	});

	$('#correctionEndDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#correctionBeiginDate").datepicker("option", "maxDate",date);
			}
		}
	});

	$('#custom_file_upload_proKey').uploadFile({
		queueID:"custom-queue_proKey",
		selectInputId:"proKeyAttachFiles",
		targetType:"NEWSAFETYPRODUCTIONKEY"
	});

	$('#custom_file_upload_fireSafetyKey').uploadFile({
		queueID:"custom-queue_fireSafetyKey",
		selectInputId:"fireSafetyKeyAttachFiles",
		targetType:"NEWFIRESAFETYKEY"
	});

	$('#custom_file_upload_securityKey').uploadFile({
		queueID:"custom-queue_securityKey",
		selectInputId:"securityKeyAttachFiles",
		targetType:"NEWSECURITYKEY"
	});

	$('#custom_file_upload_pollSource').uploadFile({
		queueID:"custom-queue_pollSource",
		selectInputId:"pollSourceAttachFiles",
		targetType:"POLLUTIONSOURCE"
	});
 	
	fillBuniess();
	isShow();
	isShow1();
	isShow2();
	isShow3();
	fillFile();
	$("#maintainForm").formValidate({
		submitHandler: function(form) {
	        $(form).ajaxSubmit({
				success: function(data){
					var str = data.replace(/<[^>]+>/g,"");
					var data = str.replace(/\"/g, "");
					if(data == true || data =='true'){
					}else{
						$.messageBox({
							message:"业务信息保存失败!",
							level: "error"
						});
					}
			    	$("#companyPlaceList").trigger("reloadGrid");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
	rules:{
	},
	messages:{
	}
	});
	$("#maintainForm").attr("action","${path}/baseinfo/companyPlaceBusinessManage/saveCompanyPlaceBusiness.action");
	
	$('#basinName').click(function(){
		getPropertyDictByPinYinAndDomainid();
     });
	
	$('#tradeTypeSmallName').click(function(){
		getPropertyDictByDomainidAndInternalid();
     });
});

//查询选择组织机构的所对应的街道组织机构
function fillTownOrgInfo(){
	var orgId=getCurrentOrgId();
	$.ajax({
		url:PATH+"/sysadmin/orgManage/findTownOrgInfo.action",
		data:{
			"orgId": orgId
		},
		success:function(responseData){
			if(responseData != null && responseData.id != null){
				$("#orgName").val(responseData.orgName);
				$("#orgId").val(responseData.id);
				$("#orgInternalCode").val(responseData.orgInternalCode);
			}
		}
			
	});
}

//根据属性名称 查询对应的编号
function fillPropertyDomainByDomainName(domainName){
	$.ajax({
		 url:PATH+"/sysadmin/propertyManage/getPropertyDomainByDomainName.action",
		data:{
			"domainName":domainName
		},
		success:function(responseData){
			if(responseData != null && responseData.id != null){
				if(domainName == '行业-类别'){
					$("#propertyDomainId_tradeType").val(responseData.id);
				}else{
					$("#propertyDomainId").val(responseData.id);
				}
			}
		}
	});
}

//根据拼音查询字典项--所属流域
function getPropertyDictByPinYinAndDomainid(){
	var propertyDomainId = $("#propertyDomainId").val();
    $("#basin_dlg").selectArea({
   	 	url:PATH+"/sysadmin/propertyManage/getPropertyDictByPinYinAndDomainid.action",
   	 	dataMed:{
			"propertyDomainId":propertyDomainId
   	 	},
        height: 200,
   	 	clickCallback:function(e,data){
   	 		$("#basin").val("");
   	 		$("#basinName").val("");
   	 	    $("#basin").val(data.id);
   			$("#basinName").val(data.text);
   	 	}
     })
}

//根据拼音查询字典项--行业类别-二级分类
function getPropertyDictByDomainidAndInternalid(){
	var propertyDomainId = $("#propertyDomainId_tradeType").val();
	 $("#tradeTypeSmall_dlg").selectArea({
		 url:PATH+"/sysadmin/propertyManage/getPropertyDictByPinYinAndDomainid.action",
		 menuUrl:PATH+"/sysadmin/propertyManage/getPropertyDictByDomainidAndInternalid.action",
		 dataMed:{
				"propertyDomainId":propertyDomainId
	   	 },
	   	 clickCallback:function(e,data){
	   		$("#tradeType").val("");
	   		$("#tradeType").val(data.id);
	   	 },
		 menuCallback:function(e,data){
			$("#tradeTypeSmall").val("");
			$("#tradeTypeSmallName").val("");
			$("#tradeTypeSmallName").val(data.text);
			$("#tradeTypeSmall").val(data.id);
		 },
        levelMenu:true
     })
}

//清空废水
function cleanWasteWater(){
	$("#wasteWaterName").val("");
	cleanDictCommon("wasteWaterType");
	
}
//清空固废
function cleanWasteSolid(){
	$("#wasteSolidName").val("");
	cleanDictCommon("wasteSolidType");
}
//清空放射性
function cleanRadioaction(){
	$("#radioactionName").val("");
	cleanDictCommon("radioactionType");
}
//清空噪音
function cleanNoise(){
	$("#noiseName").val("");
	$("#noiseNature").val("");
	cleanDictCommon("noiseType");
}
//清空废气
function cleanWasteGas(){
	$("#wasteGasName").val("");
	cleanDictCommon("wasteGasType");
}

//置空字典项公用方法
function cleanDictCommon(idName){
	$("#"+idName+" option").each(function(){
		if($(this).text()=="请选择"){
			$(this).attr("selected","selected");
		}
	});
}

//字典项默认选中值
function fillDictCommon(){
	$("#concernType option").each(function(){
		if($("#concernType").val() == ""){
			if($(this).text()=="非重点"){
				$(this).attr("selected","selected");
			}
		}
	});
	$("#industryType option").each(function(){
		if($("#industryType").val() == ""){
			if($(this).text()=="三产企业"){
				$(this).attr("selected","selected");
			}
		}
	});
	$("#basin option").each(function(){
		if($("#basin").val() == ""){
			if($(this).text()=="长江流域"){
				$(this).attr("selected","selected");
			}
		}
	});
	$("#lowAsicsType option").each(function(){
		if($("#lowAsicsType").val() == ""){
			if($(this).text()=="否"){
				$(this).attr("selected","selected");
			}
		}
	});
}

//清空污染源所有
function cleanAllContaminationInfo(){
	$("#isWasteWater").attr("checked",false);
	$("#isWasteSolid").attr("checked",false);
	$("#isRadioaction").attr("checked",false);
	$("#isNoise").attr("checked",false);
	$("#isWasteGas").attr("checked",false);
	$("#wasteWaterDiv").css('display','none');
	cleanWasteWater();
	$("#wasteSolidDiv").css('display','none');
	cleanWasteSolid();
	$("#radioactionDiv").css('display','none');
	cleanRadioaction();
	$("#noiseDiv").css('display','none');
	cleanNoise();
	$("#wasteGasDiv").css('display','none');
	cleanWasteGas();
	$("#basin").val("");
	$("#basinName").val("");
	cleanDictCommon("industryType");
	$("#tradeType").val("");
	$("#tradeTypeSmallName").val("");
	$("#tradeTypeSmall").val("");
	$("#area").val("");
	cleanDictCommon("assessmentType");
	$("#startBusinessDate").val("");
	cleanDictCommon("lowAsicsType");
	cleanDictCommon("concernType");
	cleanDictCommon("relationshipType");
	cleanDictCommon("unitType");
	cleanDictCommon("scaleType");
	cleanDictCommon("keyIndustryType");
	cleanDictCommon("superviseType");
	cleanDictCommon("contaminationType");
	cleanDictCommon("licensingType");
	$("#penaltyDate").val("");
	$("#penaltyAmount").val("");
	$("#penaltyReason").val("");
	$("#penaltyExecute").val("");
	$("#builddatas-centerLon").val("");
	$("#builddatas-centerLat").val("");
}
function fillBuniess(){
	if($("#proKeyIsKeyTypeValue").val() != ''){
		$("#proKeyIsKeyType").attr("checked", true);
	}
	if($("#fireSafetyKeyIsKeyTypeValue").val() != ''){
		$("#fireSafetyKeyIsKeyType").attr("checked", true);
	}
	if($("#securityKeyIsKeyTypeValue").val() != ''){
		$("#securityKeyIsKeyType").attr("checked", true);
	}
	if($("#pollSourceIsKeyTypeValue").val() != ''){
		$("#pollSourceIsKeyType").attr("checked", true);
	}
	
	var modulKey = $("#modulKey").val();
	if(modulKey == 'NEWSAFETYPRODUCTIONKEY'){
		$("#newWhetherProductionKey").css('display','block');
		$("#proKeyIsKeyType").attr("checked", true);
		$("#proKeyIsKeyType").click(function(){return false})
	}else if(modulKey == 'NEWFIRESAFETYKEY'){
		$("#newWhetherFireSafetyKey").css('display','block');
		$("#fireSafetyKeyIsKeyType").attr("checked", true);
		$("#fireSafetyKeyIsKeyType").click(function(){return false})
	}else if(modulKey == 'NEWSECURITYKEY'){
		$("#newWhetherSecurityKey").css('display','block');
		$("#securityKeyIsKeyType").attr("checked", true);
		$("#securityKeyIsKeyType").click(function(){return false})
	}else if(modulKey == 'POLLUTIONSOURCE'){
		$("#newWhetherPollutionSource").css('display','block');
		<pop:JugePermissionTag ename="contaminationInfoManagement">
			$("#contaminationInfoDiv").css('display','block');
			fillcontaminationInfo();
		</pop:JugePermissionTag>
		$("#pollSourceIsKeyType").attr("checked", true);
		$("#pollSourceIsKeyType").click(function(){return false})
	}else{
		$("#newWhetherProductionKey").css('display','block');
		$("#newWhetherFireSafetyKey").css('display','block');
		$("#newWhetherSecurityKey").css('display','block');
		$("#newWhetherPollutionSource").css('display','block');
		<pop:JugePermissionTag ename="contaminationInfoManagement">
			$("#contaminationInfoDiv").css('display','block');
			fillcontaminationInfo();
		</pop:JugePermissionTag>
	}
}

function fillcontaminationInfo(){
	if($("#isWasteWaterValue").val() != ''){
		$("#isWasteWater").attr("checked", true);
		$("#wasteWaterDiv").show();
	}
	if($("#isWasteSolidValue").val() != ''){
		$("#isWasteSolid").attr("checked", true);
		$("#wasteSolidDiv").show();
	}
	if($("#isRadioactionValue").val() != ''){
		$("#isRadioaction").attr("checked", true);
		$("#radioactionDiv").show();
	}
	if($("#isNoiseValue").val() != ''){
		$("#isNoise").attr("checked", true);
		$("#noiseDiv").show();
		$("#noiseNatureDiv").show();
	}
	if($("#isWasteGasValue").val() != ''){
		$("#isWasteGas").attr("checked", true);
		$("#wasteGasDiv").show();
	}
	if($("#isHeavyMetalValue").val() != ''){
		$("#isHeavyMetal").attr("checked", true);
	}
}

$("#proKeyIsKeyType").change(function(){
	if(modulKey != 'NEWSAFETYPRODUCTIONKEY'){
		if( $("#proKeyIsKeyType").attr('checked') != 'checked' ){
				$.confirm({
					title:"确认清空",
					message:"去掉勾选会清空所有数据，您确认清空吗?",
					okFunc: function() {
						$("#proKeyIsKeyTypeValue").val("");
				    	$("#proKeyHiddangerInfo").val("");
				    	$("#proKeyCorrectionInfo").val("");
				    	$("#proKeyAttachFiles").val("");
				    	$("#proKeyAttachFiles").find("option").remove();
				    	$("#custom-queue_proKey").find("div").remove(); 
				    	if($("#proKeyCompanyPlaceBusinessId").val() != null){
				    		$("#delProKeyID").val($("#proKeyCompanyPlaceBusinessId").val());
				    	}
				    	$("#proKeyIsKeyType").attr("checked", false);
				    	$("#productionKey").css('display','none');
					}
				});
		  }else{
			  $("#productionKey").css('display','block');
			  $("#delProKeyID").val(""); 
		  }
	}
});
$("#fireSafetyKeyIsKeyType").change(function(){
	if(modulKey != 'NEWFIRESAFETYKEY'){
		if( $("#fireSafetyKeyIsKeyType").attr('checked') != 'checked' ){
			$.confirm({
				title:"确认清空",
				message:"去掉勾选会清空所有数据，您确认清空吗?",
				okFunc: function() {
					$("#fireSafetyKeyIsKeyTypeValue").val("");
			    	$("#fireSafetyKeyHiddangerInfo").val("");
			    	$("#fireSafetyKeyCorrectionInfo").val("");
			    	$("#fireSafetyKeyAttachFiles").val("");
			    	$("#fireSafetyKeyAttachFiles").find("option").remove();
			    	$("#custom-queue_fireSafetyKey").find("div").remove(); 
			    	if($("#fireSafetyKeyCompanyPlaceBusinessId").val() != null){
			    		$("#delfirKeyID").val($("#fireSafetyKeyCompanyPlaceBusinessId").val());
			    	}
			    	$("#fireSafetyKey").css('display','none');
			    	$("#fireSafetyKeyIsKeyType").attr("checked", false);
				}
			});
	    }else{
	    	$("#fireSafetyKey").css('display','block');
			$("#delfirKeyID").val(""); 
		}
	}
});
$("#securityKeyIsKeyType").change(function(){
	if(modulKey != 'NEWSECURITYKEY'){
		if( $("#securityKeyIsKeyType").attr('checked') != 'checked' ){
			$.confirm({
				title:"确认清空",
				message:"去掉勾选会清空所有数据，您确认清空吗?",
				okFunc: function() {
					$("#securityKeyIsKeyTypeValue").val("");
			    	$("#correctionBeiginDate").val("");
			    	$("#correctionEndDate").val("");
			    	$("#securityKeyHiddangerInfo").val("");
			    	$("#securityKeyCorrectionInfo").val("");
			    	$("#effectassessment").val("");
			    	$("#securityKeyAttachFiles").val("");
			    	$("#securityKeyAttachFiles").find("option").remove();
			    	$("#custom-queue_securityKey").find("div").remove(); 
			    	if($("#securityKeyCompanyPlaceBusinessId").val() != null){
			    		$("#delsecKeyID").val($("#securityKeyCompanyPlaceBusinessId").val());
			    	}
			    	$("#securityKey").css('display','none');
			    	$("#securityKeyIsKeyType").attr("checked", false);
				}
			});
	    }else{
	    	$("#securityKey").css('display','block');
			  $("#delsecKeyID").val(""); 
		}
	}
});
$("#pollSourceIsKeyType").change(function(){
	if(modulKey != 'POLLUTIONSOURCE'){
		if( $("#pollSourceIsKeyType").attr('checked') != 'checked' ){
			$.confirm({
				title:"确认清空",
				message:"去掉勾选会清空所有数据，您确认清空吗?",
				okFunc: function() {
					$("#pollSourceIsKeyTypeValue").val("");
			    	$("#pollSourceHiddangerInfo").val("");
			    	$("#pollSourcePollutionInfo").val("");
			    	$("#pollutionType").val("");
			    	$("#pollSourceAttachFiles").val("");
			    	$("#pollSourceAttachFiles").find("option").remove();
			    	$("#custom-queue_pollSource").find("div").remove(); 
			    	if($("#pollSourceCompanyPlaceBusinessId").val() != null){
			    		$("#delpollKeyID").val($("#pollSourceCompanyPlaceBusinessId").val());
			    	}
			    	$("#pollutionSource").css('display','none');
					$("#pollutionCase").css('display','none');
			    	$("#pollSourceIsKeyType").attr("checked", false);
			    	<pop:JugePermissionTag ename="contaminationInfoManagement">
			    		cleanAllContaminationInfo();
			    	</pop:JugePermissionTag>
				}
			});
	    }else{
	    	$("#pollutionSource").css('display','block');
			$("#pollutionCase").css('display','block');
			  $("#delpollKeyID").val(""); 
		}
	}
});
function fillFile(){
	<c:if test="${companyPlaceBusinessVO!=null}">
		<c:if test="${companyPlaceBusinessVO.proKeyCompanyPlaceBusiness !=null && companyPlaceBusinessVO.proKeyCompanyPlaceBusiness.placeAnnexFiles!=null  && fn:length(companyPlaceBusinessVO.proKeyCompanyPlaceBusiness.placeAnnexFiles)>0}">
			<c:forEach items="${companyPlaceBusinessVO.proKeyCompanyPlaceBusiness.placeAnnexFiles}" var="placeAnnexFile">
		        $("#custom-queue_proKey").addUploadFileValue({
			        id:"${placeAnnexFile.id}",
			        filename:"${placeAnnexFile.fileName}",
			    	link:"${path}/baseinfo/companyPlaceBusinessManage/downloadCompanyPlaceAnnexAttachFile.action?companyPlaceAnnex.id=${placeAnnexFile.id}",
			        onRemove:function(id){removeAttachById('proKeyAttachFiles',id)}
				});
		        $("<option>").attr("id","${placeAnnexFile.id}").val("${placeAnnexFile.id},${placeAnnexFile.fileName}").attr("selected",true).appendTo($("#proKeyAttachFiles"));
		     </c:forEach>
	     </c:if>
		<c:if test="${companyPlaceBusinessVO.fireSafetyKeyCompanyPlaceBusiness !=null && companyPlaceBusinessVO.fireSafetyKeyCompanyPlaceBusiness.placeAnnexFiles!=null  && fn:length(companyPlaceBusinessVO.fireSafetyKeyCompanyPlaceBusiness.placeAnnexFiles)>0}">
			<c:forEach items="${companyPlaceBusinessVO.fireSafetyKeyCompanyPlaceBusiness.placeAnnexFiles}" var="placeAnnexFile">
		        $("#custom-queue_fireSafetyKey").addUploadFileValue({
			        id:"${placeAnnexFile.id}",
			        filename:"${placeAnnexFile.fileName}",
			    	link:"${path}/baseinfo/companyPlaceBusinessManage/downloadCompanyPlaceAnnexAttachFile.action?companyPlaceAnnex.id=${placeAnnexFile.id}",
			        onRemove:function(id){removeAttachById('fireSafetyKeyAttachFiles',id)}
				});
		        $("<option>").attr("id","${placeAnnexFile.id}").val("${placeAnnexFile.id},${placeAnnexFile.fileName}").attr("selected",true).appendTo($("#fireSafetyKeyAttachFiles"));
		     </c:forEach>
	     </c:if>
		<c:if test="${companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness !=null && companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.placeAnnexFiles!=null  && fn:length(companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.placeAnnexFiles)>0}">
			<c:forEach items="${companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.placeAnnexFiles}" var="placeAnnexFile">
		        $("#custom-queue_securityKey").addUploadFileValue({
			        id:"${placeAnnexFile.id}",
			        filename:"${placeAnnexFile.fileName}",
			    	link:"${path}/baseinfo/companyPlaceBusinessManage/downloadCompanyPlaceAnnexAttachFile.action?companyPlaceAnnex.id=${placeAnnexFile.id}",
			        onRemove:function(id){removeAttachById('securityKeyAttachFiles',id)}
				});
		        $("<option>").attr("id","${placeAnnexFile.id}").val("${placeAnnexFile.id},${placeAnnexFile.fileName}").attr("selected",true).appendTo($("#securityKeyAttachFiles"));
		     </c:forEach>
	     </c:if>
		<c:if test="${companyPlaceBusinessVO.pollSourceCompanyPlaceBusiness !=null && companyPlaceBusinessVO.pollSourceCompanyPlaceBusiness.placeAnnexFiles!=null  && fn:length(companyPlaceBusinessVO.pollSourceCompanyPlaceBusiness.placeAnnexFiles)>0}">
			<c:forEach items="${companyPlaceBusinessVO.pollSourceCompanyPlaceBusiness.placeAnnexFiles}" var="placeAnnexFile">
		        $("#custom-queue_pollSource").addUploadFileValue({
			        id:"${placeAnnexFile.id}",
			        filename:"${placeAnnexFile.fileName}",
			    	link:"${path}/baseinfo/companyPlaceBusinessManage/downloadCompanyPlaceAnnexAttachFile.action?companyPlaceAnnex.id=${placeAnnexFile.id}",
			        onRemove:function(id){removeAttachById('pollSourceAttachFiles',id)}
				});
		        $("<option>").attr("id","${placeAnnexFile.id}").val("${placeAnnexFile.id},${placeAnnexFile.fileName}").attr("selected",true).appendTo($("#pollSourceAttachFiles"));
		     </c:forEach>
	     </c:if>
	</c:if>
}

function removeAttachById(fname,id){
	$("#"+fname).find("option[id="+id+"]").remove();
}

jQuery.validator.addMethod("decimal", function(value, element) {
    var decimal = /^-?\d+(\.\d{1,2})?$/;
    if (value==""){
	     return true;
	 }
    if(value>=0){
    	return this.optional(element) || (decimal.test(value));
    }
    return false;
});
</script>