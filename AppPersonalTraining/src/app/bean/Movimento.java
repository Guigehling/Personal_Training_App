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
public class Movimento implements Serializable {

    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("posicaoInicial")
    private Posicao posicaoInicial;
    @Expose
    @SerializedName("posicaoFinal")
    private Posicao posicaoFinal;
    @Expose
    @SerializedName("posicaoFinal")
    private double distancia;
    @Expose
    @SerializedName("velocidade")
    private double velocidade;
    @Expose
    @SerializedName("dia")
    private Date dia;
    @Expose
    @SerializedName("hora")
    private Time hora;

    public Movimento() {
    }

    public Movimento(int id, Posicao posicaoInicial, Posicao posicaoFinal, double distancia, double velocidade, Date dia, Time hora) {
        this.id = id;
        this.posicaoInicial = posicaoInicial;
        this.posicaoFinal = posicaoFinal;
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

    public Posicao getPosicaoInicial() {
        return posicaoInicial;
    }

    public void setPosicaoInicial(Posicao posicaoInicial) {
        this.posicaoInicial = posicaoInicial;
    }

    public Posicao getPosicaoFinal() {
        return posicaoFinal;
    }

    public void setPosicaoFinal(Posicao posicaoFinal) {
        this.posicaoFinal = posicaoFinal;
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
        hash = 19 * hash + this.id;
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
        return "Movimento{" + "id=" + id + ", posicaoInicial=" + posicaoInicial + ", posicaoFinal=" + posicaoFinal + ", distancia=" + distancia + ", velocidade=" + velocidade + ", dia=" + dia + ", hora=" + hora + '}';
    }
}
