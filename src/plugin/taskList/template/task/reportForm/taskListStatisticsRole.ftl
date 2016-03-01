<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<style>
.fontClass{
color: red;
font-size: medium;
font-weight: bold;
}
</style>
<div id="dialog-form" class="container container_20">
<img  src="${path }/resource/images/taskListRole.png">
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
   			<label class="form-lb1 fontClass">人数：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">表示截止当前选择时间辖区人员总数，只记录了人口信息里面没有注销的人数。</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">走访人数：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">当前选择时间段内走访过的人员总数。</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">走访次数：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">根据时间条件来显示，本周、上周、每个月对辖区的特殊人群的走访次数记录。</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">走访比例：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">当前选择时间段内，已走访的人数 除以 下辖的人数。</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
   			<label class="form-lb1 fontClass">异常：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">对于走访时候发现有异常的有多少条。</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
   			<label class="form-lb1 fontClass">签收：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">对于有异常的问题签收了多少。</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
   			<label class="form-lb1 fontClass">回复：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">对于签收以后回复了多少。</label>
</div>
</div>	