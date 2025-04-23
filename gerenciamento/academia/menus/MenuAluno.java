package gerenciamento.academia.menus;

import java.time.LocalDate;
import java.util.Scanner;

import gerenciamento.academia.modelos.Aluno;
import gerenciamento.academia.modelos.Avaliacao;
import gerenciamento.academia.modelos.Personal;
import gerenciamento.academia.principal.SistemaAcademia;

public class MenuAluno {
	static Scanner sc = new Scanner(System.in);

	public static void iniciarMenuAluno(String cpf) {
		int opcao;
		
		do {
			System.out.println("""
						\n ||Menu Aluno ||
						_____________________________________________
					    |1| Visualizar dados pessoais e plano contratado
					    |2| Contratar personal Trainer
					    |3| Visualizar avaliações físicas
					    |4| Voltar ao Login 
					    Selecione uma opção do menu acima: """);
			
			opcao = sc.nextInt();
			sc.next();

			switch (opcao) {
			case 1 -> visualizarDados(cpf);
			case 2 -> contratarPersonal(cpf); 
			case 3 -> visualizarAvaliacoes(cpf); 
			case 4 -> SistemaAcademia.iniciar();
			default -> System.out.println("Opção inválida.");
			}

		} while (opcao != 4);
	}

	private static void visualizarDados(String cpf) {
		Aluno aluno = identificarAluno(cpf);

		System.out.println("Nome: " + aluno.getNome());
		System.out.println("CPF: " + cpf);
		System.out.println("Plano: " + aluno.getPlano());
		System.out.println("Data de Matrícula: " + aluno.getDataMatricula());
		if(aluno.getPersonalContratado().equals(null)) {
			System.out.println("Parece que você ainda não contratou um personal" );
		}
		else {
			System.out.println("Personal Contratado: " + aluno.getPersonalContratado());
		} 
	}

	private static void contratarPersonal(String cpf) {
		int qntd = 0, choice; 

		do {
			qntd = listarPersonal();
			System.out.println("Informe o personal que deseja contratar: ");
			choice = sc.nextInt();

		}while(choice > qntd || choice <= 0);
		sc.next();

		Aluno aluno = identificarAluno(cpf);

		aluno.setPersonalContratado(Cadastro.personals.get(choice - 1));

	}

	private static void visualizarAvaliacoes(String cpf) {
		Aluno aluno = identificarAluno(cpf);

		int i = 1;
		System.out.println("Avaliações: ");
		for(Avaliacao a : Cadastro.avaliacoes) {
			if(a.getAluno().equals(aluno)) {
				if(a.getData().isAfter(LocalDate.now())) {
					System.out.println(i + ". À concluir: " + a.toString());
				}
				else {
					System.out.println(i + ". Concluida: " + a.toString());
				}
			}
			i++;
		}

	}

	private static int listarPersonal() {
		int i = 1;
		for(Personal p : Cadastro.personals) {
			System.out.println(i + ". " + p.toString());
		}
		return i;
	}

	private static Aluno identificarAluno(String cpf) {
		for(Aluno a : Cadastro.alunos){
			if(a.getCpf().equals(cpf)){
				return a;
			}
		}
		return null;
	}

}

