Projeto de teste - por Leonardo Quartieri

# Visão geral

O projeto é uma aplicação back-end com objetivo de disponibilizar uma APIs utilizando os recursos Spring Boot, Postgres, Docker e docker-compose para gerenciamento de uma conta simples e transacoes sobre a mesma.


# Setup da aplicação (local)

## Instalação da aplicação

É preciso compilar o código e baixar as dependências do projeto:
```
mvn clean install
```
Para este teste utilizei o banco postgres (https://www.postgresql.org/) num container docker (https://www.docker.com/), com o docker rodando na sua maquina suba o banco antes de tentar rodar a aplicacao:
```
docker run -p 5432:5432 -e POSTGRES_PASSWORD=1234 postgres
```
Nao se preocupe, a aplicação esta utilizando Flyway, assim vai gerar um banco vazio quando subir a primeira vez. 
O Flyway eh uma maneira incrivel de gerenciar o banco das aplicações com suas alterações.
Mais info sobre Flyway: https://flywaydb.org/


Finalizado esse passo, vamos iniciar a aplicação:
```
mvn spring-boot:run
```
Neste ponto você já pode acessar nossa aplicação utilizando um Postman (https://www.postman.com/), por exemplo. Deixe na pasta resources da aplicação a collection do postman com as chamadas para os testes.
Collection: src/main/resources/Teste-Objective.postman_collection.json
A aplicação está disponível em http://localhost:8080

A aplicação esta segura por uma basic autentication, aqui estão os dados de auth: user= user, password= password
Existe uma imagem mostrando como fazer esta config no Postman para conseguir executar as chamadas, a images esta aqui: src/main/resources/evidencias/autenticacao_api.png


```
Tomcat started on port(s): 8080 (http)
Started AppConfig in xxxx seconds (JVM running for xxxx)
```
Swagger (https://swagger.io/) da aplicação pode ser consultado em:
http://localhost:8080/swagger-ui/index.html#/

Inclui também o Java melody (https://github.com/javamelody/javamelody) na aplicação pode ser consultado em:
http://localhost:8080/monitoring
Este serve para verificar erros, banco, processamento e muito mais sobre a ibtancia/conteiner no qual esta rodando nossa aplicacao

# Setup da aplicação com docker

## Preparando ambiente

Temos um Dockerfile na pasta raiz do projeto, e assim que buildar a primeira vez ja teremos um jar para ser incluido na imagem do docker.
Assim ja poderiamos rodar o comando docker para criar nossa imagem:
```
docker build .
```

Depois disso já teremos nossa imagem criada, assim podemos derrubar o container para subir tudo, agora via compose (já sobe a aplicacao e obanco de dados)

Ainda na pasta raiz, suba nossa aplicacao + base utilizando o docker-compose:
```
docker-compose up
```
Agora, da mesmoa forma que fizemos antes, é só acessar a url http://localhost:8080 e testar novamente a aplicação.