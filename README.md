# Desafio-delivery

## Pré requisitos
- #### Maven 
- #### Docker
- #### Java 8

# Ambiente


### Linux

```
./mvnw package 
``` 

```
docker build -t desafio-delivery .
``` 

```
docker-compose -f docker-compose.yml up -d
``` 

### Windows

```
mvnw package 
``` 

```
docker build -t desafio-delivery .
``` 

```
docker-compose -f docker-compose.yml up -d
``` 

## Endpoints


### Cadastrar Parceiro

```
POST http://localhost:8080/parceiros
``` 

###Buscar por Id

```
GET http://localhost:8080/parceiros/{id}
``` 

###Buscar Parceiro Próximo

```
GET http://localhost:8080/parceiros/proximo?long=30.0&lat=30.0
``` 



