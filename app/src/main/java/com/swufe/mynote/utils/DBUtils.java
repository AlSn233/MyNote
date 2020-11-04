package com.swufe.mynote.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DBUtils {
    public static final String DATABASE_NAME="MyNote";//数据库名
    public static final String DATABASE_TABLE="Note";//表名
    public static final int DATABASE_VERSION =1;//数据库版本
    //数据库表中的列名
    public static String NOTEPAD_ID="id";
    public static final String NOTE_HEAD ="head";
    public static final String NOTE_CONTENT ="content";
    public static final String NOTE_TIME ="time";
    //获取当前日期
    public static final String getNowTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date=new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

}
