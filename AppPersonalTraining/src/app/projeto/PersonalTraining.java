package app.projeto;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

    LatLong latLong = new LatLong();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ativaGPS();
    }

    public void onClickBtEnviar(View v) throws InterruptedException, ServicoException {
        latLong.setId(1L);
        ServicoWebClient servico = new ServicoWebClient();
        latLong = servico.postJsonRetDistancia(latLong);
        EditText txtEnviar = (EditText) findViewById(R.id.txtEnviar);
        txtEnviar.setText(latLong.getDistancia());
    }

    public void ativaGPS() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                String msg = String.format("Latitude: [%9.6f] Longitude: [%9.6f]", location.getLatitude(), location.getLongitude());
                latLong.setLatitude_inicial(String.format("[%9.6f]", location.getLatitude()));
                latLong.setLongitude_inicial(String.format("[%9.6f]", location.getLongitude()));
                latLong.setLatitude_final(String.format("[%9.6f]", location.getLatitude()));
                latLong.setLongitude_final(String.format("[%9.6f]", location.getLongitude()));
            }

            public String getLatitude(Location location) {
                String latitude = String.format("[%9.6f]", location.getLatitude());
                return latitude;
            }

            public String getLongitude(Location location) {
                String longitude = String.format("[%9.6f]", location.getLongitude());
                return longitude;
            }

            public void onStatusChanged(String string, int i, Bundle bundle) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            public void onProviderEnabled(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            public void onProviderDisabled(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 3000, 0, locationListener);
    }
}