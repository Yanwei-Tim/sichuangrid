#!/usr/bin/php
<?php

/**
   * 发布脚本，用于发布分布式java应用程序
   * TODO List:
   *  关闭jetty服务后，需要去测试是否正常关闭. 目前潜在的问题是，可能服务没有关闭又重启服务。
   *  启动Jetty服务后，测试相关的服务是否正常启动
   *  使用请看readme.txt
   * @author     Kent Wei
   * @version     1.0
*/	


require_once './config.php';
require_once './hack.php';

if(checkEnvironment() && parseParams($argv,$argc - 1) ){
   dispatchPublish();
}



function dispatchPublish(){
	global $mode,$baseDir;
	$serverList = getServerList();
	if(in_array($mode, array('preview', 'publish','haltPublish','resPublish','resPreview','fileManagePreview','fileManagePublish'))){
		if($baseDir==null){
			echo "发布、模拟发布、停机、资源文件、文件管理系统等发布，baseDir必须指定，请用-b参数指定发布根目录\n";
			printUserage();
			return ;
		}
	}
	if($mode=='preview'){
		foreach ($serverList as $k => $v) {
		   echo "***************************************************************\n";
           echo "准备发布服务: {$k},目标机器: ".$v['serverName'].",发布模式为: $mode \n";
		   preview($k,$v);
		   echo "服务:{$k} 发布结束 \n";
        }
	}
	if($mode=='publish'){
		foreach ($serverList as $k => $v) {
		   echo "***************************************************************\n";
           echo "准备发布服务: {$k},目标机器: ".$v['serverName'].",发布模式为: $mode \n";
		   publishServer($k,$v);
		   echo "服务:{$k} 发布结束 \n";
        }
	}

	if($mode=='haltPublish'){
		foreach ($serverList as $k => $v) {
		   echo "***************************************************************\n";
           echo "准备发布服务: {$k},目标机器: ".$v['serverName'].",发布模式为: $mode \n";
		   haltPublish($k,$v);
		   echo "服务:{$k} 发布结束 \n";
        }
	}
	if($mode=='stopServer'){
		foreach ($serverList as $k => $v) {
		   echo "***************************************************************\n";
           echo "准备发布服务: {$k},目标机器: ".$v['serverName'].",发布模式为: $mode \n";
		   stopServer($k,$v);
		   echo "服务:{$k} 发布结束 \n";
        }
	}
	if($mode=='startServer'){
		foreach ($serverList as $k => $v) {
		   echo "***************************************************************\n";
           echo "准备发布服务: {$k},目标机器: ".$v['serverName'].",发布模式为: $mode \n";
		   startServer($k,$v);
		   echo "服务:{$k} 发布结束 \n";
        }
	}
	if($mode=='restartServer'){
		foreach ($serverList as $k => $v) {
		   echo "***************************************************************\n";
           echo "准备重新启动服务: {$k},目标机器: ".$v['serverName'].",发布模式为: $mode \n";
		   stopServer($k,$v);
		   startServer($k,$v);
		   echo "服务:{$k} 发布结束 \n";
        }
	}

	global $cacheServers;
	if($mode=='stopCache'){
		foreach ($cacheServers as $k => $v) {
		   echo "***************************************************************\n";
           echo "准备停止服务: {$k},目标机器: ".$v['serverName'].",发布模式为: $mode \n";
		   stopCache($k,$v);
		   echo "服务:{$k} 停止结束 \n";
        }
	}

	if($mode=='startCache'){
		foreach ($cacheServers as $k => $v) {
		   echo "***************************************************************\n";
           echo "准备启动服务: {$k},目标机器: ".$v['serverName'].",发布模式为: $mode \n";
		   startCache($k,$v);
		   echo "服务:{$k} 启动结束 \n";
        }
	}
	if($mode=='restartCache'){
		foreach ($cacheServers as $k => $v) {
		   echo "***************************************************************\n";
           echo "准备重启服务: {$k},目标机器: ".$v['serverName'].",发布模式为: $mode \n";
		   restartCache($k,$v);
		   echo "服务:{$k} 重启结束 \n";
        }
	}

	global  $squidServers;
	if($mode=='resPublish'){
		foreach ($squidServers as $k => $v) {
		   echo "***************************************************************\n";
           echo "准备发布资源文件: {$k},目标机器: ".$v['serverName'].",发布模式为: $mode \n";
		   publishResource($k,$v);
		   echo "发布资源文件到:{$k} 结束 \n";
        }
	}
	if($mode=='resPreview'){
		foreach ($squidServers as $k => $v) {
		   echo "***************************************************************\n";
           echo "准备模拟发布资源文件: {$k},目标机器: ".$v['serverName'].",发布模式为: $mode \n";
		   publishResource($k,$v);
		   echo "模拟发布资源文件到:{$k} 结束 \n";
        }
	}
	
	global $fileManageServers;
	if($mode=='fileManagePreview'){
		foreach ($fileManageServers as $k => $v) {
		   echo "***************************************************************\n";
           echo "准备模拟发布文件管理系统文件: {$k},目标机器: ".$v['serverName'].",发布模式为: $mode \n";
		   publishResource($k,$v);
		   echo "发布文件管理系统到:{$k} 结束 \n";
        }
	}
	if($mode=='fileManagePublish'){
		foreach ($fileManageServers as $k => $v) {
		   echo "***************************************************************\n";
           echo "准备发布文件管理系统文件: {$k},目标机器: ".$v['serverName'].",发布模式为: $mode \n";
		   publishResource($k,$v);
		   echo "发布文件管理系统到:{$k} 结束 \n";
        }
	}	
	
	global $forumServers;
	if($mode=='forumPreview'){
		foreach ($forumServers as $k => $v) {
		   echo "***************************************************************\n";
           echo "准备模拟发布文件管理系统文件: {$k},目标机器: ".$v['serverName'].",发布模式为: $mode \n";
		   publishResource($k,$v);
		   echo "发布文件管理系统到:{$k} 结束 \n";
        }
	}
	if($mode=='forumPublish'){
		foreach ($forumServers as $k => $v) {
		   echo "***************************************************************\n";
           echo "准备发布文件管理系统文件: {$k},目标机器: ".$v['serverName'].",发布模式为: $mode \n";
		   publishResource($k,$v);
		   echo "发布文件管理系统到:{$k} 结束 \n";
        }
	}	
}

