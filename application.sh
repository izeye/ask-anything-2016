#!/bin/bash

THIS_SCRIPT=$0
COMMAND=$1

PROGRAM_NAME="ask-anything"
PROGRAM_OUTPUT_FILE=application.out

JAR_PATH=build/libs/ask-anything.jar

VM_OPTION_JMX="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=10001 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"
VM_OPTION_HEAP="-Xms1g -Xmx1g"
VM_OPTION_GC="-verbose:gc -Xloggc:gc.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationStoppedTime"
VM_OPTIONS="$VM_OPTION_JMX $VM_OPTION_HEAP $VM_OPTION_GC"

PID_FILE=application.pid

function start() {
    if [ -e $PID_FILE ]
    then
        echo "Already started!"
        exit 1
    fi

    echo "Starting ${PROGRAM_NAME}..."
    echo `date +"%F %T"` - Start application. >> event.log

    java $VM_OPTIONS -jar $JAR_PATH >> $PROGRAM_OUTPUT_FILE 2>&1 &
}

function stop() {
    if ! [ -e $PID_FILE ]
    then
        echo "Already stopped!"
        exit 1
    fi

    echo "Stopping ${PROGRAM_NAME}..."
    echo `date +"%F %T"` - Stop application. >> event.log

    PID=`cat $PID_FILE`
    kill -TERM $PID
    echo "Waiting 3 seconds for the application to be killed..."
    sleep 3
    PIDS=`jps | cut -f1 -d' '`
    for pid in $PIDS
    do
        if [ "$PID" == "$pid" ]
        then
            echo "The process $PID is still alive! Please kill it manually."
            exit 1
        fi
    done
    echo "Killed successfully."
}

function usage() {
    echo "Usage: $THIS_SCRIPT <start|stop>"
    exit 1
}

if [ "$COMMAND" == "start" ]
then
    start
    exit 0
elif [ "$COMMAND" == "stop" ]
then
    stop
    exit 0
fi

usage
