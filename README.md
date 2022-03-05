# CarService :car: :truck: :taxi:

## Objetivo 
- Criar uma aplicação web para alugueis de veiculos. 

### Descrição 
- O cliente poderá se cadastrar e escolher o veiculo que quiser para aluguar.
- Condições de aluguel: Além da taxa de aluguel padrão de cada veiculo, será cobrada um valor a mais por cada dia que o cliente ficar com o veiculo 

### Progresso
1. Criar uma API RESTful.
2. Criar um projeto web para acessar as funcionalidades da API.

## Tecnologias

### Backend
- Java
- JPA (sem usar spring data para gerar os repositorios)
- Testes unitarios
- Spring Boot
- Maven 
- GIT
- MVC (usei uma extrutura de pacotes por camadas (model, controller, service ...), entretanto segue o mesmo principio do MVC, acoplamento fraco e cada classe só tem um obrigação)

### Web
- HTML5
- CSS3
- Javascript

## Instruções

### Configuração do Banco de Dados

Como foi utilizado o banco h2 que é local você pode acessa-lo pela url abaixo
 
http://localhost:8080/locacao-veiculos-services/h2

Acessando a URL, será exibida uma página na qual será possível informar a URL do JDBC do banco para conexão:
Preencha com <b>jdbc:h2:file:${user.home}/locacao_veiculos_db/data</b>, onde ${user.home} é a pasta do seu usuário da máquina.

Preencha com o usuário e senha e conecte.

- usuario banco: h2sa
- senha banco: admin

### Executando a aplicação 

Abra o projeto backend em uma IDE de sua preferencia, e execute a classe <b>LocacaoVeiculosServicesApplication</b>

- passo 1: No projeto web entre dentro da pasta pages e executa qualquer uma das 3 paginas
- passo 2: pronto já pode começar a inserir, deletar e editar nas paginas

