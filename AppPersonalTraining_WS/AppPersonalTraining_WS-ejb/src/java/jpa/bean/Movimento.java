/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.bean;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
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
@Table(name = "movimento")
@NamedQueries({
    @NamedQuery(name = "Movimento.achaTODOS", query = "SELECT o FROM Movimento o ORDER BY o.id")
})
@SequenceGenerator(name = "seqMovimento", sequenceName = "SEQMOVIMENTO", allocationSize = 1)
public class Movimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqMovimento")
    private int id;
//    @OneToOne
//    @JoinColumn(name = "id_posicao_inicial", referencedColumnName = "id")
//    private Posicao posicaoInicial;
//    @OneToOne
//    @JoinColumn(name = "id_posicao_final", referencedColumnName = "id")
//    private Posicao posicaoFinal;
    private double distancia, velocidade;
    @Temporal(TemporalType.DATE)
    private Date dia;
    private Time hora;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
