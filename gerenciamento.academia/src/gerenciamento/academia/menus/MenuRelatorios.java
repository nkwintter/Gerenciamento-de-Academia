package gerenciamento.academia.menus;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import gerenciamento.academia.modelos.Aluno;
import gerenciamento.academia.modelos.Avaliacao;
import gerenciamento.academia.modelos.Funcionario;
import gerenciamento.academia.modelos.Personal;

public class MenuRelatorios {
	
	static Scanner sc = new Scanner(System.in);

	public static void iniciarMenuRelatorios() {
		int opcao = 0;

		do {
			System.out.println("|| MENU DE RELATÓRIOS ||");
			System.out.println(" ------------------------------------");
			System.out.println("|1| Relatório de Planos");
			System.out.println("|2| Relatório de Pessoas(alunos, funcionários e personal trainers)");
			System.out.println("|3| Relatório de Avaliações Físicas");
			System.out.println("|4| Gerar arquivo dos Relatórios");
			System.out.println("|5| Valor total a receber no mês");
			System.out.println("|6| Voltar");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1 -> RelatorioPlanos.relatorioPlanos();
			case 2 -> relatorioPessoas();
			case 3 -> relatorioAvaliacao();
			case 4 -> arquivosRelatorios();
			case 5 -> totalAhReceber();
			case 6 -> System.out.println("Voltando ao menu anterior.");
			default -> System.out.println("Opção inválida. Digite uma opção válida.");}

		} while (opcao != 6);
		sc.next();
	}

	private static String totalAhReceber() {
		double total = 0;
		
		for(Aluno a : Cadastro.alunos) {
			total += a.getPlano().getPreco();
		}
		
		return String.format("Valor total a receber no mês: R$%.2f", total);
	}

	private static void relatorioPessoas() {
		System.out.println(Cadastro.alunos);
		System.out.println(Cadastro.funcionarios);
		System.out.println(Cadastro.personals);
	}
	
	private static void relatorioAvaliacao() {
		System.out.println(Cadastro.avaliacoes);
	}


	public static void arquivosRelatorios() {
		String path = "RelatoriosAcademia.csv";
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			bw.write("Funcionários:");
			bw.newLine();
            for (Funcionario f : Cadastro.funcionarios) {
                bw.write(f.gerar());
                bw.newLine();
            }
            
			bw.write("Alunos:");
			bw.newLine();
            for (Aluno a : Cadastro.alunos) {
                bw.write(a.gerar());
                bw.newLine();
            }
            
            bw.write("Personal Trainers:");
			bw.newLine();
            for (Personal p : Cadastro.personals) {
                bw.write(p.gerar());
                bw.newLine();
            }
            
            bw.write("Avaliações:");
			bw.newLine();
            for (Avaliacao a : Cadastro.avaliacoes) {
                bw.write(a.gerar());
                bw.newLine();
            }
            
            bw.write("Total a receber:");
			bw.newLine();
            
            
            System.out.println("Arquivo gerado com sucesso!");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	

}

