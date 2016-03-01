1、ngx_times.c、ngx_http_log_module.c
     作用：更改nginx（版本0.8.53）access日志中时间的格式，使其与jetty的日志格式相同，方便分析处理。
     部署位置：
          ngx_times.c：/usr/local/src/nginx-0.8.53/src/core/
          ngx_http_log_module.c：/usr/local/src/nginx-0.8.53/src/http/modules/
     使用方法：覆盖源文件后对nginx进行重新编译与安装。
               

2、nginxlog.sh
     作用： 将nginx日志按日期分割，并删除一个月前的日志
     部署目录:  /usr/localwzgrids/nginx/sbin/
  crontab设置：00 00  * * *  /bin/sh /usr/localwzgrids/nginx/sbin/nginxlog.sh.sh

  
3、nginx.conf
      作用：app1-4上的nginx配置文件
      部署目录:/usr/localwzgrids/nginx/conf

4、php.ini
      作用：app1-4上的PHP功能配置文件
      部署目录:/usr/localwzgrids/php/etc
       
5、php-fpm.conf
      作用：app1-4上的php-fastcgi配置文件
      部署目录:/usr/localwzgrids/php/etc
      使用方法：/usr/localwzgrids/php/sbin/php-fpm start (已经做了开机启动)，改变配置文件后重新加载使用 php-fpm reload
 

6、fcgi.conf
      作用：在nginx.conf调用，让nginx支持php
      部署目录:/usr/localwzgrids/nginx/conf

