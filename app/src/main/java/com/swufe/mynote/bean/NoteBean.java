package com.swufe.mynote.bean;

public class NoteBean {
    private String note_id;
    private String note_Head;//记录的标题
    private String note_Content;//记录的内容
    private String note_Time;//保存记录的时间

    public String getNote_id() {
        return note_id;
    }

    public void setNote_id(String note_id) {
        this.note_id = note_id;
    }

    public String getNote_Head() {
        return note_Head;
    }
    public void setNote_Head(String note_Head) {
        this.note_Head = note_Head;
    }

    public String getNote_Content() {
        return note_Content;
    }

    public void setNote_Content(String note_Content) {
        this.note_Content = note_Content;
    }

    public String getNote_Time() {
//        @SuppressLint("SimpleDateFormat")
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
//        //获取当前时间
//        Date date = new Date(System.currentTimeMillis());
//        return notepadTime=simpleDateFormat.format(date);
        return note_Time;
    }

    public void setNote_Time(String note_Time) {
        this.note_Time = note_Time;
    }
}
