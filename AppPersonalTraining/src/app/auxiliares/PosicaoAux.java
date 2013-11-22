/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.auxiliares;

import app.bean.Posicao;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme Gehling
 */
public class PosicaoAux {

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
    private String dia;
    @Expose
    @SerializedName("hora")
    private String hora;
    @Expose
    @SerializedName("usuario_id")
    private int usuario_id;
    @Expose
    @SerializedName("atividade_id")
    private int atividade_id;
    @Expose
    @SerializedName("ultimaposicao")
    private boolean ultimaPosicao;

    public PosicaoAux() {
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

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getAtividade_id() {
        return atividade_id;
    }

    public void setAtividade_id(int atividade_id) {
        this.atividade_id = atividade_id;
    }

    public boolean getUltimaPosicao() {
        return ultimaPosicao;
    }

    public void setUltimaPosicao(boolean ultimaPosicao) {
        this.ultimaPosicao = ultimaPosicao;
    }

    public PosicaoAux(int id, String latitude, String longitude, String dia, String hora, int usuario_id, int atividade_id, boolean ultimaPosicao) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dia = dia;
        this.hora = hora;
        this.usuario_id = usuario_id;
        this.atividade_id = atividade_id;
        this.ultimaPosicao = ultimaPosicao;
    }

    public void converteParaPosicaoAux(Posicao posicao) {
        this.id = posicao.getId();
        this.latitude = posicao.getLatitude();
        this.longitude = posicao.getLongitude();
        if (posicao.getDia() != null) {
            SimpleDateFormat dataformatacao = new SimpleDateFormat("dd/MM/yyyy");
            String dataTexto = dataformatacao.format(posicao.getDia());
            this.dia = dataTexto;
        } else {
            this.dia = null;
        }
        if (posicao.getHora() != null) {
            SimpleDateFormat dataformatacao = new SimpleDateFormat("HH:mm");
            String dataHora = dataformatacao.format(posicao.getHora());
            this.hora = dataHora;
        } else {
            this.hora = null;
        }
        this.usuario_id = posicao.getUsuario_id();
        this.atividade_id = posicao.getAtividade_id();
        this.ultimaPosicao = posicao.getUltimaPosicao();
    }

    public Posicao converteParaPosicao() {
        Posicao posicao = new Posicao();
        posicao.setId(this.id);
        posicao.setLatitude(this.latitude);
        posicao.setLongitude(this.longitude);
        if (this.dia != null) {
            String data = this.dia;
            SimpleDateFormat dataformatacao = new SimpleDateFormat("dd/MM/yyyy");
            Date dataData = null;
            try {
                dataData = (Date) dataformatacao.parse(data);
            } catch (ParseException ex) {
                Logger.getLogger(UsuarioAux.class.getName()).log(Level.SEVERE, null, ex);
            }
            posicao.setDia(dataData);
        } else {
            posicao.setDia(null);
        }
        if (this.hora != null) {
            String horario = this.dia;
            SimpleDateFormat dataformatacao = new SimpleDateFormat("HH:mm");
            Time datahora = null;
            try {
                datahora = (Time) dataformatacao.parse(horario);
            } catch (ParseException ex) {
                Logger.getLogger(UsuarioAux.class.getName()).log(Level.SEVERE, null, ex);
            }
            posicao.setHora(datahora);
        } else {
            posicao.setHora(null);
        }
        posicao.setAtividade_id(this.atividade_id);
        posicao.setUsuario_id(this.usuario_id);
        posicao.setUltimaPosicao(this.ultimaPosicao);
        return posicao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.id;
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
        final PosicaoAux other = (PosicaoAux) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PosicaoAux{" + "id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", dia=" + dia + ", hora=" + hora + ", usuario_id=" + usuario_id + ", atividade_id=" + atividade_id + ", ultimaPosicao=" + ultimaPosicao + '}';
    }
}
