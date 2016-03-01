/*==============================================================*/
/* Sequence: s_peopleAspirations   民生诉求序列	                */
/*==============================================================*/
create sequence s_peopleAspirations
 increment by 1
 start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;
 
 /*==============================================================*/
/* Table: peopleAspirations   民生诉求表	                        */
/*==============================================================*/
 create table peopleAspirations(
 	id					NUMBER(10)                      not null,
 	orgId				NUMBER(10)                      not null,
 	orgInternalCode 	VARCHAR2(32)                    not null,
 	serialNumber		VARCHAR2(30)                    not null,
 	name				VARCHAR2(60)					not null,
 	idCardNo			VARCHAR2(60)					not null,
 	mobileNumber        VARCHAR2(15),
 	gender              NUMBER(10),
 	birthDay            DATE,
 	permanentAddress	VARCHAR2(150),
 	responseGroupNo		NUMBER(10),
 	isPartyMember		NUMBER(1)                     	default 0,
 	position			NUMBER(10),
 	appealHumanType		NUMBER(10),
 	appealContent		CLOB							not null,
 	itemCategory		NUMBER(10)						not null,
 	itemCategorySub		NUMBER(10)						not null,
 	serverContractor 	VARCHAR2(60),
 	serverTelephone		VARCHAR2(30),
 	serverJob			VARCHAR2(60),
 	serverUnit			VARCHAR2(150),
 	createUser          VARCHAR2(60) 					not null,
	updateUser          VARCHAR2(60),
	createDate          DATE         					not null,
	updateDate          DATE,
	createTableType		NUMBER(10),
	occurOrgId			NUMBER(10)						not null,
	occurOrgInternalCode VARCHAR2(32)                   not null,
	gridNo				VARCHAR2(60),
	bookingUnit 		VARCHAR2(60),
	registrant			VARCHAR2(32)                    not null,
	registrationTime	DATE,
	constraint pkPeopleAspirations primary key (ID)
 );
 -- Add comments to the table 
comment on table peopleAspirations
  is '民生诉求表';
-- Add comments to the columns 
comment on column peopleAspirations.id
  is '民生诉求id'; 
comment on column peopleAspirations.orgId
  is '所属网格'; 
comment on column peopleAspirations.orgInternalCode
  is '所属网格编号'; 
comment on column peopleAspirations.serialNumber
  is '编号';
comment on column peopleAspirations.name
  is '姓名';
comment on column peopleAspirations.idCardNo
  is '身份证';   
comment on column peopleAspirations.mobileNumber
  is '联系电话';     
comment on column peopleAspirations.gender
  is '性别';  
comment on column peopleAspirations.birthDay
  is '出生年月'; 
comment on column peopleAspirations.permanentAddress
  is '常住地址';   
comment on column peopleAspirations.responseGroupNo
  is '反应群体人数';     
comment on column peopleAspirations.isPartyMember
  is '是否党员，0否 1是';
comment on column peopleAspirations.position
  is '职业或身份'; 
comment on column peopleAspirations.appealHumanType
  is '诉求人类别'; 
comment on column peopleAspirations.appealContent
  is '主要愿望或诉求';  
comment on column peopleAspirations.itemCategory
  is '项目类别(大类)';    
comment on column peopleAspirations.itemCategorySub
  is '项目类别(子类)';
comment on column peopleAspirations.serverContractor
  is '服务联系人';
comment on column peopleAspirations.serverTelephone
  is '服务联系电话';  
comment on column peopleAspirations.serverJob
  is '服务职务';  
comment on column peopleAspirations.serverUnit
  is '服务联系人单位'; 
comment on column peopleAspirations.createUser
  is '创建人';
comment on column peopleAspirations.updateUser
  is '修改人';
comment on column peopleAspirations.createDate
  is '创建时间';
comment on column peopleAspirations.updateDate
  is '修改时间';
comment on column peopleAspirations.createTableType
  is '建表类型';
comment on column peopleAspirations.occurOrgId
  is '发生网格id';  
comment on column peopleAspirations.occurOrgInternalCode
  is '发生网格编号';
comment on column peopleAspirations.gridNo
  is '网格号'; 
comment on column peopleAspirations.bookingUnit
  is '登记单位'; 
comment on column peopleAspirations.registrant
  is '登记人'; 
comment on column peopleAspirations.registrationTime
  is '登记时间'; 
  
  
