/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Guilherme Gehling
 */
public class Atividade implements Serializable {

    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("dia")
    private Date dia;
    @Expose
    @SerializedName("tempo")
    private Time tempo;
    @Expose
    @SerializedName("usuario_id")
    private int usuario_id;
    @Expose
    @SerializedName("distancia")
    private double distancia;
    @Expose
    @SerializedName("velocidade")
    private double velocidade;
    @Expose
    @SerializedName("concluida")
    private int concluida;

    public Atividade() {
    }

    public Atividade(int id, Date dia, Time tempo, int usuario_id, double distancia, double velocidade, int concluida) {
        this.id = id;
        this.dia = dia;
        this.tempo = tempo;
        this.usuario_id = usuario_id;
        this.distancia = distancia;
        this.velocidade = velocidade;
        this.concluida = concluida;
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

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
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

    public int getConcluida() {
        return concluida;
    }

    public void setConcluida(int concluida) {
        this.concluida = concluida;
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
        final Atividade other = (Atividade) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Atividade{" + "id=" + id + ", dia=" + dia + ", tempo=" + tempo + ", usuario_id=" + usuario_id + ", distancia=" + distancia + ", velocidade=" + velocidade + ", concluida=" + concluida + '}';
    }
}
