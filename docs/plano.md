# Plano de implementação: Busca de tarefas por título

## Tarefas

1. **[x] Adicionar o campo de busca à listagem**
   - Arquivos: `src/Frontend/index.html`,
     `src/Frontend/css/style.css`.
   - Incluir um controle de busca identificado como busca por título, junto ao
     filtro de status, e ajustar o layout para manter a listagem utilizável em
     telas menores.
   - Validação realizada: abri o frontend no navegador, confirmei a associação
     do rótulo ao campo e verifiquei a disposição em desktop (1280 px) e móvel
     (390 px), sem overflow horizontal.

2. **[x] Aplicar a busca em conjunto com o filtro de status**
   - Arquivo: `src/Frontend/js/script.js`.
   - Atualizar a filtragem da listagem para combinar trecho do título e status.
     Ignorar espaços nas extremidades, diferenças entre maiúsculas/minúsculas e
     acentos. Atualizar os resultados enquanto o texto muda; campo vazio ou
     contendo somente espaços não aplica critério de busca.
   - Manter o filtro de status atual; não adicionar filtros de categoria ou
     prioridade. Não alterar tarefas, `localStorage` ou contadores.
   - Validação realizada: no navegador, `REUNIAO` encontrou títulos
     `Reunião` por trecho, sem diferença de caixa ou acento, e espaços externos
     foram ignorados. Entrada só de espaços exibiu todas as tarefas; os
     resultados atualizaram durante a digitação (contagens 5, 4 e 3), combinaram
     corretamente com TODO, DOING e DONE, e limpar a busca preservou o status
     selecionado. Os contadores permaneceram globais e `localStorage` não foi
     alterado pela busca.

3. **[x] Exibir mensagens conforme os critérios ativos**
   - Arquivo: `src/Frontend/js/script.js`.
   - Tratar como busca ativa somente um termo não vazio após `trim`; selecionar
     exatamente uma mensagem para cada estado mutuamente exclusivo: sem
     tarefas, busca e status ativos, somente busca ativa ou somente status
     ativo.
   - Validação realizada: no navegador, confirmei as quatro mensagens exatas
     para lista vazia, busca sem resultados, filtro sem resultados e busca
     combinada com filtro. A lista sem tarefas teve precedência mesmo com busca
     e status ativos; espaços isolados foram tratados como busca inativa; a
     mensagem ficou oculta quando houve resultados. A busca e o filtro
     anteriores continuaram funcionando, os contadores permaneceram globais
     (`3`, `0`, `1`, `2`) e `localStorage` permaneceu inalterado.

4. **[x] Fazer uma verificação de regressão do frontend**
   - Arquivos de aplicação alterados: nenhum; verificar o comportamento em
     `src/Frontend/index.html`, `src/Frontend/css/style.css` e
     `src/Frontend/js/script.js`.
   - Validação realizada: no navegador, criei três tarefas, editei uma e
     excluí outra; confirmei o filtro `DOING`, os contadores globais, a busca
     combinada com status e a persistência após recarregar a página. A busca
     não alterou `localStorage`; os dados temporários foram removidos e o
     armazenamento voltou ao estado inicial vazio.
   - Ocorrência: a primeira execução do Playwright foi interrompida pelo alerta
     `Tarefa criada com sucesso.`. Retomei a execução diferida; os diálogos do
     fluxo foram aceitos e as verificações terminaram com sucesso.
   - Não há suíte de testes automatizados identificada no projeto; a validação
     prevista é manual no navegador, sem adicionar dependências ou tooling.
