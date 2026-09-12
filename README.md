ZG-Hero TODO List

Aplicação de gerenciamento de tarefas desenvolvida em Java, como parte do desafio (K1-T3): Java — ZG-Hero Project.

O sistema funciona diretamente pelo terminal e permite cadastrar, visualizar, atualizar, excluir e filtrar tarefas de acordo com diferentes critérios.

Desenvolvedora

Liz Soares

Objetivo do projeto

Desenvolver uma aplicação backend simples para gerenciamento de tarefas, utilizando os conceitos fundamentais da linguagem Java, programação orientada a objetos, organização em camadas e persistência de dados.


O sistema possui as seguintes funcionalidades:

Cadastrar tarefas

Listar todas as tarefas

Listar tarefas por categoria

Listar tarefas por prioridade

Listar tarefas por status

Atualizar tarefas

Remover tarefas

Contar tarefas por status

Ordenar tarefas por prioridade

Salvar tarefas em arquivo

Carregar tarefas automaticamente ao iniciar o sistema

Dados de uma tarefa

Cada tarefa possui:

-ID

-Nome

-Descrição

-Data de término

-Prioridade, de 1 a 5

-Categoria

-Status

Os status disponíveis são:

TODO   -> tarefa ainda não iniciada

DOING  -> tarefa em andamento

DONE  -> tarefa concluída

Na prioridade, quanto menor o número, maior a prioridade:

1 --> prioridade máxima

5 --> prioridade mínima

 Tecnologias utilizadas

-- Java 21

-- Gradle

-- Git

-- GitHub

-- Programação Orientada a Objetos (POO)

-- Persistência em arquivo .txt

Como executar

1. Clonar o repositório
   git clone (https://github.com/Liz-sa-code/ZG-Hero-TODO-List.git)

Depois entre na pasta:

cd ZG-Hero-TODO-List

2. Dar permissão ao Gradle Wrapper

chmod +x gradlew

3. Compilar o projeto

   ./gradlew build

4. Executar a aplicação

   ./gradlew run