package multiroll.modelo;

/**
 * 18/04/2017
 *
 * @author leleti
 */
public class MaquinaItem {

	private Long id;
	private int quantidade;
	private String descricao;
	private String diametro;
	private String comprimento;
	private double revestimento;
	private double eixo;
	private Maquina maquina;

	public MaquinaItem() {
	}

	public MaquinaItem(Long id, int quantidade, String descricao, String diametro, String comprimento,
			double revestimento, double eixo, Maquina maquina) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.diametro = diametro;
		this.comprimento = comprimento;
		this.revestimento = revestimento;
		this.eixo = eixo;
		this.maquina = maquina;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDiametro() {
		return diametro;
	}

	public void setDiametro(String diametro) {
		this.diametro = diametro;
	}

	public String getComprimento() {
		return comprimento;
	}

	public void setComprimento(String comprimento) {
		this.comprimento = comprimento;
	}

	public double getRevestimento() {
		return revestimento;
	}

	public void setRevestimento(double revestimento) {
		this.revestimento = revestimento;
	}

	public double getEixo() {
		return eixo;
	}

	public void setEixo(double eixo) {
		this.eixo = eixo;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comprimento == null) ? 0 : comprimento.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((diametro == null) ? 0 : diametro.hashCode());
		long temp;
		temp = Double.doubleToLongBits(eixo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((maquina == null) ? 0 : maquina.hashCode());
		result = prime * result + quantidade;
		temp = Double.doubleToLongBits(revestimento);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaquinaItem other = (MaquinaItem) obj;
		if (comprimento == null) {
			if (other.comprimento != null)
				return false;
		} else if (!comprimento.equals(other.comprimento))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (diametro == null) {
			if (other.diametro != null)
				return false;
		} else if (!diametro.equals(other.diametro))
			return false;
		if (Double.doubleToLongBits(eixo) != Double.doubleToLongBits(other.eixo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (maquina == null) {
			if (other.maquina != null)
				return false;
		} else if (!maquina.equals(other.maquina))
			return false;
		if (quantidade != other.quantidade)
			return false;
		if (Double.doubleToLongBits(revestimento) != Double.doubleToLongBits(other.revestimento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MaquinaItens [id=" + id + ", quantidade=" + quantidade + ", descricao=" + descricao + ", diametro="
				+ diametro + ", comprimento=" + comprimento + ", revestimento=" + revestimento + ", eixo=" + eixo
				+ ", maquina=" + maquina + "]";
	}

}
