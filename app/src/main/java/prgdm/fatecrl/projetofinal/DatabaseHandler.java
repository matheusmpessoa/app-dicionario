package prgdm.fatecrl.projetofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dan on 17/11/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MylibDicionarioBD";
    private static final String TABLE_DICIONARIO = "tbDicionario";
    private static final String KEY_ID = "id";
    private static final String KEY_PALAVRA = "palavra";
    private static final String KEY_SIGNIFICADO = "significado";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHandler(Context ctx) {

        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        String CREATE_DICIONARIO_TABLE =
                "CREATE TABLE " + TABLE_DICIONARIO + "("
                        + KEY_ID + " INTEGER PRIMARY KEY,"
                        + KEY_PALAVRA + " TEXT,"
                        + KEY_SIGNIFICADO + " TEXT" + ")";
        bd.execSQL(CREATE_DICIONARIO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd,
                          int velhaVersao, int novaVersao) {
        bd.execSQL("DROP TABLE IF EXISTS " + TABLE_DICIONARIO);
        onCreate(bd);
    }

    public void inserir(Dicionario dicionario) { //pega um objeto e transforma pro relacional
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(KEY_PALAVRA, dicionario.getPalavra());
        valores.put(KEY_SIGNIFICADO, dicionario.getSignificado());
        db.insert(TABLE_DICIONARIO, null, valores); //manda p/ relacional
        db.close();
    }

    public void atualizar(Dicionario dicionario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(KEY_PALAVRA, dicionario.getPalavra());
        valores.put(KEY_SIGNIFICADO, dicionario.getSignificado());
        db.update(TABLE_DICIONARIO, valores, KEY_PALAVRA +
                " = ?", new String[]{"" + dicionario.getPalavra()});
        db.close();
    }


    public void deletar(Dicionario dicionario) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DICIONARIO, KEY_PALAVRA + " = ?",
                new String[]{String.valueOf(dicionario.getPalavra())});
        //db.delete(TABLE_DICIONARIO, KEY_ID + " = ?",
        //        new String[]{String.valueOf(dicionario.getId())});
        db.close();
    }


    public List<Dicionario> buscar() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Dicionario> list = new ArrayList<Dicionario>();
        String[] colunas = new String[]{"id", "palavra", "significado"};
        Cursor cursor = db.query("tbDicionario", colunas, null, null, null, null, "id ASC");
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Dicionario d = new Dicionario();
                d.setId(cursor.getLong(0));
                d.setPalavra(cursor.getString(1));
                d.setSignificado(cursor.getString(2));
                list.add(d);
            } while (cursor.moveToNext());
        }
        return (list);
    }
}

