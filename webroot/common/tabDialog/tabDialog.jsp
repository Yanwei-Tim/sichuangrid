<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="dlgBox">
    <div class="dlgLeft">
        <div class="dlgHendBox">
            <jsp:include page="/common/header.jsp"/>
        </div>
    </div>
    <div class="dlgRightAdd">
        <div class="ui-widget-overlay dlgUrlBoxLoading" id="dlgUrlBoxLoading" style="display: none;">
            <div class="loadingCtt"><img src="${resource_path}/resource/images/dlgloading1.gif">
                <div>正在加载中，请稍候…</div>
            </div>
        </div>
        <div class="dlgUrlBox" id="tabDialogComponent">
        	<ul></ul>
        </div>
        <div class="clear"></div>
    </div>
</div>

<input id="tabDialogId" type="hidden" value='<s:property value="#parameters.id"/>'/>
<input id="tabDialogType" type="hidden" value='<s:property value="#parameters.type"/>'/>
<input id="imageType" type="hidden" value='<s:property value="#parameters.imagetype"/>'/>
<input id="operateSource" type="hidden" value='<s:property value="#parameters.operateSource"/>'/>