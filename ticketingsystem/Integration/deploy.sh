#!/usr/bin/env bash
# This script can be used to automatically build and deploy this project
# Add following lines to your ~/.bash_profile
# export CATALINA_HOME=path to tomcat without ending slash

cd ..
mvn clean install -DskipTests -o
rm -r $CATALINA_HOME/webapps/ticketing-system*
cp web/target/ticketing-system.war $CATALINA_HOME/webapps
export JPDA_ADDRESS=5005
export JPDA_TRANSPORT=dt_socket
sh $CATALINA_HOME/bin/catalina.sh jpda run
