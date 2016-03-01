--重点人员里面“其它人员”放在最后，和艾滋人员换下位置
update permissions pm set pm.indexid=7 where pm.ename='aidspopulationsManagement';
update permissions pm set pm.indexid=8 where pm.ename='otherAttentionPersonnelManagement';
commit;