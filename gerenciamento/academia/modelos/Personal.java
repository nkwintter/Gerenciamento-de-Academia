package gerenciamento.academia.modelos;

public class Personal extends Pessoa implements GerarRelatorios{
	private String especialidade;
	private String cref;

	public Personal(String nome, String cpf, String senha, String especialidade, String cref) {
		super(nome, cpf, senha);
		this.especialidade = especialidade;
		this.cref = cref;
		
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public String getCref() {
		return cref;
	}

	@Override
	public String toString() {
		return "Nome: " + super.getNome() + " Especialidade: " + especialidade + ", cref:" + cref;
	}
	
	@Override
	public String gerar() {
		return super.gerar() + especialidade + "," + cref;
		
	}

}
