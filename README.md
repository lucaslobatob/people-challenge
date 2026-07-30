# People Challenge

**Autor:** Lucas Lobato

## Descrição

Aplicação Full Stack desenvolvida como desafio técnico para cadastro de pessoas.

O sistema permite realizar as operações de CRUD (Create, Read, Update e Delete) de pessoas, permitindo listar, cadastrar, editar e excluir registros por meio de uma interface responsiva desenvolvida em Vue 3, consumindo uma API REST construída com Spring Boot.

## Funcionalidades

- ✅ Listagem de pessoas
- ✅ Cadastro de pessoas
- ✅ Edição de pessoas
- ✅ Exclusão com confirmação em modal
- ✅ Filtro por Nome
- ✅ Filtro por CPF/CNPJ
- ✅ Interface responsiva

## Tecnologias utilizadas

### Frontend

- Vue 3
- Vue Router
- Axios
- Bootstrap 5
- Bootstrap Icons

### Backend

- Java 21
- Spring Boot
- Spring Data JPA
- Maven
- H2 Database

---

# Como executar o projeto

## 1. Clonar o repositório

```bash
git clone https://github.com/SEU-USUARIO/people-challenge.git
```

Entre na pasta do projeto:

```bash
cd people-challenge
```

---

## 2. Executar o Backend

Acesse a pasta do backend:

```bash
cd people-api
```

Execute a aplicação:

### Linux / macOS

```bash
./mvnw spring-boot:run
```

### Windows

```bash
mvnw.cmd spring-boot:run
```

O backend ficará disponível em:

```
http://localhost:8080
```

> **Observação:** O projeto utiliza o banco de dados **H2 Database**, portanto não é necessária a instalação ou configuração de um banco de dados externo para executar a aplicação.

---

## 3. Executar o Frontend

Em outro terminal, acesse a pasta do frontend:

```bash
cd people-front
```

Instale as dependências:

```bash
npm install
```

Execute a aplicação:

```bash
npm run dev
```

O frontend ficará disponível em:

```
http://localhost:5173
```
