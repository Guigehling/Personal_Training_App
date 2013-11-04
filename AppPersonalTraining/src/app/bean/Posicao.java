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
public class Posicao implements Serializable {

    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("latitude")
    private String latitude;
    @Expose
    @SerializedName("longitude")
    private String longitude;
    @Expose
    @SerializedName("dia")
    private Date dia;
    @Expose
    @SerializedName("hora")
    private Time hora;
    @Expose
    @SerializedName("usuario")
    private Usuario usuario;
    @Expose
    @SerializedName("ultimaPosicao")
    private boolean ultimaPosicao;

    public Posicao() {
    }

    public Posicao(int id, String latitude, String longitude, Date dia, Time hora, Usuario usuario, boolean ultimaPosicao) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dia = dia;
        this.hora = hora;
        this.usuario = usuario;
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

    public boolean isUltimaPosicao() {
        return ultimaPosicao;
    }

    public void setUltimaPosicao(boolean ultimaPosicao) {
        this.ultimaPosicao = ultimaPosicao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
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
        return "Posicao{" + "id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", dia=" + dia + ", hora=" + hora + ", usuario=" + usuario + ", ultimaPosicao=" + ultimaPosicao + '}';
    }
}
