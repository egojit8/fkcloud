#!/bin/sh
#ZK_SERVER="172.17.0.2:2181,localhost:2181"
#USER_SET=${USER_SET:-"{\"users\": [{ \"username\":\"admin\" , \"password\":\"manager\",\"role\": \"ADMIN\" \},{ \"username\":\"appconfig\" , \"password\":\"appconfig\",\"role\": \"USER\" \}]\}"}
#LOGIN_MESSAGE=${LOGIN_MESSAGE:-"Please login using admin/manager or appconfig/appconfig."}

#sed -i 's/^zkServer=.*$/zkServer=$ZK_SERVER/' /var/app/config.cfg

#sed -i 's/^userSet = .*$/userSet = $USER_SET/' /var/app/config.cfg
#sed -i 's/^loginMessage=.*$/loginMessage=$LOGIN_MESSAGE/' /var/app/config.cfg
#echo '===================================='
echo "Starting fkcloud……"
exec java -Dspring.config.location=/var/app/application.properties  -jar /var/app/fkcloud.jar