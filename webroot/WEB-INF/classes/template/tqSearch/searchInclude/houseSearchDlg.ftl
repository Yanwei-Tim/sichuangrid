<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div>
	<div id="orSearchFields">
		<input type="hidden" name="tqSearchVo.searchFields" value="address,houseCode,name,certificateNumbe" title="房屋地址，房屋编号，产权人姓名，产权人证件号码">
	</div>
	<div id="andSearchFields" class="ScreenListLay">
		<div class="zjsScreenList permission">
			<div class="listName"><strong>房屋类型：</strong></div>
			<div class="listLay"><span class="on">全部</span>
			<@pop.JugePermissionTag ename="viewRentalHouse">
				<span value="1">出租房</span>
			</@pop.JugePermissionTag>
			<@pop.JugePermissionTag ename="viewActualHouse">
				<span value="0">非出租房</span>
			</@pop.JugePermissionTag>
			<a href="javascript:;" id="showAll">展开</a><a href="javascript:;" id="hideAll" style="display:none;">收缩</a></div>
			<input type="hidden" name="tqSearchVo.searchs.isRentalHouse">
		</div>
		<div class="zjsScreenList">
			<div class="listName"><strong>产权人类型：</strong></div>
			<div class="listLay">
				<span class="on">全部</span><@pop.PropertyDictShowTag jspNode="<span value='{id}'>{displayName}</span>" domainName="@com.tianque.domain.property.PropertyTypes@PROPERTY_TYPES"/>
			</div>
			<input type="hidden" name="tqSearchVo.searchs.propertyTypes">
		</div>
		<div class="zjsScreenList">
			<div class="listName"><strong>出租房类别：</strong></div>
			<div class="listLay">
				<span class="on">全部</span><@pop.PropertyDictShowTag jspNode="<span value='{id}'>{displayName}</span>" domainName="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_TYPE"/>
			</div>
			<input type="hidden" name="tqSearchVo.searchs.type">
		</div>
		<div class="zjsScreenList">
			<div class="listName"><strong>隐患程度：</strong></div>
			<div class="listLay">
				<span class="on">全部</span><@pop.PropertyDictShowTag jspNode="<span value='{id}'>{displayName}</span>" domainName="@com.tianque.domain.property.PropertyTypes@HIDDEN_TROUBLE_LEVEL"/>
			</div>
			<input type="hidden" name="tqSearchVo.searchs.hiddenDangerLevel">
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
	//权限
	if($(".zjsScreenList.permission span").length<3){
		$(".zjsScreenList.permission span:eq(0)").attr("value",$(".zjsScreenList.permission span:eq(1)").attr("value"));
		$(".zjsScreenList.permission span:eq(0)").click();
	}
});
</script>	