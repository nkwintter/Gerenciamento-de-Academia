package gerenciamento.academia.menus;


import java.util.Scanner;
import gerenciamento.academia.extras.Limpar;
import gerenciamento.academia.relatorios.*;

public class MenuRelatorios {
	
	static Scanner sc = new Scanner(System.in);

	public static void iniciarMenuRelatorios() {
		
		int opcao = 0;

		do {
			System.out.println("\n|| MENU DE RELATÓRIOS ||");
			System.out.println("______________________________________________________________________");
			System.out.println("|1| Relatório de Planos");
			System.out.println("|2| Relatório de Pessoas(alunos, funcionários e personal trainers)");
			System.out.println("|3| Relatório de Avaliações Físicas");
			System.out.println("|4| Gerar arquivo dos Relatórios");
			System.out.println("|5| Voltar");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1 -> RelatorioPlanos.relatorioPlanos();
			case 2 ->RelatorioPessoas.relatorioPessoas();
			case 3 -> RelatorioAvaliacoes.relatorioAvaliacoes();
			case 4 -> RelatorioArquivos.arquivosRelatorios();
			case 5 -> MenuFuncionario.iniciarMenuFuncionario();
			default -> System.out.println("\nOpção inválida. Digite uma opção válida.");}

		} while (opcao != 5);
		sc.next();
		Limpar.limparConsole(30);
	}
	
}	


