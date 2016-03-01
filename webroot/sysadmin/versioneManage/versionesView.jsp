<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<style type="text/css">
.imageC pre img{width:200px;height:200px;}

</style>
<table class="tablelist">
	<tr>
		<td class="title"><label>版本号</label></td>
		<td colspan="3" class="content" >
			<span id="">${versions.versionId}</span>
		</td>
	</tr>
	<tr>
		<td class="title">版本说明</td>
		<td  colspan="3" class="content" style="padding-top: 8px; padding-bottom: 5px;">
			<span id="" class="imageC"><pre>${versions.versionContent}</pre></span>
		</td>
	</tr>
</table>

