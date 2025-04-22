package gerenciamento.academia.modelos;

import java.time.LocalDate;

public class Aluno extends Pessoa implements GerarRelatorios{
	
	private LocalDate dataMatricula;
	private Plano plano;
	private Personal personalContratado = null; // criar set()
	
	public Aluno(String nome, String cpf, String senha, LocalDate dataMatricula, Plano plano) {
		super(nome, cpf, senha);
		this.dataMatricula = dataMatricula;
		this.plano = plano;
	}

	@Override
	public String toString() {
		return super.toString() + "Aluno [dataMatricula=" + dataMatricula + ", plano=" + plano + ", personalContratado="
				+ personalContratado + "]";
	}
	
	public LocalDate getDataMatricula() {
		return dataMatricula;
	}

	public Plano getPlano() {
		return plano;
	}

	public Personal getPersonalContratado() {
		return personalContratado;
	}

	public void setPersonalContratado(Personal personalContratado) {
		this.personalContratado = personalContratado;
	}

	public boolean PersonalVinculado(String cpf) {
		if(getPersonalContratado().getCpf() == cpf){
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
