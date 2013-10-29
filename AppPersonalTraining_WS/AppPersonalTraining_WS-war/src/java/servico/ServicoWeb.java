/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
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
import jpa.bean.Movimento;
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
    public Movimento postMovimento(Movimento movimento) throws IOException {
        movimento.setDistancia(obtemDistancia(movimento));
        return movimento;
    }

    /**
     * Envia as coordenadas de posicionamento do usuario a API do Google, para
     * obter a distancia percorrida.
     *
     * @param Movimento Posicao atual do usuario
     * @param PosicaoAnterior Ultima posicao valida do usuario.
     * @return Distancia percorrida pelo usuario.
     */
    public double obtemDistancia(Movimento movimento) {
        String distanciaXML = "";
        double distancia = 0;
        movimento.setLatitudePartida("-31.373022");
        movimento.setLongitudePartida("-51.997503");
        movimento.setLatitudeChegada("-31.373095");
        movimento.setLongitudeChegada("-51.997466");
        //-31.373022,-51.997503
        //-31.373095,-51.997466
        String urlString = "http://maps.googleapis.com/maps/api/directions/xml?origin=" + movimento.getLatitudePartida() + "," + movimento.getLongitudePartida() + "&destination=" + movimento.getLatitudeChegada() + "," + movimento.getLongitudeChegada() + "&sensor=false";
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
                distanciaXML = nodeList.item(i).getFirstChild().getNodeValue();
            }

            urlGoogleDirCon.disconnect();
            distancia = Double.parseDouble(distanciaXML);
            return distancia;
        } catch (Exception e) {
            System.out.println(e);
            return distancia;
        }
    }
}
