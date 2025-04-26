package gerenciamento.academia.relatorios;

import java.util.Scanner;

import gerenciamento.academia.extras.Limpar;
import gerenciamento.academia.menus.Cadastro;
import gerenciamento.academia.modelos.Aluno;
import gerenciamento.academia.modelos.Periodo;
import gerenciamento.academia.modelos.Plano;

public class RelatorioPlanos {
	static Scanner sc = new Scanner(System.in);

	public static void relatorioPlanos() {
		Limpar.limparConsole(8);

		int opcao = 0;

		do {
			System.out.println("\n|| RELATÓRIOS DE PLANOS ||");
			System.out.println(" ------------------------------------\n");

			System.out.println("\n|| PLANOS MENSAIS ||");
			System.out.println(" ------------------------------------");
			planosMensais();

			System.out.println("\n|| PLANOS TRIMESTRAIS ||");
			System.out.println(" ------------------------------------");
			planosTrimestrais();

			System.out.println("\n|| PLANOS ANUAIS ||");
			System.out.println(" ------------------------------------");
			planosAnuais();

			System.out.println("\n|| OPÇÕES ||");
			System.out.println(" ------------------------------------");
			System.out.println("|1| Repetir relatório");
			System.out.println("|2| Voltar ao menu anterior)");
			opcao = sc.nextInt();
			sc.nextLine();

		} while(opcao != 2);
	}

	private static void planosAnuais() {
		for (Plano plano : Cadastro.planos) {
			int quantidade = 0;
			for (Aluno aluno : Cadastro.alunos) {
				
				if(aluno.getPlano().getPlano().equals(plano)) {
					if (aluno.getPlano().getPeriodo() == Periodo.ANUAL) {
						quantidade++;
					}
				}
				
			}
			double valor = plano.precoPeriodo(Periodo.ANUAL);
			System.out.printf("Plano: %-20s | Valor: R$ %.2f | Alunos: %d\n", plano.getNome(), valor, quantidade);
			
		}
	}

	private static void planosTrimestrais() {
		for (Plano plano : Cadastro.planos) {
			int quantidade = 0;
			for (Aluno aluno : Cadastro.alunos) {
				
				if(aluno.getPlano().getPlano().equals(plano)) {
					if (aluno.getPlano().getPeriodo() == Periodo.TRIMESTRAL) {
						quantidade++;
					}
				}
				
			}
			double valor = plano.precoPeriodo(Periodo.TRIMESTRAL);
			System.out.printf("Plano: %-20s | Valor: R$ %.2f | Alunos: %d\n", plano.getNome(), valor, quantidade);
			
		}
	}

	private static void planosMensais() {
		for (Plano plano : Cadastro.planos) {
			int quantidade = 0;
			for (Aluno aluno : Cadastro.alunos) {
				
				if(aluno.getPlano().getPlano().equals(plano)) {
					if (aluno.getPlano().getPeriodo() == Periodo.MENSAL) {
						quantidade++;
					}
				}
				
			}
			double valor = plano.precoPeriodo(Periodo.MENSAL);
			System.out.printf("Plano: %-20s | Valor: R$ %.2f | Alunos: %d\n", plano.getNome(), valor, quantidade);
			
		}
	}
}