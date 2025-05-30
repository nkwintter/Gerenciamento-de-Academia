package gerenciamento.academia.modelos;

import java.time.LocalDate;

public class Aluno extends Pessoa{
	
	private LocalDate dataMatricula;
	private PlanoAtivo plano;
	private Personal personalContratado = null; // criar set()
	
	public Aluno(String nome, String cpf, String senha, LocalDate dataMatricula, PlanoAtivo plano) {
		super(nome, cpf, senha);
		this.dataMatricula = dataMatricula;
		this.plano = plano;
	}

	@Override
	public String toString() {
		return "Nome: " +  super.getNome() + ", DataMatricula:" + dataMatricula + ", plano:" + plano;
	}
	
	public LocalDate getDataMatricula() {
		return dataMatricula;
	}

	public PlanoAtivo getPlano() {
		return plano;
	}

	public Personal getPersonalContratado() {
		return personalContratado;
	}

	public void setPersonalContratado(Personal personalContratado) {
		this.personalContratado = personalContratado;
	}

	public boolean PersonalVinculado(String cpf) {
		if(getPersonalContratado() == null) {
			return false;
		}
		
		if(getPersonalContratado().getCpf().equals(cpf)){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public String gerar() {
		return super.gerar() + dataMatricula + "," + plano;
		
	}
	
	
	
}
