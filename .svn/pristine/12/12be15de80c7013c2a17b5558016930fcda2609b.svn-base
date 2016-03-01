<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style type="text/css">
.chooseDlgTop{height:33px;line-height:33px;background:#fff;}
	.chooseDlgTop .title{font:normal 14px/35px 'microsoft yahei' !important;text-indent:4px;}
	.chooseDlgTop .close{
		background:#444 url(/resource/images/right.png) no-repeat 11px 6px;
		
		color:#fff;position:absolute;top:7px;right:14px;width:87px;height:26px;line-height:26px;text-indent:27px;
		border-radius:2px;-webkit-border-radius:2px;-moz-border-radius:2px;
	}
	#ui-datepicker-div{width:244px;}
	ul.holder,
	ul.holder .bit-input{
		height: 26px;
		padding:0;
		border:0 none;
	}
	.toChoose {
		position: relative;
		border: 1px solid #c6c6c6;
		border-radius: 2px;
		-webkit-border-radius: 2px;
		-moz-border-radius: 2px;
		font-family: 'microsoft yahei';
		font-size: 12px;
	}
	.toChoose span.changeBtn {
		position: absolute;
		top: 0;
		right: 0;
		z-index: 1;
		width: 58px;
		height: 26px;
		line-height: 26px;
		border-left: 1px solid #B6B6B6;
		background: url(/resource/system/images/newImg/qxz.jpg) -1px bottom no-repeat;
		text-align: center;
		cursor: pointer;
		color: #2d2d2d;
	}
	.toChoose textarea{
		height:20px;width: 90px;
	}
	#ITD_dlg{z-index:2;}
</style>
<div class="container container_24">
<form id="administrativeOrgTimeLimitStandardForm" method="post" action="">
<pop:token />
	<input type="hidden" value="${administrativeOrgTimeLimitStandard.createOrg.id }" name="administrativeOrgTimeLimitStandard.createOrg.id"/>
    <input id="mode" type="hidden" name="mode" value="${mode}" />
	<input id="administrativeOrgTimeLimitStandardId" type="hidden" name="administrativeOrgTimeLimitStandard.id" value="${administrativeOrgTimeLimitStandard.id }" />
	<input id="useLevelId" type="hidden" name="administrativeOrgTimeLimitStandard.useLevel.id" value="${administrativeOrgTimeLimitStandard.useLevel.id }" />
	<div class="grid_4 lable-right">
	    <em class="form-req">*</em>
		<label class="form-lbl">行政部门：</label>
	</div>
	
	<div class="grid_19">
		<input id="administrativeUseLevel" type="text" readonly="readonly" class='form-txt' value="${administrativeOrgTimeLimitStandard.useLevel.displayName }">
	</div>
	<div class='clearLine'>&nbsp;</div>
	

    <div class="grid_4 lable-right">
        <em class="form-req">*</em><label class="form-lbl">事件类型：</label>
    </div>
    <div class="grid_20 heightAuto" style='margin:4px 0 2px;'>
     	<div id="issueTypeDescription" name="typeNames"></div>
        <div class="rightBar" id="ITD_rightBar">
            <a href="javascript:;" class="down"></a>
        </div>
     </div>
            
    <div id="ITD_dlg" style="display: none;height:400px;overflow:auto;">
       	<div class="chooseDlgTop" style="position:relative;">
       		<a href="javascript:;" id="objectSelectBoxClose" class="close">我选好了</a>
		<h3 class="title">请选择事件类型：（可多选）</h3>
		</div>
        		
        <c:forEach items="${assembleValues}" var="domain"> 
 		<div class="multipeSelect" style="clear:both;">
			<input style="display:none;" id="issueTypeSelector0" name="" type="checkbox" class="category" value="." checked="checked">
  			<label class="form-check-text" for="">${domain.issueTypeDomain.domainName}</label> 
  			<ul id="issueTypes${issueType.id }">
  				<c:forEach items="${domain.issueTypes }" var="issueType">
   			  <li>
   				<label class="form-check-text"><input name="selectedTypes" type="checkbox" value='${issueType.fullPinYin}'  />${issueType.issueTypeName}</label> 
   			  </li>
  			  	</c:forEach>
  			</ul>
		</div>
       	</c:forEach> 
		
   </div>

	
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_2"></div>
   	<div class="grid_22">
   		<em class="form-req">*</em>
		<label class="form-lbl" style="color: red;">办理时限（至少填写一项）</label>
	</div>		
		
		<table>
			<tr>
				<td colspan="2">
					<div style="padding: 15px 0px 5px 50px;color: #C9C9C9;">——<span style="font-weight: bolder;color:#353535;font-size:14px;padding:0px 10px 0px 10px">受理</span>————————————————————————————————————————————</div>		
				</td>
			</tr>
			<tr>
				<td style="padding: 0px 0px 0px 60px;">
					<div class='clearLine'></div>
					<div class="grid_6 lable-right" >
						<label class="form-lb1">黄牌时限：</label>
					</div>
					<div class="grid_3">
						<input  type="text"  id="administrativeOrgTimeLimitStandardYellowLimitAccept" maxlength="10" name="administrativeOrgTimeLimitStandard.yellowLimitAccept" value="${administrativeOrgTimeLimitStandard.yellowLimitAccept}" title="黄牌受理时限"
							class='form-txt'/>
					</div>
					<div class="grid_7">
						&nbsp;&nbsp;个工作日
					</div>
					<div class="grid_7" style="text-align:left">
					</div>
				</td>
				<td>
					<div class='clearLine'>&nbsp;</div>
					<div class="grid_6 lable-right" >
						<label class="form-lb1">红牌时限：</label>
					</div>
					<div class="grid_3">
						<input  type="text" id="administrativeOrgTimeLimitStandardRedLimitAccept" maxlength="10" name="administrativeOrgTimeLimitStandard.redLimitAccept" value="${administrativeOrgTimeLimitStandard.redLimitAccept}" title="红牌受理时限"
							class='form-txt'/>
					</div>
					<div class="grid_7">
						&nbsp;&nbsp;个工作日
					</div>
					<div class="grid_7" style="text-align:left">
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="color: red;padding: 15px 0px 0px 60px;">
					注:受理的时限标准从事件交办之日起计算，红牌时限需大于黄牌时限
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<div style="padding: 15px 0px 5px 50px;color: #C9C9C9;">——<span style="font-weight: bolder;color:#353535;font-size:14px;padding:0px 10px 0px 10px">办理</span>————————————————————————————————————————————</div>		
				</td>
			</tr>
			<tr>
				<td style="padding: 0px 0px 0px 60px;">
					<div class='clearLine'>&nbsp;</div>
					<div class="grid_6 lable-right" >
						<label class="form-lb1">黄牌时限：</label>
					</div>
					<div class="grid_3">
						<input  type="text" id="administrativeOrgTimeLimitStandardYellowLimitHandle" maxlength="10" name="administrativeOrgTimeLimitStandard.yellowLimitHandle" value="${administrativeOrgTimeLimitStandard.yellowLimitHandle}" title="黄牌办理时限"
							class='form-txt'/>
					</div>
					<div class="grid_7">
						&nbsp;&nbsp;个工作日
					</div>
					<div class="grid_7" style="text-align:left">
					</div>			
				</td>
				<td>
					<div class='clearLine'>&nbsp;</div>
					<div class="grid_6 lable-right" >
						<label class="form-lb1">红牌时限：</label>
					</div>
					<div class="grid_3">
						<input  type="text" id="administrativeOrgTimeLimitStandardRedLimitHandle" maxlength="10" name="administrativeOrgTimeLimitStandard.redLimitHandle" value="${administrativeOrgTimeLimitStandard.redLimitHandle}" title="红牌办理时限"
							class='form-txt'/>
					</div>
					<div class="grid_7">
						&nbsp;&nbsp;个工作日
					</div>
					<div class="grid_7" style="text-align:left">
					</div>		
				</td>
			</tr>
			<tr>
				<td colspan="2" style="color: red;padding: 15px 0px 0px 60px;">
					注:办理的时限标准从事件受理之日起计算，红牌时限需大于黄牌时限
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<div style="padding: 15px 0px 5px 50px;color: #C9C9C9;">——<span style="font-weight: bolder;color:#353535;font-size:14px;padding:0px 10px 0px 10px">验证</span>————————————————————————————————————————————</div>		
				</td>
			</tr>
			<tr>
				<td style="padding: 0px 0px 0px 60px;">
					<div class='clearLine'>&nbsp;</div>
					<div class="grid_6 lable-right" >
						<label class="form-lb1">黄牌时限：</label>
					</div>
					<div class="grid_3">
						<input  type="text" id="administrativeOrgTimeLimitStandardYellowLimitVerify" maxlength="10" name="administrativeOrgTimeLimitStandard.yellowLimitVerify" value="${administrativeOrgTimeLimitStandard.yellowLimitVerify}" title="黄牌验证时限"
							class='form-txt'/>
					</div>
					<div class="grid_7">
						&nbsp;&nbsp;个工作日
					</div>
					<div class="grid_7" style="text-align:left">
					</div>
				</td>
				<td>
					<div class='clearLine'>&nbsp;</div>
					<div class="grid_6 lable-right" >
						<label class="form-lb1">红牌时限：</label>
					</div>
					<div class="grid_3">
						<input  type="text" id="administrativeOrgTimeLimitStandardRedLimitVerify" maxlength="10" name="administrativeOrgTimeLimitStandard.redLimitVerify" value="${administrativeOrgTimeLimitStandard.redLimitVerify}" title="红牌验证时限"
							class='form-txt' />
					</div>
					<div class="grid_7">
						&nbsp;&nbsp;个工作日
					</div>
					<div class="grid_7" style="text-align:left">
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="color: red;padding: 15px 0px 0px 60px;">
					注:验证的时限标准从事件结案之日起计算，红牌时限需大于黄牌时限
				</td>
			</tr>			
		</table> 
	
	<div class='clearLine'>&nbsp;</div>
	<div id="remark">
		<div class="grid_6 lable-right" >
			<label class="form-lb1">备注：</label>
		</div>
		<div class="grid_16 heightAuto">
			<textarea id="administrativeOrgTimeLimitStandardRemark" style="width:360px;height:60px" name="administrativeOrgTimeLimitStandard.remark" title="请输入备注"
			class="form-txt {maxlength:200,messages:{maxlength:$.format('备注最多需要输入{0}个字符')}}">${administrativeOrgTimeLimitStandard.remark}</textarea>
		</div>
	</div>
	<input type="hidden" name="administrativeOrgTimeLimitStandard.itemIds" id="itemNameIds">
</form>

</div>
<script type="text/javascript">

$(function(){
	$.typeSelect();

	function callback(){
	    var dfop = {
	    	ifAdd:'${mode=="add"}',
	    	ifEdit:'${mode=="edit"}'
	    }
	    TQ.maintainAdministrativeOrgTimeLimitStandardDlg(dfop)
	}
	$.cacheScript({
	    url:'/resource/getScript/issue/issueAccessConfig/maintainAdministrativeOrgTimeLimitStandardDlg.js',
	    callback: callback
	})
})

</script>