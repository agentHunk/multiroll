package multiroll.modelo;

import java.util.Date;
import java.util.Objects;

/**
 * 18/04/2017
 *
 * @author leleti
 */
public class OrdemServico {

    private Long id;
    private double valorTotal;
    private Date dataServico;
    private Date dataEntrega;
    private Date dataPrevisao;
    private String observacao;
    private Cliente cliente;
    private FormaPgto formaPgto;
    private Usuario usuario;

    public OrdemServico() {
    }

    public OrdemServico(Long id, double valorTotal, Date dataServico, Date dataEntrega, Date dataPrevisao, String observacao, Cliente cliente, FormaPgto formaPgto, Usuario usuario) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.dataServico = dataServico;
        this.dataEntrega = dataEntrega;
        this.dataPrevisao = dataPrevisao;
        this.observacao = observacao;
        this.cliente = cliente;
        this.formaPgto = formaPgto;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataServico() {
        return dataServico;
    }

    public void setDataServico(Date dataServico) {
        this.dataServico = dataServico;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Date getDataPrevisao() {
        return dataPrevisao;
    }

    public void setDataPrevisao(Date dataPrevisao) {
        this.dataPrevisao = dataPrevisao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public FormaPgto getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(FormaPgto formaPgto) {
        this.formaPgto = formaPgto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.valorTotal) ^ (Double.doubleToLongBits(this.valorTotal) >>> 32));
        hash = 89 * hash + Objects.hashCode(this.dataServico);
        hash = 89 * hash + Objects.hashCode(this.dataEntrega);
        hash = 89 * hash + Objects.hashCode(this.dataPrevisao);
        hash = 89 * hash + Objects.hashCode(this.observacao);
        hash = 89 * hash + Objects.hashCode(this.cliente);
        hash = 89 * hash + Objects.hashCode(this.formaPgto);
        hash = 89 * hash + Objects.hashCode(this.usuario);
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
        final OrdemServico other = (OrdemServico) obj;
        if (Double.doubleToLongBits(this.valorTotal) != Double.doubleToLongBits(other.valorTotal)) {
            return false;
        }
        if (!Objects.equals(this.observacao, other.observacao)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dataServico, other.dataServico)) {
            return false;
        }
        if (!Objects.equals(this.dataEntrega, other.dataEntrega)) {
            return false;
        }
        if (!Objects.equals(this.dataPrevisao, other.dataPrevisao)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.formaPgto, other.formaPgto)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrdemServico{" + "id=" + id + ", valorTotal=" + valorTotal + ", dataServico=" + dataServico + ", dataEntrega=" + dataEntrega + ", dataPrevisao=" + dataPrevisao + ", observacao=" + observacao + ", cliente=" + cliente + ", formaPgto=" + formaPgto + ", usuario=" + usuario + '}';
    }

}
