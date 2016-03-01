#!/bin/bash
#

PATH=/sbin:/bin:/usr/sbin:/usr/bin


CURRENTPATH=/opt/wzgrids/logs/

today=`date -d yesterday +%Y_%m_%d`

echo "start to gather logs ..."

echo "gather from app1"


mkdir $CURRENTPATH/$today

scp admin@app1:/opt/wzgrids/admin/program1/logs/jetty-$today*.log  $CURRENTPATH/$today/app1_1.log
scp admin@app1:/opt/wzgrids/admin/program2/logs/jetty-$today*.log  $CURRENTPATH/$today/app1_2.log
scp admin@app1:/usr/localwzgrids/nginx/logs/access_$today*.log  $CURRENTPATH/$today/app1_3.log

echo "gather from app2"

scp admin@app2:/opt/wzgrids/admin/program1/logs/jetty-$today*.log  $CURRENTPATH/$today/app2_1.log
scp admin@app2:/opt/wzgrids/admin/program2/logs/jetty-$today*.log  $CURRENTPATH/$today/app2_2.log
scp admin@app2:/usr/localwzgrids/nginx/logs/access_$today*.log  $CURRENTPATH/$today/app2_3.log

echo "gather from app3"

scp admin@app3:/opt/wzgrids/admin/program1/logs/jetty-$today*.log  $CURRENTPATH/$today/app3_1.log
scp admin@app3:/opt/wzgrids/admin/program2/logs/jetty-$today*.log  $CURRENTPATH/$today/app3_2.log
scp admin@app3:/usr/localwzgrids/nginx/logs/access_$today*.log  $CURRENTPATH/$today/app3_3.log

echo "gather from app4"

scp admin@app4:/opt/wzgrids/admin/program1/logs/jetty-$today*.log  $CURRENTPATH/$today/app4_1.log
scp admin@app4:/opt/wzgrids/admin/program2/logs/jetty-$today*.log  $CURRENTPATH/$today/app4_2.log
scp admin@app4:/usr/localwzgrids/nginx/logs/access_$today*.log  $CURRENTPATH/$today/app4_3.log

echo "gather from squid1"

scp admin@squid1:/usr/localwzgrids/nginx/logs/access_$today*.log  $CURRENTPATH/$today/squid1_3.log



sort -m -t " " -k 4 -o $CURRENTPATH/access_$today.log $CURRENTPATH/$today/*.log

rm -fr $CURRENTPATH/$today/


echo "logs gathered! go , go , go !"
