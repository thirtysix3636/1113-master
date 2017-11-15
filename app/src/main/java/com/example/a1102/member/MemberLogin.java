package com.example.a1102.member;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a1102.HelloActivity;
import com.example.a1102.MainActivity;
import com.example.a1102.R;
import com.example.a1102.dbhelper.DBHelper;

/**
 * Created by Jeff_Hwang on 2017. 11. 13..
 */

/* 로그인 Activiy 설정 */
public class MemberLogin extends AppCompatActivity implements View.OnClickListener{
    EditText id_edit, pwd_edit; // 아이디, 패스워드 폼
    Button login_btn, back_btn; // 로그인, 뒤로가기 버튼
    DBHelper dbHelper;
    SQLiteDatabase db;
    String sql , id, pwd;
    Cursor cursor;
    String pwd_confirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_login);

        /* editText 세팅*/
        id_edit = (EditText)findViewById(R.id.member_id_login); //아이디 입력
        pwd_edit = (EditText)findViewById(R.id.member_pwd_login); //비밀번호 입력

        /* 버튼 세팅*/
        login_btn = (Button)findViewById(R.id.member_login_btn);
        back_btn = (Button)findViewById(R.id.member_login_back);

        /* 원클릭리스너 세팅 */
        login_btn.setOnClickListener(this);
        back_btn.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()){
            /* 로그인 버튼 */
            case R.id.member_login_btn:
                dbHelper = new DBHelper(getApplicationContext());
                db = dbHelper.getReadableDatabase();
                id = id_edit.getText().toString();
                pwd = pwd_edit.getText().toString();

                sql = "select * from member where _id = '"+id+"'";
                try {
                    cursor = db.rawQuery(sql, null);

                    while (cursor.moveToNext()) {
                        pwd_confirm = cursor.getString(3);
                    }

                    if (pwd_confirm.equals(pwd)) {
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("id_put", id);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;

            /* 뒤로가기 버튼 */
            case R.id.member_login_back:
                intent = new Intent(getApplicationContext(), HelloActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                break;
        }
    }
}