/*字典项*/
  /*职业或身份*/
insert into propertydomains(id,domainname,systemsensitive,systemrestrict)
values(s_propertyDomains.NEXTVAL,'职业或身份',0,'');

insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='职业或身份'),0,1, '教师', 'js', 'jiaoshi','admin',sysdate);
 
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='职业或身份'),1,2, '医生', 'ys', 'yisheng','admin',sysdate);

insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='职业或身份'),2,3, '公务人员', 'gwry', 'gongwurenyuan','admin',sysdate);

insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='职业或身份'),3,4, '企业人员', 'qyry', 'qiyerenyuan','admin',sysdate);

insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='职业或身份'),4,5, '学生', 'xs', 'xuesheng','admin',sysdate);

insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='职业或身份'),5,6, '城镇居民', 'czjm', 'chengzhenjumin','admin',sysdate);

insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='职业或身份'),6,7, '在家农民', 'zjnm', 'zaijianongmin','admin',sysdate);

insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='职业或身份'),7,8, '外出农民', 'wcnm', 'waichunongmin','admin',sysdate);

insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='职业或身份'),8,9, '其他', 'qt', 'qita','admin',sysdate);

/*诉求人类别*/
insert into propertydomains(id,domainname,systemsensitive,systemrestrict)
values(s_propertyDomains.NEXTVAL,'诉求人类别',0,'');

insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='诉求人类别'),0,1, '反应人', 'fyr', 'fanyingren','admin',sysdate);
 
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='诉求人类别'),1,2, '反应群体代表', 'fyqtdb', 'fanyingquntidaibiao','admin',sysdate);

/*项目类别(大类)*/
insert into propertydomains(id,domainname,systemsensitive,systemrestrict)
values(s_propertyDomains.NEXTVAL,'项目类别(大类)',0,'');

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(大类)'),0,1,'水利类','sll','shuililei','admin',sysdate);
  
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(大类)'),1,2,'交通类','jtl','jiaotonglei','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(大类)'),2,3,'能源类','nyl','nengyuanlei','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(大类)'),3,4,'教育科技类','jykjl','jiaoyukejilei','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(大类)'),4,5,'文体类','wtl','wentilei','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(大类)'),5,6,'医疗卫生','ylws','yiliaoweisheng','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(大类)'),6,7,'劳动和社会保障','ldhshbz','laodongheshehuibaozhang','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(大类)'),7,8,'环境保护类','hjbhl','huanjingbaohulei','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(大类)'),8,9,'城乡规划建设管理类','cxghjsgll','chengxiangguihuajiansheguanli','admin',sysdate);
  
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(大类)'),9,10,'农业类','nyl','nongyelei','admin',sysdate);
   
  
  
