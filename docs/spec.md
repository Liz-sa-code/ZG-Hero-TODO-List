# Especificação: Busca de tarefas por título

## Objetivo

Permitir que a pessoa usuária encontre tarefas no frontend digitando parte do
título, em conjunto com o filtro de status já existente.

## Contexto atual

O frontend mantém as tarefas em memória e persiste os dados em `localStorage`.
A função `renderTasks()` monta a lista e aplica o filtro de status selecionado.
Os contadores atuais representam todas as tarefas, mesmo quando a lista está
filtrada.

## Escopo

- Adicionar busca por título à listagem do frontend.
- Aplicar a busca em conjunto com o filtro de status existente.
- Exibir uma mensagem adequada quando não houver tarefas ou resultados.
- Manter inalterados o modelo de tarefa, as operações CRUD, a persistência e o
  comportamento dos contadores.

Esta feature não inclui alterações à CLI Java, integração frontend/backend,
busca por descrição ou outros campos, nem mudanças no formato dos dados
persistidos. A busca deve combinar somente com o filtro de status já existente
no frontend. Não devem ser adicionados filtros de categoria ou prioridade à
interface.

## Requisitos funcionais

1. A listagem deve oferecer um campo de busca por título junto aos controles da
   listagem existente.
2. A busca deve encontrar títulos que contenham o termo digitado; não exige
   correspondência exata.
3. A busca deve atualizar os resultados enquanto a pessoa usuária digita, sem
   exigir envio de formulário ou confirmação.
4. Espaços no início e no fim do texto digitado devem ser ignorados. Se o campo
   contiver somente espaços, deve ser considerado vazio e nenhum critério de
   busca deve ser aplicado.
5. A comparação deve ignorar diferenças entre letras maiúsculas e minúsculas e
   entre caracteres acentuados e não acentuados. Por exemplo, buscar `reuniao`
   deve encontrar o título `Reunião`.
6. A busca deve considerar somente o título da tarefa.
7. A busca e o filtro de status devem ser aplicados em conjunto. Uma tarefa
   exibida deve corresponder ao termo de busca e ao status selecionado; quando
   o filtro estiver em `Todas`, qualquer status pode corresponder.
8. Nenhum filtro de categoria ou prioridade deve ser adicionado ao frontend
   como parte desta feature.
9. Limpar o campo de busca deve remover o critério de busca e atualizar a lista
   imediatamente, mantendo o filtro de status atualmente selecionado.
10. A busca só pode afetar quais tarefas são exibidas. Ela não deve criar,
   editar, excluir ou alterar tarefas, nem modificar os dados persistidos.
11. Os contadores devem continuar representando todas as tarefas, sem serem
   recalculados com base nos resultados da busca ou do filtro.

## Mensagens para lista sem resultados

Para esta regra, a busca está ativa somente quando o texto após remover
espaços das extremidades não está vazio. O filtro de status está ativo quando
seu valor não é `ALL`. Os estados de mensagem abaixo são mutuamente exclusivos:
depois de verificar se há tarefas cadastradas, deve ser escolhida exatamente
uma mensagem conforme a combinação dos critérios ativos.

1. **Lista vazia:** se não houver tarefas cadastradas, exibir
   `Nenhuma tarefa cadastrada.`
2. **Busca e filtro ativos:** havendo tarefas cadastradas e nenhum resultado,
   exibir `Nenhuma tarefa corresponde à busca e aos filtros selecionados.`
3. **Somente busca ativa:** havendo tarefas cadastradas, busca ativa, nenhum
   filtro de status ativo e nenhum resultado, exibir
   `Nenhuma tarefa encontrada para essa busca.`
4. **Somente filtro ativo:** havendo tarefas cadastradas, filtro de status
   ativo, busca inativa e nenhum resultado, exibir
   `Nenhuma tarefa corresponde aos filtros selecionados.`

Quando houver resultados, a mensagem de lista vazia não deve ser exibida.

## Critérios de aceite

- Ao digitar parte de um título, são mostradas as tarefas cujo título contém o
  trecho, sem necessidade de correspondência exata.
- A busca encontra correspondências independentemente de caixa e acentuação;
  por exemplo, `reuniao` encontra `Reunião`.
- Espaços antes e depois do termo são ignorados; um campo contendo somente
  espaços equivale a uma busca vazia.
- O resultado respeita simultaneamente o texto de busca e o status selecionado.
- A busca não adiciona filtros de categoria ou prioridade ao frontend.
- Ao limpar a busca, a listagem volta a refletir todas as tarefas permitidas
  pelo filtro de status atual.
- Uma lista sem tarefas exibe `Nenhuma tarefa cadastrada.`
- Uma busca sem correspondências exibe a mensagem de busca definida acima.
- Um filtro de status sem correspondências exibe a mensagem de filtros definida
  acima.
- Busca e filtro sem correspondências exibem a mensagem combinada definida
  acima.
- Pesquisar não altera tarefas nem os valores armazenados em `localStorage`.
- Os contadores permanecem baseados no conjunto completo de tarefas.
