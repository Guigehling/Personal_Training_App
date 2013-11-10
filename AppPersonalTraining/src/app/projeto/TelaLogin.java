/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.projeto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import app.auxiliares.ServicoException;
import app.bean.Usuario;
import app.servico.ServicoWebClient;

/**
 *
 * @author Guilherme Gehling
 */
public class TelaLogin extends Activity {

    ServicoWebClient servico = new ServicoWebClient();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.telalogin);
//        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
//        Usuario usuario = usuarioDAO.retrive();
//        if (usuario != null) {
//            Intent intent = new Intent(this, TelaAtividade.class);
//            startActivity(intent);
//        }
    }

    public void onClickbtLogin(View v) throws InterruptedException, ServicoException {
        EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
        EditText txtSenha = (EditText) findViewById(R.id.txtSenha);
        Usuario novousuario = new Usuario();
        novousuario.setId(1);
        novousuario.setNome("");
        novousuario.setEmail(txtEmail.getText().toString());
        novousuario.setSenha(txtSenha.getText().toString());
        Usuario usuarioconfirmado = new Usuario();
        usuarioconfirmado = servico.retonaUsuario(novousuario);
        if (usuarioconfirmado != null) {
            Intent intent = new Intent(this, TelaAtividade.class);
            startActivity(intent);
        } else {
            new AlertDialog.Builder(this).setTitle("Aviso!!").setMessage("Falha na Conexão!").setNeutralButton("OK", null).show();
        }
    }
}