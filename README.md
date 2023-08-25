
# API de Produtos

Bem-vindo à documentação da API de produtos. Esta API permite a consulta e criação de produtos, 
como também inativa e desativa categoria, marca e produto.

Também é possivel cadastrar categoria e marca. Só é possivel inserir um produto novo caso a categoria e marca exista. 

Como adicional foi implementado  a publicação do evento na fila para PRODUTOS, MARCA e CATEGORIA quando é utilizado os endpoints do tipo POST/PATCH pelo RabbitMQ.

Projeto já está com testes unitários feitos.

## baixar imagem postgress, RabbitMQ 

Para baixar a imagem do postgress e rabbitMQ é necessário acessar o diretório da pasta "docker-compose/"
executar o comando abaixo:

docker-compose up -d

## Banco de dados

Em seguida pode utilizar uma ferramenta para acessar o banco de dados, exemplo: DBeaver

### Configurações de acesso ao banco de dados Postgress:
jdbc:postgresql://0.0.0.0:5432/product
username: postgresql
password: 123

**Obs**: Você encontra essas informacões também no arquivo application.yml

**Obs**: Quando subir o banco de dados, automaticamente a aplicacao executar o arquivo init.sql que está no diretório docker-compose/, 
o sql vai criar o banco de dados, tabelas e inserir categorias e marcas.

## Acesso ao RabbitMQ

**URL**
http://localhost:15672/#/
username: admin
password: 123456

Está sendo usado a exchange padrão do RabbitMQ:
Exchange: amq.direct

## Endpoints 

Executar as APIS por alguma ferramenta, por exemplo Postman.

### URL para baixar a collection:

Marca:
https://api.postman.com/collections/20150800-d085dda8-48a2-41ac-8470-dc1a0fdc9f81?access_key=PMAT-01H8PSSQX159MZ6ZBFGJEMZT63

Categoria:
https://api.postman.com/collections/20150800-df4b7e22-a921-49b8-8dc3-4b046c0b9593?access_key=PMAT-01H8Q3RPRE6DBBWP38TXW6YK6D

produto:
https://api.postman.com/collections/20150800-ae914c6b-e545-4f8c-bbe6-a9258d346060?access_key=PMAT-01H8Q3WY6P1F5744SG1VVPWPJ9


