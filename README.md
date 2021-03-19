# Desafio Programador Fullstack UNOESC

Esse é o nosso desafio para a vaga de programador fullstack na [UNOESC](https://www.unoesc.edu.br/). Serão testadas as habilidades e qualidade de código ao transformar requisitos limitados em uma aplicação web.

### DESAFIO

Desenvolver uma aplicação web responsável por gerenciar processos de uma instituição de ensino superior. Soluções parciais serão aceitas, mas o que for submetido deve estar funcionando.

A aplicação deverá possuir 03 (três) perfis diferentes:

Visão do administrador:
- Incluir, excluir, atualizar e visualizar usuários, cursos e disciplinas;

Visão do aluno:
- Realizar seu cadastro no sistema;
- Visualizar os cursos e efetuar sua inscrição em um deles.

Visão do professor:
- Visualizar as disciplinas ministradas pelo professor.
- Visualizar os alunos vinculados às disciplinas ministradas pelo professor.
- Imprimir um relatório com as disciplinas ministradas pelo professor no formato CSV, XLS ou PDF;

Atributos mínimos do usuário: 
- código, nome, cpf e perfil.

Atributos mínimos do curso: 
- código, nome e vagas.

Atributos mínimos da disciplina: 
- código, nome e curso.

Atributos mínimos da inscrição: 
- código, usuário e curso.

### ESCOPO DO DESAFIO

- Documentar todas suposições realizadas sobre o desafio no arquivo README.md.
  - Exemplo de suposição: Cada disciplina pode ser ministrada por apenas um professor.
- Tecnologias a serem utilizadas:
  - Java 8;
  - Spring Boot;
  - Maven;
  - Thymeleaf;
  - MySQL;
  - Git;

### AVALIAÇÃO

- O código será avaliado de acordo com os critérios: 
  - Build e execução da aplicação (resultado funcional);
  - Padrões de projeto MVC (Model View Controller); 
  - Clean code (pattern, manutenabilidade e clareza); 
  - Histórico do Git; 
  - Boas práticas de UI (Interface com o Usuário);
  - Estrutura do banco de dados;
  - Organização do relatório.
- Não esqueça de documentar o processo necessário para rodar a aplicação.


## DOCUMENTAÇÃO DA IMPLEMENTAÇÃO

- Para a implementação de autentcação, foi utilizado o spring security.
* O ceonteúdo base para a implementação se encontra nos links abaixo:

https://www.codejava.net/frameworks/spring-boot/spring-boot-security-authentication-with-jpa-hibernate-and-mysql

https://www.codejava.net/frameworks/spring-boot/spring-boot-security-customize-login-and-logout

- Para a implementação de perfil de usuários, tambem foi utilizado o Spring Secutiry
para que fosse feita a integração com o Thuymeleaf de exibir ou não os menus de acesso.




