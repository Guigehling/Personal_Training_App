/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.ejb.dao;

import java.util.List;
import javax.ejb.Remote;
import jpa.bean.Posicao;

/**
 *
 * @author Guilherme Gehling
 */
@Remote
public interface PosicaoDAORemote {

    Posicao create(Posicao posicao);

    Posicao retrive(Posicao posicao);

    void update(Posicao posicao);

    void delete(Posicao posicao);

    boolean valida(Posicao posicao);

    List<Posicao> listaTodos();

    Posicao achaUltimoAcesso();
}
