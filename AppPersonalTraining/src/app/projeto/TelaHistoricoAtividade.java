/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.projeto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import app.bean.Atividade;
import app.servico.ServicoWebClient;
import java.text.SimpleDateFormat;

/**
 *
 * @author Guilherme Gehling
 */
public class TelaHistoricoAtividade extends Activity {

    TextView txtDistancia, txtVelocidade, txtData, txtTempo;
    ServicoWebClient servico = new ServicoWebClient();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Atividade atividade = new Atividade();
        atividade.setId(bundle.getInt("id_atividade"));
        atividade = servico.buscaAtividade(atividade);
        setContentView(R.layout.telahistoricoatividade);
        txtDistancia = (TextView) findViewById(R.id.txtdistanciafinal);
        txtVelocidade = (TextView) findViewById(R.id.txtvelocidadefinal);
        txtData = (TextView) findViewById(R.id.txtdatafinal);
        txtTempo = (TextView) findViewById(R.id.txttempofinal);
        String a = String.valueOf(atividade.getDistancia());
        txtDistancia.setText(a + " m");
        txtVelocidade.setText(String.valueOf(atividade.getVelocidade()) + " m/s");
        if (atividade.getDia() != null) {
            SimpleDateFormat dataformatacao = new SimpleDateFormat("dd/MM/yyyy");
            String dataTexto = dataformatacao.format(atividade.getDia());
            txtData.setText(dataTexto);
        } else {
            txtData.setText("");
        }
        if (atividade.getTempo() != null) {
            SimpleDateFormat dataformatacao = new SimpleDateFormat("mm:ss");
            String dataHora = dataformatacao.format(atividade.getTempo());
            txtTempo.setText(dataHora);
        } else {
            txtTempo.setText("00:00");
        }
    }

    public void onClickBtMapa(View v) {
        Intent intent = new Intent(this, TelaMapa.class);
        startActivity(intent);
    }
}
