package gerenciamento.academia.modelos;

public class Funcionario extends Pessoa implements GerarRelatorios{
	private Cargo cargo;

	public Funcionario(String nome, String cpf, String senha, Cargo cargo) {
		super(nome, cpf, senha);
		this.cargo = cargo;
	}

	public Cargo getCargo() {
		return cargo;
	}

	@Override
	public String gerar() {
		return super.gerar() + cargo;
	}

	@Override
	public String toString() {
		return super.toString() + "Funcionario [cargo=" + cargo + "]";
	}
	
	

}
