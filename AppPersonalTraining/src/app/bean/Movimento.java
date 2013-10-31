/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.bean;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Guilherme Gehling
 */
public class Movimento implements Serializable {

    private int id;
    private String latitudePartida, latitudeChegada, longitudePartida, longitudeChegada;
    private double distancia, velocidade;
    private Date dia;
    private Time hora;

    public Movimento() {
    }

    public Movimento(int id, String latitudePartida, String latitudeChegada, String longitudePartida, String longitudeChegada, double distancia, double velocidade, Date dia, Time hora) {
        this.id = id;
        this.latitudePartida = latitudePartida;
        this.latitudeChegada = latitudeChegada;
        this.longitudePartida = longitudePartida;
        this.longitudeChegada = longitudeChegada;
        this.distancia = distancia;
        this.velocidade = velocidade;
        this.dia = dia;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatitudePartida() {
        return latitudePartida;
    }

    public void setLatitudePartida(String latitudePartida) {
        this.latitudePartida = latitudePartida;
    }

    public String getLatitudeChegada() {
        return latitudeChegada;
    }

    public void setLatitudeChegada(String latitudeChegada) {
        this.latitudeChegada = latitudeChegada;
    }

    public String getLongitudePartida() {
        return longitudePartida;
    }

    public void setLongitudePartida(String longitudePartida) {
        this.longitudePartida = longitudePartida;
    }

    public String getLongitudeChegada() {
        return longitudeChegada;
    }

    public void setLongitudeChegada(String longitudeChegada) {
        this.longitudeChegada = longitudeChegada;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
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
        final Movimento other = (Movimento) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Movimento{" + "distancia=" + distancia + ", velocidade=" + velocidade + ", dia=" + dia + ", hora=" + hora + '}';
    }
}
