<table class="newTableList">
	<tbody>
		<tr>
			<td class="issueTitle">姓名</td>
			<td class="issueTitle">性别</td>
			<td class="issueTitle">身份证</td>
			<td class="issueTitle">刑法执行类别</td>
			<td class="issueTitle">社区矫正日期</td>
			<td class="issueTitle">所属网格</td>
			<td class="issueTitle">现居住地址</td>
       	</tr>
       	<#list list as people>
           <tr>
       			<td class="issueContable">${people.name}</td>
       			<td class="issueContable">${people.gender.displayName}</td>
       			<td class="issueContable">${people.idCardNo}</td>
       			<td class="issueContable">${people.executeType.displayName}</td>
       			<td class="issueContable">${people.rectifyStartDate?string("yyyy-MM-dd")}至${people.rectifyEndDate?string("yyyy-MM-dd")}</td>
       			<td class="issueContable">${people.organization.orgName}</td>
       			<td class="issueContable">${people.currentAddress!}</td>
       		</tr>
      	</#list>
	</tbody>
</table>

