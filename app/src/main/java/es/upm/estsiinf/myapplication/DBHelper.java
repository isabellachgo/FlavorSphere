package es.upm.estsiinf.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    static String TABLE_NAME = "favoritos";
    static String[] COLUMNS = {"titulo", "ingredientes", "pasos", "minutos", "personas", "likes"};
    static String DB_NAME = "favoritosDB.bd";
    static int VERSION_ACTUAL = 1;
    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION_ACTUAL);
    }

    public DBHelper(Context ctx, String nombreDB){
        super(ctx, nombreDB, null, VERSION_ACTUAL);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crea la tabla 'favoritos' en la base de datos
        db.execSQL("CREATE TABLE favoritos (titulo TEXT PRIMARY KEY, ingredientes TEXT, pasos TEXT, minutos TEXT, personas TEXT, likes TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
