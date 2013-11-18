/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.auxiliar;

import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import jpa.bean.Atividade;
import jpa.bean.Usuario;
import jpa.ejb.dao.UsuarioDAORemote;

/**
 *
 * @author Guilherme Gehling
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AtividadeAux implements Serializable {

    @EJB
    UsuarioDAORemote usuarioDAORemote;
    private int id;
    private String dia;
    private String tempo;
    private int usuario_id;
    private double distancia;
    private double velocidade;
    private int concluida;

    public AtividadeAux() {
    }

    public AtividadeAux(int id, String dia, String tempo, int usuario_id, double distancia, double velocidade, int concluida) {
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

    public int getConcluida() {
        return concluida;
    }

    public void setConcluida(int concluida) {
        this.concluida = concluida;
    }

    public void converteParaAtividadeAux(Atividade atividade) {
        this.id = atividade.getId();
        this.usuario_id = atividade.getUsuario().getId();
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
            SimpleDateFormat dataformatacao = new SimpleDateFormat("HH:mm");
            String dataHora = dataformatacao.format(atividade.getTempo());
            this.tempo = dataHora;
        } else {
            this.tempo = null;
        }
        if (atividade.getConcluida()) {
            this.concluida = 1;
        } else {
            this.concluida = 0;
        }
    }

    public Atividade converteParaAtividade() {
        Atividade atividade = new Atividade();
        atividade.setId(this.id);
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario = usuarioDAORemote.retrive(usuario);
        atividade.setUsuario(usuario);
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
            SimpleDateFormat dataformatacao = new SimpleDateFormat("HH:mm");
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
        if (this.concluida == 1) {
            atividade.setConcluida(true);
        } else {
            atividade.setConcluida(false);
        }
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
