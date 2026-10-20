# Taskflow

API REST para criar, consultar, atualizar e excluir tarefas. O projeto usa Java 21, Spring Boot, Spring Data JPA e MySQL.

## Requisitos

- Java 21
- Docker e Docker Compose

## Como executar

1. Na raiz do projeto, inicie o banco de dados:

   ```bash
   docker compose -f docker/docker-compose.yml up -d
   ```

2. Execute a aplicação:

   **Windows (PowerShell):**

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

   **Linux/macOS:**

   ```bash
   ./mvnw spring-boot:run
   ```

A API estará disponível em `http://localhost:8080`. A aplicação conecta ao MySQL usando as configurações de `src/main/resources/application.properties`; mantenha essas configurações alinhadas às do serviço `db` em `docker/docker-compose.yml`.

## Documentação da API

Com a aplicação em execução, acesse a interface Swagger em:

```text
http://localhost:8080/swagger-ui/index.html
```

Todas as rotas de tarefas usam o prefixo `/v1/tasks`.

| Método | Rota | Descrição |
| --- | --- | --- |
| `GET` | `/v1/tasks` | Lista as tarefas, ordenadas por status. |
| `GET` | `/v1/tasks/{id}` | Consulta uma tarefa pelo ID. |
| `POST` | `/v1/tasks` | Cria uma tarefa com status `PENDING`. |
| `PUT` | `/v1/tasks/{id}` | Atualiza título, descrição e status da tarefa. |
| `DELETE` | `/v1/tasks/{id}` | Exclui a tarefa. |

Os status aceitos são `PENDING`, `IN_PROGRESS` e `COMPLETED`.

### Criar uma tarefa

```http
POST /v1/tasks
Content-Type: application/json
```

```json
{
  "title": "Preparar apresentação",
  "description": "Organizar os tópicos para a reunião"
}
```

A criação retorna a lista atualizada de tarefas. O status inicial é `PENDING`.

### Atualizar uma tarefa

```http
PUT /v1/tasks/1
Content-Type: application/json
```

```json
{
  "title": "Preparar apresentação",
  "description": "Apresentação revisada para a reunião",
  "status": "IN_PROGRESS"
}
```

A atualização também retorna a lista atualizada de tarefas.

### Consultar e excluir

```http
GET /v1/tasks
GET /v1/tasks/1
DELETE /v1/tasks/1
```

As operações por ID retornam `404 Not Found` quando a tarefa não existe. A exclusão bem-sucedida retorna `204 No Content`.

## Testes

Execute os testes com o Maven Wrapper:

**Windows (PowerShell):**

```powershell
.\mvnw.cmd test
```

**Linux/macOS:**

```bash
./mvnw test
```
