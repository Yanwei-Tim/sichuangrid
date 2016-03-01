<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<table class="tablelist">
	<tr>
		<td class="title"><label>发放时间：</label></td>
		<td class="content"><span>${(redEnvelopesPaymentRecords.send_time)!""}</span></td>
		<td class="title"><label>发放类型：</label></td>
		<td class="content"><span>${(redEnvelopesPaymentRecords.send_type)!""}</span></td>
	</tr>
	<tr>
		<td class="title"><label>状态：</label></td>
		<td class="content"><span>${(redEnvelopesPaymentRecords.status)!""}</span></td>
		<td class="title"><label>红包类型：</label></td>
		<td class="content"><span>${(redEnvelopesPaymentRecords.hb_type)!""}</span></td>
	</tr>
	<tr>
		<td class="title"><label>appid：</label></td>
		<td class="content"><span>${(redEnvelopesPaymentRecords.wxappid)!""}</span></td>
		<td class="title"><label>发放对象：</label></td>
		<td class="content"><span>${(redEnvelopesPaymentRecords.fanName)!""}</span></td>
	</tr>
	<tr>
		<td class="title"><label>红包单号：</label></td>
		<td class="content"><span>${(redEnvelopesPaymentRecords.detail_id)!""}</span></td>
		<td class="title"><label>商户订单单号：</label></td>
		<td class="content"><span>${(redEnvelopesPaymentRecords.mch_billno)!""}</span></td>
	</tr>
	<tr>
		<td class="title"><label>红包个数：</label></td>
		<td class="content"><span>${(redEnvelopesPaymentRecords.total_num)!""}</span></td>
		<td class="title"><label>红包金额(元)：</label></td>
		<td class="content"><span><#if redEnvelopesPaymentRecords.total_amount??>${((redEnvelopesPaymentRecords.total_amount)/100)!""}<#else>${(redEnvelopesPaymentRecords.total_amount)!}</#if></span></td>
	</tr>
	<tr>
		<td class="title"><label>退款时间：</label></td>
		<td class="content"><span>${(redEnvelopesPaymentRecords.refund_time)!""}</span></td>
		<td class="title"><label>退款金额：</label></td>
		<td class="content"><span><#if redEnvelopesPaymentRecords.total_amount??>${((redEnvelopesPaymentRecords.refund_amount)/100)!""}<#else>${(redEnvelopesPaymentRecords.refund_amount)!}</#if></span></td>
	</tr>
</table>
