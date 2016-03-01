<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div>
	<div id="orSearchFields">
		<input type="hidden" name="tqSearchVo.searchFields" value="name,code" title="文件名,文件编号">
	</div>
	<div id="andSearchFields" class="ScreenListLay">
		<div class="zjsScreenList">
			<div class="listName"><strong>文件类型：</strong></div>
			<input type="hidden" name="tqSearchVo.searchs.isRentalHouse">
		</div>
	</div>
	<div id="orderFields" class="zjsLeftTop">排序：
	  <a name="tqSearchVo.sortFields.updateDate" href="javascript:;">数据更新时间</a>
	  <a name="tqSearchVo.sortFields.createDate" href="javascript:;">录入时间</a>
	</div>
</div>
<script language="javascript">
$(document).ready(function(){
	$('.zjsScreenList').eq(0).show().siblings().hide();
	$('.zjsLeftTop').show();
});
</script>	