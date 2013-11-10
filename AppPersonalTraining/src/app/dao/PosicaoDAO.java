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
import app.bean.Posicao;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Guilherme Gehling
 */
public class PosicaoDAO {

    private Context context;

    public PosicaoDAO(Context context) {
        this.context = context;
    }

    public void create(Posicao posicao) {
        BancoDados bd = new BancoDados(context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("id", posicao.getId());
        valores.put("latitude", posicao.getLatitude());
        valores.put("longitude", posicao.getLongitude());
        SimpleDateFormat dataformatacao = new SimpleDateFormat("dd/MM/yyyy");
        String dataformatada = dataformatacao.format(posicao.getDia());
        valores.put("dia", dataformatada);
        SimpleDateFormat horaformatacao = new SimpleDateFormat("HH:mm");
        String horaformatada = horaformatacao.format(posicao.getHora());
        valores.put("hora", horaformatada);
        valores.put("usuario_id", posicao.getUsuario_id());
        valores.put("atividade_id", posicao.getAtividade_id());
        conn.insert("posicao", null, valores);
        conn.close();
    }

    public Posicao retrive() throws ParseException {
        Posicao posi = null;
        BancoDados bd = new BancoDados(context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        Cursor cursor = conn.rawQuery("SELECT * FROM posicao", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Posicao posicao = new Posicao();
            posicao.setId(cursor.getInt(0));
            posicao.setLatitude(cursor.getString(1));
            posicao.setLongitude(cursor.getString(2));
            String dia = cursor.getString(3);
            SimpleDateFormat dataformatacao = new SimpleDateFormat("dd/MM/yyyy");
            Date dataformatada = (Date) dataformatacao.parse(dia);
            posicao.setDia(dataformatada);
            String hora = cursor.getString(4);
            SimpleDateFormat horaformatacao = new SimpleDateFormat("HH:mm");
            Time horaformatada = (Time) horaformatacao.parse(hora);
            posicao.setHora(horaformatada);
            posicao.setUsuario_id(cursor.getInt(5));
            posicao.setAtividade_id(cursor.getInt(6));
            posi = posicao;
            cursor.moveToNext();
        }
        conn.close();
        return posi;
    }

    public void update(Posicao posicao) {
        BancoDados bd = new BancoDados(context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("id", posicao.getId());
        valores.put("latitude", posicao.getLatitude());
        valores.put("longitude", posicao.getLongitude());
        SimpleDateFormat dataformatacao = new SimpleDateFormat("dd/MM/yyyy");
        String dataformatada = dataformatacao.format(posicao.getDia());
        valores.put("dia", dataformatada);
        SimpleDateFormat horaformatacao = new SimpleDateFormat("HH:mm");
        String horaformatada = horaformatacao.format(posicao.getHora());
        valores.put("hora", horaformatada);
        valores.put("usuario_id", posicao.getUsuario_id());
        valores.put("atividade_id", posicao.getAtividade_id());
        conn.update("posicao", valores, null, null);
        conn.close();
    }

    public void delete() {
        BancoDados bd = new BancoDados(context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        conn.delete("posicao", null, null);
    }

    public List<Posicao> listAll() throws ParseException {
        List<Posicao> lista = new ArrayList<Posicao>();
        BancoDados bd = new BancoDados(context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        Cursor cursor = conn.rawQuery("SELECT * FROM posicao", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Posicao posicao = new Posicao();
            posicao.setId(cursor.getInt(0));
            posicao.setLatitude(cursor.getString(1));
            posicao.setLongitude(cursor.getString(2));
            SimpleDateFormat dataformatacao = new SimpleDateFormat("dd/MM/yyyy");
            String dia = cursor.getString(3);
            Date dataformatada = (Date) dataformatacao.parse(dia);
            posicao.setDia(dataformatada);
            String hora = cursor.getString(4);
            SimpleDateFormat horaformatacao = new SimpleDateFormat("HH:mm");
            Time horaformatada = (Time) horaformatacao.parse(hora);
            posicao.setHora(horaformatada);
            posicao.setUsuario_id(cursor.getInt(5));
            posicao.setAtividade_id(cursor.getInt(6));
            lista.add(posicao);
            cursor.moveToNext();
        }
        conn.close();
        return lista;
    }
}
