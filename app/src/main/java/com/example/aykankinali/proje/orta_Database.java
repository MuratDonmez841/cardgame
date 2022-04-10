package com.example.aykankinali.proje;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class orta_Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "orta_database";
    private static final String TABLE_NAME = "Skorlar_orta";
    private static String KULLANICI_ADI = "kullanici_adi";
    private static String KULLANICI_ID = "id";
    private static String PUAN = "puan";
    private static String SURE="dbsure";
    public orta_Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KULLANICI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KULLANICI_ADI + " TEXT,"
                + PUAN + " TEXT,"
                + SURE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }
    public void kullaniciSil(int id){
        //kullanılmadı
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KULLANICI_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }
    public void orta_skorEkle(String kullanici_adi,String puan,String dbsure) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KULLANICI_ADI, kullanici_adi);
        values.put(PUAN, puan);
        values.put(SURE, dbsure);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public HashMap<String, String> skorDetay(int id){
        //EKLENEBİLİR ŞUAN KULLANMIYORUM
        HashMap<String,String> skor = new HashMap<String,String>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME+ " WHERE id="+id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            skor.put(KULLANICI_ADI, cursor.getString(1));
            skor.put(PUAN, cursor.getString(2));
            skor.put(SURE, cursor.getString(3));
        }
        cursor.close();
        db.close();
        return skor;
    }
    public ArrayList<HashMap<String, String>> orta_skorlar(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> orta_skor = new ArrayList<HashMap<String, String>>();

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0; i<cursor.getColumnCount();i++)
                {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }

                orta_skor.add(map);
            } while (cursor.moveToNext());
        }
        db.close();
        return orta_skor;
    }

    public void orat_tabloTemizle(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Skorlar");
    }
}
