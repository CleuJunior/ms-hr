# Microsserviços de Recursos Humanos(RH)

Todo o projeto consiste em criar microserviços que possam ter uma folha de pagamento adequada para cada usuário. Os 
serviços são comunicados através das bibliotecas do Spring Cloud, utilizando Eureka, Hystrix, Zuul, Ribbon e Feign. 
Também foi utilizado o OAuth para ter acesso aos endpoints, dependendo do nível de privilégio de cada usuário, 
protegendo assim os endpoints da aplicação para uso externo.

### Colocando a aplicação de pé.

Toda a aplicação antes de ser levantada sera necessario fazer a instalaçao dos pacotes use sempre o comando 
`mvn clean install` para baixar e instalar os pacotes.


#### hr-config-server:

Antes de tudo, é necessário iniciar nosso [hr-config-server](hr-config-server), que é responsável por configurar o 
servidor dos outros microserviços. Após baixar as dependências do projeto basta apenas rodar a aplicação de bootstrap 
para subir o projeto na porta **8888**.

#### hr-eureka-server:

Agora, é necessário iniciar nossa aplicação [hr-eureka-server](hr-eureka-server) para permitir o registro automático e a 
descoberta dinâmica dos microserviços. A porta padrão é a **8761**.

#### hr-api-gateway-zuul:

Agora, após ter instalado as dependências do [hr-api-gateway-zuul](hr-api-gateway-zuul), basta iniciar a aplicação que 
está configurada para rodar na porta **8765**. O Zuul desempenha o papel de um gateway, servindo como o ponto de entrada 
centralizado para as demais aplicações.

#### hr-oauth:

Agora, após subir a aplicação [hr-oauth](hr-oauth), ela será responsável por conceder as devidas autorizações e 
permissões aos usuários cadastrados nas aplicações. Esse microserviço não precisa se preocupar com a porta em que está 
a ser executado, pois a nossa API Gateway fará o contato com a porta aleatória do serviço.

#### hr-payroll:

[hr-payroll](hr-payroll) e aplicação é bastante simples; ela visa calcular o salário que o trabalhador receberá com base 
na quantidade de dias trabalhados.

- Endpoint:

```http
GET /payments/{workerId}/days/{days}
```

| Parâmetro  | Tipo      | Descrição                    |
|:-----------|:----------|:-----------------------------|
| `workerId` | `Long`    | ID do trabalhador.           |
| `days`     | `Integer` | Número de dias trabalhados.. |

Resposta de exemplo:

```json
{
  "workerId": 123,
  "daysWorked": 10,
  "totalPayment": 500.00
}
```

#### hr-user:

O microserviço [hr-user](hr-user) é responsável por estabelecer a relação entre o usuário cadastrado e suas respectivas 
autorizações e permissões.

- Endpoint:

```http
GET /users/{id}
```

| Parâmetro | Tipo      | Descrição      |
|:----------|:----------|:---------------|
| `id`      | `Long`    | ID do usuário. |

Resposta de exemplo:

```json
{
  "id": 1,
  "name": "Claudio Dantas",
  "email": "dantas@outlook.com",
  "password": "0ec09ef9836da03f1add21e3ef607627e687e790",
  "roles": ["ROLE_USER"]
}
```

```http
GET /users/search?email=${email}
```

| Parâmetro | Tipo     | Descrição         |
|:----------|:---------|:------------------|
| `email`   | `string` | email do usuário. |

Resposta de exemplo:

```json
{
  "id": 2,
  "name": "Josuelen Dantas",
  "email": "jsdantas@outlook.com",
  "password": "0ec09ef9836da03f1add21e3ef607627e687e790",
  "roles": ["ROLE_USER", "ROLE_ADMIN"]
}
```

Use essa query para o banco de dados para poder adicionar usuarios para testes.

```sql
INSERT INTO tb_user (name, email, password) VALUES ('Nina Brown', 'nina@gmail.com', '$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu');
INSERT INTO tb_user (name, email, password) VALUES ('Leia Red', 'leia@gmail.com', '$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu');


INSERT INTO tb_role (role_name) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (role_name) VALUES ('ROLE_ADMIN');


INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
```

