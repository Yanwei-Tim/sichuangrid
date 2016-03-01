<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<table width="200" class="tablelist">

<tr>

    <td class="title"><label>发现地点</label></td>
    <td class="content">${(hiddenDanger.address)!}</td>
     <td class="title"><label>发现时间</label></td>
    <td class="content"><@s.date name="hiddenDanger.discoverDate" format="yyyy-MM-dd HH:mm:ss" /></td>
  </tr>
   <tr>
   <td class="title"><label>异常情况</label></td>
    <td class="content">${(hiddenDanger.exceptionSituation)!}</td>
    <td class="title"><label>异常类型</label></td>
    <td class="content"><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@DANGER_EXCEPTION_TYPE" defaultValue="${(hiddenDanger.exceptionType.id)!}" /></td>
  </tr>
    
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="hiddenDangerOrgName">${(hiddenDanger.organization.orgName)!}</td>
  </tr>
  
  <tr>
    <td class="title"><label>网格员电话</label></td>
    <td colspan="3" class="content" id="hiddenDangerOrgName">${(hiddenDanger.telephone)!}</td>
  </tr>
  
   <tr>
    <td class="title"><label>备注</label></td>
    <td class="content" colspan="3">${(hiddenDanger.remark)!}</td>
  </tr>
   <tr>
   <td class="title"><label>签收人</label></td>
    <td class="content" colspan="3">${(hiddenDanger.signUserName)!}</td>
  </tr>
   <tr>
    <td class="title"><label>签收意见</label></td>
    <td class="content" colspan="3">${(hiddenDanger.advice)!}</td>
  </tr>
  <tr>
    <td class="title"><label>签收时间</label></td>
    <td class="content" colspan="3"><@s.date name="hiddenDanger.signDate" format="yyyy-MM-dd HH:mm:ss" /></td>
  </tr>
  
  
 </table>
   
