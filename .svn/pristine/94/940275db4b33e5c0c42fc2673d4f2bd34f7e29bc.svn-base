--单位场所基础表删除KeyPlace表关联字段Id_Key
ALTER TABLE companyplacebase DROP COLUMN id_key;

--单位场业务表删除单位场所表关联字段baseID
ALTER TABLE companyplacebusiness DROP COLUMN companyplacebaseid;

--删除冗余新医院类型字段
ALTER TABLE hospitals DROP COLUMN newtype;

--删除冗余新学校类型字段
ALTER TABLE schools DROP COLUMN newtype;

--删除服务记录临时表
drop table recordid_temp;

--删除单位场所临时表
drop table complaceInfo_temp;
