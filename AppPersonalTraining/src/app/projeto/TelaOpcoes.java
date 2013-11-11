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
import app.dao.UsuarioDAO;
import java.util.ArrayList;

/**
 *
 * @author Guilherme Gehling
 */
public class TelaOpcoes extends Activity implements OnItemClickListener {

    private ListView listView;
    private Lista_opcao lista;
    private ArrayList<ItemOpcao> itens;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.telaopcoes);

        listView = (ListView) findViewById(R.id.list);
        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(this);

        createListView();
    }

    public void onItemClick(AdapterView<?> av, View view, int i, long l) {
        //Pega o item que foi selecionado.
        ItemOpcao item = lista.getItem(i);
        if ("Nova Atividade".equals(item.getAcao())) {
            Intent intent = new Intent(this, TelaAtividade.class);
            startActivity(intent);
        }
        if ("Historico".equals(item.getAcao())) {
//            Intent intent = new Intent(this, ListMsgRecebidas.class);
//            startActivity(intent);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menuopcao, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.btLimparTudo:
//                UsuarioDAO usr = new UsuarioDAO(this);
//                RecebidasDAO rec = new RecebidasDAO(this);
//                EnviadasDAO env = new EnviadasDAO(this);
//                rec.delete();
//                env.delete();
//                usr.delete();
//                new AlertDialog.Builder(this).setTitle("Aviso!!").setMessage("Seus Registros Foram Excluidos!").show();
//                Intent intent = new Intent(this, FrmLogin.class);
//                startActivity(intent);
//            default:
//                return super.onContextItemSelected(item);
//        }
//    }
    private void createListView() {
        //Criamos nossa lista que preenchera o ListView
        itens = new ArrayList<ItemOpcao>();
        ItemOpcao item1 = new ItemOpcao("Nova Atividade");
        ItemOpcao item2 = new ItemOpcao("Historico");

        itens.add(item1);
        itens.add(item2);

        //Cria o adapter
        lista = new Lista_opcao(this, itens);

        //Define o Adapter
        listView.setAdapter(lista);
        //Cor quando a lista é selecionada para ralagem.
        listView.setCacheColorHint(Color.CYAN);
    }
}
