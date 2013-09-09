package app.projeto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import app.auxiliares.ServicoException;
import app.bean.LatLong;
import app.servico.ServicoWebClient;

/**
 *
 * @author Guilherme Gehling
 */
public class PersonalTraining extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClickBtEnviar(View v) throws InterruptedException, ServicoException {
        LatLong latLong = new LatLong();
        latLong.setId(1L);
        latLong.setLatitude_inicial("1");
        latLong.setLongitude_inicial("2");
        latLong.setLatitude_final("3");
        latLong.setLongitude_final("4");
        ServicoWebClient servico = new ServicoWebClient();
        latLong = servico.postJsonRetDistancia(latLong);
        EditText txtEnviar = (EditText) findViewById(R.id.txtEnviar);
        txtEnviar.setText(latLong.getDistancia());
    }
}
