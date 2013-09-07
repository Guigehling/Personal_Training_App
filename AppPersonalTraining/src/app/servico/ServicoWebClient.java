/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.servico;

import app.auxiliares.EntidadeAuxJASON;
import app.auxiliares.ServicoException;
import app.bean.Entidade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 *
 * @author Guilherme Gehling
 */
public class ServicoWebClient {

    private static final String URL = "http://10.0.0.103/TesteRestful/servico";

    public EntidadeAuxJASON testPostJsonRetText(List<Entidade> entidades) throws ServicoException {
        EntidadeAuxJASON aux = new EntidadeAuxJASON();
        aux.setEntidade(entidades);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(aux);
        String resp = new String(this.makeRequestPost(json.getBytes(), "/retjson"));
        return gson.fromJson(resp, EntidadeAuxJASON.class);
    }

    private byte[] makeRequestPost(byte[] params, String path) throws ServicoException {
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
}
