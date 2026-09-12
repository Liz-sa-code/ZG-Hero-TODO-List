package org.zghero.service;

import org.zghero.model.Status;
import org.zghero.model.Tarefa;
import org.zghero.repository.TarefaRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TarefaService {

    private final List<Tarefa> tarefas;
    private final TarefaRepository repository;

    private int proximoId;

    public TarefaService() {

        repository = new TarefaRepository();
        tarefas = repository.carregar();

        proximoId = tarefas.stream()
                .mapToInt(Tarefa::getId)
                .max()
                .orElse(0) + 1;

        ordenarPorPrioridade();
    }

    public void adicionarTarefa(Tarefa tarefa) {

        tarefas.add(tarefa);

        ordenarPorPrioridade();

        repository.salvar(tarefas);
    }

    public Tarefa criarTarefa(String nome, String descricao,
                              java.time.LocalDate dataTermino,
                              int prioridade, String categoria,
                              Status status) {

        Tarefa tarefa = new Tarefa(
                proximoId++,
                nome,
                descricao,
                dataTermino,
                prioridade,
                categoria,
                status
        );

        adicionarTarefa(tarefa);

        return tarefa;
    }

    public List<Tarefa> listarTodas() {
        return new ArrayList<>(tarefas);
    }

    public List<Tarefa> listarPorCategoria(String categoria) {

        return tarefas.stream()
                .filter(t -> t.getCategoria()
                        .equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    public List<Tarefa> listarPorPrioridade(int prioridade) {

        return tarefas.stream()
                .filter(t -> t.getPrioridade() == prioridade)
                .collect(Collectors.toList());
    }

    public List<Tarefa> listarPorStatus(Status status) {

        return tarefas.stream()
                .filter(t -> t.getStatus() == status)
                .collect(Collectors.toList());
    }

    public boolean removerTarefa(int id) {

        boolean removida = tarefas.removeIf(
                tarefa -> tarefa.getId() == id
        );

        if (removida) {
            repository.salvar(tarefas);
        }

        return removida;
    }

    public boolean atualizarTarefa(int id, String nome,
                                   String descricao,
                                   java.time.LocalDate dataTermino,
                                   int prioridade,
                                   String categoria,
                                   Status status) {

        for (Tarefa tarefa : tarefas) {

            if (tarefa.getId() == id) {

                tarefa.setNome(nome);
                tarefa.setDescricao(descricao);
                tarefa.setDataTermino(dataTermino);
                tarefa.setPrioridade(prioridade);
                tarefa.setCategoria(categoria);
                tarefa.setStatus(status);

                ordenarPorPrioridade();
                repository.salvar(tarefas);

                return true;
            }
        }

        return false;
    }

    public int contarPorStatus(Status status) {

        return (int) tarefas.stream()
                .filter(t -> t.getStatus() == status)
                .count();
    }

    private void ordenarPorPrioridade() {

        tarefas.sort(
                Comparator.comparingInt(Tarefa::getPrioridade)
        );
    }

    public Tarefa buscarPorId(int id) {

        return tarefas.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }
}