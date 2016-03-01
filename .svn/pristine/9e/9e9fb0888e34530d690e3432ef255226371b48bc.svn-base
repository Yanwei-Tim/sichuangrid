--规上企业
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,ep.attentionextent,kp.address,CASE WHEN ep.isemphasis is null THEN 0 ELSE ep.isemphasis  END,ep.isemphasisdate,ep.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,ep.sourcesstate,ep.imgurl,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,kp.id_key FROM keyplaces kp,enterprises ep WHERE kp.type='ENTERPRISEKEY' AND kp.id = ep.id and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,faxnumber,coveredarea,remarks,businesslicenseno,partycountnumber,registeredcapital,countno,scaletype,haslicense,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '单位' AND p.internalid = 1),(SELECT id FROM  propertydicts p WHERE p.displayname = '单位-其他' AND p.internalid = 105),'OTHERCOMPANY',(SELECT id FROM  propertydicts p WHERE p.displayname = '单位-其他' AND p.internalid = 10507),ep.legalperson,ep.mobilenumber,ep.telephone,ep.fax,ep.area,ep.remark,ep.businesslicense,ep.partymemberamount,ep.registeredcapital,ep.employeeamount,ep.type,CASE WHEN ep.businesslicense is not null THEN 1 ELSE 0 END,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='ENTERPRISEKEY' AND cpb.id_key = kp.id_key);


--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,2,ep.hiddencases,ep.hiddenrectification,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,cpb.id  FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb  WHERE kp.id = ep.id AND  kp.type='ENTERPRISEKEY' AND cpb.id_key = kp.id_key);


--规下企业
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,ep.attentionextent,kp.address,CASE WHEN ep.isemphasis is null THEN 0 ELSE ep.isemphasis  END,ep.isemphasisdate,ep.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,ep.sourcesstate,ep.imgurl,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,kp.id_key FROM keyplaces kp,enterprises ep WHERE kp.type='ENTERPRISEDOWNKEY' AND kp.id = ep.id  and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,faxnumber,coveredarea,remarks,businesslicenseno,partycountnumber,registeredcapital,countno,scaletype,haslicense,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '单位' AND p.internalid = 1),(SELECT id FROM  propertydicts p WHERE p.displayname = '单位-其他' AND p.internalid = 105),'OTHERCOMPANY',(SELECT id FROM  propertydicts p WHERE p.displayname = '单位-其他' AND p.internalid = 10507),ep.legalperson,ep.mobilenumber,ep.telephone,ep.fax,ep.area,ep.remark,ep.businesslicense,ep.partymemberamount,ep.registeredcapital,ep.employeeamount,ep.type,CASE WHEN ep.businesslicense is not null THEN 1 ELSE 0 END,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='ENTERPRISEDOWNKEY' AND cpb.id_key = kp.id_key);


--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,2,ep.hiddencases,ep.hiddenrectification,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,cpb.id  FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb  WHERE kp.id = ep.id AND  kp.type='ENTERPRISEDOWNKEY' AND cpb.id_key = kp.id_key);



--安全生产重点
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,ep.attentionextent,kp.address,CASE WHEN ep.isemphasis is null THEN 0 ELSE ep.isemphasis  END,ep.isemphasisdate,ep.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,ep.sourcesstate,ep.imgurl,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,kp.id_key FROM keyplaces kp,enterprises ep WHERE kp.type='SAFETYPRODUCTIONKEY' AND kp.id = ep.id  and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,faxnumber,coveredarea,remarks,businesslicenseno,partycountnumber,registeredcapital,countno,scaletype,haslicense,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '单位' AND p.internalid = 1),(SELECT id FROM  propertydicts p WHERE p.displayname = '单位-其他' AND p.internalid = 105),'OTHERCOMPANY',(SELECT id FROM  propertydicts p WHERE p.displayname = '单位-其他' AND p.internalid = 10507),ep.legalperson,ep.mobilenumber,ep.telephone,ep.fax,ep.area,ep.remark,ep.businesslicense,ep.partymemberamount,ep.registeredcapital,ep.employeeamount,ep.type,CASE WHEN ep.businesslicense is not null THEN 1 ELSE 0 END,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='SAFETYPRODUCTIONKEY' AND cpb.id_key = kp.id_key);

--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,0,ep.hiddencases,ep.hiddenrectification,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,cpb.id  FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb  WHERE kp.id = ep.id AND  kp.type='SAFETYPRODUCTIONKEY' AND cpb.id_key = kp.id_key);


--消防安全重点(娱乐场所)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,ep.attentionextent,kp.address,CASE WHEN ep.isemphasis is null THEN 0 ELSE ep.isemphasis  END,ep.isemphasisdate,ep.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,ep.sourcesstate,ep.imgurl,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,kp.id_key FROM keyplaces kp,enterprises ep WHERE kp.type='FIRESAFETYKEY' AND kp.id = ep.id AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='娱乐场所' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='消防安全重点类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,faxnumber,coveredarea,remarks,businesslicenseno,partycountnumber,registeredcapital,countno,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '娱乐场所' AND p.internalid = 203),'ENTERTAINMENTPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '娱乐场所-其他' AND p.internalid = 20308),ep.legalperson,ep.mobilenumber,ep.telephone,ep.fax,ep.area,ep.remark,ep.businesslicense,ep.partymemberamount,ep.registeredcapital,ep.employeeamount,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='FIRESAFETYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='娱乐场所' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='消防安全重点类别')));


--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,listedcorrection,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,1,ep.hiddencases,ep.hiddenrectification,ep.renovatetype,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,cpb.id  FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='FIRESAFETYKEY' AND cpb.id_key = kp.id_key  AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='娱乐场所' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='消防安全重点类别')));


--消防安全重点(商场)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,ep.attentionextent,kp.address,CASE WHEN ep.isemphasis is null THEN 0 ELSE ep.isemphasis  END,ep.isemphasisdate,ep.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,ep.sourcesstate,ep.imgurl,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,kp.id_key FROM keyplaces kp,enterprises ep WHERE kp.type='FIRESAFETYKEY' AND kp.id = ep.id AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='商场' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='消防安全重点类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,faxnumber,coveredarea,remarks,businesslicenseno,partycountnumber,registeredcapital,countno,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '商贸场所' AND p.internalid = 204),'TRADEPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '大型商场、超市' AND p.internalid = 20401),ep.legalperson,ep.mobilenumber,ep.telephone,ep.fax,ep.area,ep.remark,ep.businesslicense,ep.partymemberamount,ep.registeredcapital,ep.employeeamount,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='FIRESAFETYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='商场' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='消防安全重点类别')));


--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,listedcorrection,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,1,ep.hiddencases,ep.hiddenrectification,ep.renovatetype,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,cpb.id  FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='FIRESAFETYKEY' AND cpb.id_key = kp.id_key  AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='商场' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='消防安全重点类别')));


--消防安全重点(市场)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,ep.attentionextent,kp.address,CASE WHEN ep.isemphasis is null THEN 0 ELSE ep.isemphasis  END,ep.isemphasisdate,ep.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,ep.sourcesstate,ep.imgurl,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,kp.id_key FROM keyplaces kp,enterprises ep WHERE kp.type='FIRESAFETYKEY' AND kp.id = ep.id AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='市场' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='消防安全重点类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,faxnumber,coveredarea,remarks,businesslicenseno,partycountnumber,registeredcapital,countno,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '商贸场所' AND p.internalid = 204),'TRADEPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '贸易市场' AND p.internalid = 20402),ep.legalperson,ep.mobilenumber,ep.telephone,ep.fax,ep.area,ep.remark,ep.businesslicense,ep.partymemberamount,ep.registeredcapital,ep.employeeamount,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='FIRESAFETYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='市场' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='消防安全重点类别')));


--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,listedcorrection,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,1,ep.hiddencases,ep.hiddenrectification,ep.renovatetype,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,cpb.id  FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='FIRESAFETYKEY' AND cpb.id_key = kp.id_key  AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='市场' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='消防安全重点类别')));


