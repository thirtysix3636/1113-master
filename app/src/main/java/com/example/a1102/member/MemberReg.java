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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.a1102.HelloActivity;
import com.example.a1102.MainActivity;
import com.example.a1102.R;
import com.example.a1102.dbhelper.DBHelper;

/* 회원가입 폼 액티비티 정의 부분 */
public class MemberReg extends AppCompatActivity implements View.OnClickListener{

    DBHelper dbHelper;


    RadioGroup gender_group; //성별 라디오그룹
    RadioButton rbtn_man, rbtn_woman, rbtn_etc; //라디오 버튼 남자, 여자, 기타
    EditText name_edit, nick_name_edit, id_edit, pwd_edit,
            pwd_edit_check, phone_edit; // 이름, 닉네임, 아이디, 비밀번호, 비밀번호체크, 전화번호
    Button id_check_btn, reg_btn, back_btn; // 중복확인, 회원가입, 뒤로가기 버튼

    String id, name, nick_name, pwd;

    int phone, gender;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_reg);

        /* 버튼 등록 */
        id_check_btn = (Button)findViewById(R.id.member_id_check);
        reg_btn = (Button)findViewById(R.id.member_reg_btn);
        back_btn = (Button)findViewById(R.id.member_reg_back);

        /* Edit Text 설정 */
        name_edit = (EditText)findViewById(R.id.member_reg_name); // 이름 폼
        nick_name_edit = (EditText)findViewById(R.id.member_nickname_reg); // 닉네임 폼
        id_edit = (EditText)findViewById(R.id.member_id_reg); // 아이디 폼
        pwd_edit = (EditText)findViewById(R.id.member_pwd_reg); // 비밀번호 폼
        pwd_edit_check = (EditText)findViewById(R.id.member_pwd_confirm_reg); //비밀번호 체크 폼
        phone_edit  = (EditText)findViewById(R.id.member_phone__reg);// 전화번호 폼

        /* 성별 그룹 */
        gender_group = (RadioGroup)findViewById(R.id.gender_group);

        /* 성별 버튼 */
        rbtn_man = (RadioButton)findViewById(R.id.radio_btn_man);
        rbtn_woman = (RadioButton)findViewById(R.id.radio_btn_woman);
        rbtn_etc = (RadioButton)findViewById(R.id.radio_btn_etc);


        /* 버튼 원클릭 리스너 세팅 */
        id_check_btn.setOnClickListener(this);
        reg_btn.setOnClickListener(this);
        back_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            /* 중복확인 버튼 */
            case R.id.member_id_check:


                break;
            /* 회원가입 버튼 */
            case R.id.member_reg_btn:

                DBHelper dbHelper = new DBHelper(getApplicationContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                id = id_edit.getText().toString();
                name = name_edit.getText().toString();
                nick_name = nick_name_edit.getText().toString();
                pwd = pwd_edit.getText().toString();
                phone = Integer.parseInt(phone_edit.getText().toString());
                gender = gender_group.getCheckedRadioButtonId();

                try {

                    db.execSQL("insert into member (_id, name, nick_name, pwd," +
                            "phone, gender) values ( '" + id + "'," +
                            "'" + name + "'," +
                            "'" + nick_name + "'," +
                            "'" + pwd + "'," +
                            "" + phone + "," +
                            "" + 1 +
                            ")");
                    db = dbHelper.getReadableDatabase();

                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("id_value", id);
                    db.close();
                    startActivity(intent);
                    finish();

                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"양식에 맞춰 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
/*
                String sql=  "select * from member order by _id desc";
                Cursor cursor = db.rawQuery(sql,null);
                while(cursor.moveToNext()){
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    System.out.println(cursor.getString(0));
                    System.out.println(cursor.getString(1));
                }
*/
                break;
            /* 뒤로가기 버튼 */
            case R.id.member_reg_back:
                intent = new Intent(getApplicationContext(), HelloActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                break;
        }
    }
}
