/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.auxiliar;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import jpa.bean.Usuario;

/**
 *
 * @author Guilherme Gehling
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UsuarioAux implements Serializable {

    private int id;
    private String nome, email, senha;
    private String dataNascimento;
    private String sexo;
    private double peso;

    public UsuarioAux() {
    }

    public UsuarioAux(int id, String nome, String email, String senha, String dataNascimento, String sexo, double peso) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.peso = peso;
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void converteParaUsuarioAux(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        if (usuario.getDataNascimento() != null) {
            SimpleDateFormat dataformatacao = new SimpleDateFormat("dd/MM/yyyy");
            String dataTexto = dataformatacao.format(usuario.getDataNascimento());
            this.dataNascimento = dataTexto;
        } else {
            this.dataNascimento = null;
        }
        this.sexo = usuario.getSexo();
        this.peso = usuario.getPeso();
    }

    public Usuario converteParaUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(this.id);
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);
        if (this.dataNascimento != null) {
            SimpleDateFormat dataformatacao = new SimpleDateFormat("dd/MM/yyyy");
            Date dataData = null;
            try {
                dataData = (Date) dataformatacao.parse(this.dataNascimento);
            } catch (ParseException ex) {
                Logger.getLogger(UsuarioAux.class.getName()).log(Level.SEVERE, null, ex);
            }
            usuario.setDataNascimento(dataData);
        } else {
            usuario.setDataNascimento(null);
        }
        usuario.setSexo(this.sexo);
        usuario.setPeso(this.peso);
        return usuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
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
        final UsuarioAux other = (UsuarioAux) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", dataNascimento=" + dataNascimento + ", sexo=" + sexo + ", peso=" + peso + '}';
    }
}
