/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.bean;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Guilherme Gehling
 */
@Entity
@Table(name = "atividade")
@NamedQueries({
    @NamedQuery(name = "Atividade.achaTODOS", query = "SELECT o FROM Atividade o ORDER BY o.id")
})
@SequenceGenerator(name = "seqAtividade", sequenceName = "SEQATIVIDADE", allocationSize = 1)
public class Atividade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqAtividade")
    private int id;
    @Temporal(TemporalType.DATE)
    private Date dia;
    private Time hora;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;
    @OneToMany
    @JoinTable(name = "atividade_movimentos", joinColumns = {
        @JoinColumn(name = "id_atividade")}, inverseJoinColumns = {
        @JoinColumn(name = "id_movimentos")})
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
