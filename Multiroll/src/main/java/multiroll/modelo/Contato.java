package multiroll.modelo;

import java.util.Objects;

/**
 * 18/04/2017
 * @author leleti
 */
public class Contato {

    private Long id;
    private String telFixo;
    private String telFixo2;
    private String telCel;
    private String telCel2;
    private String whatsApp;
    private String email;
    private String email2;
    private String observacao;
    private Cliente cliente;

    public Contato() {
    }

    public Contato(Long id, String telFixo, String telFixo2, String telCel, String telCel2, String whatsApp, String email, String email2, String observacao, Cliente cliente) {
        this.id = id;
        this.telFixo = telFixo;
        this.telFixo2 = telFixo2;
        this.telCel = telCel;
        this.telCel2 = telCel2;
        this.whatsApp = whatsApp;
        this.email = email;
        this.email2 = email2;
        this.observacao = observacao;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelFixo() {
        return telFixo;
    }

    public void setTelFixo(String telFixo) {
        this.telFixo = telFixo;
    }

    public String getTelFixo2() {
        return telFixo2;
    }

    public void setTelFixo2(String telFixo2) {
        this.telFixo2 = telFixo2;
    }

    public String getTelCel() {
        return telCel;
    }

    public void setTelCel(String telCel) {
        this.telCel = telCel;
    }

    public String getTelCel2() {
        return telCel2;
    }

    public void setTelCel2(String telCel2) {
        this.telCel2 = telCel2;
    }

    public String getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.telFixo);
        hash = 67 * hash + Objects.hashCode(this.telFixo2);
        hash = 67 * hash + Objects.hashCode(this.telCel);
        hash = 67 * hash + Objects.hashCode(this.telCel2);
        hash = 67 * hash + Objects.hashCode(this.whatsApp);
        hash = 67 * hash + Objects.hashCode(this.email);
        hash = 67 * hash + Objects.hashCode(this.email2);
        hash = 67 * hash + Objects.hashCode(this.observacao);
        hash = 67 * hash + Objects.hashCode(this.cliente);
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
        final Contato other = (Contato) obj;
        if (!Objects.equals(this.telFixo, other.telFixo)) {
            return false;
        }
        if (!Objects.equals(this.telFixo2, other.telFixo2)) {
            return false;
        }
        if (!Objects.equals(this.telCel, other.telCel)) {
            return false;
        }
        if (!Objects.equals(this.telCel2, other.telCel2)) {
            return false;
        }
        if (!Objects.equals(this.whatsApp, other.whatsApp)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.email2, other.email2)) {
            return false;
        }
        if (!Objects.equals(this.observacao, other.observacao)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Contato{" + "id=" + id + ", telFixo=" + telFixo + ", telFixo2=" + telFixo2 + ", telCel=" + telCel + ", telCel2=" + telCel2 + ", whatsApp=" + whatsApp + ", email=" + email + ", email2=" + email2 + ", observacao=" + observacao + ", cliente=" + cliente + '}';
    }
    
}
