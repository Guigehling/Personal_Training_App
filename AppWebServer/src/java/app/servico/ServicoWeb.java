/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.servico;

import app.bean.ObjTeste;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Guilherme Gehling
 */
@Path(value = "/servicoweb")
public class ServicoWeb {

    // http://localhost:8080/TesteRestful/servico/servicoweb
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String testeJSON(){
        return "Funcionou";
    }
}
