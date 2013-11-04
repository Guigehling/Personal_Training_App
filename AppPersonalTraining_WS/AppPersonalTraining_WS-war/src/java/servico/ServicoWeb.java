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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
import jpa.bean.Posicao;
import jpa.ejb.dao.PosicaoDAO;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author Guilherme Gehling
 */
@Path(value = "/servicoweb")
public class ServicoWeb {

    PosicaoDAO posicaoDAO = new PosicaoDAO();
    Movimento novoMovimento = new Movimento();
    Posicao ultimaPosicao = new Posicao();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testeGetText() {
        return "teste de endereco";
    }

    @POST
    @Path(value = "retjsondist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Movimento postMovimento(Posicao posicao) throws IOException {
        novoMovimento = null;
        ultimaPosicao = null;
        ultimaPosicao = posicaoDAO.achaUltimoAcesso();
        if (ultimaPosicao == null) {
            posicao.setUltimaPosicao(true);
            posicaoDAO.create(posicao);
            return novoMovimento;
        } else {
            novoMovimento.setPosicaoInicial(ultimaPosicao);
            novoMovimento.setPosicaoFinal(posicao);
            novoMovimento.setDistancia(obtemDistancia(novoMovimento));
            return novoMovimento;
        }
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

//        movimento.setLatitudePartida("-31.373022");
//        movimento.setLongitudePartida("-51.997503");
//        movimento.setLatitudeChegada("-31.373095");
//        movimento.setLongitudeChegada("-51.997466");
        //-31.373022,-51.997503
        //-31.373095,-51.997466

        String urlString = "http://maps.googleapis.com/maps/api/directions/xml?origin=" + movimento.getPosicaoInicial().getLatitude() + "," + movimento.getPosicaoInicial().getLongitude() + ""
                + "&destination=" + movimento.getPosicaoFinal().getLatitude() + "," + movimento.getPosicaoFinal().getLongitude() + "&sensor=false";
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

    private PosicaoDAO lookupPosicaoDAOBean() {
        try {
            Context c = new InitialContext();
            return (PosicaoDAO) c.lookup("java:global/AppPersonalTraining_WS/AppPersonalTraining_WS-war/PosicaoDAO!jpa.ejb.dao.PosicaoDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private PosicaoDAO lookupPosicaoDAOBean1() {
        try {
            Context c = new InitialContext();
            return (PosicaoDAO) c.lookup("java:global/AppPersonalTraining_WS/AppPersonalTraining_WS-war/PosicaoDAO!jpa.ejb.dao.PosicaoDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
