package gerenciamento.academia.menus;

import java.time.LocalDate;
import java.util.Scanner;

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
            		\n ||Menu Personal Trainer ||
                    _____________________________________________
                    |1| Visualizar alunos
                    |2| Registrar avaliação física
                    |3| Visualizar lista de avaliações realizadas
                    |4| Voltar ao Login 
                    Selecione uma opção do menu acima: """);

            opcao = sc.nextInt();
            sc.next();
            switch (opcao) {
            case 1 -> listarAlunos(cpf);
            case 2 -> registrarAvaliacao(cpf);
            case 3 -> listarAvaliacoesRealizadas();
            case 4 -> SistemaAcademia.iniciar();
            default -> System.out.println("Opção inválida! Insira um numero de 1 a 4!");
            }

        }while (opcao != 4);
     
	}

	private static void listarAvaliacoesRealizadas() {
		int i = 1;
		System.out.println("|| Avaliações concluídas ||");
		for(Avaliacao a : Cadastro.avaliacoes) {
			if(LocalDate.now().isAfter(a.getData())) {
				System.out.println(i + ". " + a.toString());
				i++;
			}
		}
	}
	
	//tem q ser referente aos alunos ligados a este personal em específico
	private static void registrarAvaliacao(String cpf) {
		int qntd = 0, choice;
		
		do {
			qntd = listarAlunos(cpf);
			System.out.println("Escolha o número correspondente ao aluno que deseja registrar uma avaliação: ");
			choice = sc.nextInt();
			
		} while(choice > qntd || choice <= 0);
		sc.next();
		Aluno aluno = Cadastro.alunos.get(choice - 1);;
		
		Personal personal = identificarPersonal(cpf);
		
		//fazer proteções
		System.out.println("Informe a data da avaliação: ");
		String data = sc.nextLine();
		
		System.out.println("Informe a descrição da avaliação: ");
		String descricao = sc.nextLine();
		
		Cadastro.avaliacoes.add(new Avaliacao(aluno, LocalDate.parse(data), personal, descricao));
	}

	private static int listarAlunos(String cpf) {
		int i = 1;
		System.out.println("||Lista de alunos ||");
		for(Aluno a : Cadastro.alunos){
			if(a.PersonalVinculado(cpf)) {
				System.out.println(i + ". " + a.toString());
				i++;
				return i;
			}
			
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
