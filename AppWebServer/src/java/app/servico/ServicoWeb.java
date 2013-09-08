/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.servico;

import app.bean.Entidade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Guilherme Gehling
 */
@Path(value = "/servicoweb")
public class ServicoWeb {

    //invocar http://localhost:8080/AppWebServer/servico/servicoweb
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testeGetText() {
        return "teste do get TEXTO";
    }

    @POST
    @Path(value = "retjson")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List< Entidade> testPostJsonRetText(List< Entidade> lista) {
        StringBuilder sb = new StringBuilder("Registros recebidos --------- \n\n");
        for (Entidade entidade : lista) {
            entidade.setResultado("3 x 1");
            String resp = String.format("Entidade [%s]", entidade.getDescricao());
            sb.append(resp);
            sb.append("\n");
            Logger.getLogger(ServicoWeb.class.getName()).log(Level.SEVERE, resp);
        }
        return lista;
    }
}
