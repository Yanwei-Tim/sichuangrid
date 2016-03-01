<?php
/**
  保留全局变量
*/
global $baseDir,$dir,$mode,$instance,$servers,$group,$excludelist,$logDir,$forumServers;

$baseDir = null; 
$mode = "preview";
$dir="";
$instance="all";
$group=null;

/**也可以选择为每个服务定义**/
global $antbin,$afterStopSleepSeconds;
$antbin ='/usr/local/apache-ant-1.9.0/bin/ant ';

/**
  * 排除同步的文件
*/
$excludefile = "list.exclude";

/****
  * 停止服务后，等待多长时间发布文件 
*/
$afterStopSleepSeconds = 5;

/**
  * 同步日志目录
$logDir = '/opt/wzgrids/admin/publish/logs/';
*/

$logDir = null;

/****memcache serverlist***/
$cacheServers = array(
    'cache1' => array(             //Key 保留关键字
		    'serverName' => 'app9' //serverName 保留关键字，余下可以自定义
	)
);

/****forumServers serverlist***/
$forumServers = array(
	'forumServers1' => array(
	 'serverName' => 'message1',
	 'baseDir'=> '/home/admin/tianqueGridForum/'
	)
);

/****fileManage serverlist***/
$fileManageServers = array(
	'fileServers1' => array(
		 'serverName' => 'app1',
		 'baseDir'=> '/opt/wzgrids/admin/php/'
		),
	'fileServers2' => array(
		 'serverName' => 'app2',
		 'baseDir'=> '/opt/wzgrids/admin/php/'
		),
	'fileServers3' => array(
		 'serverName' => 'app3',
		 'baseDir'=> '/opt/wzgrids/admin/php/'
		),
	'fileServers4' => array(
		 'serverName' => 'app4',
		 'baseDir'=> '/opt/wzgrids/admin/php/'
	)	
);

/*******squid serverList****/
$squidServers = array(
	'squid1' => array(
		 'serverName' => 'squid1',
		 'baseDir' => '/home/admin/tianquegrid/'
	 ),
	 'squid2' => array(
		 'serverName' => 'squid2',
		 'baseDir' => '/home/admin/tianquegrid/'
	 )
);

/**
  * 扩展不能改变三级结构，除说明外，可以增加自定义字段。改变现有字段（除保留字段）
  * 需要改写hack.php
*/
$servers = array(
	'group1' => array(                      //group 保留关键字
		'app1_1' => array(                  //appx_y 保留关键字
		    'serverName' => 'app9',         //serverName 保留关键字
			'baseDir' => '/home/admin/program1/tianqueGrid/',
			'port'    => '8080',
			'listenerPort' => '8081' 
		),
		'app1_2' => array(
		    'serverName' => 'app9',
			'baseDir' => '/home/admin/program2/tianqueGrid/',
			'port'    => '8090',
			'listenerPort' => '8091' 
		)
	),'group2' => array(                      //group 保留关键字
		'app2_1' => array(                  //appx_y 保留关键字
		    'serverName' => 'app9',         //serverName 保留关键字
			'baseDir' => '/home/admin/program3/tianqueGrid/',
			'port'    => '8088',
			'listenerPort' => '8089' 
		),
		'app2_2' => array(
		    'serverName' => 'app9',
			'baseDir' => '/home/admin/program4/tianqueGrid/',
			'port'    => '8098',
			'listenerPort' => '8099' 
		)
	)
);
?>