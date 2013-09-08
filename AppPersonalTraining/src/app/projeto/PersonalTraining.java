package app.projeto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import app.auxiliares.EntidadeAuxJASON;
import app.auxiliares.ServicoException;
import app.bean.Entidade;
import app.servico.ServicoWebClient;
import java.util.ArrayList;
import java.util.List;

public class PersonalTraining extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClickBtEnviar(View v) throws InterruptedException, ServicoException {
        List<Entidade> lista = buscaLista();
        ServicoWebClient servico = new ServicoWebClient();
        EntidadeAuxJASON auxiliar=servico.testPostJsonRetText(lista);
    }

    private List<Entidade> buscaLista() {
        List<Entidade> lista = new ArrayList<Entidade>();
        Entidade entidade = new Entidade();
        entidade.setDesc("Brasil X Japão");
        entidade.setId(1L);
        lista.add(entidade);
        entidade = new Entidade();
        entidade.setDesc("Brasil X México");
        entidade.setId(2L);
        lista.add(entidade);
        entidade = new Entidade();
        entidade.setDesc("Brasil X Itália");
        entidade.setId(3L);
        lista.add(entidade);
        return lista;
    }
}
