#!/usr/bin/env sh


cd /demo


CP_PARAM="-cp /demo/jars/*"
CP_PARAM="$CP_PARAM:/demo/config/"
DEBUG_PARAM="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=$1"
LAUNCHER_CLASS="org.springframework.boot.loader.JarLauncher"

JAVA_CMD="java $DEBUG_PARAM $CP_PARAM $LAUNCHER_CLASS"

nohup $JAVA_CMD
