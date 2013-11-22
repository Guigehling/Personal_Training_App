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
import jpa.bean.Posicao;
import jpa.bean.Usuario;

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
    public List<Posicao> achaTodos() {
        return (List<Posicao>) em.createNamedQuery("Posicao.achaTODOS").getResultList();
    }

    @Override
    public List<Posicao> achaPorAtividade(Atividade atividade) {
        Query query = em.createNamedQuery("Posicao.achaPorAtividade");
        query.setParameter("atividade", atividade.getId());
        List<Posicao> posicaos = query.getResultList();
        return posicaos;
    }

    @Override
    public List<Posicao> achaPorUsuario(Usuario usuario) {
        Query query = em.createNamedQuery("Posicao.achaPorusuario");
        query.setParameter("usuario", usuario.getId());
        List<Posicao> posicaos = query.getResultList();
        return posicaos;
    }

    @Override
    public Posicao achaUltimaPosicao(Posicao posicao) {
        Query query = em.createNamedQuery("Posicao.achaUltimaPosicao");
        query.setParameter("atividade", posicao.getAtividade());
        Posicao posicaoRet = new Posicao();
        if (query.getResultList().size() > 0) {
            posicaoRet = (Posicao) query.getSingleResult();
        } else {
            posicaoRet = null;
        }
        return posicaoRet;
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