function preview($serviceName,$service){
	syncFiles($serviceName,$service);
}

function haltPublish($serviceName,$service){
    stopServer($serviceName,$service);
	syncFiles($serviceName,$service);
}

function publishServer($serviceName,$service){
    stopServer($serviceName,$service);
	syncFiles($serviceName,$service);
	startServer($serviceName,$service);
}

function checkEnvironment(){
	global $logDir,$excludefile,$servers;
	echo "检查全局配置.....";	
//	if(!file_exists($logDir)){
//		echo "\n日志目录:$logDir 不存在，请检查配置\n";
//		return false;
//	}
	if($servers==null){
		echo "\n不存在相关服务配置,请检查配置。\n";
		return false;
	}
	if($excludefile!=null && !file_exists($excludefile)){
		echo "\n不存在过滤文件:$excludefil,请检查配置。\n";
		return false;
	}
	echo "完成全局配置检查!\n";	
	return true;
}

/***组优先实例，指定-g的情况下忽略-i****/
function getServerList(){
	global $instance,$group,$servers;
	if($group!=null){
		return $servers[$group];
	}else if($instance=="all"){
		return getApps();
	}else{
		$serverList = getApps();
		return array($instance => $serverList[$instance]);
	}
}

function checkInstance($instance){
	if ($instance == "all"){
		return true;
	}
	$serverList = getApps();
	if(array_key_exists($instance,$serverList)){
		return true;
	}
	echo "服务:$instance 不存在，请核对输入\n";
	return false;
}
function checkGroup($groupName){
	global $servers;
	if(array_key_exists($groupName,$servers)){
		return true;
	}
	echo "服务组:$groupName 不存在，请核对输入\n";
	return false;
}
function checkDir($dir){
	global $baseDir;
	if($dir==null || !file_exists($baseDir.$dir)){
	    echo "发布内容:$baseDir$dir 不存在，请核对输入\n";
		return false;
	}	
    return true;
}
function checkMode($mode){
	if(in_array($mode, array('forumPublish', 'forumPreview', 'preview',  'publish', 'startServer', 'stopServer','stopCache','startCache','restartCache','restartServer','haltPublish','resPublish','resPreview','fileManagePreview','fileManagePublish'))){
		return true;
	}
	echo "发布模式:$mode 不存在，请核对输入\n";
	echo "可用发布模式: 'forumPublish', 'forumPreview', 'preview', 'publish', 'startServer', 'stopServer','stopCache','startCache','restartCache','restartServer','haltPublish','resPublish','resPreview','fileManagePreview','fileManagePublish' \n";
	return false;
}
function checkBaseDir($baseDir){
	if(file_exists($baseDir)){
		return true;
	}
	echo "发布根路径:$baseDir 不存在，请核对输入\n";
	return false;
}

