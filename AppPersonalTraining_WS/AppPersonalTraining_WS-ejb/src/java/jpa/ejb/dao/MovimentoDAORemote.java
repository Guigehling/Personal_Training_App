/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.ejb.dao;

import java.util.List;
import javax.ejb.Remote;
import jpa.bean.Movimento;

/**
 *
 * @author Guilherme Gehling
 */
@Remote
public interface MovimentoDAORemote {

    Movimento create(Movimento movimento);

    Movimento retrive(Movimento movimento);

    void update(Movimento movimento);

    void delete(Movimento movimento);

    List<Movimento> listaTodos();

    boolean valida(Movimento movimento);
}
