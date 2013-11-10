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
@Table(name = "atividade")
@NamedQueries({
    @NamedQuery(name = "Atividade.achaTODOS", query = "SELECT o FROM Atividade o ORDER BY o.id"),
    @NamedQuery(name = "Atividade.achaPorUsuario", query = "SELECT o FROM Atividade o WHERE o.usuario = :usuario")
})
@SequenceGenerator(name = "seqAtividade", sequenceName = "SEQATIVIDADE", allocationSize = 1)
public class Atividade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqAtividade")
    private int id;
    @Temporal(TemporalType.DATE)
    private Date dia;
    private Time tempo;
    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;
    private double distancia, velocidade;

    public Atividade() {
    }

    public Atividade(int id, Date dia, Time tempo, Usuario usuario, double distancia, double velocidade) {
        this.id = id;
        this.dia = dia;
        this.tempo = tempo;
        this.usuario = usuario;
        this.distancia = distancia;
        this.velocidade = velocidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Time getTempo() {
        return tempo;
    }

    public void setTempo(Time tempo) {
        this.tempo = tempo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id;
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
        final Atividade other = (Atividade) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Atividade{" + "id=" + id + ", dia=" + dia + ", tempo=" + tempo + ", usuario=" + usuario + ", distancia=" + distancia + ", velocidade=" + velocidade + '}';
    }
}
