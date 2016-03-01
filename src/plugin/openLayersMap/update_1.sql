update permissions p  
set p.normalurl='/hotModuel/openLayersMap/system/layerManage/layerManage.jsp'
where p.ename='gisLayerManage';

update gisfunction set detailsurl='/baseinfo/dustbinManage/dispatchOperateByEncrypt.action?mode=view'||'&'||'location.id='
where moduleId=(select id from gismodulemanages where tablename='dustbin');

update gisfunction set detailFieldNameB='负责地址',detailFieldB='address'
where sonclassid=(select id from gisTypeManages where key='SCENICTRAFFIC');

commit;