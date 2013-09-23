/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.servico;

import app.bean.Entidade;
import app.bean.LatLong;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import net.sf.json.JSONObject;
import org.w3c.dom.Document;

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

    @POST
    @Path(value = "retjsondist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LatLong postLatLong(LatLong latlong) throws IOException {
        StringBuilder sb = new StringBuilder("Registros Recebidos ------- \n\n");
        conexaoGoogle();
        contGoogle();
        ConexaoJSon();
        latlong.setDistancia("12345");
        //String resp = String.format("LatLong [%s]", latlong.getLatitude_inicial());
        //sb.append(resp);
        //sb.append("\n");
        //Logger.getLogger(ServicoWeb.class.getName()).log(Level.SEVERE, resp);
        return latlong;
    }

    //Faz a conexão com o Google
    public String contGoogle() {
        String distancia = "";
        String urlString = "http://maps.googleapis.com/maps/api/directions/json?sensor=false&origin=-31.37392-51.997007&destination=-31.375294-51.955765&sensor=false";
        //http://maps.googleapis.com/maps/api/directions/json?sensor=false&origin=-31.37392-51.997007&destination=-31.375294-51.955765&sensor=false
        System.out.println(urlString);

        try {
            URL urlGoogleDirService = new URL(urlString);

            HttpURLConnection urlGoogleDirCon = (HttpURLConnection) urlGoogleDirService.openConnection();

            urlGoogleDirCon.setAllowUserInteraction(false);
            urlGoogleDirCon.setDoInput(true);
            urlGoogleDirCon.setDoOutput(false);
            urlGoogleDirCon.setUseCaches(true);
            urlGoogleDirCon.setRequestMethod("GET");
            urlGoogleDirCon.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(urlGoogleDirCon.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();

            DocumentBuilderFactory factoryDir = DocumentBuilderFactory.newInstance();
            DocumentBuilder parserDirInfo = factoryDir.newDocumentBuilder();
            Document docDir = parserDirInfo.parse(urlGoogleDirCon.getInputStream());

            urlGoogleDirCon.disconnect();
            distancia = docDir.toString();
            return distancia;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private String ConexaoJSon() throws IOException {
        URL url = new URL("http://maps.googleapis.com/maps/api/directions/json?sensor=false&origin=-31.37392-51.997007&destination=-31.375294-51.955765&sensor=false");
        URLConnection connection = url.openConnection();
        connection.addRequestProperty("Referer", "http://localhost:8080/AppWebServer/");

        String line;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((line = reader.readLine()) != null) {
            builder.append(line);
            System.out.println(line);
        }

        JSONObject json = new JSONObject();
        return null;
    }

    private String conexaoGoogle() {
        try {
            URL url = new URL("http://br.yahoo.com/");
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            urlconnection.disconnect();
        } catch (MalformedURLException ex) {
            System.out.println("Erro ao criar URL. Formato inválido.");
        } catch (IOException ex) {
            System.out.println("Erro ao acessar URL.");
        }

        return null;
    }
}