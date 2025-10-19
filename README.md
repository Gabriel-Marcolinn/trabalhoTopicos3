# 📚 Sistema de Biblioteca Digital (Quarkus + JPA + JSF)
Este projeto é uma aplicação completa de um Sistema de Biblioteca Digital, desenvolvido com Jakarta EE e Quarkus. O objetivo é demonstrar uma arquitetura em camadas (Apresentação, Negócio, Persistência), o uso de relacionamentos JPA (One-to-Many, Many-to-One) e a integração de componentes com CDI.

A aplicação gerencia Autores, Livros e Empréstimos, exibindo os dados e estatísticas em uma interface web construída com Jakarta Faces (JSF).

🚀 Tecnologias Utilizadas
- Framework: Quarkus
Interface Web (View): Jakarta Faces (JSF)
- Persistência (ORM): Jakarta Persistence (JPA) / Hibernate
- Injeção de Dependência: CDI
- Banco de Dados: PostgreSQL
- Build Tool: Maven

🏗️ Estrutura do Projeto
O projeto segue uma arquitetura em camadas para uma clara separação de responsabilidades:
- com.biblioteca.entity: Contém as entidades JPA (Autor, Livro, Emprestimo) que mapeiam o banco de dados.
- com.biblioteca.repository: Responsável pelas operações de persistência e consultas JPQL (ex: AutorRepository, LivroRepository). Utiliza o EntityManager injetado via @PersistenceContext.
- com.biblioteca.service: Contém a lógica de negócio, orquestrando as chamadas aos repositórios (ex: BibliotecaService).
- com.biblioteca.controller: Camada de apresentação (View Model), contém os Managed Beans (@Named) do JSF (ex: BibliotecaBean).
- src/main/resources/META-INF/resources/: Contém as páginas web (index.xhtml).
- src/main/resources/application.properties: Arquivo central de configurações da aplicação (banco de dados, JSF, Hibernate).
- src/main/resources/import.sql: Script de carga inicial de dados, executado automaticamente pelo Hibernate na inicialização.

⚙️ Pré-requisitos
- JDK 17+
- Apache Maven 3.8+
- Um servidor PostgreSQL em execução.

🚀 Como Executar
Siga os passos abaixo para configurar e iniciar a aplicação localmente.
1. Criar o Banco de Dados

Primeiro, você precisa de um banco de dados PostgreSQL acessível. O application.properties está configurado para o banco biblioteca, mas você pode alterar isso.

Exemplo de script SQL para criar o usuário e o banco (conforme especificação do projeto):

```SQL
CREATE USER biblioteca WITH PASSWORD 'biblioteca123';
CREATE DATABASE biblioteca_digital WITH OWNER biblioteca;
```
2. Configurar o application.properties

Abra o arquivo src/main/resources/application.properties. Se o seu nome de usuário, senha ou nome do banco de dados forem diferentes do padrão, atualize as seguintes linhas:

Proprieties

```
# Configuração do banco de dados
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=postgres
quarkus.datasource.password= 'SUA SENHA DO BANCO AQUI'
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/biblioteca

# Hibernate
quarkus.hibernate-orm.database.generation=drop-and-create
quarkus.hibernate-orm.log.sql=true
quarkus.hibernate-orm.sql-load-script=import.sql

# Importante: A propriedade quarkus.hibernate-orm.database.generation=drop-and-create fará com que as tabelas sejam recriadas a cada inicialização, e o import.sql será executado para popular o banco com os dados de exemplo.
```

3. Executar a Aplicação (Modo de Desenvolvimento)
   
No terminal, na raiz do projeto, execute o seguinte comando Maven para compilar e iniciar o Quarkus em modo de desenvolvimento (que inclui live reload):

```
./mvnw compile quarkus:dev
```
Aguarde o console exibir que o Quarkus foi iniciado.

4. Acessar a Aplicação
   
Após a aplicação iniciar, acesse a página principal no seu navegador:
```
http://localhost:8080/index.html
```