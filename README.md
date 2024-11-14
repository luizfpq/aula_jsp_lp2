# Projeto para Aula Prática: Exemplo de Cadastro e Listagem de Pessoas

Este projeto é uma aplicação prática desenvolvida em Java com JSP e servlets, criada para ser utilizada em uma aula prática. O sistema permite o cadastro e a listagem de pessoas com atributos como nome, sobrenome, e endereço. A aplicação faz uso do servidor Apache Tomcat e segue a estrutura de uma aplicação web dinâmica.

## Tecnologias Utilizadas
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![JSP](https://img.shields.io/badge/JSP-007396?style=for-the-badge&logo=java&logoColor=white)
![Servlet](https://img.shields.io/badge/Servlet-5382a1?style=for-the-badge&logo=apache-tomcat&logoColor=white)
![Tomcat](https://img.shields.io/badge/Tomcat-4EAA25?style=for-the-badge&logo=apache-tomcat&logoColor=white)

## Estrutura do Projeto

- **Pessoa.java**: Classe modelo representando uma pessoa, com atributos de exemplo, como `idPessoa`, `nome`, `email`, etc.
- **PessoaServlet.java**: Servlet responsável por gerenciar o cadastro e a listagem de pessoas.
- **pessoa.jsp**: Página JSP que exibe a listagem de pessoas e permite a adição de novos cadastros.

## Como Executar o Projeto

1. Clone o repositório em sua máquina:
   ```bash
   git clone https://github.com/usuario/nome-do-repositorio.git```
2. Abra o projeto em sua IDE de preferência.
3. Certifique-se de ter o Apache Tomcat configurado (versão 9.0.41 ou posterior compatível).
4. Compile e execute o projeto, e acesse http://localhost:8080/nome-do-contexto/ para visualizar a aplicação.

### Dicas para o Uso do Repositório

    Acesse a documentação do código para entender o funcionamento de cada classe.
    Para modificar os atributos da pessoa, edite o arquivo Pessoa.java e ajuste as exibições no JSP.
    Se encontrar problemas ao carregar o JSTL, certifique-se de que as dependências no pom.xml estão corretamente configuradas.