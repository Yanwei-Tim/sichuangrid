<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div>
	<div id="orSearchFields">
		<input type="hidden" name="tqSearchVo.searchFields" value="name,address" title="场所名称,场所地址">
	</div>
	<div id="andSearchFields" class="ScreenListLay">
		<div class="zjsScreenList">
			<div class="listName"><strong>场所类型：</strong></div>
			<div class="listLay"><span class="on">全部</span><span></span><span>学校</span><span>医院</span><span>网吧</span><a href="javascript:;" id="showAll">展开</a><a href="javascript:;" id="hideAll" style="display:none;">收缩</a></div>
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