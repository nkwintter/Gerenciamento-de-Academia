package gerenciamento.academia.modelos;

public class Plano {
	private String nome;
	private String descricao;
	private double precoM; // preço Mensal
	private double precoT; // preço Trimestral
	private double precoA; // preço Anual
	
	public Plano(String nome, String descricao, double precoM, double precoT, double precoA) {
		this.nome = nome;
		this.descricao = descricao;
		this.precoM = precoM;
		this.precoT = precoT;
		this.precoA = precoA;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public double precoPeriodo(Periodo periodo) {
		
		return switch(periodo) {
			case MENSAL -> precoM;
			case TRIMESTRAL -> precoT;
			case ANUAL -> precoA;
		};
	}

	@Override
	public String toString() {
		return "Plano:" + nome + ", descricao:" + descricao + "]";
	}
	
	
	
}
