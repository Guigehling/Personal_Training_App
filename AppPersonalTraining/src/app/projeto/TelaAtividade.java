/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.projeto;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import app.auxiliares.ServicoException;
import app.bean.Atividade;
import app.bean.Posicao;
import app.bean.Usuario;
import app.dao.UsuarioDAO;
import app.servico.ServicoWebClient;
import java.sql.Time;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme Gehling
 */
public class TelaAtividade extends Activity {

    Posicao posicaoAtual = new Posicao();
    Atividade atividadeAtual = new Atividade();
    Usuario usuarioLogado = new Usuario();
    ServicoWebClient servico = new ServicoWebClient();
    Chronometer cronometro;
    ImageButton btStart, btPause, btStop;
    TextView txtDistancia, txtVelocidade;
    boolean cronometroPausado = false;
    long tempoQuandoParado = 0;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.telaatividade);
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        usuarioLogado = usuarioDAO.retrive();
        cronometro = (Chronometer) findViewById(R.id.txtcronometro);
        btStart = (ImageButton) findViewById(R.id.btstart);
        btPause = (ImageButton) findViewById(R.id.btpause);
        btStop = (ImageButton) findViewById(R.id.btstop);
        txtDistancia = (TextView) findViewById(R.id.txtdistancia);
        txtVelocidade = (TextView) findViewById(R.id.txtvelocidade);
        ativaGPS();
    }

    public void onClickBtStart(View v) {
        btStart.setVisibility(View.INVISIBLE);
        btPause.setVisibility(View.VISIBLE);
        iniciaCronometro();
        ativaGPS();
    }

    public void onClickBtpause(View v) {
        btStart.setVisibility(View.VISIBLE);
        btPause.setVisibility(View.INVISIBLE);
        pausaCronometro();
    }

    public void onClickBtStop(View v) {
        btStart.setVisibility(View.VISIBLE);
        btPause.setVisibility(View.INVISIBLE);
        finalizaCronometro();
    }

//    public void onClickBtEnviar(View v) throws InterruptedException, ServicoException {
//        posicaoAtual.setId(1);
//        // movimentoAtual = servico.retornaAtividade(posicaoAtual);
//        EditText txtEnviar = (EditText) findViewById(R.id.txtEnviar);
//        txtEnviar.setText((int) movimentoAtual.getDistancia());
//    }
//
    public void ativaGPS() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                String msg = String.format("Latitude: [%9.6f] Longitude: [%9.6f]", location.getLatitude(), location.getLongitude());
                Log.w("PersonalTraining", msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                posicaoAtual.setLatitude(String.format("%9.6f", location.getLatitude()));
                posicaoAtual.setLongitude(String.format("%9.6f", location.getLongitude()));
                posicaoAtual.setDia(new Date());
                posicaoAtual.setHora(new Time(new Date().getTime()));
                posicaoAtual.setUsuario_id(usuarioLogado.getId());
                atividadeAtual = servico.retornaAtividade(posicaoAtual);
                txtDistancia.setText(String.valueOf(atividadeAtual.getDistancia()));
                txtVelocidade.setText(String.valueOf(atividadeAtual.getVelocidade()));

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

    public void iniciaCronometro() {
        if (cronometroPausado) {
            cronometro.setBase(SystemClock.elapsedRealtime() + tempoQuandoParado);
            cronometro.start();
            tempoQuandoParado = 0;
            cronometroPausado = false;
        } else {
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.start();
            tempoQuandoParado = 0;
        }
    }

    public void pausaCronometro() {
        if (cronometroPausado == false) { //entra para false;
            tempoQuandoParado = cronometro.getBase() - SystemClock.elapsedRealtime();
        }
        cronometro.stop();
        cronometroPausado = true;
    }

    public void finalizaCronometro() {
        cronometro.stop();
        cronometro.setText("00:00");
        tempoQuandoParado = 0;
    }
}
