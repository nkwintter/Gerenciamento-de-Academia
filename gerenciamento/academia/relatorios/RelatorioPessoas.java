package gerenciamento.academia.relatorios;

import java.util.Scanner;

import gerenciamento.academia.extras.Limpar;
import gerenciamento.academia.menus.Cadastro;
import gerenciamento.academia.modelos.Aluno;
import gerenciamento.academia.modelos.Cargo;
import gerenciamento.academia.modelos.Funcionario;
import gerenciamento.academia.modelos.Personal;


public class RelatorioPessoas {
	static Scanner sc = new Scanner(System.in);

	public static void relatorioPessoas() {
		Limpar.limparConsole(5);

		int opcao = 0;

		do {
			System.out.println("\n|| RELATÓRIOS DE INTEGRANTES ||");
			System.out.println(" ------------------------------------");

			System.out.println("\n|| TOTAL DE INTEGRANTES ||");
			System.out.println(" ------------------------------------");
			totalIntegrantes();

			System.out.println("\n|| LISTA DE FUNCIONÁRIOS ||");
			System.out.println("------------------------------------");
			listaFuncionarios();

			System.out.println("\n|| FUNCIONÁRIOS POR CARGO ||");
			System.out.println(" ------------------------------------");
			listaFuncionariosPorCargo();

			System.out.println("\n|| LISTA DE PERSONALS TRAINERS ||");
			System.out.println(" ------------------------------------");
			listaPersonal();

			System.out.println("\n|| LISTA DE ALUNOS ||");
			System.out.println(" ------------------------------------");
			listaAlunos();

			System.out.println("\n|| OPÇÕES ||");
			System.out.println(" ------------------------------------");
			System.out.println("|1| Repetir relatório");
			System.out.println("|2| Voltar ao menu anterior)");
			opcao = sc.nextInt();
			sc.nextLine();
		} while (opcao != 2);
	}

	private static void listaFuncionariosPorCargo() {

		for(Cargo listaCargos: Cargo.values()) {
			int quantidade = 0;
			for (Funcionario listaFuncionario : Cadastro.funcionarios) {
				if(listaFuncionario.getCargo() == listaCargos) {
					quantidade++;
				}
			}
			System.out.printf("Cargo: %-20s | Funcionários: %d\n", listaCargos, quantidade);
		}
	}

	private static void totalIntegrantes () {
		int totalFuncionarios = Cadastro.funcionarios.size();
		int totalPersonals = Cadastro.personals.size();
		int totalAlunos = Cadastro.alunos.size();

		System.out.printf("%-25s %-15s %-15s\n", "FUNCIONÁRIOS", "PERSONALS TRAINERS", "ALUNOS");
		System.out.printf("%-25s %-15s %-15s\n", totalFuncionarios, totalPersonals, totalAlunos);
	}

	private static void listaFuncionarios () {
		System.out.printf("%-25s | %-15s | %-15s\n", "NOME", "CPF", "CARGO");
		for(Funcionario listaFuncionarios: Cadastro.funcionarios) {
			System.out.printf("%-25s | %-15s | %-15s\n", listaFuncionarios.getNome(), listaFuncionarios.getCpf(), listaFuncionarios.getCargo().toString());
		}
	}

	private static void listaPersonal () {
		System.out.printf("%-10s | %-25s | %-15s | %-20s\n", "CREF", "NOME", "CPF", "ESPECIALIDADE");
		for(Personal listaPersonal : Cadastro.personals) {
			System.out.printf("%-10s | %-25s | %-15s | %-20s\n", listaPersonal.getCref(), listaPersonal.getNome(), listaPersonal.getCpf(), listaPersonal.getEspecialidade());
		}
	}

	private static void listaAlunos () {
		System.out.printf("%-30s | %-15s | %-25s | %-30s | %-20s\n", "NOME", "CPF", "PLANO", "PERSONAL TRAINER", "DATA DA MATRÍCULA");
		for(Aluno listaAlunos: Cadastro.alunos){
			if(listaAlunos.getPersonalContratado() == null) {
				System.out.printf("%-30s | %-15s | %-25s | %-30s | %-20s\n", listaAlunos.getNome(), listaAlunos.getCpf(), listaAlunos.getPlano().getPlano().getNome(), " Não possui personal! ", listaAlunos.getDataMatricula());
			}
			else {
				System.out.printf("%-30s | %-15s | %-25s | %-30s | %-20s\n", listaAlunos.getNome(), listaAlunos.getCpf(), listaAlunos.getPlano().getPlano().getNome(), listaAlunos.getPersonalContratado().getNome(), listaAlunos.getDataMatricula());
			}
		}
	}
}