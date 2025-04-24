package gerenciamento.academia.menus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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
            \n||Menu Personal Trainer ||
            _____________________________________________
            |1| Visualizar alunos
            |2| Registrar avaliação física
            |3| Visualizar lista de avaliações realizadas
            |4| Voltar ao Login 
            Selecione uma opção do menu acima: """);

            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
            case 1 -> listarAlunos(cpf);
            case 2 -> registrarAvaliacao(cpf);
            case 3 -> listarAvaliacoesRealizadas(cpf);
            case 4 -> {
            	Limpar.limparConsole(10);
            	SistemaAcademia.iniciar();
            }
            default -> System.out.println("Opção inválida! Insira um numero de 1 a 4!");
            }

        }while (opcao != 4);
	}

	private static void listarAvaliacoesRealizadas(String cpf) {
		int i = 1;
		System.out.println("\n|| Avaliações: ||");
		
		Personal personal = identificarPersonal(cpf);
		
		for(Avaliacao a : Cadastro.avaliacoes) {
			if(a.getPersonal().equals(personal)) {
				if(LocalDate.now().isAfter(a.getData())) {
					System.out.println(i + ". Concluída: " + a.toString());
					i++;
				}
				else {
					System.out.println(i + ". Pendente: " + a.toString());
					i++;
				}
			}
		}
		if(i == 1) {
			System.out.println("Você não possui avaliações!");
		}
	}
	
	//tem q ser referente aos alunos ligados a este personal em específico
	private static void registrarAvaliacao(String cpf) {
		int qntd = 0, choice;
		
		do {
			qntd = listarAlunos(cpf);
			if(qntd == 0) {
				return;
			}
			else {
				System.out.println("\nEscolha o número correspondente ao aluno que deseja registrar uma avaliação: ");
				choice = sc.nextInt();
			}
			
		} while(choice > qntd || choice <= 0);
		sc.nextLine();
		Aluno aluno = Cadastro.alunos.get(choice - 1);;
		
		Personal personal = identificarPersonal(cpf);
		
		//fazer proteções
		System.out.println("\nInforme a data da avaliação: ");
		String data = sc.nextLine();
		
		System.out.println("Informe a descrição da avaliação: ");
		String descricao = sc.nextLine();
		
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Cadastro.avaliacoes.add(new Avaliacao(aluno, LocalDate.parse(data, formater), personal, descricao));
		System.out.println("\nAvaliação cadastrada com sucesso!");
	}

	private static int listarAlunos(String cpf) {
		int i = 1;
		System.out.println("\n||Lista de alunos ||");
		for(Aluno a : Cadastro.alunos){
			if(a.PersonalVinculado(cpf)) {
				System.out.println(i + ". " + a.toString());
				i++;
				return i;
			}
			
		}
		if(i == 1) {
			System.out.println("Você ainda não possui alunos vinculados!");
		}
		return 0;
		
	}
	
	private static Personal identificarPersonal(String cpf) {
		for(Personal p : Cadastro.personals) {
			if(p.getCpf().equals(cpf)) {
				return p;
			}
		}
		return null;			
	}
}
