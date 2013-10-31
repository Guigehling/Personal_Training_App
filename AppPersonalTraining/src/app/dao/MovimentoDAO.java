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
import app.bean.Movimento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guilherme Gehling
 */
public class MovimentoDAO {

    private Context context;

    public MovimentoDAO(Context context) {
        this.context = context;
    }

    public void create(Movimento movimento) {
        BancoDados bd = new BancoDados(this.context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("id", movimento.getId());
        valores.put("conteudo", movimento.getDistancia());
        valores.put("destinatario", movimento.getVelocidade());
        conn.insert("enviadas", null, valores);
        conn.close();
    }

    public List<Movimento> listAll() {
        List<Movimento> lista = new ArrayList<Movimento>();
        BancoDados bd = new BancoDados(context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        Cursor cursor = conn.rawQuery("SELECT * FROM enviadas", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Movimento mensagem = new Movimento();
            mensagem.setId(cursor.getInt(0));
            mensagem.setDistancia(cursor.getDouble(1));
            mensagem.setVelocidade(cursor.getDouble(2));
            lista.add(mensagem);
            cursor.moveToNext();
        }
        conn.close();
        return lista;
    }
}
