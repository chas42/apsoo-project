# **Projeto APSOO - 2024.2 - UFRPE - AlugaFácil**

## **Visão Geral**

Este projeto é baseado em **microsserviços** e utiliza **Eureka** para _service discovery_, **Spring Cloud Gateway** como _API Gateway_ e serviços backend em **.NET Core** e **Spring**. A arquitetura permite escalabilidade e fácil manutenção dos serviços.

## **Estrutura do Projeto**

```bash
Projeto/
│   ├── docker-compose.yml  # Configurações do Docker Compose
│   ├── discovery/          # Eureka Discovery
│   ├── gateway/            # API Gateway
│   ├── property/           # Microsserviço de Propriedades
│   ├── usuario-service/    # Microsserviço de Usuários
│   ├── payment/            # Microsserviço de Pagamento
│   └── README.md           # README do projeto "AlugaFácil"
```

---

## **🛠 Tecnologias Utilizadas**

- **.NET Core 8.0** (ASP.NET Core)
- **Spring** (Framework Java)
- **Spring Cloud Gateway** (API Gateway)
- **Eureka** (Service Discovery)
- **SQLite, M2 DataBase e InMemory** (Banco de dados)
- **Docker e Docker Compose** (Contêineres)

---

## **🚀 Como Executar o Projeto**

### **Pré-requisitos**

Antes de iniciar, verifique se você tem os seguintes softwares instalados:

- [Docker](https://www.docker.com/get-started) (versão mais recente se possível :D)
- [Docker Compose](https://docs.docker.com/compose/install/)

---

### **Executar o projeto com Docker Compose**

Para iniciar todos os serviços do projeto, execute o seguinte comando no diretório raiz do projeto:

```sh
docker-compose up --build
```

Esse comando irá:

- **Construir** as imagens Docker dos serviços
- **Subir** os contêineres do Eureka Server, Gateway e os Microsserviços
- **Conectar os serviços automaticamente** através do Eureka

### **Verificar os Serviços**

Após iniciar, os serviços estarão rodando nas seguintes portas:

| Serviço           | URL (Padrão)                     | Descrição                   |
| ----------------- | -------------------------------- | --------------------------- |
| **Eureka Server** | `http://localhost:8761/`         | Painel de Service Discovery |
| **Gateway**       | `http://localhost:8084/`         | API Gateway                 |
| **Usuários**      | `http://localhost:8084/user`     | Microsserviço de Usuários   |
| **Imóveis**       | `http://localhost:8084/property` | Microsserviço de Imóveis    |
| **Pagamentos**    | `http://localhost:8084/payment`  | Microsserviço de Pagamento  |

### **Testar a API**

Após subir os serviços, você pode testar as APIs usando **Postman** ou **cURL**.
