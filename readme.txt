社会服务管理综合平台

系统环境说明：
jdb1.6
ActiveMQ 5.6.0 Release

1.如果应用JTA事务后发生异常：ERROR IN RECOVERY，请执行以下SQL语句：
grant select on sys.dba_pending_transactions to youdbname; 
grant select on sys.pending_trans$ to youdbname; 
grant select on sys.dba_2pc_pending to youdbname;   
grant execute on sys.dbms_system to youdbname;
   

2、由于系统使用了flash作为上传组件
对flash的版本要求是：等于或高于Flash Player 9.0.24


基础信息开发列表:(实例：moduleName=FloatingPopulation lowerCaseModuleName=floatingPopulation )

列表数据=/baseinfo/${lowerCaseModuleName}Manage/${lowerCaseModuleName}List.action?${lowerCaseModuleName}.isEmphasis=xxx&orgId=xxx

新增时基础信息页面=/baseinfo/${lowerCaseModuleName}Manage/dispatchOperate.action?mode=add&dailogName=${lowerCaseModuleName}Dialog
新增时业务信息页面=/baseinfo/${lowerCaseModuleName}Manage/dispatch${moduleName}Business.action?mode=add&dailogName=${lowerCaseModuleName}Dialog

修改时基础信息页面=/baseinfo/${lowerCaseModuleName}Manage/dispatchOperate.action?population.id=xxx&mode=edit&dailogName=${lowerCaseModuleName}Dialog
修改时业务信息页面=/baseinfo/${lowerCaseModuleName}Manage/dispatch${moduleName}Business.action?population.id=xxx&mode=edit&dailogName=${lowerCaseModuleName}Dialog

查看页面=${path}/baseinfo/${lowerCaseModuleName}Manage/dispatchOperate.action?mode=view&population.id=1
查询页面=${path}/baseinfo/${lowerCaseModuleName}Manage/dispatchOperate.action?mode=search&orgId=1

取下/重新关注=${path}/baseinfo/${lowerCaseModuleName}Manage/updateEmphasiseById.action

记录删除=${path}/baseinfo/${lowerCaseModuleName}Manage/delete${moduleName}ByIds.action?deleteIds=1,2,3,


条件查询=${path}/baseinfo/search${moduleName}/find${moduleName}sByQueryCondition.action 参数：search${moduleName}Vo.xxxx post
数据导出=${path}/baseinfo/search${moduleName}/download${moduleName}.action
按身份证快速查询=${path}/baseinfo/search${moduleName}/fastSearch.action?orgId=orgId&search${moduleName}Vo.isEmphasis=1&search${moduleName}Vo.idCardNo=xxxx
按身份证快速查询=${path}/baseinfo/search${moduleName}/fastSearch.action?orgId=orgId&search${moduleName}Vo.isEmphasis=1&search${moduleName}Vo.name=xxxx
