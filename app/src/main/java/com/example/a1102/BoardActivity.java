package com.example.a1102;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by 황윤후 on 2017-11-05.
 */

public class BoardActivity extends AppCompatActivity implements View.OnClickListener{
    Button board_call_btn, board_msg_btn, board_chat_btn, board_writer_list_btn;
    String phone_num;

    DrawerLayout drawerLayout;
    LinearLayout main_top_layout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);

        //게시글 버튼 목록
        board_call_btn=(Button)findViewById(R.id.board_call); //전화버튼
        board_msg_btn=(Button)findViewById(R.id.board_msg); //문자버튼
        board_chat_btn=(Button)findViewById(R.id.board_chat); //채팅버튼
        board_writer_list_btn=(Button)findViewById(R.id.board_writer_list);//작성자글목록 버튼


        //layout 세팅 부
        main_top_layout = (LinearLayout)findViewById(R.id.main_top_layout);
        drawerLayout = (DrawerLayout) findViewById(R.id.left_Drawer);


        //버튼 set 부분
        board_call_btn.setOnClickListener(this);
        board_msg_btn.setOnClickListener(this);
        board_chat_btn.setOnClickListener(this);
        board_writer_list_btn.setOnClickListener(this);

        //전화번호 등록
        phone_num = "010-9916-3363";


        //drawerLisner 세팅 부분
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                main_top_layout.setVisibility(View.INVISIBLE);
                Button drawer_user_nick = (Button)findViewById(R.id.drawer_user_nickName);

                drawer_user_nick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()){
                            case R.id.drawer_user_nickName:
                                Intent intent = new Intent(getApplicationContext(), MyInfo.class);
                                startActivity(intent);
                                overridePendingTransition(0,0);
                                break;
                        }
                    }
                });
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                main_top_layout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }

    //onclik 정의
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.board_call: //전화
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+phone_num));
                startActivity(intent);
                break;
            case R.id.board_msg: //문자
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"+phone_num));
                startActivity(intent);
                break;
            case R.id.board_chat: //채팅

                break;
            case R.id.board_writer_list: //게시글목록
                
                break;
        }

    }
}
