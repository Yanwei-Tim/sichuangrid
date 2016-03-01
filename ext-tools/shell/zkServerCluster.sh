################################################
######function：启动停止Zookeeper服务    #######
######author：龙振东                     #######
################################################

#!/bin/bash
#
PATH=$PATH:/usr/local/sbin:/sbin:/bin:/usr/sbin

serverList=(
app1
app2
)
dirList=(
/home/admin/zookeeper-3.4.5/bin/zkServer.sh
/home/admin/zookeeper-3.4.5/bin/zkServer.sh
)
length=${#serverList[*]}
start(){
    for((i=0;i<$length;i=i+1))
      do
         ssh admin@${serverList[$i]} ${dirList[$i]} start
         echo "${serverList[$i]}启动完成"
      done
    echo "集群启动完毕"
}

stop(){
   for((i=0;i<$length;i=i+1))
     do
        ssh admin@${serverList[$i]} ${dirList[$i]} stop
        echo "${serverList[$i]}停止完成"
     done
   echo "集群停止完毕"
}

case "$1" in
        start)
                start
                ;;
        stop)
                stop
                ;;
        
        *)
			echo "请输入正确指令{start|stop}"
			exit 1
esac

