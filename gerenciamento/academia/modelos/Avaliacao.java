package gerenciamento.academia.modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Avaliacao implements GerarRelatorios{
	private Aluno aluno;
	private LocalDate data;
	private Personal personal;
	private String descricao;
	
	public Avaliacao(Aluno aluno, LocalDate data, Personal personal, String descricao) {
		this.aluno = aluno;
		this.data = data;
		this.personal = personal;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return " Data: " + data.format(formater) + ", Aluno:" + aluno.getNome() + ", Personal:" + personal.getNome() + ", Descricao:" + descricao;
	}

	public Personal getPersonal() {
		return personal;
	}

	public String getDescricao() {
		return descricao;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public LocalDate getData() {
		return data;
	}
	
	DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	@Override
	public String gerar() {
		return aluno.getNome() + "," + data.format(formater) + "," + personal.getNome() + "," + descricao;
	}
	
	
}
