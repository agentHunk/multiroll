package multiroll.modelo;

import java.util.Objects;

/**
 * 18/04/2017
 *
 * @author leleti
 */
public class HistoricoOrdemServico {

    private Long id;
    private String observacao;
    private OrdemServico ordemServico;

    public HistoricoOrdemServico() {
    }

    public HistoricoOrdemServico(Long id, String observacao, OrdemServico ordemServico) {
        this.id = id;
        this.observacao = observacao;
        this.ordemServico = ordemServico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.observacao);
        hash = 29 * hash + Objects.hashCode(this.ordemServico);
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
        final HistoricoOrdemServico other = (HistoricoOrdemServico) obj;
        if (!Objects.equals(this.observacao, other.observacao)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.ordemServico, other.ordemServico)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HistoricoOrdemServico{" + "id=" + id + ", observacao=" + observacao + ", ordemServico=" + ordemServico + '}';
    }

}
