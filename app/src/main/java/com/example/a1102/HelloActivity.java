package com.example.a1102;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.a1102.member.MemberLogin;
import com.example.a1102.member.MemberReg;

/**
 * Created by 황윤후 on 2017-11-14.
 */

public class HelloActivity extends AppCompatActivity implements View.OnClickListener{
    Button login_btn, join_btn; // 로그인 버튼, 회원가입 버튼

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello);
        /* 버튼 정의 부분 */
        login_btn = (Button)findViewById(R.id.hello_login_btn);
        join_btn = (Button)findViewById(R.id.hello_join_btn);

        login_btn.setOnClickListener(this);
        join_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            Intent intent;
        switch (v.getId()){

            case R.id.hello_login_btn: // 로그인 화면으로 넘어가기
                intent = new Intent(getApplicationContext(), MemberLogin.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                break;

            case R.id.hello_join_btn: // 회원가입 화면으로 넘어가기
                intent  = new Intent(getApplicationContext(), MemberReg.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                break;
        }
    }
}
