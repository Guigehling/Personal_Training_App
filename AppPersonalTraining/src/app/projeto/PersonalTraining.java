package app.projeto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PersonalTraining extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClickBtEnviar(View v) throws InterruptedException {
        Thread t = new Thread(new ConexaoWWW());
        t.start();
    }

    private class ConexaoWWW implements Runnable {

        public void run() {
            try {
                URL urlObj = new URL("http://192.168.0.100:8080/TrabMsgWeb/ServletMsg");
                HttpURLConnection httpConn = (HttpURLConnection) urlObj.openConnection();
                httpConn.setDoInput(true);
                httpConn.setDoOutput(true);
                httpConn.setUseCaches(false);
                httpConn.setRequestMethod("POST");
                httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                httpConn.setRequestProperty("Content-Language", "pt-BR");
                httpConn.setRequestProperty("Accept", "application/octet-stream");
                httpConn.setRequestProperty("Connection", "close");
            } catch (Exception ex) {
//                Display display = Display.getDisplay(mid);
//                Alert alert = new Alert("Informação");
//                alert.setString("Erro na conexão!");
//                display.setCurrent(alert);
                ex.printStackTrace();
            }
        }
    }
}
