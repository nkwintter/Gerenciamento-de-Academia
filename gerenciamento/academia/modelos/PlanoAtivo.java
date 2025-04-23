package gerenciamento.academia.modelos;

public class PlanoAtivo {
	private Plano plano;
	private Periodo periodo;
	private double precoFinal;
	
	public PlanoAtivo(Plano plano, Periodo periodo) {
		this.plano = plano;
		this.periodo = periodo;
		this.precoFinal = plano.precoPeriodo(periodo);
	}

	public Plano getPlano() {
		return plano;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public double getPrecoFinal() {
		return precoFinal;
	}

	@Override
	public String toString() {
		return "PlanoAtivo [plano=" + plano + ", periodo=" + periodo + ", precoFinal=" + precoFinal + "]";
	}

}
