# HANDOVER — Aprovee Android

## Sessão: 2026-03-31

---

### O que foi feito hoje

#### 1. Reorganização do CLAUDE.md
- Extraímos as regras de ensino/persona para `.claude/skills/teaching-mode.md`
- Condensamos a seção de Backend (era redundante com `docs/backend.md`)
- Adicionamos seção "Project Status" para deixar o estado do projeto explícito
- Resultado: CLAUDE.md passou de ~184 linhas para ~80 linhas sem perder nada

#### 2. Design System mapeado na memória
- Dados do Figma transferidos do projeto Flutter para a memória deste projeto
- Cores, tipografia, tokens de layout, inventário de telas e ordem de milestones salvos em:
  `.claude/projects/.../memory/figma_design.md`
- Figma file key: `g6OsYou7KabQJMGzd3cyh4`
- Nota: MCP do Figma tem rate limit no plano Starter

#### 3. Scaffold do projeto Android
Projeto Android criado do zero com as seguintes decisões:

| Decisão | Escolha | Motivo |
|---|---|---|
| Build system | Kotlin DSL (`.kts`) | Type safety, autocomplete, padrão moderno |
| Gradle version | 8.14 | Estável, compatível com AGP 8.9.x |
| AGP | 8.9.1 | Versão mais recente estável |
| Kotlin | 2.0.21 | Versão mais recente estável |
| Compose BOM | 2025.02.00 | Garante compatibilidade entre libs Compose |
| minSdk | 26 (Android 8.0) | Cobre ~95% dos dispositivos ativos |
| targetSdk | 35 | Android 15 |
| Package | `com.aprovee.app` | Identificador único para o Google Play |

**Estrutura criada:**
```
Aprovee/
├── gradle/
│   ├── libs.versions.toml         ← Version Catalog (centraliza versões)
│   └── wrapper/                   ← gradle-wrapper.jar + .properties
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/aprovee/app/
│       │   └── MainActivity.kt    ← placeholder vazio
│       └── res/values/
│           ├── strings.xml
│           └── themes.xml
├── build.gradle.kts               ← raiz (plugins declarados, não aplicados)
├── settings.gradle.kts
├── gradle.properties
└── gradlew
```

**Gradle sync:** confirmado funcionando (`./gradlew tasks` executou com sucesso).

---

### Decisões importantes registradas

1. **`MainActivity.kt` é um placeholder** — contém apenas `Text("Aprovee 🚀")`. Será substituído quando implementarmos a navegação real.
2. **Tema** — `themes.xml` usa `Theme.Material.Light.NoTitleBar` como base XML obrigatória pelo `AndroidManifest`. O tema visual real (cores do Figma) será feito em Compose com `MaterialTheme`.
3. **Inter font** — não adicionada ainda. Deve ser incluída como custom font no Milestone 0.

---

### Problemas em aberto

- [ ] Projeto ainda não tem repositório Git
- [ ] `.idea/` já existia na pasta antes do scaffold — pode ter configs antigas (inofensivo, Android Studio vai regenerar)
- [ ] `local.properties` foi gerado pelo Gradle sync — contém path local do SDK, não deve ir para o repositório
- [ ] Figma MCP com rate limit no plano Starter — dados de design já salvos na memória, mas telas "Missing Screens" (nodes `25:2` e `27:2`) ainda não foram exploradas em detalhe

---

### Próximos passos (Milestone 0 — Design System)

- [ ] **1. Criar repositório Git** (ver seção abaixo)
- [ ] **2. Criar `.gitignore`** para Android — excluir `build/`, `local.properties`, `.idea/`
- [ ] **3. Configurar tema Compose** em `app/src/main/java/com/aprovee/app/ui/theme/`
  - [ ] `Color.kt` — tokens de cor do Figma (light + dark)
  - [ ] `Type.kt` — escala tipográfica com Inter
  - [ ] `Theme.kt` — `AproveeTheme` composable com `MaterialTheme`
- [ ] **4. Adicionar fonte Inter** via `res/font/` ou `downloadable fonts`
- [ ] **5. Configurar navegação** — Jetpack Navigation Compose + estrutura de rotas
- [ ] **6. Definir estrutura de pastas** dos features:
  ```
  com.aprovee.app/
  ├── ui/
  │   ├── theme/
  │   └── navigation/
  ├── domain/
  ├── data/
  └── common/
  ```
- [ ] **7. Primeiro teste de sanidade** — rodar o app no emulador e ver o placeholder na tela

---

### Como criar o repositório Git

Veja instruções detalhadas na seção de git abaixo (gerada pelo `/encerrar`).