--消防安全重点(重点单位)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,ep.attentionextent,kp.address,CASE WHEN ep.isemphasis is null THEN 0 ELSE ep.isemphasis  END,ep.isemphasisdate,ep.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,ep.sourcesstate,ep.imgurl,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,kp.id_key FROM keyplaces kp,enterprises ep WHERE kp.type='FIRESAFETYKEY' AND kp.id = ep.id AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='重点单位' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='消防安全重点类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,faxnumber,coveredarea,remarks,businesslicenseno,partycountnumber,registeredcapital,countno,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '场所-其他' AND p.internalid = 210),'OTHERPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '场所-其他' AND p.internalid = 21009),ep.legalperson,ep.mobilenumber,ep.telephone,ep.fax,ep.area,ep.remark,ep.businesslicense,ep.partymemberamount,ep.registeredcapital,ep.employeeamount,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='FIRESAFETYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='重点单位' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='消防安全重点类别')));



--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,listedcorrection,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,1,ep.hiddencases,ep.hiddenrectification,ep.renovatetype,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,cpb.id  FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='FIRESAFETYKEY' AND cpb.id_key = kp.id_key  AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='重点单位' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='消防安全重点类别')));


--消防安全重点(其他)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,ep.attentionextent,kp.address,CASE WHEN ep.isemphasis is null THEN 0 ELSE ep.isemphasis  END,ep.isemphasisdate,ep.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,ep.sourcesstate,ep.imgurl,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,kp.id_key FROM keyplaces kp,enterprises ep WHERE kp.type='FIRESAFETYKEY' AND kp.id = ep.id AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='其他' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='消防安全重点类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,faxnumber,coveredarea,remarks,businesslicenseno,partycountnumber,registeredcapital,countno,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '场所-其他' AND p.internalid = 210),'OTHERPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '场所-其他' AND p.internalid = 21009),ep.legalperson,ep.mobilenumber,ep.telephone,ep.fax,ep.area,ep.remark,ep.businesslicense,ep.partymemberamount,ep.registeredcapital,ep.employeeamount,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='FIRESAFETYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='其他' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='消防安全重点类别')));



--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,listedcorrection,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,1,ep.hiddencases,ep.hiddenrectification,ep.renovatetype,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,cpb.id  FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='FIRESAFETYKEY' AND cpb.id_key = kp.id_key  AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='其他' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='消防安全重点类别')));


--治安重点(车站码头)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,ep.attentionextent,kp.address,CASE WHEN ep.isemphasis is null THEN 0 ELSE ep.isemphasis  END,ep.isemphasisdate,ep.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,ep.sourcesstate,ep.imgurl,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,kp.id_key FROM keyplaces kp,enterprises ep WHERE kp.type='SECURITYKEY' AND kp.id = ep.id AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='车站码头' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,faxnumber,coveredarea,remarks,businesslicenseno,partycountnumber,registeredcapital,countno,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '交通场所' AND p.internalid = 202),'TRAFFICPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '交通场所-其他' AND p.internalid = 20206),ep.legalperson,ep.mobilenumber,ep.telephone,ep.fax,ep.area,ep.remark,ep.businesslicense,ep.partymemberamount,ep.registeredcapital,ep.employeeamount,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='SECURITYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='车站码头' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')));


--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,listedcorrection,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,2,ep.hiddencases,ep.hiddenrectification,ep.renovatetype,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,cpb.id   FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb  WHERE kp.id = ep.id AND  kp.type='SECURITYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='车站码头' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')));


--治安重点(娱乐场所)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,ep.attentionextent,kp.address,CASE WHEN ep.isemphasis is null THEN 0 ELSE ep.isemphasis  END,ep.isemphasisdate,ep.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,ep.sourcesstate,ep.imgurl,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,kp.id_key FROM keyplaces kp,enterprises ep WHERE kp.type='SECURITYKEY' AND kp.id = ep.id AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='娱乐场所' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,faxnumber,coveredarea,remarks,businesslicenseno,partycountnumber,registeredcapital,countno,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '娱乐场所' AND p.internalid = 203),'ENTERTAINMENTPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '娱乐场所-其他' AND p.internalid = 20308),ep.legalperson,ep.mobilenumber,ep.telephone,ep.fax,ep.area,ep.remark,ep.businesslicense,ep.partymemberamount,ep.registeredcapital,ep.employeeamount,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='SECURITYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='娱乐场所' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')));


--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,listedcorrection,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,2,ep.hiddencases,ep.hiddenrectification,ep.renovatetype,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,cpb.id  FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='SECURITYKEY' AND cpb.id_key = kp.id_key  AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='娱乐场所' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')));



--治安重点(批发市场)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,ep.attentionextent,kp.address,CASE WHEN ep.isemphasis is null THEN 0 ELSE ep.isemphasis  END,ep.isemphasisdate,ep.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,ep.sourcesstate,ep.imgurl,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,kp.id_key FROM keyplaces kp,enterprises ep WHERE kp.type='SECURITYKEY' AND kp.id = ep.id AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='批发市场' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,faxnumber,coveredarea,remarks,businesslicenseno,partycountnumber,registeredcapital,countno,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '商贸场所' AND p.internalid = 204),'TRADEPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '贸易市场' AND p.internalid = 20402),ep.legalperson,ep.mobilenumber,ep.telephone,ep.fax,ep.area,ep.remark,ep.businesslicense,ep.partymemberamount,ep.registeredcapital,ep.employeeamount,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='SECURITYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='批发市场' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')));


--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,listedcorrection,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,2,ep.hiddencases,ep.hiddenrectification,ep.renovatetype,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,cpb.id  FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='SECURITYKEY' AND cpb.id_key = kp.id_key  AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='批发市场' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')));



--治安重点(宾馆与旅馆)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,ep.attentionextent,kp.address,CASE WHEN ep.isemphasis is null THEN 0 ELSE ep.isemphasis  END,ep.isemphasisdate,ep.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,ep.sourcesstate,ep.imgurl,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,kp.id_key FROM keyplaces kp,enterprises ep WHERE kp.type='SECURITYKEY' AND kp.id = ep.id AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='宾馆与旅馆' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,faxnumber,coveredarea,remarks,businesslicenseno,partycountnumber,registeredcapital,countno,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '住宿服务场所' AND p.internalid = 206),'ACCOMMODATIONSERVICESPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '宾馆、饭店（可住宿）' AND p.internalid = 20601),ep.legalperson,ep.mobilenumber,ep.telephone,ep.fax,ep.area,ep.remark,ep.businesslicense,ep.partymemberamount,ep.registeredcapital,ep.employeeamount,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='SECURITYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='宾馆与旅馆' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')));


--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,listedcorrection,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,2,ep.hiddencases,ep.hiddenrectification,ep.renovatetype,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,cpb.id  FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='SECURITYKEY' AND cpb.id_key = kp.id_key  AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='宾馆与旅馆' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')));



--治安重点(城乡结合部)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,ep.attentionextent,kp.address,CASE WHEN ep.isemphasis is null THEN 0 ELSE ep.isemphasis  END,ep.isemphasisdate,ep.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,ep.sourcesstate,ep.imgurl,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,kp.id_key FROM keyplaces kp,enterprises ep WHERE kp.type='SECURITYKEY' AND kp.id = ep.id AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='城乡结合部' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,faxnumber,coveredarea,remarks,businesslicenseno,partycountnumber,registeredcapital,countno,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '场所-其他' AND p.internalid = 210),'OTHERPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '城乡结合部' AND p.internalid = 21004),ep.legalperson,ep.mobilenumber,ep.telephone,ep.fax,ep.area,ep.remark,ep.businesslicense,ep.partymemberamount,ep.registeredcapital,ep.employeeamount,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='SECURITYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='城乡结合部' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')));


--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,listedcorrection,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,2,ep.hiddencases,ep.hiddenrectification,ep.renovatetype,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,cpb.id  FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb  WHERE kp.id = ep.id AND  kp.type='SECURITYKEY'  AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='城乡结合部' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')));


