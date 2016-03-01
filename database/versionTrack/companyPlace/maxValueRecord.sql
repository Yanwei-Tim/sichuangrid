--keyplaces中取得单位场所最大值总条数
select max(id_key) from keyplaces;
--治安负责人服务人员和服务服务对象的关联关系数据关联
select max(id) from servicememberhasobject;
--治安负责人服务团队和服务对象的关联关系数据关联
select max(id) from serviceTeamHasObject;
--巡场情况数据关联
select max(id) from serviceRecordRelyObject;
--服务记录和人员的关联关系
select max(id) from serviceRecordRelyMember;
--服务记录和人员的关联关系附件
select max(id) from serviceRecordHasAttachments;
--服务记录
select max(id) from serviceRecords;