#### hr-worker:

O microserviço [hr-worker](hr-worker) é responsável por retornar os trabalhadores cadastrados.

- Endpoint:

```http
GET /workers
```
Resposta de exemplo:

```json
[
  {
    "id": 1,
    "name": "Claudio Dantas",
    "dailyIncome": 55.32
  },
  {
    "id": 2,
    "name": "Ferdnandis Dantas",
    "dailyIncome": 33.29
  }
]
```

```http
GET /workers/{id}
```

| Parâmetro | Tipo   | Descrição      |
|:----------|:-------|:---------------|
| `id`      | `Long` | ID do usuário. |

Resposta de exemplo:

```json
{
  "id": 1,
  "name": "Claudio Dantas",
  "dailyIncome": 55.32
}
```

#### Postman Setup

[Postman collection](Hr Microservice.postman_collection.json)

[Postman environment](hr-ms.postman_environment.json)

[//]: # ()
[//]: # ()
[//]: # ()
[//]: # (### 1.2 Implementar projeto hr-worker)

[//]: # ()
[//]: # ()
[//]: # (Script SQL)

[//]: # ()
[//]: # (```sql)

[//]: # ()
[//]: # (INSERT INTO tb_worker &#40;name, daily_Income&#41; VALUES &#40;'Bob', 200.0&#41;;)

[//]: # ()
[//]: # (INSERT INTO tb_worker &#40;name, daily_Income&#41; VALUES &#40;'Maria', 300.0&#41;;)

[//]: # ()
[//]: # (INSERT INTO tb_worker &#40;name, daily_Income&#41; VALUES &#40;'Alex', 250.0&#41;;)

[//]: # ()
[//]: # (```)

[//]: # ()
[//]: # ()
[//]: # ()
[//]: # ()
[//]: # (### 1.3 Criar projeto hr-payroll)

[//]: # ()
[//]: # ()
[//]: # ()
[//]: # ()
[//]: # (### 1.4 Implementar projeto hr-payroll &#40;mock&#41;)

[//]: # ()
[//]: # ()
[//]: # (### 1.5 RestTemplate)

[//]: # ()
[//]: # ()
[//]: # (### 1.6 Feign)

[//]: # ()
[//]: # ()
[//]: # (### 1.7 Ribbon load balancing)

[//]: # ()
[//]: # ()
[//]: # (# Fase 2: Eureka, Hystrix, Zuul)

[//]: # ()
[//]: # ()
[//]: # (### 2.1 Criar projeto hr-eureka-server)

[//]: # ()
[//]: # ()
[//]: # (### 2.2 Configurar hr-eureka-server)

[//]: # ()
[//]: # ()
[//]: # (Porta padrão: 8761)

[//]: # ()
[//]: # ()
[//]: # (Acessar o dashboard no navegador: http://localhost:8761)

[//]: # ()
[//]: # ()
[//]: # (### 2.3 Configurar clientes Eureka)

[//]: # ()
[//]: # ()
[//]: # (Eliminar o Ribbon de hr-payroll:)

[//]: # ()
[//]: # (- Dependência Maven)

[//]: # ()
[//]: # (- Annotation no programa principal)

[//]: # ()
[//]: # (- Configuração em application.properties)

[//]: # ()
[//]: # ()
[//]: # (Atenção: aguardar um pouco depois de subir os microsserviços)

[//]: # ()
[//]: # ()
[//]: # (### 2.4 Random port para hr-worker)

[//]: # ()
[//]: # ()
[//]: # (```)

[//]: # ()
[//]: # (server.port=${PORT:0})

[//]: # ()
[//]: # ()
[//]: # (eureka.instance.instance-id=${spring.application.name}:${spring.application.instance_id:${random.value}})

[//]: # ()
[//]: # (```)

[//]: # ()
[//]: # ()
[//]: # (Atenção: deletar as configurações múltiplas de execução de hr-worker)

[//]: # ()
[//]: # ()
[//]: # (### 2.5 Tolerância a falhas com Hystrix)

[//]: # ()
[//]: # ()
[//]: # (### 2.6 Timeout de Hystrix e Ribbon)

[//]: # ()
[//]: # ()
[//]: # (Atenção: testar antes sem a annotation do Hystrix)

[//]: # ()
[//]: # ()
[//]: # (```)

[//]: # ()
[//]: # (hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds=60000)

[//]: # ()
[//]: # (ribbon.ConnectTimeout=10000)

[//]: # ()
[//]: # (ribbon.ReadTimeout=20000)

[//]: # ()
[//]: # (```)

[//]: # ()
[//]: # ()
[//]: # (### 2.7 Criar projeto hr-zuul-server)

[//]: # ()
[//]: # ()
[//]: # (### 2.8 Configurar hr-zuul-server)

[//]: # ()
[//]: # ()
[//]: # (Porta padrão: 8765)

[//]: # ()
[//]: # ()
[//]: # (### 2.9 Random port para hr-payroll)

[//]: # ()
[//]: # ()
[//]: # ()
[//]: # (### 2.10 Zuul timeout)

[//]: # ()
[//]: # ()
[//]: # (Mesmo o timeout de Hystrix e Ribbon configurado em um microsserviço, se o Zuul não tiver seu timeout configurado, para ele será um problema de timeout. Então precisamos configurar o timeout no Zuul.)

[//]: # ()
[//]: # ()
[//]: # (Se o timeout estiver configurado somente em Zuul, o Hystrix vai chamar o método alternativo no microsserviço específico.)

[//]: # ()
[//]: # ()
[//]: # (# Fase 3: Configuração centralizada)

[//]: # ()
[//]: # ()
[//]: # (### 3.1 Criar projeto hr-config-server)

[//]: # ()
[//]: # ()
[//]: # (### 3.2 Configurar projeto hr-config-server)

[//]: # ()
[//]: # ()
[//]: # (Quando um microsserviço é levantado, antes de se registrar no Eureka, ele busca as configurações no repositório central de configurações.)

[//]: # ()
[//]: # ()
[//]: # (hr-worker.properties)

[//]: # ()
[//]: # (```)

[//]: # ()
[//]: # (test.config=My config value default profile)

[//]: # ()
[//]: # (```)

[//]: # ()
[//]: # (hr-worker-test.properties)

[//]: # ()
[//]: # (```)

[//]: # ()
[//]: # (test.config=My config value test profile)

[//]: # ()
[//]: # (```)

[//]: # ()
[//]: # (Teste:)

[//]: # ()
[//]: # (```)

[//]: # ()
[//]: # (http://localhost:8888/hr-worker/default)

[//]: # ()
[//]: # (http://localhost:8888/hr-worker/test)

[//]: # ()
[//]: # (```)

[//]: # ()
[//]: # ()
[//]: # (### 3.3 hr-worker como cliente do servidor de configuração, profiles ativos)

[//]: # ()
[//]: # ()
[//]: # (No arquivo bootstrap.properties configuramos somente o que for relacionado com o servidor de configuração, e também o profile do projeto.)

[//]: # ()
[//]: # ()
[//]: # (Atenção: as configurações do bootstrap.properties tem prioridade sobre as do application.properties)

[//]: # ()
[//]: # ()
[//]: # (### 3.4 Actuator para atualizar configurações em runtime)

[//]: # ()
[//]: # ()
[//]: # (Atenção: colocar @RefreshScope em toda classe que possua algum acesso às configurações)

[//]: # ()
[//]: # ()
[//]: # (### 3.5 Repositório Git privativo)

[//]: # ()
[//]: # ()
[//]: # (Atenção: reinicie a IDE depois de adicionar as variáveis de ambiente)

[//]: # ()
[//]: # ()
[//]: # (# Fase 4: autenticação e autorização)

[//]: # ()
[//]: # ()
[//]: # (### 4.1 Criar projeto hr-user)

[//]: # ()
[//]: # ()
[//]: # (### 4.2 Configurar projeto hr-user)

[//]: # ()
[//]: # ()
[//]: # (### 4.3 Entidades User, Role e associação N-N)

[//]: # ()
[//]: # ()
[//]: # (### 4.4 Carga inicial do banco de dados)

[//]: # ()
[//]: # ()
[//]: # ()
[//]: # ()
[//]: # (### 4.5 UserRepository, UserResource, Zuul config)

[//]: # ()
[//]: # ()
[//]: # (### 4.6 Criar projeto hr-oauth)

[//]: # ()
[//]: # ()
[//]: # (### 4.7 Configurar projeto hr-oauth)

[//]: # ()
[//]: # ()
[//]: # (### 4.8 UserFeignClient)

[//]: # ()
[//]: # ()
[//]: # (### 4.9 Login e geração do Token JWT)

[//]: # ()
[//]: # ()
[//]: # (Source -> Override -> configure&#40;AuthenticationManagerBuilder&#41;)

[//]: # ()
[//]: # ()
[//]: # (Source -> Override -> authenticationManager&#40;&#41;)

[//]: # ()
[//]: # ()
[//]: # (Basic authorization = "Basic " + Base64.encode&#40;client-id + ":" + client-secret&#41;)

[//]: # ()
[//]: # ()
[//]: # (### 4.10 Autorização de recursos pelo gateway Zuul)

[//]: # ()
[//]: # ()
[//]: # (### 4.11 Deixando o Postman top)

[//]: # ()
[//]: # ()
[//]: # (Variáveis:)

[//]: # ()
[//]: # (- api-gateway: http://localhost:8765)

[//]: # ()
[//]: # (- config-host: http://localhost:8888)

[//]: # ()
[//]: # (- client-name: CLIENT-NAME)

[//]: # ()
[//]: # (- client-secret: CLIENT-SECRET)

[//]: # ()
[//]: # (- username: leia@gmail.com)

[//]: # ()
[//]: # (- password: 123456)

[//]: # ()
[//]: # (- token:)

[//]: # ()
[//]: # ()
[//]: # (Script para atribuir token à variável de ambiente do Postman:)

[//]: # ()
[//]: # (```js)

[//]: # ()
[//]: # (if &#40;responseCode.code >= 200 && responseCode.code < 300&#41; {)

[//]: # ()
[//]: # (    var json = JSON.parse&#40;responseBody&#41;;)

[//]: # ()
[//]: # (    postman.setEnvironmentVariable&#40;'token', json.access_token&#41;;)

[//]: # ()
[//]: # (})

[//]: # ()
[//]: # (```)

[//]: # ()
[//]: # (### 4.12 Configuração de segurança para o servidor de configuração)

[//]: # ()
[//]: # ()
[//]: # (### 4.13 Configurando CORS)

[//]: # ()
[//]: # ()
[//]: # (Teste no navegador:)

[//]: # ()
[//]: # (```js)

[//]: # ()
[//]: # (fetch&#40;"http://localhost:8765/hr-worker/workers", {)

[//]: # ()
[//]: # (  "headers": {)

[//]: # ()
[//]: # (    "accept": "*/*",)

[//]: # ()
[//]: # (    "accept-language": "en-US,en;q=0.9,pt-BR;q=0.8,pt;q=0.7",)

[//]: # ()
[//]: # (    "sec-fetch-dest": "empty",)

[//]: # ()
[//]: # (    "sec-fetch-mode": "cors",)

[//]: # ()
[//]: # (    "sec-fetch-site": "cross-site")

[//]: # ()
[//]: # (  },)

[//]: # ()
[//]: # (  "referrer": "http://localhost:3000",)

[//]: # ()
[//]: # (  "referrerPolicy": "no-referrer-when-downgrade",)

[//]: # ()
[//]: # (  "body": null,)

[//]: # ()
[//]: # (  "method": "GET",)

[//]: # ()
[//]: # (  "mode": "cors",)

[//]: # ()
[//]: # (  "credentials": "omit")

[//]: # ()
[//]: # (}&#41;;)

[//]: # ()
[//]: # (```)