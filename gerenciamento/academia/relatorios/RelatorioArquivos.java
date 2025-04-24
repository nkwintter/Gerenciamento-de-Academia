package gerenciamento.academia.relatorios;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import gerenciamento.academia.menus.Cadastro;
import gerenciamento.academia.menus.MenuFuncionario;
import gerenciamento.academia.modelos.Aluno;
import gerenciamento.academia.modelos.Avaliacao;
import gerenciamento.academia.modelos.Funcionario;
import gerenciamento.academia.modelos.Personal;
import gerenciamento.academia.modelos.Plano;

public class RelatorioArquivos {

	public static void arquivosRelatorios() {
		Path pathRelatorio = Paths.get("./src/gerenciamento/academia/arquivos/RelatoriosAcademia.csv");

		try (BufferedWriter bw = Files.newBufferedWriter(pathRelatorio)){
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

			bw.write("Planos:");
			bw.newLine();
			for (Plano p : Cadastro.planos) {
				bw.write(p.gerar());
				bw.newLine();
			}

			bw.write("Avaliações:");
			bw.newLine();
			for (Avaliacao a : Cadastro.avaliacoes) {
				bw.write(a.gerar());
				bw.newLine();
			}

			bw.write("Valor total a receber no mês: R$" + MenuFuncionario.totalAhReceber());
			bw.newLine();

			System.out.println("Arquivo gerado com sucesso!\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
