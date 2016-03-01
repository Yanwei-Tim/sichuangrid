<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div class="content" align="center">
</br>
      <table align="center">
        <@s.iterator value="replyMessageList" var="type" >
        		<tr width="600" height="25" align="center">
        			<td width="300" align="center">
        				<b>${type.content}</b>
        			</td>
        			<td align="center">
        				<@s.date name="#type.createDate" format="yyyy-MM-dd HH:mm:ss" />
        			</td>
        		</tr>
        </@s.iterator>
       </table>
</div>