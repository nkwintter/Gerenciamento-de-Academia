package gerenciamento.academia.menus;

import java.time.LocalDate;
import java.util.Scanner;

import gerenciamento.academia.extras.Limpar;
import gerenciamento.academia.modelos.Aluno;
import gerenciamento.academia.modelos.Periodo;
import gerenciamento.academia.modelos.Personal;
import gerenciamento.academia.modelos.Plano;
import gerenciamento.academia.modelos.PlanoAtivo;
import gerenciamento.academia.principal.SistemaAcademia;

public class MenuFuncionario {

	static Scanner sc = new Scanner(System.in);

	public static void iniciarMenuFuncionario(){
		
		int opcao; 

		do {

			System.out.println("""
			\n||Menu Funcionário ||
			_____________________________________________
			|1| Cadastrar novo plano
			|2| Cadastrar novo aluno
			|3| Cadastrar novo Personal Trainer
			|4| Emitir relatórios
			|5| Valor total a receber no mês
			|6| Voltar ao Login
			Selecione uma opção do menu acima: """);
			
			opcao = sc.nextInt();
			sc.nextLine();

			switch(opcao) {
			case 1 -> cadastrarPlano();
			case 2 -> cadastrarAluno();		
			case 3 -> cadastrarPersonal();
			case 4 -> MenuRelatorios.iniciarMenuRelatorios();
			case 5 -> System.out.printf("Valor total a receber no mês: R$%.2f\n", totalAhReceber());
			case 6 -> {
				Limpar.limparConsole(18);
				SistemaAcademia.iniciar();
			}
			default -> System.out.println("\nInválido!");
			}

		} while(opcao != 6);
	}
	
	//revisar e fazer de acordo com o período
	public static double totalAhReceber() {
		double lucros = 0;
		
		for(Aluno a : Cadastro.alunos) {
			lucros += a.getPlano().getPrecoFinal();
		}
		return lucros;
	}

	private static void cadastrarPlano() {
		System.out.println("\nInforme o nome do plano: ");
		String nome = sc.nextLine();

		//testar esse e o outro modo, o melhor fica!!!
		for(Plano p : Cadastro.planos) {
			if(p.getNome().equals(nome)) {
				System.out.println("\nJá existe um cadastro para esse plano!");
				return;
				
			}
		}
		System.out.println("Informe a descrição do plano: ");
		String descricao = sc.nextLine();
		
		System.out.println("Informe o valor mensal do plano: ");
		double valorM = sc.nextDouble();
		
		System.out.println("Informe o valor trimestral do plano: ");
		double valorT = sc.nextDouble();
		
		System.out.println("Informe o valor anual do plano: ");
		double valorA = sc.nextDouble();
		sc.nextLine();
		
		Cadastro.planos.add(new Plano(nome, descricao, valorM, valorT, valorA));
		
		System.out.println("\nPlano cadastrado com sucesso!");
	}

	private static void cadastrarAluno() {
		System.out.println("Digite o cpf do aluno: ");
		String cpfAluno = sc.nextLine();

		if(Cadastro.cpfs.contains(cpfAluno)) {
			System.out.println("Já existe um cadastro para esse CPF!");
		}
		else {
			Cadastro.cpfs.add(cpfAluno);

			System.out.println("Digite o nome do aluno: ");
			String nomeAluno = sc.nextLine();
			System.out.println("Digite uma senha para o aluno: ");
			String senha = sc.nextLine();
			
			int choice = 0, qntd;
			do {
				qntd = listarPlanos();
				System.out.println("\nDigite o plano do aluno: ");
				choice = sc.nextInt();
				
			}while(choice < 0 || choice > qntd);
			sc.nextLine();
			
			System.out.println("Digite o periodo do plano (Mensal | Trimestral | Anual): ");
			String periodo = sc.nextLine().toUpperCase();
			
			
			//rever!
			Cadastro.alunos.add(new Aluno(nomeAluno, cpfAluno, senha, LocalDate.now(), new PlanoAtivo(Cadastro.planos.get(choice -1 ) , Periodo.valueOf(periodo))));
			System.out.println("Aluno cadastrado com sucesso!");
		}
	}

	private static void cadastrarPersonal() {
		System.out.println("Informe o cpf do Personal Trainer: ");
		String cpfPersonal = sc.nextLine();

		if(Cadastro.cpfs.contains(cpfPersonal)) {
			System.out.println("Já existe um cadastro para esse CPF!");

		}else {
			Cadastro.cpfs.add(cpfPersonal);

			System.out.println("Informe o nome do Personal Trainer: ");
			String nomePersonal = sc.nextLine();
			System.out.println("Informe uma senha para Personal Trainer: ");
			String senha = sc.nextLine();
			System.out.println("Informe a especialidade do Personal Trainer: ");
			String especialidade = sc.nextLine();
			System.out.println("Informe o CREF do Personal Trainer: ");
			String cref = sc.nextLine();

			Cadastro.personals.add(new Personal(nomePersonal, cpfPersonal, senha, especialidade, cref));
			System.out.println("Personal cadastrado com sucesso!");
		}

	}
	
	public static int listarPlanos() {
		int i = 1;
		for(Plano p : Cadastro.planos) {
			System.out.println(i + ". "+ p.toString());
			i++;
		}
		return i;
	}

}

