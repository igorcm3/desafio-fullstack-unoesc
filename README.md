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

# Suposições
  - Cursos e Usuários podem ser excluidos apenas se não houverem Inscrições feitas com os mesmos, exemplo: Gerei uma inscrição com o user Igor no curso Eng. Computação. A partir deste momento, não será possivel excluir o curso Eng. Computação e o usuário Igor.
  - No cadastro do curso são inseridas disciplinas. Ao excluir disciplinas, as mesmas serão removidas do curso.
  - No cadastro de dsciplinas é necessario informar um professor, ao excluir um professor que esta associado a uma disciplina, a mesma ficará sem professor até que seja editada e incluido um novo.
  - 
# Rodando a aplicação
  - A aplicação foi desenvolvida utilizando o Java 11, desta forma, é necessario ter instalado o OpenJDK 11.
  - A IDE utilizada foi o VsCode, sendo necessário instalar algumas extensões, segue link com um tutorial na documentação oficial: https://code.visualstudio.com/docs/java/java-spring-boot
  - É necessário criar um banco de dados no MySQL, e informa-lo na **application.properties** dentro do projeto, juntamente com o username e password para configurar a conexão. Basta substituir em **spring.datasource.url** o trecho **desafio_unoesc** com o nome do banco criado, ou criar um banco com esse nome já pré configurado.
  - Ao subir aplicação, o spring irá acionar o JPA e irá criar todas as tabelas referentes as entidades do package **models**. Tambem ao subir a plicação, é inserido no banco de dados automaticamente os perfis ADMIN, PROFESSOR e ALUNO, na tabela perfil, e um usuario com CPF = 200 e senha = 200 com perfil de ADMIN. Desta forma, fica configurado um ponto de partida logando com este usuario e criando os demais registros. (vide class DataLoader no package repository para verificar este processo).
  - O projeto pode ser rodado em outras IDEs, todavia no desenvolvimento isso não foi testado.




