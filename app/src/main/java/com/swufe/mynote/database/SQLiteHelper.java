package com.swufe.mynote.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.swufe.mynote.bean.NoteBean;
import com.swufe.mynote.utils.DBUtils;

import java.util.ArrayList;
import java.util.List;



public class SQLiteHelper extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;
    //创建数据库
    public SQLiteHelper(Context context){
        super(context, DBUtils.DATABASE_NAME,null,DBUtils.DATABASE_VERSION);
        sqLiteDatabase=this.getWritableDatabase();
    }
    //创建表
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE "+DBUtils.DATABASE_TABLE+"("+DBUtils.NOTEPAD_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+DBUtils.NOTE_HEAD +" text, "+DBUtils.NOTE_CONTENT +" text, "+DBUtils.NOTE_TIME +" text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }


    //添加数据
    public boolean InputData(String userHead, String userContent, String time){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DBUtils.NOTE_HEAD,userHead);
        contentValues.put(DBUtils.NOTE_CONTENT,userContent);
        contentValues.put(DBUtils.NOTE_TIME,time);
        return sqLiteDatabase.insert(DBUtils.DATABASE_TABLE,null,contentValues)>0;
    }

    //修改数据
    public boolean UpdateData(String id, String head, String content, String time){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DBUtils.NOTE_HEAD,head);
        contentValues.put(DBUtils.NOTE_CONTENT,content);
        contentValues.put(DBUtils.NOTE_TIME,time);
        String sql=DBUtils.NOTEPAD_ID+"=?";
        String[] strings=new String[]{id};
        return sqLiteDatabase.update(DBUtils.DATABASE_TABLE,contentValues,sql,strings)>0;
    }


    //删除数据
    public boolean DeleteData(String id){
        String sql=DBUtils.NOTEPAD_ID+"=?";
        //连接字符串，易错点!
        String[] contentValuesArray=new String[]{String.valueOf(id)};
        return sqLiteDatabase.delete(DBUtils.DATABASE_TABLE,sql,contentValuesArray)>0;
    }

    //查询数据
    public List<NoteBean> query(){
        List<NoteBean> list=new ArrayList<NoteBean>();
        Cursor cursor=sqLiteDatabase.query(DBUtils.DATABASE_TABLE,null,null,
                null,null,null,DBUtils.NOTEPAD_ID+" desc");
        if (cursor!=null){
            while (cursor.moveToNext()){
                NoteBean nb=new NoteBean();
                String id= String.valueOf(cursor.getInt(cursor.getColumnIndex(DBUtils.NOTEPAD_ID)));
                String head=cursor.getString(cursor.getColumnIndex(DBUtils.NOTE_HEAD));
                String content=cursor.getString(cursor.getColumnIndex(DBUtils.NOTE_CONTENT));
                String time=cursor.getString(cursor.getColumnIndex(DBUtils.NOTE_TIME));
                nb.setNote_id(id);
                nb.setNote_Head(head);
                nb.setNote_Content(content);
                nb.setNote_Time(time);
                list.add(nb);
            }
            cursor.close();
        }
        return list;
    }
}
