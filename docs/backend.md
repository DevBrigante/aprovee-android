# Backend – Architecture, API and Security

A concise guide on how the Aprovee backend should work: architecture, API design,
data model, security, testing, and operations. It serves to:
- Help me (junior dev) understand the backend as a whole.
- Help Claude make consistent, justified, and secure decisions.

---

## 1. Overview

- Goal: expose a secure API for the Android app (organizing salespeople, customers, orders).
- Ensure confidentiality and integrity of user data.
- Keep it simple enough for me to learn and maintain, but with room to evolve.

High-level overview (no fixed stack yet):
- Client: Android app (Kotlin + Jetpack Compose).
- Backend: REST/JSON HTTP API with token-based authentication (e.g.: JWT).
- Database: relational (e.g.: PostgreSQL) or equivalent, with clear justification.
- Infra: simple PaaS/managed service (Railway/Render/Heroku-like or managed cloud).

When the stack is not yet decided, Claude must:
1. Ask questions about experience, budget, and expected scale.
2. Propose 2–3 options with pros/cons.
3. Recommend an initial path suited to my level.

---

## 2. Non-Functional Goals

Priority (in order):
1. Security (passwords, customer and salesperson data, avoid leaks).
2. Reliability (predictable API, clear error messages).
3. Development simplicity (good learning curve).
4. Reasonable scalability (grow without rewriting everything).
5. Observability (minimal logs and basic metrics).

In conflicts between "shortcut" and "security", prioritize security.

---

## 3. Stack and Services (Guidelines)

> This section must be updated as decisions are made.

- Language/framework (to be decided): e.g. Kotlin (Ktor/Spring Boot) or Node.js (NestJS/Express).
  - Criteria: familiarity, security libraries, documentation/community, ease of deploy.
- Relational database (e.g.: PostgreSQL):
  - Robust transactions, good relationship support, migration and backup tooling.
- Infra/deploy:
  - HTTPS by default.
  - Environment variables for secrets.
  - Easy access to logs.

---

## 4. API Design

General style:
- RESTful API in JSON.
- Resources as nouns: `/salespeople`, `/customers`, `/orders`.
- HTTP methods: `GET` read, `POST` create, `PUT/PATCH` update, `DELETE` remove.

URLs and versioning:
- `/api/v1/...` prefix from the start.
- Lowercase names with hyphens when needed: `/api/v1/active-salespeople`.

Responses and errors:
- Typical codes: `200/201` success, `400` validation, `401` unauthenticated,
  `403` no permission, `404` not found, `429` rate limiting, `500` internal error.
- Errors in JSON with:
  - `code` (short string),
  - `message` (clear and safe message),
  - optional `details` (validations), no stack trace.

Lists:
- Pagination with `?page=` and `?pageSize=` for large lists.
- Simple query string filters (e.g.: `?status=active`), always validated.

---

## 5. Data Model (Conceptual)

> Column/index details can go in `docs/db-schema.md`.

Initial entities (may evolve):

- **Salesperson**: basic data (name, contact, region, status); relates to customers/orders.
- **Customer**: identification (name, document, contact); optional link to a primary Salesperson.
- **Order** (if applicable): values, status, dates; linked to Salesperson and Customer.
- **System User**: email, hashed password + salt, roles (admin, salesperson, etc.).

Always maintain:
- Who "owns" each piece of data.
- Relationships (1:N, N:N) and the reason for each.

---

## 6. Authentication and Authorization

Authentication:
- Prefer tokens (e.g.: JWT) with adequate expiration.
- Never store passwords in plain text.
- Hash passwords with modern algorithms (e.g.: bcrypt, Argon2, PBKDF2) and always with salt.

Authorization:
- Resource-level checks:
  - A salesperson can only access/edit their own data and associated records.
  - Admins have broader access, but always explicit.
- Protect against BOLA (Broken Object Level Authorization):
  - Never trust only IDs coming from the client.
  - Always verify on the backend whether the resource belongs to the user.

---

## 7. Data and API Security

Principles:
- Security by design: think about security from the endpoint design stage.
- Principle of least privilege for users, services, and queries.
- HTTPS mandatory in production.

Sensitive data protection:
- In transit: always HTTPS (recent TLS).
- At rest:
  - Hash for passwords (non-reversible).
  - Encryption only for truly sensitive fields (tokens, secrets).

Input/output:
- Validate and sanitize:
  - Body, query, and headers (types, max sizes, formats).
  - Use ORMs or parameterized queries to prevent injection.
- Never return passwords, hashes, tokens, or keys in responses.

Extra protections:
- Rate limiting on sensitive endpoints (login, password recovery, etc.).
- Logs of suspicious attempts (many consecutive `401`/`403`).

---

## 8. Observability

- Log: method, URL, status, response time, request correlation ID.
- Do not log: passwords, tokens, sensitive data.
- Future: simple metrics (errors, requests/minute) and basic alerts.

---

## 9. Tests and Quality (Backend)

Test types:
- Unit:
  - Business logic (use cases/services/validations).
  - Authorization rules.
- Integration:
  - Critical flows (login, salesperson/customer creation).
- Basic security:
  - Confirm authentication/authorization requirements on sensitive endpoints.
  - Exercise some scenarios inspired by the OWASP API Security Top 10.

Tools:
- Depend on the stack (JUnit for JVM, Jest/Vitest for Node, etc.).
- Coverage with the stack's native tool (e.g.: JaCoCo for JVM).

---

## 10. Operations, Deploy, and Data

Environments:
- `dev` (local development),
- `staging` (optional, for manual testing),
- `prod` (production).

Deploy:
- Reproducible build (Docker or simple pipeline).
- Automated or semi-automated deploy, with version history.

Database:
- Versioned migrations (Flyway, Liquibase, or equivalent).
- Regular backups in production and periodic restore testing.

---

## 11. How Claude Should Use This File

- Before proposing endpoints, changing the data model or stack, review this `docs/backend.md`.
- If any suggestion violates principles here, point out the conflict and propose an aligned alternative.
- If an important decision arises that is not documented here:
  - Suggest creating/updating a subsection in this file in a concise way,
  - Or create a separate doc in `docs/` and reference it from here.
