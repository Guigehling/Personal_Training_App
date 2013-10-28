/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.ejb.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.bean.Movimento;

/**
 *
 * @author Guilherme Gehling
 */
@Stateless
public class MovimentoDAO implements MovimentoDAORemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Movimento create(Movimento movimento) {
        if (this.valida(movimento)) {
            em.persist(movimento);
            return movimento;
        } else {
            return null;
        }
    }

    @Override
    public Movimento retrive(Movimento movimento) {
        Movimento retorno = em.find(Movimento.class, movimento.getId());
        return retorno;
    }

    @Override
    public void update(Movimento movimento) {
        if (this.valida(movimento)) {
            em.merge(movimento);
        }
    }

    @Override
    public void delete(Movimento movimento) {
        movimento = this.retrive(movimento);
        em.remove(movimento);
    }

    @Override
    public List<Movimento> listaTodos() {
        return (List<Movimento>) em.createNamedQuery("Movimento.achaTODOS").getResultList();
    }

    @Override
    public boolean valida(Movimento movimento) {
        boolean ret = false;
        if (movimento != null) {
            ret = true;
        }
        return ret;
    }
}
