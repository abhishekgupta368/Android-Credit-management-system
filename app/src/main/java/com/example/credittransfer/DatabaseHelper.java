package com.example.credittransfer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.nio.Buffer;
import java.util.Arrays;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_DATABASE = "Creadit.db";
    public static final String TABLE_NAME = "user_creadit";

    public static final String col_1 = "ID";
    public static final String col_2 = "NAME";
    public static final String col_3 = "CREADIT";

    public DatabaseHelper(Context context) {
        super(context, CREATE_DATABASE, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,CREADIT INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insetData(String name,String cread){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,name.toLowerCase());
        contentValues.put(col_3,cread.toLowerCase());
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cur;
    }

    public  boolean UpdateData(String id,String cred,String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,name.toLowerCase());
        contentValues.put(col_3,cred.toLowerCase());
        db.update(TABLE_NAME,contentValues,"ID= ?",new String[]{id});
        return true;
    }

    public boolean update_credit(String from,Integer cred, String to){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cur1 = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME,null );
        Cursor cur2 = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME,null );
        int flag=0;
        int from_user=0,to_user=0;
        while(cur1.moveToNext()){
            if(from.equals(cur1.getString(cur1.getColumnIndex(col_2)))){
                from_user = cur1.getInt(cur1.getColumnIndex(col_3));
                break;
            }
        }
        while(cur2.moveToNext()){
            if(to.equals(cur2.getString(cur2.getColumnIndex(col_2)))){
                to_user = cur2.getInt(cur2.getColumnIndex(col_3));
                break;
            }
        }
//        if(from_user<=0){
//           return false;
//        }
//        else{
            Integer new_from_user = from_user-cred;
            Integer new_to_user = to_user+cred;
            ContentValues contentValues1 = new ContentValues();
            ContentValues contentValues2 = new ContentValues();
            contentValues1.put(col_3,new_from_user);
            contentValues2.put(col_3,new_to_user);

            int res1 = db.update(TABLE_NAME,contentValues1,col_2+"=?",new String[]{from});
            int res2 = db.update(TABLE_NAME,contentValues2,col_2+"=?",new String[]{to});
            Log.i("TAG", String.valueOf(res1));

            if(res1>0&&res2>0)
                flag=1;
            else{
                flag=0;
            }
        //}
        if(flag==1){
            return true;
        }
        else {
            return false;
        }
    }

}
