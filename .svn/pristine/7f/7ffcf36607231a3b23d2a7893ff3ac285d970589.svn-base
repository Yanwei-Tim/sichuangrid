--地图相关信息插入
--新重点场所对应数据
insert into keyplaces(id,orgid,name,orginternalcode,type,fullpinyin,simplepinyin,address,buildingid,centerx,centery,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,createdate,id_key,emphasis,chargeperson)
(select ct.companyplaceId,k.orgid,k.name,k.orginternalcode,ct.classificationen,k.fullpinyin,k.simplepinyin,k.address,k.buildingid,k.centerx,k.centery,k.centerlon,k.centerlat,k.centerlon2,k.centerlat2,k.featureid,k.builddataid,k.createdate,s_keyplaces.nextval,k.emphasis,k.chargeperson from keyplaces k,complaceInfo_temp ct where k.id_key=ct.id_key);

--安全生产重点
insert into keyplaces(id,orgid,name,orginternalcode,type,fullpinyin,simplepinyin,address,buildingid,centerx,centery,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,createdate,id_key,emphasis,chargeperson)
(select ct.companyplaceId,k.orgid,k.name,k.orginternalcode,'NEWSAFETYPRODUCTIONKEY',k.fullpinyin,k.simplepinyin,k.address,k.buildingid,k.centerx,k.centery,k.centerlon,k.centerlat,k.centerlon2,k.centerlat2,k.featureid,k.builddataid,k.createdate,s_keyplaces.nextval,k.emphasis,k.chargeperson from keyplaces k,complaceInfo_temp ct where k.id_key=ct.id_key and k.type ='SAFETYPRODUCTIONKEY');
--安全生产重点(规上)
insert into keyplaces(id,orgid,name,orginternalcode,type,fullpinyin,simplepinyin,address,buildingid,centerx,centery,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,createdate,id_key,emphasis,chargeperson)
(select ct.companyplaceId,k.orgid,k.name,k.orginternalcode,'ENTERPRISE',k.fullpinyin,k.simplepinyin,k.address,k.buildingid,k.centerx,k.centery,k.centerlon,k.centerlat,k.centerlon2,k.centerlat2,k.featureid,k.builddataid,k.createdate,s_keyplaces.nextval,k.emphasis,k.chargeperson from keyplaces k,complaceInfo_temp ct where k.id_key=ct.id_key and k.type ='SAFETYPRODUCTIONKEY' and ct.scaletype =(select id from propertydicts where displayname ='规上企业'));
--安全生产重点(规下)
insert into keyplaces(id,orgid,name,orginternalcode,type,fullpinyin,simplepinyin,address,buildingid,centerx,centery,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,createdate,id_key,emphasis,chargeperson)
(select ct.companyplaceId,k.orgid,k.name,k.orginternalcode,'ENTERPRISEDOWN',k.fullpinyin,k.simplepinyin,k.address,k.buildingid,k.centerx,k.centery,k.centerlon,k.centerlat,k.centerlon2,k.centerlat2,k.featureid,k.builddataid,k.createdate,s_keyplaces.nextval,k.emphasis,k.chargeperson from keyplaces k,complaceInfo_temp ct where k.id_key=ct.id_key and k.type ='SAFETYPRODUCTIONKEY' and ct.scaletype =(select id from propertydicts where displayname ='规下企业'));

--消防安全重点
insert into keyplaces(id,orgid,name,orginternalcode,type,fullpinyin,simplepinyin,address,buildingid,centerx,centery,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,createdate,id_key,emphasis,chargeperson)
(select ct.companyplaceId,k.orgid,k.name,k.orginternalcode,'NEWFIRESAFETYKEY',k.fullpinyin,k.simplepinyin,k.address,k.buildingid,k.centerx,k.centery,k.centerlon,k.centerlat,k.centerlon2,k.centerlat2,k.featureid,k.builddataid,k.createdate,s_keyplaces.nextval,k.emphasis,k.chargeperson from keyplaces k,complaceInfo_temp ct where k.id_key=ct.id_key and k.type ='FIRESAFETYKEY');

