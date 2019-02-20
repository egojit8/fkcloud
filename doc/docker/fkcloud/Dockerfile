FROM java:8

MAINTAINER egojit <egojit@qq.com>
WORKDIR /var/app

ADD fkcloud-*.jar /var/app/fkcloud.jar
ADD application.properties /var/app/application.properties
ADD bootstrap.sh /var/app/bootstrap.sh

ENTRYPOINT ["/var/app/bootstrap.sh"]

EXPOSE 8080
