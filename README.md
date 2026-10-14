# Aprovee

App Android nativo para vendedores autônomos organizarem **clientes, pedidos e receita** num só lugar.

> 🚧 Em desenvolvimento ativo.

## Stack

- **Kotlin** + **Jetpack Compose** (Material 3)
- Arquitetura **MVVM**: `ui → domain ← data`, `StateFlow` + `collectAsStateWithLifecycle`
- **Navigation Compose** com rotas type-safe (`kotlinx.serialization`) e grafos aninhados
- **Credential Manager** para salvar e recuperar credenciais (OS-native first)
- **DataStore Preferences** para flags locais
- Testes: **JUnit 4**, **Truth**, **MockK**, **kotlinx-coroutines-test** + cobertura com JaCoCo

**Toolchain:** Gradle 9 · AGP 9 (Kotlin embutido) · Kotlin 2.3 · minSdk 26 · target/compileSdk 36.

## Estrutura

```
app/src/main/java/com/aprovee/app/
├── ui/
│   ├── theme/        cores, tipografia e formas do design system
│   ├── components/   componentes reutilizáveis (botões, campos, ícones...)
│   ├── navigation/   rotas e NavHost
│   └── screens/      telas (padrão Screen stateful + Content stateless)
├── domain/           modelos e contratos de repositório
├── data/             implementações dos repositórios
└── common/           utilitários
```

## Como rodar

Pré-requisitos: Android Studio recente e JDK 17+.

```bash
git clone https://github.com/DevBrigante/aprovee-android.git
cd aprovee-android
./gradlew assembleDebug   # build
./gradlew installDebug    # instala no emulador/aparelho conectado
```

## Testes

```bash
./gradlew test                                # testes unitários
./gradlew createDebugUnitTestCoverageReport   # relatório de cobertura
./gradlew lint                                # lint
```

## Sobre o projeto

O Aprovee também é um projeto de aprendizado: cada decisão técnica é discutida e registrada antes de virar código, e o desenvolvimento conta com o **[Claude](https://claude.ai)** como mentor técnico.
