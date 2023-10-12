# Java e JDBC: trabalhando com um banco de dados

## Aula 1: Conhecendo a aplicação

- Para salvar um dado permanentemente, precisamos de um banco de dados. Isso porque em uma lista estática, se adicionarmos um novo valor, pararmos de executar a aplicação e executarmos novamente em seguida, o dado se perde;
- Para acessar o banco de dados, precisamos de um driver. Um driver é uma biblioteca (JAR), que podemos buscar no Maven Repository;
- Para abrir uma conexão, devemos usar o método getConnection(), da classe DriverManager;
- O método getConnection() recebe uma string de conexão JDBC, que define o host, database, usuário e senha.

## Aula 2: 

- Para simplificar e encapsular a criação da conexão, devemos usar uma classe ConnectionFactory ;
- O Factory Method encapsula a criação de um objeto;
- Para inserir dados na banco de dados, podemos usar a interface java.sql.PreparedStatement e a cláusula insert do sql;
- Para melhorar o código definindo responsabilidades, podemos criar uma classe DAO (Data Access Object), que ficará responsável por toda integração com o banco de dados.

### Utilizando a classe DAO

Existe um padrão muito utilizado para definir essa parte de banco de dados. Ele é conhecido como Data access object (DAO), no português, objeto de acesso a dados. Como ele dá acesso ao objeto externo, encapsulamos em uma classe DAO e deixamos o banco de dados nele. 

### Para saber mais:

- [Padrão Factory](https://refactoring.guru/pt-br/design-patterns/factory-method)

## Aula 3:

- Para listar dados do banco de dados, podemos usar a interface java.sql.PreparedStatement e a cláusula select do SQL;
- Precisamos fechar os recursos do banco de dados, como conexão, Prepared Statement, Result Set, entre outros;
- É boa prática usar um pool de conexões, que administra/controla a quantidade de conexões abertas;
- Como existe uma interface que representa a conexão (java.sql.Connection), também existe uma que representa o pool de conexões (javax.sql.DataSource) .

## Aula 4:

- Para atualizar uma informação já existente no banco de dados, podemos usar a interface java.sql.PreparedStatement e a cláusula update do SQL;
- Usamos o update para poder sacar e depositar valores nas contas;
- Realizamos a transferência entre contas, reaproveitando os métodos de realizar depósito e saques.

### Para saber mais:

- [Transações no SQL: Mantendo os dados íntegros e consistentes](https://www.alura.com.br/artigos/transacoes-no-sql-mantendo-os-dados-integros?_gl=1*jqakob*_ga*MTc3NTM0NzMwNi4xNjczODg5Nzkz*_ga_1EPWSW3PCS*MTY5NzEyMTgzMy4xNjMuMS4xNjk3MTIxODM0LjAuMC4w*_fplc*UHcyUzExb1kxJTJGQXR1dkcxa3kyQkNZdzZMWlA4QkZ4MzJDeUFmRXM5YWpDVEY3R3N0NGdudyUyQkslMkZnWWcza3NRVmgySVRBWUoyaGdSZldDdmdja1k1cG5yOFhST01OJTJGJTJCUXN1TlpXJTJGV1ZLUU5kVDBZRWNhVEhhQ2tqWk5XeVpRJTNEJTNE)

## Aula 5:

- Para deletar uma informação no banco de dados, podemos usar a interface java.sql.PreparedStatement e a cláusula delete do SQL;
- Usamos o delete para remover contas;
- Realizamos o delete lógico, com o qual não apagamos o dado do banco e só informamos se a conta está ativa ou não. Caso esteja, o valor é retornado nas pesquisas. Caso contrário, não.

### Para saber mais

- [SQL JOIN: Aprenda INNER, LEFT, RIGHT, FULL e CROSS](https://www.alura.com.br/artigos/join-em-sql?_gl=1*1rjd222*_ga*MTc3NTM0NzMwNi4xNjczODg5Nzkz*_ga_1EPWSW3PCS*MTY5NzEzMTg3NS4xNjQuMS4xNjk3MTMxOTQ0LjAuMC4w*_fplc*RyUyQk5vZ1NxRm9rb0lrOWRVb0tGbDB2YkQ0UXpaUHQyWlJRQUFZUzhFT0lua2JWUHklMkJzSExLNzZINlF2S2VDU0ExVHQ3Nndia2Z6SmZZdlhIaWFDTllGdE52UWpITEw0ZmFobmJZRWcwWTNUV3doQU9ianBzaG1aUWNOUXI1ZyUzRCUzRA..)