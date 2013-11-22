/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.bean;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme Gehling
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "posicao")
@NamedQueries({
    @NamedQuery(name = "Posicao.achaTODOS", query = "SELECT o FROM Posicao o ORDER BY o.id"),
    @NamedQuery(name = "Posicao.achaPorAtividade", query = "SELECT o FROM Posicao o WHERE o.atividade = :atividade"),
    @NamedQuery(name = "Posicao.achaPorusuario", query = "SELECT o FROM Posicao o WHERE o.atividade = :usuario"),
    @NamedQuery(name = "Posicao.achaUltimaPosicao", query = "SELECT o FROM Posicao o WHERE o.atividade = :atividade AND o.ultimaPosicao = true")
})
@SequenceGenerator(name = "seqPosicao", sequenceName = "SEQPOSICAO", allocationSize = 1)
public class Posicao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqPosicao")
    private int id;
    private String latitude, longitude;
    @Temporal(TemporalType.DATE)
    private Date dia;
    private Time hora;
    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
    @OneToOne
    @JoinColumn(name = "atividade_id", referencedColumnName = "id")
    private Atividade atividade;
    private Boolean ultimaPosicao;

    public Posicao() {
    }

    public Posicao(int id, String latitude, String longitude, Date dia, Time hora, Usuario usuario, Atividade atividade, Boolean ultimaPosicao) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dia = dia;
        this.hora = hora;
        this.usuario = usuario;
        this.atividade = atividade;
        this.ultimaPosicao = ultimaPosicao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public Boolean getUltimaPosicao() {
        return ultimaPosicao;
    }

    public void setUltimaPosicao(Boolean ultimaPosicao) {
        this.ultimaPosicao = ultimaPosicao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Posicao other = (Posicao) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Posicao{" + "id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", dia=" + dia + ", hora=" + hora + ", usuario=" + usuario + ", atividade=" + atividade + ", ultimaPosicao=" + ultimaPosicao + '}';
    }
}