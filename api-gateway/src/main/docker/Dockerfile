FROM kong:1.1rc1-centos
LABEL description="Centos 7 + Kong 0.14 + kong-oidc plugin"

RUN yum install -y git unzip && yum clean all
RUN USER=root luarocks install kong-oidc

COPY ./docker-entrypoint.sh /docker-entrypoint.sh
RUN chmod 777 /docker-entrypoint.sh

COPY ./nginx-custom-static-sso.template  /usr/local/kong/template/nginx-custom-static-sso.template

COPY ./config-kong.sh /tmp
RUN chmod 777 /tmp/config-kong.sh
