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
import app.bean.Atividade;
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
public class AtividadeDAO {

    private Context context;

    public void create(Atividade atividade) {
        BancoDados bd = new BancoDados(context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("id", atividade.getId());
        SimpleDateFormat dataformatacao = new SimpleDateFormat("dd/MM/yyyy");
        String dataformatada = dataformatacao.format(atividade.getDia());
        valores.put("dia", dataformatada);
        SimpleDateFormat tempoformatacao = new SimpleDateFormat("HH:mm");
        String tempoformatado = tempoformatacao.format(atividade.getTempo());
        valores.put("hora", tempoformatado);
        valores.put("usuario_id", atividade.getUsuario_id());
        valores.put("distancia", atividade.getDistancia());
        valores.put("velocidade", atividade.getVelocidade());
        conn.insert("atividade", null, valores);
        conn.close();
    }

    public Atividade retrive() throws ParseException {
        Atividade ativi = new Atividade();
        BancoDados bd = new BancoDados(context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        Cursor cursor = conn.rawQuery("SELECT * FROM atividade", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Atividade atividade = new Atividade();
            atividade.setId(cursor.getInt(0));
            String dia = cursor.getString(1);
            SimpleDateFormat dataformatacao = new SimpleDateFormat("dd/MM/yyyy");
            Date dataformatada = (Date) dataformatacao.parse(dia);
            atividade.setDia(dataformatada);
            String tempo = cursor.getString(2);
            SimpleDateFormat tempoformatacao = new SimpleDateFormat("HH:mm");
            Time tempoformatada = (Time) tempoformatacao.parse(tempo);
            atividade.setTempo(tempoformatada);
            atividade.setUsuario_id(cursor.getInt(3));
            atividade.setDistancia(cursor.getDouble(4));
            atividade.setVelocidade(cursor.getDouble(5));
            ativi = atividade;
            cursor.moveToNext();
        }
        conn.close();
        return ativi;
    }

    public void update(Atividade atividade) {
        BancoDados bd = new BancoDados(context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("id", atividade.getId());
        SimpleDateFormat dataformatacao = new SimpleDateFormat("dd/MM/yyyy");
        String dataformatada = dataformatacao.format(atividade.getDia());
        valores.put("dia", dataformatada);
        SimpleDateFormat tempoformatacao = new SimpleDateFormat("HH:mm");
        String tempoformatado = tempoformatacao.format(atividade.getTempo());
        valores.put("hora", tempoformatado);
        valores.put("usuario_id", atividade.getUsuario_id());
        valores.put("distancia", atividade.getDistancia());
        valores.put("velocidade", atividade.getVelocidade());
        conn.update("atividade", valores, null, null);
        conn.close();
    }

    public void delete() {
        BancoDados bd = new BancoDados(context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        conn.delete("atividade", null, null);
    }

    public List<Atividade> listAll() throws ParseException {
        List<Atividade> lista = new ArrayList<Atividade>();
        BancoDados bd = new BancoDados(context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        Cursor cursor = conn.rawQuery("SELECT * FROM atividade", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Atividade atividade = new Atividade();
            atividade.setId(cursor.getInt(0));
            String dia = cursor.getString(1);
            SimpleDateFormat dataformatacao = new SimpleDateFormat("dd/MM/yyyy");
            Date dataformatada = (Date) dataformatacao.parse(dia);
            atividade.setDia(dataformatada);
            String tempo = cursor.getString(2);
            SimpleDateFormat tempoformatacao = new SimpleDateFormat("HH:mm");
            Time tempoformatada = (Time) tempoformatacao.parse(tempo);
            atividade.setTempo(tempoformatada);
            atividade.setUsuario_id(cursor.getInt(3));
            atividade.setDistancia(cursor.getDouble(4));
            atividade.setVelocidade(cursor.getDouble(5));
            lista.add(atividade);
            cursor.moveToNext();
        }
        conn.close();
        return lista;
    }
}