--治安重点(城中村)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,ep.attentionextent,kp.address,CASE WHEN ep.isemphasis is null THEN 0 ELSE ep.isemphasis  END,ep.isemphasisdate,ep.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,ep.sourcesstate,ep.imgurl,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,kp.id_key FROM keyplaces kp,enterprises ep WHERE kp.type='SECURITYKEY' AND kp.id = ep.id AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='城中村' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,faxnumber,coveredarea,remarks,businesslicenseno,partycountnumber,registeredcapital,countno,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '场所-其他' AND p.internalid = 210),'OTHERPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '城中村' AND p.internalid = 21005),ep.legalperson,ep.mobilenumber,ep.telephone,ep.fax,ep.area,ep.remark,ep.businesslicense,ep.partymemberamount,ep.registeredcapital,ep.employeeamount,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='SECURITYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='城中村' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')));


--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,listedcorrection,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,2,ep.hiddencases,ep.hiddenrectification,ep.renovatetype,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,cpb.id  FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='SECURITYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='城中村' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')));


--治安重点(流动人口聚集地)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,ep.attentionextent,kp.address,CASE WHEN ep.isemphasis is null THEN 0 ELSE ep.isemphasis  END,ep.isemphasisdate,ep.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,ep.sourcesstate,ep.imgurl,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,kp.id_key FROM keyplaces kp,enterprises ep WHERE kp.type='SECURITYKEY' AND kp.id = ep.id AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='流动人口聚集地' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,faxnumber,coveredarea,remarks,businesslicenseno,partycountnumber,registeredcapital,countno,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '场所-其他' AND p.internalid = 210),'OTHERPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '流动人口聚集地' AND p.internalid = 21006),ep.legalperson,ep.mobilenumber,ep.telephone,ep.fax,ep.area,ep.remark,ep.businesslicense,ep.partymemberamount,ep.registeredcapital,ep.employeeamount,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='SECURITYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='流动人口聚集地' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')));


--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,listedcorrection,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,2,ep.hiddencases,ep.hiddenrectification,ep.renovatetype,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,cpb.id  FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='SECURITYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='流动人口聚集地' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')));



--治安重点(出租房屋区)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,ep.attentionextent,kp.address,CASE WHEN ep.isemphasis is null THEN 0 ELSE ep.isemphasis  END,ep.isemphasisdate,ep.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,ep.sourcesstate,ep.imgurl,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,kp.id_key FROM keyplaces kp,enterprises ep WHERE kp.type='SECURITYKEY' AND kp.id = ep.id AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='出租房屋区' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,faxnumber,coveredarea,remarks,businesslicenseno,partycountnumber,registeredcapital,countno,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '场所-其他' AND p.internalid = 210),'OTHERPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '出租房屋区' AND p.internalid = 21007),ep.legalperson,ep.mobilenumber,ep.telephone,ep.fax,ep.area,ep.remark,ep.businesslicense,ep.partymemberamount,ep.registeredcapital,ep.employeeamount,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='SECURITYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='出租房屋区' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')));


--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,listedcorrection,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,2,ep.hiddencases,ep.hiddenrectification,ep.renovatetype,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,cpb.id  FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='SECURITYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='出租房屋区' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')));


--治安重点(矿山市场)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,ep.attentionextent,kp.address,CASE WHEN ep.isemphasis is null THEN 0 ELSE ep.isemphasis  END,ep.isemphasisdate,ep.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,ep.sourcesstate,ep.imgurl,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,kp.id_key FROM keyplaces kp,enterprises ep WHERE kp.type='SECURITYKEY' AND kp.id = ep.id AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='矿山市场' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,faxnumber,coveredarea,remarks,businesslicenseno,partycountnumber,registeredcapital,countno,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '场所-其他' AND p.internalid = 210),'OTHERPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '矿山市场' AND p.internalid = 21008),ep.legalperson,ep.mobilenumber,ep.telephone,ep.fax,ep.area,ep.remark,ep.businesslicense,ep.partymemberamount,ep.registeredcapital,ep.employeeamount,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='SECURITYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='矿山市场' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')));


--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,listedcorrection,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,2,ep.hiddencases,ep.hiddenrectification,ep.renovatetype,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,cpb.id  FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='SECURITYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='矿山市场' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')));


--治安重点(其他)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,ep.attentionextent,kp.address,CASE WHEN ep.isemphasis is null THEN 0 ELSE ep.isemphasis  END,ep.isemphasisdate,ep.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,ep.sourcesstate,ep.imgurl,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,kp.id_key FROM keyplaces kp,enterprises ep WHERE kp.type='SECURITYKEY' AND kp.id = ep.id AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='其他' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,faxnumber,coveredarea,remarks,businesslicenseno,partycountnumber,registeredcapital,countno,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '场所-其他' AND p.internalid = 210),'OTHERPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '场所-其他' AND p.internalid = 21009),ep.legalperson,ep.mobilenumber,ep.telephone,ep.fax,ep.area,ep.remark,ep.businesslicense,ep.partymemberamount,ep.registeredcapital,ep.employeeamount,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='SECURITYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='其他' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')));


--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,listedcorrection,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,2,ep.hiddencases,ep.hiddenrectification,ep.renovatetype,ep.createuser,ep.updateuser,ep.createdate,ep.updatedate,cpb.id  FROM enterprises ep,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = ep.id AND  kp.type='SECURITYKEY' AND cpb.id_key = kp.id_key AND ep.type = (SELECT id FROM propertydicts p WHERE p.displayname='其他' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='治安重点类别')));


--学校
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,s.attentionextent,kp.address,CASE WHEN s.isemphasis is null THEN 0 ELSE s.isemphasis  END,s.isemphasisdate,s.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,s.sourcesstate,s.imgurl,s.createuser,s.updateuser,s.createdate,s.updatedate,kp.id_key FROM keyplaces kp,schools s WHERE kp.type='SCHOOLS' AND kp.id = s.id  and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,faxnumber,area,email,legalviceprincipal,schoolnature,schoolnameen,schoolwebsite,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '单位' AND p.internalid = 1),(SELECT id FROM  propertydicts p WHERE p.displayname = '学校' AND p.internalid = 102),'NEWSCHOOLS',s.newtype,s.president,s.personliablemobilenumber,s.personliabletelephone,s.fax,s.remark,s.email,s.personliable,s.kind,s.englishname,s.website,s.createuser,s.updateuser,s.createdate,s.updatedate FROM schools s,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = s.id AND  kp.type='SCHOOLS' AND cpb.id_key = kp.id_key);



--医院
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,h.attentionextent,kp.address,CASE WHEN h.isemphasis is null THEN 0 ELSE h.isemphasis  END,h.logouttime,h.logoutreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,h.sourcesstate,h.imgurl,h.createuser,h.updateuser,h.createdate,h.updatedate,kp.id_key FROM keyplaces kp,hospitals h WHERE kp.type='HOSPITAL' AND kp.id = h.id  and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,faxnumber,area,remarks,email,hospitalnature,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '单位' AND p.internalid = 1),(SELECT id FROM  propertydicts p WHERE p.displayname = '医院' AND p.internalid = 103),'NEWHOSPITAL',h.newtype,h.director,h.mobilenumber,h.telephone,h.fax,h.affiliatedunit,h.remark,h.email,h.kind,h.createuser,h.updateuser,h.createdate,h.updatedate FROM hospitals h,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = h.id AND  kp.type='HOSPITAL' AND cpb.id_key = kp.id_key);



