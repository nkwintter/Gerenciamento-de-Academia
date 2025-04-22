package gerenciamento.academia.modelos;

public enum Plano {
	MENSAL_1_MODALIDADE("Mensal - 01 modalidade", 80),
	MENSAL_2_MUSCULACAO("Mensal - 02 musculação ", 90),
	MENSAL_3_FUNCIONAL("Mensal - 03 funcional", 70),
	MENSAL_4_MUSCULACAO_FUNCIONAL("Mensal - 04 musculação + funcional", 150),
	MENSAL_TOTAL("Mensal - musculação + funcional + coletivas", 190);
	
	private final String descricao;
	private final double preco;

	Plano(String descricao, double preco) {
		this.descricao = descricao;
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getPreco() {
		return preco;
	}
}


