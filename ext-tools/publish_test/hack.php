<?php

/**
 * 停止服务，假设系统装有ant,并打通通道,ant路径来自$antbin
*/
function stopServer($serviceName,$service){
	global $mode,$antbin,$logDir,$afterStopSleepSeconds ;
	$command ="ssh admin@{$service['serverName']}  ".$antbin. 
		" -buildfile {$service['baseDir']}production.build.xml  stopJetty -Dlistenerport={$service['listenerPort']} ";
	echo "准备停止服务:{$serviceName}\n";
	echo $command."\n";
	system($command);
	sleep($afterStopSleepSeconds);
}

/**
 * 同步服务，假设系统已经配置相关rsync
*/
function syncFiles($serviceName,$service){
	global $mode,$excludefile,$logDir,$baseDir,$dir;
	$command = "rsync -avu --delete ";
	if($excludefile!==null){
		$command .= " --exclude-from={$excludefile} ";
	}
	$command .=  " {$baseDir}{$dir} admin@{$service['serverName']}:{$service['baseDir']}{$dir} ";
	
	if($mode=='preview'){
		$command .= " -n";
	}
	echo $command."\n";
	system($command);
	
	generateServiceFlag($serviceName,$service);
}

function generateServiceFlag($serviceName,$service){
	$command ="ssh admin@{$service['serverName']}  ".
		"  \"echo '{$service['serverName']} : {$service['port']}' > {$service['baseDir']}webroot/1.txt \"";
	echo $command."\n";
	system($command);
}
/**
 * 启动服务，假设系统装有ant,并打通通道,ant路径来自$antbin
*/
function startServer($serviceName,$service){
	global $mode,$antbin,$logDir;
	$command = "ssh admin@{$service['serverName']}   ".$antbin. 
		" -buildfile {$service['baseDir']}production.build.xml  startJetty -Dport={$service['port']} -Dlistenerport={$service['listenerPort']} -Dpath=/ -Drootdir=./webroot ";
	echo "准备启动服务:{$serviceName}\n";
	echo $command."\n";
	system($command);
}  

/**
 * 停止cache，假设/home/admin/wzmem/wzmem.sh 脚本存在
*/
function stopCache($serviceName,$service){
	$command ="ssh admin@{$service['serverName']} /home/admin/publish_test/wzmem/wzmem.sh stop";
	echo $command."\n";
	system($command);
}

/**
 * 启动cache，假设/home/admin/wzmem/wzmem.sh 脚本存在
*/
function startCache($serviceName,$service){
	$command ="ssh admin@{$service['serverName']} /home/admin/publish_test/wzmem/wzmem.sh start";
	echo $command."\n";
	system($command);
}

/**
 * 重启cache，假设/home/admin/wzmem/wzmem.sh 脚本存在
*/
function restartCache($serviceName,$service){
	$command ="ssh admin@{$service['serverName']} /home/admin/publish_test/wzmem/wzmem.sh restart";
	echo $command."\n";
	system($command);
}


function publishResource($serviceName,$service){
	global $mode,$excludefile,$baseDir,$dir;
	$command = "rsync -avuC --delete ";
	if($excludefile!==null){
		$command .= " --exclude-from={$excludefile} ";
	}
	$command .=  " {$baseDir}{$dir} admin@{$service['serverName']}:{$service['baseDir']} ";
	
	if($mode=='resPreview'){
		$command .= " -n";
	}
	echo $command."\n";
	system($command);
}

function publishFileManage($serviceName,$service){
	global $mode,$excludefile,$baseDir,$dir;
	$command = "rsync -avuC --delete ";
	if($excludefile!==null){
		$command .= " --exclude-from={$excludefile} ";
	}
	$command .=  " {$baseDir}{$dir} admin@{$service['serverName']}:{$service['baseDir']} ";
	
	if($mode=='fileManagePreview'){
		$command .= " -n";
	}
	echo $command."\n";
	system($command);
}

?>