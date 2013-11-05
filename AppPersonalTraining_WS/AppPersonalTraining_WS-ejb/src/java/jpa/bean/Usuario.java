/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme Gehling
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.achaTODOS", query = "SELECT o FROM Usuario o ORDER BY o.id"),
    @NamedQuery(name = "Usuario.achaUsuarioPorEmail", query = "SELECT o FROM Usuario o WHERE o.email = :email")
})
@SequenceGenerator(name = "seqUsuario", sequenceName = "SEQUSUARIO", allocationSize = 1)
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqUsuario")
    private int id;
    @Column(nullable = true)
    private String nome, email, senha;

    public Usuario() {
    }

    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean validaSenha(String senha) {
        boolean ret = false;
        try {
            if (this.senha.equals(senha)) {
                ret = true;
            }
        } catch (NullPointerException ex) {
        }
        return ret;
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
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
