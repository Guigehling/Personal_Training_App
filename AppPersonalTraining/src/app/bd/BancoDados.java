/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 * @author Guilherme Gehling
 */
public class BancoDados extends SQLiteOpenHelper {

    public BancoDados(Context context) {
        super(context, "BDApp", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqld) {
        sqld.execSQL("CREATE TABLE usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, email TEXT NOT NULL, senha TEXT NOT NULL);");
        sqld.execSQL("CREATE TABLE movimento (id INTEGER PRIMARY KEY AUTOINCREMENT,latitudePartida TEXT, latitudeChegada TEXT, longitudePartida TEXT, longitudeChegada TEXT, distancia REAL, velocidade REAL, dia, NUMERIC, hora NUMERIC);");
        sqld.execSQL("CREATE TABLE atividade (id INTEGER PRIMARY KEY AUTOINCREMENT, dia NUMERIC, hora NUMERIC, id_usuario INTEGER, FOREIGN KEY (id_usuario) REFERENCES usuario (id));");
        sqld.execSQL("CREATE TABLE atividade_movimento (id_atividade INTEGER, id_movimento INTEGER, FOREIGN KEY(id_atividade) REFERENCES atividade(id) ON DELETE CASCADE, FOREIGN KEY(id_movimento) REFERENCES movimento(id) ON DELETE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqld, int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
