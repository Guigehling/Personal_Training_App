/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import app.bd.BancoDados;
import app.bean.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme Gehling
 */
public class UsuarioDAO {

    private Context context;

    public UsuarioDAO(Context context) {
        this.context = context;
    }

    public void create(Usuario usuario) {
        BancoDados bd = new BancoDados(context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("id", usuario.getId());
        valores.put("nome", usuario.getNome());
        valores.put("email", usuario.getEmail());
        valores.put("senha", usuario.getSenha());
        SimpleDateFormat dataformatacao = new SimpleDateFormat("dd/MM/yyyy");
        String dataformatada = dataformatacao.format(usuario.getDataNascimento());
        valores.put("dataNascimento", dataformatada);
        valores.put("sexo", usuario.getSexo());
        valores.put("peso", usuario.getPeso());
        conn.insert("usuario", null, valores);
        conn.close();
    }

    public Usuario retrive() {
        Usuario usr = null;
        BancoDados bd = new BancoDados(context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        Cursor cursor = conn.rawQuery("SELECT * FROM usuario", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNome(cursor.getString(1));
            usuario.setEmail(cursor.getString(2));
            usuario.setSenha(cursor.getString(3));
            String dia = cursor.getString(4);
            SimpleDateFormat dataformatacao = new SimpleDateFormat("dd/MM/yyyy");
            Date dataformatada = null;
            try {
                dataformatada = (Date) dataformatacao.parse(dia);
            } catch (ParseException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            usuario.setDataNascimento(dataformatada);
            usuario.setSexo(cursor.getString(5));
            usuario.setPeso(cursor.getDouble(6));
            usr = usuario;
            cursor.moveToNext();
        }
        conn.close();
        return usr;
    }

    public void update(Usuario usuario) {
        BancoDados bd = new BancoDados(context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        ContentValues valores = new ContentValues();
        String strFilter = "id=" + 1;
        valores.put("id", 1);
        valores.put("nome", usuario.getNome());
        valores.put("email", usuario.getEmail());
        valores.put("senha", usuario.getSenha());
        SimpleDateFormat dataformatacao = new SimpleDateFormat("dd/MM/yyyy");
        String dataformatada = dataformatacao.format(usuario.getDataNascimento());
        valores.put("dataNascimento", dataformatada);
        valores.put("sexo", usuario.getSexo());
        valores.put("peso", usuario.getPeso());
        conn.update("usuario", valores, null, null);
        conn.close();
    }

    public void delete() {
        BancoDados bd = new BancoDados(context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        conn.delete("usuario", null, null);
    }

    public List<Usuario> listContact(Usuario logado)  {
        List<Usuario> lista = new ArrayList<Usuario>();
        BancoDados bd = new BancoDados(context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        Cursor cursor = conn.rawQuery("SELECT * FROM usuario where id <> " + logado.getId(), null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNome(cursor.getString(1));
            usuario.setEmail(cursor.getString(2));
            usuario.setSenha(cursor.getString(3));
            String dia = cursor.getString(4);
            SimpleDateFormat dataformatacao = new SimpleDateFormat("dd/MM/yyyy");
            Date dataformatada = null;
            try {
                dataformatada = (Date) dataformatacao.parse(dia);
            } catch (ParseException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            usuario.setDataNascimento(dataformatada);
            usuario.setSexo(cursor.getString(5));
            usuario.setPeso(cursor.getDouble(6));
            lista.add(usuario);
            cursor.moveToNext();
        }
        conn.close();
        return lista;
    }
}