--危险化学品单位--
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,d.attentionextent,kp.address,CASE WHEN d.isemphasis is null THEN 0 ELSE d.isemphasis  END,d.logouttime,d.logoutreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,d.sourcesstate,d.imgurl,d.createuser,d.updateuser,d.createdate,d.updatedate,kp.id_key FROM keyplaces kp,dangerouschemicalsunit d WHERE kp.type='DANGEROUSCHEMICALSUNIT' AND kp.id = d.id  and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,telephone,area,remarks,generalstorage,generaltype,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '单位' AND p.internalid = 1),(SELECT id FROM  propertydicts p WHERE p.displayname = '危化品存放单位' AND p.internalid = 104),'NEWDANGEROUSCHEMICALSUNIT',(SELECT id FROM  propertydicts p WHERE p.displayname = '危险品存放站点' AND p.internalid = 10401),d.superintendent,d.telephone,d.copyscope,d.remark,d.storagedevice,d.commoditytype,d.createuser,d.updateuser,d.createdate,d.updatedate FROM dangerouschemicalsunit d,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = d.id AND  kp.type='DANGEROUSCHEMICALSUNIT' AND cpb.id_key = kp.id_key);


--网吧
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,i.attentionextent,kp.address,CASE WHEN i.isemphasis is null THEN 0 ELSE i.isemphasis  END,i.logouttime,i.logoutreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,i.sourcesstate,i.imgurl,i.createuser,i.updateuser,i.createdate,i.updatedate,kp.id_key FROM keyplaces kp,internetbar i WHERE kp.type='INTERNETBAR' AND kp.id = i.id  and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,area,coveredarea,remarks,networkcardno,generalstorage,generaltype,nowip,countno,generalmanage,generalmente,fireauditopinionno,internetbarno,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '网吧' AND p.internalid = 205),'NEWINTERNETBAR',(SELECT id FROM  propertydicts p WHERE p.displayname = '网吧' AND p.internalid = 20501),i.manager,i.mobile,i.stationpolice,i.operationarea,i.remark,i.tempnetcardnum,i.internetaccesstype,i.netaccessproviders,i.useip,i.totalcomputernum,i.netculturelicenceno,i.netsecurityauditno,i.fireauditdocumentno,i.barcode,i.createuser,i.updateuser,i.createdate,i.updatedate FROM internetbar i,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = i.id AND  kp.type='INTERNETBAR' AND cpb.id_key = kp.id_key);



--公共场所
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,p.attentionextent,kp.address,CASE WHEN p.isemphasis is null THEN 0 ELSE p.isemphasis  END,p.logouttime,p.logoutreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,p.sourcesstate,p.imgurl,p.createuser,p.updateuser,p.createdate,p.updatedate,kp.id_key FROM keyplaces kp,publicplace p WHERE kp.type='PUBLICPLACE' AND kp.id = p.id  and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,area,coveredarea,remarks,cloakroom,businessarea,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '商贸场所' AND p.internalid = 204),'TRADEPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '大型商场、超市' AND p.internalid = 20401),p.manager,p.passageway,p.totalarea,p.remark,(SELECT CASE WHEN p.displayname= '是' THEN 0 WHEN p.displayname= '否' THEN 1  END  FROM  propertydicts p WHERE id=p.cloakroom),p.operationarea,p.createuser,p.updateuser,p.createdate,p.updatedate FROM publicplace p,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = p.id AND  kp.type='PUBLICPLACE' AND cpb.id_key = kp.id_key);



--公共复杂场所(车站码头)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,pc.attentionextent,kp.address,CASE WHEN pc.isemphasis is null THEN 0 ELSE pc.isemphasis  END,pc.logouttime,pc.logoutreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,pc.sourcesstate,pc.imgurl,pc.createuser,pc.updateuser,pc.createdate,pc.updatedate,kp.id_key FROM keyplaces kp,publiccomplexplaces pc WHERE kp.type='PUBLICCOMPLEXPLACES' AND kp.id = pc.id AND pc.type = (SELECT id FROM propertydicts p WHERE p.displayname='车站码头' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='公共复杂场所分类')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,remarks,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '交通场所' AND p.internalid = 202),'TRAFFICPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '交通场所-其他' AND p.internalid = 20206),pc.manager,pc.managermobile,pc.managertelephone,pc.remark,pc.createuser,pc.updateuser,pc.createdate,pc.updatedate FROM publiccomplexplaces pc,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = pc.id AND  kp.type='PUBLICCOMPLEXPLACES' AND cpb.id_key = kp.id_key AND pc.type = (SELECT id FROM propertydicts p WHERE p.displayname='车站码头' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='公共复杂场所分类')));


--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,2,pc.hiddencases,pc.hiddenrectification,pc.createuser,pc.updateuser,pc.createdate,pc.updatedate,cpb.id  FROM publiccomplexplaces pc,keyplaces kp,companyplacebase_temp cpb  WHERE kp.id = pc.id AND  kp.type='PUBLICCOMPLEXPLACES' AND cpb.id_key = kp.id_key AND pc.type = (SELECT id FROM propertydicts p WHERE p.displayname='车站码头' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='公共复杂场所分类')));



--公共复杂场所(公共娱乐场所)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,pc.attentionextent,kp.address,CASE WHEN pc.isemphasis is null THEN 0 ELSE pc.isemphasis  END,pc.logouttime,pc.logoutreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,pc.sourcesstate,pc.imgurl,pc.createuser,pc.updateuser,pc.createdate,pc.updatedate,kp.id_key FROM keyplaces kp,publiccomplexplaces pc WHERE kp.type='PUBLICCOMPLEXPLACES' AND kp.id = pc.id AND pc.type = (SELECT id FROM propertydicts p WHERE p.displayname='公共娱乐场所' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='公共复杂场所分类')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,remarks,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '娱乐场所' AND p.internalid = 203),'ENTERTAINMENTPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '娱乐场所-其他' AND p.internalid = 20308),pc.manager,pc.managermobile,pc.managertelephone,pc.remark,pc.createuser,pc.updateuser,pc.createdate,pc.updatedate FROM publiccomplexplaces pc,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = pc.id AND  kp.type='PUBLICCOMPLEXPLACES' AND cpb.id_key = kp.id_key AND pc.type = (SELECT id FROM propertydicts p WHERE p.displayname='公共娱乐场所' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='公共复杂场所分类')));


--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,2,pc.hiddencases,pc.hiddenrectification,pc.createuser,pc.updateuser,pc.createdate,pc.updatedate,cpb.id  FROM publiccomplexplaces pc,keyplaces kp,companyplacebase_temp cpb  WHERE kp.id = pc.id AND  kp.type='PUBLICCOMPLEXPLACES' AND cpb.id_key = kp.id_key AND pc.type = (SELECT id FROM propertydicts p WHERE p.displayname='公共娱乐场所' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='公共复杂场所分类')));


--公共复杂场所(市场)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,pc.attentionextent,kp.address,CASE WHEN pc.isemphasis is null THEN 0 ELSE pc.isemphasis  END,pc.logouttime,pc.logoutreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,pc.sourcesstate,pc.imgurl,pc.createuser,pc.updateuser,pc.createdate,pc.updatedate,kp.id_key FROM keyplaces kp,publiccomplexplaces pc WHERE kp.type='PUBLICCOMPLEXPLACES' AND kp.id = pc.id AND pc.type = (SELECT id FROM propertydicts p WHERE p.displayname='市场' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='公共复杂场所分类')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,remarks,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '商贸场所' AND p.internalid = 204),'TRADEPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '贸易市场' AND p.internalid = 20402),pc.manager,pc.managermobile,pc.managertelephone,pc.remark,pc.createuser,pc.updateuser,pc.createdate,pc.updatedate FROM publiccomplexplaces pc,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = pc.id AND  kp.type='PUBLICCOMPLEXPLACES' AND cpb.id_key = kp.id_key AND pc.type = (SELECT id FROM propertydicts p WHERE p.displayname='市场' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='公共复杂场所分类')));


--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,2,pc.hiddencases,pc.hiddenrectification,pc.createuser,pc.updateuser,pc.createdate,pc.updatedate,cpb.id  FROM publiccomplexplaces pc,keyplaces kp,companyplacebase_temp cpb  WHERE kp.id = pc.id AND  kp.type='PUBLICCOMPLEXPLACES' AND cpb.id_key = kp.id_key AND pc.type = (SELECT id FROM propertydicts p WHERE p.displayname='市场' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='公共复杂场所分类')));



