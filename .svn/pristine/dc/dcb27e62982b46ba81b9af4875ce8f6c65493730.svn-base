#!/bin/bash
#

PATH=$PATH:/usr/local/sbin:/sbin:/bin:/usr/sbin

NAME=memcached
DESC=memcached
PIDFILE=/var/tmp/$NAME.pid

RETVAL=0

start() {
        echo -n $"Starting $DESC: "
        memcached -d -m 1024 -P $PIDFILE
        RETVAL=$?
        [ $RETVAL -eq 0 ] && touch $PIDFILE
        echo
        return $RETVAL
}

stop() {
        echo -n $"Shutting down $DESC: "
        kill `cat $PIDFILE`
        RETVAL=$?
        echo
        [ $RETVAL -eq 0 ] && rm -f $PIDFILE
        return $RETVAL
}

# See how we were called.
case "$1" in
        start)
                start
                ;;
        stop)
                stop
                ;;
        restart|reload)
                stop
                start
                RETVAL=$?
                ;;
        status)
                status $prog
                RETVAL=$?
                ;;
        *)
                echo $"Usage: $0 {start|stop|restart|status}"
                exit 1
esac

exit $RETVAL

EOF
