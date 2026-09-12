package org.zghero.repository;

import org.zghero.model.Status;
import org.zghero.model.Tarefa;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TarefaRepository {

    private static final String ARQUIVO = "tarefas.txt";

    public void salvar(List<Tarefa> tarefas) {

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(ARQUIVO))) {

            for (Tarefa tarefa : tarefas) {

                writer.write(
                        tarefa.getId() + ";" +
                                tarefa.getNome() + ";" +
                                tarefa.getDescricao() + ";" +
                                tarefa.getDataTermino() + ";" +
                                tarefa.getPrioridade() + ";" +
                                tarefa.getCategoria() + ";" +
                                tarefa.getStatus()
                );

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar tarefas: " + e.getMessage());
        }
    }

    public List<Tarefa> carregar() {

        List<Tarefa> tarefas = new ArrayList<>();

        File arquivo = new File(ARQUIVO);

        if (!arquivo.exists()) {
            return tarefas;
        }

        try (BufferedReader reader = new BufferedReader(
                new FileReader(ARQUIVO))) {

            String linha;

            while ((linha = reader.readLine()) != null) {

                String[] dados = linha.split(";");

                Tarefa tarefa = new Tarefa(
                        Integer.parseInt(dados[0]),
                        dados[1],
                        dados[2],
                        LocalDate.parse(dados[3]),
                        Integer.parseInt(dados[4]),
                        dados[5],
                        Status.valueOf(dados[6])
                );

                tarefas.add(tarefa);
            }

        } catch (IOException | RuntimeException e) {
            System.out.println("Erro ao carregar tarefas: " + e.getMessage());
        }

        return tarefas;
    }
}