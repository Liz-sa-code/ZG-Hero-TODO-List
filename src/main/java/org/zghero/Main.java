package org.zghero;

import org.zghero.model.Status;
import org.zghero.model.Tarefa;
import org.zghero.service.TarefaService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final TarefaService service = new TarefaService();

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("       ZG-HERO TODO LIST");
        System.out.println("       Desenvolvido por Liz Soares");
        System.out.println("=================================");

        int opcao;

        do {

            exibirMenu();

            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {

                case 1 -> cadastrarTarefa();

                case 2 -> listarTodas();

                case 3 -> listarPorCategoria();

                case 4 -> listarPorPrioridade();

                case 5 -> listarPorStatus();

                case 6 -> atualizarTarefa();

                case 7 -> removerTarefa();

                case 8 -> mostrarQuantidadeStatus();

                case 0 -> System.out.println("Encerrando aplicação...");

                default -> System.out.println("Opção inválida!");

            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {

        System.out.println("\n=================================");
        System.out.println("1 - Cadastrar tarefa");
        System.out.println("2 - Listar todas as tarefas");
        System.out.println("3 - Listar por categoria");
        System.out.println("4 - Listar por prioridade");
        System.out.println("5 - Listar por status");
        System.out.println("6 - Atualizar tarefa");
        System.out.println("7 - Remover tarefa");
        System.out.println("8 - Quantidade por status");
        System.out.println("0 - Sair");
        System.out.println("=================================");
    }

    private static void cadastrarTarefa() {

        System.out.println("\n--- CADASTRO DE TAREFA ---");

        String nome = lerTexto("Nome: ");

        String descricao = lerTexto("Descrição: ");

        LocalDate data = lerData("Data de término (dd/MM/yyyy): ");

        int prioridade;

        do {
            prioridade = lerInteiro("Prioridade (1-5): ");

            if (prioridade < 1 || prioridade > 5) {
                System.out.println("A prioridade deve estar entre 1 e 5.");
            }

        } while (prioridade < 1 || prioridade > 5);

        String categoria = lerTexto("Categoria: ");

        Status status = lerStatus();

        service.criarTarefa(
                nome,
                descricao,
                data,
                prioridade,
                categoria,
                status
        );

        System.out.println("Tarefa cadastrada com sucesso!");
    }

    private static void listarTodas() {

        System.out.println("\n--- TODAS AS TAREFAS ---");

        List<Tarefa> tarefas = service.listarTodas();

        exibirTarefas(tarefas);
    }

    private static void listarPorCategoria() {

        String categoria = lerTexto("Digite a categoria: ");

        List<Tarefa> tarefas =
                service.listarPorCategoria(categoria);

        exibirTarefas(tarefas);
    }

    private static void listarPorPrioridade() {

        int prioridade = lerInteiro("Digite a prioridade (1-5): ");

        List<Tarefa> tarefas =
                service.listarPorPrioridade(prioridade);

        exibirTarefas(tarefas);
    }

    private static void listarPorStatus() {

        Status status = lerStatus();

        List<Tarefa> tarefas =
                service.listarPorStatus(status);

        exibirTarefas(tarefas);
    }

    private static void atualizarTarefa() {

        int id = lerInteiro("Digite o ID da tarefa: ");

        Tarefa tarefa = service.buscarPorId(id);

        if (tarefa == null) {
            System.out.println("Tarefa não encontrada.");
            return;
        }

        System.out.println("Deixe vazio para manter o valor atual.");

        String nome = lerTexto("Novo nome: ");
        String descricao = lerTexto("Nova descrição: ");

        LocalDate data = lerData("Nova data (dd/MM/yyyy): ");

        int prioridade = lerInteiro("Nova prioridade (1-5): ");

        String categoria = lerTexto("Nova categoria: ");

        Status status = lerStatus();

        boolean atualizada = service.atualizarTarefa(
                id,
                nome,
                descricao,
                data,
                prioridade,
                categoria,
                status
        );

        if (atualizada) {
            System.out.println("Tarefa atualizada com sucesso!");
        } else {
            System.out.println("Não foi possível atualizar.");
        }
    }

    private static void removerTarefa() {

        int id = lerInteiro("Digite o ID da tarefa: ");

        boolean removida = service.removerTarefa(id);

        if (removida) {
            System.out.println("Tarefa removida com sucesso!");
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }

    private static void mostrarQuantidadeStatus() {

        System.out.println("\n--- QUANTIDADE POR STATUS ---");

        System.out.println("TODO: " +
                service.contarPorStatus(Status.TODO));

        System.out.println("DOING: " +
                service.contarPorStatus(Status.DOING));

        System.out.println("DONE: " +
                service.contarPorStatus(Status.DONE));
    }

    private static void exibirTarefas(List<Tarefa> tarefas) {

        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
            return;
        }

        for (Tarefa tarefa : tarefas) {
            System.out.println(tarefa);
        }
    }

    private static String lerTexto(String mensagem) {

        System.out.print(mensagem);

        return scanner.nextLine();
    }

    private static int lerInteiro(String mensagem) {

        while (true) {

            try {

                System.out.print(mensagem);

                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Digite um número válido.");
            }
        }
    }

    private static LocalDate lerData(String mensagem) {

        while (true) {

            try {

                System.out.print(mensagem);

                return LocalDate.parse(
                        scanner.nextLine(),
                        FORMATO_DATA
                );

            } catch (Exception e) {

                System.out.println(
                        "Data inválida. Use o formato dd/MM/yyyy."
                );
            }
        }
    }

    private static Status lerStatus() {

        while (true) {

            System.out.print("Status (TODO/DOING/DONE): ");

            try {

                return Status.valueOf(
                        scanner.nextLine().toUpperCase()
                );

            } catch (IllegalArgumentException e) {

                System.out.println(
                        "Status inválido. Use TODO, DOING ou DONE."
                );
            }
        }
    }
}