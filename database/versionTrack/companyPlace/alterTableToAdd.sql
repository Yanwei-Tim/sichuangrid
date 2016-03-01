--单位场所基础表新增KeyPlace表关联字段Id_Key
ALTER TABLE companyplacebase ADD (id_key number(10));
--单位场业务表新增单位场所表关联字段baseID
ALTER TABLE companyplacebusiness ADD (companyplacebaseid number(10));

--增加冗余新学校类型字段
ALTER TABLE schools ADD (newtype number(10));
--把学校类型新类型字段更新为新单位场所的字典项ID
update schools s set s.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '小学' AND p.internalid = 10201) WHERE s.type = (SELECT id FROM  propertydicts p WHERE p.displayname = '小学' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='学校类型'));
update schools s set s.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '幼儿园' AND p.internalid = 10202) WHERE s.type = (SELECT id FROM  propertydicts p WHERE p.displayname = '幼儿园' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='学校类型'));
update schools s set s.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '托管机构' AND p.internalid = 10203) WHERE s.type = (SELECT id FROM  propertydicts p WHERE p.displayname = '托管机构' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='学校类型'));
update schools s set s.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '中学' AND p.internalid = 10204) WHERE s.type =(SELECT id FROM  propertydicts p WHERE p.displayname = '中学' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='学校类型'));
update schools s set s.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '大专院校' AND p.internalid = 10205) WHERE s.type = (SELECT id FROM  propertydicts p WHERE p.displayname = '大专院校' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='学校类型'));
update schools s set s.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '成人教育、职高、技校等' AND p.internalid = 10206) WHERE s.type = (SELECT id FROM  propertydicts p WHERE p.displayname = '职业学校' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='学校类型'));
update schools s set s.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '教育-其他' AND p.internalid = 10210) WHERE s.type = (SELECT id FROM  propertydicts p WHERE p.displayname = '其它' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='学校类型'));

--增加冗余新医院类型字段
ALTER TABLE hospitals ADD (newtype number(10));
--把医院类型新类型字段更新为新单位场所的字典项ID
update hospitals h set h.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '综合医院' AND p.internalid = 10301) WHERE h.type in (SELECT id FROM  propertydicts p WHERE p.displayname = '综合医院' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='医院类型'));
update hospitals h set h.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '专科医院' AND p.internalid = 10305) WHERE h.type in (SELECT id FROM  propertydicts p WHERE p.displayname = '专科医院' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='医院类型'));
update hospitals h set h.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '门诊部、医务室、诊所' AND p.internalid = 10308) WHERE h.type = (SELECT id FROM  propertydicts p WHERE p.displayname = '门诊' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='医院类型'));
update hospitals h set h.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '门诊部、医务室、诊所' AND p.internalid = 10308) WHERE h.type in (SELECT id FROM  propertydicts p WHERE p.displayname = '其他' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='医院类型'));
update hospitals h set h.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '门诊部、医务室、诊所' AND p.internalid = 10308) WHERE h.type is null;

--创建单位场所服务记录有关的服务记录表
create table recordid_temp(
   id                   NUMBER(10)      not null,
   recordId 			NUMBER(10) 		not null
);

--创建单位场所信息临时表
create table complaceInfo_temp (
   id_key               number(10)      not null,
   classificationen     NVARCHAR2(150),
   companyplaceId       NUMBER(10)      not null,
   scaletype            NUMBER(10)
);
--创建单位场所信息临时表id_key索引
create index idx_complaceInfo_temp_idkey on complaceInfo_temp(id_key);

