package com.swufe.mynote.bean;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotepadBean {
    private String id;
    private String notepadHead;//记录的标题
    private String notepadContent;//记录的内容
    private String notepadTime;//保存记录的时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotepadHead() {
        return notepadHead;
    }
    public void setNotepadHead(String notepadHead) {
        this.notepadHead = notepadHead;
    }

    public String getNotepadContent() {
        return notepadContent;
    }

    public void setNotepadContent(String notepadContent) {
        this.notepadContent = notepadContent;
    }

    public String getNotepadTime() {
//        @SuppressLint("SimpleDateFormat")
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
//        //获取当前时间
//        Date date = new Date(System.currentTimeMillis());
//        return notepadTime=simpleDateFormat.format(date);
        return notepadTime;
    }

    public void setNotepadTime(String notepadTime) {
        this.notepadTime = notepadTime;
    }
}
