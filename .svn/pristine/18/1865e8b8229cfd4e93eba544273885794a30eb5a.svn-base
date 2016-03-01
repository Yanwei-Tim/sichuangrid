<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/> 

<div class="container container_24" >
	<div id="positiveInfoRecord" class="container container_24">
		<form id="positiveInfoRecordForm" method="post" action="${path}/plugin/taskListManage/positiveInfoRecord/addPositiveInfoRecord.action"  >
			<@pop.token />
			<input type="hidden" name="mode" id="mode" value="${(mode)!}" />
			<input type="hidden" name="addFlag" id="addFlag" value="${(addFlag)!}" />
			<input type="hidden" name="positiveInfoRecord.positiveInfoId" id="positiveInfoId" value="${(population.id)!}" />
			<input type="hidden" name="positiveInfoRecord.organization.id" id="orgId" value="${(positiveInfoRecord.organization.id)!}" />
			<input type="hidden" name="receipt.id" id="receiptId" value="${(positiveInfoRecord.id)!}" />
			<input type="hidden" name="receipt.objectType" id="objectType" value="<@s.property value='@com.tianque.plugin.taskList.constants.Constants@POSITIVEINFORECORD_KEY' />" />
			<input id="memberId" name="serviceRecord.memberId" type="hidden" value="${(serviceRecordVo.memberId)!}" />
			
			<div class="grid_5 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">姓名：</label>
			</div>
			<@s.if test="'add'.equals(mode)">
			<div class="grid_7">
				<input type="text" name="positiveInfoRecord.name" id="positiveInfoRecordName" value="${(population.name)!}" <@s.if test="!'false'.equals(addFlag)">readOnly</@s.if> <@s.if test="!'add'.equals(mode)">readOnly</@s.if>  class="form-txt {required:true,messages:{required:'姓名不能为空'}}" maxlength="10" />
			</div>
			</@s.if>
			<@s.else>
			<div class="grid_7">
				<input type="text" name="positiveInfoRecord.name" id="positiveInfoRecordName" value="${(positiveInfoRecord.name)!}"  <@s.if test="!'add'.equals(mode)">readOnly</@s.if>  class="form-txt {required:true,messages:{required:'姓名不能为空'}}" maxlength="10" />
			</div>
			</@s.else>
			<div class="grid_5 lable-right">
				<label class="form-lbl">身份证号码：</label>
			</div>
			<@s.if test="'add'.equals(mode)">
			<div class="grid_7">
				<input  name="positiveInfoRecord.idCard" value="${(population.idCardNo)!}" <@s.if test="!'false'.equals(addFlag)">readOnly</@s.if> <@s.if test="!'add'.equals(mode)">readOnly</@s.if> class="form-txt {idCard:true,messages:{idCard:$.format('请输入一个合法的身份证号码')}}" />
			</div>
			</@s.if>
			<@s.else>
			<div class="grid_7">
			<input  name="positiveInfoRecord.idCard" value="${(positiveInfoRecord.idCard)!}"  <@s.if test="!'add'.equals(mode)">readOnly</@s.if> class="form-txt {idCard:true,messages:{idCard:$.format('请输入一个合法的身份证号码')}}" />
			</div>
			</@s.else>
			<div class='clearLine'>&nbsp;</div>
				
			<div class="grid_5 lable-right">
				<label class="form-lbl">电话号码：</label>
			</div>
			<@s.if test="'add'.equals(mode)">
			<div class="grid_7"> 
			<input name="positiveInfoRecord.phone" value="${(population.mobileNumber)!}"  <@s.if test="!'add'.equals(mode)">readOnly</@s.if> class="form-txt {phoneAndMobile:true,messages:{phoneAndMobile:$.format('请输入以固定电话：028-87653333或者手机：15102888888为格式的号码')}}" />
			</div>
			</@s.if>
			<@s.else>
			<div class="grid_7">
			 <input name="positiveInfoRecord.phone" value="${(positiveInfoRecord.phone)!}"  <@s.if test="!'add'.equals(mode)">readOnly</@s.if> class="form-txt " />
			</div>
			</@s.else>
			<div class="grid_5 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">家属联系方式：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="positiveInfoRecord.familyPhone" id="familyPhone" value="${(positiveInfoRecord.familyPhone)!}" <@s.if test="!'add'.equals(mode)">readOnly</@s.if>  class="form-txt <@s.if test="'add'.equals(mode)">{required:true,phoneAndMobile:true,messages:{required:'家属联系方式不能为空',phoneAndMobile:$.format('请输入以固定电话：028-87653333或者手机：15102888888为格式的号码')}}</@s.if>" />
			</div>	
			
			<div class='clearLine'>&nbsp;</div>
			
			<div class="grid_5 lable-right">
				<label class="form-lbl">帮扶人员：</label>
			</div>
			<div class="grid_19 heightAuto" style="margin-top:5px;">
				<input type="text" id="helpPeople" name="positiveInfoRecord.helpPeople"  value="${(positiveInfoRecord.helpPeople)!}" class='form-txt' <@s.if test="!'add'.equals(mode)">readOnly</@s.if><@s.else>style="height:70px;"</@s.else>/>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_5 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">生活来源：</label>
			</div>
			<div class="grid_7">
				<select id="livelihoodWay" name="positiveInfoRecord.livelihoodWay.id" class="form-txt {required:true,messages:{required:'请输入生活来源'}}" <@s.if test="!'add'.equals(mode)">disabled</@s.if>>
					<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@DRUGGY_LIFE_RESOURCE" defaultValue="${(positiveInfoRecord.livelihoodWay.id)!}"/>
				</select>
			</div>
			<div class='clearLine'>&nbsp;</div>
			
			
			<div class="grid_5 lable-right">
				<em class="form-req">*</em>
				<label class="form-lb1">时间：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="positiveInfoRecord.recordDate" id="recordDate" value="${(positiveInfoRecord.recordDate?string('yyyy-MM-dd HH:mm:ss'))!}"  class="form-txt" readOnly/>
			</div>
			
			<div class='clearLine'>&nbsp;</div>
			
			<div class="grid_5 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">地点：</label>
			</div>
			<@s.if test="'add'.equals(mode)">
			<div class="grid_19">
				<input type="text" name="positiveInfoRecord.address" id="address" value="${(population.currentAddress)!}"  <@s.if test="!'add'.equals(mode)">readOnly</@s.if> class="form-txt {required:true,messages:{required:'地点不能为空'}}" maxlength="50" />
			</div>
			</@s.if>
			<@s.else>
			<div class="grid_19">
				<input type="text" name="positiveInfoRecord.address" id="address" value="${(positiveInfoRecord.address)!}"  <@s.if test="!'add'.equals(mode)">readOnly</@s.if> class="form-txt {required:true,messages:{required:'地点不能为空'}}" maxlength="50" />
			</div>
			</@s.else>	
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_6 lable-right" >
				<em class="form-req">*&nbsp;</em>
				<label class="form-lbl">有无异常：</label>
			</div>
			<div class="grid_15 " >
				<input type="radio" name="positiveInfoRecord.hasException" checked="checked" value="1" onclick="s(this.value)" <@s.if test="positiveInfoRecord.hasException==1">checked="checked"</@s.if> <@s.if test="!'add'.equals(mode)">disabled="disabled"</@s.if>/>有 <input name="positiveInfoRecord.hasException" type="radio" onclick="s(this.value)" <@s.if test="positiveInfoRecord.hasException==0">checked="checked"</@s.if> <@s.if test="!'add'.equals(mode)">disabled="disabled"</@s.if> value="0"/>无
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_5 lable-right" id="a">
			 <em class="form-req">*&nbsp;</em>
				<label>异常情况：</label>
			</div>
			<div class="grid_19 heightAuto" id="b">
				<textarea name="positiveInfoRecord.exceptionSituationInfo" style="height:70px;width:99%;" <@s.if test="!'add'.equals(mode)">readOnly</@s.if> maxlength="250" class='form-txt {maxlength:250,messages:{maxlength:"异常情况不能超过250个字符"}}'>${(positiveInfoRecord.exceptionSituationInfo)!}</textarea>
			</div>
			
			<div class='clearLine'>&nbsp;</div>
			
			<div class="grid_5 lable-right">
				<label class="form-lbl">备注：</label>
			</div>
			<div class="grid_19 heightAuto">
				<textarea type="text" style="height:70px;width:99%" name="positiveInfoRecord.mark" id="mark" <@s.if test="!'add'.equals(mode)">readOnly</@s.if> class="form-txt dialogtip" maxlength="300">${(positiveInfoRecord.mark)!}</textarea>
			</div>	
			<div class='clearLine'>&nbsp;</div>
			
			<select id="attachFileNames" name="positiveInfoRecord.attachFileNames" multiple="multiple" style="width:200px;display:none"></select>

			<div id="signDiv" style="display:none;">
				<div class="grid_5 lable-right">
					<label class="form-lbl">所属网格：</label>
				</div>
				<div class="grid_19">
					<input type="text" style="width:99%" name="positiveInfoRecord.organization.fullOrgName" id="fullOrgName" value="${(positiveInfoRecord.organization.fullOrgName)!}" readOnly  class="form-txt" />
				</div>
				<div class='clearLine'>&nbsp;</div>
				
				<div class="grid_5 lable-right">
					<label class="form-lbl">网格员联系电话：</label>
				</div>
				<div class="grid_19">
					<input type="text" style="width:99%" name="positiveInfoRecord.gridMemberPhone" id="gridMemberPhone" value="${(positiveInfoRecord.gridMemberPhone)!}" readOnly class="form-txt" />
				</div>
				<div class='clearLine'>&nbsp;</div>
				
				<div class="grid_5 lable-right">
					<label class="form-lbl">签收人：</label>
				</div>
				<div class="grid_7">
					<input type="text" name="receipt.signMemberName" id="signMemberName" value="${(positiveInfoRecord.signMemberName)!}" readOnly class="form-txt dialogtip" />
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">签收时间：</label>
				</div>
				<div class="grid_7">
					<input type="text" name="receipt.signDate" id="signDate" value="${(positiveInfoRecord.signDate?string('yyyy-MM-dd HH:mm:ss'))!}"  class="form-txt dialogtip" readOnly />
				</div>
				<div class='clearLine'>&nbsp;</div>
				
				<div class="grid_5 lable-right">
					<label class="form-lbl">签收意见：</label>
				</div>
				<div class="grid_19 heightAuto">
					<textarea type="text" style="height:70px;width:99%" name="receipt.attitude" id="attitude"  class="form-txt dialogtip" maxlength="200" >${(positiveInfoRecord.attitude)!}</textarea>
				</div>	
			</div>
			<input name="isSubmit" id="isSubmit" type="hidden" value="">
		</form>
		<div class="filePanel">
			<div class="grid_5 lable-right">
				<label class="form-lbl">附件上传：</label>
			</div>
			<div id="attachFilesDiv" class="grid_19 heightAuto" style="width:77%;">
				<@s.if test='"add".equals(mode)'>
				<input id="custom_file_upload" name="uploadFile" type="file" />
				</@s.if>
				<div id="custom-queue" style="clear:both;border:1px solid #ccc;overflow-x:hidden;height:100px;"></div>
			</div>
		</div>
  	</div>
