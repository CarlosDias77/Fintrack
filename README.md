# Fintrack 💰

API REST para controle de finanças pessoais, desenvolvida com Java e Spring Boot.
Permite registrar lançamentos financeiros classificados como receita ou despesa,
com autenticação segura via JWT.

> 🚀 Deploy disponível no Railway.

---

## 🛠️ Tecnologias

- **Java 25**
- **Spring Boot 4.0.6**
- **Spring Security**
- **JJWT 0.12.6** — geração e validação de tokens JWT
- **PostgreSQL**
- **Spring Data JPA / Hibernate**
- **Lombok**
- **Railway** — deploy em nuvem

---

## ✅ Funcionalidades implementadas

- Cadastro de usuários
- Autenticação via JWT (stateless)
- Registro de lançamentos com descrição, valor, data e tipo (receita/despesa)
- Listagem de lançamentos por usuário
- Remoção de lançamentos
- Endpoints protegidos — acesso apenas com token válido

## 🚧 Funcionalidades planejadas

- Controle de recorrência e parcelas
- Separação entre renda fixa e variável
- Relatórios financeiros mensais
- Integração com IA para análise de gastos

---

## 🚀 Como rodar localmente

### Pré-requisitos

- Java 25+
- PostgreSQL
- Maven

### Passos

1. Clone o repositório
```bash
   git clone https://github.com/CarlosDias77/Fintrack
   cd fintrack
```

2. Crie o banco de dados
```sql
   CREATE DATABASE fintrack;
```

3. Configure as variáveis de ambiente (veja seção abaixo)

4. Rode a aplicação
```bash
   ./mvnw spring-boot:run
```

5. A API estará disponível em `http://localhost:8080`

---

## ⚙️ Variáveis de ambiente

Configure as seguintes variáveis no seu ambiente ou em `application.properties`:

```properties
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/fintrack
SPRING_DATASOURCE_USERNAME=seu_usuario
SPRING_DATASOURCE_PASSWORD=sua_senha
JWT_SECRET=sua_chave_secreta_aqui
```

> ⚠️ Nunca commite credenciais reais no repositório.

---

## 📡 Endpoints

### Autenticação
| Método | Rota | Descrição | Auth |
|--------|------|-----------|------|
| POST | `/auth/login` | Realiza login e retorna token JWT | ❌ |
| POST | `/usuarios` | Registra novo usuário | ❌ |

### Usuários
| Método | Rota | Descrição | Auth |
|--------|------|-----------|------|
| GET | `/usuarios` | Lista todos os usuários | ✅ |
| PUT | `/usuarios/{id}` | Atualiza usuário | ✅ |
| DELETE | `/usuarios/{id}` | Remove usuário | ✅ |

### Lançamentos
| Método | Rota | Descrição | Auth |
|--------|------|-----------|------|
| POST | `/lancamentos` | Cria novo lançamento | ✅ |
| GET | `/lancamentos/usuario/{id}` | Lista lançamentos do usuário | ✅ |
| DELETE | `/lancamentos/{id}` | Remove lançamento | ✅ |

> ✅ Requer header: `Authorization: Bearer {token}`

---

## 🧠 Decisões técnicas

### Por que JWT?
Optei por JWT para manter a API stateless — o servidor não precisa armazenar
sessões. Cada requisição carrega suas próprias credenciais no token, o que
facilita escalabilidade e deploy em nuvem.

### Por que o registro está fora do /auth?
O endpoint `/auth/login` exige que o usuário já exista no banco. Se o registro
também exigisse autenticação, nenhum usuário novo conseguiria se cadastrar —
um paradoxo. Por isso `POST /usuarios` é público, enquanto os demais
endpoints são protegidos.

### Por que Spring Security + BCrypt?
Spring Security é o padrão do mercado para aplicações Java. BCrypt foi escolhido
para hash de senhas por ser resistente a ataques de força bruta — cada hash
gerado é único mesmo para senhas iguais, devido ao salt automático.

---

## 👨‍💻 Autor

Carlos — Desenvolvedor Java Backend em formação.  
[GitHub](https://github.com/CarlosDias77) · [LinkedIn](https://www.linkedin.com/in/carlos-henrique-corsi-dias-0502432ba)
