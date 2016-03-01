使用方法:
publish -i [all | app1_1 | app1_2 ] [-g group1 | group2] [-b dir] [-d dir|file] [-m publish | preview]
发布脚本参数说明：
  -i,  instance简写，指定发布服务名称，可以是all,app1_1,app1_2.......app4_1,app4_2. 默认值是all
  -g,  group简写，指定发布某个具体的组，-g和-i是互斥的，-g指定后忽略-i参数
  -d,  指定需要发布的特定目录或者是文件 
  -m,  mode简写，发布模式，默认值是preview, 可以有'preview', 'publish', 'startServer', 'stopServer','stopCache','startCache','restartCache','restartServer','haltPublish','resPublish','resPreview','fileManagePreview','fileManagePublish' 
       preview：模拟发布.并不实质发布，仅仅列出发布过程.
       publish：实质发布.停止指定Web服务，更新文件，启动Web服务.
       haltPublish：停机发布.停止指定Web服务，更新文件，但不启动Web服务.
       stopServer：  停止指定的Web服务 
       startServer:  启动指定的Web服务 
       stopCache:    停止memcache服务.(包括所有memcache实例)，忽略-i,-g参数 
       startCache:   启动memcache服务.(包括所有memcache实例)，忽略-i,-g参数 
       restartCache: 重启memcache服务.(包括所有memcache实例)，忽略-i,-g参数 
       restartServer:重新启动指定的Web服务 
       resPublish: 发布资源文件，请正确填写-d参数（！！！！！！！！！）。忽略-i,-g参数 
       resPreview: 模拟发布资源文件，请正确填写-d参数（！！！！！！！！！）。忽略-i,-g参数
       fileManagePublish: 发布文件管理系统，请正确填写-d参数（！！！！！！！！！）。忽略-i,-g参数 
       fileManagePreview: 模拟发布文件管理系统，请正确填写-d参数（！！！！！！！！！）。忽略-i,-g参数
  -b,  baseDir简写，需要发布的原始目录，请以/结尾目录 

设计说明：
	publish.php: 解析参数，确定工作流
	config.php:  全局配置参数
	hack.php:    操作函数，包括启动和关闭cache\server，同步文件等
	
预期扩展：
          仅仅是对现有工作流的扩展，可以直接改写hack.php
          如果需要改变工作流或者增加更多的参数，需要改写publish.php
    
	