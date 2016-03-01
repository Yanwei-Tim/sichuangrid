<?php
/**
  保留全局变量
*/
global $baseDir,$dir,$mode,$instance,$servers,$group,$excludelist,$logDir,$forumServers,$mobileServers;$coreServers;$anaServers;

$baseDir = null; 
$mode = "preview";
$dir="";
$instance="all";
$group=null;

/**也可以选择为每个服务定�?*/
global $antbin,$afterStopSleepSeconds;
$antbin ='/usr/local/apache-ant-1.9.0/bin/ant ';

/**
  * 排除同步的文�?
*/
$excludefile = "list.exclude";

/****
  * 停止服务后，等待多长时间发布文件 
*/
$afterStopSleepSeconds = 3;

/**
  * 同步日志目录
$logDir = '/opt/wzgrids/admin/publish/logs/';
*/

$logDir = null;

/****memcache serverlist***/
$cacheServers = array(
    'cache1' => array(             //Key 保留关键�?
		    'serverName' => 'app1' //serverName 保留关键字，余下可以自定�?
	),
    'cache2' => array(
		    'serverName' => 'app2'
	),
    'cache3' => array(
		    'serverName' => 'cellphone3'
	),
    'cache4' => array(
		    'serverName' => 'app4'
	),
    'cache5' => array(
		    'serverName' => 'app5'
	),
    'cache6' => array(
		    'serverName' => 'app6'
	),
    'cache7' => array(
		    'serverName' => 'app7'
	),
    'cache8' => array(
		    'serverName' => 'app8'
	),
    'cache10' => array(
		    'serverName' => 'app10'
	),
    'cache11' => array(
		    'serverName' => 'app11'
	),
    'cache12' => array(
		    'serverName' => 'app12'
	)
);

