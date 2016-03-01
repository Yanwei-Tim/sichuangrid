#!/bin/bash
log_dir=/usr/localwzgrids/nginx/logs
yesterday=`date -d yesterday +%Y_%m_%d`
lastday=`date -d '-1 month' +%Y_%m_%d`
/bin/rm ${log_dir}/access_${lastday}.log
/bin/rm ${log_dir}/error_${lastday}.log
/bin/mv ${log_dir}/access.log ${log_dir}/access_${yesterday}.log
/bin/mv ${log_dir}/error.log ${log_dir}/error_${yesterday}.log
/usr/localwzgrids/nginx/sbin/nginx -s reload

