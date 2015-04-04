package com.example.markuss.asignaturas;

/**
 * Created by markuss on 2/4/15.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    //private final String dbName = "Horario";

    private final String tabla = "asignaturas";
    private final String materias = "materias";
    private final String profesores = "profesores";
    private final String fechaini = "fechaini";
    private final String fechafin = "fechafin";



    public DatabaseHelper(Context context) {
        super(context, "Horario", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creando tabla de profesores

        createTable(db,this.tabla);
        //addTableColumn(db,this.table3,this.table3Col1,"INTEGER");
        addTableColumn(db,this.tabla,this.materias,"TEXT");
        addTableColumn(db,this.tabla,this.profesores,"TEXT");
        addTableColumn(db,this.tabla,this.fechaini,"TEXT");
        addTableColumn(db,this.tabla,this.fechafin,"TEXT");

    }


    public void createTable(SQLiteDatabase db, String tableName){
        db.execSQL("CREATE TABLE " +tableName);
    }

    public void addTableColumn(SQLiteDatabase db, String tableName, String columnName, String typeColumn){
        db.execSQL("ALTER TABLE " +tableName+" ADD COLUMN "+columnName+" "+typeColumn+";");
    }

    public void insertTable(String data1, String data2, String data3, String data4){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(materias, data1);
        values.put(profesores, data2);
        values.put(fechaini, data3);
        values.put(fechafin, data4);

        db.insert(tabla, null, values);
        db.close();
    }

    public Cursor getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c=db. rawQuery("SELECT * FROM "+this.tabla+";", null);
        return c;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
