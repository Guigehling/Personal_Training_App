/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.ejb.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import jpa.bean.Usuario;

/**
 *
 * @author Guilherme Gehling
 */
@Stateless
public class UsuarioDAO implements UsuarioDAORemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Usuario create(Usuario usuario) {
        if (this.valida(usuario)) {
            em.persist(usuario);
            return usuario;
        } else {
            return null;
        }
    }

    @Override
    public Usuario retrive(Usuario usuario) {
        Usuario retorno = em.find(Usuario.class, usuario.getId());
        return retorno;
    }

    @Override
    public void update(Usuario usuario) {
        if (this.valida(usuario)) {
            em.merge(usuario);
        }
    }

    @Override
    public void delete(Usuario usuario) {
        usuario = this.retrive(usuario);
        em.remove(usuario);
    }

    @Override
    public List<Usuario> listaTodos() {
        return (List<Usuario>) em.createNamedQuery("Usuario.achaTodos").getResultList();
    }

    @Override
    public Usuario achaUsuarioPorEmail(Usuario usuario) {
        Query query = em.createNamedQuery("Usuario.achaUsuarioPorEmail");
        query.setParameter("email", usuario.getEmail());
        Usuario usuarioret = (Usuario) query.getSingleResult();
        return usuarioret;
    }

    @Override
    public boolean valida(Usuario usuario) {
        boolean ret = false;
        if (usuario != null) {
            ret = true;
        }
        return ret;
    }
}
