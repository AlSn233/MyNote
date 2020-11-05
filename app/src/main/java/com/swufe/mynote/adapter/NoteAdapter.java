package com.swufe.mynote.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.swufe.mynote.R;
import com.swufe.mynote.bean.NoteBean;


import java.util.List;



public class NoteAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;//这个对象用于加载item的布局文件
    private List<NoteBean> list;//list集合是列表中需要显示的集合
    public NoteAdapter(Context context, List<NoteBean> list){
        layoutInflater= LayoutInflater.from(context);
        this.list=list;
    }
    @Override
    public int getCount() {
        return list==null?0:list.size();
    }//获取集合长度

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }//返回集合的内容

    @Override
    public long getItemId(int position) {
        return position;
    }//返回位置信息

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.notepad_item,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        NoteBean noteBean =(NoteBean)getItem(position);
        viewHolder.tvNote_Head.setText(noteBean.getNote_Head());
        viewHolder.tvNote_Content.setText(noteBean.getNote_Content());
        viewHolder.tvNote_Time.setText(noteBean.getNote_Time());
        //viewHolder.tvNotepadTime.setText(getNowTime());
        return convertView;
    }
    class ViewHolder{
        TextView tvNote_Head;
        TextView tvNote_Content;
        TextView tvNote_Time;
        public ViewHolder(View view){
            tvNote_Head =view.findViewById(R.id.item_head);
            tvNote_Content =view.findViewById(R.id.item_content);//记录的内容
            tvNote_Time =view.findViewById(R.id.item_time);//保存记录的时间

        }
    }
//    String getNowTime(){
//        @SuppressLint("SimpleDateFormat")
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
//        //获取当前时间
//        Date date = new Date(System.currentTimeMillis());
//        return simpleDateFormat.format(date);
//    }
}
