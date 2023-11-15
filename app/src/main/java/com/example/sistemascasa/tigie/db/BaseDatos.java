package com.example.sistemascasa.tigie.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.sistemascasa.tigie.pojo.Chapters;
import com.example.sistemascasa.tigie.pojo.Fractions;
import com.example.sistemascasa.tigie.settings.IconosCapitulos;


import java.util.ArrayList;

/**
 * Created by desarrolloweb on 14/07/16.
 */
public class BaseDatos extends SQLiteOpenHelper {

    private Context context;


    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    public void open() throws SQLException {
        close();
        this.getWritableDatabase();
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    @Override
    /**
     * Se crea la estructura de la base de datos, todas las tablas (Create table)
     */
    public void onCreate(SQLiteDatabase db) {

    }

    /*
    * Se ejecuta cuando se tiene que reestructurar la BD
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public ArrayList<Chapters> getAllChapters() {
        ArrayList<Chapters> chapters = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_TARIFF_CHAPTERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);   //Devolver coleccion de datos del query que consulto  "null" es para definir parametros
        int contador = 1;
        IconosCapitulos iconos = new IconosCapitulos();

        while (registros.moveToNext()) {
            if (contador <= 98) {
                Chapters chapterActual = new Chapters();

                chapterActual.setId(registros.getInt(0));
                chapterActual.setCodigo(registros.getString(1));
                chapterActual.setDescripcion(registros.getString(2));

                int icon = iconos.IconosCapitulos(registros.getString(1));
                chapterActual.setIcono(icon);
                chapters.add(chapterActual);
            }
            contador++;
        }

        db.close();
        return chapters;
    }

    public ArrayList<Chapters> getChapterByCode (String code) {
        ArrayList<Chapters> chapters = new ArrayList<Chapters>();
        try {
            String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_TARIFF_CHAPTERS + " WHERE " + ConstantesBaseDatos.TABLE_TARIFF_CHAPTERS_CODE + " = '" + code + "' ;";
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor registros = db.rawQuery(query, null);
            int counter = 1;
            while (registros.moveToNext()) {
                if (counter == 1) {
                    Chapters chapter= new Chapters();
                    chapter.setId(registros.getInt(0));
                    chapter.setCodigo(registros.getString(1));
                    chapter.setDescripcion(registros.getString(2));
                    chapters.add(chapter);
                }
                counter++;
            }
            db.close();
        } catch (Exception e) {

        }
        return chapters;
    }



    public Boolean insertFavouites(String fraction) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fraccion", fraction);
        db.insert("Favoritos", null, contentValues);
        db.close();
        return true;
    }

    public Boolean insertUserData(String email, String password, int status) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email_usuario",  email.toString());
        contentValues.put("password_usuario",  password.toString());
        contentValues.put("status_usuario",  1);
        db.insert("user_data", null, contentValues);
        db.close();
        return true;
    }

    public Boolean deleteUserData (){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String queryA = "DELETE FROM user_data ;";
            db.execSQL(queryA);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            db.close();
        }
    }


    public ArrayList<Fractions> getFavourites () {
        ArrayList<Fractions> fractions = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_FAVORITOS ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()) {
            String queryFrac = "SELECT * "
                            + " FROM "
                            + ConstantesBaseDatos.TABLE_TARIFF_FRACTIONS + " FR "
                            + "  WHERE FR."
                            + ConstantesBaseDatos.TABLE_TARIFF_FRACTIONS_CODE + " = '" + registros.getString(1) + "'" ;

            Cursor registros2 = db.rawQuery(queryFrac, null);

            while (registros2.moveToNext()) {
                int id = registros2.getInt(0);
                fractions.add(new Fractions(registros2.getInt(0), registros2.getInt(1), registros2.getInt(2), registros2.getInt(3), registros2.getString(4), registros2.getString(5), registros2.getInt(7)));
            }
        }
        db.close();
        return fractions;
    }

    public Boolean getFavouritesByFrac (String fraccion) {
        Boolean resp = false;
        try {
            String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_FAVORITOS + " WHERE fraccion = '" + fraccion + "';";
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor registros  = db.rawQuery(query, null);
            while (registros.moveToNext()) {
                resp = true;
            }
            db.close();
        } catch (Exception e) {
            resp = false;
        }
        return resp;
    }

    public Boolean deleteFavouritesByFrac (String fraccion) {
        Boolean resp = false;
        try {
            String query = "DELETE FROM " + ConstantesBaseDatos.TABLE_FAVORITOS + " WHERE fraccion = '" + fraccion + "' ;";
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(query);
            resp = true;
            db.close();
        } catch (Exception e) {
            resp = false;
        }
        return resp;
    }

    public ArrayList<Object> getUserData (){
        ArrayList<Object> userData = new ArrayList <Object>();
        String query = "SELECT email_usuario, password_usuario, status_usuario FROM user_data WHERE status_usuario = 1";

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor data = DB.rawQuery(query, null);

        while (data.moveToNext()) {
            userData.add(data.getString(0));
            userData.add(data.getString(1));
            userData.add(data.getInt(2));
        }
        DB.close();
        return userData;
    }

}

