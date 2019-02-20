#!/bin/bash

bold=$(tput bold)
underline=$(tput sgr 0 1)
reset=$(tput sgr0)

red=$(tput setaf 1)
green=$(tput setaf 76)
white=$(tput setaf 7)
tan=$(tput setaf 202)
blue=$(tput setaf 25)
fkcloud=~/fkcloud
#
# Headers and Logging
#

underline() {
    printf "${underline}${bold}%s${reset}\n" "$@"
}
h1() {
    printf "\n${underline}${bold}${blue}%s${reset}\n" "$@"
}
h2() {
    printf "\n${underline}${bold}${white}%s${reset}\n" "$@"
}
success() {
    printf "${green}✔ %s${reset}\n" "$@"
}

item=0
if [ -f fkcloud*.tar.gz ]
then
	h2 "[Step $item]: loading fkcloud images ..."; let item+=1
	docker load -i ./fkcloud*.tar.gz
fi
echo ""

zkuiConfigPath=$fkcloud/docker/zkui
h2 "[Step $item]: copy zkui config.cfg to $zkuiConfigPath ..."; let item+=1

if [ ! -f "$zkuiConfigPath/config.cfg" ];then
    mkdir -p $zkuiConfigPath
    cp ./zkui/config.cfg $zkuiConfigPath/config.cfg
    h2 "copy file to $zkuiConfigPath/config.cfg success"
else
    h2 "$zkuiConfigPath/config.cfg is exit"
fi

# registry 目录处理
registryPath=$fkcloud/docker/registry
if [ ! -d "$registryPath" ];then
    mkdir -p $registryPath
    h2 "mkdir $registryPath success"
else
    h2 "$registryPath is exit"
fi

cp ./fkcloud/application.properties $fkcloud/application.properties


echo ""
h2 "[Step $item]: start fkcloud container ..."; let item+=1
docker_compose_list='-f docker-compose.yml'
docker-compose $docker_compose_list up -d
success "start fkcloud success……"