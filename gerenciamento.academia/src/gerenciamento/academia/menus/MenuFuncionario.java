package gerenciamento.academia.menus;

import java.time.LocalDate;
import java.util.Scanner;

import gerenciamento.academia.modelos.Aluno;
import gerenciamento.academia.modelos.Personal;
import gerenciamento.academia.modelos.Plano;
import gerenciamento.academia.modelos.PlanoPersonalizado;

public class MenuFuncionario {

	static Scanner sc = new Scanner(System.in);

	public static void iniciarMenuFuncionario(){
		int opcao;

		String menu = """
				====== MENU DO FUNCIONÁRIO =====
				1. Cadastrar novo plano
				2. Cadastrar novo aluno
				3. Cadastrar novo Personal Trainer
				4. Emitir Relatórios
				5. Valor total a receber no mês

				Escolha uma opção: 
				""";

		do {

			System.out.println(menu);
			opcao = sc.nextInt();
			sc.nextLine();

			switch(opcao) {
			case 1 -> cadastrarPlano();
			case 2 -> cadastrarAluno();		
			case 3 -> cadastrarPersonal();
			case 4 -> MenuRelatorios.iniciarMenuRelatorios();
			case 5 -> System.out.println("Valor total a receber no mês: ");
			default -> System.out.println("Inválido!");
			}

		} while(opcao != 5);

	}

	//rever Isso!
	private static void cadastrarPlano() {
		System.out.println("Informe o nome do plano: ");
		String nome = sc.nextLine().toUpperCase();

		for(PlanoPersonalizado pp : Cadastro.planosExtras) {
			if(pp.getNome().equals(nome)) {
				System.out.println("Já existe um cadastro para esse plano!");
				return;
			}
		}

		System.out.println("Informe a descrição do plano: ");
		String descricao = sc.nextLine();

		System.out.println("Informe o valor do plano: ");
		double valor = sc.nextDouble();
		System.out.println();
		System.out.println("Plano cadastrado com sucesso!");


		Cadastro.planosExtras.add(new PlanoPersonalizado(nome, descricao, valor));

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
			System.out.println("Digite o plano do aluno: ");
			String plano = sc.nextLine();

			Cadastro.alunos.add(new Aluno(nomeAluno, cpfAluno, senha, LocalDate.now(), Plano.valueOf(plano)));
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

}

