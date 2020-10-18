# Trabalho Final de Engenharia de Software I - Clínica com Teste para COVID-19

## Sobre

Este repositório contém o código fonte para o trabalho final da disciplina de Engenharia de Software I (curso de Sistemas de Informação, UFMG). A proposta do trabalho foi o desenvolvimento de um sistema para clínicas que oferecem testes para COVID-19. O sistema foi desenvolvido em Java, utilizando padrões de projeto como MVC e DAO, além de interface construída com Java Swing e persistência no banco de dados MariaDB.

**Demonstração:** https://drive.google.com/file/d/1qEf2_BqH3uTwOVf5Zu_CxzWpCg5hOPmi/view?usp=sharing

## Contribuidores

Clone o repositório utilizando o comando:
```shell
$ git clone git@github.com:danilo-p/eng-soft-1-tp-clinica.git
```

Adicione o projeto na IDE de sua preferência. Eclipse é recomendado.


Instale o banco de dados mariadb:
```shell
$ sudo apt-get install mariadb
```

Entre na pasta do repositório e execute o script para criar o banco:
```shell
$ mysql -u root < create_db.sql
```
*Obs 1: Esse script remove o banco e o cria novamente, inserindo  alguns dados iniciais.*

*Obs 2: Você pode executar esse script várias vezes, porém perderá os seus dados do banco.*
