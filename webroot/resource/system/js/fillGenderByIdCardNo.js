/*
 * 根据身份证号码获取性别
 * idCardNo 身份证号码
 * dictFieldName字典项的id名称
 * fieldName隐藏域的 id名称
 * isDict 是否是字典项
*/
function fillGenderByIdCardNo(idCardNo,dictFieldName,fieldName,isDict){
    var sex;
    if(idCardNo==null||idCardNo=="" || typeof(idCardNo)=="undefined"){
        return;
    }
    if(idCardNo.length!=15 && idCardNo.length!=18){
        return;
    }

    if (15 == idCardNo.length) { //15位身份证号码
        if (parseInt(idCardNo.charAt(14) / 2) * 2 != idCardNo.charAt(14))
            sex = '男';
        else
            sex = '女';
     }
    if (18 == idCardNo.length) { //18位身份证号码
        if (parseInt(idCardNo.charAt(16) / 2) * 2 != idCardNo.charAt(16))
           sex = '男';
        else
          sex = '女';
    }
    showGender(sex,dictFieldName,fieldName,isDict);
}

function showGender(gender,dictFieldName,fieldName,isDict){
    if(isDict){
        $("#"+dictFieldName + " option").each(function(value){
            if($(this).text()==gender)  {
                $(this).attr("selected",true);
                $("#"+fieldName).val($(this).val())
            }
        });
    }
}