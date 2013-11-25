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
import app.dao.UsuarioDAO;
import app.servico.ServicoWebClient;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        Usuario usuario = usuarioDAO.retrive();
        if (usuario != null) {
            try {
                Usuario usuarioconfirmado = servico.retonaUsuario(usuario);
                if (usuarioconfirmado.equals(usuario)) {
                    EditText txtEmail = (EditText) findViewById(R.id.txtEmailLogin);
                    EditText txtSenha = (EditText) findViewById(R.id.txtSenhaLogin);

                    txtEmail.setText(usuarioconfirmado.getEmail());
                    txtSenha.setText(usuarioconfirmado.getSenha());
                    Intent intent = new Intent(this, TelaOpcoes.class);
                    startActivity(intent);
                } else {
                    new AlertDialog.Builder(this).setTitle("Aviso!!").setMessage("Sem Usuarios cadastrados!").setNeutralButton("OK", null).show();
                }
            } catch (ServicoException ex) {
                Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void onClickbtLogin(View v) throws InterruptedException, ServicoException {
        EditText txtEmail = (EditText) findViewById(R.id.txtEmailLogin);
        EditText txtSenha = (EditText) findViewById(R.id.txtSenhaLogin);

        txtEmail.setText("g");
        txtSenha.setText("g");

        Usuario novousuario = new Usuario();
        novousuario.setEmail(txtEmail.getText().toString());
        novousuario.setSenha(txtSenha.getText().toString());
        Usuario usuarioconfirmado = servico.retonaUsuario(novousuario);
        if (!"Não Cadastrado".equals(usuarioconfirmado.getNome())) {
            UsuarioDAO usuarioDAO = new UsuarioDAO(this);
            usuarioDAO.create(usuarioconfirmado);
            Intent intent = new Intent(this, TelaOpcoes.class);
            startActivity(intent);
        } else {
            new AlertDialog.Builder(this).setTitle("Aviso!!").setMessage("Usuario não encontrado!").setNeutralButton("OK", null).show();
        }
    }

    public void onClickbtCadastro(View v) {
        Intent intent = new Intent(this, TelaCadastroUsuario.class);
        startActivity(intent);
    }
}