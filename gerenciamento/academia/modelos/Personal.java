package gerenciamento.academia.modelos;

public class Personal extends Pessoa implements GerarRelatorios{
	private String especialidade;
	private String cref;

	public Personal(String nome, String cpf, String senha, String especialidade, String cref) {
		super(nome, cpf, senha);
		this.especialidade = especialidade;
		this.cref = cref;
		
	}

	@Override
	public String toString() {
		return super.toString() + "Personal [especialidade=" + especialidade + ", cref=" + cref + "]";
	}
	
	@Override
	public String gerar() {
		return super.gerar() + especialidade + "," + cref;
		
	}

}
