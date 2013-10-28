/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.ejb.dao;

import java.util.List;
import javax.ejb.Remote;
import jpa.bean.Usuario;

/**
 *
 * @author Guilherme Gehling
 */
@Remote
public interface UsuarioDAORemote {

    Usuario create(Usuario usuario);

    Usuario retrive(Usuario usuario);

    void update(Usuario usuario);

    void delete(Usuario usuario);

    boolean valida(Usuario usuario);

    List<Usuario> listaTodos();

    Usuario achaUsuarioPorEmail(String email);
}
