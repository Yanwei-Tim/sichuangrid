<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div  class="zjsSeekMainLay" style="margin-left:0px;">
	    <div class="zjsSeekMain">
          <strong class="logo"><img src="${resource_path }/resource/tqSearch/img/tylogo.png"></strong>
          <div class="zjsSeekNav">
          		<div id="tqSearchType">
		              <ul class="cf">
		              	<@pop.JugePermissionTag ename="peopleManageSystem">
		              		<li><a href="javascript:;" key="<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@POPULATION_KEY'/>"><@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@POPULATION_NAME'/></a></li>
		              	</@pop.JugePermissionTag>
		              	<@pop.JugePermissionTag ename="houseManageSystem">
		              		<li><a href="javascript:;" key="<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@HOUSE_KEY'/>"><@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@HOUSE_NAME'/></a></li>
		              	</@pop.JugePermissionTag>
		              	<@pop.JugePermissionTag ename="serviceWork">
		              		<li><a href="javascript:;" key="<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@ISSUE_KEY'/>"><@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@ISSUE_NAME'/></a></li>
		              	</@pop.JugePermissionTag>
		              </ul>
              </div>
              <div class="zjsSeekTextLay">
                <input id="tqSearchText" type="text" class="zjsSeekText" value="">
                <input id="tqSearchButton" type="button" class="zjsSeek" value="搜 索">
                <span id="tqSearchTitle" class="title">请输入姓名、身份证号……</span>
              </div>
          </div>
          <div  id="tqSearchField" class="hidden"></div>
      </div>
      <div class="zjsEntry">
       	<ul>
          <li><p><img src="${resource_path }/resource/tqSearch/img/personnel.png"/></p><strong>人员搜索</strong><span>人员搜索与关系图</span></li>
          <li><p><img src="${resource_path }/resource/tqSearch/img/peopleSearch.png"/></p><strong>事件搜索</strong><span>事件搜索与关系图</span></li>
          <li><p><img src="${resource_path }/resource/tqSearch/img/placeSearch.png"/></p><strong>场所搜索</strong><span>场所搜索与关系图</span></li>
          <li><p><img src="${resource_path }/resource/tqSearch/img/homeSearch.png"/></p><strong>房屋搜索</strong><span>房屋搜索与关系图</span></li>
          <li><p><img src="${resource_path }/resource/tqSearch/img/fileSearch.png"/></p><strong>文件搜索</strong><span>文件搜索与关系图</span></li>
        </ul>
      </div>
 <div>
<script type="text/javascript">
$(function(){
	TQSearch.initTqSearchPanel();
})
</script>
