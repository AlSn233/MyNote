package com.swufe.mynote;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.swufe.mynote.adapter.NoteAdapter;
import com.swufe.mynote.bean.NoteBean;
import com.swufe.mynote.database.SQLiteHelper;

import java.util.List;



public class NoteActivity extends AppCompatActivity {
    private ListView listView;
    private SQLiteHelper mySQLiteHelper;
    private List<NoteBean> list;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview);
        ImageView imageView=findViewById(R.id.add);
        initData();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NoteActivity.this,RecordActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }
    public void initData(){
        mySQLiteHelper =new SQLiteHelper(this);
        showQueryData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id ){
                NoteBean nb=list.get(position);
                Intent intent=new Intent(NoteActivity.this,RecordActivity.class);
                intent.putExtra("id",nb.getNote_id());
                intent.putExtra("head",nb.getNote_Head());
                intent.putExtra("content",nb.getNote_Content());
                intent.putExtra("time",nb.getNote_Time());
                //intent.putExtra("time",getNowTime());
                NoteActivity.this.startActivityForResult(intent,1);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog dialog;
                AlertDialog.Builder builder=new AlertDialog.Builder(NoteActivity.this)
                        .setMessage("是否删除此记录?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                NoteBean notepadBean=list.get(position);
                                if(mySQLiteHelper.DeleteData(notepadBean.getNote_id())){
                                    list.remove(position);
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(NoteActivity.this,"删除成功", Toast.LENGTH_LONG).show();
                                }

                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                dialog=builder.create();
                dialog.show();
                return true;
            }
    });
}

//    String getNowTime(){
//        @SuppressLint("SimpleDateFormat")
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
//        //获取当前时间
//        Date date = new Date(System.currentTimeMillis());
//        return simpleDateFormat.format(date);
//    }

    private void showQueryData(){
        if(list!=null){
            list.clear();
        }
        list= mySQLiteHelper.query();
        adapter=new NoteAdapter(this,list);
        listView.setAdapter(adapter);
    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==1&&resultCode==2){
            showQueryData();
        }
    }
}
