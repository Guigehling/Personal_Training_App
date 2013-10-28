/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.ejb.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.bean.Atividade;

/**
 *
 * @author Guilherme Gehling
 */
@Stateless
public class AtividadeDAO implements AtividadeDAORemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Atividade create(Atividade atividade) {
        if (this.valida(atividade)) {
            em.persist(atividade);
            return atividade;
        } else {
            return null;
        }
    }

    @Override
    public Atividade retrive(Atividade atividade) {
        Atividade atividadeRet = em.find(Atividade.class, atividade.getId());
        return atividadeRet;
    }

    @Override
    public void update(Atividade atividade) {
        if (this.valida(atividade)) {
            em.merge(atividade);
        }
    }

    @Override
    public void delete(Atividade atividade) {
        atividade = this.retrive(atividade);
        em.remove(atividade);
    }

    @Override
    public List<Atividade> listaTodos() {
        return (List<Atividade>) em.createNamedQuery("Atividade.achaTODOS").getResultList();
    }

    @Override
    public boolean valida(Atividade atividade) {
        boolean ret = false;
        if (atividade != null) {
            ret = true;
        }
        return ret;
    }
}
