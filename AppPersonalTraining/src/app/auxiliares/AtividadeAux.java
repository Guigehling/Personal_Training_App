/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.auxiliares;

import app.bean.Atividade;
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
public class AtividadeAux {

    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("dia")
    private String dia;
    @Expose
    @SerializedName("tempo")
    private String tempo;
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
    private boolean concluida;

    public AtividadeAux() {
    }

    public AtividadeAux(int id, String dia, String tempo, int usuario_id, double distancia, double velocidade, boolean concluida) {
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

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
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

    public boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public void converteParaAtividadeAux(Atividade atividade) {
        this.id = atividade.getId();
        this.usuario_id = atividade.getUsuario_id();
        this.distancia = atividade.getDistancia();
        this.velocidade = atividade.getVelocidade();
        if (atividade.getDia() != null) {
            SimpleDateFormat dataformatacao = new SimpleDateFormat("dd/MM/yyyy");
            String dataTexto = dataformatacao.format(atividade.getDia());
            this.dia = dataTexto;
        } else {
            this.dia = null;
        }
        if (atividade.getTempo() != null) {
            SimpleDateFormat dataformatacao = new SimpleDateFormat("mm:ss");
            String dataHora = dataformatacao.format(atividade.getTempo());
            this.tempo = dataHora;
        } else {
            this.tempo = null;
        }
        this.concluida = atividade.getConcluida();
    }

    public Atividade converteParaAtividade() {
        Atividade atividade = new Atividade();
        atividade.setId(this.id);
        atividade.setUsuario_id(this.usuario_id);
        atividade.setDistancia(this.distancia);
        atividade.setVelocidade(this.velocidade);
        if (this.dia != null) {
            String data = this.dia;
            SimpleDateFormat dataformatacao = new SimpleDateFormat("dd/MM/yyyy");
            Date dataData = null;
            try {
                dataData = (Date) dataformatacao.parse(data);
            } catch (ParseException ex) {
                Logger.getLogger(UsuarioAux.class.getName()).log(Level.SEVERE, null, ex);
            }
            atividade.setDia(dataData);
        } else {
            atividade.setDia(null);
        }
        if (this.tempo != null) {
            String horario = this.dia;
            SimpleDateFormat dataformatacao = new SimpleDateFormat("mm:ss");
            Time datahora = null;
            try {
                datahora = (Time) dataformatacao.parse(horario);
            } catch (ParseException ex) {
                Logger.getLogger(UsuarioAux.class.getName()).log(Level.SEVERE, null, ex);
            }
            atividade.setTempo(datahora);
        } else {
            atividade.setTempo(null);
        }
        atividade.setConcluida(this.concluida);
        return atividade;
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
        final AtividadeAux other = (AtividadeAux) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
