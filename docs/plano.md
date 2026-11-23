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

3. **Exibir mensagens conforme os critérios ativos**
   - Arquivo: `src/Frontend/js/script.js`.
   - Tratar como busca ativa somente um termo não vazio após `trim`; selecionar
     exatamente uma mensagem para cada estado mutuamente exclusivo: sem
     tarefas, busca e status ativos, somente busca ativa ou somente status
     ativo.
   - Validação prevista: verificar cada uma das quatro mensagens, confirmar
     que espaços isolados são busca inativa, que a lista vazia prevalece quando
     não há tarefas e que nenhuma mensagem aparece quando há resultados.

4. **Fazer uma verificação de regressão do frontend**
   - Arquivos alterados: nenhum; verificar o comportamento em
     `src/Frontend/index.html`, `src/Frontend/css/style.css` e
     `src/Frontend/js/script.js`.
   - Validação prevista: verificar criação, edição, exclusão, filtro por status,
     contadores e persistência das tarefas em `localStorage`; confirmar que a
     busca não altera os dados armazenados.
   - Não há suíte de testes automatizados identificada no projeto; a validação
     prevista é manual no navegador, sem adicionar dependências ou tooling.
