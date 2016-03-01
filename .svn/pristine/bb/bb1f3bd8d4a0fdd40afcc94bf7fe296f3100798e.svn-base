1、gatherLogs.sh
     作用： 从各台服务器上收集日志文件，并按日志时间合并成一个文件。
     部署目录:  /opt/wzgrids/logs
  crontab设置：21 00  * * *  /opt/wzgrids/logs/gatherLogs.sh
  
  awstats的配置： LogFile="/opt/wzgrids/logs/access_%YYYY-24_%MM-24_%DD-24.log"
     日志分析crontab: 0 2 * * * /usr/localwzgrids/awstats/wwwroot/cgi-bin/awstats.pl -update -config=172.20.26.165


2、nginx.conf
      作用：publish1上的nginx配置文件
      部署目录:/usr/localwzgrids/nginx/conf
       

3、php-fpm.conf
      作用：  php的fastcgi配置文件
      部署目录:/usr/localwzgrids/php/etc
      使用方法：/usr/localwzgrids/php/sbin/php-fpm start (已经做了开机启动)，改变配置文件后重新加载使用 php-fpm reload
 

4、php.ini
      作用：php的功能配置文件
      部署目录：/usr/localwzgrids/php/etc


5、perl-fcgi
      作用： perl的fastcgi启动脚本，用于nginx支持cgi
      部署目录: /bin
      使用方法：./perl-fcgi &


6、fastcgi_params,fcgi.conf
      作用：在nginx.conf调用，让nginx支持php和cgi
      部署目录:/usr/localwzgrids/nginx/conf