<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>
<style>
	.param{
		float:left;
		margin-top: 15px;
		margin-left: 36px;
	}
	.qrcodePath{
		padding: 10px;
	}
	.qrcodeName{
		height:65px;
		 width: 132px;
		 text-align:center;
		 word-wrap: break-word; 
		 word-break: normal; 
		 margin-left: 22px;
		 
	}
</style>


<ul class="qrcodelist">
	<s:iterator value="qrcodeList" var="qrcode">
		<li class="param">
			<s:if test="#qrcode.qrcodeUrl==null">
			 	<img src="${resource_path}/resource/images/nopic.jpg" />
			 </s:if>
			 <s:else>
			 	<img src="${qrcode.qrcodeUrl}"  class="qrcodeUrl"/>
			 </s:else>
			 <div class="qrcodeName">
				<span>${qrcode.qrcodeUrlName}</span>
			</div>
		</li>
	</s:iterator>

		
</ul>