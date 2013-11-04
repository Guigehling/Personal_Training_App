/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.ejb.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.bean.Posicao;

/**
 *
 * @author Guilherme Gehling
 */
@Stateless
public class PosicaoDAO implements PosicaoDAORemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Posicao create(Posicao posicao) {
        if (this.valida(posicao)) {
            em.persist(posicao);
            return posicao;
        } else {
            return null;
        }
    }

    @Override
    public Posicao retrive(Posicao posicao) {
        Posicao retorno = em.find(Posicao.class, posicao.getId());
        return retorno;
    }

    @Override
    public void update(Posicao posicao) {
        if (this.valida(posicao)) {
            em.merge(posicao);
        }
    }

    @Override
    public void delete(Posicao posicao) {
        posicao = this.retrive(posicao);
        em.remove(posicao);
    }

    @Override
    public List<Posicao> listaTodos() {
        return (List<Posicao>) em.createNamedQuery("Posicao.achaTODOS").getResultList();
    }

    @Override
    public Posicao achaUltimoAcesso() {
        return (Posicao) em.createNamedQuery("Posicao.achaUltimaPosicao").getResultList();
    }

    @Override
    public boolean valida(Posicao posicao) {
        boolean ret = false;
        if (posicao != null) {
            ret = true;
        }
        return ret;
    }
}