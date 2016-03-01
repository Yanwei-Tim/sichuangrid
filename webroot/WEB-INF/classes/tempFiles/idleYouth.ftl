<table class="newTableList">
	<tbody>
		<tr>
			<td class="issueTitle">姓名</td>
			<td class="issueTitle">性别</td>
			<td class="issueTitle">身份证</td>
			<td class="issueTitle">人员类型</td>
			<td class="issueTitle">所属网格</td>
			<td class="issueTitle">现居住地址</td>
       	</tr>
       	<#list list  as people>
           <tr>
       			<td class="issueContable">${people.name}</td>
       			<td class="issueContable">${people.gender.displayName}</td>
       			<td class="issueContable">${people.idCardNo}</td>
       			<td class="issueContable">
       				<#list people.staffType  as staffType>${(staffType.displayName)!}<#if staffType_has_next>、</#if></#list>
       			</td>
       			<td class="issueContable">${people.organization.orgName}</td>
       			<td class="issueContable">${people.currentAddress!}</td>
       		</tr>
      	</#list>
	</tbody>
</table>

