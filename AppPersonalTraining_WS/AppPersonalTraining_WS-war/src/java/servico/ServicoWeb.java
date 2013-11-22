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
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import jpa.bean.Atividade;
import jpa.bean.Movimento;
import jpa.bean.Usuario;
import jpa.ejb.dao.AtividadeDAORemote;
import jpa.ejb.dao.UsuarioDAORemote;
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
import jpa.auxiliar.AtividadeAux;
import jpa.auxiliar.PosicaoAux;
import jpa.auxiliar.UsuarioAux;
import jpa.bean.Posicao;
import jpa.ejb.dao.PosicaoDAORemote;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author Guilherme Gehling
 */
@Path(value = "/servicoweb")
@RequestScoped
public class ServicoWeb {

    @EJB
    UsuarioDAORemote usuarioDAORemote;
    @EJB
    AtividadeDAORemote atividadeDAORemote;
    @EJB
    PosicaoDAORemote posicaoDAORemote;

    @GET
    @Path(value = "teste")
    @Produces(MediaType.TEXT_PLAIN)
    public String testeGetText() {
        return "teste de endereco";
    }

    //Controle dos Usuario
    @POST
    @Path(value = "retnovousuario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioAux postNovoUsuario(UsuarioAux usuarioAux) throws IOException {
        Usuario usuario = usuarioAux.converteParaUsuario();
        usuario = usuarioDAORemote.achaUsuarioPorEmail(usuario);
        if ("Não Cadastrado".equals(usuario.getNome())) {
            usuario = usuarioAux.converteParaUsuario();
            usuarioDAORemote.create(usuario);
            usuario = usuarioDAORemote.achaUsuarioPorEmail(usuario);
        }
        usuarioAux.converteParaUsuarioAux(usuario);
        return usuarioAux;
    }

    @POST
    @Path(value = "retusuario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioAux postUsuario(UsuarioAux usuarioAux) throws IOException {
        Usuario usuario = usuarioAux.converteParaUsuario();
        usuario = usuarioDAORemote.achaUsuarioPorEmail(usuario);
        usuarioAux.converteParaUsuarioAux(usuario);
        return usuarioAux;
    }

    //Controle das Atividades
    @POST
    @Path(value = "novaatividade")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AtividadeAux criaAtividade(AtividadeAux atividadeAux) {
        Atividade atividade = atividadeAux.converteParaAtividade(usuarioDAORemote);
        atividade = atividadeDAORemote.create(atividade);
        atividadeAux.converteParaAtividadeAux(atividade);
        return atividadeAux;
    }

    @POST
    @Path(value = "atualizaatividade")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AtividadeAux atualizaAtividade(AtividadeAux atividadeAux) {
        Atividade atividadeAtualizada = new Atividade();
        Atividade atividade = atividadeAux.converteParaAtividade(usuarioDAORemote);
        atividadeAtualizada = atividadeDAORemote.retrive(atividade);
        atividadeAtualizada.setTempo(atividade.getTempo());
        //Realizar aqui o calculo de velocidade
        atividadeDAORemote.update(atividadeAtualizada);
        atividade = atividadeDAORemote.retrive(atividadeAtualizada);
        atividadeAux.converteParaAtividadeAux(atividade);
        return atividadeAux;
    }

    @POST
    @Path(value = "novaposicao")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PosicaoAux novaposicao(PosicaoAux posicaoAux) {
        Posicao posicao = new Posicao();
        posicao = posicaoAux.converteParaPosicao(usuarioDAORemote, atividadeDAORemote);
        Posicao ultimaPosicao = posicaoDAORemote.achaUltimaPosicao(posicao);
        if (ultimaPosicao == null) {
            posicao = posicaoDAORemote.create(posicao);
        } else {
            ultimaPosicao.setUltimaPosicao(false);
            posicaoDAORemote.update(ultimaPosicao);
            posicao = posicaoDAORemote.create(posicao);
            double distancia = calculaDistancia(ultimaPosicao, posicao);
            Atividade atividade = atividadeDAORemote.achaEmExecucao(posicao.getUsuario());
            atividade.setDistancia(atividade.getDistancia() + distancia);
            atividadeDAORemote.update(atividade);
        }
        posicaoAux.converteParaPosicaoAux(posicao);
        return posicaoAux;
    }

    @POST
    @Path(value = "retatividade")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AtividadeAux postMovimento(PosicaoAux posicaoAux) throws IOException {
        Posicao posicao = new Posicao();
        Atividade atividade = new Atividade();
        posicao = posicaoAux.converteParaPosicao(usuarioDAORemote, atividadeDAORemote);
        if (posicao.getAtividade() == null) {
            atividade = atividadeDAORemote.create(posicao.getAtividade());
            posicao.setAtividade(atividade);
        }


        AtividadeAux atividadeAux = new AtividadeAux();
//        atividade = null;
//        ultimaPosicao = null;
//        ultimaPosicao = posicaoDAO.achaUltimoAcesso();
//        if (ultimaPosicao == null) {
//            posicao.setUltimaPosicao(true);
//            posicaoDAO.create(posicao);
//            return atividade;
//        } else {
//            List<Posicao> posicaos = new ArrayList<Posicao>();
//
//            return null;
//        }
        return atividadeAux;
    }

    public double calculaDistancia(Posicao partida, Posicao chegada) {
        String distanciaXML = "";
        double distancia = 0;

        String urlString = "http://maps.googleapis.com/maps/api/directions/xml?origin=" + partida.getLatitude() + "," + partida.getLongitude() + ""
                + "&destination=" + chegada.getLatitude() + "," + chegada.getLongitude() + "&sensor=false";

//        String urlString = "http://maps.googleapis.com/maps/api/directions/xml?origin=-31.373022,-51.997503&destination=-31.373095,-51.997466&sensor=false";
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

//        String urlString = "http://maps.googleapis.com/maps/api/directions/xml?origin=" + movimento.getPosicaoInicial().getLatitude() + "," + movimento.getPosicaoInicial().getLongitude() + ""
//                + "&destination=" + movimento.getPosicaoFinal().getLatitude() + "," + movimento.getPosicaoFinal().getLongitude() + "&sensor=false";

        String urlString = "http://maps.googleapis.com/maps/api/directions/xml?origin=-31.373022,-51.997503&destination=-31.373095,-51.997466&sensor=false";
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
