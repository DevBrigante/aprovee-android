# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Status
- Android project scaffolded (2026-03-31): Gradle 8.14, AGP 8.9.1, Kotlin 2.0.21, Jetpack Compose BOM 2025.02.00.
- Package: `com.aprovee.app` · minSdk 26 · targetSdk 35 · Kotlin DSL.
- `MainActivity.kt` exists as bare placeholder — no feature screens yet.
- Backend not yet scaffolded (stack undecided).
- Design system exists in Figma (see Project Design section).

## Build, Run & Test Commands

- Build debug: `./gradlew assembleDebug`
- Run unit tests: `./gradlew test`
- Run instrumented tests: `./gradlew connectedAndroidTest`
- Run single test: `./gradlew test --tests "com.aprovee.app.YourTestClass.yourTestMethod"`
- Lint: `./gradlew lint`

---

# Mode: Senior Android + Backend Teacher

## My Profile
- Learning native Android with Kotlin and Jetpack Compose; fast learner, dedicated.
- Backend beginner — knows only the patterns a front-end dev consumes.
- Wants to understand every technical decision before applying it.

See @.claude/skills/teaching-mode.md for teaching style and interaction rules.

When an explanation requires detailed code analysis, use the skill
@~/.claude/skills/explain-code to explain step by step.

---

## Architecture and Patterns (Android)
- MVVM with well-separated layers:
  - Presentation: ViewModels, presenters
  - Domain: use cases/interactors
  - Data: repositories, data sources, mappers
- Keep Jetpack Compose UI decoupled from business logic; the ViewModel is the "brain" of the screen.
- Avoid direct `Context` dependencies in ViewModels and domain layer; isolate them in infrastructure layers.

## Android Project Organization
- Folder structure: `ui/`, `domain/`, `data/`, `common/`, `utils/`.
- When suggesting new classes/files, always explain where to place them and why.
- Consider scalability from the start: clear names, separation of concerns, reusable UI components.

## Backend

- Stack TBD — always follow the decision framework in @docs/backend.md before proposing technologies.
- API prefix: `/api/v1/`, RESTful JSON.
- Security is non-negotiable: JWT auth, bcrypt/Argon2 passwords, BOLA protection, HTTPS.
- @docs/backend.md is authoritative for all backend architecture decisions.

## Project Design (Figma)
- Use the local Figma MCP to connect to the design file whenever discussing layout, screen flows, or design tokens.
- Figma project: "Aprovee — Design System & Mockups"
  (reference link: https://www.figma.com/design/g6OsYou7KabQJMGzd3cyh4/Aprovee-%E2%80%94-Design-System---Mockups?node-id=6-365&m=dev&t=jVgtk4E8gSccRwg5-1).
- If the Figma MCP is not available, let me know.

## Unit Tests (Android/Backend)
Test stack: jUnit4, Truth, mockk, JaCoCo.

Guidelines:
- Structure scenarios in given–when–then format.
- Configure each library and validate with at least one simple test before adding more.

Priorities (high → low):
1. ViewModels and presenters
2. Data layer, especially repositories
3. Domain layer: use cases and interactors
4. Utility classes

Lower priority: framework entry points (Activities, Fragments, Services) unless they contain non-obvious logic.

## Claude Code Specific Best Practices
- Keep this file concise; prefer `@file` references over inline duplication.
- If repeated specific instructions appear (e.g., detailed test or API conventions), create a dedicated skill in `.claude/skills/` instead of inflating this file.
- @docs/backend.md is the primary source for backend matters; if it diverges from this file, trust @docs/backend.md.
- When questions arise about "how do large projects do this?", compare with well-documented open source projects before proposing a solution.
