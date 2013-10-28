/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import jpa.bean.Posicao;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author Guilherme Gehling
 */
@Path(value = "/servicoweb")
public class ServicoWeb {

    @POST
    @Path(value = "retjsondist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LatLong postLatLong(LatLong latlong) throws IOException {
        StringBuilder sb = new StringBuilder("Registros Recebidos ------- \n\n");
        //contGoogle();
        //ConexaoJSon();
        latlong.setDistancia(contGoogle(latlong));
        //        conexaoGoogle();
        //String resp = String.format("LatLong [%s]", latlong.getLatitude_inicial());
        //sb.append(resp);
        //sb.append("\n");
        //Logger.getLogger(ServicoWeb.class.getName()).log(Level.SEVERE, resp);
        return latlong;
    }

    //Faz a conexão com o Google
    public String contGoogle(Posicao posicaoAnterior, Posicao posicaoAtual) {
        String distancia = "";
        String urlString = "http://maps.googleapis.com/maps/api/directions/xml?origin=" + posicaoAnterior.getLatitude() + "," + posicaoAnterior.getLongitude() + "&destination=" + posicaoAtual.getLatitude() + "," + posicaoAtual.getLongitude() + "&sensor=false";
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
            String t = in.readLine();
            System.out.println(t);
            String line = null;
            String teste = "";
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                teste = teste + line;
            }

            XPathFactory factory = XPathFactory.newInstance();
            XPath xPath = factory.newXPath();
            String espressao = "/DirectionsResponse/route/leg/distance/value";
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(new ByteArrayInputStream(teste.getBytes()));
            NodeList nodeList = (NodeList) xPath.compile(espressao).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
                distancia = nodeList.item(i).getFirstChild().getNodeValue();
            }

            urlGoogleDirCon.disconnect();
            return distancia;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
