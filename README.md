# Products API

Este repositório é um projeto pessoal para estudos em Java, focado em chamadas HTTP utilizando um **DTO** para produtos.

## Descrição

A API é uma aplicação simples de gerenciamento de produtos, que oferece operações CRUD (Create, Read, Update, Delete) para manipulação de uma lista de produtos. As operações são realizadas através de chamadas HTTP utilizando o framework **Spring Boot**.

### Funcionalidades

- **POST /product**: Adiciona um novo produto.
- **GET /product/listAll**: Retorna a lista de todos os produtos.
- **PUT /product**: Atualiza o preço de um produto com base no SKU.
- **DELETE /product/{sku}**: Remove um produto da lista pelo SKU.

### Estrutura de Dados

O projeto utiliza um `ProductDTO` com os seguintes atributos:

- `sku`: Identificador único do produto.
- `name`: Nome do produto.
- `description`: Descrição do produto.
- `price`: Preço do produto.
- `weight`: Preço do produto.

## Tecnologias Utilizadas

- Java
- Spring Boot
- DTO (Data Transfer Object)

### Atualizações

Adicionado também rotas para reservation, simulando reservas de um restaurante.