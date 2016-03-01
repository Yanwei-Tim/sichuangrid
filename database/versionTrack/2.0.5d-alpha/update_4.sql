--为严重精神障碍患者，吸毒人员创建索引（任务清单）
--组织结构orgCode
create index i_druggyTask_orgCode on druggyTask(Orginternalcode);
create index i_mentalPatientTask_orgCode on Mentalpatienttask(Orginternalcode);

--是否签收, id
create index i_druggyTask_id on druggyTask(id);
create index i_druggyTask_status on druggyTask(status);
create index i_mentalPatientTask_id on Mentalpatienttask(id);
create index i_mentalPatientTask_status on Mentalpatienttask(status);

--记录起始时间，结束时间

