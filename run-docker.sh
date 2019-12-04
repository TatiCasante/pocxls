#!/bin/bash

# Script para inicialização do container Docker com a imagem gerada pelo script 'criar-imagem.sh'
# Passar o profile e a versão da imagem que pode ser obtida através do comando 'docker images', na coluna TAG referente a imagem gerada pelo script anterior
# Foram comentados os comandos que comunicam com o Nexus
# Através do comando 'docker ps' é possível verificar se tudo deu OK

# $1 PROFILE (local ou local-kafka)
# $2 VERSAO DO PROJETO
# $3 username banco
# $4 password banco
# $5 username banco endereco
# $6 password banco endereco

if [ "$#" -ne 2 ]; then
  echo "Uso: $0 <PROFILE> <VERSAO_PROJETO>" >&2
  echo "Exemplo: $0 local 1.0.0" >&2
  exit 1
fi

PROJECT=aplicacao-template
NOME_IMAGEM='10.90.0.30:8123/confidence/aplicacao-template'
source /etc/profile

ID_IMAGEM=$(docker images $NOME_IMAGEM -q)

#docker stop $PROJECT

#docker rmi $ID_IMAGEM --force

#docker pull 10.90.0.30:8123/confidence/$PROJECT:$2

PROFILE="$1"
LOG_FOLDER=/home/confidence/aplicacao-template
SERVER_PORT=8080

rm pp.out -rf

docker run \
-d \
-e "SPRING_PROFILES_ACTIVE=$PROFILE" \
-e "log.folder=/logs" \
-e "server.port=$SERVER_PORT" \
-e "spring.datasource.username=$3" \
-e "spring.datasource.password=$4" \
-e "spring.datasource.endereco.username=$5" \
-e "spring.datasource.endereco.password=$6" \
-p $SERVER_PORT:$SERVER_PORT \
-h $PROFILE \
-v $LOG_FOLDER:/logs \
--rm \
--name $PROJECT \
10.90.0.30:8123/confidence/$PROJECT:$2