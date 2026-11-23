# AGENTS.md

## Visão geral

ZG-Hero TODO List é um projeto de gerenciamento de tarefas com duas partes:
uma aplicação de linha de comando em Java e uma interface web estática. As
partes funcionam separadamente; o frontend não se comunica com o backend Java.

## Stack tecnológica

- Java 21 para a aplicação CLI.
- Gradle para compilação e execução da aplicação Java.
- HTML, CSS e JavaScript sem framework para o frontend.
- Persistência independente em arquivo de texto no backend e em `localStorage`
  no frontend.

## Estrutura do projeto

- `src/main/java/org/zghero/`: aplicação Java.
  - `Main.java`: menu e interação com a pessoa usuária via terminal.
  - `model/`: modelos `Tarefa`, `Categoria` e enum `Status`.
  - `repository/`: leitura e gravação de tarefas em `tarefas.txt`.
  - `services/`: lógica de criação, listagem, edição, remoção, contagem e
    ordenação de tarefas.
- `src/Frontend/`: interface web independente.
  - `index.html`: estrutura da página e formulário/listagem.
  - `css/style.css`: estilos e regras de responsividade.
  - `js/script.js`: operações sobre tarefas, renderização, filtros, contadores
    e persistência no armazenamento local do navegador.
- `build.gradle`, `settings.gradle`, `gradlew` e `gradlew.bat`: configuração e
  execução do projeto Gradle.
- `tarefas.txt`: arquivo de persistência usado pelo backend, criado ou
  atualizado durante a execução.

## Funcionalidades existentes

### CLI Java

- Criar, listar, atualizar e remover tarefas.
- Filtrar por categoria, prioridade ou status.
- Contar tarefas por status e ordenar por prioridade.
- Persistir tarefas em `tarefas.txt` e carregá-las na inicialização.
- Cada tarefa tem ID, nome, descrição, data de término, prioridade, categoria
  e status (`TODO`, `DOING` ou `DONE`).

### Frontend

- Criar, listar, editar e excluir tarefas.
- Filtrar a lista por status e mostrar contagens total e por status.
- Persistir tarefas no `localStorage` do navegador.
- Os campos do frontend são título, descrição e status; seus dados são
  independentes dos dados da CLI.

## Compilar e executar

Na raiz do repositório, com Java 21 disponível:

- Compilar e executar as verificações de build: `./gradlew build`
- Executar a CLI: `./gradlew run`
- Abrir o frontend: abrir `src/Frontend/index.html` em um navegador.

No Windows, usar `gradlew.bat` no lugar de `./gradlew`.

## Testes

Na exploração do repositório não foram encontrados arquivos de teste nem uma
suíte de testes existente. A tarefa Gradle padrão para executar testes, caso
sejam adicionados, é `./gradlew test`.

## Convenções de código observadas

- Código e mensagens da interface estão majoritariamente em português.
- Java usa pacotes sob `org.zghero`, classes em `PascalCase`, métodos e campos
  em `camelCase`, e constantes em maiúsculas com `_`.
- Há uma diferença observada entre o diretório `services/` e o pacote declarado
  pelo serviço (`org.zghero.service`); não presumir que o nome do diretório
  corresponda ao pacote.
- Os status são valores de enum em maiúsculas: `TODO`, `DOING` e `DONE`.
- O frontend usa JavaScript sem framework, funções e variáveis em `camelCase`,
  IDs/classes HTML em `kebab-case` e CSS organizado por componentes da página.
- A lógica Java está separada entre entrada CLI, modelos, serviço e repositório.

## Restrições para futuras implementações

- Manter a futura busca por título no frontend JavaScript, sem ampliar seu
  escopo para integração com o backend ou mudanças na CLI.
- Preservar o comportamento existente de criação, edição, exclusão, filtro por
  status, contadores e persistência em `localStorage`.
- Não alterar o formato de persistência do backend nem a estrutura dos dados
  além do necessário para a feature solicitada.
- Evitar dependências ou frameworks novos; seguir os padrões já usados no
  frontend.
- Não modificar arquivos ou funcionalidades fora do escopo da solicitação.
- Criar testes somente se houver uma configuração de testes compatível e
  necessária para verificar o comportamento novo; não afirmar que existem
  testes antes de eles serem adicionados.