--公共复杂场所(景点)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,pc.attentionextent,kp.address,CASE WHEN pc.isemphasis is null THEN 0 ELSE pc.isemphasis  END,pc.logouttime,pc.logoutreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,pc.sourcesstate,pc.imgurl,pc.createuser,pc.updateuser,pc.createdate,pc.updatedate,kp.id_key FROM keyplaces kp,publiccomplexplaces pc WHERE kp.type='PUBLICCOMPLEXPLACES' AND kp.id = pc.id AND pc.type = (SELECT id FROM propertydicts p WHERE p.displayname='景点' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='公共复杂场所分类')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,remarks,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '旅游场所' AND p.internalid = 208),'TRAVELINGPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '风景名胜区' AND p.internalid = 20802),pc.manager,pc.managermobile,pc.managertelephone,pc.remark,pc.createuser,pc.updateuser,pc.createdate,pc.updatedate FROM publiccomplexplaces pc,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = pc.id AND  kp.type='PUBLICCOMPLEXPLACES' AND cpb.id_key = kp.id_key AND pc.type = (SELECT id FROM propertydicts p WHERE p.displayname='景点' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='公共复杂场所分类')));


--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,2,pc.hiddencases,pc.hiddenrectification,pc.createuser,pc.updateuser,pc.createdate,pc.updatedate,cpb.id  FROM publiccomplexplaces pc,keyplaces kp,companyplacebase_temp cpb  WHERE kp.id = pc.id AND  kp.type='PUBLICCOMPLEXPLACES' AND cpb.id_key = kp.id_key AND pc.type = (SELECT id FROM propertydicts p WHERE p.displayname='景点' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='公共复杂场所分类')));


--公共复杂场所(不选择)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,pc.attentionextent,kp.address,CASE WHEN pc.isemphasis is null THEN 0 ELSE pc.isemphasis  END,pc.logouttime,pc.logoutreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,pc.sourcesstate,pc.imgurl,pc.createuser,pc.updateuser,pc.createdate,pc.updatedate,kp.id_key FROM keyplaces kp,publiccomplexplaces pc WHERE kp.type='PUBLICCOMPLEXPLACES' AND kp.id = pc.id AND pc.type is null  and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,remarks,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '场所-其他' AND p.internalid = 210),'OTHERPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '场所-其他' AND p.internalid = 21009),pc.manager,pc.managermobile,pc.managertelephone,pc.remark,pc.createuser,pc.updateuser,pc.createdate,pc.updatedate FROM publiccomplexplaces pc,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = pc.id AND  kp.type='PUBLICCOMPLEXPLACES' AND cpb.id_key = kp.id_key AND pc.type is null);


--单位场所业务表
INSERT INTO companyplacebusiness_temp(id,iskeytype,hiddangerinfo,correctioninfo,createuser,updateuser,createdate,updatedate,companyplacebaseid)
(SELECT s_companyplacebusiness.nextval,2,pc.hiddencases,pc.hiddenrectification,pc.createuser,pc.updateuser,pc.createdate,pc.updatedate,cpb.id  FROM publiccomplexplaces pc,keyplaces kp,companyplacebase_temp cpb  WHERE kp.id = pc.id AND  kp.type='PUBLICCOMPLEXPLACES' AND cpb.id_key = kp.id_key AND pc.type is null);


--其他场所(餐饮服务)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,o.attentionextent,kp.address,CASE WHEN o.isemphasis is null THEN 0 ELSE o.isemphasis END ,o.isemphasisdate,o.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,o.sourcesstate,o.imgurl,o.createuser,o.updateuser,o.createdate,o.updatedate,kp.id_key FROM keyplaces kp,otherlocales o WHERE kp.type='OTHERLOCALES' AND kp.id = o.id AND o.type = (SELECT id FROM propertydicts p WHERE p.displayname='餐饮服务' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='其他场所类型')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,remarks,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '餐饮服务场所' AND p.internalid = 207),'NEWFOODSERVICESPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '餐饮服务场所-其他' AND p.internalid = 20707),o.contactperson,o.mobilenumber,o.telephone,o.remark,o.createuser,o.updateuser,o.updatedate,o.createdate  FROM otherlocales o,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = o.id AND  kp.type='OTHERLOCALES' AND cpb.id_key = kp.id_key AND o.type = (SELECT id FROM propertydicts p WHERE p.displayname='餐饮服务' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='其他场所类型')));


--其他场所(个体诊所)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,o.attentionextent,kp.address,CASE WHEN o.isemphasis is null THEN 0 ELSE o.isemphasis END ,o.isemphasisdate,o.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,o.sourcesstate,o.imgurl,o.createuser,o.updateuser,o.createdate,o.updatedate,kp.id_key FROM keyplaces kp,otherlocales o WHERE kp.type='OTHERLOCALES' AND kp.id = o.id AND o.type = (SELECT id FROM propertydicts p WHERE p.displayname='个体诊所' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='其他场所类型')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,remarks,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '单位' AND p.internalid = 1),(SELECT id FROM  propertydicts p WHERE p.displayname = '医院' AND p.internalid = 103),'NEWHOSPITAL',(SELECT id FROM  propertydicts p WHERE p.displayname = '门诊部、医务室、诊所' AND p.internalid = 10308),o.contactperson,o.mobilenumber,o.telephone,o.remark,o.createuser,o.updateuser,o.updatedate,o.createdate  FROM otherlocales o,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = o.id AND  kp.type='OTHERLOCALES' AND cpb.id_key = kp.id_key AND o.type = (SELECT id FROM propertydicts p WHERE p.displayname='个体诊所' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='其他场所类型')));


--其他场所(电镀)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,o.attentionextent,kp.address,CASE WHEN o.isemphasis is null THEN 0 ELSE o.isemphasis END ,o.isemphasisdate,o.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,o.sourcesstate,o.imgurl,o.createuser,o.updateuser,o.createdate,o.updatedate,kp.id_key FROM keyplaces kp,otherlocales o WHERE kp.type='OTHERLOCALES' AND kp.id = o.id AND o.type = (SELECT id FROM propertydicts p WHERE p.displayname='电镀' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='其他场所类型')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,remarks,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '场所-其他' AND p.internalid = 210),'OTHERPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '电镀站' AND p.internalid = 21002),o.contactperson,o.mobilenumber,o.telephone,o.remark,o.createuser,o.updateuser,o.updatedate,o.createdate  FROM otherlocales o,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = o.id AND  kp.type='OTHERLOCALES' AND cpb.id_key = kp.id_key AND o.type = (SELECT id FROM propertydicts p WHERE p.displayname='电镀' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='其他场所类型')));



--其他场所(化妆品)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,o.attentionextent,kp.address,CASE WHEN o.isemphasis is null THEN 0 ELSE o.isemphasis END ,o.isemphasisdate,o.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,o.sourcesstate,o.imgurl,o.createuser,o.updateuser,o.createdate,o.updatedate,kp.id_key FROM keyplaces kp,otherlocales o WHERE kp.type='OTHERLOCALES' AND kp.id = o.id AND o.type = (SELECT id FROM propertydicts p WHERE p.displayname='化妆品' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='其他场所类型')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,remarks,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '场所-其他' AND p.internalid = 210),'OTHERPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '化妆品' AND p.internalid = 21003),o.contactperson,o.mobilenumber,o.telephone,o.remark,o.createuser,o.updateuser,o.updatedate,o.createdate  FROM otherlocales o,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = o.id AND  kp.type='OTHERLOCALES' AND cpb.id_key = kp.id_key AND o.type = (SELECT id FROM propertydicts p WHERE p.displayname='化妆品' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='其他场所类型')));