</div>


<script type="text/javascript">
//电话号码
jQuery.validator.addMethod("phoneAndMobile", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var mobile = /^(1[3|4|5|7|8][0-9])+\d{8}$/;
	var length = value.length;
	if(length == 11 && mobile.test(value)){return true;}
	var phone = /^([0-9]{3,4}-)+[0-9]{7,8}$/;	
	if (value.match(phone)==null) {return false;}
	return true
});
$(document).ready(function(){
	setFormDiv();
	$("#positiveInfoRecordForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			emptyFormDivBefSubmit();
			$(form).ajaxSubmit({
				async:false,
				success:function(data){
					if(data == true){
						
					}else if(!data.id){
           	 			$.messageBox({ 
							level: "error",
							message:data
			 			});
            			return;
					}
	                if("add"==$("#mode").val()){
	                	 $.messageBox({message:"社区刑释人员记录新增成功！"});
	                	 $("#positiveInfoRecordDialog").dialog("close");
	                }
	                if("sign"==$("#mode").val()){
	                	 $.messageBox({message:"社区刑释人员记录签收成功！"});
	                	 $("#positiveInfoRecordDialog").dialog("close");
	                }
	                $("#positiveInfoRecordList").trigger("reloadGrid");
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
		},
		messages:{
		}
	});
	
	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames"
	});

	<@s.if test='!"add".equals(mode)'>	
		<@s.if test="positiveInfoRecord.taskListAttachFiles!=null && positiveInfoRecord.taskListAttachFiles.size > 0">
			<@s.iterator value="positiveInfoRecord.taskListAttachFiles" var="file">
			    var itemHtml = '<div id="item'+$(this).attr("id")+'${(file.id)!}'
					+'" class="uploadifyQueueItem completed">'+'<a href="'+'${path}/plugin/taskListManage/common/downLoadAttachFile.action?attachFileId=${(file.id)!}'
					+'" target="_blank"><span class="fileName">'+'${(file.fileName)!}'+'</span></a></div>';
			    $("#custom-queue").append($(itemHtml));
			    $("<option>").attr("id","${file.id}").val("${file.fileName}").attr("selected",true).appendTo($("#attachFileNames"));
			</@s.iterator>
		</@s.if>	
	</@s.if>
	<@s.if test='"add".equals(mode)'>	
	$("#helpPeople").personnelComplete({
		multiple: true,
		height:70,
		param:"serviceTeamMemberVo.name",
		url:"/plugin/serviceTeam/serviceRecord/findServiceMembers.action",
		postDataFunction: function(){
			return {
				"serviceTeamMemberVo.org.id":selectConfigTaskOrg(),
				"serviceTeamMemberVo.memberId":$("#memberId").val(),
				"serviceTeamMemberVo.searchType":"allSearch"
			};
		},
		select: function(data){
				$("#memberId").val(data.split("-")[0]);
		},
		itemClose: function(data){
				if($("#helpPeople").val()==""){
					$("#memberId").val("");
				}
		}
	});	
	</@s.if>
});


function setFormDiv(){
	var mode = "${(mode)!}";
	if(mode == "add"){
		$("#signDiv").hide();
		$("#orgId").val(selectConfigTaskOrg());
	}
	if(mode == "sign"){
		$("#signDiv").show();
		$("#positiveInfoRecordForm").attr("action","${path}/plugin/taskListManage/common/signRecord.action");
		$("#signMemberName").val("<@s.property value='@com.tianque.core.util.ThreadVariable@getUser().getName()' />");
	}
}

function emptyFormDivBefSubmit(){
	var mode = "${(mode)!}";
	if(mode == "add"){
		$("#signDiv").find("input").val("");
	}
}

 function s(va) {
   if(va=="1"){
       document.getElementById('a').style.display='block';
       document.getElementById('b').style.display='block';
      }else if(va="0"){
       document.getElementById('a').style.display='none';
        document.getElementById('b').style.display='none';
   }
}
</script>