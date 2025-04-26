package gerenciamento.academia.menus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import gerenciamento.academia.extras.Limpar;
import gerenciamento.academia.modelos.Aluno;
import gerenciamento.academia.modelos.Avaliacao;
import gerenciamento.academia.modelos.Personal;
import gerenciamento.academia.principal.SistemaAcademia;

public class MenuPersonal {
    static Scanner sc = new Scanner(System.in);

    public static void iniciarMenuPersonal(String cpf) {
        int opcao;

        do {
            System.out.println("""
                \n|| Menu Personal Trainer ||
                _____________________________________________
                |1| Visualizar alunos
                |2| Registrar avaliação física
                |3| Visualizar lista de avaliações realizadas
                |4| Voltar ao Login 
                Selecione uma opção do menu acima: """);

            while (!sc.hasNextInt()) {
                System.out.println("Digite um número válido:");
                sc.next();
            }
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> listarAlunos(cpf);
                case 2 -> registrarAvaliacao(cpf);
                case 3 -> listarAvaliacoesRealizadas(cpf);
                case 4 -> {
                    Limpar.limparConsole(5);
                    SistemaAcademia.iniciar();
                }
                default -> System.out.println("Opção inválida! Insira um número de 1 a 4!");
            }

        } while (opcao != 4);
    }

    private static void listarAvaliacoesRealizadas(String cpf) {
        int i = 1;
        System.out.println("\n|| Avaliações: ||");

        Personal personal = identificarPersonal(cpf);

        for (Avaliacao a : Cadastro.avaliacoes) {
            if (a.getPersonal().equals(personal)) {
                String status = LocalDate.now().isAfter(a.getData()) ? "Concluída" : "Pendente";
                System.out.println(i + ". " + status + ": " + a.toString());
                i++;
            }
        }

        if (i == 1) {
            System.out.println("Você não possui avaliações!");
        }
    }

    private static void registrarAvaliacao(String cpf) {
        List<Aluno> alunosDoPersonal = Cadastro.alunos.stream()
        		.filter(a -> a.getPersonalContratado() != null && a.getPersonalContratado().getCpf().equals(cpf))
                .collect(Collectors.toList());

        if (alunosDoPersonal.isEmpty()) {
            System.out.println("Você ainda não possui alunos vinculados!");
            return;
        }

        System.out.println("\n|| Lista de Alunos ||");
        for (int i = 0; i < alunosDoPersonal.size(); i++) {
            System.out.println((i + 1) + ". " + alunosDoPersonal.get(i).getNome() + " | Plano: " + alunosDoPersonal.get(i).getPlano().getPlano().getNome());
        }

        int choice;
        do {
            System.out.println("\nEscolha o número correspondente ao aluno que deseja registrar uma avaliação:");
            while (!sc.hasNextInt()) {
                System.out.println("Digite um número válido:");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();
        } while (choice <= 0 || choice > alunosDoPersonal.size());

        Aluno aluno = alunosDoPersonal.get(choice - 1);
        Personal personal = identificarPersonal(cpf);

        System.out.println("\nInforme a data da avaliação (dd/MM/yyyy): ");
        String dataStr = sc.nextLine();

        LocalDate dataAvaliacao;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataAvaliacao = LocalDate.parse(dataStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida. Avaliação não registrada.");
            return;
        }

        System.out.println("Informe a descrição da avaliação: ");
        String descricao = sc.nextLine();

        Cadastro.avaliacoes.add(new Avaliacao(aluno, dataAvaliacao, personal, descricao));
        System.out.println("\nAvaliação cadastrada com sucesso!");
    }

    private static void listarAlunos(String cpf) {
        List<Aluno> alunosDoPersonal = Cadastro.alunos.stream()
                .filter(a -> a.getPersonalContratado() != null && a.getPersonalContratado().getCpf().equals(cpf))
                .collect(Collectors.toList());

        System.out.println("\n|| Lista de Alunos ||");
        if (alunosDoPersonal.isEmpty()) {
            System.out.println("Você ainda não possui alunos vinculados!");
            return;
        }

        for (int i = 0; i < alunosDoPersonal.size(); i++) {
            System.out.println((i + 1) + ". " + alunosDoPersonal.get(i).getNome() + " | Plano: " + alunosDoPersonal.get(i).getPlano().getPlano().getNome());
        }
    }

    private static Personal identificarPersonal(String cpf) {
        for (Personal p : Cadastro.personals) {
            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }
        return null;
    }
}