--治安重点
insert into keyplaces(id,orgid,name,orginternalcode,type,fullpinyin,simplepinyin,address,buildingid,centerx,centery,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,createdate,id_key,emphasis,chargeperson)
(select ct.companyplaceId,k.orgid,k.name,k.orginternalcode,'NEWSECURITYKEY',k.fullpinyin,k.simplepinyin,k.address,k.buildingid,k.centerx,k.centery,k.centerlon,k.centerlat,k.centerlon2,k.centerlat2,k.featureid,k.builddataid,k.createdate,s_keyplaces.nextval,k.emphasis,k.chargeperson from keyplaces k,complaceInfo_temp ct where k.id_key=ct.id_key and k.type ='SECURITYKEY');


--规上数据
insert into keyplaces(id,orgid,name,orginternalcode,type,fullpinyin,simplepinyin,address,buildingid,centerx,centery,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,createdate,id_key,emphasis,chargeperson)
(select ct.companyplaceId,k.orgid,k.name,k.orginternalcode,'ENTERPRISE',k.fullpinyin,k.simplepinyin,k.address,k.buildingid,k.centerx,k.centery,k.centerlon,k.centerlat,k.centerlon2,k.centerlat2,k.featureid,k.builddataid,k.createdate,s_keyplaces.nextval,k.emphasis,k.chargeperson from keyplaces k,complaceInfo_temp ct where k.id_key=ct.id_key and k.type ='ENTERPRISEKEY');
--规上数据业务关联治安重点
insert into keyplaces(id,orgid,name,orginternalcode,type,fullpinyin,simplepinyin,address,buildingid,centerx,centery,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,createdate,id_key,emphasis,chargeperson)
(select ct.companyplaceId,k.orgid,k.name,k.orginternalcode,'NEWSECURITYKEY',k.fullpinyin,k.simplepinyin,k.address,k.buildingid,k.centerx,k.centery,k.centerlon,k.centerlat,k.centerlon2,k.centerlat2,k.featureid,k.builddataid,k.createdate,s_keyplaces.nextval,k.emphasis,k.chargeperson from keyplaces k,complaceInfo_temp ct where k.id_key=ct.id_key and k.type ='ENTERPRISEKEY');

--规下数据
insert into keyplaces(id,orgid,name,orginternalcode,type,fullpinyin,simplepinyin,address,buildingid,centerx,centery,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,createdate,id_key,emphasis,chargeperson)
(select ct.companyplaceId,k.orgid,k.name,k.orginternalcode,'ENTERPRISEDOWN',k.fullpinyin,k.simplepinyin,k.address,k.buildingid,k.centerx,k.centery,k.centerlon,k.centerlat,k.centerlon2,k.centerlat2,k.featureid,k.builddataid,k.createdate,s_keyplaces.nextval,k.emphasis,k.chargeperson from keyplaces k,complaceInfo_temp ct where k.id_key=ct.id_key and k.type ='ENTERPRISEDOWNKEY');
--规下数据业务关联治安重点
insert into keyplaces(id,orgid,name,orginternalcode,type,fullpinyin,simplepinyin,address,buildingid,centerx,centery,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,createdate,id_key,emphasis,chargeperson)
(select ct.companyplaceId,k.orgid,k.name,k.orginternalcode,'NEWSECURITYKEY',k.fullpinyin,k.simplepinyin,k.address,k.buildingid,k.centerx,k.centery,k.centerlon,k.centerlat,k.centerlon2,k.centerlat2,k.featureid,k.builddataid,k.createdate,s_keyplaces.nextval,k.emphasis,k.chargeperson from keyplaces k,complaceInfo_temp ct where k.id_key=ct.id_key and k.type ='ENTERPRISEDOWNKEY');

--公共场所数据业务关联治安重点
insert into keyplaces(id,orgid,name,orginternalcode,type,fullpinyin,simplepinyin,address,buildingid,centerx,centery,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,createdate,id_key,emphasis,chargeperson)
(select ct.companyplaceId,k.orgid,k.name,k.orginternalcode,'NEWSECURITYKEY',k.fullpinyin,k.simplepinyin,k.address,k.buildingid,k.centerx,k.centery,k.centerlon,k.centerlat,k.centerlon2,k.centerlat2,k.featureid,k.builddataid,k.createdate,s_keyplaces.nextval,k.emphasis,k.chargeperson from keyplaces k,complaceInfo_temp ct where k.id_key=ct.id_key and k.type ='PUBLICCOMPLEXPLACES');
