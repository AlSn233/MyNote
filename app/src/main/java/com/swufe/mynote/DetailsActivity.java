package com.swufe.mynote;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.swufe.mynote.database.SQLiteHelper;
import com.swufe.mynote.utils.DBUtils;


public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{

    TextView note_time;
    EditText note_content;
    TextView note_name;
    EditText note_head;
    ImageView note_delete;
    ImageView note_save;
    ImageView note_back;

    private SQLiteHelper mSQLiteHelper;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        note_name = findViewById(R.id.note_name);//标题的名称
        note_time= findViewById(R.id.tv_time);//保存内容的时间
        note_head = findViewById(R.id.head_content);//标题内容
        note_content = findViewById(R.id.note_content);//便签的具体内容
        note_delete = findViewById(R.id.delete);//清空的按钮
        note_save= findViewById(R.id.note_save);//保存的按钮
        note_back= findViewById(R.id.note_back);//返回键

        note_delete.setOnClickListener(this);
        note_save.setOnClickListener(this);
        note_back.setOnClickListener(this);
        Note_ShowContent();

    }
    public void Note_ShowContent(){       //显示添加界面还是修改界面
        mSQLiteHelper=new SQLiteHelper(this);
        note_name.setText("具体内容");
        Intent intent=getIntent();
        if(intent!=null){
            id=intent.getStringExtra("id");
            if(id!=null){
                note_name.setText("修改内容");
                note_head.setText(intent.getStringExtra("head"));
                note_content.setText(intent.getStringExtra("content"));
                note_time.setText(intent.getStringExtra("time"));
                note_time.setVisibility(View.VISIBLE);
                note_time.setTextColor(Color.parseColor("#808080"));
            }
        }
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.note_back:
                finish();
                break;
            case R.id.delete:
                note_content.setText(" ");
                note_head.setText("");
                break;
            case R.id.note_save:
                String headContent = note_head.getText().toString().trim();
                String noteContent = note_content.getText().toString().trim();
                if(id==null){
                    //添加内容的功能
                    if(noteContent.length()>0 && headContent.length()>0){
                        if (mSQLiteHelper.InputData(headContent,noteContent,DBUtils.getNowTime())){
                            showToast("保存成功");
                            setResult(2);
                            finish();
                        }else{
                            showToast("保存失败");
                        }
                    } else{
                        showToast("保存的内容不能为空");
                    }

                }else{
            //修改内容的功能
            if(noteContent.length()>0 && headContent.length()>0){
                if (mSQLiteHelper.UpdateData(id,headContent,noteContent,DBUtils.getNowTime())){
                    showToast("修改成功");
                    setResult(2);
                    finish();
                }else{
                    showToast("修改失败");
                }
            } else{
                showToast("修改的内容不能为空");
                }
                break;
        }
    }
    }
    public void showToast(String message){
        Toast.makeText(DetailsActivity.this,message, Toast.LENGTH_LONG).show();
    }
}
