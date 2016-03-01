update permissions set cname='查看事项' where ename='viewProcessJurisdictionsNeed';
update permissions set cname='查看事项' where ename='viewProcessJurisdictionsDone';
commit;

---其他人员---
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'其他人员','OTHERATTENTIONPERSONNELS','OTHERATTENTIONPERSONNEL','keyPerson',1,sysdate,'admin');


insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='OTHERATTENTIONPERSONNELS'),
'姓名','name',
'/baseinfo/otherAttentionPersonnelManage/dispatchOperate.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=otherAttentionPersonnel'||'&'||'id=',
'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
'refineSearch',sysdate,'admin','地址','address',
'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');
commit;