<?php
if (!defined('IN_CKFINDER')) exit;

/**
 * Include base XML command handler
 */
require_once CKFINDER_CONNECTOR_LIB_DIR . "/CommandHandler/XmlCommandHandlerBase.php";

class CustomACL  extends CKFinder_Connector_CommandHandler_XmlCommandHandlerBase{
	 private $command;
      /**
     * handle request and build XML
     * @access protected
     */
    function buildXml()
    {
		if(in_array($this->command, array('FileUpload','QuickUpload'))){
			$_handler = & CKFinder_Connector_Core_Factory::getInstance('ErrorHandler_FileUpload');
			$_handler ->throwError(CKFINDER_CONNECTOR_ERROR_UNAUTHORIZED);
			return;
		}
		if($this->command=='Thumbnail'){
			$_handler = & CKFinder_Connector_Core_Factory::getInstance('ErrorHandler_Http');
			$_handler ->throwError(CKFINDER_CONNECTOR_ERROR_UNAUTHORIZED);
			return;
		}
        $this->_errorHandler->throwError(CKFINDER_CONNECTOR_ERROR_UNAUTHORIZED);
    }


    /**
     * @access public
     */
    function beforeExecuteCommand( &$command ){
		$_config =& CKFinder_Connector_Core_Factory::getInstance("Core_Config");
		$permission = $_config->getPermission();
		$this->command = $command;
		if(!$this->isLogined($permission) || in_array($command, array('CreateFolder', 'DeleteFolder', 'RenameFolder'))){
            $this->sendResponse();
            return false;
		}
		if(in_array($command, array('DownloadFile', 'GetFiles','Thumbnail'))){
			if(!$this->checkViewPermission($permission)){
				 $this->sendResponse();
                 return false;
			}
		}
		if(in_array($command, array('CopyFiles', 'MoveFiles','DeleteFile','FileUpload','QuickUpload','SaveFile','ImageResize','RenameFile'))){
			if(!$this->checkEditPermission($command,$permission)){
				 $this->sendResponse();
                 return false;
			}
		}
        return true ;
    }

	function isLogined($permission){
		return $permission['logined'];
	}

	function checkViewPermission($permission){
		if($permission['isAdmin']) return true;
		if($permission['isManager']) return true;
		if($permission['edit']) return true;
		if($permission['view']) return true;
		return false;
	}

	function checkEditPermission($command,$permission){
		if($permission['isAdmin']) return true;

		$filePath = $this->_currentFolder->getServerPath();
		if($command <> 'MoveFiles'){
			return $this->checkPathEditPermission($filePath,$permission);
		}else{
		    //移动文件夹需要具备两个文件夹的权限
			return $this->checkPathEditPermission($filePath,$permission) and $this->checkSourcePath($permission);
		}
	}

     //源目录是否有权限
    function checkSourcePath($permission){
		$_config =& CKFinder_Connector_Core_Factory::getInstance("Core_Config");
		if (!empty($_POST['files']) && is_array($_POST['files'])) {
			foreach ($_POST['files'] as $index => $arr) {
				if (empty($arr['name'])) {
					continue;
				}
                if (!isset($arr['name'], $arr['type'], $arr['folder'])) {
                        return true;
                }
                $type = $arr['type'];
                // client path
                $path = CKFinder_Connector_Utils_FileSystem::convertToFilesystemEncoding($arr['folder']);
				$sourcePath = $_config->getResourceTypeConfig($type)->getDirectory().$path;
				return $this->checkPathEditPermission($sourcePath,$permission);
			}
		}

	}

	function checkPathEditPermission($filepath,$permission){
		$accessIni = parse_ini_file(CKFinder_Connector_Utils_FileSystem::combinePaths($filepath, 'access.ini'));
		//本级目录且有权限

		if($accessIni['internalCode'] == $permission['internalCode'] && $permission['edit']) return true;
		
		//下级目录且有超级管理员权限
		if($permission['isManager'] and ( strpos($accessIni['internalCode'],$permission['internalCode']) === 0) ){
			return true;
		}
		return false;
    }
}

$customACL = new CustomACL();
$config['Hooks']['BeforeExecuteCommand'][] = array($customACL, 'beforeExecuteCommand');

