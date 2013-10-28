/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import jpa.bean.Posicao;

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
    public Posicao postPosicao(Posicao posicao) throws IOException {

        return posicao;
    }
    
    
    /**
     * Envia as coordenadas de posicionamento do usuario a API do Google, para obter a distancia percorrida.
     *
     * @param posicaoAtual Posicao atual do usuario
     * @param PosicaoAnterior Ultima posicao valida do usuario.
     * @return Distancia percorrida pelo usuario.
     */

//    public String contGoogle(Posicao posicao) {
//        String distancia = "";
//        latLong.setLatitude_inicial("-31.373022");
//        latLong.setLongitude_inicial("-51.997503");
//        latLong.setLatitude_final("-31.373095");
//        latLong.setLongitude_final("-51.997466");
//        //-31.373022,-51.997503
//        //-31.373095,-51.997466
//        String urlString = "http://maps.googleapis.com/maps/api/directions/xml?origin=" + latLong.getLatitude_inicial() + "," + latLong.getLongitude_inicial() + "&destination=" + latLong.getLatitude_final() + "," + latLong.getLongitude_final() + "&sensor=false";
//        System.out.println(urlString);
//
//        try {
//            URL urlGoogleDirService = new URL(urlString);
//            HttpURLConnection urlGoogleDirCon = (HttpURLConnection) urlGoogleDirService.openConnection();
//            urlGoogleDirCon.setAllowUserInteraction(false);
//            urlGoogleDirCon.setDoInput(true);
//            urlGoogleDirCon.setDoOutput(false);
//            urlGoogleDirCon.setUseCaches(true);
//            urlGoogleDirCon.setRequestMethod("GET");
//            urlGoogleDirCon.connect();
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(urlGoogleDirCon.getInputStream()));
//            String t = in.readLine();
//            System.out.println(t);
//            String line = null;
//            String teste = "";
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//                teste = teste + line;
//            }
//
//            XPathFactory factory = XPathFactory.newInstance();
//            XPath xPath = factory.newXPath();
//            String espressao = "/DirectionsResponse/route/leg/distance/value";
//            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = null;
//            builder = builderFactory.newDocumentBuilder();
//            Document xmlDocument = builder.parse(new ByteArrayInputStream(teste.getBytes()));
//            NodeList nodeList = (NodeList) xPath.compile(espressao).evaluate(xmlDocument, XPathConstants.NODESET);
//            for (int i = 0; i < nodeList.getLength(); i++) {
//                System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
//                distancia = nodeList.item(i).getFirstChild().getNodeValue();
//            }
//
//            urlGoogleDirCon.disconnect();
//            return distancia;
//        } catch (Exception e) {
//            System.out.println(e);
//            return null;
//        }
//    }
}
