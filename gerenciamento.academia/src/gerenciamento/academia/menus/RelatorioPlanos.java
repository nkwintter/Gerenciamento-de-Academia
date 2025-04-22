package gerenciamento.academia.menus;

import java.util.Scanner;
import gerenciamento.academia.modelos.*;


public class RelatorioPlanos {
	static Scanner sc = new Scanner(System.in);

	public static void relatorioPlanos() {

		int opcao = 0;
		do {
			System.out.println("|| ESCOLHA O PERÍODO ||");
			System.out.println(" ------------------------------------");
			System.out.println("|1| Relatório por Mês");
			System.out.println("|2| Relatório por Ano");
			System.out.println("|3| Voltar");
			opcao =  sc.nextInt();
			sc.nextLine();

			switch (opcao){
			case 1 -> relatorioMesAvaliacoes();
			case 2 -> relatorioAnoAvaliacoes();
			case 3 -> System.out.println("Voltando ao menu anterior.");
			default -> System.out.println("Opção inválida. Digite uma opção válida.");
			}
		} while(opcao != 3);
		sc.nextLine();
	}

	private static void relatorioAnoAvaliacoes() {

		System.out.print("Digite o ano (ex.: 2021): ");
		String ano = sc.nextLine();
		int anoInt = Integer.parseInt(ano);

		System.out.println("|| RELATÓRIO REFERENTE AO ANO DE "+ ano +" ||");

		for (Plano plano : Plano.values()) {
			int totalAlunos = 0;

			for (Aluno aluno : Cadastro.alunos) {             
				if (aluno.getDataMatricula().getYear() == anoInt &&
						aluno.getPlano().equals(plano)) {
					totalAlunos++;}
			}

			if (totalAlunos > 0) {
				System.out.printf("%-25s %10s\n", "PLANOS", "TOTAL DE ALUNOS");
				System.out.println("---------------------------------------------------");
				System.out.printf("%-25s R$ %8.2f\n"+ plano + totalAlunos);}
			if (totalAlunos <= 0) {
				System.out.println("Não há resgistros de planos pra essa data.");
			}
		}
	}

	private static void relatorioMesAvaliacoes() {

		System.out.print("Digite o mês (ex.: 9): ");
		String mes = sc.nextLine();
		int mesInt = Integer.parseInt(mes);
		System.out.print("Digite o ano (ex.: 2021): ");
		String ano = sc.nextLine();
		int anoInt = Integer.parseInt(ano);

		System.out.println("|| RELATÓRIO DO MÊS "+mes+" REFERENTE AO ANO DE "+ano+" ||");        


		for (Plano plano : Plano.values()) {
			int totalAlunos = 0;

			for (Aluno aluno : Cadastro.alunos) {             
				if (aluno.getDataMatricula().getYear() == anoInt &&
						aluno.getDataMatricula().getMonthValue() == mesInt &&
						aluno.getPlano().equals(plano)) {
					totalAlunos++;}
			}

			if (totalAlunos > 0) {
				System.out.printf("%-25s %10s\n", "PLANOS", "TOTAL DE ALUNOS");
				System.out.println("---------------------------------------------------");
				System.out.printf("%-25s R$ %8.2f\n"+ plano + totalAlunos);}
			if (totalAlunos <= 0) {
				System.out.println("Não há resgistros de planos pra essa data.");}
			else {
				System.out.println("Opção inválida. Digite uma opção válida."); 
			}
		}  
	}
}