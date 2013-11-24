/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.servico;

import app.auxiliares.AtividadeAux;
import app.auxiliares.PosicaoAux;
import app.auxiliares.ServicoException;
import app.auxiliares.UsuarioAux;
import app.bean.Atividade;
import app.bean.Posicao;
import app.bean.Usuario;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme Gehling
 */
public class ServicoWebClient {

    private static final String URL = "http://10.0.0.101:8080/AppPersonalTraining_WS-war/servico/servicoweb";
    // private static final String URL = "http://10.13.2.199:8080/AppPersonalTraining_WS-war/servico/servicoweb";

    /**
     * Este metodo é o responsavel por solicitar ao Web Server a criação de uma
     * nova atividade
     *
     * @param Atividade
     * @return Atividade
     * @throws ServicoException
     */
    public Atividade criaAtividade(Atividade atividade) {
        AtividadeAux atividadeAux = new AtividadeAux();
        atividadeAux.converteParaAtividadeAux(atividade);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(atividadeAux);
        String resp = null;
        try {
            resp = new String(this.requisicaoWebServer(json.getBytes(), "/novaatividade"));
        } catch (ServicoException ex) {
            Logger.getLogger(ServicoWebClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        atividadeAux = gson.fromJson(resp, AtividadeAux.class);
        atividade = atividadeAux.converteParaAtividade();
        return atividade;
    }

    /**
     * Este metodo é o responsavel por solicitar ao Web Server a atualização das
     * informações da atividade em andamento
     *
     * @param Atividade
     * @return Atividade
     * @throws ServicoException
     */
    public Atividade atualizaAtividade(Atividade atividade) {
        AtividadeAux atividadeAux = new AtividadeAux();
        atividadeAux.converteParaAtividadeAux(atividade);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(atividadeAux);
        String resp = null;
        try {
            resp = new String(this.requisicaoWebServer(json.getBytes(), "/atualizaatividade"));
        } catch (ServicoException ex) {
            Logger.getLogger(ServicoWebClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        atividadeAux = gson.fromJson(resp, AtividadeAux.class);
        atividade = atividadeAux.converteParaAtividade();
        return atividade;
    }

    /**
     * Este metodo é o responsavel por informar ao Web Server o ecerramento de
     * uma atividade.
     *
     * @param Atividade
     * @return Atividade
     * @throws ServicoException
     */
    public Atividade finalizaAtividade(Atividade atividade) {
        AtividadeAux atividadeAux = new AtividadeAux();
        atividadeAux.converteParaAtividadeAux(atividade);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(atividadeAux);
        String resp = null;
        try {
            resp = new String(this.requisicaoWebServer(json.getBytes(), "/finalizaatividade"));
        } catch (ServicoException ex) {
            Logger.getLogger(ServicoWebClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        atividadeAux = gson.fromJson(resp, AtividadeAux.class);
        atividade = atividadeAux.converteParaAtividade();
        return atividade;
    }

    /**
     * Este metodo é o responsavel por informar ao Web Server o ecerramento de
     * uma atividade.
     *
     * @param Atividade
     * @return Atividade
     * @throws ServicoException
     */
    public Atividade historicoAtividade(Usuario usuario) {
        Atividade atividadeModelo = new Atividade();
        atividadeModelo.setUsuario_id(usuario.getId());
        AtividadeAux atividadeAux = new AtividadeAux();
        atividadeAux.converteParaAtividadeAux(atividadeModelo);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(atividadeAux);
        String resp = null;
        try {
            resp = new String(this.requisicaoWebServer(json.getBytes(), "/historicoatividade"));
        } catch (ServicoException ex) {
            Logger.getLogger(ServicoWebClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        atividadeAux = gson.fromJson(resp, AtividadeAux.class);
        atividadeModelo = atividadeAux.converteParaAtividade();
        return atividadeModelo;
    }

    /**
     * Este metodo é o responsavel por solicitar ao Web Server uma lista das
     * atividades realizadas pelo usuario.
     *
     * @param Posicao
     * @return Posicao
     * @throws ServicoException
     */
    public Posicao novaPosicao(Posicao posicao) {
        PosicaoAux posicaoAux = new PosicaoAux();
        posicaoAux.converteParaPosicaoAux(posicao);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(posicaoAux);
        String resp = null;
        try {
            resp = new String(this.requisicaoWebServer(json.getBytes(), "/novaposicao"));
        } catch (ServicoException ex) {
            Logger.getLogger(ServicoWebClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        posicaoAux = gson.fromJson(resp, PosicaoAux.class);
        posicao = posicaoAux.converteParaPosicao();
        return posicao;
    }

//    /**
//     * Este metodo é o responsavel por solicitar a conexao com o web server e
//     * montar o arquivo a ser enviado.
//     *
//     * @param Posicao
//     * @return Movimento
//     * @throws ServicoException
//     */
//    public Atividade retornaAtividade(Posicao posicao) {
//        Atividade atividade = new Atividade();
//        AtividadeAux atividadeAux = new AtividadeAux();
//        PosicaoAux posicaoAux = new PosicaoAux();
//        posicaoAux.converteParaPosicaoAux(posicao);
//        Gson gson = new GsonBuilder().create();
//        String json = gson.toJson(posicaoAux);
//        String resp = null;
//        try {
//            resp = new String(this.requesicaoAtividade(json.getBytes(), "/retatividade"));
//        } catch (ServicoException ex) {
//            Logger.getLogger(ServicoWebClient.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        atividadeAux = gson.fromJson(resp, AtividadeAux.class);
//        atividade = atividadeAux.converteParaAtividade();
//        return atividade;
//    }
//    private byte[] requesicaoAtividade(byte[] params, String path) throws ServicoException {
//        try {
//            URL urlObj = new URL(ServicoWebClient.URL + path);
//            HttpURLConnection httpConn = (HttpURLConnection) urlObj.openConnection();
//            httpConn.setDoInput(true);
//            httpConn.setDoOutput(true);
//            httpConn.setUseCaches(false);
//            httpConn.setRequestMethod("POST");
//            httpConn.setRequestProperty("Content-Type", "application/json");
//            httpConn.setRequestProperty("Connection", "close");
//            OutputStream output = httpConn.getOutputStream();
//            output.write(params);
//            String msg = httpConn.getResponseMessage();
//            int code = httpConn.getResponseCode();
//            if (code == 200) {
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                InputStream in = httpConn.getInputStream();
//                for (int c = in.read(); c != -1; c = in.read()) {
//                    baos.write(c);
//                }
//                baos.close();
//                return baos.toByteArray();
//            } else {
//                throw new ServicoException(String.format("Falha na conexão http [%s], Retorno [%d] Mensagem [%s]",
//                        ServicoWebClient.URL + path, code, msg));
//            }
//        } catch (ServicoException ex) {
//            throw ex;
//        } catch (Exception ex) {
//            throw new ServicoException("Falha na conexão http", ex);
//        }
//    }
    /**
     * Este metodo é o responsavel por solicitar a conexao com o web server e
     * montar o arquivo a ser enviado.
     *
     * @param Usuario
     * @return Movimento
     * @throws ServicoException
     */
    public Usuario retonaUsuario(Usuario usuario) throws ServicoException {
        UsuarioAux usuarioAux = new UsuarioAux();
        usuarioAux.converteParaUsuarioAux(usuario);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(usuarioAux);
        String resp = new String(this.requisicaoWebServer(json.getBytes(), "/retusuario"));
        usuarioAux = gson.fromJson(resp, UsuarioAux.class);
        Usuario usuarioRetorno;
        usuarioRetorno = usuarioAux.converteParaUsuario();
        return usuarioRetorno;
    }

    public Usuario criaUsuario(Usuario usuario) {
        UsuarioAux usuarioAux = new UsuarioAux();
        usuarioAux.converteParaUsuarioAux(usuario);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(usuarioAux);
        String resp = null;
        try {
            resp = new String(this.requisicaoWebServer(json.getBytes(), "/retnovousuario"));
        } catch (ServicoException ex) {
            Logger.getLogger(ServicoWebClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        usuarioAux = gson.fromJson(resp, UsuarioAux.class);
        Usuario usuarioRetorno;
        usuarioRetorno = usuarioAux.converteParaUsuario();
        return usuarioRetorno;
    }

    private byte[] requisicaoWebServer(byte[] params, String path) throws ServicoException {
        try {
            URL urlObj = new URL(ServicoWebClient.URL + path);
            HttpURLConnection httpConn = (HttpURLConnection) urlObj.openConnection();
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            httpConn.setUseCaches(false);
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setRequestProperty("Connection", "close");
            OutputStream output = httpConn.getOutputStream();
            output.write(params);
            String msg = httpConn.getResponseMessage();
            int code = httpConn.getResponseCode();
            if (code == 200) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                InputStream in = httpConn.getInputStream();
                for (int c = in.read(); c != -1; c = in.read()) {
                    baos.write(c);
                }
                baos.close();
                return baos.toByteArray();
            } else {
                throw new ServicoException(String.format("Falha na conexão http [%s], Retorno [%d] Mensagem [%s]",
                        ServicoWebClient.URL + path, code, msg));
            }
        } catch (ServicoException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ServicoException("Falha na conexão http", ex);
        }
    }
//
//    private byte[] rquesicaoUsuario(byte[] params, String path) throws ServicoException {
//        try {
//            URL urlObj = new URL(ServicoWebClient.URL + path);
//            HttpURLConnection httpConn = (HttpURLConnection) urlObj.openConnection();
//            httpConn.setDoInput(true);
//            httpConn.setDoOutput(true);
//            httpConn.setUseCaches(false);
//            httpConn.setRequestMethod("POST");
//            httpConn.setRequestProperty("Content-Type", "application/json");
//            httpConn.setRequestProperty("Connection", "close");
//            OutputStream output = httpConn.getOutputStream();
//            output.write(params);
//            String msg = httpConn.getResponseMessage();
//            int code = httpConn.getResponseCode();
//            if (code == 200) {
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                InputStream in = httpConn.getInputStream();
//                for (int c = in.read(); c != -1; c = in.read()) {
//                    baos.write(c);
//                }
//                baos.close();
//                return baos.toByteArray();
//            } else {
//                throw new ServicoException(String.format("Falha na conexão http [%s], Retorno [%d] Mensagem [%s]",
//                        ServicoWebClient.URL + path, code, msg));
//            }
//        } catch (ServicoException ex) {
//            throw ex;
//        } catch (Exception ex) {
//            throw new ServicoException("Falha na conexão http", ex);
//        }
//    }
}