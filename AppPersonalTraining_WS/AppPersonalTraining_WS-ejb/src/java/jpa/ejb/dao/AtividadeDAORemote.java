/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.ejb.dao;

import java.util.List;
import javax.ejb.Remote;
import jpa.bean.Atividade;

/**
 *
 * @author Guilherme Gehling
 */
@Remote
public interface AtividadeDAORemote {

    Atividade create(Atividade atividade);

    Atividade retrive(Atividade atividade);

    void update(Atividade atividade);

    void delete(Atividade atividade);

    boolean valida(Atividade atividade);

    List<Atividade> listaTodos();
}
