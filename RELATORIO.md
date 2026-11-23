# Relatório da atividade SDD — ZG-Hero TODO List

## Objetivo e feature

Implementar busca de tarefas por título no frontend do ZG-Hero TODO List. A
busca encontra trechos do título sem diferenciar caixa ou acentos, ignora
espaços externos, atualiza enquanto a pessoa usuária digita e combina com o
filtro de status existente. A busca não altera tarefas nem a persistência.

## Processo Spec-Driven Development

- A branch `feature/busca-titulo` foi criada para a atividade. Nenhum número de
  issue foi fornecido ou associado neste fluxo.
- `AGENTS.md` foi consultado para as convenções e limites do repositório.
- A especificação foi criada e revisada com a pessoa usuária antes da
  implementação; as decisões em aberto foram esclarecidas e aprovadas.
- O plano foi dividido em quatro tarefas, com arquivos e validações definidos.
- As Tarefas 1, 2 e 3 foram implementadas, validadas, revisadas e commitadas
  separadamente. A Tarefa 4 consistiu em regressão manual, sem alterações de
  arquivos da aplicação.
- Esta Skill genérica e este relatório foram acrescentados como artefatos da
  atividade; não houve merge ou push.

## Commits da feature

1. Tarefa 1 — `7a6f3a8805d367576b901105aa0919d4d7cf33a5`
   (`feat(frontend): add title search input`)
2. Tarefa 2 — `ae62a97571ce367a82c2b8f14f7b24a116ffb2eb`
   (`feat(frontend): implement title search`)
3. Tarefa 3 — `a532bf8f23d61340abc1c27fbf3c2df19265ad2b`
   (`feat(frontend): add empty search states`)

Não houve commit da Tarefa 4, pois ela foi uma verificação sem alterações de
código e nenhum commit foi solicitado para essa etapa.

## Testes e validações

Não foi encontrada uma suíte automatizada para o frontend. As validações foram
manuais no navegador:

- Tarefa 1: associação entre rótulo e campo de busca e disposição dos controles
  em desktop (1280 px) e móvel (390 px), sem overflow horizontal.
- Tarefa 2: correspondência parcial, `REUNIAO` para `Reunião`, espaços
  externos e campo contendo apenas espaços; atualização durante a digitação;
  combinação com `TODO`, `DOING` e `DONE`; limpeza preservando o status; busca
  sem alteração do `localStorage` e contadores globais.
- Tarefa 3: conferência das quatro mensagens para lista vazia, busca sem
  resultados, filtro sem resultados e busca combinada com filtro; precedência
  da lista vazia, busca de espaços como inativa e ocultação da mensagem quando
  há resultados.
- Tarefa 4: criação de três tarefas, edição de uma, exclusão de outra, filtro
  por status, busca combinada com status, contadores, persistência após reload
  e igualdade do `localStorage` antes/depois da busca. Os dados temporários
  foram removidos ao final e o armazenamento voltou ao estado inicial vazio.
- `node --check src/Frontend/js/script.js` passou; `git diff --check` passou nas
  etapas em que houve alterações.

## Erros e recuperações

- Durante a regressão da Tarefa 4, o Playwright foi interrompido pelo alerta
  `Tarefa criada com sucesso.`. A execução foi retomada pela continuação
  diferida; os cinco diálogos do fluxo foram aceitos e as verificações
  terminaram com sucesso.
- Uma primeira asserção de teste da Tarefa 3 esperava ausência de resultados
  para `DOING`, embora a massa de teste contivesse uma tarefa nesse status. O
  cenário foi corrigido para usar `TODO`, sem alteração no código, e a matriz
  completa de mensagens passou.

## Tokens

Não houve medição real de tokens por atividade. Estimativa grosseira: cerca de
25.000 tokens de interação do agente, sem precisão por etapa.

## Aprendizados e resultado

- A aprovação da especificação antes do código ajudou a resolver as regras de
  trim, acentuação e combinação com filtros sem ampliar o escopo.
- Validar com dados controlados permitiu conferir mensagens, contadores,
  persistência e CRUD; cenários de teste devem corresponder exatamente aos
  dados preparados.
- A busca por título está implementada no frontend e as quatro etapas foram
  revisadas e aprovadas. A regressão manual foi concluída e está registrada em
  `docs/plano.md`.
