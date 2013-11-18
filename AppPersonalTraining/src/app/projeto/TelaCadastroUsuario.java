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
import app.auxiliares.UsuarioAux;
import app.bean.Usuario;
import app.dao.UsuarioDAO;
import app.servico.ServicoWebClient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme Gehling
 */
public class TelaCadastroUsuario extends Activity {

    EditText txtnome, txtemail, txtsenha, txtconfsenha, txtdtnasc, txtsexo, txtpeso;
    ServicoWebClient servico = new ServicoWebClient();

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.telacadastrousuario);
    }

    public void onClickbtSalvar(View v) {
//        txtnome = (EditText) findViewById(R.id.txtnome);
//        txtemail = (EditText) findViewById(R.id.txtemail);
//        txtsenha = (EditText) findViewById(R.id.txtsenha);
//        txtconfsenha = (EditText) findViewById(R.id.txtconfsenha);
//        txtdtnasc = (EditText) findViewById(R.id.txtdtnascimento);
        String dia = "04/01/1993";//txtdtnasc.getText().toString();
        SimpleDateFormat dataformatacao = new SimpleDateFormat("dd/MM/yyyy");
        Date dtNascimento = null;
        try {
            dtNascimento = (Date) dataformatacao.parse(dia);
        } catch (ParseException ex) {
            Logger.getLogger(UsuarioAux.class.getName()).log(Level.SEVERE, null, ex);
        }
//        txtsexo = (EditText) findViewById(R.id.txtsexo);
//        txtpeso = (EditText) findViewById(R.id.txtpeso);
//        Double peso = Double.parseDouble(txtpeso.getText().toString());

//        Usuario usuarioCriado = new Usuario(0, txtnome.getText().toString(), txtemail.getText().toString(), txtsenha.getText().toString(), dtNascimento, txtsexo.getText().toString(), peso);
        Usuario usuarioCriado = new Usuario(0, "g", "g", "g", dtNascimento, "g", 10);
        usuarioCriado = servico.criaUsuario(usuarioCriado);

        if (!"Não Cadastrado".equals(usuarioCriado.getNome())) {
            UsuarioDAO usuarioDAO = new UsuarioDAO(this);
            usuarioDAO.create(usuarioCriado);
            new AlertDialog.Builder(this).setTitle("Aviso!!").setMessage("Bem Vindo ao Let's Rining!").setNeutralButton("OK", null).show();
            Intent intent = new Intent(this, TelaOpcoes.class);
            startActivity(intent);
        } else {
            new AlertDialog.Builder(this).setTitle("Aviso!!").setMessage("Usuario não encontrado!").setNeutralButton("OK", null).show();
        }
    }
}
