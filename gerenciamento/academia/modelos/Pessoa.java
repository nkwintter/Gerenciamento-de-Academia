package gerenciamento.academia.modelos;

public abstract class Pessoa implements GerarRelatorios{
	
	private String nome;
	private String cpf;
	private String senha;
	
	public Pessoa(String nome, String cpf, String senha) {
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getSenha() {
		return senha;
	}

	public boolean autenticarSenha(String senha) {
		if(this.senha.equals(senha)) {
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", cpf=" + cpf + ", senha=" + senha + "]";
	}
	
	@Override
	public String gerar() {
		return nome + "," + cpf + ",";
	}

}