--其他场所(其他)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,o.attentionextent,kp.address,CASE WHEN o.isemphasis is null THEN 0 ELSE o.isemphasis END ,o.isemphasisdate,o.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,o.sourcesstate,o.imgurl,o.createuser,o.updateuser,o.createdate,o.updatedate,kp.id_key FROM keyplaces kp,otherlocales o WHERE kp.type='OTHERLOCALES' AND kp.id = o.id AND o.type = (SELECT id FROM propertydicts p WHERE p.displayname='其他' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='其他场所类型')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,remarks,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '场所' AND p.internalid = 2),(SELECT id FROM  propertydicts p WHERE p.displayname = '场所-其他' AND p.internalid = 210),'OTHERPLACE',(SELECT id FROM  propertydicts p WHERE p.displayname = '场所-其他' AND p.internalid = 21009),o.contactperson,o.mobilenumber,o.telephone,o.remark,o.createuser,o.updateuser,o.updatedate,o.createdate  FROM otherlocales o,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = o.id AND  kp.type='OTHERLOCALES' AND cpb.id_key = kp.id_key AND o.type = (SELECT id FROM propertydicts p WHERE p.displayname='其他' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='其他场所类型')));


--其他场所(药品)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,o.attentionextent,kp.address,CASE WHEN o.isemphasis is null THEN 0 ELSE o.isemphasis END ,o.isemphasisdate,o.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,o.sourcesstate,o.imgurl,o.createuser,o.updateuser,o.createdate,o.updatedate,kp.id_key FROM keyplaces kp,otherlocales o WHERE kp.type='OTHERLOCALES' AND kp.id = o.id AND o.type = (SELECT id FROM propertydicts p WHERE p.displayname='药品' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='其他场所类型')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,remarks,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '单位' AND p.internalid = 1),(SELECT id FROM  propertydicts p WHERE p.displayname = '医院' AND p.internalid = 103),'NEWHOSPITAL',(SELECT id FROM  propertydicts p WHERE p.displayname = '药店、药房' AND p.internalid = 10315),o.contactperson,o.mobilenumber,o.telephone,o.remark,o.createuser,o.updateuser,o.updatedate,o.createdate  FROM otherlocales o,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = o.id AND  kp.type='OTHERLOCALES' AND cpb.id_key = kp.id_key AND o.type = (SELECT id FROM propertydicts p WHERE p.displayname='药品' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='其他场所类型')));


--其他场所(医疗器械)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,o.attentionextent,kp.address,CASE WHEN o.isemphasis is null THEN 0 ELSE o.isemphasis END ,o.isemphasisdate,o.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,o.sourcesstate,o.imgurl,o.createuser,o.updateuser,o.createdate,o.updatedate,kp.id_key FROM keyplaces kp,otherlocales o WHERE kp.type='OTHERLOCALES' AND kp.id = o.id AND o.type = (SELECT id FROM propertydicts p WHERE p.displayname='医疗器械' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='其他场所类型')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,remarks,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '单位' AND p.internalid = 1),(SELECT id FROM  propertydicts p WHERE p.displayname = '医院' AND p.internalid = 103),'NEWHOSPITAL',(SELECT id FROM  propertydicts p WHERE p.displayname = '医疗器械' AND p.internalid = 10316),o.contactperson,o.mobilenumber,o.telephone,o.remark,o.createuser,o.updateuser,o.updatedate,o.createdate  FROM otherlocales o,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = o.id AND  kp.type='OTHERLOCALES' AND cpb.id_key = kp.id_key AND o.type = (SELECT id FROM propertydicts p WHERE p.displayname='医疗器械' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='其他场所类型')));


--其他场所(煤气点)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,o.attentionextent,kp.address,CASE WHEN o.isemphasis is null THEN 0 ELSE o.isemphasis END ,o.isemphasisdate,o.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,o.sourcesstate,o.imgurl,o.createuser,o.updateuser,o.createdate,o.updatedate,kp.id_key FROM keyplaces kp,otherlocales o WHERE kp.type='OTHERLOCALES' AND kp.id = o.id AND o.type = (SELECT id FROM propertydicts p WHERE p.displayname='煤气点' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='其他场所类型')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,remarks,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '单位' AND p.internalid = 1),(SELECT id FROM  propertydicts p WHERE p.displayname = '危化品存放单位' AND p.internalid = 104),'NEWDANGEROUSCHEMICALSUNIT',(SELECT id FROM  propertydicts p WHERE p.displayname = '天然气、煤气、液化气' AND p.internalid = 10403),o.contactperson,o.mobilenumber,o.telephone,o.remark,o.createuser,o.updateuser,o.updatedate,o.createdate  FROM otherlocales o,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = o.id AND  kp.type='OTHERLOCALES' AND cpb.id_key = kp.id_key AND o.type = (SELECT id FROM propertydicts p WHERE p.displayname='煤气点' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='其他场所类型')));


--其他场所(加油站)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,attentionextent,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,o.attentionextent,kp.address,CASE WHEN o.isemphasis is null THEN 0 ELSE o.isemphasis END ,o.isemphasisdate,o.isemphasisreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,o.sourcesstate,o.imgurl,o.createuser,o.updateuser,o.createdate,o.updatedate,kp.id_key FROM keyplaces kp,otherlocales o WHERE kp.type='OTHERLOCALES' AND kp.id = o.id AND o.type = (SELECT id FROM propertydicts p WHERE p.displayname='加油站' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='其他场所类型')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,mobilenumber,telephone,remarks,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '单位' AND p.internalid = 1),(SELECT id FROM  propertydicts p WHERE p.displayname = '危化品存放单位' AND p.internalid = 104),'NEWDANGEROUSCHEMICALSUNIT',(SELECT id FROM  propertydicts p WHERE p.displayname = '油库' AND p.internalid = 10402),o.contactperson,o.mobilenumber,o.telephone,o.remark,o.createuser,o.updateuser,o.updatedate,o.createdate  FROM otherlocales o,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = o.id AND  kp.type='OTHERLOCALES' AND cpb.id_key = kp.id_key AND o.type = (SELECT id FROM propertydicts p WHERE p.displayname='加油站' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='其他场所类型')));


