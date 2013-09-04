/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.servico;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 *
 * @author Guilherme Gehling
 */
public class ServicoWebClient {

    private WebResource webResource;
    private Client client;
    private static final String URL = "";

    public ServicoWebClient() {
        com.sun.jersey.api.client.config.ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(URL).path("servicoweb");
    }

    public String GetJASON() throws UniformInterfaceException {
        return webResource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public void close() {
        client.destroy();
    }
}
