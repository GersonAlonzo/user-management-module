FROM mysql:8.0

COPY ./init.sql /docker-entrypoint-initdb.d/

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=sompopo
ENV MYSQL_USER=root2
ENV MYSQL_PASSWORD=root

EXPOSE 3306