--实有单位(国家行政机关)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,kp.address,CASE WHEN a.isemphasis is null THEN 0 ELSE a.isemphasis  END,a.logouttime,a.logoutreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,a.sourcesstate,a.imgurl,a.createuser,a.updateuser,a.createdate,a.updatedate,kp.id_key FROM keyplaces kp,actualCompany a WHERE kp.type='ACTUALCOMPANY' AND kp.id = a.id AND a.companytype = (SELECT id FROM propertydicts p WHERE p.displayname='国家行政机关' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='实有企业_单位类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,telephone,faxnumber,area,remarks,businesslicenseno,orgcode,registeredcapitalno,begintime,endtime,generalstorage,countno,generalmanage,generalmente,economicnature,managementlevel,firelevel,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '单位' AND p.internalid = 1),(SELECT id FROM  propertydicts p WHERE p.displayname = '党政机关' AND p.internalid = 101),'PARTYGOVERNMENTORGANCOMPANY',(SELECT id FROM  propertydicts p WHERE p.displayname = '国家行政机关' AND p.internalid = 10101),a.corporaterepresentative,a.telephone,a.facsimile,a.businessscope,a.remark,a.businesslicenseno,a.orgcode,a.registeredcapital,a.registrationdate,a.expirydate,a.registrationaddress,a.employeesnum,a.competentdepartment,a.supervisorydepartment,a.economicnature,a.supervisorylevel,a.firefightinglevel,a.createuser,a.updateuser,a.createdate,a.updatedate  FROM actualCompany a,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = a.id AND  kp.type='ACTUALCOMPANY' AND cpb.id_key = kp.id_key AND a.companytype = (SELECT id FROM propertydicts p WHERE p.displayname='国家行政机关' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='实有企业_单位类别')));



--实有单位(政府)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,kp.address,CASE WHEN a.isemphasis is null THEN 0 ELSE a.isemphasis  END,a.logouttime,a.logoutreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,a.sourcesstate,a.imgurl,a.createuser,a.updateuser,a.createdate,a.updatedate,kp.id_key FROM keyplaces kp,actualCompany a WHERE kp.type='ACTUALCOMPANY' AND kp.id = a.id AND a.companytype = (SELECT id FROM propertydicts p WHERE p.displayname='政府' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='实有企业_单位类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,telephone,faxnumber,area,remarks,businesslicenseno,orgcode,registeredcapitalno,begintime,endtime,generalstorage,countno,generalmanage,generalmente,economicnature,managementlevel,firelevel,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '单位' AND p.internalid = 1),(SELECT id FROM  propertydicts p WHERE p.displayname = '党政机关' AND p.internalid = 101),'PARTYGOVERNMENTORGANCOMPANY',(SELECT id FROM  propertydicts p WHERE p.displayname = '政府' AND p.internalid = 10102),a.corporaterepresentative,a.telephone,a.facsimile,a.businessscope,a.remark,a.businesslicenseno,a.orgcode,a.registeredcapital,a.registrationdate,a.expirydate,a.registrationaddress,a.employeesnum,a.competentdepartment,a.supervisorydepartment,a.economicnature,a.supervisorylevel,a.firefightinglevel,a.createuser,a.updateuser,a.createdate,a.updatedate  FROM actualCompany a,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = a.id AND  kp.type='ACTUALCOMPANY' AND cpb.id_key = kp.id_key AND a.companytype = (SELECT id FROM propertydicts p WHERE p.displayname='政府' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='实有企业_单位类别')));


--实有单位(国有企业)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,kp.address,CASE WHEN a.isemphasis is null THEN 0 ELSE a.isemphasis  END,a.logouttime,a.logoutreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,a.sourcesstate,a.imgurl,a.createuser,a.updateuser,a.createdate,a.updatedate,kp.id_key FROM keyplaces kp,actualCompany a WHERE kp.type='ACTUALCOMPANY' AND kp.id = a.id AND a.companytype = (SELECT id FROM propertydicts p WHERE p.displayname='国有企业' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='实有企业_单位类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,telephone,faxnumber,area,remarks,businesslicenseno,orgcode,registeredcapitalno,begintime,endtime,generalstorage,countno,generalmanage,generalmente,economicnature,managementlevel,firelevel,haslicense,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '单位' AND p.internalid = 1),(SELECT id FROM  propertydicts p WHERE p.displayname = '单位-其他' AND p.internalid = 105),'OTHERCOMPANY',(SELECT id FROM  propertydicts p WHERE p.displayname = '单位-其他' AND p.internalid = 10507),a.corporaterepresentative,a.telephone,a.facsimile,a.businessscope,a.remark,a.businesslicenseno,a.orgcode,a.registeredcapital,a.registrationdate,a.expirydate,a.registrationaddress,a.employeesnum,a.competentdepartment,a.supervisorydepartment,a.economicnature,a.supervisorylevel,a.firefightinglevel,CASE WHEN a.businesslicenseno is not null OR a.orgcode is not null THEN 1 ELSE 0 END,a.createuser,a.updateuser,a.createdate,a.updatedate  FROM actualCompany a,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = a.id AND  kp.type='ACTUALCOMPANY' AND cpb.id_key = kp.id_key AND a.companytype = (SELECT id FROM propertydicts p WHERE p.displayname='国有企业' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='实有企业_单位类别')));


--实有单位(国有控股企业)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,kp.address,CASE WHEN a.isemphasis is null THEN 0 ELSE a.isemphasis  END,a.logouttime,a.logoutreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,a.sourcesstate,a.imgurl,a.createuser,a.updateuser,a.createdate,a.updatedate,kp.id_key FROM keyplaces kp,actualCompany a WHERE kp.type='ACTUALCOMPANY' AND kp.id = a.id AND a.companytype = (SELECT id FROM propertydicts p WHERE p.displayname='国有控股企业' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='实有企业_单位类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,telephone,faxnumber,area,remarks,businesslicenseno,orgcode,registeredcapitalno,begintime,endtime,generalstorage,countno,generalmanage,generalmente,economicnature,managementlevel,firelevel,haslicense,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '单位' AND p.internalid = 1),(SELECT id FROM  propertydicts p WHERE p.displayname = '单位-其他' AND p.internalid = 105),'OTHERCOMPANY',(SELECT id FROM  propertydicts p WHERE p.displayname = '单位-其他' AND p.internalid = 10507),a.corporaterepresentative,a.telephone,a.facsimile,a.businessscope,a.remark,a.businesslicenseno,a.orgcode,a.registeredcapital,a.registrationdate,a.expirydate,a.registrationaddress,a.employeesnum,a.competentdepartment,a.supervisorydepartment,a.economicnature,a.supervisorylevel,a.firefightinglevel,CASE WHEN a.businesslicenseno is not null OR a.orgcode is not null THEN 1 ELSE 0 END,a.createuser,a.updateuser,a.createdate,a.updatedate  FROM actualCompany a,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = a.id AND  kp.type='ACTUALCOMPANY' AND cpb.id_key = kp.id_key AND a.companytype = (SELECT id FROM propertydicts p WHERE p.displayname='国有控股企业' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='实有企业_单位类别')));


--实有单位(外资企业)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,kp.address,CASE WHEN a.isemphasis is null THEN 0 ELSE a.isemphasis  END,a.logouttime,a.logoutreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,a.sourcesstate,a.imgurl,a.createuser,a.updateuser,a.createdate,a.updatedate,kp.id_key FROM keyplaces kp,actualCompany a WHERE kp.type='ACTUALCOMPANY' AND kp.id = a.id AND a.companytype = (SELECT id FROM propertydicts p WHERE p.displayname='外资企业' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='实有企业_单位类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,telephone,faxnumber,area,remarks,businesslicenseno,orgcode,registeredcapitalno,begintime,endtime,generalstorage,countno,generalmanage,generalmente,economicnature,managementlevel,firelevel,haslicense,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '单位' AND p.internalid = 1),(SELECT id FROM  propertydicts p WHERE p.displayname = '单位-其他' AND p.internalid = 105),'OTHERCOMPANY',(SELECT id FROM  propertydicts p WHERE p.displayname = '单位-其他' AND p.internalid = 10507),a.corporaterepresentative,a.telephone,a.facsimile,a.businessscope,a.remark,a.businesslicenseno,a.orgcode,a.registeredcapital,a.registrationdate,a.expirydate,a.registrationaddress,a.employeesnum,a.competentdepartment,a.supervisorydepartment,a.economicnature,a.supervisorylevel,a.firefightinglevel,CASE WHEN a.businesslicenseno is not null OR a.orgcode is not null THEN 1 ELSE 0 END,a.createuser,a.updateuser,a.createdate,a.updatedate  FROM actualCompany a,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = a.id AND  kp.type='ACTUALCOMPANY' AND cpb.id_key = kp.id_key AND a.companytype = (SELECT id FROM propertydicts p WHERE p.displayname='外资企业' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='实有企业_单位类别')));



--实有单位(合资企业)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,kp.address,CASE WHEN a.isemphasis is null THEN 0 ELSE a.isemphasis  END,a.logouttime,a.logoutreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,a.sourcesstate,a.imgurl,a.createuser,a.updateuser,a.createdate,a.updatedate,kp.id_key FROM keyplaces kp,actualCompany a WHERE kp.type='ACTUALCOMPANY' AND kp.id = a.id AND a.companytype = (SELECT id FROM propertydicts p WHERE p.displayname='合资企业' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='实有企业_单位类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,telephone,faxnumber,area,remarks,businesslicenseno,orgcode,registeredcapitalno,begintime,endtime,generalstorage,countno,generalmanage,generalmente,economicnature,managementlevel,firelevel,haslicense,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '单位' AND p.internalid = 1),(SELECT id FROM  propertydicts p WHERE p.displayname = '单位-其他' AND p.internalid = 105),'OTHERCOMPANY',(SELECT id FROM  propertydicts p WHERE p.displayname = '单位-其他' AND p.internalid = 10507),a.corporaterepresentative,a.telephone,a.facsimile,a.businessscope,a.remark,a.businesslicenseno,a.orgcode,a.registeredcapital,a.registrationdate,a.expirydate,a.registrationaddress,a.employeesnum,a.competentdepartment,a.supervisorydepartment,a.economicnature,a.supervisorylevel,a.firefightinglevel,CASE WHEN a.businesslicenseno is not null OR a.orgcode is not null THEN 1 ELSE 0 END,a.createuser,a.updateuser,a.createdate,a.updatedate  FROM actualCompany a,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = a.id AND  kp.type='ACTUALCOMPANY' AND cpb.id_key = kp.id_key AND a.companytype = (SELECT id FROM propertydicts p WHERE p.displayname='合资企业' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='实有企业_单位类别')));


--实有单位(私营企业)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,kp.address,CASE WHEN a.isemphasis is null THEN 0 ELSE a.isemphasis  END,a.logouttime,a.logoutreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,a.sourcesstate,a.imgurl,a.createuser,a.updateuser,a.createdate,a.updatedate,kp.id_key FROM keyplaces kp,actualCompany a WHERE kp.type='ACTUALCOMPANY' AND kp.id = a.id AND a.companytype = (SELECT id FROM propertydicts p WHERE p.displayname='私营企业' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='实有企业_单位类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));

--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,telephone,faxnumber,area,remarks,businesslicenseno,orgcode,registeredcapitalno,begintime,endtime,generalstorage,countno,generalmanage,generalmente,economicnature,managementlevel,firelevel,haslicense,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '单位' AND p.internalid = 1),(SELECT id FROM  propertydicts p WHERE p.displayname = '单位-其他' AND p.internalid = 105),'OTHERCOMPANY',(SELECT id FROM  propertydicts p WHERE p.displayname = '单位-其他' AND p.internalid = 10507),a.corporaterepresentative,a.telephone,a.facsimile,a.businessscope,a.remark,a.businesslicenseno,a.orgcode,a.registeredcapital,a.registrationdate,a.expirydate,a.registrationaddress,a.employeesnum,a.competentdepartment,a.supervisorydepartment,a.economicnature,a.supervisorylevel,a.firefightinglevel,CASE WHEN a.businesslicenseno is not null OR a.orgcode is not null THEN 1 ELSE 0 END,a.createuser,a.updateuser,a.createdate,a.updatedate  FROM actualCompany a,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = a.id AND  kp.type='ACTUALCOMPANY' AND cpb.id_key = kp.id_key AND a.companytype = (SELECT id FROM propertydicts p WHERE p.displayname='私营企业' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='实有企业_单位类别')));


--实有单位(事业单位)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,kp.address,CASE WHEN a.isemphasis is null THEN 0 ELSE a.isemphasis  END,a.logouttime,a.logoutreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,a.sourcesstate,a.imgurl,a.createuser,a.updateuser,a.createdate,a.updatedate,kp.id_key FROM keyplaces kp,actualCompany a WHERE kp.type='ACTUALCOMPANY' AND kp.id = a.id AND a.companytype = (SELECT id FROM propertydicts p WHERE p.displayname='事业单位' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='实有企业_单位类别')) and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,telephone,faxnumber,area,remarks,businesslicenseno,orgcode,registeredcapitalno,begintime,endtime,generalstorage,countno,generalmanage,generalmente,economicnature,managementlevel,firelevel,haslicense,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '单位' AND p.internalid = 1),(SELECT id FROM  propertydicts p WHERE p.displayname = '单位-其他' AND p.internalid = 105),'OTHERCOMPANY',(SELECT id FROM  propertydicts p WHERE p.displayname = '单位-其他' AND p.internalid = 10507),a.corporaterepresentative,a.telephone,a.facsimile,a.businessscope,a.remark,a.businesslicenseno,a.orgcode,a.registeredcapital,a.registrationdate,a.expirydate,a.registrationaddress,a.employeesnum,a.competentdepartment,a.supervisorydepartment,a.economicnature,a.supervisorylevel,a.firefightinglevel,CASE WHEN a.businesslicenseno is not null OR a.orgcode is not null THEN 1 ELSE 0 END,a.createuser,a.updateuser,a.createdate,a.updatedate  FROM actualCompany a,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = a.id AND  kp.type='ACTUALCOMPANY' AND cpb.id_key = kp.id_key AND a.companytype = (SELECT id FROM propertydicts p WHERE p.displayname='事业单位' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='实有企业_单位类别')));


