package app.projeto;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import app.servico.ServicoWebClient;

public class PersonalTraining extends Activity {

    private Handler manipulador = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            envia();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClickBtEnviar(View v) throws InterruptedException {
        Thread t = new Thread(new ConexaoWWW());
        t.start();
    }

    public void envia() {
    }

    private class ConexaoWWW implements Runnable {

        private ServicoWebClient servicoWeb;

        public void run() {
            try {
                ((TextView) findViewById(R.id.txtEnviar)).setText(servicoWeb.GetJASON());
            } catch (Exception ex) {
                ((TextView) findViewById(R.id.txtEnviar)).setText("Nada");
            }
        }
    }
}
