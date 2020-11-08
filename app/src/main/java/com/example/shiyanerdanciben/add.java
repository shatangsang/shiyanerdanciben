package com.example.shiyanerdanciben;


import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class add extends AppCompatActivity implements View.OnClickListener {
    String Title,Content,simpleDate;
    Button ButtonAddCancel,ButtonAddSave;
    EditText EditTextAddTitle,EditTextAddContent,EditTextAddAuthor;
    String Author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ButtonAddCancel = (Button)findViewById(R.id.ButtonAddCancel);
        ButtonAddSave = (Button)findViewById(R.id.ButtonAddSave);
        EditTextAddContent = findViewById(R.id.EditTextAddContent);
        EditTextAddTitle = findViewById(R.id.EditTextAddTitle);
        EditTextAddAuthor = findViewById(R.id.EditTextAddAuthor);
        ButtonAddCancel.setOnClickListener(this);
        ButtonAddSave.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        database database = new database(this,"Note.db",null,1);
        SQLiteDatabase db = database.getWritableDatabase();
        switch (v.getId()) {
            case R.id.ButtonAddSave:
                Date date = new Date();
                DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        //配置时间格式
                simpleDate = simpleDateFormat.format(date);
                ContentValues values = new ContentValues();
                Title = String.valueOf(EditTextAddTitle.getText());         //获取需要储存的值
                Content = String.valueOf(EditTextAddContent.getText());
                Log.d("Title",Title);
                if(Title.length()==0){               //标题为空给出提示
                    Toast.makeText(this, "请输入一个标题", Toast.LENGTH_LONG).show();
                }else {
                    values.put("title", Title);
                    values.put("content", Content);
                    values.put("date", simpleDate);
                    db.insert("Note", null, values);                 //将值传入数据库中
                    add.this.setResult(RESULT_OK, getIntent());
                    add.this.finish();
                }


                Author = String.valueOf(EditTextAddAuthor.getText());
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("author",Author);      //使用sharedperferences设置默认作者
                editor.apply();
                break;

            case R.id.ButtonAddCancel:
                add.this.setResult(RESULT_OK,getIntent());
                add.this.finish();

                break;
        }


    }
}
