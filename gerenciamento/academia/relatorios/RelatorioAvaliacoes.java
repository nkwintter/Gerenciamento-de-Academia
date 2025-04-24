package gerenciamento.academia.relatorios;

import java.util.Scanner;
import gerenciamento.academia.extras.Limpar;
import gerenciamento.academia.menus.Cadastro;
import gerenciamento.academia.modelos.Avaliacao;
import gerenciamento.academia.modelos.Personal;

public class RelatorioAvaliacoes {
	static Scanner sc = new Scanner(System.in);

	public static void relatorioAvaliacoes() {
		Limpar.limparConsole(5);

		int opcao = 0;

		do { 
			System.out.println("\n|| ESCOLHA UM PERÍODO||");
			System.out.println("------------------------------------");

			System.out.print("MÊS: ");
			String mes = sc.nextLine();
			int meses = 0;
			
			try {
				meses = Integer.parseInt(mes);
			}
			catch(NumberFormatException e) {
				switch(mes.toLowerCase()) {
				case "janeiro" -> meses = 1;
				case "fevereiro" -> meses = 2;
				case "março" -> meses = 3;
				case "abril" -> meses = 4;
				case "maio" -> meses = 5;
				case "junho" -> meses = 6;
				case "julho" -> meses = 7;
				case "agosto" -> meses = 8;
				case "setembro" -> meses = 9;
				case "outubro" -> meses = 10;
				case "novembro" -> meses = 11;
				case "dezembro" -> meses = 12;
				default -> System.out.println("\nOpção inválida!\n");
				}
			}

			System.out.print("ANO: ");
			String ano = sc.nextLine();
			int anos = Integer.parseInt(ano);

			System.out.println("\n|| RELATÓRIO DO MÊS " + mes + " REFERENTE AO ANO DE " + ano + " ||");
			System.out.println(" ------------------------------------");

			System.out.println("\n|| RELATÓRIOS DE AVALIÇÕES ||");
			System.out.println(" ------------------------------------");

			System.out.println("\n|| LISTA DE AVALIAÇÕES ||");
			System.out.println(" ------------------------------------");
			relatorioAvaliacoesPeriodo(meses, anos);

			System.out.println("\n|| AVALIAÇÕES POR PERSONAL ||");
			System.out.println(" ------------------------------------");
			relatorioAvaliacoesPorPersonal(meses, anos);

			System.out.println("\n|| OPÇÕES ||");
			System.out.println(" ------------------------------------");
			System.out.println("|1| Escolha um novo período");
			System.out.println("|2| Voltar ao menu anterior)");
			opcao = sc.nextInt();
			sc.nextLine();

		} while (opcao  != 2);
		Limpar.limparConsole(3);
	}

	private static void relatorioAvaliacoesPorPersonal(int mes, int ano) {
		System.out.printf("%-25s | %-15s\n ", "PERSONAL", "TOTAL DE AVALIAÇÕES");

		

		for (Personal p: Cadastro.personals) {
			int totalAvaliacoes = 0;
			for(Avaliacao a : Cadastro.avaliacoes) {
				if(a.getPersonal().equals(p)) {
					if(a.getData().getMonthValue() == mes && a.getData().getYear() == ano) {
						totalAvaliacoes++;
					}
				}
			}
			System.out.printf("%-25s | %-15s\n ", p.getNome(), totalAvaliacoes);
		}
		
	}

	private static void relatorioAvaliacoesPeriodo(int mes, int ano) {
		System.out.printf("%-25s | %-15s | %-15s\n ", "ALUNO", "PERSONAL", "DATA");

		for(Avaliacao listaAvaliacoes : Cadastro.avaliacoes) {
			if(listaAvaliacoes.getData().getMonthValue() == mes && listaAvaliacoes.getData().getYear() == ano) {
				System.out.printf("%-25s | %-15s | %-15s\n", listaAvaliacoes.getAluno(), listaAvaliacoes.getPersonal(), listaAvaliacoes.getData());
			}
		}

	}
}