--实有单位(类型不选择)
--单位场所基础表
INSERT INTO companyplacebase_temp(id,org,orginternalcode,name,address,isemphasis,isemphasisdate,isemphasisreason,centerlon,centerlat,centerlon2,centerlat2,featureid,builddataid,sourcesstate,imgurl,createuser,updateuser,createdate,updatedate,id_key)
(SELECT s_companyplacebase.nextval,kp.orgid,kp.orginternalcode,kp.name,kp.address,CASE WHEN a.isemphasis is null THEN 0 ELSE a.isemphasis  END,a.logouttime,a.logoutreason,kp.centerlon,kp.centerlat,kp.centerlon2,kp.centerlat2,kp.featureid,kp.builddataid,a.sourcesstate,a.imgurl,a.createuser,a.updateuser,a.createdate,a.updatedate,kp.id_key FROM keyplaces kp,actualCompany a WHERE kp.type='ACTUALCOMPANY' AND kp.id = a.id AND a.companytype is null  and kp.createdate > to_date('2014-7-19','yyyy-mm-dd'));


--单位场所表
INSERT INTO companyplace_temp(id,baseid,type,classification,classificationen,category,username,telephone,faxnumber,area,remarks,businesslicenseno,orgcode,registeredcapitalno,begintime,endtime,generalstorage,countno,generalmanage,generalmente,economicnature,managementlevel,firelevel,haslicense,createuser,updateuser,createdate,updatedate)
(SELECT s_companyplace.nextval,cpb.id,(SELECT id FROM  propertydicts p WHERE p.displayname = '单位' AND p.internalid = 1),(SELECT id FROM  propertydicts p WHERE p.displayname = '单位-其他' AND p.internalid = 105),'OTHERCOMPANY',(SELECT id FROM  propertydicts p WHERE p.displayname = '单位-其他' AND p.internalid = 10507),a.corporaterepresentative,a.telephone,a.facsimile,a.businessscope,a.remark,a.businesslicenseno,a.orgcode,a.registeredcapital,a.registrationdate,a.expirydate,a.registrationaddress,a.employeesnum,a.competentdepartment,a.supervisorydepartment,a.economicnature,a.supervisorylevel,a.firefightinglevel,CASE WHEN a.businesslicenseno is not null OR a.orgcode is not null THEN 1 ELSE 0 END,a.createuser,a.updateuser,a.createdate,a.updatedate  FROM actualCompany a,keyplaces kp,companyplacebase_temp cpb WHERE kp.id = a.id AND  kp.type='ACTUALCOMPANY' AND cpb.id_key = kp.id_key AND a.companytype is null);


--单位场所业务表中间表
INSERT INTO companyplacebusslation_temp(baseid,businessid)
(SELECT cp.id,cpb.id FROM companyplace_temp cp,companyplacebusiness_temp cpb WHERE cp.baseid =cpb.companyplacebaseid);

--单位场所信息插入临时表
insert into complaceInfo_temp(id_key,classificationen,companyplaceId,scaletype)
(select cb.id_key,cp.classificationen,cp.id,cp.scaletype from companyplace_temp cp, companyplacebase_temp cb where cp.baseid = cb.id);

--更新单位场所基础表PCORMOBILE字段
update companyplacebase_temp c set c.pcormobile=0 where c.pcormobile is null;

--单位场所基础临时表删除KeyPlace表关联字段Id_Key
ALTER TABLE companyplacebase_temp DROP COLUMN id_key;

--单位场业务临时表删除单位场所表关联字段baseID
ALTER TABLE companyplacebusiness_temp DROP COLUMN companyplacebaseid;

--删除冗余新医院类型字段
ALTER TABLE hospitals DROP COLUMN newtype;

--删除冗余新学校类型字段
ALTER TABLE schools DROP COLUMN newtype;