/****forumServers serverlist***/
$forumServers = array(
	'forumServers1' => array(
	 'serverName' => 'message1',
	 'baseDir'=> '/home/admin/sichuanGridForum/'
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
	'squid' => array(
		 'serverName' => 'squid',
		 'baseDir' => '/home/admin/sichuangrid/'
	 )
);

$anaServers = array(
	'group1' => array(                      
		'app12_1' => array(                  
		    'serverName' => 'app12',        
			'baseDir' => '/home/admin/program1/sichuanGrid/',
			'port'    => '8080',
			'listenerPort' => '8081' 
		),
		'app12_2' => array(
		    'serverName' => 'app12',
			'baseDir' => '/home/admin/program2/sichuanGrid/',
			'port'    => '8082',
			'listenerPort' => '8083' 
		),
		'app12_3' => array(
		    'serverName' => 'app12',
			'baseDir' => '/home/admin/program3/sichuanGrid/',
			'port'    => '8084',
			'listenerPort' => '8085' 
		),
		'app12_4' => array(
		    'serverName' => 'app12',
			'baseDir' => '/home/admin/program4/sichuanGrid/',
			'port'    => '8086',
			'listenerPort' => '8087' 
		),
		'app12_5' => array(
		    'serverName' => 'app12',
			'baseDir' => '/home/admin/program5/sichuanGrid/',
			'port'    => '8088',
			'listenerPort' => '8089' 
		),
		'app12_6' => array(
		    'serverName' => 'app12',
			'baseDir' => '/home/admin/program6/sichuanGrid/',
			'port'    => '8090',
			'listenerPort' => '8091' 
		)
	)

);

$coreServers = array(
	'group1' => array(                      
		'app10_1' => array(                  
		    'serverName' => 'app10',        
			'baseDir' => '/home/admin/program1/sichuanGrid/',
			'port'    => '8080',
			'listenerPort' => '8081' 
		),
		'app10_2' => array(
		    'serverName' => 'app10',
			'baseDir' => '/home/admin/program2/sichuanGrid/',
			'port'    => '8082',
			'listenerPort' => '8083' 
		),
		'app10_3' => array(
		    'serverName' => 'app10',
			'baseDir' => '/home/admin/program3/sichuanGrid/',
			'port'    => '8084',
			'listenerPort' => '8085' 
		),
		'app10_4' => array(
		    'serverName' => 'app10',
			'baseDir' => '/home/admin/program4/sichuanGrid/',
			'port'    => '8086',
			'listenerPort' => '8087' 
		),
		'app10_5' => array(
		    'serverName' => 'app10',
			'baseDir' => '/home/admin/program5/sichuanGrid/',
			'port'    => '8088',
			'listenerPort' => '8089' 
		),
		'app10_6' => array(
		    'serverName' => 'app10',
			'baseDir' => '/home/admin/program6/sichuanGrid/',
			'port'    => '8090',
			'listenerPort' => '8091' 
		)
	),
	'group2' => array(                      
		'app11_1' => array(                  
		    'serverName' => 'app12',        
			'baseDir' => '/home/admin/program1/sichuanGrid/',
			'port'    => '8080',
			'listenerPort' => '8081' 
		),
		'app11_2' => array(
		    'serverName' => 'app12',
			'baseDir' => '/home/admin/program2/sichuanGrid/',
			'port'    => '8082',
			'listenerPort' => '8083' 
		),
		'app11_3' => array(
		    'serverName' => 'app12',
			'baseDir' => '/home/admin/program3/sichuanGrid/',
			'port'    => '8084',
			'listenerPort' => '8085' 
		),
		'app11_4' => array(
		    'serverName' => 'app12',
			'baseDir' => '/home/admin/program4/sichuanGrid/',
			'port'    => '8086',
			'listenerPort' => '8087' 
		),
		'app11_5' => array(
		    'serverName' => 'app12',
			'baseDir' => '/home/admin/program5/sichuanGrid/',
			'port'    => '8088',
			'listenerPort' => '8089' 
		),
		'app11_6' => array(
		    'serverName' => 'app12',
			'baseDir' => '/home/admin/program6/sichuanGrid/',
			'port'    => '8090',
			'listenerPort' => '8091' 
		)
	)

);


$mobileServers = array(
	'group1' => array(                      
		'mobile3_1' => array(                  
		    'serverName' => 'cellphone3',        
			'baseDir' => '/home/admin/mobile/mobileService1/',
			'port'    => '9000',
			'listenerPort' => '9001' 
		),
		'mobile4_1' => array(
		    'serverName' => 'cellphone4',
			'baseDir' => '/home/admin/mobile/mobileService1/',
			'port'    => '9000',
			'listenerPort' => '9001' 
		),
		'mobile5_1' => array(
		    'serverName' => 'app5',
			'baseDir' => '/home/admin/mobile/mobileService1/',
			'port'    => '9000',
			'listenerPort' => '9001' 
		),
		'mobile3_2' => array(
		    'serverName' => 'cellphone3',
			'baseDir' => '/home/admin/mobile/mobileService2/',
			'port'    => '9010',
			'listenerPort' => '9011' 
		),
		'mobile4_2' => array(
		    'serverName' => 'cellphone4',
			'baseDir' => '/home/admin/mobile/mobileService2/',
			'port'    => '9010',
			'listenerPort' => '9011' 
		),
		'mobile5_2' => array(
		    'serverName' => 'app5',
			'baseDir' => '/home/admin/mobile/mobileService2/',
			'port'    => '9010',
			'listenerPort' => '9011' 
		)
	),
	'group2' => array(                      
		'bigdata_1' => array(
		    'serverName' => 'bigdata',
			'baseDir' => '/home/admin/mobile/mobileService1/',
			'port'    => '9000',
			'listenerPort' => '9001' 
		),
		'bigdata_2' => array(
		    'serverName' => 'bigdata',
			'baseDir' => '/home/admin/mobile/mobileService2/',
			'port'    => '9002',
			'listenerPort' => '9003' 
		),
		'bigdata_3' => array(
		    'serverName' => 'bigdata',
			'baseDir' => '/home/admin/mobile/mobileService3/',
			'port'    => '9004',
			'listenerPort' => '9005' 
		),
		'bigdata_4' => array(
		    'serverName' => 'bigdata',
			'baseDir' => '/home/admin/mobile/mobileService4/',
			'port'    => '9006',
			'listenerPort' => '9007' 
		),
		'bigdata_5' => array(
		    'serverName' => 'bigdata',
			'baseDir' => '/home/admin/mobile/mobileService5/',
			'port'    => '9008',
			'listenerPort' => '9009' 
		),
		'bigdata_6' => array(
		    'serverName' => 'bigdata',
			'baseDir' => '/home/admin/mobile/mobileService6/',
			'port'    => '9010',
			'listenerPort' => '9011' 
		)
	)

);

/**
  * 扩展不能改变三级结构，除说明外，可以增加自定义字段。改变现有字段（除保留字段）
  * 需要改写hack.php
*/
$servers = array(
	'group1' => array(                      //group 保留关键�?
		'app4_1' => array(
		    'serverName' => 'app4',
			'baseDir' => '/home/admin/program1/sichuanGrid/',
			'port'    => '8080',
			'listenerPort' => '8081' 
		),
		'app4_2' => array(
		    'serverName' => 'app4',
			'baseDir' => '/home/admin/program2/sichuanGrid/',
			'port'    => '8082',
			'listenerPort' => '8083' 
		),
		'app4_3' => array(
		    'serverName' => 'app4',
			'baseDir' => '/home/admin/program3/sichuanGrid/',
			'port'    => '8084',
			'listenerPort' => '8085' 
		),
		'app4_4' => array(
		    'serverName' => 'app4',
			'baseDir' => '/home/admin/program4/sichuanGrid/',
			'port'    => '8086',
			'listenerPort' => '8087' 
		),
		'app4_5' => array(
		    'serverName' => 'app4',
			'baseDir' => '/home/admin/program5/sichuanGrid/',
			'port'    => '8088',
			'listenerPort' => '8089' 
		),
		'app4_6' => array(
		    'serverName' => 'app4',
			'baseDir' => '/home/admin/program6/sichuanGrid/',
			'port'    => '8090',
			'listenerPort' => '8091' 
		),
		'app6_1' => array(
		    'serverName' => 'app6',
			'baseDir' => '/home/admin/program1/sichuanGrid/',
			'port'    => '8080',
			'listenerPort' => '8081' 
		),
		'app6_2' => array(
		    'serverName' => 'app6',
			'baseDir' => '/home/admin/program2/sichuanGrid/',
			'port'    => '8082',
			'listenerPort' => '8083' 
		),
		'app6_3' => array(
		    'serverName' => 'app6',
			'baseDir' => '/home/admin/program3/sichuanGrid/',
			'port'    => '8084',
			'listenerPort' => '8085' 
		),
		'app6_4' => array(
		    'serverName' => 'app6',
			'baseDir' => '/home/admin/program4/sichuanGrid/',
			'port'    => '8086',
			'listenerPort' => '8087' 
		),
		'app6_5' => array(
		    'serverName' => 'app6',
			'baseDir' => '/home/admin/program5/sichuanGrid/',
			'port'    => '8088',
			'listenerPort' => '8089' 
		),
		'app6_6' => array(
		    'serverName' => 'app6',
			'baseDir' => '/home/admin/program6/sichuanGrid/',
			'port'    => '8090',
			'listenerPort' => '8091' 
		)
	),
	'group2' => array(
		'app7_1' => array(
		    'serverName' => 'app7',
			'baseDir' => '/home/admin/program1/sichuanGrid/',
			'port'    => '8080',
			'listenerPort' => '8081' 
		),
		'app7_2' => array(
		    'serverName' => 'app7',
			'baseDir' => '/home/admin/program2/sichuanGrid/',
			'port'    => '8082',
			'listenerPort' => '8083' 
		),
		'app7_3' => array(
		    'serverName' => 'app7',
			'baseDir' => '/home/admin/program3/sichuanGrid/',
			'port'    => '8084',
			'listenerPort' => '8085' 
		),
		'app7_4' => array(
		    'serverName' => 'app7',
			'baseDir' => '/home/admin/program4/sichuanGrid/',
			'port'    => '8086',
			'listenerPort' => '8087' 
		),
		'app7_5' => array(
		    'serverName' => 'app7',
			'baseDir' => '/home/admin/program5/sichuanGrid/',
			'port'    => '8088',
			'listenerPort' => '8089' 
		),
		'app7_6' => array(
		    'serverName' => 'app7',
			'baseDir' => '/home/admin/program6/sichuanGrid/',
			'port'    => '8090',
			'listenerPort' => '8091' 
		),
		'app8_1' => array(
		    'serverName' => 'app8',
			'baseDir' => '/home/admin/program1/sichuanGrid/',
			'port'    => '8080',
			'listenerPort' => '8081' 
		),
		'app8_2' => array(
		    'serverName' => 'app8',
			'baseDir' => '/home/admin/program2/sichuanGrid/',
			'port'    => '8082',
			'listenerPort' => '8083' 
		),
		'app8_3' => array(
		    'serverName' => 'app8',
			'baseDir' => '/home/admin/program3/sichuanGrid/',
			'port'    => '8084',
			'listenerPort' => '8085' 
		),
		'app8_4' => array(
		    'serverName' => 'app8',
			'baseDir' => '/home/admin/program4/sichuanGrid/',
			'port'    => '8086',
			'listenerPort' => '8087' 
		),
		'app8_5' => array(
		    'serverName' => 'app8',
			'baseDir' => '/home/admin/program5/sichuanGrid/',
			'port'    => '8088',
			'listenerPort' => '8089' 
		),
		'app8_6' => array(
		    'serverName' => 'app8',
			'baseDir' => '/home/admin/program6/sichuanGrid/',
			'port'    => '8090',
			'listenerPort' => '8091' 
		)
	),
	'group3' => array(
		'mongo1_1' => array(
		    'serverName' => 'mongo1',
			'baseDir' => '/home/admin/program1/sichuanGrid/',
			'port'    => '8080',
			'listenerPort' => '8081' 
		),
		'mongo1_2' => array(
		    'serverName' => 'mongo1',
			'baseDir' => '/home/admin/program2/sichuanGrid/',
			'port'    => '8082',
			'listenerPort' => '8083' 
		),
		'mongo1_3' => array(
		    'serverName' => 'mongo1',
			'baseDir' => '/home/admin/program3/sichuanGrid/',
			'port'    => '8084',
			'listenerPort' => '8085' 
		),
		'mongo1_4' => array(
		    'serverName' => 'mongo1',
			'baseDir' => '/home/admin/program4/sichuanGrid/',
			'port'    => '8086',
			'listenerPort' => '8087' 
		),
		'mongo1_5' => array(
		    'serverName' => 'mongo1',
			'baseDir' => '/home/admin/program5/sichuanGrid/',
			'port'    => '8088',
			'listenerPort' => '8089' 
		),
		'mongo1_6' => array(
		    'serverName' => 'mongo1',
			'baseDir' => '/home/admin/program6/sichuanGrid/',
			'port'    => '8090',
			'listenerPort' => '8091' 
		),
		'mongo2_1' => array(
		    'serverName' => 'mongo2',
			'baseDir' => '/home/admin/program1/sichuanGrid/',
			'port'    => '8080',
			'listenerPort' => '8081' 
		),
		'mongo2_2' => array(
		    'serverName' => 'mongo2',
			'baseDir' => '/home/admin/program2/sichuanGrid/',
			'port'    => '8082',
			'listenerPort' => '8083' 
		),
		'mongo2_3' => array(
		    'serverName' => 'mongo2',
			'baseDir' => '/home/admin/program3/sichuanGrid/',
			'port'    => '8084',
			'listenerPort' => '8085' 
		),
		'mongo2_4' => array(
		    'serverName' => 'mongo2',
			'baseDir' => '/home/admin/program4/sichuanGrid/',
			'port'    => '8086',
			'listenerPort' => '8087' 
		),
		'mongo2_5' => array(
		    'serverName' => 'mongo2',
			'baseDir' => '/home/admin/program5/sichuanGrid/',
			'port'    => '8088',
			'listenerPort' => '8089' 
		),
		'mongo2_6' => array(
		    'serverName' => 'mongo2',
			'baseDir' => '/home/admin/program6/sichuanGrid/',
			'port'    => '8090',
			'listenerPort' => '8091' 
		)
	),

	'group4' => array(
		'yunapp1_1' => array(
		    'serverName' => 'yunapp1',
			'baseDir' => '/home/admin/program1/sichuanGrid/',
			'port'    => '8080',
			'listenerPort' => '8081' 
		),
		'yunapp1_2' => array(
		    'serverName' => 'yunapp1',
			'baseDir' => '/home/admin/program2/sichuanGrid/',
			'port'    => '8082',
			'listenerPort' => '8083' 
		),
		'yunapp2_1' => array(
		    'serverName' => 'yunapp2',
			'baseDir' => '/home/admin/program3/sichuanGrid/',
			'port'    => '8080',
			'listenerPort' => '8081' 
		),
		'yunapp2_2' => array(
		    'serverName' => 'yunapp2',
			'baseDir' => '/home/admin/program4/sichuanGrid/',
			'port'    => '8082',
			'listenerPort' => '8083' 
		),
		'yunapp3_1' => array(
		    'serverName' => 'yunapp3',
			'baseDir' => '/home/admin/program5/sichuanGrid/',
			'port'    => '8080',
			'listenerPort' => '8081' 
		),
		'yunapp3_2' => array(
		    'serverName' => 'yunapp3',
			'baseDir' => '/home/admin/program6/sichuanGrid/',
			'port'    => '8082',
			'listenerPort' => '8083' 
		)
	)
);
?>
