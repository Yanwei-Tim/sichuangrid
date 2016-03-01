function serviceListCommonImport(dataType,templates,listName){
        $("#serviceListDialog").createDialog({
            width: 400,
            height: 230,
            title:'数据导入',
            url:PATH+'/common/import.jsp?isNew=1&dataType='+dataType+'&dialog=serviceListDialog&startRow=5&templates='+templates+'&listName='+listName,
            buttons:{
                "导入" : function(event){
                    $("#mForm").submit();
                },
                "关闭" : function(){
                    $(this).dialog("close");
                }
            },
            shouldEmptyHtml:false
        });
}