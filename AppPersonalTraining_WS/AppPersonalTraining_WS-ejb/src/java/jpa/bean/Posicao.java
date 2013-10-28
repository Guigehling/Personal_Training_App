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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Guilherme Gehling
 */
@Entity
@Table(name = "posicao")
@NamedQueries({
    @NamedQuery(name = "Posicao.achaTODOS", query = "SELECT o FROM Posicao o ORDER BY o.id")
})
@SequenceGenerator(name = "seqPosicao", sequenceName = "SEQPOSICAO", allocationSize = 1)
public class Posicao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqPosicao")
    private int id;
    private String latitude, longitude;
    private double distancia;
    @Temporal(TemporalType.DATE)
    private Date dia;
    private Time hora;

    public Posicao() {
    }

    public Posicao(int id, String latitude, String longitude, double distancia, Date dia, Time hora) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distancia = distancia;
        this.dia = dia;
        this.hora = hora;
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

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public Date getData() {
        return dia;
    }

    public void setData(Date dia) {
        this.dia = dia;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
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
        return "Posicao{" + "latitude=" + latitude + ", longitude=" + longitude + ", distancia=" + distancia + '}';
    }
}
