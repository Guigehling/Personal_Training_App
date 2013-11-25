/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.projeto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import app.auxiliares.ItemOpcao;
import app.bean.Atividade;
import app.bean.Usuario;
import app.dao.AtividadeDAO;
import app.dao.UsuarioDAO;
import app.servico.ServicoWebClient;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guilherme Gehling
 */
public class TelaHistorico extends Activity implements OnItemClickListener {

    private ListView listView;
    private Lista_opcao lista;
    List<Atividade> atividades = new ArrayList<Atividade>();
    private ArrayList<ItemOpcao> itens;
    ServicoWebClient servico = new ServicoWebClient();

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.telahistorico);

        listView = (ListView) findViewById(R.id.list);
        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(this);

        AtividadeDAO atividadeDAO = new AtividadeDAO(this);
        atividades = atividadeDAO.listAll();

        createListView();
    }

    public void onItemClick(AdapterView<?> av, View view, int i, long l) {
        ItemOpcao item = lista.getItem(i);
        Intent intent = new Intent(this, TelaHistoricoAtividade.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id_atividade", atividades.get(i).getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menutelahistorico, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btCarrega:
                atividades.clear();
                UsuarioDAO usuarioDAO = new UsuarioDAO(this);
                Usuario usuario = usuarioDAO.retrive();
                int qtd = servico.quantidadeAtividade(usuario);
                int id = 0;
                for (int i = 0; i < qtd; i++) {
                    Atividade atividade = new Atividade();
                    atividade.setId(id);
                    atividade.setUsuario_id(usuario.getId());
                    atividade = servico.carregaAtividade(atividade);
                    atividades.add(atividade);
                    id = atividade.getId();
                }
                createListView();
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void createListView() {
        itens = new ArrayList<ItemOpcao>();
        if (atividades.size() > 0) {
            for (int i = 0; i < atividades.size(); i++) {
                String atv = String.valueOf(atividades.get(i).getId());
                ItemOpcao item1 = new ItemOpcao(atv);
                itens.add(item1);
            }
        } else {
            new AlertDialog.Builder(this).setTitle("Aviso!!").setMessage("Você não possui atividades em seu historico local!").show();
//            UsuarioDAO usuarioDAO = new UsuarioDAO(this);
//            Usuario usuario = usuarioDAO.retrive();
//            int qtd = servico.quantidadeAtividade(usuario);
//            int id = 0;
//            for (int i = 0; i < qtd; i++) {
//                Atividade atividade = new Atividade();
//                atividade.setId(id);
//                atividade.setUsuario_id(usuario.getId());
//                atividade = servico.carregaAtividade(atividade);
//                atividades.add(atividade);
//                id = atividade.getId();
//            }
//            createListView();
        }

        //Cria o adapter
        lista = new Lista_opcao(this, itens);

        //Define o Adapter
        listView.setAdapter(lista);
        //Cor quando a lista é selecionada para ralagem.
        listView.setCacheColorHint(Color.CYAN);
    }
}
