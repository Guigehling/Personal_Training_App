/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.ejb.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import jpa.bean.Atividade;
import jpa.bean.Usuario;

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
    public List<Atividade> achaTodos() {
        return (List<Atividade>) em.createNamedQuery("Atividade.achaTODOS").getResultList();
    }

    @Override
    public List<Atividade> achaPorUsuario(Usuario usuario) {
        Query query = em.createNamedQuery("Atividade.achaPorUsuario");
        query.setParameter("usuario", usuario.getId());
        List<Atividade> atividades = query.getResultList();
        return atividades;
    }

    @Override
    public Atividade achaEmExecucao(Usuario usuario) {
        Query query = em.createNamedQuery("Atividade.achaEmExecucao");
        query.setParameter("usuario", usuario.getId());
        Atividade atividade = new Atividade();
        if (query.getResultList().size() > 0) {
            atividade = (Atividade) query.getSingleResult();
        } else {
            create(atividade);
        }
        return atividade;
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
