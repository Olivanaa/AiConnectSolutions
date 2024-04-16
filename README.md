# AI Connect Solutions
API do projeto AIConnectSolutions - Sistema de Relacionamento de Clientes

---

## Integrantes
Ana Clara Rocha de Oliveira: Java<br>
Thiemi Hiratani Favaro: Mobile<br>
Stephany Siqueira: C#.Net<br>
Camila dos Santos Cunha: Devops<br>
Guilherme Castro: Banco de Dados

---

## Diagramas
![Diagrama de classes](diagrama_classes.png)
![DER](DER.jpg)
---

## Vídeo de Apresentação



## Documentação da API

### Endpoint
- [Listar Todos os Clientes](#listar-todos-os-clientes)
- [Cadastrar Cliente](#cadastrar-cliente)
- [Detalhes do Cliente](#detalhes-do-cliente)



### Listar Todos os Clientes

`GET` /cliente

Retorna todos os clientes cadastrados.

#### Exemplo de Resposta

```js
[
	{
		"id": 1,
		"nome": "Fulano da Silva",
		"email": "fulano@example.com",
		"telefone": "1122334455",
		"dtaNascimento": "1990-01-01",
		"dtaCadastro": "2022-10-15",
		"segmentoMercado": "Tecnologia",
		"dtaUltimaInteracao": "2023-05-20",
		"interesses": "Tênis, Viagens, Música",
		"historico": "Novo cliente em potencial"
	}
]
```

#### Códigos de Status

|código|descrição|
|------|---------|
|200|A lista de clientes foi retornada com sucesso!
|401|Acesso não permitido. É necessário autentificação.

---

### Cadastrar Cliente 

`POST` /cliente

Cria um novo cliente.

#### Corpo da Requisição

|campo|tipo|obrigatório|descrição|
|-----|----|:-----------:|---------|
|nome|string|✅| Nome do cliente.
|email|string|✅|Email do cliente.
|telefone|string|✅|Telefone do cliente.
|dtaNascimento|localDate|✅|Data de nascimento do cliente.
|dtaCadastro|localDate|✅|Data de cadastro do cliente.
|segmentoMercado|string|✅|Segmento de mercado do cliente.
|interesses|string|✅|Interesses do cliente.
|historico|string|✅|Histórico do cliente.
|dtaUltimaInteracao|localDate|✅|Data da ultima interação do cliente.



```js
{
  "nome": "Fulano da Silva",
  "email": "fulano@example.com",
  "telefone": "1122334455",
  "dtaNascimento": "1990-01-01",
  "dtaCadastro": "2022-10-15",
  "segmentoMercado": "Tecnologia",
  "interesses": "Tênis, Viagens, Música",
  "historico": "Novo cliente em potencial",
  "dtaUltimaInteracao": "2023-05-20"
	
}
```

#### Exemplo de Resposta

```js
{
	"id": 1,
	"nome": "Fulano da Silva",
	"email": "fulano@example.com",
	"telefone": "1122334455",
	"dtaNascimento": "1990-01-01",
	"dtaCadastro": "2022-10-15",
	"segmentoMercado": "Tecnologia",
	"dtaUltimaInteracao": "2023-05-20",
	"interesses": "Tênis, Viagens, Música",
	"historico": "Novo cliente em potencial"
}
```

#### Códigos de Status

|código|descrição|
|------|---------|
|201|Cliente adicionado com sucesso!
|400|Não foi possível adicionar o cliente. Verifique o corpo da requisição
|401|Acesso não permitido. É necessário autentificação.
---

### Detalhes do Cliente

`GET` /explorar/`{id}`

Retorna um cliente específico pelo ID. Parâmetros de entrada:
ID do cliente.

#### Exemplo de Resposta

```js
// requisição para /cliente/1
{
	"id": 1,
	"nome": "Fulano da Silva",
	"email": "fulano@example.com",
	"telefone": "1122334455",
	"dtaNascimento": "1990-01-01",
	"dtaCadastro": "2022-10-15",
	"segmentoMercado": "Tecnologia",
	"dtaUltimaInteracao": "2023-05-20",
	"interesses": "Tênis, Viagens, Música",
	"historico": "Novo cliente em potencial"
}
```

#### Códigos de Status

|código|descrição|   
|------|---------|
|200|Os dados do cliente foram retornados com sucesso!
|401|Acesso não permitido. É necessário autentificação.
|404|Não existe dados do cliente com o `id` informado.

___

