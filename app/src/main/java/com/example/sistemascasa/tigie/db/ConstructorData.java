package com.example.sistemascasa.tigie.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.adapter.SubHeadingAdapter;
import com.example.sistemascasa.tigie.pojo.Aladi;
import com.example.sistemascasa.tigie.pojo.AlliancePacific;
import com.example.sistemascasa.tigie.pojo.Chapters;
import com.example.sistemascasa.tigie.pojo.Fractions;
import com.example.sistemascasa.tigie.pojo.Headings;
import com.example.sistemascasa.tigie.pojo.Prosec;
import com.example.sistemascasa.tigie.pojo.Subheadings;
import com.example.sistemascasa.tigie.pojo.Tlc;

import java.util.ArrayList;

/**
 * Created by desarrolloweb on 12/07/16.
 */
public class ConstructorData {

    public void startDb () {
        BaseDatos db = new BaseDatos(context);

        TestAdapter mDbHelper = new TestAdapter(context);
        mDbHelper.createDatabase();
        mDbHelper.open();
        Cursor testdata = mDbHelper.getTestData();
        mDbHelper.close();
    }

    private Context context;
    public ConstructorData (Context context){
        this.context = context;
    }
    ArrayList<Tlc> tlcs;
    ArrayList<Aladi> aladis;
    ArrayList<AlliancePacific> alliancePacifics;
    ArrayList<Fractions> fractionses;

    public ArrayList<Chapters> getChapters ()  {
        BaseDatos db = new BaseDatos(context);
        return db.getAllChapters();
    }

    public ArrayList<Chapters> getChapterByCode (String code) {
        BaseDatos db1 = new BaseDatos(this.context);
        return db1.getChapterByCode(code);
    }

    public Boolean insertFavouites (String fraction) {
        BaseDatos db = new BaseDatos(context);
        Boolean validate = db.insertFavouites(fraction);

        if(validate)
            return true;
        else
            return false;
    }

    public ArrayList<Fractions> getFractionsFav () {
        BaseDatos db = new BaseDatos(context);
        return db.getFavourites();
    }

    public Boolean getFavouritesByFrac (String fraccion) {
        BaseDatos db = new BaseDatos(context);
        return db.getFavouritesByFrac(fraccion);
    }

    public Boolean deleteFavouritesByFrac (String fraccion) {
        BaseDatos db = new BaseDatos(context);
        return db.deleteFavouritesByFrac(fraccion);
    }

    public Boolean insertUserData (String email, String password, int status){
        BaseDatos bd = new BaseDatos(this.context);
        return bd.insertUserData(email, password, status);
    }

    public Boolean deleteUserData () {
        BaseDatos bd = new BaseDatos(this.context);
        return bd.deleteUserData();
    }
}
