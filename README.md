# 📚 Sistema de Biblioteca Digital

Este projeto é um trabalho prático desenvolvido para consolidar os conhecimentos adquiridos no curso sobre **Jakarta EE com Quarkus**, com foco em **arquitetura em camadas**, **relacionamentos JPA**, **injeção de dependência**, e **interface web com Jakarta Faces**.

---

## 🧭 Sumário

1. [🎯 Objetivo da Atividade](#-objetivo-da-atividade)  
2. [📊 Competências Avaliadas](#-competências-avaliadas)  
3. [📋 Especificações do Sistema](#-especificações-do-sistema)  
4. [🏗️ Estrutura do Projeto](#-estrutura-do-projeto)  
5. [📦 Implementação das Camadas](#-implementação-das-camadas)  
6. [🎨 Interface de Apresentação](#-interface-de-apresentação)  
7. [💾 Dados de Exemplo](#-dados-de-exemplo)  
8. [📝 Critérios de Avaliação](#-critérios-de-avaliação)  
9. [🚀 Instruções de Execução e Entrega](#-instruções-de-execução-e-entrega)  
10. [💡 Dicas de Implementação](#-dicas-de-implementação)  
11. [📚 Recursos de Apoio](#-recursos-de-apoio)

---

## 🎯 Objetivo da Atividade

Desenvolver um sistema web completo de **gestão de biblioteca digital**, utilizando as principais tecnologias e práticas de desenvolvimento empresarial com **Quarkus + Jakarta EE**.

---

## 📊 Competências Avaliadas

| Competência              | Descrição |
|--------------------------|-----------|
| Arquitetura em Camadas   | Separação entre camadas de apresentação, serviço e persistência |
| Relacionamentos JPA      | One-to-Many e Many-to-One com JPA/Hibernate |
| Injeção de Dependência   | Uso de CDI para gerenciamento de componentes |
| Controle Transacional    | Operações com segurança e consistência de dados |
| Interface de Apresentação| Desenvolvimento com **Jakarta Faces (JSF)** |

---

## 📋 Especificações do Sistema

### 3.1. Entidades Principais

- **Autor**: `id`, `nome`, `email`, `dataNascimento`, `biografia`  
- **Livro**: `id`, `titulo`, `isbn`, `dataPublicacao`, `numeroPaginas`, `disponivel`, `autorId`  
- **Empréstimo**: `id`, `nomeUsuario`, `emailUsuario`, `dataEmprestimo`, `dataDevolucaoPrevista`, `dataDevolucao`, `livroId`  

### 3.2. Relacionamentos JPA

- `Autor → Livro`: One-to-Many  
- `Livro → Empréstimo`: One-to-Many  

---

## 🏗️ Estrutura do Projeto

### 4.1. Dependências (Maven)

```xml
<dependencies>
    <dependency>
        <groupId>io.quarkus</groupId>
        <artifactId>quarkus-resteasy-reactive</artifactId>
    </dependency>
    <dependency>
        <groupId>io.quarkus</groupId>
        <artifactId>quarkus-faces</artifactId>
    </dependency>
    <dependency>
        <groupId>io.quarkus</groupId>
        <artifactId>quarkus-hibernate-orm-panache</artifactId>
    </dependency>
    <dependency>
        <groupId>io.quarkus</groupId>
        <artifactId>quarkus-jdbc-postgresql</artifactId>
    </dependency>
    <dependency>
        <groupId>io.quarkus</groupId>
        <artifactId>quarkus-junit5</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
