#!/bin/bash
set -e

#
# the big CloudFoundry installer
#

CLOUD_DOMAIN=${DOMAIN:-run.pivotal.io}
CLOUD_TARGET=api.${DOMAIN}

function login(){
  cf api | grep ${CLOUD_TARGET} || cf api ${CLOUD_TARGET} --skip-ssl-validation
  cf a | grep OK || cf login
}

function app_domain(){
  D=`cf apps | grep $1 | tr -s ' ' | cut -d' ' -f 6 | cut -d, -f1`
  echo $D
}

function deploy_app(){

  APP_NAME=$1
  cd $APP_NAME
  cf push $APP_NAME  --no-start
  APPLICATION_DOMAIN=`app_domain $APP_NAME`
  echo determined that application_domain for $APP_NAME is $APPLICATION_DOMAIN.
  cf env $APP_NAME | grep APPLICATION_DOMAIN || cf set-env $APP_NAME APPLICATION_DOMAIN $APPLICATION_DOMAIN
  cf restart $APP_NAME
  cd ..
}

function deploy_service(){
  N=$1
  D=`app_domain $N`
  JSON='{"uri":"http://'$D'"}'
  echo cf cups $N  -p $JSON
  cf cups $N -p $JSON
}

function deploy_config_server() {
  NAME=tour-config-server
  deploy_app $NAME
  deploy_service $NAME
}

function reset(){
  cf d tour-config-server
  
  cf ds tour-config-server
  
  cf delete-orphaned-routes
}

mvn -DskipTests=true clean install

login
reset
deploy_config_server