/*项目类别(小类)*/
insert into propertydomains(id,domainname,systemsensitive,systemrestrict)
values(s_propertyDomains.NEXTVAL,'项目类别(子类)',0,'');

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),0,1,'饮水工程','ysgc','yinshuigongcheng','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),0,2,'山坪塘','spt','shanpingtang','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),0,3,'蓄水池','xsc','xushuichi','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),0,4,'水窖','sj','shuijiao','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),0,5,'提灌站','tgz','tiguanzhan','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),0,6,'渠道','qd','qudao','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),0,7,'中小河流治理','zxhlzl','zhongxiaoheliuzhili','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),0,8,'水利-其他','slqt','shuiliqita','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),1,9,'道路建设','dljs','daolujianshe','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),1,10,'道路维护','dlwh','daoluweihu','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),1,11,'桥梁建设','qljs','qiaoliangjianshe','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),1,12,'桥梁维护','qlwh','qiaoliangweihu','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),1,13,'安全设施工程','aqssgc','anquansheshigongcheng','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),1,14,'客运','ky','keyun','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),1,15,'交通-其他','jtqt','jiaotongqita','admin',sysdate);
  
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),2,16,'电力','dl','dianli','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),2,17,'沼气','zq','zhaoqi','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),2,18,'太阳能','tyn','taiyangneng','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),2,19,'石油液化气','syyhq','shiyouyehuaqi','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),2,20,'汽柴油','qcy','qicaiyou','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),2,21,'煤碳','mt','meitan','admin',sysdate);
  
  
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),3,22,'教育设施建设','jyssjs','jiaoyusheshijianshe','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),3,23,'就学','jx','jiuxue','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),3,24,'科技','kj','keji','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),3,25,'科协','kx','kexie','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),3,26,'教育-其它','jyqt','jiaoyuquta','admin',sysdate);
                                                
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),4,27,'广播电视','gbds','guangbodianshi','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),4,28,'旅游','ly','lvyou','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),4,29,'文化','wh','wenhua','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),4,30,'体育','ty','tiyu','admin',sysdate);
                                                                                                       
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),5,31,'医疗服务','ylfw','yiliaofuwu','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),5,32,'食品卫生','spws','shipinweisheng','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),5,33,'公共卫生服务','ggwsfw','gonggongweishengfuwu','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),5,34,'新农合','xnh','xinnonghe','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),5,35,'服务能力建设','fwnljs','fuwunenglijianshe','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),5,36,'医疗-其他','ylqt','yiliaoqita','admin',sysdate);
                                                      
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),6,37,'就业','jy','jiuye','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),6,38,'社会保障','shbz','shehuibaozhang','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),6,39,'农民工工资','nmggz','nongmingonggongzi','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),6,40,'社保-其他','sbqt','shebaoqita','admin',sysdate);
                                                                                                                                                                                                                                                                                                                                      
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),7,41,'工业企业','gyqy','gongyeqiye','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),7,42,'农村环保','nchb','nongcunhuanbao','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),7,43,'生活污染源','shwry','shenghuowuranyuan','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),7,44,'电磁辐射','dcfs','diancifushe','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),7,45,'环境-其他','hjqt','huanjingqita','admin',sysdate);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),8,46,'城市管理','csgl','chengshiguanli','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),8,47,'城市街道','csjd','chengshijiedao','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),8,48,'市政公共设施','szggss','shizhenggonggongsheshi','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),8,49,'市政环卫','szhw','shizhenghuanwei','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),8,50,'村镇规划建设管理','czghjsgl','cunzhenjguihuajiansheguanli','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),8,51,'住房保障','zfbz','zhufangbaozhang','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),8,52,'建筑质量安全','jzzlaq','jianzhuzhilianganquan','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),8,53,'城乡-其他','cxqt','chengxiangqita','admin',sysdate);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),9,54,'农业产业化','nycyh','nongyechanyehua','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),9,55,'田间工程','tjgc','tianjiangongcheng','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),9,56,'农业培训','nypx','nongyepeixun','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='项目类别(子类)'),9,57,'农业-其它','nyqt','nongyeqita','admin',sysdate);
  

  /*建表类型*/
insert into propertydomains(id,domainname,systemsensitive,systemrestrict)
values(s_propertyDomains.NEXTVAL,'建表类型',0,'');

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='建表类型'),0,1,'新建','xj','xinjian','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='建表类型'),1,2,'上年接转','snjz','shangnianjiezhuan','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='建表类型'),2,3,'其他台账转入','qttzzr','qitataizhangzhuanru','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='建表类型'),3,4,'上级主管部门和市级领导班子有关领导交办(市级部门选填)','sjbmxt','shijibumenxuantian','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='建表类型'),4,5,'市委市政府及市级领导班子有关领导交办(乡镇选填)','xzxt','xiangzhenxuantian','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='建表类型'),5,6,'市人大议案、建议、意见交办','srdya','shirendayian','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='建表类型'),6,7,'市政协提案、建议、意见交办','szxta','shizhengxietian','admin',sysdate);


-- 添加字段
alter table peopleAspirations add occurOrgId NUMBER(10) not null;
comment on column peopleAspirations.occurOrgId
  is '发生网格id';
 
alter table peopleAspirations add occurOrgInternalCode VARCHAR2(32) not null;
comment on column peopleAspirations.occurOrgInternalCode
  is '发生网格编号';
  
alter table peopleAspirations add gridNo VARCHAR2(60);  
comment on column peopleAspirations.gridNo
  is '网格号'; 
  
alter table peopleAspirations add bookingUnit VARCHAR2(60);   
comment on column peopleAspirations.bookingUnit
  is '登记单位'; 
  
alter table peopleAspirations add registrant VARCHAR2(32) not null; 
comment on column peopleAspirations.registrant
  is '登记人'; 
  
alter table peopleAspirations add registrationTime DATE;   
comment on column peopleAspirations.registrationTime
  is '登记时间'; 

commit;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
                                                    


