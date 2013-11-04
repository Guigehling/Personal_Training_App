package app.projeto;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import app.auxiliares.ServicoException;
import app.bean.Movimento;
import app.bean.Posicao;
import app.servico.ServicoWebClient;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme Gehling
 */
public class PersonalTraining extends Activity {

    Posicao posicaoAtual = new Posicao();
    Movimento movimentoAtual = new Movimento();
    ServicoWebClient servico = new ServicoWebClient();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ativaGPS();
    }

    public void onClickBtEnviar(View v) throws InterruptedException, ServicoException {
        posicaoAtual.setId(1);
        movimentoAtual = servico.postJsonRetDistancia(posicaoAtual);
        EditText txtEnviar = (EditText) findViewById(R.id.txtEnviar);
        txtEnviar.setText((int) movimentoAtual.getDistancia());
    }

    public void ativaGPS() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                String msg = String.format("Latitude: [%9.6f] Longitude: [%9.6f]", location.getLatitude(), location.getLongitude());
                Log.w("PersonalTraining", msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                posicaoAtual.setId(1);
                posicaoAtual.setLatitude(String.format("%9.6f", location.getLatitude()));
                posicaoAtual.setLongitude(String.format("%9.6f", location.getLongitude()));
//                posicaoAtual.setLatitudeChegada(String.format("%9.6f", location.getLatitude()));
//                posicaoAtual.setLongitudeChegada(String.format("%9.6f", location.getLongitude()));
                posicaoAtual.setDia(new Date());
                try {
                    movimentoAtual = servico.postJsonRetDistancia(posicaoAtual);
                } catch (ServicoException ex) {
                    Logger.getLogger(PersonalTraining.class.getName()).log(Level.SEVERE, null, ex);
                }
                EditText txtEnviar = (EditText) findViewById(R.id.txtEnviar);
                String distancia = String.valueOf(movimentoAtual.getDistancia());
                txtEnviar.setText(distancia);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                String msg = "onStatusChanged " + provider;
                switch (status) {
                    case LocationProvider.AVAILABLE:
                        msg += " GPS novamente disponível";
                        break;
                    case LocationProvider.OUT_OF_SERVICE:
                        msg += " GPS sem serviço";
                        break;
                    case LocationProvider.TEMPORARILY_UNAVAILABLE:
                        msg += " GPS temporariamente indisponível";
                        break;
                }
                Log.w("PersonalTraining", msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }

            public void onProviderEnabled(String provider) {
                String msg = "onProviderEnabled " + provider;
                Log.w("PersonalTraining", msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }

            public void onProviderDisabled(String provider) {
                String msg = "onProviderDisabled " + provider;
                Log.w("PersonalTraining", msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000, 0, locationListener);
    }
}