# Docker keycloak with mysql database

### setup keycloak
* Build container keycloak with mysql database.
  * run this command
    * docker-compose up -d
* If you need to see tables the database , First you need to get ip address the container mysql
  * run this command 
    * docker inspect -f \
      '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' \
      [id-container]