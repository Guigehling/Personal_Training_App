/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.bean;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Guilherme Gehling
 */
public class Atividade implements Serializable {

    private int id;
    private Date dia;
    private Time hora;
    private Usuario usuario;
    private List<Movimento> movimentos;

    public Atividade() {
    }

    public Atividade(int id, Usuario usuario, List<Movimento> movimentos, Date dia, Time hora) {
        this.id = id;
        this.usuario = usuario;
        this.movimentos = movimentos;
        this.dia = dia;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Movimento> getMovimento() {
        return movimentos;
    }

    public void setMovimento(List<Movimento> movimentos) {
        this.movimentos = movimentos;
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
        int hash = 3;
        hash = 41 * hash + this.id;
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
}
