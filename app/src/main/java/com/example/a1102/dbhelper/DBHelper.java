package com.example.a1102.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jeff_Hwang on 2017. 11. 13..
 */
public class DBHelper extends SQLiteOpenHelper {
    String list[] = { "전체 게시판","자유 게시판", " 데스크탑","노트북/맥북/넷북","태블릿 PC","CPU/RAM/메인보드","HDD/SSD/ODD", "VGA","파워서플라이", "주변기기", "무료 나눔",
                    "남성 악세사리", "여성 악세사리", "남성 의류", "여성 의류", "SKT 중고폰", "KT 중고폰", "LG 중고폰", "스마트폰 주변기기", "음향 기기", "가전 제품", "생활용품"};

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context) {
        super(context, "test.db", null, 1);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
    //테이블 생성
        String member, board, category, chat, exchange, location, exchange_reserve; //회원,게시판, 채팅, 거래, 위치 거래예약

        /* 회원 테이블 sql 정의 및 실행 */
        member = "create table if not exists member(_id text not null primary key," +
                "name text not null," +
                "nick_name text not null," +
                "pwd text not null," +
                "phone text not null," +
                "gender integer not null)";
        db.execSQL(member);


        board = "create table if not exists board(board_id integer not null primary key, " +
                "board_title text not null," +  //게시글 제목
                "board_name text not null," + // 작성자 이름
                "board_price integer not null default 0," + //제품 가격
                "board_content text not null,"+ //본문 내용
                "board_location_x integer," + //지도 경도
                "board_location_y integer," + // 지도 위도
                "board_reply_id integer not null)"; // 댓글
        db.execSQL(board);

        category = "create table if not exists category(cat_id integer not null primary key auto increment," +
                "cat_name text not null)"; // 카테고리
        db.execSQL(category);

   /*   String insert_cat_list = "insert into category(cat_name) values ";

        for(int num = 0; num<21; num++)
            db.execSQL(insert_cat_list+"('"+list[num]+"')");
*/

    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String create_at, String item, int price) {
        // 읽고 쓰기가 가능하게 DB 열기
    }

    public void update(String item, int price) {
        //업데이트
    }

    public void delete(String item) {
        //삭제
    }

    public void getResult() {
        // 읽기가 가능하게 DB 열기

    }
}


