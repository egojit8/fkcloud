NAME=fkcloud
VERSION=1.0
basepath=$(cd `dirname $0`; pwd)
cd ../../..
mvn clean install -Dmaven.test.skip=true
cp fkcloud-api/src/main/resources/application-docker.properties $basepath/application.properties
cp fkcloud-api/target/$NAME-*.jar $basepath/
echo $basepath
cd $basepath
pwd
docker build -t $NAME:$VERSION -f ./Dockerfile --no-cache --rm ./
rm $NAME-*.jar
#rm application.properties