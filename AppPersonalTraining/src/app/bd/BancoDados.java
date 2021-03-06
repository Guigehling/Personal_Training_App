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
        sqld.execSQL("CREATE TABLE usuario (id INTEGER, nome TEXT, email TEXT, senha TEXT, dataNascimento TEXT, sexo TEXT, peso NUMERIC);");
        sqld.execSQL("CREATE TABLE atividade (id INTEGER PRIMARY KEY AUTOINCREMENT, dia TEXT, tempo TEXT, concluida INTEGER, usuario_id INTEGER, "
                + "FOREIGN KEY (usuario_id) REFERENCES usuario (id));");
        sqld.execSQL("CREATE TABLE posicao (id INTEGER PRIMARY KEY AUTOINCREMENT, latitude TEXT, longitude TEXT, dia TEXT, hora TEXT, usuario_id INTEGER, atividade_id INTEGER, ultimaposicao INTEGER, "
                + " FOREIGN KEY (usuario_id) REFERENCES usuario (id), FOREIGN KEY (atividade_id) REFERENCES atividade (id));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqld, int i, int i1) {
        sqld.execSQL("DROP TABLE IF EXISTS usuario;");
        sqld.execSQL("DROP TABLE IF EXISTS posicao;");
        sqld.execSQL("DROP TABLE IF EXISTS atividade;");
        this.onCreate(sqld);
    }
}