function parseParams(array $params,$paramCount){
  global $baseDir,$dir,$mode,$instance,$group;
  if ($paramCount < 2 || in_array($params[1], array('--help', '-help', '-h', '-?'))) {
	 	printUserage();
		return false;
  }

   if($paramCount%2==1){
	    echo "参数个数不匹配，请检查输入参数\n";
	 	printUserage();
		return false;
  }
  $pairParams = array_slice( $params,1,$paramCount);

  /****如果有根路径的话，首先获得发布根路径****/
  if(in_array('-b', $pairParams)){
	  $index =  array_search('-b', $pairParams); 
	  if($index + 1 < $paramCount  ){
		  $baseDir = $pairParams[$index + 1];
	  }
  }
  
  $i=0;
  while($i < $paramCount){
	switch ($pairParams[$i]) {
      case "-i":
        if(!checkInstance($pairParams[$i+1])){
	 	   printUserage();
		   return false;
	    }
		$instance = $pairParams[$i+1];
        break;
      case "-g":
        if(!checkGroup($pairParams[$i+1])){
	 	   printUserage();
		   return false;
	    }
		$group = $pairParams[$i+1];
        break;
      case "-d":
        if(!checkDir($pairParams[$i+1])){
	 	   printUserage();
		   return false;
	    }
		$dir = $pairParams[$i+1];
        break;
      case "-m":
        if(!checkMode($pairParams[$i+1])){
	 	   printUserage();
		   return false;
	    }
		$mode = $pairParams[$i+1];
        break;
      case "-b":
        if(!checkBaseDir($pairParams[$i+1])){
	 	   printUserage();
		   return false;
	    }
		$baseDir = $pairParams[$i+1];
        break;
	
    }
	  $i=$i+2;
  }
  return true;
}

function printUserage(){
  echo "使用方法:\n";
  echo "publish -i [all | app1_1 | app1_2 ] [-g group1 | group2] [-b dir] [-d dir|file] [-m publish | preview]\n";
  echo "发布脚本参数说明：\n";
  echo "  -i,  instance简写，指定发布服务名称，可以是all,app1_1,app1_2.......app4_1,app4_2. 默认值是all\n";
  echo "  -g,  group简写，指定发布某个具体的组，-g和-i是互斥的，-g指定后忽略-i参数\n";
  echo "  -d,  指定需要发布的特定目录或者是文件 \n";
  echo "  -m,  mode简写，发布模式，默认值是preview, 可以有'preview', 'publish', 'startServer', 'stopServer','stopCache','startCache','restartCache','restartServer','haltPublish','resPublish','resPreview','fileManagePreview','fileManagePublish', 'forumPublish', 'forumPreview'\n";
  echo "       preview：模拟发布.并不实质发布，仅仅列出发布过程.\n";
  echo "       publish：实质发布.停止指定Web服务，更新文件，启动Web服务.\n";
  echo "       haltPublish：停机发布.停止指定Web服务，更新文件，但不启动Web服务.\n";
  echo "       stopServer：  停止指定的Web服务 \n";
  echo "       startServer:  启动指定的Web服务 \n";
  echo "       stopCache:    停止memcache服务.(包括所有memcache实例)，忽略-i,-g参数 \n";
  echo "       startCache:   启动memcache服务.(包括所有memcache实例)，忽略-i,-g参数 \n";
  echo "       restartCache: 重启memcache服务.(包括所有memcache实例)，忽略-i,-g参数 \n";
  echo "       restartServer:重新启动指定的Web服务 \n";
  echo "       resPublish: 发布资源文件，请正确填写-d参数（！！！！！！！！！）。忽略-i,-g参数 \n";
  echo "       resPreview: 模拟发布资源文件，请正确填写-d参数（！！！！！！！！！）。忽略-i,-g参数\n";
  echo "       fileManagePublish: 发布文件管理系统，请正确填写-d参数（！！！！！！！！！）。忽略-i,-g参数 \n";
  echo "       fileManagePreview: 模拟发布文件管理系统，请正确填写-d参数（！！！！！！！！！）。忽略-i,-g参数\n";  
  echo "       forumPublish: 发布论坛，请正确填写-d参数（！！！！！！！！！）。忽略-i,-g参数 \n";
  echo "       forumPreview: 模拟发布论坛，请正确填写-d参数（！！！！！！！！！）。忽略-i,-g参数\n";  
  echo "  -b,  baseDir简写，需要发布的原始目录，请以/结尾目录 \n";
  echo "To get this help info, please type --help,-help,-h,-? \n";
}

function getApps(){
	global $servers;
	return  array_merge($servers['group1'],$servers['group2']);
}

?>