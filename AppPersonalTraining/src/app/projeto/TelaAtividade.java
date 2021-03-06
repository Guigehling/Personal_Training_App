/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.projeto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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
import app.bean.Atividade;
import app.bean.Posicao;
import app.bean.Usuario;
import app.dao.AtividadeDAO;
import app.dao.UsuarioDAO;
import app.servico.ServicoWebClient;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme Gehling
 */
public class TelaAtividade extends Activity {

    //Posicao posicaoAtual = new Posicao();
    Atividade atividadeAtual = new Atividade();
    Usuario usuarioLogado = new Usuario();
    ServicoWebClient servico = new ServicoWebClient();
    Chronometer cronometro;
    ImageButton btStart, btPause, btStop;
    TextView txtDistancia, txtVelocidade;
    boolean cronometroPausado = false;
    boolean ativado = false;
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
    }

    public void onClickBtStart(View v) {
        btStart.setVisibility(View.INVISIBLE);
        btPause.setVisibility(View.VISIBLE);
        iniciaCronometro();
        atividadeAtual.setDia(new Date());
        Time datahora = null;
        DateFormat formato = new SimpleDateFormat("mm:ss");
        try {
            datahora = new java.sql.Time(formato.parse(cronometro.getText().toString()).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(TelaAtividade.class.getName()).log(Level.SEVERE, null, ex);
        }
        atividadeAtual.setTempo(datahora);
        atividadeAtual.setUsuario_id(usuarioLogado.getId());
        atividadeAtual.setConcluida(false);
        atividadeAtual = servico.criaAtividade(atividadeAtual);
        AtividadeDAO atividadeDAO = new AtividadeDAO(this);
        atividadeDAO.create(atividadeAtual);
        ativado = true;
        ativaGPS();
    }

    public void onClickBtpause(View v) {
        btStart.setVisibility(View.VISIBLE);
        btPause.setVisibility(View.INVISIBLE);
        ativado = false;
        pausaCronometro();
    }

    public void onClickBtStop(View v) {
        btStart.setVisibility(View.VISIBLE);
        btPause.setVisibility(View.INVISIBLE);
        txtDistancia.setText("0.0 m");
        txtVelocidade.setText("0.0 m/s");
        Time datahora = null;
        DateFormat formato = new SimpleDateFormat("mm:ss");
        try {
            datahora = new java.sql.Time(formato.parse(cronometro.getText().toString()).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(TelaAtividade.class.getName()).log(Level.SEVERE, null, ex);
        }
        atividadeAtual.setTempo(datahora);
        atividadeAtual = servico.finalizaAtividade(atividadeAtual);
        AtividadeDAO atividadeDAO = new AtividadeDAO(this);
        atividadeDAO.update(atividadeAtual);
        ativado = false;
        ativaGPS();
        finalizaCronometro();
        new AlertDialog.Builder(this).setTitle("Aviso!!").setMessage("Atividade Concluida!").show();
        Intent intent = new Intent(this, TelaOpcoes.class);
        startActivity(intent);
    }

    public void ativaGPS() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                if (ativado) {
                    String msg = String.format("Latitude: [%9.6f] Longitude: [%9.6f]", location.getLatitude(), location.getLongitude());
                    Posicao posicaoAtual = new Posicao();
                    posicaoAtual.setLatitude(String.format("%9.6f", location.getLatitude()));
                    posicaoAtual.setLongitude(String.format("%9.6f", location.getLongitude()));
                    posicaoAtual.setDia(new Date());
                    posicaoAtual.setHora(new Time(new Date().getTime()));
                    posicaoAtual.setUsuario_id(usuarioLogado.getId());
                    posicaoAtual.setAtividade_id(atividadeAtual.getId());
                    posicaoAtual.setUltimaPosicao(true);
                    posicaoAtual = servico.novaPosicao(posicaoAtual);
                    Time datahora = null;
                    DateFormat formato = new SimpleDateFormat("mm:ss");
                    try {
                        datahora = new java.sql.Time(formato.parse(cronometro.getText().toString()).getTime());
                    } catch (ParseException ex) {
                        Logger.getLogger(TelaAtividade.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    atividadeAtual.setTempo(datahora);
                    atividadeAtual = servico.atualizaAtividade(atividadeAtual);
                    String a = String.valueOf(atividadeAtual.getDistancia());
                    txtDistancia.setText(a + " m");
                    txtVelocidade.setText(String.valueOf(atividadeAtual.getVelocidade()) + " m/s");
                }
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
        if (cronometroPausado == false) {
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
