package es.upm.estsiinf.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class FavoritosDB {
    private DBHelper DBHelper;
    private SQLiteDatabase db;

    public FavoritosDB(Context ctx){
        DBHelper = new DBHelper(ctx);
        db = DBHelper.getReadableDatabase();
    }

    public FavoritosDB(Context ctx, String nombreDB){
        DBHelper = new DBHelper(ctx, nombreDB);
        db = DBHelper.getReadableDatabase();
    }

    public static FavoritosDB obtenerInstancia (Context ctx, String nombreDB){
        return new FavoritosDB(ctx, nombreDB);
    }

    public void guardarReceta (String titulo, String ingredientes, String pasos, String minutos, String personas, String likes){
        ContentValues values = new ContentValues();
        values.put("titulo", titulo);
        values.put("ingredientes", ingredientes);
        values.put("pasos", pasos);
        values.put("minutos", minutos);
        values.put("personas", personas);
        values.put("likes", likes);
        db.insert("favoritos", null, values);
    }

    public ArrayList<String[]> verReceta (){
        ArrayList<String[]> list = new ArrayList<>();
        Cursor cursor = db.query(DBHelper.TABLE_NAME, DBHelper.COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String[] receta = new String[6];
            receta[0]= cursor.getString(0);
            receta[1]= cursor.getString(1);
            receta[2]= cursor.getString(2);
            receta[3]= cursor.getString(3);
            receta[4]= cursor.getString(4);
            receta[5]= cursor.getString(5);

            Log.i("FavoritosDB", "VER RECETA: "+receta[0]+" "+receta[1]+" "+receta[2]+" "+receta[3]+" "+receta[4]+" "+receta[5]);

            list.add(receta);
            cursor.moveToNext();
        }

        cursor.close();
        return list;
    }

    public void eliminarReceta (String titulo){
        db.delete("favoritos", "titulo=?", new String[]{titulo});
    }

    public boolean recetaIncliuda (String titulo){
        Cursor cursor = db.query(DBHelper.TABLE_NAME, DBHelper.COLUMNS, "titulo=?", new String[]{titulo}, null, null, null);
        if(cursor.moveToFirst() == false) return false;
        else return true;
    }
}
