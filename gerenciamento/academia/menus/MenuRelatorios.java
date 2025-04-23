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
			System.out.println("______________________________________________________________________");
			System.out.println("|1| Relatório de Planos");
			System.out.println("|2| Relatório de Pessoas(alunos, funcionários e personal trainers)");
			System.out.println("|3| Relatório de Avaliações Físicas");
			System.out.println("|4| Gerar arquivo dos Relatórios");
			System.out.println("|5| Voltar");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1 -> RelatorioPlanos.relatorioPlanos();
			case 2 -> relatorioPessoas();
			case 3 -> relatorioAvaliacao();
			case 4 -> arquivosRelatorios();
			case 5 -> MenuFuncionario.iniciarMenuFuncionario();
			default -> System.out.println("Opção inválida. Digite uma opção válida.");}

		} while (opcao != 5);
		sc.next();
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
            
            //rever!
            bw.write("Valor total a receber no mês: R$" + MenuFuncionario.totalAhReceber());
			bw.newLine();
            
            
            System.out.println("Arquivo gerado com sucesso!");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	

}

