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
import jpa.bean.Posicao;
import jpa.bean.Usuario;
import jpa.ejb.dao.AtividadeDAORemote;
import jpa.ejb.dao.UsuarioDAORemote;

/**
 *
 * @author Guilherme Gehling
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PosicaoAux implements Serializable {

    @EJB
    UsuarioDAORemote usuarioDAORemote;
    AtividadeDAORemote atividadeDAORemote;
    private int id;
    private String latitude;
    private String longitude;
    private String dia;
    private String hora;
    private int usuario_id;
    private int atividade_id;

    public PosicaoAux() {
    }

    public PosicaoAux(int id, String latitude, String longitude, String dia, String hora, int usuario_id, int atividade_id) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dia = dia;
        this.hora = hora;
        this.usuario_id = usuario_id;
        this.atividade_id = atividade_id;
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
        this.usuario_id = posicao.getUsuario().getId();
        this.atividade_id = posicao.getAtividade().getId();
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
        Atividade atividade = new Atividade();
        atividade = atividadeDAORemote.retrive(atividade);
        posicao.setAtividade(atividade);
        Usuario usuario = new Usuario();
        usuario = usuarioDAORemote.retrive(usuario);
        posicao.setUsuario(usuario);
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
        return "PosicaoAux{" + "id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", dia=" + dia + ", hora=" + hora + ", usuario_id=" + usuario_id + ", atividade_id=" + atividade_id + '}';
    }
}
