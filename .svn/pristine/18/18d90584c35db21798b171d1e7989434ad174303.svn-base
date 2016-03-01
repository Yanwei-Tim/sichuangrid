(function($) {
$.fn.extend({
    primaryOrgMemberAutoComplete : function(option) {
        var self = $(this);
        var defaultPostData = {
            // address:'',
            // community:'',
            // block:'',
            // unit:'',
            // searchType : function(){return defaultOption.searchType},
            // orgId :function(){return getCurrentOrgId();},
            rows : 10
        };
        $.extend(defaultPostData,option.postData);

        function getPostData(value){
            return $.extend({"memberName" : value,"orgId":$("#fatesonOrgId").val(),"primaryOrgMember.primaryOrgId":$("#primaryOrgId").val()},defaultPostData);
        }

        function getDisplayLabel(rowData){
            var genderid= rowData.gender.id;
           var genderText=   getTextByValue($("#gender"),genderid);
           return  "姓名:"+rowData.name+"；性别:"+genderText;
        }

        function getTextByValue(o,value){
            var result="";
            var count=o.get(0).options.length;
            for(var i=0;i<count;i++)
            {
                if(o.get(0).options[i].value == value){
                  result=  o.get(0).options[i].text;
                  break;
                }
            }
            return result;
        }
        function clearContent(){
            $("#gender").val("");
            $("#dutyId").val("");
            $("#job").val("")   ;
            $("#year").val("")      ;
            $("#mobileNumber").val("");
            $("#telephone").val("");
            $("#remark").val("");
        }
        var defaultOption={
            delay : 500,
            minLength : 0,
            searchByMemberName:false,
            searchLevel:'',
            close:function(){
                //self.removeClass("errorInput").poshytip('disable');
            },
            source : function(request, response) {
                if(request.term==''){
                    request.term=null;
                    $("#serviceTeamMemberForm")[0].reset();
                    return;
                    //response();
                }
                $.ajax({
                    url : PATH + "/baseinfo/primaryOrgMemberManage/findOrgMembersByName.action",
                    data : getPostData(request.term),
                    success : function(_data) {
                        self.data("data",_data);
                        if(_data.length==0){
                              //没查到结果的话
                              clearContent();
                         _data={"":""};
                           response($.map(_data, function() {
                            return {
                              noRecord:"",
                              label     : function(){return "没查询到记录"},
                               value     : function(){return  $("#name").val()}
                           };
                        }));


                        }else{
                        response($.map(_data, function(rowData) {
                            return {
                                dutyId      : rowData.dutyId.id,
                                genderId    : rowData.gender.id,
                                position    : rowData.position,
                                remark      : rowData.remark,
                                telephone   : rowData.telephone,
                                year        : rowData.year,
                                mobile      : rowData.mobile,
                                label     : function(){return getDisplayLabel(rowData)},
                                value     : function(){return rowData.name}
                            };
                        }));
                        }
                    },
                    error : function() {
                        $.messageBox( {
                            message : "搜索失败，请重新登入！",
                            level : "error"
                        });
                    }
                });
            }
        };
        $.extend(defaultOption,option);
        defaultOption.select=function(event,ui){
            if (option.select!=null){
                option.select(event,ui);
            }
        };
        $(this).autocomplete(defaultOption);
    }
});

})(jQuery);
