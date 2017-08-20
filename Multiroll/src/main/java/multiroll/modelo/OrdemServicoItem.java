package multiroll.modelo;

import java.util.Objects;

/**
 * 18/04/2017
 *
 * @author leleti
 */
public class OrdemServicoItem {

    private Long id;
    private int quantidade;
    private double valorUnit;
    private String diametroF;
    private String diametroB;
    private String cumprimento;
    private String descricao;
    private OrdemServico ordemServico;
    private Servico servico;

    public OrdemServicoItem() {
    }

    public OrdemServicoItem(Long id, int quantidade, double valorUnit, String diametroF, String diametroB, String cumprimento, String descricao, OrdemServico ordemServico, Servico servico) {
        this.id = id;
        this.quantidade = quantidade;
        this.valorUnit = valorUnit;
        this.diametroF = diametroF;
        this.diametroB = diametroB;
        this.cumprimento = cumprimento;
        this.descricao = descricao;
        this.ordemServico = ordemServico;
        this.servico = servico;
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

    public double getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(double valorUnit) {
        this.valorUnit = valorUnit;
    }

    public String getDiametroF() {
        return diametroF;
    }

    public void setDiametroF(String diametroF) {
        this.diametroF = diametroF;
    }

    public String getDiametroB() {
        return diametroB;
    }

    public void setDiametroB(String diametroB) {
        this.diametroB = diametroB;
    }

    public String getCumprimento() {
        return cumprimento;
    }

    public void setCumprimento(String cumprimento) {
        this.cumprimento = cumprimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + this.quantidade;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.valorUnit) ^ (Double.doubleToLongBits(this.valorUnit) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.diametroF);
        hash = 97 * hash + Objects.hashCode(this.diametroB);
        hash = 97 * hash + Objects.hashCode(this.cumprimento);
        hash = 97 * hash + Objects.hashCode(this.descricao);
        hash = 97 * hash + Objects.hashCode(this.ordemServico);
        hash = 97 * hash + Objects.hashCode(this.servico);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrdemServicoItem other = (OrdemServicoItem) obj;
        if (this.quantidade != other.quantidade) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorUnit) != Double.doubleToLongBits(other.valorUnit)) {
            return false;
        }
        if (!Objects.equals(this.diametroF, other.diametroF)) {
            return false;
        }
        if (!Objects.equals(this.diametroB, other.diametroB)) {
            return false;
        }
        if (!Objects.equals(this.cumprimento, other.cumprimento)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.ordemServico, other.ordemServico)) {
            return false;
        }
        if (!Objects.equals(this.servico, other.servico)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrdemServicoItem{" + "id=" + id + ", quantidade=" + quantidade + ", valorUnit=" + valorUnit + ", diametroF=" + diametroF + ", diametroB=" + diametroB + ", cumprimento=" + cumprimento + ", descricao=" + descricao + ", ordemServico=" + ordemServico + ", servico=" + servico + '}';
    }

}
